package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the USER_GROUP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="USER_GROUP")
public class UserGroup implements Serializable,IAuditLog{

	
	private static final long serialVersionUID = 1L;
	private UserGroupPK compositeId;
	private Boolean active = true;
	private Long groupId;
	private Long userId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Group group;
	private User user;
	//private Set<GroupAddress> groupAddresses;
	//private Set<Cart> carts;
	//private Set<GroupRole> groupRoles;
	//private Set<GroupCustomer> groupCustomers;


	public UserGroup() {
	}


	@EmbeddedId
	public UserGroupPK getCompositeId() {
		return compositeId;
	}

	public void setCompositeId(UserGroupPK compositeId) {
		this.compositeId = compositeId;
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

	@Column(name = "GROUP_ID", insertable=false, updatable=false)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	@Column(name = "USER_ID", insertable=false, updatable=false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn(name = "GROUP_ID", insertable=false, updatable=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID", insertable=false, updatable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 /*   @OneToMany(mappedBy="userGroup",fetch=FetchType.EAGER)
	public Set<GroupCustomer> getGroupCustomers() {
		return groupCustomers;
	}

	public void setGroupCustomers(Set<GroupCustomer> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}*/

	

	/*@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public Set<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(Set<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}
	@OneToMany(mappedBy="userGroups")
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}*/
/*	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public Set<GroupAddress> getGroupAddresses() {
		return groupAddresses;
	}

	public void setGroupAddresses(Set<GroupAddress> groupAddresses) {
		this.groupAddresses = groupAddresses;
	}
*/
	@Override
	public String toString() {
		return "UserGroup [id=" + compositeId + ", active=" + active + ", userId="
				+ groupId + ", userId=" + userId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}
	
	@Transient
	@Override
	public Long getId(){
		return this.compositeId.getUserId();
	}
 
	@Transient
	@Override
	public String getLogDetail(){
		StringBuilder sb = new StringBuilder();
		sb.append(" User Id : ").append(compositeId.getUserId())
		.append(" Group Name : ").append(compositeId.getGroupId())
		.append(" Group Id : ").append(groupId);
		
		//logger.debug("*************************************************"+sb);
 
		return sb.toString();
	}

	@Transient
	@Override
	public Serializable getCompositeKey() {
		return this.getCompositeId();
	}
}

