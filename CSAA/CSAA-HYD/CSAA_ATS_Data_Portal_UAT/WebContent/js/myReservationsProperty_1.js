(function($){

	$(document).ready(function() {
		//code for admin dashboard
		
	// For next and previous pages	
		
		$('#propReservationTable').DataTable( {
			paging:true,
			bInfo: true,
			 "bFilter" : false,
             "dom": '<"top"iflp<"clear">>rt',
             'order': [1, 'asc'],
			"columnDefs": [ 
			{
				 "targets": 0,
                 "sTitle": "<input type='checkbox' name='auto_select_all'></input>",
                 "orderable": false,
                 "width":"15px",
                 "defaultContent": "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
	        },
	        {
	            "targets": 1,       
	             "width":"80px"
	        }
	        ]
	    } );
		
		$(".dataTables_empty").addClass('empty-row');
		$("#propReservationTable thead").on('click','th',function() {
			$(".dataTables_empty").addClass('empty-row');
		} );
		
	// end next and previous page	

		$('#propReservationTable tbody')
			.on( 'mouseenter', 'td', function () {
				var tr = $(this).closest('tr');

				$("#propReservationTable tbody tr").removeClass( 'highlight' );
				$(tr).addClass( 'highlight' );
			} );

		$('#propReservationTable').on('mouseout',function () {
			$("#propReservationTable tbody tr").removeClass( 'highlight' );
			$(this).css('color','');
		} );
		
		$('#propReservationTable tbody').on('click', 'input[type="checkbox"]', function(e){
		      

		      // Update state of "Select all" control
		      updateDataTableSelectAllCtrl($('#propReservationTable'));

		      // Prevent click event from propagating to parent
		      e.stopPropagation();
		   });
			
			function updateDataTableSelectAllCtrl(table){
			   //var $table             = table.node();
			   var $chkbox_all        = $('tbody input[type="checkbox"]', table);
			   var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', table);
			   var chkbox_select_all  = $('thead input[name="auto_select_all"]', table).get(0);

			   // If none of the checkboxes are checked
			   if($chkbox_checked.length === 0){
				  chkbox_select_all.checked = false;
				  if('indeterminate' in chkbox_select_all){
					 chkbox_select_all.indeterminate = false;
				  }

			   // If all of the checkboxes are checked
			   } else if ($chkbox_checked.length === $chkbox_all.length){
				  chkbox_select_all.checked = true;
				  if('indeterminate' in chkbox_select_all){
					 chkbox_select_all.indeterminate = false;
				  }

			   // If some of the checkboxes are checked
			   } else {
				  chkbox_select_all.checked = true;
				  if('indeterminate' in chkbox_select_all){
					 chkbox_select_all.indeterminate = true;
				  }
			   }
			}
			
			// Handle click on table cells with checkboxes
		   $('#propReservationTable').on('click', 'tbody td, thead th:first-child', function(e){
		      $(this).parent().find('input[type="checkbox"]').trigger('click');
		   });

		   // Handle click on "Select all" control
		   $('#propReservationTable thead input[name="auto_select_all"]').on('click', function(e){
		      if(this.checked){
		         $('#propReservationTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
		      } else {
		         $('#propReservationTable tbody input[type="checkbox"]:checked').trigger('click');
		      }

		      // Prevent click event from propagating to parent
		      e.stopPropagation();
		   });
		   
		   //Sorting code
		   $("#propReservationTable thead tr").on('click','.sorting',function(){
				var columnId = $(this).index();
				var jsonData = "";
				if($(this).hasClass('sorting_asc') == true)
				{
					$(this).removeClass('sorting_asc');
					$(this).addClass('sorting_desc');
					jsonData = {"index":columnId, "type":"desc"};
				}
				else if($(this).hasClass('sorting_desc') == true)
				{
					$(this).addClass('sorting_asc');
					$(this).removeClass('sorting_desc');
					jsonData = {"index":columnId, "type":"asc"};
				}
				else
				{
					$("#propReservationTable thead th").removeClass('sorting_asc');
					$("#propReservationTable thead th").removeClass('sorting_desc');
					$(this).addClass('sorting_asc');
					jsonData = {"index":columnId, "type":"asc"};
					
				}
				//Put url here
				$.ajax({
						type:'POST',
						url:'',
						data:jsonData
					});
			});
		   
		   
			//Filter code
			var filterTimeOut = null;
			$("#autoReservetableWrapper .top .dataTables_filter").bind("propertychange change keyup paste input",'#autoReserveFilter', function(){
				var searchValue = $('#autoReserveFilter').val();
				clearTimeout(filterTimeOut);
				filterTimeOut = setTimeout(function(){checkSearchChanged(searchValue);}, 2000);
			});
			
			function checkSearchChanged(searchValue) {
					var currentValue = $('#autoReserveFilter').val();
					if ((currentValue) && currentValue != searchValue && currentValue != '') {
						searchValue = $('#autoReserveFilter').val();
						filterTimeOut = clearTimeout(filterTimeOut);
						//Put url here
						$.ajax({
						type:'POST',
						url:'',
						data:searchValue
					});
					}
					else {
						setTimeout(checkSearchChanged, 2000);
					}
				}
			
			//Page length change
			$( "#autoReserveLength" ).change(function() {
				var changedLength = $(this).val();
				//Put url here
						$.ajax({
						type:'POST',
						url:'',
						data:changedLength
					});
			});
			
			var requestRows = ($("#propReservationTable").find("tbody").find("tr"));	
			var reqLen = requestRows.length;
			var headheight = ($("#propReservationTable").find("thead")).height();
			var reqHeight = ($("#propReservationTable").find("tbody").find("tr:first-child")).height();
			if(reqLen<25)
			{
				var parentHeight = ((25 * reqHeight)+headheight)+"px";
				$(".tab-content").css({"min-height":parentHeight});
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
			
			$("#autoReserve").click(function(){
				showLoader();
			});
	} );
})(jQuery);