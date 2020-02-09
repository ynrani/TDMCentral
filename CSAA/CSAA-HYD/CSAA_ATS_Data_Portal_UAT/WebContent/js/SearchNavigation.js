
					var formSubmitted =$('#submitFlag').find('p').text();
					if(formSubmitted != null && formSubmitted != '1')
					{
						$("#searchresultsAuto").css('display','none');
					}

					
					var formSubmitted =$('#submitFlagProp').find('p').text();
					if(formSubmitted != null && formSubmitted != '1')
					{
						$("#searchresultsProp").css('display','none');
					}
