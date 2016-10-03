package com.ryanair.web;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import com.ryanair.web.core.WebTest;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage;
import com.ryanair.web.pages.core.RyanairPage;
import org.junit.Test;

/**
 * Tests booking of one way flight.
 */
public class BookOneWayFamilyTest extends WebTest {
    RyanairPage currentPage;

    private String FROM_CODE = "DUB";
    private String TO_CODE = "SXF";
    private String FROM_EXPECTED = "Dublin";
    private String TO_EXPECTED = "Berlin (SXF)";
    private String FROM_DATE = "03-11-2016";

    @Test
    public void fillWrongPaymentDetails() {
        fillFlightDetails();
        fillBookingDetails();
        fillBookingExtras();

        if (currentPage.whoAmI().endsWith(BookingPaymentPage.PATH)) {
            BookingPaymentPage bookingPaymentPage = new BookingPaymentPage(driver);

            currentPage = bookingPaymentPage;

            bookingPaymentPage.fillAdult(0, "Mr", "Halo", "Tam");
            bookingPaymentPage.fillAdult(1, "Mr", "Jam", "Jest");
            bookingPaymentPage.fillChild(2, "Kim", "Han");

            bookingPaymentPage.fillContactDetails("abc@def.de", "Andorra", "123456789");

            bookingPaymentPage.fillCardDetails("5555555555555557", "MasterCard", "10", "2018", "167", "Ban An");
            bookingPaymentPage.fillBillingAddress("Einzweidrei", "Berlin");
            bookingPaymentPage.acceptTerms();

            bookingPaymentPage.submitPayment();

            assertThat("payment should be declined and error displayed",
                    bookingPaymentPage.waitForPaymentDeclined());

            assertThat("should not move forward, still payment page",
                    currentPage.whoAmI(), equalTo(BookingPaymentPage.PATH));

            LogHelper.success("passed, payment error is displayed at " + BookingPaymentPage.PATH + " page");
        }
    }

    @Test
    public void fillBookingExtras() {
        if (currentPage.whoAmI().endsWith(BookingExtrasPage.PATH)) {
            BookingExtrasPage bookingExtrasPage = new BookingExtrasPage(driver);

            currentPage = bookingExtrasPage;

            bookingExtrasPage.clickNext();

            if (bookingExtrasPage.waitForSeatPopupPresent()) {
                bookingExtrasPage.closeSeatPopup();
            }

            assertThat("should move forward, still booking home page",
                    currentPage.whoAmI(), not(equalTo(BookingExtrasPage.PATH)));
            assertThat("should move to booking payment page, did not",
                    currentPage.whoAmI(), equalTo(BookingPaymentPage.PATH));

            LogHelper.success("passed extras selection on " + BookingExtrasPage.PATH + " page");
        }
    }

    @Test
    public void fillBookingDetails() {
        if (currentPage.whoAmI().endsWith(BookingHomePage.PATH)) {
            BookingHomePage bookingHomePage = new BookingHomePage(driver);
            currentPage = bookingHomePage;

            bookingHomePage.chooseFirstRegularFlight();
            bookingHomePage.clickContinue();

            if (currentPage.waitForPromoDisplayed()) {
                currentPage.closePromo();
            }

            assertThat("should move forward, still booking home page",
                    currentPage.whoAmI(), not(equalTo(BookingHomePage.PATH)));
            assertThat("should move to booking extras page, did not",
                    currentPage.whoAmI(), equalTo(BookingExtrasPage.PATH));

            LogHelper.success("passed fare selection on " + BookingHomePage.PATH + " page");
        }
    }

    @Test
    public void fillFlightDetails() {
        HomePage homepage = new HomePage(driver);
        currentPage = homepage;

        homepage.open();
        assert homepage.clickOneWay();
        assertThat("should select one way check box, but did not select it",
                homepage.oneWay_checkBox.isSelected());

        assert homepage.typeFromAirport(FROM_CODE);
        //homepage.waitForFromAirportText("Dublin");
        assertThat("should select Dublin as departure airport, it did not",
                homepage.getFromAirportText(), equalTo(FROM_EXPECTED));

        assert homepage.typeToAirport(TO_CODE);
        //homepage.waitForToAirportText("Berlin (SXF)");
        assertThat("should select Berlin as destination airport, it did not",
                homepage.getToAirportText(), equalTo(TO_EXPECTED));

        assert homepage.provideFromDate(FROM_DATE);

        assert homepage.openPassengersDropdown();
        assert homepage.addAdult();
        assert homepage.addChild();
        assert homepage.searchFlights();

        if (currentPage.waitForPromoDisplayed()) {
            currentPage.closePromo();
        }

        assertThat("should move forward, still home page",
                currentPage.whoAmI(), not(equalTo(HomePage.PATH)));
        assertThat("should move to booking home page, did not",
                currentPage.whoAmI(), equalTo(BookingHomePage.PATH));

        LogHelper.success("passed flight details on home page");
    }

}