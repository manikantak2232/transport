var suggestion;
var x;
var truck_details;
var truck_id;

function waiting() {
	  
	if($('#dispatch_status :selected').val() == 'waiting') {		
		$('#waiting_location').css("visibility", 'visible');
		$('#waiting_location').css("width", 150);			
	}else{
		$('#waiting_location').css("visibility", 'hidden');
		$('#waiting_location').css("width", 0);

	}
};

function assignDriver(){
	
	var resultObject = search($("#truck_number").val(), truck_details);
	  function search(nameKey, myArray){
		    for (var i=0; i < myArray.length; i++) {
		        if (myArray[i].truck_number === nameKey) {
		        	truck_id=myArray[i].truck_id;
		        }
		    }
		}

	  $("#truck_id").val(truck_id);
}


  function getFactoryDispatchedTrucks(){
	  $(document).ready(function() {
			  var truck_number;

			  $.ajax({
					url: '/transport/storage/trucks/dispatched/get',
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


function test(){  
	
	var check_truck_number=$.inArray($('#truck_number').val(), suggestion)
	
	if(check_truck_number==-1)
		{
		alert("select Truck Number only from Suggestions");
		
		}
	else{
	    	var truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/storage/status/get?'+'truck_id='+truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var statusDispatch = response.StatusDispatch;	            	
	            	$.each(statusDispatch, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	}
}
	  
	  $(document).ready(function (){ 
			$('#btTest2').click(function () {
				var requestData = new Object();
				requestData.storage_dispatch_id= $('#storage_dispatch_id').val();
				requestData.dispatch_status=$('#dispatch_status').val();
				if($('#dispatch_status').val()=='waiting' & $('#waiting_location').val()=='Select'){
					alert("Please Select Waiting Location");
					}
				else{
					if($('#waiting_location').val()=='Select'){
						requestData.waiting_location="";
						}
					else{
						requestData.waiting_location=$('#waiting_location').val();
					}
					}
				
				
				$.ajax({
					url: '/transport/storage/status/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	          	
		            //	  alert("success")  
		            	$("#Message").html("Dispatch Updated Successfully");
		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
			});
		});