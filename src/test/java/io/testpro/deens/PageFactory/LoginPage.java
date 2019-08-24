package io.testpro.deens.PageFactory;

import io.testpro.deens.BaseTest;
import io.testpro.deens.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage  {
    WebDriver driver;

    @FindBy (css = "#password")
    private WebElement password;

    @FindBy (how = How.ID,using = "email")
    private WebElement emailText;

    @FindBy (css = "[data-testid='loginSubmit']")
    private WebElement submitBtn;

    @FindBy (css = ".ui.error.message")
    private WebElement errorMessage;


    //more then 1 elements example
//    @FindBy(css = "[data-testid='loginSubmit']")
//    private List<WebElement> test;

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void enterLogin(String email){
        emailText.sendKeys(email);
    }

    public void submit(){
        this.submitBtn.click();
    }

    public boolean errorMessageAppeared() {
        return errorMessage.isDisplayed();
    }

    public void openPage() {
        driver.get("https://deens-master.now.sh/login");
    }


    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);



    }


}
