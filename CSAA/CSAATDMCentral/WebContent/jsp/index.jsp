<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TDM Central | Index</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script src="js/html5.js"></script>
<link href="css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container tdm-central">
				<ol class="breadcrumb">
					<li><a class="hrefVisited" href="./index">Home</a></li>
					<li>/</li>
					<li class="active">TDM Life Cycle</li>
				</ol>
				<div class="gridCntr">
					<!-- Thumbnail for Demand -->
					<diV class="thumbnail gutter demand disableDivCls">
						<h4>Demand</h4>
						<p>Test data Request in take from consumers of the TDM
							services</p>
						<ul>
							<li><a >Masking Request</a></li>
							<li><a >TDM On-boarding Request</a></li>
							<li><a >Change Request</a></li>
							<li><a >Sub Setting Request</a></li>
							<li><a >Data Refresh Request</a></li>
						</ul>
	 
					</diV>
					<!-- /Thumbnail for Demand -->
					<!-- Thumbnail for Design -->
					<diV class="thumbnail gutter design disableDivCls">
						<h4>Design</h4>
						<p>Design required test data as per test data demand from
							testing team</p>
						<ul>
							<li><a >Estimation Tool</a></li>
							<li><a href="http://10.102.22.77:80/SensitivityProfilerApp" TARGET="_NEW">Sensitivity
									Profiler</a></li>
						</ul>
					</diV>
					<!-- /Thumbnail for Design -->
					<!-- Thumbnail for Prepare -->
					<diV class="thumbnail gutter prepare disableDivCls">
						<h4>Prepare</h4>
						<p>Prepare required test data as per design and test data need
							from testing team</p>
						<ul>
							<li><a href="http://in-pnq-coe13/SensitivityProfilerApp" TARGET="_NEW">DMASSK</a></li>
							
						</ul>
					</diV>
					<!-- /Thumbnail for Prepare -->
					<!-- Thumbnail for Provision -->
					<diV class="thumbnail gutter provision">
						<h4>Provision</h4>
						<p>Publish required test data to testing team to carry out
							testing</p>
						<ul>
							<li><a href="./policyProp">Find Test Data and
									Reservation</a></li>
					 		 
						</ul>
					</diV>
					<!-- /Thumbnail for Provision -->



				</div>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('tdm_life_cycle1');
		
		
		
		 $(document).ready(function() {
			    $('#provCatgType').change(function() {
			        var selectedValue = $(this).val();
			        var servletUrl = 'testdaSpecility?value=' + selectedValue;

			        $.getJSON(servletUrl, function(options) {
			             var provSpec = $('#provSpecType');
			            $('>option', provSpec).remove(); // Clean old options first.
			            if (options) {
			            	 provSpec.append($('<option/>').text("All"));
			                 $.each(options, function(value, value) {
			                	provSpec.append($('<option/>').val(value).text(value));
			                });
			            } else {
			            	provSpec.append($('<option/>').text("Please select Category"));
			            }
			        });
			    });
			}); 
		    
	</script>
</body>
</html>
