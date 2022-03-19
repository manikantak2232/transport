function currentInventory() {
					
					$.ajax({
						url: '/transport/spareparts/current/inventory/get',
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
			            	for(var i=0; i<currentInventoryList.currentInventory.length; i++){
			            		part = currentInventoryList.currentInventory[i];		            		
				            	table_rows = table_rows + 
				            					"<tr>"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.spare_part_name + "</td>"+		            						
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.spare_part_count + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Spare Part Name" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Count" + "</td>"+
											"</tr>";
						
			            	table = "<table width=70% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#currentInventory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}