package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Footer_Tests {

    @Test
    public void Footer_Partners(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(2) > .commonStyles__P-cbpCjc:nth-child(3) > a")).click();
        driver.close();
    }

    @Test
    public void Footer_Privacy_Policy(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(3) > .commonStyles__P-cbpCjc:nth-child(4) > a")).click();
        driver.close();
    }

    @Test
    public void Footer_Help_Center(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(4) a")).click();
        driver.close();
    }
}