package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Narasingha
 *
 */
public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String countryFlag;
	private String companyName;
	private String address;
	private String customerAccount;
	private Long userLanguage;
	private String notes;
	private String alias;
	private String password;
	private List<DivisionsDTO> userDivision;
	private long currentLoggedUserId;
	
	
	//For OAuth
	private String externalAuthenticationToken;
	
	
	
	public String getExternalAuthenticationToken() {
		return externalAuthenticationToken;
	}
	public void setExternalAuthenticationToken(String externalAuthenticationToken) {
		this.externalAuthenticationToken = externalAuthenticationToken;
	}
	public long getCurrentLoggedUserId() {
		return currentLoggedUserId;
	}
	public void setCurrentLoggedUserId(long currentLoggedUserId) {
		this.currentLoggedUserId = currentLoggedUserId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	public Long getUserLanguage() {
		return userLanguage;
	}
	public void setUserLanguage(Long userLanguage) {
		this.userLanguage = userLanguage;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<DivisionsDTO> getUserDivision() {
		return userDivision;
	}
	public void setUserDivision(List<DivisionsDTO> userDivisions) {
		this.userDivision = userDivisions;
	}
	public String getCountryFlag() {
		return countryFlag;
	}
	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", countryFlag=" + countryFlag + ", companyName=" + companyName + ", address=" + address
				+ ", customerAccount=" + customerAccount + ", userLanguage=" + userLanguage + ", notes=" + notes
				+ ", alias=" + alias + ", password=" + password + ", userDivision=" + userDivision
				+ ", currentLoggedUserId=" + currentLoggedUserId + "]";
	}
	
	
}
