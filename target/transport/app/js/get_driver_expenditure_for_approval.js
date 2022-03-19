var table;
var table_rows = "";
var rows="";
var factory= "";
var tables = "";
var totalFreight=0;
var totalQuantity=0;
var companyName;
var button_names="";
var xhr;
var ass_id;
var date_type;
var today;

var svtcData;
function getDispatch() {
	
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
					$('#get').prop('disabled',true);
					$('#svtc_table').css('display','none');

					table_rows = "";
					factory= "";
					tables = "";
					
					xhr=$.ajax({
						url: '/transport/factory/driver/expenditure/approval/get',
						type: 'GET',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	$('#get').prop('disabled',false);

			            	svtcData = response.Factories;
			            	
			            	/*var svtcData = $.grep(factoriesList.Factories, function (element) {    
		            			  return element.company_name=='svtc';
		            			});	*/		         
			            	
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
				            									            						
				            						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'> <input type='button' data-toggle='modal' data-target='#myModal' id='but' onclick='fun("+factory.dispatch_id+")' value='get'></td>"+
					            					"</tr>";

			            	}
			            	
			            	rows=table_rows;
			            	
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
								"<td width=7%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Action" + "</td>"+
								
								            						
							"</tr>";
			            	
			            	

			            	table = "<table>" + table_header + table_rows + "</table>";
			            	
			            	$('#dataTab').html(table);
			            	
			            	if(rows!=''){ 
			            
			            		$('#dataTab').css('display','block');
			            		$('#svtc_table').css('display','block');
			            	}
			            	if(rows==''){
			            		alert('Sorry, No Data Available');
			            	}
			            	
			            },
			      //      async: false,

			            error: function(error) {
			            	$('#get').prop('disabled',false);
			                console.log(error);
			            }
					});
				};
var balance;
var dispatch_id;
var driver_id;
function fun(id){
	dispatch_id=id;
	var arry=svtcData.filter(obj=>obj.dispatch_id==id);
	$('#transport').val(arry[0].transport);
	$('#driver_name').val(arry[0].driver_name);
	$('#balance').val(arry[0].balance);
	$('#load').val(arry[0].load);
	$('#cover_tying').val(arry[0].cover_tying);
	$('#contonment').val(arry[0].contonment);
	$('#toll_gate').val(arry[0].toll_gate);
	$('#loading_wage').val(arry[0].loading_wage);
	$('#unloading_wage').val(arry[0].unloading_wage);
	$('#phone_bills').val(arry[0].phone_bills);
	$('#spares_parts').val(arry[0].spares_parts);
	
	$('#puncher').val(arry[0].puncher);
	$('#entry').val(arry[0].entry);
	$('#return_transport').val(arry[0].return_transport);
	$('#return_loading').val(arry[0].return_loading);
	$('#return_unloading').val(arry[0].return_unloading);
	$('#others').val(arry[0].others);
	balance=arry[0].balance;
	driver_id=arry[0].fk_driver_id;
}

