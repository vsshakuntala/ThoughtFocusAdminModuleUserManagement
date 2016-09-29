package com.tf.usermanagement.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.UserManagementDao;
import com.tf.usermanagement.domain.Organization;
import com.tf.usermanagement.domain.User;
import com.tf.usermanagement.domain.UserNotes;
import com.tf.usermanagement.domain.UserOrganizationMap;
import com.tf.usermanagement.domain.UserPreference;
import com.tf.usermanagement.dto.CustomerDownloadDto;
import com.tf.usermanagement.dto.DivisionsDTO;
import com.tf.usermanagement.dto.LanguageDTO;
import com.tf.usermanagement.dto.OrganizationsDTO;
import com.tf.usermanagement.dto.UserDTO;
import com.tf.usermanagement.dto.UserOrgActiveDTO;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;
/**
 * 
 * @author Narasingha Padhi
 *
 */

@Repository
@Transactional
public class UserManagementDaoImpl implements UserManagementDao{
	
	Calendar calendar = Calendar.getInstance();
	private java.util.Date now = calendar.getTime();
	private java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactroy;
	
	public SessionFactory getSessionFactroy() {
		return sessionFactroy;
	}

	public void setSessionFactroy(SessionFactory sessionFactroy) {
		this.sessionFactroy = sessionFactroy;
	}

