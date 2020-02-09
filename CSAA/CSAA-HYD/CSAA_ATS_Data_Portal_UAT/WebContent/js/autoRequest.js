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

$(document).ready(function() {
	initialize();
	$("[data-toggle=tooltip]").tooltip({'placement': 'top'});

	$('#datetimepicker1').datetimepicker({
							format: "DD/MM/YYYY HH:mm:ss",
							ignoreReadonly: true
							}).on('change', function(ev) {
				            $(this).valid();  // triggers the validation test
				            // '$(this)' refers to '$("#datepicker")'
				               });;
	
	$("#btnNewSchenario").click(function(){
		window.location.href = "./requestData";
	});
	
	function initialize()
	{
		$("#generalDetailsContent :input").attr("disabled", true);
		$("#generalDetailsContent select").attr("disabled", 'disabled');
		$('#generalDetailsContent').css('display', 'none');
		$("#autoScenario").toggleClass('hide-class');
		$("#autoAddButton").toggleClass('autotoolbar-btn-style-active');
		$("#propertyScenario").toggleClass('hide-class');
		$("#propertyAddButton").toggleClass('autotoolbar-btn-style-active');
		if(userRole == "ROLE_ADMIN")
		{
			$("#newTestDataRequirement").addClass("hide-class");
			$("#status").attr("disabled", false);
			$("#approver").attr("disabled", false);
			$("#assignedToId").attr("disabled", false);
			$("#assignedGroup").attr("disabled", false);
			$("#autoScenario input[type='checkbox']").attr('disabled', true);
			$("#propertyScenario input[type='checkbox']").attr('disabled', true);
			//$("#generalDetailsContent :input").attr("disabled", true);
			//$("#generalDetailsContent select").attr("disabled", 'enabled');
		}
		hideShowEnvDesc($("#environment").val());
	}
	if (userRole == "ROLE_ADMIN") {
		$("#otherDetails :input")
				.attr("readOnly", true);
		$("#otherDetails select").attr("disabled",
				"disabled");
		$("#generalDetailsContent td:last input").attr(
				"readOnly", false);
		/* $('#btnSubmit').attr('disabled', true); */
		$('#btnSubmit').attr('disabled', true);
		$('#btnSubmit').addClass('btn-disabled');
		$('#btnReset').attr('disabled', true);
		$('#btnReset').addClass('btn-disabled');
		$("#autoAddButton").prop('disabled', true);
		$("#propertyAddButton").prop('disabled', true);
		if (status == "New") {
			$("#status").prop('selectedIndex', 2);
		} else if (status == "Open") {
			$("#status").prop('selectedIndex', 3);
		} else if (status == "In-Progress") {
			$("#status").prop('selectedIndex', 4);
		} else if (status == "Completed") {
			$("#status").prop('selectedIndex', 5);
		} else if (status == "Sent for Clarification") {
			$("#status").prop('selectedIndex', 6);
		} else if (status == "Rejected") {
			$("#status").prop('selectedIndex', 7);
		} else if (status == "Re-Opened") {
			$("#status").prop('selectedIndex', 8);
		} else if (status == "Cancelled") {
			$("#status").prop('selectedIndex', 9);
		} else if (status == "Closed") {
			$("#status").prop('selectedIndex', 10);
		}
		
	}
	if (serviceIdentifier != 'myRequest'
			&& serviceIdentifier != 'serviceDesk') {
		$('#btnBack').hide();
	}

	if (userRole == "ROLE_USER") {
		if (status == "" || status == "Saved"
				|| status == "New") {
			if (status == "") {
				// $('#status').hide();
				$("#status").prop('selectedIndex', 0);
				$("#generalDetailsContent select")
						.attr("disabled", "disabled");
			}
			if (status == "New") {
				$("#status").prop('selectedIndex', 2);
				$("#generalDetailsContent select")
						.attr("disabled", "disabled");
			}
			if (status == "Saved") {
				$("#status").prop('selectedIndex', 1);
				$("#generalDetailsContent select")
						.attr("disabled", "disabled");
				$('#btnReset').attr('disabled', true);
				$('#btnReset').addClass('btn-disabled');
			}
		}

		if (status == "Open") {
			$("#status").prop('selectedIndex', 3);
			$("#generalDetailsContent select").attr(
					"disabled", "disabled");
		}
		if (status != "" && status != "Saved"
				&& status != "1") {
			$("#otherDetails :input").attr("disabled",
					true);
			$("#otherDetails select").attr("disabled",
					"disabled");
			$("#generalDetailsContent :input").attr(
					"disabled", true);
			$('#btnSubmit').attr('disabled', true);
			$('#btnSave').attr('disabled', true);
			$('#btnReset').attr('disabled', true);
			$('#btnSave').addClass('btn-disabled');
			$('#btnSubmit').addClass('btn-disabled');
			$('#btnReset').addClass('btn-disabled');
			$("#autoAddButton").prop('disabled', true);
			$("#propertyAddButton").prop('disabled', true);
		}

		if (status == "New") {
			$("#status").prop('selectedIndex', 2);
		} else if (status == "Open") {
			$("#status").prop('selectedIndex', 3);
		} else if (status == "In-Progress") {
			$("#status").prop('selectedIndex', 4);
		} else if (status == "Completed") {
			$("#status").prop('selectedIndex', 5);
		} else if (status == "Sent for Clarification") {
			$("#status").prop('selectedIndex', 6);
		} else if (status == "Rejected") {
			$("#status").prop('selectedIndex', 7);
		} else if (status == "Re-Opened") {
			$("#status").prop('selectedIndex', 8);
		} else if (status == "Cancelled") {
			$("#status").prop('selectedIndex', 9);
		} else if (status == "Closed") {
			$("#status").prop('selectedIndex', 10);
		}
	}
	//changeStatusNote();
	var statusValue = $("#status").val();
	if(statusValue == 5 || statusValue == 7 || statusValue == 9 || statusValue == 10)
	{
		$("#expectedDate").attr('required', false);
	}
	
	$("#environment").change(function(){
		hideShowEnvDesc(this.value);
	});
	
	function hideShowEnvDesc(value)
	{
		if(value == '3')
		{
			$('#lblEnvDesc').removeClass('hide-class');
			$('#envDesc').removeClass('hide-class');
		}
		else
		{
			$('#lblEnvDesc').addClass('hide-class');
			$('#envDesc').addClass('hide-class');
		}
	}

    $('#autoPolicyTable').DataTable( {
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
														
													},
													{
														"targets" : 1,
														
													},
													{
														"targets" : 2,
														
														"defaultContent" : "<select id='ddlRiskState' class='table-cell-input-auto'><option selected='selected' value='AZ'>AZ - Arizona</option></select>"
													},
													{
														"targets" : 3,
														
														"defaultContent" : "<select id='ddlProductType' class='table-cell-input-auto'></select>"
													},
													{
														"targets" : 4,
														
														"defaultContent" : "<select id='ddlTerm' class='table-cell-input-auto'></select>"
													},
													{
														"targets" : 5,
														
														"defaultContent" : "<select id='ddlNoDrivers' class='table-cell-input-auto'><option selected='selected' value='1'>1</option></select>"
													},
													{
														"targets" : 6,
														
														"defaultContent" : "<select id='ddlVechicles' class='table-cell-input-auto'><option selected='selected' value='1'>1</option></select>"
													},
													{
														"targets" : 7,
														
														"defaultContent" : "<select id='ddlPaymentPlan' class='table-cell-input-auto'><option selected='selected' value='Annual'>Annual</option></select>"
													}
													]
    } );
	
	//$("div.autoPolicytoolbar").html('<button type="button" id="autoAddButton" class="toolbar-btn-style">Select Scenario <i class="fa fa-angle-down angle-btn-style"></i></button>');
	
	$('#autoPolicyTable tbody').on( 'click', '#filtersubmit', function (){ 
		var tr = $(this).closest('tr');
		var rowId = $(tr).attr('id');
		var descriptionValue = $(tr).find('#txtDescription').val();
		createNewAutoDialog();
		BootstrapDialog.getDialog(
											"autoPolicyDialog").setData(
											'rowIdentifier',
											'#autoPolicyTable tr[id="' + rowId
													+ '"]').setData(
											'description', descriptionValue)
											.open();
		$('td').removeClass('table-highlight');
    } );
	
	$('#autoPolicyTable tbody').on('click', '#btnAutoSubmitRow', function(){
		 var tr = $(this).closest('tr');
		 var row = $('#autoPolicyTable').DataTable().row( tr );
		 var schenario = row.data()[0];
		 var requirementDescription = $(tr).find('#txtDescription').val();
		 var riskState = $(tr).find('#ddlRiskState').val();
		 var paymentPlan = $(tr).find('#ddlPaymentPlan').val();
		 var noOfDrivers = $(tr).find('#ddlNoDrivers').val();
		 var noOfVehicles = $(tr).find('#ddlVechicles').val();
		 var noOfPolicies = $(tr).find('#txtPolicies').val();
		 $(tr).find('#txtDescription').remove();
		 $(tr).find('#ddlRiskState').remove();
		 $(tr).find('#ddlPaymentPlan').remove();
		 $(tr).find('#ddlNoDrivers').remove();
		 $(tr).find('#ddlVechicles').remove();
		 $(tr).find('#txtPolicies').remove();
		 row.data([schenario,riskState,paymentPlan,noOfVehicles,noOfDrivers,noOfPolicies,requirementDescription]).draw();
		 $(tr).find('#btnAutoSubmitRow').addClass('hide-class');
		 $(tr).find('#btnAutoEditRow').removeClass('hide-class');
		 $('td').removeClass('table-highlight'); 
	});
	
	$('#autoPolicyTable tbody').on('click', '#btnAutoEditRow', function(){
		 var tr = $(this).closest('tr');
		 var row = $('#autoPolicyTable').DataTable().row( tr );
		 var schenario = row.data()[0];
		 var riskState = row.data()[1];
		 var paymentPlan = row.data()[2];
		 var noOfVehicles = row.data()[3];
		 var noOfDrivers = row.data()[4];
		 var noOfPolicies = row.data()[5];
		  var requirementDescription = row.data()[6];
		 row.data([schenario]).draw();
		 
		 fillAutoRow($(tr).attr('id'));
		 $(tr).find('#txtDescription').val(requirementDescription);
		 $(tr).find('#ddlRiskState').val(riskState);
		 $(tr).find('#ddlPaymentPlan').val(paymentPlan);
		 $(tr).find('#ddlNoDrivers').val(noOfDrivers);
		 $(tr).find('#ddlVechicles').val(noOfVehicles);
		 $(tr).find('#txtPolicies').val(noOfPolicies);
		 $(tr).find('#btnAutoEditRow').addClass('hide-class');
		 $(tr).find('#btnAutoSubmitRow').removeClass('hide-class');
		 $('td').removeClass('table-highlight');
	});
	
	$('#autoPolicyTable tbody').on('click','#btnAutoDeleteRow', function(){
		var tr = $(this).closest('tr');
        var row = $('#autoPolicyTable').DataTable().row( tr );
		row.remove().draw( false );
		$(".dataTables_empty").addClass('empty-row');
		$('td').removeClass('table-highlight');
		//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
	});
	
	function fillAutoRow(rowId)
	{
		//var riskStateArr = AutoCountryList
		$.each(AutoCountryList, function(index, valueProp) {
			     $('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlRiskState').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
		});
		
		//fill Payment Plan
		
		$.each(paymentPlan, function(index, valueProp) {
			
			 $('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlPaymentPlan').append($('<option>').text(valueProp.listValue).attr('value', valueProp.valueCode));
		});
		
		//fill Data Source
		var noDriversArr = [ {text:'2', value:'2'}, {text:'3', value:'3'}, {text:'4', value:'4'}, {text:'5', value:'5'}, {text:'6', value:'6'}, {text:'7', value:'7'}];
		$.each(noDriversArr, function(index, valueProp) {
			 $('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlNoDrivers').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		//fill Environment
		var noVehiclesArr = [ {text:'2', value:'2'}, {text:'3', value:'3'}, {text:'4', value:'4'}, {text:'5', value:'5'}, {text:'6', value:'6'}, {text:'7', value:'7'}];
		$.each(noVehiclesArr, function(index, valueProp) {
			 $('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlVechicles').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		var prodType = [ {text:'Auto Signature Series',value:'Auto Signature Series'},{text:'CA Select Auto',value:'CA Select Auto'}]

		$.each(prodType, function(index, valueProp) {
			$('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlProductType').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		var policyTermArr = [ {text:'Annual',value:'Annual'},{text:'Semi-Annual',value:'Semi-Annual'}];

		$.each(policyTermArr, function(index, valueProp) {
			$('#autoPolicyTable tbody tr[id="'+rowId+ '"] #ddlTerm').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		$('#autoPolicyTable tbody tr[id="' + rowId
									+ '"] #txtEffectiveDate' + rowId)
							.datetimepicker({
								// showOn: "button",
								format: "MM/DD/YYYY",
								widgetPositioning: {
									vertical: 'bottom'
								},
								showClear:true
							})
	}
	
	/*$('#autoPolicyTable tbody').on('click', '.details-control', function () {
        var tr = $(this).closest('tr');
        var row = $('#autoPolicyTable').DataTable().row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
			$(this).removeClass('fa-minus-circle');
			$(this).addClass('fa-plus-circle');
        }
        else {
            // Open this row
            row.child.show();
            tr.addClass('shown');
			$(this).removeClass('fa-plus-circle');
			$(this).addClass('fa-minus-circle');
        }
    } );*/
	var autoSchenarioCount = 1;
	$("#autoAddButton").on('click',function(){
		//$("#autoScenario").toggle(200);
        $("#autoScenario").toggleClass('hide-class');
		$("#autoAddButton").toggleClass('autotoolbar-btn-style-active');
		//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
	});
	
	 $('input[type=checkbox][name=autoScenarioType]').change(function() {
		var rowId = this.value;
		if(this.value.indexOf(".") > -1)
		{
			rowId = this.value.replace(".","_");
		}
		if(this.checked == true)
		{
			$('#autoPolicyTable').DataTable().row.add([$(this).parent('label').text(),"<div id='input-group"+ rowId +"'><input id='txtEffectiveDate"
																			+ rowId
																			+ "' type='text' value=''  class='table-cell-input-auto'/></div>",null,
																	null,
																	null,
																	null,
																	null,null
																	]).draw();
			$('#autoPolicyTable tr:last').attr('id',rowId);
			fillAutoRow(rowId);
		}
		else
		{
			$('#autoPolicyTable').DataTable().row('[id="' + rowId + '"]').remove().draw();
			$(".dataTables_empty").addClass('empty-row');
			$('td').removeClass('table-highlight');
		}
		//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
	 });
	
	function createNewAutoDialog()
	{
		 new BootstrapDialog(
		{
			id : 'autoPolicyDialog',
			// modal: true,
			title : "Additional Request Notes",
			resize : "auto",
			position : 'center',
			height : 300,
			width : 600,
			// bgiframe: true,
			// show: 'fade',
			// hide:'fade',
			// dialogClass: "noclose",
			autoOpen : false,
			onshow : function(dialog) {
				var description = dialog
						.getData('description');
				var markup = null;
				if (description != "Enter your request notes...") {
					markup = '<div><textarea class="dialog-text-area" maxlength="500">'
							+ description
							+ '</textarea></div>';
				} else {
					markup = '<div><textarea class="dialog-text-area" maxlength="500">&lt;Enter your additional request notes&gt;</textarea></div>';
				}

				dialog.setMessage(markup);
				dialog.getModalHeader().addClass(
						'email-title');
			},
			buttons : [ {
				cssClass : "btn btn-primary",
				label : "OK",
				action : function(dialog) {
					var description = $(
							".dialog-text-area")
							.val();
					var identifier = dialog
							.getData('rowIdentifier');
					$(
							identifier
									+ " #txtDescription")
							.val(description);
					dialog.close();
				}
			} ]
		});
	  }
	
	$('#propertyPolicyTable').DataTable( {
		paging:false,
		bFilter: false, 
		bInfo: false,
		ordering: false,
		"bAutoWidth":false,
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
														
													},
													{
														"targets" : 1,
														
													},
													{
														"targets" : 2,
														
														"defaultContent" : "<select id='ddlProductType' class='table-cell-input-auto'></select>"
													},
													{
														"targets" : 3,
														
														"defaultContent" : "<select id='ddlPolicyType' class='table-cell-input-auto'><option selected='selected' value='HO3'>HO3</option></select>"
													},
													{
														"targets" : 4,
														
														"defaultContent" : "<select id='ddlPropertyRiskState' class='table-cell-input-auto'><option selected='selected' value='AZ'>AZ - Arizona</option></select>"
													},
													{
														"targets" : 5,
														
														"defaultContent" : "<select id='ddlPropertyPaymentPlan' class='table-cell-input-auto'><option selected='selected' value='Annual'>Annual</option></select>"
															
													},
													{
														"targets" : 6,
														
														"defaultContent" : "<select id='ddlMortgagee' class='table-cell-input-auto'><option selected='selected' value='Yes'>Yes</option></select>"
													},
													{
														"targets" : 7,
														
														"defaultContent" : "<select id='ddlInterest' class='table-cell-input-auto'><option selected='selected' value='No'>No</option></select>"
													}]
    } );
	
	$('#propertyPolicyTable tbody').on( 'click', '#filtersubmit', function (){ 
		var tr = $(this).closest('tr');
		var rowId = $(tr).attr('id');
		var descriptionValue = $(tr).find(
											'#txtPropertyDescription').val();
									createNewPropertyDialog();
									BootstrapDialog.getDialog(
											"propertyPolicyDialog").setData(
											'rowIdentifier',
											'#propertyPolicyTable tr[id="'
													+ rowId + '"]').setData(
											'description', descriptionValue)
											.open();
		$('td').removeClass('table-highlight');
    } );
	
	/*$('#propertyPolicyTable tbody').on( 'click', 'tr', function (){ 
		if($("#propertyDeleteButton").is(":disabled") == false)
		{
			$(this).toggleClass('selected');
		}
    } );*/
	
	$('#propertyPolicyTable tbody').on('click', '#btnPropSubmitRow', function(){
		 var tr = $(this).closest('tr');
		 var row = $('#propertyPolicyTable').DataTable().row( tr );
		 var schenario = row.data()[0];
		 var requirementDescription = $(tr).find('#txtDescription').val();
		 var policyType = $(tr).find('#ddlPolicyType').val();
		 var riskState = $(tr).find('#ddlRiskState').val();
		 var paymentPlan = $(tr).find('#ddlPaymentPlan').val();
		 var noOfPolicies = $(tr).find('#txtPolicies').val();
		 $(tr).find('#txtDescription').remove();
		 $(tr).find('#ddlPolicyType').remove();
		 $(tr).find('#ddlRiskState').remove();
		 $(tr).find('#ddlPaymentPlan').remove();
		 $(tr).find('#txtPolicies').remove();
		 row.data([schenario,policyType,riskState,paymentPlan,noOfPolicies,requirementDescription]).draw();
		 $(tr).find('#btnPropSubmitRow').addClass('hide-class');
		 $(tr).find('#btnPropEditRow').removeClass('hide-class');
		 $('td').removeClass('table-highlight'); 
	});
	
	$('#propertyPolicyTable tbody').on('click', '#btnPropEditRow', function(){
		 var tr = $(this).closest('tr');
		 var row = $('#propertyPolicyTable').DataTable().row( tr );
		 var schenario = row.data()[0];
		 var policyType = row.data()[1];
		 var riskState = row.data()[2];
		 var paymentPlan = row.data()[3];
		 var noOfPolicies = row.data()[6];
		  var requirementDescription = row.data()[7];
		 row.data([schenario]).draw();
		 
		 fillPropertyRow($(tr).attr('id'));
		 $(tr).find('#txtDescription').val(requirementDescription);
		 $(tr).find('#ddlPolicyType').val(policyType);
		 $(tr).find('#ddlRiskState').val(riskState);
		 $(tr).find('#ddlPaymentPlan').val(paymentPlan);
		 $(tr).find('#txtPolicies').val(noOfPolicies);
		 $(tr).find('#btnPropEditRow').addClass('hide-class');
		 $(tr).find('#btnPropSubmitRow').removeClass('hide-class');
		 $('td').removeClass('table-highlight'); 
	});
	
	function fillPropertyRow(rowId)
	{
		
							$.each(policyTypeArr, function(index, valueProp) {
								$(
										'#propertyPolicyTable tbody tr[id="'
												+ rowId + '"] #ddlPolicyType')
										.append(
												$('<option>').text(
														valueProp.listValue).attr(
														'value',
														valueProp.valueCode));
							});

							
							
							
							$.each(riskStateArr, function(index, valueProp) {
								$(
										'#propertyPolicyTable tbody tr[id="'
												+ rowId
												+ '"] #ddlPropertyRiskState')
										.append(
												$('<option>').text(
														valueProp.listValue).attr(
														'value',
														valueProp.valueCode));
							});
						
							$.each(paymentPlan1, function(index, valueProp) {
								$(
										'#propertyPolicyTable tbody tr[id="'
												+ rowId
												+ '"] #ddlPropertyPaymentPlan')
										.append(
												$('<option>').text(
														valueProp.listValue).attr(
														'value',
														valueProp.valueCode));
							});
							
							var prodType = [ {text:'Auto Signature Series',value:'Auto Signature Series'},{text:'CA Select Auto',value:'CA Select Auto'}]

		$.each(prodType, function(index, valueProp) {
			$('#propertyPolicyTable tbody tr[id="'+rowId+ '"] #ddlProductType').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		var mortgagee = [ {text:'No',value:'No'}];

		$.each(mortgagee, function(index, valueProp) {
			$('#propertyPolicyTable tbody tr[id="'+rowId+ '"] #ddlMortgagee').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		var Interest = [ {text:'Yes',value:'Yes'}];

		$.each(Interest, function(index, valueProp) {
			$('#propertyPolicyTable tbody tr[id="'+rowId+ '"] #ddlInterest').append($('<option>').text(valueProp.text).attr('value', valueProp.value));
		});
		
		$('#propertyPolicyTable tbody tr[id="' + rowId+ '"] #txtPropEffectiveDate' + rowId).datetimepicker({
								// showOn: "button",
								format: "MM/DD/YYYY",
								widgetPositioning: {
									vertical: 'bottom'
								},
								showClear:true
							});
							
							
	}
	/*$('#propertyPolicyTable tbody').on('click', '.details-control', function () {
        var tr = $(this).closest('tr');
        var row = $('#propertyPolicyTable').DataTable().row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
			$(this).removeClass('fa-minus-circle');
			$(this).addClass('fa-plus-circle');
        }
        else {
            // Open this row
            row.child.show();
            tr.addClass('shown');
			$(this).removeClass('fa-plus-circle');
			$(this).addClass('fa-minus-circle');
        }
    } );*/
	var propSchenarioCount = 1;
	$("#propertyAddButton").on('click',function(){
		//$("#propertyScenario").toggle(200);
        $("#propertyScenario").toggleClass('hide-class');
		$("#propertyAddButton").toggleClass('autotoolbar-btn-style-active');
		//$('#chkPropertyPolicyDiv').css('height',$('#propertyWidgetDiv').height() + 'px');
		
	});
	
	 $('input[type=checkbox][name=propertyScenarioType]').change(function() {
		var rowId = this.value;
		if(this.value.indexOf(".") > -1)
		{
			rowId = this.value.replace(".","_");
		}
		if(this.checked == true)
		{
			$('#propertyPolicyTable').DataTable().row.add([ $(this).parent('label').text(),"<div id='input-group"+ rowId +"'><input id='txtPropEffectiveDate" + rowId + "' type='text' value=''  class='table-cell-input-auto'/></div>",null,
																	null,
																	null,
																	null,
																	null,null
																	]).draw();
			$('#propertyPolicyTable tr:last').attr('id',rowId);
			fillPropertyRow(rowId);
		}
		else
		{
			$('#propertyPolicyTable').DataTable().row('[id="' + rowId + '"]').remove().draw();
			$(".dataTables_empty").addClass('empty-row');
			$('td').removeClass('table-highlight');
		}
		//$('#chkPropertyPolicyDiv').css('height',$('#propertyWidgetDiv').height() + 'px');
	 });
	
		function createNewPropertyDialog()
		{
		  new BootstrapDialog(
									{
										id : 'propertyPolicyDialog',
										title : "Additional Request Notes",
										resize : "auto",
										position : 'center',
										height : 300,
										width : 600,
										// bgiframe: true,
										// show: 'fade',
										// hide:'fade',
										// dialogClass: "noclose",
										autoOpen : false,
										onshow : function(dialog) {
											var description = dialog
													.getData('description');
											var markup = null;
											if (description != "Enter your request notes...") {
												markup = '<div><textarea class="dialog-text-area" maxlength="500">'
														+ description
														+ '</textarea></div>';
											} else {
												markup = '<div><textarea class="dialog-text-area" maxlength="500">&lt;Enter your additional request notes&gt;</textarea></div>';
											}
											dialog.setMessage(markup);
											dialog.getModalHeader().addClass(
													'email-title');
										},
										buttons : [ {
											cssClass : "btn btn-primary",
											label : "OK",
											action : function(dialog) {
												var description = $(
														".dialog-text-area")
														.val();
												var identifier = dialog
														.getData('rowIdentifier');
												$(
														identifier
																+ " #txtPropertyDescription")
														.val(description);
												dialog.close();
											}
										} ]
									});
	  }
	  
	  //Save button functionality
	  $("#btnSave").click(function(e){
		  var isValid = $("#requestDataForm").valid();
		  var autoPolicyCount = $("#autoPolicyTable").DataTable().data().length;
		  var propPolicyCount = $("#propertyPolicyTable").DataTable().data().length;
		  $("#tableDataRequiredMsg").css('display','none');
		  if(autoPolicyCount == 0 && propPolicyCount == 0)
		  {
			  isValid = false;
			  $("#tableDataRequiredMsg").css('display','block');
			  $("#tableDataRequiredMsg").text("Please fill in the Auto/Property Policy Table");
			  e.preventDefault();
		  }
		  if(isValid)
		  {
			  showLoader();
			  e.preventDefault();
			  fillValuesForSave();
			  $("#tableDataRequiredMsg").css('display','none');
			  $('#requestDataForm').append('<input type="hidden" name="save" value="save" />');
			  $("#requestDataForm").submit();
		  }
		  
	  
	  });
	  function fillValuesForSave()
	  {
						var autoPolicy = [];
						var propPolicy = [];
					
						var rows = $(
								"#autoPolicyTable tbody")
								.find("tr");
						for (var index = 0; index < rows.length; index++) {
							var scenarioNo = $(rows[index]).attr("id");

							var autoScenarioTxt = $(rows[index]).find("td:eq(0)").text().trim();
							
							var effectiveDate = $(
									rows[index]).find(
									"#txtEffectiveDate"
											+ scenarioNo)
									.val();
							var ddlRiskState = $(
									rows[index]).find(
									"#ddlRiskState").val();
							var ddlProductType =$(
									rows[index]).find(
									"#ddlProductType").val();
							var ddlTerm =$(
									rows[index]).find(
									"#ddlTerm").val();
							var paymentPlan = $(rows[index])
									.find("#ddlPaymentPlan")
									.val();
							var noOfDrivers = $(rows[index])
									.find("#ddlNoDrivers")
									.val();
							var noOfVehicles = $(
									rows[index]).find(
									"#ddlVechicles").val();
							
						
							var rowObj = {
								"sno" : scenarioNo,
								"rs" : ddlRiskState,
								"pt" : ddlProductType,
								"term" : ddlTerm,
								"pp" : paymentPlan,
								"nod" : noOfDrivers,
								"nov" : noOfVehicles,
								"ed" : effectiveDate,
								"ast": autoScenarioTxt
								
								
							};
							autoPolicy.push(rowObj);
						}
						var propPolicy = [];
						var rows = $(
								"#propertyPolicyTable tbody")
								.find("tr");
						for (var index = 0; index < rows.length; index++) {
							var scenarioNo = $(rows[index]).attr("id");
							var propScenarioTxt = $(rows[index]).find("td:eq(0)").text().trim();
							var effectiveDate = $(
									rows[index]).find(
									"#txtPropEffectiveDate"
											+ scenarioNo)
									.val();
							var product = $(
									rows[index]).find(
									"#ddlProductType")
									.val();
							var ddlPolicyType = $(
									rows[index]).find(
									"#ddlPolicyType").val();
							var ddlRiskState = $(
									rows[index])
									.find(
											"#ddlPropertyRiskState")
									.val();
							var ddlPaymentPlan = $(
									rows[index])
									.find(
											"#ddlPropertyPaymentPlan")
									.val();
							var mortgage=$(
									rows[index])
									.find(
											"#ddlMortgagee")
									.val();
							var addInterest=$(
									rows[index])
									.find(
											"#ddlInterest")
									.val();
							var rowObj1 = {
								"sno" : scenarioNo,
								"product" : product,
								"pt" : ddlPolicyType,
								"rs" : ddlRiskState,
								"pp" : ddlPaymentPlan,
								"mortgage" : mortgage,
								"additionlInterest" : addInterest,
								"pst": propScenarioTxt,
								"ed" : effectiveDate
							};
							propPolicy.push(rowObj1);
						}

						var generalDetails = [];

						var serviceType = $("#serviceType")
								.val();
						var requestedBy = $("#requestedBy")
								.val();
						var applicationOwner = $(
								"#applicationOwner").val();
						var assignedGroup = $(
								"#assignedGroup").val();
						var approver = $("#approver").val();
						var assignedTo = $("#assignedToId")
								.val();
						var statusChangeDesc = $(
								"#statusChange").val();
						var createdOn = $("#createdOn")
								.val();
						var requestId = $("#requestId")
								.val();
						var status = $("#status").val();
						var consumerGroup = $(
								"#consumerGroup").val();
						var Priority = $("#Priority").val();
						var dataSource = $("#dataSource")
								.val();
						var environment = $("#environment")
								.val();
						var expectedDate = $(
								"#expectedDate").val();
						var subject = $("#subject").val();
						var autoPolicyType = $(
								"#requestDataForm input[name=autoPolicyType]:checked")
								.val();
						var propPolicyType = $(
								"#requestDataForm input[name=propPolicyType]:checked")
								.val();
						var envDesc = $("#envDesc").val();
						var genObj = {
							"st" : serviceType,
							"rb" : requestedBy,
							"ao" : applicationOwner,
							"ag" : assignedGroup,
							"ap" : approver,
							"at" : assignedTo,
							"sc" : statusChangeDesc,
							"co" : createdOn,
							"rid" : requestId,
							"ss" : status,
							"cg" : consumerGroup,
							"pr" : Priority,
							"ds" : dataSource,
							"en" : environment,
							"ed" : expectedDate,
							"sb" : subject,
							"envd" : envDesc,
							"apt" : autoPolicyType,
							"ppt" : propPolicyType
						};
						generalDetails.push(genObj);

						$('#autoPolicye').val(JSON.stringify(autoPolicy));
						$('#propPolicye').val(JSON.stringify(propPolicy));
						$('#genDet').val(JSON.stringify(generalDetails));
						return true;

					};
	  //Submit button functionality
					$("#btnSubmit")
					.click(
							function(e) {
								 var isValid = $("#requestDataForm").valid();
								  var autoPolicyCount = $("#autoPolicyTable").DataTable().data().length;
								  var propPolicyCount = $("#propertyPolicyTable").DataTable().data().length;
								  $("#tableDataRequiredMsg").css('display','none');
								  if(autoPolicyCount == 0 && propPolicyCount == 0)
								  {
									  isValid = false;
									  $("#tableDataRequiredMsg").css('display','block');
									  $("#tableDataRequiredMsg").text("Please fill in the Auto/Property Policy Table");
									  e.preventDefault();
								  }
								  
								  if(isValid)
								  {
									  e.preventDefault();
									  $("#tableDataRequiredMsg").css('display','none');
									  BootstrapDialog.show({
											title : "Message from ATS Data Portal",
											resize : "auto",
											position : 'center',
											height : 170,
											width : 600,
											// bgiframe: true,
											// show: 'fade',
											// hide:'fade',
											// dialogClass:
											// "noclose",
											onshow : function(
													dialog) {
												var markup = '<div><i class="fa fa-question-circle question-tooltip-style"></i>Do you want to place data creation request? Please verify your data creation request entries.</div>';
												dialog
														.setMessage(markup);
												dialog
														.getModalHeader()
														.addClass(
																'email-title');
											},
											buttons : [
													{
														cssClass : "btn btn-primary",
														label : "Cancel",
														action : function(
																dialog) {
															dialog
																	.close();
														}
													},
													{
														cssClass : "btn btn-primary",
														label : "Confirm",
														action : function(
																dialog) {
															showLoader();
															fillValuesForSave();
															$(
																	'#requestDataForm')
																	.append(
																			'<input type="hidden" name="btnSubmit1" value="btnSubmit1" />');
															$(
																	"#requestDataForm")
																	.submit();
															// openMessageDialog();
															dialog
																	.close();
														}
													} ]
										});
								  }
							});
	  
	  //Cancel button functionality
	  $("#btnCancel").click(function(){
	  });
	  
	  //Close button functionality
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
		//set default to all values
		$('#requestDataForm')[0].reset();
		formValidator.resetForm();
		$("#tableDataRequiredMsg").css('display','none');
		/*$('#txtSubject').val('');
		$("#ddlConsumerGroup > [value='0']").attr("selected", "true");
		$("#ddlPriority > [value='0']").attr("selected", "true");
		$("#ddlDataSource > [value='0']").attr("selected", "true");
		$("#ddlEnvironment > [value='0']").attr("selected", "true");
		$('#expectedDate').val('');
		$('txtStatusChangeDescription').val('');
		$('#autoPolicyTable').DataTable().clear().draw();
		$('#propertyPolicyTable').DataTable().clear().draw();
		$('#autoScenario .chk-control').removeAttr('checked');
		$('#propertyScenario .chk-control').removeAttr('checked');
		$("#autoAddButton").removeClass('autotoolbar-btn-style-active');
		$("#propertyAddButton").removeClass('autotoolbar-btn-style-active');
		$('#autoScenario').addClass('hide-class');
		$('#propertyScenario').addClass('hide-class');
		$("#chkAutoYes").prop('checked',true);
		$("#chkPropYes").prop('checked',true);
		$(".dataTables_empty").addClass('empty-row');*/
		//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
		//$('#chkPropertyPolicyDiv').css('height',$('#propertyWidgetDiv').height() + 'px');
	  }
	  
	 
	  
	  $('input[type=radio][name=autoPolicyType]').change(function() {
		if (this.id == "chkAutoYes") {
			enableDisableAuto(false);
		}
		else
		{
			if($("#autoPolicyTable").DataTable().data().length > 0)
			{
				BootstrapDialog.show({
					title: "Are you sure?",
					resize:"auto",
					position: 'center',
					height:200,
					width:600,
					//bgiframe: true,
					//show: 'fade',
					//hide:'fade',
					//dialogClass: "noclose",
					onshow: function(dialog) {
					  var markup = '<div>Deselecting product type check option will delete all your scenario entries.<br/>Are you sure you want to perform this action?</div>';
					  dialog.setMessage(markup);
					  dialog.getModalHeader().addClass('email-title');
					},
					onhidden:function()
					{
						if($("#autoPolicyTable").DataTable().data().length > 0)
						{
							$("#chkAutoYes").prop('checked',true);
						}
					},
					buttons: [
					  {
						cssClass:"btn btn-primary",
						label:"Cancel",
						action: function(dialog) {
						 $("#chkAutoYes").prop('checked',true);
						 dialog.close();
						}
					  },
					   {
						cssClass:"btn btn-primary",
						label:"Confirm",
						action: function(dialog) {
						 enableDisableAuto(true);
						 dialog.close();
						}
					  }
					]
				  });
			}
			else
			{
				$("#autoAddButton").prop('disabled', true);
			}
		}
    });
	
	
	$('input[type=radio][name=propPolicyType]').change(function() {
		if (this.id == 'chkPropYes') {
			enableDisableProperty(false);
		}
		else
		{
			if($("#propertyPolicyTable").DataTable().data().length > 0)
			{
				BootstrapDialog.show({
					title: "Are you sure?",
					resize:"auto",
					position: 'center',
					height:200,
					width:600,
					//bgiframe: true,
					//show: 'fade',
					//hide:'fade',
					//dialogClass: "noclose",
					onshow: function(dialog) {
					  var markup = '<div>Deselecting product type check option will delete all your scenario entries.<br/>Are you sure you want to perform this action?</div>';
					  dialog.setMessage(markup);
					  dialog.getModalHeader().addClass('email-title');
					},
					onhidden:function()
					{
						if($("#propertyPolicyTable").DataTable().data().length > 0)
						{
							$("#chkPropYes").prop('checked',true);
						}
					},
					buttons: [
					   {
						cssClass:"btn btn-primary",
						label:"Cancel",
						action: function(dialog) {
						 $("#chkPropYes").prop('checked',true);
						 dialog.close();
						}
					  },
					   {
						cssClass:"btn btn-primary",
						label:"Confirm",
						action: function(dialog) {
						 enableDisableProperty(true);
						 dialog.close();
						}
					  }
					]
				  });
			}
			else
			{
				$("#propertyAddButton").prop('disabled', true);
			}
		}
        
    });
	
	function enableDisableAuto(flag)
	{
		$("#autoAddButton").prop('disabled', flag);
		if(flag == true)
		{
			$("#autoPolicyTable").DataTable().clear().draw();
			$(".dataTables_empty").addClass('empty-row');
			$('#autoScenario .chk-control').removeAttr('checked');
			$('#autoScenario').addClass('hide-class');
			$("#autoAddButton").removeClass('autotoolbar-btn-style-active');
		}
		//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
	}
	
	function enableDisableProperty(flag)
	{
		$("#propertyAddButton").prop('disabled', flag);
		if(flag == true)
		{
			$("#propertyPolicyTable").DataTable().clear().draw();
			$(".dataTables_empty").addClass('empty-row');
			$('#propertyScenario .chk-control').removeAttr('checked');
			$('#propertyScenario').addClass('hide-class');
			$("#propertyAddButton").removeClass('autotoolbar-btn-style-active');
		}
		//$('#chkPropertyPolicyDiv').css('height',$('#propertyWidgetDiv').height() + 'px');
	}
	
	$(".dataTables_empty").addClass('empty-row');
	$('#autoPolicyTable tbody')
        .on( 'mouseenter', 'td', function () {
            var tr = $(this).closest('tr');
 
            $("#autoPolicyTable tbody tr").removeClass( 'highlight' );
            $(tr).addClass( 'highlight' );
        } );
		
	$('#autoPolicyTable').on('mouseout',function () {
            $("#autoPolicyTable tbody tr").removeClass( 'highlight' );
        } );
		
	$('#propertyPolicyTable tbody')
        .on( 'mouseenter', 'td', function () {
            var tr = $(this).closest('tr');
 
            $("#propertyPolicyTable tbody tr").removeClass( 'highlight' );
            $(tr).addClass( 'highlight' );
        } );
		
	$('#propertyPolicyTable').on('mouseout',function () {
            $("#propertyPolicyTable tbody tr").removeClass( 'highlight' );
        } );
	
	$('#propertyPolicyTable').on('click','td', function() {
		$('td').removeClass('table-highlight');
		$(this).addClass('table-highlight');
	});
	
	$('#autoPolicyTable').on('click','td', function() {
		$('td').removeClass('table-highlight');
		$(this).addClass('table-highlight');
	});
	
	$("#btnNewRequest").click(function(e){
		 e.preventDefault();
		 e.stopPropagation();
		window.location.href = "./dgAutomation";
	});
	
	function disableAutoPolicyTable() {
		$("#autoScenario input[type='checkbox']").attr('disabled', true);
		$("#autoPolicyTable :input").attr('disabled', true);
	}

	function disablePropertyPolicyTable() {
		$("#propertyScenario input[type='checkbox']").attr('disabled', true);
		$("#propertyPolicyTable :input").attr('disabled',true);
	}
	
	var formValidator = $('#requestDataForm').validate({
		  errorPlacement: function(error, element) { //solve you problem
			var trigger = element.parent('.input-group');
			error.insertAfter(trigger.length > 0 ? trigger : element);
		  }
		});
	if (autoPolicyTableData != '') {
		
		for (var index = 0; index < autoPolicyTableData.length; index++) {
			var rowData = autoPolicyTableData[index];
			var rowId = rowData.scenarioNo;
			//var autoScenarioSelected = rowId.replace('_','.');
			$("input[type='checkbox'][name='autoScenarioType'][value='"+ rowId +"']").prop('checked',true).change();
			/*$('#autoPolicyTable').DataTable().row.add([rowData.automationScenario,"<div id='input-group"+ rowId +"'><input id='txtEffectiveDate"
														+ rowId
														+ "' type='text' value='' readonly='true'  class='table-cell-input'/></div>",null,
												null,
												null,
												null,
												null,null
												]).draw();*/
			//$('#autoPolicyTable tr:last').attr('id',
				//	rowId);
			//fillAutoRow(rowId);
			var selectedRow = $("#autoPolicyTable tbody")
					.find(
							"tr[id='" + rowId
									+ "']");
			$(selectedRow).find(
					"#ddlRiskState option[value='"
							+ rowData.riskState + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlProductType option[value='"
							+ rowData.productType + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlTerm option[value='"
							+ rowData.term + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlNoDrivers option[value='"
							+ rowData.noOfDrivers + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlVechicles option[value='"
							+ rowData.noOfVehicles + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlPaymentPlan option[value='"
							+ rowData.paymentPlan + "']")
					.attr('selected', true);
			
			$(selectedRow).find(
					"#txtEffectiveDate" + rowId).val(
					rowData.effectiveDate);
			
		}
		
		
		
			if (userRole == "ROLE_ADMIN") {
				disableAutoPolicyTable();
			}
			if (userRole != null && userRole == 'ROLE_USER'
					&& status == 'New'
					) {
				disableAutoPolicyTable();
			}
			if (userRole != null && userRole == 'ROLE_USER'
				&& status != 'Saved'
				) {
			disableAutoPolicyTable();
		}
		
	}
	if (propPolicyTableData != '') {
		for (var index = 0; index < propPolicyTableData.length; index++) {
			var rowData = propPolicyTableData[index];
			var rowId = rowData.scenarioNo;
			//var propScenarioSelected = rowId.replace('_','.');
			$("input[type='checkbox'][name='propertyScenarioType'][value='"+ rowId +"']").prop('checked',true).change();
			var selectedRow = $(
					"#propertyPolicyTable tbody").find(
					"tr[id='" + rowId + "']");
			$(selectedRow).find(
					"#ddlProductType option[value='"
							+ rowData.product + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlPolicyType option[value='"
							+ rowData.policyType + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlPropertyRiskState option[value='"
							+ rowData.riskState + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlPropertyPaymentPlan option[value='"
							+ rowData.paymentPlan + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlMortgagee option[value='"
							+ rowData.mortgage + "']")
					.attr('selected', true);
			$(selectedRow).find(
					"#ddlInterest option[value='"
							+ rowData.additionalInterest + "']")
					.attr('selected', true);
			
			$(selectedRow).find(
					"#txtPropEffectiveDate" + rowId).val(
					rowData.effectiveDate);
			
		}
		if (userRole == "ROLE_ADMIN") {
			disablePropertyPolicyTable();
		}
		if (userRole != null && userRole == 'ROLE_USER'
				&& status == 'New'
				) {
			disablePropertyPolicyTable();
		}
		if (userRole != null && userRole == 'ROLE_USER'
			&& status != 'Saved'
			) {
		disablePropertyPolicyTable();
	}
	}
	
		$("#btnRunManager").click(function(){
		var file = $("#runManagerFile").get(0).files[0];
		
		var form_data = new FormData();
		form_data.append("file", file);
		$.ajax({
	    	type : 'POST',
	        url: './uploadAutoExcel',
	        cache: false,
			
			enctype:"multipart/form-data",
			processData: false,
			contentType:false,
			data :form_data,
			//dataType:'json',
	       	success : function(response) {
	       		//$("#runManagerCompleteMsg").text("File uploaded successfully");
	       		//$("#runManagerCompleteMsg").removeClass('login-error');
				$("#runManagerCompleteMsg").css("display","block");			
			}
		});
		
	});
	
	$("#btnAutoPolicytestData").click(function(){
		var file = $("#autoPolicytestDataFile").get(0).files[0];
		
		var form_data = new FormData();
		form_data.append("file", file);
		$.ajax({
	    	type : 'POST',
	        url: './uploadAutoExcel',
	        cache: false,
			
			enctype:"multipart/form-data",
			processData: false,
			contentType:false,
			data :form_data,
			//dataType:'json',
	       	success : function(response) {
	       		//$("#autotestCompleteMsg").text("File uploaded successfully");
	       		//$("#autotestCompleteMsg").removeClass('login-error');
	       		$("#autotestCompleteMsg").css("display","block");			
			}
		});
		
	});
	
	$("#btnPropPolicytestData").click(function(){
		var file = $("#propPolicytestDataFile").get(0).files[0];
		
		var form_data = new FormData();
		form_data.append("file", file);
		$.ajax({
	    	type : 'POST',
	        url: './uploadAutoExcel',
	        cache: false,
			
			enctype:"multipart/form-data",
			processData: false,
			contentType:false,
			data :form_data,
			//dataType:'json',
	       	success : function(response) {
	       		//$("#proptestCompleteMsg").text("File uploaded successfully");
	       		//$("#proptestCompleteMsg").removeClass('login-error');
	       		$("#proptestCompleteMsg").css("display","block");			
			}
		});
		
	});
	
	$(document).on('change', '.btn-file :file', function() {
		$("#runManagerCompleteMsg").css("display","none");	
		$("#autotestCompleteMsg").css("display","none");
		$("#proptestCompleteMsg").css("display","none");
		  var input = $(this),
		      numFiles = input.get(0).files ? input.get(0).files.length : 1,
		      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		  input.trigger('fileselect', [numFiles, label]);
		});
	//$('#chkAutoPolicyDiv').css('height',$('#autoWidgetDiv').height() + 'px');
	//$('#chkPropertyPolicyDiv').css('height',$('#propertyWidgetDiv').height() + 'px');
	
	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {
        
        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
            $("#btnRunManager").addClass('hide-class');
            $("#btnAutoPolicytestData").addClass('hide-class');
        	$("#btnPropPolicytestData").addClass('hide-class');
            if(log.length != 0 && input.attr('id').indexOf('RunManager') > -1)
            {
            	$("#btnRunManager").removeClass('hide-class');
            }
            else if(log.length != 0 && input.attr('id').indexOf('AutoPolicytestData') > -1)
            {
            	$("#btnAutoPolicytestData").removeClass('hide-class');
            }
            else if(log.length != 0 && input.attr('id').indexOf('PropPolicytestData') > -1)
            {
            	$("#btnPropPolicytestData").removeClass('hide-class');
            }
        } else {
        }
        
    });
	
	$(".input-link").click(function(){
		$("#runManagerCompleteMsg").css("display","none");	
		$("#autotestCompleteMsg").css("display","none");
		$("#proptestCompleteMsg").css("display","none");
		$(".input-group").addClass('hide-class');
		$(this).next(".input-group").removeClass('hide-class');
	});
	
	var autoScenarioLength = $("#autoScenario .scenario-row .scenario-column").length;
	if(autoScenarioLength % 3 == 0)
	{
		$("#autoScenario .scenario-row div:last-child").removeClass("scenario-col-border");
	}
	else
	{
		$("#autoScenario .scenario-row").not(":last").find("div:last-child").removeClass("scenario-col-border");
		if(autoScenarioLength % 3 == 1)
		{
			$("#autoScenario .scenario-row:last-child").append('<div class="col-md-4 scenario-chk scenario-column scenario-col-border"></div>');
		}	
	}
	
	var propScenarioLength = $("#propertyScenario .scenario-row .scenario-column").length;
	if(propScenarioLength % 3 == 0)
	{
		$("#propertyScenario .scenario-row div:last-child").removeClass("scenario-col-border");
	}
	else
	{
		$("#propertyScenario .scenario-row").not(":last").find("div:last-child").removeClass("scenario-col-border");
		if(propScenarioLength % 3 == 1)
		{
			$("#propertyScenario .scenario-row:last-child").append('<div class="col-md-4 scenario-chk scenario-column scenario-col-border"></div>');
		}	
	}
	
		$("#btnGenerateTemplate").click(function() {
			showLoader();
		$.ajax({
			type : 'POST',
			url : './genarateExcel?requestID='+$("#requestId").val(),
			cache : false,
			success : function(response) {
				hideLoader();
			}
		});

	});
	
});

if(userRole == "ROLE_ADMIN")
{
	$("#newTestDataRequirement").addClass("hide-class");
	$("#status").prop("disabled", false);
	//$("#generalDetailsContent :input").attr("disabled", true);
	//$("#generalDetailsContent select").attr("disabled", 'enabled');
}
$("#btnBack").click(function(){
	$("form").validate().cancelSubmit = true;
});

})(jQuery);

function downloadRunManagerExcel() {
	$("#runManagerCompleteMsg").css("display","none");	
	$("#autotestCompleteMsg").css("display","none");
	$("#proptestCompleteMsg").css("display","none");
	$(".input-group").addClass('hide-class');
	} 