package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver.Timeouts;
import java.util.concurrent.TimeUnit;

//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;


public class LandingTests {

    @Test
    public void titleIsCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement waitTitle = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        Assert.assertEquals(driver.getTitle(), "Deens, plan my trip!", "Landing page titile doesn't match.");
        driver.quit();
    }

    @Test
    private void headerIsLoaded() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        Assert.assertEquals(driver.findElement(By.cssSelector("header[class^=\"TopBar\"]")).isDisplayed(),
                true, "Landing page header is not displayed.");
        driver.quit();
    }

    @Test
    private void logoIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class^=\"Logo\"")).isDisplayed(),
                true, "Logo is not visible.");
        driver.quit();
    }

    // Regular view: verify the top menu navigation bar is loaded
    @Test
    private void navBarIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        //Maximizing the screen size
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class^=\"DesktopNav\"")).isDisplayed(),
                true,"The top menu is not visisble.");
        driver.quit();
    }

    // Mobile view: verify the top menu navigation bar is NOT loaded
    @Test
    private void mobile_NavBarIsNotVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        // Setting the screen size to Iphone X parameters
        driver.manage().window().setSize(new Dimension(375, 812));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // flag=true, if the menu is visible, flag=false - otherwise
        boolean flag = true;
        try {
            driver.findElement(By.cssSelector("div[class^=\"DesktopNav\""));
        } catch(NoSuchElementException ex) {
            flag = false;
        }
        Assert.assertEquals(flag, false, "The top menu is visible on mobile view.");
        driver.quit();
    }
}