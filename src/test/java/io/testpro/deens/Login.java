package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {

    @Test
    public void LoginTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        String homeTagLine = driver.findElement(By.tagName("h1")).getText();

        driver.quit();

        Assert.assertEquals(homeTagLine, "Customizable travels created by locals for free", "Home page Tag Line doesn't match");

    }

    @Test
    public void LoginEmptyEmailTest(){
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/login");
        driver.findElement(By.cssSelector("#password")).sendKeys("qwerty");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());

        driver.quit();
    }


    @Test
    public void LoginEmptyPasswordlTest(){
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/login");
        driver.findElement(By.cssSelector("#email")).sendKeys("azat@testpro.io");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());

        driver.quit();
    }


}


