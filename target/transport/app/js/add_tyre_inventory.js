
  
  $(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.invoice_number=$('#invoice_number').val();
			requestData.invoice_date=$('#invoice_date').val();
			requestData.brand_name=$('#brand_name').val();
			requestData.tyre_number=values;
			requestData.tyre_type=values1;
		
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