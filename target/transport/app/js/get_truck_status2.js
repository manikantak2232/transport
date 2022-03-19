var current_status;
var truck_id;
var dispatch_type;
var dispatch_id;
var driver_id;
var association_id;
var driver_alloted_association_name;
var driver_name;
var truck_number;
var loading_driver_id;

var unloading_truck_id;
var unloading_dispatch_id;

var idle_truck_id;
var idle_truck_number;

var manager_type;
var allotment_type;

var loading_date;

function factory(manager_type) {

//	manager_type=querystring('manager_type');
	  
	if(manager_type == 'factory manager') {		
		$('#factory_office').css("display", 'block');			
	}
	else{
		$('#branch_office').css("display", 'block');

	}
};


function waiting() {
	  
	if($('#dispatch_status :selected').val() == 'waiting' | $('#branch_dispatch_status :selected').val() == 'waiting') {		
		$('#waiting_location').css("visibility", 'visible');
		$('#waiting_location').css("width", 150);			
	}else{
		$('#waiting_location').css("visibility", 'hidden');
		$('#waiting_location').css("width", 0);

	}
};

function idle() {
	  
	if($('#dispatch_status :selected').val() == 'idle' | $('#branch_dispatch_status :selected').val() == 'idle') {		
		$('#idle_reason').css("visibility", 'visible');
		$('#idle_reason').css("width", 150);			
	}else{
		$('#idle_reason').css("visibility", 'hidden');
		$('#idle_reason').css("width", 0);

	}
};

