 $(function() {
	    $('#get').click(function() {
	    	var invoice_number = $('#invoice_number').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/seller/invoice/get?'+'invoice_number='+invoice_number,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	           
	            	var sellerInvoice = response.SellerInvoice;	            	
	            	$.each(sellerInvoice, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});