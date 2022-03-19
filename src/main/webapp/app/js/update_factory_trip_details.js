 $(function() {
	    $('#button1').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trip/details/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	                //$('#lblData').html(JSON.stringify(response));
	            	// $('#first_name').val(response.first_name);
	            	//populate('#MyForm', response);
	            	
	            	
	            	var  trip = response.Trips ;	            	
	            	$.each( trip, function(key, value){
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
		  
		$('#btTest2').click(function () {
			var requestData = new Object();
			requestData.trip_id= $('#trip_id').val();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.start_location=$('#start_location').val();
			requestData.start_date=$('#start_date').val();
			requestData.time_of_start=$('#time_of_start').val();
			requestData.destination=$('#destination').val();
			requestData.estimated_km=$('#estimated_km').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			requestData.closing_meter_reading=$('#closing_meter_reading').val();
			requestData.checked_kms=$('#checked_kms').val();
			requestData.load_quantity=$('#load_quantity').val();
			requestData.freight=$('#freight').val();
			//requestData.trip_status=$('#trip_status').val();
			
			$.ajax({
				url: '/transport/trip/details/update',
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