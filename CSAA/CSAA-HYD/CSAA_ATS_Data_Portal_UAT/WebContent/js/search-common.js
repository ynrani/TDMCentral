$(document).ready(function(){
    $(".collpasewidget .widget-title").on('click', '.plus-minus-style', function (e) {
        $(this).parents(".widget-title").next('.widget-content').toggle();
        $(this).parents('.collpasewidget').toggleClass('active');
		if($(this).hasClass('fa-minus-square-o') == true)
		{
			$(this).addClass('fa-plus-square-o');
			$(this).removeClass('fa-minus-square-o');
		}
		else if($(this).hasClass('fa-plus-square-o') == true)
		{
			$(this).addClass('fa-minus-square-o');
			$(this).removeClass('fa-plus-square-o');
		}
    });
	
    $("[data-toggle=tooltip]").tooltip({'placement': 'top','trigger':'manual'});
    $("[data-toggle=tooltip]").addClass("grey-tooltip");
	$("#logout").click(function(){
		window.location.href = "login.html";
	});

	var tooltipTimeout = null;
	$('[data-toggle=tooltip]').on('click', function () {
		$('[data-toggle=tooltip]').not(this).tooltip('hide');
		clearTimeout(tooltipTimeout);
		$(this).tooltip('show');
	});
	
	$('[data-toggle=tooltip]').on('shown.bs.tooltip', function () {
	   tooltipTimeout = setTimeout(function () {
		$('[data-toggle=tooltip]').tooltip('hide');
	   }, 3000);
	});
	
	


})
