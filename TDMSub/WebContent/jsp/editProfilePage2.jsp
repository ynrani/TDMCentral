<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Data Subset | Index</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/theme.default.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script>
	$(document)
			.ready(
					function() {

						if ("${tdmProfilerDTO.editflag} == true") {
							var str = "${tdmProfilerDTO.passedConditions}";
							if (str != '')
								if (str.toLowerCase().indexOf("$") >= 0) {
									var strArraymain = str.split('$');
									for (var j = 0; j < strArraymain.length - 1; j++) {
										var strArray = strArraymain[j]
												.split('#');
										var markup = "<tr><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'><input type='checkbox' name='record' title='"+strArray[0]+"'></td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'>"
												+ strArray[0]
												+ "</td><td style='border: 1px solid #cdcdcd;' align='left'>"
												+ strArray[1]
												+ "</td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left' title='"+strArray[0]+"#"+strArray[1]+"#"+strArray[2]+"'>"
												+ strArray[2].replace('TDG_SG',"'") + "</td></tr>";
										//$("table tbody").append(markup);
										$('#fileTable > tbody:last-child')
												.append(markup);
										$(
												"#columnName option[value='"
														+ name + "']").remove();
									}
								} else {
									var strArray = str.split('#');

									var markup = "<tr><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'><input type='checkbox' name='record' title='"+strArray[0]+"'></td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'>"
											+ strArray[0]
											+ "</td><td style='border: 1px solid #cdcdcd;' align='left'>"
											+ strArray[1]
											+ "</td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left' title='"+strArray[0]+"#"+strArray[1]+"#"+strArray[2]+"'>"
											+ strArray[2].replace('TDG_SG',"'") + "</td></tr>";
									//$("table tbody").append(markup);
									$('#fileTable > tbody:last-child').append(
											markup);
									$(
											"#columnName option[value='" + name
													+ "']").remove();
								}
						}

						$("#selectedTablesMulti")
								.dblclick(
										function() {
											//alert("changed");
											// get reference to display textarea
											/* var display = document.getElementById('selectedTables');
											display.innerHTML = ''; // reset
											
											// callback fn handles selected options
											getSelectedOptions(this, callback);
											
											// remove ', ' at end of string
											var str = display.innerHTML.slice(0, -2);
											display.innerHTML = str; */
											/* $('option:selected', $(this)).each(function() {
												var str = $('#selectedTables').val();
												//alert($(this).val());
												if(str != ''){
													str += ","+$(this).val();
												}else
													str += $(this).val();
												//var str = display.innerHTML.slice(0, -2);
											    $('#selectedTables').val(str);
											    $("#selectedTablesMulti option[value='"+$(this).val()+"']").remove();
											  }); */

											$('option:selected', $(this))
													.each(
															function() {

																/* $('#selectedTables').append('<option val='+$(this).val()+'>'+$(this).val()+'</option>'); */
																/* $("#selectedTablesMulti option[value='"+$(this).val()+"']").remove(); */
														        var selectValue = $(this).val();
														        if(selectValue == 'ALL'){
														        	var str = $('#finalTabs').val();
														        	$('#selectedTablesMulti > option').each(function() {
														    	    		str += ","+$(this).val();
														    	    		
														    		        $("#selectedTablesMulti option[value='"+$(this).val()+"']").hide();
														    		        $("#selectedTables option[value='"+$(this).val()+"']").show();
														        	});
														        	$('#finalTabs').val(str);
														        }else{
														        	
														        var str = $('#finalTabs').val();
														        if(str != ''){
														    		str += ","+$(this).val();
														    	}else
														    		str += $(this).val();
														        $('#finalTabs').val(str);
														        $("#selectedTablesMulti option[value='"+$(this).val()+"']").hide();
														        $("#selectedTables option[value='"+$(this).val()+"']").show();
														        }
															});
										});

						$("#selectedTables").dblclick(
								function() {

									/* var str = $('#selectedTables').val();
									var res = str.replace($(this).val(), "");
									$('#selectedTables').val(str); */
									/*  $("#selectedTables option[value='"+$(this).val()+"']").remove(); */
									/* $('#selectedTablesMulti').append('<option val='+$(this).val()+'>'+$(this).val()+'</option>'); */
									var str = $('#finalTabs').val();
									var res = str.replace($(this).val(), "");
									$('#finalTabs').val(res);

									$(
											"#selectedTablesMulti option[value='"
													+ $(this).val() + "']")
											.show();
									$(
											"#selectedTables option[value='"
													+ $(this).val() + "']")
											.hide();
								});

						$("#add-row")
								.click(
										function() {
											var name = $("#columnName").val();
											var type = $("#type").val();

											var values = $("#values").val();
											if (values == null || values == '') {
												alert("Enter possible conditions for "
														+ name);
												$("#values").focus();
												return;
											}
											if (type == 'IN') {
												values = '(' + values + ')';
											}
											var title=name+"#"+type+"#";
								            //values.replace(/'/g , "TDM_SG");
								            title =title+values;
								            if(title.indexOf("'") > 0){
								            	title.replace("'" , "TDM_SG");
								            }
								            alert(title);
											var markup = "<tr><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'><input type='checkbox' name='record' title='"+name+"'></td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left'>"
													+ name
													+ "</td><td style='border: 1px solid #cdcdcd;' align='left'>"
													+ type
													+ "</td><td style='border: 1px solid #cdcdcd; padding :10px; ' align='left' title='"+title+"'>"
													+ values + "</td></tr>";
											//$("table tbody").append(markup);
											$('#fileTable > tbody:last-child')
													.append(markup);
											$(
													"#columnName option[value='"
															+ name + "']")
													.remove();
											$("#values").val('');
										});

						// Find and remove selected table rows
						$("#deleterow")
								.click(
										function() {
											var x = 0;
											$("table tbody")
													.find(
															'input[name="record"]')
													.each(
															function() {
																if ($(this)
																		.is(
																				":checked")) {
																	$(
																			"#columnName")
																			.append(
																					"<option value='"
																							+ $(
																									this)
																									.attr(
																											"title")
																							+ "'>"
																							+ $(
																									this)
																									.attr(
																											"title")
																							+ "</option>");
																	$(this)
																			.parents(
																					"tr")
																			.remove();
																	x++;
																}
															});
											if (x == 0) {
												alert("Please select atleast one record to delete");
												return;
											}
										});

						$("#createDictionary")
								.click(
										function() {
											var count = 0;
											var finalParam = '';
											$("#fileTable tbody tr")
													.each(
															function() {
																if (finalParam != '')
																	finalParam += '$';
																finalParam += $(
																		this)
																		.find(
																				"td:last-child")
																		.attr(
																				"title");
															});
											if (finalParam == '') {
												alert("Atleast one criteria is required");
												return;
											}
											finalParam += '$CREATEPROFILER';

											/*  if($("#dateFormates").val() != ''){
											  if(finalParam != '')
												  finalParam += '$';
											  finalParam += 'TDG_DATE_FORMAT'+$("#dateFormates").val();
											 }
											 //sequencePrefixTabs
											 if($("#sequencePrefixTabs").val() != ''){
											  if(finalParam != '')
												  finalParam += '$';
											  finalParam += 'TDG_SEQUENCE_PREFIX_TABS'+$("#sequencePrefixTabs").val();
											 }
											 if($("#businessRules").val() != ''){
											  if(finalParam != '')
												  finalParam += '$';
											  finalParam += 'TDG_BUSINESS_RULES'+$("#businessRules").val();
											 }
											 if($("#dependentDbs").val() != ''){
											  if(finalParam != '')
												  finalParam += '$';
											  finalParam += 'TDG_DEPENDENT_DBS'+$("#dependentDbs").val();
											 } */
											/*  $.ajaxSetup({
												global : false,
												type : "GET",
												url : './tdgaNextCreateMasterDictionary',
												beforeSend : function() {
													$(".modal").show();
												},
											//complete: function () {
											//$(".modal").hide();
											//}
											}); */
											$.ajaxSetup({
												global : false,
												type : "POST",
												url : './editProfilerSubmit',
												beforeSend : function() {
													$(".modal").show();
												},
											//complete: function () {
											//$(".modal").hide();
											//}
											});
											$
													.ajax({
														data : {
															reqVals : finalParam
														},
														success : function(
																responseText) {
															if (responseText
																	.indexOf("#") > -1) {

																var res = responseText
																		.split("#");
																alert(res[1]);
																if (responseText
																		.indexOf("SUCCESS") > -1) {
																	document.location.href = '${pageContext.request.contextPath}/tdmProfilersDashboard';
																}
															}
															/* if (responseText.toLowerCase().indexOf("created") >= 0){
																alert(responseText);
																document.location.href="./createProfilePage";
															}else{
																alert(responseText);
															} */
														}
													});
										});

					});

	function clickEvent() {
		var str = $('#finalTabs').val();
		$.ajaxSetup({
			global : false,
			type : "POST",
			url : './editProfiler',
			beforeSend : function() {
				$(".modal").show();
			},
		//complete: function () {
		//$(".modal").hide();
		//}
		});
		$.ajax({
			data : {
				reqVals : str
			},
			success : function(responseText) {
			}
		});
	}

	function getSelectedOptions(sel, fn) {
		var opts = [], opt;

		// loop through options in select list
		for (var i = 0, len = sel.options.length; i < len; i++) {
			opt = sel.options[i];

			// check if selected
			if (opt.selected) {
				// add to array of option elements to return from this function
				opts.push(opt);

				// invoke optional callback function if provided
				if (fn) {
					fn(opt);
				}
			}
		}

		// return array containing references to selected option elements
		return opts;
	}

	function callback(opt) {
		// display in textarea for this example
		var display = document.getElementById('selectedTables');
		display.innerHTML += opt.value + ', ';

		// can access properties of opt, such as...
		//alert( opt.value )
		//alert( opt.text )
		//alert( opt.form )
	}
	
	 function fetchRelationalTabs(){
		 var str = $('#finalTabs').val();
		 $.ajaxSetup({
				global : false,
				type : "GET",
				url : './fetchRelationTabs',
				beforeSend : function() {
					$(".modal").show();
				},
			//complete: function () {
			//$(".modal").hide();
			//}
			});
			$.ajax({
				data : {
					reqVals : str
				},
				success : function(responseText) {
					$(".modal").hide();
					$("#relationTabs option").remove();
					if (responseText.indexOf(",") > -1) {
	  					
						var res = responseText.split(",");
						
						for(var i=0;i<res.length;i++){
							if(res[i] != ''){
								$('#relationTabs').append('<option value='+res[i]+'>'+res[i]+'</option>'); 
							}
						}
						}else{
							if(responseText != '')
								$('#relationTabs').append('<option value='+responseText+'>'+responseText+'</option>');
						}
						
			}
	});
	 }
