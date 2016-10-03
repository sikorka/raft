package com.ryanair.web.pages;

import com.ryanair.web.pages.core.RyanairPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Booking payment page.
 */
public class BookingPaymentPage extends RyanairPage {

    public static final String PATH = "booking/payment";

    @FindBy(xpath = "//*[contains(@id, 'emailAddress')]")
    public WebElement emailAddress_input;
    @FindBy(xpath = "//*[contains(@id, 'confirmEmail')]")
    public WebElement confirmEmail_input;
    @FindBy(xpath = "//input[contains(@name, 'phoneNumber')]")
    public WebElement phoneNumber_input;
    @FindBy(name = "phoneNumberCountry")
    public WebElement phoneNumberCountry_select;

    @FindBy(xpath = "//input[contains(@id, 'cardNumber')]")
    public WebElement cardNumber_input;
    @FindBy(xpath = "//*[contains(@id, 'cardType')]")
    public WebElement cardType_select;
    @FindBy(xpath = "//*[contains(@id, 'expiryMonth')]")
    public WebElement expiryMonth_select;
    @FindBy(xpath = "//*[contains(@name, 'expiryYear')]")
    public WebElement expiryYear_select;

    @FindBy(xpath = "//*[contains(@name, 'securityCode')]")
    public WebElement securityCode_input;
    @FindBy(xpath = "//*[contains(@name, 'cardHolderName')]")
    public WebElement cardHolderName_input;

    @FindBy(xpath = "//*[contains(@name, 'billingAddressAddressLine1')]")
    public WebElement billingAddressAddressLine1_input;
    @FindBy(xpath = "//*[contains(@name, 'billingAddressCity')]")
    public WebElement billingAddressCity_input;

    @FindBy(xpath = "//*[contains(@id,'acceptTerms')]")
    public WebElement acceptTerms_checkBox;

    @FindBy(xpath = "//button[@translate='common.components.payment_forms.pay_now']")
    public WebElement submit_button;


    public BookingPaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean waitForPaymentDeclined() {
        return waitForPresent(By.xpath("//prompt[@ng-switch-when='PaymentDeclined']"));
    }

    public void fillAdult(int index, String title, String firstName, String lastName) {
        fillPassenger(index, firstName, lastName, title);
    }

    private void fillPassenger(int id, String firstName, String lastName, String title) {
        waitForDisplayed(billingAddressCity_input);

        WebElement firstName_input = driver.findElement(By.xpath("//*[@name='passengerForm" + id + "']//input[contains(@id, 'firstName')]"));
        WebElement lastName_input = driver.findElement(By.xpath("//*[@name='passengerForm" + id + "']//input[contains(@id, 'lastName')]"));

        firstName_input.sendKeys(firstName);
        lastName_input.sendKeys(lastName);

        if (title != null) {
            Select select = new Select(driver.findElement(By.xpath("//*[@name='passengerForm" + id + "']//select[contains(@id, 'title')]")));
            select.selectByVisibleText(title);
        }
    }

    public void fillChild(int howManyBeforeChild, String name, String surname) {
        fillPassenger(howManyBeforeChild, name, surname, null);
    }

    public void fillContactDetails(String email, String phoneNumberCountry, String phoneNumber) {
        new Select(phoneNumberCountry_select).selectByVisibleText(phoneNumberCountry);
        emailAddress_input.sendKeys(email);
        confirmEmail_input.sendKeys(email);
        phoneNumber_input.sendKeys(phoneNumber);
    }

    public void fillCardDetails(String number, String type, String month, String year, String code, String cardholdersName) {
        cardNumber_input.sendKeys(number);
        new Select(cardType_select).selectByVisibleText(type);
        new Select(expiryMonth_select).selectByVisibleText(month);
        new Select(expiryYear_select).selectByVisibleText(year);
        securityCode_input.sendKeys(code);
        cardHolderName_input.sendKeys(cardholdersName);
    }

    public void fillBillingAddress(String street, String city) {
        billingAddressAddressLine1_input.sendKeys(street);
        billingAddressCity_input.sendKeys(city);
    }

    public void acceptTerms() {
        acceptTerms_checkBox.click();
    }

    public void submitPayment() {
        submit_button.click();
    }
}
