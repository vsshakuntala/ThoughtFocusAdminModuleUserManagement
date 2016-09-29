package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VW_MACHINE_CATALOG")
public class MachineCatalog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private MachineCatalogPK id;

	private Long userId;
	private Long catalogid;
	
	private Catalog catalog;
	
	
	@EmbeddedId
	public MachineCatalogPK getId() {
		return id;
	}
	public void setId(MachineCatalogPK id) {
		this.id = id;
	}
	@Column(name = "USER_ID",insertable=false, updatable=false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "CATALOG_ID",insertable=false, updatable=false)
	public Long getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(Long catalogid) {
		this.catalogid = catalogid;
	}
	
	/*@Column(name = "CATALOG_ID", insertable=false, updatable=false)
	*/
	
	
	
	@ManyToOne
	@JoinColumn(name="CATALOG_ID" , insertable=false,updatable=false)
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
}
