
	  $(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/allotment/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	         
	            	var trucksAllotment = response.trucksAllotment;	            	
	            	$.each(trucksAllotment, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});