var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;

var inwardDetails;
var pk_inward_id;
var delete_inward_id;

function editFunction(inward_id){
	pk_inward_id=inward_id;
	var arry=inwardDetails.filter(obj=>obj.id==inward_id);

	$('#invoiceNumber').val(arry[0].invoice_number);
	$('#invoiceDate').val(arry[0].invoice_date);
	$('#inwordDate').val(arry[0].inward_date);
	$('#quantity').val(arry[0].quantity);
	$('#typeOfCement').val(arry[0].fk_type_of_cement_name);
	$('#truckNumber').val(arry[0].truck_number);
	$('#transporter').val(arry[0].transporter);
	$('#hamaliBag').val(arry[0].hamali_per_bag);
	$('#associationName').val(arry[0].fk_association_name);
	$('#crossing_quantity').val(arry[0].crossing_quantity);
	$('#direct_quantity').val(arry[0].direct_quantity);
	var associationdropdowm=$('#associationName').val();
	document.getElementById("unloadLocationName").options.length = 0;
	var finalLocations='';
	var listOfLocations='';
	var list= unloadlocations.filter(obj=>obj.association_name== associationdropdowm);
	listOfLocations=list[0].locations;
	finalLocations= listOfLocations.split(",");
	addDropDownOptions(finalLocations);

	$('#unloadLocationName').val(arry[0].fk_unload_location_name);
	$('#actionName').val(arry[0].fk_action_name);

}

function getInward(){ 
	dataSet=[];
	dataSe=[];
//	alert("byeee");
	$(".overlay").show();
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();

	$.ajax({
		url: '/transport/godown/inward/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			inwardDetails = response.inwardDetails;
			if((inwardDetails!=null) & (inwardDetails.length!=0)){
				for(var i=0; i<inwardDetails.length; i++){
					inward = inwardDetails[i];
					
				
				
					
					dataSe=[];
					dataSe.push('');
					dataSe.push(inward.invoice_number);
					dataSe.push(inward.invoice_date);
					dataSe.push(inward.inward_date);
					dataSe.push(inward.quantity);
					dataSe.push(inward.fk_type_of_cement_name);
					dataSe.push(inward.truck_number);
					dataSe.push(inward.transporter);
					dataSe.push(inward.hamali_per_bag);
					dataSe.push(inward.fk_association_name);
					dataSe.push(inward.fk_unload_location_name);
					dataSe.push(inward.fk_action_name);
					dataSe.push(inward.crossing_quantity);
					dataSe.push(inward.direct_quantity);
					if(inward.fk_action_name=='pre-booking'){
						dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+inward.id+"\")'>");
						
					}else{
						dataSe.push('');
					}
					dataSet.push(dataSe);

				}

				fun();
			}else{
				
				alert('No data Available..');
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
			columns: [
				{ title: "S.No" },
				{ title: "Invoice number" },
				{ title: "Invoice Date" },
				{ title: "Inward Date" },
				{ title: "Quantity" },
				{ title: "Cement Type"},
				{ title: "Truck Number" },
				{ title: "Transpoter" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "Hamali Per Bag"},
				{ title: "Association Company"},
				{ title: "Stroage Location"},
				{ title: "Action"},
				{ title: "Crossing Quantity"},
				{ title: "Direct Quantity"},
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

};

function updateInward(){

	$(function () {
		$('#myModal').modal('toggle');
	});
	$(".overlay").show();

	var requestData = new Object();
	requestData.id = pk_inward_id;
	requestData.invoice_number= $('#invoiceNumber').val();
	requestData.invoice_date=$('#invoiceDate').val();
	requestData.inward_date=$('#inwordDate').val();
	requestData.quantity=$('#quantity').val();
	requestData.fk_type_of_cement_name=$('#typeOfCement').val();
	requestData.truck_number=$('#truckNumber').val();
	requestData.transporter=$('#transporter').val();
	requestData.hamali_per_bag=$('#hamaliBag').val();
	requestData.fk_association_name=$('#associationName').val();
	requestData.fk_unload_location_name=$('#unloadLocationName').val();
	requestData.fk_action_name=$('#actionName').val();
	requestData.crossing_quantity=$('#crossing_quantity').val();
	requestData.direct_quantity=$('#direct_quantity').val();


	$.ajax({
		url: '/transport/godown/inward/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getInward();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}


