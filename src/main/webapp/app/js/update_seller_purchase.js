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
	  
	  $(document).ready(function (){ 
			$('#update').click(function () {
				var requestData = new Object();
				requestData.seller_purchase_id= $('#seller_purchase_id').val();
				requestData.name_of_brand= $('#name_of_brand').val();
				requestData.number_of_bags=$('#number_of_bags').val();
				requestData.cost_per_bag= $('#cost_per_bag').val();
				requestData.total_value=$('#total_value').val();				
				requestData.date= $('#date').val();
				
				$.ajax({
					url: '/transport/seller/purchase/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	          	
		            //	  alert("success")  
		            	$("#Message").html("Updated Successfully");
		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
			});
		});