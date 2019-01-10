package com.bento.common.dto;

public class ProductDTO {

	private String productTitle;
	private String productDescription;
	private String price;
	private String shippingDays;
	private String totalQty;
	private String maxQty;
	private String keywords;

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShippingDays() {
		return shippingDays;
	}

	public void setShippingDays(String shippingDays) {
		this.shippingDays = shippingDays;
	}

	public String getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	public String getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(String maxQty) {
		this.maxQty = maxQty;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String toString() {
		return " ProductTitle : " + productTitle + ", Description : " +productDescription + ", price : " +price+ ", shippingDays : " +shippingDays+ ", totalQty : " +totalQty+ ", maxQty : " +maxQty+ ", keywords : " +keywords;
	}
}
