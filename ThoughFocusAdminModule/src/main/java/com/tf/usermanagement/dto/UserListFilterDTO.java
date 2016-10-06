package com.tf.usermanagement.dto;


public class UserListFilterDTO {
	private String divisions;
	private String roles;
	private String status;
	private String from_date;
	private String to_date;
	private String companyName;
	private String name;
	public String getDivisions() {
		return divisions;
	}
	public void setDivisions(String divisions) {
		this.divisions = divisions;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserListFilterDTO(String divisions, String roles, String status, String from_date, String to_date,
			String company, String name) {
		super();
		this.divisions = divisions;
		this.roles = roles;
		this.status = status;
		this.from_date = from_date;
		this.to_date = to_date;
		this.companyName = company;
		this.name = name;
	}
	public String getCompanyName() {
	    return companyName;
	}
	public void setCompanyName(String companyName) {
	    this.companyName = companyName;
	}
	
	
	
}
