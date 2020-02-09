<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1">
<meta name="robots" content="noodp " />
<title>ATS Data Central</title>
<meta name="description"
	content="Reach out to ATS Data Central for all your test data needs" />
<link rel="shortcut icon" href="images/favicon.ico" />
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.6-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/zoho-home-new.css">
<link type="text/css" rel="stylesheet"
	href="css/jquery.faloading.min.css" />
<script type="text/javascript" src="js/jquery-common.js"></script>
<script type="text/javascript" src="js/zsa.js"></script>
<script type="text/javascript" src="js/jquery-plus-ui.js"></script>
<!-- <script type="text/javascript" src="js/query.fitvids.js"></script> -->
<script type="text/javascript" src="js/prd-common.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<script type="text/javascript" src="js/chosen.jquery.min.js"></script>
<!-- <script type="text/javascript" src="js/signup.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="lib/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="js/jquery.faloading-0.2.min.js"></script>
<style type="text/css">

/* FOOTER */
.main-section {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	overflow: hidden;
	position: relative;
	z-index: 6;
}

.footer {
	background: #fff none repeat scroll 0 0;
	width: 100%;
	border: none;
	padding: 0;
	position: relative;
}

.footer-links li {
	color: #aaa;
	font-weight: 700;
}

.common-footer li a, .common-footer p {
	color: #999;
}

.common-footer li {
	border-left: 1px solid #444;
}

.footer-links li a {
	color: #999;
}

.footer-links {
	border-bottom: 1px solid #444;
}

.input-focus.light-theme:after {
	background: rgba(255, 255, 255, 0.5);
}

.signup-section {
	border: none;
}

.overlay-loading {
	background-color: #fff;
	background-image: url("../images/ajax_loader.gif");
	background-position: center center;
	background-repeat: no-repeat;
	height: 100%;
	left: 0;
	opacity: 1;
	position: fixed;
	top: 0;
	visibility: visible;
	width: 100%;
	z-index: 9999;
}

