package com.bento.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.pages.BasePage;

public class AdminLoginPage extends BasePage{
	
	@FindBy(linkText="LOGIN")
	public WebElement loginLink;

	@FindBy(id="username")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="loginButton")
	WebElement login;
	
	@FindBy(xpath="//span[@class='help-block']/a")
	WebElement forgotPassword;
	
	
	public void login(String user, String pass) {
		userName.sendKeys(user);
		password.sendKeys(pass);
		login.click();
	}
	
}
