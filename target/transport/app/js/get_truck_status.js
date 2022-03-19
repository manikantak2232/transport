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
/*function startTrip() {

	if($('#dispatch_status :selected').val() == 'in transit to factory' | $('#branch_dispatch_status :selected').val() == 'in transit to factory') {		
		alert('This is Not Dispatch. If Empty Truck goes with out load, Then You can Start Trip.')			
	}
};*/

function waiting() {

	if($('#dispatch_status :selected').val() == 'waiting' | $('#branch_dispatch_status :selected').val() == 'waiting') {		
		$('#waiting_location').css("visibility", 'visible');
		$('#waiting_location').css("width", 230);			
	}else{
		$('#waiting_location').css("visibility", 'hidden');
		$('#waiting_location').css("width", 0);

	}
};

function unloading() {

	if($('#dispatch_status :selected').val() == 'unloading' | $('#branch_dispatch_status :selected').val() == 'unloading') {		
		$('#unload_report_locations_id').css('display', 'block');		
	}else{
		$('#unload_report_locations_id').css("display", 'none');

	}
};

function idle() {

	if($('#dispatch_status :selected').val() == 'idle' | $('#branch_dispatch_status :selected').val() == 'idle') {		
		$('#idle_reason').css("visibility", 'visible');
		$('#idle_reason').css("width", 230);			
	}else{
		$('#idle_reason').css("visibility", 'hidden');
		$('#idle_reason').css("width", 0);

	}
};

