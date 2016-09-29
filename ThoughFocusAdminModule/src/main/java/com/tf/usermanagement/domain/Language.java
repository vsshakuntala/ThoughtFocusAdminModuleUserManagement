package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the LANGUAGE database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="LANGUAGE")
public class Language implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Long languageId;
	private String languageDescription;
	private String languageName;
	private Set<UserPreference> userPreferences;
	private Media media;

    public Language() {
    }


	@Id
	@Column(name="LANGUAGE_ID")
	public Long getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}


	@Column(name="LANGUAGE_DESCRIPTION")
	public String getLanguageDescription() {
		return this.languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}


	@Column(name="LANGUAGE_NAME")
	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}


	//bi-directional many-to-one association to UserPreference
	@OneToMany(mappedBy="language")
	public Set<UserPreference> getUserPreferences() {
		return this.userPreferences;
	}

	public void setUserPreferences(Set<UserPreference> userPreferences) {
		this.userPreferences = userPreferences;
	}
	

	//bi-directional one-to-one association to Media
	@OneToOne(mappedBy="language")
	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}


	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", languageName="
				+ languageName + "]";
	}

}