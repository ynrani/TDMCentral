$(document).ready(function(){
	resetRegistrationForm();

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

$('#registerPage').on('hide.bs.modal', function(){
	resetRegistrationForm();
	hideLoader();
});

$('#registerPage').on('show.bs.modal', function(){
	hideLoader();
});

$('#cnclId').click(function(){
	hideLoader();
});

$('#submitReq').click(function(){
	var formObj1 = $("#registrationForm");
	if(formObj1.valid())
	{
		showLoader();
	}
});

	var textBoxUserFilter = null, isRegisterUnable = false;
	$("#userId").bind("propertychange change keyup paste input", function(){
							clearTimeout(textBoxUserFilter);
							var searchValue = $('#userId').val();
							$("#submitReq").prop('disabled',true);
							if(searchValue != "")
							{
								textBoxUserFilter = setTimeout(function(){checkAvailUser(searchValue);}, 1000);
							}
							else
							{
								$("#username_availability_result").css('display','none');
							}
					});

	

	function checkAvailUser(userid) {
		//var userid = $('#userId').val()
		
		$.ajax({
			type : 'POST',
			url : './checkAvailability',
			 cache: false,
			data : {
				'userid' : userid,
			},
			success : function(response) {
			
				if (response == "SUCCESS") {
					$('#username_availability_result').html(
							userid + ' is available Continue your Registration');
					$("#username_availability_result").css('display','block');
					if(isRegisterUnable == true)
					{
						$("#submitReq").prop('disabled',false);	
					}
					isRegisterUnable = true;
					
				}
				else
				{
					
					$('#username_availability_result').html(
							userid + ' is not available Try with Another User Id');
					$("#username_availability_result").css('display','block');
					isRegisterUnable = false;
					
				}
			}
		})

	}
		
		
	var textBoxEmailFilter = null;
	$("#userEmail").bind("propertychange change keyup paste input", function(){
							clearTimeout(textBoxEmailFilter);
							var searchValue = $('#userEmail').val();
							$("#submitReq").prop('disabled',true);
							if(searchValue != "")
							{
								textBoxEmailFilter = setTimeout(function(){checkAvailEmail(searchValue);}, 1000);
							}
							else
							{
								$("#email_availability_result").css('display','none');
							}
					});
		
		
	//Checking email id
	function checkAvailEmail(emailId) {
	
		$.ajax({
			type : 'POST',
			url : './checkEmailAvailability',
			cache: false,
			data : {
			   'emailId' : emailId
			},
			success : function(response) {
			
				if (response == "SUCCESS") {
					$('#email_availability_result').html(
							emailId + ' is available Continue your Registration');
					$("#email_availability_result").css('display','block');
					if(isRegisterUnable == true)
					{
						$("#submitReq").prop('disabled',false);
					}
					isRegisterUnable = true;
				}
				else
				{
					
					$('#email_availability_result').html(
							emailId + ' is not available try with another Email ID');
					$("#email_availability_result").css('display','block');
					isRegisterUnable = false;
					
				}
			}
		})

	};