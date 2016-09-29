package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartDescriptionPK implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
		
		private Long partId;
		private Long languageId;

		public PartDescriptionPK() {
		}

		@Column(name = "PART_ID")
		public Long getPartId() {
			return partId;
		}

		public void setPartId(Long partId) {
			this.partId = partId;
		}

		@Column(name = "LANGUAGE_ID")
		public Long getLanguageId() {
			return languageId;
		}

		public void setLanguageId(Long languageId) {
			this.languageId = languageId;
		}

		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof PartDescriptionPK)) {
				return false;
			}
			PartDescriptionPK castOther = (PartDescriptionPK) other;
			return (this.partId.equals(castOther.partId))
					&& (this.languageId.equals(castOther.languageId));

		}
		
		
		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
			hash = hash * prime
					+ ((int) (this.languageId ^ (this.languageId >>> 32)));

			return hash;
		}

		@Override
		public String toString() {
			return "PartDescriptionPK [partId=" + partId + ", languageId=" + languageId + "]";
		}
		
}
