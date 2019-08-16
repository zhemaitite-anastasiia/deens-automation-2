package io.testpro.deens.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    WebDriver driver;

    @FindBy(css = "#email")
    private WebElement email;

    @FindBy(css = "#username")
    private WebElement username;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = ".green-btn")
    private WebElement clickToElement;

    public void enterEmail(String email){
        this.email.sendKeys(email);
    }

    public void enterUserName(String username){
        this.username.sendKeys(username);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void submit(){
        this.clickToElement.click();
    }

    public void openPage() {
        driver.get("https://deens-master.now.sh/register");
    }

    public SignUpPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);



    }
}
