package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait =  new WebDriverWait(driver,10);
    }

    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }
    public WebElement waitUntilVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void typeText(String cssLocator, String value) {
        WebElement element = waitUntilClickable(By.cssSelector(cssLocator));
        element.click();
        element.sendKeys(value);
    }

    public void clickToElement(String locator) {
        WebElement element = waitUntilClickable(By.cssSelector(locator));
        element.click();
    }
}
