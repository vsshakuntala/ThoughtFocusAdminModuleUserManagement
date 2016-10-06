package com.tf.usermanagement.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tf.usermanagement.dao.MachineAssignmentDao;
import com.tf.usermanagement.domain.UserCatalog;
import com.tf.usermanagement.domain.UserCatalogPK;
import com.tf.usermanagement.dto.CatalogCountForOrgDto;
import com.tf.usermanagement.dto.CatalogTotalCountDto;
import com.tf.usermanagement.dto.MachineDownloadDto;
import com.tf.usermanagement.utils.DateTimeUtil;

@Repository
public class MachineAssignmentDaoimpl implements MachineAssignmentDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineAssignmentDaoimpl.class);
	private static final String AVAILABLE_COUNT_QUERY = "select  count(distinct Cat.CATALOG_ID )"
			+ " from USERS Usr "
			+ " inner join USER_ORG_MAP UsrOrg on Usr.USER_ID = UsrOrg.USER_ID AND  UsrOrg.ACTIVE = 1 "
			+ " inner join ORGANIZATION Org on Org.ORGANIZATION_ID = UsrOrg.ORGANIZATION_ID "
			+ " inner join CUSTOMER_ORGANIZATION_MAP CustOrg on UsrOrg.ORGANIZATION_ID =CustOrg.ORGANIZATION_ID AND CustOrg.ACTIVE = 1 "
			+ " inner join CUSTOMER Cust on Cust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND Cust.ACTIVE = 1 "
			+ " inner join USER_CUSTOMER UsrCust on CustOrg.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND UsrCust.ACTIVE = 1 AND Usr.USER_ID = UsrCust.USER_ID "
			+ " inner join CATALOG Cat on UsrCust.CUSTOMER_ID = Cat.CUSTOMER_ID AND Cat.ORGANIZATION_ID = CustOrg.ORGANIZATION_ID AND Cat.ACTIVE = 1 "
			+ " where Usr.USER_ID =:userId and Org.ORGANIZATION_ID=:orgId";

	@SuppressWarnings("unchecked")
	@Override
	public List<MachineDownloadDto> getAllAssignedMachineList(String query) {
		Session session = null;
		List<MachineDownloadDto> list = null;
		try {
			session = sessionFactory.openSession();
			list = session.createSQLQuery(query).addScalar("catalog_id", LongType.INSTANCE)
					.addScalar("model", StringType.INSTANCE).addScalar("catalog_reference", StringType.INSTANCE)
					.addScalar("customer_name", StringType.INSTANCE)
					.addScalar("group_name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(MachineDownloadDto.class)).list();

		} catch (HibernateException e) {
			LOGGER.error("Exception in getAllAssignedMachineList " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getAllAssignedMachineList " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;

	}

	/**
	 * used to assign the catalog for a user input- user id, list of catalogs
	 * which have to assign for user
	 * 
	 * 
	 */
	@Override
	@Transactional
	public String assignMachine(Long loginUserId, Long user_id, Long orgId, List<Long> machineList) {
		String msg = null;

		try {
			if (machineList != null && !machineList.isEmpty()) {
				LOGGER.info("inside the new list dao");
				for (Long catalog_id : machineList) {
					LOGGER.info("inside the new list dao1 :" + catalog_id);
					UserCatalogPK pk = new UserCatalogPK();
					pk.setCatalogId(catalog_id);
					pk.setUserId(user_id);

					UserCatalog userCatalog = (UserCatalog) em.find(UserCatalog.class, pk);
					if (userCatalog != null) {
						LOGGER.info("inside the assign service new list dao3 update");
						userCatalog.setActive(true);
						userCatalog.setModifiedBy(loginUserId);
						userCatalog.setModifiedDate(DateTimeUtil.getSqlTimeStamp());

					} else {
						LOGGER.info("inside the new list dao3 create");
						UserCatalog userCatalog1 = new UserCatalog();
						userCatalog1.setActive(true);
						userCatalog1.setCatalogId(catalog_id.longValue());
						userCatalog1.setUserId(user_id);
						userCatalog1.setCreatedBy(loginUserId);
						userCatalog1.setCreatedDate(DateTimeUtil.getSqlTimeStamp());
						userCatalog1.setId(pk);
						em.persist(userCatalog1);

					}
				}
				msg = "Successfully catalog is assigned";
				return msg;
			} else {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while assigning the catalog " + e.getMessage());
		}

		return msg;
	}

	/**
	 * used to remove the catalog for a user input- user id, list of catalogs
	 * which have to remove for user
	 * 
	 */
	@Override
	@Transactional
	public String removeMachine(Long loginUserId, Long user_id, Long orgId, List<Long> machineList) {
		String msg = null;
		try {
			if (machineList != null && !machineList.isEmpty()) {
				LOGGER.info("inside the new list dao");
				for (Long catalog_id : machineList) {
					LOGGER.info("inside the new list dao1 :" + catalog_id);
					UserCatalogPK pk = new UserCatalogPK();
					pk.setCatalogId(catalog_id);
					pk.setUserId(user_id);
					UserCatalog userCatalog = (UserCatalog) em.find(UserCatalog.class, pk);
					if (userCatalog != null) {
						userCatalog.setActive(false);
						userCatalog.setModifiedBy(loginUserId);
						userCatalog.setModifiedDate(DateTimeUtil.getSqlTimeStamp());
						msg = "Successfully catalog is removed";
						UserCatalog userCatalog1 = (UserCatalog) em.find(UserCatalog.class, pk);
					}
				}

				return msg;
			} else {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while removing the catalog " + e.getMessage());
		}

		return msg;
	}

	@SuppressWarnings("unchecked")
	public List<BigInteger> getUnassignedIdList(Long loginUserId, Long user_id, String query, Long org_id) {
		List<BigInteger> catIdList = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		SQLQuery qry = null;
		String getUnassignedCatalogIdQuery = "SELECT CATALOG_ID FROM  USER_ORG_MAP UsrOrg inner join USER_CUSTOMER UsrCust on UsrOrg.USER_ID=UsrCust.USER_ID and UsrCust.ACTIVE=1 inner join CATALOG Cat on UsrOrg.ORGANIZATION_ID=Cat.ORGANIZATION_ID and UsrCust.CUSTOMER_ID=Cat.CUSTOMER_ID where UsrOrg.USER_ID=:userId and  Cat.ORGANIZATION_ID=:orgId AND Cat.CATALOG_ID  NOT IN ("
				+ query + ")";

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			qry = session.createSQLQuery(getUnassignedCatalogIdQuery);
			qry.setLong("orgId", org_id);
			qry.setLong("userId", user_id);
			catIdList = qry.list();
			tx.commit();
			if (catIdList != null && !catIdList.isEmpty()) {
				LOGGER.info("No,of rows updated while de-assigning catalog for user :" + catIdList.size());
				// msg="Successfully All catalogs are removed";
				return catIdList;
			}

		} catch (HibernateException e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} catch (Exception e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}

		return null;
	}

	/**
	 * used to assign all catalog for a user input- user id, list of all
	 * catalogs which have to assign for user
	 * 
	 */
	@Override
	@Transactional
	public String assignAllMachine(Long loginUserId, Long user_id, String query, Long org_id) {
		String msg = null;
		int newc = 0;
		int createdc = 0;
		List<BigInteger> catIdList = getUnassignedIdList(loginUserId, user_id, query, org_id);
		try {
			if (catIdList != null && !catIdList.isEmpty()) {
				LOGGER.info("inside the new list dao:" + catIdList);

				for (BigInteger catalog_id : catIdList) {
					LOGGER.info("inside the new list dao1");
					UserCatalogPK pk = new UserCatalogPK();
					pk.setCatalogId(catalog_id.longValue());
					pk.setUserId(user_id);
					LOGGER.info("inside the new list dao2");
					UserCatalog userCatalog = (UserCatalog) em.find(UserCatalog.class, pk);
					if (userCatalog != null) {
						newc++;
						LOGGER.info("inside the new list dao3 update");
						userCatalog.setActive(true);
						userCatalog.setModifiedBy(loginUserId);
						userCatalog.setModifiedDate(DateTimeUtil.getSqlTimeStamp());

					} else {
						createdc++;
						LOGGER.info("inside the new list dao3 create");
						UserCatalog userCatalog1 = new UserCatalog();
						userCatalog1.setActive(true);
						userCatalog1.setCatalogId(catalog_id.longValue());
						userCatalog1.setUserId(user_id);
						userCatalog1.setCreatedBy(loginUserId);
						userCatalog1.setCreatedDate(DateTimeUtil.getSqlTimeStamp());
						userCatalog1.setId(pk);
						em.persist(userCatalog1);

					}
				}
				LOGGER.info("update count :" + newc);
				LOGGER.info("create count :" + createdc);
				msg = "Successfully All catalogs are assigned";
				return msg;
			} else {
				throw new NullPointerException();
			}

		} catch (Exception e) {
			LOGGER.error("Exception while assigning all catalog " + e.getMessage());
		}

		return msg;
	}

	/**
	 * used to remove all catalog for a user input- user id, list of all
	 * catalogs which have to remove for user
	 * 
	 */
	@Override
	public String removeAllMachine(Long loginUserId, Long user_id, String query) {

		String updateSql = "UPDATE USER_CATALOG set ACTIVE=0,MODIFIED_BY=:modifiedby, MODIFIED_DATE=:modifieddate WHERE USER_ID=:user_id AND CATALOG_ID IN ";
		Session session = null;
		Transaction tx = null;
		SQLQuery qry = null;
		int count = 0;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			qry = session.createSQLQuery(updateSql + "(" + query + ")");

			qry.setLong("modifiedby", loginUserId);
			qry.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());
			qry.setLong("user_id", user_id);

			count = qry.executeUpdate();
			tx.commit();
			if (count >= 0) {
				LOGGER.info("No,of rows updated while de-assigning catalog for user :" + count);
				msg = "Successfully All catalogs are removed";
				return msg;
			}

		} catch (HibernateException e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} catch (Exception e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}

		return msg;
	}

	/**
	 * use to get all the unassigned catalog list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getUnassignedListCat(String query) {

		Session session = null;
		SQLQuery qry = null;
		List<Long> catList = null;
		try {
			session = sessionFactory.openSession();
			qry = session.createSQLQuery(query).addScalar("UNASSIGNEDID", LongType.INSTANCE);
			;
			catList = qry.list();

		} catch (HibernateException e) {
			LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getRoleCountByOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return catList;

	}

	/**
	 * /** used to remove all catalog for a user input- user id, list of all
	 * catalogs which have to remove for user
	 * 
	 */

	@Override
	public String updateAllMachine(Long loginUserId, Long user_id, List<Long> catIdList) {

		String updateSql = "UPDATE USER_CATALOG set ACTIVE=0,MODIFIED_BY=:modifiedby MODIFIED_DATE=:modifieddate WHERE USER_ID=:userId AND CATALOG_ID IN ";
		Session session = null;
		Transaction tx = null;
		SQLQuery qry = null;
		int count = 0;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			qry = session.createSQLQuery(updateSql + "(" + catIdList + ")");

			qry.setLong("modifiedby", loginUserId);
			qry.setLong("user_id", user_id);
			qry.setParameter("modifieddate", DateTimeUtil.getSqlTimeStamp());

			count = qry.executeUpdate();
			tx.commit();
			if (count >= 0) {
				LOGGER.info("No,of rows updated while de-assigning catalog for user :" + count);
				msg = "Successfully All catalogs are removed";
				return msg;
			}

		} catch (HibernateException e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} catch (Exception e) {
			if (tx != null) {

				tx.rollback();
			}
			LOGGER.error("Exception in deAssignCatalogOfOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MachineDownloadDto> getAllUnAssignedMachineList(String query) {
		Session session = null;
		List<MachineDownloadDto> list = null;
		try {
			session = sessionFactory.openSession();
			list = session.createSQLQuery(query).addScalar("catalog_id", LongType.INSTANCE)
					.addScalar("model", StringType.INSTANCE).addScalar("catalog_reference", StringType.INSTANCE)
					.addScalar("customer_name", StringType.INSTANCE)
					.addScalar("group_name", StringType.INSTANCE)
					.addScalar("catalog_name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(MachineDownloadDto.class)).list();

		} catch (HibernateException e) {
			LOGGER.error("Exception in getAllUnAssignedMachineList " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getAllUnAssignedMachineList " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CatalogCountForOrgDto getCatalogsCountForOrganization(Long userId,Long orgId) {
		Session session = null;
		SQLQuery query = null;
		CatalogCountForOrgDto catCountObj = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(AVAILABLE_COUNT_QUERY);
			query.setLong("orgId", orgId);
			query.setLong("userId", userId);
			List<Integer> value = query.list();
			if (value != null) {

				catCountObj = new CatalogCountForOrgDto();
				catCountObj.setCatalogCount(value.get(0));
				LOGGER.info("in dao impl count query check :"+catCountObj);
				return catCountObj;
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception in getCatalogsCountForOrganization: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getCatalogsCountForOrganization: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public Long getCatalogAssignedCount(Long orgId, Long userId) {
		LOGGER.info("User id " + userId);
		Session session = null;
		SQLQuery query = null;
		long catalogCount=0;
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
