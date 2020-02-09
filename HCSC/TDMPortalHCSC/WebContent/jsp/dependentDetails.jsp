<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dependent Details</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link href="css/theme.default.css" rel="stylesheet">
<link href="css/elements.css" rel="stylesheet">
<script src="js/html5Shiv.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/messages.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	
		<div class="dependents_scrollingX" >
			<c:choose>
			 <c:when test="${depenDetailsList ne null}">			 
			 <table id="search_output_table" class="table tablesorter"
							style="width: 100%; font-size: 10px;">
							<thead>
								<tr>
									<th bgcolor="#E3EFFB"  class="whitefont">Subscriber ID</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">First Name</th>
									<th bgcolor="#E3EFFB"  class="whitefont">Last Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Date Of Birth</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Gender</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Relationship</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Relationship Name</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Relationship Code</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Dependent Status</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">DPT Effective Date</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">DPT End Date</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="dependentDetailsDTOList" items="${depenDetailsList}" varStatus="status">
							<tr>							     
									<td style="font-size: 10px;">${dependentDetailsDTOList.subId}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.firstName}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.lastName}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.dob}</td>	
									<td style="font-size: 10px;">${dependentDetailsDTOList.gender}</td>	
									<td style="font-size: 10px;">${dependentDetailsDTOList.relationShip}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.relationShipName}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.relationShipCode}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.depStatus}</td>	
									<td style="font-size: 10px;">${dependentDetailsDTOList.deptEffDate}</td>
									<td style="font-size: 10px;">${dependentDetailsDTOList.deptEndDate}</td>							
								</tr>
							</c:forEach>								
							</tbody>
						</table>
			 </c:when>
			 <c:otherwise>
			 <table	style="width: 100%; border: 0; font-size: 12px; color: #EC0B2D; cellpadding: 4;">
							<tbody>
								<tr>
									<td class="lable-title" align="left" valign="middle"> No dependent details found.</td>
								</tr>
							</tbody>
						</table>
			 		<p>	</p>
			 
			 </c:otherwise>			
			</c:choose>
			
		</div>
	<script type="text/javascript">
	
	$("#search_output_table").tablesorter({
    widgets: ['zebra']
  });
  $(".table tr:odd").css('background-color', '#ffffff');
  $(".table tr:even").addClass('even');
	</script>
</body>
</html>