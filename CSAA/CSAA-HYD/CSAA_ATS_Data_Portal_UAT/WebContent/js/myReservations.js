(function($){

$(document).ready(function() {
    //code for admin dashboard
	$('#reservationTable').DataTable( {
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
        }]
    } );
	
	//$(".dataTables_empty").attr('colspan','13');
	$(".dataTables_empty").addClass('empty-row');
	$("#reservationTable thead").on('click','th',function() {
		$(".dataTables_empty").addClass('empty-row');
	} );
	
	hideNextAutoPrevious();
	
	function hideNextAutoPrevious()
	{
		if($("#reservationTable").DataTable().data().length == 0)
		{
			$('.dataTables_paginate').addClass('hide-class');
		}
	}
	
	initializeMyRequestTable();
	
	function initializeMyRequestTable()
	{
		$('.dataTables_paginate').removeClass('hide-class');
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
	
	if($('#reservationTable').height() > $("body").height())
		{
			$('.tab-content').height($('#reservationTable').height() + 100 + 'px');
		}
		else
		{
			$('.tab-content').height($("body").height() + 'px');
		}
	
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

$('#propreservationTable').DataTable( {
		paging:true,
		bInfo: true,
		"dom": '<"top"iflp<"clear">>rt',
		'order': [1, 'asc'],
		"bAutoWidth":false,
		"columnDefs": [ 
		{
            "targets": 0,
			"sTitle": "<input type='checkbox' name='prop_select_all'></input>",
			"orderable": false,
			"width":"15px",
            "defaultContent": "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
        },
		{
            "targets": 1,
			"width":"65px"
        },
		{
            "targets": 2,
			"width":"95px"
        },
		{
            "targets": 3,
			"width":"123px"
        },
		{
            "targets": 4,
			"width":"310px"
        },
		{
            "targets": 5,
			"width":"50px"
        },
		{
            "targets": 6,
			"width":"50px"
        },
		{
            "targets": 7,
			"width":"50px"
        },
		{
            "targets": 8,
			"width":"50px"
        }]
    } );
	
	//$(".dataTables_empty").attr('colspan','13');
	$(".dataTables_empty").addClass('empty-row');
	$("#propreservationTable thead").on('click','th',function() {
		$(".dataTables_empty").addClass('empty-row');
	} );
	
	hideNextPropPrevious();
	
	function hideNextPropPrevious()
	{
		if($("#propreservationTable").DataTable().data().length == 0)
		{
			$('.dataTables_paginate').addClass('hide-class');
		}
	}
	
	initializePropMyRequestTable();
	
	function initializePropMyRequestTable()
	{
		var table = $('#propreservationTable').DataTable();
		for(var index = 236; index < (236 + 25); index++)
		{
			table.row.add([null,'Un-Reserve', 'AZSS000000' + index,'2/15/2018 9:44 AM','California Homeowners', 'New','AZ - Arizona','2/15/2016 9:44 AM', 'Yes']);
			index = index + 1;
			table.row.add([null,'Un-Reserve', 'AZSS000000' + index,'2/15/2018 9:44 AM','Homeowners Signature Series', 'Analyzing', 'CA - California', '2/15/2016 9:44 AM', 'No']);
			index = index + 1;
			table.row.add([null,'Un-Reserve', 'AZSS000000' + index,'2/15/2018 9:44 AM','California Homeowners', 'Pending', 'CO - Colorado','2/15/2016 9:44 AM', 'Yes']);
			index = index + 1;
			table.row.add([null,'Un-Reserve', 'AZSS000000' + index,'2/15/2018 9:44 AM','Homeowners Signature Series', 'Pending', 'CT - Connecticut','2/15/2016 9:44 AM', 'No']);
		}
		table.draw();
		$('.dataTables_paginate').removeClass('hide-class');
	}
	
	$('#propreservationTable tbody')
        .on( 'mouseenter', 'td', function () {
            var tr = $(this).closest('tr');
 
            $("#propreservationTable tbody tr").removeClass( 'highlight' );
            $(tr).addClass( 'highlight' );
        } );
		
	$('#propreservationTable').on('mouseout',function () {
            $("#propreservationTable tbody tr").removeClass( 'highlight' );
			$(this).css('color','');
        } );
	
	if($('#propreservationTable').height() > $("body").height())
		{
			$('.tab-content').height($('#propreservationTable').height() + 100 + 'px');
		}
		else
		{
			$('.tab-content').height($("body").height() + 'px');
		}
		
	$('#propreservationTable tbody').on('click', 'input[type="checkbox"]', function(e){
      

      // Update state of "Select all" control
      updateDataTableSelectPropAllCtrl($('#propreservationTable').DataTable());

      // Prevent click event from propagating to parent
      e.stopPropagation();
   });
	
	function updateDataTableSelectPropAllCtrl(table){
	   var $table             = table.table().node();
	   var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
	   var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
	   var chkbox_select_all  = $('thead input[name="prop_select_all"]', $table).get(0);

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
   $('#propreservationTable').on('click', 'tbody td, thead th:first-child', function(e){
      $(this).parent().find('input[type="checkbox"]').trigger('click');
   });

   // Handle click on "Select all" control
   $('#propreservationTable thead input[name="auto_select_all"]', $('#propreservationTable').DataTable().table().container()).on('click', function(e){
      if(this.checked){
         $('#propreservationTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
      } else {
         $('#propreservationTable tbody input[type="checkbox"]:checked').trigger('click');
      }

      // Prevent click event from propagating to parent
      e.stopPropagation();
   });
});

})(jQuery);