 $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/service/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	               
	            	
	            	var trucksService = response.trucksService;	            	
	            	$.each(trucksService, function(key, value){
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
			requestData.trucks_service_id= $('#truck_service_id').val();
			requestData.fk_truck_id= $('#truck_id').val();
			requestData.service_center_name=$('#service_center_name').val();
			requestData.service_date= $('#service_date').val();
			requestData.service_total_cost= $('#service_total_cost').val();
			requestData.fk_spare_parts_id= $('#fk_spare_parts_id').val();
			requestData.description=$('#description').val();
			
			
			$.ajax({
				url: '/transport/trucks/service/update',
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
