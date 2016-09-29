package com.tf.usermanagement.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.UserListDao;
import com.tf.usermanagement.dto.UserListResultDto;

/**
 * 
 * @author Rajendra
 *
 */
@Repository
@Transactional
public class UserListDaoImpl implements UserListDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserListDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserListResultDto> getAllUsers(String sqlquery) {

		Session session = null;
		List<UserListResultDto> list = null;
		try {
			session = sessionFactory.openSession();
			list = session.createSQLQuery(sqlquery).addScalar("userId", LongType.INSTANCE)
					.addScalar("userName", StringType.INSTANCE).addScalar("firstName", StringType.INSTANCE)
					.addScalar("lastName", StringType.INSTANCE).addScalar("email", StringType.INSTANCE)
					.addScalar("createdDate", DateType.INSTANCE).addScalar("companyName", StringType.INSTANCE)
					.addScalar("phoneNumber", StringType.INSTANCE).addScalar("approved", LongType.INSTANCE)
					.addScalar("pending", LongType.INSTANCE)

					.setResultTransformer(Transformers.aliasToBean(UserListResultDto.class)).list();

			return list;

		} catch (HibernateException e) {
			LOGGER.error("Exception in download " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception while downloading the report " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOrganizationNameById(List<Integer> organizationIds) {

		Session session = null;
		List<String> orgNames = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"select organizationName from Organization where organizationId in (:organizationIds)");
			query.setParameterList("organizationIds", organizationIds);

			orgNames = query.list();
		} catch (HibernateException e) {
			LOGGER.error("Exception in getOrganizationNameById " + e.getMessage());

		} catch (Exception e) {
			LOGGER.error("Exception in getOrganizationNameById " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return orgNames;

	}

}
