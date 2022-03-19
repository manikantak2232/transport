var issued_battery;
var returned_battery;

function test(){  
	
	var check_issued_battery=$.inArray($('#issued_battery_number').val(), issued_battery)
	var check_returned_battery=$.inArray($('#returned_battery_number').val(), returned_battery)
	
	if(check_issued_battery==-1 || check_returned_battery==-1)
		{
		alert("select Battery Number only from Suggestions");
		
		}
	else
		{
		var requestData = new Object();
 		requestData.issued_battery_number=$('#issued_battery_number').val();	
 		requestData.fk_issued_battery_id=$('#fk_issued_battery_id').val();
 		requestData.issued_and_returned_date=$('#issued_and_returned_date').val();
 		requestData.fk_truck_id=$('#truck_id').val();
 		requestData.fk_driver_id=$('#fk_driver_id').val();
 		requestData.returned_battery_number= $('#returned_battery_number').val();
 		requestData.fk_returned_brand_id=$('#fk_returned_brand_id').val();

 		$.ajax({
 			url: '/transport/spareparts/battery/issued/add',
 			type: 'POST',
 			dataType: 'json',
 			data: JSON.stringify(requestData),
 			contentType: 'application/json; charset=utf-8',
 			success: function(response) {	            		 	         
 				$("#Message").html("Inserted Successfully");
 			},
 			error: function(error) {
 				console.log(error);
 			}
 		});
		
		}
	 }

function addDropDownOptions5(brand){
	for(var i=0;i<brand.length;++i){
		addOption5(document.add.issued_brand_name,brand[i].brand_name,brand[i].fk_battery_brand_id);
	}
}
function addOption5(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list5(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/spareparts/battery/inventory/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.brandNames;
				addDropDownOptions5(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}

function testChange7(){
	var selectedTruck=$("#issued_brand_name :selected").text();
	var selectedTruckId=$("#issued_brand_name :selected").val();
	$("#fk_issued_battery_id").val(selectedTruckId);
}

function addDropDownOptions7(brand){
	for(var i=0;i<brand.length;++i){
		addOption7(document.add.returned_brand_name,brand[i].brand_name,brand[i].fk_battery_brand_id);
	}
}
function addOption7(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list7(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/spareparts/battery/running/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.brandNames;
				addDropDownOptions7(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}

function testChange8(){
	var selectedTruck=$("#returned_brand_name :selected").text();
	var selectedTruckId2=$("#returned_brand_name :selected").val();
	$("#fk_returned_brand_id").val(selectedTruckId2);
}

function addOption_list4(){
	  $(document).ready(function() {
	  
		  if($('#issued_brand_name :selected').val() != 'Select'){

					var requestData = new Object();
					requestData.fk_battery_brand_id=$('#fk_issued_battery_id').val();	
					
					$.ajax({
						url: '/transport/spareparts/battery/inventory/number/get',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
						issued_battery=response.batteryNumbers; 
						
		        		 if(issued_battery==''){
		        	  	alert("No Battery Available With that Brand Name");	
		        	  	}  
		        	  	
		        	  	$("#issued_battery_number").autocomplete({
		        		  source:issued_battery,
		        		
		        		  minLength: 1
		        	
		        		});
		        	       	
		          },
		          error: function(error) {
		              console.log(error);
		          }
		        
				});
		    		
		     }

		  $.ui.autocomplete.filter = function (array, term) {
		        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
		        return $.grep(array, function (value) {
		            return matcher.test(value.label || value.value || value);
		        });
		    };
	 
	});
}
	  



function addOption_list6(){
	  $(document).ready(function() {
 
		  if($('#returned_brand_name :selected').val() != 'Select' ){

					var requestData = new Object();
					requestData.fk_battery_brand_id=$('#fk_returned_brand_id').val();	
				
					$.ajax({
						url: '/transport/spareparts/battery/running/number/get',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
		        	  	returned_battery=response.batteryNumber; 
		        	  	 if(returned_battery==''){
				        	  	alert("No Battery Available With that Brand Name");	
				        }
		        	  	$("#returned_battery_number").autocomplete({
		        		  source:returned_battery ,
		        		  minLength: 1
		    
		        		});
		        	       	
		          },
		          error: function(error) {
		              console.log(error);
		          }
				});
		    		
		     }

		  $.ui.autocomplete.filter = function (array, term) {
		        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
		        return $.grep(array, function (value) {
		            return matcher.test(value.label || value.value || value);
		        });
		    };
	 
	});
}

function currentDate() {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!

	  var yyyy = today.getFullYear();
	  if(dd<10){
	      dd='0'+dd;
	  } 
	  if(mm<10){
	      mm='0'+mm;
	  } 
	  var today =yyyy+'/'+mm+'/'+ dd;
$("#issued_and_returned_date").val(today);

	  }