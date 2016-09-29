package com.tf.usermanagement.dto;

import java.util.Date;

/**
 * This is a DTO class which is used to get the 
 * UserId and OrganizationId for which the Admin
 * need to De-assign the user for a particular division
 * 
 * and this also have the admin Id and the date admin
 * de-assign teh user
 * @author Manideep
 *
 */
public class DeAssignUserToOrgInputDto {

	private long userId;
	private long organizationId;
	private long modifiedById;
	private Date modifiedDate;
	private boolean addUserToOrg;
	private String notes;
	
	
	//getters and setters
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getModifiedById() {
		return modifiedById;
	}
	public void setModifiedById(long modifiedById) {
		this.modifiedById = modifiedById;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public boolean isAddUserToOrg() {
		return addUserToOrg;
	}
	public void setAddUserToOrg(boolean addUserToOrg) {
		this.addUserToOrg = addUserToOrg;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	//to string
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeAssignUserToOrgInputDto [userId=");
		builder.append(userId);
		builder.append(", organizationId=");
		builder.append(organizationId);
		builder.append(", modifiedById=");
		builder.append(modifiedById);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", addUserToOrg=");
		builder.append(addUserToOrg);
		builder.append(", notes=");
		builder.append(notes);
		builder.append("]");
		return builder.toString();
	}
	
}
