function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number,trucks[i].fk_truck_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function getEngagedTrucks(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/storage/engaged/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function testChange2(){
	var selectedTruck=$("#truck_list :selected").text();
	var selectedTruckId=$("#truck_list :selected").val();
	$("#truck_id").val(selectedTruckId);
}


function driverChange(){  
		
		var requestData = new Object();
		requestData.fk_driver_id=$('#fk_driver_id').val();
		requestData.location=$('#location').val();
		requestData.advance=$('#advance').val();
		requestData.fk_storage_dispatch_id=fk_storage_dispatch_id;
	
		$.ajax({
			url: '/transport/storage/driverchange/add',
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