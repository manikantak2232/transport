$(function() {
	$('#button1').click(function() {
		var invoice_number = $('#invoice_number').val();
		$.ajax({
			type: 'GET',
			url: '/transport/factory/dispatch/get?'+'invoice_number='+invoice_number,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {


				var  factories = response.Factories ;	            	
				$.each( factories , function(key, value){
					$('#'+key+'').val(value);	            	   
				});
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
});
$(document).ready(function (){ 

	$('#btTest2').click(function () {
		var requestData = new Object();
		requestData.factory_dispatch_id= $('#factory_dispatch_id').val();
		requestData.dispatch_date= $('#dispatch_date').val();
		requestData.fk_truck_id=$('#fk_truck_id').val();
		requestData.fk_driver_id=$('#fk_driver_id').val();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.start_location=$('#start_location').val();
		requestData.time_of_start=$('#time_of_start').val();
		requestData.unload_location=$('#unload_location').val();
		requestData.estimated_km=$('#estimated_km').val();
		requestData.starting_meter_reading=$('#starting_meter_reading').val();
		requestData.closing_meter_reading=$('#closing_meter_reading').val();
		requestData.checked_kms=$('#checked_kms').val();
		requestData.load_quantity=$('#load_quantity').val();
		requestData.freight=$('#freight').val();
		requestData.advance= $('#advance').val();
		requestData.balance=$('#balance').val();
		requestData.dispatch_status=$('#dispatch_status').val();

		$.ajax({
			url: '/transport/factory/dispatch/update',
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