package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The persistent class for the USER_CUSTOMER database table.
 * 
 * @author Arvind Chauhan
 * 
 */
@Entity
@Table(name = "USER_CUSTOMER")
public class UserCustomer implements Serializable {
	private static final long serialVersionUID = 1L;
	private UserCustomerPK id;
	private Long userId;
	private Long customerId;
	private Boolean active;
	private User user;
	private Customer customer;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	//private Set<Address> addresses;
	
	public UserCustomer() {
	}

	@EmbeddedId
	public UserCustomerPK getId() {
		return this.id;
	}

	public void setId(UserCustomerPK id) {
		this.id = id;
	}

	@Column(name = "USER_ID", insertable=false, updatable=false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable=false, updatable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	
	/*@OneToMany
	@JoinColumn(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}*/

	@Override
	public String toString() {
		return "UserCustomer [id=" + id + ", userId=" + userId + ", active="
				+ active + "]";
	}

}