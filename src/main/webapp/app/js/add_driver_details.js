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
	$('#insert').click(function () {
		$('#loading').show();
		
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
			url: '/transport/driver/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	 
				$('#loading').hide();
				$("#Message").html(response.Message);
				
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
});