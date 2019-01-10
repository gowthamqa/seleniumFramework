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

public class EmployerHomePage extends BasePage {
	
	Actions actions = new Actions(m_driver);
	
	@FindBy(xpath = "//span[contains(text(),'Add Employee')]")
	public WebElement addEmployeeButton;
	
	@FindBy(xpath = "//span[contains(text(),'Bulk Employee Upload (.csv)')]")
	public WebElement bulkEmployeeUploadButton;
	
	@FindBy(xpath = "//span[contains(text(),'Locate File')]")
	public WebElement locateFileButton;
	
	@FindBy(xpath = "//span[contains(text(),'Save')]")
	public WebElement saveUploadButton;
	
	@FindBy(xpath = "//input[contains(@id,'EnterFirstName')]")
	public WebElement memberFirstNameEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterMiddleName')]")
	public WebElement memberMidNameEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterLastName')]")
	public WebElement memberLastNameEle;
	
	@FindBy(xpath = "//input[contains(@id,'MemberEmail')]")
	public WebElement memberEmailEle;
	
	@FindBy(xpath = "//input[contains(@id,'PhoneNumber')]")
	public WebElement memberphNumberEle;
	
	@FindBy(xpath = "//input[contains(@id,'MemberSSN')]")
	public WebElement memberSSNEle;
	
	@FindBy(xpath = "//div[@aria-label='Add Employee']//div[@class='grid']/div[4]/div[2]")
	public WebElement memberdateOfBirthEle;
	
	@FindBy(xpath = "//div[@aria-label='Add Employee']//div[@class='grid']/div[5]/div[1]")
	public WebElement startDateEle;
	
	@FindBy(xpath = "//div[@aria-label='Add Employee']//div[@class='grid']/div[5]/div[2]")
	public WebElement endDateEle;
	
	@FindBy(xpath = "//span[contains(text(),'Save')]")
	public WebElement saveButton;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	public WebElement cancelButton;
	
	@FindBy(xpath = "//input[contains(@id,'FilteringNameDOB')]")
	public WebElement filterNamerDOB;
	
	@FindBy(xpath = "//table[@class='employeesTable']/tbody/tr[2]/td[6]//button")
	public WebElement firstViewButton;
	
	@FindBy(xpath = "//span[contains(text(),'Terminate Employee')]")
	public WebElement terminateButton;
	
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
	
	public void serchEmployee(String name) {
		filterNamerDOB.sendKeys(name);
		
	}
	
	public void cancelTerminateEmployee() throws InterruptedException {
		firstViewButton.click();
		Thread.sleep(4000);
		terminateButton.click();	
		actions.moveToElement(terminateConfirmNo);
		actions.click();
		actions.build().perform();
		terminateConfirmNo.click();
		
	}
	
	public void terminateEmployee() throws InterruptedException {
		firstViewButton.click();
		Thread.sleep(4000);
		terminateButton.click();	
		actions.moveToElement(terminateConfirmYes);
		actions.click();
		actions.build().perform();
		terminateConfirmYes.click();
		finalCoverageDateEle.sendKeys(AutomationUtils.getTodaysDateWithFormat("MMddYYYY"));
		//terminateEmployeeYes.click();
		
	}

	public void addNewEmpoloyee(EmployeeDTO dto) {
		addEmployeeButton.click();
		memberFirstNameEle.sendKeys(dto.getMemberFirstName());
		memberMidNameEle.sendKeys(dto.getMemberMiddleName());
		memberLastNameEle.sendKeys(dto.getMemberLastName());
		memberEmailEle.sendKeys(dto.getMemberEmailID());
		memberphNumberEle.sendKeys(dto.getMemberPhoneNumber());
		memberSSNEle.sendKeys(dto.getMemberSSN());
		actions.moveToElement(memberdateOfBirthEle);
		actions.click();
		actions.sendKeys(dto.getMemberDOB());
		actions.build().perform();
		actions.moveToElement(startDateEle);
		actions.click();
		actions.sendKeys(dto.getStartDate());
		actions.build().perform();
		actions.moveToElement(endDateEle);
		actions.click();
		actions.sendKeys(dto.getEndDate());
		actions.build().perform();
		
	}
	
	public void bulkEmployeeUpload(String title, String filePath) {
		bulkEmployeeUploadButton.click();
		//AutomationUtils.sleep(3);
		locateFileButton.click();
		//AutomationUtils.sleep(3);
		AutoIt.uploadFile(title, filePath);
		AutomationUtils.sleep(5);
		saveUploadButton.click();		
		
	}
	
	public void addNewEmpoloyee() {
		addEmployeeButton.click();
		memberFirstNameEle.sendKeys("James");
		memberMidNameEle.sendKeys("m");
		memberLastNameEle.sendKeys("Sorenson");
		memberEmailEle.sendKeys("jmsrf@gmail.com");
		memberphNumberEle.sendKeys("9123456780");
		memberSSNEle.sendKeys("567890234");
		actions.moveToElement(memberdateOfBirthEle);
		actions.click();
		actions.sendKeys("10101990");
		actions.build().perform();
		actions.moveToElement(startDateEle);
		actions.click();
		actions.sendKeys("12012018");
		actions.build().perform();
		actions.moveToElement(endDateEle);
		actions.click();
		actions.sendKeys("11312019");
		actions.build().perform();
		saveButton.click();
		
		/*memberdateOfBirthEle.sendKeys("10101990");
		startDateEle.sendKeys("12012018");
		endDateEle.sendKeys("11312019");*/
		
	}
}
