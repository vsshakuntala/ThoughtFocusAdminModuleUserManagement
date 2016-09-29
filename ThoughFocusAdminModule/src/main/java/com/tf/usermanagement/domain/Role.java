package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the ROLES database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable,Comparable<Role> {
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Boolean active;
	private Long createdBy;
	private Timestamp createdDate;
	private Long customerId;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private String roleDescription;
	private String roleName;
	private Set<Permission> permissions;
	private Set<User> users;
	private Set<RolePermission> rolePermissions;

	@Id
	@Column(name = "ROLE_ID")
	/*@SequenceGenerator(name = "RoleSequence", sequenceName = "ROLES_SEQ")
	@GeneratedValue(generator="RoleSequence")*/
	@GeneratedValue
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "ROLE_DESCRIPTION")
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany
	@JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID") })
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	/**
	 * @return User associated active Permission(s).
	 **/
	public Set<Permission> fetchActivePermissions() {
		Set<Permission> activePermissions = new HashSet<Permission>();

		for (RolePermission rolePermission : getRolePermissions()) {
			if (rolePermission.getActive())
				for (Permission permission : getPermissions()) {
					if ((rolePermission.getId().getPermissionId() == permission
							.getPermissionId()) && permission.getActive()) {
						activePermissions.add(permission);
						break;
					}
				}
		}

		return activePermissions;
	}

	@Override
	public int compareTo(Role o) {
		return this.roleName.compareTo(o.getRoleName());
	}

	@Override
	public int hashCode(){
		return this.roleId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof Role){
			Role r = (Role)obj;
			result = (r.getRoleId().equals(this.roleId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", active=" + active
				+ ", roleDescription=" + roleDescription + ", roleName="
				+ roleName + "]";
	}
	
}