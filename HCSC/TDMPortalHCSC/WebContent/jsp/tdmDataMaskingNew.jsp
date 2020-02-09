<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>TDM Central | Data Masking</title>
  <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" >
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/custom.css">
  <link href="css/theme.default.css" rel="stylesheet">
  <script src="js/html5Shiv.js"></script>
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/main.js"></script>
  <script src="js/jquery.validate.min.js" type="text/javascript" ></script>  
  <script src="js/messages.js"></script>
  <script src="js/common.js"></script>
  <script src="js/jquery-migrate-1.2.1.min.js"></script>
  
<body>
  <div class="mainAll">
 <jsp:include page="indexHeader.jsp"></jsp:include>
  <c:if test="${readOnly ne null}">
	 <div style="float: right; width: 14%; border: 0; font-color: #163361 ;padding-top: 15px;padding-left: 74px;">
	      <a class="DbButton" href="${pageContext.request.servletContext.contextPath}/tdmDtMaskDashboard"><spring:message code="label.back.db" /></a>
	 </div>
 </c:if> 
 <div class="container">
  <c:if test="${error ne null}">
	            <table class="my-error-class">
	              <tbody>
	                <tr>
	                  <td class="lable-title" align="left" valign="middle"> ${error}</td>	                
	                </tr>
	              </tbody>
	            </table>
	           </c:if>           
    
        <ol class="breadcrumb">
	    <li><a class="hrefVisited" href="./index">Home</a></li>
	    <li>&#x2f;</li>
	    <li><a class="hrefVisited" href="./index">TDM Portal Functions</a></li>
	    <!-- <li>&#x2f;</li>
		<li><a class="hrefVisited" href="./tdmDtMaskDashboard">Data Masking Dashboard</a></li> -->
	    <li>&#x2f;</li>
	    <li class="active">Data Masking</li>
	  </ol>  
	  <div class="three-step-container">
	      <div class="step1-active"><span class="step-number1">1</span><span class="step-heading">Requestor <small>Details</small></span></div>
	      <div class="step2"><span class="step-number2">2</span><span class="step-heading">Pre-<small>Requisites</small></span></div>
	      <div class="step3"><span class="step-number3">3</span><span class="step-heading">Masking <small>Details</small></span></div>
	   </div>
	   
      <h2 style="color: #0098cc;font-size: 14px;"><spring:message code="label.dm.reqDtl" /></h2>
      <hr>
      
      <div class="two-col">
      <form:form id="dataMaskingForm" name="dataMaskingForm" action="${pageContext.request.contextPath}/tdmDataMaskingNew" modelAttribute="tdgDataMaskingDTO">
  	   <form:hidden path="id"/>
  	   <form:hidden path="reqTypeCR"/>
  	   <form:hidden path="status"/>
  	   <form:hidden path="edit"/>
  	   <form:hidden path="vno"/>
        <table style="width:100%; border:0; font-size: 13px; cellpadding:4;">
        <tbody>
          <tr>
        	<td class="lable-title" width="35%" align="left" valign="middle"><spring:message code="label.dm.name" /> <span>*</span> </td>
            <td class="flied-title" width="20%" align="left" valign="middle">
            	<form:input path="userName" id="userName" class="form-control" readonly="true" />
            </td>
            <td class="lable-title" width="25%" align="left" valign="middle"><spring:message code="label.dm.uname" /></td>
            <td class="lable-title" width="20%" align="left" valign="middle" scope="col">
           		 <form:input path="userId" id="userId" class="form-control" readonly="true" />
           </td>
          </tr>          
          <tr>
        	<td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.email" /><span>*</span></td>
            <td class="flied-title" align="left" valign="middle">
            	<form:input path="emailId" id="emailId" class="form-control"readonly="true" />
            </td>
            <td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.phNo" /></td>
            <td class="lable-title" align="left" valign="middle" scope="col">
           		 <form:input path="phoneNo" id="phoneNo" class="form-control" />
           </td>
          </tr>          
          <tr>
        	<td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.deptName" /><span>*</span> </td>
            <td class="flied-title" align="left" valign="middle">
            	<form:input path="deptName" id="deptName" class="form-control" />
            </td>
            <td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.needBy" /></td>
            <td class="lable-title" align="left" valign="middle" scope="col">
           	  <%--  <form:select path="neededBy" id="neededBy" class="down-control">
                  <form:option value="">--Select--</form:option>
                  <form:option value="Tester">Tester</form:option>
                  <form:option value="Lead">Lead</form:option>
                  <form:option value="Architect">Architect</form:option>
                  <form:option value="Manager">Manager</form:option>
                  <form:option value="Client">Client</form:option>                  
               </form:select> --%>
              <form:input path="neededBy" id="neededBy" onChange="validateDate();" placeholder="mm/dd/yyyy" class="datepicker date-control" />
           </td>
          </tr>
          <tr>
         	<td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.projName" /><span>*</span> </td>
             <td class="flied-title" align="left" valign="middle">
            	<form:input path="projName" id="projName" class="form-control"/>
            </td>
            <td class="lable-title" align="left" valign="middle"><spring:message code="label.dm.projPhase" /></td>
             <td class="flied-title" align="left" valign="middle">
             	<form:input path="projPhase" id="projPhase" class="form-control"/>
            </td>
           </tr>
          <tr>
            <td class="lable-title" align="left" valign="middle">User Application Name</td>
            <td class="flied-title" align="left" valign="middle">
            	<form:input path="projectId" id="projectId" class="form-control"/>
            </td> 
          	<td class="lable-title" align="left" valign="middle"><spring:message code="label.env" /> </td>
          	<td class="flied-title" align="left" valign="middle">
             	<form:input path="envType" id="envType" class="form-control"/>              
            </td>
          </tr>
          </tbody>
        </table>
       <c:choose>
       <c:when test="${readOnly eq null}">        
        <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col">
                    <input type="submit" name="submit" class="btn-primary btn-cell" value="Save and Continue"> 
                 </th>
               </tr>
            </tbody>
         </table> 
         </c:when>
        <c:when test="${changeReqYN eq true}">        
        <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col">
                    <input type="submit" id="submit" name="submit" class="btn-primary btn-cell" value="Save and Continue"> 
                 </th>
               </tr>
            </tbody>
         </table> 
         </c:when>
         <c:otherwise>
           <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col">
                    <input type="submit" id="submit"  name="submit" class="btn-primary btn-cell" value="Continue"> 
                 </th>
               </tr>
            </tbody>
         </table> 
         </c:otherwise>
       </c:choose>       
        </form:form>           
      </div>       
  </div>
  <script src="include/footer.js"></script>
