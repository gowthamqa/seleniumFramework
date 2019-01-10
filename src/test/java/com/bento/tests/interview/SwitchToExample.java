package com.bento.tests.interview;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class SwitchToExample {
	
	public void test1() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gowthamm\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naukri.com/");
		String titleOfmain = driver.getTitle();
		System.out.println(titleOfmain);
		
		String mainHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles.size());
		for (String handle : handles) {
			if(!mainHandle.equals(handle)) {
				driver.switchTo().window(handle);
				String titleOfChild = driver.getTitle();
				System.out.println(titleOfChild);
				driver.close();
			}
		}
		
		
		driver.switchTo().window(mainHandle);
	}
	
	
	public void jquery() {
		System.setProperty("webdriver.gecko.driver", "D:\\selenium_classes\\jars\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://jqueryui.com/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Droppable']")).click();
		WebElement frameElement = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frameElement);
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination  = driver.findElement(By.id("droppable"));
		Actions actions = new Actions(driver);
		actions.moveToElement(source).build().perform();
		actions.dragAndDrop(source, destination).build().perform();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Resizable']")).click();
		
	}
	
	public void setEx() {
		HashSet<String> set = new HashSet<String>();
		set.add("Bharath");
		set.add("Kumar");
		set.add("asdasd");
		set.add("zdfr");
		System.out.println(set.size());
		
		// Foreach 
		 for(String st : set) {
			 System.out.println(st);
		 }
		 
		 Iterator<String> it = set.iterator();
		
		 while(it.hasNext()) {
			 String  st  = it.next();
			 System.out.println(st);
		 }
	}
	
	public void kart() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gowthamm\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.instamojo.com/accounts/login/");
		//driver.get("https://www.instamojo.com/");
		//Thread.sleep(3000);
		//driver.findElement(By.cssSelector("a[class*='form-overlay-close']")).click();
		//driver.findElement(By.linkText("Log In")).click();
		driver.findElement(By.id("id_login_username")).sendKeys("snktarun27@gmail.com");
		driver.findElement(By.id("id_login_password")).sendKeys("8500971433");
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Add Product')]")).click();
		driver.findElement(By.cssSelector("body.pace-done.pace-done.pace-done.pace-done.pace-done.pace-done.pace-done.pace-done:nth-child(2) div.row:nth-child(3) section.columns.small-12:nth-child(2) div.row div.columns.small-12.medium-11.large-8.medium-centered.large-centered div.card.soft-card.push-one-half--bottom.small-only-soft-half form.form:nth-child(3) div.row div.columns.columns.small-12 ul.form-items-container li:nth-child(3) div:nth-child(2) > a.btn")).click();
		driver.switchTo().frame("fpapi_comm_iframe");
		driver.findElement(By.xpath("//button[text()='Choose File']")).click();
		
		//driver.findElement(By.xpath("//textarea[contains(@placeholder,'Add all the information that your customer needs to know')]")).sendKeys(Keys.chord(Keys.SHIFT,Keys.TAB,"ok bangaram"));
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		SwitchToExample sw = new SwitchToExample();
		sw.kart();
	}
}
