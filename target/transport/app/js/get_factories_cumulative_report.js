var table;
var table_rows = "";
var rows="";
var factory= "";
var tables = "";
var totalFreight=0;
var totalQuantity=0;

var other_table;
var other_table_rows = "";
var other_rows="";
var otherFactory= "";
var tables = "";
var otherFreight=0;
var otherTotalQuantity=0;
var companyName;

$(document).ready(function (){ 
				$('#get').click(function () {
					
					$('#svtc_table').css('display','none');
					$('#otherTable').css('display','none');
					$('#dataTab').css('display','none');
					$('#outsideDispatch').css('display','none');
					$('#export_button').css("display", 'none');
					table="";
					table_rows = "";
					factory= "";
					tables = "";
					totalFreight=0;
	            	totalQuantity=0;
	            	other_table="";
	            	other_table_rows = "";
	            	otherFactory= "";
	            	otherFreight=0;
	            	otherTotalQuantity=0;
	            	
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();
					requestData.association_id= $('#association_id').val();	
					$.ajax({
						url: '/transport/factory/cumulative/report/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var factory= "";
			            	var tables = ""
			            	var factoriesList = response;
			            			            	
			            	var svtcData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name=='svtc';
		            			});
			           
			            	var otherData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name!='svtc';
		            			});
			            	
			            	for(var i=0; i<svtcData.length; i++){
			            		factory = svtcData[i];
			            		
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

			            		var fr=factory.freight;
			            		totalFreight += fr;
			            		
			            		var quantity=factory.load_quantity;
			            		totalQuantity += quantity;
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.loading_date + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.start_location + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.starting_meter_reading + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.closing_meter_reading + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.load_quantity + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.freight + "</td>"+
				    //        						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.dispatch_status + "</td>"+
				            									            						
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
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalQuantity + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalFreight + "</td>"+
        		//				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
        						
			            		"</tr>";
			            
			            	var table_header = 
			            						"<tr width=80%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Loading Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Starting Meter Reading" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closing Meter Reading" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
						//						"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Dispatch Status" + "</td>"+
												
												            						
											"</tr>";
						
			            	for(var i=0; i<otherData.length; i++){
			            		otherFactory = otherData[i];
			            		
			            		if(otherFactory.invoice_number==null){
			            			otherFactory.invoice_number="";
			            		}
			            		if(otherFactory.start_location==null){
			            			 factory.start_location="";
			            		}
			            		if(otherFactory.unload_location==null){
			            			otherFactory.unload_location="";
			            		}
			            		if(otherFactory.load_quantity==null){
			            			otherFactory.load_quantity="";
			            		}
			            		if(otherFactory.freight==null){
			            			otherFactory.freight="";
			            		}
			            		
			            		var otherFr=otherFactory.freight;
			            		otherFreight += otherFr;
			            		
			            		var otherQuantity=otherFactory.load_quantity;
			            		otherTotalQuantity += otherQuantity;
			            		
				            	other_table_rows = other_table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.driver_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.company_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.invoice_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.loading_date + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.start_location + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.unload_location + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.load_quantity + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.freight + "</td>"+
				            									            						
				            						"</tr>";

			            	}
			            	
			            	
			            	other_rows=other_table_rows;

			            	other_table_rows=other_table_rows+
			            		"<tr width=80% >"+
			            		"<td width=5% style=' border:none;' >"+ '' + "</td>"+
			            		"<td width=7% style=' border:none;'>" + ''+ "</td>"+		
			            		"<td width=7% style=' border:none;'>" + ''+ "</td>"+	
        						"<td width=10% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=10% style='border:none;'>" + '' + "</td>"+	
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherTotalQuantity + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFreight + "</td>"+
        						
			            		"</tr>";
			            
			            	var other_table_header = 
			            						"<tr width=80%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Company Name" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Loading Date" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												
												            						
											"</tr>";
			            	
			            	table = "<table style='align:center;' >" + table_header + table_rows + "</table>";
			            	
			            	other_table = "<table>" + other_table_header + other_table_rows + "</table>";
			            
			            	$('#dataTab').html(table);
			            	$('#outsideDispatch').html(other_table);
			        //    	rows='';
			            	if(rows!=''){ 
			            
			            		$('#dataTab').css('display','block');
			            		$('#svtc_table').css('display','block');
			            		$('#export_button').css('display', 'block');
			            	}
			            	if(rows=='' & other_rows==''){
			            		alert('Sorry, No Data Available');
			            	}
			            	
			            	if(other_rows!=''){			            		
			            		$('#outsideDispatch').css('display','block');
			            		$('#otherTable').css('display','block');
			            		$('#export_button').css('display', 'block');
			            	}
			            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			  });

/*$("#btnExport").click(function(event){
	fnExportHTML("dataTable","#87AFC6","Exported Excel");
});*/





