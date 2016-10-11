package com.ryanair.web.pages;

import com.ryanair.web.LogHelper;
import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Booking extras page.
 */
public class BookingExtrasPage extends RyanairPage {

    /** URL's path after main URL. */
    public static final String PATH = "booking/extras";
    public static final String NEXT_PATH = BookingPaymentPage.PATH;

    /** Continue button. */
    @FindBy(xpath = "//*[@class='button-next']//button")
    protected WebElement next_button;

    private static final String seat_popup_xpath = "//*[contains(@class, 'seat-prompt-popup')]";
    @FindBy(xpath = seat_popup_xpath)
    protected WebElement seat_popup;

    private static final String seat_popup_close_button_xpath = "//*[@ng-click='closeThisDialog()']";
    @FindBy(xpath = seat_popup_close_button_xpath)
    protected WebElement seat_popup_close_button;

    public BookingExtrasPage(WebDriver driver) {
        super(driver);
    }

    public boolean amIme() {
        return whoAmI().endsWith(PATH);
    }

    public boolean clickNext() {
        if (waitForDisplayed(next_button)) {
            scrollIntoView(next_button);
            next_button.click();

            return true;
        }
        return false;
    }

    public boolean waitForSeatPopupPresent() {
        return waitForPresent(By.xpath(seat_popup_xpath), 15);
    }

    public void closeSeatPopup() {
        seat_popup_close_button.click();
    }

    public void skipBookingExtras() {
        if (amIme()) {
            clickNext();

            if (waitForSeatPopupPresent()) {
                closeSeatPopup();
            }

            waitForPath(NEXT_PATH);

            assertThat("should move forward, still booking extras page",
                    whoAmI(), not(equalTo(PATH)));
            assertThat("should move to booking payment page, did not",
                    whoAmI(), equalTo(NEXT_PATH));

            LogHelper.success("passed extras selection on " + PATH + " page");
        }
    }
}
