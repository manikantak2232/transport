	function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number);
	}
}
function addOption2(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function getAllTrucks(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/alltrucks/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}


	$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					requestData.tyre_category=$('#tyre_category').val();
					requestData.truck_number=$('#truck_list').val();
					
					$.ajax({
						url: '/transport/tyre/issued/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var tyre = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var tyre = "";
			            	var issuedTyreList = response;
			            	for(var i=0; i<issuedTyreList.issued_tyres.length; i++){
			    //        		var tyre_life = tyre.returned_tyre_reading - tyre.issued_tyre_reading;
			            		tyre = issuedTyreList.issued_tyres[i];		            		
				            	table_rows = table_rows + 
				            					"<tr>"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.issued_tyre_date + "</td>"+		            						
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+
				            						"<td width=7% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            						"<td width=8%  style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_type + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.truck_number + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.issued_tyre_reading + "</td>"+
				            						"<td width=8%  style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.returned_tyre_reading + "</td>"+	
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.returned_tyre_date + "</td>"+	
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.name + "</td>"+	
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + (tyre.returned_tyre_reading - tyre.issued_tyre_reading) + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr >"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Issued Date" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Tyre Number" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Tyre Type" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Issued Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Taken Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Taken Date" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Tyre Life(Km)" + "</td>"+
											"</tr>";
						
			            	table = "<table align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#issuedTyre').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});