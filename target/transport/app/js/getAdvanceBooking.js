var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;

var advanceBookingDetails;
var pk_advance_outward_id;
var delete_advance_booking_id;

function getAdvanceBooking(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();

	$.ajax({
		url: '/transport/godown/advanvebooking/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
		   advanceBookingDetails = response.getAdvanceBookingDetails;
			if(advanceBookingDetails!=null){
				for(var i=0; i<advanceBookingDetails.length; i++){
					advanceBooking = advanceBookingDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(advanceBooking.invoice_number);
					dataSe.push(advanceBooking.date_of_outward);
					dataSe.push(advanceBooking.quantity);
					dataSe.push(advanceBooking.truck_number);
					dataSe.push(advanceBooking.transporter);
					dataSe.push(advanceBooking.hamali_per_bags);
					dataSe.push(advanceBooking.action_name);
					dataSet.push(dataSe);

				}

				fun();
			}else{
				alert(response.message);
				$(".overlay").hide();
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
		"bSort" : false,
		//   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
		          'excel','pdf','print'
		          ],
		          columns: [
		                    { title: "S.No" },
		                    { title: "Invoice number" },
		                    { title: "Outward Date" },
		                    { title: "Quantity" },
		                    { title: "Truck Number" },
		                    { title: "Transpoter" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
		                    { title: "Hamali Per Bag"},
		                    { title: "Action"}
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
