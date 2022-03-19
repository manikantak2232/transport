var table1 = "";
var table_rows_bill1 = "";
var factory1= "";
var tables1 = "";
var totalFreight1=0;
var totalQuantity1=0;
var dispatchCount1=0;
var xhr1;

function getDetails() {
	
	if ( xhr1 && xhr1.readyState > 0 && xhr1.readyState < 4 ) {
		xhr1.abort();    
    }
					
					$('#factories').css('display','none');
					$('#view').css("display", 'none');
					$('#btnExport1').css('display','none');
	            	
					table1="";
					table_rows_bill1 = "";
					factory1= "";
					tables1 = "";
					var requestData = new Object();
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();
					requestData.association_id= $('#association_id').val();	
					xhr1=$.ajax({
						url: '/transport/factory/dispatch/bill/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var factory1= "";
			            	var tables1 = ""
			            	var factoriesList = response;
			            	for(var i=0; i<factoriesList.Factories.length; i++){
			            		factory1 = factoriesList.Factories[i];
			            				            		
			            		if(factory1.invoice_number==null){
			            			factory1.invoice_number="";
			            		}
			            		if(factory1.start_location==null){
			            			 factory1.start_location="";
			            		}
			            		if(factory1.unload_location==null){
			            			factory1.unload_location="";
			            		}
			            		
			            		if(factory1.load_quantity==null){
			            			factory1.load_quantity="";
			            		}
			            		if(factory1.freight==null){
			            			factory1.freight="";
			            		}
			            		
			            		var fr=factory1.freight;
			            		totalFreight1 += fr;
			            		
			            		var quantity=factory1.load_quantity;
			            		totalQuantity1 += quantity;
			            		
			            		dispatchCount1 += 1;
			            		
				            	table_rows_bill1 = table_rows_bill1 + 
				            	
				            					"<tr width=100%  style='border: 1px solid black;border-collapse:collapse;'>"+
					            					"<td width=5% style='border: 1px solid black;border-collapse:collapse;'>" + (i+1) + "</td>"+
					            					"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.loading_date + "</td>"+		            						
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.truck_number + "</td>"+
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.unload_location + "</td>"+
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.invoice_number + "</td>"+	
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + '' + "</td>"+
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.load_quantity + "</td>"+	
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + (factory1.freight/factory1.load_quantity) + "</td>"+
				            						"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + factory1.freight + "</td>"+
				            									            						
				            						"</tr>";

			            	}	          
			            
			            	
			            	var table_header1 = 
			            						"<tr width=100% style='background-color:#96989b'>"+
								            	"<td width=5% style='border: 1px solid black;border-collapse:collapse;'>" +"S.NO" + "</td>"+
								            	"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" +"DATE" + "</td>"+
												"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + "VEH. NO" + "</td>"+
												"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + "PLACE" + "</td>"+
												"<td width=5%  style='border: 1px solid black;border-collapse:collapse;'>"  + "INV NO" + "</td>"+
												"<td width=10% style='border: 1px solid black;border-collapse:collapse;'>" + "Shortage Bags" + "</td>"+
												"<td width=8%  style='border: 1px solid black;border-collapse:collapse;'>" + "Qty" + "</td>"+
												"<td width=8%  style='border: 1px solid black;border-collapse:collapse;'>" + "Rate" + "</td>"+
												"<td width=8%  style='border: 1px solid black;border-collapse:collapse;'>" + "Amount" + "</td>"+
												
												            						
											"</tr>";
						

			            	table1 = "<br><table align=center >" + table_header1 + table_rows_bill1 + "</table><br><br>";
			            
			            	$('#factories').html(table1);
			           
			            	
			            	if(table_rows_bill1!=''){
			            		$('#view').css("display", 'block');
			            		
			            	}
//			            	alert(totalFreight1);
//			            	alert(totalQuantity1);
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				};

function viewDetails() {
	/*$('#factories').css("display", 'block');*/
	
	 var x = document.getElementById('factories');
	    if (x.style.display === 'none') {
	        x.style.display = 'block';
	        $('#btnExport1').css('display','block');
	    } else {
	        x.style.display = 'none';
	        $('#btnExport1').css('display','none');
	    }
}

