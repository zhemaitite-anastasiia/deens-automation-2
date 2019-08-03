package io.testpro.deens;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class LandingTests {

    // Verify title is correct
    @Test
    public void titleIsCorrect() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement waitTitle = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        assertEquals(driver.getTitle(), "Deens, plan my trip!", "Landing page titile doesn't match.");
        driver.quit();
    }

    // Verify header is loaded
    @Test
    private void headerIsLoaded() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        assertEquals(driver.findElement(By.cssSelector("header[class^=\"TopBar\"]")).isDisplayed(),
                true, "Landing page header is not displayed.");
        driver.quit();
    }

    // Verify logo is visible
    @Test
    private void logoIsVisible() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        assertEquals(driver.findElement(By.cssSelector("div[class^=\"Logo\"")).isDisplayed(),
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
        assertTrue(driver.findElement(By.cssSelector("div[class^=\"DesktopNav\"")).isDisplayed());
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
        // navbarIsFound=true, if the menu is visible, navbarIsFound=false - otherwise
        boolean navbarIsFound = true;
        try {
            driver.findElement(By.cssSelector("div[class^='DesktopNav']"));
        } catch (NoSuchElementException ex) {
            navbarIsFound = false;
            System.out.println("WebDriver couldn't locate the element.");
        } catch (WebDriverException ex) {
            navbarIsFound = false;
        }
        finally {
            assertFalse(navbarIsFound);
        }
        driver.quit();
    }


    //Featured trip creator
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
        assertEquals(currentLink, expectedLink);

        driver.close();
    }

    // Verify the list of available creator trips
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
        assertEquals(listOfCreatorTrips.size(), 5);

        driver.close();
    }

    // Verify that clicking on creators trip name redirects to the trip details page
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
        assertEquals(currentTripName, expectedTripName);

        driver.close();
    }
}

