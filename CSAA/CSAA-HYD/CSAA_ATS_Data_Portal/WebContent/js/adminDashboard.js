(function($){

$(document).ready(function() {
    //code for admin dashboard
	$('#dashboardTable').DataTable( {
		paging:true,
		bInfo: true,
		"dom": '<"top"iflp<"clear">>rt',
		"bAutoWidth":false,
		ordering:true,
		'order':[1,'desc'],
		"columnDefs": [ 
		{
            "targets": 0,
			"orderable": false,
			"width":"10px",
            "defaultContent": "<input id='txtNoResiUnits' type='checkbox' value=''  class='table-cell-input'/>"
        },
        {
            "targets": 1,
			"width":"98px"
        },
        {
            "targets": 2,
			"width":"68px"
        },
        {
            "targets": 3,
			"width":"52px"
        },
        {
            "targets": 4,
			"width":"50px"
        },
        {
            "targets": 5,
			"width":"64px"
        },
        {
            "targets": 6,
			"width":"70px"
        },
        {
            "targets": 7,
			"width":"82px"
        },
        {
            "targets": 8,
			"width":"42px"
        },
        {
            "targets": 9,
			"width":"78px"
        },
        {
            "targets": 10,
			"width":"72px"
        }]
    } );
	
	$('#dashboardTable tbody tr td').each( function() {
	    this.setAttribute( 'title', $(this).text());
	});
	
	$('#dashboardTable').on('draw.dt', function() {
		$('#dashboardTable tbody tr td').each( function() {
		    this.setAttribute( 'title', $(this).text());
		});
	});
	
	//$(".dataTables_empty").attr('colspan','13');
	$(".dataTables_empty").addClass('empty-row');
	$("#dashboardTable thead").on('click','th',function() {
		$(".dataTables_empty").addClass('empty-row');
	} );
	
	hideNextAutoPrevious();
	
	function hideNextAutoPrevious()
	{
		if($("#dashboardTable").DataTable().data().length == 0)
		{
			$('.dataTables_paginate').addClass('hide-class');
		}
	}
	
	initializeDashboard();
	
	function initializeDashboard()
	{
		/*var table = $('#dashboardTable').DataTable();
		for(var index = 0; index < 100; index++)
		{
			table.row.add([null,'DC000000' + index,'Data Creation','Automation-Data Creation Request', 'New','ATS Support','Bhumika','Customer 360','Medium', '2/15/2016 9:44 AM','2/16/2016 9:44 AM']);
			index = index + 1;
			table.row.add([null,'DC000000' + index,'Data Creation','Manual-Data Creation Request', 'Analyzing','ATS Support','Ravi','Customer 360','Medium', '2/15/2016 9:44 AM','2/16/2016 9:44 AM']);
			index = index + 1;
			table.row.add([null,'DC000000' + index,'Subset','Data Subset Request', 'New','ATS Support','Panchal','Customer 360','High', '2/15/2016 9:44 AM','2/16/2016 9:44 AM']);
			index = index + 1;
			table.row.add([null,'DC000000' + index,'Refresh','Data Refresh Request', 'Completed','ATS Support','Arun','Customer 360','High', '2/15/2016 9:44 AM','2/16/2016 9:44 AM']);
		}
		table.draw();*/
		$('.dataTables_paginate').removeClass('hide-class');
	}
	
	$('#dashboardTable tbody')
        .on( 'mouseenter', 'td', function () {
            var tr = $(this).closest('tr');
 
            $("#dashboardTable tbody tr").removeClass( 'highlight' );
            $(tr).addClass( 'highlight' );
        } );
		
	$('#dashboardTable').on('mouseout',function () {
            $("#dashboardTable tbody tr").removeClass( 'highlight' );
			$(this).css('color','');
        } );
	
	$("select[name='dashboardTable_length']").change(function(){
		if($('#dashboardTable').height() > $("body").height())
		{
			$('.tab-content').height($('#dashboardTable').height() + 100 + 'px');
		}
		else
		{
			$('.tab-content').height($("body").height() + 100 + 'px');
		}
	});
	
	$('.tab-content').height($("body").height() + 100 + 'px');
	
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
} );

})(jQuery);