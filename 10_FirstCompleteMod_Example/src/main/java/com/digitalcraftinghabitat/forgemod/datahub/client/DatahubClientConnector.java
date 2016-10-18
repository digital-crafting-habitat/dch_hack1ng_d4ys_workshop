package com.digitalcraftinghabitat.forgemod.datahub.client;

import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import redis.clients.jedis.Jedis;

/**
 * Created by christopher on 25/08/15.
 */
public class DatahubClientConnector {

    DCHConfiguration dchConfiguration;

    public Jedis getJedis() {
        return jedis;
    }

    private Jedis jedis;

    public DatahubClientConnector(DCHConfiguration dchConfiguration) {
        if (dchConfiguration != null){
            initJedis(dchConfiguration);
        }
        else {
            throw new IllegalArgumentException("config is null");
        }

    }

    public DatahubClientConnector() {
        dchConfiguration = DCHConfiguration.getInstance();
        initJedis(dchConfiguration);
    }

    private void initJedis(DCHConfiguration dchConfiguration) {
        jedis = new Jedis(dchConfiguration.getJedisUrl());
        jedis.auth(dchConfiguration.getJedisAuth());
        //jedis = new Jedis("192.168.99.100", 32768);

        jedis.getClient().setConnectionTimeout(40000);
        jedis.getClient().setSoTimeout(40000);
        jedis.getClient().setConnectionTimeout(40000);
    }


    public String getSringValueForKey(String key) {
        return "demoString";
    }

    public int getIntValueForKey(String valueKey) {
        try {
            String returnedValue = jedis.get(valueKey);
            System.out.println(valueKey);

            if ((returnedValue != null) && (returnedValue.length() > 0)) {
                int parsedIntegerValue = Integer.parseInt(returnedValue.replaceAll("\\D", ""));
                // DCHLog.warning("Returned Redis Value for Key " + valueKey + " was " + parsedIntegerValue);
                return parsedIntegerValue;
            }
            else{
                // DCHLog.warning("Returned Redis Value for Key " + valueKey + " was empty");
            }
        }
        catch (Exception e){
            DCHLog.error(e);
        }
        return -1;
    }

    public void setValueForKey(String valueKey, String value) {
        jedis.set(valueKey, value);
    }

    public float getFloatValueForKey(String key) {
        return 3.0f;
    }

    public String[] getArrayForKey(String key) {
        String[] returnArray = {"a", "b"};
        return returnArray;
    }

    public void setIntValueForKey(String key, int value) {
        jedis.set(key, String.valueOf(value));
    }

}
