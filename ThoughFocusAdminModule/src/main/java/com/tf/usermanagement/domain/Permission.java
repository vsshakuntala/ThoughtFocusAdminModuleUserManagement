package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the PERMISSIONS database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="PERMISSIONS")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long permissionId;
	private Boolean active;
	private String permissionDescription;
	private String permissionName;
	private Set<Role> roles;

    public Permission() {
    }


	@Id
	@Column(name="PERMISSION_ID")
	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}


	@Column(name="ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	@Column(name="PERMISSION_DESCRIPTION")
	public String getPermissionDescription() {
		return this.permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}


	@Column(name="PERMISSION_NAME")
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="permissions")
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", active="
				+ active + ", permissionDescription=" + permissionDescription
				+ ", permissionName=" + permissionName + "]";
	}
	
}