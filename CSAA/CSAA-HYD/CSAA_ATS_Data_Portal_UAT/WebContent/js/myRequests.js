(function($){

$(document).ready(function() {
    //code for admin dashboard
	$('#requestTable').DataTable( {
		paging:true,
		bInfo: true,
		
		 "bFilter" : true,
         "dom": '<"top"iflp<"clear">>rt',
		ordering:true,
		'order':[1,'desc'],
		"columnDefs": [ 
		{
            "targets": 0,
			"width":"115px",
			'orderable' : false
        },
		{
            "targets": 1,
			"width":"115px"
        },
		{
            "targets": 2,
			"width":"115px"
        },
		{
            "targets": 3,
			"width":"115px"
        },
        {
            "targets": 4,
			"width":"131px"
        }]
    } );
	
	//$(".dataTables_empty").attr('colspan','13');
	$(".dataTables_empty").addClass('empty-row');
	$("#requestTable thead").on('click','th',function() {
		$(".dataTables_empty").addClass('empty-row');
	} );
	
	hideNextAutoPrevious();
	
	function hideNextAutoPrevious()
	{
		if($("#requestTable").DataTable().data().length == 0)
		{
			$('.dataTables_paginate').addClass('hide-class');
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
	
	$('#requestTable tbody tr td').each( function() {
	    this.setAttribute( 'title', $(this).text());
	});
	
	$('#requestTable').on('draw.dt', function() {
		$('#requestTable tbody tr td').each( function() {
		    this.setAttribute( 'title', $(this).text());
		});
	});
} );
})(jQuery);