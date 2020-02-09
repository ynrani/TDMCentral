package com.tdm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.service.RefreshService;

@Controller
public class TDMRefreshController {
	private static Logger logger = Logger.getLogger(TDMRefreshController.class);

	@Resource(name = MessageConstant.DB_REFRESH_SERVICE)
	RefreshService refreshService;

	@RequestMapping(value = AppConstant.DATAREFRESH, method = RequestMethod.GET)
	public String refreshDB(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		logger.info("Start of DB refresh");
		try {
			refreshService.getPolicysummaryData(request, response);

			List<String> dbRefresh = new ArrayList<String>();
			dbRefresh
					.add("Below steps are done as part of Database Referesh.\n");
			dbRefresh.add("Staging tables are droped and recreated.\n");
			dbRefresh
					.add("Data fetched from source tables and inserted into appropriate staging tables.\n");
			model.addAttribute("DB_STATUS", dbRefresh);
		} catch (Exception e) {
			logger.error("Problem while performing the Database refresh", e);
			throw e;
		}

		logger.info("End of DB refresh.");
		return AppConstant.TDM_DB_REFRESH;
	}
}
