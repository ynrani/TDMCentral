<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>ATS Data Central | Policy Auto Search</title>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/theme.default.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>

</head>

<body>


	<div id="main" class="wrapper mainAll">
		<!--  <script src="include/header.js"></script> -->
		<jsp:include page="headerPC.jsp"></jsp:include>
		<script src="include/menuPC.js"></script>
		<div id="tabs-1" class="container">
			<c:if test="${error ne null}">
				<table class="my-error-class">
					<tbody>
						<tr>
							<td class="lable-title" align="left" valign="middle">
								${error}</td>
						</tr>
					</tbody>
				</table>
			</c:if>
		</div>
		<div id="myErrorCls"></div>
		<div>
			<c:if test="${DB_STATUS ne null &&  not empty DB_STATUS}">
				<c:forEach items="${DB_STATUS}" var="dbStatus">

					<ul class="ulDBRefresh">
						<li>${dbStatus}</li>
					</ul>
				</c:forEach>
			</c:if>
			</
		</div>
	</div>


	<script src="include/footer.js"></script>

	<script src="include/copyrtfooter.js"></script>

</body>
</html>
