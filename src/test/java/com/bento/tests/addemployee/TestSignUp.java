package com.bento.tests.addemployee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bento.common.dto.ClientDTO;
import com.bento.common.dto.ProductDTO;
import com.bento.common.utils.AutomationUtils;
import com.bento.common.utils.data.ExcelManager;
import com.bento.pages.common.AdminDashboardPage;
import com.bento.pages.common.AdminLoginPage;
import com.bento.pages.common.AdminProductsPage;
import com.bento.pages.common.KartAdminLoginPage;
import com.bento.pages.common.KartAdminProductsPage;
import com.bento.pages.common.SignUpPage;
import com.bento.tests.BaseTestCase;

public class TestSignUp extends BaseTestCase {

	protected static final Logger logger = LogManager.getLogger(TestSignUp.class);

	/**
	 * @step 1.Launch Kart site
	 * @step 2.Login as admin
	 * @step 3.Add product
	 * @step 4.Validate Message
	 * @author Gowtham
	 */
	@Test(enabled=false)
	public void verifySignUp() {
		logger.info("1.Launch Kart url");
		m_driver.get(appProperties.getAppUrl());
		m_driver.findElement(By.partialLinkText("Sign Up")).click();
		m_driver.findElement(By.name("company_name")).sendKeys("Bento");
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterCompanyAddress1')]")).sendKeys("124 George St.");
		
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterCity')]")).sendKeys("Columbus");
		//We are not able to identify the select box so using keys operation
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterCity')]")).sendKeys(Keys.TAB , "Ohio", Keys.ENTER);
		String res = m_driver.findElement(By.xpath("//div[@class='css-1hwfws3']")).getText();
		System.out.println("result : " + res);
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterZip')]")).sendKeys("55607");
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterAdministratorFirstName')]")).sendKeys("First Name");
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterAdministratorLastName')]")).sendKeys("Last Name");
		m_driver.findElement(By.xpath("//input[contains(@id,'EnterAdministratorsEmail')]")).sendKeys("test1@gmail.com");
		m_driver.findElement(By.xpath("//input[contains(@id,'AdministratorsPhoneNumber')]")).sendKeys("6768903876");
		m_driver.findElement(By.id("agreeToCambalt")).click();
		m_driver.findElement(By.xpath("//span[contains(text(),'Sign Up')]")).click();
		
		String message = m_driver.findElement(By.xpath("//div[@style='font-size: 18px;']/p[1]")).getText();
		System.out.println(message);		
	}
	
	@Test
	public void verifySignUpEx() {
		m_driver.get(appProperties.getAppUrl());
		SignUpPage signUppage = PageFactory.initElements(m_driver, SignUpPage.class);
		ExcelManager manager = new ExcelManager();
		ClientDTO dto = manager.getClientDTO(1);
		signUppage.signUp(dto);
		//System.out.println(signUppage.getAlertMessage());
		
	}
	
}
