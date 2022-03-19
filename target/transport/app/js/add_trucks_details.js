var fitnessImageBytes=null;
var fitnessImageType=null;
var rcImageBytes=null;
var rcImageType=null;
var insuranceImageBytes=null;
var insuranceImageType=null;
var permitImageBytes=null;
var permitImageType=null;

var openFileFitness = function(event) {
	    var input = event.target;

	    var reader_fitness = new FileReader();
	    reader_fitness.onload = function(){
	      var fitnessDataURL = reader_fitness.result;
	      var fitness_output = document.getElementById('fitness_output');
	      fitness_output.src = fitnessDataURL;
	      
	//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
	      fitnessImageBytes = fitnessDataURL.split(";base64,")[1] ;
	      fitnessImageType = fitnessDataURL.split(";base64,")[0].split("data:image/")[1] ;
	      
	    };
	    reader_fitness.readAsDataURL(input.files[0]);
	  };
	  
	  var openFileInsurance = function(event) {
		    var input = event.target;

		    var reader_insurance = new FileReader();
		    reader_insurance.onload = function(){
		      var insuranceDataURL = reader_insurance.result;
		      var insurance_output = document.getElementById('insurance_output');
		      insurance_output.src = insuranceDataURL;
		      
	//	      document.getElementById('insuranceImageBytes').innerHTML = insuranceDataURL.split(";base64,")[1] ;
		      insuranceImageBytes = insuranceDataURL.split(";base64,")[1] ;
		      insuranceImageType = insuranceDataURL.split(";base64,")[0].split("data:image/")[1] ;
		      
		    };
		    reader_insurance.readAsDataURL(input.files[0]);
		  };
		  
		  var openFileRc = function(event) {
			    var input = event.target;

			    var reader_rc = new FileReader();
			    reader_rc.onload = function(){
			      var rcDataURL = reader_rc.result;
			      var rc_output = document.getElementById('rc_output');
			      rc_output.src = rcDataURL;
			      			      
		//	      document.getElementById('rcImageBytes').innerHTML = rcDataURL.split(";base64,")[1] ;
			      rcImageBytes = rcDataURL.split(";base64,")[1] ;
			      rcImageType = rcDataURL.split(";base64,")[0].split("data:image/")[1] ;
			    };
			    reader_rc.readAsDataURL(input.files[0]);
			  };
			  
			  var openFilePermit = function(event) {
				    var input = event.target;

				    var reader_permit = new FileReader();
				    reader_permit.onload = function(){
				      var permitDataURL = reader_permit.result;
				      var permit_output = document.getElementById('permit_output');
				      permit_output.src = permitDataURL;		   
				      
			//	      document.getElementById('permitImageBytes').innerHTML = permitDataURL.split(";base64,")[1] ;
				      permitImageBytes = permitDataURL.split(";base64,")[1] ;
				      permitImageType = permitDataURL.split(";base64,")[0].split("data:image/")[1] ;
				    };
				    reader_permit.readAsDataURL(input.files[0]);
				  };

$(document).ready(function (){ 
		$('#insert').click(function () {
			
			if(fitnessImageBytes==null){
				fitnessImageBytes="";
			}
			if(fitnessImageType==null){
				fitnessImageType="";
			}
			if(insuranceImageBytes==null){
				insuranceImageBytes="";
			}
			if(insuranceImageType==null){
				insuranceImageType="";
			}
			if(rcImageBytes==null){
				rcImageBytes="";
			}
			if(rcImageType==null){
				rcImageType="";
			}
			if(permitImageBytes==null){
				permitImageBytes="";
			}
			if(permitImageType==null){
				permitImageType="";
			}
			
			var requestData = new Object();
			requestData.truck_number= $('#truck_number').val();
			requestData.chassis_number=$('#chassis_number').val();
		    requestData.engine_number=$('#engine_number').val();
			requestData.registration_date=$('#registration_date').val();
			requestData.permit_number=$('#permit_number').val();
			requestData.permit_area=$('#permit_area').val();
			requestData.permit_validity=$('#permit_validity').val();
			requestData.insurance_company_name=$('#insurance_company_name').val();
			requestData.insurance_policy_number=$('#insurance_policy_number').val();
			requestData.insurance_date=$('#insurance_date').val();
			requestData.insurance_expiry_date=$('#insurance_expiry_date').val();
			requestData.fitness_certificate_number=$('#fitness_certificate_number').val();
			requestData.fitness_expire_date=$('#fitness_expire_date').val();
			requestData.fitness_image_bytes_string = fitnessImageBytes;
			requestData.fitness_image_type = fitnessImageType;
			
			requestData.rc_image_bytes_string = rcImageBytes;
			requestData.rc_image_type = rcImageType;
			requestData.insurance_image_bytes_string = insuranceImageBytes;
			requestData.insurance_image_type = insuranceImageType;
			requestData.permit_image_bytes_string = permitImageBytes;
			requestData.permit_image_type = permitImageType;
		
	        $.ajax({
	        	url: '/transport/trucks/details/add',
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
