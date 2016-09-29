package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the GROUP_CATALOG database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="GROUP_CATALOG")
public class GroupCatalog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private GroupCatalogPK id;
	private Boolean active = true;
	private Long groupId;
	private Long catalogId;
	private Long createdBy;
	private Timestamp createdDate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Group group;
	private Catalog catalog;
	//private Set<UserGroup> userGroups;


	public GroupCatalog() {
	}

	@EmbeddedId
	public GroupCatalogPK getId() {
		return this.id;
	}

	public void setId(GroupCatalogPK id) {
		this.id = id;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	@Column(name = "GROUP_ID", insertable=false, updatable=false)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
	@Column(name = "CATALOG_ID", insertable=false, updatable=false)
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	@ManyToOne
	@JoinColumn(name = "GROUP_ID", insertable=false, updatable=false)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	@ManyToOne
	@JoinColumn(name = "CATALOG_ID", insertable=false, updatable=false)
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	/*@OneToMany
	@JoinColumn(name="GROUP_ID", referencedColumnName="GROUP_ID")
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}*/

	@Override
	public String toString() {
		return "GroupCatalog [id=" + id + ", active=" + active + ", userId="
				+ groupId + ", catalogId=" + catalogId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

}

