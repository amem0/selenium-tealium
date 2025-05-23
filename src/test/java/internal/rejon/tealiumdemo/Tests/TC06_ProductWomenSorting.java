package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06_ProductWomenSorting extends BaseTest {
    public TC06_ProductWomenSorting() {
        super();
    }

    /**
     * Women Product List dhe selektojme sorting me Price. <br>
     * Assert 1: Verifikojme sorting (Ascending)
     * Assert 2: Verifikojme numrin e produkteve ne wishlist nga Header.
     */
    @Test(groups = {"ProductSorting"})
    public void ProductWomenSortingTest() {
        this.LoginPreReq();
        HomePage homePage = new HomePage(driver, true);
        ProductListPage productListPage = homePage.goToWomenProductList();
        productListPage.setPriceSorting();
        Assert.assertTrue(productListPage.checkPriceSorting());
        productListPage.addFirstNToWishlist(2);
        Assert.assertEquals(productListPage.getWishlistItemCountHeader(), 2);
    }
}
