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

    public RegisterPage inputFirstName(MockUserData userData) {
        this.type(firstNameLocator, userData.firstName);
        return this;
    }

    public RegisterPage inputMiddleName(MockUserData userData) {
        this.type(middleNameLocator, userData.middleName);
        return this;
    }

    public RegisterPage inputLastName(MockUserData userData) {
        this.type(lastNameLocator, userData.lastName);
        return this;
    }

    public RegisterPage inputEmailAddress(MockUserData userData) {
        this.type(emailAddressLocator, userData.emailAddress);
        return this;
    }

    public RegisterPage inputPassword(MockUserData userData) {
        this.type(passwordLocator, userData.password);
        return this;
    }

    public RegisterPage inputConfirmPassword(MockUserData userData) {
        this.type(confirmPasswordLocator, userData.password);
        return this;
    }

    public RegisterPage inputSignUp4Newsletter(MockUserData userData) {
        if (userData.signUp4Newsletter && !(this.isChecked(this.signUp4NewsletterLocator))) {
            this.click(signUp4NewsletterLocator);
        }
        return this;
    }

    public RegisterPage inputRememberMe(MockUserData userData) {
        if (userData.rememberMe && !(this.isChecked(this.rememberMeLocator))) {
            this.click(rememberMeLocator);
        }
        return this;
    }

    /*
     * Nuk ka nevoje per return LoginPage pasi do krijonte dy GET.
     */
    public void goToLoginPageBack() {
        this.click(interactionBackButton);
    }

    public void Register() {
        this.click(interactionSubmitButton);
    }
}
