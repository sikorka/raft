/**
 * Created by ana.
 */

import com.ryanair.web.LogHelper;
import com.ryanair.web.pages.BookingPaymentPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * This is a main one-pager test class for the project to verify basic things are flying nice.
 * It uses Google Selenium's example.
 * */
public class TestSelenium {
    //these are browsers
    public static WebDriver ff;
    public static WebDriver chrome;

    static {
        //Chrome needs path to local 'server'
        //user shall be so kind as to put the path into system.properties
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("system.properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //therefore reading it from properties
        System.getProperties().putAll(props);

        System.getProperties().list(System.out);
    }

    /**
     * Runs Google test using @driver.
     * @param driver properly prepped driver.
     * */
    private static void runGoogleScenario(WebDriver driver) {
        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }

    public static void main(String[] args) {
        //safari blows

        //run the scenario in many browsers
        for (WebDriver d : new WebDriver[] {
                new FirefoxDriver(),
                new ChromeDriver()}) {
            runGoogleScenario(d);
        }

    }


}