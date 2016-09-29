package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.util.Date;



public class UserListResultDto {
	
	
	/**
	 * 
	 */
	
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdDate;
	private String phoneNumber;
	private String companyName;
	private Long approved;
	private Long pending;
	
	
	public UserListResultDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public UserListResultDto(Long userId, String userName, String firstName, String lastName, String email,
			Date createdDate, String phoneNumber, String companyName, Long approved, Long pending) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createdDate = createdDate;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
		this.approved = approved;
		this.pending = pending;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPhone() {
		return phoneNumber;
	}
	public void setPhone(String phone) {
		this.phoneNumber = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getApproved() {
		return approved;
	}
	public void setApproved(Long approved) {
		this.approved = approved;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	@Override
	public String toString() {
		return "UserListResultDto [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", createdDate=" + createdDate + ", phone=" + phoneNumber + ", companyName="
				+ companyName + ", approved=" + approved + ", pending=" + pending + "]";
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
