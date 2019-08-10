package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyProfileTests extends BaseTest {

    private String login = "mogreat1@gmail.com";
    private String password = "Password1";
    private static By biography = By.xpath("//*[@name='biography']");
    private static By save = By.xpath("//*[text()='Save']");

    @BeforeMethod
    public void setUpAndLogin() {
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Login']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys(login);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='ui dropdown']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Profile']"))).click();

    }


    @Test(enabled = true, description = "PC-37 Verify ability to add Bio information in your Profile using Letters only")
    public void addBioInfoTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='pencil icon']"))).click();
        WebElement biographyTestField = wait.until(ExpectedConditions.visibilityOfElementLocated(biography));
        biographyTestField.clear();
        String bioText = "Test Info about User";
        biographyTestField.sendKeys(bioText);
        wait.until(ExpectedConditions.elementToBeClickable(save)).click();

        SoftAssert softAssert = new SoftAssert();
        List<WebElement> textField = driver.findElements(biography);
        softAssert.assertTrue(textField.size() == 0);

        List<WebElement> saveButton = driver.findElements(save);
        softAssert.assertTrue(saveButton.size() == 0);
        softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'Profile__Horizontal')]/p"))).getText().contains(bioText));

        softAssert.assertAll();

    }

    @Test(enabled = true, description = "PC-103 User can open Review Page - Version 1")
    public void openReviewPageTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'UserBasicInfo__Name')]"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Reviews given to the user']"))).isDisplayed());

    }

    @Test(enabled = true, description = "PC-106 Verify that user can see his/her Profile info - Version 1")
    public void profileInfoTest() {

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Profile']"))).isDisplayed());
        softAssert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/my/profile");
        softAssert.assertAll();

    }


}
