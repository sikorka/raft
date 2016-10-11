package com.ryanair.web.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.yandex.qatools.allure.annotations.Attachment;
import org.openqa.selenium.WebDriver;

/**
 * Any test.
 */
public abstract class WebTest {
    protected static WebDriver driver;

    private HomePage homePage;
    private BookingHomePage bookingHomePage;
    private BookingExtrasPage bookingExtrasPage;
    private BookingPaymentPage bookingPaymentPage;

    @BeforeClass
    public static void setUpClass() {
        driver = DriverHelper.getDriverFromProps();
    }

    @AfterClass
    public static void tearDownClass() {
        DriverHelper.getRidOfDriver();
    }

    @After
    public void tearDown() {
        DriverHelper.takeScreenshot(DriverHelper.getDriverFromProps());
    }

    @Attachment
    //@Step("Take screenshot of page")
    public byte[] takeScreenShot() {
        return DriverHelper.takeScreenshot(driver);
    }

    public HomePage getHomePage() {
        if (homePage == null)
            homePage = new HomePage(driver);
        return homePage;
    }

    public BookingHomePage getBookingHomePage() {
        if (bookingHomePage == null)
            bookingHomePage = new BookingHomePage(driver);
        return bookingHomePage;
    }

    public BookingExtrasPage getBookingExtrasPage() {
        if (bookingExtrasPage == null)
            bookingExtrasPage = new BookingExtrasPage(driver);
        return bookingExtrasPage;
    }

    public BookingPaymentPage getBookingPaymentPage() {
        if (bookingPaymentPage == null)
            bookingPaymentPage = new BookingPaymentPage(driver);
        return bookingPaymentPage;
    }
}
