package io.testpro.deens.seleniumActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SeleniumActions {

    public static void main(String [] args ){

        System.setProperty("webdriver.chrome.driver","/Users/brozybay/MyWorkSpace/Drivers/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        //mouse actions
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
        Actions act = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("//button[text()='Automation Tools']"));
        act.moveToElement(ele).perform();
        List<WebElement> links =driver.findElements(By.xpath("//*[@class='dropdown-content']/a"));
        for(int i=0; i<links.size(); i++){
            WebElement element = links.get(i);
            String text = element.getAttribute("innerHTML");
            System.out.println("Links names are "+text);
            if(text.equalsIgnoreCase("Appium")){
                element.click();
                break;
            }
        }

////////////////////////////////

        //This is window Handle
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");

        driver.findElement(By.cssSelector("[class='Bgzgmd']>li:nth-child(3)>a")).click();

        //windowHandle

        Set<String> winId = driver.getWindowHandles();
        System.out.println("Window size is "+ winId.size());

        //iteration
        Iterator<String>it = winId.iterator();
        String mainWindow=it.next();
        String firstWindow=it.next();
        System.out.println("This is mainWindow "+mainWindow);

        driver.switchTo().window(firstWindow);
        System.out.println("This is firstWindow "+firstWindow);
        driver.switchTo().window(mainWindow);
        System.out.println("This is mainWindow "+mainWindow);

        driver.quit();
        driver.close();

    }
}
