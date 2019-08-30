package io.testpro.deens;


//import io.testpro.deens.Pages.LoginPage;


import io.testpro.deens.PageFactory.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Login extends BaseTest{



//    @Test(enabled = false)
//    public void LoginEmptyEmailTest(){
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.openPage();
//        loginPage.enterPassword("qwerty");
//        loginPage.submit();
//
//        Assert.assertTrue(loginPage.errorMessageAppeared());
//
//    }

    @DataProvider(name = "Credentials")
    public static Object[][] credentials() {

        return new Object[][] { { "testuser_1", "Test@123" }};

    }



    @Test(dataProvider = "Credentials")
    public void LoginEmptyPasswordlTest(String username, String password){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.enterLogin(username);
        loginPage.enterPassword(password);
//        loginPage.submit();

        Assert.assertTrue(loginPage.errorMessageAppeared());

    }

//    @Test
//    public void LoginIncorrectPasswordlTest(){
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.openPage();
//        loginPage.enterLogin("azat@testpro.io");
//        loginPage.enterPassword("Incorrect password");
//        loginPage.submit();
//
//        Assert.assertTrue(loginPage.errorMessageAppeared());
//
//    }








}


