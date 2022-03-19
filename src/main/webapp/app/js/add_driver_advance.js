

function currentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
		dd='0'+dd;
	} 
	if(mm<10){
		mm='0'+mm;
	} 
	var today =yyyy+'-'+mm+'-'+ dd;
	$("#date").val(today);

}

function addDriverBalance() {
	$('#loading').show();
	
	if($('#balance').val()>10000){
		alert('Dont give advance. Contact Main Office..');
		$('#loading').hide();
		return true;
	}

	var requestData = new Object();
	requestData.fk_truck_id=  $('#truck_id').val();
	requestData.fk_driver_id= $('#fk_driver_id').val();
	requestData.date=$('#date').val();
	requestData.advance=$('#advance').val();

	$.ajax({
		url: '/transport/factory/driver/advance/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$('#loading').hide();
			alert(response.message);
		},
		error: function(error) {
			console.log(error);
			$('#loading').hide();
		}
	});
};

/*
function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number,trucks[i].pk_trucks_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/all/other/trucks/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
*/