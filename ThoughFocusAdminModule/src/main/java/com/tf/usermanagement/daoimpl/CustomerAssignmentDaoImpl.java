package com.tf.usermanagement.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.CustomerAssignmentDao;
import com.tf.usermanagement.domain.UserCustomer;
import com.tf.usermanagement.domain.UserCustomerPK;
import com.tf.usermanagement.dto.CustomerCount;
import com.tf.usermanagement.dto.CustomerDownloadDto;
import com.tf.usermanagement.utils.DateTimeUtil;
/**
 * This class is used to assign and de-assign the customer from the division
 * @author Santosh
 *
 */
@Repository
@Transactional
public class CustomerAssignmentDaoImpl implements CustomerAssignmentDao{
    
    
    @PersistenceContext
    private EntityManager em; 
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAssignmentDaoImpl.class);
    
    private static final String CUSTOMER_COUNT = "SELECT count(distinct com.CUSTOMER_ID) AS availableCustomerCount,t1.assignedCustomer AS assignedCustomerCount "
    	+ "from CUSTOMER_ORGANIZATION_MAP com ,(SELECT Count(assignedCustomer ) AS assignedCustomer   from ((SELECT distinct UsrCust.CUSTOMER_ID AS assignedCustomer "
    	+ "FROM USERS Usr INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
    	+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
    	+ "LEFT JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
    	+ "WHERE Usr.USER_ID = :userId AND CustOrg.ORGANIZATION_ID = :orgId) union "
    	+ "(SELECT DISTINCT GrpCust.CUSTOMER_ID AS assignedCustomer from users usr "
    	+ "INNER join user_group ug on usr.user_id= ug.user_id  and  ug.active=1 "
    	+ "inner join groups grp on grp.group_id=ug.group_id  and grp.active=1 "
    	+ "inner  JOIN GROUP_CUSTOMER GrpCust ON ug.GROUP_ID = GrpCust.GROUP_ID AND   GrpCust.ACTIVE = 1 "
    	+ "INNER JOIN CUSTOMER c on c.customer_ID=GrpCust.customer_ID  and c.active=1 "
    	+ "where usr.user_id=:userId AND grp.ORGANIZATION_ID=:orgId) ) t ) t1 "
    	+ "where com.ORGANIZATION_ID=:orgId  AND com.ACTIVE=1 group by t1.assignedCustomer";

    private static final String CUSTOMER_ASSIGNED_COUNT = "SELECT count(distinct UsrCust.CUSTOMER_ID) AS assignedCustomerCount "
	    	+ "FROM USERS Usr INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
	    	+ "LEFT JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
	    	+ "WHERE Usr.USER_ID = :userId AND CustOrg.ORGANIZATION_ID = :orgId";

    /**
     * used to assign the customer for a user
     * input- user id, list of customers which have to assign for user
     * 
     * 
     */
    @Override
    @Transactional
    public String assignCustomer(Long loginUserId,Long userId, List<Long> customerList) {
	String msg= null;
	try{
	    if(customerList != null && !customerList.isEmpty()){
		    LOGGER.info("inside the new list dao"+customerList);
		for(Long custId :customerList){
		    
		    UserCustomerPK pk =new UserCustomerPK();
		    pk.setCustomerId(custId);
		    pk.setUserId(userId);
		    
		    UserCustomer userCustomer = (UserCustomer) em.find(UserCustomer.class, pk);
		    
		    if (userCustomer != null) {
			 LOGGER.info("inside the existing list dao");
			    userCustomer.setActive(true);
			    userCustomer.setModifiedBy(loginUserId);
			    userCustomer.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
			}else {
			    LOGGER.info("inside the creation of  new list dao");
			    UserCustomer userCustomer1 = new UserCustomer();
			    userCustomer1.setActive(true);
			    userCustomer1.setCustomerId(custId);
			    userCustomer1.setUserId(userId);
			    userCustomer1.setCreatedBy(loginUserId);
			    userCustomer1.setCreatedDate(DateTimeUtil.getSqlTimeStamp());
			    userCustomer1.setId(pk);
			    em.persist(userCustomer1);
			}	    
		  }
		msg = "Successfully customer is assigned";
		return msg;
		} else{
		    throw new NullPointerException();
		}
	} catch (Exception e) {
		LOGGER.error("Exception while assigning the customer " + e.getMessage());
	} 
	
	return msg;
    }

    /**
     * used to remove the customer for a user
     * input- user id, list of customers which have to remove for user
     * 
     */
    @Override
    public String removeCustomer(Long loginUserId,Long userId, List<Long> customerList) {
	String msg= null;
	try{
	    if(customerList != null && !customerList.isEmpty()){
		    LOGGER.info("inside the new list dao");
		for(Long custId :customerList){
		    
		    UserCustomerPK pk =new UserCustomerPK();
		    pk.setCustomerId(custId);
		    pk.setUserId(userId);
		    
		    UserCustomer userCustomer = (UserCustomer) em.find(UserCustomer.class, pk);
			if (userCustomer != null) {
			    userCustomer.setActive(false);
			    userCustomer.setModifiedBy(loginUserId);
			    userCustomer.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
			}	    
		  }
		msg="Successfully customer is removed";
		return msg;
		} else{
		    throw new NullPointerException();
		}
	} catch (Exception e) {
		LOGGER.error("Exception while assigning the customer " + e.getMessage());
	} 	
	
	return msg;
    }

    /**
     * used to assign all customer for a user
     * input- user id, list of all customers which have to assign for user
     * 
     */
    @Override
    public String assignAllCustomer(Long loginUserId,Long userId,String query,List<Long> custIdList) {	
	String msg=null;
	
	try{
	    
	    if(custIdList != null && !custIdList.isEmpty()){
		    LOGGER.info("inside the new list dao");
		    
		for(Long custId :custIdList){
		    
		    UserCustomerPK pk =new UserCustomerPK();
		    pk.setCustomerId(custId);
		    pk.setUserId(userId);
		    
		    UserCustomer userCustomer = (UserCustomer) em.find(UserCustomer.class, pk);
			if (userCustomer != null) {
			    userCustomer.setActive(true);
			    userCustomer.setModifiedBy(loginUserId);
			    userCustomer.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
			    
			}else{
			    UserCustomer userCustomer1 = new UserCustomer();
			    userCustomer1.setActive(true);
			    userCustomer1.setCustomerId(custId);
			    userCustomer1.setUserId(userId);
			    userCustomer1.setCreatedBy(loginUserId);
			    userCustomer1.setCreatedDate(DateTimeUtil.getSqlTimeStamp());
			    userCustomer1.setId(pk);
			    em.persist(userCustomer1);
			    
			}	    
		  }
		msg="Successfully All customers are assigned";
		return msg;
		} else{
		    throw new NullPointerException();
		}
	    
	}catch(Exception e){
	    LOGGER.error("Exception while assigning all customer " + e.getMessage());
	}
	
	return msg;
    }

    /**
     * used to remove all customer for a user
     * input- user id, list of all customers which have to remove for user
     * 
     */
    @Override
    public String removeAllCustomer(Long loginUserId,Long userId,String query) {
	
	String updateSql = "UPDATE USER_CUSTOMER set ACTIVE=0,MODIFIED_BY=:modifiedby, MODIFIED_DATE=:modifieddate WHERE CUSTOMER_ID IN ";
	Session session = null;
	Transaction tx = null;
	SQLQuery qry = null;
	int count=0;
	String msg=null;
	try{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		qry=session.createSQLQuery(updateSql+ "("+query+")");
		
		qry.setLong("modifiedby",loginUserId);
		qry.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());	
		
		count=	qry.executeUpdate();
		tx.commit();
		if(count>=0){
			LOGGER.info("No,of rows updated while de-assigning customer for user :"+count);
			msg="Successfully All customers are removed";
			return msg;
		}
				
	}catch (HibernateException e) {
	    if(tx != null){
		
		tx.rollback();
	    }
		LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
	} catch (Exception e) {
            if(tx != null){
		
		tx.rollback();
	    }
		LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
		
	}

	return msg;
    }

    /**
     * use to get all the unassigned customer list
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getUnassignedList(String query) {

	Session session = null;
	SQLQuery qry = null;
	List<Long> custList = null;
	try {
		session = sessionFactory.openSession();
		qry = session.createSQLQuery(query).addScalar("customerNumber", LongType.INSTANCE);
		custList = qry.list();
		
	} catch (HibernateException e) {
		LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	return custList;

    }

    /**
     * /**
     * used to remove all customer for a user
     * input- user id, list of all customers which have to remove for user
     * 
     */
     
    @Override
    public String updateAllCustomer(Long loginUserId, Long userId, List<Long> custIdList) {
	
	String updateSql = "UPDATE USER_CUSTOMER set ACTIVE=0,MODIFIED_BY=:modifiedby MODIFIED_DATE=:modifieddate WHERE USER_ID=:userId AND CUSTOMER_ID IN ";
	Session session = null;
	Transaction tx = null;
	SQLQuery qry = null;
	int count=0;
	String msg=null;
	try{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		qry=session.createSQLQuery(updateSql+ "("+custIdList+")");
		
		qry.setLong("modifiedby",loginUserId);
		qry.setLong("userId",userId);
		qry.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());
		
		
		count=	qry.executeUpdate();
		tx.commit();
		if(count>=0){
			LOGGER.info("No,of rows updated while de-assigning customer for user :"+count);
			msg="Successfully All customers are removed";
			return msg;
		}
				
	}catch (HibernateException e) {
	    if (tx != null) {

		tx.rollback();
	    }
		LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
	} catch (Exception e) {
	    if (tx != null) {

		tx.rollback();
	    }
		LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
		
	}    
	return msg;
    }

    /**
     * used to download the customer report for assigned customer based on filter criteria
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerDownloadDto> getAllCustomer(String sqlquery) {
	Session session = null;
	List<CustomerDownloadDto> list = null;
	try {
		session = sessionFactory.openSession();
		list = session.createSQLQuery(sqlquery)
			.addScalar("CustomerNum", LongType.INSTANCE)
			.addScalar("Name", StringType.INSTANCE)
			.addScalar("Address1", StringType.INSTANCE)
			.addScalar("City", StringType.INSTANCE)
			.addScalar("State", StringType.INSTANCE)
			.addScalar("Postal", StringType.INSTANCE)
			.addScalar("Country", StringType.INSTANCE)			
			.addScalar("Type", StringType.INSTANCE)
			.setResultTransformer( Transformers.aliasToBean(CustomerDownloadDto.class))
			  .list();
				

		
	} catch (HibernateException e) {
		LOGGER.error("Exception in download " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception while downloading the report " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	return list;
}
    /**
     * used to download the customer report for unassigned customer based on filter criteria
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerDownloadDto> getAllCustomerForUnassigned(String sqlquery) {
	Session session = null;
	List<CustomerDownloadDto> list = null;
	try {
		session = sessionFactory.openSession();
		list = session.createSQLQuery(sqlquery)
			.addScalar("CustomerNum", LongType.INSTANCE)
			.addScalar("Name", StringType.INSTANCE)
			.addScalar("Address1", StringType.INSTANCE)
			.addScalar("City", StringType.INSTANCE)
			.addScalar("State", StringType.INSTANCE)
			.addScalar("Postal", StringType.INSTANCE)
			.addScalar("Country", StringType.INSTANCE)
			.addScalar("Status", StringType.INSTANCE)
			.setResultTransformer( Transformers.aliasToBean(CustomerDownloadDto.class))
			  .list();
				

		
	} catch (HibernateException e) {
		LOGGER.error("Exception in download for unassignement customer " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception while downloading the unassigned customer " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	return list;
}

    /**
     * used to get the number of available customer and assigned customer for a division
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerCount> getavailableCustomerCount(Long userId, Long orgId) {
	Session session = null;
	List<CustomerCount> custList = null;
	try {
		session = sessionFactory.openSession();
		custList = session.createSQLQuery(CUSTOMER_COUNT)
			.addScalar("availableCustomerCount", LongType.INSTANCE)
			.addScalar("assignedCustomerCount", LongType.INSTANCE)
			.setParameter("userId", userId)
			.setParameter("orgId", orgId)
			.setResultTransformer( Transformers.aliasToBean(CustomerCount.class))
			  .list();
		
	} catch (HibernateException e) {
		LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	return custList;
    }

    /**
     * used to get the number of assigned customer for a division
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerCount> getAssignedCustomerCount(Long userId, Long orgId) {
	Session session = null;
	List<CustomerCount> custList = null;
	try {
		session = sessionFactory.openSession();
		custList = session.createSQLQuery(CUSTOMER_ASSIGNED_COUNT)
			.addScalar("assignedCustomerCount", LongType.INSTANCE)
			.setParameter("userId", userId)
			.setParameter("orgId", orgId)
			.setResultTransformer( Transformers.aliasToBean(CustomerCount.class))
			  .list();
		
	} catch (HibernateException e) {
		LOGGER.error("Exception in getAssignedCustomerCountByOrganization " + e.getMessage());
	} catch (Exception e) {
		LOGGER.error("Exception in getAssignedCustomerCountByOrganization " + e.getMessage());
	} finally {
		if (session != null) {
			session.close();
		}
	}
	return custList;
    }

}
