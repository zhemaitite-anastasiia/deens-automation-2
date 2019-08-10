package io.testpro.deens;


import io.testpro.deens.Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Login extends BaseTest{



    @Test
    public void LoginEmptyEmailTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterPassword("qwerty");
        loginPage.submit();

        Assert.assertTrue(loginPage.errorMessageAppeared());

    }

    @Test
    public void LoginEmptyPasswordlTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin("azat@testpro.io");
        loginPage.submit();

        Assert.assertTrue(loginPage.errorMessageAppeared());

    }

    @Test
    public void LoginIncorrectPasswordlTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin("azat@testpro.io");
        loginPage.enterPassword("Incorrect password");
        loginPage.submit();

        Assert.assertTrue(loginPage.errorMessageAppeared());

    }
//
//
//    public void openLogin() {
//        driver.get("https://deens-master.now.sh/login");
//    }








}


