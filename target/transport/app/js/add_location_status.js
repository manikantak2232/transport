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
$("#date").val(today);

	  }

$(document).ready(function (){ 
	  
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.status_of_truck = $('#status_of_truck ').val();
			requestData.fk_truck_id= $('#truck_id').val();
			requestData.present_location=$('#present_location').val();
			requestData.date=$('#date').val();
			$.ajax({
	        	url: '/transport/location/status/add',
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