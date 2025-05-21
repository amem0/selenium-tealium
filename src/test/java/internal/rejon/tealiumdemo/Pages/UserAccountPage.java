package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class UserAccountPage extends BasePage {
    public By accountDashboardLocator = By.cssSelector("div.my-account > div.dashboard");
    public By accountMessagesLocator = new ByChained(accountDashboardLocator, By.cssSelector("ul.messages li"));
    public By accountMessageContentLocator = new ByChained(accountMessagesLocator, By.cssSelector("span"));

    public boolean getSuccessMessage() {
        if(this.isDisplayed(accountMessagesLocator))
            return this.getWebElement(accountMessageContentLocator).getDomAttribute("class").contains("success-msg");
        return false;
    }

    public String getMessageContent() {
        if(this.isDisplayed(accountMessageContentLocator)) {
            return this.getWebElement(accountMessageContentLocator).getText();
        }
        return "";
    }

    public UserAccountPage(WebDriver driver, boolean useGet) {
        super(driver);
        this.subpath="customer/account/index/";
        if (useGet) {
            this.driver.get(this.baseUrl + this.subpath);
            SeleniumDriver.setConsentCookie();
        }
    }

}
