/*var suggestion;
var x;
var truck_details;
var driver_name;
var driver_id;
var truck_id;
var fk_association_id;*/
var balance;
var dispatch_id;
var truck_number;
var loading_driver_id;
var association_name;
var manager_type;

var startingMeterReadingImageBytes=null;
var startingMeterReadingImageType=null;

var openFilestartingMeterReading = function(event) {
    var input = event.target;

    var reader_starting_meter_reading = new FileReader();
    reader_starting_meter_reading.onload = function(){
      var startingMeterReadingDataURL = reader_starting_meter_reading.result;
      var starting_meter_reading_output = document.getElementById('starting_meter_reading_output');
      starting_meter_reading_output.src = startingMeterReadingDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      startingMeterReadingImageBytes = startingMeterReadingDataURL.split(";base64,")[1] ;
      startingMeterReadingImageType = startingMeterReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;
      alert(startingMeterReadingImageBytes);
      alert(startingMeterReadingImageType);
    };
    reader_starting_meter_reading.readAsDataURL(input.files[0]);
  };

  function queryString(){
	  $(document).ready(function() {
		  
		  dispatch_id=querystring('dispatch_id');
		  truck_number=querystringTruck('truck_number');
		  $('#truck_number').val(truck_number);
		  loading_driver_id=querystringDriver('loading_driver_id');
		  driver_balance();
	 
	});
 }
  
  function querystring(key) {
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
 
 function querystringTruck(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
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
$("#dispatch_date").val(today);

	  }
  
var driver_advance;
var delivery_number;
var po_number;
var delivery_number2;
var po_number2;
var invoice_number2;
var freight_number2;
var load_quantity2;
var invoice_number;
var estimated_km;
var starting_meter_reading;

$(document).ready(function (){
	$('#btnAdd').click(function () {
		var x = document.getElementById('invoice_div');
		if (x.style.display === 'none') {
		    x.style.display = 'block';
			$('#load_quantity_div').css("display", 'block');
			$('#freight_div').css("display", 'block');
			if(association_name=='zuari cement ltd (sithapuram)' || association_name=='zuari'){
				$('#numbers2').css("display", 'block');
			}
			$('#btnAdd').val("Hide");
			
		} else {
		    x.style.display = 'none';
			$('#load_quantity_div').css("display", 'none');
			$('#freight_div').css("display", 'none');
			$('#numbers2').css("display", 'none');
			$('#btnAdd').val("Add Invoice");
		}
		
		
	});	
});



/*$(document).ready(function (){ 
var var1;

$("#btnAdd").bind("click", function () {
    var div = $("<div />");
    div.html(GetDynamicTextBox(""));
    $("#TextBoxContainer").append(div);
   
});
$("#insert").bind("click", function () {
    var values = "";
    $("input[name=DynamicTextBox]").each(function () {
        values += $(this).val() + "\n";
    });
 //   var1=values+$('#tyre_number').val();
    alert(values);
});


$("body").on("click", ".remove", function () {
    $(this).closest("div").remove();
});
});*/

function addDispatchDetails(){  
	$('#loading').show();

	if(startingMeterReadingImageBytes==null){
		startingMeterReadingImageBytes="";
	}
	if(startingMeterReadingImageType==null){ 
		startingMeterReadingImageType="";
	}
	
	driver_advance=$('#advance').val();
	estimated_km=$('#estimated_km').val();
	starting_meter_reading=$('#starting_meter_reading').val();
	delivery_number=$('#delivery_number').val();
	po_number=$('#po_number').val();
	
	delivery_number2=$('#delivery_number2').val();
	po_number2=$('#po_number2').val();
	invoice_number2=$('#invoice_number2').val();
	load_quantity2=$('#load_quantity2').val();
	freight2=$('#freight2').val();
	invoice_number=$('#invoice_number').val();
	
	if(driver_advance==undefined | driver_advance==''){ 
		driver_advance=0;
	}
	if(estimated_km==undefined | estimated_km==''){ 
		estimated_km=0;
	}
	if(starting_meter_reading==undefined | starting_meter_reading==''){ 
		starting_meter_reading=0;
	}
	if(delivery_number==undefined){ 
		delivery_number='';
	}
	if(po_number==undefined){ 
		po_number='';
	}
	if(invoice_number2==undefined){ 
		invoice_number2='';
	}
	if(delivery_number2==undefined){ 
		delivery_number2='';
	}
	if(po_number2==undefined){ 
		po_number2='';
	}
	if(load_quantity2==undefined | load_quantity2==''){ 
		load_quantity2=0;
	}
	if(freight2==undefined | freight2==''){ 
		freight2=0;
	}
	if(invoice_number==undefined){ 
		invoice_number='';
	}
	
		var requestData = new Object();
		requestData.factory_dispatch_id=dispatch_id;
		requestData.invoice_number=invoice_number;
		requestData.start_location=$('#start_location').val();
		requestData.unload_location=$('#unload_location').val();
		requestData.unload_location_id=$("#unload_location_id").val();
		requestData.unload_location_name=$('#unload_location_name').val();
		requestData.type_of_cement=$('#type_of_cement').val();
		requestData.dispatch_date=$('#dispatch_date').val();
		requestData.estimated_km=estimated_km;
		requestData.starting_meter_reading=starting_meter_reading;
		requestData.load_quantity=$('#load_quantity').val();
		requestData.freight=$('#freight').val();
		requestData.advance= driver_advance;
		requestData.delivery_number= delivery_number;
		requestData.po_number= po_number;
		requestData.delivery_number2= delivery_number2;
		requestData.po_number2= po_number2;
		requestData.invoice_number2= invoice_number2;
		requestData.load_quantity2= load_quantity2;
		requestData.freight2= freight2;
		requestData.starting_meter_reading_image_bytes_string = startingMeterReadingImageBytes;
		requestData.starting_meter_reading_image_type = startingMeterReadingImageType;
		

		$.ajax({
			url: '/transport/factory/dispatch/update/intially',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	
				$('#loading').hide();
				$("#Message").html(response.message);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}


function userDetails(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/login/user/details/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var userDetails=response.userDetails;
				association_name=userDetails.association_name;
				manager_type=userDetails.manager_type;
			//	factory(manager_type);
				$('#start_location').val(association_name);
				if(association_name=='zuari cement ltd (sithapuram)' || association_name=='zuari'){
					$('#numbers').css("display", 'block');
				}
				getUnloadLocation(userDetails.association_id);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
$(document).ready(function(){
	$('#estimated_km').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }
	
	    e.preventDefault();
	    return false;
	});
	

	$('#starting_meter_reading').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

	$('#load_quantity').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

	$('#freight').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

	$('#advance').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

});

function driver_balance(){
	  $(document).ready(function() {
	    	var fk_driver_id = loading_driver_id;
	        $.ajax({
	            type: 'GET',
	            url: '/transport/driver/balance/get?'+'fk_driver_id='+fk_driver_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            	var Details = response.DriverDetails;
	            	balance=Details.balance;
	            	$('#balance').val(balance);	   
	            	
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	  });
	}
/*
function loc(){
	  
	if($("#location_list :selected").text() == 'hyderabad') {		
		$('#unload_loc_id').css("display", 'block');
		
	}else{
		$('#unload_loc_id').css("display", 'none');

	}
};*/

/*function GetDynamicTextBox(value) {
    return '<input name = "DynamicTextBox" placeholder="Enter Tyre number" type="text" value = "' + value + '" />&nbsp;' +
    //		'<input name = "DynamicTextBox1" placeholder="Enter front or housing" type="text" value = "' + value + '" />&nbsp;' +
            '<input type="button" value="Remove" class="remove" style="position:absolute;margin-left:100px;"/><br><br>'
}	
*/