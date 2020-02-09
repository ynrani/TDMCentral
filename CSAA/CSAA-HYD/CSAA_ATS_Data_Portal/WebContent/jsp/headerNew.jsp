<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Home</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript"
	src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
</head>

<body>

<script>
	var userRole ='<%=(String) request.getSession().getAttribute("ROLE")%>';
</script>
	<header class="main-header">
		<div class="row">
			<div class="col-xs-2">
				<div class="logo" title="Home">
					<a href="javascript:void(0)"></a>
				</div>
			</div>
			<div class="col-xs-10">
				<nav class="ats-main-nav">
					<ul class="pull-right">

						<li><span> <%
						
	                       if (session.getAttribute("UserName") != null
							&& session.getAttribute("LAST_LOGIN")!=null) {
						out.println("Welcome "+ session.getAttribute("UserName") + "  Last login  "+session.getAttribute("LAST_LOGIN"));
						
					} else if (session.getAttribute("UserName") != null
							&& session.getAttribute("LAST_LOGIN")== null) {
						out.println("Welcome "+ session.getAttribute ("UserName"));
						}
					     %>
				    
						</span></li>
						

						<li><a href="javascript:void(0)">ATS Data Support</a></li>
						<li><a href="./logout?logout=true" id="logout" class ="fa fa-sign-out">Logout</a></li>
					</ul>
				</nav>
				<div class="ats-sub-nav">
					<div class="container-fluid">
						<div class="row">
							<div class="page-menu">
								<div class="col-xs-11">
									<nav class="other-nav land-nav"></nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
</body>
</html>
