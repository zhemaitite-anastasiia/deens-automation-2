package io.testpro.deens;

import io.testpro.deens.Pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class SignUpTests extends BaseTest{



    @Test
    public void signUpSuccess() {

        SignUpPage signUp = new SignUpPage(driver);

        signUp.openPage();

//        signUp.enterUserName(generatedUsername());
//        signUp.enterEmail(generatedEmail());
//        signUp.enterPassword("qwertyqwerty");
//        signUp.submit();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*=\"AvatarWrapper\"]")));
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertEquals(currentUrl, "https://deens-master.now.sh/");

    }

    @Test
    public void tanyasTestCase() {

        driver.get("https://deens-master.now.sh/book/trip/nyc-must-see-2-in-new-york-and-vicinity_5cb732c6e5f15f03c87e3639");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='TripDescription__About-dgtwrt gWDkqv']")));
        driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//h3[1]//a[1]")).click();

//        WebDriverWait wait = new WebDriverWait(driver,60);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ui blue icon right labeled button']"))).click();
//        driver.findElement(By.xpath("//button[@class='ui blue icon right labeled button']")).click();
//        Assert.assertTrue(driver.findElement(By.cssSelector("div.Page-jLerck.dTjQQs div.CheckoutContainer__Wrapper-fzObJW.hCoRWS div.CheckoutContainer__Top-jkYYxN.frSlua div.CheckoutContainer__Steps-fqtMJZ.hksCPU > div.CheckoutContainer__Step-jTOHIe.iauDbA:nth-child(1)")).isDisplayed());

    }

    public long getTimestamp() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public String generatedEmail() {
        long timestamp = getTimestamp();
        String email = timestamp + "+qa@testpro.io";
        return email;

    }

    public String generatedUsername() {
        long timestamp = getTimestamp();
        String userName = timestamp + "";
        return userName;

    }


}

