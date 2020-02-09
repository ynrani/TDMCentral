
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="headerNew.jsp"></jsp:include>
<!DOCTYPE html>

<!-- saved from url=(0014)about:internet -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - My Auto Reservations</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/adminDashboard.css">
<link rel="stylesheet" type="text/css" href="css/customDataTable.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style>
.tab-content
{
padding:20px;
margin-top: 0px;
margin-bottom: 20px;
}
.customDataTable > tbody > tr > td:first-child
{
	border-left: 1px solid #ccc;
}
</style>
</head>
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
							<li>User Panel</li>
							<li class="active">My Reservations</li>
						</ol>
					</div>
				</div>
				<ul class="nav nav-tabs" role="tablist" id="request-tab">
					<li role="presentation" class="auto-search active">

						<a href="#"><i
							class="fa fa-car csaa-vectors"></i> Policy - Auto
							Reservations</a>
					</li>
					<li role="presentation" class="policy-search">

						<a id="propReserve" href="./myReservationProp"><i
							class="fa fa-home fa-lg csaa-vectors"></i> Policy - Property
							Reservations</a>
					</li>

				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div class="dataTables_wrapper" id="autoReservetableWrapper">
						<form:form id="requestDataForm" name="requestDataForm"
							action="${pageContext.request.contextPath}/myReservationAuto"
							modelAttribute="tdmAutoPropertySearchDTO">
							<table id="reservationTable" class="customDataTable"
								cellspacing="0" width="100%">
								<thead>
									<tr>
									    <th></th>
										<th class="sorting sorting_asc">Action</th>
										<th class="sorting">Policy<br />Number
										</th>
										<th class="sorting">Expires<br />On
										</th>
										<th class="sorting">Policy<br />Status
										</th>
										<th class="sorting">Risk<br />State
										</th>
										<th class="sorting">Policy<br />Term
										</th>
										<th class="sorting">Effective<br />Date
										</th>
										<th class="sorting">Number of<br />Drivers
										</th>
										<th class="sorting">Number of<br />Vehicles
										</th>
										<th class="sorting">Number of<br />Violations
										</th>
										<th class="sorting">Test <br />Case Id
										</th>
									</tr>
								</thead>
								<c:forEach items="${tdmPolicyAutoSearchResultDTOList}"
									var="tdmPolicyAutoSearchResultDTOList" varStatus="status">
									<tr class="${status.index % 2 == 0 ? 'even' : 'odd'}">
									    <td><input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/></td>
										<td><a class="hrefVisited"
											href="${pageContext.request.servletContext.contextPath}/unreserveAuto?id=${tdmPolicyAutoSearchResultDTOList.policynumber}"><spring:message
													code="label.unreserve" /></a>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.policynumber}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.expairDate}</td>
										<%-- <td align="center">${tdmPolicyPropertySearchResultDTOList.productType}</td> --%>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.policyStage}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.policyState}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.policyTerm}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.policyEffectDt}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.noOfDrivers}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.noOfVehi}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.noOfViola}</td>
										<td align="center">${tdmPolicyAutoSearchResultDTOList.testCaseId}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<input type="submit" id="export" value="Export All to Excel"
								name="export" class="btn pull-right common-btn-style" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<footer class="footer home-footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="nav-links">
						<div class="copyright">&copy;2016 CSAA Insurance Group.</div>
						<ul>
							<li><a href="./governance#gettingStarted">About Us</a></li>
							<li><a href="./contactus">Contact Us</a></li>
							<li><a href="javascript:void(0)">Site Map</a></li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</footer>
	<script src="js/landing.js"></script>
	<script type="text/javascript" src="js/myReservationsAuto.js"></script>
	<script type="text/javascript" src="js/search-common.js"></script>
	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html>