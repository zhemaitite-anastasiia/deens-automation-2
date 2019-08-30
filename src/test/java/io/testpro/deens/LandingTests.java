package io.testpro.deens;

import io.testpro.deens.Pages.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LandingTests extends BaseTest {

    // Regular view tests
    private LandingPage testSetUp() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openPage(landingPage.url);
        driver.manage().window().maximize();
        return landingPage;
    }


    @Test //Vladimir
    public void checkLogoLink() {
        //initializing
        LandingPage landingPage = testSetUp();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        WebElement logo =myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div#root a > img")))); driver.findElement(By.cssSelector("div#root a > img"));
        WebElement earnMoneyLink = myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[href='/earn-money']"))));

        //actions
        logo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
        earnMoneyLink.click();
        logo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/");
    }

    @Test //Vladimir
    public void checkNavigationBarContent() {
        //initializing
        LandingPage landingPage = testSetUp();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        String[] expectedNavigationBarElements = {"Earn Money","•","Create Trip","Login","Sign up"};
        WebElement navigationBar =myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='DesktopNav__Wrap-bgeqrS dHbCgo']"))));
        String[] actualNavigationBarElements = navigationBar.getText().split("\\r?\\n");

        //actions
        for (int i=0; i<actualNavigationBarElements.length; i++) {
            Assert.assertEquals(actualNavigationBarElements[i], expectedNavigationBarElements[i]);
        }
    }


    @Test //Vladimir
    public void checkWorldPictureClickability() {
        //initializing
        LandingPage landingPage = testSetUp();
        WebDriverWait myWaitVar = new WebDriverWait(driver,10);
        WebElement worldPicture = myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@class,'lazyloaded')]")));
        //Wy the following way is not working???
        // WebElement worldPicture =myWaitVar.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[contains(@class,'lazyloaded')]"))));

        //actions
        Assert.assertTrue(worldPicture.isDisplayed());
    }


    /*
    /  Header tests
    */

    @Test
    private void titleIsCorrect() {
        LandingPage landingPage = testSetUp();
        landingPage.isPresent(By.tagName("title"));
        assertEquals(driver.getTitle(), "Deens, plan my trip!", "Landing page titile doesn't match.");
    }

    // Verify header is loaded
    @Test
    private void headerIsLoaded() {
        LandingPage landingPage = testSetUp();
        assertTrue(landingPage.isVisible(By.cssSelector(landingPage.cssHeader)));
    }

    // Verify logo is visible
    @Test
    private void logoIsVisible() {
        LandingPage landingPage = testSetUp();
        assertTrue(landingPage.isVisible(By.cssSelector(landingPage.cssLogo)));
    }

    // Regular view: verify the top menu navigation bar is visible
    @Test
    private void navBarIsVisible() {
        LandingPage landingPage = testSetUp();
        assertTrue(landingPage.isVisible(By.cssSelector(landingPage.cssNavBar)));
    }

    // Regular view: verify the top menu navigation has all links
    @Test
    private void navBarHasAllLinks() {
        LandingPage landingPage = testSetUp();
        assertEquals(landingPage.countNavBarLinks(), 2);
    }

    // Mobile view: verify the top menu navigation bar is NOT loaded
    @Test
    private void mobile_NavBarIsNotVisible() {
        LandingPage landingPage = testSetUp();
        // Setting the screen size to Iphone X parameters
        driver.manage().window().setSize(new Dimension(375, 812));
        assertTrue(landingPage.waitUntilNotVisible(By.cssSelector(landingPage.cssNavBar)));
    }

    // Using Actions to Validate Login and Sign Up links by checking that the form was loaded
    @Test
    private void validateLoginAndSignUpLinks() {
        LandingPage landingPage = testSetUp();
        List<WebElement> links = landingPage.getLoginSignUpLinks();
        Actions clicker = new Actions(driver);
        boolean allLinksAreGood = true;
        for(int i = 0; i < 2; i++) {
            links = landingPage.getLoginSignUpLinks();
            System.out.println(links.get(i));
            clicker.moveToElement(links.get(i)).click().perform();
            if(!landingPage.isVisible(By.tagName("form"))) {
                allLinksAreGood = false;
            }
            // Switch to the Landing Page
            clicker.moveToElement(driver.findElement(By.cssSelector("a[class*='Logo']"))).click().perform();
        }
        assertTrue(allLinksAreGood);
    }



    /*
    //  Top Destination tests
    */

    // Regular view: verify the top menu navigation bar is visible
    @Test
    private void topDestinationsSectionIsVisible() {
        LandingPage landingPage = testSetUp();
        assertTrue(landingPage.isVisible(By.cssSelector(landingPage.cssTopDestinations)));
    }

    // Regular view: verify the top menu navigation has all links
    @Test
    private void topDestinationsSectionHasAllLinks() {
        LandingPage landingPage = testSetUp();
        assertEquals(landingPage.countTopDestinationsLinks(), 4);
    }


    // Using JavaExecutor
    @Test
    private void validateTopDestinationsLinks() throws InterruptedException {
        LandingPage landingPage = testSetUp();
        // Boolean flag - true, if all links are good and have appropriate titles, false - otherwise
        boolean allLinksAreGood = true;
        List<WebElement> topDestinationLinks = landingPage.getTopDestinations();
        int numTopDestinationsLinks = landingPage.countTopDestinationsLinks();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        /*
        /  Inside the FOR loop:
        /  1. Clicking on top destination link
        /  2. Validating the link by checking url of the loaded page
        /  3. Switching back to the landing page
        */

        for(int i = 0; i < numTopDestinationsLinks; i++) {
            // Avoiding StaleElementReferenceException
            topDestinationLinks = landingPage.getTopDestinations();
            // Clicking on the link
            js.executeScript("arguments[0].click();", topDestinationLinks.get(i));
            // Get url of a current page
            String currentUrl = driver.getCurrentUrl();
            // Checking that url contains the target destination
            if(!currentUrl.contains(landingPage.topDestinations[i])) {
                allLinksAreGood = false;
            }
            // Switch to the Landing Page
            landingPage.openPage(landingPage.url);
        }
        assertTrue(allLinksAreGood);
    }


    /*
    /  Trip creator tests
    */

    // Verify trip creator name link
    @Test
    public void tripCreatorNameCheckTest() {
        LandingPage landingPage = testSetUp();

        //Click on the trip creator name and check that user was redirected to the right page
        landingPage.clickToTripCreatorName();
        landingPage.waitUntilClickable(By.cssSelector(".UserBasicInfo__NameDiv-hmhybR"));

        String currentLink = driver.getCurrentUrl();
        assertEquals(currentLink, "https://deens-master.now.sh/user/beabatravel");
    }

    // Verify the list of available creator trips
    @Test
    public void listOfCreatorTripsCheckTest() {
        LandingPage landingPage = testSetUp();
        landingPage.clickRightCreatorCarouselButton();

        //Check the number of trips
        assertEquals(landingPage.getSizeOflistOfCreatorTrips(), 5);
    }

    // Verify that clicking on creators trip name redirects to the trip details page
    @Test
    public void creatorTripNameCheckTest() {
        LandingPage landingPage = testSetUp();

        //Open "Romantic week-end in NYC" and Check that user was redirected to the right page
        landingPage.openTripFromCreatorList(landingPage.romanticNewYorkImageLink);
        String currentTripName = landingPage.getTripNameFromTheTripPage();
        assertEquals(currentTripName, "Romantic week-end in NYC");
    }

    // Alex
    @Test
    public void Footer_Partners() {
        LandingPage landingPage = testSetUp();
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(2) > .commonStyles__P-cbpCjc:nth-child(3) > a")).click();

        String ourPartners = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(ourPartners, "Our Partners");
    }

    @Test
    public void Footer_Privacy_Policy() {
        LandingPage landingPage = testSetUp();
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(3) > .commonStyles__P-cbpCjc:nth-child(4) > a")).click();

        String privacyPolicy = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(privacyPolicy, "Privacy Policy");

    }

    @Test
    public void Footer_Terms() {
        LandingPage landingPage = testSetUp();
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(3) > .commonStyles__P-cbpCjc:nth-child(3) > a")).click();
        String terms = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(terms, "Terms and Conditions");
    }
}