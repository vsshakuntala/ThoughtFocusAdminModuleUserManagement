package com.tf.usermanagement.dto;

import java.io.Serializable;

public class CatalogCountForOrgDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long catalogCount;

	public CatalogCountForOrgDto() {
		super();
	}

	public long getCatalogCount() {
		return catalogCount;
	}

	public void setCatalogCount(long catalogCount) {
		this.catalogCount = catalogCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalogCountForOrgDto [catalogCount=");
		builder.append(catalogCount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
