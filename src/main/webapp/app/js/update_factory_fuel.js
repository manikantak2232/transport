var fuel_station_loaded=0;
var truck_loaded=0;
var driver_loaded=0;
var truck_id=null;

function querystringName(key) {
	var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	var r=[], m;
	while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	return r[0];
}

function querystringId(key) {
	var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	var r=[], m;
	while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	return r[0];
}

function querystringTruck(key) {
	var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	var r=[], m;
	while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	return r[0];
}

function querystringTruckId(key) {
	var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	var r=[], m;
	while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	return r[0];
}

function querystringDriver(key) {
	var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	var r=[], m;
	while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	return r[0];
}


var factory_fuel_id=null;
var fuel_station_name=null;
var truck_no=null;
var driver=null;

function getFuel(){
	if(truck_loaded==1 && fuel_station_loaded==1 && driver_loaded==1){
	    	factory_fuel_id=querystringId('factory_fuel_id');
	    	fuel_station_name=querystringName('fuel_station_name');
	    	truck_no=querystringName('truck');
	    	driver=querystringName('driver');
	    	truck_id=querystringTruckId('truck_id');

	        $.ajax({
	            type: 'GET',
	            url: '/transport/factory/fuel/get?'+'factory_fuel_id='+factory_fuel_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var  numbers = response.FuelReport ;	            	
	            	$.each( numbers, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});

	            	setSelectedIndex1(document.getElementById("truck_list"),truck_no);
	            	setSelectedIndex1(document.getElementById("driver_list"),decodeURI(driver));
	            	setSelectedIndex1(document.getElementById("fuel_list"),decodeURI(fuel_station_name));
	            },
	          error: function(error) {
	                console.log(error);
	            }
	        });
	    };
};
	  $(document).ready(function (){ 
		  
			$('#btTest2').click(function () {
				var requestData = new Object();
				requestData.factory_fuel_id= factory_fuel_id;
				requestData.truck_no= $('#truck_list option:selected').text();
				requestData.fk_driver_id=$('#fk_driver_id').val();
				requestData.fuel_quantity=$('#fuel_quantity').val();
				requestData.fuel_rate=$('#fuel_rate').val();
				requestData.starting_meter_reading=$('#starting_meter_reading').val();
				requestData.fk_fuel_station_id=$('#fk_fuel_station_id').val();
				requestData.closing_meter_reading=$('#closing_meter_reading').val();
				requestData.date=$('#date').val();
				$.ajax({
					url: '/transport/factory/fuel/update',
					type: 'POST',
					dataType: 'json',
					data: JSON.stringify(requestData),
					contentType: 'application/json; charset=utf-8',
		            success: function(response) {
		            	$("#Message").html(response.message);		            	
		            },
		            error: function(error) {
		                console.log(error);
		            }
				});
			});
		});
	  
	  function setSelectedIndex1(s, v) {
			for ( var i = 0; i < s.options.length; i++ ) {
				if ( s.options[i].text == v ) {
					s.options[i].selected = true;
					return;
				}
			}
		}
	  
/*	  function setSelectedIndex2(s, v) {
			for ( var i = 0; i < s.options.length; i++ ) {
		//		alert(s.options[i].text);
				if ( s.options[i].text == v ) {
					s.options[i].selected = true;
					return;
				}
			}
		}
	  
	  function setSelectedIndex3(s, v) {
			for ( var i = 0; i < s.options.length; i++ ) {
				if ( s.options[i].text == v ) {
					s.options[i].selected = true;
					return;
				}
			}
		}*/