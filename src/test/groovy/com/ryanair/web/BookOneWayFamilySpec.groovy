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
    def "booking one way flight, from #fromAirportCode to #toAirportCode, for family, with wrong card details -> gives a payment declined error"() {
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
        and:
            assertThat("payment should be declined and error displayed",
                    getBookingPaymentPage().waitForPaymentDeclinedError());
            DriverHelper.takeScreenshot("paymentDeclined");
        and:
            LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");

        where:
            fromAirportCode |   toAirportCode |   flightOutDate |   adults |   children |   expectedFromAirportDisplayedToUser |   expectedToAirportDisplayedToUser
            "SXF"           |   "DUB"         |   "05-11-2016"  |   2      |   2        |   "Berlin (SXF)"                     |   "Dublin"
            "DUB"           |   "STA"         |   "04-11-2016"  |   1      |   5        |   "Dublin"                           |   "London Stansted"
    }

}