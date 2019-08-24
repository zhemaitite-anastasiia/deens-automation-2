package io.testpro.deens;
import io.testpro.deens.Pages.PDPPage;
import org.testng.Assert;
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
        Assert.assertEquals(pdpPage.H2HeaderOn_Villa_dEstrees(), "Villa d'Estrées");
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
        Assert.assertEquals(pdpPage.H2HeaderOn_Villa_dEstrees(), "Villa d'Estrées");
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
        Assert.assertEquals(pdpPage.h2WhereDoYouWantToGo.getText(), "Where do you want to go?");
    }




    @Test(description = "counts the trips quantity under of Feature Trips and lists them down in console.")
    public  void listOfFeatureTrips(){
        PDPPage pdpPage = new PDPPage(driver);
        pdpPage.scrollTillfeatureTripsCaruselList();
        pdpPage.clickOnRightCaruselBtn();
        pdpPage.countFeatureTripsAndListThem();
        Assert.assertEquals(pdpPage.quantityOfTripsInList, 6);
    }




    @Test(description = "Does some search and unckeck the checkbox")
    public void disableCheckbox() {
        PDPPage pdpPage = new PDPPage(driver);
        pdpPage.doSearchAndDisableCheckboxUsingActionsClass();
        Assert.assertEquals(pdpPage.checkbox.isSelected(), false, "Checkbox was already unselected!");
    }


}

