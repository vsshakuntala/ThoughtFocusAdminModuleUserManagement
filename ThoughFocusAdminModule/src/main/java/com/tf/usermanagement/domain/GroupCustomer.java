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


/**
 * The persistent class for the GROUP_CUSTOMER database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="GROUP_CUSTOMER")
public class GroupCustomer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private GroupCustomerPK id;
	private Boolean active = true;
	private Long groupId;
	private Long customerId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Group group;
	private Customer customer;
	//private Set<GroupAddress> groupAddress;

	/*private UserGroup userGroup;*/
	
	public GroupCustomer() {
	}

	@EmbeddedId
	public GroupCustomerPK getId() {
		return this.id;
	}

	public void setId(GroupCustomerPK id) {
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

	@Column(name = "GROUP_ID", insertable=false, updatable=false)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	@Column(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@ManyToOne
	@JoinColumn(name = "GROUP_ID", insertable=false, updatable=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

/*	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumns({  
      @JoinColumn(name="GROUP_ID", referencedColumnName="GROUP_ID")
    })  
	public Set<GroupAddress> getGroupAddress() {
		return groupAddress;
	}

	public void setGroupAddress(Set<GroupAddress> groupAddress) {
		this.groupAddress = groupAddress;
	}*/
/*	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="GROUP_ID", insertable=false, updatable=false)
	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
*/
	@Override
	public String toString() {
		return "GroupCustomer [id=" + id + ", active=" + active + ", userId="
				+ groupId + ", customerId=" + customerId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}
}

