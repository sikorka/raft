package com.ryanair.web.pages;

import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Booking extras page.
 */
public class BookingExtrasPage extends RyanairPage {

    /** URL's path after main URL. */
    public static final String PATH = "booking/extras";

    @FindBy(xpath = "//*[@class='button-next']//button")
    public WebElement next_button;
    @FindBy(xpath = "//*[contains(@class, 'seat-prompt-popup')]")
    public WebElement seat_popup;

    public BookingExtrasPage(WebDriver driver) {
        super(driver);
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
        return waitForPresent(By.xpath("//*[contains(@class, 'seat-prompt-popup')]"), 15);
    }

    public void closeSeatPopup() {
        driver.findElement(By.xpath("//*[@ng-click='closeThisDialog()']")).click();
    }
}
