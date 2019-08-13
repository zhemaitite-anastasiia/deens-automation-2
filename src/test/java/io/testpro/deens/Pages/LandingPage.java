package io.testpro.deens.Pages;

import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    public LandingPage (WebDriver driver){
        super(driver);
    }

    public void openPage() {
        driver.get("https://deens-master.now.sh/");
    }
}