.userinfo-container, .userinfo-container .goto-link {
	display: none !important;
}
html,body, .main-section, .main-content, .home-bg
{
	height:100%;
}
.home-bg
{
	width:100%;
}
.header
{
	background-size:cover;
	height:100% !important;
}
.footer-section
{
	bottom:0; 
	position:fixed;
}
</style>
</head>
<body>
	<div class="loaderDiv" style='display: none;'></div>


	<!-- MAIN DIV STARTS-->

	<div class="main-section">
		<div class="loadingImg"></div>
		<!-- SIGNUP SECTION STARTS -->
		<div class="signing dark-theme"></div>
		<div class="signup-section dark-theme">
			<div class="loggedin-userinfo"></div>

			<!-- SIGNUP PART RIGHT SIDE STARTS -->

			<div class="signup-box">
				<div class="logo1 logo-align">
					<span></span>
				</div>
				<br></br>
				<p class="signup-heading">Login to ATS Data Central</p>

				<div id="pwdstrength"></div>
				<!--<form  name="signupform" method="post" class="form">-->
				<form:form id="loginForm" name="loginForm"
					action="${pageContext.request.contextPath}/tesdaLogin"
					method="post" autocomplete="on" class="form">
					<section class="signupcontainer">
						<div class="za-email-container sgfrm">
							<span class="placeholder">User Name</span>
							<!-- <input type="email"   name="email" class="form-input sgnemail" tabindex="1" id="emailfield" onkeypress="hideMsg(this)" value=""  > -->
							<input id="userid" name="userid" class="form-input sgnemail"
								tabindex="1" onkeypress="hideMsg(this)" required="required"
								type="text" class="form-control" required />
						</div>
						<div class="za-password-container sgfrm">
							<span class="placeholder">Password</span>
							<!--  <input type="password"   name="password" id="password" class="form-input sgnpaswrd" onkeyup="checkPasswordStrength(8)" onfocus="hideMsg(this);showPwdMsg()" onblur="hideLenError(this,8)" tabindex="1" maxlength="250"> -->
							<input id="password" name="password" required="required"
								type="password" class="form-input sgnpaswrd"
								onkeyup="checkPasswordStrength(8)"
								onfocus="hideMsg(this);showPwdMsg()"
								onblur="hideLenError(this,8)" tabindex="1" required />
							<div class="field-msg">
								<div onclick="togglePasswordField(8);" id="show-password"
									class="column show-password">
									<span id="show-password-icon" class="icon-medium uncheckedpass"></span>
									<label id="show-password-label">Show</label>
								</div>
								<p class="message">
									<span id="errormg" class="pwderror">Minimum 8 characters</span>
								</p>
								<div class="pwdparent">
									<div id="pwdstrength"></div>
									<div class="pwdtext"></div>
								</div>
							</div>
						</div>
						<div class="sgnbtnmn">
							<div class="sgnbtn">
								<input id="submitBtn" type="Submit" tabindex="1" class="signupbtn"
									value="Login" />
								<div class="login-error">
									<p>
										<%
						if (request.getParameter("auth") != null
									&& request.getParameter("auth").equals("fail")) {
								out.println("Invalid Username or Password");
							} else if (request.getParameter("logout") != null
									&& request.getParameter("logout").equals("true")) {
								out.println("Logged out Successfully");
							} else if (request.getParameter("session") != null
									&& request.getParameter("session").equals("expired")) {
								out.println("Session Expired");
							} else if (request.getParameter("session") != null
									&& request.getParameter("session").equals(
											"alreadyLogged")) {
								out.println("User Already Logged In");
							}
					%>
									</p>

								</div>
								<div class="loadingImg"></div>
							</div></br></br>
							<div class="sign_agree">
								New to ATS Data Central? <a id="registerLink" href="./register"
									data-toggle="modal" data-target="#registerPage">Start here.</a>
							</div>
						</div></br>
						<div class="footer-section">
						<ul>
							<li><a href="#" id="contactUsLink">Contact Us</a></li>
						</ul>
						<div class="footer-logo">
							<p><a href="https://www.csaa-insurance.aaa.com/">CSAA</a> &copy;2016 CSAA Insurance Group.</p>
							
						</div>
						</div>
					</section>
				</form:form>
			</div>
			<%
									if (request.getAttribute("Err_msg") != null) {
								%>

			<%=(String) request.getAttribute("Err_msg")%>


			<%
									}
								%>
			<!-- SIGNUP PART RIGHT SIDE ENDS -->

			<div class="testi-video">
				<p>
					<span class="video-caption"></span><span class="testi-video-thumb"></span>
				</p>
			</div>
		</div>

		<!-- SIGNUP SECTION ENDS -->

		<!-- MAIN CONTENT STARTS -->
		<div class="main-content">

			<!-- HOME BG SECTION STARTS-->

			<div class="home-bg">

				<!-- BANNER PART STARTS-->

				<div class="header for-world">
					<div class="banner-content">
						<div class="logo">
							<span>ATS Data</span>
						</div>
						<h1></h1>
						<br> </br> <br> </br> <br> </br> <br> </br> <br> </br> <br/> </br>
						<div class="signup-button-part">
							<a class="signup-button" href="">Login to ATS Data Central</a> <span></span>
						</div>
					</div>
				</div>

				<!-- BANNER PART ENDS-->
			</div>
		</div>
		<!-- HOME BG SECTION ENDS-->
	</div>
	<!-- MAIN CONTENT ENDS -->

	<!-- FOOTER SECTION STARTS -->
	<!-- 
  <div class="footer">

    <div class="common-footer">
      <ul>
        <li><a href="">Contact</a></li>
        <li><a href="">Terms of Service</a></li>
        <li><a href=""> Privacy Policy</a></li>
      </ul>
      <p>&copy; 2016 AAA, All Rights Reserved.</p>
      <div class="footer-logo"> <a href="https://www.csaa-insurance.aaa.com/">CSAA</a> </div>
    </div>
  </div>

  FOOTER SECTION ENDS

</div> -->

	<!-- MAIN DIV ENDS -->

	<!-- SCRIPT SECTION STARTS -->
	<div class="modal fade" tabindex="-1" role="dialog" id="registerPage">
		<div class="modal-dialog modal-lg">
			<div class="modal-content reg-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h2 class="new-user">
						<a href="./register" data-toggle="modal"
							data-target="#registerPage">Start here.</a>
					</h2>
				</div>
			</div>
		</div>
	</div>
	<script>
