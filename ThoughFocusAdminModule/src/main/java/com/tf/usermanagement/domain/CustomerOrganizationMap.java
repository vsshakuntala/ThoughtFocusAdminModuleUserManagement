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
 * The persistent class for the ORGANIZATION_ADDRESS database table.
 * 
 * @author Lakhan Jain
 * 
 */
@Entity
@Table(name="CUSTOMER_ORGANIZATION_MAP")
public class CustomerOrganizationMap implements Serializable {
	private static final long serialVersionUID = 1L;

	private CustomerOrganizationMapPK id;
	private Boolean active = true;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Customer customer;
	
	
	@EmbeddedId
	public CustomerOrganizationMapPK getId() {
		return id;
	}
	public void setId(CustomerOrganizationMapPK id) {
		this.id = id;
	}
	
	

	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
/*	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", insertable=false, updatable=false)
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	*/
	
	
	
	
	
	
	
}
