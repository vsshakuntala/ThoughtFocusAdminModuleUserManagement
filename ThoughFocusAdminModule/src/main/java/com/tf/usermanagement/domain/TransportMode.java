package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * 
 * The persistent class for the TRANSPORT_MODE database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name="TRANSPORT_MODE")
@SqlResultSetMapping(name="carrierTransportModeMap",
entities={
    @EntityResult(entityClass=Carrier.class,
    		fields = {
	        @FieldResult(name = "carrierId", column = "CARRIER_ID"),
	        @FieldResult(name = "carrierReference", column = "CARRIER_REFERENCE"),
	        @FieldResult(name = "carrierDescription", column = "CARRIER_DESCRIPTION")
    }),
    @EntityResult(entityClass=TransportMode.class,
    		fields = {
	        @FieldResult(name = "transportModeId", column = "TRANSPORT_MODE_ID"),
	        @FieldResult(name = "transportModeReference", column = "TRANSPORT_MODE_REFERENCE"),
	        @FieldResult(name = "transportDescription", column = "TRANSPORT_MODE_DESCRIPTION"),
	        @FieldResult(name = "deliveryTime", column = "DELIVERY_TIME")
})
})
public class TransportMode implements Serializable,Comparable<TransportMode> {

	private static final long serialVersionUID = 1L;
	
	private Integer transportModeId;
	private String transportModeReference;
	private String transportDescription;
	private String deliveryTime;
	private Set<Cart> carts;

	@Id
	@Column(name="TRANSPORT_MODE_ID")
	public Integer getTransportModeId() {
		return transportModeId;
	}
	
	public void setTransportModeId(Integer transportModeId) {
		this.transportModeId = transportModeId;
	}

	@Column(name="TRANSPORT_MODE_REFERENCE")
	public String getTransportModeReference() {
		return transportModeReference;
	}

	public void setTransportModeReference(String transportModeReference) {
		this.transportModeReference = transportModeReference;
	}
	
	@Column(name="TRANSPORT_MODE_DESCRIPTION")
		public String getTransportDescription() {
		return transportDescription;
	}

	public void setTransportDescription(String transportDescription) {
		this.transportDescription = transportDescription;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	@Column(name="DELIVERY_TIME")
	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}
	
	@OneToMany(mappedBy="transportMode")
	public Set<Cart> getCarts() {
		return carts;
	}

	@Override
	public int compareTo(TransportMode o) {
		return this.transportDescription.compareTo(o.getTransportDescription());
	}

	@Override
	public int hashCode(){
		return this.transportModeId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof TransportMode){
			TransportMode tm=(TransportMode)obj;
			result = (tm.getTransportModeId().equals(this.transportModeId));
		}
		return result;
	}

}
