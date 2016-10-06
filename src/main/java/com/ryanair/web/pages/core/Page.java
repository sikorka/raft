package com.ryanair.web.pages.core;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.LogHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Any page.
 *
 * Contains helper methods for waiting for elements.
 * Default timeout to wait for element is 10s.
 */
public abstract class Page {

    /** Timeout to wait for element, in seconds. */
    private static final int DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT = 10;

    protected WebDriver driver;

    protected Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /** Waits for an element being displayed with default timeout. */
    public Boolean waitForValue(WebElement element, String value) {
        return waitForValue(element, value, DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT);
    }

    /** Waits for an element being displayed with default timeout. */
    public Boolean waitForDisplayed(WebElement element) {
        return waitForDisplayed(element, DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT);
    }

    /** Waits for an element being displayed with default timeout. */
    public Boolean waitForPresent(By element) {
        return waitForPresent(element, DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT);
    }

    /** Waits until element is displayed. */
    private Boolean waitForDisplayed(WebElement element, Integer timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOf(element), timeout);
        } catch (TimeoutException exception) {
            return false;
        } return true;
    }

    /** Waits until element is displayed. */
    protected Boolean waitForPresent(By element, Integer timeout) {
        try {
            waitFor(ExpectedConditions.presenceOfElementLocated(element), timeout);
//                    (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException exception) {
            return false;
        } return true;
    }

    /** Waits until element's value is present. */
    private Boolean waitForValue(WebElement element, String value, Integer timeout) {
        try {
            waitFor(ExpectedConditions.textToBePresentInElement(element, value), timeout);
//                    (timeout.length > 0 ? timeout[0] : null));
        } catch (TimeoutException exception) {
            return false;
        } return true;
    }

    private void waitFor(ExpectedCondition condition, Integer timeout) {
        timeout = (timeout != null ? timeout : DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    public boolean waitForPath(String path) {
        return waitForPath(path, DEFAULT_TIMEOUT_TO_WAITFOR_ELEMENT);
    }

    /** Waits for path to be appended in URL. */
    private boolean waitForPath(String path, Integer timeout) {
        try {
            waitFor(ExpectedConditions.urlContains(path), timeout);
        } catch (TimeoutException exception) {
            return false;
        } return true;
    }

    /** Uses JS to scroll an element into view. If it can't scroll handles exception and continues.
     * @param element an element to scroll into view
     * @return true if not exception (assuming: scrolled), false otherwise*/
    protected boolean scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                LogHelper.fail("sth went wrong with scrolling element into view");
                return false;
            }
        } catch (NoSuchElementException e) {
            LogHelper.fail("could not find element");
            DriverHelper.takeScreenshot("scrollingIntoView");
            return false;
        }
        return true;
    }
}
