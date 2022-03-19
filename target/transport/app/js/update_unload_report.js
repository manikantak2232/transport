var factory_dispatch_id;

function querystringId(key) {
	var re = new RegExp('(?:\\?|&)' + key + '=(.*?)(?=&|$)', 'gi');
	var r = [], m;
	while ((m = re.exec(document.location.search)) != null)
		r.push(m[1]);
	return r[0];
}

function getDispatch() {
	factory_dispatch_id = querystringId('factory_dispatch_id');

	var requestData = new Object();
	requestData.factory_dispatch_id = factory_dispatch_id;

	$.ajax({
		url : '/transport/factory/unload/report/by/id',
		type : 'POST',
		data : JSON.stringify(requestData),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(response) {

			var factories = response.unloadReport;
			$.each(factories, function(key, value) {
				$('#' + key + '').val(value);
			});

		},
		error : function(error) {
			console.log(error);
		}
	});
};

function updateDispatch() {
	var requestData = new Object();
	
	var unload_report_locations=$('#unload_report_locations').val();
	var closing_meter_reading=$('#closing_meter_reading').val();
	var starting_meter_reading=$('#starting_meter_reading').val();
	var checked_kms=$('#checked_kms').val();
	
	if(closing_meter_reading==undefined | closing_meter_reading==''){ 
		closing_meter_reading=0;
	}
	if(starting_meter_reading==undefined | starting_meter_reading==''){ 
		starting_meter_reading=0;
	}
	if(checked_kms==undefined | checked_kms==''){ 
		checked_kms=0;
	}
	
	if(unload_report_locations==undefined | unload_report_locations==''){ 
		alert("Please Enter Unload Locations..");
		return false;
	}
	
	requestData.factory_dispatch_id = factory_dispatch_id;
	requestData.starting_meter_reading = starting_meter_reading;
	requestData.closing_meter_reading = closing_meter_reading;
	requestData.unload_report_locations = unload_report_locations;
	requestData.checked_kms = checked_kms;

	$.ajax({
		url : '/transport/factory/unload/report/update',
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
};
