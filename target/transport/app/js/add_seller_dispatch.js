var suggestion;
var x;
var truck_details;
var driver_name;
var driver_id;
var truck_id;
var fk_association_id;
function assignDriver(){
	
	var resultObject = search($("#truck_number").val(), truck_details);
	  function search(nameKey, myArray){
		    for (var i=0; i < myArray.length; i++) {
		        if (myArray[i].truck_number === nameKey) {
		        	driver_name= myArray[i].driver_name;
		        	driver_id=myArray[i].driver_id;
		        	truck_id=myArray[i].truck_id;
		        	fk_association_id=myArray[i].fk_association_id;
		        }
		    }
		}
		
	  $("#driver_name").val(driver_name);
	  $("#fk_driver_id").val(driver_id);
	  $("#truck_id").val(truck_id);
}


  function getAllotedTrucks(){
	  $(document).ready(function() {
			  var truck_number;

			  $.ajax({
					url: '/transport/trucks/alloted/get',
					type: 'GET',
				   dataType: 'json',
					contentType: 'application/json; charset=utf-8',
						success: function(response) {
			        	  	truck_number=response.truck_number; 
			        	  	truck_details=response.trucksDetails;
			        	  	$("#truck_number").autocomplete(
			        	  		{
				        		  source:truck_number,
				        		  minLength: 1
			        			}
			        	  	);		
 	
			          },
			          error: function(error) {
			              console.log(error);
			          }
				});

	   $.ui.autocomplete.filter = function (array, term) {
	        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
	        suggestion = $.grep(array, function (value) {
	        	return matcher.test(value.label || value.value || value);
	        });
	        return suggestion;
	    };
	 
	});
 }

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
$("#dispatch_date").val(today);

	  }

function test(){  
	
	var check_truck_number=$.inArray($('#truck_number').val(), suggestion)
	
	if(check_truck_number==-1)
		{
		alert("select Truck Number only from Suggestions");
		
		}
	else{
			var requestData = new Object();
			requestData.dispatch_date= $('#dispatch_date').val();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.fk_driver_id=$('#fk_driver_id').val();
			requestData.invoice_number=$('#invoice_number').val();
			requestData.start_location=$('#start_location').val();
			requestData.unload_location=$('#unload_location').val();
			requestData.estimated_km=$('#estimated_km').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			requestData.load_quantity=$('#load_quantity').val();
			requestData.freight=$('#freight').val();
			requestData.advance= $('#advance').val();
			requestData.fk_association_id=fk_association_id;
			
		  
	        $.ajax({
	        	url: '/transport/seller/dispatch/add',
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
	}
}