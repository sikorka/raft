package com.ryanair.web.pages.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Any Ryanair page.
 *
 */
public abstract class RyanairPage extends Page {

    protected RyanairPage(WebDriver driver) {
        super(driver);
    }

    /** Recognizes Ryanair page it is at right now and returns a unique name for it.
     *
     * @return web page name related to path - if current URL matches
     * main URL AND a recognized URL's path; <code>null</code> - if not recognized
     *
     * */
    public String whoAmI() {
        String path = driver.getCurrentUrl();

        if (path.endsWith(BookingHomePage.PATH))
            return BookingHomePage.PATH;
        else if (path.endsWith(BookingExtrasPage.PATH))
            return BookingExtrasPage.PATH;
        else if (path.endsWith(BookingPaymentPage.PATH))
            return BookingPaymentPage.PATH;
        else if (path.equals(DriverHelper.getMainUrl()))
                return HomePage.PATH;

        return null;
    }

    public boolean waitForPromoDisplayed() {
        return waitForPresent(By.xpath("//*[contains(@class, 'promo-popup')]"), 15);
    }

    public void closePromo() {
        driver.findElement(By.xpath("//*[contains(@class, 'promo-popup-close')]")).click();
    }
}
