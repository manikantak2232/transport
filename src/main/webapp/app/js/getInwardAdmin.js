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
var unloadingCount;
var crossingCount;
var directCount;
var preCount;

function editFunction(inward_id){
	pk_inward_id=inward_id;
	var arry=inwardDetails.filter(obj=>obj.id==inward_id);
	//	alert(arry);


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
	//var selectedTruck=$("#associationName :selected").text();
	var finalLocations='';
	var listOfLocations='';
//	alert(unloadlocations);
	var list= unloadlocations.filter(obj=>obj.association_name== associationdropdowm);
	listOfLocations=list[0].locations;
	// alert(is);
	// console.log(is);

	finalLocations= listOfLocations.split(",");
	//alert(loc2);
	addDropDownOptions(finalLocations);

	$('#unloadLocationName').val(arry[0].fk_unload_location_name);
	$('#actionName').val(arry[0].fk_action_name);
	/*	$('#truckNumberUrl').val(arry[0].truck_number_image_url);
	$('#workUrl').val(arry[0].work_done_image_url);*/



}

function del(inward_id){
	delete_inward_id=inward_id;
}

function delFunction(){
	$(".overlay").show();
	var requestData = new Object();
	requestData.id=delete_inward_id;


	$.ajax({
		url: '/transport/godown/inward/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getInward();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}

function getInward(){ 
	dataSet=[];
	dataSe=[];
	
	 unloadingCount = 0;
	  crossingCount = 0;
	  directCount =0;
	  preCount=0;
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
			if(inwardDetails!=null){
				for(var i=0; i<inwardDetails.length; i++){
					inward = inwardDetails[i];
					
					var quantityCount = inward.quantity;
					var actionCount = inward.fk_action_name;
					
					if(actionCount == "unloading"){
						
						 unloadingCount = +unloadingCount + +quantityCount;
						
					}
					
                    if(actionCount == "crossing-Inward"){
                    	
                    	 crossingCount = +crossingCount + +quantityCount;
						
					}
                    
                    if(actionCount == "direct-Inward"){
                    	
                    	directCount = +directCount + +quantityCount;
						
					}
                    
                    if(actionCount == "pre-booking"){
                    	
                    	preCount = +preCount + +quantityCount;
						
					}
                    
                    header_rows = 

						"<tr class='table'>"+
						"<td colspan='7' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'Actual total tons according to actions' + "</td>"+
						
						"</tr>"
						;
					
					unloading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 1 + "</td>"+
						"<td colspan='4' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Unloading Quantity(Tons) ' + "</td>"+		            						
						"<td colspan='2' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + unloadingCount +  "</td>"+	
		

						"</tr>"
						;
					crossing_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 2 + "</td>"+
						"<td colspan='4' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Crossing Quantity(Tons) ' + "</td>"+		            						
						"<td colspan='2' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + crossingCount +  "</td>"+	
		

						"</tr>"
						;
					direct_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 3 + "</td>"+
						"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Direct Quantity(Tons) ' + "</td>"+		            						
						"<td colspan='2' font-weight:bold; style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + directCount +  "</td>"+	
		

						"</tr>"
						
						pre_rows = 
1
							"<tr class='table'>"+
							"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 4 + "</td>"+
							"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Pre-Booking Quantity(Tons) ' + "</td>"+		            						
							"<td colspan='2' font-weight:bold; style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + preCount +  "</td>"+	
			

							"</tr>"
						;
					
					table= header_rows+unloading_rows+crossing_rows+direct_rows+pre_rows ;
					$('#dataTabInward').html(table);
					$('#tabInward').css('display','block');
					
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
					/*					dataSe.push(inward.truck_number_image_url);
					dataSe.push(inward.work_done_image_url);*/
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+inward.id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+inward.id+"\")'>");
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
		buttons: [
			'excel','pdf','print'
			],
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
				/*              { title: "Truck Number url"},
		                    { title: "Work url"},*/
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

};

function updateInward(){  

	$(function () {
		$('#myModal').modal('toggle');
	});
	$(".overlay").show();
//	$('body').removeClass('modal-open'); 

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

