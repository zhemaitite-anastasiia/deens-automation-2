package io.testpro.deens;


import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class LandingTests {



    @Test //Vladimir
    public void checkLogoLink(){
        //initializing
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        driver.quit();
    }

    @Test //Vladimir
    public void checkNavigationBarContent(){
        //initializing
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/");
        String[] expectedNavigationBarElements = {"Earn Money","•","Create Trip","Login","Sign up"};
        WebElement navigationBar = driver.findElement(By.xpath("//div[@class='DesktopNav__Wrap-bgeqrS dHbCgo']"));
        String[] actualNavigationBarElements = navigationBar.getText().split("\\r?\\n");

        //action
        for (int i=0; i<actualNavigationBarElements.length; i++) {
            Assert.assertEquals(actualNavigationBarElements[i], expectedNavigationBarElements[i]);
        }
        driver.quit();
    }


    @Test //Vladimir
    public void checkWorldPictureClickability(){
        //initializing
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //action
        WebElement worldPicture = driver.findElement(By.xpath("//img[contains(@class,'lazyloaded')]"));
        Assert.assertTrue(worldPicture.isDisplayed());
        driver.quit();
    }

}
