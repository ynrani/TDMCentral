<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="headerNew.jsp" ></jsp:include> 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>TDM Central | Data Subset</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<!-- <link rel="stylesheet" href="css/landing_menu.css" >	 -->
	<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/data-subset.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="lib/datetimepicker/bootstrap-datetimepicker.min.css">
	<link type="text/css" rel="stylesheet" href="css/jquery.faloading.min.css" />
	<!--<link rel="stylesheet" type="text/css" href="css/bootstrap-dialog.css">-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="lib/datetimepicker/moment.js"></script>
	<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript" src="lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="lib/datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
	
</head>
<body>

<script>

var envObj = '';
if('${envObject}' != '')
{
	envObj = JSON.parse('${envObject}');
}

 var productTypeList = '';
var policystatusList = '';
var riskStateArr='';
var policytermList=''; 
var paymentplanList ='';
var subsetScenariosList='';
 if('${producttype}' != '')
{
	productTypeList = JSON.parse('${producttype}');
}
if('${policystatus}' != '')
{
	policystatusList = JSON.parse('${policystatus}');
}
if('${riskstate}' != '')
{
	riskStateArr = JSON.parse('${riskstate}');
}
if('${policyterm}' != '')
{
	policytermList = JSON.parse('${policyterm}');
} 
if('${paymentplan}' != '')
{
	paymentplanList = JSON.parse('${paymentplan}');
}
if('${subsetScenarios}' != '')
{
	subsetScenariosList = JSON.parse('${subsetScenarios}');
}

