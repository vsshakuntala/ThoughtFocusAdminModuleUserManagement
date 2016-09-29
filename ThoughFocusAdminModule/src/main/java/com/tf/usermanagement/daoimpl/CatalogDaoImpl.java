package com.tf.usermanagement.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.CatalogDao;
import com.tf.usermanagement.dto.CatalogDto;
import com.tf.usermanagement.dto.CatalogTotalCountDto;
import com.tf.usermanagement.dto.CustomerCatalogCountDto;
import com.tf.usermanagement.dto.GroupCatalogCountDto;

/**
 * @author abhilash
 *
 */
@Repository
public class CatalogDaoImpl implements CatalogDao {

	private static final String CATALOGCOUNTFORORG = "select DISTINCT count(*) as count from CATALOG where ORGANIZATION_ID =:orgId and ACTIVE =1";


	
	private static final String CATALOGCOUNTFORDIRECTACTIVE = "select DISTINCT (c.CATALOG_ID), count(*) as count  from CATALOG c  INNER JOIN USER_CATALOG"
			+ " uc on uc.CATALOG_ID=c.CATALOG_ID " + " where ORGANIZATION_ID =:orgId and uc.USER_ID =:userId and (uc.ACTIVE=1 and c.ACTIVE=1) GROUP BY c.CATALOG_ID ";
	
	private static final String CATALOGCOUNTFORGROUPCTACTIVE ="select DISTINCT (gc.CATALOG_ID), count(*) as count"
			+ "  from CATALOG c  INNER JOIN GROUP_CATALOG gc on gc.CATALOG_ID=c.CATALOG_ID "
			+ "INNER JOIN USER_GROUP ug on ug.GROUP_ID=gc.GROUP_ID  where ORGANIZATION_ID =:orgId " + "and ug.USER_ID =:userId and( (gc.ACTIVE=1 and c.ACTIVE=1) and (gc.ACTIVE=1 and ug.ACTIVE=1)) GROUP BY gc.CATALOG_ID";
	
	private static final String ORGID = "orgId";
	private static final String USERID = "userId";

	@Autowired
	private SessionFactory sessionFactroy;

	private static final Logger LOGGER = Logger.getLogger(CatalogDaoImpl.class);

