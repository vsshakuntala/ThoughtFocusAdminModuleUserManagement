package com.tf.usermanagement.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.usermanagement.dao.CatalogDao;
import com.tf.usermanagement.exception.CatalogException;
import com.tf.usermanagement.service.CatalogService;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogDao catalogDao;

	private static final Logger LOGGER = Logger.getLogger(CatalogServiceImpl.class);

	@Override
	public Integer catalogCountBasedOnOrg(Long orgId) {

		Integer count = catalogDao.catalogCountBasedOnOrg(orgId);
		if (count != null) {
			return count;
		} else {
			LOGGER.info("No Machines for the selected Org!");
			throw new CatalogException("No Count!");
		}

	}


	
	@Override
	public Integer getCatalogCount(long orgId,long userId){
		Integer count=catalogDao.getCatalogCount(orgId, userId);
		if(count!=null){
			return count;
		} else {
			LOGGER.info("No Machines for the selected Org!");
			throw new CatalogException("No Count!");
		}
		
	}



	@Override
	public long getCatalogAssignedCount(long orgId, long userId) {/*
		Long groupAssignedCount=catalogDao.getCatalogGroupAssignedCount(orgId, userId);
		Long directAssignedCount=catalogDao.getCatalogDirectAssignedCount(orgId, userId);
		Long total=groupAssignedCount+directAssignedCount;
		System.out.println("Group assignment count :"+directAssignedCount);
		System.out.println("Individual assignment count :"+groupAssignedCount);
		if(groupAssignedCount!=null && directAssignedCount!=null){
			return total;
		} else {
			LOGGER.info("No Assigned Catalog for the selected Org!");
			throw new CatalogException("No Count!");
		}
		
	*/
		return catalogDao.getCatalogAssignedCount(userId, orgId);	
	}

}
