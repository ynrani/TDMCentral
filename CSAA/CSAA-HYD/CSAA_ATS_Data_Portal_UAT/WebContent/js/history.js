$(document).ready(function(){

    $('#historyTable').DataTable( {
        paging:false,
        bFilter: false,
        bInfo: false,
        ordering: true
    } );

    $(".dataTables_empty").addClass('empty-row');

    initialiseHistoryTable();
    function initialiseHistoryTable()
    {

        var tableActualData =[
            {
                "REQUEST_ID":"DC000001323",
                "MODIFIED_DATE":"2/15/2016 9:00 AM",
                "MODIFIED_BY":"User",
                "REQUEST_STATUS":"New",
                "COMMENTS":"Manual data Creation request"
            },{
                "REQUEST_ID":"DC000001324",
                "MODIFIED_DATE":"2/16/2016 9:00 AM",
                "MODIFIED_BY":"ATD Data Support",
                "REQUEST_STATUS":"New",
                "COMMENTS":"Data Subsetting request"
            },
            {
                "REQUEST_ID":"DC000001325",
                "MODIFIED_DATE":"2/17/2016 9:00 AM",
                "MODIFIED_BY":"User",
                "REQUEST_STATUS":"In progress",
                "COMMENTS":"Manual data Creation request"
            },
            {
                "REQUEST_ID":"DC000001326",
                "MODIFIED_DATE":"2/18/2016 9:00 AM",
                "MODIFIED_BY":"User",
                "REQUEST_STATUS":"Completed",
                "COMMENTS":"Manual data Creation request"
            },
            {
                "REQUEST_ID":"DC000001327",
                "MODIFIED_DATE":"2/19/2016 9:00 AM",
                "MODIFIED_BY":"ATS Data Support",
                "REQUEST_STATUS":"New",
                "COMMENTS":"Data Subsetting request"
            },
            {
                "REQUEST_ID":"DC000001328",
                "MODIFIED_DATE":"2/20/2016 9:00 AM",
                "MODIFIED_BY":"User",
                "REQUEST_STATUS":"In progress",
                "COMMENTS":"Data Subsetting request"
            }

        ];

        var table = $('#historyTable').DataTable();
        /*for(var i=0;i<tableActualData.length;i++)
        {
            var rowData =[];
            $.each( tableActualData[i], function( key, value ) {
                rowData.push(value);
            });
            table.row.add(rowData);
        }
		table.draw();*/
    }
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	  var target = $(e.target).attr('data-target'); // activated tab
	  if(target == '#manual')
	  {
		$('#btnNewRequest').removeClass('hide-class');
	  }
	  else
	  {
		$('#btnNewRequest').addClass('hide-class');
	  }
	});
	
	//$('.tab-content').height($("body").height() + 250 + 'px');
	//$("#history .content-section").height($("body").height() - 100 + 'px');
	
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