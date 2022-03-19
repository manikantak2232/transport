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
    
/*    $("#insert").bind("click", function () {
        var values1 = "";
        $("input[name=DynamicTextBox1]").each(function () {
            values1 += $(this).val() + "\n";
        });
        var2=values1+$('#tyre_type').val();
    });*/

    $("body").on("click", ".remove", function () {
        $(this).closest("div").remove();
    });
    
    $('#insert').click(function () {
		var requestData = new Object();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.invoice_date=$('#invoice_date').val();
		requestData.brand_name=$('#brand_name').val();
		requestData.tyre_unit_price=$('#tyre_unit_price').val();
		requestData.tyre_discount_percent=$('#tyre_discount_percent').val();
		requestData.tyre_gst_percent=$('#tyre_gst_percent').val();
		requestData.tube_unit_price=$('#tube_unit_price').val();
		requestData.tube_discount_percent=$('#tube_discount_percent').val();
		requestData.tube_gst_percent=$('#tube_gst_percent').val();
		requestData.flap_unit_price=$('#flap_unit_price').val();
		requestData.flap_discount_percent=$('#flap_discount_percent').val();
		requestData.flap_gst_percent=$('#flap_gst_percent').val();
		
		requestData.tyre_number= var1;
		requestData.tyre_type= $('#tyre_type').val();
	
        $.ajax({
        	url: '/transport/tyre/inventory/add',
			type: 'POST',
            dataType: 'json',
            data: JSON.stringify(requestData),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {	            		 	         
            	$("#Message").html(response.message);
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
    
});
// style="margin-left:550px;"
function GetDynamicTextBox(value) {
    return '<input name = "DynamicTextBox" placeholder="Enter Tyre number" type="text" value = "' + value + '" />&nbsp;' +
    //		'<input name = "DynamicTextBox1" placeholder="Enter front or housing" type="text" value = "' + value + '" />&nbsp;' +
            '<input type="button" value="Remove" class="remove" style="position:absolute;margin-left:100px;"/><br><br>'
}

function calculate() {
	  var tyre_unit_price=$('#tyre_unit_price').val();
	  var tyre_quantity=$('#tyre_quantity').val();
	  
	  $('#tyres_total_amount').val(tyre_unit_price*tyre_quantity);
	  
	  var tyre_discount_percent=$('#tyre_discount_percent').val();
	  
	  var discount_price=((tyre_unit_price*tyre_quantity)*tyre_discount_percent)/100;
	  
	  $('#tyre_discount_amount').val(discount_price);
	  
	  var tyre_gst_percent=$('#tyre_gst_percent').val();
	  
	  var gst_percent=((tyre_unit_price*tyre_quantity)-(discount_price))*tyre_gst_percent/100;
	  $('#tyre_price_with_gst').val((((tyre_unit_price*tyre_quantity)-(discount_price))+gst_percent)/tyre_quantity);
	  
	  
}

function calculateTubeCost() {
	  var tube_unit_price=$('#tube_unit_price').val();
	  var no_of_tubes=$('#no_of_tubes').val();
	  
	  $('#total_tubes_amount').val(tube_unit_price*no_of_tubes);
	  
	  var tube_discount_percent=$('#tube_discount_percent').val();
	  
	  var discount_price=((tube_unit_price*no_of_tubes)*tube_discount_percent)/100;
	  
	  $('#tubes_discount_amount').val(discount_price);
	  
	  var tube_gst_percent=$('#tube_gst_percent').val();
	  
	  var gst_percent=((tube_unit_price*no_of_tubes)-(discount_price))*tube_gst_percent/100;
	  $('#tube_price_with_gst').val((((tube_unit_price*no_of_tubes)-(discount_price))+gst_percent)/no_of_tubes);  
	  
}

function calculateFlapCost() {
	  var flap_unit_price=$('#flap_unit_price').val();
	  var no_of_flaps=$('#no_of_flaps').val();
	  
	  $('#total_flaps_amount').val(flap_unit_price*no_of_flaps);
	  
	  var flap_discount_percent=$('#flap_discount_percent').val();
	  
	  var discount_price=((flap_unit_price*no_of_flaps)*flap_discount_percent)/100;
	  
	  $('#flap_discount_amount').val(discount_price);
	  
	  var flap_gst_percent=$('#flap_gst_percent').val();
	  
	  var gst_percent=((flap_unit_price*no_of_flaps)-(discount_price))*flap_gst_percent/100;
	  $('#flap_price_with_gst').val((((flap_unit_price*no_of_flaps)-(discount_price))+gst_percent)/no_of_flaps);  
	  
}