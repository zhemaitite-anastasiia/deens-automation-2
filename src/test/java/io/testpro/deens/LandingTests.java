package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;

//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;

import org.testng.annotations.Test;
import org.testng.Assert;


public class LandingTests {

    @Test
    public void TitleIsCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        Assert.assertEquals(driver.getTitle(), "Deens, plan my trip!", "Home page Title doesn't match.");
        driver.quit();
    }

    @Test
    private void HeaderIsLoaded() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        WebElement header = driver.findElement(By.cssSelector("header[class^=\"TopBar\"]"));
        Assert.assertEquals(header.isDisplayed(), true, "Landing page header is not displayed.");
        driver.quit();
    }

    @Test
    private void LogoIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        WebElement logo = driver.findElement(By.cssSelector("div[class^=\"Logo\""));
        Assert.assertEquals(logo.isDisplayed(), true, "Logo is not visible.");
        driver.quit();
    }

    // Regular view: verify the top menu navigation bar is loaded
    @Test
    private void NavBarIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        //Maximizing the screen size
        driver.manage().window().maximize();
        WebElement navBar = driver.findElement(By.cssSelector("div[class^=\"DesktopNav\""));
        Assert.assertEquals(navBar.isDisplayed(), true, "The top menu is not visisble.");
        driver.quit();
    }

    // Mobile view: verify the top menu navigation bar is NOT loaded
    @Test
    private void Mobile_NavBarIsNotVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        // Setting the screen size to Iphone X parameters
        driver.manage().window().setSize(new Dimension(375,812));
        // flag=true, if the menu is visible, flag=false - otherwise
        boolean flag = true;
        try {
            WebElement navBar = driver.findElement(By.cssSelector("div[class^=\"DesktopNav\""));
        } catch(NoSuchElementException ex) {
            flag = false;
        }
        Assert.assertEquals(flag, false, "The top menu is visisble on mobile view.");
        driver.quit();
    }
}