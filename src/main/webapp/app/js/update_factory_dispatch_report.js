var factory_dispatch_id;
var company_name;
var association_id;

function querystringAssociation(key) {
	var re = new RegExp('(?:\\?|&)' + key + '=(.*?)(?=&|$)', 'gi');
	var r = [], m;
	while ((m = re.exec(document.location.search)) != null)
		r.push(m[1]);
	return r[0];
}

function querystringName(key) {
	var re = new RegExp('(?:\\?|&)' + key + '=(.*?)(?=&|$)', 'gi');
	var r = [], m;
	while ((m = re.exec(document.location.search)) != null)
		r.push(m[1]);
	return r[0];
}

function querystringId(key) {
	var re = new RegExp('(?:\\?|&)' + key + '=(.*?)(?=&|$)', 'gi');
	var r = [], m;
	while ((m = re.exec(document.location.search)) != null)
		r.push(m[1]);
	return r[0];
}

function getDispatch() {
	factory_dispatch_id = querystringId('factory_dispatch_id');
	company_name = querystringName('company_name');
	association_id = querystringAssociation('association_id');
	// alert(association_id);
	getUnloadLocation(association_id);
	var requestData = new Object();
	requestData.factory_dispatch_id = factory_dispatch_id;
	requestData.company_name = company_name;

	$.ajax({
		url : '/transport/factory/dispatch/by/id',
		type : 'POST',
		data : JSON.stringify(requestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(response) {

			var factories = response.Factories;
			$.each(factories, function(key, value) {
				$('#' + key + '').val(value);
			});

			setSelectedIndex1(document.getElementById("location_list"), $(
					'#unloadLocation').val());

		},
		error : function(error) {
			console.log(error);
		}
	});
};

/*
 * function setSelectedIndex(s, v) { for ( var i = 0; i < s.options.length; i++ ) {
 * if ( s.options[i].text == v ) { s.options[i].selected = true; return; } } }
 */
/*
 * function setSelectedIndex1(g, b) { for ( var i = 0; i < g.options.length; i++ ) {
 * if ( g.options[i].text == b ) { g.options[i].selected = true; return; } } }
 */

// setSelectedIndex(document.getElementById("type_of_cement1"),$('#type_of_cement').val());
function updateDispatch() {
	var requestData = new Object();

	var d = new Date();
	var todayTime = new Date(d);
	var month = todayTime.getMonth() + 1;
	var day = todayTime.getDate();
	var year = todayTime.getFullYear();
	var hour = todayTime.getHours();
	var min = todayTime.getMinutes();
	var sec = todayTime.getSeconds();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;
	if (hour < 10)
		hour = "0" + hour;

	if (min < 10)
		min = "0" + min;

	if (sec < 10)
		sec = "0" + sec;

	var curDate = year + "-" + month + "-" + day + " " + hour + ":" + min + ":"
			+ sec;

	if ($('#loading_date').val() > $('#invoice_date').val()
			| curDate < $('#invoice_date').val()
			| curDate < $('#loading_date').val()) {
		/*
		 * alert($('#loading_date').val()); alert($('#invoice_date').val());
		 * alert(curDate);
		 */

		if ($('#loading_date').val() > $('#invoice_date').val()) {
			alert('loading date must be smaller than invoice date');
		}
		if (curDate < $('#invoice_date').val()
				| curDate < $('#loading_date').val()) {
			alert('you can not select future date');
		}
		;
	}

	else {
		requestData.factory_dispatch_id = factory_dispatch_id;
		requestData.loading_date = $('#loading_date').val();
		requestData.invoice_date = $('#invoice_date').val();
		requestData.invoice_number = $('#invoice_number').val();
		requestData.invoice_number2 = $('#invoice_number2').val();
		requestData.unload_location_id = $('#unload_location_id').val();
		requestData.type_of_cement = $('#type_of_cement').val();
		requestData.unload_location_name = $('#unload_location_name').val();
		requestData.load_quantity = $('#load_quantity').val();
		requestData.load_quantity2 = $('#load_quantity2').val();
		requestData.freight = $('#freight').val();
		requestData.freight2 = $('#freight2').val();

		$.ajax({
			url : '/transport/factory/dispatch/update',
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(requestData),
			contentType : 'application/json; charset=utf-8',
			success : function(response) {
				$("#Message").html(response.message);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}

};

function addDropDownOptions2(locations) {
	for (var i = 0; i < locations.length; ++i) {
		addOption2(document.add.location_list, locations[i].name,
				locations[i].pk_unload_location_id);
	}
}
function addOption2(selectbox, text, id) {
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = text;
	optn.id = id;
	selectbox.options.add(optn);
}
function getUnloadLocation(association_id) {
	$.ajax({
		url : '/transport/factory/unload/locations/get?' + 'association_id='
				+ association_id,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(response) {
			var locations = response.unloadLocations;
			addDropDownOptions2(locations);

			setSelectedIndex1(document.getElementById("location_list"), $(
					'#unloadLocation').val());
		},
		error : function(error) {
			console.log(error);
		}
	});
}
function locationChange() {
	var selectedTruck = $("#location_list :selected").text();
	var selectedTruckId = $("#location_list :selected").attr("id");
	$("#unload_location_id").val(selectedTruckId);
	// alert(selectedTruckId);
}

function setSelectedIndex1(s, v) {
	for (var i = 0; i < s.options.length; i++) {
		if (s.options[i].text == v) {
			s.options[i].selected = true;
			return;
		}
	}
}