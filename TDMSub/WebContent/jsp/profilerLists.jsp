<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Data Subset  | Profilers Dashboard</title>
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
				 
	      <h2 style="color: #0098cc ; padding-top: 38px;">Profiler Dash Board</h2>  
	       <c:if test="${error ne null}">
	            <table class="my-error-class">
	              <tbody>
	                <tr>
	                  <td class="lable-title" align="left" valign="middle">${error}</td>	                
	                </tr>
	              </tbody>
	            </table>
	       </c:if>           
	<form:form id="dataConListConnectionsForm" name="dataConListConnectionsForm" action="${pageContext.request.contextPath}/tdmProfilersDashboard">  
	<c:choose>
      <c:when test="${profilerDashboardListDTO ne null && not empty  profilerDashboardListDTO.listProfilerDashboardDTO}">      
 		   <%
				int currentPage = (Integer) request.getAttribute("currentPage");
				int count1 = currentPage - 1;
				count1 = count1 * 10;
 			%>								
	  <div class="nav" id="myid">	
	   <table id="search_output_table" class="hoverTable"  style="width:100%; font-size: 13px; border:0; cellpadding:0; cellspacing:1;">
				<thead>
					<tr>
					  	<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">Profiler Name</th>
					    <th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">Connection Name</th>						  	
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">User Selected Tables</th>	
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">Action</th>						
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">Created By</th>
						<th align="center"  bgcolor="#E3EFFB" scope="col" class="whitefont">Excepted SQL Queries</th>							
					</tr>
				</thead>
			 <tbody>
                 <c:forEach items="${profilerDashboardListDTO.listProfilerDashboardDTO}" var="profilerDashboardListDTO" varStatus="status">
                   <tr>
                     <td align="left">${profilerDashboardListDTO.profilerName}</td>
                     <td align="left">${profilerDashboardListDTO.connectionName}</td>
                     <td align="left">${profilerDashboardListDTO.selectedTables}</td>
                     <td align="left"><a href="#" onClick="editTdmProfiler('${profilerDashboardListDTO.profilerId}')">Edit</a> Or <a href="#" onclick="deleteTdmProfiler('${profilerDashboardListDTO.profilerId}')">Delete</a></td>
                     <td align="left">${profilerDashboardListDTO.createdBy}</td>   
                     <td align="left"><a href="#" onClick="downloadTdmProfilerQuery('${profilerDashboardListDTO.profilerId}')">Download</a></td>
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
			   									<li><a href="tdmProfilersDashboard?page=<%= 1 %>">First</a><div>First</div></li>
			   									<li><a href="tdmProfilersDashboard?page=<%= currentPage-1 %>">&lt; Prev</a><div>&lt; Prev</div>
			   							<%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
			   								 	<li class="disable"><a href="tdmProfilersDashboard?page=<%= 1 %>">First</a><div>First</div></li>
			   									<li class="disable"><a href="tdmProfilersDashboard?page=<%= currentPage-1 %>">&lt; Prev</a><div>&lt; Prev</div>
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
			    										<li><a href="tdmProfilersDashboard?page=<%= i %>" id="employeeLink"><%= i %></a><div><%= i %></div></li>
			    						<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
			    		     					<li><a href="tdmProfilersDashboard?page=<%= currentPage+1 %>">Next &gt;</a><div>Next &gt;</div></li>
			    		     					<li><a href="tdmProfilersDashboard?page=<%= noOfPages %>">Last</a><div>Last</div></li>
			    		   				<%
											} else {
											    if(noOfPages > 1) {
										%>
			    		     					<li class="disable"><a href="tdmProfilersDashboard?page=<%= currentPage+1 %>">Next &gt;</a><div>Next &gt;</div></li>
			    		     					<li class="disable"><a href="tdmProfilersDashboard?page=<%= noOfPages %>">Last</a><div>Last</div></li>
										<%
											    }
											}
										%>
									</ul>
									
											
   	    	 <!-- <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col" class="buttonsAll15">
                       <input type="submit" name="export" id="export" value="ExportAll to Excel">
                   </th>
                </tr>
              </tbody>
            </table>	 --> 
        
        </c:when>
      <c:otherwise>
        <h3 style="float: left; width: 40%; border: 0; font-size: 14px;color: black; padding-top: 15px">
				<div>No Profilers are available</div>
			</h3>
        <br/>
      </c:otherwise>
    </c:choose>
    </form:form>
	</div>
	<script src="include/footer.js"></script>
  </div>		

<script>
menu_highlight('admin');
menu_highlight('admin_db_connection');
menu_highlight('db_connection_list');

window.location.hash = "myid";
  /*  function getRequestDtls(id){
	   document.location.href="./dataConAddConnection?id="+id;
   } */
   
   $("#search_output_table").tablesorter({
	    widgets: ['zebra']
	  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
   
   function deleteTdmProfiler(manId){
		 if (confirm('Are you sure to delete a Profiler?')) {
	 	   document.location.href="./deleteProfiler?reqprofileName="+manId;
		  }
}
   
   function editTdmProfiler(manId){
	 	   document.location.href="./editProfiler?reqprofileName="+manId;
		  }
   function downloadTdmProfilerQuery(profileId){
	   document.location.href="./downloadProfilerQueries?reqprofileName="+profileId;
   }
</script>
</body>
</html>