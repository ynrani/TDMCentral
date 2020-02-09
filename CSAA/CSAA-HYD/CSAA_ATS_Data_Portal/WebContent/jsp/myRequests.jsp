
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<jsp:include page="headerNew.jsp"></jsp:include>
<!-- saved from url=(0014)about:internet -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - My Requests</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/adminDashboard.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/select.datatables.min.css">
<link rel="stylesheet" type="text/css" href="css/customDataTable.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style>
.container{
height:auto;
}
#requestTable_wrapper{
	margin-bottom:10px;
}
.tab-content{
	padding: 20px;
	margin-top: 10px;
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
						<!--<i class="fa fa-cogs"></i>
							  <h1>My Requests</h1>-->
						<ol class="breadcrumb">
							<li>Home</li>
							<li>User Panel</li>
							<li class="active">My Requests</li>
						</ol>
					</div>
				</div>

				<!-- Tab panes -->
				<div class="tab-content">
			
					<table id="requestTable" class="customDataTable requestsTable" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th>Request Number</th>
								<th>Requested On</th>
								<th>Status</th>
								<th>Subject</th>
								<th>Last Modified Date</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${displayUser}" var="item">
						  <c:set var="reqId" value="${item.requestId}"></c:set>
						  <c:set var="reqId1" value="${fn:substring(reqId, 0, 6)}"></c:set>
								<tr>
								  <c:if test = "${reqId1 eq 'DGMREQ'}">
									<td><a	href="${pageContext.request.contextPath}/requestDataByReqId?requestId=${item.requestId}&serviceIdentifier=myRequest">${item.requestId}</a></td>
									</c:if>
									 <c:if test = "${reqId1 eq 'DGAREQ'}">
									<td><a	href="${pageContext.request.contextPath}/requestDataAutoMation?requestId=${item.requestId}&serviceIdentifier=myRequest">${item.requestId}</a></td>
									</c:if>
									<td>${item.createdOn}</td>
									<td>${item.status}</td>
									<td>${item.subject}</td>
									<td>${item.lastModifiedDate}</td>
								</tr>
							</c:forEach>
							</tbody>
					</table>
					
				
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
	<script type="text/javascript" src="js/myRequests.js"></script>
	<script type="text/javascript" src="js/search-common.js"></script>
	<script type="text/javascript" src="js/stickyfooter.js"></script>
</body>
</html>