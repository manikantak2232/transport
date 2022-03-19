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
			requestData.invoice_number= $('#invoice_number').val();
			requestData.date=$('#date').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.from_address=$('#from_address').val();
			requestData.to_address=$('#to_address').val();
			requestData.brand_name=$('#brand_name').val();
			requestData.cost_per_bag=$('#cost_per_bag').val();
			requestData.number_of_bags= $('#number_of_bags').val();
			requestData.total_weight=$('#total_weight').val();
			requestData.total_value=$('#total_value').val();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.time_of_arrival=$('#time_of_arrival').val();
			requestData.reading_km=$('#reading_km').val();
			requestData.status=$('#status').val();
	        $.ajax({
	        	url: '/transport/storage/incoming/add',
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