package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class SignUpTests {

    @Test
    public void signUpSuccess() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.get("https://deens-master.now.sh/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "#username"))).sendKeys(generatedUsername());


//        driver.findElement(By.cssSelector("#email")).sendKeys(generatedEmail());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "#email"))).sendKeys(generatedEmail());

//        driver.findElement(By.cssSelector("#password")).sendKeys("qwertyqwerty");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "#password"))).sendKeys("qwertyqwerty");
//        driver.findElement(By.cssSelector(".green-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( ".green-btn"))).click();




//        WebElement avatar = driver.findElement(By.cssSelector("[class*=\"AvatarWrapper\"]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*=\"AvatarWrapper\"]")));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://deens-master.now.sh/");
        driver.get("https://deens-master.now.sh/my/trips");
        driver.findElement(By.xpath("//*[contains(@class,'left labeled button')]")).click();



//        driver.quit();
    }

    @Test
    public void tanyasTestCase() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://deens-master.now.sh/book/trip/nyc-must-see-2-in-new-york-and-vicinity_5cb732c6e5f15f03c87e3639");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='TripDescription__About-dgtwrt gWDkqv']")));
        driver.findElement(By.xpath("//div[@class='Itinerary__Wrapper-KIerx dDuomi']//div[2]//div[2]//div[2]//h3[1]//a[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver,60);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ui blue icon right labeled button']"))).click();
        driver.findElement(By.xpath("//button[@class='ui blue icon right labeled button']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.Page-jLerck.dTjQQs div.CheckoutContainer__Wrapper-fzObJW.hCoRWS div.CheckoutContainer__Top-jkYYxN.frSlua div.CheckoutContainer__Steps-fqtMJZ.hksCPU > div.CheckoutContainer__Step-jTOHIe.iauDbA:nth-child(1)")).isDisplayed());
        driver.quit();
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

