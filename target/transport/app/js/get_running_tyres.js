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
					requestData.truck_number=$('#truck_list').val();
					
					$.ajax({
						url: '/transport/tyre/running/get/date',
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
			            	var runningTyreList = response;
			            	for(var i=0; i<runningTyreList.running_tyres.length; i++){
			            		tyre = runningTyreList.running_tyres[i];	
			            		
			            		if(tyre.date==null){
			            			tyre.date='';
			            		}
			            		
				            	table_rows = table_rows + 
				            					"<tr>"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+	            						
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.date + "</td>"+
				            						"<td width=10%  style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.truck_number + "</td>"+				
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Tyre Number" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
											"</tr>";
						
			            	table = "<table width=80% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#runningTyre').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});