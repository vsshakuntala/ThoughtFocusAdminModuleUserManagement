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
public class OrganizationConfigPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer organizationId;
	private String configName;

	public OrganizationConfigPK() {
	}
	
	public OrganizationConfigPK(Integer organizationId, String configName) {
		super();
		this.organizationId = organizationId;
		this.configName = configName;
	}

	@Column(name = "ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "CONFIG_NAME")
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserActionMetadataValuePK)) {
			return false;
		}
		OrganizationConfigPK castOther = (OrganizationConfigPK) other;
		return (this.organizationId.equals(castOther.organizationId))
				&& (this.configName.equals(castOther.configName));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.organizationId ^ (this.organizationId >>> 32)));
		/*hash = hash * prime
				+ ((int) (this.configName ^ (this.configName >>> 32)));*/

		return hash;
	}

	@Override
	public String toString() {
		return "UserRolePK [userActionId=" + organizationId + ", actionMetadataId=" + organizationId + "]";
	}
	
}