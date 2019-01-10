package com.bento.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.common.dto.ClientDTO;
import com.bento.pages.BasePage;

public class SignUpPage extends BasePage {
	
	@FindBy(partialLinkText = "Sign Up")
	public WebElement signUplink;

	@FindBy(name = "company_name")
	public WebElement companyName;
	
	@FindBy(xpath = "//input[contains(@id,'EnterCompanyAddress1')]")
	public WebElement address1Ele;
	
	@FindBy(xpath = "//input[contains(@id,'EnterCompanyAddress2')]")
	public WebElement address2Ele;
	
	@FindBy(xpath = "//input[contains(@id,'EnterCity')]")
	public WebElement cityEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterZip')]")
	public WebElement zipcodeEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterAdministratorFirstName')]")
	public WebElement firstNameEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterAdministratorLastName')]")
	public WebElement lastNameEle;
	
	@FindBy(xpath = "//input[contains(@id,'EnterAdministratorsEmail')]")
	public WebElement emailEle;
	
	@FindBy(xpath = "//input[contains(@id,'AdministratorsPhoneNumber')]")
	public WebElement phoneNumberEle;
	
	@FindBy(id="agreeToCambalt")
	public WebElement checkBox;
	
	@FindBy(xpath = "//span[contains(text(),'Sign Up')]")
	public WebElement signUpButton;
	
	@FindBy(xpath = "//div[@style='font-size: 18px;']/p[1]")
	public WebElement alertMessage;
	
	
	
	
	public String getAlertMessage() {
		return alertMessage.getText().trim();
	}

	public void signUp(ClientDTO dto) {
		signUplink.click();
		companyName.sendKeys(dto.getCompanyName());
		address1Ele.sendKeys(dto.getAddress1());
		address2Ele.sendKeys(dto.getAddress2());
		cityEle.sendKeys(dto.getCity());
		cityEle.sendKeys(Keys.TAB, dto.getState(), Keys.ENTER);
		zipcodeEle.sendKeys(dto.getZipcode());
		firstNameEle.sendKeys(dto.getAdminFirstName());
		lastNameEle.sendKeys(dto.getAdminLastName());
		emailEle.sendKeys(dto.getAdminEmailID());
		phoneNumberEle.sendKeys(dto.getAdminPhoneNumber());
		checkBox.click();
		signUpButton.click();
		
	}
}
