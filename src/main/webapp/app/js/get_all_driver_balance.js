var balance;
var Details;

 function getAllDriverBalance(){
	        $.ajax({
	            type: 'GET',
	            url: '/transport/driver/balance/get/all',
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	Details = response.DriverDetails;
/*	            	balance=Details.balance;
	            	$('#balance').val(balance);	*/   
	            	
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	  });
	}

 function search(){
	    for (var i=0; i < Details.length; i++) {
	        if (Details[i].fk_driver_id == $('#fk_driver_id').val()) {
	        	balance= Details[i].balance;
	        	$('#balance').val(balance);
	        }
	    }
	}
