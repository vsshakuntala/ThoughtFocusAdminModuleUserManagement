package com.tf.usermanagement.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.RoleManagementDao;
import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.dto.RolesDto;
import com.tf.usermanagement.utils.DateTimeUtil;

@Repository
@Transactional
public class RoleManagementDaoImp implements RoleManagementDao {

	@PersistenceContext
    private EntityManager em;  
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = Logger.getLogger(RoleManagementDaoImp.class);
	
	
	private RolesDto roleDto=new RolesDto();
	
	@Override
	public List<RolesDto> getAssignedRoles(Long userId, Long orgId) {
		
		LOGGER.info("User id getAssignedRoles:" + userId+"  Org Id :"+orgId);
		Session session = null;
		SQLQuery query = null;
		List<RolesDto> assignedRolesList=new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query=session.createSQLQuery("SELECT R.ROLE_ID,R.ROLE_NAME from ROLES R JOIN USER_ORGANIZATION_ROLE UOR ON R.ROLE_ID = UOR.ROLE_ID AND UOR.ACTIVE=1 AND UOR.ORGANIZATION_ID= :orgId AND UOR.USER_ID= :userId");
			query.setLong("userId", userId);
			query.setLong("orgId",orgId);
			
			@SuppressWarnings("unchecked")
			List<Object[]> groupCustomerArray  = query.list();
			LOGGER.info("list size : " + groupCustomerArray.size());
			
			for (Object[] groupCustomer : groupCustomerArray) {
				roleDto = new RolesDto();
				roleDto.setRoleId(((BigInteger)groupCustomer[0]).longValue());
				roleDto.setRoleName((String)groupCustomer[1]);
				assignedRolesList.add(roleDto);					
			}
			
		}catch(HibernateException e){
			LOGGER.error("Exception in getAssigned Roles List " + e.getMessage());
			
		}catch(Exception e){
			LOGGER.error("Exception in getAssigned Roles List " + e.getMessage());
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return assignedRolesList;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolesDto> getUnAssignedRoles(Long userId, Long orgId) {
		LOGGER.info("User id " + userId+"Org Id :"+orgId);
		Session session = null;
		SQLQuery query = null;
		List<RolesDto> unassignedRolesList=new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query=session.createSQLQuery("select r.ROLE_ID,r.ROLE_NAME from ROLES r where r.ACTIVE = 1 and r.ROLE_NAME not in (SELECT R.ROLE_NAME from ROLES R JOIN USER_ORGANIZATION_ROLE UOR ON R.ROLE_ID = UOR.ROLE_ID AND UOR.ACTIVE=1 AND UOR.ORGANIZATION_ID= :orgId AND UOR.USER_ID= :userId);");
			query.setLong("userId", userId);
			query.setLong("orgId", orgId);
			List<Object[]> groupCustomerArray  = query.list();
			LOGGER.info("list size : " + groupCustomerArray.size());
			for (Object[] groupCustomer : groupCustomerArray) {
				roleDto = new RolesDto();
				roleDto.setRoleId(((BigInteger)groupCustomer[0]).longValue());
				roleDto.setRoleName((String)groupCustomer[1]);
				unassignedRolesList.add(roleDto);					
			}
			
		}catch(HibernateException e){
			LOGGER.error("Exception in getUnAssignedRoles Roles List " + e.getMessage());
			
		}catch(Exception e){
			LOGGER.error("Exception in getUnAssignedRoles Roles List " + e.getMessage());
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		LOGGER.info("getUnAssignedRoles IN DAO:"+unassignedRolesList+"Count :"+unassignedRolesList.size());
		return unassignedRolesList;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<PermissionsDto> getRolePermissions(long roleId) {
		
		LOGGER.info("roleId id " + roleId);
		Session session = null;
		SQLQuery query = null;
		List<PermissionsDto> unassignedRolesList=new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query=session.createSQLQuery("select p.PERMISSION_ID,p.PERMISSION_NAME from PERMISSIONS p join role_permission rp on rp.PERMISSION_ID=p.PERMISSION_ID and rp.ROLE_ID = :roleId and p.ACTIVE=1");
			
			query.setLong("roleId", roleId);
			
			List<Object[]> groupCustomerArray  = query.list();
			LOGGER.info("list size : " + groupCustomerArray.size()+"Permissions List :"+groupCustomerArray);
			for (Object[] groupCustomer : groupCustomerArray) {
				PermissionsDto roleDto = new PermissionsDto();
				roleDto.setPermissionId(((BigInteger)groupCustomer[0]).longValue());
				roleDto.setPermissionName((String)groupCustomer[1]);
				unassignedRolesList.add(roleDto);					
			}
			return unassignedRolesList;
			
		}catch(HibernateException e){
			LOGGER.error("Exception in getAssigned Roles List " + e.getMessage());
			
		}catch(Exception e){
			LOGGER.error("Exception in getAssigned Roles List " + e.getMessage());
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateRole(List<Long> oldRoleList,List<Long> newRoleList,long userId,long orgId) {
		LOGGER.info("In update roles :"+oldRoleList);
		LOGGER.info("newGrpList dao "+newRoleList);
		Session session = null;
		SQLQuery query = null;
		org.hibernate.Transaction tx;
		int result =-1;
		
		try{
		  if(newRoleList != null && !newRoleList.isEmpty()){
				    LOGGER.info("inside the update list dao");
				    session=sessionFactory.openSession();
				   
		     for(Long grid :newRoleList){
		    	 
				query=session.createSQLQuery("SELECT * FROM USER_ORGANIZATION_ROLE  where USER_ID = :userId AND ROLE_ID = :roleId AND ORGANIZATION_ID = :orgId");
				query.setParameter("userId", userId);
				query.setParameter("roleId", grid);
				query.setParameter("orgId", orgId);
				List<Object[]> list=  query.list();
			    
				if (list != null && !list.isEmpty()) {
					tx=session.beginTransaction();
					Object[] obj = (Object[]) query.list().get(0);
					query=session.createSQLQuery("UPDATE USER_ORGANIZATION_ROLE SET ACTIVE=0 , MODIFIED_DATE = :modifieddate where USER_ID = :userId AND ROLE_ID = :roleId AND ORGANIZATION_ID = :orgId ");
					query.setParameter("userId", userId);
					query.setParameter("roleId", grid);
					query.setParameter("orgId", orgId);
					query.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());
					result=query.executeUpdate();
					tx.commit();
				}
			}  
		}
			
			if(oldRoleList != null && !oldRoleList.isEmpty()){
				for(Long grid :oldRoleList){
					session=sessionFactory.openSession();
					session.beginTransaction();
					query=session.createSQLQuery("SELECT * FROM USER_ORGANIZATION_ROLE  where USER_ID = :userId AND ROLE_ID = :roleId AND ORGANIZATION_ID = :orgId");
					query.setParameter("userId", userId);
					query.setParameter("roleId", grid);
					query.setParameter("orgId", orgId);
					List<Object[]> list=  query.list();
					if (list != null && !list.isEmpty()) {
						session=sessionFactory.openSession();
						tx=session.beginTransaction();
						query=session.createSQLQuery("UPDATE USER_ORGANIZATION_ROLE SET ACTIVE=1,MODIFIED_BY= :modifiedby,MODIFIED_DATE= :modifieddate where USER_ID = :userId AND ROLE_ID = :roleId AND ORGANIZATION_ID = :orgId");
						query.setParameter("userId", userId);
						query.setParameter("roleId", grid);
						query.setParameter("orgId", orgId);
						query.setParameter("modifiedby", userId);
						query.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());
						result=query.executeUpdate();
						tx.commit();
					}else{
						query=session.createSQLQuery("INSERT into USER_ORGANIZATION_ROLE(USER_ID,ROLE_ID,ORGANIZATION_ID,ACTIVE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) VALUES (:userId,:roleId,:orgId,:activeFlag,:createdby,:createddate,:modifiedby,:modifieddate)");
						query.setParameter("userId", userId);
						query.setParameter("roleId", grid);
						query.setParameter("orgId", orgId);
						query.setParameter("activeFlag", 1);
						query.setParameter("createdby", userId);
						query.setParameter("createddate", DateTimeUtil.getSqlTimeStamp());
						query.setParameter("modifiedby", userId);
						query.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());
						result = query.executeUpdate();
						
						session.getTransaction().commit();
						if(result==-1)
							return false;
					}
				}
			}
			return true;
		}catch(ConstraintViolationException e){
			LOGGER.info("In ConstraintViolationException:inserting duplicate object");
			
			return true;
			
		}catch(Exception e){
		    
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PermissionsDto> getPermissionForLogedInUser(String adminemail) {
		Session session = null;
		SQLQuery query = null;
		List<PermissionsDto> permissionList=new ArrayList<>();
		String sql=	"select p.PERMISSION_ID as permissionId,p.PERMISSION_NAME AS permissionName from USERS u,USER_ROLE ur,PERMISSIONS p, ROLE_PERMISSION rp, USER_ORG_MAP um "
				+ "where u.USER_ID= :userEmail and u.USER_ID=ur.USER_ID and ur.ROLE_ID=rp.ROLE_ID and p.PERMISSION_ID=rp.PERMISSION_ID " 
				+ "and rp.ACTIVE=1 and p.ACTIVE=1 and um.USER_ID=u.USER_ID and ur.active=1 and u.active=1 and um.APPROVAL_STATUS=1";
		try{
			session = sessionFactory.openSession();
			
			query = session.createSQLQuery(sql);
			query.setParameter("userEmail",adminemail);
			query.addScalar("permissionId", LongType.INSTANCE);
			query.addScalar("permissionName", StringType.INSTANCE);
			
			permissionList = query.setResultTransformer( Transformers.aliasToBean(PermissionsDto.class)).list();
			
			LOGGER.debug(permissionList);
			
		}catch (HibernateException e) {
			
			LOGGER.error("Exception in permissionList " + e.getMessage());
		} catch (Exception e) {
			
			LOGGER.error("Exception in permissionList " + e.getMessage());
		}
		return permissionList;
	}
	
}
