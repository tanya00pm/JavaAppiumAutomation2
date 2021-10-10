package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iOSNavigationUI;
import lib.ui.NavigationUI;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        }
        else {
            return new iOSNavigationUI(driver);
        }
    }
}
