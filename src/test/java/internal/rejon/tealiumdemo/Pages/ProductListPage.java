package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.SeleniumDriver;
import internal.rejon.tealiumdemo.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ProductListPage extends BasePage {

    public By productLocator = By.cssSelector("div.category-products ul.products-grid > li.item");
    public By productImageLocator = By.cssSelector("a.product-image > img");
    public By productInfoNameLocator = By.cssSelector("div.product-info h2.product-name > a");
    public By productPriceBoxLocator = By.cssSelector("div.product-info div.price-box");
    public By productActionsLocator = By.cssSelector("div.product-info div.actions");
    public By productPricesLocator = By.cssSelector("div.product-info div.price-box p");
    public By productRegularPriceLocator = new ByChained(productPricesLocator, By.cssSelector("span.regular-price > span.price"));
    public By productOldPriceLocator = new ByChained(productPriceBoxLocator, By.cssSelector("p.old-price"));
    public By productSpecialPriceLocator = new ByChained(productPriceBoxLocator, By.cssSelector("p.special-price"));
    public By productPriceValueLocator = By.cssSelector("span.price");


    public By productAddToWishlistLocator = new ByChained(productActionsLocator, By.cssSelector("li > a.link-wishlist"));

    /*
     * TC: 5. Filter by color.
     */
    public By filterPanelLocator = By.cssSelector("div.sidebar.col-left dl#narrow-by-list dt");

    /* Merr sibling e pare. Mund te mos punoj per shkak se nuk eshte selector i sakte. Konsiderim per XPath.
     * Mund te perdoret dhe klasa "configurable-swatch-list" ama ne raste te vecanta mund te ndodhen disa swatch ne sidepanel.
     */
    // #TODO: selector me i sakte ne baze te subquery: https://developer.mozilla.org/en-US/docs/Web/CSS/Next-sibling_combinator
    public By filterColorLocator = By.cssSelector("+ dd > ol.configurable-swatch-list li");

    /* Marrim swatch dhe elementin e pare te ngjyres
     */
    public By filterColorSwatchLocator = By.cssSelector("ol.configurable-swatch-list > li");

    public By blackColorSwatchLocator = By.cssSelector("div.product-info > ul.configurable-swatch-color > li.option-black[data-option-label=\"option-black\"]");
    public By swatchLinkLocator = By.cssSelector("a.swatch-link");

    public By filterPriceLocator = By.cssSelector("ol > li > a");
    public By sortControl = By.cssSelector("div.sort-by select[title=\"Sort By\"]");

    public ProductListPage(WebDriver driver, boolean useGet) {
        super(driver);
        //String[] strings = driver.getCurrentUrl().split(new StringBuffer(this.baseUrl).insert(0,'^').toString());
        //this.subpath = strings[strings.length-1]; // Marrim elementin e fundit gjithmone (eleminojme OutofBounds)
        if(useGet) {
            this.driver.get(this.baseUrl + this.subpath);
            SeleniumDriver.setConsentCookie();
        }
    }

    public List<WebElement> getProductList() {
        return this.getWebElements(productLocator);
    }

    public int getProductCount() {
        return this.getProductList().size();
    }

    public WebElement getProduct() {
        return this.getWebElement(productLocator);
    }

    public boolean isStyleChangedOnHoverImg(WebElement element) {
        String[] initialBorderValues = new String[4];
        String[] currentBorderValues = new String[4];
        initialBorderValues[0] = element.getCssValue("border-bottom-color");
        initialBorderValues[1] = element.getCssValue("border-left-color");
        initialBorderValues[2] = element.getCssValue("border-right-color");
        initialBorderValues[3] = element.getCssValue("border-top-color");

        this.hoverOnElement(element);

        currentBorderValues[0] = element.getCssValue("border-bottom-color");
        currentBorderValues[1] = element.getCssValue("border-left-color");
        currentBorderValues[2] = element.getCssValue("border-right-color");
        currentBorderValues[3] = element.getCssValue("border-top-color");
        return !(Arrays.equals(initialBorderValues, currentBorderValues));
    }

    public boolean isStyleChangedOnHoverText(WebElement element) {
        String initialTextColor = element.getCssValue("color");
        this.hoverOnElement(element);
        String currentTextColor = element.getCssValue("color");
        return !(initialTextColor.equals(currentTextColor));
    }

    /*
     * Verifikim qe kemi disa prices ne pricebox. Duhet te verifikoj qe cmimet nuk jane bosh.
     */
    public boolean multiplePricesCheck(WebElement rootElement) {
        List<WebElement> priceElements = this.getWebElements(productPricesLocator, rootElement);
        boolean numOfPriceBoxes = (priceElements.size() == 2);
        boolean pricesNotEmpty = true;
        for(WebElement priceElement : priceElements) {
            if(this.getWebElement(productPriceValueLocator, priceElement).getText().isEmpty()){
                pricesNotEmpty = false;
            }
        }
        return numOfPriceBoxes && pricesNotEmpty;
    }

    public boolean oldPriceStyleCheck(WebElement rootElement) {
        WebElement oldPrice = this.getWebElement(productOldPriceLocator, rootElement).findElement(productPriceValueLocator);
        boolean strikethrough = oldPrice.getCssValue("text-decoration-line").equals("line-through");
        boolean color = oldPrice.getCssValue("text-decoration-color").equals(StyleGuidelines.PriceStyle.oldPriceColor);
        return strikethrough && color;
    }

    /*
     * Marrim vlerat css per "text-decoration-line" dhe "text-decoration-color" dhe i krahasojme me verat qe kerkohen
     */
    public boolean specialPriceStyleCheck(WebElement rootElement) {
        WebElement specialPrice = this.getWebElement(productSpecialPriceLocator, rootElement).findElement(productPriceValueLocator);
        // nullable?
        boolean notStrikethrough = specialPrice.getCssValue("text-decoration-line").equals("none");
        boolean color = specialPrice.getCssValue("text-decoration-color").equals(StyleGuidelines.PriceStyle.specialPriceColor);
        return notStrikethrough && color;
    }

    public boolean swatchStyleCheck(WebElement rootElement) {
        String[] borderValues = new String[4];
        borderValues[0] = rootElement.getCssValue("border-bottom-color");
        borderValues[1] = rootElement.getCssValue("border-left-color");
        borderValues[2] = rootElement.getCssValue("border-right-color");
        borderValues[3] = rootElement.getCssValue("border-top-color");
        for(String border : borderValues) {
            if(!border.equals(StyleGuidelines.HoverStyle.borderHoverColor))
                return false;
        }
        return true;
    }

    /*
     * Neqoftese nje nga kushtet nuk permbushet kthejme False.
     */
    public boolean checkSalePrices() {
        for (WebElement product : this.getProductList()) {
            this.scroll2ElementJS(product);
            if(multiplePricesCheck(product) && oldPriceStyleCheck(product) && specialPriceStyleCheck(product)){
                return true;
            }
        }
        return false;
    }

    /**
     * Filtrojme sipas ngjyres se zeze:
     * 1. Gjejme elmentin Header te color dhe marrim tabelen e ngjyrave
     * 2. Zgjedhim ngjyren e pare (te zezen).
     */
    public ProductListPage filterByColor(){
        List<WebElement> filters = this.getWebElements(filterPanelLocator);
        WebElement colorFilterElement = null;
        for (WebElement filter : filters) {
            if(filter.getText().equals("Color"))
                colorFilterElement = filter;
        }
        if(colorFilterElement == null) {
            return this;
        }
        else {
            List<WebElement> colors = this.getWebElements(filterColorLocator, colorFilterElement);
            this.click(this.getWebElement(By.cssSelector("a"), colors.get(0)));
        }
        return this;
    }

    /*
     * TC 5. Color filtering check.
     */
    public boolean checkSelectedColor(){
        boolean classCheck, styleCheck;
        for(WebElement product : this.getProductList()) {
            WebElement blackSwatchElement = this.getWebElement(blackColorSwatchLocator, product);
            classCheck = blackSwatchElement.getDomAttribute("class").contains("selected");
            styleCheck = swatchStyleCheck(this.getWebElement(swatchLinkLocator, blackSwatchElement));
            if(!(classCheck && styleCheck))
                return false;
        }
        return true;
    }

    /**
     * Filtro sipas groupit te cmimit. Default zgjedh grupin e pare (0..99).
     */
    public void filterByPrice(){
        List<WebElement> filters = this.getWebElements(filterPanelLocator);
        WebElement priceFilterElement = null;
        for (WebElement filter : filters) {
            if(filter.getText().equals("Price"))
                priceFilterElement = filter;
        }
        if(priceFilterElement == null) {
            return;
        }
        else {
            List<WebElement> prices = this.getWebElements(filterPriceLocator, priceFilterElement);
            this.click(this.getWebElement(By.cssSelector("a"), prices.get(0)));
        }
    }

    public double getProductPrice(WebElement rootElement) {
       return Util.priceString2Int(this.getWebElement(productRegularPriceLocator, rootElement).getText());
    }

    /*
     * TC 5. Price filtering check.
     */
    public boolean checkPrices() {
        for(WebElement product : this.getProductList()) {
            if(this.getProductPrice(product) < 0 || this.getProductPrice(product) > 99.99)
                return false;
        }
        return true;
    }

    /*
     * TC 6. Check price sorting
     */
    public boolean checkPriceSorting() {
        ArrayList<Double> prices = new ArrayList<Double>();
        for(WebElement product : this.getProductList()) {
            prices.add(this.getProductPrice(product));
        }

        for(int i = 0; i < (prices.size() - 1); i++) {
            if(prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public ProductListPage setPriceSorting() {
       new Select(this.getWebElement(sortControl)).selectByVisibleText("Price");
       return this;
    }

    public ProductListPage addToWishlist(int index) {
        WebElement product;
        if(index < this.getProductList().size() && index > 0){
            product = this.getProductList().get(index);
            this.click(this.getWebElement(productAddToWishlistLocator, product));
        }
        return this;
    }

    /**
     * Shtim ne wishlist dergon ne UserAccountPage -> Pas cdo OP kthehemi mbrapa.
     */
    public ProductListPage addFirstNToWishlist(int n) {
        if(this.getProductList().size() >= n && n > 0){
            for(int index = 0; index < n; index++){
                this.addToWishlist(index);
                this.goBack(); //this.goToHomePage().goToWomenProductList().setPriceSorting();
            }
        }
        return this;
    }

    public boolean hoverProductStyleChange() {
        WebElement product = this.getProduct();
        this.scroll2ElementJS(product);
        boolean imageBorderChanged = isStyleChangedOnHoverImg(this.getWebElement(productImageLocator, product));
        boolean textColorChanged = isStyleChangedOnHoverText(this.getWebElement(productInfoNameLocator, product));
        return imageBorderChanged && textColorChanged;
    }

    public boolean hoverAllProductsStyleChange() {
        for (WebElement product : this.getProductList()) {
            this.scroll2ElementJS(product);
            boolean imageBorderChanged = isStyleChangedOnHoverImg(this.getWebElement(productImageLocator, product));
            boolean textColorChanged = isStyleChangedOnHoverText(this.getWebElement(productInfoNameLocator, product));
            if(!(imageBorderChanged && textColorChanged)) {
                return false;
            }
        }
        return true;
    }
}