$(document).ready(function (){ 
			$('#get').click(function () {
				var requestData = new Object();
				truck_number=$('#truck_number').val();
				
				requestData.truck_number= truck_number;
				
				$.ajax({
					url: '/transport/trucks/status/get',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	var truckStatus=response.truckStatus;
		            		
		            	var message=response.message;
		            	if(message=='No Login User Found'){
		            		alert('Please Login');
		            	}
		            	if(message==undefined){
		            		alert('Not Found');
		            	}
		            	if(message!=='driver alloted'){
		            		
		            		$('#driver').css("display", 'none');
		    	    //		$('#driver_name').css("width", 0);	
		            		
		            	}
		            	if(message!=='success' | message!=='loading' | message!=='unloading'){
		            		
		            		$('#loadingDate').css("display", 'none');
		            		
		            	}
		            	if(message=='this is not your truck number'){
		            		alert('Truck Number Not Belongs To You');
		            	}
		            	if(message=='success'){
		            		current_status=truckStatus.dispatch_status;
		            		truck_id=truckStatus.truck_id;
		            		dispatch_type=truckStatus.dispatch_type;
		            		dispatch_id=truckStatus.dispatch_id;
		            		loading_date=truckStatus.loading_date;
		            		
			            	$('#current_status').val(current_status);
			            	
			            	$('#loadingDate').css("display", 'block');
			            	
			            	$('#loading_date').val(loading_date);
		            	}
		            	if(message=='driver alloted'){
		            		driver_id=truckStatus.driver_id;
		            		truck_id=truckStatus.truck_id;
		            		association_id=truckStatus.association_id;
		            		driver_name=truckStatus.driver_name;
		            		driver_alloted_association_name=truckStatus.association_name;
		            		current_status=truckStatus.truck_status;
		            		
		            		document.getElementById('in_transit_from_factory').disabled = true;
		            		
		            		$('#current_status').val(current_status);
		            		
		            		/*$('#driver').css("visibility", 'visible');
		    	    		$('#driver_name').css("width", 125);	
		            		
			            	$('#driver_name').val(driver_name);*/
		            		
		            		$('#driver').css("display", 'block');
			            	$('#driver_name').val(driver_name);
      	
		            	}
		            	
		            	if(message=='loading'){
		            		current_status=truckStatus.dispatch_status;
		            		truck_id=truckStatus.truck_id;
		            		dispatch_type=truckStatus.dispatch_type;
		            		dispatch_id=truckStatus.dispatch_id;
		            		loading_driver_id=truckStatus.driver_id;
		            		loading_date=truckStatus.loading_date;
		            		
			            	$('#current_status').val(current_status);
			            	
			            	$('#loadingDate').css("display", 'block');
			            	$('#loading_date').val(loading_date);
		            	}
		            	
		            	if(message=='unloading'){
		            		current_status=truckStatus.dispatch_status;
		            		unloading_truck_id=truckStatus.truck_id;
		            		unloading_dispatch_id=truckStatus.dispatch_id;
		            		loading_date=truckStatus.loading_date;
		            		
			            	$('#current_status').val(current_status);
			            	
			            	$('#loadingDate').css("display", 'block');
			            	$('#loading_date').val(loading_date);
		            	}
		            	
		            	if(message=='idle'){
		            		current_status=truckStatus.truck_status;
		            		idle_truck_id=truckStatus.truck_id;
		            		idle_truck_number=truckStatus.truck_number;
		            		
			            	$('#current_status').val(current_status);
			            //		$('#load').disabled = true;
			            		$("#load").prop('disabled', true);
			            		document.getElementById('in_transit_from_factory').disabled = true;
			            		document.getElementById('start').disabled = false;
			            		document.getElementById('idle').disabled = false;
			            		document.getElementById('branch_start').disabled = false;
			            		document.getElementById('branch_idle').disabled = false;
			            
		            	}
		            	
		            	if(message=='truck in both allotment and trip'){
		            		current_status=truckStatus.dispatch_status;
		            		truck_id=truckStatus.truck_id;
		            		dispatch_type=truckStatus.dispatch_type;
		            		dispatch_id=truckStatus.dispatch_id;
		            		
			            	$('#current_status').val(current_status);
			            	document.getElementById('load').disabled = true;
		            	}
		            	
		            	if(message=='truck not found'){
		            		alert('Enter Another Truck Number');
		            	}
		            	
		            	if(message=='truck in both allotment and idle'){
		            		var allotment_status=response.truck_allotment_status;
		            		for(var i=0; i<allotment_status.length; i++){
		            			if(allotment_status[i].allotment_type=='idle'){
			            		
			            			idle_truck_id=allotment_status[i].truck_id;
			            			idle_truck_number=allotment_status[i].truck_number;
			            			
			            			current_status=allotment_status[i].truck_status;
					            	$('#current_status').val(current_status);
					            	
					            		document.getElementById('start').disabled = false;
					            		document.getElementById('idle').disabled = false;
			            		}
		            			if(allotment_status[i].allotment_type=='driver alloted'){
			            			
			            			driver_id=allotment_status[i].driver_id;
				            		truck_id=allotment_status[i].truck_id;
				            		association_id=allotment_status[i].association_id;
				            		driver_name=allotment_status[i].driver_name;
				            		driver_alloted_association_name=allotment_status[i].association_name;
				            		current_status=allotment_status[i].association;
				            		
				         /*   		$('#driver').css("visibility", 'visible');
				    	    		$('#driver_name').css("width", 125);	
				            		
					            	$('#driver_name').val(driver_name);*/
				            		$('#driver').css("display", 'block');					            	
					            	$('#driver_name').val(driver_name);
				            		
					            	$('#current_status').val(current_status);
					            	
					            	document.getElementById('in_transit_from_factory').disabled = true;
			            		}
			            		
		            		}  		
		            		
		            	}
		            
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
			});
		});

$(document).ready(function (){ 
	$('#update').click(function () {
			if($('#dispatch_status').val()=='waiting in factory office for loading' && dispatch_type=="factory dispatch"){
				window.open("/app/views/close_factory_dispatch.html?"+
						"truck_id="+truck_id );
			}
			
			if($('#dispatch_status').val()=='waiting in factory office for loading' && dispatch_type=="trip"){
				window.open("/app/views/close_factory_trip_details.html?"+
						"truck_id="+truck_id );
			}
			
			if($('#dispatch_status').val()=='loading' ){
				if(driver_alloted_association_name==association_name){
					
					var requestData = new Object();
					requestData.fk_truck_id=truck_id;	
					requestData.fk_driver_id=driver_id;
					requestData.fk_association_id=association_id;
					requestData.dispatch_status=$('#dispatch_status').val();

					$.ajax({
						url: '/transport/factory/loading/add',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {	            		 	         
						//	$("#Message").html(response.message);
							alert(response.message);
						},
						error: function(error) {
							console.log(error);
						}
					});	
				}
				else{
					alert('Sorry Not Your Truck');
				}			
			}
			
			if($('#dispatch_status').val()=='in transit from factory' && dispatch_type=='factory dispatch'){
				window.open("/app/views/add_factory_dispatch.html?"+
						"dispatch_id="+dispatch_id+"&truck_number="+truck_number+"&loading_driver_id="+loading_driver_id );
			}

			if($('#dispatch_status').val()=='unloading' | $('#dispatch_status').val()=='waiting'){
				
				var requestData = new Object();
				requestData.factory_dispatch_id= unloading_dispatch_id;
				requestData.dispatch_status=$('#dispatch_status').val();
				
				if($('#dispatch_status').val()=='waiting' & $('#waiting_location').val()==''){
					alert("Please Enter Waiting Location");
					}
				else{
					if($('#waiting_location').val()==''){
						requestData.waiting_location="";
						}
					else{
						requestData.waiting_location=$('#waiting_location').val();
					}
					}
				
				
				$.ajax({
					url: '/transport/factory/status/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	          	
		            //	$("#Message").html("Updated Successfully");
		            	alert(response.message);
		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
				
			}
			
			if($('#dispatch_status').val()=='in transit to factory'){
				window.open("/app/views/add_factory_trip_details.html?"+
						"truck_number="+idle_truck_number+"&truck_id="+idle_truck_id );
			}
			
			if($('#dispatch_status').val()=='idle'){
				
				if( $('#idle_reason').val()==''){
					alert('Please Enter Reason For Idle');
				}
				else{
					var requestData = new Object();
					requestData.fk_truck_id=idle_truck_id;	
					requestData.idle_reason=$('#idle_reason').val();

					$.ajax({
						url: '/transport/trucks/idle/add',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {	            		 	         
						//	$("#Message").html(response.message);
							alert(response.message);
						},
						error: function(error) {
							console.log(error);
						}
					});
				}
				
			}
		if(manager_type == 'branch office manager'){
			if($('#branch_dispatch_status').val()=='waiting in factory office for loading' && dispatch_type=="factory dispatch"){
				window.open("/app/views/close_factory_dispatch.html?"+
						"truck_id="+truck_id );
			}
			
			if($('#branch_dispatch_status').val()=='waiting in factory office for loading' && dispatch_type=="trip"){
				window.open("/app/views/close_factory_trip_details.html?"+
						"truck_id="+truck_id );
			}
			
			if($('#branch_dispatch_status').val()=='unloading' | $('#branch_dispatch_status').val()=='waiting'){
				
				var requestData = new Object();
				requestData.factory_dispatch_id= unloading_dispatch_id;
				requestData.dispatch_status=$('#branch_dispatch_status').val();
				
				if($('#branch_dispatch_status').val()=='waiting' & $('#waiting_location').val()==''){
					alert("Please Enter Waiting Location");
					}
				else{
					if($('#waiting_location').val()==''){
						requestData.waiting_location="";
						}
					else{
						requestData.waiting_location=$('#waiting_location').val();
					}
					}
				
				
				$.ajax({
					url: '/transport/factory/status/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	          	
		            //	$("#Message").html("Updated Successfully");
		            	alert(response.message);
		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
				
			}
			
			if($('#branch_dispatch_status').val()=='in transit to factory'){
				window.open("/app/views/add_factory_trip_details.html?"+
						"truck_number="+idle_truck_number+"&truck_id="+idle_truck_id );
			}
			
			if($('#branch_dispatch_status').val()=='idle'){
				
				if( $('#idle_reason').val()==''){
					alert('Please Enter Reason For Idle');
				}
				else{
					var requestData = new Object();
					requestData.fk_truck_id=idle_truck_id;	
					requestData.idle_reason=$('#idle_reason').val();

					$.ajax({
						url: '/transport/trucks/idle/add',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {	            		 	         
						//	$("#Message").html(response.message);
							alert(response.message);
						},
						error: function(error) {
							console.log(error);
						}
					});
				}
				
			}
		}
		
		});
});
	  