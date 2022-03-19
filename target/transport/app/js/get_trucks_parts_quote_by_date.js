$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/trucks/quote/get/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var truck = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var truck = "";
			            	var trucksPartsQuoteList = response;
			            	for(var i=0; i<trucksPartsQuoteList.trucksPartsQuote.length; i++){
			            		truck = trucksPartsQuoteList.trucksPartsQuote[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.quote_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.new_quote_date + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.product_name + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.company_name + "</td>"+	
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.agent + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.number_of_units + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.unit_price + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.total + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.no_of_days_for_delivery + "</td>"+	
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.no_of_hours_for_delivery + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.mode_of_payment + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.type_of_payment + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.valid_for_number_of_days + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.contact_number + "</td>"+	
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.quote_status + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Quote Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Product Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Company Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Agent" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Number Of Units" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unit Price" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "No of Days for Delivery" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "No of Hours for Delivery" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Mode of Payment" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type Of Payment" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Valid for No of Days" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Contact Number" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Quote Status" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	
			            	$('#trucksPartsQuote').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});