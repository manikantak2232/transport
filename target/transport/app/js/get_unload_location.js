function addDropDownOptions2(locations){
	for(var i=0;i<locations.length;++i){
		addOption2(document.add.location_list,locations[i].name,locations[i].pk_unload_location_id);
	}
}
function addOption2(selectbox,text,id)
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=text;
	optn.id=id;
	selectbox.options.add(optn);
}
function getUnloadLocation(association_id){		
		$.ajax({
			url: '/transport/factory/unload/locations/get?'+'association_id='+association_id,
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var locations=response.unloadLocations;
				addDropDownOptions2(locations);
/*   				setSelectedIndex(document.getElementById("company_list"),$('#companyName').val());
				setSelectedIndex1(document.getElementById("location_list"),$('#unloadLocation').val());*/
			},
			error: function(error) {
				console.log(error);
			}
	});
}
function locationChange(){
	var selectedTruck=$("#location_list :selected").text();
	var selectedTruckId=$("#location_list :selected").attr("id");
	$("#unload_location_id").val(selectedTruckId);
	// alert(selectedTruckId);
}