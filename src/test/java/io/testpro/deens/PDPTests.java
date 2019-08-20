package io.testpro.deens;
import io.testpro.deens.Pages.PDPPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;






public class PDPTests extends BaseTest{

    @BeforeMethod
    public void linkAndWait(){
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }





    @Test(description = "Should open the map on full screen, but map is deleted from the web page")
    public void mapOnFullScreen1(){
        PDPPage pdpPage = new PDPPage(driver);

        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseParisWithLoveTrip();
        pdpPage.scrollTillItineraryTitleOnPLP();
        pdpPage.chooseVillaEstréesHotel();
        pdpPage.assertH2HeaderIsDisplayed();
    }




    @Test(description = "should clear SearchBox on PDP.")
    public void clearSearchField(){
        PDPPage pdpPage = new PDPPage(driver);

        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseParisWithLoveTrip();
        pdpPage.scrollTillItineraryTitleOnPLP();
        pdpPage.chooseVillaEstréesHotel();
        pdpPage.clearSearchBox();
        pdpPage.assertH2HeaderIsDisplayed();
    }




    @Test(description = "verifies if \"Create Trip\" button works.")
    public void createTripBtn(){
        PDPPage pdpPage = new PDPPage(driver);

        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.chooseNYC_MustSeeTrip();
        pdpPage.scrollTillDay2TitleOnPLP();
        pdpPage.clickOnMuseumsBtnUnderActivityOnPLP();
        pdpPage.clickOnCreateTripBtn();
        pdpPage.assertH2onCreateTripPage();
    }




    @Test(description = "counts the trips quantity under of Feature Trips and lists them down in console.")
    public  void listOfFeatureTrips(){
        PDPPage pdpPage = new PDPPage(driver);

        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.countFeatureTripsAndListIt();
    }


}

