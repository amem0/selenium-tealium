package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.ShoppingCartPage;
import internal.rejon.tealiumdemo.Pages.WishlistPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07_ShoppingCartTest extends BaseTest {
    public TC07_ShoppingCartTest() {
        super();
    }
    /**
     *
     */
    @Test(dependsOnGroups = {"ProductSorting"}, groups = {"ShoppingCart"})
    public void ShoppingCartTest() {
        this.LoginPreReq();
        HomePage homePage = new HomePage(driver, true);
        WishlistPage wishlistPage = homePage.goToWishlistPage();
        wishlistPage.addItemToCart(0).selectProductColor(0)
                .selectProductSize(0)
                .addProductToCart();

        wishlistPage = wishlistPage.goToHomePage().goToWishlistPage();
        wishlistPage.addItemToCart(0).selectProductColor(0)
                .selectProductSize(0)
                .addProductToCart();

        ShoppingCartPage shoppingCartPage =  wishlistPage.goToHomePage().goToShoppingCart();
        shoppingCartPage.setQtyValue(0,2);
        Assert.assertTrue(shoppingCartPage.checkCartPrice());
    }
}
