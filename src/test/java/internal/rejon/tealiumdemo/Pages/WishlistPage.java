package internal.rejon.tealiumdemo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class WishlistPage extends BasePage {

    public By wishlistLocator = By.cssSelector("div.my-account > div.my-wishlist");
    public By wishlistItemLocator = new ByChained(wishlistLocator, By.cssSelector("table#wishlist-table tbody > tr"));

    public By wishlistAddToCartLocator = By.cssSelector("td.customer-wishlist-item-cart button.btn-cart");

    /*
     * TC 7. Whishlist test
     */
    public List<WebElement> getWishlistItems() {
        return this.getWebElements(wishlistItemLocator);
    }

    public ProductPage addItemToCart(int index) {
        if(index < this.getWishlistItems().size()) {
            this.click(this.getWebElement(wishlistAddToCartLocator, this.getWishlistItems().get(index)));
        }
        return this;
    }

    public WishlistPage(WebDriver driver, boolean useGet) {
        super(driver);
        this.subpath = "wishlist";
        if(useGet)
            this.driver.get(this.baseUrl + this.subpath);
    }
}
