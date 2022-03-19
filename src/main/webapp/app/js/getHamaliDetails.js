var hamaliDetailsList;
var hamaliDetails;
var totalHamali;
var grandTotal;
var dataSet=[];
var dataSe=[];
var pk_hamali_id;
var delete_hamali_id;

function editHamaliDetails(pk_id){
	pk_hamali_id=pk_id;
	var arry=hamaliDetailsList.filter(obj=>obj.pk_id==pk_id);
 //	alert(arry);


	$('#associationName1').val(arry[0].association_name);
	$('#startDate').val(arry[0].start_date);	
	$('#endDate').val(arry[0].end_date);
	$('#hamali').val(arry[0].hamali);
 //	alert(arry[0].bill_date);
	$('#fixedExpenses').val(arry[0].fixed_expenses);
	$('#hamaliPsc').val(arry[0].hamali_psc);
	$('#hamaliCon').val(arry[0].hamali_con);
	$('#serviceCharge').val(arry[0].service_charge);
	$('#hamaliTrade').val(arry[0].hamali_trade);

	
}


function deleteHamaliDetails(pk_id){
	delete_hamali_id=pk_id;
}

function delFunction(){
//	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_id=delete_hamali_id;
//	alert(delete_inward_id);

	$.ajax({
		url: '/transport/godown/hamali/details/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
	//		$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully deleted..'){ 
				getHamaliDetails();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});

}



function getHamaliDetails(){ 
	grandTotal = 0;
	dataSet=[];
	dataSe=[];
//	document.getElementById("example").deleteTFoot();
// alert("byeee");
	$(".overlay").show();
	var requestData = new Object();
	
	requestData.associationName=$('#associationName').val();



	$.ajax({
		url: '/transport/godown/hamali/details/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			hamaliDetailsList = response.getHamaliDetails;
		
			if(hamaliDetailsList!=null){
				for(var i=0; i<hamaliDetailsList.length; i++){
					hamaliDetails = hamaliDetailsList[i];
					/*totalHamali = hamaliDetails.total;
					alert(totalHamali);
					
					grandTotal = +grandTotal + +totalHamali;  
					alert(grandTotal);
					*/
					dataSe=[];
					dataSe.push('');
					dataSe.push(hamaliDetails.association_name);
					dataSe.push(hamaliDetails.start_date);
					dataSe.push(hamaliDetails.end_date);
					dataSe.push(hamaliDetails.hamali);
					dataSe.push(hamaliDetails.service_charge);
					dataSe.push(hamaliDetails.fixed_expenses);
					
					dataSe.push(hamaliDetails.hamali_psc);
					dataSe.push(hamaliDetails.hamali_con);
					dataSe.push(hamaliDetails.hamali_trade);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editHamaliDetails(\""+hamaliDetails.pk_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return deleteHamaliDetails(\""+hamaliDetails.pk_id+"\")'>");
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
			$(".overlay").hide();
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
		                    { title: "Association Company" },
		                    { title: "Start Date" },
		                    { title: "End Date" },
		                    { title: "Hamali" },
		                    { title: "Service Charge" },
		                    { title: "Fixed Expenses" },
		                    { title: "Hamali PSC" },
		                    { title: "Hamali CON"},
		                    { title: "Hamali Trade"},
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


function updateHamali(){  

	$(function () {
		$('#myModal').modal('toggle');
	});

	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_id = pk_hamali_id;
	requestData.association_name= $('#associationName1').val();
	requestData.start_date=$('#startDate').val();
	requestData.end_date=$('#endDate').val();
	requestData.hamali=$('#hamali').val();
	requestData.fixed_expenses=$('#fixedExpenses').val();
	requestData.hamali_psc=$('#hamaliPsc').val();
	requestData.hamali_con=$('#hamaliCon').val();
	requestData.service_charge=$('#serviceCharge').val();
	requestData.hamali_trade=$('#hamaliTrade').val();

	$.ajax({
		url: '/transport/godown/hamali/details/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Successfully updated..'){
				getHamaliDetails();
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}
	});
}

