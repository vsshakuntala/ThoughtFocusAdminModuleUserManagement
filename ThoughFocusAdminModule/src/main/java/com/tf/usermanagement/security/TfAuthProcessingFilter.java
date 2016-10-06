/*
 * To change this license authHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	
	
	public TfAuthProcessingFilter() {
		super("/**");
	}

	@Override
	public boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean retVal=false;
		SecurityContext context=SecurityContextHolder.getContext();
		if(context==null || context.getAuthentication()==null || !context.getAuthentication().isAuthenticated() || context.getAuthentication().getPrincipal()==null)
			retVal=true;
		LOGGER.info("\n\n  NOTE: returning requiresAuthentication for URL {} as {}\n\n",request.getRequestURL(), retVal);
		return retVal;
		// return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest hsr,
			HttpServletResponse hsr1) throws AuthenticationException,
			IOException, ServletException {

		System.out.println("Test "+hsr.getRequestURL()+" "+hsr.getQueryString());
		// Logic for TF Admin parsing URL.
		//int i=parseAdminId(hsr.getRequestURI());
		//System.out.println("id--"+i);
		String adminId = hsr.getParameter("adminId");
		//For testing purpose
		String originUrl = "http://localhost:8089/security/";
		//Production
		//String originUrl = hsr.getHeader("referer");
		LOGGER.info("\n\n ***** Referer URL *****  :- "+originUrl+"\n\n");
		
		if(adminId==null)
			throw new TfAuthenticationException("Missing adminId in url ");
		
							
		TfAuthRequestToken authRequest = new TfAuthRequestToken(new String("4"),originUrl);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	public void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse resp, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LOGGER.info("At successfulAuthentication {} with auth class as {}",authResult, authResult.getClass().getName());
		super.successfulAuthentication(req, resp, chain, authResult);
		LOGGER.info("Post super.successfulAuthentication");
		
		//TfAuthenticationToken tfVlidation = (TfAuthenticationToken)authResult;
		//AdminUser adminUser=(AdminUser) tfVlidation.getPrincipal();
		chain.doFilter(req, resp);
		//req.getRequestDispatcher("/#/usermgmt/user/viewuser").forward(req, resp);
		//resp.sendRedirect(req.getRequestURL()+"?adminEmail="+adminUser.getUsername()+"#/usermgmt/user/viewuser");
		//resp.sendRedirect(successUrlBuilder(req.getRequestURL(),adminUser.getUsername()));
	}
	
	private  String successUrlBuilder(StringBuffer str1,String mail){
		return new StringBuilder(str1.substring(0, str1.lastIndexOf("/"))).append("/#/usermgmt/user/viewuser?adminEmail=").append(mail).toString();
	}
	
	private String parseAdminId(String url){
		LOGGER.info("Parsing url "+url.toString());
		Pattern p = Pattern.compile("/?adminId=(\\d+)");
		  Matcher m = p.matcher(url);
		  if (m.find()) {
			  LOGGER.info("\n Extracting admin id from URL, found adminId-  "+m.group(1));
		    return  m.group(1); 
		  }
		  return null;
	}
	
	
}
