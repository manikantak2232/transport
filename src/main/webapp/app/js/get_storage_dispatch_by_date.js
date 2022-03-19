$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/storage/dispatch/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var dispatch = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var dispatch = "";
			            	var storageDispatchList = response;
			            	for(var i=0; i<storageDispatchList.StorageDispatch.length; i++){
			            		dispatch = storageDispatchList.StorageDispatch[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.driver_name + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.invoice_number + "</td>"+
				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.start_location + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.dispatch_date + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.time_of_start + "</td>"+
				            						"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.unload_location + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.estimated_km + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.starting_meter_reading + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.closing_meter_reading + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.checked_kms + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.load_quantity + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.freight + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.advance + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.balance + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.dispatch_status + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + dispatch.expenditure + "</td>"+	
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Start Location" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Dispatch Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Time of Start" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Estimated Km" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Starting Meter Reading" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closing Meter Reading" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Checked Kms" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Advance" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Balance" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Dispatch Status" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Expenditure" + "</td>"+
												
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#storageDispatch').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});