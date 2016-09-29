package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_SALES_AREA_CURRENCY")
public class CustomerSalesAreaCurrency implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long custSalesAreaCurrencyId;
	private Long customerId;
	private Long salesAreaId;
	private String currencyCode;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean active;
	
	@Id
	@Column(name="CUST_SALES_AREA_CUR_ID")
	public Long getCustSalesAreaCurrencyId() {
		return custSalesAreaCurrencyId;
	}
	public void setCustSalesAreaCurrencyId(Long custSalesAreaCurrencyId) {
		this.custSalesAreaCurrencyId = custSalesAreaCurrencyId;
	}
	
	@Column(name="CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Column(name="SALES_AREA_ID")
	public Long getSalesAreaId() {
		return salesAreaId;
	}
	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}
	
	@Column(name="CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
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
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
