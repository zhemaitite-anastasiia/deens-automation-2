package io.testpro.deens.webElementsExample.testNg;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class takeScreenshot {
	
	static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		System.setProperty("webdriver.gecko.driver", "/Users/aizada/Selenium/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://abcnews.go.com/");
		driver.manage().window().maximize();
		takeScreenShot();
	}
	
	public static void takeScreenShot() {
		// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//browser.jpg"));
		}
		 
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		 
		 }
		
		driver.quit();
		driver.close();
		
	}

}
