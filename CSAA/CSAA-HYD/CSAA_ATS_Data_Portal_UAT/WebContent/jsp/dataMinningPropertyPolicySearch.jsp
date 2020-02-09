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
<title>ATS Data Central - Auto Policy Search</title>
<jsp:include page="headerNew.jsp"></jsp:include>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
<link rel="stylesheet" type="text/css" href="css/datamining.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
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

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
	//Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};

	$(document)
			.ready(
					function() {

						$("#propSearchTable")
								.dataTable(
										{
											"bProcessing" : true,
											"bServerSide" : true,
											"sort" : "position",
											//bStateSave variable you can use to save state on client cookies: set value "true" 
											"bStateSave" : false,
											//Default: Page display length
											"iDisplayLength" : 10,
											//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
											"iDisplayStart" : 0,
											"dom" : '<"top"iflp<"clear">>rt',
											"fnDrawCallback" : function() {
												//Get page numer on client. Please note: number start from 0 So
												//for the first page you will see 0 second page 1 third page 2...
												//Un-comment below alert to see page number
												//alert("Current page number: "+this.fnPagingInfo().iPage); 
												//alert("1");
											},
											"sAjaxSource" : "dataMinningPropertyPolicySearchGetRcds",
											"aoColumns" : [
													{
														"targets" : 0,
														"sTitle" : "<input type='checkbox' name='auto_select_all'></input>",
														'orderable' : false,
														"width" : "15px",
														"defaultContent" : "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
													},
													{
														"mData" : "policynumber"
													},
													{
														"mData" : "policyStage"
													},
													{
														"mData" : "policyState"
													},
													{
														"mData" : "policyEffectDt"
													}, {
														"mData" : "totalDue"
													}

											]

										});

					});
</script>
<!-- <script type="text/javascript" class="init">
	

$(document).ready(function() {
	var table = $('#propSearchTable').DataTable();

	$('#propSearchTable tbody').on( 'click', 'tr', function () {
		$(this).toggleClass('selected');
	} );

	$('#button').click( function () {
		alert( table.rows('.selected').data().length +' row(s) selected' );
	} );
} );


	</script> -->
