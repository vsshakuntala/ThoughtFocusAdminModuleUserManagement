package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The composite key class for the USER_ACTION_METADATA_VALUE database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Embeddable
public class UserActionMetadataValuePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userActionId;
	private Long actionMetadataId;

	public UserActionMetadataValuePK() {
	}

	@Column(name = "USER_ACTION_ID")
	public Long getUserActionId() {
		return this.userActionId;
	}

	public void setUserActionId(Long userActionId) {
		this.userActionId = userActionId;
	}

	@Column(name = "ACTION_METADATA_ID")
	public Long getActionMetadataId() {
		return actionMetadataId;
	}

	public void setActionMetadataId(Long actionMetadataId) {
		this.actionMetadataId = actionMetadataId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserActionMetadataValuePK)) {
			return false;
		}
		UserActionMetadataValuePK castOther = (UserActionMetadataValuePK) other;
		return (this.userActionId.equals(castOther.userActionId))
				&& (this.actionMetadataId.equals(castOther.actionMetadataId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userActionId ^ (this.userActionId >>> 32)));
		hash = hash * prime
				+ ((int) (this.actionMetadataId ^ (this.actionMetadataId >>> 32)));

		return hash;
	}

	@Override
	public String toString() {
		return "UserRolePK [userActionId=" + userActionId + ", actionMetadataId=" + actionMetadataId + "]";
	}
	
}