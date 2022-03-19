function getAvailableDrivers(){
					$.ajax({
						url:'/transport/driver/available/engaged/get',
						type: 'get',
						data: JSON.stringify(),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            
			            	var driver= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var driver = "";
			            	var driverDetailsList = response;
			            	for(var i=0; i<driverDetailsList.DriverDetails.length; i++){
			            		driver = driverDetailsList.DriverDetails[i];
				            	table_rows = table_rows +
				            	
				            	"<tr width=100%>"+
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
            					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + driver.driver_name + "</td>"+	
/*             					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + slip.fk_truck_id + "</td>"+ */
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + driver.phone_number + "</td>"+//
            					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" +driver.driver_status + "</td>"+	
            				
        						
        						
        						"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-center:3px; padding-center:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-center:3px'>" + "Driver Name" + "</td>"+
/* 								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Id" + "</td>"+ */
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-center:3px'>" + "Phone  Number" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-center:3px; padding-center:3px'>" + "Driver Status" + "</td>"+
								            	
								            	
									
												"</tr>";
							table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#driverDetails').html(table);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				}
			