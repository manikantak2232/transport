 
  $(document).ready(function (){ 
	  
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.invoice_number=$('#invoice_number').val();
			requestData.start_location=$('#start_location').val();
			requestData.time_of_start=$('#time_of_start').val();
			requestData.unload_location=$('#unload_location').val();
			requestData.dispatch_date=$('#dispatch_date').val();
			requestData.estimated_dispatch_days= $('#estimated_dispatch_days').val();
			requestData.estimated_dispatch_hours=$('#estimated_dispatch_hours').val();
			requestData.estimated_km=$('#estimated_km').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			//requestData.closing_meter_reading=$('#closing_meter_reading').val();
			requestData.load_quantity=$('#load_quantity').val();
			requestData.freight=$('#freight').val();
			requestData.advance= $('#advance').val();
			//requestData.balance=$('#balance').val();
			//requestData.dispatch_status=$('#dispatch_status').val();
		  
	        $.ajax({
	        	url: '/transport/factory/dispatch/add',
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