package com.bento.tests.addemployee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bento.common.dto.ClientDTO;
import com.bento.common.dto.EmployeeDTO;
import com.bento.common.dto.ProductDTO;
import com.bento.common.utils.AutomationUtils;
import com.bento.common.utils.data.ExcelManager;
import com.bento.pages.common.AdminDashboardPage;
import com.bento.pages.common.AdminLoginPage;
import com.bento.pages.common.AdminProductsPage;
import com.bento.pages.common.EmployerHomePage;
import com.bento.pages.common.KartAdminLoginPage;
import com.bento.pages.common.KartAdminProductsPage;
import com.bento.pages.common.ManageAccountPage;
import com.bento.pages.common.SignInPage;
import com.bento.pages.common.SignUpPage;
import com.bento.tests.BaseTestCase;

public class TestAccountStatusAndReportsDownload extends BaseTestCase {

	protected static final Logger logger = LogManager.getLogger(TestAccountStatusAndReportsDownload.class);
	
	/**
	 * @step 1.Launch bento apps
	 * @step 2.Login
	 * @step 3.Add Employee
	 * @step 4.Search Employee
	 * @step 5.Bulk Upload
	 * @step 6.Terminate Employee
	 * @author gowtham
	 */
	
	@Test(timeOut=10000)
	public void verifyAccountStatus() throws InterruptedException {
		m_driver.get(appProperties.getAppUrl());
		String adminUser = appProperties.getAdminUser();
		String adminPass = appProperties.getAdminPass();
		SignInPage signInPage = PageFactory.initElements(m_driver, SignInPage.class);
		signInPage.login(adminUser, adminPass);
		Thread.sleep(3000);
		ManageAccountPage manageAccPage = PageFactory.initElements(m_driver, ManageAccountPage.class);
		System.out.println(manageAccPage.getAccountStatus());
		manageAccPage.downloadReport();
		manageAccPage.downloadRoster();		

	}
	
	
}
