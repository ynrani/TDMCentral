<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <jsp:include page="headerNew.jsp"></jsp:include> 

<!DOCTYPE html>
  <!-- saved from url=(0014)about:internet -->
 <html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TDM Central | Auto Policy Search</title>
	  <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="images/favicon.ico" >	
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="css/stylesNew.css">
    <link rel="stylesheet" type="text/css" href="css/datamining.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">

	  <script type="text/javascript" src="js/jquery.min.js"></script>
	  <script type="text/javascript" src="js/jquery.datatable.min.js"></script>
	  <script type="text/javascript" src="js/jquery-ui.js"></script>
    <script type="text/javascript" src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>

  </head>
  <body>
  
  <div class="container">
  <div id="main" class="wrapper mainAll">

<%-- 	 <jsp:include page="HeaderNew.jsp"></jsp:include> --%>
		
			<c:if test="${error ne null}">
				<table class="my-error-class">
					<tbody>
						<tr>
							<td class="lable-title" align="left" valign="middle">
								${error}</td>
						</tr>
					</tbody>
				</table>
				
			</c:if>
			
			<div id="myErrorCls"></div>
			<form:form id="testDataForm" name="testDataForm"
				action="${pageContext.request.contextPath}/dataMinningAutoPolicySearch"
				modelAttribute="tdmAtsSearchDTO">
	  <div class="request-tab">
		  <div class="main-content">

			  <div class="request-header">
				  <div class="csa-header-bar"></div>
				  <div class="page-heading">
					  <i class="fa fa-cogs"></i>
					  <h1>Data Mining</h1>
				  </div>

				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs data-mine-tabs" role="tablist" id="request-tab">
					  <li role="presentation" class="auto-search active">
						  <a data-target="#manual" aria-controls="manual" role="tab" data-toggle="tab">
							  <i class="fa fa-clock-o csaa-vectors"></i>Auto Policy
						  </a>
					  </li>
					  <li role="presentation" class="policy-search" >
						  <a data-target="#property" aria-controls="property" role="tab" data-toggle="tab" href="/policyPropertyNew" >
							  <i class="fa fa-lock csaa-vectors"></i>Property Policy
						  </a>
					  </li>
				  </ul>
			  </div>


			  <!-- Tab panes -->
			  <div class="tab-content" id="tabData">
				  <div role="tabpanel" class="tab-pane active" id="manual">
					  <div class="content-section datamine-content-section">
						  <div class="collpasewidget active" id="generalDetails">
							  <div class="widget-inner">
								  <div id="generalDetailsContent" class="widget-content">
									  <div class="table-parent">
										  <table class="details-table">
											  <tbody>
											  <tr>
												  <td class="lable-title blue-label" width="25%" align="left" valign="middle">
												  <span data-toggle="tooltip" title="Environment from where data is needed">Environment</span></td>
												  <td class="flied-title" width="25%" align="left" valign="middle">
													<form:select path="envType" id="envType" class="down-control"
										required="true">
										<c:if test="${not empty environment}">
											<c:forEach var="envFieldListDTO" items="${environment}">
												<form:option value="${envFieldListDTO.valueCode}">${envFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
												  </td>
												  <td class="lable-title" width="25%" align="left" valign="middle"></td>
												  <td class="flied-title" width="25%" align="left" valign="middle">
												  </td>
											  </tr>
											  </tbody>
										  </table>
									  </div>

								  </div>
								  <div class="widget-title">
									  <div class="container-fluid">
										  <div class="row">
											  <div class="col-xs-12">
												  <i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
												  <span class="request-title">Basic Search</span>
											  </div>
										  </div>
									  </div>
								  </div>
								  <div id="basicSearch" class="widget-content">
									  <div class="table-parent">
										  <div id="otherDetails" class="other-details">
											  <div class="table-parent other-table-parent">
												  <table class="details-table">
													  <tbody>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
														  <span class="tool" data-toggle="tooltip" title="AAA_CSA-CA Select,AAA_SS-Signature Series">Product Type</span></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <form:select	path="addproductType" id="addproductType" class="down-control">
															  <c:if test="${not empty producttype}">
															<c:forEach var="prdFieldListDTO" items="${producttype}">
														<form:option value="${prdFieldListDTO.valueCode}">${prdFieldListDTO.listValue}</form:option>
												`	</c:forEach>
												</c:if>
											</form:select>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle">
														  <span class="tool" data-toggle="tooltip" title="Current status of the policy">Policy Status</span></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <form:select path="policyStage" id="policyStage"	class="down-control">
																<c:if test="${not empty policystatus}">
																<c:forEach var="policyFieldListDTO" items="${policystatus}">
																<form:option value="${policyFieldListDTO.valueCode}">${policyFieldListDTO.listValue}</form:option>
															</c:forEach>
														</c:if>

														</form:select>
														  </td>
													  </tr>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="State where policy is written">Risk State</span></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <form:select path="riskState" id="riskState" 	class="down-control">

										<c:if test="${not empty riskstate}">
											<c:forEach var="riskFieldListDTO" items="${riskstate}">
												<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>														  
									</td>														 
									 <td class="lable-title" width="25%" align="left" valign="middle">
									 <span class="tool" data-toggle="tooltip" title="The period a policy is in force, from the beginning or effective date to the expiration date">Policy Term</span></td>
									 <td class="flied-title" width="25%" align="left" valign="middle">
										<form:select path="policyTerm" id="policyTerm" class="down-control">
										<c:if test="${not empty policyterm}">
											<c:forEach var="policytermFieldListDTO" items="${policyterm}">
												<form:option value="${policytermFieldListDTO.valueCode}">${policytermFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
								 </td>
								 </tr>
							  <tr>
								  <td class="lable-title" align="left" valign="middle">
								  <spring:message code="label.policy.coverage" />
								  </td>
								<td class="flied-title" align="left" valign="middle">
									<form:select multiple="multiple" path="policyCovge" id="policyCovge" class="down-control-list-checkbox">
										<c:if test="${not empty policylevel}">
											<c:forEach var="policyLeveltermFieldListDTO"
												items="${policylevel}">
												<c:if test="${policyLeveltermFieldListDTO.valueCode eq 'Any'}">
												<form:option selected="selected"
													value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
													</c:if>
													
													<c:if test="${policyLeveltermFieldListDTO.valueCode ne 'Any'}">
												<form:option
													value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
													</c:if> 
												</c:forEach>
										</c:if>
									</form:select></td>
							  <td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Vehicle Level Coverage">Vehicle Level Coverage</span></td>
							  <td class="flied-title" width="25%" align="left" valign="middle">				 
									<form:select multiple="multiple" path="addRiskCovge" id="addRiskCovge"
										class="down-control-list-checkbox">
										<c:if test="${not empty vehiclelevel}">
											<c:forEach var="vehicleLeveltermFieldListDTO"
												items="${vehiclelevel}">
											  <c:if test="${vehicleLeveltermFieldListDTO.valueCode eq 'Any'}">
												<form:option selected="selected"
													value="${vehicleLeveltermFieldListDTO.valueCode}">${vehicleLeveltermFieldListDTO.listValue}</form:option>
													</c:if>
													
													<c:if test="${vehicleLeveltermFieldListDTO.valueCode ne 'Any'}">
												<form:option
													value="${vehicleLeveltermFieldListDTO.valueCode}">${vehicleLeveltermFieldListDTO.listValue}</form:option>
													</c:if>
											 </c:forEach>
										</c:if>

									</form:select>
														  </td>
													  </tr>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  <span class="tool" data-toggle="tooltip" title="Automobile Death Benefits">Automobile Death Benefits</span>
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio data-mine-radio">
																  <label>
																	  <input type="radio" id="autoDeathYes" name="autoDeath" value="Yes" />Yes
																  </label>
																  <label>
																	  <input type="radio" id="autoDeathNo" name="autoDeath" value="No" />No
																  </label>
																  <label>
																	  <input type="radio" id="autoDeathAny" name="autoDeath" value="Any" checked="checked"/>Any
																  </label>
															  </div>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  <span class="tool" data-toggle="tooltip" title="Total Disablility">Total Disablility</span>
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio data-mine-radio">
																  <label>
																	  <input type="radio" id="totDisaYes" name="autoTotDisa" value="Yes" />Yes
																  </label>
																  <label>
																	  <input type="radio" id="totDisaNo" name="autoTotDisa" value="No" />No
																  </label>
																  <label>
																	  <input type="radio" id="totDisaAny" name="autoTotDisa" value="Any" checked="checked"/>Any
																  </label>
															  </div>
														  </td>
													  </tr>
													  </tbody>
												  </table>
											  </div>

										  </div>
										  <div id="billing" class="other-details">
											  <div class="table-parent other-table-parent">
												  <div class="widget-title">
													  <div class="container-fluid">
														  <div class="row">
															  <div class="col-xs-12 billing-title">
																  <i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
																  <span class="request-title">Billing</span>
															  </div>
														  </div>
													  </div>
												  </div>
												  <table class="details-table widget-content">
													  <tbody>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  <span class="tool" data-toggle="tooltip" title="Payment plan is the lumpsum or equally distributed payment towards insurance premium amount">Payment Plan</span>
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															 <form:select
										path="addPaymentPlan" id="addPaymentPlan" class="down-control">
										<c:if test="${not empty paymentplan}">
											<c:forEach var="paymentPlantermFieldListDTO"
												items="${paymentplan}">
												<form:option
													value="${paymentPlantermFieldListDTO.valueCode}">${paymentPlantermFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle"></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
														  </td>
													  </tr>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  Total Due
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio data-mine-radio">
																  <label>
																	  <input type="radio" id="payOffYes" name="payOff" value="Yes" />Yes
																  </label>
																  <label>
																	  <input type="radio" id="payOffNo" name="payOff" value="No" />No
																  </label>
																  <label>
																	  <input type="radio" id="payOffAny" name="payOff" value="Any" checked="checked"/>Any
																  </label>
															  </div>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle"></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
														  </td>
													  </tr>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  Do you want a policy with autopay eligibility?
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio data-mine-radio">
																  <label>
																	  <input type="radio" id="autoPayYes" name="autoPay" value="Yes" />Yes
																  </label>
																  <label>
																	  <input type="radio" id="autoPayNo" name="autoPay" value="No" />No
																  </label>
																  <label>
																	  <input type="radio" id="autoPayAny" name="autoPay" value="Any" checked="checked"/>Any
																  </label>
															  </div>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle"></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
														  </td>
													  </tr>
													  <tr>
														  <td class="lable-title" width="25%" align="left" valign="middle">
															  Minimum Due
														  </td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio data-mine-radio">
																  <label>
																	  <input type="radio" id="currBalYes" name="currBal" value="Yes" />Yes
																  </label>
																  <label>
																	  <input type="radio" id="currBalNo" name="currBal" value="No" />No
																  </label>
																  <label>
																	  <input type="radio" id="currBalAny" name="currBal" value="Any" checked="checked"/>Any
																  </label>
															  </div>
														  </td>
														  <td class="lable-title" width="25%" align="left" valign="middle"></td>
														  <td class="flied-title" width="25%" align="left" valign="middle">
														  </td>
													  </tr>
													  </tbody>
												  </table>
											  </div>
										  </div>
									  </div>
								  </div>
								  <div class="widget-title">
									  <div class="container-fluid">
										  <div class="row">
											  <div class="col-xs-12">
												  <i class="fa fa-plus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
												  <span class="request-title">Show Advanced Search</span>
											  </div>
										  </div>
									  </div>
								  </div>
								  <div id="advSearch" class="other-details widget-content">
									  <div class="table-parent">
										  <table class="details-table">
											  <tbody>
											  <tr>
												  <td class="lable-title" width="13%" align="left" valign="middle">
													  <span class="tool" data-toggle="tooltip" title="Number of Drivers">Number of Drivers</span>
												  </td>
												  <td class="flied-title adv-search-title" width="12%" align="left" valign="middle">
													  <form:select
										path="noOfDrivers" id="noOfDrivers" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select>
												  </td>
												  <td class="lable-title" width="13%" align="left" valign="middle">
													  <span class="tool" data-toggle="tooltip" title="First person in whose name the insurance policy is issued">Number of Named Insured</span>
												  </td>
												  <td class="flied-title adv-search-title" width="12%" align="left" valign="middle">
													  <form:select
										path="noOfViola" id="noOfViola" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select>
												  </td>
												  <td class="lable-title" width="13%" align="left" valign="middle">
													  <span class="tool" data-toggle="tooltip" title="Number of Vehicles">Number of Vehicles</span>
												  </td>
												  <td class="flied-title adv-search-title" width="12%" align="left" valign="middle">
													  <form:select
										path="noOfVehi" id="noOfVehi" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
										<form:option value="8">8</form:option>
									</form:select>
												  </td>
												  <td class="lable-title" width="13%" align="left" valign="middle">
													  <span class="tool">Number of Violations</span>
												  </td>
												  <td class="flied-title adv-search-title" width="12%" align="left" valign="middle">
													  <form:select
										path="noOfViola" id="noOfViola" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select>
												  </td>
											  </tr>
											  </tbody>
										  </table>
									  </div>
								  </div>
							  </div>
						  </div>

						  <div class="btn-container">
						  <input type="submit"  value="Search" id="btnSearchResultAuto" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style" title="Search" 
						  	/><i class="fa fa-search csaa-vectors"></i>
							  <!-- <button type="button" id="btnSearchResultAuto" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style" title="Search">
								  <i class="fa fa-search csaa-vectors"></i>Search
							  </button> -->
							  
							  <button type="button" id="btnRsetResultAuto" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style" title="Reset">
								  <i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
							  </button>
						  </div>
						  <div id="searchresultsAuto" class="other-details search-details" style="display:none">
							  <div class="table-parent other-table-parent collpasewidget">
								  <div class="widget-title">
									  <div class="container-fluid">
										  <div class="row">
											  <div class="col-xs-12">
												  <i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
												  <span class="request-title">Search Results</span>
											  </div>
										  </div>
									  </div>
								  </div>
								  <div class="table-parent search-main widget-content" id="myid">
									   <table id="autoSearchTable" class="customDataTable" cellspacing="0" width="100%">
										  <thead>
										  <tr>
											  <th></th>
											  <th>Reserve*</th>
											  <th>Policy <br />Number</th>
											  <th>Product <br />Type</th>
											  <th>Policy <br />Stage</th>
											  <th>Risk <br />State</th>
											  <th>Policy <br />Term</th>
											  <th>Effective <br />Date</th>
											  <th>Expiration <br />Date</th>
											  <th>Risk Level <br />Converage</th>
											  <th>Available <br />Payments</th>
											  <th>Available <br />Documents</th>
											  <th>Document <br />Type</th>
										  </tr>
										  </thead>
									  
										<tbody>
								<c:forEach
									items="${tdmPolicyAutoSearchDTO.tdmPolicyPropertySearchResultDTOList}"
									var="tdmPolicyAutoSearchResultDTOList" varStatus="status">
									<tr>
										<c:if
											test="${tdmPolicyAutoSearchResultDTOList.reservedYN eq null }">
											<td><label class="checkbox-inline"> <form:checkbox
														path="tdmPolicyAutoSearchResultDTOList[${status.index}].reservedYN"
														id="tdmPolicyAutoSearchResultDTOList[${status.index}].reservedYN"
														class="cb_class checkBoxCls" value="Yes" />
											</label></td>
										</c:if>

										<td>${tdmPolicyAutoSearchResultDTOList.policynumber}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.productType}</td>
									    <td>${tdmPolicyAutoSearchResultDTOList.policyStage}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyState}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyTerm}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyEffectDt}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyExpDt}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.riskCovge}</td>	
										<td>${tdmPolicyAutoSearchResultDTOList.assoPayReq}</td>	
										<td>${tdmPolicyAutoSearchResultDTOList.assoDocReq}</td>								
										<td>${tdmPolicyAutoSearchResultDTOList.docType}</td>

									</tr>
								</c:forEach>

								<c:forEach
									items="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}"
									var="tdmPolicyAutoSearchResultDTOList" varStatus="status">

									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policynumber" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].productType" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyStage" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyState" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyTerm" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyEffectDt" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyExpDt" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].riskCovge" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].assoPayReq" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].assoDocReq" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].docType" />

								</c:forEach>
							</tbody>
						</table> 
					</div>
									  
									  
									 <div class="result-btn-container">
					  <button type="button" id="btnSave" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve" title="Reserve">Reserve</button>
					  <button type="button" id="btnClose" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export" title="Export">Export</button>
				  </div>
										<div class="widget-title res-widget-title">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-12">
													<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
													<span class="request-title">View Reservation</span>
												</div>
											</div>
										</div>
									</div>
								  <div id="reservTable" class="table-parent reser-details widget-content">
									  <div class="table-parent">
										  <p>Record found. Reservation details for the current policy search criteria</p>
										  <table id="autoReservationTable" class="customDataTable" cellspacing="0" width="100%">
										  <thead>
										  <tr>
											  <th>#</th>
											  <th>User Id</th>
											  <th>Number of Reserved Records</th>
											  <th>Email User</th>
										  </tr>
										  </thead>
									  </table>
									  </div>
								  </div>
								  </div>
								  
							  </div>
						  </div>
					  </div>

				  <!--Property tab-->
				   <div role="tabpanel" class="tab-pane" id="property">
					<div class="content-section datamine-content-section">
							<div class="collpasewidget active" id="generalDetails">
								<div class="widget-inner">
									<div id="generalDetailsContent" class="widget-content">
										<div class="table-parent">
											<table class="details-table">
												<tbody>
												<tr>
													<td class="lable-title blue-label" width="25%" align="left" valign="middle">
														<span data-toggle="tooltip" title="" data-original-title="Environment from where data is needed" class="grey-tooltip">
															Environment
														</span>
													</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:select path="envType" id="envType" class="down-control"
										required="true">
										<c:if test="${not empty environment}">
											<c:forEach var="envFieldListDTO" items="${environment}">
												<form:option value="${envFieldListDTO.valueCode}">${envFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
													</td>
													<td class="lable-title" width="25%" align="left" valign="middle"></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
													</td>
												</tr>
												</tbody>
											</table>
										</div>

									</div>
									<div class="widget-title">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-12">
													<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
													<span class="request-title">Basic Search</span>
												</div>
											</div>
										</div>
									</div>
									<div id="basicSearch" class="widget-content">
										<div class="table-parent">
											<div id="otherDetails" class="other-details">
												<div class="table-parent other-table-parent">
													<table class="details-table">
														<tbody>
														<tr>
															<td class="lable-title" width="25%" align="left" valign="middle">
																<span class="tool" data-toggle="tooltip" title="AAA_CSA-CA Select,AAA_SS-Signature Series">
																	Product Type
																</span>
															</td>
															<td class="flied-title" width="25%" align="left" valign="middle">
																<form:select
										path="addproductType" id="addproductType" class="down-control">

										<c:if test="${not empty producttype}">
											<c:forEach var="prdFieldListDTO" items="${producttype}">
												<form:option value="${prdFieldListDTO.valueCode}">${prdFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
															</td>
															<td class="lable-title" width="25%" align="left" valign="middle">
																<span class="tool" data-toggle="tooltip" title="Current status of the policy">Policy Status</span>
															</td>
															<td class="flied-title" width="25%" align="left" valign="middle">
																<form:select path="policyStage" id="policyStage"
										class="down-control">
										<c:if test="${not empty policystatus}">
											<c:forEach var="policyFieldListDTO" items="${policystatus}">
												<form:option value="${policyFieldListDTO.valueCode}">${policyFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select>
															</td>
														</tr>
														<tr>
															<td class="lable-title" width="25%" align="left" valign="middle" ><span class="tool" data-toggle="tooltip" title="State where policy is written">Risk State</span></td>
															<td class="flied-title" width="25%" align="left" valign="middle">
																<form:select path="policyState" id="policyState1"
										class="down-control">
										<c:if test="${not empty riskstate}">
											<c:forEach var="riskFieldListDTO" items="${riskstate}">
												<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select>
								</td>
							<td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Policy Type">Policy Type</span></td>
							<td class="flied-title" width="25%" align="left" valign="middle">
									<form:select  path="policyType" id="policyType" class="down-control">
										<c:if test="${not empty policytype}">
											<c:forEach var="ptFieldListDTO" items="${policytype}">
												<form:option value="${ptFieldListDTO.valueCode}">${ptFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
								</td>
								</tr>
										<tr>
											<td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Policy Coverage">Policy Coverage</span></td>
											<td class="flied-title" width="25%" align="left" valign="middle">
												<form:select
										multiple="multiple" path="policyCovge" id="policyCovge1"
										class="down-control-list-checkbox">
										<c:if test="${not empty policylevel}">
											<c:forEach var="policyLeveltermFieldListDTO"
												items="${policylevel}">
												 <c:if test="${policyLeveltermFieldListDTO.valueCode eq 'Any'}">
												<form:option selected="selected"
													value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
													</c:if>
													
													<c:if test="${policyLeveltermFieldListDTO.valueCode ne 'Any'}">
												<form:option
													value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
													</c:if> 
											</c:forEach>
											
										</c:if>

									</form:select>
															</td>

															<td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Total Disablility">Total Disablility</span></td>
															<td class="flied-title" width="25%" align="left" valign="middle">
																<div class="radio data-mine-radio">
																	<label>
																		<input type="radio" id="totDisaYes" name="totDisa" value="Yes" />Yes
																	</label>
																	<label>
																		<input type="radio" id="totDisaNo" name="totDisa" value="No" />No
																	</label>
																	<label>
																		<input type="radio" id="totDisaAny" name="totDisa" value="Any" checked="checked"/>Any
																	</label>
																</div>
															</td>
														</tr>
														<tr>
															<td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Automobile Death Benefits">Automobile Death Benefits</span></td>
															<td class="flied-title" width="25%" align="left" valign="middle">
																<div class="radio data-mine-radio">
																	<label>
																		<input type="radio" id="propDeathYes" name="propDeath" value="Yes" />Yes
																	</label>
																	<label>
																		<input type="radio" id="propDeathNo" name="propDeath" value="No" />No
																	</label>
																	<label>
																		<input type="radio" id="propDeathAny" name="propDeath" value="Any" checked="checked"/>Any
																	</label>
																</div>
															</td>

														</tr>
														</tbody>
													</table>
												</div>

											</div>
											<div id="billing" class="other-details">
												<div class="table-parent other-table-parent">
													<div class="widget-title">
														<div class="container-fluid">
															<div class="row">
																<div class="col-xs-12 billing-title">
																	<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
																	<span class="request-title">Billing</span>
																</div>
															</div>
														</div>
													</div>
													<table class="details-table widget-content">
														<tbody>
															<tr>
																<td class="lable-title" width="25%" align="left" valign="middle"><span class="tool" data-toggle="tooltip" title="Payment plan is the lumpsum or equally distributed payment towards insurance premium amount">Payment Plan</span></td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																	<select id="payPlan" name="payPlan" class="down-control">
																	</select>
																</td>
																<td class="lable-title" width="25%" align="left" valign="middle"></td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																</td>
															</tr>
															<tr>
																<td class="lable-title" width="25%" align="left" valign="middle">
																	<span class="tool" data-toggle="tooltip" title="Total Due">
																		Total Due
																	</span>
																</td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																	<div class="radio data-mine-radio">
																		<label>
																			<input type="radio" id="payOffYes" name="propPayOff" value="Yes" />Yes
																		</label>
																		<label>
																			<input type="radio" id="payOffNo" name="propPayOff" value="No" />No
																		</label>
																		<label>
																			<input type="radio" id="payOffAny" name="propPayOff" value="Any" checked="checked"/>Any
																		</label>
																	</div>
																</td>
																<td class="lable-title" width="25%" align="left" valign="middle"></td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																</td>
															</tr>
															<tr>
																<td class="lable-title" width="25%" align="left" valign="middle">
																	Do you want a policy with autopay eligibility?
																</td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																	<div class="radio data-mine-radio">
																		<label>
																			<input type="radio" id="autoPayYes" name="propPay" value="Yes" />Yes
																		</label>
																		<label>
																			<input type="radio" id="autoPayNo" name="propPay" value="No" />No
																		</label>
																		<label>
																			<input type="radio" id="autoPayAny" name="propPay" value="Any" checked="checked"/>Any
																		</label>
																	</div>
																</td>
																<td class="lable-title" width="25%" align="left" valign="middle"></td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																</td>
															</tr>
															<tr>
																<td class="lable-title" width="25%" align="left" valign="middle">
																	<span class="tool" data-toggle="tooltip" title="Minimum Due">
																		Minimum Due
																	</span>
																</td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																	<div class="radio data-mine-radio">
																		<label>
																			<input type="radio" id="currBalYes" name="propCurrBal" value="Yes" />Yes
																		</label>
																		<label>
																			<input type="radio" id="currBalNo" name="propCurrBal" value="No" />No
																		</label>
																		<label>
																			<input type="radio" id="currBalAny" name="propCurrBal" value="Any" checked="checked"/>Any
																		</label>
																	</div>
																</td>
																<td class="lable-title" width="25%" align="left" valign="middle"></td>
																<td class="flied-title" width="25%" align="left" valign="middle">
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>


								</div>
							</div>

							<div class="btn-container">
								<button type="button" id="btnSearchResultProp" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style" title="Search">
									<i class="fa fa-search csaa-vectors"></i>Search
								</button>
								<button type="button" id="btnRsetResultProp" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style" title="Reset">
									<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
								</button>
							</div>
							<div id="searchresultsprop" class="other-details search-details" style="display:none">
								<div class="table-parent other-table-parent collpasewidget">
									<div class="widget-title">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-12">
													<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
													<span class="request-title">Search Results</span>
												</div>
											</div>
										</div>
									</div>-->
									<div class="table-parent search-main widget-content">
										<table id="propSearchTable" class="customDataTable" cellspacing="0" width="100%">
											<thead>
											<tr>
												<th></th>
												<th>Reserve*</th>
												<th>Policy <br />Number</th>
												<th>Product <br />Type</th>
												<th>Policy <br />Stage</th>
												<th>Risk <br />State</th>
												<th>Policy <br />Term</th>
												<th>Effective <br />Date</th>
												<th>Expiration <br />Date</th>
												<th>Risk Level <br />Converage</th>
												<th>Available <br />Payments</th>
												<th>Available <br />Documents</th>
												<th>Document <br />Type</th>
											</tr>
											</thead>
										</table>
										<div class="result-btn-container">
					  <button type="button" id="btnSave" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve" title="Reserve">Reserve</button>
					  <button type="button" id="btnClose" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export" title="Export">Export</button>
				  </div>
									</div>
									<div class="widget-title res-widget-title">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-12">
													<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
													<span class="request-title">View Reservation</span>
												</div>
											</div>
										</div>
									</div>
									<div id="reservTable" class="table-parent reser-details widget-content">
										<div class="table-parent">
											<p>Record found. Reservation details for the current policy search criteria</p>
											<table id="reservationTable" class="customDataTable" cellspacing="0" width="100%">
											  <thead>
											  <tr>
												  <th>#</th>
												  <th>User Id</th>
												  <th>Number of Reserved Records</th>
												  <th>Email User</th>
											  </tr>
											  </thead>
										  </table>
										</div>

									</div>
								</div>
							</div>
						</div>
			  </div> 
		  </div>
		  
		  </div>
	</div>
	 </form:form>
	 </div>
	 </div>
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
  <script type="text/javascript" src="js/search-common.js"></script>
   <script type="text/javascript" src="js/autopolicy.js"></script> 
	<script type="text/javascript" src="js/proppolicy.js"></script>
	
  </body>
</html>