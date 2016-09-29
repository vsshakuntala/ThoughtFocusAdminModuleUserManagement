package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "CART_ITEM_AVAILABILITY")
public class CartItemAvailability implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CartItemAvailabilityPK id;
	
	private Integer availableQuantity;
	private Integer leadTime;
	private Boolean active = true;
	
	@EmbeddedId
	public CartItemAvailabilityPK getId() {
		return id;
	}
	public void setId(CartItemAvailabilityPK id) {
		this.id = id;
	}
	
	@Column(name = "AVAILABLE_QUANTITY")
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	@Column(name = "LEAD_TIME")
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}