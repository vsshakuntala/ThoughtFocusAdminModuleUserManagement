package com.tf.usermanagement.dto;

/**
 * @author Narasingha
 *
 */
public class LanguageDTO {
	
	private Long languageId;
	private String languageDescription;
	
	public LanguageDTO() {
		super();
	}

	public LanguageDTO(Long languageId, String languageDescription) {
		super();
		this.languageId = languageId;
		this.languageDescription = languageDescription;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageDescription() {
		return languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}
	
	

}
