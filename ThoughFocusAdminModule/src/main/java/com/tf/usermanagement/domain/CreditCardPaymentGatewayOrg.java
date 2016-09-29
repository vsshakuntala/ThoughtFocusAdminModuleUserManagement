package com.tf.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD_PAYMENT_GW_ORG")
public class CreditCardPaymentGatewayOrg {

	@EmbeddedId
	private CreditCardPaymentGatewayOrgPK id;

	@Column(name = "MERCHANT_ID")
	private String merchantId;

	@Column(name = "PASSWORD")
	private String merchantPassword;

	@Column(name = "MERCHANT_NAME")
	private String merchantName;

	@Column(name = "PAYMENT_GATEWAY_URL")
	private String paymentGatewayUrl;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPaymentGatewayUrl() {
		return paymentGatewayUrl;
	}

	public void setPaymentGatewayUrl(String paymentGatewayUrl) {
		this.paymentGatewayUrl = paymentGatewayUrl;
	}

	public CreditCardPaymentGatewayOrgPK getId() {
		return id;
	}

	public void setId(CreditCardPaymentGatewayOrgPK id) {
		this.id = id;
	}

}