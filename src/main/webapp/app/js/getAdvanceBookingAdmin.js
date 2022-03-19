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
var loadingCount;
var crossingCount;
var directCount;
var pendingCount;
var advanceCount;

function editFunction(advance_id){
	pk_advance_outward_id=advance_id;
	var arry=advanceBookingDetails.filter(obj=>obj.pk_advance_outward_id==advance_id);
	//	alert(arry);


	$('#invoice_number').val(arry[0].invoice_number);
	$('#date_of_outward').val(arry[0].date_of_outward);
	$('#quantity').val(arry[0].quantity);
	$('#truck_number').val(arry[0].truck_number);
	$('#transporter').val(arry[0].transporter);
	$('#hamali_per_bags').val(arry[0].hamali_per_bags);
    $('#actionName').val(arry[0].action_name);
	


}

function del(advance_id){
	delete_advance_booking_id=advance_id;
//	alert(delete_advance_booking_id);
}

function delFunction(){
//	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_advance_outward_id=delete_advance_booking_id;
	//alert(delete_advance_booking_id);

	$.ajax({
		url: '/transport/godown/advanvebooking/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//		$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getAdvanceBooking();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}

function getAdvanceBooking(){ 
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
					
					var quantityCount = advanceBooking.quantity;
					var actionCount = advanceBooking.action_name;
					
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
  					$('#dataTabAdvance').html(table);
  					$('#tabAdvance').css('display','block');
  					
					
					dataSe=[];
					dataSe.push('');
					dataSe.push(advanceBooking.invoice_number);
					dataSe.push(advanceBooking.date_of_outward);
					dataSe.push(advanceBooking.quantity);
					dataSe.push(advanceBooking.truck_number);
					dataSe.push(advanceBooking.transporter);
					dataSe.push(advanceBooking.hamali_per_bags);
					dataSe.push(advanceBooking.action_name);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+advanceBooking.pk_advance_outward_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+advanceBooking.pk_advance_outward_id+"\")'>");
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

function updateOutward(){  

	$(function () {
		$('#myModal').modal('toggle');
	});

//	$('body').removeClass('modal-open'); 
	$(".overlay").show();
	var requestData = new Object();

	requestData.pk_advance_outward_id = pk_advance_outward_id;
	requestData.invoice_number= $('#invoice_number').val();
	requestData.date_of_outward=$('#date_of_outward').val();
	requestData.quantity=$('#quantity').val();
	requestData.truck_number=$('#truck_number').val();
	requestData.transporter=$('#transporter').val();
	requestData.hamali_per_bags=$('#hamali_per_bags').val();
	requestData.action_name=$('#actionName').val();


	$.ajax({
		url: '/transport/godown/advanvebooking/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getAdvanceBooking();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}

