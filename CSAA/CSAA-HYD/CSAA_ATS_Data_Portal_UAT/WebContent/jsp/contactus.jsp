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
	<title>ATS Data Central - Contact Us</title>
	<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
	<link rel="shortcut icon" href="images/favicon.ico" >
	<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/font_awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	 <link rel="stylesheet" type="text/css" href="css/stickyfooter.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.faloading.min.css" />
	<link rel="stylesheet" type="text/css" href="css/contactus.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="lib/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
</head>
<body>
	<main>
		<div class="container">
			<h1 class="contact-us">Contact us</h1>
	 		<p class="contact-info">We encourage you to share your ideas and improvements with us.</p>
	 		<p class="contact-info">Please see the contact information</p>
	 		<div class="row">
	 			<div class="col-xs-offset-3 col-xs-4">
	 				<address>
					  	<strong>Deepak Shrivastava,</strong>
					  	<br>
					  	<a href="javascrip:void(0)">Deepak.X.Srivastava@csaa.com</a>
					</address>
	 			</div>
	 			<div class="col-xs-4">
	 				<address>
					  	<strong>Anusha Srinivasan</strong>
					  	<br>
					  	<a href="javascrip:void(0)">Anusha.Srinivasan@csaa.com</a>
					</address>
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
	<script src="js/landing.js"></script>
	<script src="js/stickyfooter.js"></script>
	<script>
	function showLoader() {
		$(".loaderDiv").css('display', 'block');
		$(".loaderDiv").width($('html').width() + 'px');
		$(".loaderDiv").height($('html').height() + 'px');
		$(".loaderDiv").faLoading();
	}
	function hideLoader() {
		$(".loaderDiv").faLoading(false);
		$(".loaderDiv").css('display', 'none');
	}
	</script>	
</body>
</html>