</script>


</head>
<body>

	<div class="mainAll">

		<jsp:include page="indexHeader.jsp"></jsp:include>
		<section class="bodySec">
			<div class="container">
				<form:form id="tdmProfileForm" name="tdmProfileForm"
					action="${pageContext.request.servletContext.contextPath}/editProfiler"
					modelAttribute="tdmProfilerDTO">
					<form:hidden path="finalTabs" id="finalTabs" />
					<div class="two-col">
						<h2 style="color: #0098cc; padding-top: 5%;">Edit Profile</h2>
						<table
							style="width: 100%; border: 0; font-size: 13px; cellpadding: 2; border-spacing: 5px; padding: 2% 0% 0% 0%;">
							<tbody>
								<tr>
									<td class="lable-title" width="20%" align="left"
										valign="middle">Profile Name</td>
									<td class="flied-title" width="30%" align="left"
										valign="middle" colspan="2"><b>${tdmProfilerDTO.profilerName}</b>

									</td>
								</tr>
								<tr>
                		<td class="lable-title" width="20%" align="left" valign="middle">Start Table</td>
                		<td class="flied-title" width="30%" align="left" valign="middle" colspan="3">
                  		<form:select path="startTable" id="startTable" class="down-control" >
                  		<c:forEach items="${tdmProfilerDTO.listTables}" var="dbConnectionsDTOs" varStatus="status">
                  		<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
                  		</c:forEach>
                  		</form:select>
	                    </td>
                 	  </tr>
								<tr>
									<td class="lable-title" align="left" valign="middle">Available
										Tables<span>*</span>
									</td>
									<td class="flied-title" align="left" valign="middle"><form:select
											path="listTables" id="selectedTablesMulti"
											class="down-control" multiple="multiple" size="15">
											<%-- <c:forEach items="${tdmProfilerDTO.listTables}" var="dbConnectionsDTOs" varStatus="status">
		<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
		</c:forEach> --%>
		<option value="ALL">ALL</option>
											<c:choose>
												<c:when
													test="${tdmProfilerDTO.listSelectedTabs ne null &&  not empty tdmProfilerDTO.listSelectedTabs}">
													<c:forEach items="${tdmProfilerDTO.listTables}"
														var="dbConnectionsDTOs" varStatus="status">
														<c:set var="refreshSent" value="${true}" />
														<c:forEach items="${tdmProfilerDTO.listSelectedTabs}"
															var="dbPassedTabs" varStatus="tabstatus">

															<c:if
																test="${fn:containsIgnoreCase(dbConnectionsDTOs,dbPassedTabs)}">
																<option value="${dbConnectionsDTOs}" hidden="true">${dbConnectionsDTOs}</option>
																<c:set var="refreshSent" value="${false}" />
															</c:if>

														</c:forEach>


														<c:if test="${refreshSent}">
															<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
														</c:if>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${tdmProfilerDTO.listTables}"
														var="dbConnectionsDTOs" varStatus="status">
														<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
													</c:forEach>
												</c:otherwise>
											</c:choose>

										</form:select></td>
									<td class="flied-title" align="left" valign="middle">
										<%-- <form:textarea id="selectedTables" path="selectedTables" cols="40" rows="16" /> --%>
										<%-- <form:select path="listTables" id="selectedTables" class="down-control" multiple="multiple" size="15"> --%>
										<form:select path="selectedTables" id="selectedTables"
											class="down-control" multiple="multiple" size="15">
											<c:choose>
												<c:when
													test="${tdmProfilerDTO.listSelectedTabs ne null &&  not empty tdmProfilerDTO.listSelectedTabs}">

													<c:forEach items="${tdmProfilerDTO.listTables}"
														var="dbConnectionsDTOs" varStatus="status">
														<c:forEach items="${tdmProfilerDTO.listSelectedTabs}"
															var="dbPassedTabs" varStatus="tabstatus">
															<c:set var="refreshSent" value="${true}" />
															<c:if
																test="${fn:containsIgnoreCase(dbConnectionsDTOs,dbPassedTabs)}">
																<option value="${dbConnectionsDTOs}">${dbConnectionsDTOs}</option>
																<c:set var="refreshSent" value="${false}" />
															</c:if>
														</c:forEach>
														<c:if test="${refreshSent}">
															<option value="${dbConnectionsDTOs}" hidden="true">${dbConnectionsDTOs}</option>
														</c:if>

													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${tdmProfilerDTO.listTables}"
														var="dbConnectionsDTOs" varStatus="status">
														<option value="${dbConnectionsDTOs}" hidden="true">${dbConnectionsDTOs}</option>
													</c:forEach>
												</c:otherwise>
											</c:choose>


										</form:select>
									</td>
									<td class="flied-title" align="left" valign="middle"><input
										type="button" class="btn-primary btn-cell" id="fetchFkValues"
										value="Find Relational Tables" onclick="fetchRelationalTabs()"><br>
									<br> <form:select path="relationTabs" id="relationTabs"
											class="down-control" multiple="multiple" size="10">
										</form:select></td>
								</tr>

							</tbody>
						</table>


						<table
							style="width: 50%; border: 0; font-size: 13px; cellpadding: 4; padding: 1% 0% 0% 0%;">
							<tbody>
								<tr>
									<td colspan="2" align="center" valign="middle"
										class="buttonsAll22">
										<!-- <input type="button" name="create" id="fetchcolumns" onclick="clickEvent()" value="Fetch Columns"> -->
										<input type="submit" name="create" id="fetchcolumns"
										value="Fetch Columns">
									</td>
								</tr>
							</tbody>
						</table>

					</div>

					<c:if
						test="${tdmProfilerDTO.listColumns ne null &&  not empty tdmProfilerDTO.listColumns}">
						<div id="basedOnSelect">
							<div style="display: block;width=90%;">
								Coumn Name
								<!-- <input type="text" id="columnName" required="required" placeholder="Column Name" class="form-control-half-dictionary"> -->
								<select id="columnName" class="form-control-half-dictionary">
									<c:forEach items="${tdmProfilerDTO.listColumns}"
										var="columnName1" varStatus="status">
										<option value="${columnName1}">${columnName1}</option>
									</c:forEach>
								</select>&nbsp; Operator <select id="type"
									class="form-control-half-dictionary-small">
									<option value="=">=</option>
									<option value="<">&lt;</option>
									<option value=">">&gt;</option>
									<option value="LIKE">LIKE</option>
									<option value="AND">AND</option>
									<option value="OR">OR</option>
									<option value="BETWEEN">BETWEEN</option>
									<option value="IN">IN</option>
								</select> &nbsp; Condition <input type="text" id="values"
									placeholder="Please enter condition"
									class="form-control-half-dictionary"> &nbsp; <input
									type="button" class="btn-primary btn-cell" id="add-row"
									value="Add Row">
							</div>
							<!-- 
        <input type="text" id="email" placeholder="Email Address" class="form-control">
    	<input type="button" class="btn-primary btn-cell" id="add-row" value="Add Row"> -->
							<table id="fileTable"
								style="width: 100%; font-size: 13px; border: 0; cellpadding: 0; cellspacing: 1; margin-bottom: 20px; border-collapse: collapse;">
								<thead>
									<tr>
										<th style="border: 1px solid #cdcdcd; padding: 10px;"
											bgcolor="#E3EFFB" scope="col" class="whitefont"
											align="center">Select</th>
										<th style="border: 1px solid #cdcdcd; padding: 10px;"
											bgcolor="#E3EFFB" scope="col" class="whitefont"
											align="center">Column Name</th>
										<th style="border: 1px solid #cdcdcd; padding: 10px;"
											bgcolor="#E3EFFB" scope="col" class="whitefont"
											align="center">Operator</th>
										<th style="border: 1px solid #cdcdcd; padding: 10px;"
											bgcolor="#E3EFFB" scope="col" class="whitefont"
											align="center">Conditions</th>
									</tr>
								</thead>
								<tbody>
									<!--   <tr>
                <td align="left"><input type="checkbox" name="record"></td>
                <td align="left">Peter Parker</td>
                <td align="left">peterparker@mail.com</td>
            </tr>
 -->
								</tbody>
							</table>
							<input type="button" id="deleterow" class="btn-primary btn-cell"
								value="Delete Row" /> &nbsp; <input type="button"
								id="createDictionary" class="btn-primary btn-cell"
								value="Create/Update Profile" />
						</div>
					</c:if>
				</form:form>
			</div>
		</section>
		<script src="include/footer.js"></script>
	</div>
	<script>
		menu_highlight('admin');
		menu_highlight('admin_db_connection');
		menu_highlight('db_connection');
		
		
		
	  
	</script>
</body>
</html>
