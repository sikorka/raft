package com.ryanair.web;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.ryanair.web.core.WebTest;
import com.ryanair.web.pages.BookingPaymentPage;

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

        makeScreenShot();

        LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");
    }

    @Step
    public void fillFlightDetails() {
        getHomePage().fillFlightDetails(true, "DUB", "Dublin", "STA", "London Stansted", "03-11-2016", 2, 1);
        makeScreenShot();
    }

    @Step
    public void chooseFirstRegularFare() {
        getBookingHomePage().chooseFirstRegularFare();
        makeScreenShot();
    }

    @Step
    public void skipBookingExtras() {
        getBookingExtrasPage().skipBookingExtras();
        makeScreenShot();
    }

    @Step
    public void fillWrongCardDetails() {
        getBookingPaymentPage().fillWrongCardDetails(2, 1);
        makeScreenShot();
    }
}
