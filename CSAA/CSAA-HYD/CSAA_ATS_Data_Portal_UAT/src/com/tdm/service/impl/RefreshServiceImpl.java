package com.tdm.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.dao.RefreshDAO;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.service.RefreshService;

@Component
@Service("refreshService")
@Transactional(propagation = Propagation.REQUIRED)
public class RefreshServiceImpl extends TdmBaseServiceImpl implements
		RefreshService {
	final static Logger logger = Logger.getLogger(RefreshServiceImpl.class);

	@Autowired
	RefreshDAO refreshDAO;

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getPolicysummaryData(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		String process = "<br> Started to drop stage tables and re-creating stage tables.</br>";

		try {
			boolean refreshFlag = false;
			// boolean scriptFlag = false;
			boolean scriptFlag = false;

			// scriptFlag = refreshDAO.getRunScript(request, response);
			scriptFlag = refreshDAO.dropAndRecreateStageTables(request,
					response);

			if (scriptFlag) {
				process = process
						+ "<br> Dropped and re-created the stage tables.</br>";
				process = process
						+ "<br> Stared refreshing Policy summary stage table.</br>";
				refreshFlag = refreshDAO.getPolicysummaryData();
				process = process
						+ "<br> Refreshed Policy summary stage table.</br>";
				System.out
						.println("@@@@@@@@@@@@@@  >  Part - 1 Done .........  ");
				process = process
						+ "<br>Started Refresh for child tables.</br>";
				refreshFlag = refreshDAO.getPolicyDerivedData(); //
				System.out
						.println("%%%%%%%%%%%%%  >  Part - 2 Done .........  ");
				process = process + "<br>Stage table are refreshed.</br>";
			}

			return process;

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public boolean getPolicyCoverageData() throws ServiceException {
		try {
			boolean refreshFlag = refreshDAO.getPolicyRiskCoverage();
			System.out.println(" Part - 2 Done .........  ");

			return refreshFlag;

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public boolean getPolicyDerivedData() throws ServiceException {
		try {
			boolean refreshFlag = refreshDAO.getPolicyDerivedData();
			System.out.println(" Part - 3 Done .........  ");

			return refreshFlag;

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

}
