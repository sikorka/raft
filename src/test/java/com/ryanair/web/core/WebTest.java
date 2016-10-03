package com.ryanair.web.core;

import com.ryanair.web.DriverHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

/**
 * Any test.
 */
public abstract class WebTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverHelper.getDriverFromProps();
    }

    @AfterClass
    public static void tearDown() {
        DriverHelper.getRidOfDriver();
    }

}
