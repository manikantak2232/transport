$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/trucks/allotment/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var trucks = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var trucks = "";
			            	var trucksAllotmentList = response;
			            	for(var i=0; i<trucksAllotmentList.trucksAllotment.length; i++){
			            		trucks = trucksAllotmentList.trucksAllotment[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + trucks.driver_name + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trucks.truck_allotment_date + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + trucks.allotment_location + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + trucks.truck_number + "</td>"+	
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Allotment Location" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#trucksAllotment').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});