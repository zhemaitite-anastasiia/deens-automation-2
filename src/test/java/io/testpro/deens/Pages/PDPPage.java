package io.testpro.deens.Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.stream.Stream;


public class PDPPage extends BasePage{
    
    public String url = "https://deens-master.now.sh/";

    @FindBy(css = "[class*=\"SectionTrips__SectionHeader\"]>h2")
    private WebElement featuredTripsHeaderOnHomePage;

    @FindBy(xpath = "(//*[contains(@class, 'ButtonRight')])[1]")
    private  WebElement rightCaruselBtn;

    @FindBy(css = "[class*='slick-track'] div:nth-child(6) a")
    private  WebElement parisWithLoveTrip;

    @FindBy(css = "[class*= \"Itinerary__Title\"]")
    private WebElement itineraryTitleOnPLP;

    @FindBy(xpath = "(//*[contains(@class, \"Itinerary__Wrapper\")] // div[contains(@class, 'Itinerary__Day')][1]//h3[contains(@class, 'Itinerary__ServiceTitle')]//a[1])[1]")
    private WebElement villaEstréesHotel;

    @FindBy(css = "[class*=\"Service__HeaderWrap\"]>h2")
    public WebElement villaEstréesH2Header;

    @FindBy(css = "[name=\"search\"]")
    public WebElement searchBoxElement;

    @FindBy(css = "[title=\"NYC Must See 2\"]")
    private WebElement nycMustSeeTrip;

    @FindBy(xpath = "//div[contains(text(),\"Day 2\")]")
    private WebElement day2TitleOnPLP;

    @FindBy(xpath = "//span[contains(text(),'Museums')]")
    private  WebElement museumsBtnUnderActivityOnPLP;

    @FindBy(css = "[class*=\"Results__NotFound\"] div a")
    private WebElement createTripBtn;

    @FindBy(css = "[class*=\"Modal__ChildrenContent\"] div h2")
    public WebElement h2WhereDoYouWantToGo;

    @FindBy(xpath = "(//*[@class = 'slick-list'])[1]//*[contains(@class, 'CssOnlyTruncate__TruncateContainer')]")
    private List<WebElement> titleOfTripInFirstCaruselOnHomePage;

    @FindBy(css = "[class *= 'Results__MapOptions'] > div > input")
    public WebElement checkbox;
    
    @FindBy(xpath = "//div[contains(@class,'TripDescription__About')]")
    private WebElement scroll;
    
    @FindBy(css = ".Results__ResultItem-kYrlTr")
    private List<WebElement> listOfTrips;
    
    @FindBy(xpath = "//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//h3[1]")
    private WebElement hiltohHotel;
    
    @FindBy(xpath = "//div[@id='_atssh']//iframe")
    private WebElement map;
    
    @FindBy(xpath = "//h6[contains(text(),'Book')]")
    private WebElement bookTab;
    
    @FindBy(css = "button[class='ui blue icon right labeled button']")
    private WebElement bookButton;
    
    @FindBy(xpath = "//tr[contains(@class,'ServiceInformation__Row-kqYfXL')]//span[contains(text(),'San Francisco, United States of America')]")
    private WebElement locationLink;
    
    @FindBy(css = ".ImgSlider__Wrap-iIVRqG.hdKFky")
    private WebElement imageThatShouldBeLoadedToClickOnBookButton;
    
    @FindBy(css="div.Itinerary__Day-dgFDHM")
    private List<WebElement> travelList;


    public PDPPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    @DataProvider(name = "dataForSearchFiled")
    public static Object[][] searchData(){
        return  new Object[][] {{"testNG", "645"}, {"Picture", "312"}, {"Cucumber", "123"}};
    }

    public void openPage(){
        driver.get(url);
    }
    
    public void openTripPage() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
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

    public String getTextOf_H2Header_OnVilla_dEstrees(){
        String a = wait.until(ExpectedConditions.visibilityOf(villaEstréesH2Header)).getText();
        return a;
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

    public int countFeatureTripsAndListThem(){
        List<WebElement> listofFeaturesTrips = titleOfTripInFirstCaruselOnHomePage;
        int quantityOfTripsInList = listofFeaturesTrips.size();
        return quantityOfTripsInList;
    }

    public void doSearchAndDisableCheckboxUsingActionsClass() {
        String mainWindow = driver.getWindowHandle();
        driver.switchTo().window(mainWindow);
        Actions action = new Actions(driver);
        action.moveToElement(searchBoxElement).click()
                .sendKeys("test").keyDown(Keys.LEFT_SHIFT).sendKeys("ng")
                .keyUp(Keys.LEFT_SHIFT).sendKeys(Keys.ENTER).build().perform();
        if (checkbox.isSelected()){
            action.moveToElement(checkbox).click().build().perform();
            System.out.println("Checkbox is unselected now!");
        }
    }

    public void doSearchUsingDataProvider(String keyWord1, String keyWord2) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxElement)).clear();
        searchBoxElement.sendKeys(keyWord1, keyWord2);
        searchBoxElement.sendKeys(Keys.ENTER);
    }
    
    public void scrollUntilTripDescription() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
    }

    public void selectHotel() {
        this.hiltohHotel.click();
    }

    public void findMap() {
        waitUntilVisibility(map);
    }

    public boolean isMapPersistsOnPage() {
        return map.isDisplayed();
    }

    public void clickOnLocation() {
        waitUntilVisibility(locationLink).click();
    }

    public void waitOnImage() {
        waitUntilVisibility(imageThatShouldBeLoadedToClickOnBookButton);
    }

    public void clickOnBookButton() {
        waitUntilVisibility(bookButton).click();
    }

    public boolean isBookButtonPersitsOnThePage() {
        return bookButton.isDisplayed();
    }

    public boolean isListOfLocationsContainsSanFrancisco(String expectedlocation, SoftAssert softAssert) {
        boolean allLocationsContainExpected = true;
        List<WebElement> locations = listOfTrips;
        for (WebElement element : locations) {
            String text = element.getText();
            if (!text.equals(expectedlocation)) {
                softAssert.assertTrue(text.contains("San Francisco"), "San Francisco not found in trip:" + text);
                allLocationsContainExpected = false;
            }
        }
        return allLocationsContainExpected;

    }

    public int verificationOfCountDaysInTheTrip(){
        List<WebElement> daysInTheTrip= travelList;
        for (int i=0;i<daysInTheTrip.size(); i++){
        }return daysInTheTrip.size();
    }
}

