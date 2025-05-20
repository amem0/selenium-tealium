package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.MockUserData;
import internal.rejon.tealiumdemo.Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC01_RegisterTest extends BaseTest {
    public TC01_RegisterTest() {
        super();
    }

    /**
     * Ne kete metode kryejme rregjistrimin e user.<br>
     * Assert 1: Shohim neqoftese eshte ndryshuar faqja<br>
     * Assert 2: Kemi marre mesazh suksesi<br>
     * Assert 3: Mesazhi i suksesit perputhet<br>
     * Shenim: TC perdor nje MockUser statik
     * 1. Mund te krijohet nga cdo test case me konstruktor
     * 2. Mund te kalohet ne TestNG params
     * Shenim: Ndryshimi i URL ndoshta mund te behet me driver event.
     */
    @Test
    public void register() {
        MockUserData userData = new MockUserData();
        HomePage homePage = new HomePage(this.driver, true);
        RegisterPage registerPage = homePage.goToRegisterPage();
        String currentURL = this.driver.getCurrentUrl();
        registerPage.inputFirstName(userData)
                .inputMiddleName(userData)
                .inputLastName(userData)
                .inputEmailAddress(userData)
                .inputPassword(userData)
                .inputConfirmPassword(userData)
                .inputSignUp4Newsletter(userData)
                .inputRememberMe(userData)
                .Register();

        // Kemi ndryshuar site
        Assert.assertNotEquals(this.driver.getCurrentUrl(), currentURL);
        // Kemi marre mesazhin e suksesit
        if(!(this.driver.getCurrentUrl().equals(currentURL))){
            UserAccountPage userAccountPage = new UserAccountPage(this.driver, false);
            Assert.assertTrue(userAccountPage.getSuccessMessage());
            Assert.assertEquals(userAccountPage.getMessageContent(),
                    "Thank you for registering with Tealium Ecommerce.");
            userAccountPage.LogOut();
        }

    }
}
