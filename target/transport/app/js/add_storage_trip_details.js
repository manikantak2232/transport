function currentDate() {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!

	  var yyyy = today.getFullYear();
	  if(dd<10){
	      dd='0'+dd;
	  } 
	  if(mm<10){
	      mm='0'+mm;
	  } 
	  var today =yyyy+'/'+mm+'/'+ dd;
	  $("#start_date").val(today);

	  }

  $(document).ready(function (){ 
	  
		$('#insert').click(function () {
		var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.start_location=$('#start_location').val();
			requestData.start_date=$('#start_date').val();
			requestData.destination=$('#destination').val();
	//		requestData.time_of_start=$('#time_of_start').val();
			requestData.estimated_km=$('#estimated_km').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			requestData.load_quantity=$('#load_quantity').val();
			requestData.freight=$('#freight').val();
			requestData.advance=$('#advance').val();

	        $.ajax({
	        	url: '/transport/trip/storage/details/add',
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