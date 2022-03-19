var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;
var dataSet=[];
var dataSe=[];

function getExpenditure(){
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();	
	requestData.fk_truck_id= $('#truck_id').val();

	$.ajax({
		url: '/transport/trucks/maintenance_expenditure/get/date',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			paymentDetails = response.maintenance;
			if(paymentDetails!=null){
				for(var i=0; i<paymentDetails.length; i++){
					payment = paymentDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(payment.date);
					dataSe.push(payment.truck_number);
					dataSe.push(payment.item_names);
					dataSe.push(payment.cost);
					dataSe.push(payment.expenditure_type);
					dataSe.push(payment.remarks);
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
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Truck Number" },
				{ title: "Item Names" },
				{ title: "Cost" },
				{ title: "Expenditure Type" },
				{ title: "Remarks"}
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

