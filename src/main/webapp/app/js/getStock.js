//var hamaliList;
//var hamaliDetails;
//var totalHamali;
var grandTotal;
//var dataSet=[];
//var dataSe=[];
//var lowerDate;
//var upperDate;
var associationName;
var unloadLocationName;
var stockType;
var typeOfCement;
var unloadingValue="";
var crosssingInwardValue = "";
 var directInwardValue ="";
 var prebookingValue = "";
var loadingValue="";
var crossingOutwardValue="";
var directOutwardValue = "";
var  dcPendingValue ="";
var advanceValue ="";
var inward = "";
var outward = "";
function getStock(){ 
	grandTotal = 0;
	 unloadingValue=0;
	 crosssingInwardValue = 0;
	  directInwardValue =0;
	  prebookingValue = 0;
	 loadingValue=0;
	 crossingOutwardValue=0;
	 directOutwardValue = 0;
	  dcPendingValue =0;
	 advanceValue =0;
	 inward =0;
	 outward =0;
//	dataSet=[];
//	dataSe=[];
//	document.getElementById("example").deleteTFoot();
// alert("byeee");
//	$(".overlay").show();

	associationName=$('#associationName').val();
	unloadLocationName=$('#unloadLocationName').val();
	stockType=$('#stockType').val();
	typeOfCement =$('#typeOfCement').val();
	
	
	if(associationName==undefined | associationName=='Select'){ 
		alert("Please Select Association Company..");
		return false;
	}
	if(unloadLocationName==undefined | unloadLocationName=='Select'){ 
		alert("Please Select Unload Location..");
		return false;
	}
	if(stockType==undefined | stockType=='Select'){ 
		alert("Please Select stock Type..");
		return false;
	}
	if(typeOfCement==null | typeOfCement==' '){ 
		alert("Please Select typeOfCement Type..");
		return false;
	}
	var requestData = new Object();

	requestData.associationName=$('#associationName').val();
//	alert($('#associationName').val());
	requestData.unloadLocationName=$('#unloadLocationName').val();
//	alert($('#unloadLocationName').val());
	requestData.stockType=$('#stockType').val();
//	alert($('#hamaliType').val());
	requestData.typeOfCement=$('#typeOfCement').val();

	$.ajax({
		url: '/transport/godown/stock/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			
			var stockDetails = response.stock;
			
			if(stockDetails != null){
				for(i=0;i<stockDetails.length;i++){
					
				var	stock = stockDetails[i];
				
				var Qua = stock.quantity;
				var action = stock.actionlist;
				var cement = stock.typeCement;
					
				if(action == "unloading"){
					unloadingValue = +unloadingValue + +Qua;
				}
				if(action == "crossing-Inward"){
					crosssingInwardValue = +crosssingInwardValue + +Qua;
				}
				if(action == "direct-Inward"){
					directInwardValue = +directInwardValue + +Qua;
				}
				if(action == "pre-booking"){
					prebookingValue = +prebookingValue + +Qua;
				}
				
				if(action == "loading"){
					loadingValue = +loadingValue + +Qua;
				}
				if(action == "crossing-Outward"){
					crossingOutwardValue = +crossingOutwardValue + +Qua;
				}
				if(action == "direct-Outward"){
					directOutwardValue = +directOutwardValue + +Qua;
				}
				if(action == "dc pending"){
				   dcPendingValue =  +dcPendingValue + +Qua;	
				}
				if(action == "advance booking"){
					advanceValue = +advanceValue + +Qua;
				}
				
				}
				
				if( stockType == "Physical Stock"){
				 grandTotal =  ((+unloadingValue) - (+loadingValue));
				 console.log(unloadingValue);
				 console.log(loadingValue);
					console.log(grandTotal);
					
					header_rows = 

						"<tr class='table'>"+
						"<td colspan='7' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'Physical Stock' + "</td>"+
						
						"</tr>"
						;
					
					unloading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 1 + "</td>"+
						"<td colspan='4' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Unloaded (Tons) ' + "</td>"+		            						
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + unloadingValue +  "</td>"+	
		

						"</tr>"
						;
					loading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 2 + "</td>"+
						"<td colspan='4' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'loaded (Tons)' + "</td>"+		            						
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + loadingValue +  "</td>"+	
		

						"</tr>"
						;
					grandTotal_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total Physical Stock ' + "</td>"+		            						
						"<td colspan='2' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + grandTotal +  "</td>"+	
		

						"</tr>"
						;
					
					table= header_rows+unloading_rows+loading_rows+grandTotal_rows ;
					$('#dataTab').html(table);
					$('#tabl1').css('display','block');
					$('#export_zuari').css('display','block');
				}else{
					
					inward = (+unloadingValue + +crosssingInwardValue + +directInwardValue + +prebookingValue);
					outward = (+advanceValue + +dcPendingValue + +directOutwardValue + +crossingOutwardValue + +loadingValue);
					grandTotal = ((+inward) - (+outward));
					console.log("inward",inward);
					console.log("outward",outward);
					console.log("grandTotal",grandTotal);
					
					header_rows = 

						"<tr class='table'>"+
						"<td colspan='7' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'System Stock' + "</td>"+
						
						"</tr>"
						;
					
					unloading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 1 + "</td>"+
						"<td colspan='4' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Unloaded (Tons) ' + "</td>"+		            						
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + inward +  "</td>"+	
		

						"</tr>"
						;
					loading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 2 + "</td>"+
						"<td colspan='4' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'loaded (Tons) ' + "</td>"+		            						
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + outward +  "</td>"+	
		

						"</tr>"
						;
					grandTotal_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='4'font-weight:bold; style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Total System Stock ' + "</td>"+		            						
						"<td colspan='2' style='font-weight:bold;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + grandTotal +  "</td>"+	
		

						"</tr>"
						;
					
					table= header_rows+unloading_rows+loading_rows+grandTotal_rows ;
					$('#dataTab').html(table);
					$('#tabl1').css('display','block');
					$('#export_zuari').css('display','block');
					
				}
			}

		//	$(".overlay").hide();
		//	hamaliList = response.Hamali;
		//	alert(hamaliList);
			/*if(hamaliList!=null){
				for(var i=0; i<hamaliList.length; i++){
					hamaliDetails = hamaliList[i];
					totalHamali = hamaliDetails.total;*/
				//	alert(totalHamali);
					
				//	grandTotal = +grandTotal + +totalHamali;  
				//	alert(grandTotal);
					
		/*			dataSe=[];
					dataSe.push('');
					dataSe.push(hamaliDetails.truck_number);
					dataSe.push(hamaliDetails.actionlist);
					dataSe.push(hamaliDetails.quantity);
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
			console.log(error);
			alert('Something Wrong.. Try again..!');
			var table = $('#example').DataTable(); 
			table.clear().draw();*/

		}
	});
}

/*function fun() {
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
		                    { title: "Truck Number" },
		                    { title: "Action List" },
		                    { title: "Quantity" },
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
	
	$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th></th><th>'+(grandTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>')

};*/