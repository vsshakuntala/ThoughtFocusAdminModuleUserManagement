package com.tf.usermanagement.domain;

public class AdminCatalog {
	private int id;
	private String language;
	private String productCatalogId;
	private String organizationId;

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

	public AdminCatalog(int id, String language, String productCatalogId,
			String organizationId) {
		super();
		this.id = id;
		this.language = language;
		this.productCatalogId = productCatalogId;
		this.organizationId = organizationId;
	}

	public AdminCatalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdminCatalog [id=" + id + ", language=" + language
				+ ", productCatalogId=" + productCatalogId
				+ ", organizationId=" + organizationId + "]";
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

}
