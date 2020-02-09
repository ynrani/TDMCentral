$(document).ready(function(){
    var calculatedHeight = $("footer").innerHeight();
    var footerHeight = calculatedHeight+"px";
    var bodyMargin = (calculatedHeight)+"px";
    $("footer").css("height",footerHeight);
    $("body").css("margin-bottom",bodyMargin);
})