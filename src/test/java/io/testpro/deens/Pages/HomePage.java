package io.testpro.deens.Pages;
import io.testpro.deens.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    WebDriverWait wait;
    WebDriver driver;


    public HomePage(WebDriver driver, WebDriverWait wait){
        this.wait = wait;
        this.driver = driver;
    }




    public void scrollTill(String xPath){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xPath)));
    }

    public boolean assertIsDisplayed(String xPath) {
        return driver.findElement(By.xpath(xPath)).isDisplayed();
    }

    public WebElement waitUntilClickable(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

}
