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

    public ControllerRefreshThread(World world) {
        this.world = world;
        dchConfiguration = DCHConfiguration.getInstance();
        connector = new DatahubClientConnector(dchConfiguration);
        run = true;
    }



    @Override
    public void run() {
        int keyValue;
        while (run) {

            scanParams = new ScanParams();
            scanParams.match(dchConfiguration.getJedis_prefix() + "*");
            ScanResult<String> scanResult = connector.getJedis().scan("0", scanParams);
            List<String> keys = scanResult.getResult();

            Iterator<String> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                String id = keyIterator.next();
                keyValue = connector.getIntValueForKey(id);

                valueUpdateEvent = new ValueUpdateEvent(this.world);
                valueUpdateEvent.setValue(keyValue);
                valueUpdateEvent.setId(id);

                MinecraftForge.EVENT_BUS.post(valueUpdateEvent);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
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


