
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Portal | Display Users</title>
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
		<!-- <script src="include/indexHeader.js"></script> -->
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div id="tabs-1" class="container">
			<ol class="breadcrumb">
				<li><a class="hrefVisited" href="./index">Home</a></li>
				<li>/</li>
				<li><a class="hrefVisited" href="./indexCmdCtr">Command
						Center</a></li>
				<li>/</li>
				<li><a class="hrefVisited" href="./testdisplayAdmin">Admin</a></li>
				<li>/</li>
				<li class="active">User</li>
			</ol>
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
			<form:form>
				<c:choose>
					<c:when test="${!empty displayUser}">

						<%
							int currentPage = (Integer) request
												.getAttribute("currentPage");
										int count1 = currentPage - 1;
										count1 = count1 * 10;
						%>

						<table id="displayUsers" class="table tablesorter"
							style="width: 100%; font-size: 13px; border: 0; cellpadding: 0; cellspacing: 1">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">User
										Id</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">User
										Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Email
										Id</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Role</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Application Name
										</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">First Login Date
										</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Last Login Date
										</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Login Count
										</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${displayUser}" var="item">
									<tr>
										<td>${item.userId}</td>

										<td>${item.userName}</td>

										<td>${item.emailId}</td>
                                        <td><c:if
												test="${item.role == 'ROLE_ADMIN' }">
                                                 Admin
                                        </c:if> <c:if
												test="${item.role == 'ROLE_USER' }">
                                                 User
                                         </c:if></td>	
										<td>${item.applicationName}</td>

										<td>${item.firstLoginDate}</td>
										<td>${item.lastLoginDate}</td>
										<td>${item.loginCount}</td>
																			
									</tr>
								</c:forEach>

							</tbody>
						</table>


						<!-- Pagination Starts -->
						<ul class="grdPagination">
							<%
								int noOfPages = (Integer) request
													.getAttribute("noOfPages");
											int startPage = (Integer) request
													.getAttribute("startPage");
											int lastPage = (Integer) request
													.getAttribute("lastPage");

											if (currentPage != 1) {
							%>
							<li><a href="testdaAdmin?page=<%=1%>">First</a>
								<div>First</div></li>
							<li><a href="testdaAdmin?page=<%=currentPage - 1%>">&lt;
									Prev</a>
								<div>&lt; Prev</div> <%
 							} else {
 								if (noOfPages > 1) {
 							%>
							<li class="disable"><a href="testdaAdmin?page=<%=1%>">First</a>
								<div>First</div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%=currentPage - 1%>">&lt; Prev</a>
								<div>&lt; Prev</div> <%
 							}
 							}
 								if (noOfPages > 1) {
 							for (int i = startPage; i <= lastPage; i++) {
 							if (currentPage == i) {
 								%>
							<li class="active"><a href="#"><%=i%></a>
								<div><%=i%></div></li>
							<%
								} else {
							%>
							<li><a href="testdaAdmin?page=<%=i%>" id="employeeLink"></a>
								<div><%=i%></div></li>
							<%
								}
												}
											}
											if (currentPage < noOfPages) {
							%>
							<li><a href="testdaAdmin?page=<%=currentPage + 1%>">Next
									&gt;</a>
								<div>Next &gt;</div></li>
							<li><a href="testdaAdmin?page=<%=noOfPages%>">Last</a>
								<div>Last</div></li>
							<%
								} else {
												if (noOfPages > 1) {
							%>
							<li class="disable"><a
								href="testdaAdmin?page=<%=currentPage + 1%>">Next &gt;</a>
								<div>Next &gt;</div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%=noOfPages%>">Last</a>
								<div>Last</div></li>
							<%
								}
											}
							%>
						</ul>

						<!-- Pagination Ends -->

						

					</c:when>
					<c:otherwise>
						<h3 align="center">
							<font color="red">No USERS !</font>
						</h3>
						
					</c:otherwise>

				</c:choose>

			</form:form>
		</div>
		<script src="include/footer.js"></script>
	</div>
</body>
<script>
	menu_highlight('tdm_command_center1');

	
	$("#displayUsers").tablesorter({
		widgets : [ 'zebra' ]
	});
	$(".table tr:odd").css('background-color', '#ffffff');
	$(".table tr:even").addClass('even');
</script>
</html>