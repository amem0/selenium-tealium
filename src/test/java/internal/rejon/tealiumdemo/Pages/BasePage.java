package internal.rejon.tealiumdemo.Pages;

import internal.rejon.tealiumdemo.PropertyParsing;
import internal.rejon.tealiumdemo.Util;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public String baseUrl;
    public String subpath;

    public By accountLocator = By.cssSelector("#header > div > div.skip-links > div > a");
    public By accountListLocator = By.cssSelector("div#header-account ul");

    /*
     * Logged in locators
     */
    public By accountWhishlistLocator = new ByChained(accountListLocator, By.cssSelector("li:nth-child(2) a"));
    public By accountCartLocator = new ByChained(accountListLocator, By.cssSelector("li:nth-child(3) a.top-link-cart"));
    // Login dhe Logout
    public By accountLogIOLocator = new ByChained(accountListLocator, By.cssSelector("li.last a"));

    /*
     * Logged out locators
     */
    public By accountRegisterLocator = new ByChained(accountListLocator, By.cssSelector("a[title=\"Register\"]"));

    public By welcomeMessageLocator = By.cssSelector("p.welcome-msg");

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            this.baseUrl = PropertyParsing.getProperties().getProperty("baseURL");
            this.subpath = PropertyParsing.getProperties().getProperty("subpathModifier");
        }
        catch (IOException e) {}
    }

    public BasePage(WebDriver driver, String subpath) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            this.baseUrl = PropertyParsing.getProperties().getProperty("baseURL");
            this.subpath = PropertyParsing.getProperties().getProperty("subpathModifier") + subpath;
        }
        catch (IOException e) {}
    }

    public boolean checkUserHead(String userName) {
        return this.getWebElement(this.welcomeMessageLocator).getText().equals(userName);
    }

    public void clickOnAccount() {
        this.navigate(accountLocator);
    }

    public HomePage goToHomePage() {
        return new HomePage(driver, true);
    }

    public LoginPage goToLoginPage() {
        this.navigate(accountLocator, accountLogIOLocator, true);
        return new LoginPage(this.driver, false);
    }

    public ShoppingCartPage goToShoppingCart() {
        this.navigate(accountListLocator, accountCartLocator, true);
        return new ShoppingCartPage(this.driver, false);
    }

    public WishlistPage goToWishlistPage() {
        this.navigate(accountListLocator, accountWhishlistLocator, true);
        return new WishlistPage(this.driver, false);
    }

    public RegisterPage goToRegisterPage() {
        this.navigate(accountListLocator, accountRegisterLocator, true);
        return new RegisterPage(this.driver, false);
    }

    public HomePage LogOut() {
        this.navigate(accountListLocator, accountLogIOLocator, true);
        return new HomePage(driver, false);
    }

    public int getWishlistItemCountHeader() {
        int itemCount = 0;
        this.clickOnAccount();
        if(this.isDisplayed(accountWhishlistLocator))
            itemCount = Util.itemCountString2Int(this.getWebElement(accountWhishlistLocator).getText());
        this.clickOnAccount();
        return itemCount;
    }

    public int getCartItemCountHeader() {
        int itemCount = 0;
        this.clickOnAccount();
        if(this.isDisplayed(accountCartLocator))
            itemCount = Util.itemCountString2Int(this.getWebElement(accountCartLocator).getText());
        this.clickOnAccount();
        return itemCount;
    }

    public WebElement getWebElement(By locator) {
        if(this.isDisplayed(locator))
            return this.driver.findElement(locator);
        return null;
    }

    public WebElement getWebElement(By locator, WebElement rootElement) {
        if(this.isDisplayed(locator))
            return rootElement.findElement(locator);
        return null;
    }

    public List<WebElement> getWebElements(By locator) {
        if(this.isDisplayed(locator))
            return this.driver.findElements(locator);
        return null;
    }

    public List<WebElement> getWebElements(By locator, WebElement rootElement) {
        if(this.isDisplayed(locator))
            return rootElement.findElements(locator);
        return null;
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    public void click(By locator) {
        getWebElement(locator).click();
    }

    public void click(WebElement element) {
        if(this.isDisplayed(element))
            element.click();
    }

    public void type(By locator, String text) {
        getWebElement(locator).sendKeys(text);
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void clear(By locator) {
        getWebElement(locator).clear();
    }

    public void clear(WebElement element) {
        element.clear();
    }

    public void navigate(By locator) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(this.getWebElement(locator));
        actions.click().build().perform();
    }

    public void moveElement(WebElement srcElement, WebElement dstElement) {
        Actions actions = new Actions(this.driver);
        actions.dragAndDrop(srcElement, dstElement).build().perform();
    }

    public void navigate(By locator1, By locator2, boolean firstClick) {
        Actions actions = new Actions(this.driver);
        if(firstClick)
            actions.click(this.getWebElement(locator1)).build().perform();
        else
            actions.moveToElement(this.getWebElement(locator1));
        if(isDisplayed(locator2))
            actions.moveToElement(this.getWebElement(locator2));
        actions.click().build().perform();

    }

    public void navigate(By locator1, By locator2, By locator3) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(this.getWebElement(locator1));
        if(isDisplayed(locator2)) {
            actions.moveToElement(this.getWebElement(locator2));
            if(isDisplayed(locator3)) {
                actions.moveToElement(this.getWebElement(locator3));
            }
        }
        actions.click().build().perform();
    }

    /*
     * Chromium vtm
     */
    public void scroll2Element(By locator) {
        if (driver instanceof ChromeDriver) {
            Actions action = new Actions(this.driver);
            action.scrollToElement(this.getWebElement(locator)).build().perform();
            return;
        }
        else {
            scroll2ElementJS(locator);
        }
    }

    /*
     * Punon per te gjithe browsers. (js)
     */
    public void scroll2ElementJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView()", this.getWebElement(locator));
    }

    public void scroll2ElementJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void hoverOnElement(By locator) {
        new Actions(this.driver).moveToElement(this.getWebElement(locator)).build().perform();
    }

    public void hoverOnElement(WebElement element) {
        new Actions(this.driver).moveToElement(element).build().perform();
    }

    public void doubleClick(By locator) {
        Actions action = new Actions(this.driver);
        action.doubleClick(this.getWebElement(locator)).build().perform();
    }

    public void rightClick(By locator) {
        Actions action = new Actions(this.driver);
        action.contextClick(getWebElement(locator)).build().perform();
    }

    public boolean isClickable(By locator) {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }

    public boolean isChecked(By locator) {
        if(isDisplayed(locator) && isClickable(locator)){
            return this.getWebElement(locator).isSelected();
        }
        return false;
    }

    public boolean isDisplayed(By locator) {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }

    public boolean isDisplayed(WebElement element) {
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }
}