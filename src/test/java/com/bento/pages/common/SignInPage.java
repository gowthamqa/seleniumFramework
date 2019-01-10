package com.bento.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.common.dto.ClientDTO;
import com.bento.pages.BasePage;

public class SignInPage extends BasePage {
	
	@FindBy(xpath = "//input[contains(@id,'BentoEmployerID')]")
	public WebElement userName;
	
	@FindBy(xpath = "//input[contains(@id,'Password')]")
	public WebElement password;
	
	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	public WebElement loginButton;
	
	@FindBy(partialLinkText = "Forgot Password?")
	public WebElement forgotPassword;
	
	@FindBy(xpath = "//span[contains(text(),'Employer ID:']")
	public WebElement alertMessage;
	
	public void login(String user, String pass) {
		userName.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
	}
	
	public String getemployerID() {
		return alertMessage.getText().trim();
	}
	
}
