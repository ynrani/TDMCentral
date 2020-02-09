<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Portal | Find Test Data</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link href="css/theme.default.css" rel="stylesheet">
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
	<div class="wrapper mainAll">
		<jsp:include page="indexHeader.jsp"></jsp:include>
		<script src="include/menu.js"></script>
		<script src="include/exceptions.js"></script>
		<div  class="container">
			<form:form id="testDataFormNon" name="testDataFormNon"
				action="./tdmNSSearch" modelAttribute="tdmNonStandSearchDTO" >
				<div class="nav" id="myid">
					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="30%" align="left" valign="middle"><spring:message
										code="label.ns.memCatt" /><span>*</span></td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:select path="memCat" id="memCat" class="down-control">
										<c:forEach var="memcat" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.memCatagories}">
										  <c:choose>
											 <c:when test="${memcat =='Any'}">
											 <form:option value="${memcat}" title="No preference-user is searching for a subscriber with any items in the category">${memcat}</form:option>
											 </c:when>
											 <c:otherwise>											
											    <form:option value="${memcat}">${memcat}</form:option>
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>									
								</td>								
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.state" /><span>*</span></td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="provState" id="provState"
										class="down-control">
										<c:forEach var="state" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.stateTypes}">
										   <c:choose>
											 <c:when test="${state =='Any'}">
											 <form:option value="${state}" title="No preference-user is searching for a subscriber with any items in the category">${state}</form:option>
											 </c:when>
											 <c:otherwise>											
											    <form:option value="${state}">${state}</form:option>
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>								
							</tr>
                            <tr id="retailONOff" style="display:none;">
                            
                                      <td class="lable-title" width="30%" align="left" valign="middle">Exchange Type</td>
									<td class="flied-title" class="flied-title" align="left"
									valign="middle"> <label class="radio-inline"> <form:radiobutton
											path="retailOnOff" id="retailOnOff" value="None" checked="true"/>Any</label> <label class="radio-inline"> <form:radiobutton
											path="retailOnOff" id="retailOnOff" value="ON-EXCHANGE" />ON-Exchange
								</label> <label class="radio-inline"> <form:radiobutton
											path="retailOnOff" id="retailOnOff" value="OFF-EXCHANGE" />OFF-Exchange
								</label></td>                 
                            </tr>
                         
							<tr>							
							<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.cov" /><span>*</span></td>
								<td class="flied-title" align="left" valign="middle" scope="col">

									<form:select path="coverageCode" id="coverageCode" multiple="true"
										class="down-control">
										<c:forEach var="cover" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.coverageTypes}">										
											<c:choose>
											 <c:when test="${cover =='Any'}">
											 <form:option value="${cover}" title="No preference-user is searching for a subscriber with any items in the category">${cover}</form:option>
											 </c:when>
											 <c:when test="${cover =='All'}">
											 <form:option value="${cover}" title="All selections combined-user is searching for a subscriber with all items in the category">${cover}</form:option>
											 </c:when>
											 <c:otherwise>											
											    <form:option value="${cover}">${cover}</form:option>		
											 </c:otherwise>											
											</c:choose>			
										</c:forEach>
									</form:select>
								</td>								
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.plnType" /><span>*</span></td>
								<td class="flied-title" align="left" valign="middle" scope="col">

									<form:select path="planType" id="planType" multiple="true"
										class="down-control">
										<c:forEach var="plan" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.planTypes}">
										  <c:choose>
											 <c:when test="${plan =='Any'}">
											 <form:option value="${plan}" title="No preference-user is searching for a subscriber with any items in the category">${plan}</form:option>
											 </c:when>											 
											 <c:otherwise>											
											   <form:option id="${fn:replace(plan,' ','')}" value="${plan}">${plan}</form:option>		
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>
							</tr>
                                <!-- =================== ======================== ===== -->
                         <%--    <tr>
                               <td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.pbm" /></td>
								<td class="flied-title" align="left" valign="middle" scope="col">

									<form:select path="pbm" id="pbm" multiple="true"
										class="down-control">
										<c:forEach var="pb" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.pbms}">										
											<c:choose>
											 <c:when test="${pb =='Any'}">
											 <form:option value="${pb}" title="No preference-user is searching for a subscriber with any items in the category">${pb}</form:option>
											 </c:when>										
											 <c:otherwise>											
											    <form:option value="${pb}">${pb}</form:option>		
											 </c:otherwise>											
											</c:choose>			
										</c:forEach>
									</form:select>
								</td>								
								<td id="fundLable" class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.fundInd" /></td>
								<td id="fundInd" class="flied-title" align="left" valign="middle" scope="col">

									<form:select path="fundingInd" id="fundingInd" multiple="true"
										class="down-control">
										<form:option value="Any" title="No preference-user is searching for a subscriber with any items in the category">Any</form:option>
										<c:forEach var="fi" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.fundingInd}">
										  <c:choose>
											 <c:when test="${fi =='Any'}">
											 
											 </c:when>											 
											 <c:otherwise>											
											   <form:option  value="${fi}">${fi}</form:option>		
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>              
                            </tr>     --%>                       
                           <!-- =================== ======================== ===== -->
							<tr>
							  <td class="lable-title" align="left" valign="middle" scope="col"><spring:message
										code="label.ns.memType" /><span>*</span></td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="subscRelation" id="subscRelation" class="down-control">
										<c:forEach var="subRel" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.subscRelations}">
										 <c:choose>
											 <c:when test="${subRel =='Any'}">
											 <form:option value="${subRel}" title="No preference-user is searching for a subscriber with any items in the category">${subRel}</form:option>
											 </c:when>											 
											 <c:otherwise>											
											   <form:option value="${subRel}">${subRel}</form:option>		
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>
								
								<td class="lable-title" align="left" width="30%" valign="middle"
									scope="col"><spring:message code="label.ns.ageGrp" /></td>
								<td class="flied-title" align="left" width="20%" valign="middle"
									scope="col"><form:select path="ageGroup"
										id="ageGroup" class="down-control">
										<c:forEach var="ageGrp" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.ageGroups}">
											<c:choose>
											<c:when test="${ageGrp =='Any'}">
											 <form:option value="${ageGrp}" title="No preference-user is searching for a subscriber with any items in the category">${ageGrp}</form:option>
											 </c:when>											 
											 <c:otherwise>											
											   <form:option value="${ageGrp}">${ageGrp}</form:option>		
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select></td>								
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.sts" /><span>*</span></td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="subscStatus" id="subscStatus" class="down-control">
										<c:forEach var="memStat" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.memStatus}">											
											<c:choose>
											 <c:when test="${memStat =='Any'}">
											 <form:option value="${memStat}" title="No preference-user is searching for a subscriber with any items in the category">${memStat}</form:option>
											 </c:when>
											 <c:otherwise>											
											    <form:option value="${memStat}">${memStat}</form:option>
											 </c:otherwise>											
											</c:choose>											
										</c:forEach>
									</form:select></td>									
															
							</tr>

							<tr>
							    <td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.orignlEfffDt" /></td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="originalEffDate" id="originalEffDate" onChange="validateDate();" placeholder="mm/dd/yyyy" class="datepicker date-control" />
										
								<%-- <label class="radio-inline"> <form:radiobutton
											path="onBefAfterOED" id="onBefAfterOED" value="On-BEFORE" checked="true"/>On-Before</label> 
								<label class="radio-inline"> <form:radiobutton
											path="onBefAfterOED" id="onBefAfterOED" value="ON-AFTER" />On-After
								</label> --%>										
								</td>						      	
						      	
						      	<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.extClm" /></td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="extClaim" id="extClaim" multiple="true" class="down-control">
										<c:forEach var="clmType" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.claimTypes}">
											<c:choose>
											 <c:when test="${clmType =='Any'}">
											 <form:option value="${clmType}" title="No preference-user is searching for a subscriber with any items in the category">${clmType}</form:option>
											 </c:when>
											 <c:when test="${clmType =='All'}">
											 <form:option value="${clmType}" title="All selections combined-user is searching for a subscriber with all items in the category">${clmType}</form:option>
											 </c:when>
											 <c:otherwise>											
											    <form:option value="${clmType}">${clmType}</form:option>
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>															
							</tr>
							<tr>							
							<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.acName" /></td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="accountName" id="accountName" class="form-control autosearch" maxlength="80" /></td>
																
							<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.acNum" /></td>
							<td class="flied-title" align="left" valign="middle"><form:input
										path="accountNum" id="accountNum" class="form-control autosearch"
										maxlength="80" /></td>													
							</tr>
							<tr>
							<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.subId" /></td>
							<td class="flied-title" align="left" valign="middle"><form:input
										path="subscId" id="subscId" class="form-control"
										maxlength="80" /></td>
			             	<td id="fundLable" class="lable-title" align="left" valign="middle"><spring:message
										code="label.ns.fundIndCode" /></td>
								<td id="fundInd" class="flied-title" align="left" valign="middle" scope="col">

									<form:select path="fundingInd" id="fundingInd" multiple="true"
										class="down-control">
										<form:option value="Any" title="No preference-user is searching for a subscriber with any items in the category">Any</form:option>
										<c:forEach var="fi" items="${tdmNonStandSearchDTO.nonStandSrchFldsDTO.fundingInd}">
										  <c:choose>
											 <c:when test="${fi =='Any'}">
											 
											 </c:when>											 
											 <c:otherwise>											
											   <form:option  value="${fi}">${fi}</form:option>		
											 </c:otherwise>											
											</c:choose>
										</c:forEach>
									</form:select>
								</td>	
							</tr>
						</tbody>
						<tbody id="myContent">
						</tbody>
						<tr>
							<td class="lable-title" align="left"><spring:message
									code="label.noOfRec" /></td>
							<td class="flied-title"><form:select path="searchRecordsNo"
									id="searchRecordsNo" class="down-control-small">
									<form:option value="5">5</form:option>
									<form:option value="10">10</form:option>
									<form:option value="15">15</form:option>
									<form:option value="20">20</form:option>
									<form:option value="30">30</form:option>
								</form:select></td>
						</tr>
					</table>

					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td colspan="4" align="center" valign="middle"><input
									type="submit" name="search" id="search"
									class="btn-primary btn-cell"
									value="<spring:message code="button.serch"/>"> <input
									type="reset" value="Reset" class="btn-primary btn-cell"
									onClick="clearFields('./tdmNonStandardSearch');"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<br />
				<br />
				<c:if
					test="${tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs eq null}">

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
						<c:if test="${reservedOnly ne true}">
						<table
							style="width: 100%; border: 0; font-size: 12px; color: #EC0B2D; cellpadding: 4;">
							<tbody>
								<tr>
									<td class="lable-title" align="left" valign="middle">No
										Records Found. <a href="javascript:;" class="l1l2support"
								onclick="popupuser('./popupEmail?result=${result}','L1/L2 Support','popup','popupOverlay','500');">
								<label class="l1l2support">Send email to TestDataManagementOffice@bcbstx.com.</label></a></td>
								</tr>
							</tbody>
						</table>
						</c:if>						
					</c:if>
					
					<c:if test="${reserveFlag ne null && reservedOnly eq true}">
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
					test="${tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs ne null &&  not empty tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs}">


					<table
						style="width: 100%; border: 0; font-size: 12px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
						<tbody>
							<tr>
								<td  class="lable-title" align="left" valign="middle">
									${result}</td>

							</tr>
						</tbody>
					</table>

					<table
						style="width: 100%; border: 0; font-size: 12px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="right" valign="middle"><spring:message
										code="label.totRecFetc" />${totalRecords}</td>
							</tr>
						</tbody>
					</table>
				</c:if>


				<c:if test="${show eq true &&  not empty tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs}">

					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.tcId" /><span>*</span></td>
								<td  align="left" valign="middle"><form:input
										path="testCaseId" id="testCaseId"
										class="down-control-small mandetCls1" /></td>
								<td class="lable-title" align="left" valign="middle"><spring:message
										code="label.tcName" /><span>*</span></td>
								<td  align="left" valign="middle"><form:input
										path="testCaseName" id="testCaseName"
										class="down-control-small mandetCls2" /></td>
								<td align="left" valign="middle">Application
									ID</td>
								<td  align="left" valign="baseline"><form:input
										path="applicationId" id="applicationId"
										class="down-control-small mandetCls3"  readonly="true"/></td>
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
							style="width: 100%; font-size: 12px;">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="lebel.chkbResrv" /></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont"><spring:message
											code="label.ns.subId" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.memType" /></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont"><spring:message
											code="label.prov.fname" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.prov.lname" /></th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.gender" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.dob" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.state" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.zip" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.grpNum" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.acName" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.plnType" /></th>									
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.pcpMg" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.fundInd" /></th>											
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.blndCat" /></th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.exchngeType" /></th>		
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.memEffDate" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.memEnddate" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont" title="Membership Coverage Group Section Effective Date">
									<spring:message	code="label.ns.mcgSecEffDt" /> </th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont" title="Membership Coverage Group Section End Date">
									<spring:message	code="label.ns.mcgSecEndDt" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.orignlEfffDt" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.cov" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
											code="label.ns.extClm" /></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="tdmNonStandardSrchResultListDTO" items="${tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs}" varStatus="status">
							<tr>
							     <c:if test="${tdmNonStandardSrchResultListDTO.reservedYN eq null }">
									<td><label class="checkbox-inline">
									<form:checkbox path="tdmNonStandardSrchResultListDTOs[${status.index}].reservedYN"
									id="tdmNonStandardSrchResultListDTOs[${status.index}].reservedYN"
														class="cb_class checkBoxCls" value="Yes" />
									</label></td>
                                    </c:if>
                                    <td style="font-size: 11px;color:blue;" title="Vew Dependent Details" align="left" onmouseover="this.style.cursor='pointer'"
												onClick="getDependentDtls('${tdmNonStandardSrchResultListDTO.memId}','${tdmNonStandardSrchResultListDTO.subscId}')">${tdmNonStandardSrchResultListDTO.subscId}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.memType}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.firstName}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.lastName}</td>	
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.gender}</td>								
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.dob}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.provState}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.homeZipCode}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.groupNum}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.acName}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.productType}</td>									
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.pcpMG}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.fundingInd}</td>									
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.blendGroup}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.exchangeType}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.memEffDateGov}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.memEndDateGroup}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.mcgSecEffDate}</td>									
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.mcgSecEndDate}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.originalEffDate}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.coverageCode}</td>
									<td style="font-size: 11px;">${tdmNonStandardSrchResultListDTO.extClaim}</td>									
								</tr>
							</c:forEach>								
							</tbody>
						</table>
						</div>
							<%
									int currentPage = (Integer) request.getAttribute("currentPage");
									int count1 = currentPage - 1;
									count1 = count1 * 10;					 
							%>

					<!-- Pagination Starts -->
					<ul class="grdPagination">
						<%
			               int noOfPages = (Integer) request.getAttribute("noOfPages");
			               int startPage = (Integer) request.getAttribute("startPage");
			               int lastPage = (Integer) request.getAttribute("lastPage");
			               int nextHun = (Integer) request.getAttribute("next_100");
			                  		  
						if (currentPage != 1) {
			   			%>
						<li><a href="tdmNSSearch?page=<%= 1 %>" onclick="showLoading(this);"  title="First Page">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li><a href="tdmNSSearch?page=<%= currentPage-1 %>" onclick="showLoading(this);"  title="Previous Page" >&lt;</a>
							<div>&lt;</div> 
						<%
			   			} else {
			   				if(noOfPages > 1) {
			   			%>
						<li class="disable"><a href="tdmNSSearch?page=<%= 1 %>" onclick="showLoading(this);">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li class="disable"><a
							href="tdmNSSearch?page=<%= currentPage-1 %>" onclick="showLoading(this);">&lt;</a>
							<div>&lt;</div>
						 <%
			   			 }
			   			}
						if(noOfPages > 1) {
			    		 for (int i=startPage; i<=lastPage; i++) {
				    		 if(currentPage == i) {
				   			 %>
							<li class="active"><a href="#"><%= i %></a>
								<div><%= i %></div></li>
							<%
				    		} else {
				    		%>
							<li><a href="tdmNSSearch?page=<%= i %>" onclick="showLoading(this);"><%= i %></a>
								<div><%= i %></div></li>
							<%
			    		 }
			    		 }
			    		}
						if(currentPage < noOfPages) {
						%>
						<li><a href="tdmNSSearch?page=<%= currentPage+1 %>" onclick="showLoading(this);" title="Next Page">&gt;</a>
							<div>&gt;</div></li>
						<li><a href="tdmNSSearch?page=<%= noOfPages %>" onclick="showLoading(this);"  title="Last Page">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
						} else {
					   if(noOfPages > 1) {
						%>
						<li class="disable"><a
							href="tdmNSSearch?page=<%= currentPage+1 %>" onclick="showLoading(this);">&gt;</a>
							<div>&gt;</div></li>
						<li class="disable"><a
							href="tdmNSSearch?page=<%= noOfPages %>" onclick="showLoading(this);">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
						   }
						}						
					    %>
					    <%if(nextHun == 100) {%>
					    <li>&nbsp;&nbsp;<input type="submit" name="next" id="next" class="btn-primary-next btn-cell-next"  
					        value="Get Next 100" title="Get Next 100 Records">
					    </li>
					  <%} %>
					</ul>					
				<!-- Pagination Ends -->				

					<br>
					<table style="width: 100%; border: 0">
						<tbody>
							<tr>
								<th scope="col"><input type="submit" name="reserve"
									class="btn-primary btn-cell" id="reserve" value="Reserve">
								<input type="submit" name="export" id="export"	class="btn-primary btn-cell" value="Export All to Excel"></th>
							</tr>
						</tbody>
					</table>

				</c:if>
				<c:if
					test="${tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs ne null &&  not empty tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs}">
                 
					<c:forEach
						items="${tdmNonStandSearchDTO.tdmNonStandardSrchResultListDTOs}"
						var="tdmNonStandardSrchResultListDTOs" varStatus="status">
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].memId" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].subscId" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].memType" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].firstName" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].lastName" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].gender" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].homeZipCode" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].dob" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].groupNum" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].acName" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].memEffDateGov" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].memEndDateGroup" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].mcgSecEffDate" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].mcgSecEndDate" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].originalEffDate" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].coverageCode" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].extClaim" />
					    <form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].exchangeType" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].AcNum" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].provState" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].productType" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].pcpMG" />
						<form:hidden
							path="tdmNonStandardSrchResultListDTOs[${status.index}].fundingInd" />
					</c:forEach>			

				</c:if>
			</form:form>
		</div>
      <div class="modal"></div>
	</div>
     <script src="include/footer.js"></script>
	<script>
	$body = $("body");
  menu_highlight('Non_Standard_Search');
  window.location.hash = "myid";
  testDataFormNon();
 function getDependentDtls(memId,subId)
 {
    popupuser('./getDepenDetails?subId='+memId+':'+subId,'Dependent Details For Subscriber '+subId,'popup','popupOverlay','600');
 }
