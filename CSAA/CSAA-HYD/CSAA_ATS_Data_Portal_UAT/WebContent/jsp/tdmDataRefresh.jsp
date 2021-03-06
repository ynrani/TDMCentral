<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>ATS Data Central - Data Refresh</title>
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
<body>
  <div class="wrapper mainAll">
 <jsp:include page="indexHeader.jsp"></jsp:include>
 <div class="container">
    <form:form id="dataRefreshForm" name="dataRefreshForm" action="${pageContext.request.contextPath}/tdmDataRefresh" modelAttribute="tdgDataRefreshDTO">
    
	     
      <h2 style="color: #0098cc"><spring:message code="label.dtref.dtl" /></h2>
      <hr>
      <div class="two-col">
      <table style="width:100%; border:0; font-size: 13px; color: #0C5473;cellpadding:2;">
        <tbody>
          <tr>
        	<td class="lable-title" width="80%" align="left" valign="middle"><spring:message code="label.dtref.q1"/><span>*</span> </td>
            <td class="flied-title" width="20%" align="left" valign="middle">
            	<form:textarea path="pageDtRef1" id="pageDtRef1" class="form-control" />
            </td>
          </tr>  
          <tr>  
            <td class="lable-title" align="left" valign="middle"><spring:message code="label.dtref.q2"/><span>*</span><br/></td>
            <td class="lable-title" align="left" valign="middle" scope="col">
           		 <form:textarea path="pageDtRef2" id="pageDtRef2" class="form-control" />
           </td>
          </tr>          
          <tr>
        	<td class="lable-title" align="left" valign="middle"><spring:message code="label.dtref.q3"/><span>*</span> </td>
            <td class="flied-title" align="left" valign="middle">
            	<form:textarea path="pageDtRef3" id="pageDtRef3" class="form-control" />
            </td>
          </tr>
        </tbody>
      </table>  
      <br/>
         <table style="width:100%; border:0;">
            <tbody>
              <tr>
                  <th scope="col" class="buttonsAll8">
                    <input type="button" name="submit" value="Submit"> 
                 </th>
               </tr>
            </tbody>
         </table>
       </div>
    </form:form>
  </div>
  <script src="include/footer.js"></script>
</div>
<script src="include/copyrtfooter.js"></script>
<script>
menu_highlight('services');
menu_highlight('services_ref');

 tdgDataRefreshValidation();
</script>

</body>
</html>
