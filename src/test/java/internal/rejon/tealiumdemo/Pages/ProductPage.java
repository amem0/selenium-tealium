package internal.rejon.tealiumdemo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class ProductPage extends BasePage {

    public By productShopLocator = By.cssSelector("div.product-shop");
    public By productOptionSelectLocator = new ByChained(productShopLocator, By.cssSelector("div.product-options"));
    public By productColorSelectLocator = new ByChained(productOptionSelectLocator, By.cssSelector("dl > dd:nth-child(1) ul#configurable_swatch_color > li"));
    public By productSizeSelectLocator = new ByChained(productOptionSelectLocator, By.cssSelector("dl > dd:nth-child(2) ul#configurable_swatch_size > li"));
    public By productSelectLocator = By.cssSelector("a.swatch-link");

    public By productAddToCartLocator = new ByChained(productShopLocator, By.cssSelector("div.product-options-bottom div.add-to-cart-buttons > button.btn-cart"));

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage selectProductColor(int index) {
        List<WebElement> colors = this.getWebElements(productColorSelectLocator);
        if(index < colors.size()) {
            this.click(this.getWebElement(productSelectLocator, colors.get(index)));
        }
        return this;
    }

    public ProductPage selectProductSize(int index) {
        List<WebElement> size = this.getWebElements(productSizeSelectLocator);
        if(index < size.size()) {
            this.click(this.getWebElement(productSelectLocator, size.get(index)));
        }
        return this;
    }

    public ProductPage addProductToCart() {
        this.click(productAddToCartLocator);
        return this;
    }

}
