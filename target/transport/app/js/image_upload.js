  var imageBytes;
  var imageType;
  var openFile = function(event) {
	    var input = event.target;

	    var reader = new FileReader();
	    reader.onload = function(){
	      var dataURL = reader.result;
	      var output = document.getElementById('output');
	      output.src = dataURL;
	      document.getElementById('imageBytes').innerHTML = dataURL.split(";base64,")[1] ;
	      imageBytes = dataURL.split(";base64,")[1] ;
	      imageType = dataURL.split(";base64,")[0].split("data:image/")[1] ;
	    };
	    reader.readAsDataURL(input.files[0]);
	  };
	  
	function uploadImage(){
	
		var requestData = new Object();
		requestData.image_bytes_string = imageBytes;
		requestData.image_type = imageType;
		requestData.bucket = "truckimages";
		requestData.folder = "fitness";
		requestData.primary_id = "123";
		
        $.ajax({
            type: 'POST',
            url: '/transport/image/uploadToAwsTest',															   
            dataType: 'json',
            data: JSON.stringify(requestData),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {	         	
            	            	
            	$('#result').html("<center><font size=16>Image Added Successfully.</font></center> <br> <center><font size=16>Thank You.</font></center>"); 
            	
            },
            error: function(error) {
            	$('#result').html("<center><font size=14>Image could not be added.</font></center>");
            }
        });
	}
	