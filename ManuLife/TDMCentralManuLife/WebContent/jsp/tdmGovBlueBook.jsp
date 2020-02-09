<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<div class="container tdm-central">
				   <ol class="breadcrumb">
				    <li><a class="hrefVisited" href="./index"><spring:message code="label.home" /></a></li>
				    <li>&#x2f;</li>
				    <li><a class="hrefVisited" href="./indexGovn"><spring:message code="label.tdmgovernance" /></a></li>
				    <li>&#x2f;</li>
				    <li class="active"><spring:message code="label.gv.bluebook"/></li>
				  </ol>
				
				<form:form id="dataMaskingForm" name="dataMaskingForm" action="#">
	
				<h2 class="h2cls"><spring:message code="label.gv.cgtdm"/></h2>
				<hr>

				<p><spring:message code="label.gv.assestment"/></p>
				<p>
					<b><spring:message code="label.gv.initiation"/></b>
				</p>
				<ul>
					<li class="paln"><spring:message code="label.gv.tdmmaturity"/></li>
					<li class="paln"><spring:message code="label.gv.tdmaccelerated"/></li>
				</ul>
				<p>
					<b><spring:message code="label.gv.pilot"/></b>
				</p>
				<ul>
					<li class="paln"><spring:message code="label.gv.estimation"/></li>
				</ul>
				<p>
					<b><spring:message code="label.gv.rollout"/></b>
				</p>
				<ul>
					<li class="paln"><spring:message code="label.gv.datacreation"/></li>
					<li class="paln"><spring:message code="label.gv.trackmetrics"/></li>
				</ul>
				<p>
					<b><spring:message code="label.gv.operations"/></b>
				</p>
				<ul>
					<li class="paln"><spring:message code="label.gv.operationsphase"/></li>
				</ul>
				<h2 class="h2cls"><spring:message code="label.gv.benefits"/></h2>
				<hr>
				<p><spring:message code="label.gv.benefitssetting"/></p>
				<p><spring:message code="label.gv.improve"/></p>
				<p>
					<b><spring:message code="label.gv.benefitstdm"/></b>
				</p>
				<ul>
					<li class="paln"><spring:message code="label.gv.benefitstdm1"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm2"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm3"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm4"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm5"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm6"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm7"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm8"/></li>
					<li class="paln"><spring:message code="label.gv.benefitstdm9"/></li>
				</ul>
				<p>
					<b> <spring:message code="label.gv.benefitstdm10"/><a class="hrefVisited" href="#"><spring:message code="label.gv.bluebookdownload"/></a>
					</b>
				</p>
			</form:form>
			</div>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('tdm_governance1');
	</script>
</body>
</html>

 