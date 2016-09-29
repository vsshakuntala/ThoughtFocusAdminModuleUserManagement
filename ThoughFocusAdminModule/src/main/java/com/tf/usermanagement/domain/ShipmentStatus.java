package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Object for ShipmentStatus   <br/>
 * 
 * The persistent class for the  SHIPMENT_STATUS database table.
 * 
 * @author Sai Prasad
 */
@Entity
@Table(name =  "SHIPMENT_STATUS")
public class ShipmentStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer statusId;
	private String statusName;
	private String description;
	
	@Id
	@Column(name = "SHIPMENT_STATUS_ID")
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "NAME")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ShipmentStatus [statusId=" + statusId + ", statusName="
				+ statusName + ", description=" + description + "]";
	}

}
