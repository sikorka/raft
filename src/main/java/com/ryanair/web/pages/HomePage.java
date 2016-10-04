package com.ryanair.web.pages;

import com.ryanair.web.DriverHelper;
import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * The landing page with flight search.
 */
public class HomePage extends RyanairPage {
    /** URL's path after main URL. */
    public static final String PATH = "";

    static final String url = DriverHelper.getMainUrl();

    private static final String oneWay_checkBox_xpath = "//input[@id='flight-search-type-option-one-way']";
    @FindBy(xpath = oneWay_checkBox_xpath)
    public WebElement oneWay_checkBox;

    @FindBy(xpath = "//input[@aria-labelledby='label-airport-selector-from']")
    public WebElement fromAirport_textInput;
    @FindBy(name = "departureAirportName")
    public WebElement fromAirport_richInput; //get @value to get selected airport name

    @FindBy(xpath = "//input[@aria-labelledby='label-airport-selector-to']")
    public WebElement toAirport_text;
    @FindBy(name = "destinationAirportName")
    public WebElement toAirport_richInput; //get @value to get selected airport name

    private static final String dateOneWay_complexField_xpath = "//*[contains(@class, 'container-from')]//*[contains(@class, 'select-date')]";
    @FindBy(xpath = dateOneWay_complexField_xpath)
    public WebElement dateOneWay_complexField;
    private static String dateOneWay_input0_xpath = "//*[@name='dateInput0']";
    @FindBy(xpath = dateOneWay_complexField_xpath)
    public WebElement dateOneWay_input0;
    @FindBy(xpath = "//*[@name='passengers']")
    public WebElement passengers_complexField;
    @FindBy(xpath = "//*[@value='paxInput.adults']//button[contains(@class, 'inc')]")
    public WebElement passengersDrop_adults_inc_button;
    @FindBy(xpath = "//*[@value='paxInput.children']//button[contains(@class, 'inc')]")
    public WebElement passengersDrop_children_inc_button;
    @FindBy(xpath = "//button[@ng-click='searchFlights()']")
    public WebElement searchFlights_button;

    /** Home page needs a driver. */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /** Opens home page in driver browser. Returns when loaded. */
    public void open() {
        driver.navigate().to(url);
    }

    /** Waits for <code>oneWay_checkBox</code>, then clicks it.
     *
     * @return true if element appeared and click was performed */
    public boolean clickOneWay() {
        if (waitForPresent(By.xpath(oneWay_checkBox_xpath))) {  //wait for element
            oneWay_checkBox.click();    //hit it

            return true;                //true when clicked
        }
        return false;
    }

    /** Waits for <code>fromAirport_textInput</code> web element, then clicks it and moves to next field with a tab.
     *
     * @param airportName name or code of the departure airport
     * @return true if element appeared and click was performed */
    public boolean typeFromAirport(String airportName) {
        if (waitForDisplayed(fromAirport_textInput)) {
            fromAirport_textInput.clear(); //click() as a user does it, does not work, BAD
            fromAirport_textInput.sendKeys(airportName);
            fromAirport_textInput.sendKeys("\t");

            return true;
        }
        return false;
    }

    public String getFromAirportText() {
        return fromAirport_richInput.getAttribute("value");
    }

    public boolean waitForFromAirportText(String value) {
        return waitForValue(fromAirport_richInput, value);
    }

    /** Waits for <code>toAirport_text</code> web element, then clicks it and moves to next field with a tab.
     *
     * @param airportName name or code of the departure airport
     * @return true if element appeared and click was performed */
    public boolean typeToAirport(String airportName) {
        if (waitForDisplayed(toAirport_text)) {
            toAirport_text.clear(); //click() as a user does it, does not work, BAD
            toAirport_text.sendKeys(airportName);
            toAirport_text.sendKeys(Keys.TAB);

            return true;
        }
        return false;
    }

    public boolean waitForToAirportText(String value) {
        return waitForValue(toAirport_richInput, value);
    }

    /** Gets name of destination airport from destination airport input. */
    public String getToAirportText() {
        return toAirport_richInput.getAttribute("value");
    }

    /**
     * Selects a day in the date drop down. Only works with this and next month dates.
     * @param dateString has format: 03-11-2016 (dd-mm-yyyy)
     * @return true if clicked */
    public boolean provideFromDate(String dateString) {
        if (waitForPresent(By.xpath(dateOneWay_complexField_xpath))
                && waitForPresent(By.xpath(dateOneWay_input0_xpath))
                ) {
            if (!waitForPresent(By.xpath("//core-datepicker"))) {
                dateOneWay_complexField.click();
            }

            WebElement selectDate = getDateButton(dateString);
            //in order to click day in dropdown in Chrome it is needed
            //that the day control is in the view
            //this is also something user would do - would scroll to see what to click
            scrollIntoView(selectDate);
            //only then it can be clicked successfully
            selectDate.click();

            return true;
        }
        return false;
    }

    private WebElement getDateButton(String dateString) {
        return driver.findElement(By.xpath("//li[@data-id='" + dateString + "']"));
    }

    /** @return <code>true</code> if passengers box displayed and clicked,
     * <code>false</code> means dropdown will likely be missing */
    public boolean openPassengersDropdown() {
        if (waitForDisplayed(passengers_complexField)) {
            passengers_complexField.click(); //opens dropdown
            return true;
        }
        return false; //dropdown's missing
    }

    public boolean addAdult() {
        if (waitForDisplayed(passengersDrop_adults_inc_button)) {
            passengersDrop_adults_inc_button.click();
            return true;
        }
        return false;
    }

    public boolean addChild() {
        if (waitForDisplayed(passengersDrop_children_inc_button)) {
            passengersDrop_children_inc_button.click();
            return true;
        }
        return false;
    }

    public boolean searchFlights() {
        if (waitForDisplayed(searchFlights_button)) {
            searchFlights_button.click();
            return true;
        }
        return false; //button's missing
    }
}
