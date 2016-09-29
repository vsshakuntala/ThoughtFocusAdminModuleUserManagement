package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *  Entity Object for Shipment <br/>
 *  
 *  The persistent class for the SHIPMENT database table.
 * 
 * @author Sai Prasad
 */
@Entity
@Table(name = "SHIPMENT")
public class Shipment implements Serializable {

	private static final long serialVersionUID = 1L;

	// Main Shipment Order Details
	private Long shipmentId;
	private Long invoiceId;
	private String shipmentReference;
	private String trackingReference;
	private Timestamp shipmentDate;
	//private Timestamp shipmentTime; // TimeStamp wont work here. instead we can go with java.util.Date with @Temporal annotation.
	private BigDecimal shipmentWeight;
	private String shipmentWeightUOM; //Shipment Weight UOM refers to the total weight for the Shipment
	
	private ShipmentStatus shipmentStatus;
	private BranchPlant branchPlant;
	private ChildCart childCart;
	private Set<ShipmentCartItem> shipmentCartItems;

	// Default Fields
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Boolean active;

	@Id
	@Column(name = "SHIPMENT_ID")
	/*@SequenceGenerator(name = "ShipmentSequence", sequenceName = "SEQ_SHIPMENT")
	@GeneratedValue(generator = "ShipmentSequence")*/
	@GeneratedValue
	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	@Column(name = "INVOICE_ID")
	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(name = "SHIPMENT_REFERENCE")
	public String getShipmentReference() {
		return shipmentReference;
	}

	public void setShipmentReference(String shipmentReference) {
		this.shipmentReference = shipmentReference;
	}

	@Column(name = "TRACKING_REFERENCE")
	public String getTrackingReference() {
		return trackingReference;
	}

	public void setTrackingReference(String trackingReference) {
		this.trackingReference = trackingReference;
	}

	@Column(name = "SHIPMENT_DATE")
	public Timestamp getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Timestamp shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	@Column(name = "SHIPMENT_WEIGHT")
	public BigDecimal getShipmentWeight() {
		return shipmentWeight;
	}
	
	public void setShipmentWeight(BigDecimal shipmentWeight) {
		this.shipmentWeight = shipmentWeight;
	}
	
	@Column(name = "SHIPMENT_WEIGHT_UOM")
	public String getShipmentWeightUOM() {
		return shipmentWeightUOM;
	}

	public void setShipmentWeightUOM(String shipmentWeightUOM) {
		this.shipmentWeightUOM = shipmentWeightUOM;
	}

	@OneToOne
	@JoinColumn(name = "SHIPMENT_STATUS_ID")
	public ShipmentStatus getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(ShipmentStatus shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	@OneToOne
	@JoinColumn(name = "BRANCH_ID")
	public BranchPlant getBranchPlant() {
		return branchPlant;
	}

	public void setBranchPlant(BranchPlant branchPlant) {
		this.branchPlant = branchPlant;
	}

	
	@OneToOne 
	@JoinColumn(name = "CHILD_CART_ID")
	public ChildCart getChildCart() {
		return childCart;
	}

	public void setChildCart(ChildCart childCart) {
		this.childCart = childCart;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToMany(mappedBy="shipment", fetch=FetchType.LAZY)
	public Set<ShipmentCartItem> getShipmentCartItems() {
		return shipmentCartItems;
	}

	public void setShipmentCartItems(Set<ShipmentCartItem> shipmentCartItems) {
		this.shipmentCartItems = shipmentCartItems;
	}

	 
	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", invoiceId="
				+ invoiceId + ", shipmentReference=" + shipmentReference
				+ ", trackingReference=" + trackingReference
				+ ", shipmentDate=" + shipmentDate + ", shipmentWeight="
				+ shipmentWeight + ", shipmentWeightUOM=" + shipmentWeightUOM
				+ ", shipmentStatus=" + shipmentStatus + ", branchPlant="
				+ branchPlant + ", childCart=" + childCart
				+ ", shipmentCartItems=" + shipmentCartItems + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", active="
				+ active + "]";
	}

}
