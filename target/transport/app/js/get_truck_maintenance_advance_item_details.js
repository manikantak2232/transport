var id; 
function querystring(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}
 
				function Item() {
					id=querystring('id');
					var requestData = new Object();
					requestData.truck_maintenance_advance_id= id;			
					$.ajax({
						url: '/transport/spareparts/advance/item/details/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var item= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var item = "";
			            	var itemDetailsList = response;
			            	for(var i=0; i<itemDetailsList.itemDetails.length; i++){
			            		item = itemDetailsList.itemDetails[i];		            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=100% >"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:center; padding-left:3px; padding-right:3px ; display:none;'>" + item.pk_truck_maintenance_advance_id + "</td>"+		            						
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + item.date + "</td>"+
				            						"<td width=15% style='text-align:center; padding-left:3px; padding-right:3px ; display:none;'>" + item.fk_truck_id + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + item.truck_number + "</td>"+	
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + item.item_name + "</td>"+
				            						"<td width=10% contenteditable='true' style='text-align:center; padding-left:3px; padding-right:3px'>" + item.item_cost + "</td>"+	
				            									            						
				            						"</tr>";

			            	}	          
			            
			            	
			            	var table_header = 
			            						"<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px; display:none;'>" +"Id" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px ; display:none;'>" + "Truck id" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Item Name" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Item Cost" + "</td>"+
												
												            						
											"</tr>";
						

			            	table = "<table width=95% align=center >" + table_header + table_rows + "</table>";

			            	$('#ItemDetails').html(table);
			            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				};
		

