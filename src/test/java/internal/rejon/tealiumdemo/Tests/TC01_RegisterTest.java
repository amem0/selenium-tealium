package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.MockUserData;
import internal.rejon.tealiumdemo.Pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC01_RegisterTest extends BaseTest {

    public TC01_RegisterTest(String subpath) {
        super(subpath);
    }

    @Test
    public void register() {
        RegisterPage registerPage = new RegisterPage(this.driver);
    }

    @Test
    public void login() {
        MockUserData mockUser = new MockUserData();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.inputEmail(mockUser)
                 .inputPassword(mockUser)
                 .inputRememberMe(mockUser)
                 .Login();
        Assert.assertFalse(loginPage.errorMsgVisible());
        Assert.assertTrue(loginPage.baseURL.equals());

    }

}
