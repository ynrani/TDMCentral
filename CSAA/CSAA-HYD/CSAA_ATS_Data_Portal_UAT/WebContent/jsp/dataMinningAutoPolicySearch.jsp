<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Property Policy Search</title>
<jsp:include page="headerNew.jsp"></jsp:include>
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
<script type="text/javascript">
var riskState='${tdmAtsSearchDTO.riskState}';
var riskStateVal='${tdmAtsSearchDTO.riskStateValue}';
var riskStateKey='${tdmAtsSearchDTO.riskStateKey}';
	var policycoverge = '${tdmAtsSearchDTO.policyCovge}';
	var addRiskCovge = '${tdmAtsSearchDTO.addRiskCovge}';
	var autoPolicyTableData = null;
	if('${tdmPolicyAutoSearchDTO1}' != '')
	{
		autoPolicyTableData = JSON.parse('${tdmPolicyAutoSearchDTO1}');
	}

	$(document)
			.ready(
					function() {

						$("#autoSearchTable")
								.dataTable(
										{
											paging : true,
											bInfo : true,
											"bFilter" : true,
											ordering : true,
											"dom" : '<"top"iflp<"clear">>rt',
											/* "fnDrawCallback" : function() {
												//Get page numer on client. Please note: number start from 0 So
												//for the first page you will see 0 second page 1 third page 2...
												//Un-comment below alert to see page number
												//alert("Current page number: "+this.fnPagingInfo().iPage); 
												//alert("1");
											},
											"error" : function() {

											}, */

											//"sAjaxSource" : "dataMinningAutoPolicySearchGetRcds",
											'order':[1,'asc'],
											"aoColumns" : [
													{
														"targets" : 0,
														"sTitle" : "<input type='checkbox' name='auto_select_all'></input>",
														'orderable' : false,
														"width" : "15px",
														"defaultContent" : "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
													},
													{
														"targets" : 1,
														sDefaultContent : ""
													},
													{
														"targets" : 2,
														sDefaultContent : ""
													},
													{
														"targets" : 3,
														sDefaultContent : ""
													},
													{
														"targets" : 4,
														sDefaultContent : ""
													},
													{
														"targets" : 5,
														sDefaultContent : ""
													},
													{
														"targets" : 6,
														sDefaultContent : ""
													},
													{
														"targets" : 7,
														sDefaultContent : ""
													},
													{
														"targets" : 8,
														sDefaultContent : ""
													}

											]

										});

					});

	function submitAutoAssignSort(code, type) {

		if (code == 'PolicyNumber') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=PolicyNumber&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'PolicyStage') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=PolicyStage&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'PolicyState') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=PolicyState&sortType="
					+ type;
			document.testDataForm.submit();
		}
		if (code == 'PolicyEffectDt') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=PolicyEffectDt&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'NoOfDrivers') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=NoOfDrivers&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'NoOfVehi') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=NoOfVehi&sortType="
					+ type;
			document.testDataForm.submit();
		}

		if (code == 'NoOfViola') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=NoOfViola&sortType="
					+ type;
			document.testDataForm.submit();
		}
		if (code == 'TotalDue') {
			document.testDataForm.action = "./dataMinningAutoPolicySearch?sortColCode=TotalDue&sortType="
					+ type;
			document.testDataForm.submit();
		}

	}
</script>
</head>