	@PersistenceContext
	private EntityManager em;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationsDTO> getOrgNameAndId() {
		Query query = em.createQuery(
				"select new com.tf.usermanagement.dto.OrganizationsDTO(org.organizationId,org.organizationName) from Organization org");
		List<OrganizationsDTO> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LanguageDTO> getLanguages() {
		Query query = em.createQuery(
				"select new com.tf.usermanagement.dto.LanguageDTO(lang.languageId,lang.languageDescription) from Language lang order by lang.languageDescription");
		List<LanguageDTO> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationsDTO> getAssignedOrganization(long adminId) {
		Session session = null;
		SQLQuery query = null;
		List<OrganizationsDTO> userUnassignedOrgDtoList = new ArrayList<>();
		try {
			session = sessionFactroy.openSession();
			query = session
					.createSQLQuery(
							 "select org.ORGANIZATION_ID as organizationId,org.ORGANIZATION_NAME as organizationName "
							+" FROM ORGANIZATION org"
							+" INNER JOIN USER_ORGANIZATION_ROLE userOrgRole"
							+" on org.ORGANIZATION_ID=userOrgRole.ORGANIZATION_ID"
							+" AND userOrgRole.USER_ID=:adminId"
							+" AND ROLE_ID=4"
							+" AND userOrgRole.ACTIVE=1"
							+" order By org.ORGANIZATION_ID"		
							)
					.addScalar("organizationId", StandardBasicTypes.INTEGER)
					.addScalar("organizationName", StandardBasicTypes.STRING);
			
			query.setLong("adminId",adminId);
			userUnassignedOrgDtoList = query.setResultTransformer( Transformers.aliasToBean(OrganizationsDTO.class)).list();
			
		} catch (HibernateException e) {
			LOGGER.error("Exception in getAssignedOrganization " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in getAssignedOrganization " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return userUnassignedOrgDtoList;
	}

	/**
	 * saveUser will save a new user  
	 */
	@Override
	public boolean saveUser(UserDTO user) {
		Transaction tx = null;
		boolean flag = true;
		Session session = null;
		

		try {
			session = sessionFactroy.openSession();
			tx = session.beginTransaction();
			if (user != null) {
				User newUser = new User();
				newUser.setActive(true);
				newUser.setUserName(user.getUserName());
				newUser.setEmail(user.getEmail());
				newUser.setFirstName(user.getFirstName());
				newUser.setMiddleName(user.getMiddleName());
				newUser.setLastName(user.getLastName());
				newUser.setAlias(user.getAlias());
				newUser.setPhoneNumber(user.getPhoneNumber());
				newUser.setCountryFlag(user.getCountryFlag());
				newUser.setCompanyName(user.getCompanyName());
				newUser.setExternalAuthenticationToken(user.getExternalAuthenticationToken());
				newUser.setPassword(new StandardPasswordEncoder().encode(user.getPassword()));
				newUser.setAddress(user.getAddress());
				newUser.setCustomerAccount(user.getCustomerAccount());
				newUser.setAgreedToTermsOfUse(true);
				newUser.setResetPassword(false);
				newUser.setCreatedBy(user.getCurrentLoggedUserId());
				newUser.setCreatedDate(currentTimestamp);
				newUser.setLocked(false);
				newUser.setPasswordExpiryDate(null);
				newUser.setRememberMeToken(null);
				

				LOGGER.info("Divsion information : {}", user.getUserDivision());

				for (DivisionsDTO divId : user.getUserDivision()) {

					LOGGER.info("divId.getId()", divId);
					Organization organization = (Organization) session.get(Organization.class,
							divId.getOrganizationId());

					LOGGER.info("Organization information :{}", organization);

					if (organization != null) {
						newUser.setOrganization(organization);
						session.save(newUser);
						session.merge(organization);

						LOGGER.info("Organization information :{}", organization);

						Set<UserOrganizationMap> map = new HashSet<UserOrganizationMap>();
						UserOrganizationMap up = new UserOrganizationMap();
						if (up != null) {
							up.setOrganizationId(divId.getOrganizationId().longValue());
							up.setUser(newUser);
							up.setTermsCondition(true);
							up.setApprovalStatus(false);
							up.setCreatedDate(currentTimestamp);
							session.save(up);
							map.add(up);
							newUser.setUserOrganizationMap(map);
							session.merge(newUser);
						}
					}
				}

				UserPreference userPreference = new UserPreference();
				userPreference.setUser(newUser);
				if (userPreference != null) {
					userPreference.setLanguageId(user.getUserLanguage());
					session.save(userPreference);
					session.merge(newUser);
				}

				Long userId = (Long) session.save(newUser);
				if (user.getNotes() != null) {
					UserNotes userNotes = new UserNotes();
					userNotes.setUser(newUser);
					userNotes.setNotes(user.getNotes());
					userNotes.setCreatedDate(currentTimestamp);
					userNotes.setCreatedBy(user.getCurrentLoggedUserId());
					session.save(userNotes);
				}

				LOGGER.info("The User id is " + userId);
				tx.commit();
				flag = true;
				
			}
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }
			flag = false;
			
			LOGGER.info("Erron in adding new User :{}" + e.getMessage());
		}finally {
			if (session != null) {
				session.close();
			}
		}
	
		
		
		
		return flag;
	}
	/**
	 * 
	 */
	@Override
	public UserDTO getUserById(Long userId) {
		Session session = null;
		Transaction tx = null;
		User user=null;
		UserDTO dto=new UserDTO();
		try {
			session = sessionFactroy.openSession();
			tx = session.beginTransaction();
			user = (User) session.get(User.class, userId);
			Hibernate.initialize(user.getUserOrganizationMap());
			Hibernate.initialize(user.getUserPreference());
			if(user!=null){
				dto.setUserId(user.getUserId());
				dto.setFirstName(user.getFirstName());
				dto.setLastName(user.getLastName());
				dto.setUserName(user.getUserName());
				dto.setMiddleName(user.getMiddleName());
				dto.setEmail(user.getEmail());
				dto.setPhoneNumber(user.getPhoneNumber());
				dto.setAddress(user.getAddress());
				dto.setCompanyName(user.getCompanyName());
				dto.setCustomerAccount(user.getCustomerAccount());
				dto.setUserLanguage(user.getUserPreference().getLanguageId());
			}
			return dto;
		} catch (Exception e) {
			if (tx != null) {

		tx.rollback();
	    }	
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserOrgActiveDTO> getUserOrgActive(Long userId) {
		SQLQuery query = null;
		Session session = null;
		List<UserOrgActiveDTO> userOrgActiveDtoList = new ArrayList<>();
		try {
			session = sessionFactroy.openSession();
			query = session.createSQLQuery("select ORGANIZATION_ID as organizationId,ACTIVE as active from USER_ORG_MAP where ACTIVE=1 AND USER_ID = :userId")
			.addScalar("organizationId", StandardBasicTypes.BIG_INTEGER)
			.addScalar("active", StandardBasicTypes.BOOLEAN);
			query.setLong("userId", userId);
			
			List<Object[]> userOrgActiveArray = query.list();
			for (Object[] object : userOrgActiveArray) {
				UserOrgActiveDTO userOrgActiveDTO = new UserOrgActiveDTO();
				userOrgActiveDTO.setOrganizationId(((BigInteger) object[0]).longValue());
				userOrgActiveDTO.setActive((Boolean) object[1]);
				
				userOrgActiveDtoList.add(userOrgActiveDTO);
			}
		}catch (HibernateException e) {
			LOGGER.info("Error in UserOrgaActive"+e.getMessage());
			
		}catch (Exception e) {
			LOGGER.info("Error in UserOrgaActive"+e.getMessage());
			
		}finally {
			if(session != null){
				session.close();
			}
		}
		return userOrgActiveDtoList;
	}
	
	/**
	 *updateUser will update the selected user 
	 */
	@Override
	public boolean updateUser(UserDTO userDto) {
		System.out.println("Entered update user:"+userDto);
		boolean flag = false;
		Session session = sessionFactroy.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			LOGGER.debug("Updating user in DAO impl ");
			User user = (User) session.get(User.class, userDto.getUserId());
			user.setUserId(userDto.getUserId());
			user.setEmail(userDto.getEmail());
			user.setFirstName(userDto.getFirstName());
			user.setMiddleName(userDto.getMiddleName());
			user.setLastName(userDto.getLastName());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setCountryFlag(userDto.getCountryFlag());
			user.setCompanyName(userDto.getCompanyName());
			user.setCustomerAccount(userDto.getCustomerAccount());
			user.setAddress(userDto.getAddress());
			user.setAlias(userDto.getAlias());
			user.setModifiedBy(userDto.getCurrentLoggedUserId());
			user.setModifiedDate(currentTimestamp);
			System.out.println("am here.....");
			session.saveOrUpdate(user);
			//session.update(user);
			tx.commit();
			flag = true;
			LOGGER.debug("User updated");
		}catch (HibernateException e) {
			LOGGER.error("Hibernate exception : ", e);			
			if(tx != null)
			{
				if (tx != null) {

					tx.rollback();
	    }
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}
	
	@Override
	public boolean updateUserOrganizations(List<DivisionsDTO> userOrgs, Long userId, Long currentLoggedUserId) {
		boolean flag = false;
		Session session = sessionFactroy.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(DivisionsDTO org : userOrgs)
			{
				String hql = "from UserOrganizationMap where user.userId =" + userId+" and organizationId = "+org.getOrganizationId();
				UserOrganizationMap userOrganizationMap = null;
				userOrganizationMap = (UserOrganizationMap) session.createQuery(hql).uniqueResult();
				if(userOrganizationMap != null)
				{
					LOGGER.debug("Updating exsisting org map "+userOrganizationMap.getOrganizationId());
					userOrganizationMap.setActive(true);
					userOrganizationMap.setModifiedBy(currentLoggedUserId);
					userOrganizationMap.setModifiedDate(currentTimestamp);
					session.update(userOrganizationMap);
				}else
				{
					userOrganizationMap = new UserOrganizationMap();
					userOrganizationMap.setOrganizationId(org.getOrganizationId().longValue());
					LOGGER.debug("Inserting org map "+userOrganizationMap.getOrganizationId());
					userOrganizationMap.setActive(true);			
					userOrganizationMap.setModifiedBy(currentLoggedUserId);
					userOrganizationMap.setModifiedDate(currentTimestamp);
					userOrganizationMap.setApprovalStatus(false);
					userOrganizationMap.setTermsCondition(true);
					userOrganizationMap.setUser((User)session.get(User.class, userId));
					session.save(userOrganizationMap);
				}
			}
			LOGGER.debug("User orgs updated ");
			tx.commit();
			flag = true;
		}
		catch (HibernateException e) {
			LOGGER.error("Hibernate exception : ", e);
			if(tx != null)
			{
				if (tx != null) {

		tx.rollback();
	    }
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	
	}
	
	@Override
	public boolean upateUserPreferences(Long userId,Long  languageId,Long currentLoggedUserId) {
		boolean flag = false;
		Session session = sessionFactroy.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
				String hql = "from UserPreference where user.userId =" + userId;
				UserPreference userPreference = null;
				userPreference = (UserPreference) session.createQuery(hql).uniqueResult();
				if(userPreference != null)
				{
					LOGGER.debug("Updating exsisting user preference ");
					userPreference.setLanguageId(languageId);
					session.update(userPreference);
				}
			
			LOGGER.debug("User orgs updated ");
			tx.commit();
			flag = true;
		}
		catch (HibernateException e) {
			flag=false;
			LOGGER.error("Hibernate exception : ", e);
			if(tx != null)
			{
				if (tx != null) {

		tx.rollback();
	    }
			}
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	
	}
	
	@Override
	public boolean checkEmail(String email) {
		Session session = null;

		SQLQuery query = null;
		boolean result = false;
		LOGGER.debug("Email from UI: " + email);
		try {
			session = sessionFactroy.openSession();

			query = session.createSQLQuery("select EMAIL email from USERS where EMAIL = :email").addScalar("email",StandardBasicTypes.STRING);

			query.setString("email", email);

			List<Object[]> emailArray = query.list();
			if (emailArray.isEmpty()) {
				result = false;
			} else {
				result = true;
			}

		} catch (Exception e) {
			LOGGER.error("Exception : ", e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	

	
}
