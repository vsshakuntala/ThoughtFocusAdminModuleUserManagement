package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CATALOG_TREE_XML")
public class CatalogTreeXml implements Serializable{


	private static final long serialVersionUID = 1L;
	private CatalogTreeXmlPK id;
	private String treeXml;
	private String treeJson;
	
	@EmbeddedId
	public CatalogTreeXmlPK getId() {
		return id;
	}
	public void setId(CatalogTreeXmlPK id) {
		this.id = id;
	}
	
	@Column(name="TREE_XML")
	public String getTreeXml() {
		return treeXml;
	}
	public void setTreeXml(String treeXml) {
		this.treeXml = treeXml;
	}
	
	@Column(name="TREE_JSON")
	public String getTreeJson() {
		return treeJson;
	}
	public void setTreeJson(String treeJson) {
		this.treeJson = treeJson;
	}
	
	
	
	
}
