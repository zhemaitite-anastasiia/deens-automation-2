package io.testpro.deens.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;



public class PDPPage extends BasePage{

    @FindBy(xpath = "(//h2[contains(@class,\"commonStyles\")])[3]")
    private WebElement featuredTripsHeaderOnHomePage;

    @FindBy(xpath = "(//*[contains(@class, 'ButtonRight')])[1]")
    private  WebElement rightCaruselBtn;

    @FindBy(xpath = "//*[@title='Paris with love, for a romantic 3 days in the capital of love']")
    private  WebElement parisWithLoveTrip;

    @FindBy(xpath = "//div[contains(@class, \"Itinerary__Title\")]")
    private WebElement itineraryTitleOnPLP;

    @FindBy(xpath = "//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]")
    private WebElement villaEstréesHotel;

    @FindBy(xpath = "//div[contains(@class, \"Service__HeaderWrap\")]//h2")
    private WebElement villaEstréesH2Header;

    @FindBy(xpath = "//*[@name=\"search\"]")
    private WebElement searchBoxElement;

    @FindBy(xpath = "//*[@title=\"NYC Must See 2\"]")
    private WebElement nycMustSeeTrip;

    @FindBy(xpath = "//div[contains(text(),\"Day 2\")]")
    private WebElement day2TitleOnPLP;

    @FindBy(xpath = "//span[contains(text(),'Museums')]")
    private  WebElement museumsBtnUnderActivityOnPLP;

    @FindBy(xpath = "//div[contains(@class, \"Results__NotFound\")]//a")
    private WebElement createTripBtn;

    @FindBy(xpath = "//h2[text() = \"Where do you want to go?\"]")
    private WebElement h2WhereDoYouWantToGo;

    @FindBy(xpath = "(//*[@class = 'slick-list'])[1]//*[contains(@class, 'CssOnlyTruncate__TruncateContainer')]")
    private List<WebElement> titleOfTripInFirstCaruselOnHomePage;






    public PDPPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }





    public void clearSearchBox(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxElement)).clear();
    }


    public void scrollTillfeatureTripsCaruselList(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", featuredTripsHeaderOnHomePage);
    }


    public void clickOnRightCaruselBtn(){
       wait.until(ExpectedConditions.elementToBeClickable(rightCaruselBtn)).click();
    }


    public void chooseParisWithLoveTrip(){
        wait.until(ExpectedConditions.elementToBeClickable(parisWithLoveTrip)).click();
    }


    public void scrollTillItineraryTitleOnPLP(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itineraryTitleOnPLP);
    }


    public void chooseVillaEstréesHotel(){
        wait.until(ExpectedConditions.elementToBeClickable(villaEstréesHotel)).click();
    }


    public boolean assertH2HeaderIsDisplayed(){
        return villaEstréesH2Header.isDisplayed();
    }


    public void chooseNYC_MustSeeTrip(){
        wait.until(ExpectedConditions.elementToBeClickable(nycMustSeeTrip)).click();
    }


    public void scrollTillDay2TitleOnPLP(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", day2TitleOnPLP);
    }


    public void clickOnMuseumsBtnUnderActivityOnPLP(){
        wait.until(ExpectedConditions.elementToBeClickable(museumsBtnUnderActivityOnPLP)).click();
    }


    public void clickOnCreateTripBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(createTripBtn)).click();
    }


    public void assertH2onCreateTripPage(){
        Assert.assertEquals(h2WhereDoYouWantToGo.getText(), "Where do you want to go?");
    }


    public void countFeatureTripsAndListIt(){
        List<WebElement> listofFeaturesTrips = titleOfTripInFirstCaruselOnHomePage;
        System.out.println("Total number of Featured trips: " + listofFeaturesTrips.size());

        for (int i = 0; i<listofFeaturesTrips.size(); i++){
            WebElement titles = listofFeaturesTrips.get(i);
            System.out.println("- " + titles.getText());
        }
        Assert.assertEquals(listofFeaturesTrips.size(), 6);
    }



}

