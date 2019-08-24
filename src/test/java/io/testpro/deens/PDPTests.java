package io.testpro.deens;
import org.openqa.selenium.By;
import io.testpro.deens.Pages.PDPPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.concurrent.TimeUnit;






public class PDPTests extends BaseTest {


    @BeforeMethod
    public void linkAndWait() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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







    @Test(description = "PC-45 : Verify that Google map is presented on the PDP page.")
    public void googleMapPDP() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'TripDescription__About-dgtwrt')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()='Parc 55 San Francisco - a Hilton Hotel'][1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='_atssh']//iframe")));
        //verivy google map
        assertTrue(driver.findElement(By.xpath("//div[@id='_atssh']//iframe")).isDisplayed());

    }

    @Test(description = "PC-77 : Verify that search by location is available.")
    public void searchByLocationsPDP() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'TripDescription__About')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Parc 55 San Francisco - a Hilton Hotel'][1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@class,'ServiceInformation__Row-kqYfXL')]//span[contains(text(),'San Francisco, United States of America')]"))).click();
        //Check all results...
        List<WebElement> elements = driver.findElements(By.cssSelector(".Results__ResultItem-kYrlTr"));
        assertTrue(elements.size() > 0, "There were no trips found");
        SoftAssert softAssert = new SoftAssert();
        for (WebElement element : elements) {
            String text = element.getText();
            //System.out.println(text);
            softAssert.assertTrue(text.contains("San Francisco"), "San Francisco not found in trip:" + text);
        }
        softAssert.assertAll();
    }

    @Test(description = "PC-51 : Verify that \"Book now\" button allows to book activity.")
    public void searchVerifyBookNowActivityPDP() {
        driver.get("https://deens-master.now.sh/book/trip/the-outer-san-francisco-from-silicon-valley-to-yosemite-in-san-francisco-and-vicinity_5cb865ceef96cec3b64004f6");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'TripDescription__About')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Parc 55 San Francisco - a Hilton Hotel'][1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ImgSlider__Wrap-iIVRqG.hdKFky")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='ui blue icon right labeled button']"))).click();
        assertTrue(driver.findElement(By.xpath("//h6[contains(text(),'Book')]")).isDisplayed());

    }

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




