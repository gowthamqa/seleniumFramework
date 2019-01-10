package com.bento.pages.common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bento.common.dto.ProductDTO;
import com.bento.common.utils.data.ExcelManager;
import com.bento.pages.BasePage;

public class KartAdminProductsPage extends BasePage {
	
	/*@FindBy(css = "a[href='/offer/create']")
	public WebElement addProductButton;*/
	
	@FindBy(xpath = "//div[contains(text(),'Add Product')]")
	public WebElement addProductButton;
	
	@FindBy(xpath = "//input[@placeholder='Add a title']")
	public WebElement productTitleEle;
	
	@FindBy(xpath = "//textarea[contains(@placeholder,'Add all the information that your customer needs to know')]")
	public WebElement productDescriptionEle;

	@FindBy(xpath = "//input[@placeholder='Enter 0 if free']")
	public WebElement priceEle;
	
	@FindBy(xpath = "//input[@placeholder='Required']")
	public WebElement shippingDaysEle;
	
	@FindBy(partialLinkText = "Click To Select")
	public WebElement uploadImage;

	@FindBy(xpath = "//span[contains(text(),'Advanced Settings')]")
	public WebElement advancedSettingsEle;

	@FindBy(xpath = "//input[contains(@placeholder,'unlimited quantity')]")
	public WebElement totalAvailableQty;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Default is 10')]")
	public WebElement maxOrderQty;
	
	@FindBy(xpath = "//span[contains(text(),'SEO Options')]")
	public WebElement SEOEle;

	@FindBy(xpath = "//input[contains(@placeholder,'Eg: ')]")
	public WebElement keywordsEle;

	@FindBy(xpath = "//input[@value='Add Product to Store']")
	public WebElement productSaveButton;

	@FindBy(xpath = "//div[@class='caps']")
	public WebElement alertMessage;
	
	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement productEdit;
	
	public void editFirstProduct(String newName) {
		//productEditName.clear();
		//productEditName.sendKeys(newName);
	}

	public String getAlertMessage() {
		return alertMessage.getText().trim();
	}
	
	/*public void addProduct(ProductDTO dto) {
		addProductButton.click();
		productTitleEle.sendKeys(dto.getProductName());
		productDescriptionEle.sendKeys(dto.getDescription());
		
	}*/
	
	public void addProduct(ProductDTO dto) {
		addProductButton.click();
		//productTitleEle.sendKeys("Pigeon Special Combo Pack 2 L, 3 L, 5 L Pressure Cooker  (Aluminium)");
		String title = Keys.chord(Keys.SHIFT,Keys.TAB, "title");
		productDescriptionEle.sendKeys(title);
		productDescriptionEle.sendKeys(dto.getProductDescription());
		priceEle.sendKeys(dto.getPrice());
		shippingDaysEle.sendKeys(dto.getShippingDays());
		advancedSettingsEle.click();
		totalAvailableQty.sendKeys(dto.getTotalQty());
		maxOrderQty.sendKeys(dto.getMaxQty());
		SEOEle.click();
		keywordsEle.sendKeys(dto.getKeywords());
		productSaveButton.click();
	}
	
	public void addProduct() {
		addProductButton.click();
		//productTitleEle.sendKeys("Pigeon Special Combo Pack 2 L, 3 L, 5 L Pressure Cooker  (Aluminium)");
		//String text = Keys.chord(Keys.SHIFT,Keys.TAB, "Title");
		//productDescriptionEle.sendKeys(text);
		/*productDescriptionEle.sendKeys("General\n" + 
				"Sales Package\n" + 
				"3 Pressure Cooker\n" + 
				"Capacity\n" + 
				"2, 3, 5 litre\n" + 
				"Model Name\n" + 
				"Special Combo Pack\n" + 
				"Model Number\n" + 
				"12735\n" + 
				"Lid Locking Mechanism\n" + 
				"Knob Lock System\n" + 
				"Dishwasher Safe\n" + 
				"Yes\n" + 
				"Height\n" + 
				"45.7 cm\n" + 
				"Weight\n" + 
				"5.4 kg");*/
		priceEle.sendKeys("1599");
		shippingDaysEle.sendKeys("7");
		advancedSettingsEle.click();
		totalAvailableQty.sendKeys("100");
		maxOrderQty.sendKeys("2");
		SEOEle.click();
		keywordsEle.sendKeys("Pigeon");
		productSaveButton.click();
	}
	
	/*public void addProduct() {
		ExcelManager manager = new ExcelManager();
		addProductButton.click();
		//productTitleEle.sendKeys("Pigeon Special Combo Pack 2 L, 3 L, 5 L Pressure Cooker  (Aluminium)");
		String title = manager.readData(1, "productTitle");
		String text = Keys.chord(Keys.SHIFT,Keys.TAB, title);
		productDescriptionEle.sendKeys(text);
		String prodDesc = manager.readData(1, "productDescription");
		productDescriptionEle.sendKeys(prodDesc);
		priceEle.sendKeys("1599");
		shippingDaysEle.sendKeys("7");
		advancedSettingsEle.click();
		totalAvailableQty.sendKeys("100");
		maxOrderQty.sendKeys("2");
		SEOEle.click();
		keywordsEle.sendKeys("Pigeon");
		productSaveButton.click();
	}*/
	
}
