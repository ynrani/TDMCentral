<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Portal | Index</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="js/html5.js"></script>
<link href="css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container tdm-central">
				<ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index">Home</a></li>
					<li>/</li>
					<li class="active">TDM Portal Functions</li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Demand -->
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<diV class="thumbnail gutter demand">
						<h4>Engagement</h4>
						<p>Test Data Request Forms</p>
						<ul>
							<li><a href="./tdmDataMaskingNew">Masking Request</a></li>
							<li><a href="./tdmOnboardReq">TDM On-Boarding Request</a></li>
							<li><a href="./tdmChangeReqExt">Change Request</a></li>
						</ul>
					</diV>
					</security:authorize>
					<!-- /Thumbnail for Demand -->
					
					<!-- Thumbnail for Provision -->
				    <diV class="thumbnail gutter provision">
						<h4>Test Data Delivery</h4>
						<p>Publish Required Test Data To Testing Team To Carry Out
							Testing</p>
						<ul>
							<li><a href="./tdmNSSearch">Test Data Search and
									Reservation</a></li>
						</ul>
					</diV>	
					<!-- /Thumbnail for Provision -->
					<!-- Thumbnail for Design -->
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<diV class="thumbnail gutter design">
						<h4>Design</h4>
						<p>Design Required Test Data As Per Test Data Demand From
							Testing Team</p>
						<ul>
							<li><a href="./tdmEstimationTool">Estimation Tool</a></li>
							<li><a href="./tdpSensitiveProfiler">Sensitivity
									Profiler</a></li>
						</ul>
					</diV>
					</security:authorize>
					<!-- /Thumbnail for Design -->
					<!-- Thumbnail for Prepare -->
					<!-- <diV class="thumbnail gutter prepare">
						<h4>Prepare</h4>
						<p>Prepare required test data as per design and test data need
							from testing team</p>
						<ul>
							<li><a href="#">DMASSK</a></li>
						</ul>
					</diV> -->
					<!-- /Thumbnail for Prepare -->
				</div>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('tdm_life_cycle1');
	</script>
</body>
</html>
