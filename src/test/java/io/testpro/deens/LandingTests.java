package io.testpro.deens;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.*;

import java.util.concurrent.TimeUnit;
import java.util.List;

public class LandingTests {

    @Test
    public void titleIsCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        Assert.assertEquals(driver.getTitle(), "Deens, plan my trip!", "Landing page titile doesn't match.");
        driver.quit();
    }

    @Test
    private void headerIsLoaded() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("header[class^=\"TopBar\"]")).isDisplayed());
        driver.quit();
    }

    @Test
    private void logoIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class^=\"Logo\"")).isDisplayed());
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
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class^=\"DesktopNav\"")).isDisplayed());
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

        // headerIsFound = true, if the menu is visible, headerIsFound = false - otherwise
        boolean headerIsFound = true;
        try {
            driver.findElement(By.cssSelector("div[class^=\"DesktopNav\""));
        } catch(NoSuchElementException ex) {
            headerIsFound = false;
            System.out.println("NoSuchElementException exception is thrown.");
        }
        Assert.assertFalse(headerIsFound);
        driver.quit();
    }


    // Verify trip creator name link
    @Test
    public void tripCreatorNameCheckTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //Click on the trip creator name
        String tripCreatorNameLink = "[href*='beabatravel']";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(tripCreatorNameLink)));
        driver.findElement(By.cssSelector(tripCreatorNameLink)).click();

        //Check that user was redirected to the right page
        String userBasicProfileName = ".UserBasicInfo__NameDiv-hmhybR";
        String expectedLink = "https://deens-master.now.sh/user/beabatravel";
        String currentLink = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(userBasicProfileName)));
        Assert.assertEquals(currentLink, expectedLink);

        driver.close();
    }


    //Verify the list of available creator trips
    @Test
    public void listOfCreatorTripsCheckTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://deens-master.now.sh/");


        //click right carousel button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class*='Carousel__ButtonRight']")));
        WebElement rightCreatorCarouselButton = driver.findElements(By.cssSelector("button[class*='Carousel__ButtonRight']")).get(1);
        rightCreatorCarouselButton.click();

        //Count the number of trips
        List<WebElement> listOfCreatorTrips = driver.findElements(By.xpath("//main/div[6]//*[@class='slick-slide' or @class='slick-slide slick-active slick-current']"));
        Assert.assertEquals(listOfCreatorTrips.size(), 5);

        driver.close();
    }


    //Verify that clicking on creators trip name redirects to the trip details page
    @Test
    public void creatorTripNameCheckTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://deens-master.now.sh/");

        //Open Romantic week-end in NYC
        String romanticNewYorkImageLink = "//main/div[6]//*[@class='slick-slide slick-active']//*[@title='Romantic week-end in NYC']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(romanticNewYorkImageLink)));
        driver.findElement(By.xpath(romanticNewYorkImageLink)).click();

        //Check that user was redirected to the right page
        String tripName = ".Header__Title-eurZFS";
        String expectedTripName = "Romantic week-end in NYC";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(tripName)));
        String currentTripName = driver.findElement(By.cssSelector(tripName)).getText();
        Assert.assertEquals(currentTripName, expectedTripName);

        driver.close();
    }
}
