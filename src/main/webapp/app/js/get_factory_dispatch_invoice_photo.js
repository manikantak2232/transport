var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;

function fun(){
		
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
		
		table="";
		table_rows = "";
		factory= "";
		tables = "";

		xhr=$.ajax({
			url: '/transport/factory/dispatch/invoice/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {

				var factoriesList = response;
				for(var i=0; i<factoriesList.details.length; i++){
					factory = factoriesList.details[i];

					table_rows = table_rows + 
					"<tr width=100%>"+
					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+	
					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+	
					"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+		            						
					"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.factory_name + "</td>"+	
			//		"<td ><button type='button' disabled='false' onclick='large("+factory.dispatch_id+")'>Click</button></td>"+
					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'><input type='button' data-toggle='modal' data-target='#myModal' onclick='large("+factory.dispatch_id+")' value='Click' > </td>"+
					"</tr>";

				}	            	           		

				var table_header = "<tr width=100%>"+
				"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck No" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Number" + "</td>"+
				"<td width=12% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Name" + "</td>"+
				"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Factory" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				"</tr>";
				table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

				$('#dataTab').html(table);


			},
			error: function(error) {
				console.log(error);
			}
		});
	};


var petrolPumpReadingImageBytes=null;
var petrolPumpReadingImageType=null;

var openFilePetrolPumpReading = function(event) {
    var input = event.target;

    var reader_petrol_pump_reading = new FileReader();
    reader_petrol_pump_reading.onload = function(){
      var petrolPumpReadingDataURL = reader_petrol_pump_reading.result;
      var petrol_pump_reading_output = document.getElementById('petrol_pump_reading_output');
      petrol_pump_reading_output.src = petrolPumpReadingDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      petrolPumpReadingImageBytes = petrolPumpReadingDataURL.split(";base64,")[1] ;
      petrolPumpReadingImageType = petrolPumpReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;

    };
    reader_petrol_pump_reading.readAsDataURL(input.files[0]);
  };

  
function addPhoto() {
			/*$('#loading').show();*/
			
			if(petrolPumpReadingImageBytes==null){
				petrolPumpReadingImageBytes="";
			}
			if(petrolPumpReadingImageType==null){ 
				petrolPumpReadingImageType="";
			}
			
			var requestData = new Object();
			requestData.petrol_pump_reading_image_bytes_string = petrolPumpReadingImageBytes;
			requestData.petrol_pump_reading_image_type = petrolPumpReadingImageType;
			requestData.dispatch_ids = dispatch_id;
			
			$.ajax({
	        	url: '/transport/factory/invoice/photo/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	
	            	$('#loading').hide();
	            	alert(response.message);
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    };
	    
var dispatch_id;

	    function large(i){
	    	dispatch_id=i;
	    	/*var modal = document.getElementById('myModal');
	    	modal.style.display = "block";*/
	    }