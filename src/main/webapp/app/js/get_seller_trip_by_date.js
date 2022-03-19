$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();				
					$.ajax({
						url: '/transport/trip/seller/date/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var trip= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var trip = "";
			            	var tripsList = response;
			            	for(var i=0; i<tripsList.Trips.length; i++){
			            		trip =tripsList.Trips[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
/* 					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.fk_truck_id + "</td>"+	 */	
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.truck_number + "</td>"+		            			
/* 					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.fk_driver_id + "</td>"+ */		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.driver_name + "</td>"+

				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.start_date + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.start_location + "</td>"+
				            						"<td width=7%  style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.time_of_start + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.destination + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.estimated_km + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.starting_meter_reading + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.closing_meter_reading + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.load_quantity + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.freight + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.checked_kms + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trip.trip_status + "</td>"+
									            	
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
/* 								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Id" + "</td>"+ */
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
/* 								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Id" + "</td>"+ */
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+

												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Starting Time" + "</td>"+
								
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Destination" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Estimated Km" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Meter Starting Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closing Meter Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Checked Kms" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Trip Status" + "</td>"+
												
													            						
											"</tr>";
						

			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#trips').html(table);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });