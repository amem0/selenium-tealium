package internal.rejon.tealiumdemo.Tests;

import internal.rejon.tealiumdemo.Pages.HomePage;
import internal.rejon.tealiumdemo.Pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC08_EmptyShoppingCart extends BaseTest {
    public TC08_EmptyShoppingCart(){
        super();
    }
    /**
     * Assert 1: Per cdo heqje verifikim numri.
     * Assert 2: Shopping Cart empty, verifikim nga ekzistenca e elementit.
     */
    @Test
    public void EmptyShoppingCart() {
        this.LoginPreReq();
        ShoppingCartPage shoppingCartPage = new HomePage(driver,true).goToShoppingCart();
        shoppingCartPage.deleteItemFromCart(0);
        Assert.assertTrue(shoppingCartPage.validateItemNumberChange());
        Assert.assertTrue(shoppingCartPage.checkEmptyCart());
    }
}
