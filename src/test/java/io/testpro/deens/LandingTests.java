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
import java.util.List;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.NoSuchElementException;


public class LandingTests {
    WebDriver driver;

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    private void testTearDown() {
        driver.quit();
    }


    @Test //Vladimir
    public void checkLogoLink(){
        //initializing
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
        driver.manage().window().maximize();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //action
        WebElement worldPicture = driver.findElement(By.xpath("//img[contains(@class,'lazyloaded')]"));
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
    }

    // Verify the list of available creator trips
    @Test
    public void listOfCreatorTripsCheckTest() {
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
    }

    // Verify that clicking on creators trip name redirects to the trip details page
    @Test
    public void creatorTripNameCheckTest() {
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
    }
}

