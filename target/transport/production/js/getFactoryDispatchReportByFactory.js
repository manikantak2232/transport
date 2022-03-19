var xhr;
var ass_id;
var dataSet=[];
var dataSet1=[];
var t;
function fun12(freight){
	alert(freight);
}

function getDispatch() {

	if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
		xhr.abort();    
	}

	dataSet=[];
	dataSet1=[];

	var requestData = new Object();

	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();
	requestData.association_id=$('#association_id').val();

	xhr=$.ajax({	
		url: '/transport/factory/cumulative/report/get',
		type: 'POST',
		data: JSON.stringify(requestData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {

			$('#get').prop('disabled',false);
			var factory= "";
			var tables = ""
				var factoriesList = response;

			var svtcData = $.grep(factoriesList.Factories, function (element) {    
				return element.company_name=='svtc';
			});

			var otherData = $.grep(factoriesList.Factories, function (element) {  
				return element.company_name!='svtc';
			});

			paymentDetails = svtcData;
			if(paymentDetails!=null){
				for(var i=0; i<paymentDetails.length; i++){
					payment = paymentDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(payment.loading_date);
					dataSe.push(payment.truck_number);
					dataSe.push(payment.driver_name);
					dataSe.push(payment.invoice_number);
					dataSe.push(payment.start_location);
					dataSe.push(payment.unload_location);
					dataSe.push(payment.unload_location_name);
					dataSe.push(payment.unload_report_locations);
					dataSe.push(payment.type_of_cement);
					dataSe.push(payment.load_quantity);
					dataSe.push(payment.freight);
					dataSet.push(dataSe);

				}

				fun();
			}else{
				alert(response.message);
				var table = $('#example').DataTable(); 
				table.clear().draw();

			}

			otherDispatch = otherData;
			if(otherDispatch!=null){
				for(var i=0; i<otherDispatch.length; i++){
					other = otherDispatch[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(other.loading_date);
					dataSe.push(other.truck_number);
					dataSe.push(other.driver_name);
					dataSe.push(other.company_name);
					dataSe.push(other.invoice_number);
					dataSe.push(other.start_location);
					dataSe.push(other.unload_location);
					dataSe.push(other.unload_location_name);
					dataSe.push(other.type_of_cement);
					dataSe.push(other.load_quantity);
					dataSe.push(other.freight);
					dataSet1.push(dataSe);

				}

				fun1();
			}else{
				alert(response.message);
				var table = $('#example1').DataTable(); 
				table.clear().draw();

			}

		},

		error: function(error) {
			console.log(error);
		}
	});

	return false;
};

function fun() {
	t =  $('#example').DataTable({
		data: dataSet,
		destroy: true,
		"scrollX": true,
		"bSort" : true,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excelHtml5',
			'print',
			'pdf'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Truck Number" },
				{ title: "Driver" },
				{ title: "Invoice" },
				{ title: "Start Location" },
				{ title: "Godown"},
				{ title: "Party" },
				{ title: "Unload Locations" },
				{ title: "Type"},
				{ title: "Quantity"},
				{ title: "Freight" }
				],
				"order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();

};

function fun1() {
	var t =  $('#example1').DataTable({
		data: dataSet1,
		destroy: true,
		"scrollX": true,
		"bSort" : true,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Truck Number" },
				{ title: "Driver" },
				{ title: "Company" },
				{ title: "Invoice" },
				{ title: "Start Location"},
				{ title: "Godown"},
				{ title: "Party" },
				{ title: "Type"},
				{ title: "Quantity"},
				{ title: "Freight" }
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


function addAssociationDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addAssociationOption(document.add.association_list,trucks[i].association_alias_name,trucks[i].pk_association_id);
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
			url: '/transport/factory/factoriesAssociation/get',
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


