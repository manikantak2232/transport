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
			requestData.date=$('#date').val();
			requestData.fk_oil_brand_id=$('#fk_oil_brand_id').val();
			requestData.invoice_number=$('#invoice_number').val();
			requestData.liters=$('#liters').val();
			requestData.cost=$('#cost').val();
			requestData.invoice_date=$('#invoice_date').val();
		
	        $.ajax({
	        	url: '/transport/spareparts/oil/inventory/add',
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