
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/seller/fuel/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	
	            	var sellerFuel = response.SellerFuel;	            	
	            	$.each(sellerFuel, function(key, value){
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
				requestData.seller_fuel_id= $('#seller_fuel_id').val();
				requestData.fuel_quantity= $('#fuel_quantity').val();
				requestData.fuel_rate=$('#fuel_rate').val();
				requestData.amount= $('#amount').val();
				
				requestData.fuel_station_location=$('#fuel_station_location').val();
				requestData.date=$('#date').val();
				
				
				$.ajax({
					url: '/transport/seller/fuel/update',
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