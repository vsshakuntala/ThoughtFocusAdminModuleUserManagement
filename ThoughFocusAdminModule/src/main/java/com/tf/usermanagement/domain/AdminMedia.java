package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the MEDIA database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "MEDIA")
public class AdminMedia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long mediaId;
	// private Part part;
	private Integer mediaType;
	private Integer mediaPageNumber;
	private String mediaFile;
	private Language language;
	private Integer leftCoordinate;
	private Integer topCoordinate;
	private Integer rightCoordinate;
	private Integer bottomCoordinate;
	private String webPath;
	private Integer playbackPriority;
	private Boolean vectorFormat;
	private Integer mediaFileTypeId;
	private Integer diagramTypeId;
	private String description;
	private String thumbnailPath;
	private Set<CatalogMedia> catalogMedias;
	private String htmlFilePath;
	private AdminOrganization organization;
	private int organizationId;
	private String exportFilePath;
	private boolean guestUserHasAccess;

	@Column(name = "GUESTUSER_HAS_ACCESS")
	public boolean isGuestUserHasAccess() {
		return guestUserHasAccess;
	}

	public void setGuestUserHasAccess(boolean guestUserHasAccess) {
		this.guestUserHasAccess = guestUserHasAccess;
	}

	@Column(name = "EXPORTFILE_PATH")
	public String getExportFilePath() {
		return exportFilePath;
	}

	public void setExportFilePath(String exportFilePath) {
		this.exportFilePath = exportFilePath;
	}

	// Promotion Part Media
	// private Promotion promotionMedias;
	// @OneToOne(mappedBy="media")
	// @JoinColumn(name="MEDIA_ID")
	// public Promotion getPromotionMedias() {
	// return promotionMedias;
	// }
	// public void setPromotionMedias(Promotion promotionMedias) {
	// this.promotionMedias = promotionMedias;
	// }
	@Id
	@Column(name = "MEDIA_ID")
	@GeneratedValue
	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="PART_ID") public Part getPart() { return part; } public
	 * void setPart(Part part) { this.part = part; }
	 */

	@Column(name = "MEDIA_TYPE")
	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	@Column(name = "MEDIA_PAGE_NUMBER")
	public Integer getMediaPageNumber() {
		return mediaPageNumber;
	}

	public void setMediaPageNumber(Integer mediaPageNumber) {
		this.mediaPageNumber = mediaPageNumber;
	}

	@Column(name = "MEDIA_FILE")
	public String getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(String mediaFile) {
		this.mediaFile = mediaFile;
	}

	@ManyToOne
	@JoinColumn(name = "MEDIA_LANGUAGE")
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name = "LEFT_COORDINATE")
	public Integer getLeftCoordinate() {
		return leftCoordinate;
	}

	public void setLeftCoordinate(Integer leftCoordinate) {
		this.leftCoordinate = leftCoordinate;
	}

	@Column(name = "TOP_COORDINATE")
	public Integer getTopCoordinate() {
		return topCoordinate;
	}

	public void setTopCoordinate(Integer topCoordinate) {
		this.topCoordinate = topCoordinate;
	}

	@Column(name = "RIGHT_COORDINATE")
	public Integer getRightCoordinate() {
		return rightCoordinate;
	}

	public void setRightCoordinate(Integer rightCoordinate) {
		this.rightCoordinate = rightCoordinate;
	}

	@Column(name = "BOTTOM_COORDINATE")
	public Integer getBottomCoordinate() {
		return bottomCoordinate;
	}

	public void setBottomCoordinate(Integer bottomCoordinate) {
		this.bottomCoordinate = bottomCoordinate;
	}

	@Column(name = "WEB_PATH")
	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	@Column(name = "THUMBNAIL_PATH")
	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	@Column(name = "PLAYBACK_PRIORITY")
	public Integer getPlaybackPriority() {
		return playbackPriority;
	}

	public void setPlaybackPriority(Integer playbackPriority) {
		this.playbackPriority = playbackPriority;
	}

	@Column(name = "VECTOR_FORMAT")
	public Boolean getVectorFormat() {
		return vectorFormat;
	}

	public void setVectorFormat(Boolean vectorFormat) {
		this.vectorFormat = vectorFormat;
	}

	@Column(name = "MEDIA_FILE_TYPE_ID")
	public Integer getMediaFileTypeId() {
		return mediaFileTypeId;
	}

	public void setMediaFileTypeId(Integer mediaFileTypeId) {
		this.mediaFileTypeId = mediaFileTypeId;
	}

	@Column(name = "DIAGRAM_TYPE_ID")
	public Integer getDiagramTypeId() {
		return diagramTypeId;
	}

	public void setDiagramTypeId(Integer diagramTypeId) {
		this.diagramTypeId = diagramTypeId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy = "media", fetch = FetchType.LAZY)
	public Set<CatalogMedia> getCatalogMedias() {
		return catalogMedias;
	}

	public void setCatalogMedias(Set<CatalogMedia> catalogMedias) {
		this.catalogMedias = catalogMedias;
	}

	@Column(name = "HTML_FILE_PATH")
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", insertable = false, updatable = false)
	public AdminOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(AdminOrganization organization) {
		this.organization = organization;
	}

	@Column(name = "ORGANIZATION_ID")
	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public String toString() {
		return "Media [mediaId=" + mediaId + ", mediaType=" + mediaType + "]";
	}

}
