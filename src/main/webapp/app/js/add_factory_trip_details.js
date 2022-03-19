var truck_id;
var type_of_load;
var load_description;
var freight;

var startingMeterReadingImageBytes=null;
var startingMeterReadingImageType=null;

var openFilestartingMeterReading = function(event) {
    var input = event.target;

    var reader_starting_meter_reading = new FileReader();
    reader_starting_meter_reading.onload = function(){
      var startingMeterReadingDataURL = reader_starting_meter_reading.result;
      var starting_meter_reading_output = document.getElementById('starting_meter_reading_output');
      starting_meter_reading_output.src = startingMeterReadingDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      startingMeterReadingImageBytes = startingMeterReadingDataURL.split(";base64,")[1] ;
      startingMeterReadingImageType = startingMeterReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;
      
    };
    reader_starting_meter_reading.readAsDataURL(input.files[0]);
  };
  
  function checkLoadType() {
	  
		if($('#type_of_load :selected').val() == 'with_load') {		
			$('#show_desc').css("display", 'block');			
		}else{
			$('#show_desc').css("display", 'none');
		}
	};

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
$("#start_date").val(today);

	  }

function queryString(){
	  $(document).ready(function() {
		  
		  truck_number=querystringTruck('truck_number');
		  $('#truck_number').val(truck_number);
		  truck_id=querystring('truck_id');
	 
	});
}

function querystringTruck(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}

function querystring(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}


$(document).ready(function (){ 
	  
		$('#insert').click(function () {
			
			$('#loading').show();
			
			if(startingMeterReadingImageBytes==null){
				startingMeterReadingImageBytes="";
			}
			if(startingMeterReadingImageType==null){
				startingMeterReadingImageType="";
			}		
			
			type_of_load=$('#type_of_load').val();
			load_description=$('#load_description').val();
			freight=$('#freight').val();
			
			if(freight==undefined | freight==''){ 
				freight=0;
			}
			if(type_of_load==undefined){ 
				type_of_load='';
			}
			if(load_description==undefined){ 
				load_description='';
			}
						
			var requestData = new Object();
			
			requestData.fk_truck_id=truck_id;
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.start_location=$('#start_location').val();
			requestData.start_date=$('#start_date').val();
			requestData.destination=$('#destination').val();
			requestData.load_description=load_description;
			requestData.estimated_km=$('#estimated_km').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			requestData.type_of_load=type_of_load;
			requestData.freight=freight;
			requestData.advance=$('#advance').val();
			requestData.starting_meter_reading_image_bytes_string = startingMeterReadingImageBytes;
			requestData.starting_meter_reading_image_type = startingMeterReadingImageType;

	        $.ajax({
	        	url: '/transport/trip/factory/details/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	$('#loading').hide();
	            	$("#Message").html(response.message);
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});