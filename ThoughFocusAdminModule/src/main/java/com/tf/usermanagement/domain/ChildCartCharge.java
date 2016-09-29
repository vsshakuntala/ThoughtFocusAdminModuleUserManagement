package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the CHILD_CART_CHARGE database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name="CHILD_CART_CHARGE")
public class ChildCartCharge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long childCartChargeId;
	private Long childCartId;
	private Integer chargeId;
	private BigDecimal amount;
	private String currencyCode;
	private Boolean active;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;

	public ChildCartCharge() {
	}

	@Id
	@Column(name = "CHILD_CART_CHARGE_ID")
	/*@SequenceGenerator(name = "childCartChargeSequence", sequenceName = "SEQ_CHILDCARTCHARGE")
	@GeneratedValue(generator="childCartChargeSequence")*/
	@GeneratedValue
	public Long getChildCartChargeId() {
		return childCartChargeId;
	}

	public void setChildCartChargeId(Long childCartChargeId) {
		this.childCartChargeId = childCartChargeId;
	}

	@Column(name="CHILD_CART_ID")
	public Long getChildCartId() {
		return childCartId;
	}

	public void setChildCartId(Long childCartId) {
		this.childCartId = childCartId;
	}

	@Column(name="CHARGE_ID")
	public Integer getChargeId() {
		return chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name="CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ChildCartCharge)) {
			return false;
		}
		ChildCartCharge castOther = (ChildCartCharge) other;
		return (this.childCartId.equals(castOther.childCartId))
				&& (this.chargeId.equals(castOther.chargeId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.childCartId ^ (this.childCartId >>> 32)));
		hash = hash * prime
				+ ((int) (this.chargeId ^ (this.chargeId >>> 32)));

		return hash;
	}
}