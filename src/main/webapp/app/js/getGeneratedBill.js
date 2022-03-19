var dataSet=[];
var dataSe=[];
var Factories;


jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"date-uk-pre": function ( a ) {
	var ukDatea = a.split('-');
	return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	},

	"date-uk-asc": function ( a, b ) {
	return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"date-uk-desc": function ( a, b ) {
	return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
	} );

function details(date_time){
	var svtcData = $.grep(Factories, function (element) { 
		  return element.bill_date_time==date_time;
		});
	document.getElementById("exampleDetails").deleteTFoot();
	dataSet=[];
	dataSe=[];
	total_quantity=0;
	total_freight=0;
	
	for(var i=0; i<svtcData.length; i++){
		factory = svtcData[i];
		
			dataSe=[];
			dataSe.push('');
			dataSe.push(factory.loading_date);
			dataSe.push(factory.truck_number);
			dataSe.push(factory.unload_location);
			dataSe.push(factory.invoice_number);
			dataSe.push(factory.association_name);	
			dataSe.push(factory.load_quantity);			
			dataSe.push(((factory.freight)/factory.load_quantity).toFixed(2));
			dataSe.push(factory.freight);
			dataSet.push(dataSe);
			
			total_quantity=total_quantity+(factory.load_quantity);
			total_freight=total_freight+(factory.freight);
		
	}
	
	$("#exampleDetails").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></tfoot>');

	 var t =  $('#exampleDetails').DataTable( {
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
			$( api.column( 5 ).footer() ).html('');
			$( api.column( 6 ).footer() ).html('');
			$( api.column( 8 ).footer() ).html('');

			// Total over all pages
			qty = api
			.column( 6 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amount = api
			.column( 8 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Total over this page
			qtyTotal = api
			.column( 6, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amountTotal = api
			.column( 8, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$( api.column( 5 ).footer() ).html(
					'Qty: '+(qty).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ', Amt: '+(amount).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
			);
			$( api.column( 6 ).footer() ).html(
					(qtyTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") 
			);
			$( api.column( 8 ).footer() ).html(
					(amountTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
			);


		//	$( api.column( 6 ).footer() ).html(total);

		},
       destroy: true,
       "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
 //      "bSort" : false,
       dom: 'lBfrtip',
       buttons: [
       	'excel','pdf','print'
       ],
       columns: [
			{ title: "S.No" },
			{ title: "Date", "sType": "date-uk" },
			{ title: "VEH. NO" },
			{ title: "Place" },
           { title: "Inv No." },
           { title: "Factory Name" },
           { title: "Qty" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
           { title: "Rate"},
           { title: "Amount", render: $.fn.dataTable.render.number( ',', '.', 2 )}
       ],
       "order": [[ 1, 'asc' ]]
      
   } );
	 
	 t.on( 'order.dt search.dt', function () {
	        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	            t.cell(cell).invalidate('dom');
	        } );
	    } ).draw();
}

function generatedBills(){ 
//	$('#loading').show();
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.association_id=$('#association_id').val();
	
	$.ajax({
		url: '/transport/factory/generated/bill/group/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			billDetails = response.billDetails;
			generatedBillDetails();
			for(var i=0; i<billDetails.length; i++){
				bill = billDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(bill.association_name);
					dataSe.push(bill.freight);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='View' onclick='return details(\""+bill.bill_date_time+"\")'>");
					dataSet.push(dataSe);
				
			}
			$(".overlay").hide();
			fun();
			

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {
	
	var t =  $('#example').DataTable( {
		data: dataSet,
		destroy: true,
		"bSort" : false,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Company" },
				{ title: "Amount" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "" }
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
function generatedBillDetails(){ 
//	$('#loading').show();
	dataSet=[];
	dataSe=[];

//	$(".overlay").show();
	var requestData = new Object();
	requestData.association_id=$('#association_id').val();
	
	$.ajax({
		url: '/transport/factory/generated/bill/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			
			$(".overlay").hide();
			Factories = response.Factories;

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function addAssociationDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addAssociationOption(document.add.association_list,trucks[i].association_name,trucks[i].pk_association_id);
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
		//		factoryNames(trucks);

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

