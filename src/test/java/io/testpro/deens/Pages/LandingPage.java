package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;


public class LandingPage extends BasePage {
    public String url = "https://deens-master.now.sh/";
    public String cssLogo = "div[class^='Logo']";
    public String cssHeader = "div[class^='TopBar']";
    public String cssNavBar = "nav[class^='DesktopNav__Nav']";
    public String cssNavBarLinks = "a[class^='DesktopNav__NavLink']";
    public String cssLoginSignUpMenu = "div[class^='DesktopDropDownMenu'] > div > a";
    public String cssTopDestinations = "div[class*='SectionTopDestinations__Section']";
    public String cssTopDestinationsLinks = cssTopDestinations + " h1";
    public String[] topDestinations = {"Sydney", "York", "Francisco", "London"};

    public String romanticNewYorkImageLink =
                "//main/div[6]//*[@class='slick-slide slick-active']//*[@title='Romantic week-end in NYC']";

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        try {
            driver.get(url);
        } catch (Exception ex) {
            System.out.println("Page was not loaded in 30 seconds." + ex);
        }
    }

    public boolean isPresent(By locator) {
        boolean isPresent = true;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException ex) {
            isPresent = false;
        }
        return isPresent;
    }

    public boolean isVisible(By locator) {
        boolean isVisible = true;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException ex) {
            isVisible = false;
        }
        return isVisible;
    }

    public boolean waitUntilNotVisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public List<WebElement> getNavBarElements() {
        if(isVisible(By.cssSelector(cssNavBar))) {
            return driver.findElements(By.cssSelector(cssNavBarLinks));
        }
        else {
            return null;
        }
    }

    public int countNavBarLinks() {
        return getNavBarElements().size();
    }

    public List<WebElement> getLoginSignUpLinks() {
        if(isVisible(By.cssSelector(cssHeader))) {
            return driver.findElements(By.cssSelector(cssLoginSignUpMenu));
        }
        else {
            return null;
        }
    }

    public List<WebElement> getTopDestinations() {
        if(isVisible(By.cssSelector(cssTopDestinations))) {
            return driver.findElements(By.cssSelector(cssTopDestinationsLinks));
        }
        else {
            return null;
        }
    }

    public int countTopDestinationsLinks() {
        return getTopDestinations().size();
    }


    //Featured Trip Creator From Our Community
    public void clickToTripCreatorName() {
        String tripCreatorNameLink = "[href*='beabatravel']";
        waitUntilClickable(By.cssSelector(tripCreatorNameLink)).click();
    }

    public void clickRightCreatorCarouselButton() {
        waitUntilClickable(By.cssSelector("button[class*='Carousel__ButtonRight']"));
        WebElement rightCreatorCarouselButton =
                driver.findElements(By.cssSelector("button[class*='Carousel__ButtonRight']")).get(1);
        rightCreatorCarouselButton.click();
    }

    public int getSizeOflistOfCreatorTrips() {
        List<WebElement> listOfCreatorTrips =
                driver.findElements(
                    By.xpath("//main/div[6]//*[@class='slick-slide' or @class='slick-slide slick-active slick-current']")
                );
        return listOfCreatorTrips.size();
    }

    public void openTripFromCreatorList(String tripLocator) {
        waitUntilClickable(By.xpath(tripLocator)).click();
    }

    public String getTripNameFromTheTripPage() {
        String tripName = ".Header__Title-eurZFS";
        return waitUntilClickable(By.cssSelector(tripName)).getText();
    }
}
