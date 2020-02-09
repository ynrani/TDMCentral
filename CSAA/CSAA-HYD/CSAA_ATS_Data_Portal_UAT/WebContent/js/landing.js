
$(document).ready(function() {	
		
		var menuData = [{ "parentLink":"ATS Data",
							"parentClass":'fa-university',
							"child":[
										{
											"text" : "Governance",
											"link" :"governance"
										},
										{
											"text" : "Knowledge Management",
											"link" :"#"
										},
										{
											"text" : "Service Catalog",
											"link" :"#"
										},
										{
											"text" : "Operating Model",
											"link" :"#"
										},
										{
											"text" : "Test Data Planning",
											"link" :"#"
										}	
									]
							},{
								"parentLink":"Data Services",
								"parentClass":'fa fa-cogs',
							"child":[
										/*{
											"text" : "Data Generation",
											"child" :[{
												"text" : "Auto",
												"link" :"./dgAutomation"
											},
											{
												"text" : "Manual",
												"link" :"./requestData"
											}	
										]
										},*/
										{
											"text" : "Data Generation",
											"link" :"./dgAutomation"
										},
										{
											"text" : "Data Subset",
											"link" :"#"
										},
										{
											"text" : "Data Restore/Copy",
											"link" :"#"
										},
										{
											"text" : "Data Mining",
											"link" :"./dataMinningAutoPolicySearch"
										},
										{
											"text" : "Sensitivity Profiling",
											"link" :"#"
										}
									]
							},{
								"parentLink":"Metrics",
								"parentClass":'fa-line-chart',
							"child":[
										{
											"text" : "Dashboard",
											"link" :"#"
										},
										{
											"text" : "Reports",
											"link" :"#"
										}
									]
							},{
								"parentLink":"User Panel",
								"parentClass":'fa-tachometer',
							"child":[
										{
											"text" : "My Requests",
											"link" :"./findRequest"
										},
										{
											"text" : "My Reservations",
											"link" :"./myReservationAuto"
										}
									]
							},{
								"parentLink":"Admin",
								"parentClass":'fa-user',
							"child":[
										{
											"text" : "Service Desk",
											"link" :"adminDashBoard"
										},
										{
											"text" : "User Management",
											"link" :"#"
										},
										{
											"text" : "Portal Configuration",
											"link" :"#"
										},
										{
											"text" : "Monitor",
											"link" :"#"
										},
										{
											"text" : "Auto Unreserve Data",
											"link" :"#"
										}
									]
							}
						];
						
		
		
		prepareMenu();
		
		
		function prepareMenu()
		{
			var mainUl = $('<ul></ul>');
			function prepareSubmenu(menu){
				
				var mainLi = $('<li></li>');
				var mainLink = $('<a></a>');
				mainLink.attr('href','javascript:void(0)');
				var childIcon = $('<i class="fa" style="display:inline-block"></i>');
				childIcon.addClass(menu.parentClass);
				mainLink.append(childIcon);
				var childTit = $('<span style="display:inline-block"></span>');
				childTit.text(menu.parentLink);
			    mainLink.append(childTit);
				mainLi.append(mainLink);
				var subMenu = $('<ul></ul>');
				subMenu.addClass('sub-menu');
				var contentBefore = $('<li></li>');		
				contentBefore.addClass('contentBefore');		
				subMenu.append(contentBefore)		
				var contentAfter = $('<li></li>');		
				contentAfter.addClass('contentAfter');
				for(var childIndex in menu.child)
				{
					var shouldAdd = true;
					var child = menu.child[childIndex];
					var childLi = null;
					var childLink = null;
					var subChild = null;
					if(child.child == undefined)
					{
						childLi = $('<li></li>');
						childLink = $('<a class="menu-item-style"></a>');
						childLink.data('linkname',child.link);
						childLink.text(child.text);
						childLink.click(function(){
							var linkname = $(this).data('linkname');
							window.location.href = linkname;
						});
					}
					else
					{
						childLi = $('<li></li>');
						childLi.addClass('dropdown-submenu1');
						childLink = $('<a class="menu-item-style"></a>');
						childLink.text(child.text);
						//childLink.append("<i class='fa fa-caret-right'></i>");
						subChild = $('<ul></ul>');
						subChild.addClass('dropdown-menu child-dropdownMenu');
						for(var subChildIndex in child.child)
						{
							var subChild1 = child.child[subChildIndex];
							var subChildLi = $('<li></li>');
							var subChildLink = $('<a class="menu-item-style child-menu-style"></a>');
							subChildLink.data('linkname',subChild1.link);
							subChildLink.text(subChild1.text);
							subChildLink.click(function(){
								var linkname = $(this).data('linkname');
								window.location.href = linkname;
							});
							subChildLi.append(subChildLink);
							subChild.append(subChildLi);
						}
					}
					if(userRole == 'ROLE_ADMIN')
					{
						if(child.text =='Data Generation'  ||  child.text =='My Requests') {
							shouldAdd = false;
						}
					}
					else if(userRole == 'ROLE_USER')
					{
						if(child.text =='Service Desk' || child.text =='User Management' || child.text =='Service Desk' || child.text =='Portal Configuration' || child.text =='Monitor' || child.text =='Auto Unreserve Data')
						{
							shouldAdd = false;
						}
					}
					if(shouldAdd == true)
					{
						childLi.append(childLink);
						if(subChild != null)
						{
							childLi.append(subChild);
						}
						subMenu.append(childLi);
					}
				}
				subMenu.append(contentAfter);
				mainLi.append(subMenu);
				mainUl.append(mainLi);
			};
			for(var menuIndex in menuData)
			{
				var menu = menuData[menuIndex];
				if(menu.parentLink == 'Admin') {
					if(userRole =='ROLE_ADMIN' ) {
						prepareSubmenu(menu);
					}
				} 
				else if(userRole == 'ROLE_ADMIN' && menu.parentLink == 'User Panel')
				{
					
				}
				else {
					prepareSubmenu(menu);
				}
				
				
			}
			$('.other-nav').append(mainUl);
			$('.other-nav > ul > li').hover(function(){
				var calculatedPosition = ((($(this).width())/2)-10)+"px";
				$(this).find("ul.sub-menu .contentBefore").css("marginLeft",calculatedPosition);
				$(this).find("ul.sub-menu .contentAfter").css("marginLeft",calculatedPosition);
			});
		}
		
		var path = window.location.pathname;
		var page = path.split("/").pop();
		if(page != 'index')
		{
			$('.main-header .logo').click(function(){
				showLoader();
				window.location.href = "./index";
			});
		}
		else
		{
			$('.page-menu .logo').css('cursor','auto');
			if(userRole == 'ROLE_ADMIN')
			{
				$("#dataGenTile").addClass('disable-hover');
				$("#dataGenLink").removeAttr("href");
			}
		}
		
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
		
		$(".tile-link").click(function(){
			if(this.href != undefined && this.href != "")
			{
				showLoader();
			}
		});
});



