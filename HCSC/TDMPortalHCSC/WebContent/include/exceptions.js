function existingClaimVsCoverage()
{
	var isDisabled =false;
    $('#extClaim').next(".my-error-class").remove();
     var extClaims = $('#extClaim > option:selected');
     var coverages = $('#coverageCode > option:selected');    
     $('#extClaim').next(".my-error-class").remove();
     $('#coverageCode').next(".my-error-class").remove();	
     for(var j=0; j<extClaims.length;j++)
	     {
	       var coverage = coverages[j].value.toUpperCase();

	       if(extClaims.length > 1)
	        {
	    	   for(var i=0; i<extClaims.length;i++)
		        {
		          var extClaim = extClaims[i].value.toUpperCase();
		          if(extClaim == "ALL")
		          {
			          $('#extClaim').next(".my-error-class").remove();	          
		              $('#extClaim').after('<div class="my-error-class">You can not choose other options with "All".</div>');
		              $('input[type="submit"]').attr('disabled','disabled');
		              isDisabled =true;
			       }
		          else if(extClaim == "ANY")
		          {
		            $('#extClaim').next(".my-error-class").remove();	          
	                $('#extClaim').after('<div class="my-error-class">You can not choose other options with "Any".</div>');
	                $('input[type="submit"]').attr('disabled','disabled');
	                isDisabled =true;
		          }
		          else if(extClaim == "NONE")
		          {
		            $('#extClaim').next(".my-error-class").remove();	          
	                $('#extClaim').after('<div class="my-error-class">You can not choose other options with "None".</div>');
	                $('input[type="submit"]').attr('disabled','disabled');
	                isDisabled =true;
		          }	
		        }
	    	   
	    	   if(isDisabled)
	    		{
	    		   break;
	    		}
	        }      
	       
	       if(coverages.length > 1 && coverage =="ALL")
	        {
	    	   $('#coverageCode').next(".my-error-class").remove();	          
               $('#coverageCode').after('<div class="my-error-class">You can not choose other options with "All".</div>');
               $('input[type="submit"]').attr('disabled','disabled');
               isDisabled =true;
              break;
	        }
	       else if(coverages.length > 1 && coverage =="ANY")
	        {
	    	   $('#coverageCode').next(".my-error-class").remove();	          
               $('#coverageCode').after('<div class="my-error-class">You can not choose other options with "Any".</div>');
               $('input[type="submit"]').attr('disabled','disabled');
               isDisabled =true;
              break;
	        }
	       else if(coverages.length > 1 && (coverage =="VISION ONLY" || coverage =="PRESCRIPTION(RX) ONLY" || coverage =="DENTAL ONLY" || coverage =="MEDICAL ONLY" ))
	        {
	    	   $('#coverageCode').next(".my-error-class").remove();	          
              $('#coverageCode').after('<div class="my-error-class">You can not choose other options with '+coverage +'.</div>');
              $('input[type="submit"]').attr('disabled','disabled');
              isDisabled =true;
             break;
	        }
	       
	        /*if(coverage == "MEDICAL")
	        {
		        for(var i=0; i<extClaims.length;i++)
		        {
		          var extClaim = extClaims[i].value.toUpperCase();
		          if(extClaim == "DENTAL" || extClaim == "PRESCRIPTION" || extClaim=="RX" )
		          {
		           $('#extClaim').next(".my-error-class").remove();	          
	                $('#extClaim').after('<div class="my-error-class">User initially requested for Medical as coverage.</div>');
	                $('input[type="submit"]').attr('disabled','disabled');
	                isDisabled =true;
		          }
		        }
	        }
	        else if(coverage == "DENTAL")
	        {	        
	          for(var i=0; i<extClaims.length;i++)
		        {
		          var extClaim = extClaims[i].value.toUpperCase();
		          if(extClaim == "MEDICAL" || extClaim == "PRESCRIPTION" || extClaim=="RX" )
		          {
		            $('#extClaim').next(".my-error-class").remove();         
	                 $('#extClaim').after('<div class="my-error-class">User initially requested for Dental as coverage.</div>');
	                 $('input[type="submit"]').attr('disabled','disabled');
	                 isDisabled =true;
		          }
		        }	          
	        }
	        else if(coverage == "PRESCRIPTION" || coverage=="RX")
	        {	        
	         for(var i=0; i<extClaims.length;i++)
		        {
		        var extClaim = extClaims[i].value.toUpperCase();
		          if(extClaim == "MEDICAL" || extClaim == "DENTAL")
		          { 
		             $('#extClaim').next(".my-error-class").remove();
			         $('#extClaim').after('<div class="my-error-class">User initially requested Prescription as coverage.</div>');
			         $('input[type="submit"]').attr('disabled','disabled');
	                 isDisabled =true;
		          }
		        }
	        }
	        else if(coverage == "VISION")
	        {	        
	          for(var i=0; i<extClaims.length;i++)
		        {
		          var extClaim = extClaims[i].value.toUpperCase();
		          if(extClaim == "MEDICAL" || extClaim == "DENTAL" || extClaim == "PRESCRIPTION" || extClaim=="RX")
		          { 
		             $('#extClaim').next(".my-error-class").remove();
			         $('#extClaim').after('<div class="my-error-class">User initially requested for Dental as coverage.</div>');
	                 $('input[type="submit"]').attr('disabled','disabled');
	                 isDisabled =true;
		          }
		        }	           
	        }*/
	    }
     if(!isDisabled)
		{
			$('input[type="submit"]').removeAttr('disabled');
		}	
}



