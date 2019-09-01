package io.testpro.deens;
import io.testpro.deens.Pages.PDPPage;
import org.testng.Assert;
import org.testng.annotations.Test;




public class PDPTests extends BaseTest {


    private PDPPage initSetUp() {
        PDPPage pdpPage = new PDPPage(driver);
        return pdpPage;
    }


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
}

