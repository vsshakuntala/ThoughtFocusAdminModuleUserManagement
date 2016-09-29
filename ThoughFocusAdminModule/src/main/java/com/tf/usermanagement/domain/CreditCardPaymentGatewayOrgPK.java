package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CREDIT_CARD_PAYMENT_GATEWAY_ORG database table.
 *
 * 
 */

@Embeddable
public class CreditCardPaymentGatewayOrgPK implements Serializable{
	
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column(name="CREDIT_CARD_PAYMENT_GATEWAY_ID")
	private Long creditCardPaymentGatewayId;
	
	@Column(name="ORGANIZATION_ID")
	private Integer organizationId;

	public Long getCreditCardPaymentGatewayId() {
		return creditCardPaymentGatewayId;
	}

	public void setCreditCardPaymentGatewayId(Long creditCardPaymentGatewayId) {
		this.creditCardPaymentGatewayId = creditCardPaymentGatewayId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
