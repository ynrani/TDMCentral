<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<section class="headerDiv">
	<header>
		<section class="top-blue-nav">
			<div class="primary-nav">
				<nav>
					<ul>
						<li><a href="./index"><img src="images/home-icon.png"
								width="20" height="20" alt="" /> Home</a></li>
						<li><a href="http://www.capgemini.com/about-capgemini"
							TARGET="_NEW"><img src="images/about-icon.png" width="20"
								height="20" alt="" /> About Us</a></li>
						<li><a href="http://www.in.capgemini.com/contact-capgemini"
							TARGET="_NEW"><img src="images/contact-icon.png" width="20"
								height="20" alt="" /> Contact Us</a></li>
						<li><a href="./logout?logout=true"><img
								src="images/logout-icon.png" width="20" height="20" alt="" />Logout</a></li>
					</ul>
				</nav>
			</div>
			<div class="welcome">
				<h5>
					Welcome
					<%
					out.println("   "+(String) session.getAttribute("UserName"));
				    %>
				</h5>
			</div>
		</section>
		<section class="navigation">
			<div class="logo">
				<img src="images/logo-cap.jpg" class="logo" alt="" />
			</div>
			<div class="main-nav">
				<nav>
					<ul>						
						<li id="tdm_life_cycle"><a id="tdm_life_cycle1"
							href="./index">TDM Portal Functions</a></li>
					    <security:authorize access="hasRole('ROLE_ADMIN')">
						  <li id="tdm_command_center"><a id="tdm_command_center1"
							         href="./indexCmdCtr">Command Center</a></li>
						   <li id="tdm_governance"><a id="tdm_governance1"
							    href="./indexGovn">TDM Governance</a></li>
						</security:authorize>
						<li id="tdm_training"><a id="tdm_training1" href="./training">Training</a></li>
					</ul>
				</nav>
			</div>
		</section>
		<section class="title-band">
			<div class="title">
				<h3 class="h3Tdm">TDM Portal</h3>
			</div>
			<c:if test="${show ne null }">
			<div class="advanced-search">		
			  <a href="./myReservationRecords">
			        <%
			        if(session.getAttribute("ROLE").toString().equalsIgnoreCase("ROLE_ADMIN"))
			        {
			          out.println("Reservations");
			        }
			        else
			        {
			          out.println("My Reservations");
					}
				    %>
			 </a>
			</div>
			</c:if>			
		 
			<div class="quicklink-container">
				<div class="quicklink-sh">
					<a href="#">Quick Links</a>
				</div>
				 <%
			     if(session.getAttribute("ROLE").toString().equalsIgnoreCase("ROLE_ADMIN"))
			     {%>				
				<div class="quicklink-list">
					<ul>
						<li><a href="./tdpSensitiveProfiler">Sensitivity Profiler</a></li>
						<li><a href="./tdmDataMaskingNew">Masking Request</a></li>						
						<li><a href="./tdmOnboardReq">TDM On-boarding Request</a></li>
						<li><a href="./tdmChangeReqExt">Change Request</a></li>
				        <li><a href="./tdmNSSearch">Test Data Search</a></li>
						<li><a href="./tdmCmdCenterMetrics">Metrics</a></li>
						<li><a href="./downloadUserManual">User Manual</a></li>
					</ul>
				</div>
				<%} else {%>					
				<div class="quicklink-list-user">
					<ul>		        	
						<li><a href="./tdmNSSearch">Test Data Search</a></li>
						<li><a href="./downloadUserManual">User Manual</a></li>
					</ul>
				</div>
				<%}%>			
			</div>			
		</section>
	</header>
</section>
<script>
</script>