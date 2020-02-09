
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="headerNew.jsp"></jsp:include>
<!DOCTYPE html>
  <!-- saved from url=(0014)about:internet -->
 <html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ATS Data Central - Service Desk</title>
	  <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="images/favicon.ico" >	
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	 <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="css/stylesNew.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/adminDashboard.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
  </head>
  <body>
  <main class="main-style"> 
  <div class="container">
	<div class="request-tab">
		<div class="main-content">
			<div class="request-header">
				<div class="csa-header-bar"></div>
				<div class="page-heading">
					<!--<i class="fa fa-cogs"></i>
					<h1>Admin Dashboard</h1>-->
					<ol class="breadcrumb">
					  <li>User Panel</li>
					  <li class="active">Service Desk</li>
					</ol>
				</div>
			</div>

			  <!-- Tab panes -->
			<div class="tab-content">
				<table id="dashboardTable" class="customDataTable" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th></th>
							<th>Request Number</th>
							<th>Service Type</th>
							<th>Subject</th>
							<th>Status</th>
							<th>Assigned To</th>
							<th>Requested By</th>
							<th>Cunsumer Group</th>
							<th>Priority</th>
							<th>Requested On</th>
							<th>Expected Date</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
  </div>
  </main>
  <footer>
	  <div class="container-fluid">
		  <div class="row">
			  <div class="col-xs-12">
				  <div class="nav-links">
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
					  <div class="copyright">&copy;2016 CSAA Insurance Group.</div>
				  </div>
			  </div>
		  </div>
	  </div>
  </footer>
   <script src="js/landing.js"></script>
  <script type="text/javascript" src="js/adminDashboard.js"></script>
</body>
</html>