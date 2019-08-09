package io.testpro.deens.webElementsExample.testNg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class radioButton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "/Users/aizada/Selenium/geckodriver");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://tutorialehtml.com/en/html-tutorial-radio-buttons");
		driver.manage().window().maximize();
		
		List<WebElement> allRadios = driver.findElements(By.name("citizenship"));
		System.out.println(allRadios.size());
		
		allRadios.get(1).click();
		
		System.out.println(allRadios.get(1).isSelected());
		
		driver.quit();
		driver.close();

	}

}
