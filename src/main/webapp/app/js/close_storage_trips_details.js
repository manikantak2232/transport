$(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trip/storage/close/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	             
	            	
	            	var trip = response.Trips;	            	
	            	$.each( trip , function(key, value){
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
			requestData.trip_id= $('#trip_id').val();
			requestData.closing_meter_reading= $('#closing_meter_reading').val();
			requestData.checked_kms= $('#checked_kms').val();
		//	requestData.trip_status=$('#trip_status').val();
			
			$.ajax({
				url: '/transport/trip/storage/close/update',
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