package io.testpro.deens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.border.SoftBevelBorder;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyProfileTests {
    WebDriver driver;
    private String login = "mogreat1@gmail.com";
    private String password = "Password1";
    private static By biography = By.xpath("//*[@name='biography']");
    private static By save = By.xpath("//*[text()='Save']");

    @BeforeMethod
    public void setUpAndLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.xpath("//*[text()='Login']")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(login);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.xpath("//*[@class='ui dropdown']")).click();
        driver.findElement(By.xpath("//*[text()='Profile']")).click();
    }


    @Test(enabled = true, description = "PC-37 Verify ability to add Bio information in your Profile using Letters only")
    public void addBioInfoTest() {
        driver.findElement(By.xpath("//*[@class='pencil icon']")).click();
        WebElement biographyTestField = driver.findElement(biography);
        biographyTestField.clear();
        String bioText = "Test Info about User";
        biographyTestField.sendKeys(bioText);
        driver.findElement(save).click();

        SoftAssert softAssert = new SoftAssert();
        List<WebElement> textField = driver.findElements(biography);
        softAssert.assertTrue(textField.size() == 0);

        List<WebElement> saveButton = driver.findElements(save);
        softAssert.assertTrue(saveButton.size() == 0);
        softAssert.assertTrue(driver.findElement(By.xpath("//span[contains(@class,'Profile__Horizontal')]/p")).getText().contains(bioText));

        softAssert.assertAll();

    }

    @Test(enabled = true, description = "PC-103 User can open Review Page - Version 1")
    public void openReviewPageTest() {
        driver.findElement(By.xpath("//*[contains(@class,'UserBasicInfo__Name')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Reviews given to the user']")).isDisplayed());

    }

    @Test(enabled = true, description = "PC-106 Verify that user can see his/her Profile info - Version 1")
    public void profileInfoTest() {

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.xpath("//h2[text()='Profile']")).isDisplayed());
        softAssert.assertEquals(driver.getCurrentUrl(), "https://deens-master.now.sh/my/profile");
        softAssert.assertAll();

    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.quit();
    }

}
