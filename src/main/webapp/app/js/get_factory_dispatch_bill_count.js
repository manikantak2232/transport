var table;
var table_rows = "";
var rows="";
var factory= "";
var tables = "";
var totalFreight=0;
var totalQuantity=0;
var today;
var other_table;
var other_table_rows = "";
var other_rows="";
var otherFactory= "";
var tables = "";
var otherFreight=0;
var otherTotalQuantity=0;
var companyName;
var button_names="";
var xhr;
var ass_id;
var date_type;
var total_amount=0;

$(document).ready( function() {
    var now = new Date();
    var month = (now.getMonth() + 1);               
    var day = now.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    today = now.getFullYear() + '-' + month + '-' + day;
   
});

function getDispatch() {
	
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
			
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
	            	$('#tabl1').css('display','none');
	            	$('#export_button').css('display','none');
	            	
	            	
	            	var requestData = new Object();

				/*		requestData.lower_date= today;
						requestData.upper_date= today;*/
					requestData.lower_date= $('#lower_date').val();
					requestData.upper_date= $('#upper_date').val();
					requestData.association_id= $('#association_id').val();	
					
					xhr=$.ajax({
						url: '/transport/factory/dispatch/billcount/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	$('#get').prop('disabled',false);

			            	var factoriesList = response;
			            	
			  /*          	var svtcData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name=='svtc';
		            			});*/        
			       		            	
			            	for(var i=0; i<factoriesList.Factories.length; i++){
			            		factory = factoriesList.Factories[i];
			            		
			            		if(factory.unload_location==null){
			            			factory.unload_location="";
			            		}
			            		if(factory.load_quantity==null){
			            			factory.load_quantity="";
			            		}
			            		if(factory.freight==null){
			            			factory.freight="";
			            		}
			            		
			            		var fr=(factory.freight/factory.load_quantity);
			            		totalFreight += fr;
			            		
			            		var quantity=factory.load_quantity;
			            		totalQuantity += quantity;
			            		
			            		var total=((factory.load_quantity)*(factory.freight/factory.load_quantity));
			            		total_amount += total;
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr class='table'>"+
					            					"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (i+1) + "</td>"+
					            					"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Cement' + "</td>"+		            						
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + factory.unload_location + "</td>"+	
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + factory.load_quantity + "</td>"+
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (factory.freight/factory.load_quantity).toFixed(2) + "</td>"+
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ((factory.load_quantity)*(factory.freight/factory.load_quantity)).toFixed(2)+"</td>"+
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
				            						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
				            						"</tr>"
				            						;
				            	

			            	}
			            	
			            	rows=table_rows;

		            	table_rows=table_rows+
			            		
        						"<td colspan='1' style='border: none;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+	
        						"<td colspan='1' style='border: none;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
        						"<td colspan='1'  style='border: none;border-collapse:collapse;text-align:center;line-height: 200%;''>" + '' + "</td>"+
        						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + totalQuantity.toFixed(2) + "</td>"+
        						"<td colspan='1'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + totalFreight.toFixed(2) + "</td>"+
        						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ total_amount.toFixed(2)+"</td>"+
        						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
        						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
        						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
        						"<br><br>";

			            	
			            	
		            	var table_header = 
							"<tr style='background-color:#96989b'>"+
			            	"<td colspan='1' style='width: 3.4em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" +"S.No" + "</b></h5></td>"+
			            	"<td colspan='1' style='width: 11em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" +"DESCRIPTION OF BILL" + "</b></h5></td>"+
							"<td colspan='1' style='width: 7em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" + "DESTINATION" + "</b></h5></td>"+
							"<td colspan='1'style='width: 6em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" + "QTY IN (MT)" + "</b></h5></td>"+
							"<td colspan='1' style='width: 5.5em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>"  + "FREIGHT" + "</b></h5></td>"+
							"<td colspan='1' style='width: 6em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" + "TOTAL" + "</b></h5></td>"+
							"<td colspan='1' style='width: 7.15em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" + "SHORTAGES" + "</b></h5></td>"+
							"<td colspan='1' style='width: 5.73em;border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'><h5><b>" + "REMARKS" + "</b></h5></td>"+
							"<td colspan='1'><h5>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "</td>"+								
							            						
						"</tr>";
			                 //   alert(table);	
			            	
			            	table =table_header+table_rows ;
			            	$('#data').html(table);
			            	$('#tabl1').css('display','block');
			            	$('#export_button').css('display','block');
			            	
			            	
			            },
			            error: function(error) {
			            //	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};

				
				
/*				var table_header = 
					"<tr style='background-color:#96989b'>"+
	            	"<td colspan='1' style='width: 3.4em;'><h5'>" +"S.No" + "</h5></td>"+
	            	"<td colspan='1' style='width: 14.4em;'><h5>" +"DESCRIPTION OF BILL" + "</h5></td>"+
					"<td colspan='1' style='width: 7.855em;'><h5>" + "DESTINATION" + "</h5></td>"+
					"<td colspan='1'style='width: 6.9em;'><h5>" + "QTY IN (MT)" + "</h5></td>"+
					"<td colspan='1' style='width: 5.05em;'><h5>"  + "FREIGHT" + "</h5></td>"+
					"<td colspan='1' style='width: 4em;'><h5>" + "TOTAL" + "</h5></td>"+
					"<td colspan='1' style='width: 7.15em;'><h5>" + "SHORTAGES" + "</h5></td>"+
					"<td colspan='1' style='width: 5.73em;'><h5>" + "REMARKS" + "</h5></td>"+
					"<td colspan='1'><h5>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "</td>"+								
					            						
				"</tr>";*/