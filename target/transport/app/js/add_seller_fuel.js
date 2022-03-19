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
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id= $('#fk_driver_id').val();
			requestData.fuel_quantity=$('#fuel_quantity').val();
			requestData.fuel_rate=$('#fuel_rate').val();
			requestData.amount=$('#amount').val();
			requestData.fuel_station_location=$('#fuel_station_location').val();
			requestData.date=$('#date').val();
			requestData.advance=$('#advance').val();
		  
	        $.ajax({
	        	url: '/transport/seller/fuel/add',
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
  
  function calculate() {
	  var fuel_quantity=document.getElementById("fuel_quantity").value;
	  var fuel_rate=document.getElementById("fuel_rate").value;
	  var amount=document.getElementById("amount");
	  var myResult=fuel_quantity*fuel_rate;
	  amount.value=myResult;
}