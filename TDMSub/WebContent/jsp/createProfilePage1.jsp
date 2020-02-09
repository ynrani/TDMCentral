<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Data Subset | Index</title>
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
<script>
$(document).ready(function() {
	$('#errors').html('');
	$('#errors').hide();
	
	 
});
</script>
</head>
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container">
			  <form:form id="tdmProfileForm" name="tdmProfileForm" action="${pageContext.request.servletContext.contextPath}/createProfiler" modelAttribute="tdmProfilerDTO" >
			  <div id="errors" class="errorblock" style="display: none"></div>
          	 	<div class="two-col">
          	 	<h2 style="color: #0098cc ;   padding-top: 5%;">Create Profile</h2>
          	 	  <table style="width:100%; border:0; font-size: 13px; cellpadding:2;   border-spacing: 5px;  padding: 2% 0% 0% 0%; ">
          	 	   <tbody>
          	 	  	  <tr>
                		<tr>
                		<td class="lable-title" width="20%" align="left" valign="middle">Profile Name<span>*</span></td>
                		<td class="flied-title" width="30%" align="left" valign="middle">
                  		<form:input path="profilerName" id="profilerName" required="required"  class="form-control" autocomplete="off"/>
	                    </td>
                 	  </tr>
                	 <tr>
                		<td class="lable-title" align="left" valign="middle">Source Database<span>*</span></td>
                  		<td class="flied-title" align="left" valign="middle">
                  		<form:select path="selectedConnectionName" id="selectedConnectionName" class="down-control">
		<c:forEach items="${tdmProfilerDTO.connectionNames}" var="dbConnectionsDTOs" varStatus="status">
		<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
		</c:forEach>	
		</form:select>
                  		</td>
                 	  </tr>
                 	  
					</tbody>          	 	
          	 	  </table>
          	 	  
          	 	  
				  <table style="width:50%; border:0; font-size: 13px; cellpadding:4; padding: 1% 0% 0% 0%; ">
					<tbody>
					 <tr>
					   <td colspan="2" align="center" valign="middle" class="buttonsAll22">
					  	 <input type="submit" name="create" id="create" value="Submit">
					   </td>
					 </tr>
					</tbody>
				  </table>
				  
         	    </div>
         	  </form:form>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('admin');
		menu_highlight('admin_db_connection');
		menu_highlight('db_connection');
		
		$(document).ready(function() {
		if("${tdmProfilerDTO.message}" == null || typeof '${tdmProfilerDTO.message}' == '' ||  '${tdmProfilerDTO.message}' == ''){			
		}else{
			alert('${tdmProfilerDTO.message}');
		}	
		});
	</script>
</body>
</html>
