package com.ryanair.web;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.ryanair.web.core.WebTest;
import com.ryanair.web.pages.BookingPaymentPage;
import org.junit.Test;

/**
 * Tests booking of one way family flight.
 */
public class BookOneWayFamilyTest extends WebTest {

    @Test
    public void fillWrongCardDetailsExpectPaymentError() {

        getHomePage().fillFlightDetails(true, "DUB", "Dublin", "STA", "London Stansted", "03-11-2016", 2, 1);
        getBookingHomePage().chooseFirstRegularFare();
        getBookingExtrasPage().skipBookingExtras();
        getBookingPaymentPage().fillWrongCardDetails(2, 1);

        assertThat("payment should be declined and error displayed",
                getBookingPaymentPage().waitForPaymentDeclinedError());
        assertThat("should not move forward, still payment page",
                getBookingPaymentPage().whoAmI(), equalTo(BookingPaymentPage.PATH));

        LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");
    }

}