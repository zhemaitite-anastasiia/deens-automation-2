package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Stream;


public class PDPPage extends BasePage {

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

    public void openTripPage() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
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
        //return driver.findElement(By.xpath("//h6[contains(text(),'Book')]")).isDisplayed();
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



