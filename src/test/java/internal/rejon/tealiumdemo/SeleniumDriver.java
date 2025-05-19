package internal.rejon.tealiumdemo;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
                driver = new FirefoxDriver((FirefoxOptions) new FirefoxOptions().setBrowserVersion("nightly")
                        .addArguments("-headless"));
                break;
            case "CHROME":
                driver = new ChromeDriver((ChromeOptions) new ChromeOptions().setBrowserVersion("canary"));
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        setConsentCookie();
    }

    public static void setConsentCookie(){
        driver.manage().addCookie(new Cookie("CONSENTMGR","consent:false%7Cts:1747603105255"));
    }

    public static void closeDriver(){
        driver.quit();
    }
}
