package com.bento.tests.products;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bento.common.By;
import com.bento.common.utils.AutomationUtils;
import com.bento.pages.common.AdminDashboardPage;
import com.bento.pages.common.AdminLoginPage;
import com.bento.pages.common.AdminProductsPage;
import com.bento.tests.BaseTestCase;



public class TestProductUpdate extends BaseTestCase {

	protected static final Logger logger = LogManager.getLogger(TestProductUpdate.class);

	/**
	 * @step 1.Launch opencart admin url
	 * @step 2.Login
	 * @step 3.Click on Categories and product
	 * @step 4.Edit a product
	 * @step 5.Update the name
	 * @step 6.Save
	 * @step 7.Verify the message.
	 * @author bharath
	 */
	@Test
	public void verifyProductUpdated() {
		logger.info("1.Launch opencart admin url");
		m_driver.get(appProperties.getAdminUrl());
		String adminUser = appProperties.getAdminUser();
		String adminPass = appProperties.getAdminPass();
		AdminLoginPage adminLoginPage = PageFactory.initElements(m_driver, AdminLoginPage.class);
		// AdminLoginPage adminLoginPage
		// =CustomPageFactory.initElements(AdminLoginPage.class);
		adminLoginPage.login(adminUser, adminPass);
		
		AdminDashboardPage adminDashboardPage  = new AdminDashboardPage();
		PageFactory.initElements(m_driver, adminDashboardPage);
		
		adminDashboardPage.clickCatalog();
		adminDashboardPage.clickProducts();
		
		AdminProductsPage adminProductsPage = new AdminProductsPage();
		 PageFactory.initElements(m_driver, adminProductsPage);
		
		adminProductsPage.firstProductEditButton.click();
		String product  = AutomationUtils.getInputString(5);
		System.out.println(product);
		adminProductsPage.editFirstProduct("Apple - " + product );
		String expectedMessage = "Success: You have modified products!";
		String actualMessage  = adminProductsPage.getAlertMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage));
	}
	

	public void gowtham() {
		
	}
	
	

	public void bharath() {
		
	}
	
}
