package io.testpro.deens.webElementsExample.testNg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class checkBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "/Users/aizada/Selenium/geckodriver");
		FirefoxDriver driver2 = new FirefoxDriver();
		driver2.get("http://toolsqa.com/automation-practice-form/");
		driver2.manage().window().maximize();
		
		
		
		List<WebElement> allCheckBoxes = driver2.findElements(By.name("profession"));
		
		allCheckBoxes.size();
		allCheckBoxes.get(1).click();
		System.out.println(allCheckBoxes.get(1).isSelected());
		System.out.println(allCheckBoxes.size());
		
		driver2.quit();
		driver2.close();
		

	}

}
