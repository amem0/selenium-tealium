package internal.rejon.tealiumdemo.Pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl;
    String subpath;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.baseUrl = ;
    }

    public BasePage(WebDriver driver, String subpath) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.baseUrl = ;
        this.subpath = ;
    }

    public WebElement getWebElement(By locator) {
        return this.driver.findElement(locator);
    }

    public WebElement getWebElement(By locator, WebElement rootElement) {
        return rootElement.findElement(locator);
    }

    public List<WebElement> getWebElements(By locator) {
        return this.driver.findElements(locator);
    }

    public List<WebElement> getWebElements(By locator, WebElement rootElement) {
        return rootElement.findElements(locator);
    }


    public void click(By locator) {
        getWebElement(locator).click();
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(By locator, String text) {
        getWebElement(locator).sendKeys(text);
    }

    public void clear(By locator) {
        getWebElement(locator).clear();
    }

    public void navigate(By locator) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(this.getWebElement(locator));
        actions.click().build().perform();
    }

    public void moveElement(WebElement srcElement, WebElement dstElement) {
        Actions actions = new Actions(this.driver);
        //actions.clickAndHold(srcElement);
        //actions.moveToElement(dstElement);
        actions.dragAndDrop(srcElement, dstElement).build().perform();
    }

    public void navigate(By locator1, By locator2) {
        Actions actions = new Actions(this.driver);
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
        catch (TimeoutException e){
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
        catch (TimeoutException e){
            return false;
        }
        return true;
    }
}