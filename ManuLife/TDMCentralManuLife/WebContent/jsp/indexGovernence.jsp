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
					<li><a class="hrefVisited" href="./index"><spring:message code="label.home" /></a></li>
					<li>/</li>
					<li class="active"><spring:message code="label.tdmgovernance" /></li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Design -->
					<diV class="thumbnail gutter bluebook">
						<h4><spring:message code="label.gv.bluebook"/></h4>
						<p><spring:message code="label.gv.cenbluebook"/></p>
						<ul>
							<li><a href="./blueBookPage"><spring:message code="label.gv.bluebook"/></a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Design -->

					<div class="clearfloat">&nbsp;</div>
				</div>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('tdm_governance1');
	</script>
</body>
</html>
