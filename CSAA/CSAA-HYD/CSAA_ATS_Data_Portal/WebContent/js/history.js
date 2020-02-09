$(document).ready(function(){

    $('#historyTable').DataTable( {
        paging:false,
        bFilter: false,
        bInfo: false,
        ordering: true
    } );

    $(".dataTables_empty").addClass('empty-row');
	
	hideLoader();
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
	
	$("#dataCreationLink").click(function(){
		showLoader();
	});
});