function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.brand_list,trucks[i].brand_name,trucks[i].pk_battery_brand_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/spareparts/battery/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.brandNames;
				addDropDownOptions2(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function testChange2(){
	var selectedTruck=$("#brand_list :selected").text();
	var selectedTruckId=$("#brand_list :selected").val();
	$("#battery_brand_id").val(selectedTruckId);
}


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
//	var var2; 
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
        var1=values+$('#battery_number').val();
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
		requestData.date=$('#date').val();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.invoice_date=$('#invoice_date').val();
		requestData.battery_brand_id=$('#battery_brand_id').val();
		requestData.battery_number= var1;
//		requestData.tyre_type= var2;
	
        $.ajax({
        	url: '/transport/spareparts/battery/inventory/add',
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
    return '<input name = "DynamicTextBox"  placeholder="Enter Battery number" type="text" value = "' + value + '" />&nbsp;' +
   // 		'<input name = "DynamicTextBox1" placeholder="Enter front or housing" type="text" value = "' + value + '" />&nbsp;' +
            '<input type="button" style="position:absolute;margin-left:70px;" value="Remove" class="remove" /><br><br>'
}