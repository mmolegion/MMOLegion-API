package com.mmolegion.core.config;

import java.util.Properties;

public class AppProperties {

    private static volatile AppProperties instance = null;
    private static final Object lock = new Object();

    private static Properties properties;

    private AppProperties() {
    }

    public static AppProperties getInstance() {
        if(instance == null)
            synchronized (lock) {
                if(instance == null)
                    instance = new AppProperties();
            }

        return instance;
    }

    public static void setProperties(Properties p) {
        properties = p;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
