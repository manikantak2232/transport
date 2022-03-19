
	  $(function() {
	    $('#button1').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trip/seller/details/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	              
	            	var  trip = response.Trips ;	            	
	            	$.each( trip, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});
	            },
	             error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	  });