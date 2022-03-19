var table = "";
var table_rows = "";
var factory= "";
var tables = "";
$(document).ready(function (){ 
				$('#get').click(function () {
					
					$('#dataTab').css('display','none');
					$('#export_button').css("display", 'none');
					table="";
					table_rows = "";
					factory= "";
					tables = "";
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();
					requestData.fk_truck_id= $('#truck_id').val();	
					$.ajax({
						url: '/transport/factory/dispatch/fuel/date/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var factory= "";
			            	var tables = "";
			            	var factoriesList = response;
			            	for(var i=0; i<factoriesList.Factories.length; i++){
			            		factory = factoriesList.Factories[i];
			            		
			            		if(factory.closing_meter_reading==null){
			            			factory.closing_meter_reading="";
			            		}
			            		if(factory.invoice_number==null){
			            			factory.invoice_number="";
			            		}
			            		if(factory.start_location==null){
			            			 factory.start_location="";
			            		}
			            		if(factory.unload_location==null){
			            			factory.unload_location="";
			            		}
			            		if(factory.starting_meter_reading==null){
			            			factory.starting_meter_reading="";
			            		}
			            		if(factory.load_quantity==null){
			            			factory.load_quantity="";
			            		}
			            		if(factory.freight==null){
			            			factory.freight="";
			            		}
			            		if(factory.dispatch_status=='waiting in factory office for loading' |
			            			factory.dispatch_status=='waiting in branch office for next assignment'	)
			            		{
			            			factory.dispatch_status="Closed";
			            		}
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=100% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.loading_date + "</td>"+	
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.start_location + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.load_quantity + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.freight + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.liters + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.rate + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (factory.liters*factory.rate).toFixed(2) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '      ' + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '      ' + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.starting_meter_reading + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.closing_meter_reading + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (factory.closing_meter_reading-factory.starting_meter_reading )+ "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '      ' + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '      ' + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (factory.mileage).toFixed(2) + "</td>"+
				            									            						
				            						"</tr>";

			            	}	  
			            	
			            	rows=table_rows;

			            	table_rows=table_rows+
			            		"<tr width=80% >"+
			            		"<td width=5% style=' border:none;' >"+ '' + "</td>"+
			            		"<td width=7% style=' border:none;'>" + ''+ "</td>"+		            						
        						"<td width=10% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=10% style='border:none;'>" + '' + "</td>"+	
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
        						
			            		"</tr>";
			            
			            	
			            	var table_header = 
			            						"<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Loading Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Liters" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Rate" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Amount" + "</td>"+
												"<td width=13%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Trip Exp" + "</td>"+
												"<td width=13%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Advance" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "St. Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cl. Reading" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Kms" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Checked Kms" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Diff Kms" + "</td>"+
												"<td width=15%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Mileage" + "</td>"+
												
												            						
											"</tr>";
						

			            	table = "<table width=95% align=center >" + table_header + table_rows + "</table>";
			            
			            	$('#dataTab').html(table);
			            	
			            	if(table_rows!=''){
			            		
			            		$('#dataTab').css('display','block');
			            		$('#export_button').css("display", 'block');
			            	}
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });


