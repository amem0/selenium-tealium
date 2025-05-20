package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.MockUserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class HomePage extends BasePage {
    public By nav = By.cssSelector("nav#nav > ol.nav-primary");
    public By womenNav = By.cssSelector("ol.nav-primary > li.level0 > a[href$=\"women.html\"]");
    public By womenNavAll = new ByChained(womenNav, By.cssSelector("a.level1[href$=\"women.html\"]"));
    public By menNav = By.cssSelector("ol.nav-primary > li.level0 > a[href$=\"men.html\"]");
    public By menNavAll = new ByChained(menNav, By.cssSelector("a.level1[href$=\"men.html\"]"));
    public By saleNav = By.cssSelector("ol.nav-primary > li.level0 > a[href$=\"sale.html\"]");
    public By saleNavAll = new ByChained(saleNav, By.cssSelector("a.level1[href$=\"sale.html\"]"));

    public HomePage(WebDriver driver, boolean useGet) {
        super(driver);
        if (useGet)
            this.driver.get(this.baseUrl);
    }

    public ProductListPage goToWomenProductList() {
        this.navigate(womenNav, womenNavAll, false);
        return new ProductListPage(driver, false);
    }

    public ProductListPage goToMenProductList() {
        this.navigate(menNav, menNavAll, false);
        return new ProductListPage(driver, false);
    }

    public ProductListPage goToSaleProductList() {
        this.navigate(saleNav, saleNavAll, false);
        return new ProductListPage(driver, false);
    }

}
