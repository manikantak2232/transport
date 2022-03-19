var week_start_date;
var week_last_date;

var weekFreight=0;
var weekQuantity=0;

var todayFreight=0;
var todayQuantity=0;

var weekOtherFreight=0;
var weekOtherQuantity=0;

var todayOtherFreight=0;
var todayOtherQuantity=0;

var grandTotalWeekQuantity=0;
var grandTotalWeekFreight=0;

var grandTotalTodayQuantity=0;
var grandTotalTodayFreight=0;

function weekDates() {
    
    var curr = new Date; // get current date
    
    var firstDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+1));
    var lastDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+7));
    
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
	            		weekFreight += fr;
	            		
	            		var quantity=factory.load_quantity;
	            		weekQuantity += quantity;
	            	}
	            	
	            	weekDatesOther();
	            	
	            	$('#week_total_freight').html(weekFreight.toFixed(2));
	            	$('#week_total_quantity').html(weekQuantity.toFixed(2));
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
			});

};

function weekDatesOther() {
    
    var curr = new Date; // get current date
    
    var firstDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+1));
    var lastDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+7));
    
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

    week_start_date=first_day;
    week_last_date=last_day;

    
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
	            		weekOtherFreight += fr;
	            		
	            		var quantity=factory.load_quantity;
	            		weekOtherQuantity += quantity;
	            	}
	            	
	            	curDatesOther();
	            	
	            	grandTotalWeekQuantity=weekQuantity+weekOtherQuantity;
	            	grandTotalWeekFreight=weekFreight+weekOtherFreight;
	            	
	            	$('#week_other_total_freight').html(weekOtherFreight.toFixed(2));
	            	$('#week_other_total_quantity').html(weekOtherQuantity.toFixed(2));
	            	
	            	$('#week_grand_total_freight').html(grandTotalWeekFreight.toFixed(2));
	            	$('#week_grand_total_quantity').html(grandTotalWeekQuantity.toFixed(2));
 
	            },
	            error: function(error) {
	                console.log(error);
	            }
			});

};


function curDates() {
    
	  var now = new Date();
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	    today = now.getFullYear() + '-' + month + '-' + day;
    
			var requestData = new Object();
			requestData.lower_date= today;
			requestData.upper_date= today;
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
	            		todayFreight += fr;
	            		
	            		var quantity=factory.load_quantity;
	            		todayQuantity += quantity;
	            	}
	            	
	            	/*alert(weekFreight);
	            	alert(weekQuantity);*/
	            	$('#today_total_freight').html(todayFreight.toFixed(2));
	            	$('#today_total_quantity').html(todayQuantity.toFixed(2));
 
	            },
	            error: function(error) {
	                console.log(error);
	            }
			});

};


function curDatesOther() {
    
	  var now = new Date();
	    var month = (now.getMonth() + 1);               
	    var day = now.getDate();
	    if(month < 10) 
	        month = "0" + month;
	    if(day < 10) 
	        day = "0" + day;
	    today = now.getFullYear() + '-' + month + '-' + day;
  
			var requestData = new Object();
			requestData.lower_date= today;
			requestData.upper_date= today;
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
	            		todayOtherFreight += fr;
	            		
	            		var quantity=factory.load_quantity;
	            		todayOtherQuantity += quantity;
	            	}
	            	
	            	
	            	
	            	grandTotalTodayQuantity=todayQuantity+todayOtherQuantity;
	            	grandTotalTodayFreight=todayFreight+todayOtherFreight;
	            	
	            	$('#today_other_total_freight').html(todayOtherFreight.toFixed(2));
	            	$('#today_other_total_quantity').html(todayOtherQuantity.toFixed(2));
	            	
	            	$('#today_grand_total_freight').html(grandTotalTodayFreight.toFixed(2));
	            	$('#today_grand_total_quantity').html(grandTotalTodayQuantity.toFixed(2));
	            	$('#tab').css('display','block');

	            },
	            error: function(error) {
	                console.log(error);
	            }
			});

};