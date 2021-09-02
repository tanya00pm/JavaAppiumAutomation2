import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class WikipediaTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/tatiana/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    /*@Test
    public void testCompareSearchFieldText()
    {
        Assert.assertTrue(assertElementHasText(By.id("org.wikipedia:id/search_container"),
                "Search Wikipedia",
                "Web element does not contain the desired text."));
    }*/

    @Test
    public void testCancelSearch()
    {
        // set focus on the search field
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Wikipedia input",
                5
        );

        // search for the text "Kotlin"
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Kotlin",
                "Can not find search input", 5);

        // find the search result container and its instances number by image previews (for each instance it is unique)
        int instance_number = getElementInstanceNumber(By.id("org.wikipedia:id/search_results_list"));
        Assert.assertTrue("No result instances found", (instance_number > 0));

        // clear search field
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can not find search field", 5
        );

        // make sure that search field is empty (has a default text)
        Assert.assertTrue("Search field is not empty:",
                assertElementHasDefaultText(By.id("org.wikipedia:id/search_src_text"),
                        "Search…",
                        "Web element does not contain the default text."));

        // make sure that no search result container presents anymore
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Result list is still present on the page",
                5
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(By by, String text, String error_message)
    {
        WebElement search_field_element = waitForElementPresent(
                by,
                "Can not find searched field",
                5
        );

        WebElement text_element = search_field_element.findElement(By.className("android.widget.TextView"));

        if(text_element.getAttribute("text").equals(text))
            return true;

        System.out.println(error_message + "\nExpected result: '" + text
                + "'\nActual result: '"+ text_element.getAttribute("text")+"");

        return false;
    }

    private boolean assertElementHasDefaultText(By by, String default_text, String error_message)
    {
        WebElement search_field_element = waitForElementPresent(
                by,
                "Can not find searched field",
                5
        );

        if(search_field_element.getAttribute("text").equals(default_text))
            return true;

        System.out.println(error_message + "\nExpected result: '" + default_text
                + "'\nActual result: '"+ search_field_element.getAttribute("text")+"");

        return false;
    }

    private int getElementInstanceNumber(By by)
    {
        WebElement container_element = waitForElementPresent(
                by,
                "Can not find searched field",
                5
        );

        int number = container_element.findElements(By.className("android.widget.ImageView")).size();

        return number;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message+ "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}
