
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
    <title>TDM Central | Auto Policy Search</title>
	  <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="images/favicon.ico" >	
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="css/stylesNew.css">
    <link rel="stylesheet" type="text/css" href="css/datamining.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.datatables.min.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/select.datatables.min.css">

	  <script type="text/javascript" src="js/jquery.min.js"></script>
	  <script type="text/javascript" src="js/jquery.datatable.min.js"></script>
	  <script type="text/javascript" src="js/jquery-ui.js"></script>
    <script type="text/javascript" src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>


  </head>
  <body>
  <div class="container">
  <div id="main" class="wrapper mainAll">

			<jsp:include page="headerPC.jsp"></jsp:include>

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

			<div id="myErrorCls"></div>
			<form:form id="testDataForm" name="testDataForm"
				action="${pageContext.request.contextPath}/dataMinningAutoPolicySearch"
				modelAttribute="tdmAtsSearchDTO">
	  <div class="request-tab">
		  <div class="main-content">

			  <div class="request-header">
				  <div class="csa-header-bar"></div>
				  <div class="page-heading">
					  <i class="fa fa-cogs"></i>
					  <h1>Data Mining</h1>
				  </div>

				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs data-mine-tabs" role="tablist" id="request-tab">
					  <li role="presentation" class="auto-search active">
						  <a data-target="#manual" aria-controls="manual" role="tab" data-toggle="tab">
							  <i class="fa fa-clock-o csaa-vectors"></i>Auto Policy
						  </a>
					  </li>
					  <li role="presentation" class="policy-search">
						  <a data-target="#property" aria-controls="property" role="tab" data-toggle="tab">
							  <i class="fa fa-lock csaa-vectors"></i>Property Policy
						  </a>
					  </li>
				  </ul>
			  </div>


			  <!-- Tab panes -->
			  <div class="tab-content" id="tabData">
				  <div role="tabpanel" class="tab-pane active" id="manual">
				  </div>
				  <!--Property tab-->
				  <div role="tabpanel" class="tab-pane" id="property">
				  </div>
		  </div>
	  </div>
  </div>
	
	</form:form>
	</div>
	</div>
	
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
  <script type="text/javascript" src="js/search-common.js"></script>
 
	<script>
		$('#manual').load('./dataMinningAutoPolicySearch');
		$('#property').load('./dataMinningPropertyPolicySearch');
	</script>
  </body>
</html>