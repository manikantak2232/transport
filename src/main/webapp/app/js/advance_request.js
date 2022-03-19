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
	  $("#date").val(today);

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
        var1=values+$('#item_name').val();
    });
    
    $("#insert").bind("click", function () {
        var values1 = "";
        $("input[name=DynamicTextBox1]").each(function () {
            values1 += $(this).val() + "\n";
        });
        var2=values1+$('#item_cost').val();
    });

    $("body").on("click", ".remove", function () {
        $(this).closest("div").remove();
    });
    
    $('#insert').click(function () {
		var requestData = new Object();
		if(advance_request_count==0){
			request_number_value=1
		}
		if(advance_request_count>0){
			request_number_value=request_number_value+1
		}
		requestData.date=$('#date').val();
		requestData.fk_truck_id=$('#truck_id').val();
		requestData.item_name= var1;
		requestData.item_cost= var2;
		requestData.request_number_value= request_number_value;
	
        $.ajax({
        	url: '/transport/spareparts/advance/request/add',
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
    return '<input name = "DynamicTextBox"  placeholder="Enter Item Name" type="text" value = "' + value + '" />&nbsp;' +
    		'<input name = "DynamicTextBox1" placeholder="Enter Cost" type="text" value = "' + value + '" />&nbsp;' +
            '<input type="button" value="Remove" class="remove" /><br><br>'
}
var advance_request_count;
var request_number_value;

function getAdvanceRequestNumber() {
	var requestData = new Object();
	requestData.date=$('#date').val();
	requestData.fk_truck_id=$('#truck_id').val();

    $.ajax({
    	url: '/transport/spareparts/advance/request/number/get',
		type: 'POST',
        dataType: 'json',
        data: JSON.stringify(requestData),
        contentType: 'application/json; charset=utf-8',
        success: function(response) {	            		 	         
        	var numbers=response.numbers;
        	advance_request_count=numbers.advance_request_count;
        	request_number_value=numbers.request_number_value;

        },
        error: function(error) {
            console.log(error);
        }
    });
};