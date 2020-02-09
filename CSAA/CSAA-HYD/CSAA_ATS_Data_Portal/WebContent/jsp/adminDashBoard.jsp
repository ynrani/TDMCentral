
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="headerNew.jsp"></jsp:include>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ATS Data Central - Admin Dashboard</title>
	  <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="images/favicon.ico" >	
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/adminDashboard.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">
	<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

    <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
	<style>
	.customDataTable thead td, .customDataTable tbody td
	{
		font-size:13px !important;
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
					  	<li>Admin</li>
					  	<li class="active">Service Desk</li>
					</ol>
				</div>
			</div>

			  <!-- Tab panes -->
			<div class="tab-content">
					<table id="dashboardTable" class="customDataTable" width="100%">
						<thead>
							<tr>
								<th></th>
								<th>Request Number</th>
								<th>Service Type</th>
								<th>Subject</th>
								<th>Status</th>
								<th>Assigned To</th>
								<th>Requested By</th>
								<th>Consumer Group</th>
								<th>Priority</th>
								<th>Requested On</th>
								<th>Expected Date</th>
							</tr>
						</thead>
						<tbody>
				<c:choose>
						<c:when test="${!empty dashboardReqList}">
							<c:forEach items="${dashboardReqList}" var="request" varStatus="loopStatus">
							<c:set var="reqId" value="${request.requestId}"></c:set>
							 <c:set var="reqId1" value="${fn:substring(reqId, 0, 6)}"></c:set>
								 <tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
								  
									<td><input type="checkbox" id="1"/> </td>
									<td>
									<c:if test = "${reqId1 eq 'DGMREQ'}">
									<a	href="${pageContext.request.contextPath}/requestDataByReqId?requestId=${request.requestId}&serviceIdentifier=serviceDesk">${request.requestId}</a>
									</c:if>
									 <c:if test = "${reqId1 eq 'DGAREQ'}">
									<a	href="${pageContext.request.contextPath}/requestDataAutoMation?requestId=${request.requestId}&serviceIdentifier=serviceDesk">${request.requestId}</a>
									</c:if>
									</td>
									<td>${request.serviceType}</td>
									<td>${request.subject}</td>
									<td>${request.status}</td>
									<td>${request.assignedToId}</td>
									<td>${request.requestedBy}</td>
									<td>${request.consumerGroupName}</td>
									<td>${request.priorityName}</td>
									<td>${request.createdOn}</td>
									<td>${request.expectedDate}</td>
								</tr>
							</c:forEach>
						</c:when>
					<c:otherwise>
						<h3 align="center">
							<font color="red">No Records found.</font>
						</h3>
					</c:otherwise>

				</c:choose>
						</tbody>
						
					</table>
				</div>
		</div>
	</div>
  </div>
  </main>
 
   <div class="bottomNew">
  <footer class="footer home-footer">
	  <div class="container-fluid">
		  <div class="row">
			  <div class="col-xs-12">
				  <div class="nav-links">
					  <div class="copyright">&copy;2016 CSAA Insurance Group.</div>
					  <ul>
						  <li>
							  <a href="./governance#gettingStarted">About Us</a>
						  </li>
						  <li>
							  <a href="./contactus">Contact Us</a>
						  </li>
						  <li>
							  <a href="javascript:void(0)">Site Map</a>
						  </li>
					  </ul>

				  </div>
			  </div>
		  </div>
	  </div>
  </footer>
  </div>
 
   <script src="js/landing.js"></script>
  <script type="text/javascript" src="js/adminDashboard.js"></script>
  <script type="text/javascript" src="js/search-common.js"></script>
  <script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html>