$(document).ready(function (){ 
	$('#get').click(function () {

		document.getElementById('waiting_in_factory_for_loading').style.display = 'none';
		document.getElementById('waiting_in_branch_for_next_assignment').style.display = 'none';
		document.getElementById('load').style.display = 'none';
		document.getElementById('start').style.display = 'none';
		document.getElementById('branch_start').style.display = 'none';
		document.getElementById('in_transit_from_factory').style.display = 'none';
		document.getElementById('factory_waiting').style.display = 'none';
		document.getElementById('factory_unloading').style.display = 'none';
		document.getElementById('branch_waiting').style.display = 'none';
		document.getElementById('branch_unloading').style.display = 'none';
		document.getElementById('idle').style.display = 'none';
		document.getElementById('branch_idle').style.display = 'none';

		$('#waiting_in_factory_for_loading').prop('disabled', false);
		$('#waiting_in_branch_for_next_assignment').prop('disabled', false);

		$("#load").prop('disabled', false);
		$("#in_transit_from_factory").prop('disabled', false);
		$("#factory_unloading").prop('disabled', false);
		$("#factory_waiting").prop('disabled', false);
		$("#start").prop('disabled', false);
		$("#idle").prop('disabled', false);

		$("#branch_unloading").prop('disabled', false);
		$("#branch_waiting").prop('disabled', false);
		$("#branch_start").prop('disabled', false);
		$("#branch_idle").prop('disabled', false);

		/*$('#waiting_in_factory_for_loading').css("hidden", 'true');
            	$('#waiting_in_branch_for_next_assignment').css("hidden", 'true');
            	$('#load').css("hidden", 'true');
            	$('#start').css("hidden", 'true');
            	$('#branch_start').css("hidden", 'true');
            	$('#in_transit_from_factory').css("hidden", 'true');
            	$('#factory_waiting').css("hidden", 'true');
            	$('#factory_unloading').css("hidden", 'true');
            	$('#branch_waiting').css("hidden", 'true');
            	$('#branch_unloading').css("hidden", 'true');
        		$('#idle').css("hidden", 'true');
        		$('#branch_idle').css("hidden", 'true');*/

		$('#driver').css("display", 'none');
		$('#loadingDate').css("display", 'none');
		$('#current_status').val('');
		$('#waiting_location').css("visibility", 'hidden');
		$('#idle_reason').css("visibility", 'hidden');

		$('#dispatch_status option').prop('selected', function() {
			return this.defaultSelected;
		});
		$('#branch_dispatch_status option').prop('selected', function() {
			return this.defaultSelected;
		});


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
				/*	if(message!=='driver alloted'){

		            		$('#driver').css("display", 'none');	

		            	}
		            	if(message!=='success' | message!=='loading' | message!=='unloading'){

		            		$('#loadingDate').css("display", 'none');

		            	}*/
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

					$('#waiting_in_factory_for_loading').css("display", 'block');
					$('#waiting_in_branch_for_next_assignment').css("display", 'block');

					$("#load").prop('disabled', true);
					$("#in_transit_from_factory").prop('disabled', true);
					$("#factory_unloading").prop('disabled', true);
					$("#factory_waiting").prop('disabled', true);
					$("#start").prop('disabled', true);
					$("#idle").prop('disabled', true);

					$("#branch_unloading").prop('disabled', true);
					$("#branch_waiting").prop('disabled', true);
					$("#branch_start").prop('disabled', true);
					$("#branch_idle").prop('disabled', true);


				}
				if(message=='driver alloted'){
					driver_id=truckStatus.driver_id;
					truck_id=truckStatus.truck_id;
					association_id=truckStatus.association_id;
					driver_name=truckStatus.driver_name;
					driver_alloted_association_name=truckStatus.association_name;
					current_status=truckStatus.truck_status;

					//      		document.getElementById('in_transit_from_factory').disabled = true;

					$('#current_status').val(current_status);

					$('#driver').css("display", 'block');
					$('#driver_name').val(driver_name);

					if(manager_type == 'factory manager') {		
						$('#load').css("display", 'block');			
					}

					$('#start').css("display", 'block');
					$('#branch_start').css("display", 'block');

					$('#waiting_in_factory_for_loading').prop('disabled', true);
					$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
					$("#load").prop('disabled', true);
					$("#in_transit_from_factory").prop('disabled', true);
					$("#factory_unloading").prop('disabled', true);
					$("#factory_waiting").prop('disabled', true);
					$("#idle").prop('disabled', true);

					$("#branch_unloading").prop('disabled', true);
					$("#branch_waiting").prop('disabled', true);
					$("#branch_idle").prop('disabled', true);

				}

				if(message=='loading'){
					current_status=truckStatus.dispatch_status;
					truck_id=truckStatus.truck_id;
					dispatch_type=truckStatus.dispatch_type;
					dispatch_id=truckStatus.dispatch_id;
					loading_driver_id=truckStatus.driver_id;
					loading_date=truckStatus.loading_date;

					$('#current_status').val(current_status);

					// This is have to show only for factory managers. Keeping if condition is optional            		
					$('#loadingDate').css("display", 'block');
					$('#loading_date').val(loading_date);
					$('#in_transit_from_factory').css("display", 'block');	

					$('#waiting_in_factory_for_loading').prop('disabled', true);
					$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
					$("#load").prop('disabled', true);
					//          	$("#in_transit_from_factory").prop('disabled', true);
					$("#factory_unloading").prop('disabled', true);
					$("#factory_waiting").prop('disabled', true);
					$("#start").prop('disabled', true);
					$("#idle").prop('disabled', true);

					$("#branch_unloading").prop('disabled', true);
					$("#branch_waiting").prop('disabled', true);
					$("#branch_start").prop('disabled', true);
					$("#branch_idle").prop('disabled', true);

				}

				if(message=='unloading'){
					current_status=truckStatus.dispatch_status;
					unloading_truck_id=truckStatus.truck_id;
					unloading_dispatch_id=truckStatus.dispatch_id;
					loading_date=truckStatus.loading_date;

					$('#current_status').val(current_status);

					$('#loadingDate').css("display", 'block');
					$('#loading_date').val(loading_date);

					if(current_status=='in transit from factory'){
						$('#factory_waiting').css("display", 'block');
						$('#factory_unloading').css("display", 'block');
						$('#branch_waiting').css("display", 'block');
						$('#branch_unloading').css("display", 'block');

						$('#waiting_in_factory_for_loading').prop('disabled', true);
						$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
						$("#load").prop('disabled', true);
						$("#in_transit_from_factory").prop('disabled', true);
						$("#start").prop('disabled', true);
						$("#idle").prop('disabled', true);

						$("#branch_start").prop('disabled', true);
						$("#branch_idle").prop('disabled', true);
					}
					if(current_status=='waiting'){
						$('#factory_unloading').css("display", 'block');
						$('#branch_unloading').css("display", 'block');

						$('#waiting_in_factory_for_loading').prop('disabled', true);
						$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
						$("#load").prop('disabled', true);
						$("#in_transit_from_factory").prop('disabled', true);
						$("#factory_waiting").prop('disabled', true);
						$("#start").prop('disabled', true);
						$("#idle").prop('disabled', true);

						$("#branch_waiting").prop('disabled', true);
						$("#branch_start").prop('disabled', true);
						$("#branch_idle").prop('disabled', true);
					}
				}

				if(message=='idle'){
					current_status=truckStatus.truck_status;
					idle_truck_id=truckStatus.truck_id;
					idle_truck_number=truckStatus.truck_number;

					$('#current_status').val(current_status);

					$('#start').css("display", 'block');
					$('#idle').css("display", 'block');
					$('#branch_start').css("display", 'block');
					$('#branch_idle').css("display", 'block');

					$('#waiting_in_factory_for_loading').prop('disabled', true);
					$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
					$("#load").prop('disabled', true);
					$("#in_transit_from_factory").prop('disabled', true);
					$("#factory_unloading").prop('disabled', true);
					$("#factory_waiting").prop('disabled', true);

					$("#branch_unloading").prop('disabled', true);
					$("#branch_waiting").prop('disabled', true);

				}

				if(message=='truck in both allotment and trip'){
					current_status=truckStatus.dispatch_status;
					truck_id=truckStatus.truck_id;
					dispatch_type=truckStatus.dispatch_type;
					dispatch_id=truckStatus.dispatch_id;

					$('#current_status').val(current_status);
					/* 	document.getElementById('load').disabled = true;*/

					$('#waiting_in_factory_for_loading').css("display", 'block');
					$('#waiting_in_branch_for_next_assignment').css("display", 'block');

					$("#load").prop('disabled', true);
					$("#in_transit_from_factory").prop('disabled', true);
					$("#factory_unloading").prop('disabled', true);
					$("#factory_waiting").prop('disabled', true);
					$("#start").prop('disabled', true);
					$("#idle").prop('disabled', true);

					$("#branch_unloading").prop('disabled', true);
					$("#branch_waiting").prop('disabled', true);
					$("#branch_start").prop('disabled', true);
					$("#branch_idle").prop('disabled', true);
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

							/*	            	document.getElementById('start').disabled = false;
					            	document.getElementById('idle').disabled = false;*/

							$('#start').css("display", 'block');
							$('#idle').css("display", 'block');
							$('#branch_start').css("display", 'block');
							$('#branch_idle').css("display", 'block');

							$('#waiting_in_factory_for_loading').prop('disabled', true);
							$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
							$("#load").prop('disabled', true);
							$("#in_transit_from_factory").prop('disabled', true);
							$("#factory_unloading").prop('disabled', true);
							$("#factory_waiting").prop('disabled', true);

							$("#branch_unloading").prop('disabled', true);
							$("#branch_waiting").prop('disabled', true);
						}
						if(allotment_status[i].allotment_type=='driver alloted'){

							driver_id=allotment_status[i].driver_id;
							truck_id=allotment_status[i].truck_id;
							association_id=allotment_status[i].association_id;
							driver_name=allotment_status[i].driver_name;
							driver_alloted_association_name=allotment_status[i].association_name;
							current_status=allotment_status[i].association;

							$('#driver').css("display", 'block');					            	
							$('#driver_name').val(driver_name);

							$('#current_status').val(current_status);

							/*	document.getElementById('in_transit_from_factory').disabled = true;*/

							if(manager_type == 'factory manager') {		
								$('#load').css("display", 'block');
								$('#load').prop('disabled', false);


								$('#waiting_in_factory_for_loading').prop('disabled', true);
								$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
								$("#in_transit_from_factory").prop('disabled', true);
								$("#factory_unloading").prop('disabled', true);
								$("#factory_waiting").prop('disabled', true);
								$("#start").prop('disabled', true);
								$("#idle").prop('disabled', true);

								$("#branch_unloading").prop('disabled', true);
								$("#branch_waiting").prop('disabled', true);
								$("#branch_start").prop('disabled', true);
								$("#branch_idle").prop('disabled', true);
							}
							$('#start').css("display", 'block');
							$('#start').prop('disabled', false);
							$('#branch_start').css("display", 'block');
							$('#branch_start').prop('disabled', false);

							$('#idle').css("display", 'none');
							$('#branch_idle').css("display", 'none');

							/*	            	$('#waiting_in_factory_for_loading').prop('disabled', true);
					            	$('#waiting_in_branch_for_next_assignment').prop('disabled', true);
					            	$("#load").prop('disabled', true);
					            	$("#in_transit_from_factory").prop('disabled', true);
					            	$("#factory_unloading").prop('disabled', true);
					            	$("#factory_waiting").prop('disabled', true);

					            	$("#branch_unloading").prop('disabled', true);
					            	$("#branch_waiting").prop('disabled', true);*/
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
			requestData.unload_report_locations=$('#unload_report_locations_id').val();

			if($('#dispatch_status').val()=='waiting' & $('#waiting_location').val()=='Select'){
				alert("Please Select Waiting Location");
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
				requestData.unload_report_locations=$('#unload_report_locations_id').val();

				if($('#branch_dispatch_status').val()=='waiting' & $('#waiting_location').val()=='Select'){
					alert("Please Enter Waiting Location");
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
