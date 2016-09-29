/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author pradeepkm
 */
@Component
public class TfAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 24967439387607832L;

	@Override
    public void commence(HttpServletRequest hsr, HttpServletResponse resp, AuthenticationException ae) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
    
    
}
