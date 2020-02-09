<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><spring:message code="label.application"/> | <spring:message code="label.index"/></title>
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
</head>
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container tdm-central">
				<ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index"><spring:message code="label.home"/></a></li>
					<li>/</li>
					<li class="active"><spring:message code="label.tdmlifecycle"/></li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Demand -->
					<diV class="thumbnail gutter demand">
						<h4><spring:message code="label.demand"/></h4>
						<p><spring:message code="label.demandlabel"/></p>
						<ul>
							<li><a href="./tdmDataMaskingNew"><spring:message code="label.maskingrequest"/></a></li>
							<li><a href="./tdmOnboardReq"><spring:message code="label.onboarding"/></a></li>
							<li><a href="./tdmChangeReqExt"><spring:message code="label.changerequest"/></a></li>
							<li><a href="./tdmDataSubsetting"><spring:message code="label.subsetrequest"/></a></li>
							<li><a href="./tdmDataRefresh"><spring:message code="label.datarefresh"/></a></li>
						</ul>
						<div class="bottom-arrow"></div>
					</diV>
					<!-- /Thumbnail for Demand -->
					<!-- Thumbnail for Design -->
					<diV class="thumbnail gutter design">
						<h4><spring:message code="label.design"/></h4>
						<p><spring:message code="label.designlabel"/></p>
						<ul>
							<li><a href="./tdmEstimationTool"><spring:message code="label.estimationtool"/></a></li>
							<li><a href="http://in-pnq-bid01/DMASSK/" TARGET="_NEW"><spring:message code="label.sensitiverprofile"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Design -->
					<!-- Thumbnail for Prepare -->
					<diV class="thumbnail gutter prepare">
						<h4><spring:message code="label.prepare"/></h4>
						<p><spring:message code="label.preparelabel"/></p>
						<ul>
							<li><a href="#"><spring:message code="label.demask"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Prepare -->
					<!-- Thumbnail for Provision -->
					<diV class="thumbnail gutter provision">
						<h4><spring:message code="label.provision"/></h4>
						<p><spring:message code="label.provisionlabel"/></p>
						<ul>
							<li><a href="./policyProp"><spring:message code="label.ftdlabel"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Provision -->



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
