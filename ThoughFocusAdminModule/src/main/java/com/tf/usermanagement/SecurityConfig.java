/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tf.usermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tf.usermanagement.security.TfAuthProcessingFilter;
import com.tf.usermanagement.security.TfAuthenticationEntryPoint;
import com.tf.usermanagement.security.TfAuthenticationProvider;
import com.tf.usermanagement.security.TfAuthenticationSuccessHandler;
/**
 *
 * @author pradeepkm
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Autowired
    private TfAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired
    private TfAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Bean
    public TfAuthProcessingFilter authenticationTokenFilterBean() throws Exception {
        TfAuthProcessingFilter filter = new TfAuthProcessingFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new TfAuthenticationSuccessHandler());
        return filter;
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder ) throws Exception {
    	LOGGER.info("\n\n NOTE: configuring authentication provider {}\n\n", this.jwtAuthenticationProvider);
        authManagerBuilder.authenticationProvider(this.jwtAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity ) throws Exception {
        
    	LOGGER.info("\n\n NOTE: authentication entry point  {}, authentication provider {} \n\n", jwtAuthenticationEntryPoint, jwtAuthenticationProvider);
        
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and() 
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/*.*").permitAll()
				.antMatchers("/**/*.*").permitAll()
				.antMatchers("/app-content/js/*").permitAll()
				.antMatchers("/app-content/css/*").permitAll()
				.anyRequest().authenticated();
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class);
        
        httpSecurity.headers().cacheControl();
    }   
}
