function getAvailableTrucks(){
					$.ajax({
						url:'/transport/trucks/available/engaged/get',
						type: 'get',
						data: JSON.stringify(),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            
			            	var truck= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var truck = "";
			            	var trucksDetailsList = response;
			            	for(var i=0; i<trucksDetailsList.TrucksDetails.length; i++){
			            		truck = trucksDetailsList.TrucksDetails[i];
				            	table_rows = table_rows +
				            	
				            	"<tr width=100%>"+
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + truck.truck_number + "</td>"+
            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + truck.driver_name + "</td>"+	
         					   //"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + truck.truck_id + "</td>"+ 
            				    "<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + truck.allotment_date + "</td>"+
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" +truck.trucks_status + "</td>"+	
            				
        						
        						
        						"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-center:3px; padding-center:3px'>" +"Sl#" + "</td>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-center:3px'>" + "Truck  Number" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-center:3px'>" + "Driver Name" + "</td>"+
							            	   //"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Id" + "</td>"+ 
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-center:3px'>" + "Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-center:3px; padding-center:3px'>" + "Truck Status" + "</td>"+
								            	
								            	
									
												"</tr>";
							table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#trucksDetails').html(table);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				}