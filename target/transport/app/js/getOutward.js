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

function getOutward(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();

	$.ajax({
		url: '/transport/godown/outward/get',
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
					
				
  					
					
					dataSe=[];
					dataSe.push('');
					dataSe.push(outward.invoice_number);
					dataSe.push(outward.customer_name);
					dataSe.push(outward.invoice_date);
					dataSe.push(outward.date_of_outward);
					dataSe.push(outward.quantity);
					dataSe.push(outward.truck_number);
					dataSe.push(outward.transporter);
					dataSe.push(outward.association_name);
					dataSe.push(outward.typeofcement);
					dataSe.push(outward.unload_location);
					dataSe.push(outward.hamali_per_bags);
					dataSe.push(outward.action_name);
					dataSe.push(outward.freight_per_bag);
					dataSe.push(outward.distances);
					dataSe.push(outward.received_payment);
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
		//   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
/*		buttons: [
		          'excel','pdf','print'
		          ],*/
		          columns: [
		                    { title: "S.No" },
		                    { title: "Invoice number" },
		                    { title: "Customer Name" },
		                    { title: "Invoice Date" },
		                    { title: "Outward Date" },
		                    { title: "Quantity" },
		                    { title: "Truck Number"},
		                    { title: "Transporter" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
		                    { title: "Association Company"},
		                    { title: "Cement Type"},
		                    { title: "Stroage Location"},
		                    { title: "Hamali Per Bag"},
		                    { title: "Action"},
		                    { title: "Freight Per Bag"},
		                    { title: "Distances(Kms)"},
		                    { title: "Received Amount"}
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


