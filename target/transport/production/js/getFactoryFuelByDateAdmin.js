var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;

var dataSet=[];
var dataSe=[];

function getPayment(){
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();
	requestData.truck_no= $('#truck_list option:selected').text();

	$.ajax({
		url: '/transport/factory/fuel/date/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			paymentDetails = response.Factories;
			if(paymentDetails!=null){
				for(var i=0; i<paymentDetails.length; i++){
					payment = paymentDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(payment.date);
					dataSe.push(payment.truck_number);
					dataSe.push(payment.driver_name);
					dataSe.push(payment.fuel_quantity);
					dataSe.push(payment.fuel_rate);
					dataSe.push(payment.fuel_station_name);
					dataSe.push(payment.starting_meter_reading);
					dataSe.push(payment.closing_meter_reading);
					dataSet.push(dataSe);

				}

				fun();
			}else{
				alert(response.message);
				var table = $('#example').DataTable(); 
				table.clear().draw();
				
			}
			

		},
		error: function(error) {
			console.log(error);
			alert('Something Wrong.. Try again..!');
			var table = $('#example').DataTable(); 
			table.clear().draw();
			
		}
	});
}

function fun() {
	var t =  $('#example').DataTable( {
		data: dataSet,
		destroy: true,
		"scrollX": true,
		"bSort" : true,
		//   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Truck Number" },
				{ title: "Driver" },
				{ title: "Quantity" },
				{ title: "Cost" },
				{ title: "Fuel Station"},
				{ title: "St. Reading" },
				{ title: "Cl. Reading"}
				],
				"order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();

};


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

function testChange2(){
	var selectedTruck=$("#truck_list :selected").text();
	var selectedTruckId=$("#truck_list :selected").val();
	$("#truck_id").val(selectedTruckId);
}
