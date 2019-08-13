package io.testpro.deens;


import java.util.concurrent.TimeUnit;

import io.testpro.deens.Pages.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.NoSuchElementException;


public class LandingTests extends BaseTest {

    @Test //Vladimir
    public void checkLogoLink(){
        //initializing
        driver.manage().window().maximize();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        WebElement logo =myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div#root a > img")))); driver.findElement(By.cssSelector("div#root a > img"));
        WebElement earnMoneyLink = myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[href='/earn-money']"))));

        //actions
        logo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
        earnMoneyLink.click();
        logo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
        driver.quit();
    }

    @Test //Vladimir
    public void checkNavigationBarContent(){
        //initializing
        driver.manage().window().maximize();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        String[] expectedNavigationBarElements = {"Earn Money","•","Create Trip","Login","Sign up"};
        WebElement navigationBar =myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='DesktopNav__Wrap-bgeqrS dHbCgo']"))));
        String[] actualNavigationBarElements = navigationBar.getText().split("\\r?\\n");

        //actions
        for (int i=0; i<actualNavigationBarElements.length; i++) {
            Assert.assertEquals(actualNavigationBarElements[i], expectedNavigationBarElements[i]);
        }
        driver.quit();
    }


    @Test //Vladimir
    public void checkWorldPictureClickability(){
        //initializing
        driver.manage().window().maximize();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        WebElement worldPicture = myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@class,'lazyloaded')]")));
        //Wy the following way is not working???
        // WebElement worldPicture =myWaitVar.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[contains(@class,'lazyloaded')]"))));

        //actions
        Assert.assertTrue(worldPicture.isDisplayed());
        driver.quit();
    }


    @Test
    public void titleIsCorrect() {
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        assertEquals(driver.getTitle(), "Deens, plan my trip!", "Landing page titile doesn't match.");
    }

    // Verify header is loaded
    @Test
    private void headerIsLoaded() {
        driver.get("https://deens-master.now.sh/");
        assertEquals(driver.findElement(By.cssSelector("header[class^='TopBar']")).isDisplayed(),
                true, "Landing page header is not displayed.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("header[class^='TopBar']")).isDisplayed());
    }

    // Verify logo is visible
    @Test
    private void logoIsVisible() {
        driver.get("https://deens-master.now.sh/");
        assertEquals(driver.findElement(By.cssSelector("div[class^='Logo']")).isDisplayed(),
                true, "Logo is not visible.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Regular view: verify the top menu navigation bar is loaded
    @Test
    private void navBarIsVisible() {
        //Maximizing the screen size
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.cssSelector("div[class^='DesktopNav']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class^='DesktopNav']")).isDisplayed());
    }

    // Mobile view: verify the top menu navigation bar is NOT loaded
    @Test
    private void mobile_NavBarIsNotVisible() {
        // Setting the screen size to Iphone X parameters
        driver.manage().window().setSize(new Dimension(375, 812));
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // navbarIsFound=true, if the menu is visible, navbarIsFound=false - otherwise
        boolean navbarIsFound = true;
        try {
            driver.findElement(By.cssSelector("div[class^='DesktopNav']"));
        } catch ( NoSuchElementException ex ) {
            navbarIsFound = false;
            System.out.println("WebDriver couldn't locate the element.");
        } catch ( WebDriverException ex ) {
            navbarIsFound = false;
        } finally {
            assertFalse(navbarIsFound);
        }
    }


    // Verify trip creator name link
    @Test
    public void tripCreatorNameCheckTest() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openPage();

        //Click on the trip creator name
        String tripCreatorNameLink = "[href*='beabatravel']";
        landingPage.waitUntilClickable(By.cssSelector(tripCreatorNameLink)).click();

        //Check that user was redirected to the right page
        String userBasicProfileName = ".UserBasicInfo__NameDiv-hmhybR";
        String expectedLink = "https://deens-master.now.sh/user/beabatravel";
        String currentLink = driver.getCurrentUrl();
        landingPage.waitUntilClickable(By.cssSelector(userBasicProfileName));
        assertEquals(currentLink, expectedLink);
    }

    // Verify the list of available creator trips
    @Test
    public void listOfCreatorTripsCheckTest() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openPage();

        //click right carousel button
        landingPage.waitUntilClickable(By.cssSelector("button[class*='Carousel__ButtonRight']"));
        WebElement rightCreatorCarouselButton = driver.findElements(By.cssSelector("button[class*='Carousel__ButtonRight']")).get(1);
        rightCreatorCarouselButton.click();

        //Count the number of trips
        List<WebElement> listOfCreatorTrips = driver.findElements(By.xpath("//main/div[6]//*[@class='slick-slide' or @class='slick-slide slick-active slick-current']"));
        assertEquals(listOfCreatorTrips.size(), 5);
    }

    // Verify that clicking on creators trip name redirects to the trip details page
    @Test
    public void creatorTripNameCheckTest() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openPage();

        //Open Romantic week-end in NYC
        String romanticNewYorkImageLink = "//main/div[6]//*[@class='slick-slide slick-active']//*[@title='Romantic week-end in NYC']";
        landingPage.waitUntilClickable(By.xpath(romanticNewYorkImageLink)).click();

        //Check that user was redirected to the right page
        String tripName = ".Header__Title-eurZFS";
        String expectedTripName = "Romantic week-end in NYC";
        String currentTripName = landingPage.waitUntilClickable(By.cssSelector(tripName)).getText();
        assertEquals(currentTripName, expectedTripName);
    }
}