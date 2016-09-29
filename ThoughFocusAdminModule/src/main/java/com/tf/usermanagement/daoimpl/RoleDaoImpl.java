package com.tf.usermanagement.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tf.usermanagement.dao.RoleDao;
import com.tf.usermanagement.dto.RoleDto;

/**
 * 
 * @author Rajendra
 *
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getRolNameAndId() {

		Query query = em
				.createQuery("select new com.tf.usermanagement.dto.RoleDto(rol.roleId,rol.roleName) from Role rol where active=1");
		List<RoleDto> list = query.getResultList();
		return list;
	}
}
