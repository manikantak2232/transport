var suggestion;
var x;
var truck_details;

var truck_id;
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

  function getFactoryUnloadingTrucks(){
	  $(document).ready(function() {
			  var truck_number;

			  $.ajax({
					url: '/transport/storage/trucks/unloading/get',
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
	            url: '/transport/storage/close/get?'+'truck_id='+truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var storageDispatch = response.StorageDispatch;	            	
	            	$.each(storageDispatch, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	}
}
	   
	    
	  $(function() {   
	    $('#update').click(function () {
			var requestData = new Object();
			requestData.storage_dispatch_id= $('#storage_dispatch_id').val();
	//		requestData.expenditure= $('#expenditure').val();
			requestData.closing_meter_reading=$('#closing_meter_reading').val();
			requestData.checked_kms= $('#checked_kms').val();
			/*requestData.balance=$('#balance').val();
			requestData.dispatch_status=$('#dispatch_status').val();	
			requestData.transport= $('#transport').val();
			requestData.loading= $('#loading').val();
			requestData.cover_tying= $('#cover_tying').val();
			requestData.contonment= $('#contonment').val();
			requestData.toll_gate= $('#toll_gate').val();
			requestData.loading_wage= $('#loading_wage').val();
			requestData.unloading_wage= $('#unloading_wage').val();
			requestData.phone_bills= $('#phone_bills').val();
			requestData.spares_parts= $('#spares_parts').val();
			requestData.puncher= $('#puncher').val();
			requestData.entry= $('#entry').val();
			requestData.return_transport= $('#return_transport').val();
			requestData.return_loading= $('#return_loading').val();
			requestData.return_unloading= $('#return_unloading').val();
			requestData.others= $('#others').val();
			*/
			$.ajax({
				url: '/transport/storage/close/update',
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
	  
	/*  function calculate() {
			var Advance = document.getElementById('advance').value;	
			var Expenditure = document.getElementById('expenditure').value;
			var balance = document.getElementById('balance');	
			var myResult = Advance - Expenditure;
			balance.value = myResult;
	 	
		}*/
	  
	  /*function setSelectedIndex(s, v) {
		    for ( var i = 0; i < s.options.length; i++ ) {
		        if ( s.options[i].text == v ) {
		            s.options[i].selected = true;
		            return;
		        }
		    }
		}

	  setSelectedIndex(document.getElementById("dispatch_status1"),$('#dispatch_status').val());
	  */