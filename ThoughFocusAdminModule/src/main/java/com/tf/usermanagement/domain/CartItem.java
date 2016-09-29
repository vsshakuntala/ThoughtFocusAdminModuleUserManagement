package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the CART_ITEM database table.
 * 
 * @author Arvind.C
 * 
 */

@Entity
@Table(name = "CART_ITEM")
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cartItemId;
	private Cart cart;
	private Part part;
	private Integer quantity;
	private BigDecimal price;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Boolean active = true;
	private Media media;
	private Integer availableQuantity;
	private Integer leadTime;
	private BigDecimal itemTotalAmount;
	private String currencyCode;
	private byte[] errors;
	private Boolean hasPriceError;
	private Boolean hasInventoryError;
	private Boolean isAmended;
	private String uom;
	private Boolean machineBreakdown;
	private Integer availableQuantityBranch2;
	private Boolean machineBreakdownEmailSent = false;
	private String tariffCode;
	private BigDecimal weight;
	private String weightUOM;
	private BigDecimal priceMSRP;
	private BigDecimal itemTotalMSRP;
	private BigDecimal savedPrice;
	private BigDecimal savedPercentage;
	
	private Organization organization;
	/**
	 * Part number for parts which do not exist in Beyond and were loaded from offline quotes  
	 */
	private String partNumber;
	/**
	 * Description for parts which do not exist in Beyond and were loaded from offline service	
	 */
	private String partDescription;
	private String inventoryMessage;
	
	/**
	 * True if the cart item is a substitute part (not the original part)
	 */
	private Boolean isSubstitutePart=false;
	
	private String comments;
	private Long childCartId;

	@Id
	@Column(name = "CART_ITEM_ID")
	/*@SequenceGenerator(name = "CartItemSequence", sequenceName = "CART_ITEM_SEQ")
	@GeneratedValue(generator="CartItemSequence")*/
	@GeneratedValue
	public Long getCartItemId() {
		return cartItemId;
	}
	
	@Column(name="UOM")
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	@ManyToOne
	@JoinColumn(name = "CART_ID")
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@ManyToOne
	@JoinColumn(name = "PART_ID")
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Column(name="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="PRICE")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@OneToOne
	@JoinColumn(name="MEDIA_ID")
	public Media getMedia() {
		return media;
	}

	@Column(name="AVAILABLE_QUANTITY")
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Column(name="LEAD_TIME")
	public Integer getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	@Column(name="ITEM_TOTAL_AMOUNT")
	public BigDecimal getItemTotalAmount() {
		return itemTotalAmount;
	}

	public void setItemTotalAmount(BigDecimal itemTotalAmount) {
		this.itemTotalAmount = itemTotalAmount;
	}

	@Column(name="CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setErrors(byte[] errors) {
		this.errors = errors;
	}

	@Column(name="ERRORS")
	public byte[] getErrors() {
		return errors;
	}

	@Transient
	public Boolean getHasPriceError() {
		return hasPriceError;
	}

	public void setHasPriceError(Boolean hasPriceError) {
		this.hasPriceError = hasPriceError;
	}

	@Transient
	public Boolean getHasInventoryError() {
		return hasInventoryError;
	}

	public void setHasInventoryError(Boolean hasInventoryError) {
		this.hasInventoryError = hasInventoryError;
	}

	public void setIsAmended(Boolean isAmended) {
		this.isAmended = isAmended;
	}

	@Transient
	public Boolean getIsAmended() {
		return isAmended;
	}

	@Column(name="MACHINE_BREAKDOWN")
	public Boolean getMachineBreakdown() {
		return machineBreakdown;
	}

	public void setMachineBreakdown(Boolean machineBreakdown) {
		this.machineBreakdown = machineBreakdown;

	}
	
	@Column(name="AVAILABLE_QUANTITY_BRANCH2")
	public Integer getAvailableQuantityBranch2() {
		return availableQuantityBranch2;
	}

	public void setAvailableQuantityBranch2(Integer availableQuantityBranch2) {
		this.availableQuantityBranch2 = availableQuantityBranch2;
	}
	
	@Column(name="PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name="PART_DESCRIPTION")
	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	
	public void setIsSubstitutePart(Boolean isSubstitutePart) {
		this.isSubstitutePart = isSubstitutePart;
	}

	@Transient
	public String getInventoryMessage() {
		return inventoryMessage;
	}

	public void setInventoryMessage(String inventoryMessage) {
		this.inventoryMessage = inventoryMessage;
	}

	@Transient
	public Boolean getIsSubstitutePart() {
		return isSubstitutePart;
	}

	@Column(name="MACHINE_BREAKDOWN_EMAIL_SEND")
	public Boolean getMachineBreakdownEmailSent() {
		return machineBreakdownEmailSent;
	}

	public void setMachineBreakdownEmailSent(Boolean machineBreakdownEmailSent) {
		this.machineBreakdownEmailSent = machineBreakdownEmailSent;
	}

	public void mergeCartItem(CartItem cartItem) {
		this.quantity = cartItem.quantity;
		this.modifiedBy = cartItem.modifiedBy;
		this.modifiedDate = new java.sql.Timestamp(
				new java.util.Date().getTime());
	}

	@Column(name="TARIFF_CODE")
	public String getTariffCode() {
		return tariffCode;
	}
	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}
	
	@Column(name="WEIGHT")
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	@Column(name="WEIGHT_UOM")
	public String getWeightUOM() {
		return weightUOM;
	}

	public void setWeightUOM(String weightUOM) {
		this.weightUOM = weightUOM;
	}
	
	@Column(name="MSRP_PRICE")
	public BigDecimal getPriceMSRP() {
		return priceMSRP;
	}

	public void setPriceMSRP(BigDecimal priceMSRP) {
		this.priceMSRP = priceMSRP;
	}
	
	@Column(name="ITEM_TOTAL_MSRP")
	public BigDecimal getItemTotalMSRP() {
		return itemTotalMSRP;
	}

	public void setItemTotalMSRP(BigDecimal itemTotalMSRP) {
		this.itemTotalMSRP = itemTotalMSRP;
	}

	@OneToOne
	@JoinColumn(name = "ORGANIZATION_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Transient
	public BigDecimal getSavedPrice() {
		return savedPrice;
	}

	public void setSavedPrice(BigDecimal savedPrice) {
		this.savedPrice = savedPrice;
	}

	@Transient
	public BigDecimal getSavedPercentage() {
		return savedPercentage;
	}

	public void setSavedPercentage(BigDecimal savedPercentage) {
		this.savedPercentage = savedPercentage;
	}

	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="CHILD_CART_ID")
	public Long getChildCartId() {
		return childCartId;
	}

	public void setChildCartId(Long childCartId) {
		this.childCartId = childCartId;
	}
	

	@Override
	public int hashCode(){
		if(null != this.cartItemId)
		return this.cartItemId.hashCode();
		else return 1;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof CartItem){
			CartItem a=(CartItem)obj;
			//Match cartItemId and part because some sets have
			//multiple items with cartItemId 0, but part will be different
			if(null !=a.part && null !=this.cartItemId){
			result = (a.getCartItemId().equals(this.cartItemId) 
					&& a.getPart().equals(this.part));
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", part=" + part
				+ ", quantity=" + quantity + ", price=" + price + ", active="
				+ active + ", media=" + media + ", availableQuantity="
				+ availableQuantity + ", leadTime=" + leadTime
				+ ", itemTotalAmount=" + itemTotalAmount + ", currencyCode="
				+ currencyCode + ", hasPriceError=" + hasPriceError
				+ ", hasInventoryError=" + hasInventoryError + ", isAmended="
				+ isAmended+", Msg="+inventoryMessage + "]";
	}

}