	@Override
	public Integer catalogCountBasedOnOrg(Long orgId) {
		Session session = null;
		Query query = null;
		Integer count = null;
		try {

			session = sessionFactroy.openSession();
			query = session.createSQLQuery(CATALOGCOUNTFORORG);
			query.setParameter(ORGID, orgId);
			count = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			LOGGER.error("Exception in catalogCountBasedOnOrg " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(" Exception in catalogCountBasedOnOrg  " + e.getMessage());
		} finally {
			closeHibernateSession(session);
		}

		return count;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCatalogCount(long orgId, long userId) {

		Session session = null;
		SQLQuery query = null;
		Integer count = null;
		List<Integer> list1 = null;
		List<Integer> list2 = null;
		Set<Integer> set = new HashSet<>();

		try {

			session = sessionFactroy.openSession();
			query = session.createSQLQuery(CATALOGCOUNTFORDIRECTACTIVE);

			query.setParameter(ORGID, orgId);
			query.setParameter(USERID, userId);
			List<Object[]> objects = query.list();

			CatalogDto dto = new CatalogDto();

			if (objects != null && !objects.isEmpty()) {
				list1 = new ArrayList<>();
				for (Object[] obj : objects) {

					dto.setCatalogId((BigInteger) obj[0]);
					Integer id = dto.getCatalogId().intValue();
					list1.add(id);
					LOGGER.info("list-1 size is " + list1.size());

					LOGGER.info("catalogId  " + dto.getCatalogId());
					dto.setCatalogCount((Integer) obj[1]);
					LOGGER.info("catalog count  " + dto.getCatalogCount());

				}

			}
			query = session.createSQLQuery(CATALOGCOUNTFORGROUPCTACTIVE);
			query.setParameter(ORGID, orgId);
			query.setParameter(USERID, userId);

			List<Object[]> rows = null;
			rows = query.list();
			if (rows != null && !rows.isEmpty()) {
				list2 = new ArrayList<>();
				for (Object[] row : rows) {
					CatalogDto dto2 = new CatalogDto();

					dto2.setCatalogId((BigInteger) row[0]);
					Integer id1 = dto2.getCatalogId().intValue();
					list2.add(id1);
					LOGGER.info("list-2 size is " + list2.size());
					LOGGER.info("catalogId  " + dto2.getCatalogId());

					dto2.setCatalogCount((Integer) row[1]);
					LOGGER.info("catalog count  " + dto2.getCatalogCount());
				}
			}
			if (list1 != null) {
				set.addAll(list1);
			} else if (list2 != null) {
				set.addAll(list2);
			}
			LOGGER.info("After removing duplicate size is " + set.size());
			count = set.size();
			LOGGER.info("Count is " + count);

		} catch (HibernateException e) {
			LOGGER.error("Exception in getCatalogCount " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			closeHibernateSession(session);
		}
		return count;

	}

	private void closeHibernateSession(Session session) {
		if (session != null) {
			session.close();
		}
	}



	
	



	@Override
	public Long getCatalogDirectAssignedCount(long orgId, long userId) {
		long directCatalogCount=0;
		
		
		LOGGER.info("User id " + userId);
		LOGGER.info("Org id " + orgId);
		Session session = null;
		SQLQuery directCountQuery = null;
		
		
		try {
			session = sessionFactroy.openSession();
			directCountQuery=session
					.createSQLQuery(
"select userOrgMap.ORGANIZATION_ID organizationId ,count(DISTINCT userCat.CATALOG_ID) as catalogsCount"
+" From USERS users"
+" LEFT JOIN USER_ORG_MAP userOrgMap on  users.USER_ID = userOrgMap.USER_ID AND	userOrgMap.ACTIVE = 1"
+" INNER JOIN ORGANIZATION org on org.ORGANIZATION_ID=userOrgMap.ORGANIZATION_ID"
+" INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.ORGANIZATION_ID=org.ORGANIZATION_ID and custOrgMap.ACTIVE=1"
+" LEFT JOIN USER_CUSTOMER userCust on  custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and userCust.ACTIVE=1 and userCust.USER_ID=users.USER_ID"
+" LEFT JOIN CATALOG cat on cat.CUSTOMER_ID=userCust.CUSTOMER_ID"
+" LEFT JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=users.USER_ID"
+" WHERE users.USER_ID = :userId and Org.ORGANIZATION_ID= :orgId" 
+" Group By users.USER_ID,org.ORGANIZATION_NAME,userOrgMap.ORGANIZATION_ID,userOrgMap.APPROVAL_STATUS"
+" Order By users.USER_ID,org.ORGANIZATION_NAME")
			.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
			.addScalar("catalogsCount", StandardBasicTypes.BIG_INTEGER);	
			
			directCountQuery.setLong("userId", userId);
			directCountQuery.setLong("orgId", orgId);
			
			List<Object[]> catalogDirectAssignmentArray = directCountQuery.list();
			for (Object[] directCatalogAssignment : catalogDirectAssignmentArray) {
				directCatalogCount=((BigInteger) directCatalogAssignment[1]).longValue();
			}
		
			
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCatalogDirectAssignedCount " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getCatalogDirectAssignedCount " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println("In DAO machine direct assignmnet count "+directCatalogCount);
		return directCatalogCount;
	}



	@Override
	public Long getCatalogGroupAssignedCount(long orgId, long userId) {
		long groupCatalogCount=0;
		
		
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery groupCountQuery = null;
		
		
		try {
			session = sessionFactroy.openSession();
			groupCountQuery = session
					.createSQLQuery(
							"SELECT Org.ORGANIZATION_ID organizationId,Count(DISTINCT GrpCat.CATALOG_ID) groupCatalogCount"
							+" FROM	USERS Usr"
							+" LEFT JOIN USER_ORG_MAP UsrOrgMap ON	Usr.USER_ID = UsrOrgMap.USER_ID AND	UsrOrgMap.ACTIVE = 1"
							+" INNER JOIN ORGANIZATION Org ON	Org.ORGANIZATION_ID = UsrOrgMap.ORGANIZATION_ID"
							+" LEFT JOIN GROUPS Grp ON	Org.ORGANIZATION_ID = Grp.ORGANIZATION_ID AND	Grp.ACTIVE = 1"
							+" LEFT JOIN USER_GROUP UsrGrp ON Usr.USER_ID = UsrGrp.USER_ID AND Grp.GROUP_ID = UsrGrp.GROUP_ID AND UsrGrp.ACTIVE = 1"
							+" LEFT JOIN GROUP_CATALOG GrpCat ON	UsrGrp.GROUP_ID = GrpCat.GROUP_ID AND	GrpCat.ACTIVE = 1"
							+" WHERE Usr.USER_ID = :userId and Org.ORGANIZATION_ID= :orgId" 
							+" Group By Usr.USER_ID, Usr.USER_NAME, Org.ORGANIZATION_ID,Org.ORGANIZATION_NAME"
							+" ORDER BY Usr.USER_ID, Org.ORGANIZATION_ID")
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("groupCatalogCount", StandardBasicTypes.BIG_INTEGER);

			groupCountQuery.setLong("userId", userId);
			groupCountQuery.setLong("orgId", orgId);
			
			
			List<Object[]> groupCatalogArray = groupCountQuery.list();
			for (Object[] groupCatalog : groupCatalogArray) {
				groupCatalogCount=((BigInteger) groupCatalog[1]).longValue();
			}
		
			
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCatalogGroupAssignedCount " + e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("Exception in getCatalogGroupAssignedCount " + e.getMessage());
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return groupCatalogCount;
	}



	@Override
	public Long getCatalogAssignedCount(long userId, long orgId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		long catalogCount=0;
		List<CatalogTotalCountDto> catalogTotalCountList = new ArrayList<>();
		try {
			session = sessionFactroy.openSession();
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
								+ " where  Usr.USER_ID = :userId and org.ORGANIZATION_ID= :orgId)"
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
								+ " where  Usr.USER_ID = :userId and org.ORGANIZATION_ID= :orgId)"
								+ " ) t Group By organizationId order by organizationId"
								)
					.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
					.addScalar("catalogCount", StandardBasicTypes.BIG_INTEGER);

			query.setLong("userId", userId);
			query.setLong("orgId", orgId);
			
			@SuppressWarnings("unchecked")
			List<Object[]> totalCatalogCountArray = query.list();
			for (Object[] totalCatalogCount : totalCatalogCountArray) {

				CatalogTotalCountDto catalogTotalCountDto = new CatalogTotalCountDto();
				catalogTotalCountDto.setOrganizationId(((BigInteger) totalCatalogCount[0]).longValue());
				catalogTotalCountDto.setTotalCatalogCount(((BigInteger) totalCatalogCount[1]).longValue());
				catalogTotalCountList.add(catalogTotalCountDto);
			}
			CatalogTotalCountDto catalogTotalCountDto = new CatalogTotalCountDto();
			catalogTotalCountDto=catalogTotalCountList.get(0);
			catalogCount=catalogTotalCountDto.getTotalCatalogCount();
			
			
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
		return catalogCount;
	}

}
