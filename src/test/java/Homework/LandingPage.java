package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LandingPage {

    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver", "/Users/bondar/Projects/deens-automation-2/src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    private void afterMethodSetup(){
        driver.quit();
    }


    @Test
    public void checkLogoLink(){
        //initializing
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        WebElement logo = driver.findElement(By.cssSelector("div#root a > img"));
        WebElement earnMoneyLink = driver.findElement(By.cssSelector("[href='/earn-money']"));

        //action
        myWaitVar.until(ExpectedConditions.elementToBeClickable(logo)).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
        earnMoneyLink.click();
        logo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
    }

    @Test
    public void checkNavigationBarContent(){
        //initializing
        driver.get("https://deens-master.now.sh/");
        String[] expectedNavigationBarElements = {"Earn Money","•","Create Trip","Login","Sign up"};
        WebElement navigationBar = driver.findElement(By.xpath("//div[@class='DesktopNav__Wrap-bgeqrS dHbCgo']"));
        String[] actualNavigationBarElements = navigationBar.getText().split("\\r?\\n");

        //action
        for (int i=0; i<actualNavigationBarElements.length; i++) {
            Assert.assertEquals(actualNavigationBarElements[i], expectedNavigationBarElements[i]);
        }
    }


    @Test
    public void checkWorldPictureClickability(){
        //initializing
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //action
        WebElement worldPicture = driver.findElement(By.xpath("//img[contains(@class,'lazyloaded')]"));
        Assert.assertTrue(worldPicture.isDisplayed());
    }





}
