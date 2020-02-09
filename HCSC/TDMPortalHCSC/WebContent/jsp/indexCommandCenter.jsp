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
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container tdm-central">
				<ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index">Home</a></li>
					<li>/</li>
					<li class="active">Command Center</li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Demand -->
					<diV class="thumbnail gutter bluebook">
						<h4>Metrics</h4>
						<p>Provides various metrics such as Test Data Effectiveness and Efficiency.</p>
						<ul>
							<li><a class="hrefVisited" href="./tdmCmdCenterMetrics">Metrics</a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Demand -->
					<!-- Thumbnail for Design -->
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<diV class="thumbnail gutter design">
						<h4>Tracking</h4>
						<p>Centralized Dashboard For Request Tracking</p>
						<ul>
							<li><a href="./tdmDtMaskDashboard">My Dashboard</a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Design -->

					
						<!-- Thumbnail for Admin -->
						<diV class="thumbnail admin">
							<h4>Admin</h4>
							<p>TDM Admin To Use This Option For Access Management And
								Auto Scheduling</p>
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/testdisplayAdmin">Manage
										More</a></li>
							</ul>
						</diV>
						<!-- /Thumbnail for Admin -->
					</security:authorize>
					<div class="clearfloat">&nbsp;</div>
				</div>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('tdm_command_center1');
	</script>
</body>
</html>
