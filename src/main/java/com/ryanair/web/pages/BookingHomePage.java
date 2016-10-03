package com.ryanair.web.pages;

import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Booking home page.
 *
 * The one after home page and where fares are displayed to be chosen.
 */
public class BookingHomePage extends RyanairPage {

    /** URL's path after main URL. */
    public static final String PATH = "booking/home";

    @FindBy(xpath = "//*[contains(@class, 'one-third regular')]")
    public WebElement firstRegularFare_box;
    @FindBy(id = "continue")
    public WebElement continue_button;

    public BookingHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean chooseFirstRegularFlight() {
        if (waitForDisplayed(firstRegularFare_box)) {
            firstRegularFare_box.click();

            return true;
        }
        return false;
    }

    public boolean clickContinue() {
        if (waitForDisplayed(continue_button)) {
            continue_button.click();

            return true;
        }
        return false;
    }
}
