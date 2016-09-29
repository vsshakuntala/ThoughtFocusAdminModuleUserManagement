/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.service.RolesManagementService;

/**
 *
 * @author pradeepkm
 */
@Component
public class TfAuthenticationProvider implements AuthenticationProvider{
	
	@Value("${originUrl}")
	private String ORIGIN_URL;
	
	
	@Autowired
	private RolesManagementService roleManagementService;
	
    public static final Logger LOGGER = LoggerFactory.getLogger(TfAuthenticationProvider.class);
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        TfAuthRequestToken tfVlidation = (TfAuthRequestToken)a;
        String adminId=tfVlidation.getAdminId();
        String originUrl=tfVlidation.getOriginUrl();
        
        //If url is not null, validate the origin
        validateOriginUrl(originUrl);
        
        List<PermissionsDto> listPermissions=roleManagementService.getPermissionForLogedInUser(adminId);
        if(listPermissions==null || listPermissions.isEmpty())
        	throw new TfAuthenticationException("No role/permission found for given user");
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(PermissionsDto p:listPermissions)
        authorities.add(new SimpleGrantedAuthority(p.getPermissionName()));
        
        AdminUser user = new AdminUser(adminId, originUrl, authorities);
        return new TfAuthenticationToken(user, originUrl, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> type) {
        boolean retVal = TfAuthRequestToken.class.isAssignableFrom(type);
        LOGGER.info("in supports with type as {}, retVal= {}", type.getName(), retVal);
        return retVal;
    }
    
    public boolean validateOriginUrl(String originUrl){
    	
    	if(originUrl==null)
    		throw new TfAuthenticationException("\n\nOrigin URL does not match, expected url "+ORIGIN_URL);
		String decodeUrl;
		
			try {
				decodeUrl = URLDecoder.decode(originUrl, "utf-8");
				if(!ORIGIN_URL.contains(decodeUrl))
					throw new TfAuthenticationException("\n\nOrigin URL does not match, expected url "+ORIGIN_URL);
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("Error while validating source URL ", e);
			}
			
		return true;
	}
}
