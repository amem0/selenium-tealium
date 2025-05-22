package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_SaleProductStyle extends BaseTest {
    public TC04_SaleProductStyle() {
        super();
    }
    /**
     * Ne kete metode verfikimin e stilit per Sale Product.<br>
     * Assert 1: Shohim neqoftese stili i imazhit dhe text ndryshon (border-color)<br>
     */
    @Test
    public void CheckSalePriceStyle() {
        //this.LoginPreReq();
        HomePage homePage = new HomePage(driver, true);
        ProductListPage productListPage = homePage.goToSaleProductList();
        Assert.assertTrue(productListPage.checkSalePrices());
    }
}
