<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0014)about:internet -->
<html>
<head>
<jsp:include page="headerNew.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Request Activity Log</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="css/jquery-te.css">

<link rel="stylesheet" type="text/css" href="css/style-footer.css">
<link rel="stylesheet" type="text/css" href="css/demo-footer.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
<link rel="stylesheet" type="text/css" href="css/custom.ui.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/buttons.datatables.min.css">
<link rel="stylesheet" type="text/css"
	href="css/font_awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="css/select.datatables.min.css">
	<link rel="stylesheet" type="text/css"
	href="css/customDataTable.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.datatable.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery-te.min.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style>
.main-style
{
	margin-bottom:0px !important;
}
.tab-content
{
	background-color:white;
}
</style>
</head>
<body>
<div class="loaderDiv" style='display: none;'></div>
<%String requestId=(String)request.getSession().getAttribute("reqId");
  %>
  <main class="main-style"> 
	<div class="container big-container">
		<div class="request-tab">
			<div class="main-content">
				<div class="request-header">
					<div class="csa-header-bar"></div>
					<div class="page-heading">
						
						<ol class="breadcrumb">
						   <li>Home</li>
							<li>Data Services</li>
							<li class="active">Data Generation</li>
						</ol>
					</div>
					<!-- Nav tabs -->
					<ul class="nav nav-tabs data-gen-tabs" role="tablist" id="request-tab">
						<li role="presentation" >
						<a id="dataCreationLink" href="./requestDataByReqId?requestId=${requestId}&serviceIdentifier=activityLog"><i class="fa fa-pencil-square-o fa-lg csaa-vectors"></i>Data
								Request
								
						</a></li>
						<li role="presentation" class="active"><a data-target="#history"
							 role="tab" data-toggle="tab"> <i
								class="fa fa-history fa-lg csaa-vectors"></i>Request Activity Log
						</a></li>						 
					</ul>

				</div>

				<!-- Tab panes -->
				<div class="tab-content">
					<!-- div role="tabpanel" class="tab-pane active" id="manual">
						<div class="content-section">
							<div class="collpasewidget active" id="generalDetails">
								<div class="widget-inner">
									<div class="widget-title">
										<div class="container-fluid">
											<div class="row">
												<div class="col-xs-12">
													<i class="fa fa-minus-square-o plus-minus-style"
														title="Expand/Collpase Details"></i> <span
														class="request-title">General Details</span>
													<div class="dash-bar"></div>
												</div>
											</div>
										</div>
									</div>

									<div id="generalDetailsContent" class="widget-content">
										<table class="details-table">
											<tbody>
												<tr>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Request ID</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtRequestID"
														name="requestedBy" class="form-control" type="text"
														value="" /></td>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Requested By</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtRequestedBy"
														name="requestedBy" class="form-control" type="text"
														value="" /></td>
												</tr>
												<tr>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Created On</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtCreatedOn"
														name="createdOn" class="form-control" type="text" value="" />
													</td>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Application owner</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtAppOwner"
														name="appOwner" class="form-control" type="text" value="" />
													</td>

												</tr>
												<tr>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Assigned Group</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtAssignedGroup"
														name="assignedGroup" class="form-control" type="text"
														value="" /></td>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Approver</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><input id="txtApprover"
														name="approver" class="form-control" type="text" value="" />
													</td>
												</tr>
												<tr>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Assigned To</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><select id="ddlAssignedTo"
														name="assignedTo" class="down-control">
													</select></td>
													<td class="lable-title" width="14%" align="left"
														valign="middle">Status</td>
													<td class="flied-title" width="25%" align="left"
														valign="middle"><select id="ddlStatus" name="status"
														class="down-control">
													</select></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="widget-inner">
										<div class="widget-title">
											<div class="container-fluid">
												<div class="row">
													<div class="col-xs-12">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> <span
															class="request-title">Request Details</span>
														<div class="request-dash-bar"></div>
													</div>
												</div>
											</div>
										</div>

										<div id="otherDetails" class="other-details widget-content">
											<table class="details-table">
												<tbody>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Subject</td>
														<td class="flied-title" colspan=3 align="left"
															valign="middle"><input id="txtSubject"
															name="subject" class="form-control full-length-input"
															type="text" value="" /></td>
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Consumer Group</td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><select id="ddlConsumerGroup"
															name="consumerGroup" class="down-control">
														</select></td>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Priority</td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><select id="ddlPriority"
															name="priority" class="down-control">
																<option selected="selected" value="0">---Select---</option>
														</select></td>
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Data Source</td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><select id="ddlDataSource"
															name="dataSource" class="down-control">
														</select></td>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Environment</td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><select id="ddlEnvironment"
															name="environment" class="down-control">
																<option selected="selected" value="0">---Select---</option>
														</select></td>
													</tr>
													<tr>
														<td class="lable-title" width="14%" align="left"
															valign="middle">Expected Date</td>
														<td class="flied-title" width="25%" align="left"
															valign="middle"><input id="expectedDate"
															name="expectedDate"
															class="form-control date-control-style" type="text"
															value="" /></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<fieldset class="field-style">
									<div class="details-table details-extended">
										<div class="collpasewidget active policy-container-style"
											id="autoPolicyContainer">
											<div class='chk-div-style' id="chkAutoPolicyDiv">
												<label class="chk-label-style"><input id="chkAuto"
													type="checkbox" name="policyType" class="chk-control"
													value="0" checked />Auto Policy </label>
											</div>
											<div class="widget-inner policy-widget-style"
												id="autoWidgetDiv">
												<div class="policy-title">
													<div class="auto-title-style">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> Auto Policy
													</div>
													<button type="button" id="autoAddButton"
														class="toolbar-btn-style" data-toggle="tooltip"
														title='Add Scenario'>Add Data Request Scenario</button>
												</div>
												<div id="autoPolicyContent" class="widget-content">
													<table id="autoPolicyTable" class="customDataTable"
														cellspacing="0" width="100%">
														<thead>
															<tr>
																<th>Scenario No</th>
																<th>Risk State</th>
																<th>Payment Plan</th>
																<th>Number of Drivers</th>
																<th>Number of Vehicles</th>
																<th>Number of Policies</th>
																<th>Additional Information</th>
																<th></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<div class="collpasewidget active policy-container-style"
											id="propertyPolicyContainer">
											<div class='chk-div-style' id="chkPropertyPolicyDiv">
												<label class="chk-label-style"><input
													id="chkProperty" type="checkbox" name="policyType"
													class="chk-control" value="1" checked />Property Policy </label>
											</div>
											<div class="widget-inner policy-widget-style"
												id="propertyWidgetDiv">
												<div class="policy-title">
													<div class="prop-title-style">
														<i class="fa fa-minus-square-o plus-minus-style"
															title="Expand/Collpase Details"></i> Property Policy
													</div>
													<button type="button" id="propertyAddButton"
														class="toolbar-btn-style" data-toggle="tooltip"
														title='Add Scenario'>Add Data Request Scenario</button>
												</div>
												<div id="propertyPolicyContent" class="widget-content">
													<table id="propertyPolicyTable" class="customDataTable"
														cellspacing="0" width="100%">
														<thead>
															<tr>
																<th>Scenario No</th>
																<th>Policy Type</th>
																<th>Risk State</th>
																<th>Payment Plan</th>
																<th>Number of Policies</th>
																<th>Additional Information</th>
																<th></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
								<div class="btn-container">
									<button type="button" id="btnEmail"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover email-btn-style"
										title="Email Notification" data-toggle="modal"
										data-target="#emailModal">
										<i class="fa fa-envelope csaa-vectors"></i>Email
									</button>
									<button type="button" id="btnSave"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover save-btn-style"
										title="Save Request">
										<i class="fa fa-floppy-o csaa-vectors"></i>Save
									</button>
									<button type="button" id="btnSubmit"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover submit-btn-style"
										title="Submit Request">
										<i class="fa fa-sign-in csaa-vectors"></i>Submit
									</button>
									<button type="button" id="btnReset"
										class="leftButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-hover reset-btn-style"
										title="Reset Request">
										<i class="fa fa-arrow-circle-o-left csaa-vectors"></i>Reset
									</button>
								</div>
							</div>
						</div>
					</div> -->
					<div role="tabpanel" class="tab-pane active" id="history">
						<div class="content-section">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12">
										<div id="historyDetails"
											class="table-parent reser-details history-details">
											<div class="table-parent">
												<table id="historyTable" class="customDataTable"
													cellspacing="0" width="100%">
													<thead>
														<tr>
														<th class="sorting sorting_asc">Request Number</th>
															<th class="sorting">Date</th>
															<th class="sorting">Modified by</th>
															<th class="sorting">Status</th>
															<th class="sorting">Description</th>
														</tr>
													</thead>

													<tbody>
												
														<c:forEach items="${accessLog}" var="acclog" varStatus="loopStatus">
													<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
																<td>${acclog.requestId}</td>
																<td>${acclog.modifiedDate}</td>
																<td>${acclog.modifiedBy}</td>
																<td>${acclog.requestStatus}</td>
																<td>${acclog.comments}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%-- <!- Email popup ->
		<div class="modal fade email-modal" id="emailModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title email-title" id="emailTitle">Email
										Notification</h4>
								</div>
								<div class="modal-body">
									<div class="col-xs-9 right-border">
										<form class="form-horizontal">
											<div class="form-group">
												<label for="emailAdd"
													class="col-sm-2 control-label email-label">To :</label>
												<div class="col-sm-10">
													<input type="email" class="email-control" id="emailAdd">
												</div>
											</div>
											<div class="form-group">
												<label for="ccAdd"
													class="col-sm-2 control-label email-label">Cc : </label>
												<div class="col-sm-10">
													<input type="email" class="email-control" id="ccAdd">
												</div>
											</div>
											<div class="form-group">
												<label for="emailSubject"
													class="col-sm-2 control-label email-label">Subject
													: </label>
												<div class="col-sm-10">
													<input type="text" class="email-control" id="emailSubject">
												</div>
											</div>
											<div class="form-group">
												<label for="emailMessage"
													class="col-sm-2 control-label email-label">Message
													: </label>
												<div class="col-sm-10">
													<textarea class="form-control jqte-test" rows="10"
														id="emailMessage"></textarea>
												</div>
											</div>
										</form>
									</div>
									<div class="col-xs-3 center-align-style">
										<button id="btnNotificationTemplate"
											class="new-policy-text new-request-link-style full-width"
											title="Notification Template">Notification Template</button>
										<button type="button" id="btnRequestAck"
											class="email-popup-btn-style" title="Request Acknowledge">
											Request Acknowledge</button>
										<button type="button" id="btnRequestCompletionNot"
											class="email-popup-btn-style"
											title="Request Completion Notification">Request
											Completion Notification</button>
										<button type="button" id="btnAddDetailNot"
											class="email-popup-btn-style"
											title="Additional Detail Notification">Additional
											Detail Notification</button>
										<button type="button" id="btnReqRejNot"
											class="email-popup-btn-style"
											title="Request Reject Notification">Request Reject
											Notification</button>
										<button type="button" id="btnEscalNot"
											class="email-popup-btn-style" title="Escalation Notification">
											Escalation Notification</button>
										<button type="button" id="btnAddApproval"
											class="email-popup-btn-style"
											title="Seeking for Additional Approval">Seeking for
											Additional Approval</button>
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-xs-9">
										<button type="button" class="btn btn-primary">Send
											Notification</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>

		</div>
	
		</main>
		<div id="foo">
		<jsp:include page="footerNew.jsp"></jsp:include>
		</div>
		<script src="js/landing.js"></script>
		   <script type="text/javascript" src="js/history.js"></script>
		<!--   <script type="text/javascript" src="js/request.js"></script>
   <script type="text/javascript" src="js/history.js"></script>
   <script type="text/javascript" src="js/emailEditor.js"></script> -->
</body>
</html>