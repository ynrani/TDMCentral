<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Policy Center Search</title>
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
<script>
$(function() {
		$("#tabs").tabs();
		var pickerOpts = {
				dateFormat:"d/M/yy"
			};	
		$("#datepicker1").datepicker(pickerOpts);
		$("#datepicker2").datepicker(pickerOpts);
	});
</script>
</head>

<body>
	<div id="main" class="wrapper mainAll">

		<jsp:include page="header.jsp"></jsp:include>
		<script src="include/menu.js"></script>
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
				action="${pageContext.request.contextPath}/policyProp"
				modelAttribute="tdmPolicyCenterSearchDTO">
				<div class="">
					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Policy Type<span>*</span></td>
								<td class="flied-title" width="20%" align="left" valign="middle">

									<form:select path="productCode" class="down-control"
										required="true">
										<form:option value="BusinessOwners" selected="selected">BusinessOwnersLine</form:option>
										<form:option value="BusinessAuto" >BusinessAutoLine</form:option>
										<form:option value="CommercialProperty" >CommercialPropertyLine</form:option>
										<form:option value="GeneralLiability" >GeneralLiabilityLine</form:option>
										<form:option value="InlandMarine" >InlandMarineLine</form:option>
										<form:option value="PersonalAuto" >PersonalAutoLine</form:option>
										<form:option value="Policy" >PolicyLine</form:option>
										<form:option value="WorkersComp" >WorkersCompLine</form:option>
									</form:select>
								</td>

								

							</tr>

							<%-- <tr>
							<td class="lable-title" align="left" valign="middle">Account Number</td>
								<td class="flied-title" align="left" valign="middle"><form:input path="accountNumber" class="form-control"></form:input></td>
								
							<td class="lable-title" align="left" valign="middle">Policy Number</td>
								<td class="flied-title" align="left" valign="middle"><form:input path="policyNumber" class="form-control"></form:input></td>
								

							</tr> --%>
							<tr>
								<td class="lable-title" align="left" valign="middle">Account Status</td>
								<td class="flied-title" align="left" valign="middle">
								<form:select path="status" class="down-control" >
								<form:option value="">All</form:option>
										<form:option value="0">Active</form:option>
										<form:option value="1" >Merged</form:option>
										<form:option value="2" >Pending</form:option>
										<form:option value="3" >Withdrawn</form:option>
									</form:select></td>
								
							<td class="lable-title" align="left" valign="middle">Insurer Type</td>
								<td class="flied-title" width="20%" align="left" valign="middle">

									<form:select path="insurerType" class="down-control"
										required="true">
										<form:option value="18" selected="selected">PrimaryNamedInsured</form:option>
										<form:option value="1" >BAPolicyContactRole</form:option>
										<form:option value="2" >BOPPolicyContactRole</form:option>
										<form:option value="3" >CPPolicyContactRole</form:option>
										<form:option value="4" >GLPolicyContactRole</form:option>
										<form:option value="5" >IMPolicyContactRole</form:option>
										<form:option value="6" >PAPolicyContactRole</form:option>
										<form:option value="7" >PlcyNonPriNamedInsured</form:option>
										<form:option value="8" >PolicyAddlInsured</form:option>
										<form:option value="9" >PolicyAddlInterest</form:option>
										<form:option value="10" >AdditionalNamedInsured</form:option>
										<form:option value="11" >PolicyBillingContact</form:option>
										<form:option value="12" >PolicyContactRole</form:option>
										<form:option value="13" >PolicyDriver</form:option>
										<form:option value="14" >PolicyLaborClient</form:option>
										<form:option value="15" >PolicyLaborContractor</form:option>
										<form:option value="16" >PolicyNamedInsured</form:option>
										<form:option value="17" >PolicyOwnerOfficer</form:option>
										<form:option value="19" >SecondaryNamedInsured</form:option>
										<form:option value="20" >WCLaborContact</form:option>
										<form:option value="21" >WCPolicyContactRole</form:option>
									</form:select>
								</td>
								<%-- <form:radiobutton
											path="gender" id="subscGender" value="M" />
										<spring:message code="label.male" /> <label
									class="radio-inline"> <form:radiobutton
											path="gender" id="subscGender" value="F" />
										<spring:message code="label.female" />
								</label> <label class="radio-inline"> <form:radiobutton
											path="gender" id="gender1" value="Both" />
										<spring:message code="label.both" />
								</label> --%>
								
							</tr>
							
							<tr>
								<td class="lable-title" align="left" valign="middle">Country</td>
								<td class="flied-title" align="left" valign="middle">
								<form:select path="country" id="country"
										class="down-control">
										<form:option value="">All</form:option>
										<c:forEach
											items="${pctlCountrydos}"
											var="pctlCountrydos">
											<form:option
												value="${pctlCountrydos.id}">${pctlCountrydos.name}</form:option>
										</c:forEach>
									</form:select></td>
								
							<td class="lable-title" align="left" valign="middle">State </td>
								<td class="flied-title" align="left" valign="middle">
								<form:select path="state" id="state"
										class="down-control">
										<form:option value="">All</form:option>
										<c:forEach
											items="${pctlStatedos}"
											var="pctlStatedos">
											<form:option
												value="${pctlStatedos.id}">${pctlStatedos.name}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
							<td class="lable-title" align="left" valign="middle">Gender</td>
							<td class="flied-title" align="left" valign="middle">
							<form:radiobutton
											path="gender" id="subscGender" value="1" />
										<spring:message code="label.male" /> <label
									class="radio-inline"> <form:radiobutton
											path="gender" id="subscGender" value="2" />
										<spring:message code="label.female" />
								</label> <label class="radio-inline"> <form:radiobutton
											path="gender" id="gender1" value="Both" />
										<spring:message code="label.both" />
								</label>
								</td>
							</tr>

							<%-- <tr>
								<td class="lable-title" align="left" valign="middle">Effective Date</td>
								<td class="flied-title" align="left" valign="middle"><form:input path="originalEffectvieDate" id="datepicker1" class="date-control datepicker" ></form:input></td>
								
							<td class="lable-title" align="left" valign="middle">Expire Date</td>
								<td class="flied-title" align="left" valign="middle"><form:input path="expireDate" id="datepicker2" class="date-control datepicker"></form:input></td>
							</tr> --%>
							
						</tbody>

					</table>

					<table
						style="width: 100%; border: 0; font-size: 13px; cellpadding: 4;">
						<tbody>
							<tr>
								<td colspan="4" align="center" valign="middle"><input
									type="submit" name="search" id="Search"
									class="btn-primary btn-cell"
									value="<spring:message code="button.serch"/>"> <input
									type="reset" value="Reset" class="btn-primary btn-cell"
									onClick="clearFields('./policyProp');"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<br />
				<br />				



				<c:if
					test="${tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO eq null || empty tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO}">

					<c:if test="${result ne null}">
						<table
							style="width: 100%; border: 0; font-size: 14px; font-style: italic; color: #7C6DC2; cellpadding: 4;">
							<tbody>
								<tr>
									<td class="lable-title" align="left" valign="middle">
										${result}</td>

								</tr>
								<tr>
										<td class="lable-title" align="left" valign="middle"><spring:message
												code="label.msg.noRec" /></td>
									</tr>
							</tbody>
						</table>
						<br />
						<br />
					</c:if>
				</c:if>
				
				<c:if
					test="${tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO ne null &&  not empty tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO}">
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
							<!-- 	<td class="lable-title" align="left" width="30%" valign="middle"><input
									type="submit" name="reserve" class="btn-primary btn-cell"
									id="reserve" value="Reserve"></td> -->
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
									<th bgcolor="#E3EFFB" height="25" class="whitefont">Policy Number</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Account Number</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Policy Type</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Insurer Type</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">First Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Last Name</th>
									
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Email Address1</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Email Address2</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Address Line1</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Address Line2</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Address Line3</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Original Effective Date</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Expiration Date</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Work Phone</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Country</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach
									items="${tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO}"
									var="listTdmPolicyCenterSearchResultDTO" varStatus="status">
									<tr>
										<c:if
											test="${listTdmPolicyCenterSearchResultDTO.reservedYN eq null }">
											<td><label class="checkbox-inline"> <form:checkbox
														path="listTdmPolicyCenterSearchResultDTO[${status.index}].reservedYN"
														id="listTdmPolicyCenterSearchResultDTO[${status.index}].reservedYN"
														class="cb_class checkBoxCls" value="Yes" />
											</label></td>
										</c:if>
										<%-- <form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].policynumber"/> --%>

										<td>${listTdmPolicyCenterSearchResultDTO.policynumber}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.accoutnumber}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.productType}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.insurerType}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.firstName}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.lastName}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.emailAddress}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.emailAddress2}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.addressLine1}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.addressLine2}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.addressLine3}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.originalEffectiveDate}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.expirationDate}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.workPhoneNo}</td>
										<td>${listTdmPolicyCenterSearchResultDTO.country}</td>

									</tr>
								</c:forEach>
								<%-- <c:forEach
									items="${tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO}"
									var="listTdmPolicyCenterSearchResultDTO" varStatus="status">

									<form:hidden
										path="listTdmPolicyCenterSearchResultDTO[${status.index}].policynumber" />
										</c:forEach> --%>

								<c:forEach
									items="${tdmPolicyCenterSearchDTO.listTdmPolicyCenterSearchResultDTO}"
									var="listTdmPolicyCenterSearchResultDTO" varStatus="status">

										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].policynumber"></form:hidden>
									    <form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].accoutnumber"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].productType"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].insurerType"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].firstName"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].lastName"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].emailAddress"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].emailAddress2"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].addressLine1"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].addressLine2"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].addressLine3"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].originalEffectiveDate"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].expirationDate"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].workPhoneNo"></form:hidden>
										<form:hidden path="listTdmPolicyCenterSearchResultDTO[${status.index}].country"></form:hidden>
									

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
						<li class="disable"><a href="policyProp?page=<%=noOfPages%>">&gt;&gt;</a>
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
								<th scope="col"><input
									type="submit" name="reserve" class="btn-primary btn-cell"
									id="reserve" value="Reserve">
									<input type="submit" name="export" id="export"
									class="btn-primary btn-cell" value="Export to Excel"></th>
							</tr>
						</tbody>
					</table>

				</c:if>
			</form:form>
		</div>
		<script src="include/footer.js"></script>
	</div>

	<script>
		menu_highlight('Policy_Property_Search');

		var checkboxes = $('.cb_class');

		$(document).ready(function() {
		 $('#addPayReq1').change(function() {
		    	$('#myErrorCls').next(".my-error-class").remove(); 
		  		if( $('#addPayReq1').attr("checked", "checked")) {
		  			$('#myErrorCls').after('<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
		  			 $('input[type="submit"]').attr('disabled','disabled');
		  		} else {
		  			$('input[type="submit"]').removeAttr('disabled');
		  			$('#myErrorCls').next(".my-error-class").remove(); 
		  		}
		    });
		 
			 $('#addPayReq2').change(function() {
		    	$('#myErrorCls').next(".my-error-class").remove(); 
		  		if( $('#addPayReq2').attr("checked", "checked")) {
		  			$('#myErrorCls').after('<div class="my-error-class">The functionality on "Associated Payment" is currently awaited for PAS to be updated to the latest version.</div>');
		  			 $('input[type="submit"]').attr('disabled','disabled');
		  		} else {
		  			$('input[type="submit"]').removeAttr('disabled');
		  			$('#myErrorCls').next(".my-error-class").remove(); 
		  		}
		    });
			 
			 $('#addPayReq3').change(function() {
			    	$('#myErrorCls').next(".my-error-class").remove(); 
			  		if( $('#addPayReq3').attr("checked", "checked")) {
			  			$('#myErrorCls').next(".my-error-class").remove(); 
			  	 		$('input[type="submit"]').removeAttr('disabled');
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
		 $("#addPayReq1").click(function () {			 
				toggle3('paymethodContent', $('#addPayReq1').val());					 
	     });
		 $("#addPayReq2").click(function () {			 
				toggle3('paymethodContent', $('#addPayReq2').val());					 
	     });
		 $("#addPayReq3").click(function () {			 
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
		 if($("#addPayReq1").is(":checked")){
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
