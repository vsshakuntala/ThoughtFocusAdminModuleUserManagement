package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the CART_STATUS database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="CART_STATUS")
public class CartStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cartStatusId;
	private String cartStatusValue;
	private Set<Cart> carts;

    public CartStatus() {
    }


	@Id
	@Column(name="CART_STATUS_ID")
	public Integer getCartStatusId() {
		return this.cartStatusId;
	}

	public void setCartStatusId(Integer cartStatusId) {
		this.cartStatusId = cartStatusId;
	}


	@Column(name="CART_STATUS_VALUE")
	public String getCartStatusValue() {
		return this.cartStatusValue;
	}

	public void setCartStatusValue(String cartStatusValue) {
		this.cartStatusValue = cartStatusValue;
	}

	@OneToMany(mappedBy="cartStatus")
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}


	@Override
	public String toString() {
		return "CartStatus [cartStatusId=" + cartStatusId
				+ ", cartStatusValue=" + cartStatusValue + "]";
	}

}