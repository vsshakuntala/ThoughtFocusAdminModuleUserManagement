package com.tf.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tf.usermanagement.domain.Language;

public interface LanguageDao extends JpaRepository<Language,Long>{

}
