package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.MockUserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class RegisterPage extends BasePage {
    public By firstNameLocator = By.cssSelector("div.field.name-firstname div.input-box > input#firstname");
    public By middleNameLocator = By.cssSelector("div.field.name-middlename div.input-box > input#middlename");
    public By lastNameLocator = By.cssSelector("div.field.name-lastname div.input-box > input#lastname");
    public By emailAddressLocator = By.cssSelector("div.input-box > input#email_address");
    public By passwordLocator = By.cssSelector("div.input-box > input#password[type=\"password\"]");
    public By confirmPasswordLocator = By.cssSelector("div.input-box > input#confirmation[type=\"password\"]");
    public By signUp4NewsletterLocator = By.cssSelector("div.input-box > input#is_subscribed[type=\"checkbox\"]");
    public By rememberMeLocator = By.cssSelector("div.input-box > input[type=\"checkbox\"][name=\"persistent_remember_me\"]");
    public By interactionButtons = By.cssSelector("div.button-set");
    public By interactionBackButton = new ByChained(interactionButtons, By.cssSelector("a.back-link"));
    public By interactionSubmitButton = new ByChained(interactionButtons, By.cssSelector("button[type=\"submit\"]"));

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.subpath = "customer/account/create/";
        this.driver.get(this.baseUrl + this.subpath);
    }

    public void inputFirstName(MockUserData UserData) {
        this.type(firstNameLocator, UserData.firstName);
    }

    public void inputMiddleName(MockUserData UserData) {
        this.type(middleNameLocator, UserData.middleName);
    }

    public void inputLastName(MockUserData UserData) {
        this.type(lastNameLocator, UserData.lastName);
    }

    public void inputEmailAddress(MockUserData UserData) {
        this.type(emailAddressLocator, UserData.emailAddress);
    }

    public void inputPassword(MockUserData UserData) {
        this.type(passwordLocator, UserData.password);
    }

    public void inputConfirmPassword(MockUserData UserData) {
        this.type(confirmPasswordLocator, UserData.password);
    }

    public void inputSignUp4Newsletter(MockUserData UserData) {
        if (UserData.signUp4Newsletter && !(this.isChecked(this.signUp4NewsletterLocator))) {
            this.click(signUp4NewsletterLocator);
        }
    }

    public void inputRememberMe(MockUserData UserData) {
        if (UserData.rememberMe && !(this.isChecked(this.rememberMeLocator))) {
            this.click(rememberMeLocator);
        }
    }

    public LoginPage goToLoginPage() {
        this.click(interactionBackButton);
    }

    public void Register() {
        this.click(interactionSubmitButton);
    }
}
