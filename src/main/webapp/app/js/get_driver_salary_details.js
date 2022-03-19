var tF=0;
 var dispatchIds="";
var disp_id;
$(document).ready(function (){ 
				$('#get').click(function () {
					
				$('#view').css("display", 'none');
				$('#driverSal').css("display", 'none');
				
				$('#total_freight').val("");
            	$('#no_of_days').val("");
            	$('#driver_name').val("");
            	$('#commission').val("");
            	$('#salary_per_day').val("");
            	$('#short_bags').val("");
            	$('#cost_per_bag').val("");
            	$('#short_fuel').val("");
            	$('#cost_per_liter').val("");
            	$('#total_salary').val("");

					var requestData = new Object();
					requestData.fk_driver_id= $('#fk_driver_id').val();
					
					$.ajax({
						url: '/transport/driver/salary/get',
						type: 'POST',
						data: JSON.stringify(requestData),
						dataType: 'json',
						contentType: 'application/json; charset=utf-8',
			            success: function(response) {
			            	var driver = "";
			            	var tables = "";
			            	var table = "";
			            	
			            	var table_rows = "";
			            	var driver = "";
			            	var dayCount=[];
			            	var totalFreight=[];
			            	var driverName;
			      //      	alert(response);
			            	var driversSalaryList = response;
			            	for(var i=0; i<driversSalaryList.DriversSalary.length; i++){
			            		driver = driversSalaryList.DriversSalary[i];
			            		var loading_date = new Date(driver.loading_date);
				            	var closed_date = new Date(driver.closed_date);			            	
				            	var timeDiff = Math.abs(closed_date.getTime() - loading_date.getTime());
				            	dayCount.push(timeDiff/(1000 * 3600 * 24));
				      //      	alert(timeDiff/(1000 * 3600 * 24));
				            	var freight=driver.freight;
				            	totalFreight.push(freight);
				           // 	dispatchIds.push(driver.pk_factory_dispatch_id );
				            	driverName=driver.driver_name;
				            	disp_id =driver.pk_factory_dispatch_id;
				       //     	 dispatchIds = (disp_id).join(','); 
				            	dispatchIds += disp_id + ",";
				            	
				            	table_rows = table_rows + 
				            					"<tr width=100%>"+
					            					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px;display:none;'>" + driver.pk_factory_dispatch_id + "</td>"+
					            					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px;display:none;'>" + driver.fk_truck_id + "</td>"+
				            						"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.truck_number + "</td>"+	
				            						"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.invoice_number + "</td>"+
				              						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.start_location + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.unload_location + "</td>"+
					                                "<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.freight + "</td>"+
					            					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.loading_date + "</td>"+	
					              					"<td width=15% style='text-align:left; padding-left:3px; padding-right:3px'>" + driver.closed_date + "</td>"+
				            					"</tr>";
				            

			            	}	            	           		
			            	
			            	var table_header = "<tr width=100%>"+
								            	"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px;display:none;'>" +"Factory Dispatch Id" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px;display:none;'>" + "Truck Id" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
												"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Start Location" + "</td>"+
								            	"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Unload Location" + "</td>"+
								            	"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Freight" + "</td>"+
												"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Loading Date" + "</td>"+
												"<td width=15% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closed Date" + "</td>"+
												
											"</tr>";
						
			            	table = "<table width=95% align=center>" + table_header + table_rows + "</table>";
			            	
			            	$('#driverSalary').html(table); 
			            	dispatchIds = dispatchIds.replace(/,(?=[^,]*$)/, '');
			            	var totalDays=0;
			            	for(var i in dayCount) 
			            	{ 
			            		totalDays += dayCount[i]; 
			            	}
			             
			            	
			            	for(var i in totalFreight) 
			            	{ 
			            		tF += totalFreight[i]; 
			            	}
			            	
			            	$('#total_freight').val(tF);
			            	$('#no_of_days').val(totalDays);
			            	$('#driver_name').val(driverName);
			            	$('#commission').val(tF*3/100);
			            	if(table_rows!=''){
			            		$('#view').css("display", 'block');
			            	}
			            	
			            		            		            	
			            },
			            error: function(error) {
			                console.log(error);
			            }
					});
				});
			});


function calculateSalary() {
	  var numberOfDays=document.getElementById("no_of_days").value;
	  var salaryPerDay=document.getElementById("salary_per_day").value;
	 
	  var shortBagValue=document.getElementById("short_bags").value;
	  var costPerBag=document.getElementById("cost_per_bag").value;
	  
	  var shortFuelValue=document.getElementById("short_fuel").value;
	  var cost_per_liter=document.getElementById("cost_per_liter").value;
	  var balance=document.getElementById("balance").value;
	  var total=(numberOfDays*salaryPerDay)+(tF*3/100)-(shortBagValue*costPerBag)-(shortFuelValue*cost_per_liter);
	  	
	  /*if (myResult == Number.POSITIVE_INFINITY || myResult == Number.NEGATIVE_INFINITY)
	  {
		  myResult='';
	  }*/
	  var myResult=(total-balance);
	  $('#total_salary').val(myResult);
}

function viewDetails() {
	/*$('#driverSal').css("display", 'block');*/
	 var x = document.getElementById('driverSal');
	    if (x.style.display === 'none') {
	        x.style.display = 'block';
	    } else {
	        x.style.display = 'none';
	    }
}

/*function myFunction() {
    var x = document.getElementById('view');
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}*/

$(document).ready(function (){ 
	  
	$('#pay').click(function () {
		$('#loading').show();
		var requestData = new Object();
		requestData.dispatchIds= dispatchIds;

		$.ajax({
        	url: '/transport/driver/salary/pay',
			type: 'POST',
            dataType: 'json',
            data: JSON.stringify(requestData),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {	
            	$('#loading').hide();
            	$("#Message").html(response.message);
            	
            	var requestData1 = new Object();
            	requestData1.fk_driver_id= $('#fk_driver_id').val();
            	requestData1.no_of_days= $('#no_of_days').val();
            	requestData1.salary_per_day= $('#salary_per_day').val();
            	requestData1.commission= $('#commission').val();
            	requestData1.short_bags= $('#short_bags').val();
            	requestData1.cost_per_bag= $('#cost_per_bag').val();
            	requestData1.short_fuel= $('#short_fuel').val();
            	requestData1.cost_per_liter= $('#cost_per_liter').val();
            	requestData1.total_salary= $('#total_salary').val();
            	
            	$.ajax({
                	url: '/transport/driver/salary/history/add',
        			type: 'POST',
                    dataType: 'json',
                    data: JSON.stringify(requestData1),
                    contentType: 'application/json; charset=utf-8',
                    success: function(response) {	
                    	$('#loading').hide();
                    	$("#Message").html(response.message);
                    },
                    error: function(error) {
                        console.log(error);
                    }
                });
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});
