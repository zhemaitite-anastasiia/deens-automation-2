package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterPassword(String password) {

        typeText("#password", password);
    }

    public void enterLogin(String username) {

        typeText("#email", username);
    }

    public void submit() {

        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();
    }

    public boolean errorMessageAppeared() {
        return driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed();
    }

    public void openPage()
    {
        driver.get("https://deens-master.now.sh/login");
    }

}
