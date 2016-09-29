package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Long customerId;
	private Boolean active;
	private String alias;
	private Long createdBy;
	private Timestamp createdDate;
	private String customerName;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private String website;
	private Set<Catalog> catalogs;
	private Set<Part> parts;
	private Set<UserCustomer> userCustomers;
	private Set<GroupCustomer> groupCustomers;
	private Set<CustomerAddress> customerAddresses;
	private String customerReference;
	private Set<Address> addresses;
	private Integer customerTypeId;
	private Set<UserCustomerOrganization> userCustomerOrganization;
	/*private Set<CustomerOrganizationMap> customerOrganizationMap;*/

	public Customer() {
    }

	public Customer(Long customerId,  String customerReference,String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerReference = customerReference;
	}

	@Id
	@Column(name="CUSTOMER_ID")
	@GeneratedValue
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	@Column(name="ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	@Column(name="ALIAS")
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	@Column(name="CUSTOMER_NAME")
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	@Column(name="WEBSITE")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	//bi-directional many-to-one association to Catalog
	@OneToMany(mappedBy="customer")
	public Set<Catalog> getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(Set<Catalog> catalogs) {
		this.catalogs = catalogs;
	}
	

	//bi-directional many-to-one association to Part
	@OneToMany(mappedBy="customer")
	public Set<Part> getParts() {
		return this.parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	public Set<UserCustomer> getUserCustomers() {
		return userCustomers;
	}

	public void setUserCustomers(Set<UserCustomer> userCustomers) {
		this.userCustomers = userCustomers;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
	public Set<CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(Set<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	public Set<GroupCustomer> getGroupCustomers() {
		return groupCustomers;
	}


	public void setGroupCustomers(Set<GroupCustomer> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}


	@Column(name="CUSTOMER_REFERENCE")
	public String getCustomerReference() {
		return customerReference;
	}
	
	@Column(name="CUSTOMER_TYPE_ID")	
	public Integer getCustomerTypeId() {
		return customerTypeId;
	}


	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	

	@OneToMany(mappedBy="customer")
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	/*@OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
	public Set<CustomerOrganizationMap> getCustomerOrganizationMap() {
		return customerOrganizationMap;
	}


	public void setCustomerOrganizationMap(
			Set<CustomerOrganizationMap> customerOrganizationMap) {
		this.customerOrganizationMap = customerOrganizationMap;
	}
*/
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	public Set<UserCustomerOrganization> getUserCustomerOrganization() {
		return userCustomerOrganization;
	}


	public void setUserCustomerOrganization(
			Set<UserCustomerOrganization> userCustomerOrganization) {
		this.userCustomerOrganization = userCustomerOrganization;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", active=" + active
				+ ", customerName=" + customerName + "]";
	}


}