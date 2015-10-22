package com.digitalcraftinghabitat.forgemod.util;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by christopher on 29/08/15.
 */
public class DCHConfiguration {

    public static final String JEDIS_URL = "jedis_url";
    public static final String JEDIS_AUTH = "jedis_auth";
    public static final String JEDIS_URL_DEFAULT = "85.214.235.74";
    public static final String JEDIS_AUTH_DEFAULT = "DCH-Rocks-2015@12321";
    private static final String JEDIS_PREFIX = "jedis_prefix";
    private static final String JEDIS_PREFIX_DEFAULT = "dev_";
    private String jedisAuth;
    private Configuration config;
    private static DCHConfiguration instance;
    private String jedisUrl;
    private String jedis_prefix;

    private DCHConfiguration(File suggestedConfigurationFile) {
        DCHLog.info("---- load Config File: " + suggestedConfigurationFile.getAbsolutePath());
        config = new Configuration(suggestedConfigurationFile);

        config.load();
        fillConfigurationValues();
        config.save();
    }


    private void fillConfigurationValues() {
        jedisUrl = config.get(Configuration.CATEGORY_GENERAL, JEDIS_URL, JEDIS_URL_DEFAULT).getString();
        jedisAuth = config.get(Configuration.CATEGORY_GENERAL, JEDIS_AUTH, JEDIS_AUTH_DEFAULT).getString();
        jedis_prefix = config.get(Configuration.CATEGORY_GENERAL, JEDIS_PREFIX, JEDIS_PREFIX_DEFAULT).getString();
    }

    public static DCHConfiguration getInstanceWithFile(File suggestedConfigurationFile) {
        if (instance == null) {
            instance = new DCHConfiguration(suggestedConfigurationFile);
        }
        return instance;
    }

    public static DCHConfiguration getInstance() {
        if (instance == null) {
            DCHLog.warning("config not initialized");
        }
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }


    public String getJedis_prefix() {
        return jedis_prefix;
    }

    public String getJedisUrl() {
        return jedisUrl;
    }

    public String getJedisAuth() {
        return jedisAuth;
    }
}
