$(document)
		.ready(
				function() {

	function showLoader()
	{
		$(".loaderDiv").css('display','block');
		$(".loaderDiv").width($('html').width() + 'px');
		$(".loaderDiv").height($('html').height() + 'px');
		$(".loaderDiv").faLoading();
	}
	

	function hideLoader()
	{	
		$(".loaderDiv").faLoading(false);
		$(".loaderDiv").css('display','none');
	}
	
	$("#autoSearch").click(function(){
		showLoader();
	});
	
	$('#propSearchTable').on( 'draw.dt', function () {
		hideLoader();
	} );
	$("#propSearchTable").on('page.dt', function(){
		//showLoader();
		$("input[name='prop_select_all']").prop('checked', false);
	});
	
	$(".view-res-show").click(function() {
		$("#reservTable").toggle(500, function() {
			$(".search-caret").toggleClass("caret-reversed");
		});

	});

	createPolicyCovTooltip();
	
	function createPolicyCovTooltip()
	{
		var tooltip = '';
		var table = '<table style="border:1px  #555;border-collapse:collapse;" width="400px" border=1>';
		table = table + "<tr style='text-align:center;'><td colspan='5' style='font-weight:bold;'>Property Policy Coverage</td></tr>";
		table = table + "<tr style='font-weight:bold;background-color:#ccc;'><td  style='text-align:left;'>Coverage Label</td><td>HO3</td><td>HO4</td><td>HO6</td><td>DP3</td></tr>";
		table = table + "<tr><td style='text-align:left;'>Dwelling (A)</td><td><i class='fa fa-check'></i></td><td></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Other Structures (B)</td><td><i class='fa fa-check'></i></td><td></td><td></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Building<br />Additions/Alterations<br />(CovCAdd)</td><td></td><td><i class='fa fa-check'></i></td><td></td><td></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Personal Property ( C)</td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Loss of Use (D)</td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Personal Liability ( E)</td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + "<tr><td style='text-align:left;'>Medical Payments to Others (F)</td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td><td><i class='fa fa-check'></i></td></tr>";
		table = table + '</table>';
		$("#policyCovTooltip").data('tooltip',false).attr('data-original-title',table).tooltip({'title':table});
	}
	$("#btnSearchResultProp").click(function() {
		showLoader();
		$("#searchresultsProp").css('display', 'block');
	});

	$(".dataTables_empty").attr('colspan', '13');
	$(".dataTables_empty").addClass('empty-row');
	$("#propSearchTable thead").on('click', 'th', function() {
		$(".dataTables_empty").addClass('empty-row');
	});

	hideNextPrevious();

	function hideNextPrevious() {
		if ($("#propSearchTable").DataTable().data().length == 0) {
			$('.dataTables_paginate').addClass('hide-class');
		}
	}
	
	if(propPolicyTableData != null)
	{
		var table = $("#propSearchTable").DataTable();
		for(var index = 0; index < propPolicyTableData.length; index++)
		{
			var rowData = propPolicyTableData[index];
			table.row.add([null, rowData.policynumber, rowData.policyStage, rowData.policyState, rowData.policyEffectDt, rowData.totalDue]);
		}
		table.draw();
	}
	$('.dataTables_paginate').removeClass('hide-class');
	
	$('#reservationTable')
			.DataTable(
					{
						paging : false,
						bFilter : false,
						bInfo : false,
						ordering : false,
						"columnDefs" : [ {
							"targets" : 3,
							"orderable" : false,
							"defaultContent" : "<img src='images/datamining/email.png'>"
						} ]
					});

	$(".dataTables_empty").attr('colspan', '13');
	$(".dataTables_empty").addClass('empty-row');
	$("#propSearchTable thead").on('click', 'th', function() {
		$(".dataTables_empty").addClass('empty-row');
	});

	$('#proPolLevelCoverage')
			.multiselect(
					{
						numberDisplayed : 1,
						nonSelectedText : '',
						maxHeight : '110',
						onChange : function(option, checked,
								select) {
							if (checked) {
								if ($(option).val() == 'Any') {
									$('#proPolLevelCoverage option:selected').prop('selected',
											false);
									$('#proPolLevelCoverage').multiselect('select',['Any']);
									$('#proPolLevelCoverage').multiselect('refresh');
								} else {
									$("#proPolLevelCoverage option[value='Any']").removeAttr("selected");
									$('#proPolLevelCoverage').multiselect('deselect',['Any']);
								}
							} else {
								if ($(option).val() == 'Any') {
								} else if ($('.dropdown-menu input:checkbox:checked').length == 0) {
									}
							}
						}
					});

	$('#propSearchTable tbody').on(
			'mouseenter',
			'td',
			function() {
				var tr = $(this).closest('tr');

				$("#propSearchTable tbody tr").removeClass(
						'highlight');
				$(tr).addClass('highlight');
			});

	$('#propSearchTable').on(
			'mouseout',
			function() {
				$("#propSearchTable tbody tr").removeClass(
						'highlight');
			});

	$('#reservationTable tbody').on(
			'mouseenter',
			'td',
			function() {
				var tr = $(this).closest('tr');

				$("#reservationTable tbody tr").removeClass(
						'highlight');
				$(tr).addClass('highlight');
			});

	$('#reservationTable').on(
			'mouseout',
			function() {
				$("#reservationTable tbody tr").removeClass(
						'highlight');
			});

	$('#btnRsetResultAuto').click(function() {
		// open popup
		resetAutoSearchScreen();
	});

	function resetAutoSearchScreen() {
		
		$("#envType > [value='PAS-EP2']").attr("selected",
				"true");
		$("#addproductType > [value='Any']").attr("selected",
				"true");
		$("#policyStage > [value='Any']").attr("selected",
				"true");
		$("#policyState1 > [value='Any']").attr("selected",
				"true");
		$("#policyType > [value='Any']").attr("selected",
				"true");
		$("option:selected").removeAttr("selected");
		$('#proPolLevelCoverage').multiselect('refresh');
		$('.multiselect-selected-text').text('Any');
		$('#proPolLevelCoverage option[value="Any"]').prop('selected',
				true);
		$('#proPolLevelCoverage').multiselect('refresh');
		$("#addPaymentPlan > [value='Any']").attr("selected",
				"true");
		$('input:radio[name="totalDueFlag"][value="Any"]').prop(
				'checked', true);
		$('input:radio[name="propPay"][value="Any"]').prop(
				'checked', true);
		$('input:radio[name="minDueFlag"][value="Any"]').prop(
				'checked', true);
		$('#advanceSearch .plus-minus-style').removeClass(
				'fa-minus-square-o');
		$('#advanceSearch .plus-minus-style').addClass(
				'fa-plus-square-o');
		$('#advSearch').css('display', 'none');
		$('#searchresultsProp').css('display', 'none');
	}

	$('#propSearchTable tbody').on(
			'click',
			'input[type="checkbox"]',
			function(e) {

				// Update state of "Select all" control
				updateDataTableSelectAllPropCtrl($(
						'#propSearchTable').DataTable());

				// Prevent click event from propagating to
				// parent
				e.stopPropagation();
			});

	function updateDataTableSelectAllPropCtrl(table) {
		var $table = table.table().node();
		var $chkbox_all = $('tbody input[type="checkbox"]',
				$table);
		var $chkbox_checked = $(
				'tbody input[type="checkbox"]:checked', $table);
		var chkbox_select_all = $(
				'thead input[name="prop_select_all"]', $table)
				.get(0);

		// If none of the checkboxes are checked
		if ($chkbox_checked.length === 0) {
			chkbox_select_all.checked = false;
			if ('indeterminate' in chkbox_select_all) {
				chkbox_select_all.indeterminate = false;
			}

			// If all of the checkboxes are checked
		} else if ($chkbox_checked.length === $chkbox_all.length) {
			chkbox_select_all.checked = true;
			if ('indeterminate' in chkbox_select_all) {
				chkbox_select_all.indeterminate = false;
			}

			// If some of the checkboxes are checked
		} else {
			chkbox_select_all.checked = true;
			if ('indeterminate' in chkbox_select_all) {
				chkbox_select_all.indeterminate = true;
			}
		}
	}

	// Handle click on table cells with checkboxes
	$('#propSearchTable').on(
			'click',
			'tbody td, thead th:first-child',
			function(e) {
				$(this).parent().find('input[type="checkbox"]')
						.trigger('click');
			});

	// Handle click on "Select all" control
	$(
			'#propSearchTable thead input[name="prop_select_all"]',
			$('#propSearchTable').DataTable().table()
					.container())
			.on(
					'click',
					function(e) {
						if (this.checked) {
							$(
									'#propSearchTable tbody input[type="checkbox"]:not(:checked)')
									.trigger('click');
						} else {
							$(
									'#propSearchTable tbody input[type="checkbox"]:checked')
									.trigger('click');
						}

						// Prevent click event from propagating
						// to parent
						e.stopPropagation();
					});

	$("#btnSaveProp,#btnCloseProp")
			.click(
					function() {
						if (($("#propSearchTable [type=checkbox]:checked").length) == 0) {
							if ($('.alertDiv').length == 0) {
								var alertDiv = $("<div class='alertDiv'></div>");
								alertDiv
										.html('<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><strong>Error </strong> Please select at least one record');
								alertDiv
										.addClass("alert alert-danger")
								$("#propSearchTable").parent()
										.append(alertDiv);
							}
						}
					});

	$('.btn-group').click(function() {
		$('.btn-group').toggleClass('open');
		if ($('.btn-group').hasClass('open')) {
			$('.multiselect').attr('aria-expanded', true);
		} else {
			$('.multiselect').attr('aria-expanded', false);
		}

	});

	$("#reserve")
		.click(
				function() {
					showLoader();
					var policyNumberArr = [];
					var table = $("#propSearchTable")
							.dataTable();
					$("input:checked", table.fnGetNodes())
							.each(
									function() {
										var row = $(
												this)
												.parents(
														"tr");
										var policyNumber = $(
												"#propSearchTable")
												.DataTable()
												.row(row).data()[1];
										policyNumberArr
												.push(policyNumber);
									});
					
					if (policyNumberArr.length == 0) {
						  $("#exportNotSelected").css(
									'display', 'none');
							$("#reserveNotSelected").css(
									'display', 'block');
							return false;
						}

					$('#policyNumners').val(
							policyNumberArr);
				});
	$('#propSearchTable').on(
			'click',
			'tbody td, thead th:first-child',
			function(e) {
				$(this).parent().find('input[type="checkbox"]')
						.trigger('click');
			});
			
	if(policyCovge!='' &&  policyCovge!=null){
		   if(policyCovge != 'Any'){
				var str_array = policyCovge.split(',');
				$('#proPolLevelCoverage option:selected').prop('selected',false);
				$('#proPolLevelCoverage').multiselect('select',str_array);
				$('#proPolLevelCoverage').multiselect('refresh');
				 $('#proPolLevelCoverage').next('.btn-group').find('.dropdown-menu input[type="checkbox"][value="Any"]').attr('checked',false);
			}
		   }
	
	$('#export')
			.click(
			function() {

				var policyNumberArr = [];
				var table = $("#propSearchTable").dataTable();
				$("input:checked", table.fnGetNodes())
						.each(
								function() {
									var row = $(
											this)
											.parents(
													"tr");
									var policyNumber = $(
											"#propSearchTable")
											.DataTable()
											.row(row).data()[1];
									policyNumberArr.push(policyNumber);
									$('#policyNumners').val(
											policyNumberArr);

								});
				
				if (policyNumberArr.length == 0) {

					$("#reserveNotSelected").css(
							'display', 'none');
					$("#exportNotSelected").css(
							'display', 'block');
					
					return false;
				}
				return true;
			});

	$("#btnCloseAuto")
		.click(
				function() {
					$('#propSearchTable').next(
							".my-error-class").remove();
					var checkboxes = $('.table-cell-input');
					var selected = checkboxes
							.filter(":checked").length;
					if (selected == false) {
						$('#propSearchTable')
								.after(
										'<div class="my-error-class"><font color="red">Please select atleast one record to export</font></div>');
						return false;
					}
				});
	
});