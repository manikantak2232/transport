var table;
var table_rows = "";
var rows="";
var factory= "";
var tyre='';
var tables = "";
var totalFreight=0;
var totalQuantity=0;

var other_table;
var other_table_rows = "";
var other_rows="";
var otherFactory= "";

var tyre_table;
var tyre_table_rows = "";
var tyre_rows="";
var tyreFactory= "";

var otherFreight=0;
var otherTotalQuantity=0;
var companyName;
var button_names="";
var xhr;
var ass_id;
var date_type;
var today;

var quarterly_type;
var half_yearly_type;

var first_day_quarter1; 
var last_day_quarter1; 
var first_day_quarter2; 
var last_day_quarter2; 
var first_day_quarter3; 
var last_day_quarter3; 
var first_day_quarter4; 
var last_day_quarter4; 

var first_day_half1; 
var last_day_half1; 
var first_day_half2; 
var last_day_half2; 

var first_day_year;
var last_day_year;

var quarter_low_date;
var quarter_high_date;
var half_low_date;
var half_high_date;
var year_low_date;
var year_high_date;

var month_start_date;
var month_last_date;

$(document).ready( function() {
	var d=new Date();
	var cur=d.getMonth()+1;
	
	if(4<=cur & cur<=6){	
		quarterly_type='1st quarterly';
		half_yearly_type='1st half yearly';
	}
	if(7<=cur & cur<=9){
		quarterly_type='2nd quarterly';
		half_yearly_type='1st half yearly';
	}
	if(10<=cur & cur<=12){
		quarterly_type='3rd quarterly';
		half_yearly_type='2nd half yearly';
	}
	if(1<=cur & cur<=3){
		quarterly_type='4th quarterly';
		half_yearly_type='2nd half yearly';
	}
});

$(document).ready( function() {

    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    
    var month = (firstDay.getMonth() + 1);               
    var day = firstDay.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var first_day = firstDay.getFullYear() + '-' + month + '-' + day;
    
    var lmonth = (lastDay.getMonth() + 1);               
    var lday = lastDay.getDate();
    if(lmonth < 10) 
    	lmonth = "0" + lmonth;
    if(lday < 10) 
    	lday = "0" + lday;
    var last_day = lastDay.getFullYear() + '-' + lmonth + '-' + lday;
/*    $('#lower_date').val(first_day);
    $('#upper_date').val(last_day);*/
    
    month_start_date=first_day;
    month_last_date=last_day;
    });
    

$(document).ready( function() {
	var d = new Date();
	d.setMonth(3);
	d.setDate(1);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      first_day_quarter1= now.getFullYear() + '-' + month + '-' + day;
	      first_day_half1=first_day_quarter1;
	      first_day_year=first_day_quarter1;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(5);
	d.setDate(30);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      last_day_quarter1= now.getFullYear() + '-' + month + '-' + day;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(6);
	d.setDate(1);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      first_day_quarter2= now.getFullYear() + '-' + month + '-' + day;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(8);
	d.setDate(30);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      last_day_quarter2= now.getFullYear() + '-' + month + '-' + day;
	      last_day_half1=last_day_quarter2;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(9);
	d.setDate(1);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      first_day_quarter3= now.getFullYear() + '-' + month + '-' + day;
	      first_day_half2=first_day_quarter3;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(11);
	d.setDate(31);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      last_day_quarter3= now.getFullYear() + '-' + month + '-' + day;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(0);
	d.setDate(1);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      first_day_quarter4= now.getFullYear() + '-' + month + '-' + day;
});

$(document).ready( function() {
	var d = new Date();
	d.setMonth(2);
	d.setDate(31);
	d.setFullYear(d.getFullYear());
	    var now = new Date(d);
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	      last_day_quarter4= now.getFullYear() + '-' + month + '-' + day;
	      last_day_half2=last_day_quarter4;
	      last_day_year=last_day_quarter4;
});

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
	$('#quarter_id').click(function () {
		$('#half_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#year_id').css('border-color','white');
		$('#quarter_id').css('border-color','#ff0000');
	
		$('#two_dates').css('display','none');
		date_type='quart';
		
		if(quarterly_type=='1st quarterly'){
			quarter_low_date=first_day_quarter1;
			quarter_high_date=last_day_quarter1;
		}
		if(quarterly_type=='2nd quarterly'){
			quarter_low_date=first_day_quarter2;
			quarter_high_date=last_day_quarter2;
		}
		if(quarterly_type=='3rd quarterly'){
			quarter_low_date=first_day_quarter3;
			quarter_high_date=last_day_quarter3;
		}
		if(quarterly_type=='4th quarterly'){
			quarter_low_date=first_day_quarter4;
			quarter_high_date=last_day_quarter4;
		}
		getDispatch();
		return false;	
		
	});
});

