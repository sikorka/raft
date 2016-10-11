package com.ryanair.web.pages.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.BookingExtrasPage;
import com.ryanair.web.pages.BookingHomePage;
import com.ryanair.web.pages.BookingPaymentPage;
import com.ryanair.web.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Any Ryanair page.
 *
 */
public abstract class RyanairPage extends Page {

    protected RyanairPage(WebDriver driver) {
        super(driver);
    }

    /** Recognizes current page's path.
     *
     * @return web page path after main URL - if current URL matches
     * main URL AND a recognized URL's path, <code>null</code> if not
     *
     * */
    public String whoAmI() {
        try {
            URI currentUri = new URI(driver.getCurrentUrl());
            String path = currentUri.getPath();

            if (path.endsWith(BookingHomePage.PATH))
                return BookingHomePage.PATH;
            else if (path.endsWith(BookingExtrasPage.PATH))
                return BookingExtrasPage.PATH;
            else if (path.endsWith(BookingPaymentPage.PATH))
                return BookingPaymentPage.PATH;
            else if (path.endsWith(new URI(DriverHelper.getMainUrl()).getPath()))
                    return HomePage.PATH;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean waitForPromoDisplayed() {
        return waitForPresent(By.xpath("//*[contains(@class, 'promo-popup')]"), 15);
    }

    public void closePromo() {
        driver.findElement(By.xpath("//*[contains(@class, 'promo-popup-close')]")).click();
    }

    /**
     * Checks if page objects matches URL's path.
     *
     * @return <code>true</code> if page object matches its URLs path,
     * <code>false</code> otherwise. */
    public abstract boolean amIme();
}
