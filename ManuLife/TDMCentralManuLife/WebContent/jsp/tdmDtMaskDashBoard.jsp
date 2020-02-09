<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM CentralData | Masking Dashboard</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
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
	<div class="wrapper mainAll">
		<jsp:include page="indexHeader.jsp"></jsp:include>
		<jsp:include page="dashboardmenu.jsp"/>
		 
		<div class="container">
				<!-- <ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index">Home</a></li>
					<li>&#x2f;</li>
					<li><a  class="hrefVisited" href="./index">TDM Life Cycle</a></li>
					<li>&#x2f;</li>
					<li class="active">Data Masking Dashboard</li>
				</ol> 
	      <h2 style="color: #0098cc"><spring:message code="label.dm.dashboard"/></h2> -->
	       <c:if test="${error ne null}">
	            <table class="my-error-class">
	              <tbody>
	                <tr>
	                  <td class="lable-title" align="left" valign="middle">${error}</td>	                
	                </tr>
	              </tbody>
	            </table>
	       </c:if>           
	  
  <form:form id="tdmMaskingFRDataForm" name="tdmMaskingFRDataForm" action="${pageContext.request.contextPath}/tdmMaskingFR">	  
	<c:choose>
      <c:when test="${tdgDtMaskRequestListDTOs ne null}">      
 		   <%
				int currentPage = (Integer) request.getAttribute("currentPage");
				int count1 = currentPage - 1;
				count1 = count1 * 10;
 			%>						
	   <c:if test="${flag ne null and flag eq 'true'}">
 		<div>
 			<table class="my-error-class">
              <tbody>
                <tr>
                  <td class="lable-title" align="left" valign="middle"><spring:message code="label.dtm.requestdetails"/> </td>
                </tr>
              </tbody>
            </table>
 		</div>
 		</c:if>	
 		<c:if test="${flag ne null and flag eq 'T'}">
 		<div>
 			<table class="my-valid-class2">
              <tbody>
                <tr>
                  <td class="lable-title" align="left" valign="middle"> <spring:message code="label.dtm.requestcancel"/></td>
                </tr>
              </tbody>
            </table>
 		</div>
 		</c:if>	
 		<c:if test="${flag ne null and flag eq 'F'}">
 		<div>
 			<table class="my-error-class">
              <tbody>
                <tr>
                  <td class="lable-title" align="left" valign="middle"><spring:message code="label.dtm.requestfail"/></td>
                </tr>
              </tbody>
            </table>
 		</div>
 		</c:if>		
	  <div class="nav" id="myid">	
	   <table id="search_output_table" class="hoverTable"  style="width:100%; font-size: 13px; border:0; cellpadding:0; cellspacing:1;">
				<thead>
					<tr>
					  	<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.requestid"/></th>
					    <th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.description"/></th>						  	
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.createdby"/></th>	
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.projectname"/></th>
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.projectphase"/></th>						
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.requestime"/></th>
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="label.dtm.status"/></th>
					</tr>
				</thead>
			 <tbody>
                 <c:forEach items="${tdgDtMaskRequestListDTOs}" var="tdgDtMaskRequestListDTOs" varStatus="status">
                   <tr onmouseover="this.style.cursor='pointer'" onClick="getRequestDtls('${tdgDtMaskRequestListDTOs.id}','${tdgDtMaskRequestListDTOs.status}')">
                     <td align="left">${tdgDtMaskRequestListDTOs.id}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.desc}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.userName}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.projName}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.projPhase}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.reqTime}</td>
                     <td align="left">${tdgDtMaskRequestListDTOs.status}</td>
                   </tr>
                 </c:forEach>
               </tbody>
		</table>							
	 </div>		 
		 
		 <ul class="grdPagination">
			                  			<%
			                  				int noOfPages = (Integer) request.getAttribute("noOfPages");
			                  				int startPage = (Integer) request.getAttribute("startPage");
			                  				int lastPage = (Integer) request.getAttribute("lastPage");
			                  		  
											if (currentPage != 1) {
			   							%>
			   									<li><a href="tdmDtMaskDashboard?page=<%= 1 %>"><spring:message code="label.first"/></a><div><spring:message code="label.first"/></div></li>
			   									<li><a href="tdmDtMaskDashboard?page=<%= currentPage-1 %>">&lt; <spring:message code="label.prev"/></a><div>&lt; <spring:message code="label.prev"/></div>
			   							<%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
			   								 	<li class="disable"><a href="tdmDtMaskDashboard?page=<%= 1 %>"><spring:message code="label.first"/></a><div><spring:message code="label.first"/></div></li>
			   									<li class="disable"><a href="tdmDtMaskDashboard?page=<%= currentPage-1 %>">&lt; <spring:message code="label.prev"/></a><div>&lt; <spring:message code="label.prev"/></div>
			   							<%
			   								 	}
			   								}
											if(noOfPages > 1) {
			    								for (int i=startPage; i<=lastPage; i++) {
			    									if(currentPage == i) {
			   			 				%>
			   											<li class="active"><a href="#"><%= i %></a><div><%= i %></div></li>
			   							<%
			    									} else {
			    						%>
			    										<li><a href="tdmDtMaskDashboard?page=<%= i %>" id="employeeLink"><%= i %></a><div><%= i %></div></li>
			    						<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
			    		     					<li><a href="tdmDtMaskDashboard?page=<%= currentPage+1 %>"><spring:message code="label.next"/> &gt;</a><div><spring:message code="label.next"/> &gt;</div></li>
			    		     					<li><a href="tdmDtMaskDashboard?page=<%= noOfPages %>"><spring:message code="label.last"/></a><div><spring:message code="label.last"/></div></li>
			    		   				<%
											} else {
											    if(noOfPages > 1) {
										%>
			    		     					<li class="disable"><a href="tdmDtMaskDashboard?page=<%= currentPage+1 %>"><spring:message code="label.next"/> &gt;</a><div><spring:message code="label.next"/> &gt;</div></li>
			    		     					<li class="disable"><a href="tdmDtMaskDashboard?page=<%= noOfPages %>"><spring:message code="label.last"/></a><div><spring:message code="label.last"/></div></li>
										<%
											    }
											}
										%>
									</ul>
									
											
   	    <%-- <div style="float: left; width: 90%; border: 0; font-color: #163361 ;padding-top: -59px;padding-left: -81px;" align="left">
     		<a class="Button" href="${pageContext.request.servletContext.contextPath}/tdmDataMasking"><spring:message code="label.createNewReq"/></a>
        </div> --%>
        
           <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col">
                       <input type="submit" name="export" id="export" class="btn-primary btn-cell" value="<spring:message code="button.export"/> ">
                   </th>
                </tr>
              </tbody>
            </table>	
        
        </c:when>
      <c:otherwise>
        <h3 style="float: left; width: 40%; border: 0; font-size: 14px;color: black; padding-top: 15px">
				<div><spring:message code="label.dtm.nodata"/></div>
			</h3>
        <br/>
      </c:otherwise>
    </c:choose>
    </form:form>
	</div>
	<script src="include/footer.js"></script>
  </div>		

<script>
menu_highlight('tdm_command_center1');
menu_highlight('MRDashBoard');
window.location.hash = "myid";
   function getRequestDtls(reqId,status){
	   document.location.href="./tdmDataMaskingNew?reqId="+reqId+"&status="+status;
   }
   
   $("#search_output_table").tablesorter({
	    widgets: ['zebra']
	  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
</script>
</body>
</html>