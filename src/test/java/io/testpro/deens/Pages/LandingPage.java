package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends BasePage {

    public LandingPage (WebDriver driver){
        super(driver);
    }

    public String romanticNewYorkImageLink = "//main/div[6]//*[@class='slick-slide slick-active']//*[@title='Romantic week-end in NYC']";

    public void openPage() {
        driver.get("https://deens-master.now.sh/");
    }

    //Featured Trip Creator From Our Community
    public void clickToTripCreatorName (){
        String tripCreatorNameLink = "[href*='beabatravel']";
        waitUntilClickable(By.cssSelector(tripCreatorNameLink)).click();
    }

    public void clickRightCreatorCarouselButton () {
        waitUntilClickable(By.cssSelector("button[class*='Carousel__ButtonRight']"));
        WebElement rightCreatorCarouselButton = driver.findElements(By.cssSelector("button[class*='Carousel__ButtonRight']")).get(1);
        rightCreatorCarouselButton.click();
    }

    public int getSizeOflistOfCreatorTrips () {
        List <WebElement> listOfCreatorTrips = driver.findElements(By.xpath("//main/div[6]//*[@class='slick-slide' or @class='slick-slide slick-active slick-current']"));
        return listOfCreatorTrips.size();
    }

    public void openTripFromCreatorList (String tripLocator) {
        waitUntilClickable(By.xpath(tripLocator)).click();
    }

    public String getTripNameFromTheTripPage (){
        String tripName = ".Header__Title-eurZFS";
        return waitUntilClickable(By.cssSelector(tripName)).getText();
    }



}
