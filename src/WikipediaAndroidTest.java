import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class WikipediaAndroidTest {
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

    @Test
    public void testArticlesInMyList()
    {
        String folder_name = "My folder";
        String article_text_1 = "Programming language";
        String article_text_2 = "Class of Soviet cold-war destroyers";
        String article_title = "";

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Wikipedia input", 5);

        // search for text 'Kotlin'
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Kotlin",
                "Can not find search input", 5);

        // add one of article to new created folder 'My folder'
        saveArticleInNewFolder(article_text_1, "My folder");

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can not find Wikipedia input", 5);

        // search for text 'Kotlin' again
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Kotlin",
                "Can not find search input", 5);

        // add another article to folder 'My folder'
        article_title = saveArticleInExistingFolder(article_text_2, folder_name);

        // navigate to my folders
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find navigation button to my lists" ,
                15
        );

        // find folder by name
         waitForElementAndClick(
                 By.xpath("//*[@text='"+folder_name+"']"),
                 "Can not find created folder" ,
                  5
          );

        // open folder
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='"+folder_name+"']"),
                "Can not find folder '"+folder_name+ "'", 5);

        // check out if the opened folder has a correct name
        WebElement folder = waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@resource-id='org.wikipedia:id/item_title']"),
        "Can not find folder element '");
        Assert.assertTrue(folder.getAttribute("text").equals(folder_name));

        // delete first article via swiping
        swipeElementToLeft(
                By.xpath("//*[@text='"+article_text_1.toLowerCase()+"']"),
                "Can not find saved article '" + article_text_1.toLowerCase() + "'"
        );

        // if swipe was not done and article still in folder, swipe again
        if(!waitForElementNotPresent(
                By.xpath("//*[@text='"+article_text_1.toLowerCase()+"']"),
                "Saved article '" + article_text_1.toLowerCase() + "' is still on the page.",
                10
                    ))
            swipeElementToLeft(
                    By.xpath("//*[@text='"+article_text_1.toLowerCase()+"']"),
                    "Swipe was not done. Trying again..'"
            );

        // check out that deleted article is not displayed in the folder
        waitForElementNotPresent(
                By.xpath("//*[@text='"+article_text_1.toLowerCase()+"']"),
                "Saved article '" + article_text_1.toLowerCase() + "' is still on the page.",
                10
        );

        // click to open another article
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                        "Can not find saved article on page", 5);

        // get the title of article
        WebElement title  =  waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find saved article Title",
                15
        );

        // check if the title name is correct
        Assert.assertTrue("Acticle title is not the same as '"+article_title+"'", article_title.equals(title.getAttribute("text").toString()));
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");


        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message,5);
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
    private void saveArticleInNewFolder(String article_name, String new_folder_name)
    {

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+article_name+"']"),
                "Can not find Wikipedia input", 5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of article folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                new_folder_name,
                "Can not put text into article folder input",
                5
        );

        waitForElementAndClear(
                By.xpath("//*[@text='OK']"),
                "Can not press Ok button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find 'X' link" ,
                15
        );
    }
    private String saveArticleInExistingFolder(String article_name, String folder_name)
    {
        String title_text = "";

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+article_name+"']"),
                "Can not find Wikipedia input", 5);

        WebElement title  = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15
        );

        title_text = title.getAttribute("text").toString();

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find option to add article to reading list",
                5
        );

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/lists_container']//*[@text='"+folder_name+"']"),
                    "Can not find folder '"+folder_name+ "'", 5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find 'X' link" ,
                10
        );
        return title_text;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message+ "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(170)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}

