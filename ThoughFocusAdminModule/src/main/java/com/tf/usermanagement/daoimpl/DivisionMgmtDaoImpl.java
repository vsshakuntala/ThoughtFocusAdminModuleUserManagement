package com.tf.usermanagement.daoimpl;

/**
 * @author Manideep
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.DivisionMgmtDao;
import com.tf.usermanagement.domain.User;
import com.tf.usermanagement.domain.UserNotes;
import com.tf.usermanagement.domain.UserOrgSalesAreaMap;
import com.tf.usermanagement.domain.UserOrganizationMap;
import com.tf.usermanagement.dto.AdminOrgListDto;
import com.tf.usermanagement.dto.AssignOrgDto;
import com.tf.usermanagement.dto.CatalogTotalCountDto;
import com.tf.usermanagement.dto.CustomerCatalogCountDto;
import com.tf.usermanagement.dto.CustomerTotalCountDto;
import com.tf.usermanagement.dto.DeAssignUserToOrgInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.DefaultAddressCountDto;
import com.tf.usermanagement.dto.GroupCatalogCountDto;
import com.tf.usermanagement.dto.GroupCustomerCountDto;
import com.tf.usermanagement.dto.GroupTotalCountDto;
import com.tf.usermanagement.dto.OrganizationDTO;
import com.tf.usermanagement.dto.OrganizationRoleCountDto;
import com.tf.usermanagement.dto.UserNotesDto;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;

@Repository
public class DivisionMgmtDaoImpl implements DivisionMgmtDao {

	private static final Logger LOGGER = Logger.getLogger(DivisionMgmtDaoImpl.class);
	
	private static final String GETNOTEFORUSER ="select un.user_id as userId,un.note as notes,"
			+ " un.CREATED_DATE as createdDate,"	+ "(select user_name from users where user_id= un.CREATED_BY) "
					+ "as userName from USER_NOTES un where un.USER_ID = :userId order by un.CREATED_DATE desc";

	@Autowired
	private SessionFactory sessionFactory;


	/**
	 * this method is used to get the organization that user belongs to the user
	 * and also the group count and customer count belongs to each group
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GroupCustomerCountDto> getCustomerCountForGroupsByOrganization(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<GroupCustomerCountDto> groupCustomerCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"SELECT Org.ORGANIZATION_ID organizationId,Org.ORGANIZATION_NAME organizationName,Count(DISTINCT UsrGrp.GROUP_ID) groupCount,Count(DISTINCT GrpCust.CUSTOMER_ID) groupCustomerCount"
									+ " FROM	USERS Usr"
									+ " LEFT JOIN USER_ORG_MAP UsrOrgMap ON Usr.USER_ID = UsrOrgMap.USER_ID AND UsrOrgMap.ACTIVE = 1"
									+ " INNER JOIN ORGANIZATION Org ON Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
									+ " LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND Grp.ACTIVE = 1"
									+ " LEFT JOIN USER_GROUP UsrGrp ON	Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND	UsrGrp.ACTIVE = 1"
									+ " LEFT  JOIN GROUP_CUSTOMER GrpCust ON UsrGrp.GROUP_ID = GrpCust.GROUP_ID AND GrpCust.ACTIVE = 1"
									+ " WHERE Usr.USER_ID = :userId"
									+ " Group By Usr.USER_ID, Usr.USER_NAME, Org.ORGANIZATION_ID,Org.ORGANIZATION_NAME"
									+ " ORDER BY Usr.USER_ID, Org.ORGANIZATION_ID")
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("organizationName", StandardBasicTypes.STRING)
					.addScalar("groupCount", StandardBasicTypes.BIG_INTEGER)
					.addScalar("groupCustomerCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			List<Object[]> groupCustomerArray = query.list();
			for (Object[] groupCustomer : groupCustomerArray) {
				GroupCustomerCountDto groupCustomerDto = new GroupCustomerCountDto();
				groupCustomerDto.setOrganizationId(((BigInteger) groupCustomer[0]).longValue());
				groupCustomerDto.setOrganizationName((String) groupCustomer[1]);
				groupCustomerDto.setGroupCount(((BigInteger) groupCustomer[2]).longValue());
				groupCustomerDto.setGroupCustomerCount(((BigInteger) groupCustomer[3]).longValue());
				groupCustomerCountList.add(groupCustomerDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return groupCustomerCountList;
	}

	/**
	 * this method is used to get the catalog count based on the groups for the
	 * selected user for respective organizations he belongs to
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupCatalogCountDto> getCatalogCountForGroupsByOrganization(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<GroupCatalogCountDto> groupCatalogCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"SELECT Org.ORGANIZATION_ID organizationId,Count(DISTINCT GrpCat.CATALOG_ID) groupCatalogCount"
									+ " FROM	USERS Usr"
									+ " LEFT JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
									+ " INNER JOIN ORGANIZATION Org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
									+ " LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND	Grp.ACTIVE = 1"
									+ " LEFT JOIN USER_GROUP UsrGrp ON Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND UsrGrp.ACTIVE = 1"
									+ " LEFT JOIN GROUP_CATALOG GrpCat ON	UsrGrp.GROUP_ID = GrpCat.GROUP_ID AND	GrpCat.ACTIVE = 1"
									+ " WHERE Usr.USER_ID = :userId"
									+ " Group By Usr.USER_ID, Usr.USER_NAME, Org.ORGANIZATION_ID,Org.ORGANIZATION_NAME"
									+ " ORDER BY Usr.USER_ID, Org.ORGANIZATION_ID")
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("groupCatalogCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			List<Object[]> groupCatalogArray = query.list();
			for (Object[] groupCatalog : groupCatalogArray) {

				GroupCatalogCountDto groupCatalogDto = new GroupCatalogCountDto();
				groupCatalogDto.setOrganizationId(((BigInteger) groupCatalog[0]).longValue());
				groupCatalogDto.setGroupCatalogCount(((BigInteger) groupCatalog[1]).longValue());
				groupCatalogCountList.add(groupCatalogDto);
			}

			LOGGER.debug("getCatalogCountList " + groupCatalogCountList.size());
			LOGGER.debug("Data " + groupCatalogCountList.toString());
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return groupCatalogCountList;
	}
	
	/**
	 * 
	 */
	@Override
	public List<CustomerCatalogCountDto> getCustomerAndCatalogCount(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<CustomerCatalogCountDto> customerCatalogCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"select userOrgMap.ORGANIZATION_ID organizationId ,userOrgMap.APPROVAL_STATUS as status,count(DISTINCT userCust.CUSTOMER_ID) as customersCount,count(DISTINCT userCat.CATALOG_ID) as catalogsCount"
									+ " From USERS users"
									+ " LEFT JOIN USER_ORG_MAP userOrgMap on  users.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
									+ " INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
									+ " INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
									+ " LEFT JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.ACTIVE=1 and userCust.USER_ID=users.USER_ID"
									+ " LEFT JOIN CATALOG cat on cat.CUSTOMER_ID=userCust.CUSTOMER_ID"
									+ " LEFT JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=users.USER_ID"
									+ " WHERE users.USER_ID = :userId"
									+ " Group By users.USER_ID,org.ORGANIZATION_NAME,userOrgMap.ORGANIZATION_ID,userOrgMap.APPROVAL_STATUS"
									+ " Order By users.USER_ID,org.ORGANIZATION_NAME")
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("status", StandardBasicTypes.BIG_INTEGER)
					.addScalar("customersCount", StandardBasicTypes.BIG_INTEGER)
					.addScalar("catalogsCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> customerCatalogArray = query.list();
			for (Object[] customerCatalog : customerCatalogArray) {
				CustomerCatalogCountDto customerCatalogDto = new CustomerCatalogCountDto();
				customerCatalogDto.setOrganizationId(((BigInteger) customerCatalog[0]).longValue());
				customerCatalogDto.setStatus(((BigInteger) customerCatalog[1]).longValue());
				customerCatalogDto.setCustomersCount(((BigInteger) customerCatalog[2]).longValue());
				customerCatalogDto.setCatalogsCount(((BigInteger) customerCatalog[3]).longValue());

				customerCatalogCountList.add(customerCatalogDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCustomerAndCatalogCount " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getCustomerAndCatalogCount " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return customerCatalogCountList;
	}
	
	
	/**
	 * 
	 */
	@Override
	public List<CustomerTotalCountDto> getTotalCustomerCount(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<CustomerTotalCountDto> customerTotalCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select ORGANIZATION_ID as organizationId,count(DISTINCT groupCustomerId) as customerCount from ("
								+ " (SELECT DISTINCT customer.CUSTOMER_ID AS groupCustomerId,Org.ORGANIZATION_ID" 
								+ " From  USERS usr"
								+ " INNER JOIN USER_ORG_MAP UsrOrgMap ON Usr.USER_ID = UsrOrgMap.USER_ID AND UsrOrgMap.ACTIVE = 1"
								+ " INNER JOIN ORGANIZATION Org ON Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
								+ " LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND Grp.ACTIVE = 1"
								+ " LEFT JOIN USER_GROUP UsrGrp ON	Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND	UsrGrp.ACTIVE = 1"
								+ " LEFT  JOIN GROUP_CUSTOMER GrpCust ON UsrGrp.GROUP_ID = GrpCust.GROUP_ID AND GrpCust.ACTIVE = 1"
								+ " LEFT JOIN CUSTOMER customer on customer.CUSTOMER_ID=GrpCust.CUSTOMER_ID and customer.ACTIVE=1"
								+ " where  Usr.USER_ID = :userId)"
								+ " UNION"
								+ " (select DISTINCT customer.CUSTOMER_ID,Org.ORGANIZATION_ID"
								+ " From USERS Usr"
								+ " INNER JOIN USER_ORG_MAP userOrgMap on  Usr.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
								+ " INNER JOIN ORGANIZATION Org on Org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
								+ " INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
								+ " INNER JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.ACTIVE=1 and userCust.USER_ID=Usr.USER_ID"
								+ " LEFT JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID and customer.ACTIVE=1"
								+ " WHERE Usr.USER_ID = :userId)"
								+ " ) t Group By ORGANIZATION_ID order by ORGANIZATION_ID"
							)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("customerCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> totalCustomerCountArray = query.list();
			for (Object[] totalCustomerCount : totalCustomerCountArray) {

				CustomerTotalCountDto customerTotalCountDto = new CustomerTotalCountDto();
				customerTotalCountDto.setOrganizationId(((BigInteger) totalCustomerCount[0]).longValue());
				customerTotalCountDto.setTotalCustomerCount(((BigInteger) totalCustomerCount[1]).longValue());
				customerTotalCountList.add(customerTotalCountDto);
			}
			LOGGER.debug("Total assigne customer count: "+customerTotalCountList.toString());
			
		} catch (HibernateException e) {
			LOGGER.error("Exception in getTotalCustomerCount " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Exception in getTotalCustomerCount " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println("The count for customers in DAO impl :"+customerTotalCountList.size());
		return customerTotalCountList;
	}

	/**
	 * 
	 */
	@Override
	public List<CatalogTotalCountDto> getTotalCatalogCount(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<CatalogTotalCountDto> catalogTotalCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select organizationId,count(DISTINCT groupCatalogCount) as catalogCount from ("
								+ " (SELECT org.ORGANIZATION_ID organizationId, catalog.CATALOG_ID as groupCatalogCount" 
								+ " FROM USERS Usr"
								+ " LEFT JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
								+ " INNER JOIN ORGANIZATION org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
								+ " LEFT JOIN GROUPS Grp ON	org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND	Grp.ACTIVE = 1"
								+ " LEFT JOIN USER_GROUP UsrGrp ON Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND UsrGrp.ACTIVE = 1"
								+ " LEFT JOIN GROUP_CATALOG GrpCat ON	UsrGrp.GROUP_ID = GrpCat.GROUP_ID AND	GrpCat.ACTIVE = 1"
								+ " LEFT JOIN CATALOG catalog ON catalog.CATALOG_ID=GrpCat.CATALOG_ID AND catalog.ACTIVE=1 AND catalog.ORGANIZATION_ID=org.ORGANIZATION_ID"
								+ " where  Usr.USER_ID = :userId)"
								+ " UNION"
								+ " (select org.ORGANIZATION_ID organizationId , userCat.CATALOG_ID as catalogsCount"
								+ " From USERS Usr"
								+ " LEFT JOIN USER_ORG_MAP userOrgMap on  Usr.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
								+ " INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
								+ " INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
								+ " LEFT JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.ACTIVE=1 and userCust.USER_ID=Usr.USER_ID"
								+ " LEFT JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID and customer.ACTIVE=1"
								+ " LEFT JOIN CATALOG cat on cat.CUSTOMER_ID=customer.CUSTOMER_ID and cat.ACTIVE=1 AND cat.ORGANIZATION_ID=org.ORGANIZATION_ID "
								+ " LEFT JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=Usr.USER_ID"
								+ " where  Usr.USER_ID = :userId)"
								+ " ) t Group By organizationId order by organizationId"
								)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("catalogCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> totalCatalogCountArray = query.list();
			for (Object[] totalCatalogCount : totalCatalogCountArray) {

				CatalogTotalCountDto catalogTotalCountDto = new CatalogTotalCountDto();
				catalogTotalCountDto.setOrganizationId(((BigInteger) totalCatalogCount[0]).longValue());
				catalogTotalCountDto.setTotalCatalogCount(((BigInteger) totalCatalogCount[1]).longValue());
				catalogTotalCountList.add(catalogTotalCountDto);
			}
			LOGGER.debug("Total assigne catalog count: "+catalogTotalCountList.toString());
			
		} catch (HibernateException e) {
			LOGGER.error("Exception in getTotalCatalogCount " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Exception in getTotalCatalogCount " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return catalogTotalCountList;
	}
	
	
	/**
	 * 
	 */
	@Override
	public List<GroupTotalCountDto> getGroupCount(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<GroupTotalCountDto> groupCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							  "SELECT Org.ORGANIZATION_ID organizationId,Count(DISTINCT UsrGrp.GROUP_ID) groupCount"
							+ " FROM USERS Usr"
							+ " LEFT JOIN USER_ORG_MAP UsrOrgMap ON Usr.USER_ID = UsrOrgMap.USER_ID AND UsrOrgMap.ACTIVE = 1"
							+ " INNER JOIN ORGANIZATION Org ON Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
							+ " LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND Grp.ACTIVE = 1"
							+ " LEFT JOIN USER_GROUP UsrGrp ON	Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND	UsrGrp.ACTIVE = 1"
							+ " WHERE Usr.USER_ID = :userId"
							+ " Group By Usr.USER_ID, Org.ORGANIZATION_ID"
							+ " ORDER BY Usr.USER_ID, Org.ORGANIZATION_ID")
					
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("groupCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> groupCountArray = query.list();
			for (Object[] groupCount : groupCountArray) {
				GroupTotalCountDto groupTotalCountDto = new GroupTotalCountDto();
				groupTotalCountDto.setOrganizationId(((BigInteger) groupCount[0]).longValue());
				groupTotalCountDto.setTotalGroupCount(((BigInteger) groupCount[1]).longValue());
				groupCountList.add(groupTotalCountDto);
			}
			
			LOGGER.debug("Organization Group count Data: "+groupCountList.toString());
		} catch (HibernateException e) {
			LOGGER.error("Exception in getGroupCount " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getGroupCount " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return groupCountList;
	}
	
	/**
	 * 
	 */
	@Override
	public List<OrganizationRoleCountDto> getRoleCountByOrganization(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<OrganizationRoleCountDto> organizationRoleCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"SELECT UsrOrgMap.CREATED_DATE as createdDate,UsrOrgMap.MODIFIED_DATE as modifiedDate,Usr.LAST_LOGIN_DATE as lastLoginDate,Org.ORGANIZATION_ID organizationId,Count(userOrgRole.ROLE_ID) as roleCount,UsrOrgMap.APPROVAL_STATUS as approvalStatus,Org.ORGANIZATION_NAME as organizationName"
									+ " FROM	USERS Usr"
									+ " LEFT JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
									+ " INNER JOIN ORGANIZATION Org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
									+ " LEFT JOIN USER_ORGANIZATION_ROLE userOrgRole ON	Org.ORGANIZATION_ID = userOrgRole.ORGANIZATION_ID AND	userOrgRole.ACTIVE = 1 AND userOrgRole.USER_ID=Usr.USER_ID"
									+ " WHERE Usr.USER_ID = :userId"
									+ " Group By Usr.USER_ID, Usr.USER_NAME, Org.ORGANIZATION_ID,Org.ORGANIZATION_NAME,UsrOrgMap.CREATED_DATE,UsrOrgMap.MODIFIED_DATE,Usr.LAST_LOGIN_DATE,UsrOrgMap.APPROVAL_STATUS"
									+ " ORDER BY Usr.USER_ID, Org.ORGANIZATION_ID")
					.addScalar("createdDate", StandardBasicTypes.DATE)
					.addScalar("modifiedDate", StandardBasicTypes.DATE)
					.addScalar("lastLoginDate", StandardBasicTypes.DATE)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("roleCount", StandardBasicTypes.BIG_INTEGER)
			        .addScalar("approvalStatus",StandardBasicTypes.BIG_INTEGER)
			        .addScalar("organizationName",StandardBasicTypes.STRING);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> organizationRoleCountArray = query.list();
			for (Object[] organizationRoleCount : organizationRoleCountArray) {
				
				OrganizationRoleCountDto organizationRoleCountDto = new OrganizationRoleCountDto();
				organizationRoleCountDto.setCreatedDate((Date)organizationRoleCount[0]);
				organizationRoleCountDto.setModifiedDate((Date)organizationRoleCount[1]);
				organizationRoleCountDto.setLastLoginDate((Date)organizationRoleCount[2]);
				organizationRoleCountDto.setOrganizationId(((BigInteger) organizationRoleCount[3]).longValue());
				organizationRoleCountDto.setRoleCount(((BigInteger) organizationRoleCount[4]).longValue());
				organizationRoleCountDto.setApprovalStatus(((BigInteger) organizationRoleCount[5]).longValue());
				organizationRoleCountDto.setOrganizationName(((String) organizationRoleCount[6]));
				organizationRoleCountList.add(organizationRoleCountDto);
			}
			LOGGER.debug("Organization Role count: "+organizationRoleCountList.size());
			LOGGER.debug("Organization Role count Data: "+organizationRoleCountList.toString());
		} catch (HibernateException e) {
			LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return organizationRoleCountList;
	}
	
	/**
	 * 
	 */
	@Override
	public List<DefaultAddressCountDto> getDefaultAddressCountByOrganization(long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		List<DefaultAddressCountDto> defaultAddressCountList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"select userOrgMap.ORGANIZATION_ID organizationId,count(address.ADDRESS_ID) as defaultAddressCount"
						    +" From USERS users"
						    +" LEFT JOIN USER_ORG_MAP userOrgMap on  users.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
						    +" INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
						    +" LEFT JOIN USER_ORG_BILL_SHIP_MAP  userOrgBillShipMap on  userOrgBillShipMap.USER_ORG_ID=userOrgMap.USER_ORG_ID AND userOrgBillShipMap.ACTIVE=1"
						    +" LEFT JOIN ADDRESS address on userOrgBillShipMap.BILL_TO_ADDRESS_ID=address.ADDRESS_ID AND (address.ADDRESS_TYPE_ID=1 or address.ADDRESS_TYPE_ID=2)"
							+" WHERE users.USER_ID = :userId" 
						    +" Group By users.USER_ID,org.ORGANIZATION_NAME,userOrgMap.ORGANIZATION_ID,userOrgMap.APPROVAL_STATUS"
						    +" Order By users.USER_ID,org.ORGANIZATION_NAME")
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("defaultAddressCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);

			@SuppressWarnings("unchecked")
			List<Object[]> defaultAddressCountArray = query.list();
			for (Object[] defaultAddressCount : defaultAddressCountArray) {
				
				DefaultAddressCountDto defaultAddressCountDto = new DefaultAddressCountDto();
				defaultAddressCountDto.setOrganizationId(((BigInteger) defaultAddressCount[0]).longValue());
				defaultAddressCountDto.setDefaultAddressCount(((BigInteger) defaultAddressCount[1]).longValue());
				defaultAddressCountList.add(defaultAddressCountDto);
			}
			LOGGER.debug("Default Address count: "+defaultAddressCountList.size());
			LOGGER.debug("Default Address count Data: "+defaultAddressCountList.toString());
		} catch (HibernateException e) {
			LOGGER.error("Exception in getDefaultAddressCountByOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getDefaultAddressCountByOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return defaultAddressCountList;
	}
	
	/**
	 * this method is used to De-assign all the catalog's that are individually 
	 * assigned to user based on the organization 
	 */
	@Override
	public boolean deAssignCatalogsOfOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for De-assigning user from Org (for catalog's) " + deAssignUserToOrgInputDto.toString());
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		int count=0;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query=session.createSQLQuery("UPDATE USER_CATALOG SET ACTIVE = 0,MODIFIED_BY=:modifiedById ,MODIFIED_DATE=GETDATE() WHERE CATALOG_ID IN "
										 +" ("
										 +" select userCat.CATALOG_ID AS CatalogId"
										 +" From USERS users"
										 +" LEFT JOIN USER_ORG_MAP userOrgMap on  users.USER_ID = userOrgMap.USER_ID AND userOrgMap.ACTIVE = 1"
										 +" INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
										 +" INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on  custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
										 +" INNER JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.USER_ID=users.USER_ID and userCust.ACTIVE=1"
										 +" LEFT JOIN CATALOG cat on cat.CUSTOMER_ID=userCust.CUSTOMER_ID"
										 +" INNER JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=users.USER_ID"
										 +" where users.USER_ID=:userId AND userOrgMap.ORGANIZATION_ID = :organizationId"
										 +" )"
										 +" AND USER_ID = :userId");
			
			query.setLong("modifiedById",deAssignUserToOrgInputDto.getModifiedById());
			query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
			query.setLong("organizationId", deAssignUserToOrgInputDto.getOrganizationId());
			
			
			count=	query.executeUpdate();
			tx.commit();
			if(count>=0){
				LOGGER.info("No,of rows updated while de-assigning catalog for user :"+count);
				result=true;
			}
					
		}catch (HibernateException e) {
		    if (tx != null) {

			if (tx != null) {

		tx.rollback();
	    }
		    }
			result=false;
			LOGGER.error("Exception in deAssignCatalogsOfOrganization " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignCatalogsOfOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * this method is used to De-assign all the customers's that are individually
	 * assigned to user based on the organization 
	 */
	@Override
	public boolean deAssignCustomerOfOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for De-assigning user from Org (for Customers) " + deAssignUserToOrgInputDto.toString());
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		int count=0;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query=session.createSQLQuery("UPDATE USER_CUSTOMER SET ACTIVE = 0,MODIFIED_BY=:modifiedById ,MODIFIED_DATE=GETDATE() WHERE CUSTOMER_ID IN "
										 +" ("
										 +" select userCust.CUSTOMER_ID AS CustID"
										 +" From USERS users"
										 +" LEFT JOIN USER_ORG_MAP userOrgMap on  users.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
										 +" INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
										 +" INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on  custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
										 +" INNER JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.USER_ID=users.USER_ID and userCust.ACTIVE=1"
										 +" where users.USER_ID=:userId AND userOrgMap.ORGANIZATION_ID = :organizationId"
										 +" )"
										 +" AND USER_ID = :userId");
			
			query.setLong("modifiedById",deAssignUserToOrgInputDto.getModifiedById());
			query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
			query.setLong("organizationId", deAssignUserToOrgInputDto.getOrganizationId());
			
			
			count=	query.executeUpdate();
			tx.commit();
			if(count>=0){
				LOGGER.info("No,of rows updated while de-assigning customer for user :"+count);
				result=true;
			}
					
		}catch (HibernateException e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignCustomerOfOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean deAssignGroupOfOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for De-assigning user from Org (for Groups) " + deAssignUserToOrgInputDto.toString());
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		int count=0;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query=session.createSQLQuery("UPDATE USER_GROUP SET ACTIVE = 0,MODIFIED_BY=:modifiedById ,MODIFIED_DATE=GETDATE() WHERE GROUP_ID IN "
										 +" ("
										 +" select UsrGrp.GROUP_ID AS groupID"
										 +" FROM USERS Usr"
										 +" LEFT JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
										 +" INNER JOIN ORGANIZATION Org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
										 +" LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND	Grp.ACTIVE = 1"
										 +" INNER JOIN USER_GROUP UsrGrp ON	Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND	UsrGrp.ACTIVE = 1"
										 +" where Usr.USER_ID=:userId AND UsrOrgMap.ORGANIZATION_ID = :organizationId"
										 +" )"
										 +" AND USER_ID = :userId");
			
			query.setLong("modifiedById",deAssignUserToOrgInputDto.getModifiedById());
			query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
			query.setLong("organizationId", deAssignUserToOrgInputDto.getOrganizationId());
			
			
			count=	query.executeUpdate();
			tx.commit();
			if(count>=0){
				LOGGER.info("No,of rows updated while de-assigning Groups for user :"+count);
				result=true;
			}
					
		}catch (HibernateException e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignGroupOfOrganization " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignGroupOfOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean deAssignRoleOfOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for De-assigning user from Org (for Role) " + deAssignUserToOrgInputDto.toString());
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		int count=0;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query=session.createSQLQuery("UPDATE USER_ORGANIZATION_ROLE SET ACTIVE = 0,MODIFIED_BY=:modifiedById ,MODIFIED_DATE=GETDATE() WHERE ROLE_ID IN "
										 +" ("
										 +" select userOrgRole.ROLE_ID"
										 +" FROM USERS Usr"
										 +" LEFT OUTER JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
										 +" LEFT OUTER JOIN ORGANIZATION Org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
										 +" INNER JOIN USER_ORGANIZATION_ROLE userOrgRole ON	Org.ORGANIZATION_ID = userOrgRole.ORGANIZATION_ID AND	userOrgRole.ACTIVE = 1 AND userOrgRole.USER_ID=Usr.USER_ID"
										 +" where Usr.USER_ID=:userId AND UsrOrgMap.ORGANIZATION_ID = :organizationId"
										 +" )"
										 +" AND USER_ID = :userId"
										 +" AND ORGANIZATION_ID= :organizationId");
			
			query.setLong("modifiedById",deAssignUserToOrgInputDto.getModifiedById());
			query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
			query.setLong("organizationId", deAssignUserToOrgInputDto.getOrganizationId());
			
			
			count=	query.executeUpdate();
			tx.commit();
			if(count>=0){
				LOGGER.info("No,of rows updated while de-assigning Role for user :"+count);
				result=true;
			}
					
		}catch (HibernateException e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignRoleOfOrganization " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignRoleOfOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * while de-assigning user from organization we need to check if there are any active organizations
	 * for user if not we need to de-activate user in users table.
	 */
	@Override
	public boolean deAssignUserFromOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for De-assigning user from Org (for Organization) " + deAssignUserToOrgInputDto.toString());
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		int count=0;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query=session.createSQLQuery("UPDATE USER_ORG_MAP"
										+" SET ACTIVE=0,APPROVAL_STATUS=0,MODIFIED_DATE=GETDATE(),MODIFIED_BY=:modifiedById"
										+" where USER_ID=:userId and ORGANIZATION_ID= :organizationId");
			
			query.setLong("modifiedById",deAssignUserToOrgInputDto.getModifiedById());
			query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
			query.setLong("organizationId", deAssignUserToOrgInputDto.getOrganizationId());
			
			
			count=	query.executeUpdate();
			
			if(count>=0){
				LOGGER.info("No,of rows updated while de-assigning Organization for user :"+count);
				result=true;
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignUserFromOrganization " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in deAssignRoleOfOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}
	
	/**
	 * this method is used to de-activate user from USRES table
	 * if there are no active User_org_map entry.
	 * So we need to make active status as false in USERS table.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean deActivateUser(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		Session session = null;
		SQLQuery query = null;
		Transaction tx = null;
		boolean result=false;
		int count=0;
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			query = session
					.createSQLQuery("select * from USER_ORG_MAP where USER_ID= :userId and ACTIVE=1")
					.addScalar("ORGANIZATION_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("ORGANIZATION_NAME", StandardBasicTypes.STRING);
			
			query.addEntity(UserOrganizationMap.class);
			query.setParameter("userId",deAssignUserToOrgInputDto.getUserId());
			
			
			
			List<UserOrganizationMap> userOrgList=query.list();
			
			if(userOrgList.isEmpty()){
				//de-activate user for USERS table
				query=session.createSQLQuery("update USERS set ACTIVE=0,MODIFIED_BY=:modifiedById,MODIFIED_DATE=GETDATE() where USER_ID= :userId");
				query.setLong("userId", deAssignUserToOrgInputDto.getUserId());
				query.setLong("modifiedById", deAssignUserToOrgInputDto.getModifiedById());
				
				
				count=	query.executeUpdate();
				LOGGER.info("In deActivateUser User DAO :"+ "User is assigned to :"+userOrgList.size()+" Organizations So user is deactivated in USERS table :"+count);
			}
			else{
				LOGGER.info("In deActivateUser User DAO :"+ "User is assigned to :"+userOrgList.size()+" Organiations So user is not deactivated ");
			}
			tx.commit();
			result=true;
		} catch (HibernateException e) {
			result=true;
			LOGGER.error("Exception in getAllOrganizations " + e.getMessage());
			
		} catch (Exception e) {
			result=true;
			LOGGER.error("Exception in getAllOrganizations " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminOrgListDto> getOrganizationListOfAdmin(long adminId) {
		LOGGER.info("Logged in admin id " + adminId);
		Session session = null;
		SQLQuery query = null;
		List<AdminOrgListDto> adminOrgList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							 "select userOrgRole.USER_ID as adminUserId,userOrgRole.ROLE_ID as roleId,userOrgRole.ORGANIZATION_ID as organizationId, organization.ORGANIZATION_NAME as organizationName"
							+" FROM USER_ORGANIZATION_ROLE userOrgRole"
							+" INNER JOIN ORGANIZATION organization on userOrgRole.ORGANIZATION_ID=organization.ORGANIZATION_ID and userOrgRole.ACTIVE=1"		 
							+" where USER_ID=:userId AND (ROLE_ID=4 or ROLE_ID=5)"		 
							+" ORDER BY userOrgRole.ORGANIZATION_ID,organization.ORGANIZATION_NAME"		
							)
					.addScalar("adminUserId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("roleId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("organizationName",StandardBasicTypes.STRING);

			query.setLong("userId", adminId);

			List<Object[]> adminOrgListArray = query.list();
			for (Object[] adminOrg : adminOrgListArray) {
				
				AdminOrgListDto adminOrgListDto=new AdminOrgListDto();
				
				adminOrgListDto.setAdminUserId(((BigInteger) adminOrg[0]).longValue());
				adminOrgListDto.setRoleId(((BigInteger) adminOrg[1]).longValue());
				adminOrgListDto.setOrganizationId(((BigInteger) adminOrg[2]).longValue());
				adminOrgListDto.setOrganizationName(((String) adminOrg[3]).toString());
				
				adminOrgList.add(adminOrgListDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getCustomerCountByOrganization " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return adminOrgList;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDTO> getAllOrganizations() {
		Session session = null;
		SQLQuery query = null;
		List<OrganizationDTO> organizationDTOList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select ORGANIZATION_ID,ORGANIZATION_NAME  from ORGANIZATION order By ORGANIZATION_ID")
					.addScalar("ORGANIZATION_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("ORGANIZATION_NAME", StandardBasicTypes.STRING);
			

			List<Object[]> organizationListArray = query.list();
			for (Object[] org : organizationListArray) {
				
				OrganizationDTO organizationDTO=new OrganizationDTO();
				
				organizationDTO.setOrganizationId(((BigInteger) org[0]).longValue());
				organizationDTO.setOrganizationName((String)org[1]);
				
				
				organizationDTOList.add(organizationDTO);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getAllOrganizations " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getAllOrganizations " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return organizationDTOList;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserUnassignedOrgDto> getUnassignedOrganizationsForUser(long userId, long adminId) {
		System.out.println("getUnassignedOrganizationsForUser dao");
		Session session = null;
		SQLQuery query = null;
		List<UserUnassignedOrgDto> userUnassignedOrgDtoList = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							 "select org.ORGANIZATION_ID as organizationId,org.ORGANIZATION_NAME as organizationName ,userOrgRole.USER_ID as adminID"
							+" FROM ORGANIZATION org"
							+" LEFT OUTER JOIN USER_ORGANIZATION_ROLE userOrgRole"
							+" on org.ORGANIZATION_ID=userOrgRole.ORGANIZATION_ID"
							+" AND userOrgRole.USER_ID=:adminId"
							+" AND ROLE_ID=4"
							+" AND userOrgRole.ACTIVE=1"
							+" where org.ORGANIZATION_ID NOT IN (select org.ORGANIZATION_ID from ORGANIZATION org join USER_ORG_MAP uom on uom.ORGANIZATION_ID=org.ORGANIZATION_ID where  uom.ACTIVE=1 and uom.USER_ID=:userId)"
							+" order By org.ORGANIZATION_ID"		
							)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("organizationName", StandardBasicTypes.STRING)
					.addScalar("adminID", StandardBasicTypes.BIG_INTEGER);
			
			
			query.setLong("adminId",adminId);
			query.setLong("userId",userId);
			
			
			List<Object[]> userUnassignedOrgDtoArray = query.list();
			for (Object[] obj : userUnassignedOrgDtoArray) {
				
				UserUnassignedOrgDto userUnassignedOrgDto=new UserUnassignedOrgDto();
				
				userUnassignedOrgDto.setOrganizationId(((BigInteger) obj[0]).longValue());
				userUnassignedOrgDto.setOrganizationName((String)obj[1]);
				if(obj[2]!=null){
				userUnassignedOrgDto.setAdminId(((BigInteger) obj[2]).longValue());
				userUnassignedOrgDto.setAdminAccess(true);
				userUnassignedOrgDtoList.add(userUnassignedOrgDto);
				}/*else{
					userUnassignedOrgDto.setAdminId(0);
					userUnassignedOrgDto.setAdminAccess(false);
				}
				*/
				
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getUnassignedOrganizationsForUser " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getUnassignedOrganizationsForUser " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println("userUnassignedOrgDtoList in dao :"+userUnassignedOrgDtoList);
		return userUnassignedOrgDtoList;
	}
	
	/**
	 * here the logic is 
	 * 1.)if we are saving the user to organization then it should be saving a new record to DB,then we need to set userId,OrgId and created by
	 * and modified by values and save the record
	 * 
	 * 2.)If record already exists then we need to update the record set Active flag and ApprovelStatus to true,modifiedBy and Modified Date
	 * 
	 * @param userOrganizationMap
	 * @param deAssignUserToOrgInputDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean insertOrUpdateUserOrganizationMap(UserOrganizationMap userOrganizationMap,AssignOrgDto deAssignUserToOrgInputDto) {
		LOGGER.info("Input for insertOrUpdateUserOrganizationMap " + userOrganizationMap.toString());
		LOGGER.info("Input for insertOrUpdateUserOrganizationMap " + deAssignUserToOrgInputDto.toString());
		
		Session session = null;
		Transaction tx = null;
		SQLQuery query = null;
		
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			
			query = session.createSQLQuery("select * from USER_ORG_MAP where USER_ID=:userId and ORGANIZATION_ID=:organizationId");
			query.addEntity(UserOrganizationMap.class);
			query.setParameter("userId",deAssignUserToOrgInputDto.getUserId());
			query.setParameter("organizationId",userOrganizationMap.getOrganizationId());
			
			List<UserOrganizationMap> userOrgList=query.list();
			
			if(userOrgList!=null && !userOrgList.isEmpty()){
				LOGGER.info("update approvel status for userOrg entry for user in user org map");
				for(int i=0;i<userOrgList.size();i++){
					
					UserOrganizationMap userOrganizationMapEntity=new UserOrganizationMap();
					userOrganizationMapEntity=userOrgList.get(i);
					//change the values, if admin try's to add the user to org the approval status is false else approval status is true
					userOrganizationMapEntity.setActive(true);
					if(deAssignUserToOrgInputDto.isAddUserToOrg()){
					userOrganizationMapEntity.setApprovalStatus(false);
					}else{
					userOrganizationMapEntity.setApprovalStatus(true);	
					}
					userOrganizationMapEntity.setModifiedBy(deAssignUserToOrgInputDto.getModifiedById());
					userOrganizationMapEntity.setModifiedDate(new Date());
					
					session.update(userOrganizationMapEntity);
					result=true;
				}
			}else{
				LOGGER.info("create the entry for user in user org map");
				UserOrganizationMap userOrganizationMapEntity=new UserOrganizationMap();
				User user=(User) session.get(User.class, deAssignUserToOrgInputDto.getUserId());
				//set User Id
				user.setUserId(deAssignUserToOrgInputDto.getUserId());
				
				userOrganizationMapEntity.setOrganizationId(userOrganizationMap.getOrganizationId());
				userOrganizationMapEntity.setCreatedBy(deAssignUserToOrgInputDto.getModifiedById());
				userOrganizationMapEntity.setCreatedDate(new Date());
				userOrganizationMapEntity.setActive(true);
				userOrganizationMapEntity.setApprovalStatus(false);
				userOrganizationMapEntity.setTermsCondition(true);
				userOrganizationMapEntity.setUser(user);
				
				session.save(userOrganizationMapEntity);
				result=true;
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in insertOrUpdateUserOrganizationMap " + e.getMessage());
			
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			result=false;
			LOGGER.error("Exception in insertOrUpdateUserOrganizationMap " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return result;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<UserNotesDto> getNotesListForUser(long userId) {
		LOGGER.info("getNotesListForUser() " + userId);
		Session session = null;
		SQLQuery query = null;
		List<UserNotesDto> userNotesDtoList=new ArrayList<>();
		try{
			session = sessionFactory.openSession();
			
			//query = session.createSQLQuery("select un.user_id as userId,un.note as notes,un.CREATED_DATE as createdDate,u.user_name as userName from USER_NOTES un inner join USERS u on u.user_id=un.CREATED_BY where un.USER_ID= :userId order by un.CREATED_DATE desc");
			query=session.createSQLQuery(GETNOTEFORUSER);
			query.setParameter("userId",userId);
			query.addScalar("userId", LongType.INSTANCE);
			query.addScalar("notes", StringType.INSTANCE);
			query.addScalar("createdDate", StandardBasicTypes.TIMESTAMP);
			query.addScalar("userName", StringType.INSTANCE);
			userNotesDtoList = query.setResultTransformer( Transformers.aliasToBean(UserNotesDto.class)).list();
			
			LOGGER.debug(userNotesDtoList);
			
		}catch (HibernateException e) {
			
			LOGGER.error("Exception in getNotesListForUser " + e.getMessage());
			
		} catch (Exception e) {
			
			LOGGER.error("Exception in getNotesListForUser " + e.getMessage());
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return userNotesDtoList;
	}

	@Override
	public boolean addNotesToUser(UserNotesDto userNotesDto) {
		LOGGER.info("addNotesToUser() " + userNotesDto.toString());
		Session session = null;
		Transaction tx = null;
		boolean result=false;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			
			UserNotes notes=new UserNotes();
			User user=(User) session.get(User.class, userNotesDto.getUserId());
			
			notes.setUser(user);
			notes.setNotes(userNotesDto.getNotes());
			notes.setCreatedBy(userNotesDto.getCreatedBy());
			notes.setCreatedDate(new Date());
			
			session.save(notes);
			result=true;
			
			tx.commit();
			}
			
		catch (HibernateException e) {
			LOGGER.error("Exception in getNotesListForUser " + e.getMessage());
			result=false;
			if (tx != null) {

		tx.rollback();
	    }
			
		} catch (Exception e) {
			LOGGER.error("Exception in getNotesListForUser " + e.getMessage());
			result=false;
			if (tx != null) {

		tx.rollback();
	    }
			
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
		
	}

	/**
	 * this method is used to get the default address that is assigned to
	 * user (i.e) BillTo,shipTO,customerId,salesAreaId
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DefaultAddressCheckDto getDefaultAddressForUserOrg(long userOrgId) {
		LOGGER.info("getDefaultAddressForUserOrg() based on sales area " + userOrgId);
		Session session = null;
		Query query = null;
		SQLQuery sqlBillToQuery = null;
		SQLQuery sqlShipToQuery = null;

		DefaultAddressCheckDto defaultAddressCheckObj = new DefaultAddressCheckDto();
		try {
			session = sessionFactory.openSession();

			String salesAreaQuery = "select sa.SALES_AREA_ID as salesArea, sa.SALES_ORG_NAME as salesOrgName,sa.DISTRIBUTION_CHANNEL_NAME as disChannel FROM USER_ORG_SALES_AREA_MAP uosam inner join SALES_AREA sa on uosam.SALES_AREA_ID=sa.SALES_AREA_ID and sa.ACTIVE=1" 
									+" WHERE uosam.USER_ORG_ID = :userOrgId and uosam.active=1";
			query = session.createSQLQuery(salesAreaQuery)
					.addScalar("salesArea", StandardBasicTypes.LONG)
					.addScalar("salesOrgName", StandardBasicTypes.STRING)
					.addScalar("salesArea", StandardBasicTypes.STRING);
			query.setParameter("userOrgId", userOrgId);
			List<Object[]> salesAreaIdList = query.list();

			if (salesAreaIdList.isEmpty() == false && salesAreaIdList != null) {
				Object[] object=salesAreaIdList.get(0);
				defaultAddressCheckObj.setSalesAreaId((long)object[0]);
				defaultAddressCheckObj.setSalesAreaName((String)object[1]);
			}

			sqlBillToQuery = session
					.createSQLQuery(
							"select userOrgBillShipMap.CUSTOMER_ID customerId,userOrgBillShipMap.BILL_TO_ADDRESS_ID billToAddressID,cu.CUSTOMER_NAME customerName"
									+ " FROM USER_ORG_BILL_SHIP_MAP userOrgBillShipMap"
									+ " INNER JOIN CUSTOMER cu on userOrgBillShipMap.CUSTOMER_ID=cu.CUSTOMER_ID and cu.ACTIVE=1"
									+ " INNER JOIN ADDRESS address on address.ADDRESS_ID=userOrgBillShipMap.BILL_TO_ADDRESS_ID and address.ADDRESS_TYPE_ID=1"
									+ " WHERE userOrgBillShipMap.USER_ORG_ID= :userOrgId and userOrgBillShipMap.ACTIVE=1")
					.addScalar("customerId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("billToAddressID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("customerName", StandardBasicTypes.STRING);

			sqlBillToQuery.setLong("userOrgId", userOrgId);

			List<Object[]> objectBillToArray = sqlBillToQuery.list();
			for (Object[] object : objectBillToArray) {
				defaultAddressCheckObj.setCustomerId(((BigInteger) object[0]).longValue());
				defaultAddressCheckObj.setBillToAddressId(((BigInteger) object[1]).longValue());
				defaultAddressCheckObj.setCustomerName((String)object[2]);

			}

			sqlShipToQuery = session
					.createSQLQuery(
							"select userOrgBillShipMap.CUSTOMER_ID customerId,userOrgBillShipMap.SHIP_TO_ADDRESS_ID shipToAddressID"
									+ " FROM USER_ORG_BILL_SHIP_MAP userOrgBillShipMap"
									+ " INNER JOIN ADDRESS address on address.ADDRESS_ID=userOrgBillShipMap.SHIP_TO_ADDRESS_ID and address.ADDRESS_TYPE_ID=2"
									+ " WHERE userOrgBillShipMap.USER_ORG_ID= :userOrgId and userOrgBillShipMap.ACTIVE=1")
					.addScalar("customerId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("shipToAddressID", StandardBasicTypes.BIG_INTEGER);

			sqlShipToQuery.setLong("userOrgId", userOrgId);

			List<Object[]> objectShipToArray = sqlShipToQuery.list();
			for (Object[] object : objectShipToArray) {
				defaultAddressCheckObj.setCustomerId(((BigInteger) object[0]).longValue());
				defaultAddressCheckObj.setShipToAddressId(((BigInteger) object[1]).longValue());

			}

		} catch (HibernateException e) {
			LOGGER.error("Exception in getDefaultAddressForUserOrg " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getDefaultAddressForUserOrg " + e.getMessage());
			
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return defaultAddressCheckObj;
	}	
}