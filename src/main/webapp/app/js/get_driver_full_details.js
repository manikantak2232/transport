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

var otherFreight=0;
var otherTotalQuantity=0;
var companyName;
var button_names="";
var xhr;
var ass_id;
var date_type;
var today;

function getDetails() {
	
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
					$('#get').prop('disabled',true);

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
	            	
				
					xhr=$.ajax({
						url: '/transport/driver/full/details/get',
						type: 'GET',
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
			            	$('#get').prop('disabled',false);

			            	var factoriesList = response;		          		         
			            	
			            	for(var i=0; i<factoriesList.DriverDetails.length; i++){
			            		factory = factoriesList.DriverDetails[i];
			            		
			            		if(factory.driving_license==null){
			            			factory.driving_license="";
			            		}
			            		if(factory.date_of_birth==null){
			            			factory.date_of_birth="";
			            		}
			            		if(factory.svtc_id==null){
			            			factory.svtc_id="";
			            		}
			            		if(factory.phone_number==null){
			            			factory.phone_number="";
			            		}
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date_of_birth + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driving_license + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.phone_number + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.svtc_id + "</td>"+	
				            									            						
				            						"</tr>";

			            	}
			            	
			            				            	
			            	var table_header = 
        						"<tr width=80%>"+
				            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Name" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "DOB" + "</td>"+
								"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driving License" + "</td>"+
								"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Phone Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Svtc Id" + "</td>"+
								
								            						
							"</tr>";						

			            	table = "<table>" + table_header + table_rows + "</table>";
			            
						            	
			            	$('#dataTab').html(table);
			            			            	
			            	
			            },
			      //      async: false,

			            error: function(error) {
			            	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};


				