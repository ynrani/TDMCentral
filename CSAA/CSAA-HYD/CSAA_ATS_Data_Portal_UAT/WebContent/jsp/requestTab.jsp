<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="headerNew.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Data Generation</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="css/jquery-te.css">
<link rel="stylesheet" type="text/css" href="css/style-footer.css">
<link rel="stylesheet" type="text/css" href="css/demo-footer.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
<link rel="stylesheet" type="text/css" href="css/custom.ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/stickyfooter.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">
<link rel="stylesheet" type="text/css" href="css/customDataTable.css">
<link rel="stylesheet" type="text/css" href="lib/datetimepicker/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/common.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="lib/datetimepicker/moment.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="lib/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/jquery-te.min.js"></script>
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/messages.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>

</head>
<body>
<div class="loaderDiv" style='display: none;'></div>
<script>
  var userRole ='<%=(String) request.getSession().getAttribute("ROLE")%>';
	var consumerGroup = '${requestDataDTO.consumerGroup}';
	var priority = '${requestDataDTO.priority}';
	var environment = '${requestDataDTO.environment}';
	var dataSource = '${requestDataDTO.dataSource}';
	var assignedGroup = '${requestDataDTO.assignedGroup}';
	var assignedToId = '${requestDataDTO.assignedToId}';
	var status = '${requestDataDTO.status}';
	var serviceIdentifier = null;
	serviceIdentifier = '${serviceIdentifier}';
	var autoPolicyTableData = '';
	var propPolicyTableData = '';
	var AutoCountryList = '';
	var paymentPlan ='';
	var policyTypeArr='';
	var riskStateArr='';
	var paymentPlan1='';
	if('${autoPolicyLst}' != '')
	{
		autoPolicyTableData = JSON.parse('${autoPolicyLst}');
	}
	if('${propPolicyLst}' != '')
	{
		propPolicyTableData = JSON.parse('${propPolicyLst}');
	}
	if('$(AutoCountryList)' != '')
	{
		AutoCountryList = JSON.parse('${AutoCountryList}');
	}
	if('$(AutoPaymentPlan)' != '')
	{
		paymentPlan = JSON.parse('${AutoPaymentPlan}');
	}
	if('$(PropertyPolicyType)' != '')
	{
		policyTypeArr = JSON.parse('${PropertyPolicyType}');
	}
	if('$(PropertyRiskState)' != '')
	{
		riskStateArr = JSON.parse('${PropertyRiskState}');
	}
	if('$(PropertyPaymentPlan)' != '')
	{
		paymentPlan1 = JSON.parse('${PropertyPaymentPlan}');
	}
	
