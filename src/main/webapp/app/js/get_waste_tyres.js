
	$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/tyre/waste/get/date',
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
			            	var wasteTyreList = response;
			            	for(var i=0; i<wasteTyreList.waste_tyres.length; i++){
			            		tyre = wasteTyreList.waste_tyres[i];		            		
				            	table_rows = table_rows + 
				            					"<tr >"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.date + "</td>"+		            						
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+
				            						"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            									            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr >"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Tyre Number" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
											
											"</tr>";
						
			            	table = "<table width=70% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#wasteTyre').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});