</div> 
<script>

menu_highlight('tdm_life_cycle1');
tdgDataMaskingValidation();

$(function() {
	$( document ).tooltip({
		position: {
			my: "center bottom-20",
			at: "center top",
			using: function( position, feedback ) {
				$( this ).css( position );
				$( "<div>" )
					.addClass( "arrow" )
					.addClass( feedback.vertical )
					.addClass( feedback.horizontal )
					.appendTo( this );
			}
		}
	});
});

 $(function() {   
  
	    $( ".datepicker" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      changeYear:true,
	    });   
	  
 });

function validateDate()
  {
    $('#neededBy').next(".my-error-class").remove(); 
     var origDate=  $('#neededBy').val();
     var curDate = new Date();
     if(origDate.match(/^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/))
     {
        $('input[type="submit"]').removeAttr('disabled');
        origDate = new Date(origDate);
        if(origDate <= curDate)
        {
          $('#neededBy').after('<div class="my-error-class">Needed by date should be greater than the current date.</div>');
           $('input[type="submit"]').attr('disabled','disabled');
        }
     }
     else
     {
        $('#neededBy').after('<div class="my-error-class">Please enter a date in the format mm/dd/yyyy.</div>');
        $('input[type="submit"]').attr('disabled','disabled');
     }
  }


$(document).ready(function() {
	$(document).ready(function() {
		var readOnly = '${readOnly}';
		if(readOnly=='true'){			
			disablePage();
		}
  });	
});
</script>
</body>
</html>
