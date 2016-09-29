/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement.security;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author pradeepkm
 */
public class TfAuthenticationException extends AuthenticationException{
    private static final long serialVersionUID = 1L;

	public TfAuthenticationException(String msg) {
        super(msg);
    }
    
}
