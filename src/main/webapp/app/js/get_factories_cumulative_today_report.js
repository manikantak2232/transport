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

var daily_var=false;
var weekly_var=false;
var monthly_var=false;

var anjani_var=false;
var bharathi_var=false;
var chettinad_var=false;
var deccan_var=false;
var jsw_var=false;
var ramco_var=false;
var zuari_var=false;
var custom_dates_var=false;

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
		
		daily_var=true;
		weekly_var=false;
		monthly_var=false;
		
		$('#factory_button_names').css('display','block');
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
		
		daily_var=false;
		weekly_var=true;
		monthly_var=false;
		
		$('#factory_button_names').css('display','block');
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
		
		daily_var=false;
		weekly_var=false;
		monthly_var=true;
		
		$('#factory_button_names').css('display','block');
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
		
		$('#factory_button_names').css('display','none');
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

$(document).ready(function(){
    $("div").on("click","#zuari_id", function(){
    	
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','#ff0000');
		
		anjani_var=false;
		bharathi_var=false;
		chettinad_var=false;
		deccan_var=false;
		jsw_var=false;
		ramco_var=false;
		zuari_var=true;
		
		ass_id=$('#zuari_id').val();
		getDispatch();
        return false;
    });
});

function fu(){
	custom_dates_var=true;
	getDispatch();
}
$(document).ready(function(){
    $("div").on("click","#anjani_id", function(){
    	
		$('#anjani_id').css('border-color','#ff0000');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=true;
		bharathi_var=false;
		chettinad_var=false;
		deccan_var=false;
		jsw_var=false;
		ramco_var=false;
		zuari_var=false;
		
		ass_id=$('#anjani_id').val();
		getDispatch();
        return false;
    });
});

$(document).ready(function(){
    $("div").on("click","#bharathi_id", function(){
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','#ff0000');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=false;
		bharathi_var=true;
		chettinad_var=false;
		deccan_var=false;
		jsw_var=false;
		ramco_var=false;
		zuari_var=false;
		
		ass_id=$('#bharathi_id').val();
		getDispatch();
        return false;
    });
});

$(document).ready(function(){
    $("div").on("click","#chettinad_id", function(){
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','#ff0000');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=false;
		bharathi_var=false;
		chettinad_var=true;
		deccan_var=false;
		jsw_var=false;
		ramco_var=false;
		zuari_var=false;
		
		ass_id=$('#chettinad_id').val();
		getDispatch();
        return false;
    });
});

$(document).ready(function(){
    $("div").on("click","#deccan_id", function(){
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','#ff0000');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=false;
		bharathi_var=false;
		chettinad_var=false;
		deccan_var=true;
		jsw_var=false;
		ramco_var=false;
		zuari_var=false;
		
		ass_id=$('#deccan_id').val();
		getDispatch();
        return false;
    });
});

$(document).ready(function(){
    $("div").on("click","#jsw_id", function(){
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','#ff0000');
		$('#ramco_id').css('border-color','white');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=false;
		bharathi_var=false;
		chettinad_var=false;
		deccan_var=false;
		jsw_var=true;
		ramco_var=false;
		zuari_var=false;
		
		ass_id=$('#jsw_id').val();
		getDispatch();
        return false;
    });
});

