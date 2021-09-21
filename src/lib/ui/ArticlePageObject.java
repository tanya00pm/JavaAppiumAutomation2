package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LIST_FOLDER_NAME_TPL = "//*[@resource-id='org.wikipedia:id/lists_container']//*[@text='{FOLDER_NAME}']",
            MENU_ELEMENT_TPL = "//*[@text='{ELEMENT_NAME}']";

    private static final String[] MY_LIST_MENU = {
            "Change language",
            "Share link",
            "Add to reading list",
            "Find in page",
            "Font and theme"
    };

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE),
                "Can not find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeToFindElement(By.xpath(FOOTER_ELEMENT),
                "Can not find the end of the article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find button to open article options",
                5
        );

        this.waitForMenuToRender();

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can not find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can not find input to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can not put text into article folder input",
                5
        );

        this.waitForElementAndClear(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can not press Ok button",
                5
        );
    }

    public void addArticleToExistingFolder(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find button to open article options",
                5
        );

        this.waitForMenuToRender();

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(By.xpath(MY_LIST_FOLDER_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder)),
                "Can not find folder '" + name_of_folder + "'",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find 'X' link", 15);
    }

    /* Ex6 */
    public void assertArticleTitleExists()
    {
        this.assertElementPresent(
                By.id(TITLE), "Title element is not found."
        );
    }
    /* Ex6 */

    private void waitForMenuToRender()
    {
        for (String item: MY_LIST_MENU)
        {
            waitForElementPresent(
                    By.xpath(MENU_ELEMENT_TPL.replace("{ELEMENT_NAME}", item)),
                    "Cannot render menu element " + item,
                    5
            );
        }
    }
}
