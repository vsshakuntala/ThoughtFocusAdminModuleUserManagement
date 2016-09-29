package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * The persistent class for the CART database table.
 * 
 * @author Arvind.C
 * 
 */

@Entity
/*@SqlResultSetMapping(name="allPurchaseRoleCarts",
		entities=@EntityResult(entityClass=Cart.class),
		columns = @ColumnResult(name = "expiryFlag"))*/
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long cartId;
	private String cartName;
	private CartStatus cartStatus;
	private Boolean active;
	private User cartOwner;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Date orderDate;
	private Date quoteCreatedDate;
	private Date quoteExpiryDate;
	private String quoteNumber;
	private String customerPurchaseOrderNumber;
	private String orderNumber;
	private Date shipDate;
	private String comments;
	private Address shipToAddress;
	private Address billToAddress;
	private BigDecimal subtotalAmount;
	private BigDecimal totalAmount;
	private Integer expiryLevel;
	private String currencyCode;
	private Boolean isQuoteEditable;
	private Boolean isQuoteOrderable;
	private Boolean isQuoteCancellable;
	private User quotedBy;
	private User orderedBy;
	private BigDecimal taxAmount;
	private BigDecimal taxRate;
	private BigDecimal totalMSRP;
	
	private String taxAmountStr;
	private String taxRateStr;
	private String totalMSRPStr;
	private String subtotalAmountStr;
	private String totalAmountStr;
	
	private Set<CartItem> cartItems=new HashSet<CartItem>();

	private Carrier carrier;
	private TransportMode transportMode;
	private String deliveryInstructions1;
	private String deliveryInstructions2;
	private Set<UserGroup> userGroups;
	//private PaymentType paymentMethod;
	private CarrierBillingOption carrierBillingOption;
	
	private Boolean offlineQuoteFlag;
	//private Boolean offlineOrderFlag;
	private String serialNumber;
	private Boolean machineBreakdown;
	private Boolean partialShipment;
	private String customerCarrierReference;

	private Date offlineLastUpdatedDate;
	
	@Column(name = "OFFLINE_LAST_UPDATED_DATE")
	public Date getOfflineLastUpdatedDate() {
		return offlineLastUpdatedDate;
	}

	public void setOfflineLastUpdatedDate(Date offlineLastUpdatedDate) {
		this.offlineLastUpdatedDate = offlineLastUpdatedDate;
	}


	private String contactReference;
	private String contactFullName;
	
	private Integer branchId;
	
	
	public Cart() {
	}

	

	/**
	 * This constructor has been created for JPA multiselect criteria queries
	 * and is being used in CartRepository.getCartsForUser(..).
	 * 
	 * @param cartId
	 * @param cartName
	 * @param cartOwner
	 * @param modifiedDate
	 *            Its type should be Timestamp but I don't know right now why
	 *            JPA is not able to call this constructor with
	 *            "public Cart(Long cartId, String cartName, User cartOwner, Object modifiedDate)"
	 *            this signature so I kept this like the way it is and I casted
	 *            modifiedDate to Timestamp later.
	 */
	public Cart(Long cartId, String cartName, User cartOwner,
			Object modifiedDate, CartStatus cartStatus) {
		this.cartId = cartId;
		this.cartName = cartName;
		this.cartOwner = cartOwner;
		this.modifiedDate = (Date) modifiedDate;
		this.cartStatus = cartStatus;
	}

	@Id
	@Column(name = "CART_ID")
