<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Index</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
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
					<li><a class="hrefVisited"  href="./index"><spring:message code="label.home"/></a></li>
					<li>/</li>
					<li class="active"><spring:message code="label.commancenter" /></li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Demand -->
					<diV class="thumbnail gutter bluebook">
						<h4><spring:message code="label.live" /></h4>
						<p><spring:message code="label.lifecycleintegration"/></p>
						<ul>
							<li><a class="hrefVisited" href="http://in-pnq-coe05/qlikview/FormLogin.htm?opendocqs=%3Fdocument%3DTDM%2520Dashboard%2520V1.qvw%26host%3DQVS%2540in-pnq-coe05" TARGET="_NEW"><spring:message code="label.live"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Demand -->
					<!-- Thumbnail for Design -->
					<diV class="thumbnail gutter design">
						<h4><spring:message code="label.tracking"/></h4>
						<p><spring:message code="label.centralised"/></p>
						<ul>
							<li><a href="./tdmDtMaskDashboard"><spring:message code="label.mydashboard"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Design -->

					<security:authorize access="hasRole('ROLE_ADMIN')">
						<!-- Thumbnail for Admin -->
						<diV class="thumbnail admin">
							<h4><spring:message code="label.admin"/></h4>
							<p><spring:message code="label.adminusage"/></p>
							<ul>
								<li><a href="${pageContext.request.contextPath}/testdisplayAdmin"><spring:message code="label.managemore"/></a></li>
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
