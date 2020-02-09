<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ATS Data Central - Registration</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="lib/datetimepicker/bootstrap-datetimepicker-standalone.min.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>

</head>


<link rel="stylesheet" href="css/login.css">
<style>
.btn-inside-input
{
	float:right;
}
.flex-container
{
	display:flex;
	
}
.flex-input
{
	flex:1;	
}
.avail-msg-style
{
	color:red;
	font-size:11px;
	position: relative;
	margin-left: 18.5%;
	height:15px;
	margin-top: -8px;
	margin-bottom: 5px;
}
input[disabled] {
background: rgb(51, 122, 183) !important;
}
</style>

</head>
<body>
	<div class="modal-body">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h2 class="new-user">ATS Data Central Access Request</h2>
		<form:form class="form-horizontal" id="registrationForm"
			name="registrationForm"
			action="${pageContext.request.contextPath}/register" method="POST"
			autocomplete="on" modelAttribute="userDetailsDTO">
			<div class="form-group">
				<label for="fullName" class="col-sm-2">User Full Name<span
					class="login-error">*</span></label>
				<div class="col-sm-10">
					<form:input path="userName" class="form-control" id="userName"
						placeholder="User Full Name" required="required" />
				</div>
			</div>
			

			<div class="form-group">
				<label for="userId" class="col-sm-2">User ID<span
					class="login-error">*</span></label>
				<div class="col-sm-10 flex-container">
					<form:input path="userId" class="form-control mandetCls2 flex-input"
						id="userId" placeholder="User Id" required="required" />
				</div>
				
			</div>
			<div id='username_availability_result' style='display:none;' class="avail-msg-style"></div>

			<div class="form-group">
				<label for="emailIdAdd" class="col-sm-2">Email Address<span
					class="login-error">*</span></label>
				<div class="col-sm-10">
					<form:input type="input" path="userEmail" class="form-control"
					
						id="userEmail" placeholder="Email Address" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required="required" />	
				</div>
			</div>
			<div id='email_availability_result' style='display:none;' class="avail-msg-style"></div>
			
			<div class="form-group">
				<label for="ddlConsumerGroup" class="col-sm-2">Consumer
					Group<span class="login-error">*</span>
				</label>
				<div class="col-sm-10">
					<form:select path="consumerGroup" class="form-control"
						id="consumerGroup" required="required">

						<form:option value="selected">--Select--</form:option>

						<form:option value="1">PAS</form:option>
						<form:option value="2">CAS</form:option>
						<form:option value="3">Digital Services</form:option>
						<form:option value="4">MDM</form:option>
						<form:option value="5">SOA</form:option>
						<form:option value="6">Automation</form:option>
						<form:option value="7">Performance</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="managerId" class="col-sm-2">Manager <span
					class="login-error">*</span></label>
				<div class="col-sm-10">
					<form:input path="manager" class="form-control" id="manager"
						placeholder="Manager Name" required="required" />
				</div>
			</div>
			<div class="form-group">
				<label for="reasonid" class="col-sm-2">Reason for Access<span
					class="login-error">*</span></label>
				<div class="col-sm-10">
					<form:input path="accessReason" class="form-control"
						id="accessReason" placeholder="Reason for Access"
						required="required" />
				</div>
			</div>
			<div class="reg-btn-container">
				<input type="submit" id="submitReq" class="btn btn-primary" disabled="true"
					value="Register" />
						<button id="cnclId" type="button" class="btn btn-primary" 
					data-dismiss="modal">Cancel</button>

			</div>
		</form:form>
	</div>

	<script>
		//validateRegestorForm();
		
	</script>

<script type="text/javascript" src="js/register.js"></script>


</body>
</html>




