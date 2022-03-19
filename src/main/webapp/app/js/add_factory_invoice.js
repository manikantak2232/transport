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
	  
function calculate() {
	var number_of_bags = document.getElementById('number_of_bags').value;	
	var cost_per_bag = document.getElementById('cost_per_bag').value;
	var total_value = document.getElementById('total_value');	
	var myResult = number_of_bags * cost_per_bag;
	$("#total_value").val(myResult);
	
}

$(document).ready(function (){ 
	  
		$('#insert').click(function () {
			$('#loading').show();
			var requestData = new Object();
			requestData.invoice_number= $('#invoice_number').val();
			requestData.customer_first_name=$('#customer_first_name').val();
			requestData.customer_middle_name=$('#customer_middle_name').val();
			requestData.customer_last_name=$('#customer_last_name').val();
			requestData.name_of_brand=$('#name_of_brand').val();
			requestData.sales_manager_first_name=$('#sales_manager_first_name').val();
			requestData.sales_manager_middle_name=$('#sales_manager_middle_name').val();
			requestData.sales_manager_last_name= $('#sales_manager_last_name').val();
			requestData.invoice_date=$('#invoice_date').val();
			requestData.phone_number=$('#phone_number').val();
			requestData.email=$('#email').val();
			requestData.from_address=$('#from_address').val();
			requestData.to_address=$('#to_address').val();
			requestData.type_of_cement=$('#type_of_cement').val();
			requestData.number_of_bags=$('#number_of_bags').val();
			requestData.total_weight= $('#total_weight').val();
			requestData.cost_per_bag=$('#cost_per_bag').val();
			requestData.total_value=$('#total_value').val();
			requestData.freight_rate=$('#freight_rate').val();
			
			$.ajax({
	        	url: '/transport/factory/invoice/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	
	            	$('#loading').hide();
	            	$("#Message").html("Inserted Successfully");
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});