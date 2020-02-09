(function($){

$(".collpasewidget .widget-title").on(
			'click',
			'.plus-minus-style',
			function(e) {
				$(this).parents(".widget-title").next('.widget-content')
						.toggle();
				$(this).parents('.collpasewidget').toggleClass('active');
				$(this).toggleClass('fa-plus-square-o');
				if ($(this).siblings("span").text() == "General Details") {
					$("#subject").toggleClass("subject-class-dec");
				}
			});

	$(".collpasewidget .policy-title").on(
			'click',
			'.plus-minus-style',
			function(e) {
				$(this).parents(".policy-title").next('.widget-content')
						.toggle();
				$(this).parents('.collpasewidget').toggleClass('active');
				$(this).toggleClass('fa-plus-square-o');
				$(this).parents(".policy-title").find('.toolbar-btn-style')
						.toggleClass('hide-class');
			});

$(document).ready(function() {
	//initialize();
	//fillInitialData();
	$("[data-toggle=tooltip]").tooltip({'placement': 'top'});

	$('#datetimepicker1').datetimepicker({
							format: "DD/MM/YYYY HH:mm:ss",
							ignoreReadonly: true
							}).on('change', function(ev) {
				            $(this).valid();  // triggers the validation test
				            // '$(this)' refers to '$("#datepicker")'
				               });;
	
	$("#btnNewSchenario").click(function(){
		window.location.href = "dataCreationRequest.html";
	});
	
	$('#scenarioTable').DataTable( {
		paging:false,
		bFilter: false, 
		bInfo: false,
		ordering: false,
		bAutoWidth:false,
		"columnDefs": [ 
						{
							"targets" : 0,
							"width" : "20px"
						},
						{
							"targets" : 1,
							"width" : "50px"
						},
						{
							"targets" : 2,
							"width" : "420px"
						},
						{
							"targets" : 3,
							"width" : "80px"
						},
						{
							"targets" : 4,
							"width" : "50px"
						}
						]
    } );
    $('#customScenarioTable').DataTable( {
		paging:false,
		bFilter: false, 
		bInfo: false,
		ordering: false,
		"bAutoWidth":false,
		"language": {
			"emptyTable": "No scenario selected"
		},
		"dom" : 'Rrtip',
		"oColReorder": {
						 "headerContextMenu": false,
						 "bResizeTable":false,
						 "minResizeWidth":50,
						 "bAddFixed":false,
						 "allowHeaderDoubleClick":false
					  },
		"columnDefs": [ 
						{
							"targets" : 0,
							"width":"10px"
						},
						{
							"targets" : 1,
							"width":"50px"
						},
						{
							"targets" : 2,
							"width":"200px"
						},
						{
							"targets" : 3,
							"defaultContent" : "<select id='ddlProductType' class='table-cell-input-auto'></select>",
							"width":"80px"
						},
						{
							"targets" : 4,
							"defaultContent" : "<select id='ddlPolicyStatus' class='table-cell-input-auto'>" 
								/* +
									"<option selected='selected' value='Any'>Any</option></select>"*/,
							"width":"50px"
						},
						{
							"targets" : 5,
							"defaultContent" : "<select id='ddlRiskState' class='table-cell-input-auto'>" 
								/*+
									"<option selected='selected' value='Any'>Any</option></select>",*/,
							"width":"50px"
						},
						{
							"targets" : 6,
							"defaultContent" : "<select id='ddlTerm' class='table-cell-input-auto'></select>",
							"width":"50px"
						},
						{
							"targets" : 7,
							"defaultContent" : "<select id='ddlPaymentPlan' class='table-cell-input-auto'></select>",
							"width":"50px"
						},
						{
							"targets" : 8,
							"defaultContent" : "<select id='ddlTotalDue' class='table-cell-input-auto'></select>",
							"width":"50px"
						},
						{
							"targets" : 9,
							"width":"30px"
						},
						{
							"targets" : 10,
							"width":"30px"
						},
						{
							"targets" : 11,
							"defaultContent" : "<input type='text' id='txtPrefferred' value='' readOnly='true' class='table-cell-input-auto' disabled='true' placeholder='Preferred'></input>",
							"width":"50px"
						}
						]
    } );
	function initialize()
	{
		var fullFillArr = [ {key:'Manual', value:'0'},{key:'Auto', value:'1'}];
		$.each(fullFillArr, function(index, valueProp) {

			 $('#fullfillMentType').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});
		$("#txtRequestedBy").val('bhsarasw');
		var requestNo = Math.random(1000,5000);
		$('#txtRequestID').val('C011234' + requestNo);
		$("#generalDetailsContent :input").attr("disabled", true);
		$("#statusNotes").attr("disabled", false);
		$("#statusNotes").attr("placeholder", "Request status has been changed to completed");
		$("#generalDetailsContent select").attr("disabled", 'disabled');
		$("#txtAppOwner").val("Arun");
		$("#txtCreatedOn").val('12/4/2016 14:67');
		$("#appOneDate").val('12/4/2016 14:67');
		$("#appTwoDate").val('12/4/2016 14:67');
		$("#ddlAssignedGroup > [value='None']").attr("selected", "true");
		$("#txtApproverOne").val("1 Level Approval : Consumer's  Manager");
		$("#txtApproverTwo").val("2 Level Approval : Consumer's  Manager");
		$("#ddlAssignedTo > [value='None']").attr("selected", "true");
		$("#ddlStatus > [value='None']").attr("selected", "true");
		$('#generalDetailsContent').css('display', 'none');
		$("#subsetScenario").toggleClass('hide-class');

	}
		var totalSize = 0;
	var totalNoOfPolicies = 0;
		function fillSubsetRow(tableName)
		{

			/*var scenariosArr =[{
				name:'All the policies except line of business AUTO (Property, Umberlla, Earthquake)',
				estimatedPolicy:100,
				estimatedSize:5,
				lob:'Property'
			},
			{
				name:'Converted Annual term active policies with monthly payment plan',
				estimatedPolicy:200,
				estimatedSize:2,
				lob:'Auto'
			},
			{
				name:'Converted Annual term active policies with quaterly payment plan',
				estimatedPolicy:100,
				estimatedSize:1,
				lob:'Property'
			},
			{
				name:'Converted Active semi-annual policies with monthly payment plan',
				estimatedPolicy:400,
				estimatedSize:10,
				lob:'Auto'
			},
			{
				name:'Converted Cancelled policies',
				estimatedPolicy:600,
				estimatedSize:15,
				lob:'Property'
			},
			{
				name:'Converted policies for which cancellation notice has been sent',
				estimatedPolicy:100,
				estimatedSize:1,
				lob:'Auto'
			},
			{
				name:'Non - converted active policies with any payment plan which are not an endorsement',
				estimatedPolicy:250,
				estimatedSize:3,
				lob:'Property'
			},
			{
				name:'Non - converted cancelled policies with any payment plan which are not an endoresment',
				estimatedPolicy:250,
				estimatedSize:7,
				lob:'Auto'
			},
			{
				name:'Non - converted policies for which cancellation notice has been sent for any payment plan',
				estimatedPolicy:60,
				estimatedSize:8,
				lob:'Property'
			},
			{
				name:'All non converted policies with any payment plan which are reinstated',
				estimatedPolicy:50,
				estimatedSize:1,
				lob:'Auto'
			},
			{
				name:'Non-converted policies with Earned Premium Write-Off and scenarios where there is a refund',
				estimatedPolicy:500,
				estimatedSize:10,
				lob:'Property'
			},
			{
				name:'Non - converated policies where the payment is declined',
				estimatedPolicy:200,
				estimatedSize:1,
				lob:'Auto'
			},
			{
				name:'All non-converted policies where customer owes money to AAA',
				estimatedPolicy:800,
				estimatedSize:5,
				lob:'Property'
			},
			{
				name:'Policy Information which belongs to the Line of business AUTO and policy number from POLICYSUMMARY_SAMPLE',
				estimatedPolicy:600,
				estimatedSize:1,
				lob:'Auto'
			}
			];*/
			console.log(subsetScenariosList)
			$.each(subsetScenariosList, function(index, valueProp) {
				
				if(tableName=='#scenarioTable')
				{
					totalSize = totalSize + valueProp.size;
					totalNoOfPolicies = totalNoOfPolicies + valueProp.noofPolicies;
					$(tableName).DataTable().row.add([index+1,valueProp.lob,valueProp.scenarioDesc,valueProp.noofPolicies + " rows",valueProp.size + " GB"]).draw();
					if(index==(subsetScenariosList.length-1))
					{
						$(tableName).DataTable().row.add(["","","<span class='total-head-label'>Total</span>","<label id='totalNoOfPolicies' class='total-label total-label1'></label>","<label id='totalSize' class='total-label total-label2'></label>"]).draw();
					}
				}
				else
				{
					$(tableName).DataTable().row.add(['<input type="checkbox" id="customCheckbox" class="scenario-check">',valueProp.lob,valueProp.scenarioDesc, null, null, null,null, null, null,valueProp.noofPolicies + " rows",valueProp.size + " GB",null]).draw();
					$('#customScenarioTable tr:last').attr('id',index);
					fillCustomRow(index);
					if(index==(subsetScenariosList.length-1))
					{
						$(tableName).DataTable().row.add(["","","","","","","","","","<span class='total-head-label'>Total</span>","<label id='totalCustomPolicies' class='total-label total-label1'>0</label>","<label id='totalCustomSize' class='total-label total-label2'>0 GB</label>"]).draw();
					}
				}
			});
			
			$("#totalSize").text(totalSize + " GB");
			$("#totalNoOfPolicies").text(totalNoOfPolicies);
		}
		
		function fillCustomRow(rowId)
		{
			/*var prodType = [ {text:'Any',value:'Any'},{text:'CA Select Auto',value:'CA Select Auto'},{text:'Auto Signature Series',value:'Auto Signature Series'}]*/

			$.each(productTypeList, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlProductType').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
			});
			
			/*var policyStatusArr = [ {text:'Any',value:'Any'},{text:'Active',value:'Active'},{text:'Cancelled',value:'Cancelled'},{text:'Pending',value:'Pending'},{text:'Lapsed',value:'Lapsed'},{text:'Expired',value:'Expired'}];
*/
			$.each(policystatusList, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlPolicyStatus').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
			});
			
			/*var riskstateVar = [ {text:'Any',value:'Any'},{text:'AZ - Arizona',value:'AZ'},{text:'CA - California',value:'CA'},{text:'CO - Colorado',value:'CO'},{text:'CT - Connecticut',value:'CT'},{text:'DC - District of Columbia',value:'DC'},{text:'DE - Delaware',value:'DE'},{text:'ID - Idaho',value:'ID'},{text:'IN - Indiana',value:'IN'},{text:'KS - Kansas',value:'KS'},{text:'KY - Kentucky',value:'KY'},{text:'MD - Maryland',value:'MD'},
			{text:'MT - Montana',value:'MT'},{text:'NJ - New Jersey',value:'NJ'},{text:'NV - Nevada',value:'NV'},{text:'NY - New York',value:'NY'},
			{text:'OH - Ohio',value:'OH'},{text:'OK - Oklahoma',value:'OK'},{text:'OR - Oregon',value:'OR'},{text:'PA - Pennsylvania',value:'PA'},
			{text:'SD - South Dakota',value:'SD'},{text:'UT - Utah',value:'UT'},{text:'VA - Virginia',value:'VA'},{text:'WV - West Virginia',value:'WV'},
			{text:'WY - Wyoming',value:'WY'}];
*/
			$.each(riskStateArr, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlRiskState').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
			});
			
			/*var policyTermArr = [ {text:'Any',value:'Any'},{text:'Annual',value:'Annual'},{text:'Semi-Annual',value:'Semi-Annual'}];
*/
			$.each(policytermList, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlTerm').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
			});
			
			/*var payPlan = [ {text:'Any',value:'Any'},{text:'Annual',value:'Annual'},{text:'Semi-annual',value:'Semi-annual'},{text:'Quarterly',value:'Quarterly'},{text:'Monthly',value:'Monthly'},{text:'Monthly-EFT',value:'Monthly-EFT'}];
*/
			$.each(paymentplanList, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlPaymentPlan').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
			});
			
			var totalDue = [ {text:'Any',value:'Any'},{text:'Yes',value:'Yes'},{text:'No',value:'No'}];

			$.each(totalDue, function(index, valueProp) {
				$('#customScenarioTable tbody tr[id="'+rowId+ '"] #ddlTotalDue').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
			});
			
			//$('#customScenarioTable tbody tr[id="'+rowId+ '"] #txtPrefferred').val("1");
		}
		
		var totalCustomSize = 0;
		var totalCustomPolcieis = 0;
		
		$("input[name='package']").change(function(){
			if($(this).val()=="Default Package")
			{
				$(".customCheckbox:checked").attr("checked",false);
			}
		})
		
		$(document).on('change','#customCheckbox',function(){
			var row = $(this).parents('tr');
			var estimatedSize = $(row).find("td:eq(10)").text();
			var intEstimatedSize = parseInt(estimatedSize.substring(0, estimatedSize.indexOf(" ")));
			var estimatedPolicies = $(row).find("td:eq(9)").text();
			var intEstimatedPolicies = parseInt(estimatedPolicies.substring(0, estimatedPolicies.indexOf(" ")));
			if(this.checked == true)
			{
				totalCustomSize = totalCustomSize + intEstimatedSize;
				totalCustomPolcieis = totalCustomPolcieis + intEstimatedPolicies;
			}
			else
			{
			   totalCustomSize = totalCustomSize - intEstimatedSize;
			   totalCustomPolcieis = totalCustomPolcieis - intEstimatedPolicies;
			}
			$("#totalCustomSize").text(totalCustomSize + " GB");
			$("#totalCustomPolicies").text(totalCustomPolcieis);
		});
	function fillInitialData()
	{
		
		var grpArr = [ {key:'None', value:'None'}];
		$.each(grpArr, function(index, valueProp) {

			 $('#ddlAssignedGroup').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});
		
		//fill assigned to dropdown
		var assignedToArr = [ {key:'None', value:'None'},{key:'Jack', value:'Jack'}, {key:'Bill',value:'Bill'}, {key:'Hilery', value:'Hilery'}, {key:'Donald', value:'Donald'}];
		$.each(assignedToArr, function(index, valueProp) {

			 $('#ddlAssignedTo').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});
		
		//fill assigned to dropdown
		var statusArr = [ {key:'None', value:'None'},{key:'New', value:'New'},{key:'Open', value:'Open'}, {key:'In Progress', value:'In Progress'},{key: 'Cancel', value:'Cancel'}, {key:'Reopen', value:'Reopen'}, {key:'Escalation', value:'Escalation'}, {key:'Complete', value:'Complete'}, {key:'Validate', value:'Validate'}, {key:'Close', value:'Close'}];
		$.each(statusArr, function(index, valueProp) {
			 $('#ddlStatus').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});
		
		//fill consumer group
		var consumerGrpArr = [ {key:'--Select--', value:'--Select--'},{key:'PAS', value:'PAS'},{key:'CAS', value:'CAS'}, {key:' Digital Services', value:' Digital Services'}, {key:'MDM', value:'MDM'}, {key:'SOA', value:'SOA'}, {key:'Automation', value:'Automation'}, {key:'Performance', value:'Performance'}];
		$.each(consumerGrpArr, function(index, valueProp) {
			 $('#ddlConsumerGroup').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});
		
		//fill Priority
		var priorityArr = [ {text:'Low',value:'Low'},{text:'Medium', value:'Medium'}, {text:'High', value:'High'}];
		$.each(priorityArr, function(index, valueProp) {
			 $('#ddlPriority').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		//fill Data Source
		var dataSourceArr = [ {text:'--Select--',value:'--Select--'},{text:'PAS-EP2',value:'EP2'},{text:'PAS-BF', value:'BF'},{text:'Other', value:'Other'}];
		$.each(dataSourceArr, function(index, valueProp) {
			 $('#ddlSrcEnv').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		//fill Environment
		var ddlTargetEnv = [ {text:'--Select--',value:'--Select--'},{text:'PAS-EP2',value:'EP2'},{text:'PAS-BF', value:'BF'},{text:'Other', value:'Other'}];
		$.each(ddlTargetEnv, function(index, valueProp) {
			 $('#ddlTargetEnv').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});

		var appNameArr = [ {key:'PAS', value:'PAS'}];
		$.each(appNameArr, function(index, valueProp) {
			 $('#appName').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
		});

		var dbNameArr = [ {text:'--Select--',value:'--Select--'},{text:'PAS-EP2',value:'EP2'},{text:'PAS-BF', value:'BF'},{text:'Other', value:'Other'}];
		$.each(dbNameArr, function(index, valueProp) {
			 $('#dbName').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});

		
	}
	
	$("#btnReset").click(function(){
		BootstrapDialog.show({
			title: "Are you sure?",
			resize:"auto",
			position: 'center',
			height:150,
			width:600,
			//bgiframe: true,
			//show: 'fade',
			//hide:'fade',
			//dialogClass: "noclose",
			onshow: function(dialog) {
			  var markup = '<div>Are you want to perform this action?</div>';
			  dialog.setMessage(markup);
			  dialog.getModalHeader().addClass('email-title');
			},
			buttons: [
			   {
				cssClass:"btn btn-primary",
				label:"Cancel",
				action: function(dialog) {
				 dialog.close();
				}
			  },
			   {
				cssClass:"btn btn-primary",
				label:"Confirm",
				action: function(dialog) {
				 resetManualScreen();
				 dialog.close();
				}
			  }
			]
		  });
	  });
	  
	   function resetManualScreen()
	  {
		$('#txtSubject').val('');
		$('#description').val('');
		$("#ddlConsumerGroup > [value='--Select--']").attr("selected", "true");
		$("#ddlPriority > [value='0']").attr("selected", "true");
		$("#ddlSrcEnv > [value='--Select--']").attr("selected", "true");
		$("#ddlTargetEnv > [value='--Select--']").attr("selected", "true");
		$('#expectedDate').val('');
		$("#appName > [value='--Select--']").attr("selected", "true");		
		$('input[type="checkbox"]').removeAttr('checked');
		$('#commentAdd').val('');
	  }

	  fillSubsetRow('#scenarioTable');
	  fillSubsetRow('#customScenarioTable');
	  //Below code was designed for onchange on environment - Previous requirement
      /*$("#ddlSrcEnv").change(function(){
      	if(this.value=="--Select--")
      	{
      		console.log($("#ddlTargetEnv").val());
      		if($("#ddlTargetEnv").val()=="--Select--")
      		{
      			$(".env-sel").hide();	
      		}      		
      		$(".src-env").css("visibility","hidden");
      	}
      	else
      	{
      		$(".env-sel").show();
      		$(".src-env").css("visibility","visible");
      	}
      	
      });
      $("#ddlTargetEnv").change(function(){
      	if(this.value=="--Select--")
      	{
      		console.log($("#ddlSrcEnv").val());
      		if($("#ddlSrcEnv").val()=="--Select--")
      		{
      			$(".env-sel").hide();	
      		}

      		$(".tar-env").css("visibility","hidden");
      	}
      	else
      	{
      		$(".env-sel").show();
      		$(".tar-env").css("visibility","visible");
      	}
      	
      });*/
 var count = 0;
      var serviceType = 'DataSubsetting';
      //to get the current date in the mm/dd/yyyy formaTE.	
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth() + 1;
      var yyyy = today.getFullYear();
      var timeStamp = today.getHours();
      var timeStamp1 = today.getMinutes();
      var timeStamp2 = today.getSeconds();
      //var timeStamp3 = today.getDay();
      var date = mm + '/' + dd + '/' + yyyy + ' ' + timeStamp + ':'
      		+ timeStamp1+ ':'
      		+ timeStamp2;

      document.getElementById('createdOn').value = date;
      
      $("#ddlSrcEnv").change(function(){	
    	var selectedValue = this.value;
    	for(var envIndex in envObj)
    	{
    		var env = envObj[envIndex];
    		if(env.environmentId == selectedValue)
    		{
    			$("#srcSchema").val(env.schemaName);
    			$("#srcDb").val(env.instanceName);
    			break;
    		}
    	}	
    	});
      
      $("#ddlTargetEnv").change(function(){	
      	var selectedValue = this.value;
      	for(var envIndex in envObj)
      	{
      		var env = envObj[envIndex];
      		if(env.environmentId == selectedValue)
      		{
      			$("#targetSchema").val(env.schemaName);
      			$("#tarDb").val(env.instanceName);
      			break;
      		}
      	}	
      	});
} );

})(jQuery);