
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Policy Property Search</title>
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
<!-- <script type="text/javascript" src="js/multiSelect.js"></script> -->


	

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
				action="${pageContext.request.contextPath}/policyPropertyNew"
				modelAttribute="tdmPolicyPropertyNewSearchDTO">
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
									<spring:message code="label.policy.PolicyType" />
								</td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="policyType" id="policyType" class="down-control">

										<c:if test="${not empty policytype}">
											<c:forEach var="ptFieldListDTO" items="${policytype}">
												<form:option value="${ptFieldListDTO.valueCode}">${ptFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>
									</form:select></td>
							</tr>

							<tr>
								<td class="lable-title" width="25%" align="left" valign="middle">
									<spring:message code="label.policy.state" />
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:select path="policyState" id="policyState1"
										class="down-control">
										<c:if test="${not empty riskstate}">
											<c:forEach var="riskFieldListDTO" items="${riskstate}">
												<form:option value="${riskFieldListDTO.valueCode}">${riskFieldListDTO.listValue}</form:option>
											</c:forEach>
										</c:if>

									</form:select>
								</td>

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
											</c:forEach>
											
										</c:if>

									</form:select></td>

								<td class="lable-title" align="left"><spring:message
										code="label.policy.poliWithTotDue" /></td>
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
										code="label.policy.poliWithMinDue" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline"> <form:radiobutton
											path="policyWithCurBal" id="policyWithCurBal" value="Y" /> <spring:message
											code="label.yes" /></label> <label class="radio-inline"> <form:radiobutton
											path="policyWithCurBal" id="policyWithCurBal" value="N" /> <spring:message
											code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											checked="checked" path="policyWithCurBal"
											id="policyWithCurBal" value="Any" /> <spring:message
											code="label.any" /></label></td>
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
							</tr>
						</tbody>

						<tbody id="myContent" style="display: table-row-group;">
							<tr>
								<td class="lable-title" align="left" valign="middle">No of
									Drivers</td>

								<td class="flied-title" align="left"><form:select
										path="noOfDrivers" id="noOfDrivers" class="down-control">
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
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										<form:option value="6">6</form:option>
										<form:option value="7">7</form:option>
									</form:select></td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle">No of
									Named Insured</td>
								<td class="flied-title" align="left"><form:select
										path="noOfNamedInsu" id="noOfNamedInsu" class="down-control">
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
					test="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList eq null}">

					<c:if test="${result ne null}">
						<table
							style="width: 100%; border: 0; font-size: 14px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
							<tbody>
								<tr>
									<td class="lable-title" align="left" valign="middle">
										${result}</td>

								</tr>
							</tbody>
						</table>
						<br />
						<br />

						<c:if
							test="${tdmPolicyPropertySearchDTO.autoEmailDTOs ne null && empty tdmPolicyPropertySearchDTO.autoEmailDTOs && reserveFlag eq null}">

							<table
								style="width: 100%; border: 0; font-size: 14px; color: #EC0B2D; cellpadding: 4;">
								<tbody>
									<tr>
										<td class="lable-title" align="left" valign="middle"><spring:message
												code="label.msg.recFndByyou" /></td>
									</tr>
								</tbody>
							</table>

						</c:if>

						<c:if
							test="${tdmPolicyPropertySearchDTO.autoEmailDTOs ne null &&  not empty tdmPolicyPropertySearchDTO.autoEmailDTOs && reserveFlag eq null}">

							<table
								style="width: 100%; border: 0; font-size: 14px; color: #EC0B2D; cellpadding: 4;">
								<tbody>
									<tr>
										<td class="lable-title" align="left" valign="middle"><spring:message
												code="label.msg.recFndByOth" /></td>
									</tr>
								</tbody>
							</table>

							<table class="table tablesorter"
								style="width: 70%; font-size: 14px; border: 0; cellpadding: 0; cellspacing: 1;">
								<thead>
									<tr>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.userId" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcId" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcName" /></th>
										<%-- <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.msg"/></th> --%>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tdmPolicyPropertySearchDTO.autoEmailDTOs}"
										var="autoEmailDTOs" varStatus="status">
										<tr>
											<td>${autoEmailDTOs.userId}</td>
											<td>${autoEmailDTOs.testCaseId}</td>
											<td>${autoEmailDTOs.testCaseName}</td>

										</tr>
									</c:forEach>

									<c:forEach items="${tdmPolicyPropertySearchDTO.autoEmailDTOs}"
										var="autoEmailDTOs" varStatus="status">

										<form:hidden path="autoEmailDTOs[${status.index}].userId" />
										<form:hidden path="autoEmailDTOs[${status.index}].testCaseId" />
										<form:hidden
											path="autoEmailDTOs[${status.index}].testCaseName" />

									</c:forEach>

								</tbody>
							</table>

							<%
								int currentPage = (Integer) request
														.getAttribute("currentPage");
												int count1 = currentPage - 1;
												count1 = count1 * 10;
							%>


							<!-- Pagination Starts -->
							<ul class="grdPagination">
								<%
									int noOfPages = (Integer) request
															.getAttribute("noOfPages");
													int startPage = (Integer) request
															.getAttribute("startPage");
													int lastPage = (Integer) request
															.getAttribute("lastPage");

													if (currentPage != 1) {
								%>
								<li><a href="policyProp?page=<%=1%>">&lt;&lt;</a>
									<div>&lt;&lt;</div></li>
								<li><a href="policyProp?page=<%=currentPage - 1%>">&lt;</a>
									<div>&lt;</div> <%
 	} else {
 						if (noOfPages > 1) {
 %>
								<li class="disable"><a href="policyProp?page=<%=1%>">&lt;&lt;</a>
									<div>&lt;&lt;</div></li>
								<li class="disable"><a
									href="policyProp?page=<%=currentPage - 1%>">&lt;</a>
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
								<li><a href="policyProp?page=<%=i%>" id="employeeLink"><%=i%></a>
									<div><%=i%></div></li>
								<%
									}
														}
													}
													if (currentPage < noOfPages) {
								%>
								<li><a href="policyProp?page=<%=currentPage + 1%>">&gt;</a>
									<div>&gt;</div></li>
								<li><a href="policyProp?page=<%=noOfPages%>">&gt;&gt;</a>
									<div>&gt;&gt;</div></li>
								<%
									} else {
														if (noOfPages > 1) {
								%>
								<li class="disable"><a
									href="policyProp?page=<%=currentPage + 1%>">&gt;</a>
									<div>&gt;</div></li>
								<li class="disable"><a
									href="policyProp?page=<%=noOfPages%>">&gt;&gt;</a>
									<div>&gt;&gt;</div></li>
								<%
									}
													}
								%>
							</ul>

							<!-- Pagination Ends -->


						</c:if>

						<c:if
							test="${tdmPolicyPropertySearchDTO.autoEmailDTOs eq null && empty tdmPolicyPropertySearchDTO.autoEmailDTOs}">

							<table
								style="width: 100%; border: 0; font-size: 14px; color: #EC0B2D; cellpadding: 4;">
								<tbody>
									<tr>
										<td class="lable-title" align="left" valign="middle"><spring:message
												code="label.msg.noRec" /></td>
									</tr>

									<tr>

									</tr>
								</tbody>
							</table>

						</c:if>

					</c:if>
				</c:if>


				<c:if
					test="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList eq null}">
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

				<c:if
					test="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList ne null &&  not empty tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList}">
					<%
						int currentPage = (Integer) request
										.getAttribute("currentPage");
								int count1 = currentPage - 1;
								count1 = count1 * 10;
					%>

					<table
						style="width: 100%; border: 0; font-size: 14px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="left" valign="middle">
									${result}</td>

							</tr>
						</tbody>
					</table>

					<table
						style="width: 100%; border: 0; font-size: 14px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="right" valign="middle"><spring:message
										code="label.totRecFetc" />${totalRecords}</td>
							</tr>
						</tbody>
					</table>

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



					<div class="scrollingX" id="myid">
						<table id="search_output_table" class="table tablesorter"
							style="width: 100%; font-size: 13px; border: 0; cellpadding: 0; cellspacing: 1;">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="button.reserve" /><span>*</span></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont"><spring:message
											code="label.policy.num" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.prodType" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.stage" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.state" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.term" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.effDt" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.expDt" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.policy.riskCov" /></th>
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
									items="${tdmPolicyPropertySearchDTO.tdmPolicyPropertySearchResultDTOList}"
									var="tdmPolicyPropertySearchResultDTOList" varStatus="status">
									<tr>
										<c:if
											test="${tdmPolicyPropertySearchResultDTOList.reservedYN eq null }">
											<td><label class="checkbox-inline"> <form:checkbox
														path="tdmPolicyPropertySearchResultDTOList[${status.index}].reservedYN"
														id="tdmPolicyPropertySearchResultDTOList[${status.index}].reservedYN"
														class="cb_class checkBoxCls" value="Yes" />
											</label></td>
										</c:if>

										<td>${tdmPolicyPropertySearchResultDTOList.policynumber}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.productType}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.policyStage}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.policyState}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.policyType}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.policyEffectDt}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.policyExpDt}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.riskCovge}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.assoPayReq}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.assoDocType}</td>
										<td>${tdmPolicyPropertySearchResultDTOList.docType}</td>

									</tr>
								</c:forEach>


							</tbody>

						</table>
					</div>
					<!-- Pagination Starts -->
					<ul class="grdPagination">
						<%
							int noOfPages = (Integer) request.getAttribute("noOfPages");
									int startPage = (Integer) request.getAttribute("startPage");
									int lastPage = (Integer) request.getAttribute("lastPage");

									if (currentPage != 1) {
						%>
						<li><a href="policyProp?page=<%=1%>" onClick="showLoading();">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li><a href="policyProp?page=<%=currentPage - 1%>"
							onClick="showLoading();">&lt;</a>
							<div>&lt;</div> <%
 	} else {
 				if (noOfPages > 1) {
 %>
						<li class="disable"><a href="policyProp?page=<%=1%>"
							onClick="showLoading();">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li class="disable"><a
							href="policyProp?page=<%=currentPage - 1%>"
							onClick="showLoading();">&lt;</a>
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
						<li><a href="policyProp?page=<%=i%>" id="employeeLink"
							onClick="showLoading();"><%=i%></a>
							<div><%=i%></div></li>
						<%
							}
										}
									}
									if (currentPage < noOfPages) {
						%>
						<li><a href="policyProp?page=<%=currentPage + 1%>"
							onClick="showLoading();">&gt;</a>
							<div>&gt;</div></li>
						<li><a href="policyProp?page=<%=noOfPages%>"
							onClick="showLoading();">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
							} else {
										if (noOfPages > 1) {
						%>
						<li class="disable"><a
							href="policyProp?page=<%=currentPage + 1%>"
							onClick="showLoading();">&gt;</a>
							<div>&gt;</div></li>
						<li class="disable"><a href="policyProp?page=<%=noOfPages%>"
							onClick="showLoading();">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
							}
									}
						%>
					</ul>

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

				</c:if>


				<!-- reserved test data bu other users -->

				<c:if
					test="${reservedTestDataListPerUser ne null &&   not empty reservedTestDataListPerUser}">


					<table
						style="width: 100%; border: 0; font-size: 12px; color: #EC0B2D; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.msg.recFndByOth" /></td>

							</tr>
						</tbody>
					</table>

					<table class="table "
						style="width: 70%; font-size: 12px; border: 0; cellpadding: 0; cellspacing: 1">
						<thead>
							<tr>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.userId" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.tcId" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.tcName" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.rsvd.tc.data.exipryDt" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.rsvd.tc.data.createdDate" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.rsvd.tc.data.dayToExpire" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.rsvd.tc.data.noOfRecords" /></th>
								<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
										code="label.msg" /></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reservedTestDataListPerUser}"
								var="reservedTestData" varStatus="status">
								<tr>
									<td>${reservedTestData.userId}</td>
									<td>${reservedTestData.testCaseId}</td>
									<td>${reservedTestData.testCaseName}</td>
									<td>${reservedTestData.exipryDt}</td>
									<td>${reservedTestData.createdDate}</td>
									<td>${reservedTestData.dayToExpire}</td>
									<td>${reservedTestData.noOfRecords}</td>
									<td class="buttonsAll8"><input type="button" value="Email"
										id="jqxButton"
										onclick="popup('./popupEmailUser?user=${autoEmailDTOs.userId}&result=${result}&reserveId=${autoEmailDTOs.testCaseId}','Un-Reserve Request','popup','popupOverlay','550');" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</c:if>
			</form:form>
			<div class="pageloading"></div>
		</div>
		<script src="include/footer.js"></script>
	</div>
	<script src="include/copyrtfooter.js"></script>
	<script>
		$body = $("body");

		menu_highlight('Policy_Property_Search_New');
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
										'<option val="Annual"></option>');
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
		$('#addproductType')
				.change(
						function() {
							window.console && console.log($(this).val());
							if (($(this).val() == 'H04')
									|| ($(this).val() == 'Any')) {
								$('#policyCovge').empty();
								$('#policyCovge')
										.append(
												'<option val="Dwelling (A)">Dwelling (A)</option>');
								$('#policyCovge')
										.append(
												'<option val="Other Structures (B)">Other Structures (B)</option>');
								$('#policyCovge')
										.append(
												'<option val="Building Additions/Alterations">Building Additions/Alterations</option>');
								$('#policyCovge')
										.append(
												'<option val="Personal Property ( C)">Personal Property ( C)</option>');
								$('#policyCovge')
										.append(
												'<option val="Loss of Use (D)">Loss of Use (D)</option>');
								$('#policyCovge')
										.append(
												'<option val="Personal Liability ( E)">Personal Liability ( E)</option>');
								$('#policyCovge')
										.append(
												'<option val="Medical Payments to Others (F)">Medical Payments to Others (F)</option>');

								$('#policyCovge').show();
							} else if (($(this).val() == 'H03')
									|| ($(this).val() == 'H06')
									|| ($(this).val() == 'DP3')) {
								$('#policyCovge').empty();
								$('#policyCovge')
										.append(
												'<option val="Dwelling (A)">Dwelling (A)</option>');
								$('#policyCovge')
										.append(
												'<option val="Other Structures (B)">Other Structures (B)</option>');

								$('#policyCovge')
										.append(
												'<option val="Personal Property ( C)">Personal Property ( C)</option>');
								$('#policyCovge')
										.append(
												'<option val="Loss of Use (D)">Loss of Use (D)</option>');
								$('#policyCovge')
										.append(
												'<option val="Personal Liability ( E)">Personal Liability ( E)</option>');
								$('#policyCovge')
										.append(
												'<option val="Medical Payments to Others (F)">Medical Payments to Others (F)</option>');

								$('#policyCovge').show();
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
																'<option val="H03">California Homeowners</option>')
														.append(
																'<option val="H04">Homeowners Signature Series</option>');
												$('#addproductType').show();

												// Product Stage
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

												// policyType

												$('#policyType').empty();
												$('#policyType')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="H03">H03</option>')
														.append(
																'<option val="H04">H04</option>')
														.append(
																'<option val="H06">H06</option>');
												$('#policyType').show();

												$('#policyState1').empty();
												$('#policyState1')
														.append(
																'<option val="Any">Any</option>')
														.append(
																'<option val="IN">IN - Indiana</option>')
														.append(
																'<option val="PA">PA - Pennsylvania</option>')
														.append(
																'<option val="VA">VA - Virginia</option>')
												append(
														'<option value="CO">CO - Colorado</option>')
														.append(
																'<option value="NJ">NJ - New Jersey</option>');
												$('#policyState1').show();

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

												$('#policyWithPayOfAmt1')
														.filter("[value='Any']")
														.attr("checked",
																"checked");
												$('#policyWithCurBal').filter(
														"[value='Any']").attr(
														"checked", "checked");
												$('#policyWithAutopayElig1')
														.filter("[value='Any']")
														.attr("checked",
																"checked");

											})

						});

		$('#addproductType')
				.change(
						function() {
							window.console && console.log($(this).val());

							$('#policyState1').empty();
							$('#policyState1')
									.append('<option val="Any">Any</option>')
									.append(
											'<option val="IN">IN - Indiana</option>')
									.append(
											'<option val="PA">PA - Pennsylvania</option>')
									.append(
											'<option val="VA">VA - Virginia</option>')
							append('<option value="CO">CO - Colorado</option>')
									.append(
											'<option value="NJ">NJ - New Jersey</option>');
							$('#policyState1').show();

						});
	</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#policyCovge').multiselect({
        	numberDisplayed:1,
			onChange: function(option, checked, select) {
				if(checked)
				{
					if($(option).val() == 'Any')
					{
						//alert('Changed option ' + $(option).val() + '.');
						$('.dropdown-menu input[type="checkbox"]').attr('disabled',true);
						$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
					}
					else
					{
						$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',true);
					}
				}
				else
				{
					if($(option).val() == 'Any')
					{
						//alert('Changed option ' + $(option).val() + '.');
						$('.dropdown-menu input[type="checkbox"]').attr('disabled',false);
						//$(option).attr('disabled',false);
					}
					else if($('.dropdown-menu input:checkbox:checked').length == 0)
					{
						$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
					}
				}
            }
		});
        
        $('.multiselect-container').addClass('hide-dropdown');
        $(document).on('click','.multiselect',function(event){
        	$('.multiselect-container').toggleClass('hide-dropdown');
        	event.stopPropogation();
        });
        
        $('.dropdown-menu input[type="checkbox"]').attr('disabled',true);
		$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
        
        $(document).click(function(){
        	
        	$('.multiselect-container').addClass('hide-dropdown');
        });
    });
    
   
$('#resetForm').click(function(){
	//alert("h");
	$('#policyCovge').multiselect('deselectAll',true);
	$('.multiselect-selected-text').text('Any');
	$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('checked',true);
	 $('.dropdown-menu input[type="checkbox"]').attr('disabled',true);
		$('.dropdown-menu input[type="checkbox"][value="Any"]').attr('disabled',false);
});
    
	
</script>
</body>
</html>
