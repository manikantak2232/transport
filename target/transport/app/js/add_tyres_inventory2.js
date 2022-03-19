function currentDate() {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!

	  var yyyy = today.getFullYear();
	  if(dd<10){
	      dd='0'+dd;
	  } 
	  if(mm<10){
	      mm='0'+mm;
	  } 
	  var today =yyyy+'/'+mm+'/'+ dd;
	  $("#invoice_date").val(today);

	  }

$(function () {
	var var1;
	var var2; 
    $("#btnAdd").bind("click", function () {
        var div = $("<div />");
        div.html(GetDynamicTextBox(""));
        $("#TextBoxContainer").append(div);
       
    });
    $("#insert").bind("click", function () {
        var values = "";
        $("input[name=DynamicTextBox]").each(function () {
            values += $(this).val() + "\n";
        });
        var1=values+$('#tyre_number').val();
    });
    
    $("#insert").bind("click", function () {
        var values1 = "";
        $("input[name=DynamicTextBox1]").each(function () {
            values1 += $(this).val() + "\n";
        });
        var2=values1+$('#tyre_type').val();
    });

    $("body").on("click", ".remove", function () {
        $(this).closest("div").remove();
    });
    
    $('#insert').click(function () {
		var requestData = new Object();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.invoice_date=$('#invoice_date').val();
		requestData.brand_name=$('#brand_name').val();
		requestData.tyre_number= var1;
		requestData.tyre_type= var2;
	
        $.ajax({
        	url: '/transport/tyre/inventory/add',
			type: 'POST',
            dataType: 'json',
            data: JSON.stringify(requestData),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {	            		 	         
            	$("#Message").html("Inserted Successfully");
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
    
});
function GetDynamicTextBox(value) {
    return '<input name = "DynamicTextBox"  placeholder="Enter Tyre number" type="text" value = "' + value + '" />&nbsp;' +
    		/*'<input name = "DynamicTextBox1" placeholder="Enter Tyre type" type="text" value = "' + value + '" />&nbsp;' +*/
    '<select name="DynamicTextBox1" id="dispatch_status">'
		'<option selected="selected" style="display: none">'+value+'</option>&nbsp;'+
		/*'<option value="'+waiting+'">Waiting</option>&nbsp;'+
		'<option value="'+unloading+'">Unloading</option>&nbsp;'+*/
	'</select>&nbsp;' +
            '<input type="button" value="Remove" class="remove" />'
}





