package io.testpro.deens;
import io.testpro.deens.Pages.PDPPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class PDPTests extends BaseTest{

    @BeforeMethod
   private PDPPage initSetUp(){
        PDPPage pdpPage= new PDPPage(driver);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        return pdpPage;
    }


    @Test
    public void mapOnFullScreen1(){
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
    public void clearSearchField(){
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
    public void createTripBtn(){
        driver.get("https://deens-master.now.sh/");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//h2[contains(@class, \"commonStyles\")])[3]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@class, 'ButtonRight')])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title=\"NYC Must See 2\"]"))).click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div[contains(text(),\"Day 2\")]")));
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


}
