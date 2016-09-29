package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VW_USER_CUSTOMER")
public class UserCustomerOrganization implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UserCustomerOrgPK id;

	private Long userId;
	private Long customerId;
	private Long organizationId;
	
	private Customer customer;
	
	
	@EmbeddedId
	public UserCustomerOrgPK getId() {
		return id;
	}
	public void setId(UserCustomerOrgPK id) {
		this.id = id;
	}
	@Column(name = "USER_ID",insertable=false, updatable=false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "CUSTOMER_ID",insertable=false, updatable=false)
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Column(name = "ORGANIZATION_ID",insertable=false, updatable=false)
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID" , insertable=false,updatable=false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
