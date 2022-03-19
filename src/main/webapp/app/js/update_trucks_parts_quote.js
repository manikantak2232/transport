  $(function() {
	    $('#get').click(function() {
	    	var quote_number = $('#quote_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/quote/get?'+'quote_number='+quote_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	               
	            	var trucksPartsQuote = response.trucksPartsQuote;	            	
	            	$.each(trucksPartsQuote, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});
	  
	  $(document).ready(function (){ 
			$('#update').click(function () {
				var requestData = new Object();
				requestData.trucks_parts_quote_id= $('#trucks_parts_quote_id').val();
				requestData.new_quote_date= $('#new_quote_date').val();
				requestData.product_name=$('#product_name').val();
				requestData.company_name= $('#company_name').val();
				requestData.agent= $('#agent').val();
				requestData.number_of_units= $('#number_of_units').val();
				requestData.unit_price=$('#unit_price').val();
				requestData.total= $('#total').val();
				requestData.no_of_days_for_delivery= $('#no_of_days_for_delivery').val();
				requestData.no_of_hours_for_delivery= $('#no_of_hours_for_delivery').val();
				requestData.mode_of_payment=$('#mode_of_payment').val();
				requestData.type_of_payment= $('#type_of_payment').val();
				requestData.valid_for_number_of_days= $('#valid_for_number_of_days').val();
				requestData.contact_number= $('#contact_number').val();
				requestData.quote_status=$('#quote_status').val();
				
				$.ajax({
					url: '/transport/trucks/quote/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	          	
		            //	  alert("success")  
		            	$("#Message").html("Updated Successfully");
		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
			});
		});