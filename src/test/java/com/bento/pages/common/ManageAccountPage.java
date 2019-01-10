package com.bento.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.bento.common.dto.ClientDTO;
import com.bento.common.dto.EmployeeDTO;
import com.bento.common.utils.AutoIt;
import com.bento.common.utils.AutomationUtils;
import com.bento.pages.BasePage;

public class ManageAccountPage extends BasePage {
	
	Actions actions = new Actions(m_driver);
	
	@FindBy(xpath = "//div[contains(text(),'Manage Account')]")
	public WebElement manageAccountTab;
	
	@FindBy(xpath = "//div[contains(text(),'Bank Account')]")
	public WebElement bankAccountTab;
	
	@FindBy(xpath = "//div[contains(text(),'Reports')]")
	public WebElement reportsTab;
	
	@FindBy(xpath = "//span[@class='colorSuccess']")
	public WebElement bankAccountStatus;
	
	@FindBy(xpath = "//span[contains(text(),'Reset to Last Month')]")
	public WebElement resetTOLastMonthButton;
	
	@FindBy(xpath = "//span[contains(text(),'Download Report')]")
	public WebElement downloadReport;
	
	@FindBy(xpath = "//span[contains(text(),'Download Employee Roster')]")
	public WebElement dowloadEmployeeRoster;
	
	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	public WebElement terminateConfirmYes;
	
	@FindBy(xpath = "//span[contains(text(),'No')]")
	public WebElement terminateConfirmNo;
	
	@FindBy(xpath = "//input[contains(@id, 'MMDDYYYY')]")
	public WebElement finalCoverageDateEle;
	
	@FindBy(xpath = "//span[contains(text(),'Terminate Employee')]")
	public WebElement terminateEmployeeYes;
	
	@FindBy(xpath = "message")
	public WebElement alertMessage;
	
	public String getAlertMessage() {
		return alertMessage.getText().trim();
	}
	
	public String getAccountStatus() {
		manageAccountTab.click();
		sleep(3000);
		bankAccountTab.click();
		return bankAccountStatus.getText().trim();
	}
	
	
	public void downloadReport() {
		reportsTab.click();
		resetTOLastMonthButton.click();
		downloadReport.click();		
		
	}
	
	public void downloadRoster() {
		reportsTab.click();
		dowloadEmployeeRoster.click();		
	}


}
