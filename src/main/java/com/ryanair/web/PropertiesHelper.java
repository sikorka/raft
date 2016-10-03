package com.ryanair.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Helps with <code>system.properties</code> file.
 */
abstract class PropertiesHelper {

    /** What to search for in properties. */
    private static final String KEY_BROWSER =   "browser";
    static final String VALUE_BROWSER_FF =      "ff";
    static final String VALUE_BROWSER_CHROME =  "chrome";

    private static final String KEY_URL = "mainurl";

    static {
        loadProperties();
    }

    /**
     * Loads desired properties.
     * <p>
     * Properties contain:
     * <ul>
     * <li>directory of chrome driver
     * <li>browser to run tests with
     * */
    private static void loadProperties() {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("system.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.getProperties().putAll(props);
    }

    public static String getMainUrl() {
        String mainUrl = System.getProperty(KEY_URL);
        LogHelper.report("Main url found in system properties: " + mainUrl);
        return mainUrl;
    }

    public static String getBrowserName() {
        String browserName = System.getProperty(KEY_BROWSER);
        LogHelper.report("Browser name found in system properties: " + browserName);
        return browserName;
    }
}
