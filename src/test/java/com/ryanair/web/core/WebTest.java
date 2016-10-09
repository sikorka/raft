package com.ryanair.web.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

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
    public static void setUp() {
        driver = DriverHelper.getDriverFromProps();
    }

    @AfterClass
    public static void tearDown() {
        DriverHelper.getRidOfDriver();
    }

    @Attachment
    @Step("Make screenshot of page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
