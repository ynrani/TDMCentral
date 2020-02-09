<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>L1 L2 Support</title>
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
<form:form id="autoEmailForm" name="autoEmailForm" action="./l1l2SendEmail"
	modelAttribute="autoEmailDTO">
	<table style="width: 80%; border: 0; font-size: 13px; cellpadding: 4;">
		<tbody>
			<tr>
				<td class="lable-title" align="left" valign="middle">To</td>
				<td class="flied-title" align="left" valign="middle"><form:input
						id="to" path="to" class="popUp-ctl email" readonly="true" /></td>
			<tr>
				<td class="lable-title" align="left" valign="middle">Cc</td>
				<td class="flied-title" align="left" valign="middle"><form:input
						id="cc" path="cc" class="popUp-ctl email" readonly="true" /></td>
			</tr>
			<tr>
				<td class="lable-title" align="left" valign="middle">Subject</td>
				<td class="flied-title" align="left" valign="middle"><form:input
						id="subject" path="subject" class="popUp-ctl email" readonly="true" /></td>
			</tr>
			<tr>
				<td class="lable-title" align="left" valign="middle">Message</td>
				<td class="flied-title" align="left" valign="middle"><form:textarea
						id="result" path="result" class="message" charCount="2000"  readonly="true"/></td>
			</tr>
			
			<tr>
			<td align="right"><input type="button" name="submit"
						onclick="sendEmail();"  value="Send Email" class="btn-primary btn-cell" />
		   </td>
		   <form:hidden path="to" />
		   <form:hidden path="subject" />
		   <form:hidden path="from" />
		   <form:hidden path="msg" />
		   <form:hidden path="cc" />
		   
			</tr>
		</tbody>
	</table>	
</form:form>
</body>
</html>
