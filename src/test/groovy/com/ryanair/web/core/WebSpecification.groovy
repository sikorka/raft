package com.ryanair.web.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage
import org.junit.After
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver
import ru.yandex.qatools.allure.annotations.Attachment
import ru.yandex.qatools.allure.annotations.Step;
import spock.lang.Specification;

/**
 * Created by ana.
 */
public class WebSpecification extends Specification {
    static WebDriver driver;

    private HomePage homePage;
    private BookingHomePage bookingHomePage;
    private BookingExtrasPage bookingExtrasPage;
    private BookingPaymentPage bookingPaymentPage;

    def setupSpec() {
        driver = DriverHelper.getDriverFromProps();
    }

    def cleanupSpec() {
        DriverHelper.getRidOfDriver();
        driver = null;
    }

    def cleanup() {
        DriverHelper.takeScreenshot(driver);
    }

    @Attachment
    @Step("Take screenshot of page")
    public byte[] takeScreenShot() {
        return DriverHelper.takeScreenshot(driver);
    }

    def getHomePage() {
        if (homePage == null)
            homePage = new HomePage(driver);
        return homePage;
    }

    def getBookingHomePage() {
        if (bookingHomePage == null)
            bookingHomePage = new BookingHomePage(driver);
        return bookingHomePage;
    }

    def getBookingExtrasPage() {
        if (bookingExtrasPage == null)
            bookingExtrasPage = new BookingExtrasPage(driver);
        return bookingExtrasPage;
    }

    def getBookingPaymentPage() {
        if (bookingPaymentPage == null)
            bookingPaymentPage = new BookingPaymentPage(driver);
        return bookingPaymentPage;
    }
}