$(document).ready(function(){
    $("div").on("click","#ramco_id", function(){
		$('#anjani_id').css('border-color','white');
		$('#bharathi_id').css('border-color','white');
		$('#chettinad_id').css('border-color','white');
		$('#deccan_id').css('border-color','white');
		$('#jsw_id').css('border-color','white');
		$('#ramco_id').css('border-color','#ff0000');
		$('#zuari_id').css('border-color','white');
		
		anjani_var=false;
		bharathi_var=false;
		chettinad_var=false;
		deccan_var=false;
		jsw_var=false;
		ramco_var=true;
		zuari_var=false;
		
		ass_id=$('#ramco_id').val();
		getDispatch();
        return false;
    });
});
// alert(ass_id);
function getDispatch() {
	if((daily_var==true && anjani_var==true) || (daily_var==true && bharathi_var==true) || (daily_var==true && chettinad_var==true)  ||
		(daily_var==true && deccan_var==true) || (daily_var==true && jsw_var==true) || (daily_var==true && zuari_var==true) ||
		(daily_var==true && ramco_var==true) || (weekly_var==true && anjani_var==true) || (weekly_var==true && bharathi_var==true) || 
		(weekly_var==true && chettinad_var==true)  || (weekly_var==true && deccan_var==true) || (weekly_var==true && jsw_var==true) || 
		(weekly_var==true && zuari_var==true) || (weekly_var==true && ramco_var==true) || (monthly_var==true && anjani_var==true) || 
		(monthly_var==true && bharathi_var==true) || (monthly_var==true && chettinad_var==true)  || (monthly_var==true && deccan_var==true) ||
		(monthly_var==true && jsw_var==true) || (monthly_var==true && zuari_var==true) || (monthly_var==true && ramco_var==true) || (custom_dates_var==true)){

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
						requestData.association_id= ass_id;	
					}
					if(date_type=='week'){
						requestData.lower_date= week_start_date;					
						requestData.upper_date= week_last_date;
						requestData.association_id= ass_id;	
					}
					if(date_type=='month'){
						requestData.lower_date=month_start_date;
						requestData.upper_date=month_last_date;
						requestData.association_id= ass_id;	
					}
					if(date_type=='custom'){
						requestData.lower_date=$('#lower_date').val();
						requestData.upper_date= $('#upper_date').val();
						requestData.association_id=$('#association_id').val();
					}
					
					xhr=$.ajax({	
						url: '/transport/factory/cumulative/report/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {

			            	$('#get').prop('disabled',false);
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
			            		if(factory.type_of_cement==null | factory.type_of_cement=='Select'){
			            			factory.type_of_cement="";
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
			            		
			            	/*	var loadDate;
			            					                  
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
			                  };*/

				            	table_rows = table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.loading_date + "</td>"+	
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.start_location + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_location_name + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.unload_report_locations + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.type_of_cement + "</td>"+
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
        						"<td width=7% style='border:none;'>" + '' + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalQuantity + "</td>"+
        						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalFreight + "</td>"+
        		//				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
        						
			            		"</tr>";
			            
			            	var table_header = 
			            						"<tr width=80%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Loading Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Godown Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Party Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Locations" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type Of Cement" + "</td>"+
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
			            		if(otherFactory.unload_location_name==null){
			            			otherFactory.unload_location_name="";
			            		}
			            		
			            		var otherFr=otherFactory.freight;
			            		otherFreight += otherFr;
			            		
			            		var otherQuantity=otherFactory.load_quantity;
			            		otherTotalQuantity += otherQuantity;
			            		
			            					            		
			                    /*var loadDate;
				                  
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
				                  };*/
			            		
				            	other_table_rows = other_table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.loading_date + "</td>"+	
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (otherFactory.truck_number).toUpperCase()+ "</td>"+		            						
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
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
												"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Invoice Date" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Driver Name" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Company Name" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Godown Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Party Name" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type Of Cement" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Load Quantity" + "</td>"+
												"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												
												            						
											"</tr>";
			            	
			            	table = "<table style='align:center;' >" + table_header + table_rows + "</table>";
			            	
			            	other_table = "<table>" + other_table_header + other_table_rows + "</table>";
			            
			            	$('#dataTab').html(table);
			            	$('#outsideDispatch').html(other_table);
			            	$('#get').prop('disabled',false);
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
			            	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};
		return false;
};


/*$("#btnExport").click(function(event){
	fnExportHTML("dataTable","#87AFC6","Exported Excel");
});*/


function factoryNames(names_list){
	for(i=0;i<names_list.length;i++){
	//	<button id="daily_id" class="button"> Daily </button>
		button_names=button_names+"<button class='button' type='submit' id='"+names_list[i].association_alias_name+"_id' value='"+names_list[i].pk_association_id+"' >"+names_list[i].association_name+"</button>";		
    }
	$('#factory_button_names').html(button_names);
}

function addAssociationDropDown(trucks){
	for(var i=0;i<trucks.length;++i){
		addAssociationOption(document.add.association_list,trucks[i].association_alias_name,trucks[i].pk_association_id);
	}
}
function addAssociationOption(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function association(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/factory/factoriesAssociation/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.association;
				addAssociationDropDown(trucks);
				factoryNames(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
function associationChange(){
	var selectedTruck=$("#association_list :selected").text();
	var selectedTruckId=$("#association_list :selected").val();
	$("#association_id").val(selectedTruckId);
}


