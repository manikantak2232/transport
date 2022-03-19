function inventoryHistory() {
	var requestData = new Object();
	requestData.lower_date= $('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();	
					
					$.ajax({
						url: '/transport/tyre/inventory/history/get',
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
			            	var inventoryHistoryList = response;
			            	for(var i=0; i<inventoryHistoryList.inventoryHistory.length; i++){
			            		tyre = inventoryHistoryList.inventoryHistory[i];		            		
				            	table_rows = table_rows + 
				            					"<tr>"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.invoice_number + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.invoice_date + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+		            						
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            						"<td width=8%  style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_type + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Number" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Tyre Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Tyre Type" + "</td>"+
											"</tr>";
						
			            	table = "<table align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#inventoryHistory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}