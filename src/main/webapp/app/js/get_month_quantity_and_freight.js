var month_start_date;
var month_last_date;
var monthFreight=0;
var monthQuantity=0;
var monthOtherFreight=0;
var monthOtherQuantity=0;

var grandTotalMonthQuantity=0;
var grandTotalMonthFreight=0;

function monthDates() {

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
    
	var requestData = new Object();
	requestData.lower_date= first_day;
	requestData.upper_date= last_day;
	requestData.company_name= 'svtc';
	$.ajax({
		url: '/transport/factory/quantity/freight/get',
		type: 'POST',
		data: JSON.stringify(requestData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
        success: function(response) {
        	var factory= "";
        	var tables = "";
        	var factoriesList = response;
        	for(var i=0; i<factoriesList.Factories.length; i++){
        		factory = factoriesList.Factories[i];
        		
        		var fr=factory.freight;
        		monthFreight += fr;
        		
        		var quantity=factory.load_quantity;
        		monthQuantity += quantity;
        	}
        	
        	monthDatesOther()
        	
        	$('#month_total_freight').html(monthFreight.toFixed(2));
        	$('#month_total_quantity').html(monthQuantity.toFixed(2));
  //      	$('#tab').css('display','block');
        },
        error: function(error) {
            console.log(error);
        }
	});
 
};


function monthDatesOther() {

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
    
	var requestData = new Object();
	requestData.lower_date= first_day;
	requestData.upper_date= last_day;
	requestData.company_name= 'other';
	$.ajax({
		url: '/transport/factory/quantity/freight/get',
		type: 'POST',
		data: JSON.stringify(requestData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
        success: function(response) {
        	var factory= "";
        	var tables = "";
        	var factoriesList = response;
        	for(var i=0; i<factoriesList.Factories.length; i++){
        		factory = factoriesList.Factories[i];
        		
        		var fr=factory.freight;
        		monthOtherFreight += fr;
        		
        		var quantity=factory.load_quantity;
        		monthOtherQuantity += quantity;
        	}
        	
        	grandTotalMonthQuantity=monthQuantity+monthOtherQuantity;
        	grandTotalMonthFreight=monthFreight+monthOtherFreight;
        	
        	$('#month_other_total_freight').html(monthOtherFreight.toFixed(2));
        	$('#month_other_total_quantity').html(monthOtherQuantity.toFixed(2));
        	$('#month_grand_total_freight').html(grandTotalMonthFreight.toFixed(2));
        	$('#month_grand_total_quantity').html(grandTotalMonthQuantity.toFixed(2));
        	
       // 	$('#tab').css('display','block');
        },
        error: function(error) {
            console.log(error);
        }
	});
 
};