
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="headerNew.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TDM Central | Automatic Request</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/custom.ui.auto.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/select.datatables.min.css">
<link rel="stylesheet" type="text/css" href="css/ColReorder.css">
<link rel="stylesheet" type="text/css"
	href="lib/datetimepicker/bootstrap-datetimepicker.min.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css">


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/datetimepicker/moment.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

<script type="text/javascript"
	src="lib/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<script type="text/javascript" src="js/ColReorderWithResize.js"></script>

</head>
<body>
	<div class="loaderDiv" style='display: none;'></div>
	<script>
  var userRole ='<%=(String) request.getSession().getAttribute("ROLE")%>
		';
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
		var paymentPlan = '';
		var policyTypeArr = '';
		var riskStateArr = '';
		var paymentPlan1 = '';
		if ('${autoPolicyLst}' != '') {
			autoPolicyTableData = JSON.parse('${autoPolicyLst}');
		}
		if ('${propPolicyLst}' != '') {
			propPolicyTableData = JSON.parse('${propPolicyLst}');
		}
		if ('$(AutoCountryList)' != '') {
			AutoCountryList = JSON.parse('${AutoCountryList}');
		}
		if ('$(AutoPaymentPlan)' != '') {
			paymentPlan = JSON.parse('${AutoPaymentPlan}');
		}
		if ('$(PropertyPolicyType)' != '') {
			policyTypeArr = JSON.parse('${PropertyPolicyType}');
		}
		if ('$(PropertyRiskState)' != '') {
			riskStateArr = JSON.parse('${PropertyRiskState}');
		}
		if ('$(PropertyPaymentPlan)' != '') {
			paymentPlan1 = JSON.parse('${PropertyPaymentPlan}');
		}
	</script>
	<main class="auto-main-style">
	<div class="container auto-container">
		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="page-heading">
						<!--<i class="fa fa-cogs"></i>
                            <h1>PAS Data Creation Request / <h1 id="lblRequest">Request Id - 12345</h1></h1>-->
						<ol class="breadcrumb">
							<li>Home</li>
							<li>Data Services</li>
							<li class="active">Data Generation</li>
						</ol>
					</div>
					<div class="auto-basic-desc-style">
						<div style='display: inline;'>
							<i class="fa fa-wrench ser-pic"></i>
						</div>
						<div style='display: inline-block; padding: 10px'>
							Through data generation service consumers can raise request to
							create test data in the required environment.</br> The below scenarios
							represent commonly requested policy types. Don't find the
							scenario matching your test data requirement? </br>Please click on
							"Customized-Data generation" to raise your test data generation
							request.
						</div>
					</div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist" id="request-tab">
						<li role="presentation" class="active"><a
							data-target="#manual" aria-controls="manual" role="tab"
							data-toggle="tab"> <i class="fa fa-database csaa-vectors"></i>Data
								Request
						</a></li>
						<!-- 	<li role="presentation">
								<a data-target="#history" aria-controls="history" role="tab" data-toggle="tab">
									<i class="fa fa-history csaa-vectors"></i>Request Activity Log
								</a>
							</li> -->
						<li role="presentation"><a
							href="./dgActivityLog?requestId=${requestDataDTO.requestId}">


								<i class="fa fa-history fa-lg csaa-vectors"></i>Request Activity
								Log
						</a></li>
					</ul>


				</div>
				<form:form id="requestDataForm" name="requestDataForm"
					action="${pageContext.request.contextPath}/dgAutomation"
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
														<i
															class="fa fa-minus-square-o plus-minus-style fa-plus-square-o"
															title="Expand/Collpase Details"></i> <span
															class="request-title">General Details</span>
														<!--<i id="btnfetchDetail" class="fa fa-get-pocket fetch-details" title="Fill Details"></i>-->
													</div>
												</div>
											</div>
										</div>

										<div id="generalDetailsContent" class="widget-content">
											<table class="details-table">
												<tbody>
													<!-- 	<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Service Type</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="serviceType" name="serviceType" class="form-control" type="text" value="DataCreation"/>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Request Number</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="txtRequestID" name="requestedBy" class="form-control" type="text" value=""/>
													</td>
													
												</tr> -->
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


													<!-- <tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Requested By</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="txtRequestedBy" name="requestedBy" class="form-control" type="text" value=""/>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Created On</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="txtCreatedOn" name="createdOn" class="form-control" type="text" value=""/>
													</td>
													

												</tr> -->
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



													<!-- 	<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Application owner</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="txtAppOwner" name="appOwner" class="form-control" type="text" value=""/>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Assigned Group</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<select id="ddlAssignedGroup" name="assignedGroup" class="down-control">
														</select>
													</td>
													
												</tr> -->

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
													<!-- <tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Approver</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<input id="txtApprover" name="approver" class="form-control" type="text" value="" />
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Assigned To</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<select id="ddlAssignedTo" name="assignedTo" class="down-control">
														</select>
													</td>
													
												</tr> -->

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

																		<form:option value="${requestDataDTO.supportUserId}">${requestDataDTO.supportUserName}</form:option>

																	</c:forEach>
																</c:if>

															</form:select></td>

													</tr>




													<!-- 	<tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Status</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<select id="ddlStatus" name="status" class="down-control">
														</select>
													</td>
													<td class="lable-title" width="14%" align="left" valign="middle">Fulfillment Type</td>
													<td class="flied-title" width="25%" align="left" valign="middle">
														<select id="ddlFullFilled" name="ddlFullFilled" class="down-control">
														</select>
													</td>
												</tr> -->

													<tr>
														<%-- <td class="lable-title" width="14%" align="left"
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
																<form:option value="0">None</form:option>
																<c:if test="${not empty requestDataDTO.supportUserList}">
																	<c:forEach var="requestDataDTO"
																		items="${requestDataDTO.supportUserList}">
																		
																		   <form:option  value="${requestDataDTO.supportUserId}">${requestDataDTO.supportUserName}</form:option>				
																		 
																	</c:forEach>
																</c:if>

															</form:select></td> --%>

													</tr>


													<!-- <tr>
													<td class="lable-title" width="14%" align="left" valign="middle">Status Notes</td>
													<td class="flied-title" colspan="4" align="left" valign="middle">
														<input id="txtStatusChangeDescription" name="txtStatusChangeDescription" class="form-control full-length-input" type="text" value="" />
													</td>
												</tr> -->


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
												<table class="details-table">
													<tbody>
														<!-- 	<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">Short Description <span class="login-error">*</span></td>
														<td class="flied-title" colspan="4" align="left" valign="middle">
															<input id="txtSubject" name="subject" class="form-control full-length-input" type="text" value=""  />
														</td>
													</tr> -->
														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.subject.short" /><span class="login-error">*</span></td>
															<td class="flied-title" colspan=4 align="left"
																valign="middle"><form:input path="subject"
																	id="subject" required="true" maxlength="100"
																	class="form-control full-length-input subject-class-inc"
																	type="text" value="${requestDataDTO.subject}" /></td>
														</tr>


														<!-- <tr>
														<td class="lable-title" width="14%" align="left" valign="middle">Consumer Group <span class="login-error">*</span></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="ddlConsumerGroup" name="consumerGroup" class="down-control">
															</select>
														</td>
														<td class="lable-title" width="14%" align="left" valign="middle">Priority  <span class="login-error">*</span></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="ddlPriority" name="priority" class="down-control">
															</select>
														</td>
													</tr> -->
														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.consumerGroup" /><span class="login-error">*</span></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="consumerGroup"
																	id="consumerGroup" class="down-control required">
																	<form:option value="">--Select--</form:option>
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



														<!-- 	<tr>
														<td class="lable-title" width="14%" align="left" valign="middle">Data Source</td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="ddlDataSource" name="dataSource" class="down-control">
															</select>
														</td>
														<td class="lable-title" width="14%" align="left" valign="middle">Environment  <span class="login-error">*</span></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<select id="ddlEnvironment" name="environment" class="down-control">
																<option selected="selected" value="0">---Select---</option>
															</select>
														</td>
													</tr> -->

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
																	id="environment" class="down-control required"
																	required="true">
																	<form:option selected="true" value="">--Select--</form:option>
																	<c:if
																		test="${not empty requestDataDTO.environmentList}">
																		<c:forEach var="requestDataDTO"
																			items="${requestDataDTO.environmentList}">
																			<c:if
																				test="${requestDataDTO.environmentName eq 'PAS-EP2' }">
																				<form:option value="${requestDataDTO.environmentId}">${requestDataDTO.environmentName}</form:option>
																			</c:if>

																			<form:option value="${requestDataDTO.environmentId}">${requestDataDTO.environmentName}</form:option>

																		</c:forEach>
																	</c:if>
																</form:select></td>
														</tr>


														<!-- <tr>
														<td class="lable-title" width="14%" align="left" valign="middle">Expected Date  <span class="login-error">*</span></td>
														<td class="flied-title" width="25%" align="left" valign="middle">
															<div class='input-group date' id='datetimepicker1'>
															<input id="expectedDate" name="expectedDate" readonly='true' class="form-control date-control-style dateHigherThanToday" type="text" value="" required="true"/>
															<span class='input-group-addon data-clock'>
													                        <span class='glyphicon glyphicon-calendar'>
													                        	<img class='ui-datepicker-trigger' src='images/calendar.png' alt='' title=''>
													                        </span>
													                    </span>
																	</div>
														</td>
													</tr> -->


														<tr>
															<td class="lable-title" width="14%" align="left"
																valign="middle"><spring:message
																	code="label.expectedDate" /> <span class="login-error">*</span>
															</td>
															<td class="flied-title" width="25%" align="left"
																valign="middle">
																<div class='input-group date' id='datetimepicker1'>
																	<form:input path="expectedDate" id="expectedDate"
																		readonly='true'
																		class="form-control date-control-style expected-date-width dateHigherThanToday"
																		required="true" />

																	<!-- <input type='text' class="form-control" />-->
																	<span class='input-group-addon data-clock'> <span
																		class='glyphicon glyphicon-calendar'> <img
																			class='ui-datepicker-trigger'
																			src='images/calendar.png' alt='' title=''>
																	</span>
																	</span>
																</div>

															</td>

														</tr>


													</tbody>
												</table>
											</div>
										</div>
									</div>
									<%-- </form:form>  --%>
									<fieldset class="field-style">
										<div class="widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<span class="test-title">Create your Test Data</span>
														<!--<div class="request-dash-bar"></div>-->
													</div>
												</div>
											</div>
										</div>
										<div class="details-table details-extended">
											<div class="collpasewidget active policy-container-style"
												id="autoPolicyContainer">
												<div class="widget-inner policy-widget-style"
													id="autoWidgetDiv">
													<div class="policy-title">
														<span class="toolbar-btn-style normal-cursor"
															style="float: left"> <i
															class="fa fa-car car-size-style icon-size-auto"> </i>
															Auto Policy
														</span>
													</div>
													<div id="autoPolicyContent"
														class="widget-content policy-content">
														<div id="autoScenario" class="scenario-style hide-class">
															<c:forEach var="autoScenarioitem"
																items="${autoScenarioLst}" varStatus='loop'>

																<c:if test='${loop.index%3 == 0}'>
																	<c:if test='${loop.index != 0 }'>
														</div>
														</c:if>
														<div class="row scenario-row">
															</c:if>
															<div
																class="col-md-4 scenario-chk scenario-column scenario-col-border">
																<label class="scenario-chk-label-style"> <c:if
																		test='${autoScenarioitem.isActive == true}'>
																		<input type="checkbox" id="autoScenarioType"
																			name="autoScenarioType"
																			class="chk-control large-ticks"
																			value="${autoScenarioitem.scenarioId}" />
																	</c:if> <span>${autoScenarioitem.description}<i
																		class="fa fa-question-circle question-tooltip-style question-tooltip"
																		data-toggle="tooltip"
																		title="${autoScenarioitem.description}"></i></span>
																</label>
															</div>

															</c:forEach>
														</div>
													</div>
													<hr class="hr-style">
													<table id="autoPolicyTable" class="customDataTable"
														cellspacing="0" width="100%">
														<thead>
															<tr>
																<th>Selected Auto Scenario</th>
																<th>Effective Date</th>
																<th>Risk State</th>
																<th>Product</th>
																<th>Term</th>
																<th>Number of Drivers</th>
																<th>Number of Vehicles</th>
																<th>Payment Plan</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<div class="collpasewidget active policy-container-style"
											id="propertyPolicyContainer">
											<div class="widget-inner policy-widget-style"
												id="propertyWidgetDiv">
												<!--<div class="policy-title"><div class="prop-title-style"><i class="fa fa-minus-square-o plus-minus-style" title="Expand/Collpase Details"></i> Property Policy</div></div>-->
												<div class="policy-title">
													<span class="toolbar-btn-style normal-cursor"
														style="float: left"><i
														class="fa fa-home icon-size-auto"></i> Property Policy</span>
												</div>
												<div id="propertyPolicyContent" class="widget-content">
													<!--<div class="scenario-btn-container">
															<button type="button" id="propertyAddButton" class="autotoolbar-btn-style" title="Select Scenario">Select Scenario <i class="fa fa-angle-down angle-btn-style"></i></button>
														</div>-->
													<div id="propertyScenario"
														class="scenario-style hide-class">

														<c:forEach var="propertyScenarioitem"
															items="${propScenarioLst}" varStatus='loop'>

															<c:if test='${loop.index%3 == 0}'>
																<c:if test='${loop.index != 0 }'>
													</div>
													</c:if>
													<div class="row scenario-row">
														</c:if>
														<div
															class="col-md-4 scenario-chk scenario-column scenario-col-border">
															<label class="scenario-chk-label-style"> <c:if
																	test='${propertyScenarioitem.isActive == true}'>
																	<input type="checkbox" name="propertyScenarioType"
																		class="chk-control large-ticks"
																		value="${propertyScenarioitem.scenarioId}" />
																</c:if> <span>${propertyScenarioitem.description}<i
																	class="fa fa-question-circle question-tooltip-style question-tooltip"
																	data-toggle="tooltip"
																	title="${propertyScenarioitem.description}"></i></span>
															</label>
														</div>

														</c:forEach>
													</div>
												</div>
												<hr class="hr-style">
												<table id="propertyPolicyTable" class="customDataTable"
													cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>Selected Property Scenario</th>
															<th>Effective Date</th>
															<th>Product</th>
															<th>Policy Type</th>
															<th>Risk State</th>
															<th>Payment Plan</th>
															<th>Mortgagee</th>
															<th>Additonal Interest</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
								</div>
							</div>
							<div>
								<div class="container-fluid extra-cont-style">
									<div class="row">
										<div class="col-xs-12 other-container-style">
											<span class="test-title extra-title-style">Additional
												Comments</span>
											<!--<div class="request-dash-bar"></div>-->
											<div>
												<textarea class="dialog-text-area"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="newTestDataRequirement">
								<div class="container-fluid extra-cont-style">
									<div class="row">
										<div class="col-xs-12 new-scenario-question-style">
											<span class="test-title extra-title-style">Customized-Data
												generation</span>
											<!--<div class="request-dash-bar"></div>-->
											<p class="new-sce-text">Don't find the scenario matching
												your test data requirement? Please click on "Customized-Data
												generation" to raise your test data generation request.</p>
											<button id="btnNewSchenario"
												class="new-sce-btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover new-scenario-btn-text"
												title="Generate new scenario manually">Customized-Data
												generation</button>
										</div>
									</div>
								</div>
							</div>

							<div id="tableDataRequiredMsg" class="login-error table-error"
								style="display: none;">Please fill in the Auto/Property
								Policy Table</div>
							</fieldset>
							<div class="btn-container">
								<button type="submit" id="btnSave" value="save" name="save"
									class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"
									title="Save Request">
									<i class="fa fa-floppy-o csaa-vectors"></i>Save
								</button>
								<button type="button" id="btnSubmit"
									class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"
									title="Submit Request">
									<i class="fa fa-sign-in csaa-vectors"></i>Submit
								</button>
								<input type="hidden" value="" id="autoPolicye" name="auto" /> <input
									type="hidden" value="" id="propPolicye" name="prop" /> <input
									type="hidden" value="" id="genDet" name="genDet" />

								<button type="button" id="btnReset"
									class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
									title="Reset Request">
									<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
								</button>
							</div>
							<c:if test="${viewTemplateSwitch != 'OFF' }">

								<%
									String role = (String) request.getSession().getAttribute(
													"ROLE");
								%>


								<%
									if (role != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
								%>
								<c:if test="${requestDataDTO.status ==  'In-Progress'}">
									<div>
										<div class="container-fluid extra-cont-style">
											<div class="row">
												<div class="col-xs-12 new-scenario-question-style">
													<span class="test-title extra-title-style">Templates</span>
													<!--<div class="request-dash-bar"></div>-->
													<p class="new-sce-text">
														<input type="button" value="Generate Templates"
															id="btnGenerateTemplate"
															class="generate-template-btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover new-scenario-btn-text"
															title="Generate Templates" />
													<ul class="template-style">
														<li><span>Run Manager</span> <!--  --> <a
															href="./downloadRunManagerExcel?runManagerExcelFile=${requestDataDTO.requestId}"
															onclick="downloadRunManagerExcel()"><i
																class='fa fa-download upload-download-style'
																title="Download"></i></a> <a href="javascript:void(0)"
															class="input-link"><i
																class='fa fa-upload upload-download-style'
																title="Upload"></i></a>
															<div class="input-group hide-class">
																<input type="text" id="txtRunManager"
																	class="form-control file-upload-style" readonly>
																<span class="input-group-btn" style='display: inherit'>
																	<span class="btn btn-primary btn-file">
																		Browse&hellip; <input type="file" id="runManagerFile">

																</span> <input type="button" id="btnRunManager" value="Upload"
																	class="btn btn-primary hide-class" />
																</span>
															</div>
															<div id="runManagerCompleteMsg"
																class="table-error complete-msg-info"
																style="display: none;">File uploaded successfully</div>
														</li>
														<li><span>Auto Policy Test Data</span> <a
															href="./downloadAutoExcel?runManagerExcelFile=${requestDataDTO.requestId}"
															onclick="downloadRunManagerExcel()"><i
																class='fa fa-download upload-download-style'
																title="Download"></i></a> <a href="javascript:void(0)"
															class="input-link"><i
																class='fa fa-upload upload-download-style'
																title="Upload"></i></a>
															<div class="input-group hide-class">
																<input type="text" id="txtAutoPolicytestData"
																	class="form-control file-upload-style" readonly>
																<span class="input-group-btn" style='display: inherit'>
																	<span class="btn btn-primary btn-file">
																		Browse&hellip; <input type="file"
																		id="autoPolicytestDataFile">

																</span> <input type="button" id="btnAutoPolicytestData"
																	value="Upload" class="btn btn-primary" />
																</span>

															</div>
															<div id="autotestCompleteMsg"
																class="table-error complete-msg-info"
																style="display: none;">File uploaded successfully</div>
														</li>
														<li><span>Property Policy Test Data</span> <a
															href="./downloadPropertyExcel?runManagerExcelFile=${requestDataDTO.requestId}"
															onclick="downloadRunManagerExcel()"><i
																class='fa fa-download upload-download-style'
																title="Download"></i></a> <a href="javascript:void(0)"
															class="input-link"><i
																class='fa fa-upload upload-download-style'
																title="Upload"></i></a>
															<div class="input-group hide-class">
																<input type="text" id="txtPropPolicytestData"
																	class="form-control file-upload-style" readonly>
																<span class="input-group-btn" style='display: inherit'>
																	<span class="btn btn-primary btn-file">
																		Browse&hellip; <input type="file"
																		id="propPolicytestDataFile">

																</span> <input type="button" id="btnPropPolicytestData"
																	value="Upload" class="btn btn-primary" />
																</span>

															</div>
															<div id="proptestCompleteMsg"
																class="table-error complete-msg-info"
																style="display: none;">File uploaded successfully</div>
														</li>
													</ul>
													</p>
												</div>
											</div>
										</div>
									</div>
								</c:if>
								<%
									}
								%>
							</c:if>


						</div>
					</div>
					<input type="hidden" id="runManagerExcel" name="runManagerExcel"
						value="" />
			</div>
		</div>
		<!-- 		<div role="tabpanel" class="tab-pane" id="history">
						<div class="content-section">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12">
										<div id="historyDetails" class="table-parent reser-details history-details">
											<div class="table-parent">
												<table id="historyTable" class="customDataTable" cellspacing="0" width="100%">
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
					</div> -->
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
							<li><a href="aboutUs.html">About Us</a></li>
							<li><a href="javascript:void(0)">Contact Us</a></li>
							<li><a href="javascript:void(0)">Site Map</a></li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- 	<script type="text/javascript" src="js/request.js"></script> -->
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/autoRequest.js"></script>
	<script type="text/javascript" src="js/history.js"></script>
	<!--<script type="text/javascript" src="js/search-common.js"></script>-->
	<script type="text/javascript" src="js/stickyfooter.js"></script>

	<script>
		if (userRole != null && userRole == 'ROLE_ADMIN') {
			$('#btnNewRequest').hide();
		}
		var count = 0;
		var serviceType = 'DG-Auto';
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
				+ timeStamp1 + ':' + timeStamp2;
		if (status == "") {
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
										&& serviceIdentifier != 'myRequest'
										&& serviceIdentifier != 'activityLog') {
									BootstrapDialog
											.show({
												id : "saveMsgDialog",
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
													var markup = '<div>Your Data is saved :Your request no: '
															+ reqId + '</div>';
													dialog.setMessage(markup);
													dialog
															.getModalHeader()
															.addClass(
																	'email-title');
													$("#subject").prop(
															"readonly", true);
													$("#expectedDate").prop(
															"readonly", true);

												},
												buttons : [ {
													cssClass : "btn btn-primary",
													label : "Close",
													action : function(dialog) {
														dialog.close();
													}
												} ]
											});
								}
							}

							function openMessageDialog() {
								BootstrapDialog
										.show({
											title : "Message from ATS Data Portal",
											resize : "auto",
											position : 'center',
											height : 170,
											width : 600,
											//bgiframe: true,
											//show: 'fade',
											//hide:'fade',
											//dialogClass: "noclose",
											onshow : function(dialog) {
												var markup = '<div><i class="fa fa-check question-tooltip-style"></i>The submit was successful.<br/>Your request no. '
														+ $('#requestId').val()
														+ ' has been SUBMIITED.<br />This request will be assigned to ATS Data Support team for fulfillment.</div>';
												dialog.setMessage(markup);
												dialog
														.getModalHeader()
														.addClass('email-title');
											},
											buttons : [ {
												cssClass : "btn btn-primary",
												label : "Close",
												action : function(dialog) {
													dialog.close();
												}
											} ]
										});
							}

							if (userRole != null && userRole == 'ROLE_USER'
									&& status == 'New'
									&& serviceIdentifier != 'myRequest'
									&& serviceIdentifier != 'activityLog') {

								$("#newTestDataRequirement").addClass(
										"hide-class");
								openMessageDialog();

							}

							if (userRole != null && userRole == 'ROLE_ADMIN') {
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
</body>
</html>