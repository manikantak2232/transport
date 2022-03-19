 $(function() {
	    $('#button1').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/location/status/get?'+'fk_truck_id='+fk_truck_id,
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
		  
		$('#btTest2').click(function () {
			var requestData = new Object();
			requestData.location_status_id= $('#location_status_id').val();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.status_of_truck=$('#status_of_truck').val();
			requestData.present_location=$('#present_location').val();
			requestData.date=$('#date').val();
			$.ajax({
				url: '/transport/location/status/update',
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