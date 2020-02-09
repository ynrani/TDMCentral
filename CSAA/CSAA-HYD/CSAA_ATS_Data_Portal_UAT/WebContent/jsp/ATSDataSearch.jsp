<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>ATS Data Central - Policy Property Search</title>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/theme.default.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>



</head>

<body>
	<div id="main" class="wrapper mainAll">

		<jsp:include page="headerPC.jsp"></jsp:include>
		<script src="include/menuPC.js"></script>
		<div id="tabs-1" class="container">
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
				action="${pageContext.request.contextPath}/policyATSSearch"
				modelAttribute="tdmAtsSearchDTO">
				<div class="">
					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle"><spring:message
										code="label.env" /><span>*</span></td>
								<td class="flied-title" width="20%" align="left" valign="middle">

									<form:select path="envType" id="envType" class="down-control"
										required="true">
										<c:if test="${not empty environment}">
											<c:forEach var="envFieldListDTO" items="${environment}">
												<form:option value="${envFieldListDTO.valueCode}">${envFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select>
								</td>

								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.prodType" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="addproductType" id="addproductType" class="down-control">

										<c:if test="${not empty producttype}">
											<c:forEach var="prdFieldListDTO" items="${producttype}">
												<form:option value="${prdFieldListDTO.valueCode}">${prdFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select></td>

							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle" scope="col"><spring:message
										code="label.policy.stage" /></td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="policyStage" id="policyStage"
										class="down-control">
										<c:if test="${not empty policystatus}">
											<c:forEach var="policyFieldListDTO" items="${policystatus}">
												<form:option value="${policyFieldListDTO.valueCode}">${policyFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select>
								</td>


								<td class="lable-title" width="25%" align="left" valign="middle">
									<spring:message code="label.policy.state" />
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:select path="riskState" id="riskState"
										class="down-control">

										<c:if test="${not empty riskstate}">
											<c:forEach var="riskFieldListDTO" items="${riskstate}">
												<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>




									</form:select> <%--  <form:select path="policyState" id="policyState2"
										class="down-control">
										<form:option value="CA">CA</form:option>
										
									</form:select>  --%>
								</td>


							</tr>

							<tr>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.term" /></td>
								<td class="flied-title" align="left"><form:select
										path="policyTerm" id="policyTerm" class="down-control">
										<c:if test="${not empty policyterm}">
											<c:forEach var="policytermFieldListDTO" items="${policyterm}">
												<form:option value="${policytermFieldListDTO.valueCode}">${policytermFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select></td>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.atsdata.pymtplan" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="addPaymentPlan" id="addPaymentPlan" class="down-control">
										<c:if test="${not empty paymentplan}">
											<c:forEach var="paymentPlantermFieldListDTO"
												items="${paymentplan}">
												<form:option
													value="${paymentPlantermFieldListDTO.valueCode}">${paymentPlantermFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select></td>



							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.coverage" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										multiple="multiple" path="policyCovge" id="policyCovge"
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
												<%-- <form:option
													value="${policyLeveltermFieldListDTO.valueCode}">${policyLeveltermFieldListDTO.listValue}</form:option>
											 --%>
											 </c:forEach>
										</c:if>

									</form:select></td>

								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.riskCov" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										multiple="multiple" path="addRiskCovge" id="addRiskCovge"
										class="down-control-list-checkbox">
										<c:if test="${not empty vehiclelevel}">
											<c:forEach var="vehicleLeveltermFieldListDTO"
												items="${vehiclelevel}">
												<%-- <form:option
													value="${vehicleLeveltermFieldListDTO.valueCode}">${vehicleLeveltermFieldListDTO.listValue}</form:option>
											 --%>
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

									</form:select></td>
							</tr>
							<tr>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.poliWithCuBal" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline"> <form:radiobutton
											path="policyWithCurBal" id="policyWithCurBal" value="Y" /> <spring:message
											code="label.yes" /></label> <label class="radio-inline"> <form:radiobutton
											path="policyWithCurBal" id="policyWithCurBal" value="N" /> <spring:message
											code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											checked="checked" path="policyWithCurBal"
											id="policyWithCurBal" value="Any" /> <spring:message
											code="label.any" />
								</label>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.poliWithPayOfAmt" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline"> <form:radiobutton
											path="policyWithPayOfAmt" id="policyWithPayOfAmt1" value="Y" />
										<spring:message code="label.yes" /></label> <label
									class="radio-inline"> <form:radiobutton
											path="policyWithPayOfAmt" id="policyWithPayOfAmt1" value="N" />
										<spring:message code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											checked="checked" path="policyWithPayOfAmt"
											id="policyWithPayOfAmt1" value="Any" /> <spring:message
											code="label.any" />
								</label></td>

							</tr>
							<tr>

								<td class="lable-title" align="left"><spring:message
										code="label.policy.poliWithAutpayElg" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline"> <form:radiobutton
											path="policyWithAutopayElig" id="policyWithAutopayElig1"
											value="Y" /> <spring:message code="label.yes" /></label> <label
									class="radio-inline"> <form:radiobutton
											path="policyWithAutopayElig" id="policyWithAutopayElig1"
											value="N" /> <spring:message code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											checked="checked" path="policyWithAutopayElig"
											id="policyWithAutopayElig1" value="Any" /> <spring:message
											code="label.any" />
								</label></td>

							</tr>

						</tbody>




						<tbody id="headerDiv">
							<tr>
								<td align="left" valign="middle"></td>
								<td align="left" valign="middle"></td>
								<td align="left" valign="middle"></td>
								<td class="lable-title" align="left" valign="middle"><a
									id="myHeader" class="hrefVisited"
									href="javascript:toggle2('myContent','myHeader');"> <spring:message
											code="label.showLink" />
								</a></td>
							</tr>
						</tbody>

						<tbody id="myContent" style="display: table-row-group;">
							<tr>
								<td class="lable-title" align="left" valign="middle">No of
									Drivers</td>

								<td class="flied-title" align="left"><form:select
										path="noOfDrivers" id="noOfDrivers" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select></td>




								<td class="lable-title" align="left">No of Vehicles</td>

								<td class="flied-title" align="left"><form:select
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
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">No of
									Named Insured</td>
								<td class="flied-title" align="left"><form:select
										path="noOfNamedInsu" id="noOfNamedInsu" class="down-control">
										<form:option value="Any">Any</form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select></td>
								<td class="lable-title" align="left">No of Violations</td>
								<td class="flied-title" align="left"><form:select
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

					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td colspan="4" align="center" valign="middle"
									class="buttonsAll8"><input type="submit" name="search"
									id="Search" value="<spring:message code="button.serch"/>">
									<input id="resetForm" type="reset" value="Reset"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<br />
				<br />








				<c:if
					test="${tdmPolicyAutoSearchDTO.tdmPolicyPropertySearchResultDTOList eq null}">
					<c:out
						value="${tdmPolicyAutoSearchDTO.tdmPolicyPropertySearchResultDTOList}"></c:out>
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

				

					


					<table
						style="width: 80%; border: 0; font-size: 13px; cellpadding: 5;">
						<tbody>
							<tr>
								<td class="lable-title" width="10%" align="left" valign="middle"><spring:message
										code="label.tcId" /><span>*</span></td>
								<td class="flied-title" width="10%" align="left" valign="middle"><form:input
										path="testCaseId" id="testCaseId"
										class="down-control-small mandetCls1" /></td>
								<td class="lable-title" width="15%" align="left" valign="middle"><spring:message
										code="label.tcName" /><span>*</span></td>
								<td class="flied-title" width="10%" align="left" valign="middle"><form:input
										path="testCaseName" id="testCaseName"
										class="down-control-small mandetCls1 mandetCls2" /></td>
								<!-- Amruta -->
								<td class="lable-title" align="left" width="30%" valign="middle"><input
									type="submit" name="reserve" class="btn-primary btn-cell"
									id="reserve" value="Reserve"></td>
							</tr>
						</tbody>
					</table>

					


					<div class="scrollingX" id="myid">
						<table id="search_output_table" class="table tablesorter"
							style="width: 100%; font-size: 13px; border: 0; cellpadding: 0; cellspacing: 1;">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="button.reserve" /> <span>*</span></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont"><spring:message
											code="label.policy.num" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.stage" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.state" /></th>
									<%-- <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.term" /></th> --%>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.effDt" /></th>
									<%-- <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.expDt" /></th> --%>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.coverage" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.riskCov" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.prodType" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.noDrivers" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.noVehi" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.noViola" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.noInsu" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.avlPays" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.avlDocs" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Document
										Type</th>
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
										<td>${tdmPolicyAutoSearchResultDTOList.policyStage}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyState}</td>
										<%-- <td>${tdmPolicyAutoSearchResultDTOList.policyTerm}</td> --%>
										<td>${tdmPolicyAutoSearchResultDTOList.policyEffectDt}</td>
										<%-- <td>${tdmPolicyAutoSearchResultDTOList.policyExpDt}</td> --%>
										<td>${tdmPolicyAutoSearchResultDTOList.policyCovge}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.riskCovge}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.productType}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.noOfDrivers}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.noOfVehi}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.noOfViola}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.noOfNamedInsu}</td>
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
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].policyCovge" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].riskCovge" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].productType" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].noOfDrivers" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].noOfVehi" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].noOfViola" />
									<form:hidden
										path="tdmPolicyAutoSearchResultDTOList[${status.index}].noOfNamedInsu" />
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
					<!-- Pagination Starts -->
					

					<!-- Pagination Ends -->
					<br>
					<table style="width: 100%; border: 0">
						<tbody>
							<tr>
								<th scope="col" class="buttonsAll15"><input type="submit"
									name="reserve" id="reserve" value="Reserve"> <input
									type="submit" name="export" id="export" value="Export to Excel"></th>
							</tr>
						</tbody>
					</table>

				
			</form:form>
			<div class="pageloading"></div>
		</div>
		<script src="include/footer.js"></script>
	</div>
	<script src="include/copyrtfooter.js"></script>
	<script>
		$body = $("body");

		menu_highlight('Policy_ATS_Search');
		menu_highlight('services');
		menu_highlight('services_ftd_insu');
		menu_highlight('services_ftd_insu_pc');
		menu_highlight('services_ftd_insu_pc_ftd');

		$body.removeClass("loading");

		function showLoading() {
			$body.addClass("loading");
		}

		var checkboxes = $('.cb_class');

		$(document)
				.ready(
						function() {
							$('#addPayReq1')
									.change(
											function() {
												$('#myErrorCls').next(
														".my-error-class")
														.remove();
												if ($('#addPayReq1').attr(
														"checked", "checked")) {
													$('#myErrorCls')
															.after(
																	'<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
													$('input[type="submit"]')
															.attr('disabled',
																	'disabled');
												} else {
													$('input[type="submit"]')
															.removeAttr(
																	'disabled');
													$('#myErrorCls').next(
															".my-error-class")
															.remove();
												}
											});

							$('#addPayReq2')
									.change(
											function() {
												$('#myErrorCls').next(
														".my-error-class")
														.remove();
												if ($('#addPayReq2').attr(
														"checked", "checked")) {
													$('#myErrorCls')
															.after(
																	'<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
													$('input[type="submit"]')
															.attr('disabled',
																	'disabled');
												} else {
													$('input[type="submit"]')
															.removeAttr(
																	'disabled');
													$('#myErrorCls').next(
															".my-error-class")
															.remove();
												}
											});

							$('#addPayReq3')
									.change(
											function() {
												$('#myErrorCls').next(
														".my-error-class")
														.remove();
												if ($('#addPayReq3').attr(
														"checked", "checked")) {
													$('#myErrorCls').next(
															".my-error-class")
															.remove();
													$('input[type="submit"]')
															.removeAttr(
																	'disabled');
												}
											});

						});

		$(document).ready(function() {
			var showHide = '${tdmPolicyPropertySearchDTO.showHideFlag}';
			if (showHide == 'true') {
				toggle2('myContent', 'myHeader');
			}
		});

		$(document).ready(function() {
			// 	        toggle3('docContent', 'N');		
			toggle3('paymethodContent', 'N');
			$("#addPayReq1").click(function() {
				toggle3('paymethodContent', $('#addPayReq1').val());
			});
			$("#addPayReq2").click(function() {
				toggle3('paymethodContent', $('#addPayReq2').val());
			});
			$("#addPayReq3").click(function() {
				toggle3('paymethodContent', 'N');
			});
			// 		 $("#addDocReq1").click(function () {			 
			// 				toggle3('docContent', $('#addDocReq1').val());					 
			// 	     });
			// 		 $("#addDocReq2").click(function () {			 
			// 				toggle3('docContent', $('#addDocReq2').val());					 
			// 	     });
			// 		 $("#addDocReq3").click(function () {			 
			// 				toggle3('docContent', 'N');					 
			// 	     });
			if ($("#addPayReq1").is(":checked")) {
				toggle3('paymethodContent', 'Y');
			}
			// 		 if($("#addDocReq1").is(":checked")){
			// 				toggle3('docContent', 'Y');
			// 		   }

		});

		/* $(".datepicker").datepicker(); */

		$("#myContent").css("display", "none");
		$("#search_output_table").tablesorter({
			widgets : [ 'zebra' ]
		});

		$(function() {
			$(".from").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 1,
				onClose : function(selectedDate) {
					$(".to").datepicker("option", "minDate", selectedDate);
				}
			});
			$(".to").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 1,
				onClose : function(selectedDate) {
					$(".from").datepicker("option", "maxDate", selectedDate);
				}
			});
		});

		$(".table tr:odd").css('background-color', '#ffffff');
		$(".table tr:even").addClass('even');

		policyPropSearchValidation();

		window.location.hash = "myid";

		$(document)
				.ready(
						function() {
							$("#reserve")
									.click(
											function() {
												$('.mandetCls1').next(
														".my-error-class")
														.remove();
												$('.mandetCls2').next(
														".my-error-class")
														.remove();
												$('#search_output_table').next(
														".my-error-class")
														.remove();
												var checkboxes = $('.checkBoxCls');
												var selected = checkboxes
														.filter(":checked").length;
												if (selected == false) {
													$('#search_output_table')
															.after(
																	'<div class="my-error-class">There is no selection of the records from Search Result</div>');
													return false;
												}
												if ($('.mandetCls1').val() == '') {
													$('.mandetCls1')
															.after(
																	'<div class="my-error-class">This field is required.</div>');
													return false;
												}
												if ($('.mandetCls2').val() == '') {
													$('.mandetCls2')
															.after(
																	'<div class="my-error-class">This field is required.</div>');
													return false;
												}

											});
						});

		$(document)
				.ready(
						function() {
							$("#export")
									.click(
											function() {
												$('#search_output_table').next(
														".my-error-class")
														.remove();
												var checkboxes = $('.checkBoxCls');
												var selected = checkboxes
														.filter(":checked").length;
												if (selected == false) {
													$('#search_output_table')
															.after(
																	'<div class="my-error-class">There is no selection of the records from Search Result</div>');
													return false;
												}
											});
						});

		/* $(function() {
			$(document).tooltip(
					{
						position : {
							my : "center bottom-20",
							at : "center top",
							using : function(position, feedback) {
								$(this).css(position);
								$("<div>").addClass("arrow").addClass(
										feedback.vertical).addClass(
										feedback.horizontal).appendTo(this);
							}
						}
					});
		}); */

		$('#addproductType')
				.change(
						function() {
							window.console && console.log($(this).val());
							if ($(this).val() == 'AAA_CSA') {

								$('#riskState').empty();
								$('#riskState')
										.append(
												'<option val="CA">CA - California</option>');

								$('#riskState').show();
							} else if ($(this).val() == 'AAA_SS') {
								$('#riskState').empty();
								$('#riskState')
										.append(
												'<option val="Any">Any</option>')
										.append(
												'<option val="AZ">AZ - Arizona</option>')
										.append(
												'<option val="CO">CO - Colorado</option>')
										.append(
												'<option val="CT">CT - Connecticut</option>')
										.append(
												'<option val="DC">DC - District of Columbia</option>')
										.append(
												'<option val="DE">DE - Delaware</option>')
										.append(
												'<option val="ID">ID - Idaho</option>')
										.append(
												'<option val="IN">IN - Indiana</option>')
										.append(
												'<option val="KS">KS - Kansas</option>')
										.append(
												'<option val="KY">KY - Kentucky</option>')
										.append(
												'<option val="MD">MD - Maryland</option>')
										.append(
												'<option val="MT">MT - Montana</option>')
										.append(
												'<option val="NJ">NJ - New Jersey</option>')
										.append(
												'<option val="NV">NV - Nevada</option>')
										.append(
												'<option val="NY">NY - New York</option>')
										.append(
												'<option val="OH">OH - Ohio</option>')
										.append(
												'<option val="OK">OK - Oklahoma</option>')
										.append(
												'<option val="OR">OR - Oregon</option>')
										.append(
												'<option val="PA">PA - Pennsylvania</option>')
										.append(
												'<option val="SD">SD - South Dakota</option>')
										.append(
												'<option val="UT">UT - Utah</option>')
										.append(
												'<option val="VA">VA - Virginia</option>')
										.append(
												'<option val="WV">WV - West Virginia</option>')
										.append(
												'<option val="WY">WY - Wyoming</option>');
								$('#riskState').show();

							} else {
								$('#riskState').empty();
								$('#riskState')
										.append(
												'<option val="Any">Any</option>')
										.append(
												'<option val="AZ">AZ - Arizona</option>')
										.append(
												'<option val="CA">CA - California</option>')
										.append(
												'<option val="CO">CO - Colorado</option>')
										.append(
												'<option val="CT">CT - Connecticut</option>')
										.append(
												'<option val="DC">DC - District of Columbia</option>')
										.append(
												'<option val="DE">DE - Delaware</option>')
										.append(
												'<option val="ID">ID - Idaho</option>')
										.append(
												'<option val="IN">IN - Indiana</option>')
										.append(
												'<option val="KS">KS - Kansas</option>')
										.append(
												'<option val="KY">KY - Kentucky</option>')
										.append(
												'<option val="MD">MD - Maryland</option>')
										.append(
												'<option val="MT">MT - Montana</option>')
										.append(
												'<option val="NJ">NJ - New Jersey</option>')
										.append(
												'<option val="NV">NV - Nevada</option>')
										.append(
												'<option val="NY">NY - New York</option>')
										.append(
												'<option val="OH">OH - Ohio</option>')
										.append(
												'<option val="OK">OK - Oklahoma</option>')
										.append(
												'<option val="OR">OR - Oregon</option>')
										.append(
												'<option val="PA">PA - Pennsylvania</option>')
										.append(
												'<option val="SD">SD - South Dakota</option>')
										.append(
												'<option val="UT">UT - Utah</option>')
										.append(
												'<option val="VA">VA - Virginia</option>')
										.append(
												'<option val="WV">WV - West Virginia</option>')
										.append(
												'<option val="WY">WY - Wyoming</option>');
								$('#riskState').show();

							}
						});

		$('#policyTerm')
				.change(
						function() {
							window.console && console.log($(this).val());
							if ($(this).val() == 'AN') {
								$('#addPaymentPlan').empty();
								$('#addPaymentPlan')
										.append(
												'<option val="Monthly">Monthly</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Quartely">Quartely</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Semi-Annual">Semi-Annual</option>');
								$('#addPaymentPlan').append(
										'<option val="Annual">Annual</option>');
								$('#addPaymentPlan').show();
							} else if ($(this).val() == 'SA') {
								$('#addPaymentPlan').empty();

								$('#addPaymentPlan')
										.append(
												'<option val="Monthly">Monthly</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Quartely">Quartely</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Semi-Annual">Semi-Annual</option>');

								$('#addPaymentPlan').show();

							} else {
								$('#addPaymentPlan').empty();
								$('#addPaymentPlan').append(
										'<option val="Any">Any</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Monthly">Monthly</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Quartely">Quartely</option>');
								$('#addPaymentPlan')
										.append(
												'<option val="Semi-Annual">Semi-Annual</option>');
								$('#addPaymentPlan').append(
										'<option val="Annual">Annual</option>');
								$('#addPaymentPlan').show();

							}
						});

		$(document)
				.ready(
						function() {
							$("#resetForm")
									.click(
											function() {

												// Environment

												$('#envType').empty();
												$('#envType')
														.append(
																'<option val="PAS-EP2">PAS-EP2</option>')
														.append(
																'<option val="PAS-BF">PAS-BF</option>');
												$('#envType').show();

												// Product Type

												$('#addproductType').empty();
												$('#addproductType')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="AAA_SS">Auto Signature Series</option>')
														.append(
																'<option val="AAA_CSA">CA Select Auto</option>');
												$('#addproductType').show();

												// Policy Status
												$('#policyStage').empty();
												$('#policyStage')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="Active">Active</option>')
														.append(
																'<option val="Cancelled">Cancelled</option>')
														.append(
																'<option val="Expired">Expired</option>')
														.append(
																'<option val="Lapsed">Lapsed</option>')
														.append(
																'<option val="Pending">Pending</option>');
												$('#policyStage').show();

												// risk state
												$('#riskState').empty();
												$('#riskState')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="AZ">AZ - Arizona</option>')
														.append(
																'<option val="CA">CA - California</option>')
														.append(
																'<option val="CO">CO - Colorado</option>')
														.append(
																'<option val="CT">CT - Connecticut</option>')
														.append(
																'<option val="DC">DC - District of Columbia</option>')
														.append(
																'<option val="DE">DE - Delaware</option>')
														.append(
																'<option val="ID">ID - Idaho</option>')
														.append(
																'<option val="IN">IN - Indiana</option>')
														.append(
																'<option val="KS">KS - Kansas</option>')
														.append(
																'<option val="KY">KY - Kentucky</option>')
														.append(
																'<option val="MD">MD - Maryland</option>')
														.append(
																'<option val="MT">MT - Montana</option>')
														.append(
																'<option val="NJ">NJ - New Jersey</option>')
														.append(
																'<option val="NV">NV - Nevada</option>')
														.append(
																'<option val="NY">NY - New York</option>')
														.append(
																'<option val="OH">OH - Ohio</option>')
														.append(
																'<option val="OK">OK - Oklahoma</option>')
														.append(
																'<option val="OR">OR - Oregon</option>')
														.append(
																'<option val="PA">PA - Pennsylvania</option>')
														.append(
																'<option val="SD">SD - South Dakota</option>')
														.append(
																'<option val="UT">UT - Utah</option>')
														.append(
																'<option val="VA">VA - Virginia</option>')
														.append(
																'<option val="WV">WV - West Virginia</option>')
														.append(
																'<option val="WY">WY - Wyoming</option>');

												$('#riskState').show();

												//Polict Term 

												$('#policyTerm').empty();
												$('#policyTerm')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="AN">Annual</option>')
														.append(
																'<option val="SA">Semi-Annual</option>');
												$('#policyTerm').show();

												//Payment Plan
												$('#addPaymentPlan').empty();
												$('#addPaymentPlan')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="Annual">Annual</option>')
														.append(
																'<option val="Semi-annual">Semi-Annual</option>')
														.append(
																'<option val="Quarterly">Quarterly</option>')
														.append(
																'<option val="Monthly">Monthly</option>')
														.append(
																'<option val="Monthly-EFT">Monthly-EFT</option>');
												$('#addPaymentPlan').show();

												// radio buttons

												$('#policyWithCurBal').filter(
														"[value='Any']").attr(
														"checked", "checked");
												$('#policyWithPayOfAmt1')
														.filter("[value='Any']")
														.attr("checked",
																"checked");
												$('#policyWithAutopayElig1')
														.filter("[value='Any']")
														.attr("checked",
																"checked");

											})

						});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#policyCovge')
									.multiselect(
											{
												numberDisplayed:1,
												onChange : function(option,
														checked, select) {
													var btnGroup = $('#policyCovge').next('.btn-group');
													if (checked) {
														if ($(option).val() == 'Any') {
															//alert('Changed option ' + $(option).val() + '.');
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"]')
																	.attr(
																			'disabled',
																			true);
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			false);
														} else {
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			true);
														}
													} else {
														if ($(option).val() == 'Any') {
															//alert('Changed option ' + $(option).val() + '.');
																$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"]')
																	.attr(
																			'disabled',
																			false);
															//$(option).attr('disabled',false);
														} else if 	($(btnGroup).find('.dropdown-menu input:checkbox:checked').length == 0) {
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			false);
														}
													}
												}
											});

							$('#policyCovge').next('.btn-group').find(
							'.multiselect-container').addClass(
							'hide-dropdown');
					$('#policyCovge').next('.btn-group').find(
							'.multiselect').on('click',
							function(event) {
								$('#policyCovge').next('.btn-group').find('.multiselect-container')
										.toggleClass('hide-dropdown');
								event.stopImmediatePropagation()
							});
			        
					$('#policyCovge').next('.btn-group').find('.dropdown-menu input[type="checkbox"]').attr('disabled',true);
					$('#policyCovge').next('.btn-group').find('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
			        
			
				});

		$(document)
				.ready(
						function() {
							$('#addRiskCovge')
									.multiselect(
											{
												numberDisplayed:1,
												onChange : function(option,
														checked, select) {
													var btnGroup = $('#addRiskCovge').next('.btn-group');
													if (checked) {
														if ($(option).val() == 'Any') {
															//alert('Changed option ' + $(option).val() + '.');
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"]')
																	.attr(
																			'disabled',
																			true);
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			false);
														} else {
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			true);
														}
													} else {
														if ($(option).val() == 'Any') {
															//alert('Changed option ' + $(option).val() + '.');
																$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"]')
																	.attr(
																			'disabled',
																			false);
															//$(option).attr('disabled',false);
														} else if 	($(btnGroup).find('.dropdown-menu input:checkbox:checked').length == 0) {
															$(btnGroup).find(
																	'.dropdown-menu input[type="checkbox"][value="Any"]')
																	.attr(
																			'disabled',
																			false);
														}
													}
												}
											});

							$('#addRiskCovge').next('.btn-group').find(
							'.multiselect-container').addClass(
							'hide-dropdown');
					$('#addRiskCovge').next('.btn-group').find(
							'.multiselect').on('click',
							function(event) {
								$('#addRiskCovge').next('.btn-group')
										.find('.multiselect-container')
										.toggleClass('hide-dropdown');
								event.stopImmediatePropagation()
							});
					$('#addRiskCovge').next('.btn-group').find('.dropdown-menu input[type="checkbox"]').attr('disabled',true);
					$('#addRiskCovge').next('.btn-group').find('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
			        
			       $(document).click(function(){
			        	
			        	$('#addRiskCovge').next('.btn-group').find('.multiselect-container').addClass('hide-dropdown');
			        	$('#policyCovge').next('.btn-group').find('.multiselect-container').addClass('hide-dropdown');
			        }); 
					
				});
		 
	</script>
</body>
</html>
