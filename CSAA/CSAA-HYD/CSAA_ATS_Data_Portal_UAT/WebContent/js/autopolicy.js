$(document)
		.ready(
				function() {
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
					$("#propSearch").click(function(){
						showLoader();
					});
					
					$("#advSearch").hide();
					$("#autoSearchTable").on(
							'page.dt',
							function() {
								//showLoader();
								$("input[name='auto_select_all']").prop(
										'checked', false);
											});
					$("#btnSearchResultAuto").click(function() {
						showLoader();
						$("#searchresultsAuto").css('display', 'block');
																});
					function showSearchResultDiv() {
						$("#searchresultsAuto").css('display', 'block');
							};
					
					$('#policyCovge')
							.multiselect(
									{
										numberDisplayed : 1,
										nonSelectedText : ' ',
										maxHeight : '110',
										onChange : function(option, checked,
												select) {
											if (checked) {
												if ($(option).val() == 'Any') {
													$('#policyCovge option:selected').prop('selected',
															false);
													$('#policyCovge').multiselect('select',['Any']);
													$('#policyCovge').multiselect('refresh');
													} else {
														$("#policyCovge option[value='Any']").removeAttr("selected");
														$('#policyCovge').multiselect('deselect',['Any']);
												}
											} else {
												if ($(option).val() == 'Any') {
												} else if ($('#policyCovge')
														.next('.btn-group')
														.find(
																'.dropdown-menu input:checkbox:checked').length == 0) {
													}
											}
										}
									});
					$('#addRiskCovge')
							.multiselect(
									{
										numberDisplayed : 1,
										nonSelectedText : ' ',
										maxHeight : '110',
										onChange : function(option, checked,
												select) {
											if (checked) {
												if ($(option).val() == 'Any') {
													$('#addRiskCovge option:selected').prop('selected',
															false);
													$('#addRiskCovge').multiselect('select',['Any']);
													$('#addRiskCovge').multiselect('refresh');
												} else {
													$("#addRiskCovge option[value='Any']").removeAttr("selected");
													
													$('#addRiskCovge').multiselect('deselect',['Any']);
												}
											} else {
												if ($(option).val() == 'Any') {
													
												} else if ($('#addRiskCovge')
														.next('.btn-group')
														.find(
																'.dropdown-menu input:checkbox:checked').length == 0) {
												}
											}
										}
									});				

					$('#autoSearchTable tbody').on(
							'mouseenter',
							'td',
							function() {
								var tr = $(this).closest('tr');

								$("#autoSearchTable tbody tr").removeClass(
										'highlight');
								$(tr).addClass('highlight');
							});

					$('#autoSearchTable').on(
							'mouseout',
							function() {
								$("#autoSearchTable tbody tr").removeClass(
										'highlight');
							});

					$('#autoReservationTable tbody').on(
							'mouseenter',
							'td',
							function() {
								var tr = $(this).closest('tr');

								$("#autoReservationTable tbody tr")
										.removeClass('highlight');
								$(tr).addClass('highlight');
							});

					$('#autoReservationTable').on(
							'mouseout',
							function() {
								$("#autoReservationTable tbody tr")
										.removeClass('highlight');
							});

					$('#btnRsetResultAuto').click(function() {
						// open popup
						resetAutoSearchScreen();
					});

					function resetAutoSearchScreen() {

						if ($('#riskState').val() == 'CA - California'
								|| $('#riskState').val() != 'CA - California') {
							$('#riskState').empty();
							$('#riskState')
									.append('<option val="Any">Any</option>')
									.append(
											'<option val="AZ">AZ - Arizona</option>')
									.append(
											'<option val="CA">CA - California</option>')
									.append(
											'<option val="CO">CO - Colorado</option>')
									.append(
											'<option val="CT">CT - Connecticut</option>')
									.append(
											'<option val="DC">DC - District of Columbia</option>')
									.append(
											'<option val="DE">DE - Delaware</option>')
									.append(
											'<option val="ID">ID - Idaho</option>')
									.append(
											'<option val="IN">IN - Indiana</option>')
									.append(
											'<option val="KS">KS - Kansas</option>')
									.append(
											'<option val="KY">KY - Kentucky</option>')
									.append(
											'<option val="MD">MD - Maryland</option>')
									.append(
											'<option val="MT">MT - Montana</option>')
									.append(
											'<option val="NJ">NJ - New Jersey</option>')
									.append(
											'<option val="NV">NV - Nevada</option>')
									.append(
											'<option val="NY">NY - New York</option>')
									.append(
											'<option val="OH">OH - Ohio</option>')
									.append(
											'<option val="OK">OK - Oklahoma</option>')
									.append(
											'<option val="OR">OR - Oregon</option>')
									.append(
											'<option val="PA">PA - Pennsylvania</option>')
									.append(
											'<option val="SD">SD - South Dakota</option>')
									.append(
											'<option val="UT">UT - Utah</option>')
									.append(
											'<option val="VA">VA - Virginia</option>')
									.append(
											'<option val="WV">WV - West Virginia</option>')
									.append(
											'<option val="WY">WY - Wyoming</option>');
							$('#riskState').show();
						}

						$("#envType > [value='PAS-EP2']").attr("selected",
								"true");
						$("#addproductType > [value='Any']").attr("selected",
								"true");
						$("#policyStage > [value='Any']").attr("selected",
								"true");
						$("#riskState > [value='Any']")
								.attr("selected", "true");
						$("#policyTerm > [value='Any']").attr("selected",
								"true");
						$("option:selected").removeAttr("selected");
						$('#policyCovge').multiselect('refresh');
						$('.multiselect-selected-text').text('Any');
						$('#policyCovge option[value="Any"]').prop('selected',
								true);
						$('#policyCovge').multiselect('refresh');
						
						$('#policyCovge')
								.next('.btn-group')
								.find(
										'.dropdown-menu input[type="checkbox"][value="Any"]')
								.attr('disabled', false);
						$('#addRiskCovge option[value="Any"]').prop('selected',
								true);
						$('#addRiskCovge').multiselect('refresh');
						
						$('input:radio[name="autoDeath"][value="Any"]').prop(
								'checked', true);
						$('input:radio[name="autoTotDisa"][value="Any"]').prop(
								'checked', true);
						$("#payPlanAuto > [value='Any']").attr("selected",
								"true");
						$('input:radio[name="totalDue"][value="Any"]').prop(
								'checked', true);
						$('input:radio[name="autoPay"][value="Any"]').prop(
								'checked', true);
						$('input:radio[name="minimumDue"][value="Any"]').prop(
								'checked', true);
						$('#advanceSearch .plus-minus-style').removeClass(
								'fa-minus-square-o');
						$('#advanceSearch .plus-minus-style').addClass(
								'fa-plus-square-o');
						$('#advSearch').css('display', 'none');
						$("#autoNoDriv > [value='0']").attr("selected", "true");
						$("#autoNameInsured > [value='0']").attr("selected",
								"true");
						$("#autoNoVehi > [value='0']").attr("selected", "true");
						$("#autoNoVio > [value='0']").attr("selected", "true");
						$('#searchresultsAuto').css('display', 'none');
					}
					
					if(autoPolicyTableData != null)
					{
						var table = $("#autoSearchTable").DataTable();
						for(var index = 0; index < autoPolicyTableData.length; index++)
						{
							var rowData = autoPolicyTableData[index];
							table.row.add([null, rowData.policynumber, rowData.policyStage, rowData.policyState, rowData.policyEffectDt, rowData.noOfDrivers, rowData.noOfVehi,rowData.noOfViola, rowData.totalAmountDue ]);
						}
						table.draw();
					}

					$('#autoSearchTable tbody').on(
							'click',
							'input[type="checkbox"]',
							function(e) {
								// Update state of "Select all" control
								updateDataTableSelectAllCtrl($(
										'#autoSearchTable').DataTable());

								// Prevent click event from propagating to
								// parent
								e.stopPropagation();
							});

					function updateDataTableSelectAllCtrl(table) {
						var $table = table.table().node();
						var $chkbox_all = $('tbody input[type="checkbox"]',
								$table);
						var $chkbox_checked = $(
								'tbody input[type="checkbox"]:checked', $table);
						var chkbox_select_all = $(
								'thead input[name="auto_select_all"]', $table)
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
					$('#autoSearchTable').on(
							'click',
							'tbody td, thead th:first-child',
							function(e) {

								$(this).parent().find('input[type="checkbox"]')
										.trigger('click');
							});

					// Handle click on "Select all" control
					$(
							'#autoSearchTable thead input[name="auto_select_all"]',
							$('#autoSearchTable').DataTable().table()
									.container())
							.on(
									'click',
									function(e) {
										if (this.checked) {
											$(
													'#autoSearchTable tbody input[type="checkbox"]:not(:checked)')
													.trigger('click');
										} else {
											$(
													'#autoSearchTable tbody input[type="checkbox"]:checked')
													.trigger('click');
										}

										// Prevent click event from propagating
										// to parent
										e.stopPropagation();
									});

					$("#reserve")
							.click(
									function() {
										showLoader();
										var policyNumberArr = [];
										var table = $("#autoSearchTable")
												.dataTable();
										$("input:checked", table.fnGetNodes())
												.each(
														function() {
															var row =  $(
																	this)
																	.parents(
																			"tr");
															var policyNumber = $("#autoSearchTable").DataTable().row(row).data()[1];
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

										$('#policyNumners')
												.val(policyNumberArr);

									});

					$(function() {
						$('#export')
								.click(
										function() {
											var policyNumberArr = [];
											var table = $("#autoSearchTable")
													.dataTable();
											$("input:checked",
													table.fnGetNodes())
													.each(
															function() {
																var row =  $(
																		this)
																		.parents(
																				"tr");
																var policyNumber = $("#autoSearchTable").DataTable().row(row).data()[1];
																policyNumberArr
																		.push(policyNumber);
																$(
																		'#policyNumners')
																		.val(
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
					});

					$('.btn-group').click(function() {
						$('.btn-group').toggleClass('open');
						if ($('.btn-group').hasClass('open')) {
							$('.multiselect').attr('aria-expanded', true);
						} else {
							$('.multiselect').attr('aria-expanded', false);
						}

					});

					if (riskStateKey != '' && riskStateKey != null) {
						$('#riskState option[value="'+riskStateVal+'"]').attr('selected',true);
					}
					if (policycoverge != '' && policycoverge != null) {

						if (policycoverge != 'Any') {
							var str_array = policycoverge.split(',');
							$('#policyCovge option:selected').prop('selected',
									false);
							$('#policyCovge').multiselect('select', str_array);
							$('#policyCovge').multiselect('refresh');
							$('#policyCovge')
									.next('.btn-group')
									.find(
											'.dropdown-menu input[type="checkbox"][value="Any"]')
									.attr('checked', false);
							} 
					}

					if (addRiskCovge != '' && addRiskCovge != null) {
						if (addRiskCovge != 'Any') {
							var str_array = addRiskCovge.split(',');
							$('#addRiskCovge option:selected').prop('selected',
									false);
							$('#addRiskCovge').multiselect('select', str_array);
							$('#addRiskCovge').multiselect('refresh');
							$('#addRiskCovge')
									.next('.btn-group')
									.find(
											'.dropdown-menu input[type="checkbox"][value="Any"]')
									.attr('checked', false);
						}
					}

				});

$('#addproductType')
		.change(
				function() {
					
					if ($(this).val() == 'AAA_CSA') {

						$('#riskState').empty();
						$('#riskState').append(
								'<option val="CA">CA - California</option>');

						$('#riskState').show();
					} else if ($(this).val() == 'AAA_SS') {
						$('#riskState').empty();
						$('#riskState')
								.append('<option val="Any">Any</option>')
								.append(
										'<option val="AZ">AZ - Arizona</option>')
								.append(
										'<option val="CO">CO - Colorado</option>')
								.append(
										'<option val="CT">CT - Connecticut</option>')
								.append(
										'<option val="DC">DC - District of Columbia</option>')
								.append(
										'<option val="DE">DE - Delaware</option>')
								.append('<option val="ID">ID - Idaho</option>')
								.append(
										'<option val="IN">IN - Indiana</option>')
								.append('<option val="KS">KS - Kansas</option>')
								.append(
										'<option val="KY">KY - Kentucky</option>')
								.append(
										'<option val="MD">MD - Maryland</option>')
								.append(
										'<option val="MT">MT - Montana</option>')
								.append(
										'<option val="NJ">NJ - New Jersey</option>')
								.append('<option val="NV">NV - Nevada</option>')
								.append(
										'<option val="NY">NY - New York</option>')
								.append('<option val="OH">OH - Ohio</option>')
								.append(
										'<option val="OK">OK - Oklahoma</option>')
								.append('<option val="OR">OR - Oregon</option>')
								.append(
										'<option val="PA">PA - Pennsylvania</option>')
								.append(
										'<option val="SD">SD - South Dakota</option>')
								.append('<option val="UT">UT - Utah</option>')
								.append(
										'<option val="VA">VA - Virginia</option>')
								.append(
										'<option val="WV">WV - West Virginia</option>')
								.append(
										'<option val="WY">WY - Wyoming</option>');
						$('#riskState').show();

					} else {
						$('#riskState').empty();
						$('#riskState')
								.append('<option val="Any">Any</option>')
								.append(
										'<option val="AZ">AZ - Arizona</option>')
								.append(
										'<option val="CA">CA - California</option>')
								.append(
										'<option val="CO">CO - Colorado</option>')
								.append(
										'<option val="CT">CT - Connecticut</option>')
								.append(
										'<option val="DC">DC - District of Columbia</option>')
								.append(
										'<option val="DE">DE - Delaware</option>')
								.append('<option val="ID">ID - Idaho</option>')
								.append(
										'<option val="IN">IN - Indiana</option>')
								.append('<option val="KS">KS - Kansas</option>')
								.append(
										'<option val="KY">KY - Kentucky</option>')
								.append(
										'<option val="MD">MD - Maryland</option>')
								.append(
										'<option val="MT">MT - Montana</option>')
								.append(
										'<option val="NJ">NJ - New Jersey</option>')
								.append('<option val="NV">NV - Nevada</option>')
								.append(
										'<option val="NY">NY - New York</option>')
								.append('<option val="OH">OH - Ohio</option>')
								.append(
										'<option val="OK">OK - Oklahoma</option>')
								.append('<option val="OR">OR - Oregon</option>')
								.append(
										'<option val="PA">PA - Pennsylvania</option>')
								.append(
										'<option val="SD">SD - South Dakota</option>')
								.append('<option val="UT">UT - Utah</option>')
								.append(
										'<option val="VA">VA - Virginia</option>')
								.append(
										'<option val="WV">WV - West Virginia</option>')
								.append(
										'<option val="WY">WY - Wyoming</option>');
						$('#riskState').show();

					}

					$('#autoSearchTable').on('draw.dt', function() {
						hideLoader();
					});

				});
