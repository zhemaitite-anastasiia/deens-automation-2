package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class PLPTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void userCanManageGuestsNumber(){
        //TC-64
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector("[title~= Sydney]")).click();
        Actions build = new Actions(driver);
        build.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'Trip__EditableElement')]/span")), 50, 0).click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[xmlns = \"http://www.w3.org/2000/svg\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'Header__Wrapper')]"))).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'3 ')]")).isDisplayed());


    }

    @Test
    public void sortingByListOfProducts() {
        // TC-72
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("London, UK", Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'Results__SortWrapper')]/p/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class,'Sort__PopupContent')]/li/span"))).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Relevance']")).isDisplayed());
    }

    @Test
    public void helpMeButtonFunctionality() {
        //  TC-115

        driver.get("https://deens-master.now.sh/search/trip?address=San%20Francisco%2C%20CA%2C%20USA&city=San%20Francisco&countryCode=US&limit=25&locationSearchType=placeData&page=1&state=California");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@to='[object Object]']"))).click();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Support Center']")).isDisplayed());

    }
}
