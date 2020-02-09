<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<jsp:include page="headerNew.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Request Activity Log</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/custom.ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/select.datatables.min.css">
	<link rel="stylesheet" type="text/css"
	href="css/customDataTable.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style>
.main-style
{
	margin-bottom:0px !important;
}
.tab-content
{
	background-color:white;
}
</style>
</head>
<body>
<div class="loaderDiv" style='display: none;'></div>
<%String requestId=(String)request.getSession().getAttribute("reqId");
  %>
  <main class="main-style"> 
	<div class="container">
		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="csa-header-bar"></div>
					<div class="page-heading">
						
						<ol class="breadcrumb">
						   <li>Home</li>
							<li>Data Services</li>
							<li class="active">Data Generation</li>
						</ol>
					</div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs data-gen-tabs" role="tablist" id="request-tab">
						<li role="presentation" >
						<a id="dataCreationLink" href="./requestDataAutoMation?requestId=${requestId}&serviceIdentifier=activityLog"><i class="fa fa-pencil-square-o fa-lg csaa-vectors"></i>Data
								Request
								
						</a></li>
						<li role="presentation" class="active"><a data-target="#history"
							 role="tab" data-toggle="tab"> <i
								class="fa fa-history fa-lg csaa-vectors"></i>Request Activity Log
						</a></li>						 
					</ul>

				</div>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="history">
						<div class="content-section">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12">
										<div id="historyDetails"
											class="table-parent reser-details history-details">
											<div class="table-parent">
												<table id="historyTable" class="customDataTable"
													cellspacing="0" width="100%">
													<thead>
														<tr>
														<th class="sorting sorting_asc">Request Number</th>
															<th class="sorting">Date</th>
															<th class="sorting">Modified by</th>
															<th class="sorting">Status</th>
															<th class="sorting">Description</th>
														</tr>
													</thead>

													<tbody>
												
														<c:forEach items="${accessLog}" var="acclog" varStatus="loopStatus">
													<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
																<td>${acclog.requestId}</td>
																<td>${acclog.modifiedDate}</td>
																<td>${acclog.modifiedBy}</td>
																<td>${acclog.requestStatus}</td>
																<td>${acclog.comments}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	
		</main>
		<jsp:include page="footerNew.jsp"></jsp:include>
		<script src="js/landing.js"></script>
		   <script type="text/javascript" src="js/history.js"></script>
</body>
</html>