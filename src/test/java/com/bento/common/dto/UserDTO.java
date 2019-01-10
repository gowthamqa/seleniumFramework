package com.bento.common.dto;

import java.util.List;

public class UserDTO {

	
	
	private String m_lastName;
	private String m_firstName;
	private String m_emailAddress;
	private String m_userName;
	private String m_organization;
	private String m_userType;
	private List<String> m_applicationInstances;
	private List<String> m_roles;
	
	
	public List<String> getApplicationInstances() {
		return m_applicationInstances;
	}
	public void setApplicationInstances(List<String> applicationInstances) {
		this.m_applicationInstances = applicationInstances;
	}
	public List<String> getRoles() {
		return m_roles;
	}
	public void setRoles(List<String> roles) {
		this.m_roles = roles;
	}
	public String getLastName() {
		return m_lastName;
	}
	public void setLastName(String lastName) {
		this.m_lastName = lastName;
	}
	public String getFirstName() {
		return m_firstName;
	}
	public void setFirstName(String firstName) {
		this.m_firstName = firstName;
	}
	public String getEmailAddress() {
		return m_emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.m_emailAddress = emailAddress;
	}
	public String getUserName() {
		return m_userName;
	}
	public void setUserName(String userName) {
		this.m_userName = userName;
	}
	public String getOrganization() {
		return m_organization;
	}
	public void setOrganization(String organization) {
		this.m_organization = organization;
	}
	public String getUserType() {
		return m_userType;
	}
	public void setUserType(String userType) {
		this.m_userType = userType;
	}
	
}
