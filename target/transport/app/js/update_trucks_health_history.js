
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/health/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	               
	            	
	            	var healthHistory = response.healthHistory;	            	
	            	$.each(healthHistory, function(key, value){
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
				requestData.trucks_health_history_id= $('#trucks_health_history_id').val();
				requestData.description= $('#description').val();
				requestData.date=$('#date').val();
				requestData.fk_spare_parts_id= $('#fk_spare_parts_id').val();
				
				$.ajax({
					url: '/transport/trucks/health/update',
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
	  