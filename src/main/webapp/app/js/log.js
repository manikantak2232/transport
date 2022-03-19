
	   		$(function() {
				$('#get').click(function () {
					var requestData = new Object();
					requestData.action_item= $('#action :selected').val(); 
					requestData.invoice_number=$('#invoice_number').val();
					requestData.fk_truck_id=$('#truck_id').val();
					
					$.ajax({
						url: '/transport/actionlog/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var log = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var log = "";
			            	var actionLog = response;
			            	for(var i=0; i<actionLog.StorageFuel.length; i++){
			            		log = actionLog.StorageFuel[i];		            		
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + log.action_activity + "</td>"+
				            						"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + log.uname + "</td>"+
				            						"<td width=5%  style='text-align:left; padding-left:3px; padding-right:3px'>" + log.date_time + "</td>"+
				            						
				            					"</tr>";

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Action Avtivity" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "User Name" + "</td>"+
												"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Date" + "</td>"+
				
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			      
			            	
			            	$('#storageFuel').html(table);
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
	    
	    
	    $('#action').change(function() {
	  
	    	if($('#action :selected').val() == 'storage dispatch' |
	    		$('#action :selected').val() == 'storage invoice' |
	    		$('#action :selected').val() == 'storage incoming load' |
	    		$('#action :selected').val() == 'factory dispatch' |
	    		$('#action :selected').val() == 'factory invoice' |
	    		$('#action :selected').val() == 'seller dispatch' |
	    		$('#action :selected').val() == 'seller invoice' |
	    		$('#action :selected').val() == 'seller purchase'| 
	    		$('#action :selected').val() == 'truck parts quote') {
	    		
	    		$('#invoice_number').css("visibility", 'visible');
	    		$('#invoice_number').css("width", 125);	
	    		$('#truck_id').val(0);
	    		
	    	}else{
	    		$('#invoice_number').css("visibility", 'hidden');
	    		$('#invoice_number').css("width", 0);
	
	    	}
	    	
	    	if($('#action :selected').val() == 'storage fuel' | 
		    		$('#action :selected').val() == 'factory fuel' | 
		    		$('#action :selected').val() == 'seller fuel' |
		    		$('#action :selected').val() == 'truck allotment' |
		    		$('#action :selected').val() == 'truck details' |
		    		$('#action :selected').val() == 'truck health history' |
		    		$('#action :selected').val() == 'truck service' |
		    		$('#action :selected').val() == 'trip details' |
		    		$('#action :selected').val() == 'seller trip details' |
		    		$('#action :selected').val() == 'storage trip details' |
		    		$('#action :selected').val() == 'spare part allotment'){
		    		$('#truck_list').css("visibility", 'visible');
		    		$('#truck_list').css("width", 130);	
		    		$('#invoice_number').val("");
		     }else{
		    		$('#truck_list').css("visibility", 'hidden');
		    		$('#truck_list').css("width", 0);
		     }
	    	
	    });
	   	    
	});