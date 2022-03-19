$(document).ready(function (){ 
				$('#btTest').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();				
					$.ajax({
						url: '/transport/factory/invoice/date/get',
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
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.customer_name + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.name_of_brand + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.sales_manager_name + "</td>"+	
				            					    "<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_date + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.phone_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.email + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.from_address+ "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.to_address + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.type_of_cement + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.number_of_bags + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.total_weight + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.cost_per_bag + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.freight_rate + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.total_value + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.approve_status + "</td>"+
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Invoice Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Customer Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Name Of Brand" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Sales Manager Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Phone Number" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Email" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "From Address" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "To Address" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type Of Cement" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Number Of Bags" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total Weight" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost Per Bag " + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight Rate" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total Value" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Approve Status" + "</td>"+		            						
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