</head>
<body>
	<main class="main-style">
	<div class="container">

		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="request-header">
						<div class="page-heading">
							<ol class="breadcrumb">
								<li>Home</li>
								<li>Data Services</li>
								<li class="active">Self Service Data Mining</li>
							</ol>
						</div>

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist" id="request-tab">
							<li role="presentation" class="auto-search"><a
								href="./dataMinningAutoPolicySearch"><i
									class="fa fa-clock-o csaa-vectors"></i>Auto Policy </a></li>
							<li role="presentation" class="policy-search active"><a
								href="./dataMinningPropertyPolicySearch"> <i
									class="fa fa-lock csaa-vectors"></i>Property Policy
							</a></li>
						</ul>
					</div>


					<!-- Tab panes -->
					<div class="tab-content" id="tabData">
						<div role="tabpanel" class="tab-pane" id="manual"></div>
						<div role="tabpanel" class="tab-pane active" id="property">

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

								<!--Property tab-->
								<!-- <div role="tabpanel" class="tab-pane" id="property"> -->
								<div class="content-section datamine-content-section">
									<div class="collpasewidget active" id="generalDetails">
										<div class="widget-inner">
											<div id="generalDetailsContent" class="widget-content">
												<div class="table-parent">
													<table class="details-table">
														<tbody>
															<tr>
																<td class="lable-title blue-label" width="25%"
																	align="left" valign="middle"><span
																	data-toggle="tooltip" title=""
																	data-original-title="Environment from where data is needed"
																	class="grey-tooltip"> Environment </span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip"
																			title="AAA_CSA-CA Select,AAA_SS-Signature Series">
																				Product Type </span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip"
																			title="Current status of the policy">Policy
																				Status</span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip"
																			title="State where policy is written">Risk
																				State</span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip" title="Policy Type">Policy
																				Type</span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip" title="Policy Coverage">Policy
																				Coverage</span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip" title="Total Disablility">Total
																				Disablility</span></td>
																		<td class="flied-title" width="25%" align="left"
																			valign="middle">
																			<div class="radio data-mine-radio">
																				<label> <input type="radio" id="totDisaYes"
																					name="totDisa" value="Yes" />Yes
																				</label> <label> <input type="radio" id="totDisaNo"
																					name="totDisa" value="No" />No
																				</label> <label> <input type="radio" id="totDisaAny"
																					name="totDisa" value="Any" checked="checked" />Any
																				</label>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td class="lable-title" width="25%" align="left"
																			valign="middle"><span class="tool"
																			data-toggle="tooltip"
																			title="Automobile Death Benefits">Automobile
																				Death Benefits</span></td>
																		<td class="flied-title" width="25%" align="left"
																			valign="middle">
																			<div class="radio data-mine-radio">
																				<label> <input type="radio"
																					id="propDeathYes" name="propDeath" value="Yes" />Yes
																				</label> <label> <input type="radio"
																					id="propDeathNo" name="propDeath" value="No" />No
																				</label> <label> <input type="radio"
																					id="propDeathAny" name="propDeath" value="Any"
																					checked="checked" />Any
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip"
																			title="Payment plan is the lumpsum or equally distributed payment towards insurance premium amount">Payment
																				Plan</span></td>
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip" title="Total Due"> Total
																				Due </span></td>
																		<td class="flied-title" width="25%" align="left"
																			valign="middle">
																			<div class="radio data-mine-radio">
																				<label> <input type="radio" id="payOffYes"
																					name="propPayOff" value="Yes" />Yes
																				</label> <label> <input type="radio" id="payOffNo"
																					name="propPayOff" value="No" />No
																				</label> <label> <input type="radio" id="payOffAny"
																					name="propPayOff" value="Any" checked="checked" />Any
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
																			valign="middle">Do you want a policy with
																			autopay eligibility?</td>
																		<td class="flied-title" width="25%" align="left"
																			valign="middle">
																			<div class="radio data-mine-radio">
																				<label> <input type="radio" id="autoPayYes"
																					name="propPay" value="Yes" />Yes
																				</label> <label> <input type="radio" id="autoPayNo"
																					name="propPay" value="No" />No
																				</label> <label> <input type="radio" id="autoPayAny"
																					name="propPay" value="Any" checked="checked" />Any
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
																			valign="middle"><span class="tool"
																			data-toggle="tooltip" title="Minimum Due">
																				Minimum Due </span></td>
																		<td class="flied-title" width="25%" align="left"
																			valign="middle">
																			<div class="radio data-mine-radio">
																				<label> <input type="radio" id="currBalYes"
																					name="propCurrBal" value="Yes" />Yes
																				</label> <label> <input type="radio" id="currBalNo"
																					name="propCurrBal" value="No" />No
																				</label> <label> <input type="radio" id="currBalAny"
																					name="propCurrBal" value="Any" checked="checked" />Any
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
										<input type="submit" name="search" value="search" id="search"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style"
											title="SearchP" />
										<!-- <input type="submit" id="searchP"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style"
											title="Search"/> -->
										<!-- <i class="fa fa-search csaa-vectors"></i> -->

										<button type="button" id="btnRsetResultProp"
											class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
											title="Reset">
											<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
										</button>
									</div>
									<div id="searchresultsprop"
										class="other-details search-details" style="display: inline">
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
												test="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList ne null &&  not empty tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList}">
												<%
													int currentPage = (Integer) request
																	.getAttribute("currentPage");
															int count1 = currentPage - 1;
															count1 = count1 * 10;
												%>

												<table
													style="width: 100%; border: 0; font-size: 12px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
													<tbody>
														<%-- <tr>
														<td class="lable-title" align="left" valign="middle">
															${result}</td>

													</tr> --%>
													</tbody>
												</table>

												<table
													style="width: 100%; border: 0; font-size: 14px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
													<tbody>
														<%-- <tr>
														<td class="lable-title" align="right" valign="middle"><spring:message
																code="label.totRecFetc" />${totalRecords}</td>
													</tr> --%>
													</tbody>
												</table>

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
												<div id="searchresultsAuto"
													class="other-details search-details">
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
														<div class="table-parent search-main widget-content">
															<div>
																<c:out
																	value="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}" />

															</div>

															<table id="propSearchTable" class="customDataTable"
																cellspacing="0" width="100%">
																<thead>
																	<tr>

																		<th></th>
																		<th>Policy <br />Number
																		</th>
																		<th>Product <br />Type
																		</th>
																		<th>Policy <br />Status
																		</th>
																		<th>Effective <br />Date
																		</th>
																		<th>Total <br />Due
																		</th>
																	</tr>
																</thead>
																<%-- <tbody>
															
															<c:forEach
																items="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList}"
																var="tdmPolicyPropertySearchResultDTOList"
																varStatus="status">

																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].policynumber" />
																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].productType" />

																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].policyStage" />
																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].policyType" />
																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].policyEffectDt" />
																<form:hidden
																	path="tdmPolicyPropertySearchResultDTOList[${status.index}].totalDue" />


															</c:forEach>
														</tbody> --%>

															</table>
														</div>
													</div>
													<ul class="grdPagination">
														<%
															int noOfPages = (Integer) request.getAttribute("noOfPages");
																	int startPage = (Integer) request.getAttribute("startPage");
																	int lastPage = (Integer) request.getAttribute("lastPage");

																	if (currentPage != 1) {
														%>
														<li><a
															href="dataMinningPropertyPolicySearch?page=<%=1%>">&lt;&lt;</a>
															<div>&lt;&lt;</div></li>
														<li><a
															href="dataMinningPropertyPolicySearch?page=<%=currentPage - 1%>">&lt;</a>
															<div>&lt;</div> <%
 	} else {
 				if (noOfPages > 1) {
 %>
														<li class="disable"><a
															href="dataMinningPropertyPolicySearch?page=<%=1%>">&lt;&lt;</a>
															<div>&lt;&lt;</div></li>
														<li class="disable"><a
															href="dataMinningPropertyPolicySearch?page=<%=currentPage - 1%>">&lt;</a>
															<div>&lt;</div> <%
 	}
 			}
 			if (noOfPages > 1) {
 				for (int i = startPage; i <= lastPage; i++) {
 					if (currentPage == i) {
 %>
														<li class="active"><a href="#"><%=i%></a>
															<div><%=i%></div></li>
														<%
															} else {
														%>
														<li><a
															href="dataMinningPropertyPolicySearch?page=<%=i%>"
															id="employeeLink"><%=i%></a>
															<div><%=i%></div></li>
														<%
															}
																		}
																	}
																	if (currentPage < noOfPages) {
														%>
														<li><a
															href="dataMinningPropertyPolicySearch?page=<%=currentPage + 1%>">&gt;</a>
															<div>&gt;</div></li>
														<li><a
															href="dataMinningPropertyPolicySearch?page=<%=noOfPages%>">&gt;&gt;</a>
															<div>&gt;&gt;</div></li>
														<%
															} else {
																		if (noOfPages > 1) {
														%>
														<li class="disable"><a
															href="dataMinningPropertyPolicySearch?page=<%=currentPage + 1%>">&gt;</a>
															<div>&gt;</div></li>
														<li class="disable"><a
															href="dataMinningPropertyPolicySearch?page=<%=noOfPages%>">&gt;&gt;</a>
															<div>&gt;&gt;</div></li>
														<%
															}
																	}
														%>
													</ul>
													<div class="result-btn-container">
														<input type="submit" id="reserve" value="Reserve"
															name="reserve"
															class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style datamine-reserve"
															title="Reserve" /> <input type="submit" id="export"
															value="Export" name="export"
															class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style datamine-export"
															title="Export" />
													</div>
											</c:if>
										</div>
									</div>
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
											<p>Record found. Reservation details for the current
												policy search criteria</p>
											<table id="reservationTable" class="customDataTable"
												cellspacing="0" width="100%">
												<thead>
													<tr>
														<th>#</th>
														<th>User Id</th>
														<th>Number of Reserved Records</th>
														<th>Email User</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach
														items="${tdmPolicyPropertySearchDTO.reservedTestDataList}"
														var="reservedTestDataList" varStatus="status">
														<tr>

															<td>${reservedTestDataList.sno}</td>
															<td>${reservedTestDataList.userId}</td>
															<td>${reservedTestDataList.noOfRecordsResvByUser}</td>
															<td class="buttonsAll8"><input type="button"
																value="<spring:message 	code="label.msg" />"
																id="jqxButton"
																onclick="popup('./popupEmailUser?user=${autoEmailDTOs.userId}&result=${result}&reserveId=${autoEmailDTOs.testCaseId}','Un-Reserve Request','popup','popupOverlay','550');" />
															</td>
														</tr>

													</c:forEach>
												</tbody>
											</table>
										</div>

									</div>
								</div>
						</div>
					</div>
					</form:form>
				</div>
			</div>
		</div>

	</div>
	<div role="tabpanel" class="tab-pane" id="property"></div>
	</div>
	</div>
	</div>
	<div id="submitFlag" style='display: none;'>
		<c:if test="${flag == 1}">
			<p>1</p>
			<h1>test</h1>
		</c:if>
	</div>
	</div>
	</main>
	<jsp:include page="footerNew.jsp"></jsp:include>
	<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="js/search-common.js"></script>
	<script type="text/javascript" src="js/SearchNavigation.js"></script>
	<!-- <script type="text/javascript" src="js/autopolicy.js"></script> -->
	<script type="text/javascript" src="js/proppolicy.js"></script>
	<script type="text/javascript" src="js/SearchNavigation.js"></script>
</body>
</html>