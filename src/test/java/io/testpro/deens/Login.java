package io.testpro.deens;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Login extends BaseTest{


    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://deens-master.now.sh/login");
    }
    

    @Test
    public void LoginEmptyEmailTest(){

        driver.findElement(By.cssSelector("#password")).sendKeys("qwerty");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());

    }

    @Test
    public void LoginEmptyPasswordlTest(){

        driver.findElement(By.cssSelector("#email")).sendKeys("azat@testpro.io");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());

    }


}


