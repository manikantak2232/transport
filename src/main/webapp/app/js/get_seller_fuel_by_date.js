
			$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					requestData.fk_truck_id=$('#truck_id').val();
					
					$.ajax({
						url: '/transport/seller/fuel/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var fuel = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var fuel = "";
			            	var sellerFuelList = response;
			            	for(var i=0; i<sellerFuelList.SellerFuel.length; i++){
			            		fuel = sellerFuelList.SellerFuel[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + fuel.fuel_quantity + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + fuel.fuel_rate + "</td>"+
				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + fuel.amount + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + fuel.fuel_station_location + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + fuel.date + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Fuel Quantity" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Fuel Rate" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Amount" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Fuel Station Location" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			      
			            	
			            	$('#sellerFuel').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});