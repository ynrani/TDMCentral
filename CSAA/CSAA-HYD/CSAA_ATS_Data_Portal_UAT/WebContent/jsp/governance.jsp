<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <jsp:include page="headerNew.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ATS Data Central - Data Generation</title>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/governance.css">
<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.min.css">
 <link rel="stylesheet" type="text/css" href="css/stickyfooter.css" >
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
</head>
<body>
<div class="loaderDiv" style='display: none;'></div>
	<!-- <header class="main-header">
        <div class="row">
            <div class="col-xs-2">
                <div class="logo" title="Home">
                    <a href="javascript:void(0)"></a>
                </div>
            </div>
            <div class="col-xs-10">
                <nav class="ats-main-nav">
                    <ul class="pull-right">
                        <li>
                            <span>Welcome XYZ Last login 02/22/2016 12:06AM</span>
                        </li>
                        <li>
                            <a href="javascript:void(0)">ATS Data Support</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" id="logout">Logout</a>
                        </li>
                    </ul>
                </nav>
                <div class="ats-sub-nav">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="page-menu">
                                <div class="col-xs-11">
                                    <nav class="other-nav land-nav">

                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>-->
    <main>
    	<div class="container-fluid">
			<div class="row">
				<div class="gov-parent">
					<div class="gov-banner">
						<h1 class="header-title">ATS Data Governance</h1>
					</div>
					<div class="bottom-space">
					
					</div>	
				</div>
				
			</div>
			<div class="row gov-tabs-parent">
				<div class="col-xs-3">
					<a href="#gettingStarted" class="gov-tabs" id="getStartId">
						<i class="fa fa-arrow-circle-o-down"></i>
						<span>Getting Started</span>
					</a>
				</div>
				<div class="col-xs-3">
					<a href="#atsDataScope" class="gov-tabs" id="dataScopeId">
						<i class="fa fa-arrow-circle-o-down"></i>
						<span>ATS Data Scope</span>
					</a>
				</div>
				<div class="col-xs-3">
					<a href="#dataServices" class="gov-tabs" id="dataServiceId">
						<i class="fa fa-arrow-circle-o-down"></i>
						<span>Data Services</span>
					</a>
				</div>
				<div class="col-xs-3">
					<a href="#training" class="gov-tabs" id="trainingId">
						<i class="fa fa-arrow-circle-o-down"></i>
						<span>Training</span>
					</a>
				</div>
			</div>
		</div>
		<div class="gov-body">
			<div id="gettingStarted" class="gov-main">
				<div class="container">
					<h2>Getting Started</h2>
					<p align="justify">ATS Data is part of the Application Testing Services (ATS) and was formed to provide Test Data to different projects within CSAA. It is a centralized function which provides Test Data as a service to the requesting consumer groups. A centralized function for Test Data enables consolidation of effort and increases efficiency through Test Data lifecycle automation, defined controls and a standardized operating model.
					<br/>
					<br/>
					<b>ATS Data Central</b>, is leveraged as an online platform, to respond to Test Data requests from the different consumer groups. Through the portal consumers will be able to log a request for data, track the progress, and communicate with the team. Consumers can use the portal to access 5 types of Test Data services as shown below: </p>
					<div class="gs-body">
						<img class="gov-image getting-started-img" src="images/governance/Governance_image2.png" alt="Service Catalogue">
						<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Getting%20Started.aspx" target="_blank" class="gov-learn-more">Learn More..</a>
					</div>
				</div>		
			</div>	
			<div id="atsDataScope" class="gov-main other-section">
				<div class="container">
					<h2>ATS Data Scope</h2>
					<p align="justify">The below represents the different components of ATS Data that are planned to be built across multiple phases. Currently phase 1 is being executed (Jan 2016 to Jun 2016) for supporting test data requirements for PAS application.</p>
					<div class="img-detail-box">
						<img class="gov-image ats-data-scope" src="images/governance/ATS_Data_Scope.png" alt="ATS Data Scope">
					</div>
					<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Phase%201%20Scope.aspx" target="_blank" class="gov-learn-more">Learn More..</a>
				</div>		
			</div>
			<div id="dataServices" class="data-sevices-bg other-section">
				<h2>Data Services</h2>
				<div class="gov-ser-icons container">
					<div class="row">
						<a href="#testDataGen" class="gov-service col-xs-4" id="testDataID">
							<i class="fa fa-wrench"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Test Data Generation</span>
							</p>
						</a>
						<a href="#dataMining" class="gov-service col-xs-4" id="dataMiningId">
							<i class="fa fa-database"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Data Mining</span>
							</p>
						</a>
						<a href="#dataSubset" class="gov-service col-xs-4" id="dataSubsetId">
							<i class="fa fa-pie-chart"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Data Subset</span>
							</p>
						</a>
						<a href="#dataMasking" class="gov-service col-xs-4" id="dataMaskId">
							<i class="fa fa-lock"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Data Masking</span>
							</p>
						</a>
						<a href="#sensitivityProfiling" class="gov-service col-xs-4" id="serviceProfileId">
							<i class="fa fa-eye"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Sensitivity Profiling</span>
							</p>
						</a>
						<a href="#dataRefresh" class="gov-service col-xs-4" id="datarefId">
							<i class="fa fa-refresh"></i>
							<p class="data-ser-text">
								<i class="fa fa-arrow-circle-o-right"></i>
								<span>Data Refresh/Copy</span>
							</p>
						</a>
					</div>
				</div>
				
			
			</div>
			<div id="dataServicesSub" class="other-section">
					<div id="testDataGen" class="odd-data-service">
						<h2 class="data-ser-heading">Test Data Generation</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Through the Test Data Generation service consumers can request for creation of test data if it is not found by Data Mining service. To support data generation three ways are used to fulfill the request:</p>
								<div align="justify" class="data-ser-summary test-data-bullets">
								 	<ul>
								 		<li>Data subset from production after masking (Preferred for large volume data, with different combinations)</li>
								 		<li>Create test data through UI based automation scripts (Preferred for large volume data with similar combinations)</li>
								 		<li>Manually create data through the UI for low volume requests (Preferred for low volume data with unique combinations)</li>
									</ul>
								</div>
								<p align="justify" class="data-ser-summary">To raise a request for Data Generation, a consumer logs into the Data Central application, fills in the required details as requested by the application and submits it for fulfillment. In ATS Data Central, user has the option of choosing data creation from a list of predefined scenario's or manually entering the scenario that is required for creating the data.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Test%20Data%20Generation.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Generation.aspx" target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="odd-img-parent">
									<i class="fa fa-wrench odd-img-style"></i>
								</div>
							</div>
						</div>
					</div>
					<div id="dataMining" class="even-data-service other-section">
						<h2 class="data-ser-heading">Data Mining</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Data mining service is a self service utility that allows users to search for test data through an easy to use interface. In the backend, the application uses queries to search for the required data in the databases that otherwise is not possible to search using the application UI layer. </p>
								<p align="justify" class="data-ser-summary">The users are presented with search criteria fields, by choosing the required values from the search criteria fields users build a query that is then used to search the application database and retrieve the required data. For example a user can search for a Policy giving the filter conditions for status of policy, state, product type, coverages etc.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Mining%20and%20Reservation.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Mining%20and%20Reservation%20Workflow.aspx" target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="even-img-parent">
									<i class="fa fa-database even-img-style"></i>
								</div>
							</div>
							<div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/></div>
						</div>
					</div>
					<div id="dataSubset" class="odd-data-service other-section">
						<h2 class="data-ser-heading">Data Subset</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Data subset is the process of loading a reduced set of data from source to a destination database. This typically involves copy data from a production like database called as Test Master to the different test environment databases. The test master is a superset replica of the production database that has been masked to remove sensitive information as per CSAA security guidelines. Data subset is achieved by copying data from test master to the different test environment databases based on subset scenario's. For example a subset scenario could be need of policies with claims data from the last 6 months to be copied to a application test region DB for testing.</p>
								<p align="justify" class="data-ser-summary">
								ATS data central has the ability to intake request for data subset, service the request and notify the user once data has been loaded into the requested database.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Subset.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Subset%20Workflow.aspx" target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="odd-img-parent">
									<i class="fa fa-pie-chart odd-img-style"></i>
								</div>
							</div>
						</div>
					</div>
					<div id="dataMasking" class="even-data-service other-section">
						<h2 class="data-ser-heading">Data Masking</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Data masking is a technique used to identify sensitive data and then select appropriate mask formats for the sensitive data. Data Masking is used to replace the sensitive fields in the production copy with realistic but scrubbed data. Enterprises run the risk of breaching sensitive information when copying production data into non-production environments for the purposes of application development, testing or data analysis.</p>
								<p align="justify" class="data-ser-summary">Oracle Enterprise Manager (OEM) - Data Masking helps reduce this risk by irreversibly replacing the original sensitive data with fictitious data so that production data can be shared safely with non-production users.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Masking.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Masking%20Workflow.aspx" target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="even-img-parent">
									<i class="fa fa-lock even-img-style"></i>
								</div>
							</div>
						</div>
					</div>
					<div id="sensitivityProfiling" class="odd-data-service other-section">
						<h2 class="data-ser-heading">Sensitivity Profiling</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Sensitive profiling involves studying the meta data of an application to identify all the sensitive fields in the application. It uses a light weight Java code to study the meta data and identifies the sensitive fields in each of the relational as well as non relational tables of an application.</p>
								<p align="justify" class="data-ser-summary">
								The tool produces a easy to use report, which is then validated against CSAA security standards as defined in SR-045. Any gaps in the protection of sensitive elements is highlighted.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Sensitivity%20Profiling.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Sensitivity%20Profiling%20Workflow.aspx" target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="odd-img-parent">
									<i class="fa fa-eye odd-img-style"></i>
								</div>
							</div>
						</div>
					</div>
					<div id="dataRefresh" class="even-data-service other-section">
						<h2 class="data-ser-heading">Data Refresh/Copy</h2>
						<div class="row">
							<div class="col-xs-8 data-text">
								<p align="justify" class="data-ser-summary">Data Refresh services involves loading data into the required environment. Data Refresh occurs from the Test Masters that have been established to replicate production data copies after masking. During Data Refreshes consumers can request for a new data load in an environment, i.e. wipe and load new production data into the required environment.</p>
								<p align="justify" class="data-ser-summary">Data Refresh service also features reset of data to a point in time based on in built Oracle data archival functionality. Consumers can request restoring the data back into an environment to a certain point of time based on the supported archives to roll back testing due to defect fixes.</p>
								<div class="service-links">
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Refresh.aspx" target="_blank" class="sharepoint">Learn More</a>
									<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/Data%20Refresh%20and%20Copy%20Workflow.aspx"  target="_blank" class="show-flow">Flow Diagram</a>
								</div>
							</div>
							<div class="col-xs-4 data-image">
								<div class="even-img-parent">
									<i class="fa fa-refresh even-img-style"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			<div id="training" class="gov-main other-section">
				<div class="container">
					<h2>Training</h2>
					<p align="justify">To enable consumers to successfully use ATS Data Central detailed training material have been created. Training artifacts in the form of guided documents and videos are present in the SharePoint organized by the service line it represents. The training material helps the consumers to understand the scope of ATS data functionality along with step by step guide to how to use the application.</p>
					<p align="justify">The training artifacts also contains learning material on the applications supported. For example, you can learn about how to issue a policy in PAS by following the screenshots and videos posted here.</p>
					<div class="img-detail-box">
						<img class="gov-image train-img" src="images/governance/Training_New.PNG" alt="Service Catalogue">	
					</div>
					<a href="http://aaagateway/sites/TestingServices/TDM/_layouts/15/start.aspx#/SitePages/ATS%20Data%20Library.aspx" target="_blank" class="gov-learn-more">Learn More..</a>
				</div>		
			</div>
		</div>
    </main>
			
	<footer class="footer home-footer land-footer">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <div class="nav-links">
                        <div class="copyright">&#169; 2016. All rights reserved</div>
                        <ul>
                            <li>
                                <a href="./governance#gettingStarted">About Us</a>
                            </li>
                            <li>
                                <a href="./contactus">Contact Us</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">Site Map</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </footer>
	
	<a href="javascript:void(0)" id="back-to-top"><i class="fa fa-arrow-circle-o-up"></i></a>
	<script src="js/landing.js"></script>
	<script src="js/stickyfooter.js"></script>
	<script src="js/governance.js"></script>
</body>
</html>