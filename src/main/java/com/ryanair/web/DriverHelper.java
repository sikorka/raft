package com.ryanair.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Helps with creating drivers.
 */
public abstract class DriverHelper {
    private static WebDriver driver;
    private static String mainUrl;

    /**
     * Searches in <i>system.properties</i> file for browser selection,
     * preps related driver and returns it.
     *
     * @return in case no browser selection found returns <code>null</code>
     * */
    public static WebDriver getDriverFromProps() { //throws NoBrowserSelectedException {
        if (driver == null) { //no driver yet, or driver quit
            String browserName = PropertiesHelper.getBrowserName();

            if (browserName != null)
                if (browserName.equals(PropertiesHelper.VALUE_BROWSER_FF))
                    return prepFF();
                else if (browserName.equals(PropertiesHelper.VALUE_BROWSER_CHROME))
                    return prepChrome();

            LogHelper.report("No browser selected in system properties. " +
                    "Provide it in 'system.properties' or in mvn command 'mvn clean test -Dbrowser=ff', or in IDE'a VM arguments. ");

            return null; //no browser specified
       }

        return driver;
    }

    private static WebDriver prepChrome() {
        if (driver == null) {
            //Chrome needs path to local 'server'
            //user shall be so kind as to put the path into system.properties

//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--start-maximized");
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return driver;
    }

    private static WebDriver prepFF() {
        if (driver == null) {
            //create an instance of FF driver
            //this just works (tested with FF 46.0.1 and Selenium 2)
            //although supposed to work with lower
            driver = new FirefoxDriver();

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void getRidOfDriver() {
        if (driver == null) return;

        driver.quit();
        driver = null;
    }

    /** Reads main url from properties (one time). */
    public static String getMainUrl() {
        if (mainUrl == null)
            mainUrl = PropertiesHelper.getMainUrl();
        return mainUrl;
    }
}

