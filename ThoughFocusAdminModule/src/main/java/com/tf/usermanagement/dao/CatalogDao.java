package com.tf.usermanagement.dao;

/**
 * @author abhilash
 *
 */
public interface CatalogDao {

	Integer catalogCountBasedOnOrg(Long orgId);

	Integer getCatalogCount(long orgId, long userId);

	Long getCatalogDirectAssignedCount(long orgId, long userId);

	Long getCatalogGroupAssignedCount(long orgId, long userId);

	Long getCatalogAssignedCount(long userId, long orgId);
}
