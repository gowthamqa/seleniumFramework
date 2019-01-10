package com.bento.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bento.pages.BasePage;

public class AdminDashboardPage extends BasePage {

	
	//Clicking on catalog button.
	public void clickCatalog() {
		WebElement catalog = m_driver.findElement(By.xpath("//li[@id='menu-catalog']/a"));
		String value  = catalog.getAttribute("aria-expanded");
		if(value ==null || value.equals("false")) {
			catalog.click();
		}
	}
	
	//
	
	public void clickProducts() {
		m_driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
	}
}
