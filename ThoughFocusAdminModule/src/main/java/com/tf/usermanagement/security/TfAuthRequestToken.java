/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import java.util.Collections;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author pradeepkm
 */
public class TfAuthRequestToken extends AbstractAuthenticationToken {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String adminId;
    private final String originUrl;
    
	public TfAuthRequestToken(String adminId,String originUrl) {
        super(Collections.<GrantedAuthority>emptyList());
        this.adminId = adminId;
        this.originUrl=originUrl;
    }
    
    
    @Override
    public Object getCredentials() {
        return originUrl;
    }

    @Override
    public Object getPrincipal() {
    	
        return adminId;
    }


	public String getAdminId() {
		return adminId;
	}


	public String getOriginUrl() {
		return originUrl;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TfAuthRequestToken [adminId=");
		builder.append(adminId);
		builder.append(", originUrl=");
		builder.append(originUrl);
		builder.append(", isOriginUrlNeedValidation=");
		builder.append("]");
		return builder.toString();
	}


	
    

    
}