</script>
	<%
		String requestId = (String) request.getSession().getAttribute(
				"requestId");
	%>
	<%
		String UserName = (String) request.getSession().getAttribute(
				"UserName");
	%>
	<main class="main-style">
	<div class="container big-container">
		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="page-heading">
						<!--<i class="fa fa-cogs"></i>
					<h1>PAS Data Creation Request</h1>-->
						<ol class="breadcrumb">
							<li>Home</li>
							<li>Data Services</li>
							<li class="active">Data Generation</li>
						</ol>
					</div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs data-gen-tabs" role="tablist" id="request-tab">
						<li role="presentation" class="active"><a
							data-target="#manual" aria-controls="manual" role="tab"
							data-toggle="tab"> <i
								class="fa fa-pencil-square-o fa-lg csaa-vectors"></i>Data
								Request

						</a></li>
						<li role="presentation"> 
								<a id="activityLink" href="./dcActivityLog?requestId=${requestDataDTO.requestId}"><i
								class="fa fa-history fa-lg csaa-vectors"></i>Request Activity Log</a>
						</li>

					</ul>

				</div>
				<form:form id="requestDataForm" name="requestDataForm"
					action="${pageContext.request.contextPath}/requestDataNew"
					modelAttribute="requestDataDTO" autocomplete="off">

					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="manual">
							<div class="content-section">
								<div class="collpasewidget active" id="generalDetails">
									<div class="widget-inner">
										<div class="widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-minus-square-o plus-minus-style fa-plus-square-o"
															title="Expand/Collpase Details"></i> 
															<span
															class="request-title">General Details</span>
														<!--<div class="dash-bar"></div>-->
													</div>
												</div>
											</div>
										</div>


										<div id="generalDetailsContent" class="widget-content">
											<table class="details-table data-gen-table">
												<tbody>

													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.serviceType" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="serviceType"
																id="serviceType" class="form-control" /></td>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.reqId" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="requestId"
																id="requestId" class="form-control" /></td>

													</tr>
													<tr>

														<c:choose>
														<c:when test="${userRole=='ROLE_USER' }">

														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.reqBy" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="requestedBy"
																id="requestedBy" class="form-control"
																value="${UserName}${\" (\"}${requestDataDTO.requestedBy}${\" )\"}" /></td>

														</c:when>    
															<c:otherwise>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.reqBy" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="requestedBy"
																id="requestedBy" class="form-control"
																value="${requestDataDTO.requestedBy}" /></td>
														</c:otherwise>
														</c:choose>


														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.createdOn" /></td>
														<td class="flied-title" width="14%" align="left"
															valign="middle"><form:input path="createdOn"
																id="createdOn" class="form-control" value="" /></td>




													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.applciationOwner" /></td>


														<td class="flied-title" width="14%" align="left"
															valign="middle"><form:input path="applicationOwner"
																id="applicationOwner" class="form-control" type="text"
																value="${requestDataDTO.applicationOwner}" /></td>



														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.assignedGroup" /></td>

														<td class="flied-title" width="14%" align="left"
															valign="middle"><form:select path="assignedGroup"
																id="assignedGroup" class="down-control">
																<c:if
																	test="${not empty requestDataDTO.assignedGroupList}">
																	<c:forEach var="requestDataDTO"
																		items="${requestDataDTO.assignedGroupList}">
																		<form:option value="${requestDataDTO.assignGroupId}">${requestDataDTO.assignGroupName}</form:option>
																	</c:forEach>
																</c:if>
															</form:select></td>



													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.approver" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="approver"
																id="approver" class="form-control" type="text"
																value="${requestDataDTO.approver}" /></td>



														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.assignedTo" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:select path="assignedToId"
																id="assignedToId" class="down-control">
																<%-- <form:option value="0">None</form:option> --%>
																<c:if test="${not empty requestDataDTO.supportUserList}">
																	<c:forEach var="requestDataDTO"
																		items="${requestDataDTO.supportUserList}">
																		
																		   <form:option  value="${requestDataDTO.supportUserId}">${requestDataDTO.supportUserName}</form:option>				
																		 
																	</c:forEach>
																</c:if>

															</form:select></td>

													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.status" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:select path="status"
																id="status" class="down-control">
																<%-- <form:option value="0">None</form:option> --%>
																<c:if
																	test="${not empty requestDataDTO.requestStatusList}">
																	<c:forEach var="requestDataDTO"
																		items="${requestDataDTO.requestStatusList}">
																		<form:option value="${requestDataDTO.requestStatusId}">${requestDataDTO.statusName}</form:option>
																	</c:forEach>
																</c:if>
															</form:select></td>
															
															<%-- <td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.fulfill" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:select path="fulfill"
																id="fulfill" class="down-control">
																<form:option  value="Auto">AUTO</form:option>
																<form:option  value="Manual">Manual</form:option>
															</form:select></td> --%>
															
															<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message code="label.fulfill" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:select path="status"
																id="status" class="down-control">
																<form:option  value="0">Manual</form:option>
																<form:option  value="1">Auto</form:option> 
															</form:select></td>
															
													</tr>

													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.statusChange" /></td>
														<td class="flied-title" colspan=4  align="left"
															valign="middle"><form:input path="statusChange"
																id="statusChange" class="form-control full-length-input" /></td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="widget-inner">
											<div class="widget-title">
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-12">
															<i class="fa fa-minus-square-o plus-minus-style"
																title="Expand/Collpase Details"></i> <span
																class="request-title">Request Details</span>
															<!--<div class="request-dash-bar"></div>-->
														</div>
													</div>
												</div>
											</div>

											<div id="otherDetails" class="other-details widget-content">
												<table class="details-table data-gen-table">
													<tbody>

														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.subject.short" /><span class="login-error">*</span></td>
															<td class="flied-title" colspan=4 align="left"
																valign="middle"><form:input path="subject"
																	id="subject" required="true" maxlength="100" class="form-control full-length-input subject-class-inc" 
																	type="text" value="${requestDataDTO.subject}"  /></td>
														</tr>

														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.consumerGroup" /><span class="login-error">*</span></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="consumerGroup"
																	id="consumerGroup" class="down-control required">
																	<form:option  value="">--Select--</form:option>
																	<c:if
																		test="${not empty requestDataDTO.consumerGroupList}">
																		<c:forEach var="requestDataDTO"
																			items="${requestDataDTO.consumerGroupList}">
																			<form:option
																				value="${requestDataDTO.consumerGroupId}">${requestDataDTO.consumerGroupName}</form:option>
																		</c:forEach>
																	</c:if>
																</form:select></td>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.priority" /><span class="login-error">*</span></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="Priority"
																	id="Priority" class="down-control">
																	<c:if
																		test="${not empty requestDataDTO.requestPriorityList}">
																		<c:forEach var="requestDataDTO"
																			items="${requestDataDTO.requestPriorityList}">
																			<form:option value="${requestDataDTO.priorityID}">${requestDataDTO.priorityName}</form:option>
																		</c:forEach>
																	</c:if>
																</form:select></td>
														</tr>
														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.dataSource" /></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="dataSource"
																	id="dataSource" class="down-control">
																	<c:if test="${not empty requestDataDTO.dataSourceList}">dataSourceList
			             												<c:forEach var="requestDataDTO"
																			items="${requestDataDTO.dataSourceList}">
																			<form:option value="${requestDataDTO.dataSourceId}">${requestDataDTO.dataSourceName}</form:option>
																		</c:forEach>
																	</c:if>
																</form:select></td>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.environement" /><span class="login-error">*</span></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="environment"
																	id="environment" class="down-control required" required="true">
																	<form:option selected="true" value="">--Select--</form:option>
																	<c:if
																		test="${not empty requestDataDTO.environmentList}">
																		<c:forEach var="requestDataDTO"
																			items="${requestDataDTO.environmentList}">
																			<c:if
																				test="${requestDataDTO.environmentName eq 'PAS-EP2' }">
																				<form:option value="${requestDataDTO.environmentId}"
																					>${requestDataDTO.environmentName}</form:option>
																			</c:if>

																			<form:option value="${requestDataDTO.environmentId}"
																		>${requestDataDTO.environmentName}</form:option>

																		</c:forEach>
																	</c:if>
																</form:select></td>
														</tr>
														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.expectedDate" />
																	<span class="login-error">*</span>
															</td>
															<td class="flied-title" width="25%" align="left"
																valign="middle">
																	<div class='input-group date' id='datetimepicker1'>
																		<form:input path="expectedDate"
																		id="expectedDate" readonly='true' 
																		class="form-control date-control-style date-width-style dateHigherThanToday" required="true" />
																		
																		<!-- <input type='text' class="form-control" />-->
													                    <span class='input-group-addon data-clock'>
													                        <span class='glyphicon glyphicon-calendar'>
													                        	<img class='ui-datepicker-trigger' src='images/calendar.png' alt='' title=''>
													                        </span>
													                    </span>
																	</div>
																	
																	</td>
																	
																		<td class="lable-title" width="14%" align="left"
																valign="middle"><div id='lblEnvDesc'>Enviorment Description
																	<span class="login-error">*</span></div>
															</td>
															<td class="flied-title" width="25%" align="left"
																valign="middle">	
																		<form:input path="envDesc"
																		id="envDesc"  
																		class="form-control" required="true" />	
															</td>

														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</div>
									 <fieldset class="field-style">
									 	  
										  <div class="details-table details-extended">
											  <div class="collpasewidget active policy-container-style" id="autoPolicyContainer">
												  <!-- <div id="chkAutoPolicyDiv" class="radio-btn-style"><span class="policy-label-style">Do you want to create auto policy data?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><label class="chk-label-style"><input id="chkAutoYes" type="radio" name="autoPolicyType" class="chk-control" value="0" checked/>Yes</label>
													<label class="chk-label-style"><input id="chkAutoNo" type="radio" name="autoPolicyType" class="chk-control" value="1"/>No</label></div> -->
												  <div class="widget-inner policy-widget-style" id="autoWidgetDiv">

													  <div class="policy-title"><span class ="toolbar-btn-style normal-cursor" style="float:left">Auto Policy</span><button type="button" id="autoAddButton" class="toolbar-btn-style" ><i class="fa fa-plus fa-size-icon"></i> <span class="data-scenario-plus-style">Data Request Scenario</span></button></div>
													  <!-- <div class="policy-title"><span class ="toolbar-btn-style normal-cursor" style="float:left">Auto Policy</span><button type="button" id="autoAddButton" class="toolbar-btn-style" title='Add Scenario'>+ Data Request Scenario</button></div> -->
													  <div id="autoPolicyContent" class="widget-content policy-content">
														  <table id="autoPolicyTable" class="customDataTable" >
															  <thead>
															  <tr>
																  <th>Scenario No</th>
																  <th>Risk State</th>
																  <th>Payment Plan</th>
																  <th>Number of Drivers</th>
																  <th>Number of Vehicles</th>
																  <th>Number of Policies</th>
																  <th>Effective Date</th>
																  <th>Additional Request Notes<i data-toggle="tooltip" title="Additional Request Notes" class="fa fa-question-circle question-tooltip-style"></th>
																  <th></th>
																</tr>
															</thead>
														<%-- 	<tbody>
															  <c:if
																	test="${autoPolicyList ne null}">
																<c:forEach items="${autoPolicyList}" var="item">
																	<tr>
																		<td>${item.scenarioNo}</td>
																		<td>${item.riskState}</td>
																		<td>${item.paymentPlan}</td>
																		<td>${item.noOfDrivers}</td>
																		<td>${item.noOfVehicles}</td>
																		<td>${item.noOfPolicies}</td>
																		<td>${item.additionalInformation}</td>
																	</tr>
																</c:forEach>
																</c:if>
															</tbody> --%>
														  </table>
													  </div>
												  </div>
											  </div>
											  <div class="collpasewidget active policy-container-style" id="propertyPolicyContainer">
												 <!--  <div id="chkPropertyPolicyDiv" class='radio-btn-style'>
													  <span class="policy-label-style">Do you want to create property policy data?</span>
													  <label class="chk-label-style">
														  <input id="chkPropYes" type="radio" name="propPolicyType" class="chk-control" value="yes" checked/>Yes
													  </label>
													<label class="chk-label-style"><input id="chkPropNo" type="radio" name="propPolicyType" class="chk-control" value="no"/>No</label></div> -->
												  <div class="widget-inner policy-widget-style"  id="propertyWidgetDiv">

													  <div class="policy-title"><span class ="toolbar-btn-style normal-cursor" style="float:left">Property Policy</span>
														  <button type="button" id="propertyAddButton" class="toolbar-btn-style"><i class="fa fa-plus fa-size-icon"></i> <span class="data-scenario-plus-style">Data Request Scenario</span></button>
													  </div>
													  <div id="propertyPolicyContent" class="widget-content policy-content">
														  <table id="propertyPolicyTable" class="customDataTable" >
															  <thead>
															  <tr>
																  <th>Scenario No</th>
																  <th>Policy Type</th>
																  <th>Risk State</th>
																  <th>Payment Plan</th>
																  <th>Number of Policies</th>
																  <th>Additional Request Notes<i data-toggle="tooltip" title="Additional Request Notes" class="fa fa-question-circle question-tooltip-style"></th>
																  <th></th>
															  </tr>
															  </thead>
															  <%--  <tbody>
															  <c:if
																	test="${propertyPolicyList ne null}">
																<c:forEach items="${propertyPolicyList}" var="item">
																	<tr>
																		<td>${item.scenarioNo}</td>
																		<td>${item.riskState}</td>
																		<td>${item.policyType}</td>
																		<td>${item.paymentPlan}</td>
																		
																		<td>${item.noOfPolicies}</td>
																		<td>${item.additionalInformation}</td>
																	</tr>
																</c:forEach>
																</c:if>
															</tbody> --%>
														</table>
													</div>
												</div>
											</div>
										</div>
										<div id="tableDataRequiredMsg" class="login-error table-error" style="display:none;">Please fill in the Auto/Property Policy Table</div>
									</fieldset>
									<div id="buttons" class="btn-container">
										<!-- <button type="button" id="btnEmail" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover email-btn-style" title="Email Notification" data-toggle="modal" data-target="#emailModal">		
										<i class="fa fa-envelope csaa-vectors"></i>Email		
									</button> -->


										<button type="submit" id="btnSave" value="Save1" name="save1"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style">
											<i class="fa fa-floppy-o csaa-vectors"></i>Save
										</button>
										<button type="submit" id="btnBack" value="Back" name="back"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"
											title="Back to My Request Page">
											<i class="fa fa-undo csaa-vectors"></i>Back
										</button>

										<button type="submit" id="btnSubmit" value="btnSubmit"
											name="btnSubmit"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"
											title="Submit Request">
											<i class="fa fa-sign-in csaa-vectors"></i>Submit
										</button>
										
										<input type="hidden"  value="" id="autoPolicye" name="autoPolicye" />
										<input type="hidden"  value="" id="propPolicye" name="propPolicye" />
										<input type="hidden"  value="" id="genDet" name="genDet" />
										<!--   <input type="submit" id="save" value="Save" name="save"
														class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve"
														title="Save"/>
														
									<input type="submit" id="submit" value="Submit" name="submit" onclick="validate();"
														class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export"
														title="Submit"/>
														 -->

										<!-- <button type="button" id="btnSubmit" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"  title="Submit Request">
										<i class="fa fa-sign-in csaa-vectors"></i>Submit
									</button>-->
										<!-- <button type="button" id="btnReset"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
											title="Reset Request">
											<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
										</button> -->
										
										<button type="button" id="btnReset" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover com-submit-btn"  title="Reset Request">
											  <i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
										  </button>

										<form:hidden path="serviceIdentifier"
											value="${serviceIdentifier}" />



									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="history">
							<div class="content-section">
								<div class="container-fluid">
									<div class="row">
										<div class="col-xs-12">
											<div id="historyDetails"
												class="table-parent reser-details history-details">
												<div class="table-parent">
													<table id="historyTable" class="customDataTable">
														<thead>
															<tr>
																<th>Request Number</th>
																<th>Date</th>
																<th>Requested by</th>
																<th>Status</th>
																<th>Description</th>
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
				</form:form>
			</div>
		</div>
		<div class="modal fade email-modal" id="emailModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title email-title" id="emailTitle">Email
										Notification</h4>
								</div>
								<div class="modal-body">
									<div class="col-xs-8 right-border">
										<form class="form-horizontal">
											<div class="form-group">
												<label for="emailAdd"
													class="col-sm-2 control-label email-label">To :</label>
												<div class="col-sm-10">
													<input type="email" class="email-control" id="emailAdd">
												</div>
											</div>
											<div class="form-group">
												<label for="ccAdd"
													class="col-sm-2 control-label email-label">Cc : </label>
												<div class="col-sm-10">
													<input type="email" class="email-control" id="ccAdd">
												</div>
											</div>
											<div class="form-group">
												<label for="emailSubject"
													class="col-sm-2 control-label email-label">Subject
													: </label>
												<div class="col-sm-10">
													<input type="text" class="email-control" id="emailSubject">
												</div>
											</div>
											<div class="form-group">
												<label for="emailMessage"
													class="col-sm-2 control-label email-label">Message
													: </label>
												<div class="col-sm-10">
													<textarea class="form-control jqte-test" rows="10"
														id="emailMessage"></textarea>
												</div>
											</div>
										</form>
									</div>
									<div class="col-xs-4 center-align-style">
										<button id="btnNotificationTemplate"
											class="new-policy-text new-request-link-style full-width email-notification"
											title="Notification Template">Notification Template</button>
										<button type="button" id="btnRequestAck"
											class="email-popup-btn-style" title="Request Acknowledge">
											Request Acknowledge</button>
										<button type="button" id="btnRequestCompletionNot"
											class="email-popup-btn-style"
											title="Request Completion Notification">Request
											Completion Notification</button>
										<button type="button" id="btnAddDetailNot"
											class="email-popup-btn-style"
											title="Additional Detail Notification">Additional
											Detail Notification</button>
										<button type="button" id="btnReqRejNot"
											class="email-popup-btn-style"
											title="Request Reject Notification">Request Reject
											Notification</button>
										<button type="button" id="btnEscalNot"
											class="email-popup-btn-style" title="Escalation Notification">
											Escalation Notification</button>
										<button type="button" id="btnAddApproval"
											class="email-popup-btn-style"
											title="Seeking for Additional Approval">Seeking for
											Additional Approval</button>
									</div>

								</div>

								<div class="modal-footer">
									<div class="col-xs-8">
										<button type="button" class="btn btn-primary">Send
											Notification</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<footer class="home-footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="nav-links">
						<ul>
							<li><a href="./governance#gettingStarted">About Us</a></li>
							<li><a href="./contactus">Contact Us</a></li>
							<li><a href="javascript:void(0)">Site Map</a></li>
						</ul>
						<div class="copyright">&copy;2016 CSAA Insurance Group.</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript" src="js/request.js"></script>
	<script type="text/javascript" src="js/history.js"></script>
	<script type="text/javascript" src="js/emailEditor.js"></script>
	<!-- <script type="text/javascript" src="js/search-common.js"></script> -->

	
	<script>
		
		if (userRole != null && userRole == 'ROLE_ADMIN') {
			$('#btnNewRequest').hide();
		}
		var count = 0;
		var serviceType = 'Data Generation Manual';
		//to get the current date in the mm/dd/yyyy formaTE.	
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		var timeStamp = today.getHours();
		var timeStamp1 = today.getMinutes();
		var timeStamp2 = today.getSeconds();
		//var timeStamp3 = today.getDay();
		var date = mm + '/' + dd + '/' + yyyy + ' ' + timeStamp + ':'
				+ timeStamp1+ ':'
				+ timeStamp2;
   if(status=="")
	   {
	   document.getElementById('createdOn').value = date;
	   }
		
		document.getElementById('serviceType').value = serviceType;
		$(document)
				.ready(
						function() {
							var reqId = '${requestDataDTO.requestId}';
							var status = '${requestDataDTO.status}';
							if (reqId != null && reqId != '') {
	
								if (userRole != null && userRole == 'ROLE_USER'
										&& status == 'Saved'
										&& serviceIdentifier != 'myRequest' && serviceIdentifier != 'activityLog') {
									BootstrapDialog.show(
													{
														id:"saveMsgDialog",
														modal : true,
														title : "Data Creation Request",
														resize : "auto",
														position : 'center',
														height : 170,
														width : 600,
														//bgiframe : true,
														//show : 'fade',
														//hide : 'fade',
														dialogClass : "noclose",
														onshow : function(dialog) {
															var markup = '<div>Your data is saved :Your request no: '
																	+ reqId
																	+ '</div>';
															dialog.setMessage(markup);
															dialog.getModalHeader().addClass('email-title');
															$("#subject").prop(
																	"readonly",
																	true);
															$("#expectedDate")
																	.prop(
																			"readonly",
																			true);

														},
														buttons : [
																  {
																	cssClass:"btn btn-primary",
																	label:"Close",
																	action: function(dialog) {
																	 dialog.close();
																	}
																  }]
													});
								}
							}
							
							function openMessageDialog()
							  {
								  BootstrapDialog.show({
									title: "Message from ATS Data Portal",
									resize:"auto",
									position: 'center',
									height:170,
									width:600,
									//bgiframe: true,
									//show: 'fade',
									//hide:'fade',
									//dialogClass: "noclose",
									onshow: function(dialog) {
									  var markup = '<div><i class="fa fa-check question-tooltip-style"></i>The submit was successful.<br/>Your request no. '+ $('#requestId').val() +' has been SUBMIITED.<br />This request will be assigned to ATS Data Support team for fulfillment.</div>';
									  dialog.setMessage(markup);
									  dialog.getModalHeader().addClass('email-title');
									},
									buttons: [
									   {
										cssClass:"btn btn-primary",
										label:"Close",
										action: function(dialog) {
										 dialog.close();
										}
									  }
									]
								  });
							  }
							
							if (userRole != null && userRole == 'ROLE_USER'
									&& status == 'New'
									&& serviceIdentifier != 'myRequest' && serviceIdentifier != 'activityLog') {
								
								openMessageDialog();
							
												} 

												if (userRole != null
														&& userRole == 'ROLE_ADMIN') {
													var reqId = '${requestDataDTO.requestId}'
													if (reqId == "") {
														alert('Your not authorised to create data request. Please login with Role User credentials for creating data request');
														return false;
													}

													/* if ($('#statusChange')
															.val() == null
															|| $(
																	'#statusChange')
																	.val() == '') {
														alert("Please Enter the Status Change Description");
														return false;
													} */
												}
											});
							
						
							
							
							
				
	</script>

	<script src="js/landing.js"></script>

	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html>
