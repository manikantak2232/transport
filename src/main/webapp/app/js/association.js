function addDropDownOptions2(ass){
	for(var i=0;i<ass.length;++i){
		addOption2(document.add.associationName,ass[i].association_name);
	}
}
function addOption2(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){

	$.ajax({
		url: '/transport/godown/association/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			var ass=response.associationDetails;
			addDropDownOptions2(ass);

		},
		error: function(error){
			console.log(error);
		}

	});

} ;
function associationList(){
	document.getElementById("unloadLocationName").options.length = 0;
	var finalLocations='';
	var listOfLocations='';
	var list= unloadlocations.filter(obj=>obj.association_name==$('#associationName option:selected').text());
	listOfLocations=list[0].locations;
	finalLocations= listOfLocations.split(",");
	addDropDownOptions(finalLocations);
}


