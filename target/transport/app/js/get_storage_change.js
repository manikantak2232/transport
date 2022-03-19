var fk_storage_dispatch_id;
var driver_name;
var trucks;

function getTrucksChange(){
	$(document).ready(function() {
		var fk_truck_id = $('#truck_id').val();
		$.ajax({
			url: '/transport/storage/change/get?'+'truck_id='+fk_truck_id,
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				trucks=response.TrucksDetails;
				driver_name=trucks.driver_name;
				 fk_storage_dispatch_id=trucks.fk_storage_dispatch_id;
				 
				  $("#driver_name").val(driver_name);
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}