<%
	String sorttype = request.getAttribute("sortType") != null
			? (request.getAttribute("sortType")).toString()
			: "";
	String sortcode = request.getAttribute("sortColCode") != null
			? (request.getAttribute("sortColCode")).toString()
			: "";
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
						<li role="presentation" class="auto-search active"><a
							href="#"><i
								class="fa fa-car csaa-vectors"></i>Auto Policy </a></li>
						<li role="presentation" class="policy-search"><a
							id="propSearch" href="./dataMinningPropertyPolicySearch"> <i
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
							action="${pageContext.request.contextPath}/dataMinningAutoPolicySearch"
							modelAttribute="tdmAtsSearchDTO">
							<div class="content-section datamine-content-section">
								<div class="collpasewidget active" id="generalDetails">
									<div class="widget-inner">
										<div id="generalDetailsContent" class="widget-content">
											<div class="table-parent">
												<table class="details-table">
													<tbody>
														<tr>
															<td class="lable-title blue-label" width="25%"
																align="left" valign="middle"><span>Environment</span>
																<i class="fa fa-question-circle question-tooltip-style grey-tooltip"  data-toggle="tooltip" title="Environment from where data is needed"></i></td>
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
																		valign="middle"><span class="tool">Product Type</span>
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="AAA_CSA-CA Select,AAA_SS-Signature Series"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select
																			path="addproductType" id="addproductType"
																			class="down-control">
																			<c:if test="${not empty producttype}">
																				<c:forEach var="prdFieldListDTO"
																					items="${producttype}">
																					<form:option value="${prdFieldListDTO.valueCode}">${prdFieldListDTO.listValue}</form:option>
												`									</c:forEach>
																			</c:if>
																		</form:select></td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy Status</span>
																		<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Current status of the policy"></i> -->
																		</td>
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
																		valign="middle"><span class="tool">Risk State</span> 
																		<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="State where policy is written"></i> --></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="riskState"
																			id="riskState" class="down-control">
																			<c:if test="${not empty riskstate}">
																				<c:forEach var="riskFieldListDTO"
																					items="${riskstate}">
																					<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy Term</span>
																	</td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="policyTerm"
																			id="policyTerm" class="down-control">
																			<c:if test="${not empty policyterm}">
																				<c:forEach var="policytermFieldListDTO"
																					items="${policyterm}">
																					<form:option
																						value="${policytermFieldListDTO.valueCode}">${policytermFieldListDTO.listValue}</form:option>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Policy Coverage</span>
																		<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Policy Coverage"></i>
																		</td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select path="policyCovge"
																			id="policyCovge" class="down-control-list-checkbox"
																			multiple="multiple">
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
																		</form:select> </td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Vehicle Level Coverage</span>
																		<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Vehicle Level Coverage"></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle"><form:select multiple="multiple"
																			path="addRiskCovge" id="addRiskCovge"
																			class="down-control-list-checkbox">
																			<c:if test="${not empty vehiclelevel}">
																				<c:forEach var="vehicleLeveltermFieldListDTO"
																					items="${vehiclelevel}">
																					<c:if
																						test="${vehicleLeveltermFieldListDTO.valueCode eq 'Any'}">
																						<form:option selected="selected"
																							value="${vehicleLeveltermFieldListDTO.valueCode}">${vehicleLeveltermFieldListDTO.listValue}</form:option>
																					</c:if>
																					<c:if
																						test="${vehicleLeveltermFieldListDTO.valueCode ne 'Any'}">
																						<form:option
																							value="${vehicleLeveltermFieldListDTO.valueCode}">${vehicleLeveltermFieldListDTO.listValue}</form:option>
																					</c:if>
																				</c:forEach>
																			</c:if>
																		</form:select></td>
																</tr>
																<tr>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Automobile Death Benefits</span>
																		<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Automobile Death Benefits"></i>
																		</td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="autoDeathYes"
																					path="autoDeath" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="autoDeathNo"
																					path="autoDeath" value="No" />No
																			</label> <label> <form:radiobutton id="autoDeathAny"
																					path="autoDeath" value="Any" />Any
																			</label>
																		</div>
																	</td>
																	<td class="lable-title" width="25%" align="left"
																		valign="middle"><span class="tool">Total
																			Disability</span>
																			<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Total Disability"></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="totDisaYes"
																					path="autoTotDisa" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="totDisaNo"
																					path="autoTotDisa" value="No" />No
																			</label> <label> <form:radiobutton id="totDisaAny"
																					path="autoTotDisa" value="Any" />Any
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
																			<!-- <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Payment plan is the lumpsum or equally distributed payment towards insurance premium amount"></i> -->
																			</td>
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
																		valign="middle">
																		<span class="tool">Total Due
																			 </span>
																			 <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Total amount owed on the policy to date"></i>
																			 </td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="payOffYes"
																					path="totalDue" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="payOffNo"
																					path="totalDue" value="No" />No
																			</label> <label> <form:radiobutton id="payOffAny"
																					path="totalDue" value="Any" />Any
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
																		valign="middle">
																		<span class="tool">Policy With Autopay Eligibility
																			 </span>
																			 <i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Policy which is eligible to enroll in recurring payment 
																			 "></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="autoPayYes"
																					path="autoPay" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="autoPayNo"
																					path="autoPay" value="No" />No
																			</label> <label> <form:radiobutton id="autoPayAny"
																					path="autoPay" value="Any" />Any
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
																		valign="middle"><span class="tool">Minimum Due
																			 </span><i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title=" The amount required to bring the account totally current
																			 "></i></td>
																	<td class="flied-title" width="25%" align="left"
																		valign="middle">
																		<div class="radio data-mine-radio">
																			<label> <form:radiobutton id="currBalYes"
																					path="minimumDue" value="Yes" />Yes
																			</label> <label> <form:radiobutton id="currBalNo"
																					path="minimumDue" value="No" />No
																			</label> <label> <form:radiobutton id="currBalAny"
																					path="minimumDue" value="Any" />Any
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
										<div class="widget-title" id="advanceSearch">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-plus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> <span
															class="request-title">Show Advanced Search</span>
													</div>
												</div>
											</div>
										</div>
										<div id="advSearch" class="other-details widget-content">
											<div class="table-parent">
												<table class="details-table">
													<tbody>
														<tr>
															<td class="lable-title" width="13%" align="left"
																valign="middle"><span class="tool">Number
																	of Drivers</span></td>
															<td class="flied-title adv-search-title" width="12%"
																align="left" valign="middle"><form:select
																	path="noOfDrivers" id="noOfDrivers"
																	class="down-control">
																	<form:option value="Any">Any</form:option>
																	<form:option value="1">1</form:option>
																	<form:option value="2">2</form:option>
																	<form:option value="3">3</form:option>
																	<form:option value="4">4</form:option>
																	<form:option value="5">5</form:option>
																	<form:option value="6">6</form:option>
																	<form:option value="7">7</form:option>
																</form:select></td>
															<td class="lable-title" width="13%" align="left"
																valign="middle"><span class="tool">Number
																	of Named Insured</span>
																	<i class="fa fa-question-circle question-tooltip-style"  data-toggle="tooltip" title="Number of people designated as an insured in policy 
																	"></i></td>
															<td class="flied-title adv-search-title" width="12%"
																align="left" valign="middle"><form:select
																	path="noOfNamedInsu" id="noOfNamedInsu"
																	class="down-control">
																	<form:option value="Any">Any</form:option>
																	<form:option value="1">1</form:option>
																	<form:option value="2">2</form:option>
																	<form:option value="3">3</form:option>
																	<form:option value="4">4</form:option>
																	<form:option value="5">5</form:option>
																	<form:option value="6">6</form:option>
																	<form:option value="7">7</form:option>
																</form:select></td>
															<td class="lable-title" width="13%" align="left"
																valign="middle"><span class="tool">Number
																	of Vehicles</span></td>
															<td class="flied-title adv-search-title" width="12%"
																align="left" valign="middle"><form:select
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
																</form:select></td>
															<td class="lable-title" width="13%" align="left"
																valign="middle"><span class="tool">Number of
																	Violations</span></td>
															<td class="flied-title adv-search-title" width="12%"
																align="left" valign="middle"><form:select
																	path="noOfViola" id="noOfViola" class="down-control">
																	<form:option value="Any">Any</form:option>
																	<form:option value="1">1</form:option>
																	<form:option value="2">2</form:option>
																	<form:option value="3">3</form:option>
																	<form:option value="4">4</form:option>
																	<form:option value="5">5</form:option>
																	<form:option value="6">6</form:option>
																	<form:option value="7">7</form:option>
																</form:select></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>

								<div class="btn-container">
									<input type="submit" id="btnSearchResultAuto"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style"
										title="Search" value="Search" />
									<!-- <i class="fa fa-search csaa-vectors"></i>Search
							  </button> -->
									<button type="button" id="btnRsetResultAuto"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
										title="Reset">
										<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
									</button>
								</div>
								<div id="searchresultsAuto" class="other-details search-details">
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
										<c:if test="${reservedRecords ne null}">
											<table class="my-msg-class">
												<tbody>
													<tr>
														<td class="lable-title reserve-msg-style" align="left" valign="middle">
															${reservedRecords}</td>

													</tr>
												</tbody>
											</table>
										</c:if>

										<c:if
											test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList eq null}">
											<c:out
												value="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}"></c:out>
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

										<div class="table-parent search-main widget-content">
											
											<%
												if ((String) request.getAttribute("NO_AUTO_RECORDS") != null) {
											%>
											<%=(String) request.getAttribute("NO_AUTO_RECORDS")%>
											<%
												}
											%>									
											<c:if test="${searchSwitch == 'ON' }">

											<table id="autoSearchTable" class="customDataTable"
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

															<th>Number of<br />Drivers
															</th>

															<th>Number of<br />Vehicles
															</th>

															<th>Number of<br />Violations
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
											<c:if test="${display ne null}">
												<div id="reserveCountSelected" style='display: inline;'>
											</c:if>

											<c:if
												test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList ne null}">
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
																	name="testCaseId" maxlength="20"/></td>
																<td class="lable-title" width="5%" align="right"
																	valign="middle"><input type="submit"
																	name="reserve"
																	class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve com-submit-btn"
																	id="reserve" value="Reserve"></td>
																<td  width="5%" align="right"
																	valign="middle"><input type="submit" name="export"
																	id="export" value="Export"
																	class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export com-submit-btn"></td>
															</tr>
														</tbody>
													</table>
												<input type="hidden" id="policyNumners" name="policyNumners"
													value="" />
												<form:hidden path="policyNumbers" />
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
														test="${tdmAtsSearchDTO.reservedTestDataList eq null}">
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
																<c:forEach items="${tdmAtsSearchDTO.reservedTestDataList}"
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
					
				</div>
				<div role="tabpanel" class="tab-pane" id="property"></div>
			</div>
		</div>
	</div>
	<!-- </div> -->
	<div id="submitFlag" style='display: none;'>
		<c:if test="${flag == 1}">
			<p>1</p>
			<h1>test</h1>
		</c:if>
	</div>
	</main>
	<jsp:include page="footerNew.jsp"></jsp:include>
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/search-common.js"></script>
	<script type="text/javascript" src="js/autopolicy.js"></script>
	<script type="text/javascript" src="js/SearchNavigation.js"></script>
	<script type="text/javascript" src="js/stickyfooter.js"></script>
	<script type="text/javascript">
	</script>
</body>

</html>