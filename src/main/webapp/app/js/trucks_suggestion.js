var names = [];

function getTrucksSuggestions(){
	$.ajax({
		url: '/transport/trucks/all/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			var trucks=response.TruckDetails;
			for (var i = 0; i < trucks.length; i++) {
				names.push(trucks[i].truck_number);

			}
			sug();

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function sug() {
	var availableTags = names;
	$("#truck_number").autocomplete({
		source : availableTags
	});
};