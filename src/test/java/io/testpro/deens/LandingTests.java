package io.testpro.deens;

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
