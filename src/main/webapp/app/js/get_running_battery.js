function currentInventory() {
					
					$.ajax({
						url: '/transport/spareparts/battery/running/get',
						type: 'GET',
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var part = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var part = "";
			            	var currentInventoryList = response;
			            	for(var i=0; i<currentInventoryList.runningBattery.length; i++){
			            		part = currentInventoryList.runningBattery[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.date + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.brand_name + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.battery_number + "</td>"+		            						
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.truck_number + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Battery Number" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#currentInventory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}