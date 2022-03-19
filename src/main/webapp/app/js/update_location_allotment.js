 $(function() {
	    $('#button1').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/location/allotment/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	                //$('#lblData').html(JSON.stringify(response));
	            	// $('#first_name').val(response.first_name);
	            	//populate('#MyForm', response);
	            	
	            	
	            	var  location = response.Locations ;	            	
	            	$.each( location, function(key, value){
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
			requestData.location_allotment_id= $('#location_allotment_id').val();
			requestData.fk_driver_id= $('#fk_driver_id').val();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.present_location=$('#present_location').val();
			requestData.new_location=$('#new_location').val();
			requestData.date=$('#date').val();
			$.ajax({
				url: '/transport/location/allotment/update',
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