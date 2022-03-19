var driver_allotment_id;

function getDriverAllotmentId(){
	var	resultObject = search($('#truck_list').val(), trucks);
	function search(nameKey, myArray){
	    for (var j=0; j < myArray.length; j++) {
	        if (myArray[j].fk_truck_id == nameKey) {
	        	driver_allotment_id= myArray[j].pk_driver_allotment_id;
	        }
	    }
	}
}

$(document).ready(function (){ 
				$('#get').click(function () {
					$('#loading').show();
					var requestData = new Object();	
					requestData.pk_driver_allotment_id= driver_allotment_id;		
					$.ajax({
						url: '/transport/driver/allotment/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var  driverAllotment = response.DriverAllotment ;	            	
			            	$.each( driverAllotment , function(key, value){
			            		$('#'+key+'').val(value);	            	   
			            	});
			            	$('#loading').hide();

			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });

$(document).ready(function (){ 
	$('#update').click(function () {
		$('#loading').show();
		var requestData = new Object();
		requestData.pk_driver_allotment_id= $('#pk_driver_allotment_id').val();
		requestData.driver_allotment_date= $('#driver_allotment_date').val();
		requestData.fk_driver_id=$('#fk_driver_id').val();
		requestData.fk_association_id= $('#association_id').val();
		requestData.driver_status_id= $('#driver_id').val();
		
		$.ajax({
			url: '/transport/driver/allotment/update',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
            success: function(response) {
            	          	
            //	  alert("success") 
            	$('#loading').hide();
            	$("#Message").html(response.message);
            	
            },
            error: function(error) {
                console.log(error);
            }
		});
	});
});