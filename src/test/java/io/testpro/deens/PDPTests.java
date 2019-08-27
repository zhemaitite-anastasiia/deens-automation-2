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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PDPTests extends BaseTest {

    @BeforeMethod
        private PDPPage initSetUp(){
        PDPPage pdpPage= new PDPPage(driver);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        return pdpPage;
   }


    @Test
    public void mapOnFullScreen1() {
        driver.get("https://deens-master.now.sh/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//h2[contains(@class, \"commonStyles\")])[3]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@class, 'ButtonRight')])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Paris with love, for a romantic 3 days in the capital of love']"))).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class, \"Itinerary__Title\")]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]"))).click();
        String textHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, \"Service__HeaderWrap\")]//h2"))).getText();
        Assert.assertEquals(textHeader, "Villa d'Estrées");
    }


    @Test
    public void clearSearchField() {
        driver.get("https://deens-master.now.sh/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//h2[contains(@class, \"commonStyles\")])[3]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@class, 'ButtonRight')])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Paris with love, for a romantic 3 days in the capital of love']"))).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class, 'Itinerary__Title')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]"))).click();
        String textHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, \"Service__HeaderWrap\")]//h2"))).getText();
        Assert.assertEquals(textHeader, "Villa d'Estrées");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name=\"search\"]"))).clear();
    }


    @Test
    public void createTripBtn() {
        driver.get("https://deens-master.now.sh/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//h2[contains(@class, \"commonStyles\")])[3]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@class, 'ButtonRight')])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title=\"NYC Must See 2\"]"))).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div[contains(text(),\"Day 2\")]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Museums')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, \"Results__NotFound\")]//a"))).click();
    }

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
        Assert.assertTrue(pdpPage.isListOfLocationsContainsSanFrancisco("San Francisco"));

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




