package io.testpro.deens;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class PLPTests {
    @Test
    public void PC64() {
        //Verify if user can manage guests number on PLP - Version 1
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("[title~= Sydney]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions build = new Actions(driver);
        build.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'Trip__EditableElement')]/span")), 50, 0).click().build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[xmlns = \"http://www.w3.org/2000/svg\"]"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(@class,'Header__Wrapper')]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'3 ')]")).isDisplayed());
        driver.quit();


    }

    @Test
    public void PC72() {
        // Verify a list of products by Sort by is working
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("London, UK");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions build = new Actions(driver);
        build.moveToElement(driver.findElement(By.xpath("//span[contains(@class,'LocationAutoSuggest')]")), 50, 0).click().build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        build.moveToElement(driver.findElement(By.xpath("//*[contains(@class,'Results__SortWrapper')]/p/span")), 50, 0).click().build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        build.moveToElement(driver.findElement(By.xpath("//ul[contains(@class,'Sort__PopupContent')]/li/span")), 50, 0).click().build().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Relevance']")).isDisplayed());
        driver.quit();


    }

    @Test
    public void PC115() {
        //  Verify functionality of "Help me" button - Version 1
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/search/trip?address=San%20Francisco%2C%20CA%2C%20USA&city=San%20Francisco&countryCode=US&limit=25&locationSearchType=placeData&page=1&state=California");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@to='[object Object]']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Support Center']")).isDisplayed());
driver.quit();
    }
}
