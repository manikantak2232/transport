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
	  $("#truck_allotment_date").val(today);

	  }

  $(document).ready(function (){ 
	  
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.truck_allotment_date= $('#truck_allotment_date').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.allotment_location=$('#allotment_location').val();
			requestData.fk_truck_id=$('#truck_id').val();
		  
	        $.ajax({
	        	url: '/transport/trucks/allotment/add',
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