$(document).ready(function (){ 
	$('#half_id').click(function () {
		$('#quarter_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#year_id').css('border-color','white');
		$('#half_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','none');
		date_type='half';
//		alert(half_yearly_type);
		if(half_yearly_type=='1st half yearly'){
			half_low_date=first_day_half1;
			half_high_date=last_day_half1;
		}
		if(half_yearly_type=='2nd half yearly'){
			half_low_date=first_day_half2;
			half_high_date=last_day_half2;
		}
		
		getDispatch();
		return false;	
	
	});
});

$(document).ready(function (){ 
	$('#year_id').click(function () {
		$('#quarter_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#half_id').css('border-color','white');
		$('#year_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','none');
		date_type='year';

			year_low_date=first_day_year;
			year_high_date=last_day_year;	
		
		getDispatch();
		return false;	
	
	});
});

$(document).ready(function (){ 
	$('#monthly_id').click(function () {
		$('#half_id').css('border-color','white');
		$('#quarter_id').css('border-color','white');
		$('#custom_id').css('border-color','white');
		$('#year_id').css('border-color','white');
		$('#monthly_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','none');
		date_type='month';
		getDispatch();
		return false;	
		
	});
});

$(document).ready(function (){ 
	$('#custom_id').click(function () {
		
		$('#half_id').css('border-color','white');
		$('#monthly_id').css('border-color','white');
		$('#quarter_id').css('border-color','white');
		$('#year_id').css('border-color','white');
		$('#custom_id').css('border-color','#ff0000');
		
		$('#two_dates').css('display','block');
		
		date_type='custom';
		
		$('#direct_maintenance_table').css('display','none');
		$('#oil_expenses_table').css('display','none');
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
		
		return false;	
	});
});

