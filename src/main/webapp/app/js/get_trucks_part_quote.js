 $(function() {
	    $('#get').click(function() {
	    	var quote_number = $('#quote_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/quote/get?'+'quote_number='+quote_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	
	            	var trucksPartsQuote = response.trucksPartsQuote;	            	
	            	$.each(trucksPartsQuote, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});