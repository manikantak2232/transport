$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/storage/incoming/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var load = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var load = "";
			            	var storageIncomingLoadList = response;
			            	for(var i=0; i<storageIncomingLoadList.StorageIncomingLoad.length; i++){
			            		load = storageIncomingLoadList.StorageIncomingLoad[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.invoice_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.date + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.from_address + "</td>"+
				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + load.to_address + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.brand_name + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.cost_per_bag + "</td>"+
				            						"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + load.number_of_bags + "</td>"+
				            						"<td width=7%  style='text-align:left; padding-left:3px; padding-right:3px'>" + load.total_weight + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.total_value + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.truck_number + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.driver_name + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.time_of_arrival + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.reading_km + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + load.status + "</td>"+	
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "From Address" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "To Address" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost Per bag" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Number Of Bags" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total weight"+ "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total Value" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Time Of Arrival" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Reading Km" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Status" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	
			            	$('#storageIncomingLoad').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});