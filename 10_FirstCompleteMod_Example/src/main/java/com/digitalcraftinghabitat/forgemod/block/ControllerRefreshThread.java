package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.event.consumer.ValueUpdateEvent;
import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by christopher on 27/09/15.
 */
public class ControllerRefreshThread implements Runnable{

    DCHConfiguration dchConfiguration;
    ValueUpdateEvent valueUpdateEvent;
    DatahubClientConnector connector;
    ScanParams scanParams;
    private World world;
    private static boolean run = false;
    static Thread runner;
    NavigableMap<Integer, Integer> myMap;
    int lightvalue;
    int lightvalue_max;
    int lightvalue_min;
    static DatahubClientConnector datahubClientConnector;

    public ControllerRefreshThread(World world) {
        this.world = world;
        dchConfiguration = DCHConfiguration.getInstance();
        connector = new DatahubClientConnector(dchConfiguration);
        if (datahubClientConnector == null) {
            datahubClientConnector = new DatahubClientConnector();
        }
        myMap = new TreeMap<Integer, Integer>();
        run = true;
    }



    @Override
    public void run() {

        int keyValue;
        while (run) {

            scanParams = new ScanParams();
            scanParams.match(dchConfiguration.getJedis_prefix() + "*");
//            scanParams.match("*");
            ScanResult<String> scanResult = connector.getJedis().scan("0", scanParams);
            List<String> keys = scanResult.getResult();

            Iterator<String> keyIterator = keys.iterator();
            System.out.println(keys.size() + " Anzahl KEYS");

            while (keyIterator.hasNext()) {
                String id = keyIterator.next();
                keyValue = connector.getIntValueForKey(id);

                valueUpdateEvent = new ValueUpdateEvent(this.world);
                valueUpdateEvent.setValue(keyValue);
                valueUpdateEvent.setId(id);

                MinecraftForge.EVENT_BUS.post(valueUpdateEvent);
            }

            if (myMap.isEmpty()) updateTimeRange();
            setTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void setTime() {
        int time;
        int lightvalue = datahubClientConnector.getIntValueForKey("light_sensor");
        int lightvalue_max = datahubClientConnector.getIntValueForKey("light_sensor_max");
        int lightvalue_min = datahubClientConnector.getIntValueForKey("light_sensor_min");

        if (lightvalue > lightvalue_max) {
            datahubClientConnector.setIntValueForKey("light_sensor_max", lightvalue);
            lightvalue_max = lightvalue;
        }
        if (lightvalue < lightvalue_min) {
            datahubClientConnector.setIntValueForKey("light_sensor_min", lightvalue);
            lightvalue_min = lightvalue;
        }

        if (lightvalue_max == lightvalue_min) return;

        time = myMap.get(myMap.ceilingKey(lightvalue));
        world.setWorldTime(time);
    }

    public void updateTimeRange() {
        int lightvalue = datahubClientConnector.getIntValueForKey("light_sensor");
        int lightvalue_max = datahubClientConnector.getIntValueForKey("light_sensor_max");
        int lightvalue_min = datahubClientConnector.getIntValueForKey("light_sensor_min");
        if (lightvalue > lightvalue_max) {
            datahubClientConnector.setIntValueForKey("light_sensor_max", lightvalue);
            lightvalue_max = lightvalue;
        }
        if (lightvalue < lightvalue_min) {
            datahubClientConnector.setIntValueForKey("light_sensor_min", lightvalue);
            lightvalue_min = lightvalue;
        }
        int range = lightvalue_max-lightvalue_min;
        myMap.put((int)(range+lightvalue_min), 18000); // Mitternacht
        myMap.put((int)(range*0.1+lightvalue_min), 22550); // Beginn Morgenrot, es wird etwas heller am Horizont
        myMap.put((int)(range*0.15+lightvalue_min), 22925); // Sonne erscheint am Horizont
        myMap.put((int)(range*0.2+lightvalue_min), 23400); // Mond komplett hinter dem Horizont verschwunden Zombies beginnen zu brennen
        myMap.put((int)(range*0.4+lightvalue_min), 24000); // Beginn des Minecraft-Tages
        myMap.put((int)(range*0.6+lightvalue_min), 450); // Ende Morgenrot, kein Farbunterschied mehr am Horizont
        myMap.put((int)(range*0.7+lightvalue_min), 1000); // Befehl "/time set day"
        myMap.put((int)(range*0.8+lightvalue_min), 4284); // Lichtlevel 15 wird erreicht
        myMap.put((int)(range*0.9+lightvalue_min), 6000); // Mittag, Sonne steht im Zenit
    }

    public static void init(World world) {
        if (world.isRemote){
            return;
        }
        if (runner == null){
            runner = new Thread(new ControllerRefreshThread(world));
            runner.start();
        }
    }

    public static void kill(World world) {
        if (world.isRemote){
            return;
        }
        run = false;
        runner = null;
    }
}


