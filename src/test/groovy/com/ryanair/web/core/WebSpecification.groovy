package com.ryanair.web.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage
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
    WebDriver driver;

    private HomePage homePage;
    private BookingHomePage bookingHomePage;
    private BookingExtrasPage bookingExtrasPage;
    private BookingPaymentPage bookingPaymentPage;

    def setup() {
        driver = DriverHelper.getDriverFromProps();
    }

    def cleanup() {
        DriverHelper.getRidOfDriver();
        driver = null;
    }

    @Attachment
    @Step("Make screenshot of page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