</script>
	<main class="main-style">
		<div class="container subset-container">
			<div class="request-tab">
				<div class="main-content">
					<div class="request-header">
						<div class="page-heading">
							<!--<i class="fa fa-cogs"></i>
                            <h1>PAS Data Creation Request / <h1 id="lblRequest">Request Id - 12345</h1></h1>-->
							<ol class="breadcrumb">
								<li>Home</li>
								<li>Data Services</li>
								<li class="active">Data Subset</li>
							</ol>
						</div>
					</div>
					<div class="row subset-msg-parent">
								<div class="col-xs-1">
									<i class="fa fa-pie-chart subset-icon"></i>
								</div>
								<div class="col-xs-8">
									<p class="subset-title">This is auto data generation request. There are some already created scenarios for you. Select any scenario for you want to generate request.</p>
								</div>						
						</div>
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist" id="request-tab">
							<li role="presentation" class="active">
								<a data-target="#subset" aria-controls="manual" role="tab" data-toggle="tab">
									<i class="fa fa-database csaa-vectors"></i>Data Subset
								</a>
							</li>
							<li role="presentation">
								<a data-target="#activity" aria-controls="history" role="tab" data-toggle="tab">
									<i class="fa fa-history csaa-vectors"></i>Subset Activity Log
								</a>
							</li>							
						</ul>

					</div>
					<form:form id="subsetForm" name="subsetForm"
					action="${pageContext.request.contextPath}/dataSubSet"
					modelAttribute="subSetDTO" autocomplete="off">
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
														<i class="fa fa-minus-square-o plus-minus-style fa-plus-square-o" title="Expand/Collpase Details"></i>
														<span class="request-title">General Details</span>
														<!--<i id="btnfetchDetail" class="fa fa-get-pocket fetch-details" title="Fill Details"></i>-->
													</div>
												</div>
											</div>
										</div>

										 <div id="generalDetailsContent" class="widget-content">
											<table class="details-table">
												 <tbody>
											<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.reqBy" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle" >
														<form:input path="requestedBy" id="txtRequestedBy" class="form-control" value="${subSetDTO.requestedBy}" />
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message code="label.reqId"/></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
													<form:input path="requestId" id="txtRequestID" class="form-control" value="${subSetDTO.requestId}"/></td>
												</tr>
												 <tr>
													<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.applciationOwner" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:input path="applicationOwner" id="txtAppOwner" class="form-control" type="text" value="${subSetDTO.applicationOwner}" />
													</td>
													
												<%-- 	<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.createdOn" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:input path="createdOn" id="createdOn" class="form-control"  />
													</td> --%>
													
													<td class="lable-title" width="14%" align="left"
															valign="middle"><spring:message
																code="label.createdOn" /></td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><form:input path="createdOn"
																id="createdOn" class="form-control" value="" /></td>
													
													
												</tr>
												<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Approver-1</td>
													<td class="flied-title subset-flied-title" width="25%" align="left" valign="middle">
														<input id="txtApproverOne" name="approver" class="form-control subset-app-action subset-flied-input" type="text" value="" />
														<button type="button" id="actionOneApp" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style">Approve</button>
														<button type="button" id="actionOneRej" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style">Reject</button>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Approved On</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="appOneDate" name="appOneDate" class="form-control" type="text" value=""/>
													</td>
													
												</tr>
												<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Approver-2</td>
													<td class="flied-title subset-flied-title" width="25%" align="left" valign="middle">
														<input id="txtApproverTwo" name="approver" class="form-control subset-app-action subset-flied-input" type="text" value="" />
														<button type="button" id="actionTwoApp" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style">Approve</button>
														<button type="button" id="actionTwoRej" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style">Reject</button>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Approved On</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="appTwoDate" name="appOneDate" class="form-control" type="text" value=""/>
													</td>
													
												</tr>
												<tr>
													<%-- <td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.approver" />
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:input path="approver"	id="txtApprover" class="form-control" type="text" value="${subSetDTO.approver}" />
													</td> --%>
													
													<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.assignedGroup" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:select path="assignedGroup"
																id="assignedGroup" class="down-control">
																<c:if
																	test="${not empty subSetDTO.assignedGroupList}">
																	<c:forEach var="subSetDTO"	items="${subSetDTO.assignedGroupList}">
																		<form:option value="${subSetDTO.assignGroupId}">${subSetDTO.assignGroupName}</form:option>
																	</c:forEach>
																</c:if>
														</form:select>
													</td>
													 <td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.assignedTo" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:select path="assignedToId" id="ddlAssignedTo" class="down-control">
															<c:if test="${not empty subSetDTO.supportUserList}">
																<c:forEach var="subSetDTO"	items="${subSetDTO.supportUserList}">
																	<form:option  value="${subSetDTO.supportUserId}">${subSetDTO.supportUserName}</form:option>				
																</c:forEach>
															</c:if>
														</form:select>
													</td> 
												</tr>
													 <tr>
													<td class="lable-title" width="14%" align="left" valign="middle">
													<spring:message	code="label.subc.status" /></td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<form:select path="status" id="ddlStatus" class="down-control">
																<form:option value="0">None</form:option>
																<c:if
																	test="${not empty subSetDTO.requestStatusList}">
																	<c:forEach var="subSetDTO" items="${subSetDTO.requestStatusList}">
																		<form:option value="${subSetDTO.requestStatusId}">${subSetDTO.statusName}</form:option>
																	</c:forEach>
																</c:if>
															</form:select> 
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Fullfillment Type</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<select id="fullfillMentType" name="status" class="down-control">
														</select>
													</td>
												</tr> 
												<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Status Notes</td>
													<td class="flied-title subset-flied-title" colspan="4" align="left" valign="middle">
														<input id="statusNotes" name="description" class="form-control full-length-input" type="text" value=""  />
													</td>
												</tr>	 						
												</tbody> 
											</table>
										</div>
										<div class="widget-inner">
											<div class="widget-title">
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-12">
															<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
															<span class="request-title">Request Details</span>
															<!--<div class="request-dash-bar"></div>-->
														</div>
													</div>
												</div>
											</div>

											<div id="otherDetails" class="other-details widget-content">
												<table class="details-table">
													<tbody>
													<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">
															<spring:message	code="label.subject.short" /></td>
														<td class="flied-title" colspan="4" align="left" valign="middle">
														<form:input path="envDesc" id="description" required="true"
														   maxlength="100" class="form-control full-length-input" 
																	type="text" value="${subSetDTO.envDesc}"  />
														</td>
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message code="label.consumerGroup" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
														<form:select path="consumerGroup" id="ddlConsumerGroup" class="down-control">
																	<form:option  value="">--Select--</form:option>
																	<c:if
																		test="${not empty subSetDTO.consumerGroupList}">
																		<c:forEach var="subSetDTO"
																			items="${subSetDTO.consumerGroupList}">
																			<form:option
																				value="${subSetDTO.consumerGroupId}">${subSetDTO.consumerGroupName}</form:option>
																		</c:forEach>
																	</c:if>
																</form:select>
																
														</td>														
													</tr>
													<tr>
														<%-- <td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message code="label.applicationName" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="appName" name="appName" class="down-control">
															</select>
														</td> --%>
														<td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message	code="label.priority" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<form:select path="priority" id="ddlPriority" class="down-control">
																	<c:if
																		test="${not empty subSetDTO.requestPriorityList}">
																		<c:forEach var="subSetDTO"
																			items="${subSetDTO.requestPriorityList}">
																			<form:option value="${subSetDTO.priorityID}">${subSetDTO.priorityName}</form:option>
																		</c:forEach>
																	</c:if>
															</form:select>
														</td>
													</tr>
													<tr>
														
														
													</tr>
													
													<tr class="env-sel">
														
													</tr>
													<tr class="env-sel">
														
													</tr>
										
													<tr>
														<%-- <td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message	code="label.priority" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<form:select path="priority" id="ddlPriority" class="down-control">
																	<c:if
																		test="${not empty subSetDTO.requestPriorityList}">
																		<c:forEach var="subSetDTO"
																			items="${subSetDTO.requestPriorityList}">
																			<form:option value="${subSetDTO.priorityID}">${subSetDTO.priorityName}</form:option>
																		</c:forEach>
																	</c:if>
															</form:select>
														</td> --%>
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message	code="label.expectedDate" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<div class='input-group date' id='datetimepicker1'>
															<form:input path="expectedDate"	id="expectedDate" readonly='true' 
																		class="form-control date-control-style dateHigherThanToday expected-date-width" required="true" />
															<span class='input-group-addon data-clock'>
													                  <span class='glyphicon glyphicon-calendar'>
													                      <img class='ui-datepicker-trigger' src='images/calendar.png' alt='' title=''>
													                   </span>
													            </span>
															</div>
														</td>
													</tr>	
													
																									
													</tbody>
												</table>
												
												<div class="widget-inner">
											<div class="widget-title">
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-12">
															<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
															<span class="request-title">Subset Environment</span>
															<!--<div class="request-dash-bar"></div>-->
														</div>
													</div>
												</div>
											</div>
											<div id="envDetails" class="other-details widget-content">
												<table class="details-table">
													<tbody>
													<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">Data Source</td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="appName" name="appName" class="down-control">
															</select>
														</td>														
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message code="label.sourceEnv" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<!-- <select id="ddlSrcEnv"  name="dataSource" class="down-control">
															</select> -->
															<form:select path="environment"
																	id="ddlSrcEnv" class="down-control">
																	<form:option selected="true" value="">--Select--</form:option>
																	<c:if
																		test="${not empty subSetDTO.environmentList}">
																		<c:forEach var="subSetDTO"
																			items="${subSetDTO.environmentList}">
																			<c:if
																				test="${subSetDTO.environmentName eq 'PAS-EP2' }">
																				<form:option value="${subSetDTO.environmentId}"
																					>${subSetDTO.environmentName}</form:option>
																			</c:if>

																			<form:option value="${subSetDTO.environmentId}"
																		>${subSetDTO.environmentName}</form:option>

																		</c:forEach>
																	</c:if>
																</form:select>
														</td>
														<td class="lable-title" width="14%" align="left" valign="middle">
														<spring:message code="label.targetEnv" /></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															
															<form:select path="environment"
																	id="ddlTargetEnv" class="down-control">
																	<form:option selected="true" value="">--Select--</form:option>
																	<c:if
																		test="${not empty subSetDTO.environmentList}">
																		<c:forEach var="subSetDTO"
																			items="${subSetDTO.environmentList}">
																			<c:if
																				test="${subSetDTO.environmentName eq 'PAS-EP2' }">
																				<form:option value="${subSetDTO.environmentId}"
																					>${subSetDTO.environmentName}</form:option>
																			</c:if>

																			<form:option value="${subSetDTO.environmentId}"
																		>${subSetDTO.environmentName}</form:option>

																		</c:forEach>
																	</c:if>
																</form:select>
														</td>
													</tr>
													<tr class="env-sel">
														<td class="lable-title src-env" width="14%" align="left" valign="middle">Server Name</td>
														<td class="flied-title src-env" width="25%" align="left" valign="middle">
															<input id="srcHostname" name="srcHostname" class="form-control subset-app-action" type="text" value=""  />
															<i class="fa fa-arrow-right subset-arrow-icon" aria-hidden="true"></i>
														</td>
														<td class="lable-title tar-env" width="14%" align="left" valign="middle">Server Name</td>
														<td class="flied-title tar-env" width="25%" align="left" valign="middle">
															<input id="tarHostname" name="tarHostname" class="form-control" type="text" value=""  />
														</td>
													</tr>
													<tr class="env-sel">
														<td class="lable-title src-env" width="14%" align="left" valign="middle">
														<spring:message code="label.database" /></td>
														<td class="flied-title src-env" width="25%" align="left" valign="middle">
															<input id="srcDb" name="srcinstanceName" class="form-control" type="text" value="${instanceName}"  />
														</td>
														<td class="lable-title tar-env" width="14%" align="left" valign="middle">
														<spring:message code="label.database" /></td>
														<td class="flied-title tar-env" width="25%" align="left" valign="middle">
															<input id="tarDb" name="tarinstanceName" class="form-control" type="text" value="${instanceName}"  />
														</td>
													</tr>
													<tr class="env-sel">
														<td class="lable-title src-env" width="14%" align="left" valign="middle">
														<spring:message code="label.schema" /></td>
														<td class="flied-title src-env" width="25%" align="left" valign="middle">
															<input id="srcSchema" name="srcSchema" class="form-control" type="text" value="${schemaName}"  />
														</td>
														<td class="lable-title tar-env" width="14%" align="left" valign="middle">
														<spring:message code="label.schema" /></td>
														<td class="flied-title tar-env" width="25%" align="left" valign="middle">
															<input id="targetSchema" name="tarSchema" class="form-control" type="text" value="${schemaName}"  />
														</td>
													</tr>
													
																									
													</tbody>
												</table>
												
											</div>
												<%-- <fieldset class="field-style">
										
													<div class="details-table details-extended">
														<div class="collpasewidget active policy-container-style" id="scenarioContainer">
															<div class="widget-inner policy-widget-style" id="autoWidgetDiv">
																<div class="policy-title"><span class ="toolbar-btn-style normal-cursor" style="float:left">Subset Scenarios</span></div>
																<div id="subsetContent" class="widget-content policy-content">		
																<ul class="nav nav-tabs subset-tabs" role="tablist" id="request-tab">
																		<li role="presentation" class="subset-tab active">
																			<a data-target="#default" aria-controls="default" role="tab" data-toggle="tab">
																				Default Scenario
																			</a>
																		</li>
																		<li role="presentation" class="subset-tab">
																			<a data-target="#custom" aria-controls="custom" role="tab" data-toggle="tab">
																				Custom Scenario
																			</a>
																		</li>		
																	</ul>
																	<div class="tab-content subset-content">
																		<div role="tabpanel" class="tab-pane active" id="default">
																			
																			<p class="subset-overview">Overview : Default Scenarios are all the scenarios</p>
																			<table id="scenarioTable" class="customDataTable scenario-table" cellspacing="0" width="100%">
																				<thead>
																				<tr>
																					<th>#</th>
																					<th>Scenario</th>
																					<th>Estimated No of Policies</th>
																					<th>Estimated Size</th>
																				</tr>
																				</thead>
																			</table>
																			<div>
																				<label class="total-label total-label1">Total No of Policies : 4210 rows</label>
																				<label class="total-label total-label2">Total Size : 70GB</label>
																			</div>
																		</div>
																		<div role="tabpanel" class="tab-pane" id="custom">
																		<p class="subset-overview">Overview : Custom Scenarios are are the scenarios where user can select from</p>
																				<table id="customScenarioTable" class="customDataTable scenario-table" cellspacing="0" width="100%">
																				<thead>
																				<tr>
																					<th></th>
																					<th>Scenario</th>
																					<th>Estimated No of Policies</th>
																					<th>Estimated Size</th>
																				</tr>
																				</thead>
																			</table>
																			<div>
																				<label class="total-label total-label1">Total No of Policies : 4210 rows</label>
																				<label class="total-label total-label2">Total Size : 70GB</label>
																			</div>
																		</div>
																	</div>											
																	
																</div>
															</div>
														</div>											
													</div>									
												</fieldset>
											</div>
										</div>
										<div>
											<div class="container-fluid extra-cont-style">
												<div class="row">
													<div class="col-xs-12 other-container-style">
														<span class="test-title extra-title-style">Additional Comments</span>
														<!--<div class="request-dash-bar"></div>-->
														<div><textarea class="dialog-text-area" id="commentAdd"></textarea></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="btn-container">
										<button type="button" id="btnSubmit" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"  title="Submit Request">
											<i class="fa fa-sign-in csaa-vectors"></i>Submit
										</button>
										<button type="button" id="btnReset" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"  title="Reset Request">
											<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>					
				</form:form>
				</div>
			</div>
		</div>
	</main>
	<footer class="footer home-footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="nav-links">
						<div class="copyright">&#169; 2016. All rights reserved</div>
						<ul>
							<li>
								<a href="aboutUs.html">About Us</a>
							</li>
							<li>
								<a href="javascript:void(0)">Contact Us</a>
							</li>
							<li>
								<a href="javascript:void(0)">Site Map</a>
							</li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</footer>
	
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/dataSubset.js"></script>
	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html> --%>

