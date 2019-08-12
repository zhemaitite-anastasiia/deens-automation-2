package io.testpro.deens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        String ourPartners = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(ourPartners, "Our Partners");

        driver.close();
    }

    @Test
    public void Footer_Privacy_Policy(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(3) > .commonStyles__P-cbpCjc:nth-child(4) > a")).click();

        String privacyPolicy = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(privacyPolicy, "Privacy Policy");

        driver.close();
    }

    @Test
    public void Footer_Terms(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://deens-master.now.sh/");
        driver.findElement(By.cssSelector(".BrandFooter__Column-fdSHvo:nth-child(3) > .commonStyles__P-cbpCjc:nth-child(3) > a")).click();

        String terms = driver.findElement(By.cssSelector("[class='blog__Title-exuQPF iBKNTF']")).getText();
        Assert.assertEquals(terms, "Terms and Conditions");

        driver.close();
        driver.quit();
    }
}