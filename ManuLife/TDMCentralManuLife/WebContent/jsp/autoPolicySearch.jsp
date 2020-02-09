<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Policy Auto Search</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link href="css/theme.default.css" rel="stylesheet">
<link href="css/elements.css" rel="stylesheet">
<script src="js/html5Shiv.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/messages.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>

</head>

<body>
	<div id="main" class="wrapper mainAll">
		<!--  <script src="include/header.js"></script> -->
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"/>
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
			<div id="myErrorCls" >
			</div>
			<form:form id="testDataForm" name="testDataForm"
				action="${pageContext.request.contextPath}/policyAuto"
				modelAttribute="tdmPolicyAutoSearchDTO">
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
										<spring:message code="label.dsite" var="dsite"/>
										<form:option value="${dsite}" selected="selected"><spring:message code="label.dsite"/></form:option>
									</form:select>
								</td>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.prodType" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="addproductType" id="addproductType" class="down-control">
										<spring:message code="label.autop.producttype1" var="productytpe1"/>
										<spring:message code="label.autop.producttype2" var="productytpe2"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${productytpe1}"><spring:message code="label.autop.producttype1"/></form:option>
										<form:option value="${productytpe2}"><spring:message code="label.autop.producttype2"/></form:option>
									</form:select></td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle" scope="col">
									<spring:message code="label.policy.stage" />
								</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="policyStage" id="policyStage"
										class="down-control">
										<spring:message code="label.autop.policystage1" var="policystage1"/>
										<spring:message code="label.autop.policystage2" var="policystage2"/>
										<spring:message code="label.autop.policystage3" var="policystage3"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${policystage1}"><spring:message code="label.autop.policystage1"/></form:option>
										<form:option value="${policystage2}"><spring:message code="label.autop.policystage2"/></form:option>
										<form:option value="${policystage3}"><spring:message code="label.autop.policystage3"/></form:option>


									</form:select>
								</td>

								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.state" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="policyState" id="policyState" class="down-control">
										<spring:message code="label.autop.policystate1" var="policystate1"/>
										<spring:message code="label.autop.policystate2" var="policystate2"/>
										<spring:message code="label.autop.policystate3" var="policystate3"/>
										<spring:message code="label.autop.policystate4" var="policystate4"/>
										<spring:message code="label.autop.policystate5" var="policystate5"/>
										<spring:message code="label.autop.policystate6" var="policystate6"/>
										<spring:message code="label.autop.policystate7" var="policystate7"/>
										<spring:message code="label.autop.policystate8" var="policystate8"/>
										<spring:message code="label.autop.policystate9" var="policystate9"/>
										<spring:message code="label.autop.policystate10" var="policystate10"/>
										<spring:message code="label.autop.policystate11" var="policystate11"/>
										<spring:message code="label.autop.policystate12" var="policystate12"/>
										<spring:message code="label.autop.policystate13" var="policystate13"/>
										<spring:message code="label.autop.policystate14" var="policystate14"/>
										<spring:message code="label.autop.policystate15" var="policystate15"/>
										<spring:message code="label.autop.policystate16" var="policystate16"/>
										<spring:message code="label.autop.policystate17" var="policystate17"/>
										<spring:message code="label.autop.policystate18" var="policystate18"/>
										<spring:message code="label.autop.policystate19" var="policystate19"/>
										<spring:message code="label.autop.policystate20" var="policystate20"/>
										<spring:message code="label.autop.policystate21" var="policystate21"/>
										<spring:message code="label.autop.policystate22" var="policystate22"/>
										<spring:message code="label.autop.policystate23" var="policystate23"/>
										<spring:message code="label.autop.policystate24" var="policystate24"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${policystate1}"><spring:message code="label.autop.policystate1"/></form:option>
										<form:option value="${policystate2}"><spring:message code="label.autop.policystate2"/></form:option>
										<form:option value="${policystate3}"><spring:message code="label.autop.policystate3"/></form:option>
										<form:option value="${policystate4}"><spring:message code="label.autop.policystate4"/></form:option>
										<form:option value="${policystate5}"><spring:message code="label.autop.policystate5"/></form:option>
										<form:option value="${policystate6}"><spring:message code="label.autop.policystate6"/></form:option>
										<form:option value="${policystate7}"><spring:message code="label.autop.policystate7"/></form:option>
										<form:option value="${policystate8}"><spring:message code="label.autop.policystate8"/></form:option>
										<form:option value="${policystate9}"><spring:message code="label.autop.policystate9"/></form:option>
										<form:option value="${policystate10}"><spring:message code="label.autop.policystate10"/></form:option>
										<form:option value="${policystate11}"><spring:message code="label.autop.policystate11"/></form:option>
										<form:option value="${policystate12}"><spring:message code="label.autop.policystate12"/></form:option>
										<form:option value="${policystate13}"><spring:message code="label.autop.policystate13"/></form:option>
										<form:option value="${policystate14}"><spring:message code="label.autop.policystate14"/></form:option>
										<form:option value="${policystate15}"><spring:message code="label.autop.policystate15"/></form:option>
										<form:option value="${policystate16}"><spring:message code="label.autop.policystate16"/></form:option>
										<form:option value="${policystate17}"><spring:message code="label.autop.policystate17"/></form:option>
										<form:option value="${policystate18}"><spring:message code="label.autop.policystate18"/></form:option>
										<form:option value="${policystate19}"><spring:message code="label.autop.policystate19"/></form:option>
										<form:option value="${policystate20}"><spring:message code="label.autop.policystate20"/></form:option>
										<form:option value="${policystate21}"><spring:message code="label.autop.policystate21"/></form:option>
										<form:option value="${policystate22}"><spring:message code="label.autop.policystate22"/></form:option>
										<form:option value="${policystate23}"><spring:message code="label.autop.policystate23"/></form:option>
										<form:option value="${policystate24}"><spring:message code="label.autop.policystate24"/></form:option>
									</form:select></td>


							</tr>

							<tr>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.term" /></td>
								<td class="flied-title" align="left"><form:select
										path="policyTerm" id="policyTerm" class="down-control">
										<spring:message code="label.autop.policyterm3" var="policyterm3"/>
										<spring:message code="label.autop.policyterm3" var="policyterm4"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${policyterm3}"><spring:message code="label.autop.policyterm1"/></form:option>
										<form:option value="${policyterm4}"><spring:message code="label.autop.policyterm2"/></form:option>
									</form:select></td>

								<td class="lable-title" align="left"><spring:message
										code="label.policy.assoPayReq" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline"> 
									<spring:message code="label.y" var="yes"/>
										<spring:message code="label.n" var="no"/><form:radiobutton
											path="assoPayReq" id="assoPayReq1" value="${yes}" /> <spring:message
											code="label.yes" /></label> <label class="radio-inline"> <form:radiobutton
											path="assoPayReq" id="assoPayReq2" value="${no}" /> <spring:message
											code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											path="assoPayReq" id="assoPayReq3" value="" /> <spring:message
											code="label.any" />
								</label></td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.coverage" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="policyCovge" id="policyCovge" class="down-control">
										<spring:message code="label.autop.policycovge1" var="policycovge1"/>
										<spring:message code="label.autop.policycovge2" var="policycovge2"/>
										<spring:message code="label.autop.policycovge3" var="policycovge3"/>
										<spring:message code="label.autop.policycovge4" var="policycovge4"/>
										<spring:message code="label.autop.policycovge5" var="policycovge5"/>
										<spring:message code="label.autop.policycovge6" var="policycovge6"/>
										<spring:message code="label.autop.policycovge7" var="policycovge7"/>
										<spring:message code="label.autop.policycovge8" var="policycovge8"/>
										<spring:message code="label.autop.policycovge9" var="policycovge9"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${policycovge1}"><spring:message code="label.autop.policycovge1"/></form:option>
										<form:option value="${policycovge2}"><spring:message code="label.autop.policycovge2"/></form:option>
										<form:option value="${policycovge3}"><spring:message code="label.autop.policycovge3"/></form:option>
										<form:option value="${policycovge4}"><spring:message code="label.autop.policycovge4"/></form:option>
										<form:option value="${policycovge5}"><spring:message code="label.autop.policycovge5"/></form:option>
										<form:option value="${policycovge6}"><spring:message code="label.autop.policycovge6"/></form:option>
										<form:option value="${policycovge7}"><spring:message code="label.autop.policycovge7"/></form:option>
										<form:option value="${policycovge8}"><spring:message code="label.autop.policycovge8"/></form:option>
										<form:option value="${policycovge9}"><spring:message code="label.autop.policycovge9"/></form:option>
									</form:select></td>
							</tr>

							<tr>

								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.riskCov" /></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="riskCovge" id="riskCovge" class="down-control">
										<spring:message code="label.autop.riskcovge1" var="riskcovge1"/>
										<spring:message code="label.autop.riskcovge2" var="riskcovge2"/>
										<spring:message code="label.autop.riskcovge3" var="riskcovge3"/>
										<spring:message code="label.autop.riskcovge4" var="riskcovge4"/>
										<spring:message code="label.autop.riskcovge5" var="riskcovge5"/>
										<spring:message code="label.autop.riskcovge6" var="riskcovge6"/>
										<form:option value=""><spring:message code="label.any"/></form:option>
										<form:option value="${riskcovge1}"><spring:message code="label.autop.riskcovge1"/></form:option>
										<form:option value="${riskcovge2}"><spring:message code="label.autop.riskcovge2"/></form:option>
										<form:option value="${riskcovge3}"><spring:message code="label.autop.riskcovge3"/></form:option>
										<form:option value="${riskcovge4}"><spring:message code="label.autop.riskcovge4"/></form:option>
										<form:option value="${riskcovge5}"><spring:message code="label.autop.riskcovge5"/></form:option>
										<form:option value="${riskcovge6}"><spring:message code="label.autop.riskcovge6"/></form:option>

									</form:select></td>

								<td class="lable-title" width="25%" align="left" valign="middle"></td>
								<td class="lable-title" width="20%" align="left" valign="middle"
									scope="col"></td>


							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.assoDoc" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><label class="radio-inline">
									<spring:message code="label.y" var="yes"/>
										<spring:message code="label.n" var="no"/>
										<spring:message code="label.n" var="any"/> 
									<form:radiobutton
											path="assoDocReq" id="assoDocReq1" value="${yes}" /> <spring:message
											code="label.yes" /></label> <label class="radio-inline"> <form:radiobutton
											path="assoDocReq" id="assoDocReq2" value="${no}"  /> <spring:message
											code="label.no" />
								</label> <label class="radio-inline"> <form:radiobutton
											path="assoDocReq" id="assoDocReq3" value="${any}"/> <spring:message
											code="label.any" />
								</label></td>

  	 						</tr>
						</tbody>
 
					    </tbody>

						<%-- <tbody id="paymethodContent">
							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.payType" /></td>
								<td class="flied-title" class="flied-title" align="left"
									valign="middle"><form:select path="payMethod"
										id="payMethod" class="down-control">
										<form:option value="">Any</form:option>
										<form:option value="AAAPaymentDetailsPCICreditCard">AAAPaymentDetailsPCICreditCard</form:option>
										<form:option value="PaymentDetailsCheque">PaymentDetailsCheque</form:option>
										<form:option value="PaymentDetailsCash">PaymentDetailsCash</form:option>
										<form:option value="PaymentDetailsCreditCard">PaymentDetailsCreditCard</form:option>
										<form:option value="AAAPaymentDetailsEFT">AAAPaymentDetailsEFT</form:option>
									</form:select></td>
							</tr>
						</tbody> --%>

