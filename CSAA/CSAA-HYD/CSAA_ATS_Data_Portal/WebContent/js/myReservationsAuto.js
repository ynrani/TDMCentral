(function($){

$(document).ready(function() {
    //code for admin dashboard
       $('#reservationTable').DataTable( {
              paging:true,
              bInfo: true,
              "bFilter" : false,
              "dom": '<"top"iflp<"clear">>rt',
              'order': [1, 'asc'],
              'bAutoWidth':false,
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
		       },
		       {
		    	   "targets": 2,       
		    	   "width":"109px"
		       },
		       {
		    	   "targets": 3,       
		    	   "width":"78px"
		       },
		       {
		    	   "targets": 4,       
		    	   "width":"58px"
		       },
		       {
		    	   "targets": 5,       
		    	   "width":"42px"
		       },
		       {
		    	   "targets": 6,       
		    	   "width":"49px"
		       },
		       {
		    	   "targets": 7,       
		    	   "width":"78px"
		       },
		       {
		    	   "targets": 8,       
		    	   "width":"70px"
		       },
		       {
		    	   "targets": 9,       
		    	   "width":"70px"
		       },
		       {
		    	   "targets": 10,       
		    	   "width":"70px"
		       },
		       {
		    	   "targets": 11,       
		    	   "width":"77px"
		       }]
    } );
       
       //$(".dataTables_empty").attr('colspan','13');
       $(".dataTables_empty").addClass('empty-row');
       $("#reservationTable thead").on('click','th',function() {
              $(".dataTables_empty").addClass('empty-row');
       } );
	   $('.dataTables_paginate').removeClass('hide-class');
       
       hideNextAutoPrevious();
       
       function hideNextAutoPrevious()
       {
              if($("#reservationTable").DataTable().data().length == 0)
              {
                     $('.dataTables_paginate').addClass('hide-class');
              }
       }
       
       $('#reservationTable tbody')
        .on( 'mouseenter', 'td', function () {
            var tr = $(this).closest('tr');

            $("#reservationTable tbody tr").removeClass( 'highlight' );
            $(tr).addClass( 'highlight' );
        } );
              
       $('#reservationTable').on('mouseout',function () {
            $("#reservationTable tbody tr").removeClass( 'highlight' );
                     $(this).css('color','');
        } );
       
       
       
       $('#reservationTable tbody').on('click', 'input[type="checkbox"]', function(e){
      

      // Update state of "Select all" control
      updateDataTableSelectAllCtrl($('#reservationTable').DataTable());

      // Prevent click event from propagating to parent
      e.stopPropagation();
   });
       
       function updateDataTableSelectAllCtrl(table){
          var $table             = table.table().node();
          var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
          var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
          var chkbox_select_all  = $('thead input[name="auto_select_all"]', $table).get(0);

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
   $('#reservationTable').on('click', 'tbody td, thead th:first-child', function(e){
      $(this).parent().find('input[type="checkbox"]').trigger('click');
   });

   // Handle click on "Select all" control
  $('#reservationTable thead input[name="auto_select_all"]', $('#reservationTable').DataTable().table().container()).on('click', function(e){
      if(this.checked){
         $('#reservationTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
      } else {
         $('#reservationTable tbody input[type="checkbox"]:checked').trigger('click');
      }

      // Prevent click event from propagating to parent
      e.stopPropagation();
   });

   var requestRows = ($("#reservationTable").find("tbody").find("tr"));	
	var reqLen = requestRows.length;
	var headheight = ($("#reservationTable").find("thead")).height();
	var reqHeight = ($("#reservationTable").find("tbody").find("tr:first-child")).height();
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
	
	$("#propReserve").click(function(){
		showLoader();
	});
	
	$('#reservationTable tbody tr td').each( function() {
	    this.setAttribute( 'title', $(this).text());
	});
	
	$('#reservationTable').on('draw.dt', function() {
		$('#reservationTable tbody tr td').each( function() {
		    this.setAttribute( 'title', $(this).text());
		});
	});
});

})(jQuery);