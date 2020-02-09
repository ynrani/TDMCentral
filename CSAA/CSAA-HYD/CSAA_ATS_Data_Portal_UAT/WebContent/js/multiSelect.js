/*
	dropdown1 with Multiple checkbox select with jQuery - May 27, 2013
	(c) 2013 @ElmahdiMahmoud
	license: http://www.opensource.org/licenses/mit-license.php
*/

$(".dropdown1 dt a").on('click', function() {
  $(".dropdown1 dd ul").slideToggle('fast');
});

$(".dropdown1 dd ul li a").on('click', function() {
  $(".dropdown1 dd ul").hide();
});

function getSelectedValue(id) {
  return $("#" + id).find("dt a span.value").html();
}

$(document).bind('click', function(e) {
  var $clicked = $(e.target);
  if (!$clicked.parents().hasClass("dropdown1")) $(".dropdown1 dd ul").hide();
});

$('.mutliSelect input[type="checkbox"]').on('click', function() {

  var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
    title = $(this).val() + ",";
  if ($(this).is(':checked')) {
    var html = '<span title="' + title + '">' + title + '</span>';
    $('.multiSel').append(html);
    $(".hida").hide();
	if($(this).val() == 0)
	{
		$('.mutliSelect input[type="checkbox"]').attr('disabled',true);
		$(this).attr('disabled',false);
	}
	else
	{
		$('.mutliSelect input[type="checkbox"][value="0"]').attr('disabled',true);
	}
  } else {
    $('span[title="' + title + '"]').remove();
    var ret = $(".hida");
    $('.dropdown1 dt a').append(ret);
	if($(this).val() == 0)
	{
		$('.mutliSelect input[type="checkbox"]').attr('disabled',false);
	}
	else if($('.mutliSelect input:checkbox:checked').length == 0)
	{
		$('.mutliSelect input[type="checkbox"][value="0"]').attr('disabled',false);
	}
  }
});