package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the USER_CUSTOMER database table.
 * 
 * @author Arvind.C
 * 
 */
@Embeddable
public class UserOrgIntermediaryMapPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long userOrgId;
	private Long intermediaryId;

	public UserOrgIntermediaryMapPK() {
	}

	@Column(name = "INTERMEDIARY_ID")
	public Long getIntermediaryId() {
		return intermediaryId;
	}
	
	public void setIntermediaryId(Long intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	@Column(name = "USER_ORG_ID")
	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userOrgId ^ (this.userOrgId >>> 32)));
		hash = hash * prime
				+ ((int) (this.intermediaryId ^ (this.intermediaryId >>> 32)));

		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof UserOrgIntermediaryMapPK){
			UserOrgIntermediaryMapPK cat=(UserOrgIntermediaryMapPK)obj;
			result = (cat.getUserOrgId().equals(this.userOrgId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "UserOrgIntermediaryMapPK [userOrgId=" + userOrgId + ", intermediaryId=" + intermediaryId + "]";
	}
}