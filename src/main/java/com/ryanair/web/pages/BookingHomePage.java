package com.ryanair.web.pages;

import com.ryanair.web.LogHelper;
import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Booking home page.
 *
 * The one after home page and where fares are displayed to be chosen.
 */
public class BookingHomePage extends RyanairPage {

    /** URL's path after main URL. */
    public static final String PATH = "booking/home";
    public static final String NEXT_PATH = BookingExtrasPage.PATH;

    @FindBy(xpath = "//*[contains(@class, 'one-third regular')]")
    protected WebElement firstRegularFare_box;
    @FindBy(id = "continue")
    protected WebElement continue_button;

    public BookingHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean amIme() {
        return whoAmI().endsWith(PATH);
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
            scrollIntoView(continue_button);
            continue_button.click();

            return true;
        }
        return false;
    }

    public void chooseFirstRegularFare() {
        if (amIme()) {

            chooseFirstRegularFlight();
            clickContinue();

            if (waitForPromoDisplayed()) {
                closePromo();
            }

            waitForPath(NEXT_PATH);

            assertThat("should move forward, still booking home page",
                    whoAmI(), not(equalTo(PATH)));
            assertThat("should move to booking extras page, did not",
                    whoAmI(), equalTo(NEXT_PATH));

            LogHelper.success("passed fare selection on " + PATH + " page");
        }
    }
}
