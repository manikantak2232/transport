
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/spareparts/allotment/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var sparePartsAllotment = response.sparePartsAllotment;	            	
	            	$.each(sparePartsAllotment, function(key, value){
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
				requestData.spare_part_allotment_id= $('#spare_part_allotment_id').val();
				requestData.fk_driver_id= $('#fk_driver_id').val();
				requestData.fk_spare_part_id=$('#fk_spare_part_id').val();
				requestData.count=$('#count').val();
				requestData.date=$('#date').val();
				
				
				$.ajax({
					url: '/transport/spareparts/allotment/update',
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
	  
	  