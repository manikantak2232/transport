var billDetailsList;
var billDetails;
var totalHamali;
var grandTotal;
var dataSet=[];
var dataSe=[];
var pk_bill_id;
var delete_bill_id;

function editBillDetails(pk_id){
	pk_bill_id=pk_id;
	var arry=billDetailsList.filter(obj=>obj.pk_id==pk_id);
 //	alert(arry);


	$('#invoiceNumber').val(arry[0].invoiceNumber);
	$('#company').val(arry[0].association_name);
	$('#billDate').val(arry[0].bill_date);
//	alert(arry[0].bill_date);
	$('#grandTotal').val(arry[0].grand_total);
	$('#receivedAmount').val(arry[0].received_amount);
	$('#tdsAmount').val(arry[0].tds_amount);
	$('#receivedDate').val(arry[0].received_date);

	
}


function deleteBill(pk_id){
	delete_bill_id=pk_id;
}

function delFunction(){
//	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_id=delete_bill_id;
//	alert(delete_inward_id);

	$.ajax({
		url: '/transport/godown/bill/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
	//		$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getBillDetails();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}



function getBillDetails(){ 
	grandTotal = 0;
	dataSet=[];
	dataSe=[];
//	document.getElementById("example").deleteTFoot();
// alert("byeee");
	$(".overlay").show();
	var requestData = new Object();
	
	requestData.associationName=$('#associationName').val();



	$.ajax({
		url: '/transport/godown/bill/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			billDetailsList = response.getBillDetails;
		
			if(billDetailsList!=null){
				for(var i=0; i<billDetailsList.length; i++){
					billDetails = billDetailsList[i];
					
					var grand_total=billDetails.grand_total;
					var received_amount=billDetails.received_amount;
					var tds_amount = billDetails.tds_amount;
					
					var balanceAmount = (+grand_total - (+received_amount + +tds_amount)).toFixed(2);
					/*totalHamali = hamaliDetails.total;
					alert(totalHamali);
					
					grandTotal = +grandTotal + +totalHamali;  
					alert(grandTotal);
					*/
					dataSe=[];
					dataSe.push('');
					dataSe.push(billDetails.invoiceNumber);
					dataSe.push(billDetails.association_name);
					dataSe.push(billDetails.bill_date);
					dataSe.push(billDetails.grand_total);
					dataSe.push(billDetails.received_amount);
					dataSe.push(billDetails.tds_amount);
					dataSe.push(billDetails.received_date);
					dataSe.push(balanceAmount);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editBillDetails(\""+billDetails.pk_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return deleteBill(\""+billDetails.pk_id+"\")'>");
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
		"bSort" : false,
		//   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
		          'excel','pdf','print'
		          ],
		          columns: [
		                    { title: "S.No" },
		                    { title: "Invoice Number" },
		                    { title: "Company" },
		                    { title: "Date" },
		                    { title: "Grand Total" },
		                    { title: "Received Amount" },
		                    { title: "TDS" },
		                    { title: "Received Date" },
		                    { title: "Balance Amount" },
		                    { title: ""},
		                    { title: ""},
		                    ],
		                    "order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();
	
//	$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th></th><th></th><th>'+(grandTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>')

};


function updateBill(){  

	$(function () {
		$('#myModal').modal('toggle');
	});

	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_id = pk_bill_id;
	requestData.invoiceNumber= $('#invoiceNumber').val();
	requestData.association_name=$('#company').val();
	requestData.godown=$('#godown').val();
	requestData.date=$('#billDate').val();
	requestData.grand_total=$('#grandTotal').val();
	requestData.received_amount=$('#receivedAmount').val();
	requestData.tds_amount=$('#tdsAmount').val();
	requestData.received_date=$('#receivedDate').val();
	


	$.ajax({
		url: '/transport/godown/bill/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getBillDetails();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}