function subscriberStatusVsMemberType()
{	
	$('#subscStatus').next(".my-error-class").remove(); 
    var subsrbrStat = $("#subscStatus").val().toUpperCase();
    var memType = $("#subscRelation").val().toUpperCase();
		if((subsrbrStat == "SUBSCRIBER - ACTIVE WITH ACTIVE DEPENDENT(S)" || subsrbrStat=="SUBSCRIBER - ACTIVE WITH CANCELLED DEPENDENT(S)"
			|| subsrbrStat=="SUBSCRIBER - CANCELLED WITH CANCELLED DEPENDENT(S)"|| subsrbrStat=="SUBSCRIBER - CANCELLED WITH CANCELLED SPOUSE" ) && memType=="SUBSCRIBER ONLY")
		{
		   $('#subscStatus').after('<div class="my-error-class">User initially requested Subscriber Only information.</div>');
		   $('input[type="submit"]').attr('disabled','disabled');
		}
		else if(subsrbrStat == "SUBSCRIBER - CANCELLED" &&  memType=="SUBSCRIBER AND SPOUSE")
		{
		   $('#subscStatus').after('<div class="my-error-class">We cannot have a Cancelled Subscriber with an Active Spouse.</div>');
		   $('input[type="submit"]').attr('disabled','disabled');
		}
		else if(subsrbrStat == "SUBSCRIBER - CANCELLED" && memType=="SUBSCRIBER, SPOUSE AND DEPENDENT(S)")
		{
		   $('#subscStatus').after('<div class="my-error-class">We cannot have a Cancelled Subscriber with an Active Spouse and/or Active Dependents.</div>');
		   $('input[type="submit"]').attr('disabled','disabled');
		}
		else if(subsrbrStat == "SUBSCRIBER - CANCELLED" && memType=="SUBSCRIBER AND DEPENDENT(S)")
		{
		   $('#subscStatus').after('<div class="my-error-class">We cannot have a Cancelled Subscriber with Active Dependents.</div>');
		   $('input[type="submit"]').attr('disabled','disabled');
		}
		else if(subsrbrStat == "SUBSCRIBER - CANCELLED" && memType=="SUBSCRIBER AND DEPENDENT(S)")
		{
		   $('#subscStatus').after('<div class="my-error-class">We cannot have a Cancelled Subscriber with Active Dependents.</div>');
		   $('input[type="submit"]').attr('disabled','disabled');
		}
		else
		{
		   $('input[type="submit"]').removeAttr('disabled');
		}
}



