$(document).ready(function (){ 
				$('#btTest').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();				
					$.ajax({
						url: '/transport/location/status/date/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var state= "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var state = "";
			            	var statesList = response;
			            	for(var i=0; i<statesList.States.length; i++){
			            		state = statesList.States[i];		            		
				            	table_rows = table_rows + 
				            	"<tr width=100%>"+
            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+	   
            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" +state.status_of_truck+"</td>"+
        						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" +state.truck_number + "</td>"+
        						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" +state.present_location + "</td>"+
        						"<td width=7%  style='text-align:left; padding-left:3px; padding-right:3px'>" + state.date+ "</td>"+
        						"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Status Of Truck" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Present Location" + "</td>"+
										        "<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"</tr>";
							table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

			            	$('#states').html(table);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });