package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
/**
 * The persistent class for the CATALOG database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
public class Catalog implements Serializable,Comparable<Catalog> {

	private static final long serialVersionUID = 1L;
	private Long catalogId;
	private String catalogName;
	private String catalogEngineeringDescription;
	private String catalogCommercialDescription;
	private Customer customer;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Category category;
	private Boolean active;
	private Set<Bom> boms;
	private Set<User> users;
	//Represents the image for the machine
	private Media media;
	private Integer catalogView;
	private String availableViews;
	private Set<UserCatalog> userCatalogs;
	private Set<GroupCatalog> groupCatalogs;
	private Boolean hasLegacyPart;
	private Boolean useLegacyPart;
	private Address address;
	private Boolean hasAftermarketItem;
	private String catalogReference;
	private String model;
	private String dataSource;
	//Represents other associated media for this catalog
	private Set<CatalogMedia> catalogMedias;
	
	private Set<MachineCatalog> machineCatalogs;
	
	//private Integer organizationId;
	
	private Map<String, String> organizationConfigMap;
	private Organization organization;
	
	
	private String customField1;
	private String customField2;

	
	
	public Catalog() {
		organizationConfigMap=new HashMap<String, String>();
	}
	
	public Catalog(Long catalogId, String catalogName, String catalogReference) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.catalogReference = catalogReference;
	}


	@Column(name="DATA_SOURCE")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Id
	@Column(name="CATALOG_ID")
	@GeneratedValue
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	@Column(name="CATALOG_NAME")
	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	@Column(name="CATALOG_ENGINEERING_DESC")
	public String getCatalogEngineeringDescription() {
		return catalogEngineeringDescription;
	}

	public void setCatalogEngineeringDescription(
			String catalogEngineeringDescription) {
		this.catalogEngineeringDescription = catalogEngineeringDescription;
	}

	@Column(name="CATALOG_COMMERCIAL_DESC")
	public String getCatalogCommercialDescription() {
		return catalogCommercialDescription;
	}

	public void setCatalogCommercialDescription(
			String catalogCommercialDescription) {
		this.catalogCommercialDescription = catalogCommercialDescription;
	}

	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATALOG_CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(mappedBy="catalog")
	public Set<Bom> getBoms() {
		return boms;
	}

	public void setBoms(Set<Bom> boms) {
		this.boms = boms;
	}

	@ManyToMany(mappedBy="catalogs")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@OneToOne
	@JoinColumn(name="MEDIA_ID")
	public Media getMedia() {
		return media;
	}

	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	public Set<UserCatalog> getUserCatalogs() {
		return userCatalogs;
	}

	public void setUserCatalogs(Set<UserCatalog> userCatalogs) {
		this.userCatalogs = userCatalogs;
	}
	
	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	public Set<GroupCatalog> getGroupCatalogs() {
		return groupCatalogs;
	}

	public void setGroupCatalogs(Set<GroupCatalog> groupCatalogs) {
		this.groupCatalogs = groupCatalogs;
	}
	
	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	public Set<MachineCatalog> getMachineCatalogs() {
		return machineCatalogs;
	}

	public void setMachineCatalogs(Set<MachineCatalog> machineCatalogs) {
		this.machineCatalogs = machineCatalogs;
	}

	@Override
	public String toString() {
		return "Catalog [catalogId=" + catalogId + ", catalogName="
				+ catalogName + ", active=" + active + "]";
	}

	@Column(name="CATALOG_VIEW")
	public Integer getCatalogView() {
		return catalogView;
	}

	public void setCatalogView(Integer catalogView) {
		this.catalogView = catalogView;
	}

	@Column(name="AVAILABLE_VIEWS")
	public String getAvailableViews() {
		return availableViews;
	}

	public void setAvailableViews(String availableViews) {
		this.availableViews = availableViews;
	}

	public void setHasLegacyPart(Boolean hasLegacyPart) {
		this.hasLegacyPart = hasLegacyPart;
	}

	@Column(name="HAS_LEGACY_PART")
	public Boolean getHasLegacyPart() {
		return hasLegacyPart;
	}

	public void setUseLegacyPart(Boolean useLegacyPart) {
		this.useLegacyPart = useLegacyPart;
	}

	@Column(name="USE_LEGACY_PART")
	public Boolean getUseLegacyPart() {
		return useLegacyPart;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID")
	public Address getAddress() {
		return address;
	}

	public void setHasAftermarketItem(Boolean hasAftermarketItem) {
		this.hasAftermarketItem = hasAftermarketItem;
	}

	@Column(name="HAS_AFTERMARKET_ITEM")
	public Boolean getHasAftermarketItem() {
		return hasAftermarketItem;
	}

	@Column(name="CATALOG_REFERENCE")
	public String getCatalogReference() {
		return catalogReference;
	}

	public void setCatalogReference(String catalogReference) {
		this.catalogReference = catalogReference;
	}

	@Column(name="MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
	public Set<CatalogMedia> getCatalogMedias() {
		return catalogMedias;
	}

	public void setCatalogMedias(Set<CatalogMedia> catalogMedias) {
		this.catalogMedias = catalogMedias;
	}
	
	
	/*@Column(name="ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}*/


	@OneToOne
	@JoinColumn(name="ORGANIZATION_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@Column(name="CUSTOM_FIELD_1")
	public String getCustomField1() {
		return customField1;
	}

	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}
	
	@Column(name="CUSTOM_FIELD_2")
	public String getCustomField2() {
		return customField2;
	}

	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	@Transient
	public Map<String, String> getOrganizationConfigMap() {
		for(OrganizationConfig oc: this.organization.getOrganizationConfigs()){
			organizationConfigMap.put(oc.getId().getConfigName(), oc.getConfigValue());
		}
		return organizationConfigMap;
	}
	public void setOrganizationConfigMap(Map<String, String> organizationConfigMap) {
		this.organizationConfigMap = organizationConfigMap;
	}
	
	@Override
	public int compareTo(Catalog o) {
		return this.catalogName.compareTo(o.getCatalogName());
	}

	@Override
	public int hashCode(){
		return this.catalogId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof Catalog){
			Catalog cat=(Catalog)obj;
			result = (cat.getCatalogId().equals(this.catalogId));
		}
		return result;
	}
}
