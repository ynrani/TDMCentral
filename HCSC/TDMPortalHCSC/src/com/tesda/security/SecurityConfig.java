/*---------------------------------------------------------------------------------------
 * Object Name: SecurityConfig.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}