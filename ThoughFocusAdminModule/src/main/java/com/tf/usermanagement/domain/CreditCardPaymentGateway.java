package com.tf.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD_PAYMENT_GATEWAY")
public class CreditCardPaymentGateway {
	
	@Id
	@Column(name="CREDIT_CARD_PAYMENT_GATEWAY_ID")
	private Long creditCardPaymentGatewayId;

	@Column(name="PAYMENT_GATEWAY_NAME")
	private String paymentGatewayName;

	public Long getCreditCardPaymentGatewayId() {
		return creditCardPaymentGatewayId;
	}

	public void setCreditCardPaymentGatewayId(Long creditCardPaymentGatewayId) {
		this.creditCardPaymentGatewayId = creditCardPaymentGatewayId;
	}

	public String getPaymentGatewayName() {
		return paymentGatewayName;
	}

	public void setPaymentGatewayName(String paymentGatewayName) {
		this.paymentGatewayName = paymentGatewayName;
	}

}