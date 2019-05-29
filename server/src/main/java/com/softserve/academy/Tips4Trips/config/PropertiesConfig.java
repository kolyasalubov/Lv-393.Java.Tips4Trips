package com.softserve.academy.Tips4Trips.config;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private static final String APP_PROPERTIES = "app.properties";
    private static ClassLoader classLoader = PropertiesConfig.class.getClassLoader();

    private static final Logger logger = Logger.getLogger(PropertiesConfig.class);

    private static Properties getConfigurationPropetries() {
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(APP_PROPERTIES));
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return properties;
    }

    public static String getPropertyValue(String key) {
        return String.valueOf(getConfigurationPropetries().getProperty(key));
    }
}
