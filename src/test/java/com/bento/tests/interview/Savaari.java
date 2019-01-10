package com.bento.tests.interview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Savaari {
	public static String path = "D:\\selenium_classes\\jars\\geckodriver.exe";

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", path);
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://www.savaari.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement autoption = driver.findElement(By.xpath("//input[@id='from_city']"));
		autoption.sendKeys("ba");
		List<WebElement> list = driver.findElements((By.xpath("//div[@class='autocomplete-suggestions']/div")));
		String cityName = "Mumbai";
		for (WebElement webElement : list) {
			System.out.println(webElement.getText());
			if (webElement.getText().contains(cityName)) {
				if (webElement.getText().contains(cityName)) {
					webElement.click();
				}
			}
		}
	}
}