$body.removeClass("loading");
$(document).ready(function() {
		$( "#accountNum" ).autocomplete({
			source: './tdmNonStandAuto?type=acNum',
			minLength:2,
			scroll: true,
	        scrollHeight: 180
		});
		
		$( "#accountName" ).autocomplete({
			source: './tdmNonStandAuto?type=acName',
			minLength:2,
			scroll: true,
	        scrollHeight: 180
		});
		
		$( "#subscId" ).autocomplete({
		source: './tdmNonStandAuto?type=subscID',
		minLength:2,
		scroll: true,
        scrollHeight: 180
	    });
	});
  
  $(document).ready(function () {
    var showHide = '${tdmNonStandSearchDTO.showHideFlag}';
    if(showHide == 'true') {
      toggle2('myContent', 'myHeader');
    }
  });
  $(function() {   
  
	    $( ".datepicker" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      changeYear:true,
	    });   
	  
 });
  
  function validateDate()
  {
    $('#originalEffDate').next(".my-error-class").remove(); 
     var origDate=  $('#originalEffDate').val();
     if(origDate !='')
     {
	     if(origDate.match(/^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/))
	     {
	        $('input[type="submit"]').removeAttr('disabled');
	     }
	     else
	     {
	        $('#originalEffDate').after('<div class="my-error-class">Please enter a date in the format mm/dd/yyyy.</div>');
	        $('input[type="submit"]').attr('disabled','disabled');
	     }
     }
  }
  $("#myContent").css("display", "none");
  $("#search_output_table").tablesorter({
    widgets: ['zebra']
  });
  $(".table tr:odd").css('background-color', '#ffffff');
  $(".table tr:even").addClass('even');
 
  $(function () {
    $('[placeholder]').focus(function () {
      var input = $(this);
      if (input.val() == input.attr('placeholder')) {
        input.val('');
        input.removeClass('placeholder');
      }
    }).blur(function () {
      var input = $(this);
      if (input.val() == '' || input.val() == input.attr('placeholder')) {
        input.addClass('placeholder');
        //input.val(input.attr('placeholder'));
      }
    }).blur().parents('form').submit(function () {
      $(this).find('[placeholder]').each(function () {
        var input = $(this);
        if (input.val() == input.attr('placeholder')) {
          input.val('');
        }
      });
    });
  });
 
  
  $(document).ready(function() {
	    
	   var memcat = '${tdmNonStandSearchDTO.memCat}';
	   var coverages = '${tdmNonStandSearchDTO.coverageCode}'.split(",");
	   var prodTypes = '${tdmNonStandSearchDTO.planType}'.split(",");
	   var existClaims = '${tdmNonStandSearchDTO.extClaim}'.split(",");
	   if(coverages.length >1)
	   {
	     for(var i=0; i<coverageCode.length;i++ )
	     {
		     for(var j=0; j<coverages.length;j++)
		     {
		      if(coverageCode.options[i].value==coverages[j])
		       {
		        coverageCode.options[i].selected =true;
		       }
		     }	      
	     }
	   }
	   
	   if(existClaims.length >1)
	   {
	     for(var i=0; i<extClaim.length;i++ )
	     {
		     for(var j=0; j<existClaims.length;j++)
		     {
		      if(extClaim.options[i].value==existClaims[j])
		       {
		         extClaim.options[i].selected =true;
		       }
		     }	      
	     }
	   }
	      if(memcat.toUpperCase() =="GOVERNMENT")
	       {
	           showHidePlanType(true, false);           
	       }
	       else if(memcat.toUpperCase() =="RETAIL" || memcat.toUpperCase() =="GROUP")
		   {
			   if(memcat.toUpperCase() =="RETAIL")
		       {
		          $('#retailONOff').show(); 
		       }
		    /*    
		      $('#fundLable').show(); 
		      $('#fundInd').show();  */     
		      hidePlanType(memcat.toUpperCase());
		      $('#planType').val('${tdmNonStandSearchDTO.planType}');
		   }
		   else
		   {
		      if(memcat.toUpperCase() =="BLENDED")
	          {
	            showHidePlanType(false, true);
	          }
	          else
	          {
	       	    showHidePlanType(false, false);
	          }
		        $('#planType').val('${tdmNonStandSearchDTO.planType}');
		   }
		   
	 if(prodTypes.length >=1)
	   {
	     for(var i=0; i<planType.length;i++ )
	     {
		     for(var j=0; j<prodTypes.length;j++)
		     {
		      if(planType.options[i].value==prodTypes[j])
		       {
		         planType.options[i].selected =true;
		       }
		     }	      
	     }
	   }
	       
	
	}); 
	
  function showLoading(link)
   {
     $body.addClass("loading"); 
   }
  
    $("#search").on('click' ,function(){ 
	      $body.addClass("loading"); 
	});
   
   $("#planType").change(function(){
    $('#planType').next(".my-error-class").remove();
    var plans = $('#planType > option:selected');
    $('input[type="submit"]').removeAttr('disabled');
    for(var j=0; j<plans.length;j++)
	  {	     
	      var plan = plans[j].value.toUpperCase();
	      if(plan == "ANY" && plans.length >1)
	      {
	        $('#planType').next(".my-error-class").remove();	          
		    $('#planType').after('<div class="my-error-class">You can not choose other options with "ANY".</div>');
		    $('input[type="submit"]').attr('disabled','disabled');
		    break;
	      }
	  }
   });
   
    $("#memCat").change(function() {
	       ageGroupVsMemCatVsMemType();
	       var selectedValue = $(this).val().toUpperCase();
	       if(selectedValue =="RETAIL")
	       {
	            $('#retailONOff').show();
	       }
	       else
	       {
	          $('#retailONOff').hide();
	       }
	       
		   if(selectedValue =="RETAIL" || selectedValue =="GROUP")
		   {
		     /*  $('#fundLable').show(); 
              $('#fundInd').show(); */
		      hidePlanType(selectedValue);
		   }	       
	       else if(selectedValue =="GOVERNMENT")
	       {
	           showHidePlanType(true, true);
	       }
	       else
	       {
	          if(selectedValue =="BLENDED")
	          {
	            showHidePlanType(false, true);
	          }
	          else
	          {
	       	    showHidePlanType(false, false);
	          }	          
	       }
	    });
	    
	 $("#subscStatus").change(function() {
	    	 subscriberStatusVsMemberType();
	    });
	    
	 $("#subscRelation").change(function() {
	    	 subscriberStatusVsMemberType();
	    	 ageGroupVsMemCatVsMemType();
	    });
	    
	 $("#ageGroup").change(function() {
	   	ageGroupVsMemCatVsMemType();
	    });
	    
	  $("#extClaim").change(function(){
	    existingClaimVsCoverage();
	  });  
	  
	   $("#coverageCode").change(function(){
	    existingClaimVsCoverage();
	  });
 
      $("#fundingInd").change(function(){
       $('input[type="submit"]').removeAttr('disabled');
	   $('#fundingInd').next(".my-error-class").remove();
       var fundIndi = $('#fundingInd > option:selected');
          if(fundIndi.length > 1)
	        {
	          for(var i=0; i<fundIndi.length;i++)
		        {
		          var funInd = fundIndi[i].value.toUpperCase();
		          if(funInd == "ANY")
		          {
		            $('#fundingInd').next(".my-error-class").remove();	          
	                $('#fundingInd').after('<div class="my-error-class">You can not choose other options with "Any".</div>');
	                $('input[type="submit"]').attr('disabled','disabled');
	                break;
		          }
		        }
	        }
	  });
	
	  
	  $("#next").click(function(){ 
	      $body.addClass("loading"); 
	  });  
 
      $("#reserve").click(function(){
	    	$('.mandetCls1').next(".my-error-class").remove(); 
	    	$('.mandetCls2').next(".my-error-class").remove(); 
	    	$('#search_output_table').next(".my-error-class").remove(); 
	    	var checkboxes = $('.checkBoxCls');
	      	  var selected = checkboxes.filter(":checked").length;
	    	    if (selected == false) {
	    		  $('#search_output_table').after('<div class="my-error-class">There is no selection of the records from Search Result</div>');
	    		  return false;
	      	    }
	      	    	      	    
	    	    if(selected > 5)
	    	    {
	    	        var resrveFlag ='${reserveFlag}';
		    	    if(resrveFlag.length > 0)
		    	    {
		    	      $('#search_output_table').after('<div class="my-error-class">Only 5 records are allowed to reserve per search criteria.</div>');
		    	      return false;
		    	    }
		    	    else
		    	    {    	      
	    	          $('#search_output_table').after('<div class="my-error-class">Only 5 records are allowed per reservation.</div>');
	    	    	  return false;
	    	         }
	    	    }
	    	    else
	    	    {
	    	       var count ='${count}';
		    	    if((+count + +selected) > 5)
		    	    {
		    	      $('#search_output_table').after('<div class="my-error-class">Only 5 records are allowed to reserve per search criteria.</div>');
		    	      return false;
		    	    }
	    	    }
	        	if($('.mandetCls1').val()==''){
	        	  $('.mandetCls1').after('<div class="my-error-class">This field is required.</div>');
	        	  return false;
	        	}
	        	if($('.mandetCls2').val()==''){
	        	  $('.mandetCls2').after('<div class="my-error-class">This field is required.</div>');
	        	  return false;
	        	} 	        	  
	         });
  
  </script>
</body>
</html>
