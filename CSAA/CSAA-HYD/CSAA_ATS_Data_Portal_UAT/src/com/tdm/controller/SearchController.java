/*---------------------------------------------------------------------------------------
 * Object Name: LoginController.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          12/06/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.tdm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdm.constant.AppConstant;
import com.tdm.constant.UIScreenENUM;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMAtsSearchDTO;
import com.tdm.service.SearchService;

/**
 * @author Narasimha
 * @version 1.0
 */
@Controller
public class SearchController {
	
	Map<String, List<FieldListDTO>> pageDataMap = null;
	@Autowired
	SearchService searchService;

	@RequestMapping(value =AppConstant.TDM_LOAD_DATA, method = RequestMethod.GET)
	public String getPageData(@ModelAttribute("tdmAtsSearchDTO") TDMAtsSearchDTO tdmAtsSearchDTO,
			@RequestParam(value = AppConstant.PAGE, required = false) String page, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		pageDataMap= searchService.getAutoSearchPageData(
				UIScreenENUM.AUTO_DATA_SEARCH.getPageName());
		model.addAttribute("pageDataMap",pageDataMap);
		
		return AppConstant.TDM_ATS_DATA;
	}
}
