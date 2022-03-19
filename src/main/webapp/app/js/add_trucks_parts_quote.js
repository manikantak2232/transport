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
	  $("#new_quote_date").val(today);

	  }

  $(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.quote_number= $('#quote_number').val();
			requestData.new_quote_date=$('#new_quote_date').val();
			requestData.product_name=$('#product_name').val();
			requestData.company_name=$('#company_name').val();
			requestData.agent= $('#agent').val();
			requestData.number_of_units=$('#number_of_units').val();
			requestData.unit_price=$('#unit_price').val();
			requestData.total=$('#total').val();
			requestData.no_of_days_for_delivery= $('#no_of_days_for_delivery').val();
			requestData.no_of_hours_for_delivery=$('#no_of_hours_for_delivery').val();
			requestData.mode_of_payment=$('#mode_of_payment').val();
			requestData.type_of_payment=$('#type_of_payment').val();
			requestData.valid_for_number_of_days= $('#valid_for_number_of_days').val();
			requestData.contact_number=$('#contact_number').val();
			requestData.quote_status=$('#quote_status').val();
		
	        $.ajax({
	        	url: '/transport/trucks/quote/add',
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