var aadhar;
var license;
 $(function() {
	    $('#get').click(function() {
	    	var fk_driver_id = $('#fk_driver_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/driver/getDriverDetails?'+'fk_driver_id='+fk_driver_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var sellerDispatch = response.DriverDetails;
	            	aadhar=sellerDispatch.aadhar_image_url;
	            	license=sellerDispatch.license_image_url;
	            	
	            	if(aadhar!==''){
	 	            	document.getElementById('aadhar_button').innerHTML = '<input type="button"  value="View Aadhar" id="aadhar_id"   onclick="aadharOpen()"><br><br>';
		            } else {
	 	            	document.getElementById('aadhar_button').innerHTML = '';

		            }
	            	 
	            	 if(license!==''){
	 	            	document.getElementById('license_button').innerHTML = '<input type="button"  value="View License" id="license_id"   onclick="licenseOpen()"><br><br>';
		            } else {
	 	            	document.getElementById('license_button').innerHTML = '';

		            }
	            
	            	$.each(sellerDispatch, function(key, value){
	            		$('#'+key+'').val(value);	   	
	            	});  	   
	            	
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});
 function aadharOpen()
 {
 	window.open(aadhar);
 }
 
 function licenseOpen()
 {
 	window.open(license);
 }