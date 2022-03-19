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

$(document).ready( function() {
    var now = new Date();
    var month = (now.getMonth() + 1);               
    var day = now.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
     today = now.getFullYear() + '-' + month + '-' + day;
    $('#lower_date').val(today);
    $('#upper_date').val(today);
});

$(document).ready(function (){ 
	$('#daily_id').click(function () {
		$('#weekly_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#daily_id').css('border-color','#ff0000');
	
		$('#two_dates').css('display','none');
		date_type='today';
		getDispatch();
		return false;	
		
	});
});

$(document).ready(function (){ 
	$('#weekly_id').click(function () {
		$('#daily_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#weekly_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','none');
		date_type='week';
		getDispatch();
		return false;	
	
	});
});

$(document).ready(function (){ 
	$('#monthly_id').click(function () {
		$('#weekly_id').css('border-color','white');
		$('#daily_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#monthly_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','none');
		date_type='month';
		getDispatch();
		return false;	
		
	});
});

$(document).ready(function (){ 
	$('#custom_id').click(function () {
		
		$('#weekly_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#daily_id').css('border-color','white');
		$('#custom_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','block');
		
		date_type='custom';
		
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
		
		return false;	
	});
});

function getDispatch() {
	
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
					$('#get').prop('disabled',true);
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
					if(date_type=='today'){
						requestData.lower_date= today;
						requestData.upper_date= today;

					}
					if(date_type=='week'){
						requestData.lower_date= week_start_date;					
						requestData.upper_date= week_last_date;

					}
					if(date_type=='month'){
						requestData.lower_date=month_start_date;
						requestData.upper_date=month_last_date;
					}
					if(date_type=='custom'){
						requestData.lower_date=$('#lower_date').val();
						requestData.upper_date= $('#upper_date').val();
					}
					
					xhr=$.ajax({
						url: '/transport/factory/dispatch/report/daily/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	$('#get').prop('disabled',false);

			            	var factoriesList = response;
			            	
			            	var svtcData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name=='svtc';
		            			});
			           
			            	var otherData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name!='svtc';
		            			});
			         
			            	
			            	for(var i=0; i<svtcData.length; i++){
			            		factory = svtcData[i];
			            		
			            		if(factory.invoice_number==null){
			            			factory.invoice_number="";
			            		}
			            		if(factory.start_location==null){
			            			 factory.start_location="";
			            		}
			            		if(factory.unload_location==null){
			            			factory.unload_location="";
			            		}
			            		if(factory.unload_location_name==null){
			            			factory.unload_location_name="";
			            		}
			            		if(factory.unload_report_locations==null){
			            			factory.unload_report_locations="";
			            		}
			            		if(factory.load_quantity==null){
			            			factory.load_quantity="";
			            		}
			            		if(factory.freight==null){
			            			factory.freight="";
			            		}
			            		
			            		var fr=factory.freight;
			            		totalFreight += fr;
			            		
			            		var quantity=factory.load_quantity;
			            		totalQuantity += quantity;
			            		
			            		 var loadDate;
				                  
				                  if (navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1) {
				                	  loadDate=factory.loading_date;
				                  }
				                  else{
				                	  var lDate=factory.loading_date;
					            		
					            		var todayTime = new Date(lDate);
					                    var month = todayTime .getMonth() + 1;
					                    var day = todayTime .getDate();
					                    var year = todayTime .getFullYear();
					                    var hour = todayTime .getHours();
					                    var min = todayTime .getMinutes();
					                    var sec = todayTime .getSeconds();
					                    if(month < 10) 
					                    	month = "0" + month;
					                    if(day < 10) 
					                    	day = "0" + day;
					                    if(hour < 10) 
					                    	hour = "0" + hour;

					                    if(min < 10) 
					                    	min = "0" + min;

					                    if(sec < 10) 
					                    	sec = "0" + sec;

					                   loadDate= day + "-" + month + "-" + year+ " " + hour + ":" + min + ":" + sec ;
				                  };
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + loadDate + "</td>"+	
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.start_location + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location_name + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.type_of_cement + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.load_quantity + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.freight + "</td>"+
				            									            						
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
        						
			            		"</tr>";
			            	
			            	var table_header = 
        						"<tr width=80%>"+
				            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Invoice Date" + "</td>"+
				            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
								"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Godown Name" + "</td>"+
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Party Name" + "</td>"+
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type" + "</td>"+
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Qty" + "</td>"+
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
								
								            						
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
			            		if(otherFactory.unload_location_name==null){
			            			otherFactory.unload_location_name="";
			            		}
			            		
			            		var otherFr=otherFactory.freight;
			            		otherFreight += otherFr;
			            		
			            		var otherQuantity=otherFactory.load_quantity;
			            		otherTotalQuantity += otherQuantity;
			            		
			            			            		
			            		 var loadDate;
				                  
				                  if (navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1) {
				                	  loadDate=otherFactory.loading_date;
				                  }
				                  else{
				                	  var lDate=otherFactory.loading_date;
					            		
					            		var todayTime = new Date(lDate);
					                    var month = todayTime .getMonth() + 1;
					                    var day = todayTime .getDate();
					                    var year = todayTime .getFullYear();
					                    var hour = todayTime .getHours();
					                    var min = todayTime .getMinutes();
					                    var sec = todayTime .getSeconds();
					                    if(month < 10) 
					                    	month = "0" + month;
					                    if(day < 10) 
					                    	day = "0" + day;
					                    if(hour < 10) 
					                    	hour = "0" + hour;

					                    if(min < 10) 
					                    	min = "0" + min;

					                    if(sec < 10) 
					                    	sec = "0" + sec;

					                   loadDate= day + "-" + month + "-" + year+ " " + hour + ":" + min + ":" + sec ;
				                  };
			            		
				            	other_table_rows = other_table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + loadDate + "</td>"+	
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (otherFactory.truck_number).toUpperCase() + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.driver_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.company_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.invoice_number + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.start_location + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.unload_location + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.unload_location_name + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.type_of_cement + "</td>"+	
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
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherTotalQuantity + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFreight + "</td>"+
        						
			            		"</tr>";
			            
			            	var other_table_header = 
			            						"<tr width=80%>"+
								            	"<td width=3% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Invoice Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=17% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Company Name" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Godown Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Party Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Qty" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												
												            						
											"</tr>";
						

			            	table = "<table>" + table_header + table_rows + "</table>";
			            
			            	other_table = "<table>" + other_table_header + other_table_rows + "</table>";
			            	
			            	$('#dataTab').html(table);
			            	$('#outsideDispatch').html(other_table);
			            	
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
			            	}
			            	
			            },
			      //      async: false,

			            error: function(error) {
			            	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};


