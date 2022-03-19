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

$(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.invoice_number= $('#invoice_number').val();
			requestData.customer_first_name=$('#customer_first_name').val();
			requestData.customer_middle_name=$('#customer_middle_name').val();
			requestData.customer_last_name=$('#customer_last_name').val();
			requestData.invoice_date=$('#invoice_date').val();
			requestData.name_of_brand=$('#name_of_brand').val();
			
			requestData.from_address= $('#from_address').val();
			requestData.to_address=$('#to_address').val();
			requestData.type_of_cement=$('#type_of_cement').val();
			requestData.phone_number=$('#phone_number').val();
			requestData.email=$('#email').val();
			requestData.number_of_bags=$('#number_of_bags').val();
			
			requestData.cost_per_bag= $('#cost_per_bag').val();
			requestData.total_value=$('#total_value').val();
			requestData.sales_manager_name=$('#sales_manager_name').val();
			requestData.freight_rate=$('#freight_rate').val();
		  
	        $.ajax({
	        	url: '/transport/seller/invoice/add',
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