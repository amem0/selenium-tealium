package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.MockUserData;
import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.LoginPage;
import internal.rejon.tealiumdemo.Pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_HoverStyleTest extends  BaseTest {
    public TC03_HoverStyleTest() {
        super();
    }
    /**
     * Ne kete metode verfikimin e stilit per Hover.<br>
     * Assert 1: Shohim neqoftese stili i imazhit dhe text ndryshon (border-color)<br>
     */
    @Test
    public void HoverStyleTest() {
        //this.LoginPreReq();
        HomePage homePage = new HomePage(driver, true);
        ProductListPage productListPage = homePage.goToWomenProductList();
        Assert.assertTrue(productListPage.hoverProductStyleChange());
    }
}
