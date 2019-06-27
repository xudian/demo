package com.aladen.common.util;

import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentUtil {

    private static ConfigurableEnvironment environment;

    public static void setEnvironment(ConfigurableEnvironment environment) {
        EnvironmentUtil.environment = environment;
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }
}
