package com.bento.common.dto;

public class ClientDTO {

	private String companyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String adminFirstName;
	private String adminLastName;
	private String adminEmailID;
	private String adminPhoneNumber;

	

	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getZipcode() {
		return zipcode;
	}



	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}



	public String getAdminFirstName() {
		return adminFirstName;
	}



	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}



	public String getAdminLastName() {
		return adminLastName;
	}



	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}



	public String getAdminEmailID() {
		return adminEmailID;
	}



	public void setAdminEmailID(String adminEmailID) {
		this.adminEmailID = adminEmailID;
	}



	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}



	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}



	/*public String toString() {
		return " ProductTitle : " + productTitle + ", Description : " +productDescription + ", price : " +price+ ", shippingDays : " +shippingDays+ ", totalQty : " +totalQty+ ", maxQty : " +maxQty+ ", keywords : " +keywords;
	}*/
}
