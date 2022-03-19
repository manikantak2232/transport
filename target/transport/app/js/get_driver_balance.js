var balance;

 function driver_balance(){
	  $(document).ready(function() {
	    	var fk_driver_id = $('#fk_driver_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/driver/balance/get?'+'fk_driver_id='+fk_driver_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var Details = response.DriverDetails;
	            	balance=Details.balance;
	            	$('#balance').val(balance);	   
	            	
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	  });
	}
