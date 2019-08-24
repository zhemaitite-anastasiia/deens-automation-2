package io.testpro.deens.Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PDPPage extends BasePage{

    @FindBy(css = "[class*=\"SectionTrips__SectionHeader\"]>h2")
    private WebElement featuredTripsHeaderOnHomePage;

    @FindBy(xpath = "(//*[contains(@class, 'ButtonRight')])[1]")
    private  WebElement rightCaruselBtn;

    @FindBy(css = "[title = 'Paris with love, for a romantic 3 days in the capital of love']")
    private  WebElement parisWithLoveTrip;

    @FindBy(css = "[class*= \"Itinerary__Title\"]")
    private WebElement itineraryTitleOnPLP;

    @FindBy(xpath = "//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]")
    private WebElement villaEstréesHotel;

    @FindBy(css = "[class*=\"Service__HeaderWrap\"]>h2")
    public WebElement villaEstréesH2Header;

    @FindBy(css = "[name=\"search\"]")
    private WebElement searchBoxElement;

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

    public int quantityOfTripsInList;






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


    public String H2HeaderOn_Villa_dEstrees(){
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


    public void countFeatureTripsAndListThem(){
        List<WebElement> listofFeaturesTrips = titleOfTripInFirstCaruselOnHomePage;
        System.out.println("Total number of Featured trips: " + listofFeaturesTrips.size());

        for (int i = 0; i<listofFeaturesTrips.size(); i++) {
            WebElement titles = listofFeaturesTrips.get(i);
            System.out.println("- " + titles.getText());
        }
        quantityOfTripsInList = listofFeaturesTrips.size();
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

}

