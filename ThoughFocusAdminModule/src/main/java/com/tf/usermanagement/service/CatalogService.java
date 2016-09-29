/**
 * 
 */
package com.tf.usermanagement.service;

/**
 * @author abhilash
 *
 */
public interface CatalogService {
	
	Integer catalogCountBasedOnOrg(Long orgId);


	Integer getCatalogCount(long orgId, long userId);
	
	long getCatalogAssignedCount(long orgId, long userId);
}
