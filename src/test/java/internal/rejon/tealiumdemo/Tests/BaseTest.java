package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.MockUserData;
import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.LoginPage;
import internal.rejon.tealiumdemo.SeleniumDriver;
import internal.rejon.tealiumdemo.TestUtils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    String subpath;
    public BaseTest() {}

    /**
     * Login me kredencialet default. Mund te perdoret si PreRequisite per te gjithe TC
     */
    public void LoginPreReq() {
        MockUserData mockUser = new MockUserData();
        HomePage homePage = new HomePage(driver, true);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.inputEmail(mockUser)
                .inputPassword(mockUser)
                .inputRememberMe(mockUser)
                .Login();
    }

    @Parameters({ "browserType" })
    @BeforeMethod
    void setup(@Optional("firefox") String browserType) {
        SeleniumDriver.createDriver(browserType);
        this.driver = SeleniumDriver.getInstance().driver;
        this.driver.manage().window().maximize();
        /*
         * Get e ben vete page object. Nuk ka nevoje per disa driver.get() calls.
         */
        // driver.get(this.URL);
    }

    @AfterMethod
    void teardown() {
        SeleniumDriver.getInstance().closeDriver();
    }

}