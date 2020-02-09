package com.tesda.security;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tesda.constants.TDMConstants;
import com.tesda.dao.impl.TDMReportingDAOImpl;
import com.tesda.model.DO.TDMSessionReportDO;
import com.tesda.service.impl.ReportingServiceImpl;

@Component
@Scope(TDMConstants.SCOPE_SESSION)
public class SessionTimeoutListener extends HttpSessionEventPublisher
{

	@Override
	public void sessionCreated(javax.servlet.http.HttpSessionEvent event)
	{

	}

	@Override
	public void sessionDestroyed(javax.servlet.http.HttpSessionEvent event)
	{
		HttpSession currentSession = event.getSession();
		String userId = (String) currentSession.getAttribute(TDMConstants.USER_ID);
		if (userId != null)
		{
			try
			{
				TDMSessionReportDO reportDo = new TDMSessionReportDO();
				reportDo.setApplication((String) currentSession
						.getAttribute(TDMConstants.PROJECT_ID));

				reportDo.setEmail((String) currentSession.getAttribute(TDMConstants.EMAIL_ID));
				reportDo.setUserID((String) currentSession.getAttribute(TDMConstants.USER_ID));

				reportDo.setUserName((String) currentSession.getAttribute(TDMConstants.USER_NAME));
				reportDo.setRole((String) currentSession.getAttribute(TDMConstants.ROLE));
				reportDo.setQueryCount((Integer) (currentSession
						.getAttribute(TDMConstants.SEARCH_COUNT)));
				String intime = (String) currentSession.getAttribute(TDMConstants.LOGIN_TIME);

				SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MMDDYYYY_HH_MM_SS);

				reportDo.setInTime(new Timestamp(format.parse(intime).getTime()));
				reportDo.setOutTime(new Timestamp(format.parse(format.format(new Date())).getTime()));

				final ApplicationContext ctx = WebApplicationContextUtils
						.getWebApplicationContext(event.getSession().getServletContext());

				ReportingServiceImpl searchManagementService = (ReportingServiceImpl) ctx
						.getBean("reportingService");

				TDMReportingDAOImpl reportingDao = (TDMReportingDAOImpl) ctx
						.getBean("reportingDao");

				JpaTransactionManager manager = (JpaTransactionManager) ctx
						.getBean("transactionManagerUser");
				EntityManager entityManager = manager.getEntityManagerFactory()
						.createEntityManager();
				searchManagementService.updateSessionDetails(entityManager, reportingDao, reportDo);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
