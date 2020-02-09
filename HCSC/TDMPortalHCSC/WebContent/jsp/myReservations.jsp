<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>TDM Portal | My Reservation Records</title>
  <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" >
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/custom.css">
  <link href="css/theme.default.css" rel="stylesheet">
  <link href="css/elements.css" rel="stylesheet">
  <script src="js/html5Shiv.js"></script>
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/main.js"></script>
  <script src="js/jquery.validate.min.js" type="text/javascript" ></script>  
  <script src="js/messages.js"></script>
  <script src="js/common.js"></script>
  <script src="js/jquery-migrate-1.2.1.min.js"></script>
</head>

<body>
  <div id="main" class="wrapper mainAll">
    <jsp:include page="indexHeader.jsp"></jsp:include>
    <div style="float: right; width: 14%; border: 0; font-color: #163361; padding-top: 15px; padding-left: 74px;">
	<a class="backButton"	href="${pageContext.request.servletContext.contextPath}/tdmNSSearch"><spring:message
	    code="label.back" /></a>
    </div>
    <script src="include/reservationMenu.js"></script>   
    <div class="container">           
    <form:form id="reservationDtlsdashBoard" name="reservationDtlsdashBoard" action="./unReserveRecords"  modelAttribute="tdmNonStandSearchDTO">
           <c:if test="${error ne null}">
	            <table class="my-error-class">
	              <tbody>
	                <tr>
	                  <td class="lable-title" align="left" valign="middle"> ${error}</td>	                
	                </tr>
	              </tbody>
	            </table>
	     </c:if>
	     <c:if test="${reserveFlag ne null}">
						<table class="my-msg-class">
							<tbody>
								<tr>
									<td class="lable-title" align="left" valign="middle">
										${reserveFlag}</td>
								</tr>
							</tbody>
						</table>
		</c:if>   
    <c:choose>
      <c:when test="${tdmNonStandSearchDTO.tdmNonStandReservationDtos ne null}">  
      							
        <div class="container scrollingX">
          <table id="search_output_table" class="table tablesorter " style="width:100%;  font-size: 13px;">            
	             <thead>
								<tr>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont"><spring:message code="lebel.chkbUnResrv" /></th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont">Expires On</th>
									<th bgcolor="#E3EFFB" height="25" class="whitefont">Subscriber ID</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Member Type</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">First Name</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Last Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Gender</th>										
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Date of Birth</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Corp ID</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Home Zip Code</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Group Number</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Account Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Product Type</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">PCP/MG</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Funding Indicator</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Blended Category</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Exchange Type</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Member Effective Date</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Member End Date</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont" title ="Membership Coverage Group Section Effective Date">MCGSEF Date</th>
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont" title="Membership Coverage Group Section End Date">MCGSEN Date</th>								    									
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Original Effective Date</th>									
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Coverage</th>								    
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Existing Claim(s)</th>							  
								    <th bgcolor="#E3EFFB" scope="col" class="whitefont">Test Case ID</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Test Case Name</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Project ID</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Reserved Date</th>
									<th bgcolor="#E3EFFB" scope="col" class="whitefont">Reserved By</th>									  
								</tr>
							</thead>
            <tbody>
              <c:forEach items="${tdmNonStandSearchDTO.tdmNonStandReservationDtos}" var="tdmNonStandReservationDtos" varStatus="status">
                <tr>
                  <c:if test="${tdmNonStandReservationDtos.unreserveYN eq null }">
				   <td>
				     <label class="checkbox-inline">
					  	<form:checkbox path="tdmNonStandReservationDtos[${status.index}].unreserveYN"
									id="tdmNonStandReservationDtos[${status.index}].unreserveYN"
														class="cb_class checkBoxCls" value="Yes" />							 
					</label>
				   </td>
                  </c:if>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.unreservDate}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.subscrId}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.membrType}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.firstName}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.lastName}</td>
                  <td  style="font-size: 11px;"align="center">${tdmNonStandReservationDtos.gender}</td>
                  <td  style="font-size: 11px;"align="center">${tdmNonStandReservationDtos.dob}</td>
                  <td  style="font-size: 11px;"align="center">${tdmNonStandReservationDtos.corpId}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.homeZipCode}</td>             
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.groupNum}</td>        
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.accountName}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.productType}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.pcpMG}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.fundingInd}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.blendedCat}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.exchangeType}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.memEffDate}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.memEndDate}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.mcgSecEffDate}</td>
                  <td  style="font-size: 11px;"align="center">${tdmNonStandReservationDtos.mcgSecEndDate}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.originalEffDate}</td>  
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.coverage}</td>  
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.extClaimType}</td>  
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.testCaseId}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.testCaseName}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.projectId}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.reserveDate}</td>
                  <td  style="font-size: 11px;" align="center">${tdmNonStandReservationDtos.reservedBy}</td>
                </tr>
              </c:forEach>
              
              
            </tbody>
          
          </table>            		
            <c:if test="${tdmNonStandSearchDTO.tdmNonStandReservationDtos ne null &&  not empty tdmNonStandSearchDTO.tdmNonStandReservationDtos}">
                 
					<c:forEach
						items="${tdmNonStandSearchDTO.tdmNonStandReservationDtos}"
						var="tdmNonStandReservationDtos" varStatus="status">
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].subscrId" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].membrType" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].dob" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].firstName" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].lastName" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].homeZipCode" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].reserveDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].testCaseId" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].testCaseName" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].accountNum" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].accountName" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].productType" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].gender" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].exchangeType" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].blendedCat" />
							<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].extClaimType" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].groupNum" />							
							<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].memEndDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].memEffDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].mcgSecEffDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].mcgSecEndDate" />
					    <form:hidden
							path="tdmNonStandReservationDtos[${status.index}].projectId" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].coverage" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].originalEffDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].reservedBy" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].unreservDate" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].corpId" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].pcpMG" />
						<form:hidden
							path="tdmNonStandReservationDtos[${status.index}].fundingInd" />
					</c:forEach>
					</c:if>
              </div>
              <%
									int currentPage = (Integer) request.getAttribute("currentPage");
									int count1 = currentPage - 1;
									count1 = count1 * 10;
					 
								%>



					<!-- Pagination Starts -->
					<ul class="grdPagination">
						<%
			                  				int noOfPages = (Integer) request.getAttribute("noOfPages");
			                  				int startPage = (Integer) request.getAttribute("startPage");
			                  				int lastPage = (Integer) request.getAttribute("lastPage");
			                  		  
											if (currentPage != 1) {
			   							%>
						<li><a href="myReservationRecords?page=<%= 1 %>">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li><a href="myReservationRecords?page=<%= currentPage-1 %>">&lt;</a>
							<div>&lt;</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
						<li class="disable"><a href="myReservationRecords?page=<%= 1 %>">&lt;&lt;</a>
							<div>&lt;&lt;</div></li>
						<li class="disable"><a
							href="myReservationRecords?page=<%= currentPage-1 %>">&lt;</a>
							<div>&lt;</div> <%
			   								 	}
			   								}
											if(noOfPages > 1) {
			    								for (int i=startPage; i<=lastPage; i++) {
			    									if(currentPage == i) {
			   			 				%>
						<li class="active"><a href="#"><%= i %></a>
							<div><%= i %></div></li>
						<%
			    									} else {
			    						%>
						<li><a href="myReservationRecords?page=<%= i %>" id="employeeLink"><%= i %></a>
							<div><%= i %></div></li>
						<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
						<li><a href="myReservationRecords?page=<%= currentPage+1 %>">&gt;</a>
							<div>&gt;</div></li>
						<li><a href="myReservationRecords?page=<%= noOfPages %>">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
											} else {
											    if(noOfPages > 1) {
										%>
						<li class="disable"><a
							href="myReservationRecords?page=<%= currentPage+1 %>">&gt;</a>
							<div>&gt;</div></li>
						<li class="disable"><a
							href="myReservationRecords?page=<%= noOfPages %>">&gt;&gt;</a>
							<div>&gt;&gt;</div></li>
						<%
											    }
											}
										%>
					</ul>					
				<!-- Pagination Ends -->             
		    <table style="width:100%; border:0">
              <tbody>
                <tr>
                  <th scope="col">
                   <input type="submit" name="unreserve"
									class="btn-primary btn-cell" id="unreserve" value="Un Reserve">
                       <input type="submit" name="export" id="export" class="btn-primary btn-cell" value="Export All to Excel">
                   </th>
                </tr>
              </tbody>
            </table>	
      </c:when>
      <c:otherwise>
        <h3 style="float: left; width: 40%; border: 0; font-size: 14px;color: #E74949; padding-top: 15px">
				No Test Data Reserved 
			</h3>
        <br />
      </c:otherwise>
    </c:choose>
    </form:form>
      
    </div>
    <script src="include/footer.js"></script>
  </div>
 <script>
 menu_highlight('Non_Standard_Reserv');
 $(document).ready(function() {
	    $("#unreserve").click(function(){
	    	$('.mandetCls1').next(".my-error-class").remove(); 
	    	$('.mandetCls2').next(".my-error-class").remove(); 
	    	$('#search_output_table').next(".my-error-class").remove(); 
	    	var checkboxes = $('.checkBoxCls');
	      	  var selected = checkboxes.filter(":checked").length;
	    	    if (selected == false) {
	    		  $('#search_output_table').after('<div class="my-error-class">There is no selection of the records from Search Result</div>');
	    		  return false;
	      	    }     	  
	         });   
	  });
	  
  $("#search_output_table").tablesorter({
	    widgets: ['zebra']
  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
  </script>
</body>

</html>