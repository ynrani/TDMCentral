$(document).ready(function(){

	if(window.location.hash=="#gettingStarted")
	{
		$('html, body').animate({
		      scrollTop: ($(window.location.hash).offset().top) - 100
		    }, 800, function(){
		   
		      
			});
	}
	
	var currentHash = window.location.hash;
	if(currentHash)
	{
		$("#atsDataScope,#training,#gettingStarted,#dataServices,#testDataGen,#dataMining,#dataSubset,#dataMasking,#sensitivityProfiling,#dataRefresh,#dataServicesSub").hide();
		if(currentHash=="#dataServices"){
			$("#dataServices").show();	
			$("#dataServicesSub").show();
			$("#testDataGen").show();	
		}
		else if(currentHash=="#testDataGen" || currentHash=="#dataMining" || currentHash=="#dataSubset" || currentHash=="#dataMasking" || currentHash == "#sensitivityProfiling" || currentHash=="#dataRefresh")
		{
			$("#dataServices").show();	
			$("#dataServicesSub").show();
			$(currentHash).show();
		}
		else
		{
			$(currentHash).show();
		}
	}

	$("#testDataID,#dataMiningId,#dataSubsetId,#dataMaskId,#serviceProfileId,#datarefId,#dataScopeId,#trainingId,#getStartId,#dataServiceId").click(function(){
		event.preventDefault();

    	var hash = this.hash;
    	
		if(window.location.hash==""){
    		$("#gettingStarted").hide();    		
		}
		else
		{
			if(window.location.hash=="#dataServices" || window.location.hash=="#testDataGen" || window.location.hash=="#dataMining" || window.location.hash=="#dataSubset" || window.location.hash=="#dataMasking" || window.location.hash=="#sensitivityProfiling" || window.location.hash=="#dataRefresh"){
				if(hash=="#atsDataScope" || hash=="#training" || hash=="#gettingStarted"){
					$("#dataServicesSub,#dataServices,#sensitivityProfiling,#dataMining,#dataSubset,#dataMasking,#dataRefresh").hide();
					$("#testDataGen").show();	
				}
				else
				{
					if(window.location.hash=="#dataServices")
					{
						$("#testDataGen").hide();	
					}
					else
					{
						$(window.location.hash).hide();	
					}
				}		
			}
			else
			{
				$(window.location.hash).hide();	
			}
			
		}
		if(hash=="#dataServices")
		{
			$("#dataServicesSub").show();
			$("#testDataGen").show();
		}
		$(hash).show();
		

		$('html, body').animate({
	      scrollTop: ($(hash).offset().top) - 100
	    }, 800, function(){
	   
	      window.location.hash = hash;
		});	
	});

	var scrollTrigger = 100, // px
        backToTop = function () {
            var scrollTop = $(window).scrollTop();
            if (scrollTop > scrollTrigger) {
                $('#back-to-top').addClass('show');
            } else {
                $('#back-to-top').removeClass('show');
            }
        };
    backToTop();
    $(window).on('scroll', function () {
        backToTop();
    });
    $('#back-to-top').on('click', function (e) {
        e.preventDefault();
        $('html,body').animate({
            scrollTop: 0
        }, 700);
    });
    
    var calculatedHeight = $("footer").innerHeight()+"px";
    $('#back-to-top').css("bottom",calculatedHeight);
});