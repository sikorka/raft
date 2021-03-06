package com.ryanair.web;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.ryanair.web.core.WebTest;
import com.ryanair.web.pages.BookingPaymentPage;

import com.ryanair.web.pages.HomePage;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Tests booking of one way family flight.
 */
public class BookOneWayFamilyTest extends WebTest {

    @Test
    public void fillWrongCardDetailsExpectPaymentError() {
        fillFlightDetails();
        chooseFirstRegularFare();
        skipBookingExtras();
        fillWrongCardDetails();

        assertThat("payment should be declined and error displayed",
                getBookingPaymentPage().waitForPaymentDeclinedError());
        assertThat("should not move forward, still payment page",
                getBookingPaymentPage().whoAmI(), equalTo(BookingPaymentPage.PATH));

        takeScreenShot();

        LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");
    }

    @Step
    public void fillFlightDetails() {

        getHomePage().fillFlightDetails(true, "DUB", "Dublin", "STA", "London Stansted", "03-11-2016", 2, 1);
        takeScreenShot();

        getHomePage().waitForPath(HomePage.NEXT_PATH);

        assertThat("should move forward, still home page",
                getHomePage().whoAmI(), not(equalTo(HomePage.PATH)));
        assertThat("should move to booking home page, did not",
                getHomePage().whoAmI(), equalTo(HomePage.NEXT_PATH));

        LogHelper.success("passed flight details on home page");

        takeScreenShot();
    }

    @Step
    public void chooseFirstRegularFare() {
        getBookingHomePage().chooseFirstRegularFare();
        takeScreenShot();
    }

    @Step
    public void skipBookingExtras() {
        getBookingExtrasPage().skipBookingExtras();
        takeScreenShot();
    }

    @Step
    public void fillWrongCardDetails() {
        getBookingPaymentPage().fillWrongCardDetails(2, 1);
        takeScreenShot();
    }
}
