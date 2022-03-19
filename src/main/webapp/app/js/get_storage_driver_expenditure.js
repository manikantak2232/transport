$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.fk_driver_id= $('#fk_driver_id').val();
					requestData.date= $('#date').val();				
					$.ajax({
						url: '/transport/storage/expenditure/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var factory= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var factory = "";
			            	var factoriesList = response;
			            	for(var i=0; i<factoriesList.Factories.length; i++){
			            		factory = factoriesList.Factories[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px; display:none'>" + factory.pk_storage_dispatch_id + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.time_of_start + "</td>"+
				            						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'> <input type='button' id='but' onclick='fun("+factory.pk_storage_dispatch_id+")' value='get'></td>"+
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px; display:none'>" +"Storage Dispatch Id" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
												
													            						
											"</tr>";
						

			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#factories').html(table);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });


function fun(storage_dispatch_id){
	
	var fk_driver_id= $('#fk_driver_id').val();
	
	window.open("http://www.svtc.co/app/views/close_storage_driver_expenditure.html?"+
		"storage_dispatch_id="+storage_dispatch_id+"&fk_driver_id="+fk_driver_id);
	
	

}