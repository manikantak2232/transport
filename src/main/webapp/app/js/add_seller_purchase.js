function currentDate() {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!

	  var yyyy = today.getFullYear();
	  if(dd<10){
	      dd='0'+dd;
	  } 
	  if(mm<10){
	      mm='0'+mm;
	  } 
	  var today =yyyy+'/'+mm+'/'+ dd;
	  $("#date").val(today);

	  }

$(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.invoice_number= $('#invoice_number').val();
			requestData.name_of_brand=$('#name_of_brand').val();
			requestData.number_of_bags=$('#number_of_bags').val();
			requestData.cost_per_bag=$('#cost_per_bag').val();
			requestData.total_value=$('#total_value').val();
			requestData.date=$('#date').val();
		  
	        $.ajax({
	        	url: '/transport/seller/purchase/add',
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