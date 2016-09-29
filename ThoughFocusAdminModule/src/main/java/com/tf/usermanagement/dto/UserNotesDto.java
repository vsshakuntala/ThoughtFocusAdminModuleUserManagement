package com.tf.usermanagement.dto;

import java.util.Date;

public class UserNotesDto {

	private long userId;
	private String notes;
	private Date createdDate;
	private String userName;
	private long createdBy;
	
	//getters and setters
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserNotesDto [userId=");
		builder.append(userId);
		builder.append(", notes=");
		builder.append(notes);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
