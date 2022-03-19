
  
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/allotment/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	
	            	var trucksAllotment = response.trucksAllotment;	            	
	            	$.each(trucksAllotment, function(key, value){
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
				requestData.truck_allotment_date= $('#truck_allotment_date').val();
				requestData.fk_driver_id= $('#fk_driver_id').val();
				requestData.trucks_allotment_id=$('#trucks_allotment_id').val();
				requestData.allotment_location= $('#allotment_location').val();
				
				$.ajax({
					url: '/transport/trucks/allotment/update',
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
	  