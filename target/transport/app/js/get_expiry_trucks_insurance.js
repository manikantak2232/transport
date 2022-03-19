$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.date_type= $('#date_type').val();
					
					$.ajax({
						url: '/transport/trucks/expiry/insurance/get',
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
			            	var trucksInsuranceList = response;
			            	for(var i=0; i<trucksInsuranceList.trucksInsurance.length; i++){
			            		truck = trucksInsuranceList.trucksInsurance[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.truck_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.insurance_policy_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.insurance_company_name + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + truck.insurance_expiry_date + "</td>"+
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Insurance Policy Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Insurance Company Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Insurance Expiry Date" + "</td>"+

											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	
			            	$('#trucksInsurance').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});