package com.tesda.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tesda.dao.impl.TdmPolicyAutoSearchDAOImpl;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
	final static Logger logger = Logger.getLogger(UserAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

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
		request.getSession().setAttribute("projectId", request.getParameter("projectId"));
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
			if (grantedAuthority.getAuthority().equals("ROLE_USER"))
			{
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
			{
				isAdmin = true;
				break;
			}
		}
		if (isUser)
		{
			return "/index";
		} else if (isAdmin)
		{
			return "/index";
		} else
		{
			throw new IllegalStateException();
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request,
			Authentication authentication)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		// System.out.println("authentication.getName() " +
		// authentication.getName());
		// String userName = authentication.getName();
		User user = (User) authentication.getPrincipal();
		request.getSession().setAttribute("UserId", user.getUsername());

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
