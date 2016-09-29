package com.tf.usermanagement.dto;

import java.util.Date;
import java.util.List;

public class AssignOrgDto {
	private long userId;
	private long modifiedById;
	private Date modifiedDate;
	private boolean addUserToOrg;
	private List<AssignOrgMapDto> organizationIds;
	private String note;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public List<AssignOrgMapDto> getOrganizationIds() {
		return organizationIds;
	}
	public void setOrganizationIds(List<AssignOrgMapDto> organizationIds) {
		this.organizationIds = organizationIds;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignOrgDto [userId=");
		builder.append(userId);
		builder.append(", modifiedById=");
		builder.append(modifiedById);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", addUserToOrg=");
		builder.append(addUserToOrg);
		builder.append(", organizationIds=");
		builder.append(organizationIds);
		builder.append("]");
		return builder.toString();
	}
	

}
