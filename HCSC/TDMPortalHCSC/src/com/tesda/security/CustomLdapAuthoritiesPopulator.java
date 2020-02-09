/*---------------------------------------------------------------------------------------
 * Object Name: CustomLdapAuthoritiesPopulator.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.security;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.tesda.constants.TDMConstants;
import com.tesda.util.JDBCPreparedStatementSelect;


/*
 * Gets the information(Full Name, Email, Role) of the authenticated user from LDAP.
 */
@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator
{
	private static final Logger logger = LoggerFactory
			.getLogger(CustomLdapAuthoritiesPopulator.class);
	String username = "",role_in_id = "";
	
	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(
			DirContextOperations userData, String username)
	{
		logger.info("CustomLdapAuthoritiesPopulator ~ getGrantedAuthorities ");
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		this.username = username;
		getUserRole();
		getLdapRole(userData, gas);
		logger.info("CustomLdapAuthoritiesPopulator ~ getGrantedAuthorities ~ return");
		return gas;
	}
	
	
	public void getUserRole(){
		
		SimpleDateFormat sdf = new SimpleDateFormat(TDMConstants.MMDDYYYY_HH_MM_SS);
		
		JDBCPreparedStatementSelect jd = new JDBCPreparedStatementSelect();
		try {
			String username_and_role = jd.selectRecordsFromTable(username);
			StringTokenizer st = new StringTokenizer(username_and_role, "-");
			while(st.hasMoreTokens()){
				username = st.nextToken();
				role_in_id = st.nextToken();
			}
			
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		
	}
	

	public void getLdapRole(DirContextOperations userData, Collection<GrantedAuthority> gas)
	{
		logger.info("CustomLdapAuthoritiesPopulator ~ getGrantedAuthorities ~ getLdapRole");
		NamingEnumeration<?> enum1, enum2;

		try
		{
			for (enum2 = userData.getAttributes().getAll(); enum2.hasMore();)
			{
				Attribute attr = (Attribute) enum2.next();
				for (enum1 = attr.getAll(); enum1.hasMore();)
				{
					String token = (String) enum1.next();
					if (attr.getID().equalsIgnoreCase(TDMConstants.LDAP_DISPLAY_NAME))
					{
						username = token;
						gas.add(new SimpleGrantedAuthority("Name:" + token));
					}
					if (attr.getID().equalsIgnoreCase(TDMConstants.LDAP_MAIL))
					{
						gas.add(new SimpleGrantedAuthority("Mail:" + token));
					}
				/*	if (attr.getID().equalsIgnoreCase(TDMConstants.LDAP_MEMBEROF))
					{
						if (token.contains(TDMConstants.TDM_BAM_TESTER))
						{
							gas.add(new SimpleGrantedAuthority(TDMConstants.ROLE_USER));
						}
						else if (token.contains(TDMConstants.TDM_SECURITY_ADMIN)
								|| token.contains(TDMConstants.TDM_SUPER_USER))
						{
							gas.add(new SimpleGrantedAuthority(TDMConstants.ROLE_ADMIN));
						}
					}*/
				}
			}
			
			if (role_in_id.contains(TDMConstants.ROLE_USER))
			{
				gas.add(new SimpleGrantedAuthority(TDMConstants.ROLE_USER));
			}
			else if (role_in_id.contains(TDMConstants.ROLE_ADMIN))
			{
				gas.add(new SimpleGrantedAuthority(TDMConstants.ROLE_ADMIN));
			}
			logger.info("CustomLdapAuthoritiesPopulator ~ getGrantedAuthorities ~ getLdapRole END");
		}
		catch (NamingException ne)
		{
			logger.error("CustomLdapAuthoritiesPopulator ~ getGrantedAuthorities ~ getLdapRole "
					+ ne.getMessage());
		}
	}
}
