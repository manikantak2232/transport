
var actions =[];
function addDropDownOptions2InwardAction(actions){
	for(var i=0;i<actions.length;++i){
		addOption2(document.add.actionName,actions[i].name);
	}
}
function addOption2(selectbox,text ){
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function actions_listInward(){
	
		$.ajax({
			url: '/transport/godown/actions/getInward',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				 actions=response.actionInwardDetails;
				addDropDownOptions2InwardAction(actions);

			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} ;
