package com.tf.usermanagement.endpoint;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Logout {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);
	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession httpSession) {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication= context.getAuthentication();
		LOGGER.info("Logging out user "+ authentication);
		if(authentication!=null)
			authentication.setAuthenticated(false);
		
		httpSession.invalidate();
		LOGGER.info("Logging out user successful, value for isAuthenticated is - "+ authentication.isAuthenticated());
		return new ResponseEntity<>("Logout successful",HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/getPrincipal" }, method = RequestMethod.GET)
	public ResponseEntity<?> getPrincipal(Principal principal) {
		LOGGER.info("Principal details "+principal);
		LOGGER.info("Principal details "+principal.getName());
		return new ResponseEntity<>(principal,HttpStatus.OK);
	}

}
