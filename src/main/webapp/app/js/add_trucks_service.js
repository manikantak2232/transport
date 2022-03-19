$(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.service_center_name=$('#service_center_name').val();
			requestData.service_date=$('#service_date').val();
			requestData.service_total_cost=$('#service_total_cost').val();
			requestData.fk_spare_parts_id=$('#part_id').val();
			requestData.description= $('#description').val();
		
	        $.ajax({
	        	url: '/transport/trucks/service/add',
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