$(document).ready(function(e) {

getCountry=true;
$(".signup-box").hover(function(){
    $('.signup-section').addClass('input-focus');
    }, function(){
    $('.signup-section').removeClass('input-focus');
});

$('input').on('blur', function(){
	 var getTextval=$(this).val()
	 if(getTextval == "" || getTextval == null){

	 $(this).parent().removeClass('added-placeholder');
	 }
	 else{$(this).parent().addClass('added-placeholder')}
  	}).on('focus', function(){
		var getTextval=$(this).val()
		if(getTextval == "" || getTextval == null){

	$(this).parent().removeClass('').addClass('added-placeholder');
}
else{$(this).parent().addClass('added-placeholder')}
    });


var getTextval=$('#emailfield').val();
if(getTextval == "" || getTextval == null){
	 $('#emailfield').parent().removeClass('added-placeholder');
}
	 else{ $('#emailfield').parent().addClass('added-placeholder')}


findHost();

function findHost(){
	var m = window.location.href;
	m = m.replace("http://www.", '');
	m = m.replace("https://www.", '');
	m = m.replace("http://", '');
	m = m.replace("https://", '');
	host = m.split(".com")[0];
}



//*** changed the random video and bg  **//
	jQuery('.signup-section').css({'opacity':1});
//})

if(getCountry){
$('.overlay-loading').css('display','none');
}
});

</script>
	<script>
 var clearInt="";
var clearInt1="";
 /* var innerwidth = window.innerWidth ;
if(innerwidth > 400){
  	wHeight=window.innerHeight;
 	footerTop=$('.footer').offset().top;
} */


$(document).ready(function(e) {
		myVideo = document.getElementById("");
		trigger=true;
		checkWindowHeight();
		scTop=$(window).scrollTop();
		wHeight=window.innerHeight;
		//setSignuppart();
	 	var zIndex1=0;
		var count1=0;
		var chiLength1=$('.flip-part').children().length-1;
		var getChild1=$('.flip-part').children();
		startTextChanges(count1,chiLength1,getChild1);
		var zIndex=0;
		var count=0;
		var chiLength=$('.timeline-holder').children().length-1;
		var getChild=$('.timeline-holder').children();
		startAnimation(count,chiLength,getChild);
		$('.slider-dots span').click(function(){
			var navIndex=$(this).index();
			stopAnimation(navIndex,chiLength,getChild)
		})

  $(window).scroll(function () {

		 scTop=$(window).scrollTop();
		 wHeight=window.innerHeight;
		 //setSignuppart()
		 if(scTop>$('.about-zoho-inner').offset().top-wHeight){
		$('.software-craft-part').addClass('animated-svg');
		if($('.software-craft-part').hasClass('animated-svg'))
		{
			setTimeout(function(){
				$('.software-craft-part').addClass('auto-rotate');
			},200);
		}
	}
	});

});



function setSignuppart(){

	if(window.innerWidth>=1200){
	/* 	fTop=$('.footer').offset().top; */

	if(scTop>fTop-wHeight){
		kk=scTop-(fTop-wHeight)
		$('.signup-section').css({'top':(0-kk)});
		$('.signing').hide();
		$('.testi-video').css({'opacity':'0', 'transition':'0.5s ease', '-webkit-transition':'0.5s ease'});
	}
	else{
		kk=0;
		$('.signup-section').css({'top':(kk)});
		$('.signing').show();
		$('.testi-video').css({'opacity':'1', 'transition':'0.5s ease', '-webkit-transition':'0.5s ease'});
	}

	}
}

function startAnimation(count,chiLength,getChild){
		if(clearInt=="")
		{
	clearInt=window.setInterval(function(){
			count+=1;
			if(count<=chiLength)
			{
			clickShow(count,chiLength,getChild)
			}
			else
			{
				count=0;
				clickShow(count,chiLength,getChild)
			}
		},4200)
		}
}

function stopAnimation(navIndex,chiLength,getChild){
	clickShow(navIndex,chiLength,getChild)
	if(clearInt!=""){
			window.clearInterval(clearInt);
			clearInt="";
			setTimeout(function(){
				startAnimation(navIndex,chiLength,getChild)
			},100)
	}
}
function clickShow(navIndex,chiLength,getChild)
{
	var navChild=$('.slider-dots').children();
	$(navChild[navIndex]).addClass('selected').siblings().removeClass('selected');
	$(getChild[navIndex]).addClass('opacits').siblings().removeClass('opacits');

}

