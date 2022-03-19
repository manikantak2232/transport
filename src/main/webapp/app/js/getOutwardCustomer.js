var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;

var outwardDetails;
var pk_outward_id;
var delete_outward_id;

function editFunction(outward_id){
	pk_outward_id=outward_id;
	var arry=outwardDetails.filter(obj=>obj.pk_outward_id==outward_id);

	$('#invoice_number').val(arry[0].invoice_number);
	$('#customer_name_update').val(arry[0].customer_name);
	$('#invoice_date').val(arry[0].invoice_date);
	$('#quantity').val(arry[0].quantity);
	$('#freightPerBag').val(arry[0].freight_per_bag);
	$('#receivedPayment').val(arry[0].received_payment);
	$('#truck_number').val(arry[0].truck_number);
	$('#transporter').val(arry[0].transporter);
    $('#associationName').val(arry[0].association_name);

	var associationdropdowm=$('#associationName').val();

	document.getElementById("unloadLocationName").options.length = 0;
	var finalLocations='';
	var listOfLocations='';
	var list= unloadlocations.filter(obj=>obj.association_name== associationdropdowm);
	listOfLocations=list[0].locations;

	finalLocations= listOfLocations.split(",");
	addDropDownOptions(finalLocations);
	
	$('#unloadLocationName').val(arry[0].unload_location);
	$('#typeOfCement').val(arry[0].typeofcement);

	$('#actionName').val(arry[0].action_name);
	$('#truck_number_image_url').val(arry[0].truck_number_image_url);
	$('#work_done_image_url').val(arry[0].work_done_image_url);

}

function del(outward_id){
	delete_outward_id=outward_id;
	alert(delete_outward_id);
}

function delFunction(){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_outward_id=delete_outward_id;


	$.ajax({
		url: '/transport/godown/outward/customer/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
					$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getOutwardCustomer();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}

function getOutwardCustomer(){ 
	grandReceivedTotal = 0;
	grandBalanceTotal = 0;
	dataSet=[];
	dataSe=[];
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();
	requestData.customerName=$('#customer_name').val();
	$.ajax({
		url: '/transport/godown/outward/customer/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			outwardDetails = response.outwardDetails;
			if(outwardDetails!=null){
				for(var i=0; i<outwardDetails.length; i++){
					outward = outwardDetails[i];
					
					ReceivedTotal = outward.received_payment;
					BalanceTotal = outward.balance_amount;
					
					grandReceivedTotal = +grandReceivedTotal + +ReceivedTotal;
					grandBalanceTotal = +grandBalanceTotal + +BalanceTotal;
					
					
					dataSe=[];
					dataSe.push('');
					dataSe.push(outward.invoice_number);
					dataSe.push(outward.customer_name);
					dataSe.push(outward.invoice_date);
					dataSe.push(outward.quantity);
					dataSe.push(outward.truck_number);
					dataSe.push(outward.transporter);
					dataSe.push(outward.association_name);
					dataSe.push(outward.typeofcement);
					dataSe.push(outward.unload_location);
					dataSe.push(outward.action_name);
					dataSe.push(outward.freight_per_bag);
				    dataSe.push(outward.received_payment);
				    dataSe.push(outward.balance_amount);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+outward.pk_outward_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+outward.pk_outward_id+"\")'>");
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
			$(".overlay").hide();
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
		"bSort" : false,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
		          'excel','pdf','print'
		          ],
		          columns: [
		                    { title: "S.No" },
		                    { title: "Invoice number" },
		                    { title: "Customer Name" },
		                    { title: "Invoice Date" },
		                    { title: "Quantity" },
		                    { title: "Truck Number" },
		                    { title: "Transpoter" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
		                    { title: "Association Company"},
		                    { title: "Cement Type"},
		                    { title: "Stroage Location"},
		                    { title: "Action"},
		                    { title: "Freight Per Bag"},
		                    { title: "Received Amount"},
		                    { title: "Balance Amount"},
		                    { title: ""},
		                    { title: ""}
		                    ],
		                    "order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();
	$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>'+(grandReceivedTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th>'+(grandBalanceTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>')

};

function updateOutward(){  

	$(".overlay").show();
	var requestData = new Object();

	requestData.pk_outward_id = pk_outward_id;
	requestData.invoice_number= $('#invoice_number').val();
	requestData.customer_name=$('#customer_name_update').val();
	requestData.invoice_date=$('#invoice_date').val();
	requestData.quantity=$('#quantity').val();
	requestData.truck_number=$('#truck_number').val();
	requestData.transporter=$('#transporter').val();
	requestData.association_name=$('#associationName').val();
	requestData.typeofcement=$('#typeOfCement').val();
	requestData.unload_location=$('#unloadLocationName').val();
	requestData.action_name=$('#actionName').val();
	requestData.freight_per_bag = $('#freightPerBag').val();
	requestData.received_payment = $('#receivedPayment').val();

	$.ajax({
		url: '/transport/godown/outward/customer/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getOutwardCustomer();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}

var cus = [];

function listOfCustomers(){
	
		$.ajax({
			url: '/transport/godown/customers/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var customerNames=response.customerNames;
				if(customerNames!=''){
					for (var i = 0; i < customerNames.length; i++) {
						cus.push(customerNames[i].customer_name);
					}
					customers_sug();
				}

			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} 

function customers_sug() {
	var availableTags = cus;
	$("#customer_name").autocomplete({
		source : availableTags
	});
	
	$("#customer_name_update").autocomplete({
		source : availableTags
	});
};



