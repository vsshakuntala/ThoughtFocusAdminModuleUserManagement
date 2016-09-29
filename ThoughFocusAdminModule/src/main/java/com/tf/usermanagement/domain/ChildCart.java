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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * The persistent class for the CHILD_CART database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name = "CHILD_CART")
public class ChildCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long childCartId;
	private Long cartId;
	private String cartName;
	private CartStatus cartStatus;
	private Boolean active;
	private Long onBehalfOfUserId;
	private String onBehalfOfEmail;
	private String onBehalfOfName;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private Date orderDate;
	private Date quoteCreatedDate;
	private Date quoteExpiryDate;
	private String quoteNumber;
	private String customerPurchaseOrderNumber;
	private Date customerPurchaseOrderDate;
	private String orderNumber;
	//private Date shipDate;
	private String comments;
	private Address shipToAddress;
	private String shipToAddressReference;
	private String shipToAddressName;
	private String shipToAddressLine1;
	private String shipToAddressLine2;
	private String shipToAddressLine3;
	private String shipToAddressLine4;
	private String shipToAddressCity;
	private String shipToAddressState;
	private String shipToAddressCountry;
	private String shipToAddressZipCode;
	private String shipToAddressPhone;
	private String shipToAddressFax;
	private Address billToAddress;
	private String billToAddressReference;
	private String billToAddressName;
	private String billToAddressLine1;
	private String billToAddressLine2;
	private String billToAddressLine3;
	private String billToAddressLine4;
	private String billToAddressCity;
	private String billToAddressState;
	private String billToAddressCountry;
	private String billToAddressZipCode;
	private String billToAddressPhone;
	private String billToAddressFax;
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
	private Set<CartItem> cartItems=new HashSet<CartItem>();
	private Carrier carrier;
	private TransportMode transportMode;
	private String deliveryInstructions1;
	private String deliveryInstructions2;
	//private PaymentMethod paymentMethod;
	private CarrierBillingOption carrierBillingOption;
	private Boolean offlineQuoteFlag;
	//private Boolean offlineOrderFlag;
	private String serialNumber;
	private Boolean machineBreakdown;
	private Boolean partialShipment;
	private String customerCarrierReference;
	private Date offlineLastUpdatedDate;
	private String contactReference;
	private String contactFullName;
	private String contactEmail;
	private Integer branchId;
	private Integer organizationId;
	private Boolean isFromCart;
	private Boolean isSameDayShipping;
	private Boolean isQuoteRequested;
	private Integer childCartTypeId;
	private Customer customer;
	private String customerReference;
	private String customerName;
	private Organization organization;
	private Boolean autoApplyCharges;
	private Long salesAreaId;
	
	public ChildCart() {
	}

	@Id
	@Column(name = "CHILD_CART_ID")
	/*@SequenceGenerator(name = "childCartSequence", sequenceName = "SEQ_CHILDCART")
	@GeneratedValue(generator="childCartSequence")*/
	@GeneratedValue
	public Long getChildCartId() {
		return childCartId;
	}

	public void setChildCartId(Long childCartId) {
		this.childCartId = childCartId;
	}

	@Column(name = "CART_ID")
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

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "ON_BEHALF_OF_USER_ID")
	public Long getOnBehalfOfUserId() {
		return onBehalfOfUserId;
	}

	public void setOnBehalfOfUserId(Long onBehalfOfUserId) {
		this.onBehalfOfUserId = onBehalfOfUserId;
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

/*	@Column(name="SHIP_DATE")
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
*/
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
	public void mergeCart(ChildCart cart) {
		this.cartName = cart.cartName;
		this.modifiedBy = cart.modifiedBy;
		this.modifiedDate = new java.sql.Timestamp(
				new java.util.Date().getTime());
	}
	
/*	@ManyToOne
	@JoinColumn(name = "PAYMENT_METHOD_ID")
	public PaymentMethod getPaymentMethod(){
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
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

	
	@Column(name = "OFFLINE_LAST_UPDATED_DATE")
	public Date getOfflineLastUpdatedDate() {
		return offlineLastUpdatedDate;
	}

	public void setOfflineLastUpdatedDate(Date offlineLastUpdatedDate) {
		this.offlineLastUpdatedDate = offlineLastUpdatedDate;
	}
	
	
	@Column(name = "ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "IS_FROM_CART")
	public Boolean getIsFromCart() {
		return isFromCart;
	}

	public void setIsFromCart(Boolean isFromCart) {
		this.isFromCart = isFromCart;
	}

	@Column(name="ON_BEHALF_OF_EMAIL")
	public String getOnBehalfOfEmail() {
		return onBehalfOfEmail;
	}

	public void setOnBehalfOfEmail(String onBehalfOfEmail) {
		this.onBehalfOfEmail = onBehalfOfEmail;
	}

	@Column(name="ON_BEHALF_OF_NAME")
	public String getOnBehalfOfName() {
		return onBehalfOfName;
	}

	public void setOnBehalfOfName(String onBehalfOfName) {
		this.onBehalfOfName = onBehalfOfName;
	}

	@Column(name="SHIP_TO_ADDRESS_NAME")
	public String getShipToAddressName() {
		return shipToAddressName;
	}

	public void setShipToAddressName(String shipToAddressName) {
		this.shipToAddressName = shipToAddressName;
	}

	@Column(name="SHIP_TO_ADDRESS_LINE1")
	public String getShipToAddressLine1() {
		return shipToAddressLine1;
	}

	public void setShipToAddressLine1(String shipToAddressLine1) {
		this.shipToAddressLine1 = shipToAddressLine1;
	}

	@Column(name="SHIP_TO_ADDRESS_LINE2")
	public String getShipToAddressLine2() {
		return shipToAddressLine2;
	}

	public void setShipToAddressLine2(String shipToAddressLine2) {
		this.shipToAddressLine2 = shipToAddressLine2;
	}

	@Column(name="SHIP_TO_ADDRESS_LINE3")
	public String getShipToAddressLine3() {
		return shipToAddressLine3;
	}

	public void setShipToAddressLine3(String shipToAddressLine3) {
		this.shipToAddressLine3 = shipToAddressLine3;
	}

	@Column(name="SHIP_TO_ADDRESS_LINE4")
	public String getShipToAddressLine4() {
		return shipToAddressLine4;
	}

	public void setShipToAddressLine4(String shipToAddressLine4) {
		this.shipToAddressLine4 = shipToAddressLine4;
	}

	@Column(name="SHIP_TO_ADDRESS_CITY")
	public String getShipToAddressCity() {
		return shipToAddressCity;
	}

	public void setShipToAddressCity(String shipToAddressCity) {
		this.shipToAddressCity = shipToAddressCity;
	}

	@Column(name="SHIP_TO_ADDRESS_STATE")
	public String getShipToAddressState() {
		return shipToAddressState;
	}

	public void setShipToAddressState(String shipToAddressState) {
		this.shipToAddressState = shipToAddressState;
	}

	@Column(name="SHIP_TO_ADDRESS_COUNTRY")
	public String getShipToAddressCountry() {
		return shipToAddressCountry;
	}

	public void setShipToAddressCountry(String shipToAddressCountry) {
		this.shipToAddressCountry = shipToAddressCountry;
	}

	@Column(name="SHIP_TO_ADDRESS_ZIP_CODE")
	public String getShipToAddressZipCode() {
		return shipToAddressZipCode;
	}

	public void setShipToAddressZipCode(String shipToAddressZipCode) {
		this.shipToAddressZipCode = shipToAddressZipCode;
	}

	@Column(name="SHIP_TO_ADDRESS_PHONE")
	public String getShipToAddressPhone() {
		return shipToAddressPhone;
	}

	public void setShipToAddressPhone(String shipToAddressPhone) {
		this.shipToAddressPhone = shipToAddressPhone;
	}

	@Column(name="SHIP_TO_ADDRESS_FAX")
	public String getShipToAddressFax() {
		return shipToAddressFax;
	}

	public void setShipToAddressFax(String shipToAddressFax) {
		this.shipToAddressFax = shipToAddressFax;
	}

	@Column(name="BILL_TO_ADDRESS_NAME")
	public String getBillToAddressName() {
		return billToAddressName;
	}

	public void setBillToAddressName(String billToAddressName) {
		this.billToAddressName = billToAddressName;
	}

	@Column(name="BILL_TO_ADDRESS_LINE1")
	public String getBillToAddressLine1() {
		return billToAddressLine1;
	}

	public void setBillToAddressLine1(String billToAddressLine1) {
		this.billToAddressLine1 = billToAddressLine1;
	}

	@Column(name="BILL_TO_ADDRESS_LINE2")
	public String getBillToAddressLine2() {
		return billToAddressLine2;
	}

	public void setBillToAddressLine2(String billToAddressLine2) {
		this.billToAddressLine2 = billToAddressLine2;
	}

	@Column(name="BILL_TO_ADDRESS_LINE3")
	public String getBillToAddressLine3() {
		return billToAddressLine3;
	}

	public void setBillToAddressLine3(String billToAddressLine3) {
		this.billToAddressLine3 = billToAddressLine3;
	}

	@Column(name="BILL_TO_ADDRESS_LINE4")
	public String getBillToAddressLine4() {
		return billToAddressLine4;
	}

	public void setBillToAddressLine4(String billToAddressLine4) {
		this.billToAddressLine4 = billToAddressLine4;
	}

	@Column(name="BILL_TO_ADDRESS_CITY")
	public String getBillToAddressCity() {
		return billToAddressCity;
	}

	public void setBillToAddressCity(String billToAddressCity) {
		this.billToAddressCity = billToAddressCity;
	}

	@Column(name="BILL_TO_ADDRESS_STATE")
	public String getBillToAddressState() {
		return billToAddressState;
	}

	public void setBillToAddressState(String billToAddressState) {
		this.billToAddressState = billToAddressState;
	}

	@Column(name="BILL_TO_ADDRESS_COUNTRY")
	public String getBillToAddressCountry() {
		return billToAddressCountry;
	}

	public void setBillToAddressCountry(String billToAddressCountry) {
		this.billToAddressCountry = billToAddressCountry;
	}

	@Column(name="BILL_TO_ADDRESS_ZIP_CODE")
	public String getBillToAddressZipCode() {
		return billToAddressZipCode;
	}

	public void setBillToAddressZipCode(String billToAddressZipCode) {
		this.billToAddressZipCode = billToAddressZipCode;
	}

	@Column(name="BILL_TO_ADDRESS_PHONE")
	public String getBillToAddressPhone() {
		return billToAddressPhone;
	}

	public void setBillToAddressPhone(String billToAddressPhone) {
		this.billToAddressPhone = billToAddressPhone;
	}

	@Column(name="BILL_TO_ADDRESS_FAX")
	public String getBillToAddressFax() {
		return billToAddressFax;
	}

	public void setBillToAddressFax(String billToAddressFax) {
		this.billToAddressFax = billToAddressFax;
	}

	@Column(name="CUSTOMER_PURCHASE_ORDER_DATE")
	public Date getCustomerPurchaseOrderDate() {
		return customerPurchaseOrderDate;
	}

	public void setCustomerPurchaseOrderDate(Date customerPurchaseOrderDate) {
		this.customerPurchaseOrderDate = customerPurchaseOrderDate;
	}

	@Column(name="CONTACT_EMAIL")
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name="IS_SAME_DAY_SHIPPING")
	public Boolean getIsSameDayShipping() {
		return isSameDayShipping;
	}

	public void setIsSameDayShipping(Boolean isSameDayShipping) {
		this.isSameDayShipping = isSameDayShipping;
	}

	@Column(name="IS_QUOTE_REQUESTED")
	public Boolean getIsQuoteRequested() {
		return isQuoteRequested;
	}

	public void setIsQuoteRequested(Boolean isQuoteRequested) {
		this.isQuoteRequested = isQuoteRequested;
	}

	@Column(name="CHILD_CART_TYPE_ID")
	public Integer getChildCartTypeId() {
		return childCartTypeId;
	}

	public void setChildCartTypeId(Integer childCartTypeId) {
		this.childCartTypeId = childCartTypeId;
	}
	
	
   @OneToOne
   @JoinColumn(name="CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	@OneToOne
	@JoinColumn(name = "ORGANIZATION_ID", insertable=false, updatable=false)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name="SHIP_TO_ADDRESS_REFERENCE")
	public String getShipToAddressReference() {
		return shipToAddressReference;
	}

	public void setShipToAddressReference(String shipToAddressReference) {
		this.shipToAddressReference = shipToAddressReference;
	}

	@Column(name="BILL_TO_ADDRESS_REFERENCE")
	public String getBillToAddressReference() {
		return billToAddressReference;
	}

	public void setBillToAddressReference(String billToAddressReference) {
		this.billToAddressReference = billToAddressReference;
	}

	@Column(name="CUSTOMER_REFERENCE")
	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	@Column(name="CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Column(name="SALES_AREA_ID")
	public Long getSalesAreaId() {
		return salesAreaId;
	}

	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}

	/*	@Override
	@Transient
	public Long getId() {
		return this.getChildCartId();
	}

	@Override
	@Transient
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Child cart Id : ").append(this.getChildCartId())
		.append(" Cart Id : ").append(this.getCartId())
		.append(" On Behalf Of User Id : ").append(this.getOnBehalfOfUserId())
		.append(" Subtotal : ").append(this.getSubtotalAmount())
		.append(" Total : ").append(this.getTotalAmount());
		return sb.toString();
	}

	@Override
	@Transient
	public Serializable getCompositeKey() {
		return null;
	}
*/
	@Override
	public String toString() {
		return "ChildCart [childCartId="+childCartId + ", cartId=" + cartId+ ", active=" + active + "]";
	}

	@Column(name="AUTO_APPLY_CHARGES")
	public Boolean getAutoApplyCharges() {
		return autoApplyCharges;
	}

	public void setAutoApplyCharges(Boolean autoApplyCharges) {
		this.autoApplyCharges = autoApplyCharges;
	}

}
