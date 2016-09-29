package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemAvailabilityPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long cartItemId;
	private Integer branchId;
	
	@Column(name="CART_ITEM_ID")
	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	@Column(name="BRANCH_ID")
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	
	@Override
	public int hashCode(){
		return this.cartItemId.hashCode();
	}
	
	
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof CartItemAvailability){
			CartItemAvailabilityPK cat=(CartItemAvailabilityPK)obj;
			result = (cat.getCartItemId().equals(this.cartItemId));
		}
		return result;
	}
	
	@Override
	public String toString(){
		return "Cart Item [Cart_Item_Id=" + cartItemId + "]";
	}
	
}
