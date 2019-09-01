package io.testpro.deens;
import io.testpro.deens.Pages.PDPPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;




public class PDPTests extends BaseTest {


    @BeforeMethod
    private PDPPage initSetUp() {
        PDPPage pdpPage = new PDPPage(driver);
        return pdpPage;
    }

//Ivan's tests
    @Test(description = "Should open the map on full screen, but map is deleted from the web page")
    public void getOnPDPTripPage() {
        PDPPage pdpPage = initSetUp();
        pdpPage.openPage(pdpPage.url);
        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseParisWithLoveTrip();
        pdpPage.scrollTillItineraryTitleOnPLP();
        pdpPage.chooseVillaEstréesHotel();
        Assert.assertEquals(pdpPage.getTextOf_H2Header_OnVilla_dEstrees(), "Villa d'Estrées");
    }


    @Test(description = "should clear SearchBox on PDP.")
    public void clearSearchField() {
        PDPPage pdpPage = initSetUp();
        pdpPage.openPage(pdpPage.url);
        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseParisWithLoveTrip();
        pdpPage.scrollTillItineraryTitleOnPLP();
        pdpPage.chooseVillaEstréesHotel();
        pdpPage.clearSearchBox();
        Assert.assertEquals(pdpPage.searchBoxElement.getAttribute("value"), "");
    }


    @Test(description = "verifies if \"Create Trip\" button works.")
    public void createTripBtn() {
        PDPPage pdpPage = initSetUp();
        pdpPage.openPage(pdpPage.url);
        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseNYC_MustSeeTrip();
        pdpPage.scrollTillDay2TitleOnPLP();
        pdpPage.clickOnMuseumsBtnUnderActivityOnPLP();
        pdpPage.clickOnCreateTripBtn();
        Assert.assertEquals(pdpPage.h2WhereDoYouWantToGo.getText(), "Where do you want to go?");
    }


    @Test(description = "counts the trips quantity under of Feature Trips and lists them down in console.")
    public void listOfFeatureTrips() {
        PDPPage pdpPage = initSetUp();
        pdpPage.openPage(pdpPage.url);
        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.countFeatureTripsAndListThem();
        Assert.assertEquals(pdpPage.countFeatureTripsAndListThem(), 6);
    }


    @Test(description = "Does some search and unckeck the checkbox")
    public void disableCheckbox() {
        PDPPage pdpPage = initSetUp();
        pdpPage.openPage(pdpPage.url);
        pdpPage.doSearchAndDisableCheckboxUsingActionsClass();
        Assert.assertEquals(pdpPage.checkbox.isSelected(), false, "Checkbox was already unselected!");
    }
    
    

//Tanya's tests
    @Test(description = "PC-45 : Verify that Google map is presented on the PDP page.")
    public void googleMapPDP() {
        PDPPage pdpPage= initSetUp();
        pdpPage.openTripPage();
        pdpPage.scrollUntilTripDescription();
        pdpPage.selectHotel();
        pdpPage.findMap();
        Assert.assertTrue(pdpPage.isMapPersistsOnPage());

    }

    @Test(description = "PC-77 : Verify that search by location is available.")
    public void searchByLocationsPDP() {
        PDPPage pdpPage= initSetUp();
        pdpPage.openTripPage();
        pdpPage.scrollUntilTripDescription();
        pdpPage.selectHotel();
        pdpPage.clickOnLocation();
        SoftAssert softAssert = new SoftAssert();
        pdpPage.isListOfLocationsContainsSanFrancisco("San Francisco",softAssert);
        softAssert.assertAll();
    }


    @Test(description = "PC-51 : Verify that \"Book now\" button allows to book activity.")
    public void searchVerifyBookNowActivityPDP() {
        PDPPage pdpPage= initSetUp();
        pdpPage.openTripPage();
        pdpPage.scrollUntilTripDescription();
        pdpPage.selectHotel();
        pdpPage.waitOnImage();
        pdpPage.clickOnBookButton();
        Assert.assertTrue(pdpPage.isBookButtonPersitsOnThePage());

    }

    @Test(description = "Verify that trip for 5 days contains itinerary for 5 days")
    public void verificationOfCountTripsDays(){
        PDPPage pdpPage= initSetUp();
        pdpPage.openTripPage();
        Assert.assertEquals(pdpPage.verificationOfCountDaysInTheTrip(), 5);
    }
    
    
    
    //Michail's tests

    @Test(description = "PC-46 : Verify that Title of chosen Activity on PLP and PDP match.")
    public void titlePDPvsPLPTest() {
        driver.get("https://deens-master.now.sh/book/trip/sydney-down-under-in-sydney_5cb8666cef96ce77ca4004f8");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='Trip__Body-iAJjGV efEoxF']")));
        String pLpPage = driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//h3[1]//a[1]")).getText();
        WebElement element = driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//h3[1]//a[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        String pDpPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.Service__HeaderWrap-hXkKZA.cENFIW > h2:nth-child(1)"))).getText();
        Assert.assertEquals(pDpPage, pLpPage);

    }

    @Test (description = "PC-47 : Verify that the Price of chosen activity on PLP and PDP match.")
    public void pricePDPvsPLPTest(){
        driver.get("https://deens-master.now.sh/book/trip/sydney-down-under-in-sydney_5cb8666cef96ce77ca4004f8");
        driver.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='Itinerary__Title-bOSMRV iHccEh']")));
        String pLpLocation = driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//div[3]//p[2]")).getText();
        pLpLocation = pLpLocation.substring(1);
        Float f1 = Float.parseFloat(pLpLocation);
        driver.get("https://deens-master.now.sh/book/accommodation/pullman-quay-grand-sydney-harbour-in-sydney_5bbf7c3662e205f6f00c25c6");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//td[contains(text(),'Host')]")));
        String pDpLocation = driver.findElement(By.xpath("//span[@class='PriceTag__Price-dJFBBM eeIPSW']")).getText();
        pDpLocation = pDpLocation.substring(1);
        Float f2 = Float.parseFloat(pDpLocation);
        Assert.assertEquals(f1, f2);

    }


    @Test (description = "PC-48 : Verify that \"Location \" of chosen activity on PLP and PDP match.")
    public void locationPDPvsPLPTest(){
        driver.get("https://deens-master.now.sh/book/trip/sydney-down-under-in-sydney_5cb8666cef96ce77ca4004f8");
        driver.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='Itinerary__Title-bOSMRV iHccEh']")));
        String pLpLocation = driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//div[3]//p[1]")).getText();
        driver.get("https://deens-master.now.sh/book/accommodation/pullman-quay-grand-sydney-harbour-in-sydney_5bbf7c3662e205f6f00c25c6");
        String pDpLocation = driver.findElement(By.xpath("//tr[@class='ServiceInformation__Row-kqYfXL bEGdSk']//span[contains(text(),'Sydney, Australia')]")).getText();
        Assert.assertEquals(pLpLocation, pDpLocation);

    }
}

