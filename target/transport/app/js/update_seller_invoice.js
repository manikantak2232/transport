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
	  
	  $(document).ready(function (){ 
			$('#update').click(function () {
				var requestData = new Object();
				requestData.seller_invoice_id= $('#seller_invoice_id').val();
				requestData.customer_first_name= $('#customer_first_name').val();
				requestData.customer_middle_name=$('#customer_middle_name').val();
				requestData.customer_last_name= $('#customer_last_name').val();
				requestData.name_of_brand=$('#name_of_brand').val();				
				requestData.from_address= $('#from_address').val();
				requestData.to_address= $('#to_address').val();
				requestData.invoice_date=$('#invoice_date').val();
				requestData.phone_number=$('#phone_number').val();
				requestData.email= $('#email').val();				
				requestData.number_of_bags=$('#number_of_bags').val();
				requestData.cost_per_bag=$('#cost_per_bag').val();				
				requestData.total_value= $('#total_value').val();
				requestData.sales_manager_name= $('#sales_manager_name').val();
				requestData.freight_rate=$('#freight_rate').val();
				requestData.approve_status=$('#approve_status').val();
				
				$.ajax({
					url: '/transport/seller/invoice/update',
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
	  