function ageGroupVsMemCatVsMemType()
{	
	$('#ageGroup').next(".my-error-class").remove(); 
    var memType =$("#subscRelation").val().toUpperCase();        
    var memCat = $("#memCat").val().toUpperCase();
    var age = $("#ageGroup").val().toUpperCase();
		if((memCat == "GROUP" && age==">=13 AND <18" ) && memType=="SUBSCRIBER ONLY")
		{
			$('#ageGroup').after('<div class="my-error-class">We cannot have a Group Subscriber under the age of 18.</div>');
			$('input[type="submit"]').attr('disabled','disabled');
		}
		else if((memCat == "GROUP" && age=="<13" ) && memType=="SUBSCRIBER ONLY")
		{
		  $('#ageGroup').after('<div class="my-error-class">We cannot have a Group Subscriber under the age of 13.</div>');
		  $('input[type="submit"]').attr('disabled','disabled');
		}	  		
		else
		{
			$('input[type="submit"]').removeAttr('disabled');
		}
}


function showHidePlanType(flag, flag1)
{
	/* $('#fundLable').hide(); 
     $('#fundInd').hide();*/
	if(flag)
		{		
		   
		   // $("select").children('option[id="CPO"]').wrap('<span>').hide();		    
		    
		    $("#DRUGONLYPRIME").hide(); 
		    $('#TRAD').hide(); $('#PPO+').hide(); $('#PPO').hide(); 
	        $('#POS').hide(); $('#EPO').hide(); $('#PDENT').hide(); $('#HMO').hide();
	        $('#BLUECH').hide(); $('#BLUECS').hide(); $('#BLUEH').hide(); $('#BLUEOP').hide();
	        $('#BLUEP').hide(); $('#BLUEPF').hide(); $('#BLUPRE').hide(); $('#CPO').hide(); 
	        $('#DECAP').hide(); $('#DRUG').hide(); $('#HCASA').hide(); $('#HDENT').hide();
	        $('#HRA').hide(); $('#IHI').hide(); $('#MSRL').hide(); $('#MSUPS').hide();
	        $('#PHM').hide(); $('#RPO').hide(); $('#SABCC').hide(); $('#TDENT').hide(); 
	        $('#VGDENT').hide(); $('#VISION').hide(); $('#NMBLUESALUD').hide();
	        $('#BLUECD').hide(); $('#BLUECP').hide(); $('#BLUEEA').hide(); $('#BLUEES').hide(); 
	        $('#BLUEPA').hide(); $('#BLUEPE').hide(); $('#BLUEPP').hide(); $('#BLUEPR').hide(); 
	        $('#BLUEAP').hide();$('#BLUEFC').hide();
	        $('#MEDICARE').show(); $('#MEDICAID').show(); $('#MEDICAREPARTD').show(); $('#MEDICARESUPP').show();
	        if(flag1)
	        {
		       $('#planType').val("MEDICAID");
	        }
		}
	else
		{
			 if(flag1)
			 {
				 $('#NMBLUESALUD').hide();$('#MSUPS').hide();			    
			 }
			 else
			 {
				 $('#MSUPS').show(); $('#NMBLUESALUD').show();
			 }
			 
		    $('#DRUGONLYPRIME').show(); $('#TRAD').show(); $('#PPO+').show(); $('#PPO').show(); 
	        $('#POS').show(); $('#EPO').show(); $('#PDENT').show(); $('#HMO').show();
	        $('#BLUECH').show(); $('#BLUECS').show(); $('#BLUEH').show(); $('#BLUEOP').show();
	        $('#BLUEP').show(); $('#BLUEPF').show(); $('#BLUPRE').show(); $('#CPO').show(); 
	        $('#DECAP').show(); $('#DRUG').show(); $('#HCASA').show(); $('#HDENT').show();
	        $('#HRA').show(); $('#IHI').show(); $('#MSRL').show();
	        $('#PHM').show(); $('#RPO').show(); $('#SABCC').show(); $('#TDENT').show(); 
	        $('#VGDENT').show(); $('#VISION').show();
	        $('#MEDICARE').show(); $('#MEDICAID').show(); $('#MEDICAREPARTD').show(); $('#MEDICARESUPP').show();
	        $('#BLUECD').show(); $('#BLUECP').show(); $('#BLUEEA').show(); $('#BLUEES').show(); 
	        $('#BLUEPA').show(); $('#BLUEPE').show(); $('#BLUEPP').show(); $('#BLUEPR').show(); 
	        $('#BLUEAP').show();$('#BLUEFC').show();
	        $('#planType').val("Any");
		}
}