</div>
										<div class="widget-inner">
											<div class="widget-title">
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-12">
															<i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i>
															<span class="request-title">Create your Data Subset</span>
															<!--<div class="request-dash-bar"></div>-->
														</div>
													</div>
												</div>
											</div>

											<div id="datasubsetDetails" class="other-details widget-content">
											<table class="details-table widget-content">
												  <tbody>
													  <tr>
												  		<td class="lable-title" width="14%" align="left" valign="middle">
																	Select Package						
														</td>
														 <td class="flied-title" width="25%" align="left" valign="middle">
															  <div class="radio subset-radio">
																  <label>
																	  <input type="radio" id="defaultPkg" name="package" value="Default Package" checked="checked"/>
																	  <spring:message	code="label.defaultPackage" />
																  </label>
																  <label>
																	  <input type="radio" id="customPkg" name="package" value="Custom Package" />
																	  <spring:message	code="label.customPackage" />Custom Package
																  </label>							  
															  </div>
														</td>
													  </tr>
												 </tbody>
												</table>
												<fieldset class="field-style">
										
													<div class="details-table details-extended">
														<div class="collpasewidget active policy-container-style" id="scenarioContainer">
															<!--<div class="widget-inner policy-widget-style" id="autoWidgetDiv">-->

																
																<div id="subsetContent" class="widget-content policy-content">													<ul class="nav nav-tabs subset-tabs" role="tablist" id="request-tab">
																		<li role="presentation" class="subset-tab active">
																			<a data-target="#default" aria-controls="default" role="tab" data-toggle="tab">
																				Default Scenario
																			</a>
																		</li>
																		<li role="presentation" class="subset-tab">
																			<a data-target="#custom" aria-controls="custom" role="tab" data-toggle="tab">
																				Custom Scenario
																			</a>
																		</li>		
																	</ul>
																	<div class="tab-content subset-content">
																		<div role="tabpanel" class="tab-pane active" id="default">
																			
																			<!--<p class="subset-overview">Overview : Default Scenarios are all the scenarios</p>-->
																			<table id="scenarioTable" class="customDataTable scenario-table" cellspacing="0" width="100%">
																				<thead>
																				<tr>
																					<th>#</th>
																					<th>LOB</th>
																					<th>Data Subset Scenario</th>
																					<th>Estimated Number of Policies</th>
																					<th>Estimated Data Size</th>
																				</tr>
																				</thead>
																			</table>
																			<!--<div>
																				<label id='totalNoOfPolicies' class="total-label total-label1"></label>
																				<label id="totalSize" class="total-label total-label2"></label>
																			</div>-->
																		</div>
																		<div role="tabpanel" class="tab-pane" id="custom">
																		<!--<p class="subset-overview">Overview : Custom Scenarios are are the scenarios where user can select from</p>-->
																				<table id="customScenarioTable" class="customDataTable scenario-table" cellspacing="0" width="100%">
																				<thead>
																				<tr>
																					<th></th>
																					<th>LOB</th>
																					<th>Data Subset Scenario</th>
																					<th>Product Type</th>
																					<th>Policy Status</th>
																					<th>Risk State</th>
																					<th>Policy Term</th>
																					<th>Payment Plan</th>
																					<th>Total Due</th>
																					<th>Estimated No of Policies</th>
																					<th>Estimated Size</th>
																					<th>Preferred no of policies</th>
																				</tr>
																				</thead>
																			</table>
																			<!--<div>
																				<label id="totalCustomPolicies" class="total-label total-label1">Total No of Policies : 0 rows</label>
																				<label id="totalCustomSize" class="total-label total-label2">Total Size : 0 GB</label>
																			</div>-->
																		</div>
																	</div>											
																	
																</div>
															<!--</div>-->
														</div>											
													</div>									
												</fieldset>
											</div>
										</div>
										
										<div>
											<div class="container-fluid extra-cont-style">
												<div class="row">
													<div class="col-xs-12 other-container-style">
														<span class="test-title extra-title-style">Additional Comments</span>
														<!--<div class="request-dash-bar"></div>-->
														<div><textarea class="dialog-text-area" id="commentAdd"></textarea></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="btn-container">
										<button type="button" id="btnSave" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style" title="Save Request">
												<i class="fa fa-floppy-o csaa-vectors"></i>Save
											</button>
										<button type="button" id="btnSubmit" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"  title="Submit Request">
											<i class="fa fa-sign-in csaa-vectors"></i>Submit
										</button>
										<button type="button" id="btnReset" class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"  title="Reset Request">
											<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
										</button>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="activity">
							<div class="content-section">
								<div class="container-fluid">
									<div class="row">
										<div class="col-xs-12">
											<div id="historyDetails" class="table-parent reser-details history-details">
												<div class="table-parent">
													<div class="subset-activity-text">No Data in the table</div>
												</div>
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
		
	</main>
	<footer class="footer home-footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="nav-links">
						<div class="copyright">&#169; 2016. All rights reserved</div>
						<ul>
							<li>
								<a href="aboutUs.html">About Us</a>
							</li>
							<li>
								<a href="javascript:void(0)">Contact Us</a>
							</li>
							<li>
								<a href="javascript:void(0)">Site Map</a>
							</li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</footer>
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/dataSubset.js"></script>
	<!--<script type="text/javascript" src="js/subsetHistory.js"></script>-->
	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html>