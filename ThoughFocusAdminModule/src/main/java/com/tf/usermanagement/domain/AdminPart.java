package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the PARTS database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "PART")
public class AdminPart implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long partId;
	private Boolean active;
	private Boolean assembly;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private String partCommercialDescription;
	private String partEngineeringDescription;
	private String partNumber;
	private BigDecimal price;
	private Customer customer;
	private Set<Bom> boms;
	private Set<Bom> childBoms;
	private Set<Media> media;
	private AdminPartDescription partDescription;
	//private Organization organization;
	private Set<CartItem> cartItems;
	// private Map<Long, Long> associatedParentAndCatalogs;
	private Set<OrderablePart> orderableParts;
	private Set<LegacyPart> legacyParts;
	private Boolean madePart;
	private LegacyPart primaryLegacyPart;
	private String mediaId;
	private Boolean isTopic;
	private Set<VendorPart> vendorParts;
	private Set<CrossReferencePart> crossReferenceParts;
	private Set<PartMedia> partMedia;
	//private Set<PartDescription> partDesc;
	private Boolean isStockPart;
	//private Integer organizationId;
	
	

	public AdminPart() {
		// associatedParentAndCatalogs = new HashMap<Long, Long>();
	}

	public AdminPart(Long partId, String partCommercialDescription,
			String partEngineeringDescription, String partNumber,
			BigDecimal price) {
		super();
		// associatedParentAndCatalogs = new HashMap<Long, Long>();
		this.partId = partId;
		// this.partCommercialDescription = partCommercialDescription;
		// this.partEngineeringDescription = partEngineeringDescription;
		this.partNumber = partNumber;
		this.price = price;
	}

	public AdminPart(Long partId, String partNumber) {
		this.partId = partId;
		this.partNumber = partNumber;
	}

	@Id
	@Column(name = "PART_ID")
	@GeneratedValue
	public Long getPartId() {
		return this.partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "ASSEMBLY")
	public Boolean getAssembly() {
		return this.assembly;
	}

	public void setAssembly(Boolean assembly) {
		this.assembly = assembly;
	}

	/*@Column(name = "ORGANIZATION_ID",insertable=false,updatable=false)
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}*/
	
	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Transient
	public String getPartCommercialDescription() {
		return this.partCommercialDescription;
	}

	public void setPartCommercialDescription(String partCommercialDescription) {
		this.partCommercialDescription = partCommercialDescription;
	}

	@Transient
	public String getPartEngineeringDescription() {
		return this.partEngineeringDescription;
	}

	public void setPartEngineeringDescription(String partEngineeringDescription) {
		this.partEngineeringDescription = partEngineeringDescription;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return this.partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// bi-directional one-to-many association to Bom
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentPart", cascade = { CascadeType.ALL })
	public Set<Bom> getChildBoms() {
		return this.childBoms;
	}

	public void setChildBoms(Set<Bom> childBoms) {
		this.childBoms = childBoms;
	}

	@Transient
	public Set<Media> getMedia() {
		return this.media;
	}

	public void setMedia(Set<Media> media) {
		this.media = media;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@OneToMany(mappedBy = "part")
	public Set<Bom> getBoms() {
		return boms;
	}

	public void setBoms(Set<Bom> boms) {
		this.boms = boms;
	}

	/*
	 * @Transient public Map<Long, Long> getAssociatedParentAndCatalogs() {
	 * return associatedParentAndCatalogs; }
	 * 
	 * public void setAssociatedParentAndCatalogs( Map<Long, Long>
	 * associatedParentAndCatalogs) { this.associatedParentAndCatalogs =
	 * associatedParentAndCatalogs; }
	 */

	public void setOrderableParts(Set<OrderablePart> orderableParts) {
		this.orderableParts = orderableParts;
	}

	@OneToMany(mappedBy = "part")
	public Set<OrderablePart> getOrderableParts() {
		return orderableParts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
	@OrderBy("legacyPartNumber ASC")
	public Set<LegacyPart> getLegacyParts() {
		return legacyParts;
	}

	public void setLegacyParts(Set<LegacyPart> legacyParts) {
		this.legacyParts = legacyParts;
	}

	public void setMadePart(Boolean madePart) {
		this.madePart = madePart;
	}

	@Column(name = "MADE_PART")
	public Boolean getMadePart() {
		return madePart;
	}

	@Column(name = "IS_TOPIC")
	public Boolean getIsTopic() {
		return isTopic;
	}

	public void setIsTopic(Boolean isTopic) {
		this.isTopic = isTopic;
	}

	public void setPrimaryLegacyPart(LegacyPart primaryLegacyPart) {
		this.primaryLegacyPart = primaryLegacyPart;
	}

	@Transient
	public LegacyPart getPrimaryLegacyPart() {
		return primaryLegacyPart;
	}

	@Transient
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
	@OrderBy("id.vendorPartNumber ASC")
	public Set<VendorPart> getVendorParts() {
		return vendorParts;
	}

	public void setVendorParts(Set<VendorPart> vendorParts) {
		this.vendorParts = vendorParts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
	@OrderBy("id.crossRefNumber ASC")
	public Set<CrossReferencePart> getCrossReferenceParts() {
		return crossReferenceParts;
	}

	public void setCrossReferenceParts(
			Set<CrossReferencePart> crossReferenceParts) {
		this.crossReferenceParts = crossReferenceParts;
	}

	@OneToOne(mappedBy = "part", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public AdminPartDescription getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(AdminPartDescription partDescription) {
		this.partDescription = partDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
	public Set<PartMedia> getPartMedia() {
		return partMedia;
	}

	public void setPartMedia(Set<PartMedia> partMedia) {
		this.partMedia = partMedia;
	}

	/*@OneToOne
	@JoinColumn(name = "ORGANIZATION_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}*/

	/*@OneToMany
	@JoinColumn(name = "PART_ID")
	public Set<PartDescription> getPartDesc() {
		return partDesc;
	}

	public void setPartDesc(Set<PartDescription> partDesc) {
		this.partDesc = partDesc;
	}*/

	@Column(name = "IS_STOCK_PART")
	public Boolean getIsStockPart() {
		return isStockPart;
	}

	public void setIsStockPart(Boolean isStockPart) {
		this.isStockPart = isStockPart;
	}

	@Override
	public int hashCode() {
		return this.partId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Part) {
			Part p = (Part) obj;
			result = (p.getPartId().equals(this.partId));
		}
		return result;
	}

	public LegacyPart fetchPrimaryLegacyPart() {
		if (legacyParts != null) {
			for (LegacyPart lp : legacyParts) {
				if (lp.getActive() && lp.getPrimaryPart()) {
					// There should be only one primary, return it
					this.primaryLegacyPart = lp;
					return lp;
				}
			}
		}
		this.primaryLegacyPart = null;
		return null;
	}

	@Override
	public String toString() {
		return "Part [partId=" + partId + ", partNumber=" + partNumber
		/*
		 * + ", partCommercialDescription=" + partCommercialDescription +
		 * ", partEngineeringDescription=" + partEngineeringDescription
		 */
		+ ", active=" + active + "]";
	}

}