function currentInventory() {
					
					$.ajax({
						url: '/transport/tyre/inventory/current/get',
						type: 'GET',
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var tyre = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var tyre = "";
			            	var currentInventoryList = response;
			            	for(var i=0; i<currentInventoryList.currentInventory.length; i++){
			            		tyre = currentInventoryList.currentInventory[i];		            		
				            	table_rows = table_rows + 
				            					"<tr >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+		            						
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.tyre_type + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr >"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Tyre Number" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Tyre Type" + "</td>"+
											"</tr>";
						
			            	table = "<table align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#currentInventory').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
}