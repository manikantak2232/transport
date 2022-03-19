var dataSet=[];
var dataSe=[];
var Factories;
var total_quantity=0;
var total_rate=0;
var total_amount=0;

function getPurchaseRegister(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_quantity=0;
	total_rate=0;
	total_amount=0;

	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.fuel_station_id=$('#fuel_station_id').val();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();

	$.ajax({
		url: '/transport/factory/fuel/fuel_station/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			Factories = response.Factories;

			for(var i=0; i<Factories.length; i++){
				factory = Factories[i];
				dataSe=[];
				dataSe.push('');
				dataSe.push(factory.date);
				dataSe.push(factory.voucher);
				dataSe.push(factory.truck_number);
				dataSe.push(factory.fuel_station_name);
				dataSe.push(factory.fuel_quantity);
				dataSe.push(factory.fuel_rate);
				dataSe.push((factory.fuel_quantity)*(factory.fuel_rate));			
				dataSet.push(dataSe);

				total_quantity=total_quantity+(factory.fuel_quantity);
				total_rate=total_rate+(factory.fuel_rate);
				total_amount=total_amount+((factory.fuel_quantity) * (factory.fuel_rate));

			}

			fun();

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}

function fun() {
	$("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></tfoot>');
	var t =  $('#example').DataTable( {
		data: dataSet,
		"footerCallback": function ( row, data, start, end, display ) {
			var api = this.api(), data;

			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ?
						i.replace(/[\$,]/g, '')*1 :
							typeof i === 'number' ?
									i : 0;
			};
			$( api.column( 4 ).footer() ).html('');
			$( api.column( 5 ).footer() ).html('');
			$( api.column( 7 ).footer() ).html('');

			// Total over all pages
			qty = api
			.column( 5 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amount = api
			.column( 7 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Total over this page
			qtyTotal = api
			.column( 5, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amountTotal = api
			.column( 7, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$( api.column( 4 ).footer() ).html(
					'Qty: '+(qty).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ', Amt: '+(amount).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
			);
			$( api.column( 5 ).footer() ).html(
					(qtyTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") 
			);
			$( api.column( 7 ).footer() ).html(
					(amountTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
			);


		//	$( api.column( 6 ).footer() ).html(total);

		},
		destroy: true,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		"bSort" : false,
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Voucher" },
				{ title: "Truck Number" },
				{ title: "Fuel Station" },
				{ title: "Quantity" },
				{ title: "Rate" },
				{ title: "Amount" , render: $.fn.dataTable.render.number( ',', '.', 2 )}
				],
				"order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();

//	$("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th>'+total_quantity +'</th><th>'+total_rate.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th>'+(total_amount).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tr></tfoot>');

//	$('#ledger_name').html(($('#fuel_list :selected').text()).toUpperCase());

};


function addStationDropDownOptions(name){
	for(var i=0;i<name.length;++i){
		addStationOption(document.add.fuel_list,name[i].fuel_station_name,name[i].fuel_station_id);
	}
}
function addStationOption(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}

function getFuelStaionName(){
	$.ajax({
		url: '/transport/factory/fuel/station/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			var name=response.fuelStation;
			addStationDropDownOptions(name);

		},
		error: function(error) {
			console.log(error);
		}
	});
}
function fuelChange(){
	var selectedStation=$("#fuel_list :selected").text();
	var selectedStationId=$("#fuel_list :selected").val();
	$("#fuel_station_id").val(selectedStationId);
}
