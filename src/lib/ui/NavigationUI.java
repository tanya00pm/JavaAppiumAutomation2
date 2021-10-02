package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
            MY_LISTS_FOLDER_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']",
            SEARCH_MENU = "id:org.wikipedia:id/menu_search_lists";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Can not find navigation button to my lists" ,
                5
                );
    }

    public void clickFolderNameInMyLists(String folder_name)
    {
        waitForElementAndClick(
                MY_LISTS_FOLDER_NAME_TPL.replace("{FOLDER_NAME}", folder_name),
                "Can not find created folder" ,
                15
        );
    }

    public void waitForMyListsPageToRender()
    {
        waitForElementPresent(SEARCH_MENU,
                "Cannot render search button on MyLists page.", 5
        );
    }
}
