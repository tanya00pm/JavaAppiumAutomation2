package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView/*[@type='XCUIElementTypeCell']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_INPUT_TEXT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULT_BY_TITLE_DESCRIPTION_TPL = "xpath://*[@type='XCUIElementTypeCollectionView']" +
                "[//@type='XCUIElementTypeCell' and //@name='{TITLE}']" +
                "[//@type='XCUIElementTypeCell' and //@name='{DESCRIPTION}']";
    }
    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
