function inventoryHistory() {
	var requestData = new Object();
	requestData.lower_date= $('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();	
					
					$.ajax({
						url: '/transport/spareparts/oil/inventory/history/get',
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
			            	for(var i=0; i<inventoryHistoryList.inventoryHistory.length; i++){
			            		part = inventoryHistoryList.inventoryHistory[i];		            		
				            	table_rows = table_rows + 
				            					"<tr >"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.date + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.invoice_number + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.liters + "</td>"+		            						
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.cost + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.invoice_date + "</td>"+		            						
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.brand_name + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Number" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Liters" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
											"</tr>";
						
			            	table = "<table width=100vw align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#inventoryHistory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}