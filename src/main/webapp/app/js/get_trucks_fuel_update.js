/*function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number,trucks[i].pk_trucks_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/all/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);
				truck_loaded=1;
				getFuel();
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function change(){
	var selectedTruck=$("#truck_list :selected").text();
	var selectedTruckId=$("#truck_list :selected").val();
//	$("#truck_id").val(selectedTruckId);
	truck_id=selectedTruckId;	
}*/

function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number,trucks[i].pk_trucks_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/all/other/trucks/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);
				truck_loaded=1;
				getFuel();
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}