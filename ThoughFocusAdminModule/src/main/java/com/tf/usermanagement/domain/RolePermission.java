package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the ROLE_PERMISSION database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "ROLE_PERMISSION")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private RolePermissionPK id;
	private Boolean active = true;
	private Long roleId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Role role;
	private Permission permission;

	public RolePermission() {
	}

	@EmbeddedId
	public RolePermissionPK getId() {
		return this.id;
	}

	public void setId(RolePermissionPK id) {
		this.id = id;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "ROLE_ID", insertable=false, updatable=false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable=false, updatable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "PERMISSION_ID", insertable=false, updatable=false)
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "RolePermission [id=" + id + ", active=" + active + ", roleId="
				+ roleId + "]";
	}
	
}