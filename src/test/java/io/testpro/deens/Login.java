package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

    @Test
    public void LoginTest(){
        System.setProperty("webdriver.chrome.driver","src/test/java/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://deens-master.now.sh/");
        String homeTagLine = driver.findElement(By.tagName("h1")).getText();
        driver.quit();
        Assert.assertEquals(homeTagLine, "Customizable travels created by locals for free", "Home page Tag Line doesn't match");
    }

}


