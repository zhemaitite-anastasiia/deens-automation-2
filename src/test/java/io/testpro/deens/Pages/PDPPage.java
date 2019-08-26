package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class PDPPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'TripDescription__About')]")
    private WebElement scroll;
    @FindBy(xpath = ".//a[text()='Parc 55 San Francisco - a Hilton Hotel'][1]")
    private WebElement hotel;
    @FindBy(css = ".Results__ResultItem-kYrlTr")
    public List<WebElement> listOfHotels;

    public PDPPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void openTripPage() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
    }

    public void skrollUntilTripDescription() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
    }

    public void selectHotel() {
        waitUntilVisibility(By.xpath(".//a[text()='Parc 55 San Francisco - a Hilton Hotel'][1]")).click();
    }

    public void findMap() {
        waitUntilVisibility(By.xpath("//div[@id='_atssh']//iframe"));
    }

    public boolean mapPersistsOnPage() {
        return driver.findElement(By.xpath("//div[@id='_atssh']//iframe")).isDisplayed();
    }

    public void clickOnLocation() {
        waitUntilVisibility(By.xpath("//tr[contains(@class,'ServiceInformation__Row-kqYfXL')]//span[contains(text(),'San Francisco, United States of America')]")).click();
    }

    public void waitOnImage() {
        waitUntilVisibility(By.cssSelector(".ImgSlider__Wrap-iIVRqG.hdKFky"));
    }

    public void clickOnBookButton() {
        waitUntilVisibility(By.cssSelector("button[class='ui blue icon right labeled button']")).click();
    }

    public boolean bookButtonPersitsOnThePage() {
        return driver.findElement(By.xpath("//h6[contains(text(),'Book')]")).isDisplayed();
    }

    public boolean getListOfAllLocations(String expectedlocation) {
        List<WebElement> Locations = listOfHotels;
        //       List<String> LocationTexts = new ArrayList<>();
//
//        for (WebElement element : Locations) {
//            //LocationTexts.add(element.getText());
//        }
//        return LocationTexts;

        for (WebElement element : Locations) {
            String text = element.getText();
            if (!text.equals(expectedlocation)) {
                return false;
            }
        }
        return true;

    }
}



