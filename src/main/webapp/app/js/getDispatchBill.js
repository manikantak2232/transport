var dataSet=[];
var dataSe=[];
var Factories;
var total_quantity=0;
var total_freight=0;

function fu(ac_name){
	alert(ac_name);
}

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

function updateBill(){
        var favorite = [];
        $.each($("input[name='sport']:checked"), function(){          
            favorite.push($(this).val());
        });
  //      alert("My favourite sports are: " + favorite.join(","));
        
        var requestData = new Object();
    	requestData.dispatch_ids=favorite.join(",");
    	
    	$.ajax({
    		url: '/transport/factory/dipatch/bill/ids/update',
    		type: 'POST',
    		dataType: 'json',
    		data: JSON.stringify(requestData),
    		contentType: 'application/json; charset=utf-8',
    		success: function(response) {
    			if(response.message=='success'){
    				getSalesRegister();
    			}else{
    				alert(response.message);
    			}
    			
    		}
    	});
    }

var $datatable = $('#datatable-checkbox');

$datatable.dataTable({
	'order': [[ 1, 'asc' ]],
	'columnDefs': [
		{ orderable: false, targets: [0] }
		]
});
$datatable.on('draw.dt', function() {
	$('checkbox input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	});
});

$(document).ready(function() {
	if ($("input.flat")[0]) {
		$(document).ready(function () {
			$('input.flat').iCheck({
				checkboxClass: 'icheckbox_flat-green',
				radioClass: 'iradio_flat-green'
			});
		});
	}
});

function getSalesRegister(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_quantity=0;
	total_freight=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.association_id=$('#association_id').val();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();
	
	$.ajax({
		url: '/transport/factory/dipatch/report/bill/get',
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
					dataSe.push('<input type="checkbox" class="flat" name="sport" value='+factory.bill_id+'>');
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
			
			fun();
			$('#submit_button').css('display','block');

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {
	 $("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></tfoot>');

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
			$( api.column( 6 ).footer() ).html('');
			$( api.column( 7 ).footer() ).html('');
			$( api.column( 9 ).footer() ).html('');

			// Total over all pages
			qty = api
			.column( 7 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amount = api
			.column( 9 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Total over this page
			qtyTotal = api
			.column( 7, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			amountTotal = api
			.column( 9, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			$( api.column( 6 ).footer() ).html(
					'Qty: '+(qty).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ', Amt: '+(amount).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
			);
			$( api.column( 7 ).footer() ).html(
					(qtyTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") 
			);
			$( api.column( 9 ).footer() ).html(
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
        	{ title: " " },
			{ title: "S.No" },
			{ title: "Date", "sType": "date-uk"  },
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
	        t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	            t.cell(cell).invalidate('dom');
	        } );
	    } ).draw();
	 
//	 $("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>'+(total_quantity).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th></th><th>'+(total_freight).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tr></tfoot>');

 //   $('#ledger_name').html(($('#association_list :selected').text()).toUpperCase());

};


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