function calculate() {
//	 var balance=document.getElementById("balance").value;
	  var transport=document.getElementById("transport").value;
	  var load=document.getElementById("load").value;
	  var cover_tying=document.getElementById("cover_tying").value;
	  var contonment=document.getElementById("contonment").value;
	  var toll_gate=document.getElementById("toll_gate").value;
	  var loading_wage=document.getElementById("loading_wage").value;
	  var unloading_wage=document.getElementById("unloading_wage").value;
	  var phone_bills=document.getElementById("phone_bills").value;
	  var spares_parts=document.getElementById("spares_parts").value;
	  var puncher=document.getElementById("puncher").value;
	  var entry=document.getElementById("entry").value;
	  var return_transport=document.getElementById("return_transport").value;
	  var return_loading=document.getElementById("return_loading").value;
	  var return_unloading=document.getElementById("return_unloading").value;
	  var others=document.getElementById("others").value;
	  
	  if(transport=='') {
		transport=0;
	  }
	  if(load=='') {
		  load=0;
		  }
	  if(cover_tying=='') {
		  cover_tying=0;
		  }
	  if(contonment=='') {
		  contonment=0;
		  }
	  if(toll_gate=='') {
		  toll_gate=0;
		  }
	  if(loading_wage=='') {
		  loading_wage=0;
		  }
	  if(unloading_wage=='') {
		  unloading_wage=0;
		  }
	  if(phone_bills=='') {
		  phone_bills=0;
		  }
	  if(spares_parts=='') {
		  spares_parts=0;
		  }
	  if(puncher=='') {
			puncher=0;
		  }
	  if(entry=='') {
			entry=0;
		  }
	  if(return_transport=='') {
		  return_transport=0;
		  }
	  if(return_loading=='') {
		  return_loading=0;
		  }
	  if(return_unloading=='') {
		  return_unloading=0;
		  }
	  if(others=='') {
			others=0;
		  }
	
	  
	//  var balance=document.getElementById("balance").value;
	  var myResult=balance-(+transport+ +load+ +cover_tying+ +contonment+ +toll_gate+ +loading_wage+
	  		 +unloading_wage+ +phone_bills+ +spares_parts+ +puncher+ +entry+ +return_transport+
	  		 +return_loading+ +return_unloading+ +others);
	  
	 // balance.value=myResult;
	  if (myResult == Number.POSITIVE_INFINITY || myResult == Number.NEGATIVE_INFINITY)
	  {
		  myResult='';
	  }
	  $("#balance").val(myResult);
}

function updateApproval() {
		  $('#loading').show();
		  
		  var transport=document.getElementById("transport").value;
		  var load=document.getElementById("load").value;
		  var cover_tying=document.getElementById("cover_tying").value;
		  var contonment=document.getElementById("contonment").value;
		  var toll_gate=document.getElementById("toll_gate").value;
		  var loading_wage=document.getElementById("loading_wage").value;
		  var unloading_wage=document.getElementById("unloading_wage").value;
		  var phone_bills=document.getElementById("phone_bills").value;
		  var spares_parts=document.getElementById("spares_parts").value;
		  var puncher=document.getElementById("puncher").value;
		  var entry=document.getElementById("entry").value;
		  var return_transport=document.getElementById("return_transport").value;
		  var return_loading=document.getElementById("return_loading").value;
		  var return_unloading=document.getElementById("return_unloading").value;
		  var others=document.getElementById("others").value;
		  
		  if(transport=='') {
			transport=0;
		  }
		  if(load=='') {
			  load=0;
			  }
		  if(cover_tying=='') {
			  cover_tying=0;
			  }
		  if(contonment=='') {
			  contonment=0;
			  }
		  if(toll_gate=='') {
			  toll_gate=0;
			  }
		  if(loading_wage=='') {
			  loading_wage=0;
			  }
		  if(unloading_wage=='') {
			  unloading_wage=0;
			  }
		  if(phone_bills=='') {
			  phone_bills=0;
			  }
		  if(spares_parts=='') {
			  spares_parts=0;
			  }
		  if(puncher=='') {
				puncher=0;
			  }
		  if(entry=='') {
				entry=0;
			  }
		  if(return_transport=='') {
			  return_transport=0;
			  }
		  if(return_loading=='') {
			  return_loading=0;
			  }
		  if(return_unloading=='') {
			  return_unloading=0;
			  }
		  if(others=='') {
				others=0;
		  } 
		  
		var requestData = new Object();
		requestData.factory_dispatch_id= dispatch_id;
		requestData.fk_driver_id= driver_id;
		requestData.transport= transport;
		requestData.loading= load;
		requestData.cover_tying=cover_tying;
		requestData.contonment=contonment;
		requestData.toll_gate=toll_gate;
		requestData.loading_wage= loading_wage;
		requestData.unloading_wage= unloading_wage;
		requestData.phone_bills= phone_bills;
		requestData.spares_parts= spares_parts;
		requestData.puncher= puncher;
		requestData.entry= entry;
		requestData.return_transport=return_transport;
		requestData.return_loading= return_loading;
		requestData.return_unloading= return_unloading;
		requestData.others= others;
		
		$.ajax({
			url: '/transport/factory/driver/expenditure/approval/update',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
        success: function(response) {
        	          	
        //	  alert("success")  
      	  $('#loading').hide();
        	alert(response.message);
        	
        },
        error: function(error) {
            console.log(error);
        }
		});
	};
