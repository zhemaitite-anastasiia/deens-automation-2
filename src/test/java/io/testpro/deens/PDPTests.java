package io.testpro.deens;
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
    public void linkAndWait(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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


}
