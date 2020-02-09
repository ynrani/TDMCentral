<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<jsp:include page="headerNew.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Auto Policy Search</title>

<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style-footer.css">
<link rel="stylesheet" type="text/css" href="css/demo-footer.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
<link rel="stylesheet" type="text/css" href="css/datamining.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/stickyfooter.css" >

<link rel="stylesheet" type="text/css"
	href="css/bootstrap-multiselect.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/select.datatables.min.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style>
.multiselect-selected-text
{
	width:68px;
}
</style>
<script type="text/javascript">
	var policyCovge = '${tdmPolicyPropertyNewSearchDTO.policyCovge}';
	var propPolicyTableData = null;
	if('${tdmPolicyPropertyNewSearchDTO1}' != '')
	{
		propPolicyTableData = JSON.parse('${tdmPolicyPropertyNewSearchDTO1}');
	}

	$(document)
			.ready(
					function() {

						$("#propSearchTable")
								.dataTable(
										{
											paging : true,
											bInfo : true,
											"bFilter" : true,
											ordering : true,
											"dom" : '<"top"iflp<"clear">>rt',

											'order' : [ 1, 'asc' ],
											"aoColumns" : [
													{
														"targets" : 0,
														"sTitle" : "<input type='checkbox' name='prop_select_all'></input>",
														'orderable' : false,
														"width" : "15px",
														"defaultContent" : "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
													}, {
														"targets" : 1,
														sDefaultContent : ""
													}, {
														"targets" : 2,
														sDefaultContent : ""
													}, {
														"targets" : 3,
														sDefaultContent : ""
													}, {
														"targets" : 4,
														sDefaultContent : ""
													}, {
														"targets" : 5,
														sDefaultContent : ""
													} ]
										});
					});

	function submitAutoAssignSort(code, type) {

		if (code == 'PolicyNumber') {
			document.testDataForm.action = "./dataMinningPropertyPolicySearch?sortColCode=PolicyNumber&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'PolicyStage') {
			document.testDataForm.action = "./dataMinningPropertyPolicySearch?sortColCode=PolicyStage&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'PolicyState') {
			document.testDataForm.action = "./dataMinningPropertyPolicySearch?sortColCode=PolicyState&sortType="
					+ type;
			document.testDataForm.submit();
		}
		if (code == 'PolicyEffectDt') {
			document.testDataForm.action = "./dataMinningPropertyPolicySearch?sortColCode=PolicyEffectDt&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'TotalDue') {
			document.testDataForm.action = "./dataMinningPropertyPolicySearch?sortColCode=TotalDue&sortType="
					+ type;
			document.testDataForm.submit();
		}

	}
</script>
</head>
<%
	String sorttype = request.getAttribute("sortType") != null ? (request
			.getAttribute("sortType")).toString() : "";
	String sortcode = request.getAttribute("sortColCode") != null ? (request
			.getAttribute("sortColCode")).toString() : "";
