 $(function() {
	    $('#get').click(function() {
	    	var invoice_number = $('#invoice_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/seller/purchase/get?'+'invoice_number='+invoice_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	             
	            	var sellerPurchase = response.SellerPurchase;	            	
	            	$.each(sellerPurchase, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});