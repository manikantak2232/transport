 $(function() {
	    $('#get').click(function() {
	    	var invoice_number = $('#invoice_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/factory/dispatch/get?'+'invoice_number='+invoice_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	             
	            	
	            	var  factories = response.Factories ;	            	
	            	$.each( factories , function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});
	            },
	          error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	  });