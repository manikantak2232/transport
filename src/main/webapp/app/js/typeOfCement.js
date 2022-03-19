var type =[];
function type_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/godown/typecement/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var typeOfCement=response.typeDetails;
			
			//	addDropDown2(actions);
           
				for (var i = 0; i < typeOfCement.length; i++) {
					type.push(typeOfCement[i].name);
				}
				
				type_sug();
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}


function type_sug() {
	var availableTags = type;
	$("#typeOfCement").autocomplete({
		source : availableTags
	});
};