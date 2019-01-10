package com.bento.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.common.dto.ProductDTO;
import com.bento.pages.BasePage;

public class AdminProductsPage extends BasePage {

	@FindBy(xpath = "//tr[1]//a[@data-original-title='Edit']")
	public WebElement firstProductEditButton;

	@FindBy(name = "product_description[1][name]")
	public WebElement productEditName;

	@FindBy(xpath = "//button[@data-original-title='Save']")
	public WebElement saveButton;

	@FindBy(xpath = "//div[contains(@class,'alert')]")
	public WebElement alertMessage;

	@FindBy(xpath = "//input[@id='input-name1']")
	public WebElement productNameEle;

	@FindBy(xpath = "//div[@class='note-editable panel-body']")
	public WebElement descriptionEle;

	@FindBy(xpath = "//input[@id='input-meta-title1']")
	public WebElement metaTagTitleEle;

	@FindBy(xpath = "//a[text()='Data']")
	public WebElement dataEle;

	@FindBy(xpath = "//input[@id='input-model']")
	public WebElement modelEle;

	@FindBy(xpath = "//a[@data-original-title='Add New']")
	public WebElement addNewProductButton;

	@FindBy(xpath = "//button[@data-original-title='Save']")
	public WebElement productSaveButton;

	public void editFirstProduct(String newName) {
		productEditName.clear();
		productEditName.sendKeys(newName);
		saveButton.click();
	}

	public String getAlertMessage() {
		return alertMessage.getText().trim();
	}

	public void addProduct(ProductDTO dto) {
		addNewProductButton.click();
		productNameEle.sendKeys(dto.getProductTitle());
		//descriptionEle.sendKeys(dto.getDescription());
		//metaTagTitleEle.sendKeys(dto.getMetaTagTitle());
		dataEle.click();
		//modelEle.sendKeys(dto.getModel());
		productSaveButton.click();
	}
}
