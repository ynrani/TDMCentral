/*---------------------------------------------------------------------------------------
 * Object Name: UserAuthenticationSuccessHandler.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tesda.constants.AppConstant;
import com.tesda.util.JDBCPreparedStatementSelect;
import com.tesda.constants.TDMConstants;

/**
 * User authentication handler based on the role and authentication user will be
 * redirected.
 */
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private String role = "";
	private String userName = "";
	private String emailId = "";
	JDBCPreparedStatementSelect jd = null;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException
	{

		handle(request, response, authentication);
		clearAuthenticationAttributes(request, authentication);

	}

	protected void handle(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException
	{
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted())
		{
			System.out.println("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Builds the target URL according to the logic defined in the main class
	 * Javadoc.
	 */
	protected String determineTargetUrl(Authentication authentication)
	{
		boolean isUser = false;
		boolean isAdmin = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities)
		{
			String auth = grantedAuthority.getAuthority();
			if (auth.contains(TDMConstants.ROLE_USER))
			{
				role = TDMConstants.ROLE_USER;
				isUser = true;
			}
			else if (auth.contains(TDMConstants.ROLE_ADMIN))
			{
				role = TDMConstants.ROLE_ADMIN;
				isAdmin = true;
			}
			else if (auth.contains("Name"))
			{
				userName = auth.split(":")[1];
			}
			else if (auth.contains("Mail"))
			{
				emailId = auth.split(":")[1];
			}
		}

		if (isUser && isAdmin)
		{
			/*
			 * Handle if user has multiple roles
			 */
			isUser = false;
			role = TDMConstants.ROLE_ADMIN;
		}
		if (isUser)
		{
			return "/index";
		}
		else if (isAdmin)
		{
			return "/index";
		}
		else
		{
			return "/login?error=#";
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request,
			Authentication authentication)
	{

		//String username = "",role_in_id = "";
		SimpleDateFormat sdf = new SimpleDateFormat(TDMConstants.MMDDYYYY_HH_MM_SS);
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		LdapUserDetails user = (LdapUserDetails) authentication.getPrincipal();
		/*jd = new JDBCPreparedStatementSelect();
		try {
			String username_and_role = jd.selectRecordsFromTable(user.getUsername());
			StringTokenizer st = new StringTokenizer(username_and_role, "-");
			while(st.hasMoreTokens()){
				username = st.nextToken();
				role_in_id = st.nextToken();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
				
		request.getSession().setAttribute("projectId",
				request.getParameter("projectId").toUpperCase());
		request.getSession().setAttribute(TDMConstants.ROLE, role);
		request.getSession().setAttribute(TDMConstants.USER_NAME, userName);
		request.getSession().setAttribute(TDMConstants.USER_ID, userName);
		request.getSession().setAttribute(TDMConstants.EMAIL_ID, emailId);
		request.getSession().setAttribute(TDMConstants.SEARCH_COUNT, 0);
		request.getSession()
				.setAttribute(TDMConstants.LOGIN_TIME, sdf.format(new Date().getTime()));
		/*HttpSession session = request.getSession(false);
		if (session == null)
		{
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		SimpleDateFormat sdf = new SimpleDateFormat(TDMConstants.MMDDYYYY_HH_MM_SS);
		LdapUserDetails user = (LdapUserDetails) authentication.getPrincipal();
		if (user.getUsername() != null)
		{
			request.getSession().setAttribute("UserId", user.getUsername().toUpperCase());

		}
		request.getSession().setAttribute("projectId",
				request.getParameter("projectId").toUpperCase());
		request.getSession().setAttribute(TDMConstants.ROLE, role);
		request.getSession().setAttribute(TDMConstants.USER_NAME, userName);
		request.getSession().setAttribute(TDMConstants.EMAIL_ID, emailId);
		request.getSession().setAttribute(TDMConstants.SEARCH_COUNT, 0);
		request.getSession()
				.setAttribute(TDMConstants.LOGIN_TIME, sdf.format(new Date().getTime()));*/

	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy)
	{
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy()
	{
		return redirectStrategy;
	}
}
