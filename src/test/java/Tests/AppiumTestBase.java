package Tests;

import static Tests.GameStartTest.driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class AppiumTestBase
{    
    enum testPlatform
    {
        Aws,
        Localhost
    }
    
    public static AndroidDriver driver;    
    static final testPlatform target = testPlatform.Aws;
    
    public static boolean isLocal(){return target == testPlatform.Localhost;}
    
    @BeforeSuite
    public void setUpAppium() throws MalformedURLException 
    { 
        System.out.println("BeforeSuite Start");

         final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
         
        if(target == testPlatform.Aws)
        {           
            URL url = new URL(URL_STRING);
            driver = new AndroidDriver<MobileElement>(url, new DesiredCapabilities());
        }
        else if(target == testPlatform.Localhost)
        {
            URL url = new URL(URL_STRING);   

            DesiredCapabilities setting = new DesiredCapabilities();
            setting.setCapability("deviceName", "TestAndroidDevice");
            setting.setCapability("avd","NewOne");
            setting.setCapability("app", new File("demo2048.apk").getAbsolutePath());

            setting.setCapability("deviceReadyTimeout", 5);
            setting.setCapability("platformName", "Android");

            driver = new AndroidDriver(url, setting);
        }

        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        System.out.println("BeforeSuite End");
    }

    @AfterSuite
    public void tearDownAppium() 
    {
        driver.quit();
    }
}