%>
<body>
	<div class="loaderDiv" style='display: none;'></div>
	<main class="main-style">
	<div class="container">
		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="page-heading">
						<ol class="breadcrumb">
							<li>Home</li>
							<li>Data Services</li>
							<li class="active">Data Mining</li>
						</ol>
					</div>

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist" id="request-tab">
						<li role="presentation" class="auto-search"><a
							id="autoSearch" href="./dataMinningAutoPolicySearch"><i
								class="fa fa-car csaa-vectors"></i>Auto Policy </a></li>
						<li role="presentation" class="policy-search active"><a
							href="#"> <i
								class="fa fa-home fa-lg csaa-vectors"></i>Property Policy
						</a></li>
					</ul>
				</div>

				<!-- Tab panes -->
				<div class="tab-content" id="tabData">
					<div role="tabpanel" class="tab-pane active" id="manual">
						<c:if test="${error ne null}">
							<table class="my-error-class">
								<tbody>
									<tr>
										<td class="lable-title" align="left" valign="middle">${error}</td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<div id="myErrorCls"></div>
						<form:form id="testDataForm" name="testDataForm"
							action="${pageContext.request.contextPath}/dataMinningPropertyPolicySearch"
							modelAttribute="tdmPolicyPropertyNewSearchDTO">
							<div class="content-section datamine-content-section">
								<div class="collpasewidget active" id="generalDetails">
									<div class="widget-inner">
										<div id="generalDetailsContent" class="widget-content">
											<div class="table-parent">
												<table class="details-table">
													<tbody>
														<tr>
															<td class="lable-title blue-label" width="25%"
																align="left" valign="middle"><span class="tool"> Environment </span>
																<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Environment from where data is needed"></i></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"><form:select path="envType"
																	id="envType" class="down-control" required="true">
																	<c:if test="${not empty environment}">
																		<c:forEach var="envFieldListDTO"
																			items="${environment}">
																			<form:option value="${envFieldListDTO.valueCode}">${envFieldListDTO.listValue}</form:option>
																		</c:forEach>
																	</c:if>
																</form:select></td>
															<td class="lable-title" width="25%" align="left"
																valign="middle"></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> <span
															class="request-title">Basic Search</span>
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
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Product Type </span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="AAA_CSA-CA Select,AAA_SS-Signature Series"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select
																			path="addproductType" id="addproductType"
																			class="down-control">
																			<c:if test="${not empty producttype}">
																				<c:forEach var="prdFieldListDTO"
																					items="${producttype}">
																					<form:option value="${prdFieldListDTO.valueCode}">${prdFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy
																			Status</span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Current status of the policy"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="policyStage"
																			id="policyStage" class="down-control">
																			<c:if test="${not empty policystatus}">
																				<c:forEach var="policyFieldListDTO"
																					items="${policystatus}">
																					<form:option
																						value="${policyFieldListDTO.valueCode}">${policyFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Risk
																			State</span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="State where policy is written "></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="policyState"
																			id="policyState1" class="down-control">
																			<c:if test="${not empty riskstate}">
																				<c:forEach var="riskFieldListDTO"
																					items="${riskstate}">
																					<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy
																			Type</span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Policy Type"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="policyType"
																			id="policyType" class="down-control">

																			<c:if test="${not empty policytype}">
																				<c:forEach var="ptFieldListDTO"
																					items="${policytype}">
																					<form:option value="${ptFieldListDTO.valueCode}">${ptFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy
																			Coverage</span>
																			<i class="fa fa-question-circle question-tooltip-style" id="policyCovTooltip"  data-html="true" data-toggle="tooltip" ></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select multiple="multiple"
																			path="policyCovge" id="proPolLevelCoverage"
																			class="down-control-list-checkbox">
																			<c:if test="${not empty policylevel}">
																				<c:forEach var="policyLeveltermFieldListDTO"
																					items="${policylevel}">
																					<c:if
																						test="${policyLeveltermFieldListDTO.valueCode eq 'Any'}">
																						<form:option selected="selected"
																							value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
																					</c:if>
																					<c:if
																						test="${policyLeveltermFieldListDTO.valueCode ne 'Any'}">
																						<form:option
																							value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
																					</c:if>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																		<td class="lable-title" width="25%" align="left"
																valign="middle"></td>
															<td class="flied-title" width="25%" align="left"
																valign="middle"></td>
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
																		<i class="fa fa-minus-square-o plus-minus-style"
																			title="Expand/Collpase Details"></i> <span
																			class="request-title">Billing</span>
																	</div>
																</div>
															</div>
														</div>
														<table class="details-table widget-content">
															<tbody>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Payment
																			Plan</span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Payment plan is the lumpsum or equally distributed payment towards insurance premium amount"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select
																			path="addPaymentPlan" id="addPaymentPlan"
																			class="down-control">
																			<c:if test="${not empty paymentplan}">
																				<c:forEach var="paymentPlantermFieldListDTO"
																					items="${paymentplan}">
																					<form:option
																						value="${paymentPlantermFieldListDTO.valueCode}">${paymentPlantermFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"></td>
																	</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool"> Total
																			Due </span>
																			<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Total amount owed on the policy to date"></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="payOffYes"
																					path="totalDueFlag" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="payOffNo"
																					path="totalDueFlag" value="No" />No
																			</label> <label> <form:radiobutton id="payOffAny"
																					path="totalDueFlag" value="Any" />Any
																			</label>
																		</div>
																	</td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"></td>
																	</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool"><spring:message
																			code="label.policy.poliWithAutpayElg" /></span>
																			<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Policy which is eligible to enroll in recurring payment 
																			"></i>
																			</td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="autoPayYes"
																					path="propPay" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="autoPayNo"
																					path="propPay" value="No" />No
																			</label> <label> <form:radiobutton id="autoPayAny"
																					path="propPay" value="Any" />Any
																			</label>
																		</div>
																	</td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"></td>
																	</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">
																			Minimum Due </span>
																			<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="The amount required to bring the account totally current"></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="currBalYes"
																					path="minDueFlag" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="currBalNo"
																					path="minDueFlag" value="No" />No
																			</label> <label> <form:radiobutton id="currBalAny"
																					path="minDueFlag" value="Any" />Any
																			</label>
																		</div>
																	</td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"></td>
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
									<input type="submit" id="btnSearchResultProp"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style"
										title="Search" value="Search" />
										<button type="button" id="btnRsetResultAuto"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
										title="Reset">
										<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
									</button>
								</div>
								<div id="searchresultsProp" class="other-details search-details">
									<div class="table-parent other-table-parent collpasewidget">
										<div class="widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> <span
															class="request-title">Search Results</span>
													</div>
												</div>
											</div>
										</div>
										<c:if
											test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList eq null}">
											<c:out
												value="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}"></c:out>
											<c:if test="${reserveFlag ne null}">
												<table class="my-msg-class">
													<tbody>
														<tr>
															<td class="lable-title reserve-msg-style" align="left" valign="middle">
																${reserveFlag}</td>
														</tr>
													</tbody>
												</table>
											</c:if>
										</c:if>
										<div class="table-parent search-main widget-content">
											
											<%
											if ((String) request.getAttribute("NO_AUTO_RECORDS") != null) {
										%>
										<%=(String) request.getAttribute("NO_AUTO_RECORDS")%>
										<%
											}
										%>
											<c:if test="${searchSwitch == 'ON' }">
											<table id="propSearchTable" class="customDataTable"
												cellspacing="0" width="100%">
												<thead>
														<tr>
															<th></th>
															<th>Policy <br />Number
															</th>

															<th>Policy <br />Status
															</th>

															<th>Risk <br />State
															</th>

															<th>Effective <br />Date
															</th>

															<th>Total<br />Due
															</th>
														</tr>
													</thead>
											</table>
											<div id="reserveNotSelected" style='display: none;'>
												<font color="red">Please select at least one record to
													reserve.</font>
											</div>
											<div id="exportNotSelected" style='display: none;'>
												<font color="red">Please select at least one record to
													export.</font>
											</div>
											<c:if
												test="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList ne null}">
												<%
													int currentPage = (Integer) request
																	.getAttribute("currentPage");
															int count1 = currentPage - 1;
															count1 = count1 * 10;
												%>

												<c:if test="${reserveFlag ne null}">
													<table class="my-msg-class">
														<tbody>
															<tr>
																<td class="lable-title" align="left" valign="middle">
																	${reserveFlag}</td>

															</tr>
														</tbody>
													</table>
												</c:if>
												
											</c:if>
											<div class="result-btn-container">

												<table class="widget-content reserve-table">
														<tbody>
															<tr>
																<td class="lable-title" width="15%" align="left"
																	valign="middle"><p class="test-case-id">Test Case ID </p> 
																<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Test Case ID"></i></td>
																<td width="75%" align="left"
																	valign="middle"><input type="text"
																	name="testCaseId"  maxlength="20"/></td>
																<td class="lable-title" width="5%" align="right"
																	valign="middle"><input type="submit" name="reserve"
													class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve"
													id="reserve" value="Reserve"></td>
																<td width="5%" align="right"
																	valign="middle"><input type="submit" name="export" id="export"
													value="Export"
													class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export com-submit-btn"></td>
															</tr>
														</tbody>
													</table>
												<input type="hidden" id="policyNumners" name="policyNumners"
													value="" />
											</div>
										
										</c:if>
										</div>
										<c:if test="${revervationSwitch == 'ON' }">
											<div class="widget-title res-widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> <span
															class="request-title">View Reservation</span>
													</div>
												</div>
											</div>
										</div>
										<div id="reservTable"
											class="table-parent reser-details widget-content">
											<div class="table-parent">
												<c:choose>
													<c:when
														test="${tdmPolicyPropertyNewSearchDTO.reservedTestDataList eq null}">
														<tr class="even">
															<td>No reserved records available for the selected criteria</td>
															<c:set var="count" value="0" scope="page" />
														</tr>
													</c:when>
													<c:otherwise>
														<p>Record found. Reservation details for the current
															policy search criteria</p>
														<table id="autoReservationTable" class="customDataTable"
															cellspacing="0" width="100%">
															<thead>
																<tr>
																	<th>#</th>
																	<th>User ID</th>
																	<th>Test Case ID</th>	
																	<th>Number of Reserved Records</th>
																	<th>Email User</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach
																	items="${tdmPolicyPropertyNewSearchDTO.reservedTestDataList}"
																	var="reservedTestDataList" varStatus="status">
																	<c:if test="${countRsvn%2 == 0}">
																		<tr class="even">
																	</c:if>
																	<c:if test="${countRsvn%2 !=0}">
																		<tr class="odd">
																	</c:if>
																	<tr>
																	<td>${reservedTestDataList.sno}</td>
																	<td>${reservedTestDataList.userId}</td>
																	<td>${reservedTestDataList.testCaseId}</td>
																	<td>${reservedTestDataList.noOfRecordsResvByUser}</td>
																	<td class="buttonsAll8"><input type="button"
																		value="<spring:message 	code="label.msg" />"
																		id="jqxButton"
																		onclick="popup('./popupEmailUser?user=${autoEmailDTOs.userId}&result=${result}&reserveId=${autoEmailDTOs.testCaseId}','Un-Reserve Request','popup','popupOverlay','550');" />
																	</td>
																	</tr>
																	<c:set var="countRsvn" value="${countRsvn + 1}"
																		scope="page" />
																</c:forEach>
															</tbody>
														</table>
													</c:otherwise>
												</c:choose>
												
											</div>
										</div>
										</c:if>
									</div>
								</div>
							</div>
						</form:form>
					</div>
					<div role="tabpanel" class="tab-pane" id="property"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="submitFlagProp" style='display: none;'>
		<c:if test="${flag == 1}">
			<p>1</p>
		</c:if>
	</div>
	</main>
	<jsp:include page="footerNew.jsp"></jsp:include>
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/search-common.js"></script>
	<script type="text/javascript" src="js/proppolicy.js"></script>
	<script type="text/javascript" src="js/SearchNavigation.js"></script>
	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>

</html>