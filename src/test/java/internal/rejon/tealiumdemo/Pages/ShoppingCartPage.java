package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.SeleniumDriver;
import internal.rejon.tealiumdemo.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    public By cartEmptyLocator = By.cssSelector("div.cart-empty");
    public By cartEmptyMsgLocator = new ByChained(cartEmptyLocator, By.cssSelector("p"));

    public By cartTableLocator = By.cssSelector("div.cart table#shopping-cart-table");
    public By cartItemsLocator = new ByChained(cartTableLocator, By.cssSelector("tbody > tr"));

    /*
     * Cart Table item locators
     */
    public By itemPriceLocator = By.cssSelector("td.product-cart-price span.price");
    public By itemTotalPriceLocator = By.cssSelector("td.product-cart-total span.cart-price > span");
    public By itemQtyRootLocator = By.cssSelector("dt.product-cart-actions");
    public By itemQtyValueLocator = new ByChained(itemQtyRootLocator, By.cssSelector("input.qty"));
    public By itemQtyUpdateButtonLocator = new ByChained(itemQtyRootLocator, By.cssSelector("button[name=\"update_cart_action\"]"));

    /*
     * Item action locators
     */
    public By itemDeleteButtonLocator = By.cssSelector("td.product-cart-remove > a.btn-remove.btn-remove2");

    /*
     * Cart total table
     */
    public By cartTotal = By.cssSelector("div.cart-totals");
    public By cartTotalTable = new ByChained(cartTotal, By.cssSelector("table#shopping-cart-totals-table"));
    public By cartTotalGrandTotalLocator = new ByChained(cartTotal, By.cssSelector("tfoot tr td:nth-child(2) span"));
    public By cartTotalSubTotalLocator = new ByChained(cartTotal, By.cssSelector("tbody tr td:nth-child(2) span"));

    public int previousItemCount = 0;

    public List<WebElement> getItemsInCart() {
        return this.getWebElements(cartItemsLocator);
    }

    /*
     * Get Item price / unit (base)
     */
    public double getItemPrice(WebElement item) {
        return Util.priceString2Int(this.getWebElement(itemPriceLocator, item).getText());
    }

    /*
     * Get Item's subtotal price
     */
    public double getItemTotalPrice(WebElement item) {
        return Util.priceString2Int(this.getWebElement(itemTotalPriceLocator, item).getText());
    }

    /*
     * Calculate cart's subtotal (Qty).
     */
    public double computeCartSubTotal() {
        double subtotal = 0;
        for(WebElement item : this.getWebElements(cartItemsLocator)) {
            subtotal += this.getItemTotalPrice(item);
        }
        return subtotal;
    }

    /*
     * Get cart's subtotal
     */
    public double getCartSubTotal() {
        return Util.priceString2Int(this.getWebElement(cartTotalGrandTotalLocator).getText());
    }

    /*
     * TC. 7
     */
    public boolean checkCartPrice() {
        return (boolean) (computeCartSubTotal() == getCartSubTotal());
    }

    public void setQtyValue(int itemIndex, int qty) {
        List<WebElement> items = this.getItemsInCart();
        if(itemIndex <= (items.size()-1)) {
            WebElement item = items.get(itemIndex);
            WebElement itemQty = this.getWebElement(itemQtyValueLocator, item);
            this.clear(itemQty);
            this.type(itemQty, String.valueOf(qty));
            this.click(this.getWebElement(itemQtyUpdateButtonLocator, item));
        }
    }

    public boolean checkEmptyCart() {
        return this.getWebElement(cartEmptyMsgLocator).isDisplayed();
    }

    /*
     * Temp unused.
     */
    public int numberOfItems(){
        return this.getItemsInCart().size();
    }

    /*
     * TC.8 Empty Cart
     */
    public void deleteItemFromCart(int index) {
        List<WebElement> items = this.getItemsInCart();
        this.previousItemCount = items.size();
        if(!items.isEmpty()){
            this.click(this.getWebElement(itemDeleteButtonLocator, items.get(index)));
        }
    }

    /**
     * Diferenca midis operacioneve duhet te jete gjithmone 1.
     */
    public boolean validateItemNumberChange() {
        return (previousItemCount - this.numberOfItems()) == 1;
    }

    public ShoppingCartPage(WebDriver driver, boolean useGet) {
        super(driver);
        this.driver = driver;
        this.subpath = "checkout/cart/";
        if(useGet) {
            this.driver.get(this.baseUrl + this.subpath);
            SeleniumDriver.setConsentCookie();
        }
    }
}
