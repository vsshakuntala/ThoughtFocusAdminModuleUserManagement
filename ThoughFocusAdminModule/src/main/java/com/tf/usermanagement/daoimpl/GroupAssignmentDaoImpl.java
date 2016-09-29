package com.tf.usermanagement.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.GroupAssignmentDao;
import com.tf.usermanagement.domain.UserGroup;
import com.tf.usermanagement.domain.UserGroupPK;
import com.tf.usermanagement.dto.GroupDto;
import com.tf.usermanagement.utils.DateTimeUtil;

/**
 * 
 * @author Santosh
 *
 */

@Repository
@Transactional
public class GroupAssignmentDaoImpl implements GroupAssignmentDao{

    @PersistenceContext
    private EntityManager em; 
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupAssignmentDaoImpl.class);
   
    /**
     * This method is used to get the all assigned group for a user based on division id
     *    
     */
    @Override
    public List<GroupDto> getAssignedGroup(Long userId,Integer orgId) {
	
	Session session = null;
	List<GroupDto> grpAssignedList = new ArrayList<GroupDto>();
	
	try {
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT g.GROUP_ID,g.GROUP_NAME FROM GROUPS g inner join USER_GROUP ug"
			+ " on g.GROUP_ID=ug.GROUP_ID where ug.USER_ID=:userId and ug.ACTIVE=1 and g.ORGANIZATION_ID=:organizationId");
		
		query.setParameter("userId", userId);		
		query.setParameter("organizationId", orgId);
		
		@SuppressWarnings("unchecked")
		List<Object[]> groupArray = query.list();
		for (Object[] group : groupArray) {
		    GroupDto groupDto = new GroupDto();
			
		    groupDto.setGroupId(((BigInteger) group[0]).longValue());
		    groupDto.setGroupName((String) group[1]);
		    grpAssignedList.add(groupDto);
		}		
		
	}catch (HibernateException e) {
		LOGGER.error("Exception in get assigned group " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception in get group " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	
	return grpAssignedList;
	
    }

    /**
     * This method is used to get the all unassigned group for a user based on division id
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<GroupDto> getUnAssignedGroup(Long userId,Integer orgId) {
	Session session = null;
	List<GroupDto> grpUnAssignedList = new ArrayList<GroupDto>();
	
	try {
		session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select g.GROUP_ID,g.GROUP_NAME from Groups g where g.active=1 and g.ORGANIZATION_ID=:organizationId"
			+ " and g.GROUP_NAME not in (SELECT g.GROUP_NAME FROM GROUPS g inner join USER_GROUP ug on g.GROUP_ID=ug.GROUP_ID where ug.USER_ID=:userId and ug.ACTIVE=1 and g.ORGANIZATION_ID=:organizationId)");
		
		query.setParameter("userId", userId);	
		query.setParameter("organizationId", orgId);
		
		List<Object[]> groupArray = query.list();
		for (Object[] group : groupArray) {
		    GroupDto groupDto = new GroupDto();
			
		    groupDto.setGroupId(((BigInteger) group[0]).longValue());
		    groupDto.setGroupName((String) group[1]);
		    grpUnAssignedList.add(groupDto);
		}		
		
	}catch (HibernateException e) {
		LOGGER.error("Exception in get unassigned group " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception in get unassigned group " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	
	return grpUnAssignedList;
    }

    /**
     * This method is used to update the group for a user
     */
    @Override
    public String updateGroup(List<Long> oldGroupList,List<Long> newGroupList,Long userId,Long loginId) {
	
	LOGGER.info("oldGrpList dao "+oldGroupList);
	LOGGER.info("newGrpList dao "+newGroupList);
	
	try{
	    if(oldGroupList != null && !oldGroupList.isEmpty()){
		LOGGER.info("inside the update list dao");
		    for(Long grid :oldGroupList){
			    
			UserGroupPK pk =new UserGroupPK();
			pk.setGroupId(grid);
			pk.setUserId(userId);
			
			UserGroup userGroup = (UserGroup) em.find(UserGroup.class, pk);
			if (userGroup != null) {
				userGroup.setActive(false);
				userGroup.setModifiedBy(loginId);
				userGroup.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
			}
		    
		    }
		}
		
		if(newGroupList != null && !newGroupList.isEmpty()){
		    LOGGER.info("inside the new list dao");
		for(Long grId :newGroupList){
		    
		    UserGroupPK pk =new UserGroupPK();
		    pk.setGroupId(grId);
		    pk.setUserId(userId);
		    
		    UserGroup userGroup = (UserGroup) em.find(UserGroup.class, pk);
			if (userGroup != null) {
				userGroup.setActive(true);
				userGroup.setModifiedBy(loginId);
				userGroup.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
			}else{
			    UserGroup userGroup1 = new UserGroup();
			    userGroup1.setActive(true);
			    userGroup1.setGroupId(grId);
			    userGroup1.setUserId(userId);
			    userGroup1.setCreatedBy(loginId);
			    userGroup1.setCreatedDate(DateTimeUtil.getSqlTimeStamp());
			    userGroup1.setCompositeId(pk);
			    em.persist(userGroup1);
			    
			}	    
		  }		
		} 		
		return "successfully group is assigned";
	}catch (HibernateException e) {
		LOGGER.error("Exception to update group " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception to update group " + e.getMessage());
	} 	
	
	return null;
    }
}
