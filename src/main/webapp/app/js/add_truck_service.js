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
	  $("#service_start_date").val(today);

	  }

$(document).ready(function (){ 
		$('#insert').click(function () {
			 
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.service_center_name= $('#service_center_name').val();
			/* requestData.service_total_cost= $('#service_total_cost').val(); */
			requestData.service_start_date=$('#service_start_date').val();;
			/* requestData.fk_spare_parts_id=$('#part_id').val(); */
		
	        $.ajax({
	        	url: '/transport/trucks/service/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	            	$("#Message").html(response.errorMessage);
	            },
	           
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});