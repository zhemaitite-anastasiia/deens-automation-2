package io.testpro.deens.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }



    public void openPage() {
        driver.get("https://deens-master.now.sh/register");
    }

    public void enterEmail(String email) {
        typeText("#email", email);
    }

    public void enterUserName(String username) {
        typeText("#username", username);
    }

    public void enterPassword(String password) {
        typeText("#password", password);
    }

    public void submit() {
        clickToElement(".green-btn");
    }

}
