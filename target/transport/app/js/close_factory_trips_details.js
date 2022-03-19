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

	function getTripStartedTruckDetails() {
		
			truck_id=querystringTruck('truck_id'); 
			
	    	var fk_truck_id = truck_id;
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trip/close/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	                     	
	            	var trip = response.Trips;	            	
	            	$.each( trip , function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});
	            },
	          error: function(error) {
	                console.log(error);
	            }
	        });

	  };
  
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
			requestData.trip_id= $('#trip_id').val();
			requestData.closing_meter_reading= $('#closing_meter_reading').val();
			requestData.checked_kms= $('#checked_kms').val();
			requestData.freight= $('#freight').val();
			requestData.closing_meter_reading_image_bytes_string =closingMeterReadingImageBytes;
			requestData.closing_meter_reading_image_type = closingMeterReadingImageType;
	//		requestData.trip_status=$('#trip_status').val();
			
			$.ajax({
				url: '/transport/trip/close/update',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify(requestData),
				contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	          	
	            //	  alert("success")  
	            	  $('#loading').hide();
	            	$("#Message").html(response.message);
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
			});
		});
	});