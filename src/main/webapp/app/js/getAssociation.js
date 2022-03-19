function addAssociationDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addAssociationOption(document.add.association_list,trucks[i].association_name,trucks[i].pk_association_id);
	}
}
function addAssociationOption(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function association(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/factory/association/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.association;
				addAssociationDropDown(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function associationChange(){
	var selectedTruck=$("#association_list :selected").text();
	var selectedTruckId=$("#association_list :selected").val();
	$("#association_id").val(selectedTruckId);
}