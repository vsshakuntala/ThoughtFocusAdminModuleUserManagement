package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the GROUPS database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="GROUPS")
public class Group implements Serializable/*, IAuditLog*/{

	private static final long serialVersionUID = 1L;

	private Long groupId;
	private String groupName;
	private String description;
	private Integer groupTypeId;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Boolean active;
	private Set<GroupCatalog> groupCatalogs;
	private Set<GroupCustomer> groupCustomers;
	private Set<UserGroup> userGroups;
	//private Set<Role> roles;
	private Set<Catalog> catalogs;
	private Set<GroupHierarchy> groupHierarchy;
	
	//private User user;
	
	@Id
	@Column(name = "GROUP_ID")
	/*@SequenceGenerator(name = "GroupsSequence", sequenceName = "GROUPS_SEQ")
	@GeneratedValue(generator="GroupsSequence")*/
	@GeneratedValue
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name="GROUP_NAME")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	@Column(name="GROUP_TYPE_ID")
	public Integer getGroupTypeId() {
		return groupTypeId;
	}
	public void setGroupTypeId(Integer groupTypeId) {
		this.groupTypeId = groupTypeId;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public Set<GroupCatalog> getGroupCatalogs() {
		return groupCatalogs;
	}
	public void setGroupCatalogs(Set<GroupCatalog> groupCatalogs) {
		this.groupCatalogs = groupCatalogs;
	}
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public Set<GroupCustomer> getGroupCustomers() {
		return groupCustomers;
	}
	public void setGroupCustomers(Set<GroupCustomer> groupCustomers) {
		this.groupCustomers = groupCustomers;
	}
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	
/*	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "GROUP_CATALOG", joinColumns = { @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATALOG_ID", referencedColumnName = "CATALOG_ID") })
	public Set<Catalog> getCatalogs() {
		return catalogs;
	}
	
	public void setCatalogs(Set<Catalog> catalogs) {
		this.catalogs = catalogs;
	} 
	
	
	
	/*@ManyToOne
	@JoinColumn(name = "USER_ID", insertable=false, updatable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "GROUP_ID", insertable=false, updatable=false)
	public Set<GroupHierarchy> getGroupHierarchy() {
		return groupHierarchy;
	}
	public void setGroupHierarchy(Set<GroupHierarchy> groupHierarchy) {
		this.groupHierarchy = groupHierarchy;
	}
	
/*	public Set<GroupHierarchy> fetchActiveParents() {
		Set<GroupHierarchy> activeParents = new HashSet<GroupHierarchy>();

		for (GroupHierarchy gGroupHierarchy : getGroupHierarchy()) {
			if (gGroupHierarchy.getActive()){
				activeParents.add(gGroupHierarchy);
			}
		}
		return activeParents;
	}*/
	/**
	 * @return Group associated active Customer(s).
	 */
	public Set<Customer> fetchActiveCustomers() {
		Set<Customer> activeCustomers = new HashSet<Customer>();

		for (GroupCustomer userCustomer : getGroupCustomers()) {
			if (userCustomer.getActive()){
				Customer customer = userCustomer.getCustomer();
				if (customer.getActive()){
					activeCustomers.add(customer);
				}
			}
		}
		return activeCustomers;
	}
	
	/**
	 * @return Group associated active Catalog(s).
	 */
	public Set<Catalog> fetchActiveCatalogs() {
		Set<Catalog> activeCats = new HashSet<Catalog>();

		for (GroupCatalog groupCatalog : getGroupCatalogs()) {
			if (groupCatalog.getActive())
				for (Catalog catalog : getCatalogs()) {
					if ((groupCatalog.getId().getCatalogId() == catalog.getCatalogId()) && catalog.getActive()) {
						activeCats.add(catalog);
						break;
					}
				}
		}

		return activeCats;
	}
/*	@Transient
	@Override
	public Serializable getCompositeKey() {
		return null;
	}
	
	@Transient
	@Override
	public Long getId(){
		return this.groupId;
	}
 
	@Transient
	@Override
	public String getLogDetail(){
		StringBuilder sb = new StringBuilder();
		sb.append(" Group Id : ").append(groupId)
		.append(" Group Name : ").append(groupName)
		.append(" Group Desc : ").append(description);
		
		logger.debug("*************************************************"+sb);
 
		return sb.toString();
	}*/
	
	@Override
	public String toString() {
		return "Group [id=" + groupId + ", groupName=" + groupName + ", description="
				+ description + ", groupTypeId=" + groupTypeId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+active+", active"+ modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}	
}