<!-- 						<tbody id="docContent"> -->
<!-- 							<tr> -->
<%-- 								<td class="lable-title" align="left"><spring:message --%>
<%-- 										code="label.policy.docType" /></td> --%>
<%-- 								<td class="flied-title" align="left"><form:select --%>
<%-- 										path="assoDocType" id="assoDocType" --%>
<%-- 										class="down-control docType"> --%>
<%-- 										<form:option value="">Any</form:option> --%>
<%-- 										<form:option value="Declaration Page">Declaration Page</form:option> --%>
<%-- 										<form:option value="Insurance ID card">Insurance ID card</form:option> --%>
<%-- 										<form:option value="Quote Letter">Quote Letter</form:option> --%>
<%-- 										<form:option value="Identification Card">Identification Card</form:option> --%>
<%-- 										<form:option value="Insurance Identification Card">Insurance Identification Card</form:option> --%>
<%-- 										<form:option value="AutoPay Schedule">AutoPay Schedule</form:option> --%>
<%-- 										<form:option value="Welcome Letter">Welcome Letter</form:option> --%>
<%-- 										<form:option value="AutoPay Authorization Form">AutoPay Authorization Form</form:option> --%>
<%-- 										<form:option value="Request For Information">Request For Information</form:option> --%>
<%-- 										<form:option value="Declaration Form">Declaration Form</form:option> --%>
<%-- 									</form:select></td> --%>
<!-- 							</tr> -->
<!-- 						</tbody> -->

						  <tbody id="myContent">
							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.noDrivers" /></td>
								<td class="flied-title" align="left"><form:input
										path="noOfDrivers" id="noOfDrivers" class="form-control"
										maxlength="2" /></td>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.noVehi" /></td>
								<td class="flied-title" align="left"><form:input
										path="noOfVehi" id="noOfVehi" class="form-control"
										maxlength="2" /></td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.policy.noInsu" /></td>
								<td class="flied-title" align="left"><form:input
										path="noOfNamedInsu" id="noOfNamedInsu" class="form-control"
										maxlength="2" /></td>
								<td class="lable-title" align="left"><spring:message
										code="label.policy.noViola" /></td>
								<td class="flied-title" align="left"><form:input
										path="noOfViola" id="noOfViola" class="form-control"
										maxlength="3" /></td>
							</tr>

						</tbody>
    
						<tbody id="headerDiv">
							<tr>
								<td align="left" valign="middle"></td>
								<td align="left" valign="middle"></td>
								<td align="left" valign="middle"></td>
	
								<spring:message code="label.showLink" var="show"/>
								<spring:message code="label.hideLink" var="hide"/>
								<td class="lable-title" align="left" valign="middle"><a
									id="myHeader" class="hrefVisited"
									href="javascript:toggle2('myContent','myHeader','${show}','${hide}');"> <spring:message
											code="label.showLink" />
								</a></td>
							</tr>
						</tbody>

						<!--               <tr> -->
						<!--                 <td class="lable-title" align="left"> -->
						<%--                   <spring:message code="label.noOfRec" /> --%>
						<!--                 </td> -->
						<!--                 <td class="flied-title"> -->
						<%--                   <form:select path="searchRecordsNo" id="searchRecordsNo" class="down-control-small"> --%>
						<%--                     <form:option value="5">5</form:option> --%>
						<%--                     <form:option value="10">10</form:option> --%>
						<%--                     <form:option value="15">15</form:option> --%>
						<%--                     <form:option value="20">20</form:option> --%>
						<%--                   </form:select> --%>
						<!--                 </td> -->
						<!--               </tr> -->

					</table>

					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td colspan="4" align="center" valign="middle"><input
									type="submit" name="search" id="Search"
									class="btn-primary btn-cell"
									value="<spring:message code="button.serch"/>"> <input
									type="reset" value="<spring:message code="label.reset" />" class="btn-primary btn-cell"
									onClick="clearFields('./policyAuto');"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<br />
				<br />




				<c:if
					test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList eq null || empty tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}">

					<c:if test="${result ne null}">
						<table
							style="width: 100%; border: 0; font-size: 12px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
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
							test="${tdmPolicyAutoSearchDTO.autoEmailDTOs ne null && empty tdmPolicyAutoSearchDTO.autoEmailDTOs && reserveFlag eq null}">

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
							test="${tdmPolicyAutoSearchDTO.autoEmailDTOs ne null &&  not empty tdmPolicyAutoSearchDTO.autoEmailDTOs && reserveFlag eq null}">


							<table
								style="width: 100%; border: 0; font-size: 14px; color: #EC0B2D; cellpadding: 4;">
								<tbody>
									<tr>
										<td class="lable-title" align="left" valign="middle"><spring:message
												code="label.msg.recFndByOth" /></td>

									</tr>
								</tbody>
							</table>

							<table class="table  tablesorter"
								style="width: 70%; font-size: 14px; border: 0; cellpadding: 0; cellspacing: 1;">
								<thead>
									<tr>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.userId" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcId" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcName" /></th>
										<%--  <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.msg"/></th> --%>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tdmPolicyAutoSearchDTO.autoEmailDTOs}"
										var="autoEmailDTOs" varStatus="status">
										<tr>
											<td>${autoEmailDTOs.userId}</td>
											<td>${autoEmailDTOs.testCaseId}</td>
											<td>${autoEmailDTOs.testCaseName}</td>
											 
										</tr>
									</c:forEach>

									<c:forEach items="${tdmPolicyAutoSearchDTO.autoEmailDTOs}"
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
							test="${tdmPolicyAutoSearchDTO.autoEmailDTOs eq null && empty tdmPolicyAutoSearchDTO.autoEmailDTOs}">

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
					test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList eq null}">
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
					test="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList ne null &&  not empty tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}">
					<%
						int currentPage = (Integer) request
										.getAttribute("currentPage");
								int count1 = currentPage - 1;
								count1 = count1 * 10;
					%>

					<table
						style="width: 100%; border: 0; font-size: 12px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="left" valign="middle">
									${result}</td>

							</tr>
						</tbody>
					</table>

					  <table style="width:100%; border:0; font-size: 14px; font-style: italic; color:#7C6DC2;cellpadding:4;">
             			<tbody>
               			  <tr>
               			  	 <td class="lable-title" align="right" valign="middle"><spring:message code="label.totRecFetc" />${totalRecords}</td>
               			  </tr>
              			</tbody>
           			 </table> 


					<table
						style="width: 80%; border: 0; font-size: 13px; cellpadding: 5;">
						<tbody>
							<tr>
								<td class="lable-title" width="10%" align="left" valign="middle"><spring:message
										code="label.tcId" /><span>*</span></td>
								<td class="flied-title"  width="10%"  align="left" valign="middle"><form:input
										path="testCaseId" id="testCaseId"
										class="down-control-small mandetCls1" /></td>
								<td class="lable-title" width="15%" align="left" valign="middle"><spring:message
										code="label.tcName" /><span>*</span></td>
								<td class="flied-title" width="10%" align="left" valign="middle"><form:input
										path="testCaseName" id="testCaseName"
										class="down-control-small mandetCls1 mandetCls2" /></td>
								<!-- Amruta -->
								<td class="lable-title" align="left" width="30%" valign="middle"><input type="submit" name="reserve"
									class="btn-primary btn-cell" id="reserve" value="<spring:message code="button.reserve"/>"></td>
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
											code="button.reserve" /> <span>*</span></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont"><spring:message
											code="label.policy.num" /></th>
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
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.documenttype"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach
									items="${tdmPolicyAutoSearchDTO.tdmPolicyAutoSearchResultDTOList}"
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
										<td>${tdmPolicyAutoSearchResultDTOList.policyTerm}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyEffectDt}</td>
										<td>${tdmPolicyAutoSearchResultDTOList.policyExpDt}</td>
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
					<ul class="grdPagination">
						<%
							int noOfPages = (Integer) request.getAttribute("noOfPages");
									int startPage = (Integer) request.getAttribute("startPage");
									int lastPage = (Integer) request.getAttribute("lastPage");

									if (currentPage != 1) {
						%>
						<li><a href="policyAuto?page=<%=1%>">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li><a href="policyAuto?page=<%=currentPage - 1%>">&lt;</a>
							<div>&lt;</div> <%
 	} else {
 				if (noOfPages > 1) {
 %>
						<li class="disable"><a href="policyAuto?page=<%=1%>">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li class="disable"><a
							href="policyAuto?page=<%=currentPage - 1%>">&lt;</a>
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
						<li><a href="policyAuto?page=<%=i%>" id="employeeLink"><%=i%></a>
							<div><%=i%></div></li>
						<%
							}
										}
									}
									if (currentPage < noOfPages) {
						%>
						<li><a href="policyAuto?page=<%=currentPage + 1%>">&gt;</a>
							<div>&gt;</div></li>
						<li><a href="policyAuto?page=<%=noOfPages%>">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
							} else {
										if (noOfPages > 1) {
						%>
						<li class="disable"><a
							href="policyAuto?page=<%=currentPage + 1%>">&gt;</a>
							<div>&gt;</div></li>
						<li class="disable"><a href="policyAuto?page=<%=noOfPages%>">&gt;&gt;</a>
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
								<th scope="col"><input type="submit" name="reserve"
									class="btn-primary btn-cell" id="reserve2" value="<spring:message code="button.reserve"/>">
									<input type="submit" name="export" id="export"
									class="btn-primary btn-cell" value="<spring:message code="button.export"/>"></th>
							</tr>
						</tbody>
					</table>

				</c:if>
				<spring:message code="error.msg.notselect" var="notselect"/>
					<spring:message code="error.msg.fieldrequired" var="fieldrequired"/>
			</form:form>
		</div>
		<script src="include/footer.js"></script>
	</div>

	<script>
		menu_highlight('Policy_Auto_Search');

		var checkboxes = $('.cb_class');

		$(document).ready(function() {
			 $('#assoPayReq1').change(function() {
			    	$('#myErrorCls').next(".my-error-class").remove(); 
			  		if( $('#assoPayReq1').attr("checked", "checked")) {
			  			$('#myErrorCls').after('<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
			  			 $('input[type="submit"]').attr('disabled','disabled');
			  		} else {
			  			$('input[type="submit"]').removeAttr('disabled');
			  			$('#myErrorCls').next(".my-error-class").remove(); 
			  		}
			    });
			 
				 $('#assoPayReq2').change(function() {
			    	$('#myErrorCls').next(".my-error-class").remove(); 
			  		if( $('#assoPayReq2').attr("checked", "checked")) {
			  			$('#myErrorCls').after('<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
			  			 $('input[type="submit"]').attr('disabled','disabled');
			  		} else {
			  			$('input[type="submit"]').removeAttr('disabled');
			  			$('#myErrorCls').next(".my-error-class").remove(); 
			  		}
			    });
				 
				 $('#assoPayReq3').change(function() {
				    	$('#myErrorCls').next(".my-error-class").remove(); 
				  		if( $('#assoPayReq3').attr("checked", "checked")) {
				  			$('#myErrorCls').next(".my-error-class").remove(); 
				  	 		$('input[type="submit"]').removeAttr('disabled');
				  		}
				    });
			 
			});
		
		$(document).ready(function() {
			var showHide = '${tdmPolicyAutoSearchDTO.showHideFlag}';
			if (showHide == 'true') {
				toggle2('myContent', 'myHeader');
			}
		});

		$(document).ready(function() {
			//toggle3('docContent', 'N');
			toggle3('paymethodContent', 'N');
			$("#assoPayReq1").click(function() {
				toggle3('paymethodContent', $('#assoPayReq1').val());
			});
			$("#assoPayReq2").click(function() {
				toggle3('paymethodContent', $('#assoPayReq2').val());
			});
			$("#assoPayReq3").click(function() {
				toggle3('paymethodContent', 'N');
			});
 
			if ($("#assoPayReq1").is(":checked")) {
				toggle3('paymethodContent', 'Y');
			}
 
		});

		 

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

		policyAutoSearchValidation();
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
																	'<div class="my-error-class">${notselect}</div>');
													return false;
												}
												if ($('.mandetCls1').val() == '') {
													$('.mandetCls1')
															.after(
																	'<div class="my-error-class">${fieldrequired}</div>');
													return false;
												}
												if ($('.mandetCls2').val() == '') {
													$('.mandetCls2')
															.after(
																	'<div class="my-error-class">${fieldrequired}</div>');
													return false;
												}

											});
						});
		$(document)
		.ready(
				function() {
					$("#reserve2")
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
															'<div class="my-error-class">${notselect}</div>');
											return false;
										}
										if ($('.mandetCls1').val() == '') {
											$('.mandetCls1')
													.after(
															'<div class="my-error-class">${fieldrequired}</div>');
											return false;
										}
										if ($('.mandetCls2').val() == '') {
											$('.mandetCls2')
													.after(
															'<div class="my-error-class">${fieldrequired}</div>');
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
																	'<div class="my-error-class">${notselect}</div>');
													return false;
												}
											});
						});

		$(function() {
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
		});
	</script>
</body>
</html>
