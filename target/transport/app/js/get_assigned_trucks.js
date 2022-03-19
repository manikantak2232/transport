var trucks;

function addAssignedDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addAssignedOption(document.add.truck_list,trucks[i].truck_number,trucks[i].fk_truck_id);
		$("#pk_driver_allotment_id").val(trucks[i].pk_driver_allotment_id);
	}
}
function addAssignedOption(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function getAssignedTrucks(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/assigned/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				 trucks=response.trucksDetails;
				addAssignedDropDown(trucks);
				

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function truckChange(){
	var selectedTruck=$("#truck_list :selected").text();
	var selectedTruckId=$("#truck_list :selected").val();
	$("#truck_id").val(selectedTruckId);
}