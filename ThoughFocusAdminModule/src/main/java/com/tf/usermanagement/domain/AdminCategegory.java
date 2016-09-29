package com.tf.usermanagement.domain;

public class AdminCategegory {

	private int id;
	private String language;
	private String productCatalogId;
	private String organizationId;
	private String productCategoryId;
	
	
	public AdminCategegory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminCategegory(int id, String language, String productCatalogId,
			String organizationId, String productCategoryId) {
		super();
		this.id = id;
		this.language = language;
		this.productCatalogId = productCatalogId;
		this.organizationId = organizationId;
		this.productCategoryId = productCategoryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProductCatalogId() {
		return productCatalogId;
	}
	public void setProductCatalogId(String productCatalogId) {
		this.productCatalogId = productCatalogId;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	@Override
	public String toString() {
		return "AdminCategegory [id=" + id + ", language=" + language
				+ ", productCatalogId=" + productCatalogId
				+ ", organizationId=" + organizationId + ", productCategoryId="
				+ productCategoryId + "]";
	}
	
}
