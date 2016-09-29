package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the CUSTOMER_TYPE database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="CUSTOMER_TYPE")
public class CustomerType implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer customerTypeId;
	private String description;

    public CustomerType() {
    }

	@Id
	@Column(name="CUSTOMER_TYPE_ID")
	public Integer getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "CustomerType [customerTypeId=" + customerTypeId
				+ ", description=" + description + "]";
	}

}