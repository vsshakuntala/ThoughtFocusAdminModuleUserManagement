package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Entity Object for ShipmentCartItem <BR/>
 * 
 * The persistent class for the SHIPMENT_CART_ITEM database table.
 * 
 * @author Sai Prasad
 */
@Entity
@Table(name =  "SHIPMENT_CART_ITEM")
public class ShipmentCartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private ShipmentCartItemPK id;
	private Integer quantityShipped;
	private Shipment shipment;
	private CartItem cartItem;

	// Default Fields
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;

	@EmbeddedId
	public ShipmentCartItemPK getId() {
		return id;
	}

	public void setId(ShipmentCartItemPK id) {
		this.id = id;
	}

	@Column(name ="QUANTITY_SHIPPED")
	public Integer getQuantityShipped() {
		return quantityShipped;
	}

	public void setQuantityShipped(Integer quantityShipped) {
		this.quantityShipped = quantityShipped;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SHIPMENT_ID" , insertable=false, updatable=false)
	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
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
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
 	
	@OneToOne	
	@JoinColumn(name="CART_ITEM_ID", insertable=false, updatable=false)
	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "ShipmentCartItem [id=" + id + ", quantityShipped="
				+ quantityShipped + ", cartItem=" + cartItem + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", active="
				+ active + "]";
	}

}
