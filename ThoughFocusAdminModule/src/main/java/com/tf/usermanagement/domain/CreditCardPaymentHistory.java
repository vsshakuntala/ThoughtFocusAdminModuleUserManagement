package com.tf.usermanagement.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD_PAYMENT_HISTORY")
public class CreditCardPaymentHistory {
	
	@Id
	@Column(name = "CREDIT_CARD_PAYMENT_ID")
	/*@SequenceGenerator(name = "creditSeq", sequenceName = "CREDITCARDPAYMENTHISTORY_SEQ")
	@GeneratedValue(generator="creditSeq")*/
	@GeneratedValue
	private Long creditCardPaymentId;

	@Column(name = "CHILD_CART_ID")
	private Long childCartId;

	@Column(name = "TRANSACTION_TYPE_ID")
	private Long transactionTypeId;

	@Column(name = "TRANSACTION_ID")
	private String transactionId;

	@Column(name = "RESPONSE_CODE")
	private Long responseCode;

	@Column(name = "RESPONSE_MESSAGE")
	private String responseMessage;

	@Column(name = "TRANSACTION_AMOUNT")
	private BigDecimal transactionAmount;

	@Column(name = "TRANSACTION_CURRENCY_CODE")
	private String transactionCurrency;

	@Column(name = "TRANSACTION_DATE")
	private Date transactionDate;

	@Column(name = "TRANSACTION_DESC")
	private String transactionDesc;
	
	@Column(name = "MODIFIED_BY")
	private Long modifiedBy;
	
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "ACTIVE")
	private Boolean active;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "CART_ID")
	private Long cartId;

	public Long getCreditCardPaymentId() {
		return creditCardPaymentId;
	}

	public void setCreditCardPaymentId(Long creditCardPaymentId) {
		this.creditCardPaymentId = creditCardPaymentId;
	}

	public Long getChildCartId() {
		return childCartId;
	}

	public void setChildCartId(Long childCartId) {
		this.childCartId = childCartId;
	}

	public Long getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Long responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	
	
}
