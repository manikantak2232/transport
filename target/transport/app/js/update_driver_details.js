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

 var aadharImageBytes=null;
 var aadharImageType=null;
 var licenseImageBytes=null;
 var licenseImageType=null;

 var openFileAadhar = function(event) {
     var input = event.target;

     var reader_aadhar = new FileReader();
     reader_aadhar.onload = function(){
       var aadharDataURL = reader_aadhar.result;
       var aadhar_output = document.getElementById('aadhar_output');
       aadhar_output.src = aadharDataURL;
       
       aadharImageBytes = aadharDataURL.split(";base64,")[1] ;
       aadharImageType = aadharDataURL.split(";base64,")[0].split("data:image/")[1] ;
       
     };
     reader_aadhar.readAsDataURL(input.files[0]);
   };
   
   var openFileLicense = function(event) {
 	    var input = event.target;

 	    var reader_license = new FileReader();
 	    reader_license.onload = function(){
 	      var licenseDataURL = reader_license.result;
 	      var license_output = document.getElementById('license_output');
 	      license_output.src = licenseDataURL;
 	      
 	      licenseImageBytes = licenseDataURL.split(";base64,")[1] ;
 	      licenseImageType = licenseDataURL.split(";base64,")[0].split("data:image/")[1] ;
 	      
 	    };
 	    reader_license.readAsDataURL(input.files[0]);
 	  };
 

	  $(document).ready(function (){ 
			$('#update').click(function () {
				
				if(aadharImageBytes==null){
					aadharImageBytes="";
				}
				if(aadharImageType==null){
					aadharImageType="";
				}
				if(licenseImageBytes==null){
					licenseImageBytes="";
				}
				if(licenseImageType==null){
					licenseImageType="";
				}
				
				var requestData = new Object();
				requestData.fk_driver_id= $('#fk_driver_id').val();
				requestData.first_name= $('#first_name').val();
				requestData.middle_name=$('#middle_name').val();
				requestData.last_name=$('#last_name').val();
				requestData.date_of_birth=$('#date_of_birth').val();
				requestData.aadhar_card=$('#aadhar_card').val();
				requestData.driving_license=$('#driving_license').val();
				requestData.non_transport_license_expiry_date=$('#non_transport_license_expiry_date').val();
				requestData.transport_license_expiry_date=$('#transport_license_expiry_date').val();
				requestData.phone_number=$('#phone_number').val();
				requestData.address=$('#address').val();
				
				requestData.license_image_bytes_string = licenseImageBytes;
				requestData.license_image_type = licenseImageType;
				requestData.aadhar_image_bytes_string = aadharImageBytes;
				requestData.aadhar_image_type = aadharImageType;
				
				$.ajax({
					url: '/transport/driver/updateDriverDetails',
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
	  
	  
	  
	  
	  
	  /*$(function() {
	    $('#get').click(function() {
	    	var fk_driver_id = $('#fk_driver_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/driver/getDriverDetails?'+'fk_driver_id='+fk_driver_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var sellerDispatch = response.DriverDetails1;	            	
	            	$.each(sellerDispatch, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});          	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});*/
