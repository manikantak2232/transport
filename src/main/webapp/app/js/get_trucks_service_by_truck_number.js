 function addDropDownOptions(trucks){
	  for(var i=0;i<trucks.length;++i){
		  addOption(document.add.truck_list,trucks[i].truck_number,trucks[i].pk_trucks_id);
	  }
	  }
  function addOption(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
	  }
  
  function addOption_list(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/trucks/all/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  var trucks=response.TruckDetails;
        	  addDropDownOptions(trucks);        	  
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
}
  function testChange(){
	  var selectedTruck=$("#truck_list :selected").text();
	  var selectedTruckId=$("#truck_list :selected").val();
	  $("#truck_id").val(selectedTruckId);		  
  }
  
  $(document).ready(function (){ 
				$('#get').click(function () {
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date=$('#upper_date').val();
					requestData.fk_truck_id= $('#truck_id').val();	
					
					$.ajax({
						url: '/transport/trucks/service/get/truck/date',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var service = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var service = "";
			            	var trucksServiceList = response;
			            	for(var i=0; i<trucksServiceList.trucksService.length; i++){
			            		service = trucksServiceList.trucksService[i];		
			            		
			            		
			            		
			            		
			            		
			            		 /* var name= $("#part_id").val();
			        			var name_array=name.add(',');
			        			for(var i=0; i<spare_array.length;i++)
			        				 */
			        				
			        				 

				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					//"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.fk_truck_id+ "</td>"+
					            					//"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.truck_number + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.service_center_name + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.service_start_date + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.service_closed_date + "</td>"+
				            						"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.service_total_cost + "</td>"+	
				            						"<td width=25% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.name + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.service_status+ "</td>"+
				            						//"<td width=20% style='text-align:left; padding-left:3px; padding-right:3px'>" + service.description + "</td>"+
			        				
				            					"</tr>";
			        				
			            	}	
			            	
			       
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	//"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
								            	//"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Id" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Service Center Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closed Date" + "</td>"+
												"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Total Cost" + "</td>"+
												"<td width=25% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Spare Part Name" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Status" + "</td>"+
												//"<td width=20% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Description" + "</td>"+
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	
			            	$('#trucksService').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});