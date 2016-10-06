package com.tf.usermanagement.daoimpl;

/**
 * @author Biswajit
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.DefaultAddressDao;
import com.tf.usermanagement.domain.UserOrgBillToShip;
import com.tf.usermanagement.domain.UserOrgSalesAreaMap;
import com.tf.usermanagement.dto.BillAddressInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.SalesCustomerDto;
import com.tf.usermanagement.dto.SalesOrgDto;
import com.tf.usermanagement.dto.ShipAddressInputDto;
import com.tf.usermanagement.dto.UserOrgBillShipSalesAreaDto;
import com.tf.usermanagement.dto.UserOrgMapDto;

@Repository
public class DefaultAddressDaoImpl implements DefaultAddressDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAddressDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * this method is to get the customer that belongs to the user and sales
	 * area
	 */
	public List<SalesCustomerDto> getAllSalesCust(long salesAreaId, long userId,int pageNumber,int count,String queryString) {

		Session session = null;

		List<SalesCustomerDto> grpAssignedList = new ArrayList<SalesCustomerDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(getAllSalesCustomerQueryBuilder(queryString))
					.addScalar("CUSTOMER_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("CUSTOMER_NAME", StandardBasicTypes.STRING);
			query.setLong("salesAreaId", salesAreaId);
			query.setLong("userId", userId);
			query.setFirstResult((pageNumber - 1) * (count));
		    query.setMaxResults(count);
			List<Object[]> salescust = query.list();
			for (Object[] i : salescust) {
				SalesCustomerDto salesCustomerDto = new SalesCustomerDto();

				salesCustomerDto.setCustomerId(((BigInteger) i[0]).longValue());
				salesCustomerDto.setCustomerName((String) i[1]);
				grpAssignedList.add(salesCustomerDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception while getting the customer " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get customer " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return grpAssignedList;

	}
	
	private String getAllSalesCustomerQueryBuilder(String queryString){
		
		if(queryString==null)
			return "select salesCurrency.CUSTOMER_ID,customer.CUSTOMER_NAME FROM CUSTOMER_SALES_AREA_CURRENCY salesCurrency  INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=salesCurrency.CUSTOMER_ID AND userCust.USER_ID = :userId AND userCust.ACTIVE=1 INNER JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID where salesCurrency.SALES_AREA_ID = :salesAreaId and salesCurrency.ACTIVE=1 order by salesCurrency.CUSTOMER_ID";
	
		return "select salesCurrency.CUSTOMER_ID,customer.CUSTOMER_NAME FROM CUSTOMER_SALES_AREA_CURRENCY salesCurrency  INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=salesCurrency.CUSTOMER_ID AND userCust.USER_ID = :userId AND userCust.ACTIVE=1 INNER JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID where salesCurrency.SALES_AREA_ID = :salesAreaId and salesCurrency.ACTIVE=1  and (salesCurrency.CUSTOMER_ID like '%"+queryString+"%' or customer.CUSTOMER_NAME like '%"+queryString+"%') order by salesCurrency.CUSTOMER_ID";
	}
	
	private String countAllSalesCustomerQueryBuilder(String queryString){
		
		if(queryString==null)
			return "select count(salesCurrency.CUSTOMER_ID) as count FROM CUSTOMER_SALES_AREA_CURRENCY salesCurrency  INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=salesCurrency.CUSTOMER_ID AND userCust.USER_ID = :userId AND userCust.ACTIVE=1 INNER JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID where salesCurrency.SALES_AREA_ID = :salesAreaId and salesCurrency.ACTIVE=1 ";
	
		return "select count(salesCurrency.CUSTOMER_ID) as count FROM CUSTOMER_SALES_AREA_CURRENCY salesCurrency  INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=salesCurrency.CUSTOMER_ID AND userCust.USER_ID = :userId AND userCust.ACTIVE=1 INNER JOIN CUSTOMER customer on customer.CUSTOMER_ID=userCust.CUSTOMER_ID where salesCurrency.SALES_AREA_ID = :salesAreaId and salesCurrency.ACTIVE=1  and (salesCurrency.CUSTOMER_ID like '%"+queryString+"%' or customer.CUSTOMER_NAME like '%"+queryString+"%') ";
	}
	
	@Override
	public long countAllSalesCust(long salesAreaId, long userId,String queryString) {

		Session session = null;
		long count=0l;

		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(countAllSalesCustomerQueryBuilder(queryString))
					.addScalar("count", StandardBasicTypes.LONG);
			query.setLong("salesAreaId", salesAreaId);
			query.setLong("userId", userId);
			count=(long) query.uniqueResult();
		} catch (HibernateException e) {
			LOGGER.error("Exception while getting the customer " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get customer " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;

	}

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * this method is to get the customer that belongs to the user area
	 */
	public List<SalesCustomerDto> getAllUserCust(long userId,long organizationId,int pageNumber,int count,String queryString) {

		Session session = null;

		List<SalesCustomerDto> grpAssignedList = new ArrayList<SalesCustomerDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(getAllCustomersQueryBuilder(queryString))
					.addScalar("CUSTOMER_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("CUSTOMER_NAME", StandardBasicTypes.STRING);
			query.setLong("userId", userId);
			query.setLong("organizationId", organizationId);
			query.setFirstResult((pageNumber - 1) * (count));
		    query.setMaxResults(count);
			List<Object[]> usercust = query.list();
			for (Object[] i : usercust) {
				SalesCustomerDto salesCustomerDto = new SalesCustomerDto();

				salesCustomerDto.setCustomerId(((BigInteger) i[0]).longValue());
				salesCustomerDto.setCustomerName((String) i[1]);
				grpAssignedList.add(salesCustomerDto);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.error("Exception while getting the customer " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception in get customer " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return grpAssignedList;

	}
	
	private String getAllCustomersQueryBuilder(String queryString){
		
		if(queryString==null)
			return 
			"select userCust.CUSTOMER_ID,customer.CUSTOMER_NAME "
			+ " FROM CUSTOMER customer "
			+ " INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=customer.CUSTOMER_ID and customer.ACTIVE=1"
			+ " INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and custOrgMap.ORGANIZATION_ID= :organizationId"
			+ " where userCust.USER_ID = :userId and userCust.ACTIVE=1 order by userCust.CUSTOMER_ID ";
		return 
			"select userCust.CUSTOMER_ID,customer.CUSTOMER_NAME "
			+ " FROM CUSTOMER customer "
			+ " INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=customer.CUSTOMER_ID and customer.ACTIVE=1 "
			+ " INNER JOIN CUSTOMER_ORGANIZATION_MAP custOrgMap on custOrgMap.CUSTOMER_ID=userCust.CUSTOMER_ID and custOrgMap.ORGANIZATION_ID= :organizationId"
			+ " where userCust.USER_ID = :userId and userCust.ACTIVE=1 and (customer.CUSTOMER_NAME like '%"+queryString+"%' or userCust.CUSTOMER_ID like '%"+queryString+"%') order by userCust.CUSTOMER_ID";
	}
	
	private String countAllCustomersQueryBuilder(String queryString){
		
		if(queryString==null)
			return 
					"select count(userCust.CUSTOMER_ID) as count FROM CUSTOMER customer INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=customer.CUSTOMER_ID where userCust.USER_ID = :userId and userCust.ACTIVE=1";
		return 
				"select count(userCust.CUSTOMER_ID) as count FROM CUSTOMER customer INNER JOIN USER_CUSTOMER userCust on userCust.CUSTOMER_ID=customer.CUSTOMER_ID where userCust.USER_ID = :userId and userCust.ACTIVE=1 and (customer.CUSTOMER_NAME like '%"+queryString+"%' or userCust.CUSTOMER_ID like '%"+queryString+"%')";
	}
	
	@Override
	public long countAllUserCust(long userId, String queryString) {
		Session session = null;
		long count=0;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(countAllCustomersQueryBuilder(queryString))
					.addScalar("count", StandardBasicTypes.LONG);
			query.setLong("userId", userId);
		    
		    count  = (long) query.uniqueResult();

		} catch (HibernateException e) {
			LOGGER.error("Exception while getting customer count" + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getting customer count" + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return count;
	}

	/*
	 * this method is to get the billing address belongs to the customer
	 */
	@Override
	public List<BillAddressInputDto> getAllBillAdd(long customerId, Integer addressTypeId) {
		Session session = null;

		List<BillAddressInputDto> list = new ArrayList<BillAddressInputDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery("SELECT ADDRESS_NAME,ADDRESS_ID" + " FROM ADDRESS"
							+ " WHERE CUSTOMER_ID =:customerId" + " AND ADDRESS_TYPE_ID IN(1,3) AND ACTIVE=1"
							+ " order by ADDRESS.ADDRESS_TYPE_ID")
					.addScalar("ADDRESS_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("ADDRESS_NAME", StandardBasicTypes.STRING);
			query.setLong("customerId", customerId);
			
			@SuppressWarnings("unchecked")
			List<Object[]> billaddlist = query.list();
			for (Object[] i : billaddlist) {
				BillAddressInputDto salesAreaCurrencyDto = new BillAddressInputDto();

				salesAreaCurrencyDto.setAddressId(((BigInteger) i[0]).longValue());
				salesAreaCurrencyDto.setAddressName((String) i[1]);
				list.add(salesAreaCurrencyDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception while getting billing address " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get bill address " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/*
	 * this method is to get the shipping address belongs to the customer
	 */
	@Override
	public List<ShipAddressInputDto> getAllShipAdd(long customerId, Integer addressTypeId) {
		Session session = null;

		List<ShipAddressInputDto> shiplist = new ArrayList<ShipAddressInputDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery("SELECT ADDRESS_NAME,ADDRESS_ID" + " FROM ADDRESS"
							+ " WHERE CUSTOMER_ID =:customerId" + " AND ADDRESS_TYPE_ID IN(2,3) AND ACTIVE=1"
							+ " order by ADDRESS.ADDRESS_TYPE_ID")
					.addScalar("ADDRESS_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("ADDRESS_NAME", StandardBasicTypes.STRING);
			query.setLong("customerId", customerId);
			
			@SuppressWarnings("unchecked")
			List<Object[]> shipaddlist = query.list();
			for (Object[] i : shipaddlist) {
				ShipAddressInputDto shipAddressInputDto = new ShipAddressInputDto();

				shipAddressInputDto.setAddressId(((BigInteger) i[0]).longValue());
				shipAddressInputDto.setAddressName((String) i[1]);
				shiplist.add(shipAddressInputDto);
			}
		} catch (HibernateException e) {
			LOGGER.error("Exception while getting shipping address " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get shipaddress " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return shiplist;
	}

	/*
	 * this method is to get the sales area belongs to the organization
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesOrgDto> getAllSales(long organizationId,int pageNumber,int count,String queryString) {
		Session session = null;

		List<SalesOrgDto> salesList = new ArrayList<SalesOrgDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(getAllSalesQueryBuilder(queryString))
					.addScalar("SALES_AREA_ID", StandardBasicTypes.BIG_INTEGER)
					.addScalar("SALES_ORG_NAME", StandardBasicTypes.STRING)
					.addScalar("DISTRIBUTION_CHANNEL_NAME", StandardBasicTypes.STRING);
			query.setLong("organizationId", organizationId);
			query.setFirstResult((pageNumber - 1) * (count));
		    query.setMaxResults(count);
		    
			List<Object[]> sales = query.list();
			for (Object[] i : sales) {
				SalesOrgDto salesOrgDto = new SalesOrgDto();

				salesOrgDto.setSalesAreaId(((BigInteger) i[0]).longValue());
				salesOrgDto.setSalesOrgName((String) i[1]);
				salesOrgDto.setDistributionChannelName((String) i[2]);
				salesList.add(salesOrgDto);
			}

		} catch (HibernateException e) {
			LOGGER.error("Exception while getting sales area " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get sales area " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return salesList;
	}
	private String getAllSalesQueryBuilder(String queryString){
		
		if(queryString==null)
			return "select SALES_AREA_ID,SALES_ORG_NAME,DISTRIBUTION_CHANNEL_NAME from SALES_AREA where ACTIVE=1 AND ORGANIZATION_ID = :organizationId order by SALES_ORG_NAME";
		return "select SALES_AREA_ID,SALES_ORG_NAME,DISTRIBUTION_CHANNEL_NAME from SALES_AREA where ACTIVE=1 AND ORGANIZATION_ID = :organizationId and SALES_ORG_NAME like '%"+queryString+"%' order by SALES_ORG_NAME";
	}
	private String getCountAllSalesQueryBuilder(String queryString){
		
		if(queryString==null)
			return "select count(SALES_AREA_ID) as count from SALES_AREA where ACTIVE=1 AND ORGANIZATION_ID = :organizationId ";
		return "select count(SALES_AREA_ID) as count from SALES_AREA where ACTIVE=1 AND ORGANIZATION_ID = :organizationId and SALES_ORG_NAME like '%"+queryString+"%' ";
	}
	
	@Override
	public long countSales(long organizationId, String queryString) {
		Session session = null;
		long count=0;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery(getCountAllSalesQueryBuilder(queryString))
					.addScalar("count", StandardBasicTypes.LONG);
			query.setLong("organizationId", organizationId);
		    
		    count  = (long) query.uniqueResult();

		} catch (HibernateException e) {
			LOGGER.error("Exception while getting sales area count" + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get sales area count" + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return count;
	}

	/*
	 * this method is to get the userOrg belongs to the user and organization
	 * and user
	 */
	@Override
	public List<UserOrgMapDto> getAllUserOrgId(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
		Session session = null;

		long userId = userOrgBillShipSalesAreaDto.getUserId();
		long organizationId = userOrgBillShipSalesAreaDto.getOrganizationId();
		List<UserOrgMapDto> userorgList = new ArrayList<UserOrgMapDto>();
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session
					.createSQLQuery("select USER_ORG_ID" + " from USER_ORG_MAP" + " where ACTIVE=1 AND USER_ID = :userId"
							+ " and ORGANIZATION_ID = :organizationId")
					.addScalar("USER_ORG_ID", StandardBasicTypes.BIG_INTEGER);
			query.setLong("userId", userId);
			query.setLong("organizationId", organizationId);
			@SuppressWarnings("unchecked")
			List<BigInteger> uoid = query.list();
			for (BigInteger i : uoid) {
				UserOrgMapDto userOrgMapDto = new UserOrgMapDto();

				userOrgMapDto.setUserOrgMapId(i.longValue());
				userorgList.add(userOrgMapDto);
			}

		} catch (HibernateException e) {
			LOGGER.error("Exception while getting user org id " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in get user org id " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return userorgList;
	}
	/*
	 * this method is for
	 * 
	 * 1) get the UserOrgBillShipSalesArea
	 * 
	 * 2) check if record is already there then update that with the new record
	 * 
	 * 3) if record is not there then insert as a new record
	 */

	@Override
	public List<UserOrgBillShipSalesAreaDto> getAllBillShipMap(
			UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {

		long userorgId = 0;
		long billToAddressId = userOrgBillShipSalesAreaDto.getBillToAddressId();
		long customerId = userOrgBillShipSalesAreaDto.getCustomerId();
		long shipToAddressId = userOrgBillShipSalesAreaDto.getShipToAddressId();
		long adminId = userOrgBillShipSalesAreaDto.getAdminId();

		List<UserOrgMapDto> userorgidList = getAllUserOrgId(userOrgBillShipSalesAreaDto);
		for (UserOrgMapDto i : userorgidList) {
			userorgId = i.getUserOrgMapId();
		}

		Session session = null;

		List<UserOrgBillShipSalesAreaDto> mapList = new ArrayList<UserOrgBillShipSalesAreaDto>();
		
			try {
				session = sessionFactory.openSession();
				Query query = session.createQuery(
						"SELECT userorgId,billToAddressId,shipToAddressId,customerId"
								+ " FROM UserOrgBillToShip"
								+ " WHERE ACTIVE=1 AND customerId = :customerId"
								+ " AND userorgId = :userorgId");
				query.setLong("customerId", customerId);
				query.setLong("userorgId", userorgId);
				@SuppressWarnings("unchecked")
				List<UserOrgBillToShip> billshipmaps = query.list();
				if (billshipmaps.size() > 0) {
					Query uquery = session.createQuery("UPDATE UserOrgBillToShip" + " SET active = :active,"
							+ " billToAddressId = :billToAddressId,"
							+ " shipToAddressId = :shipToAddressId,"
							+ " modifiedBy = :modifiedBy," 
							+ " modifiedDate = :modifiedDate"
							+ " WHERE customerId = :customerId"
							+ " AND userorgId = :userorgId");
					uquery.setBoolean("active", true);
					uquery.setLong("modifiedBy", adminId);
					uquery.setDate("modifiedDate", new Date());
					uquery.setLong("billToAddressId", billToAddressId);
					uquery.setLong("customerId", customerId);
					uquery.setLong("shipToAddressId", shipToAddressId);
					uquery.setLong("userorgId", userorgId);
					int result = uquery.executeUpdate();
					LOGGER.info("\n\nSaved result count "+result);

				} else {

					UserOrgBillToShip billToShip = new UserOrgBillToShip();
					billToShip.setActive(true);
					billToShip.setBillToAddressId(billToAddressId);
					billToShip.setCustomerId(customerId);
					billToShip.setModifiedBy(adminId);
					billToShip.setModifiedDate(new Date());
					billToShip.setShipToAddressId(shipToAddressId);
					billToShip.setUserorgId(userorgId);
					session.save(billToShip);
				}

			} catch (HibernateException e) {
				LOGGER.error("Exception while getting bill ship map " + e.getMessage());
			} catch (Exception e) {
				LOGGER.error("Exception in get bill bill ship map " + e.getMessage());
			} finally {
				if (session != null) {
					session.close();
				}
			}
		
		return mapList;

	}

	/*
	 * this method is for
	 * 
	 * 1) get the UserOrgSalesAreaMap
	 * 
	 * 2) check if record is already there then update that with the new record
	 * 
	 * 3) if record is not there then insert as a new record
	 */
	@Override
	public List<UserOrgBillShipSalesAreaDto> getAllSalesAreaMap(
			UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
		Session session = null;

		long userOrgId = 0;
		long salesAreaId = userOrgBillShipSalesAreaDto.getSalesAreaId();
		long adminId = userOrgBillShipSalesAreaDto.getAdminId();
		List<UserOrgMapDto> userorgidList = getAllUserOrgId(userOrgBillShipSalesAreaDto);
		for (UserOrgMapDto i : userorgidList) {
			userOrgId = i.getUserOrgMapId();
		}

		List<UserOrgBillShipSalesAreaDto> mapList = new ArrayList<UserOrgBillShipSalesAreaDto>();
		if (salesAreaId != 0) {
			try {
				session = sessionFactory.openSession();
				Query query = session.createQuery("SELECT userOrgId,salesAreaId" + " FROM UserOrgSalesAreaMap"
						+ " WHERE ACTIVE=1 AND userOrgId = :userOrgId" + " AND salesAreaId = :salesAreaId");
				query.setLong("userOrgId", userOrgId);
				query.setLong("salesAreaId", salesAreaId);
				@SuppressWarnings("unchecked")
				List<UserOrgSalesAreaMap> salesareamaps = query.list();
				if (salesareamaps.size() > 0) {
					Query uquery = session.createQuery("UPDATE UserOrgSalesAreaMap" + " SET active = :active,"
							+ " modifiedBy = :modifiedBy," + " salesAreaId = :salesAreaId,"
							+ " modifiedDate = :modifiedDate" + " WHERE userOrgId = :userOrgId");
					uquery.setBoolean("active", true);
					uquery.setLong("modifiedBy", adminId);
					uquery.setDate("modifiedDate", new Date());
					uquery.setLong("userOrgId", userOrgId);
					uquery.setLong("salesAreaId", salesAreaId);
					int result = uquery.executeUpdate();
					LOGGER.info("\n\nSaved result count "+result);
				} else {
					UserOrgSalesAreaMap areaMap = new UserOrgSalesAreaMap();
					areaMap.setActive(true);
					areaMap.setModifiedBy(adminId);
					areaMap.setModifiedDate(new Date());
					areaMap.setSalesAreaId(salesAreaId);
					areaMap.setUserOrgId(userOrgId);
					areaMap.setSalesAreaId(salesAreaId);
					session.save(areaMap);
				}

			} catch (HibernateException e) {
				LOGGER.error("Exception while getting sales area map " + e.getMessage());
			} catch (Exception e) {
				LOGGER.error("Exception in get sales area map " + e.getMessage());
			} finally {
				if (session != null) {
					session.close();
				}
			}
		}
		return mapList;
	}

	@Override
	public boolean saveOrUpdateDefaultAddressForUser(DefaultAddressCheckDto defaultAddressCheckDto) {
		System.out.println(defaultAddressCheckDto.toString());
		Session session = null;
		Transaction tx = null;
		boolean result=false;
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			
			if(defaultAddressCheckDto.getSalesAreaId()>0 && defaultAddressCheckDto.getUserOrgSalesAreaId()==0){
				//save in user org sales area map
				
				UserOrgSalesAreaMap userOrgSalesAreaMap=new UserOrgSalesAreaMap();
				userOrgSalesAreaMap.setSalesAreaId(defaultAddressCheckDto.getSalesAreaId());
				userOrgSalesAreaMap.setUserOrgId(defaultAddressCheckDto.getUserOrgId());
				userOrgSalesAreaMap.setCreatedBy(defaultAddressCheckDto.getAdminId());
				userOrgSalesAreaMap.setCreatedDate(new Date());
				userOrgSalesAreaMap.setActive(true);
				
				session.save(userOrgSalesAreaMap);
				
			}if(defaultAddressCheckDto.getSalesAreaId()>0 && defaultAddressCheckDto.getUserOrgSalesAreaId()>0){
				//update in user org sales area map
				
				UserOrgSalesAreaMap userOrgSalesAreaMap=(UserOrgSalesAreaMap) session.get(UserOrgSalesAreaMap.class,defaultAddressCheckDto.getUserOrgSalesAreaId());
				
				if(userOrgSalesAreaMap!=null){
				userOrgSalesAreaMap.setSalesAreaId(defaultAddressCheckDto.getSalesAreaId());
				userOrgSalesAreaMap.setUserOrgId(defaultAddressCheckDto.getUserOrgId());
				userOrgSalesAreaMap.setModifiedBy(defaultAddressCheckDto.getAdminId());
				userOrgSalesAreaMap.setModifiedDate(new Date());
				userOrgSalesAreaMap.setActive(true);
				userOrgSalesAreaMap.setUserOrgSalesAreaId(defaultAddressCheckDto.getUserOrgSalesAreaId());
				session.update(userOrgSalesAreaMap);
				}
			}
			
			
			if(defaultAddressCheckDto.getUserOrgBillShipId()==0){
				//save
				UserOrgBillToShip userOrgBillToShip=new UserOrgBillToShip();
				userOrgBillToShip.setActive(true);
				userOrgBillToShip.setUserorgId(defaultAddressCheckDto.getUserOrgId());
				userOrgBillToShip.setBillToAddressId(defaultAddressCheckDto.getBillToAddressId());
				userOrgBillToShip.setShipToAddressId(defaultAddressCheckDto.getShipToAddressId());
				userOrgBillToShip.setCreatedBy(defaultAddressCheckDto.getAdminId());
				userOrgBillToShip.setCreatedDate(new Date());
				userOrgBillToShip.setCustomerId(defaultAddressCheckDto.getCustomerId());
				session.save(userOrgBillToShip);
				
				
			}else{
				//update
				UserOrgBillToShip userOrgBillToShip=(UserOrgBillToShip) session.get(UserOrgBillToShip.class, defaultAddressCheckDto.getUserOrgBillShipId());
				userOrgBillToShip.setActive(true);
				userOrgBillToShip.setUserorgId(defaultAddressCheckDto.getUserOrgId());
				userOrgBillToShip.setBillToAddressId(defaultAddressCheckDto.getBillToAddressId());
				userOrgBillToShip.setShipToAddressId(defaultAddressCheckDto.getShipToAddressId());
				userOrgBillToShip.setModifiedBy(defaultAddressCheckDto.getAdminId());
				userOrgBillToShip.setModifiedDate(new Date());
				userOrgBillToShip.setCustomerId(defaultAddressCheckDto.getCustomerId());
				session.update(userOrgBillToShip);
			}
			
			tx.commit();
			result=true;
			
		} catch (HibernateException e) {
			tx.rollback();
			LOGGER.error("Exception in saveOrUpdateDefaultAddressForUser " ,e);
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error("Exception in saveOrUpdateDefaultAddressForUser " , e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		
		return result;
		
	}

	

}
