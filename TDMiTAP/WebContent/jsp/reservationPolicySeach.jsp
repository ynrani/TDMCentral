<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | My Reservation Record</title>
<link rel="favicon" type="image/ico" href="images/favicon.ico">
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
		<!-- <script src="include/header.js"></script> -->
		<jsp:include page="header.jsp"></jsp:include>
		<script src="include/revMenu.js"></script>

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
			<form:form id="reservationPolicyDataForm"
				name="reservationPolicyDataForm"
				action="${pageContext.request.contextPath}/myReservationExportPolicySearch">
				<c:choose>
					<c:when test="${tdmPolicySearchResultDTOs ne null}">

						<%
									int currentPage = (Integer) request.getAttribute("currentPage");
									int count1 = currentPage - 1;
									count1 = count1 * 10;
					 
								%>
						<div id="tabs-1" class="container scrollingX">
							<table class="table outputtable"
								style="width: 100%; border: 0; cellpadding: 0; cellspacing: 1;">
								<thead>
									<tr>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.action" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.expOn" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcId" /></th>
										<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message
												code="label.tcName" /></th>
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
									<c:forEach items="${tdmPolicySearchResultDTOs}"
										var="tdmPolicySearchResultDTOs" varStatus="status">
										<tr>
											<td><a
												href="${pageContext.request.servletContext.contextPath}/unreservePolicy?id=${tdmPolicySearchResultDTOs.policynumber}"><spring:message
														code="label.unreserve" /></a>
											<td align="center">${tdmPolicySearchResultDTOs.revExpairDate}</td>
											<td align="center">${tdmPolicySearchResultDTOs.testCaseId}</td>
											<td align="center">${tdmPolicySearchResultDTOs.testCaseName}</td>
										<td align="center">${tdmPolicySearchResultDTOs.policynumber}</td>
										<td align="center">${tdmPolicySearchResultDTOs.accoutnumber}</td>
										<td align="center">${tdmPolicySearchResultDTOs.productType}</td>
										<td align="center">${tdmPolicySearchResultDTOs.insurerType}</td>
										<td align="center">${tdmPolicySearchResultDTOs.firstName}</td>
										<td align="center">${tdmPolicySearchResultDTOs.lastName}</td>
										<td align="center">${tdmPolicySearchResultDTOs.emailAddress}</td>
										<td align="center">${tdmPolicySearchResultDTOs.emailAddress2}</td>
										<td align="center">${tdmPolicySearchResultDTOs.addressLine1}</td>
										<td align="center">${tdmPolicySearchResultDTOs.addressLine2}</td>
										<td align="center">${tdmPolicySearchResultDTOs.addressLine3}</td>
										<td align="center">${tdmPolicySearchResultDTOs.originalEffectiveDate}</td>
										<td align="center">${tdmPolicySearchResultDTOs.expirationDate}</td>
										<td align="center">${tdmPolicySearchResultDTOs.workPhoneNo}</td>
										<td align="center">${tdmPolicySearchResultDTOs.country}</td>
										
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
							<li><a href="myReservationPolicy?page=<%= 1 %>">First</a>
							<div>First</div></li>
							<li><a href="myReservationPolicy?page=<%= currentPage-1 %>">&lt;
									Prev</a>
							<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
							<li class="disable"><a
								href="myReservationPolicy?page=<%= 1 %>">First</a>
							<div>First</div></li>
							<li class="disable"><a
								href="myReservationPolicy?page=<%= currentPage-1 %>">&lt;
									Prev</a>
							<div>&lt; Prev</div> <%
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
							<li><a href="myReservationPolicy?page=<%= i %>"
								id="employeeLink"><%= i %></a>
							<div><%= i %></div></li>
							<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
							<li><a href="myReservationPolicy?page=<%= currentPage+1 %>">Next
									&gt;</a>
							<div>Next &gt;</div></li>
							<li><a href="myReservationPolicy?page=<%= noOfPages %>">Last</a>
							<div>Last</div></li>
							<%
											} else {
											    if(noOfPages > 1) {
										%>
							<li class="disable"><a
								href="myReservationPolicy?page=<%= currentPage+1 %>">Next
									&gt;</a>
							<div>Next &gt;</div></li>
							<li class="disable"><a
								href="myReservationPolicy?page=<%= noOfPages %>">Last</a>
							<div>Last</div></li>
							<%
											    }
											}
										%>
						</ul>

						<!-- Pagination Ends -->

						<table style="width: 100%; border: 0">
							<tbody>
								<tr>
									<th scope="col"><input type="button" name="back" id="back"
										class="btn-primary btn-cell" value="Back"> <input
										type="submit" name="export" id="export"
										class="btn-primary btn-cell" value="ExportAll to Excel">
									</th>
								</tr>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<h3
							style="float: left; width: 40%; border: 0; font-size: 14px; color: #E74949; padding-top: 15px">
							<div>No Test Data Reserved - Policys</div>
						</h3>
						<br />
						<table style="width: 100%; border: 0">
							<tbody>
								<tr>
									<th scope="col"><input type="button" name="back" id="back"
										class="btn-primary btn-cell" value="Back"></th>
								</tr>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</form:form>

		</div>
		<script src="include/footer.js"></script>
	</div>
	<script>
  	menu_highlight('PolicySearch_Rev');
  	
   
    $(".outputtable").tablesorter({
      widgets: ['zebra']
    });
    
    
    $(document).ready(function() {
        $("#back").click(function(){
           	document.location.href="./policyProp";
      	  });
      });
    
     
  </script>
</body>

</html>