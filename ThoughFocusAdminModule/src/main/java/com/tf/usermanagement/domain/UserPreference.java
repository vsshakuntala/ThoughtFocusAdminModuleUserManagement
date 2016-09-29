package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the USER_PREFERENCE database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="USER_PREFERENCE")
public class UserPreference implements Serializable {
	private static final long serialVersionUID = 1L;
//	private Long userId;
	private Long userPreferenceId;
	private Integer defaultView;
	private Language language;
	private Boolean preload3d;
	private User user;
	private String bomGridLayout;
	private Boolean autoplayAudio;
	private Integer carrierId;
	private Integer transportModeId;
	private Address billToAddress;
	private Address shipToAddress;
	private Boolean loadOfflineQuotesOrders;
	private Integer loadOfflineQuoteAddressType;
	private Integer viewQuotesOrdersAddressType;
	private Integer quoteOrderFilterType;
	//private Long intermediaryId;
	private Long languageId;
	
	//private Intermediary intermediary;
	
    public UserPreference() {
    }
    
	public UserPreference(Long userPreferenceId, Integer defaultView,
			Language language, Boolean preload3d, User user,
			String bomGridLayout, Boolean autoplayAudio, Integer carrierId,
			Integer transportModeId, Address billToAddress,
			Address shipToAddress, Boolean loadOfflineQuotesOrders,
			Integer loadOfflineQuoteAddressType,
			Integer viewQuotesOrdersAddressType,Long languageId) {
		super();
		this.userPreferenceId = userPreferenceId;
		this.defaultView = defaultView;
		this.language = language;
		this.preload3d = preload3d;
		this.user = user;
		this.bomGridLayout = bomGridLayout;
		this.autoplayAudio = autoplayAudio;
		this.carrierId = carrierId;
		this.transportModeId = transportModeId;
		this.billToAddress = billToAddress;
		this.shipToAddress = shipToAddress;
		this.loadOfflineQuotesOrders = loadOfflineQuotesOrders;
		this.loadOfflineQuoteAddressType = loadOfflineQuoteAddressType;
		this.viewQuotesOrdersAddressType = viewQuotesOrdersAddressType;
		this.languageId = languageId;
	}
	

	@Id
	@Column(name="USER_PREFERENCE_ID")
	/*@SequenceGenerator(name = "UserReferenceSequence", sequenceName = "SEQ_USER_PREF")
	@GeneratedValue(generator="UserReferenceSequence")*/
	@GeneratedValue
	public Long getUserPreferenceId() {
		return userPreferenceId;
	}

	public void setUserPreferenceId(Long userPreferenceId) {
		this.userPreferenceId = userPreferenceId;
	}
	
/*	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
*/
	
	
	@Column(name="DEFAULT_VIEW")
	public Integer getDefaultView() {
		return this.defaultView;
	}

	public void setDefaultView(Integer defaultView) {
		this.defaultView = defaultView;
	}


	//bi-directional many-to-one association to Language
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="LANGUAGE_ID",insertable=false,updatable=false)
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	

    @OneToOne
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void setBomGridLayout(String bomGridLayout) {
		this.bomGridLayout = bomGridLayout;
	}

	@Column(name="BOM_GRID_LAYOUT")
	public String getBomGridLayout() {
		return bomGridLayout;
	}


	public void setPreload3d(Boolean preload3d) {
		this.preload3d = preload3d;
	}

	@Column(name="PRELOAD_3D")
	public Boolean getPreload3d() {
		return preload3d;
	}
	
	public void setAutoplayAudio(Boolean autoplayAudio) {
		this.autoplayAudio = autoplayAudio;
	}

	@Column(name="AUTOPLAY_AUDIO")
	public Boolean getAutoplayAudio() {
		return autoplayAudio;
	}

	@Column(name="CARRIER_ID")
	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	@Column(name="TRANSPORT_MODE_ID")
	public Integer getTransportModeId() {
		return transportModeId;
	}

	public void setTransportModeId(Integer transportModeId) {
		this.transportModeId = transportModeId;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BILL_TO_ADDRESS_ID")
	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SHIP_TO_ADDRESS_ID")
	public Address getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	@Column(name="LOAD_OFFLINE_QUOTES_ORDERS")
	public Boolean getLoadOfflineQuotesOrders() {
		return loadOfflineQuotesOrders;
	}

	public void setLoadOfflineQuotesOrders(
			Boolean loadOfflineQuotesOrders) {
		this.loadOfflineQuotesOrders = loadOfflineQuotesOrders;
	}
	
	@Column(name="LOAD_OFFLINE_QUOTE_ADD_TYPE")
	public Integer getLoadOfflineQuoteAddressType() {
		return loadOfflineQuoteAddressType;
	}

	public void setLoadOfflineQuoteAddressType(Integer loadOfflineQuoteAddressType) {
		this.loadOfflineQuoteAddressType = loadOfflineQuoteAddressType;
	}

	@Column(name="VIEW_QUOTE_ORDER_ADD_TYPE")
	public Integer getViewQuotesOrdersAddressType() {
		return viewQuotesOrdersAddressType;
	}

	public void setViewQuotesOrdersAddressType(Integer viewQuotesOrdersAddressType) {
		this.viewQuotesOrdersAddressType = viewQuotesOrdersAddressType;
	}
	

	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	@Column(name="QUOTE_ORDER_FILTER_TYPE_ID")
	public Integer getQuoteOrderFilterType() {
		return quoteOrderFilterType;
	}

	public void setQuoteOrderFilterType(Integer quoteOrderFilterType) {
		this.quoteOrderFilterType = quoteOrderFilterType;
	}

	@Override
	public String toString() {
		return "UserPreference [userPreferenceId=" + userPreferenceId
				+ ", defaultView=" + defaultView + ", language=" + language
				+ ", preload3d=" + preload3d + ", user=" + user
				+ ", bomGridLayout=" + bomGridLayout + ", autoplayAudio="
				+ autoplayAudio + ", carrierId=" + carrierId
				+ ", transportModeId=" + transportModeId + ", billToAddress="
				+ billToAddress + ", shipToAddress=" + shipToAddress
				+ ", loadOfflineQuotesOrders=" + loadOfflineQuotesOrders
				+ ", loadOfflineQuoteAddressType="
				+ loadOfflineQuoteAddressType
				+ ", viewQuotesOrdersAddressType="
				+ viewQuotesOrdersAddressType + ", intermediaryId="
				+ languageId + "]";
	}

}