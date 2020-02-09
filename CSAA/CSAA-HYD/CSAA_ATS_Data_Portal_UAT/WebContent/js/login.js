$(document).ready(function(){
    $("#loginSubmit").click(function(){
        window.location.href="landing.html";
    })
    var consumerGrpArr = [ {key:'PAS', value:'PAS'},{key:'CAS', value:'CAS'}, {key:' Digital Services', value:' Digital Services'}, {key:'MDM', value:'MDM'}, {key:'SOA', value:'SOA'}, {key:'Automation', value:'Automation'}, {key:'Performance', value:'Performance'}];
    $.each(consumerGrpArr, function(index, valueProp) {
        $('#ddlConsumerGroup').append($('<option>').text(valueProp.key).attr('value', valueProp.value));
    });

    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'DD-MM-YYYY'
        });
    });
    $("#registerPage").on("hidden.bs.modal", function(){
        $(this).find('form')[0].reset();
    });


})