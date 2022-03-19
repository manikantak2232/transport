  $(function() {
	    $('#get').click(function() {
	    	var invoice_number = $('#invoice_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/storage/incoming/get?'+'invoice_number='+invoice_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var storageIncomingLoad = response.StorageIncomingLoad;	            	
	            	$.each(storageIncomingLoad, function(key, value){
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
				requestData.storage_incoming_load_id= $('#storage_incoming_load_id').val();
				requestData.date= $('#date').val();
				requestData.from_address=$('#from_address').val();
				requestData.to_address= $('#to_address').val();
				requestData.brand_name=$('#brand_name').val();				
				requestData.cost_per_bag= $('#cost_per_bag').val();
				
				requestData.number_of_bags= $('#number_of_bags').val();
				requestData.total_weight= $('#total_weight').val();
				requestData.total_value=$('#total_value').val();
				requestData.fk_truck_id= $('#fk_truck_id').val();
				requestData.time_of_arrival=$('#time_of_arrival').val();				
				requestData.reading_km= $('#reading_km').val();
				requestData.status= $('#status').val();				
				requestData.fk_driver_id= $('#fk_driver_id').val();
				
				$.ajax({
					url: '/transport/storage/incoming/update',
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