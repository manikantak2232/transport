function inventoryHistory() {
	var requestData = new Object();
	requestData.lower_date= $('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();	
					
					$.ajax({
						url: '/transport/spareparts/battery/waste/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var part = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var part = "";
			            	var inventoryHistoryList = response;
			            	for(var i=0; i<inventoryHistoryList.wasteBattery.length; i++){
			            		part = inventoryHistoryList.wasteBattery[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.date + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.brand_name + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + part.battery_number + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Brand Name" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Battery Number" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#inventoryHistory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}