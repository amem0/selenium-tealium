package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.MockUserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class LoginPage extends BasePage {
    public By emailAddressLocator = By.cssSelector("div.input-box > input#email[name=\"login[username]\"]");
    public By passwordLocator = By.cssSelector("div.input-box > input#pass[name=\"login[password]\"]");
    public By rememberMeLocator = By.cssSelector("div.input-box > input.checkbox[name=\"persistent_remember_me\"]");
    public By interactionButtons = By.cssSelector("div.button-set");
    public By interactionSubmitButton = new ByChained(interactionButtons, By.cssSelector("button[type=\"submit\"]"));
    public By errorMsgLocator = By.cssSelector("li.error-msg");

    public LoginPage(WebDriver driver, boolean useGet) {
        super(driver);
        this.subpath = "customer/account/login/";
        if(useGet) {
            this.driver.get(this.baseUrl + this.subpath);
        }
    }

    public boolean errorMsgVisible() {
        return this.isDisplayed(this.errorMsgLocator);
    }

    public LoginPage inputEmail(MockUserData UserData) {
        this.type(emailAddressLocator, UserData.emailAddress);
        return this;
    }

    public LoginPage inputPassword(MockUserData UserData) {
        this.type(passwordLocator, UserData.password);
        return this;
    }

    public LoginPage inputRememberMe(MockUserData UserData) {
        if (UserData.rememberMe && !(this.isChecked(this.rememberMeLocator))) {
            this.click(rememberMeLocator);
        }
        return this;
    }

    public LoginPage Login() {
        this.click(interactionSubmitButton);
        return this;
    }
}