function startTextChanges(count1,chiLength1,getChild1){
		if(clearInt1=="")
		{
	clearInt1=window.setInterval(function(){
			count1+=1;
			if(count1<=chiLength1)
			{
			clickshowtext(count1,chiLength1,getChild1)
			}
			else
			{
				count1=0;
				clickshowtext(count1,chiLength1,getChild1)
			}
		},3200)
		}
}
 function clickshowtext(navIndex1,chiLength1,getChild1)
{
 	$(getChild1[navIndex1]).addClass('text-shows').siblings().removeClass('text-shows');
 }
function checkWindowHeight()
{
	if(window.innerWidth<=500)
	{
		$('.scroll-down').fadeOut();
		}

		else {
			if(window.innerHeight<=500 && window.innerHeight>=450)
			{
				$('.scroll-down').fadeIn();
			}
		}
 }
</script>
	
	<script type="text/javascript">
		function loginValidation() {
			var formObj1 = $("#loginForm");
			var buttonObj = formObj1.find('#submit');

			//form validation rules
			formObj1.validate({
				errorClass : "my-error-class",
				validClass : "my-valid-class",
				rules : {
					userid : {
						required : true,
						maxlength : 13
					},
					password : {
						required : true,
						maxlength : 22
					},
					projectId : {
						required : true,
						maxlength : 20
					}
				},
			});
		}
		loginValidation();

		window.history.forward();
		function noBack() {
			window.history.forward();
		}

		$(function() {
			$('[placeholder]').focus(function() {
				var input = $(this);
				if (input.val() == input.attr('placeholder')) {
					//input.val('');
					//input.removeClass('placeholder');
				}
			}).blur(
					function() {
						var input = $(this);
						if (input.val() == ''
								|| input.val() == input.attr('placeholder')) {
							//input.addClass('placeholder');
							//input.val(input.attr('placeholder'));
						}
					}).blur().parents('form').submit(function() {
				$(this).find('[placeholder]').each(function() {
					var input = $(this);
					if (input.val() == input.attr('placeholder')) {
						//input.val('');
					}
				})
			});
		});
		
		
		// popup for Registraion page
		 var isPopupRequired = '${ispopuprequired}';
		// alert("popup Value:"+isPopupRequired);
		if(isPopupRequired == "1")
		{
			BootstrapDialog.show({
				modal: true,
				title: "Registration Successful",
				resize:"auto",
				position: 'center',
				height:150,
				width:600,
				
				onshow: function(dialog) {
				  var markup = '<div>Registration Successfully Completed, ATS support team will get back to you</div>';
				  dialog.setMessage(markup);
				  dialog.getModalHeader().addClass('email-title');
				},
				buttons: [
				   {
					cssClass:"btn btn-primary",
					label:"OK",
					action: function(dialog) {
					 dialog.close();
					}
				  }
				]
			  });
		}
		
 		function resetRegistrationForm()
		{
	
			$("#userName").val('');
			$("#userId").val('');
			$("#username_availability_result").css('display','none');
			$("#userEmail").val('');
			$("#email_availability_result").css('display','none');
			$("#consumerGroup > [value='selected']").prop('selected',true);
			$("#manager").val('');
			$("#accessReason").val('');
		}
		
		$('#registerPage').on('hide.bs.modal', function(){
			resetRegistrationForm();
		});
		
		$("#submitBtn").click(function(){
			var formObj1 = $("#loginForm");
			if(formObj1.valid())
			{
				showLoader();
			}
			
		});
		hideLoader();
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
		
		$("#registerLink").click(function(){
			showLoader();
		});
		
		$("#contactUsLink").click(function(){
			
			var email = 'deepak.a.shrivastava@capgemini.com';
	        var subject = 'ATS Data - Contact';
	        var emailBody = 'Hi Deepak,';
	        window.location.href = "mailto:"+email+"?subject="+subject+"&body="+emailBody;
		});
		 
	</script>
</body>
</html>
