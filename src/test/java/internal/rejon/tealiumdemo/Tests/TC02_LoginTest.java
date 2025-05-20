package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.MockUserData;
import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_LoginTest extends BaseTest {
    public TC02_LoginTest() {
        super();
    }

    /**
     * Ne kete metode kryejme login e user.<br>
     * Assert 1: Shohim neqoftese mesazhi i error nuk eshte shfaqur<br>
     * Assert 2: Shohim neqoftese URL eshte ndryshuar drejt UserAccountPage<br>
     * Assert 3: Shfaqet emri i perdoruesit ne Header
     */
    @Test
    public void login() {
        MockUserData mockUser = new MockUserData();
        HomePage homePage = new HomePage(this.driver, true);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.inputEmail(mockUser)
                .inputPassword(mockUser)
                .inputRememberMe(mockUser)
                .Login();
        Assert.assertFalse(loginPage.errorMsgVisible());
        Assert.assertEquals(this.driver.getCurrentUrl(), loginPage.baseUrl + "/customer/account");
        Assert.assertTrue(loginPage.checkUserHead(mockUser.getFullNameUpper()));
        loginPage.LogOut();
    }
}
