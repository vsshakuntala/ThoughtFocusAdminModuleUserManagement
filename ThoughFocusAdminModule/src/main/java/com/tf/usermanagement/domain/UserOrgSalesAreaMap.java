package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ORG_SALES_AREA_MAP")
public class UserOrgSalesAreaMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userOrgSalesAreaId;
	private Long userOrgId;
	private Long salesAreaId;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean active;
	
	@Id
	@GeneratedValue
	@Column(name="USER_ORG_SALES_AREA_MAP_ID")
	public Long getUserOrgSalesAreaId() {
		return userOrgSalesAreaId;
	}
	public void setUserOrgSalesAreaId(Long userOrgSalesAreaId) {
		this.userOrgSalesAreaId = userOrgSalesAreaId;
	}
	
	@Column(name="USER_ORG_ID")
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	
	@Column(name="SALES_AREA_ID")
	public Long getSalesAreaId() {
		return salesAreaId;
	}
	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
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
