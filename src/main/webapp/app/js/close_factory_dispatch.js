

var suggestion;
var x;
var truck_details;

var truck_id;

var closingMeterReadingImageBytes=null;
var closingMeterReadingImageType=null;

var openFileClosingMeterReading = function(event) {
    var input = event.target;

    var reader_closing_meter_reading = new FileReader();
    reader_closing_meter_reading.onload = function(){
      var closingMeterReadingDataURL = reader_closing_meter_reading.result;
      var closing_meter_reading_output = document.getElementById('closing_meter_reading_output');
      closing_meter_reading_output.src = closingMeterReadingDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      closingMeterReadingImageBytes = closingMeterReadingDataURL.split(";base64,")[1] ;
      closingMeterReadingImageType = closingMeterReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;
    
    };
    reader_closing_meter_reading.readAsDataURL(input.files[0]);
  };

function querystringTruck(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}

function getDispatchDetails(){  
	
		truck_id=querystringTruck('truck_id');

	        $.ajax({
	            type: 'GET',
	            url: '/transport/factory/close/get?'+'truck_id='+truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	             
	            	
	            	var dispatch = response.FactoryDispatch;	            	
	            	$.each( dispatch , function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});
	            },
	          error: function(error) {
	                console.log(error);
	            }
	        });
	}


	  $(document).ready(function (){ 
		  $('#update').click(function () {
			  $('#loading').show();
			  
			  if(closingMeterReadingImageBytes==null){
				  closingMeterReadingImageBytes="";
				}
				if(closingMeterReadingImageType==null){
					closingMeterReadingImageType="";
				}
			  
			var requestData = new Object();
			requestData.factory_dispatch_id= $('#factory_dispatch_id').val();
		//	requestData.expenditure= $('#expenditure').val();
			requestData.closing_meter_reading= $('#closing_meter_reading').val();
			requestData.checked_kms= $('#checked_kms').val();
			requestData.closing_meter_reading_image_bytes_string =closingMeterReadingImageBytes;
			requestData.closing_meter_reading_image_type = closingMeterReadingImageType;
			
			$.ajax({
				url: '/transport/factory/close/update',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify(requestData),
				contentType: 'application/json; charset=utf-8',
	            success: function(response) {
 
	            	$('#loading').hide();
	            	/*if(response.message=='already closed'){
	            		alert('I found it. Try Something New..');
	            	}*/
	            	$("#Message").html(response.message);
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
			});
		});
	});
	  
	  
	  $(document).ready(function(){
			$('#closing_meter_reading').keypress(function (e) {
			    var regex = new RegExp("^[0-9.]+$");
			    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
			    if (regex.test(str)) {
			        return true;
			    }
			
			    e.preventDefault();
			    return false;
			});
			

			$('#checked_kms').keypress(function (e) {
			    var regex = new RegExp("^[0-9.]+$");
			    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
			    if (regex.test(str)) {
			        return true;
			    }

			    e.preventDefault();
			    return false;
			});
	  });
