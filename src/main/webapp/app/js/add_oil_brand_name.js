$(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.brand_name= $('#brand_name').val();

	        $.ajax({
	        	url: '/transport/spareparts/oil/new/brandname',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	            	$("#Message").html("Inserted Successfully");
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});