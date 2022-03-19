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
$("#driver_allotment_date").val(today);

	  }


$(document).ready(function (){ 
	  
		$('#insert').click(function () {
			$('#loading').show();
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.driver_allotment_date= $('#driver_allotment_date').val();
			requestData.fk_association_id=$('#association_id').val();
			
		  
	        $.ajax({
	        	url: '/transport/driver/allotment/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	
	            	$('#loading').hide();
	            	$("#Message").html(response.message);
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});