/*	@SequenceGenerator(name = "CartSequence", sequenceName = "NEW_CART_SEQ")
	@GeneratedValue(generator="CartSequence")*/
	@GeneratedValue
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	@Column(name = "CART_NAME")
	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	@ManyToOne
	@JoinColumn(name = "CART_STATUS_ID")
	public CartStatus getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(CartStatus cartStatus) {
		this.cartStatus = cartStatus;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne
	@JoinColumn(name = "CART_OWNER")
	public User getCartOwner() {
		return cartOwner;
	}

	public void setCartOwner(User cartOwner) {
		this.cartOwner = cartOwner;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name="QUOTE_CREATED_DATE")
	public Date getQuoteCreatedDate() {
		return quoteCreatedDate;
	}

	public void setQuoteCreatedDate(Date quoteCreatedDate) {
		this.quoteCreatedDate = quoteCreatedDate;
	}

	@Column(name="QUOTE_EXPIRY_DATE")
	public Date getQuoteExpiryDate() {
		return quoteExpiryDate;
	}

	public void setQuoteExpiryDate(Date quoteExpiryDate) {
		this.quoteExpiryDate = quoteExpiryDate;
	}

	@Column(name="QUOTE_NUMBER")
	public String getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	@Column(name="CUSTOMER_PURCHASE_ORDER_NUMBER")
	public String getCustomerPurchaseOrderNumber() {
		return customerPurchaseOrderNumber;
	}

	public void setCustomerPurchaseOrderNumber(String customerPurchaseOrderNumber) {
		this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
	}

	@Column(name="ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name="SHIP_DATE")
	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToOne
	@JoinColumn(name = "SHIP_TO_ADDRESS_ID")
	public Address getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	@OneToOne
	@JoinColumn(name = "BILL_TO_ADDRESS_ID")
	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	@Column(name="SUBTOTAL_AMOUNT")
	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	@Column(name="TOTAL_AMOUNT")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Transient
	public Integer getExpiryLevel() {
		return expiryLevel;
	}

	public void setExpiryLevel(Integer expiryLevel) {
		this.expiryLevel = expiryLevel;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name="CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	@Transient
	public Boolean getIsQuoteEditable() {
		return isQuoteEditable;
	}

	public void setIsQuoteEditable(Boolean isQuoteEditable) {
		this.isQuoteEditable = isQuoteEditable;
	}

	@Transient
	public Boolean getIsQuoteOrderable() {
		return isQuoteOrderable;
	}

	public void setIsQuoteOrderable(Boolean isQuoteOrderable) {
		this.isQuoteOrderable = isQuoteOrderable;
	}

	@Transient
	public Boolean getIsQuoteCancellable() {
		return isQuoteCancellable;
	}

	public void setIsQuoteCancellable(Boolean isQuoteCancellable) {
		this.isQuoteCancellable = isQuoteCancellable;
	}

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@ManyToOne
	@JoinColumn(name = "CARRIER_ID")
	public Carrier getCarrier(){
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	@ManyToOne
	@JoinColumn(name = "TRANSPORT_MODE_ID")
	public TransportMode getTransportMode(){
		return transportMode;
	}

	public void setTransportMode(TransportMode transportMode) {
		this.transportMode = transportMode;
	}

	@Column(name="DELIVERY_INSTRUCTIONS1")
	public String getDeliveryInstructions1() {
		return deliveryInstructions1;
	}

	public void setDeliveryInstructions1(String deliveryInstructions1) {
		this.deliveryInstructions1 = deliveryInstructions1;
	}

	@Column(name="DELIVERY_INSTRUCTIONS2")
	public String getDeliveryInstructions2() {
		return deliveryInstructions2;
	}

	public void setDeliveryInstructions2(String deliveryInstructions2) {
		this.deliveryInstructions2 = deliveryInstructions2;
	}

	@OneToMany
	@JoinColumn(name="USER_ID", referencedColumnName="CART_OWNER")
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	
	@ManyToOne
	@JoinColumn(name = "QUOTED_BY")
	public User getQuotedBy() {
		return quotedBy;
	}

	public void setQuotedBy(User quotedBy) {
		this.quotedBy = quotedBy;
	}

	@ManyToOne
	@JoinColumn(name = "ORDERED_BY")
	public User getOrderedBy() {
		return orderedBy;
	}

	@Column(name="OFFLINE_QUOTE_FLAG")
	public Boolean getOfflineQuoteFlag() {
		return offlineQuoteFlag;
	}

	public void setOfflineQuoteFlag(Boolean offlineQuoteFlag) {
		this.offlineQuoteFlag = offlineQuoteFlag;
	}

	/*@Column(name="OFFLINE_ORDER_FLAG")
	public Boolean getOfflineOrderFlag() {
		return offlineOrderFlag;
	}

	public void setOfflineOrderFlag(Boolean offlineOrderFlag) {
		this.offlineOrderFlag = offlineOrderFlag;
	}
*/
	@Column(name="SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setOrderedBy(User orderedBy) {
		this.orderedBy = orderedBy;
	}

	// will not merge any inner object
	public void mergeCart(Cart cart) {
		this.cartName = cart.cartName;
		this.modifiedBy = cart.modifiedBy;
		this.modifiedDate = new java.sql.Timestamp(
				new java.util.Date().getTime());
	}
	/*
	@ManyToOne
	@JoinColumn(name = "PAYMENT_TYPE_ID")
	public PaymentType getPaymentMethod(){
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentType paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	*/
	@ManyToOne
	@JoinColumn(name = "CARRIER_BILLING_OPTION_ID")
	public CarrierBillingOption getCarrierBillingOption() {
		return carrierBillingOption;
	}

	public void setCarrierBillingOption(CarrierBillingOption carrierBillingOption) {
		this.carrierBillingOption = carrierBillingOption;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartName=" + cartName
				+ ", active=" + active + "]";
	}

	@Column(name="MACHINE_BREAKDOWN")
	public Boolean getMachineBreakdown() {
		return machineBreakdown;
	}

	public void setMachineBreakdown(Boolean machineBreakdown) {
		this.machineBreakdown = machineBreakdown;

	}
	
	@Column(name="PARTIAL_SHIPMENT")
	public Boolean getPartialShipment() {
		return partialShipment;
	}

	public void setPartialShipment(Boolean partialShipment) {
		this.partialShipment = partialShipment;
	}
	
	@Column(name="CONTACT_REFERENCE")
	public String getContactReference() {
		return contactReference;
	}

	public void setContactReference(String contactReference) {
		this.contactReference = contactReference;
	}

	@Column(name="CONTACT_FULL_NAME")
	public String getContactFullName() {
		return contactFullName;
	}

	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}

	@Column(name="CUSTOMER_CARRIER_REFERENCE")
	public String getCustomerCarrierReference() {
		return customerCarrierReference;
	}

	public void setCustomerCarrierReference(String customerCarrierReference) {
		this.customerCarrierReference = customerCarrierReference;
	}
	
	@Column(name="BRANCH_ID")
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	@Column(name="TAX_AMOUNT")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name="TAX_RATE")
	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	@Column(name="TOTAL_MSRP")
	public BigDecimal getTotalMSRP() {
		return totalMSRP;
	}

	public void setTotalMSRP(BigDecimal totalMSRP) {
		this.totalMSRP = totalMSRP;
	}

	
	@Transient
	public String getTaxAmountStr() {
		return taxAmountStr;
	}

	public void setTaxAmountStr(String taxAmountStr) {
		this.taxAmountStr = taxAmountStr;
	}
	
	@Transient
	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}
	
	@Transient
	public String getTotalMSRPStr() {
		return totalMSRPStr;
	}

	public void setTotalMSRPStr(String totalMSRPStr) {
		this.totalMSRPStr = totalMSRPStr;
	}
	
	@Transient
	public String getSubtotalAmountStr() {
		return subtotalAmountStr;
	}

	public void setSubtotalAmountStr(String subtotalAmountStr) {
		this.subtotalAmountStr = subtotalAmountStr;
	}
	@Transient
	public String getTotalAmountStr() {
		return totalAmountStr;
	}

	public void setTotalAmountStr(String totalAmountStr) {
		this.totalAmountStr = totalAmountStr;
	}


// Remove Auditlog from cart 19/Oct/2015 by Ganeshan Bug GS-2291
	/*@Override
	@Transient
	public Long getId() {
		return this.getCartId();
	}

	@Override
	@Transient
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Cart Id : ").append(this.getCartId())
		.append(" Cart Owner : ").append(this.getCartOwner())
		.append(" Subtotal : ").append(this.getSubtotalAmount())
		.append(" Total : ").append(this.getTotalAmount());
		
 
		return sb.toString();
	}

	@Override
	@Transient
	public Serializable getCompositeKey() {
		return null;
	}*/
}
