package com.ryanair.web

import com.ryanair.web.core.WebSpecification
import com.ryanair.web.pages.BookingPaymentPage
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

import spock.lang.Unroll

/**
 * Tests booking of one way family flight.
 */
class BookOneWayFamilySpec extends WebSpecification {

    @Unroll
    def "booking one way family flight, #fromAirportCode -> #toAirportCode, gives a payment declined error"() {

        when:
            getHomePage().fillFlightDetails(true,
                    fromAirportCode, expectedFromAirportDisplayedToUser,
                    toAirportCode, expectedToAirportDisplayedToUser,
                    flightOutDate,
                    adults, children);
            getBookingHomePage().chooseFirstRegularFare();
            getBookingExtrasPage().skipBookingExtras();
            getBookingPaymentPage().fillWrongCardDetails(adults, children);

        then:
            assertThat("should not move forward, still payment page",
                getBookingPaymentPage().whoAmI(), equalTo(BookingPaymentPage.PATH));

            assertThat("payment should be declined and error displayed",
                    getBookingPaymentPage().waitForPaymentDeclinedError());

        and:
            takeScreenShot();
            DriverHelper.takeScreenshot("paymentDeclined");
            LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");

        where:
            fromAirportCode |   toAirportCode |   flightOutDate |   adults |   children |   expectedFromAirportDisplayedToUser |   expectedToAirportDisplayedToUser
            "SXF"           |   "DUB"         |   "05-11-2016"  |   2      |   2        |   "Berlin (SXF)"                     |   "Dublin"
            "DUB"           |   "STA"         |   "04-11-2016"  |   1      |   5        |   "Dublin"                           |   "London Stansted"
    }

}