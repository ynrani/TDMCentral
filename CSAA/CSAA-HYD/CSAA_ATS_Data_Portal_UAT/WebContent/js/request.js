(function($) {

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

	$(document)
			.ready(
					function() {
			initialize();
			$("[data-toggle=tooltip]").tooltip({
				'placement' : 'top'
			});

			$('#datetimepicker1').datetimepicker({
				format: "MM/DD/YYYY HH:mm:ss",
				ignoreReadonly: true
				}).on('change', function(ev) {
				$(this).valid();  // triggers the validation test
				   });;
			
			$(".icon-time").addClass("fa fa-clock-o");

			function initialize() {
				$("#generalDetailsContent :input").attr("readOnly",
						true);
				$("#generalDetailsContent select").attr("readOnly",
						true);
				$('#generalDetailsContent').css('display', 'none');
				 if (userRole == "ROLE_ADMIN") {
						$("#statusChange").attr('required', true);
						$('#generalDetailsContent').css('display', 'block');
						$("#generalDetails i").removeClass("fa-plus-square-o");
						
						if ($('#status').val() == "0" && $('#assignedToId').val() == "None"){
							/*$("#assignedToId").val('');
							$("#assignedToId").attr('required',true);*/
							$("#assignedToId").addClass("selectcheck");

								jQuery.validator.addMethod('selectcheck', function (value) {
									return (value != 'None');
								}, "This field is required.");

						}
						
						$("#status > [value='0']").attr('disabled', true);
						$("#status > [value='1']").attr('disabled', true);
					}
					hideShowEnvDesc($("#environment").val());

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
				changeStatusNote();
				var statusValue = $("#status").val();
				if(statusValue == 5 || statusValue == 7 || statusValue == 9 || statusValue == 10)
				{
					$("#expectedDate").attr('required', false);
				}
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
		
			$("#btnfetchDetail").click(
					function() {
						$("#generalDetailsContent :input").attr(
								"disabled", false);
						$("#generalDetailsContent select").attr(
								"disabled", '');
					});

	$('#autoPolicyTable')
			.DataTable(
					{
						paging : false,
						bFilter : false,
						bInfo : false,
						ordering : false,
						"dom" : '<"autoPolicytoolbar">frtip',
						"bAutoWidth" : false,
						"columnDefs" : [
								{
									"targets" : 0,
									"width" : "45px"
								},
								{
									"targets" : 1,
									"width" : "50px",
									"defaultContent" : "<select id='ddlRiskState' class='table-cell-input'></select>"
								},
								{
									"targets" : 2,
									"width" : "61px",
									"defaultContent" : "<select id='ddlPaymentPlan' class='table-cell-input'></select>"
								},
								{
									"targets" : 3,
									"width" : "72px",
									"defaultContent" : "<select id='ddlNoDrivers' class='table-cell-input'><option selected='selected' value='Any'>Any</option></select>"
								},
								{
									"targets" : 4,
									"width" : "75px",
									"defaultContent" : "<select id='ddlVechicles' class='table-cell-input'><option selected='selected' value='Any'>Any</option></select>"
								},
								{
									"targets" : 5,
									"width" : "72px",
									"defaultContent" : "<input id='txtPolicies' type='text' value=''  class='table-cell-input Numeric'/>"
								},
								{
									"targets" : 6,
									"width" : "72px"
								},
								{
									"targets" : 7,
									"width" : "200px",
									"defaultContent" : "<div class='table-cell-input'><input id='txtDescription' type='text' maxlength='500' placeholder='Enter your request notes...' class='desc-input-style'/><i id='filtersubmit' class='fa fa-angle-double-down desc-icon-style' title='Enter Request Notes'></i></div>"
								},
								{
									"targets" : 8,
									"data" : null,
									"width" : "19px",
									"defaultContent" : "<i id='btnAutoDeleteRow' class='fa fa-trash-o delete-control' title='Delete'></i>"
								} ]
					});

	$('#autoPolicyTable tbody').on(
			'click',
			'#filtersubmit',
			function() {
				var tr = $(this).closest('tr');
				var rowId = $(tr).attr('id');
				var descriptionValue = $(tr).find(
						'#txtDescription').val();
				createNewAutoDialog();
				BootstrapDialog.getDialog(
						"autoPolicyDialog").setData(
						'rowIdentifier',
						'#autoPolicyTable tr[id="' + rowId
								+ '"]').setData(
						'description', descriptionValue)
						.open();
				$('td').removeClass('table-highlight');
			});

	$('#autoPolicyTable tbody').on(
			'click',
			'#btnAutoSubmitRow',
			function() {
				var tr = $(this).closest('tr');
				var row = $('#autoPolicyTable').DataTable()
						.row(tr);
				var Scenario = row.data()[0];
				var requirementDescription = $(tr).find(
						'#txtDescription').val();
				var riskState = $(tr).find('#ddlRiskState')
						.val();
				var paymentPlan = $(tr).find(
						'#ddlPaymentPlan').val();
				var noOfDrivers = $(tr).find(
						'#ddlNoDrivers').val();
				var noOfVehicles = $(tr).find(
						'#ddlVechicles').val();
				var noOfPolicies = $(tr).find(
						'#txtPolicies').val();
				var effectiveDate = $(tr).find(
						'#txtEffectiveDate').val();

				$(tr).find('#txtDescription').remove();
				$(tr).find('#ddlRiskState').remove();
				$(tr).find('#ddlPaymentPlan').remove();
				$(tr).find('#ddlNoDrivers').remove();
				$(tr).find('#ddlVechicles').remove();
				$(tr).find('#txtPolicies').remove();
				$(tr).find('#txtEffectiveDate').remove();
				row.data(
						[ Scenario, riskState, paymentPlan,
								noOfDrivers, noOfVehicles,
								noOfPolicies,
								requirementDescription ])
						.draw();
				$(tr).find('#btnAutoSubmitRow').addClass(
						'hide-class');
				$(tr).find('#btnAutoEditRow').removeClass(
						'hide-class');
				$('td').removeClass('table-highlight');
			});

	$('#autoPolicyTable tbody').on(
			'click',
			'#btnAutoEditRow',
			function() {
				var tr = $(this).closest('tr');
				var row = $('#autoPolicyTable').DataTable()
						.row(tr);
				var Scenario = row.data()[0];
				var riskState = row.data()[1];
				var paymentPlan = row.data()[2];
				var noOfDrivers = row.data()[3];
				var noOfVehicles = row.data()[4];
				var noOfPolicies = row.data()[5];
				var effectiveDate = row.data()[6];
				var requirementDescription = row.data()[7];
				row.data([ Scenario ]).draw();

				fillAutoRow($(tr).attr('id'));
				$(tr).find('#txtDescription').val(
						requirementDescription);
				$(tr).find('#ddlRiskState').val(riskState);
				$(tr).find('#ddlPaymentPlan').val(
						paymentPlan);
				$(tr).find('#ddlNoDrivers')
						.val(noOfDrivers);
				$(tr).find('#ddlVechicles').val(
						noOfVehicles);
				$(tr).find('#txtPolicies')
						.val(noOfPolicies);
				$(tr).find('#txtEffectiveDate').val(
						effectiveDate);
				$(tr).find('#btnAutoEditRow').addClass(
						'hide-class');
				$(tr).find('#btnAutoSubmitRow')
						.removeClass('hide-class');
				$('td').removeClass('table-highlight');
			});

	$('#autoPolicyTable tbody')
			.on(
					'click',
					'#btnAutoDeleteRow',
					function() {
						var tr = $(this).closest('tr');
						var table = $('#autoPolicyTable')
								.DataTable();
						var row = table.row(tr);
						var currentScenarioNumber = row
								.data()[0];
						row.remove().draw(false);
						table
								.rows()
								.every(
										function(rowIdx,
												tableLoop,
												rowLoop) {
											var cellData = table
													.cell(
															rowIdx,
															0)
													.data();
											if (cellData > currentScenarioNumber) {
												table
														.cell(
																rowIdx,
																0)
														.data(
																cellData - 1)
														.draw();
												$(
														"#autoPolicyTable tbody tr:eq("
																+ rowIdx
																+ ")")
														.attr(
																'id',
																cellData - 1);
											}
										});
						autoScenarioCount = autoScenarioCount - 1;
						$(".dataTables_empty").addClass(
								'empty-row');
						$('td').removeClass(
								'table-highlight');
					});

	function fillAutoRow(rowId) {
		
		$.each(AutoCountryList, function(index, item) {
			$(
					'#autoPolicyTable tbody tr[id="'
							+ rowId + '"] #ddlRiskState')
					.append(
							$('<option>').text(
									item.listValue).attr(
									'value',
									item.valueCode));
		});

		$.each(paymentPlan, function(index, item) {
			$(
					'#autoPolicyTable tbody tr[id="'
							+ rowId + '"] #ddlPaymentPlan')
					.append(
							$('<option>').text(
									item.listValue).attr(
									'value',
									item.valueCode));
		});

		// fill Data Source
		var noDriversArr = [ {
			text : '1',
			value : '1'
		}, {
			text : '2',
			value : '2'
		}, {
			text : '3',
			value : '3'
		}, {
			text : '4',
			value : '4'
		}, {
			text : '5',
			value : '5'
		}, {
			text : '6',
			value : '6'
		}, {
			text : '7',
			value : '7'
		} ];
		$.each(noDriversArr, function(index, valueProp) {
			$(
					'#autoPolicyTable tbody tr[id="'
							+ rowId + '"] #ddlNoDrivers')
					.append(
							$('<option>').text(
									valueProp.text).attr(
									'value',
									valueProp.value));
		});

		// fill Environment
		var noVehiclesArr = [ {
			text : '1',
			value : '1'
		}, {
			text : '2',
			value : '2'
		}, {
			text : '3',
			value : '3'
		}, {
			text : '4',
			value : '4'
		}, {
			text : '5',
			value : '5'
		}, {
			text : '6',
			value : '6'
		}, {
			text : '7',
			value : '7'
		} ];
		$.each(noVehiclesArr, function(index, valueProp) {
			$(
					'#autoPolicyTable tbody tr[id="'
							+ rowId + '"] #ddlVechicles')
					.append(
							$('<option>').text(
									valueProp.text).attr(
									'value',
									valueProp.value));
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
		});

	}

	var autoScenarioCount = 0;
	$("#autoAddButton")
			.on(
					'click',
					function() {
						 if(autoScenarioCount==7)
							 {
								 return;
							 }
						autoScenarioCount = autoScenarioCount + 1;
						$('#autoPolicyTable').DataTable().row
								.add(
										[
												autoScenarioCount,
												null,
												null,
												null,
												null,
												null,
												"<div id='input-group"+ autoScenarioCount +"'><input id='txtEffectiveDate"
														+ autoScenarioCount
														+ "' type='text' value='' class='table-cell-input'/></div>" ])
								.draw();
						$('#autoPolicyTable tr:last').attr(
								'id', autoScenarioCount);
						fillAutoRow(autoScenarioCount);
						hideTableError(true);
					});

	function createNewAutoDialog() {
		new BootstrapDialog(
				{
					id : 'autoPolicyDialog',
					title : "Additional Request Notes",
					resize : "auto",
					position : 'center',
					height : 300,
					width : 600,
					autoOpen : false,
					onshow : function(dialog) {
						var description = dialog
								.getData('description');
						var markup = null;
						var disabled = "";
						if(disabledAutoTable == true)
						{
							disabled = " disabled='true' ";
						}
						if (description != "Enter your request notes...") {
							
							markup = '<div><textarea class="dialog-text-area" maxlength="500" '+ disabled +'>'
									+ description
									+ '</textarea></div>';
						} else {
							markup = '<div><textarea class="dialog-text-area" maxlength="500" '+ disabled +'>&lt;Enter your additional request notes&gt;</textarea></div>';
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

	$('#propertyPolicyTable')
		.DataTable(
				{
					paging : false,
					bFilter : false,
					bInfo : false,
					ordering : false,
					"bAutoWidth" : false,
					"columnDefs" : [
							{
								"targets" : 0,
								"width" : "45px"
							},
							{
								"targets" : 1,
								"width" : "51px",
								"defaultContent" : "<select id='ddlPolicyType' class='table-cell-input'></select>"
							},
							{
								"targets" : 2,
								"width" : "59px",
								"defaultContent" : "<select id='ddlPropertyRiskState' class='table-cell-input'></select>"
							},
							{
								"targets" : 3,
								"width" : "75px",
								"defaultContent" : "<select id='ddlPropertyPaymentPlan' class='table-cell-input'></select>"
							},
							{
								"targets" : 4,
								"width" : "79px",
								"defaultContent" : "<input id='txtPropertyPolicies' type='text' value=''  class='table-cell-input Numeric'/>"
							},
							{
								"targets" : 5,
								"width" : "347px",
								"defaultContent" : "<div class='table-cell-input'><input id='txtPropertyDescription' type='text' maxlength='500' placeholder='Enter your request notes...' class='desc-input-style'/><i id='filtersubmit' class='fa fa-angle-double-down desc-icon-style' title='Enter Request Notess'></i></div>"
							},
							{
								"targets" : 6,
								"data" : null,
								"width" : "16px",
								"defaultContent" : "<i id='btnPropDeleteRow' class='fa fa-trash-o delete-control' title='Delete'></i>"
							} ]
				});

	$('#propertyPolicyTable tbody').on(
			'click',
			'#filtersubmit',
			function() {
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
			});

	$('#propertyPolicyTable tbody')
			.on(
					'click',
					'#btnPropSubmitRow',
					function() {
						var tr = $(this).closest('tr');
						var row = $('#propertyPolicyTable')
								.DataTable().row(tr);
						var Scenario = row.data()[0];
						var requirementDescription = $(tr)
								.find(
										'#txtPropertyDescription')
								.val();
						var policyType = $(tr).find(
								'#ddlPolicyType').val();
						var riskState = $(tr).find(
								'#ddlPropertyRiskState')
								.val();
						var paymentPlan = $(tr).find(
								'#ddlPropertyPaymentPlan')
								.val();
						var noOfPolicies = $(tr).find(
								'#txtPropertyPolicies')
								.val();
						$(tr).find(
								'#txtPropertyDescription')
								.remove();
						$(tr).find('#ddlPolicyType')
								.remove();
						$(tr).find('#ddlPropertyRiskState')
								.remove();
						$(tr).find(
								'#ddlPropertyPaymentPlan')
								.remove();
						$(tr).find('#txtPropertyPolicies')
								.remove();
						row
								.data(
										[
												Scenario,
												policyType,
												riskState,
												paymentPlan,
												noOfPolicies,
												requirementDescription ])
								.draw();
						$(tr).find('#btnPropSubmitRow')
								.addClass('hide-class');
						$(tr).find('#btnPropEditRow')
								.removeClass('hide-class');
						$('td').removeClass(
								'table-highlight');
					});

	$('#propertyPolicyTable tbody').on(
			'click',
			'#btnPropEditRow',
			function() {
				var tr = $(this).closest('tr');
				var row = $('#propertyPolicyTable')
						.DataTable().row(tr);
				var Scenario = row.data()[0];
				var policyType = row.data()[1];
				var riskState = row.data()[2];
				var paymentPlan = row.data()[3];
				var noOfPolicies = row.data()[6];
				var requirementDescription = row.data()[7];
				row.data([ Scenario ]).draw();

				fillPropertyRow($(tr).attr('id'));
				$(tr).find('#txtPropertyDescription').val(
						requirementDescription);
				$(tr).find('#ddlPolicyType')
						.val(policyType);
				$(tr).find('#ddlPropertyRiskState').val(
						riskState);
				$(tr).find('#ddlPropertyPaymentPlan').val(
						paymentPlan);
				$(tr).find('#txtPropertyPolicies').val(
						noOfPolicies);
				$(tr).find('#btnPropEditRow').addClass(
						'hide-class');
				$(tr).find('#btnPropSubmitRow')
						.removeClass('hide-class');
				$('td').removeClass('table-highlight');
			});

	$('#propertyPolicyTable tbody')
			.on(
					'click',
					'#btnPropDeleteRow',
					function() {
						var tr = $(this).closest('tr');
						var table = $(
								'#propertyPolicyTable')
								.DataTable();
						var row = table.row(tr);
						var currentScenarioNumber = row
								.data()[0];
						row.remove().draw(false);
						table
								.rows()
								.every(
										function(rowIdx,
												tableLoop,
												rowLoop) {
											var cellData = table
													.cell(
															rowIdx,
															0)
													.data();
											if (cellData > currentScenarioNumber) {
												table
														.cell(
																rowIdx,
																0)
														.data(
																cellData - 1)
														.draw();
												$(
														"#propertyPolicyTable tbody tr:eq("
																+ rowIdx
																+ ")")
														.attr(
																'id',
																cellData - 1);
											}
										});
						propScenarioCount = propScenarioCount - 1;
						$(".dataTables_empty").addClass(
								'empty-row');
						$('td').removeClass(
								'table-highlight');
					});

		function fillPropertyRow(rowId) {
			$.each(policyTypeArr, function(index, item) {
				$(
						'#propertyPolicyTable tbody tr[id="'
								+ rowId + '"] #ddlPolicyType')
						.append(
								$('<option>').text(
										item.listValue).attr(
										'value',
										item.valueCode));
			});

			$.each(riskStateArr, function(index, item) {
				$(
						'#propertyPolicyTable tbody tr[id="'
								+ rowId
								+ '"] #ddlPropertyRiskState')
						.append(
								$('<option>').text(
										item.listValue).attr(
										'value',
										item.valueCode));
			});
			
			$.each(paymentPlan1, function(index, item) {
				$(
						'#propertyPolicyTable tbody tr[id="'
								+ rowId
								+ '"] #ddlPropertyPaymentPlan')
						.append(
								$('<option>').text(
										item.listValue).attr(
										'value',
										item.valueCode));
			});
		}
						
		var propScenarioCount = 0;
		$("#propertyAddButton").on(
				'click',
				function() {
					if(propScenarioCount==7)
						{
						   return;
						}
					propScenarioCount = propScenarioCount + 1;
					$('#propertyPolicyTable').DataTable().row
							.add([ propScenarioCount ]).draw();
					$('#propertyPolicyTable tr:last').attr(
							'id', propScenarioCount);
					fillPropertyRow(propScenarioCount);
					hideTableError(true);
				});

		function createNewPropertyDialog() {
			new BootstrapDialog(
					{
						id : 'propertyPolicyDialog',
						title : "Additional Request Notes",
						resize : "auto",
						position : 'center',
						height : 300,
						width : 600,
						autoOpen : false,
						onshow : function(dialog) {
							var description = dialog
									.getData('description');
							var markup = null;
							var disabled = "";
							if(disabledPropTable == true)
							{
								disabled = " disabled='true' ";
							}
							if (description != "Enter your request notes...") {
								markup = '<div><textarea class="dialog-text-area" maxlength="500" '+ disabled +'>'
										+ description
										+ '</textarea></div>';
							} else {
								markup = '<div><textarea class="dialog-text-area" maxlength="500" '+ disabled +'>&lt;Enter your additional request notes&gt;</textarea></div>';
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

		// Save button functionality
		$("#btnSave").click(function(e){
			  var isValid = $("#requestDataForm").valid();
			  var autoPolicyCount = $("#autoPolicyTable").DataTable().data().length;
			  var propPolicyCount = $("#propertyPolicyTable").DataTable().data().length;
			  var additionalDescription = $(".desc-input-style");
			  var isADNotExist = false;
			  for(var index = 0; index < additionalDescription.length; index++)
			  {
				  if($(additionalDescription[index]).val() == "Enter your request notes..." || $(additionalDescription[index]).val() == "")
				  {
					  isADNotExist = true;
					  break;
				  }
			  }
			  if(autoPolicyCount == 0 && propPolicyCount == 0)
			  {
				  isValid = false;
				  $("#tableDataRequiredMsg").css('display','block');
				  $("#tableDataRequiredMsg").text("Please fill in the Auto/Property Policy Table");
				  e.preventDefault();
			  }
			  else if(isADNotExist == true)
			  {
				  isValid = false;
				  $("#tableDataRequiredMsg").css('display','block');
				  $("#tableDataRequiredMsg").text("Additional Request Notes is mandatory");
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
								var ddlRiskState = $(
										rows[index]).find(
										"#ddlRiskState").val();
								var paymentPlan = $(rows[index])
										.find("#ddlPaymentPlan")
										.val();
								var noOfDrivers = $(rows[index])
										.find("#ddlNoDrivers")
										.val();
								var noOfVehicles = $(
										rows[index]).find(
										"#ddlVechicles").val();
								var noOfPolicies = $(
										rows[index]).find(
										"#txtPolicies").val();
								var effectiveDate = $(
										rows[index]).find(
										"#txtEffectiveDate"
												+ (index + 1))
										.val();
								var addDescription = $(
										rows[index]).find(
										"#txtDescription")
										.val();
								var rowObj = {
									"sno" : scenarioNo,
									"rs" : ddlRiskState,
									"pp" : paymentPlan,
									"nod" : noOfDrivers,
									"nov" : noOfVehicles,
									"nop" : noOfPolicies,
									"ed" : effectiveDate,
									"ad" : addDescription
								};
								autoPolicy.push(rowObj);
							}
							var propPolicy = [];
							var rows = $(
									"#propertyPolicyTable tbody")
									.find("tr");
							for (var index = 0; index < rows.length; index++) {
								var scenarioNo = $(rows[index]).attr("id");
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
								var txtPolicies = $(rows[index])
										.find(
												"#txtPropertyPolicies")
										.val();
								var txtDescription = $(
										rows[index])
										.find(
												"#txtPropertyDescription")
										.val();

								var rowObj1 = {
									"sno" : scenarioNo,
									"pt" : ddlPolicyType,
									"rs" : ddlRiskState,
									"pp" : ddlPaymentPlan,
									"nop" : txtPolicies,
									"ad" : txtDescription
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

							$('#autoPolicye').val(
									JSON.stringify(autoPolicy));
							$('#propPolicye').val(
									JSON.stringify(propPolicy));
							$('#genDet')
									.val(
											JSON
													.stringify(generalDetails));
							return true;

						};
										
		function fillValuesForSubmit() {

			var autoPolicy = [];
			var propPolicy = [];
			var rows = $("#autoPolicyTable tbody").find("tr");
			for (var index = 0; index < rows.length; index++) {
				var scenarioNo = $(rows[index]).attr("id");
				var ddlRiskState = $(rows[index]).find(
						"#ddlRiskState").val();
				var paymentPlan = $(rows[index]).find(
						"#ddlPaymentPlan").val();
				var noOfDrivers = $(rows[index]).find(
						"#ddlNoDrivers").val();
				var noOfVehicles = $(rows[index]).find(
						"#ddlVechicles").val();
				var noOfPolicies = $(rows[index]).find(
						"#txtPolicies").val();
				var effectiveDate = $(rows[index]).find(
						"#txtEffectiveDate" + (index + 1)).val();
				var addDescription = $(rows[index]).find(
						"#txtDescription").val();
				var rowObj = {
					"sno" : scenarioNo,
					"rs" : ddlRiskState,
					"pp" : paymentPlan,
					"nod" : noOfDrivers,
					"nov" : noOfVehicles,
					"nop" : noOfPolicies,
					"ed" : effectiveDate,
					"ad" : addDescription
				};
				autoPolicy.push(rowObj);
			}
			var propPolicy = [];
			var rows = $("#propertyPolicyTable tbody").find(
					"tr");
			for (var index = 0; index < rows.length; index++) {
				var scenarioNo = $(rows[index]).attr("id");
				var ddlPolicyType = $(rows[index]).find(
						"#ddlPolicyType").val();
				var ddlRiskState = $(rows[index]).find(
						"#ddlPropertyRiskState").val();
				var ddlPaymentPlan = $(rows[index]).find(
						"#ddlPropertyPaymentPlan").val();
				var txtPolicies = $(rows[index]).find(
						"#txtPropertyPolicies").val();
				var txtDescription = $(rows[index]).find(
						"#txtPropertyDescription").val();

				var rowObj1 = {
					"sno" : scenarioNo,
					"pt" : ddlPolicyType,
					"rs" : ddlRiskState,
					"pp" : ddlPaymentPlan,
					"nop" : txtPolicies,
					"ad" : txtDescription
				};
				propPolicy.push(rowObj1);
			}

			var generalDetails = [];

			var serviceType = $("#serviceType").val();
			var requestedBy = $("#requestedBy").val();
			var applicationOwner = $("#applicationOwner").val();
			var assignedGroup = $("#assignedGroup").val();
			var approver = $("#approver").val();
			var assignedTo = $("#assignedToId").val();
			var statusChangeDesc = $("#statusChange").val();
			var createdOn = $("#createdOn").val();
			var requestId = $("#requestId").val();
			var status = $("#status").val();
			var consumerGroup = $("#consumerGroup").val();
			var Priority = $("#Priority").val();
			var dataSource = $("#dataSource").val();
			var environment = $("#environment").val();
			var expectedDate = $("#expectedDate").val();
			var subject = $("#subject").val();
			var envDesc = $("#envDesc").val();
			var autoPolicyType = $(
					"#requestDataForm input[name=autoPolicyType]:checked")
					.val();
			var propPolicyType = $(
					"#requestDataForm input[name=propPolicyType]:checked")
					.val();
			var genObj = {
				"st" : serviceType,
				"rb" : requestedBy,
				"ao" : applicationOwner,
				"ag" : assignedGroup,
				"ap" : approver,
				"at" : assignedTo,
				"sc" : assignedTo,
				"co" : createdOn,
				"rid" : requestId,
				"ss" : status,
				"cg" : consumerGroup,
				"pr" : Priority,
				"ds" : dataSource,
				"en" : environment,
				"ed" : expectedDate,
				"sb" : subject,
				"envd": envDesc,
				"apt" : autoPolicyType,
				"ppt" : propPolicyType
			};
			generalDetails.push(genObj);

			$('#autoPolicye').val(JSON.stringify(autoPolicy));
			$('#propPolicye').val(JSON.stringify(propPolicy));
			$('#genDet').val(JSON.stringify(generalDetails));

		}
						
		var formValidator = $('#requestDataForm').validate({
			  errorPlacement: function(error, element) { //solve you problem
				var trigger = element.parent('.input-group');
				error.insertAfter(trigger.length > 0 ? trigger : element);
			  }
			});
		  
		  $.validator.addMethod("dateHigherThanToday", function(value, element) {
			  //If false, the validation fails and the message below is displayed
			  var myDate = value;
			  return moment(myDate,"MM/DD/YYYY HH:mm:ss").toDate() > new Date();
			  }, "Date must be higher than current date");

		// Submit button functionality
		$("#btnSubmit")
				.click(
						function(e) {
							 var isValid = $("#requestDataForm").valid();
							  var autoPolicyCount = $("#autoPolicyTable").DataTable().data().length;
							  var propPolicyCount = $("#propertyPolicyTable").DataTable().data().length;
							  var additionalDescription = $(".desc-input-style");
								var isADNotExist = false;
							  for(var index = 0; index < additionalDescription.length; index++)
							  {
								  if($(additionalDescription[index]).val() == "Enter your request notes..." || $(additionalDescription[index]).val() == "")
								  {
									  isADNotExist = true;
									  break;
								  }
							  }
							  if(autoPolicyCount == 0 && propPolicyCount == 0)
							  {
								  isValid = false;
								  $("#tableDataRequiredMsg").css('display','block');
								  $("#tableDataRequiredMsg").text("Please fill in the Auto/Property Policy Table");
								  e.preventDefault();
							  }
							  else if(isADNotExist == true)
							  {
								  isValid = false;
								  $("#tableDataRequiredMsg").css('display','block');
								  $("#tableDataRequiredMsg").text("Additional Request Notes is mandatory");
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
														fillValuesForSubmit();
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

		// Cancel button functionality
		$("#btnCancel").click(function() {
		});

		// Close button functionality
		$("#btnReset")
				.click(
						function() {
							// open popup
							BootstrapDialog
									.show({
										modal : true,
										title : "Message from ATS Data Portal",
										resize : "auto",
										position : 'center',
										height : 150,
										width : 600,

										onshow : function(
												dialog) {
											var markup = '<div><i class="fa fa-question-circle question-tooltip-style"></i>Are you sure you want to reset?</div>';
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
														resetManualScreen();
														dialog
																.close();
													}
												} ]
									});
						});

		function resetManualScreen() {
			$('#requestDataForm')[0].reset();
			formValidator.resetForm();
			$("#tableDataRequiredMsg").css('display','none');
		}

		$('input[type=radio][name=autoPolicyType]')
				.change(
						function() {
							if (this.id == "chkAutoYes") {
								enableDisableAuto(false);
							} else {
								if ($("#autoPolicyTable")
										.DataTable().data().length > 0) {
									BootstrapDialog
											.show({
												title : "Are you sure?",
												resize : "auto",
												position : 'center',
												height : 200,
												width : 600,
												onshow : function(
														dialog) {
													var markup = '<div>Deselecting product type check option will delete all your scenario entries.<br/>Are you sure you want to perform this action?</div>';
													dialog
															.setMessage(markup);
													dialog
															.getModalHeader()
															.addClass(
																	'email-title');
												},
												onhidden : function() {
													if ($(
															"#autoPolicyTable")
															.DataTable()
															.data().length > 0) {
														$(
																"#chkAutoYes")
																.prop(
																		'checked',
																		true);
													}
												},
												buttons : [
														{
															cssClass : "btn btn-primary",
															label : "Cancel",
															action : function(
																	dialog) {
																$(
																		"#chkAutoYes")
																		.prop(
																				'checked',
																				true);
																dialog
																		.close();
															}
														},
														{
															cssClass : "btn btn-primary",
															label : "Confirm",
															action : function(
																	dialog) {
																enableDisableAuto(true);
																dialog
																		.close();
															}
														} ]
											});
								} else {
									$("#autoAddButton").prop(
											'disabled', true);
								}
							}
						});

		$('input[type=radio][name=propPolicyType]')
				.change(
						function() {
							if (this.id == 'chkPropYes') {
								enableDisableProperty(false);
							} else {
								if ($("#propertyPolicyTable")
										.DataTable().data().length > 0) {
									BootstrapDialog
											.show({
												title : "Are you sure?",
												resize : "auto",
												position : 'center',
												height : 200,
												width : 600,
												onshow : function(
														dialog) {
													var markup = '<div>Deselecting product type check option will delete all your scenario entries.<br/>Are you sure you want to perform this action?</div>';
													dialog
															.setMessage(markup);
													dialog
															.getModalHeader()
															.addClass(
																	'email-title');
												},
												onhidden : function() {
													if ($(
															"#propertyPolicyTable")
															.DataTable()
															.data().length > 0) {
														$(
																"#chkPropYes")
																.prop(
																		'checked',
																		true);
													}
												},
												buttons : [
														{
															cssClass : "btn btn-primary",
															label : "Cancel",
															action : function(
																	dialog) {
																$(
																		"#chkPropYes")
																		.prop(
																				'checked',
																				true);
																dialog
																		.close();
															}
														},
														{
															cssClass : "btn btn-primary",
															label : "Confirm",
															action : function(
																	dialog) {
																enableDisableProperty(true);
																dialog
																		.close();
															}
														} ]
											});
								} else {
									$("#propertyAddButton")
											.prop('disabled',
													true);
								}
							}

						});

		function enableDisableAuto(flag) {
			$("#autoAddButton").prop('disabled', flag);
			if (flag == true) {
				$("#autoPolicyTable").DataTable().clear()
						.draw();
				$(".dataTables_empty").addClass('empty-row');
			}
		}

		function enableDisableProperty(flag) {
			$("#propertyAddButton").prop('disabled', flag);
			if (flag == true) {
				$("#propertyPolicyTable").DataTable().clear()
						.draw();
				$(".dataTables_empty").addClass('empty-row');
			}
		}

		$(".dataTables_empty").addClass('empty-row');

		$('#autoPolicyTable tbody').on(
				'mouseenter',
				'td',
				function() {
					if($(this).parents('.bootstrap-datetimepicker-widget').length == 0)
					{
						var tr = $(this).closest('tr');

						$("#autoPolicyTable tbody tr").removeClass(
								'highlight');
						$(tr).addClass('highlight');
					}
					
				});

		$('#autoPolicyTable').on(
				'mouseout',
				function() {
					if($(this).parents('.bootstrap-datetimepicker-widget').length == 0)
					{
					$("#autoPolicyTable tbody tr").removeClass(
							'highlight');
					}
				});

		$('#propertyPolicyTable tbody').on(
				'mouseenter',
				'td',
				function() {
					var tr = $(this).closest('tr');

					$("#propertyPolicyTable tbody tr")
							.removeClass('highlight');
					$(tr).addClass('highlight');
				});

		$('#propertyPolicyTable').on(
				'mouseout',
				function() {
					$("#propertyPolicyTable tbody tr")
							.removeClass('highlight');
				});

		$('#propertyPolicyTable').on('click', 'td', function() {
			$('td').removeClass('table-highlight');
			$(this).addClass('table-highlight');
		});

		$('#autoPolicyTable').on('click', 'td', function() {
			$('td').removeClass('table-highlight');
			$(this).addClass('table-highlight');
		});

		$("#btnNewRequest").click(function() {
			window.location.href = "./requestData";
		});

		var disabledAutoTable = false, disabledPropTable = false;
		function disableAutoPolicyTable() {
			disabledAutoTable = true;
			$("#autoAddButton").attr('disabled', true);
			$("#autoPolicyTable :input").attr('disabled', true);
			$("#autoPolicyTable .delete-control").prop(
					'disabled', true);
			$("#autoPolicyTable .delete-control").addClass(
					'btn-disabled');
		}

		function disablePropertyPolicyTable() {
			disabledPropTable = true;
			$("#propertyAddButton").attr('disabled', true);
			$("#propertyPolicyTable :input").attr('disabled',
					true);
			$("#propertyPolicyTable .delete-control").prop(
					'disabled', true);
			$("#propertyPolicyTable .delete-control").addClass(
					'btn-disabled');
		}

		if (autoPolicyTableData != '') {
			for (var index = 0; index < autoPolicyTableData.length; index++) {
				var rowData = autoPolicyTableData[index];
				autoScenarioCount = parseInt(rowData.scenarioNo);
				$('#autoPolicyTable').DataTable().row.add(
						[
							autoScenarioCount,
							null,
							null,
							null,
							null,
							null,
							"<input id='txtEffectiveDate"
									+ autoScenarioCount
									+ "' type='text' value='' class='table-cell-input'/>" ]).draw();
				$('#autoPolicyTable tr:last').attr('id',
						autoScenarioCount);
				fillAutoRow(autoScenarioCount);
				var selectedRow = $("#autoPolicyTable tbody")
						.find(
								"tr[id='" + autoScenarioCount
										+ "']");
				$(selectedRow).find(
						"#ddlRiskState option[value='"
								+ rowData.riskState + "']")
						.attr('selected', true);
				$(selectedRow).find(
						"#ddlPaymentPlan option[value='"
								+ rowData.paymentPlan + "']")
						.attr('selected', true);
				$(selectedRow).find(
						"#ddlNoDrivers option[value='"
								+ rowData.noOfDrivers + "']")
						.attr('selected', true);
				$(selectedRow).find(
						"#ddlVechicles option[value='"
								+ rowData.noOfVehicles + "']")
						.attr('selected', true);
				$(selectedRow).find("#txtPolicies").val(
						rowData.noOfPolicies);
				$(selectedRow).find(
						"#txtEffectiveDate" + autoScenarioCount).val(
						rowData.effectiveDate);
				$(selectedRow).find("#txtDescription").val(
						rowData.additionalInformation);
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
				propScenarioCount = parseInt(rowData.scenarioNo);
				$('#propertyPolicyTable').DataTable().row.add(
						[ propScenarioCount ]).draw();
				$('#propertyPolicyTable tr:last').attr('id',
						propScenarioCount);
				fillPropertyRow(propScenarioCount);
				var selectedRow = $(
						"#propertyPolicyTable tbody").find(
						"tr[id='" + propScenarioCount + "']");
				$(selectedRow).find(
						"#ddlPropertyRiskState option[value='"
								+ rowData.riskState + "']")
						.attr('selected', true);
				$(selectedRow).find(
						"#ddlPolicyType option[value='"
								+ rowData.policyType + "']")
						.attr('selected', true);
				$(selectedRow).find(
						"#ddlPropertyPaymentPlan option[value='"
								+ rowData.paymentPlan + "']")
						.attr('selected', true);
				$(selectedRow).find("#txtPropertyPolicies")
						.val(rowData.noOfPolicies);
				$(selectedRow).find("#txtPropertyDescription")
						.val(rowData.additionalInformation);
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
	});

	// Radion button Functionality for Auto
	$("#chkAutoNo").click(function() {
		// open popup
		BootstrapDialog.show({
			modal : true,
			title : "Are you sure?",
			resize : "auto",
			position : 'center',
			height : 150,
			width : 600,

			onshow : function(dialog) {
				var markup = '<div>Are you want to perform this action?</div>';
				dialog.setMessage(markup);
				dialog.getModalHeader().addClass('email-title');
			},
			buttons : [ {
				cssClass : "btn btn-primary",
				label : "Cancel",
				action : function(dialog) {
					dialog.close();
				}
			}, {
				cssClass : "btn btn-primary",
				label : "Confirm",
				action : function(dialog) {
					resetManualScreen();
					dialog.close();
				}
			} ]
		});
	});

	function resetManualScreen() {
		$('#requestDataForm')[0].reset();
	}
	// Radion button Functionality for Property

	$("#chkPropNo").click(function() {
		// open popup
		BootstrapDialog.show({
			modal : true,
			title : "Are you sure?",
			resize : "auto",
			position : 'center',
			height : 150,
			width : 600,

			onshow : function(dialog) {
				var markup = '<div>Are you want to perform this action?</div>';
				dialog.setMessage(markup);
				dialog.getModalHeader().addClass('email-title');
			},
			buttons : [ {
				cssClass : "btn btn-primary",
				label : "Cancel",
				action : function(dialog) {
					dialog.close();
				}
			}, {
				cssClass : "btn btn-primary",
				label : "Confirm",
				action : function(dialog) {
					resetManualScreen();
					dialog.close();
				}
			} ]
		});
	});

	function resetManualScreen() {

		$('#requestDataForm')[0].reset();
	}
	
	$(document).on('keydown','.Numeric', function(e){
		if(e.which == 8 || e.which == 46)
		{
			return true;
		}	
		if(e.which < 48 || e.which > 57)
							return false;
			return true;
	});
	
	function hideTableError(add)
	{
		if(add)
		{	
			var message = $("#tableDataRequiredMsg").text();
			if(message.indexOf("Policy Table") > -1)
			{
				$("#tableDataRequiredMsg").css('display','none');
			}
		}
	}
	
	$(document).on('keyup', '.desc-input-style', function(){
		var additionalDescription = $(".desc-input-style");
		var isValid =true;
		for(var index = 0; index < additionalDescription.length; index++)
		{
			if($(additionalDescription[index]).val() == "Enter your request notes..." || $(additionalDescription[index]).val() == "")
			{
				isValid = false;
				break;
			}
		}
		if(isValid)
		{
			$("#tableDataRequiredMsg").css('display','none');
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
	
	$("#activityLink").click(function(){
		showLoader();
	});
       
        //No validations on back button
	$("#btnBack").click(function(){
		$("form").validate().cancelSubmit = true;
	});
	
	$("#status").change(function(){
		changeStatusNote();
	});
	
	function changeStatusNote()
	{
		var placeholder = "";
		var status = $("#status").val();
		switch(status)
		{
		case "3":
				placeholder = 'Request has been approved';
				break;
		case "4":
				placeholder = 'Request status has been changed to In-Progress';
				break;
		case "5":
				placeholder = 'Request status has been changed to Completed';
				break;
		case "6":
				placeholder = 'Request has been sent for clarification';
				break;
		case "7":
				placeholder = 'Request has been rejected';
				break;
		case "8":
				placeholder = 'Request status has been changed to Re-opened';
				break;
		case "9":
				placeholder = 'Request has been cancelled';
				break;
		case "10":
				placeholder = 'Request status has been changed to Closed';
				break;
		}
		$("#statusChange").attr('placeholder', placeholder);
	}

})(jQuery);