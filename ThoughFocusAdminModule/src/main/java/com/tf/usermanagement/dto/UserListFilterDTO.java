package com.tf.usermanagement.dto;

import java.util.Date;
import java.util.List;

public class UserListFilterDTO {
	private String divisions;
	private String roles;
	private String status;
	private Date from_date;
	private Date to_date;
	private String company;
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
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserListFilterDTO(String divisions, String roles, String status, Date from_date, Date to_date,
			String company, String name) {
		super();
		this.divisions = divisions;
		this.roles = roles;
		this.status = status;
		this.from_date = from_date;
		this.to_date = to_date;
		this.company = company;
		this.name = name;
	}
	
	
	
}
