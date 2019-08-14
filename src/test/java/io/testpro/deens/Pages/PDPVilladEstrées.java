package io.testpro.deens.Pages;

import io.testpro.deens.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PDPVilladEstrées {

    WebDriverWait wait;
    WebDriver driver;

    public PDPVilladEstrées (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }




    public void searchBoxElement(String xPath){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

    }

    public void clearTexField(String xPath){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).clear();
    }



}
