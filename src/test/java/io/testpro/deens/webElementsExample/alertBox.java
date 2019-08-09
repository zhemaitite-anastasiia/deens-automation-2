package io.testpro.deens.webElementsExample;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class alertBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "/Users/aizada/Selenium/geckodriver");
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		
		driver.findElement(By.id("login1")).sendKeys("user");
		driver.findElement(By.name("proceed")).click();
		
		Alert al=driver.switchTo().alert();
		System.out.println(al.getText());
		
		al.accept();
	
		driver.switchTo().defaultContent();
		
		driver.quit();
		driver.close();

	}

}
