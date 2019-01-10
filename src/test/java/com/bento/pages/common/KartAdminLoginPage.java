package com.bento.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.pages.BasePage;

public class KartAdminLoginPage extends BasePage{
	
	@FindBy(linkText="Log In")
	WebElement loginLink;

	@FindBy(id="id_login_username")
	WebElement userName;
	
	@FindBy(id="id_login_password")
	WebElement password;
	
	@FindBy(id="submit")
	WebElement loginButton;
	
	@FindBy(xpath="//a[text()='Forgot your password?']")
	WebElement forgotPassword;
	
	@FindBy(xpath="//p[@class='message fail']")
	public WebElement errorMessage;
	
	
	public void login(String user, String pass) {
		//loginLink.click();
		userName.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
	}
	
}
