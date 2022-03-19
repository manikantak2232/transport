var unloadlocations;
function unload_list(){
	
		$.ajax({
			url: '/transport/godown/inward/unload',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				 unloadlocations =response.unloadDetails;

			},
			error: function(error) {
				console.log(error);
			}
	});
};

function addDropDownOptions(locations){
//	alert(locations);
	var optn=document.createElement("OPTION");
	optn.text='Select';
	optn.style.display = "none";

	document.add.unloadLocationName.options.add(optn);
	
	
	for(var i=0;i<locations.length;i++){
	
		addOption(document.add.unloadLocationName,locations[i]);
	}
}
function addOption(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	selectbox.options.add(optn);
	
}

function location_sug(){
	var selectedTruck=$("#unloadLocationName :selected").val();
	var selectedTruckId=$("#unloadLocationName :selected").val();
	$("#truck_id").val(selectedTruckId);
}
