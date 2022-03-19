
/*
var actions =[];
function actions_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/godown/actions/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var act=response.actionDetails;
			//	addDropDown2(actions);
           
				for (var i = 0; i < act.length; i++) {
					actions.push(act[i].name);
				}
				
				action_sug();
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}


function action_sug() {
	var availableTags = actions;
	$("#actionName").autocomplete({
		source : availableTags
	});
};*/

var actions =[];
function addDropDownOptions2Action(actions){
	for(var i=0;i<actions.length;++i){
		addOption2(document.add.actionName,actions[i].name);
	}
}
function addOption2(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function actions_list2(){
	
		$.ajax({
			url: '/transport/godown/actions/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				 actions=response.actionDetails;
				addDropDownOptions2Action(actions);

			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} ;
