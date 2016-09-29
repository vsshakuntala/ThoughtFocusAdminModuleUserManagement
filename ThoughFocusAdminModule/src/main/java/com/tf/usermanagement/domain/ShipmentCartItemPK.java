package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShipmentCartItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long shipmentId;
	private Long cartItemId;

	@Column(name = "SHIPMENT_ID")
	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	@Column(name = "CART_ITEM_ID")
	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	@Override
	public String toString() {
		return "ShipItemPK [shipmentId=" + shipmentId + ", cartItemId="
				+ cartItemId + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipmentCartItemPK other = (ShipmentCartItemPK) obj;
		if (cartItemId == null) {
			if (other.cartItemId != null)
				return false;
		} else if (!cartItemId.equals(other.cartItemId))
			return false;
		if (shipmentId == null) {
			if (other.shipmentId != null)
				return false;
		} else if (!shipmentId.equals(other.shipmentId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartItemId == null) ? 0 : cartItemId.hashCode());
		result = prime * result
				+ ((shipmentId == null) ? 0 : shipmentId.hashCode());
		return result;
	}

}
