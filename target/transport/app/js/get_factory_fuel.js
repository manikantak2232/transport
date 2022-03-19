
	  $(function() {
	    $('#button1').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/factory/fuel/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	                //$('#lblData').html(JSON.stringify(response));
	            	// $('#first_name').val(response.first_name);
	            	//populate('#MyForm', response);
	            	
	            	
	            	var  numbers = response.Numbers ;	            	
	            	$.each( numbers, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});
	            },
	          error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	  });