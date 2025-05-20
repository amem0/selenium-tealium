package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_ProductMenBlackSub100 extends BaseTest{
    public TC05_ProductMenBlackSub100() {
        super();
    }
    /**
     * Test produkte per Meshkujt te zeza me poshte se $100 <br>
     * Assert 1: Verifikojme listen per stilin e selection.
     * Assert 2: Verifikojme numrin e produkteve te filtruar me cmim.
     * Assert 3: Verifikojme qe produktet kane cmim brenda kushteve
     */
    @Test
    public void ProductMenBlackSub100() {
        this.LoginPreReq();
        HomePage homePage = new HomePage(driver, true);
        ProductListPage productListPage = homePage.goToMenProductList();
        productListPage.filterByColor();
        Assert.assertTrue(productListPage.checkSelectedColor());
        productListPage.filterByPrice();
        Assert.assertEquals(productListPage.getProductCount(), 3);
        Assert.assertTrue(productListPage.checkPrices());
    }
}
