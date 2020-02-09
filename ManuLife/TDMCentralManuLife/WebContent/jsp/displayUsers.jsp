<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Display Users</title>
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
		<!-- <script src="include/indexHeader.js"></script> -->
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div id="tabs-1" class="container">
			<ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index"><spring:message code="label.home" /></a></li>
					<li>/</li>
					<li><a class="hrefVisited" href="./indexCmdCtr"><spring:message code="label.commancenter" /></a></li>
					<li>/</li>
					<li><a class="hrefVisited" href="./testdisplayAdmin"><spring:message code="label.admin" /></a></li>
					<li>/</li>
					<li class="active"><spring:message code="label.user" /></li>
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
			<form:form
				action="${pageContext.request.servletContext.contextPath}/tesdaCreateNewUser">
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
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.userid" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.username" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.active" />?</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.emailid" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.mobileno" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.role" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.editaction" /></th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.deleteaction" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${displayUser}" var="item">
									<tr>
										<td>${item.userId}</td>

										<td>${item.username}</td>

										<td>${item.enabled}</td>

										<td>${item.emailId}</td>

										<td>${item.mobileNo}</td>
										<td><c:if
												test="${item.tdmUserAuthDTO.role == 'ROLE_ADMIN' }">
                                                 <spring:message code="label.admin" />
                                      </c:if> <c:if
												test="${item.tdmUserAuthDTO.role == 'ROLE_USER' }">
                                                 <spring:message code="label.user" />
                                         </c:if></td>
										<td><a
											href="${pageContext.request.contextPath}/editUser?userId=${item.userId}"><spring:message code="label.edit" /></a></td>
										<td><a href="#"
											onClick="getDeleteUsers('${item.userId}')"><spring:message code="label.delete" /></a></td>
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
							<li><a href="testdaAdmin?page=<%=1%>"><spring:message code="label.first" /></a>
							<div><spring:message code="label.first" /></div></li>
							<li><a href="testdaAdmin?page=<%=currentPage - 1%>">&lt;
									<spring:message code="label.prev" /></a>
							<div>&lt; <spring:message code="label.prev" /></div> <%
 							} else {
 								if (noOfPages > 1) {
 							%>
							<li class="disable"><a href="testdaAdmin?page=<%=1%>"><spring:message code="label.first" /></a>
							<div><spring:message code="label.first" /></div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%=currentPage - 1%>">&lt; <spring:message code="label.prev" /></a>
							<div>&lt; <spring:message code="label.prev" /></div> <%
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
							<li><a href="testdaAdmin?page=<%=i%>" id="employeeLink"><%=i%></a>
							<div><%=i%></div></li>
							<%
								}
												}
											}
											if (currentPage < noOfPages) {
							%>
							<li><a href="testdaAdmin?page=<%=currentPage + 1%>"><spring:message code="label.next" />
									&gt;</a>
							<div><spring:message code="label.next" /> &gt;</div></li>
							<li><a href="testdaAdmin?page=<%=noOfPages%>">Last</a>
							<div><spring:message code="label.last" /></div></li>
							<%
								} else {
												if (noOfPages > 1) {
							%>
							<li class="disable"><a
								href="testdaAdmin?page=<%=currentPage + 1%>"><spring:message code="label.next" /> &gt;</a>
							<div><spring:message code="label.next" /> &gt;</div></li>
							<li class="disable"><a
								href="testdaAdmin?page=<%=noOfPages%>"><spring:message code="label.last" /></a>
							<div><spring:message code="label.last" /></div></li>
							<%
								}
											}
							%>
						</ul>

						<!-- Pagination Ends -->



						<table
							style="width: 50%; border: 0; font-size: 13px; cellpadding: 4;">
							<tbody>
								<tr>
									<td colspan="4" align="center" valign="middle"><input
										type="submit" name="newUser" id="newUser"
										class="btn-primary btn-cell" value="Create New User">
									</td>
								</tr>
							</tbody>
						</table>

					</c:when>
					<c:otherwise>
						<h3 align="center">
							<font color="red"><spring:message code="label.usernotexist"/></font>
						</h3>

						<table
							style="width: 50%; border: 0; font-size: 13px; cellpadding: 4;">
							<tbody>
								<tr>
									<td colspan="4" align="center" valign="middle"><input
										type="submit" name="newUser" id="newUser"
										class="btn-primary btn-cell" value="<spring:message code="label.createnewuser"/>">
									</td>
								</tr>
							</tbody>
						</table>
					</c:otherwise>

				</c:choose>

			</form:form>
		</div>
		<script src="include/footer.js"></script>
	</div>
</body>
<script>
	menu_highlight('tdm_command_center1');

	function getDeleteUsers(userId) {
		if (confirm('Are you sure to delete the user?')) {
			document.location.href = "./deleteUser?userId=" + userId;
		}
	}
	$("#displayUsers").tablesorter({
		widgets : [ 'zebra' ]
	});
	$(".table tr:odd").css('background-color', '#ffffff');
	$(".table tr:even").addClass('even');
</script>
</html>