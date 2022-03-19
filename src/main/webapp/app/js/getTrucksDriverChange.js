var pk_factory_dispatch_id;
var driver_name;
var trucks;

function addTrucksDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addTrucksOption(document.add.truck_number,trucks[i].truck_number,trucks[i].fk_truck_id);
	}
}
function addTrucksOption(selectbox,text,id)
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function getTrucksDriverChange(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/changedriver/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				trucks=response.TruckDetails;
				addTrucksDropDown(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
/*function testChange2(){
	var selectedTruck=$("#truck_list :selected").text();
	var selectedTruckId=$("#truck_list :selected").val();
	$("#truck_id").val(selectedTruckId);
}*/

function assignDriver(){
	
	var resultObject = search($("#truck_number :selected").text(), trucks);
	  function search(nameKey, myArray){
		    for (var i=0; i < myArray.length; i++) {
		        if (myArray[i].truck_number === nameKey) {
		        	pk_factory_dispatch_id= myArray[i].pk_factory_dispatch_id;
		        	driver_name=myArray[i].driver_name;
		        }
		    }
		}
		
	  $("#driver_name").val(driver_name);
}


