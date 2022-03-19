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
$("#issued_date").val(today);

	  }
  
  $(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.issued_date=$('#issued_date').val();
			requestData.fk_oil_brand_id=$('#fk_oil_brand_id').val();
			requestData.liters=$('#liters').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.reading=$('#reading').val();
		
	        $.ajax({
	        	url: '/transport/spareparts/oil/issue/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	            	$("#Message").html(response.Message);
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});