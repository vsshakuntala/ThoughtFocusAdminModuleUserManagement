/*
 * To change this license authHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 *
 * @author pradeepkm
 */
public class TfAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {
	public static final Logger LOGGER = LoggerFactory.getLogger(TfAuthProcessingFilter.class);

	@Value("${successUrl}")
	private String SUCCESS_URL;
	
	public TfAuthProcessingFilter() {
		super("/**");
	}

	@Override
	public boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean retVal=false;
		SecurityContext context=SecurityContextHolder.getContext();
		if(context==null || context.getAuthentication()==null || context.getAuthentication().getPrincipal()==null)
			retVal=true;
		LOGGER.info("\n\n  NOTE: returning requiresAuthentication for URL {} as {}\n\n",request.getRequestURL(), retVal);
		return retVal;
		// return true;
	
		//return false;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest hsr,
			HttpServletResponse hsr1) throws AuthenticationException,
			IOException, ServletException {

		// Logic for TF Admin parsing URL.
		String adminId = hsr.getParameter("adminId");
		//For testing purpose
		String originUrl = "http://localhost:8089/security/";
		//Production
		//String originUrl = hsr.getHeader("referer");
		LOGGER.info("\n\n ***** Referer URL *****  :- "+originUrl+"\n\n");
		
		if(adminId==null)
			throw new TfAuthenticationException("Missing adminId as parameter ");
		
		System.out.println("Origin url "+hsr.getRequestURL());
							
		TfAuthRequestToken authRequest = new TfAuthRequestToken(adminId,originUrl);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	public void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse resp, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LOGGER.info("At successfulAuthentication {} with auth class as {}",authResult, authResult.getClass().getName());
		super.successfulAuthentication(req, resp, chain, authResult);
		LOGGER.info("Post super.successfulAuthentication");
		
		TfAuthenticationToken tfVlidation = (TfAuthenticationToken)authResult;
		AdminUser adminUser=(AdminUser) tfVlidation.getPrincipal();
		//chain.doFilter(req, resp);
		//req.getRequestDispatcher("/#/usermgmt/user/viewuser").forward(req, resp);
		//resp.sendRedirect(req.getRequestURL()+"?adminEmail="+adminUser.getUsername()+"#/usermgmt/user/viewuser");
		resp.sendRedirect(successUrlBuilder(req.getRequestURL(),adminUser.getUsername()));
	}
	
	private  String successUrlBuilder(StringBuffer str1,String userName){
		//return new StringBuilder(str1.substring(0, str1.lastIndexOf("/"))).append("/#/usermgmt/user/viewuser?adminEmail=").append(mail).toString();
		//calling URL ----  http://localhost:8081/ThoughtFocusAdminModule/index.html?adminEmail=User154@thoughtfocus.com
		return new StringBuilder(str1.substring(0, str1.lastIndexOf("/"))).append(SUCCESS_URL).append(userName).toString();
	}
	
	
}