function getDispatch() {
	
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
					$('#get').prop('disabled',true);
					$('#direct_maintenance_table').css('display','none');
					$('#oil_expenses_table').css('display','none');
					$('#tyreExpenses').css('display','none');
					$('#dataTab').css('display','none');
					$('#outsideDispatch').css('display','none');
					$('#tyreTable').css('display','none');
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
	            	tyre_table_rows = "";
	            	
	            	var requestData = new Object();
	            	
	            	if(date_type=='month'){
						requestData.lower_date=month_start_date;
						requestData.upper_date=month_last_date;
						alert(month_start_date);
						alert(month_last_date);
					}
					if(date_type=='quart'){
						requestData.lower_date= quarter_low_date;
						requestData.upper_date= quarter_high_date;
						alert(quarter_low_date);
						alert(quarter_high_date);
					}
					if(date_type=='half'){
						requestData.lower_date= half_low_date;					
						requestData.upper_date= half_high_date;
						alert(half_low_date);
						alert(half_high_date);
					}
					
					if(date_type=='year'){
						requestData.lower_date= year_low_date;					
						requestData.upper_date= year_high_date;
						alert(year_low_date);
						alert(year_high_date);
					}
					
					if(date_type=='custom'){
						requestData.lower_date=$('#lower_date').val();
						requestData.upper_date= $('#upper_date').val();
					}
					
					xhr=$.ajax({
						url: '/transport/spareparts/truck/overall/maintenance/report/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	$('#get').prop('disabled',false);

			            	var maintenanceList = response;
			            	
			            	var directMaintenance = $.grep(maintenanceList.maintenance, function (element) {    
		            			  return element.maintenance_type=='direct maintenance';
		            			});
			            	
			            	var oilMaintenance = $.grep(maintenanceList.maintenance, function (element) {    
		            			  return element.maintenance_type=='oil expenses';
		            			});
			            	
			            	var tyreMaintenance = $.grep(maintenanceList.maintenance, function (element) {    
		            			  return element.maintenance_type=='tyre expenses';
		            			});
			           	
			            	
			            	for(var i=0; i<directMaintenance.length; i++){
			            		factory = directMaintenance[i];
			            		
			            		if(factory.invoice_number==null){
			            			factory.invoice_number="";
			            		}
			            		if(factory.item_names==null){
			            			 factory.item_names="";
			            		}
			            		if(factory.cost==null){
			            			factory.cost="";
			            		}
			            		if(factory.remarks==null){
			            			factory.remarks="";
			            		}
			            		
				            	table_rows = table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.item_names + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.cost + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.remarks + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date + "</td>"+
				            									            						
				            						"</tr>";

			            	}
			            				            	
			            	var table_header = 
        						"<tr width=80%>"+
				            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Item Names" + "</td>"+
								"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost" + "</td>"+
								"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Remarks" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
								
								            						
							"</tr>";
			            	
			            	for(var i=0; i<oilMaintenance.length; i++){
			            		otherFactory = oilMaintenance[i];
			            		
			            		if(otherFactory.truck_number==null){
			            			otherFactory.truck_number="";
			            		}
			            		if(otherFactory.cost==null){
			            			 factory.cost="";
			            		}
			            		if(otherFactory.date==null){
			            			otherFactory.date="";
			            		}
			            		if(otherFactory.brand_name==null){
			            			otherFactory.brand_name="";
			            		}
			            					            		
				            	other_table_rows = other_table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.cost + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.date + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + otherFactory.brand_name + "</td>"+
				            									            						
				            						"</tr>";

			            	}
			            
			            	var other_table_header = 
			            						"<tr width=80%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Date" + "</td>"+
												"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
												            						
											"</tr>";
			            	
			            	for(var i=0; i<tyreMaintenance.length; i++){
			            		tyre = tyreMaintenance[i];
			            		
			            		if(tyre.invoice_number==null){
			            			tyre.invoice_number="";
			            		}
			            		if(tyre.truck_number==null){
			            			 tyre.truck_number="";
			            		}
			            		if(tyre.cost==null){
			            			tyre.cost="";
			            		}
			            		if(tyre.brand_name==null){
			            			tyre.brand_name="";
			            		}
			            		if(tyre.tyre_number==null){
			            			tyre.tyre_number="";
			            		}
			            		if(tyre.tyre_type==null){
			            			tyre.tyre_type="";
			            		}
			            		
				            	tyre_table_rows = tyre_table_rows + 
				            	
				            					"<tr width=80% >"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.truck_number + "</td>"+		            						
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.cost + "</td>"+
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.brand_name + "</td>"+
				            						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.tyre_number + "</td>"+	
				            						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + tyre.tyre_type + "</td>"+
				            									            						
				            						"</tr>";

			            	}
			            				            	
			            	var tyre_table_header = 
        						"<tr width=80%>"+
				            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				            	"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost" + "</td>"+
								"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Brand Name" + "</td>"+
								"<td width=10%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Tyre Number" + "</td>"+
								"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Tyre type" + "</td>"+
								
								            						
							"</tr>";
						

			            	table = "<table>" + table_header + table_rows + "</table>";
			            
			            	other_table = "<table>" + other_table_header + other_table_rows + "</table>";
			            	
			            	tyre_table = "<table>" + tyre_table_header + tyre_table_rows + "</table>";
			            	
			            	$('#dataTab').html(table);
			            	$('#outsideDispatch').html(other_table);
			            	$('#tyreExpenses').html(tyre_table);
			            	
			            	if(table_rows!=''){ 
			            
			            		$('#dataTab').css('display','block');
			            		$('#direct_maintenance_table').css('display','block');
			            		$('#export_button').css('display', 'block');
			            	}
			            	if(table_rows=='' & other_table_rows=='' & tyre_table_rows==''){
			            		alert('Sorry, No Data Available');
			            	}
			            	
			            	if(other_table_rows!=''){			            		
			            		$('#outsideDispatch').css('display','block');
			            		$('#oil_expenses_table').css('display','block');
			            	}
			            	
			            	if(tyre_table_rows!=''){			            		
			            		$('#tyreExpenses').css('display','block');
			            		$('#tyreTable').css('display','block');
			            	}
			            	
			            },
			      //      async: false,

			            error: function(error) {
			            	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};


