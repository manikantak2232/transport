var dispatch;
var truck_details;

function Dispatch(){
	
	var resultObject = search($("#truck_id").val(), truck_details);
	  function search(nameKey, myArray){
		    for (var i=0; i < myArray.length; i++) {
		        if (myArray[i].fk_truck_id == nameKey) {
		        	dispatch= myArray[i].dispatch;
		    //    	balance= myArray[i].balance;
		        	alert(dispatch);
		        
		        }
		    }
		}
	/*  $("#driver_name").val(driver_name);
	  $("#balance").val(balance);*/

}

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
function TripStartedTrucks(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trip/started/trucks/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.truckNumber;
				 truck_details=response.truckDetails
				addDropDownOptions2(trucks);
				
			//	Dispatch(truck_details);

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