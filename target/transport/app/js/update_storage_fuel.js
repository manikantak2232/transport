
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/storage/fuel/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var storageFuel = response.StorageFuel;	            	
	            	$.each(storageFuel, function(key, value){
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
				requestData.storage_fuel_id= $('#storage_fuel_id').val();
				requestData.fuel_quantity= $('#fuel_quantity').val();
				requestData.fuel_rate=$('#fuel_rate').val();
				requestData.amount= $('#amount').val();				
				requestData.fuel_station_location=$('#fuel_station_location').val();
				requestData.date=$('#date').val();
				
				
				$.ajax({
					url: '/transport/storage/fuel/update',
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
	  