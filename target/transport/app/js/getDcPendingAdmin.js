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
var loadingCount;
var crossingCount;
var directCount;
var pendingCount;
var advanceCount;

function editFunction(outward_id){
	pk_outward_id=outward_id;
	var arry=outwardDetails.filter(obj=>obj.pk_outward_hamali_id==outward_id);
	
	$('#customer_name').val(arry[0].customer_name);
	$('#invoice_date').val(arry[0].invoice_date);
	$('#quantity').val(arry[0].quantity);
	
	$('#truck_number').val(arry[0].truck_number);
	$('#transporter').val(arry[0].transporter);
	$('#hamali_per_bags').val(arry[0].hamali_per_bags);
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
	


}

function del(outward_id){
	delete_outward_id=outward_id;
	alert(delete_outward_id);
}

function delFunction(){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_outward_hamali_id=delete_outward_id;
	

	$.ajax({
		url: '/transport/godown/dcpending/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
					$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getDcPending();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});

}

function getDcPending(){ 
	dataSet=[];
	dataSe=[];
	loadingCount = 0;
	  crossingCount = 0;
	  directCount =0;
	  pendingCount=0;
	  advanceCount=0;

	$(".overlay").show();
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();

	$.ajax({
		url: '/transport/godown/dcpending/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			outwardDetails = response.getDcPending;
			if(outwardDetails!=null){
				for(var i=0; i<outwardDetails.length; i++){
					outward = outwardDetails[i];
					
					var quantityCount = outward.quantity;
					var actionCount = outward.action_name;
					
					if(actionCount == "loading"){
						
						 loadingCount = +loadingCount + +quantityCount;
						
					}
					
                    if(actionCount == "crossing-Outward"){
                    	
                    	 crossingCount = +crossingCount + +quantityCount;
						
					}
                    
                    if(actionCount == "direct-Outward"){
                    	
                    	directCount = +directCount + +quantityCount;
						
					}
                    
                    if(actionCount == "dc pending"){
                    	
                    	pendingCount = +pendingCount + +quantityCount;
						
					}
                    
                      if(actionCount == "advance booking"){
                    	
                    	advanceCount = +advanceCount + +quantityCount;
						
					}
                      
                      header_rows = 

  						"<tr class='table'>"+
  						"<td colspan='7' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'Actual total tons according to actions' + "</td>"+
  						
  						"</tr>"
  						;
  					
  					loading_rows = 

  						"<tr class='table'>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 1 + "</td>"+
  						"<td colspan='4' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Loading Quantity(Tons) ' + "</td>"+		            						
  						"<td colspan='2' font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + loadingCount +  "</td>"+	
  		

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
  						
  						pending_rows = 

  							"<tr class='table'>"+
  							"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 4 + "</td>"+
  							"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total DC Pending Quantity(Tons) ' + "</td>"+		            						
  							"<td colspan='2' font-weight:bold; style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + pendingCount +  "</td>"+	
  			

  							"</tr>"
  							
  							advanceBooking_rows = 

  	  							"<tr class='table'>"+
  	  							"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 5 + "</td>"+
  	  							"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Advance Booking Quantity(Tons) ' + "</td>"+		            						
  	  							"<td colspan='2' font-weight:bold; style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + advanceCount +  "</td>"+	
  	  			

  	  							"</tr>"
  						;
  					
  					table= header_rows+loading_rows+crossing_rows+direct_rows+pending_rows+advanceBooking_rows ;
  					$('#dataTabOutwardHamali').html(table);
  					$('#tabOutwardHamali').css('display','block');
					
					dataSe=[];
					dataSe.push('');
					
					dataSe.push(outward.customer_name);
					dataSe.push(outward.invoice_date);
				
					dataSe.push(outward.quantity);
					dataSe.push(outward.truck_number);
					dataSe.push(outward.transporter);
					dataSe.push(outward.association_name);
					dataSe.push(outward.typeofcement);
					dataSe.push(outward.unload_location);
					dataSe.push(outward.hamali_per_bags);
					dataSe.push(outward.action_name);
					
				
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+outward.pk_outward_hamali_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+outward.pk_outward_hamali_id+"\")'>");
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
		                  
		                    { title: "Customer Name" },
		                    { title: "Invoice Date" },
		                   
		                    { title: "Quantity" },
		                    { title: "Truck Number" },
		                    { title: "Transpoter" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
		                    { title: "Association Company"},
		                    { title: "Cement Type"},
		                    { title: "Stroage Location"},
		                    { title: "Hamali Per Bag"},
		                    { title: "Action"},
		                   
		                   
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

function updateDcPending(){ 
	$(".overlay").show();
	var requestData = new Object();

	requestData.pk_outward_hamali_id = pk_outward_id;

	requestData.customer_name=$('#customer_name').val();
	requestData.invoice_date=$('#invoice_date').val();
	requestData.quantity=$('#quantity').val();
	requestData.truck_number=$('#truck_number').val();
	requestData.transporter=$('#transporter').val();
	requestData.association_name=$('#associationName').val();
	requestData.typeofcement=$('#typeOfCement').val();
	requestData.unload_location=$('#unloadLocationName').val();
	requestData.hamali_per_bags=$('#hamali_per_bags').val();
	requestData.action_name=$('#actionName').val();
	
	$.ajax({
		url: '/transport/godown/dcpending/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getDcPending();
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
};