function hidePlanType(memCat)
{
	 $('#MEDICARE').hide(); $('#MEDICAID').hide(); $('#MEDICAREPARTD').hide(); $('#MEDICARESUPP').hide(); 
	 if(memCat=="GROUP")
     {
		 $('#BLUECD').show(); $('#BLUECP').show(); $('#BLUEEA').show(); $('#BLUEES').show(); 
	     $('#BLUEPA').show(); $('#BLUEPE').show(); $('#BLUEPP').show(); $('#BLUEPR').show(); 
	     $('#BLUEAP').hide(); $('#BLUEFC').hide(); $('#MSUPS').hide();  $('#NMBLUESALUD').hide();	     
	 }
	 else if(memCat=="RETAIL")
	 {
		 $('#BLUEAP').show(); $('#BLUECD').show(); $('#BLUECP').show(); $('#BLUEFC').show();
		 $('#BLUEEA').hide(); $('#BLUEES').hide(); $('#BLUEPA').hide(); $('#BLUEPE').hide();
		 $('#BLUEPP').hide(); $('#BLUEPR').hide(); $('#MSUPS').hide();  $('#NMBLUESALUD').hide();		
	 }
	 else
	 {
		 $('#MSUPS').show(); $('#NMBLUESALUD').show();
	 }
	 
	 $('#DRUGONLYPRIME').show(); $('#TRAD').show(); $('#PPO+').show(); $('#PPO').show(); 
     $('#POS').show(); $('#EPO').show(); $('#PDENT').show(); $('#HMO').show();
     $('#BLUECH').show(); $('#BLUECS').show(); $('#BLUEH').show(); $('#BLUEOP').show();
     $('#BLUEP').show(); $('#BLUEPF').show(); $('#BLUPRE').show(); $('#CPO').show(); 
     $('#DECAP').show(); $('#DRUG').show(); $('#HCASA').show(); $('#HDENT').show();
     $('#HRA').show(); $('#IHI').show(); $('#MSRL').show(); 
     $('#PHM').show(); $('#RPO').show(); $('#SABCC').show(); $('#TDENT').show(); 
     $('#VGDENT').show(); $('#VISION').show();  
     $('#planType').val("Any");
}



/*/hide elements
$("select option").each(function(index, val){
	if ($(this).is('option') && (!$(this).parent().is('span')))
		$(this).wrap((navigator.appName == 'Microsoft Internet Explorer') ? '<span>' : null).hide();
});

//show elements
$("select option").each(function(index, val) {
    if(navigator.appName == 'Microsoft Internet Explorer') {
	    if (this.nodeName.toUpperCase() === 'OPTION') {
	        var span = $(this).parent();
	        var opt = this;
	        if($(this).parent().is('span')) {
	        	$(opt).show();
	        	$(span).replaceWith(opt);
	        }
	    }
    } else {
    	$(this).show(); //all other browsers use standard .show()
    }
});*/