package com.tf.usermanagement.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.DivisionDao;
import com.tf.usermanagement.dto.DivisionDto;

/**
 * 
 * @author Rajendra
 *
 */

@Repository
@Transactional
public class DivisionDaoImpl implements DivisionDao {
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionDto> getDivisions() {
		Query query = em.createQuery(
				"select new com.tf.usermanagement.dto.DivisionDto(org.organizationId,org.organizationName) from Organization org");
				List<DivisionDto> list = query.getResultList();
				return list;
	}

}
