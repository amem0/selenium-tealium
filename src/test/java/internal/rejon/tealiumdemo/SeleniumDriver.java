package internal.rejon.tealiumdemo;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class SeleniumDriver {
    public static WebDriver driver  = null;
    public static SeleniumDriver instance = new SeleniumDriver();
    private SeleniumDriver() {}

    public static SeleniumDriver getInstance() {
        if(instance == null)
            instance = new SeleniumDriver();
        return instance;
    }

    public static void createDriver(String browserType) {
        switch(browserType.toUpperCase()) {
            case "FIREFOX":
                //driver = new FirefoxDriver((FirefoxOptions) new FirefoxOptions().setBrowserVersion("nightly")
                //        .addArguments("-headless"));
                driver = new FirefoxDriver((FirefoxOptions) new FirefoxOptions().setBrowserVersion("nightly"));
                break;
            case "CHROME":
                driver = new ChromeDriver((ChromeOptions) new ChromeOptions().setBrowserVersion("canary"));
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
    }

    public static void setConsentCookie(){
        try{
            if(driver.manage().getCookieNamed(PropertyParsing.getProperties().getProperty("privacyConsentCookieKey")) == null
             && driver.getCurrentUrl() != null
            )
                driver.manage().addCookie(new Cookie(PropertyParsing.getProperties().getProperty("privacyConsentCookieKey"),
                        PropertyParsing.getProperties().getProperty("privacyConsentCookieValue")));
        }
        catch (IOException e) {}
    }

    public static void closeDriver(){
        driver.quit();
    }
}
