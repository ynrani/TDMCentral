<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section class="headerDiv">
	<header>
		<section class="top-blue-nav">
			<div class="primary-nav">
				<nav>
					<ul>
						<li><a href="./index"><img src="images/home-icon.png" width="20" height="20" alt="" /> <spring:message code="label.home" /></a></li>
						<li><a href="?lang=en">English</a></li>
						<li><a href="?lang=jp">Japanese</a></li>
						<li><a href="http://www.capgemini.com/about-capgemini" TARGET="_NEW"><img src="images/about-icon.png" width="20" height="20" alt="" /> <spring:message code="label.aboutus" /></a></li>
						<li><a href="http://www.in.capgemini.com/contact-capgemini" TARGET="_NEW"><img src="images/contact-icon.png" width="20" height="20" alt="" /> <spring:message code="label.contact" /></a></li>
						<li><a href="./logout?logout=true"><img src="images/logout-icon.png" width="20" height="20" alt="" /><spring:message code="label.logout" /></a></li>
					</ul>
				</nav>
			</div>
			<div class="welcome">
				<h5>
					<spring:message code="label.welcome" />
					<%
					out.println((String) session.getAttribute("UserId"));
				%>
				</h5>
			</div>
		</section>
		<section class="navigation">
			<div class="logo">
				<img src="images/logo-cap.jpg" class="logo" width="220" height="70" alt="" />
			</div>	
			<div class="main-nav">
				<nav>
					<ul>
						<li id="tdm_governance"><a id="tdm_governance1"
							href="./indexGovn"><spring:message code="label.tdmgovernance" /></a></li>
						<li id="tdm_life_cycle"><a id="tdm_life_cycle1"
							href="./index"><spring:message code="label.tdmlifecycle" /></a></li>
						<li id="tdm_command_center"><a id="tdm_command_center1"
							href="./indexCmdCtr"><spring:message code="label.commancenter" /></a></li>
						<li id="tdm_training"><a id="tdm_training1" href="#"><spring:message code="label.training" /></a></li>
					</ul>
				</nav>
			</div>
		</section>
		<section class="title-band">
			<div class="title">
				<h3 class="h3Tdm"><spring:message code="label.application" /></h3>
			</div>
			<div class="quicklink-container">
				<div class="quicklink-sh">
					<a href="#"><spring:message code="label.quicklink" /></a>
				</div>
				<div class="quicklink-list">
					<ul>
						<li><a href="#"><spring:message code="label.sensitiverprofile" /></a></li>
						<li><a href="./tdmDataMaskingNew"><spring:message code="label.maskingrequest" /></a></li>
						<li><a href="./tdmOnboardReq"><spring:message code="label.onboarding" /></a></li>
						<li><a href="./tdmChangeReqExt"><spring:message code="label.changerequest" /></a></li>
						<li><a href="./policyProp"><spring:message code="label.ftdlabelql" /></a></li>
						<li><a
							href="http://in-pnq-coe05/qlikview/FormLogin.htm?opendocqs=%3Fdocument%3DTDM%2520Dashboard%2520V1.qvw%26host%3DQVS%2540in-pnq-coe05"
							TARGET="_NEW"><spring:message code="label.live" /></a></li>
					</ul>
				</div>
			</div>
		</section>
	</header>
</section>