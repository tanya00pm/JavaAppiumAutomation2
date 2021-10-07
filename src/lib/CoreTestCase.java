package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.time.Duration;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";


    protected AppiumDriver driver;
   // private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";
  //  protected Platform Platform;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        //this.Platform = new Platform();
       // driver = this.Platform.getDriver();
        driver = Platform.getInstance().getDriver();

      //  DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
      //  driver = new AndroidDriver(new URL(AppiumURL),
       //         capabilities);

        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    public void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private void skipWelcomePageForIOSApp() {
        if(Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }

    }

   /* protected DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/tatiana/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)){
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE Test");
        capabilities.setCapability("platformVersion", "13.6");
        capabilities.setCapability("app", "/Users/tatiana/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        } else
        {
        throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

        return capabilities;
    }*/

   // private AppiumDriver getDriverByPlatformEnv() throws Exception
   // {
   //     String platform = System.getenv("PLATFORM");
   // }
}
