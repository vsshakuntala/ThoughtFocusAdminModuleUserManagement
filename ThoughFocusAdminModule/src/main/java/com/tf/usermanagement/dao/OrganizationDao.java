package com.tf.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tf.usermanagement.domain.Organization;

public interface OrganizationDao extends JpaRepository<Organization, Long>{

}
