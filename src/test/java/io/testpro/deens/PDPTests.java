package io.testpro.deens;
import io.testpro.deens.Pages.BasePage;
import io.testpro.deens.Pages.HomePage;
import io.testpro.deens.Pages.PDPVilladEstrées;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class PDPTests extends BaseTest{



    @BeforeMethod
    public void linkAndWait(){
        driver.get("https://deens-master.now.sh/");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }



    @Test(description = "Should open the map on full screen, but map is deleted from the web page")
    public void mapOnFullScreen1(){
        HomePage homePage = new HomePage(driver, wait);
        BasePage basePage = new BasePage(driver);

        homePage.scrollTill("(//h2[contains(@class,\"commonStyles\")])[3]");
        basePage.waitUntilClickable("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.clickToElement("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.waitUntilClickable("//*[@title='Paris with love, for a romantic 3 days in the capital of love']");
        basePage.clickToElement("//*[@title='Paris with love, for a romantic 3 days in the capital of love']");
        homePage.scrollTill("//div[contains(@class, \"Itinerary__Title\")]");
        basePage.waitUntilClickable("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]");
        basePage.clickToElement("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]");
        homePage.assertIsDisplayed("//div[contains(@class, \"Service__HeaderWrap\")]//h2");
    }




    @Test
    public void clearSearchField(){
        HomePage homePage = new HomePage(driver, wait);
        BasePage basePage = new BasePage(driver);
        PDPVilladEstrées pdpVilladEstrées = new PDPVilladEstrées(driver, wait);

        homePage.scrollTill("(//h2[contains(@class,\"commonStyles\")])[3]");
        basePage.waitUntilClickable("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.clickToElement("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.waitUntilClickable("//*[@title='Paris with love, for a romantic 3 days in the capital of love']");
        basePage.clickToElement("//*[@title='Paris with love, for a romantic 3 days in the capital of love']");
        homePage.scrollTill("//div[contains(@class, \"Itinerary__Title\")]");
        basePage.waitUntilClickable("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]");
        basePage.clickToElement("//div[contains(text(), \"Day 1\")]/..//a[contains(text(), \"Villa d'Estrées\")]");
        homePage.assertIsDisplayed("//div[contains(@class, \"Service__HeaderWrap\")]//h2");
        pdpVilladEstrées.searchBoxElement("//*[@name=\"search\"]");
        pdpVilladEstrées.clearTexField("//*[@name=\"search\"]");
    }



    @Test
    public void createTripBtn(){
        HomePage homePage = new HomePage(driver, wait);
        BasePage basePage = new BasePage(driver);
        PDPVilladEstrées pdpVilladEstrées = new PDPVilladEstrées(driver, wait);

        homePage.scrollTill("(//h2[contains(@class, \"commonStyles\")])[3]");
        basePage.waitUntilClickable("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.clickToElement("(//*[contains(@class, 'ButtonRight')])[1]");
        basePage.waitUntilClickable("//*[@title=\"NYC Must See 2\"]");
        basePage.clickToElement("//*[@title=\"NYC Must See 2\"]");
        homePage.scrollTill("//div[contains(text(),\"Day 2\")]");
        basePage.waitUntilClickable("//span[contains(text(),'Museums')]");
        basePage.clickToElement("//span[contains(text(),'Museums')]");
        basePage.waitUntilClickable("//div[contains(@class, \"Results__NotFound\")]//a");
        basePage.clickToElement("//div[contains(@class, \"Results__NotFound\")]//a");
    }

}
