package com.tf.usermanagement.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
@Service
public interface UserListService {

	String downloadCustomerResult(HttpServletResponse response, String searhfilter);

}
