var hamaliList;
var hamaliDetails;
var totalHamali;
var grandTotal;
var dataSet=[];
var dataSe=[];
var lowerDate;
var upperDate;
var associationName;
var unloadLocationName;
var hamaliType;
function gethHamaliAccount(){ 
	grandTotal = 0;
	dataSet=[];
	dataSe=[];
	document.getElementById("example").deleteTFoot();
// alert("byeee");
	$(".overlay").show();
	lowerDate=$('#lowerDate').val();
	upperDate=$('#upperDate').val();
	associationName=$('#associationName').val();
	unloadLocationName=$('#unloadLocationName').val();
	hamaliType=$('#hamaliType').val();
	
	if(lowerDate==undefined | lowerDate==''){ 
		alert("Please Select Lower Data..");
		return false;
	}
	if(upperDate==undefined | upperDate==''){ 
		alert("Please Select Upper Date..");
		return false;
	}
	if(associationName==undefined | associationName=='Select'){ 
		alert("Please Select Association Company..");
		return false;
	}
	if(unloadLocationName==undefined | unloadLocationName=='Select'){ 
		alert("Please Select Unload Location..");
		return false;
	}
	if(hamaliType==undefined | hamaliType=='Select'){ 
		alert("Please Select hamali Type..");
		return false;
	}
	var requestData = new Object();
	requestData.lowerDate=$('#lowerDate').val();
	requestData.upperDate=$('#upperDate').val();
	requestData.associationName=$('#associationName').val();
	requestData.unloadLocationName=$('#unloadLocationName').val();
	requestData.hamaliType=$('#hamaliType').val();

	$.ajax({
		url: '/transport/godown/hamali/account/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			hamaliList = response.Hamali;
		//	alert(hamaliList);
			if(hamaliList!=null){
				for(var i=0; i<hamaliList.length; i++){
					hamaliDetails = hamaliList[i];
					totalHamali = hamaliDetails.total;
				//	alert(totalHamali);
					
					grandTotal = +grandTotal + +totalHamali;  
				//	alert(grandTotal);
					
					dataSe=[];
					dataSe.push('');
					dataSe.push(hamaliDetails.invoice_number);
					dataSe.push(hamaliDetails.truck_number);
					dataSe.push(hamaliDetails.actionlist);
					dataSe.push(hamaliDetails.quantity);
					dataSe.push(hamaliDetails.crossing1);
					dataSe.push(hamaliDetails.hamali_per_bag);
					dataSe.push(hamaliDetails.total);
					
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
		                    { title: "Invoice Number" },
		                    { title: "Truck Number" },
		                    { title: "Action List" },
		                    { title: "Quantity" },
		                    { title: "Crossing Quantity" },
		                    { title: "hamali Per Bag" },
		                    { title: "Total Hamali" },
		                   
		                    ],
		                    "order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();
	
	$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>'+(grandTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>')

};