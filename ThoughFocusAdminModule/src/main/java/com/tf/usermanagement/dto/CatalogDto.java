/**
 * 
 */
package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author abhilash
 *
 */
public class CatalogDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger catalogId;
	private Integer catalogCount;

	public BigInteger getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(BigInteger catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getCatalogCount() {
		return catalogCount;
	}

	public void setCatalogCount(Integer catalogCount) {
		this.catalogCount = catalogCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalogId == null) ? 0 : catalogId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogDto other = (CatalogDto) obj;
		if (catalogId == null) {
			if (other.catalogId != null)
				return false;
		} else if (!catalogId.equals(other.catalogId))
			return false;
		return true;
	}

}
