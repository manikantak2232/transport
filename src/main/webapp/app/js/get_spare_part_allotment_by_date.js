$(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					
					$.ajax({
						url: '/transport/spareparts/allotment/get/date',
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
			            	var sparePartList = response;
			            	for(var i=0; i<sparePartList.sparePartsAllotment.length; i++){
			            		part = sparePartList.sparePartsAllotment[i];		            		
				            	table_rows = table_rows + 
				            					"<tr >"+
					            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.driver_name + "</td>"+		            						
				            						"<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.spare_part_name + "</td>"+
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.truck_number + "</td>"+
/*				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + part.count + "</td>"+*/
				            						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + part.date + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr >"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Name" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Spare Part Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
/*												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Count" + "</td>"+*/
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
											"</tr>";
						
			            	table = "<table align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#sparePartsAllotment').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});