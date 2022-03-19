var dataSet=[];
var dataSe=[];
var Factories;
var total_quantity=0;
var total_freight=0;

function fu(ac_name){
	alert(ac_name);
}

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"date-uk-pre": function ( a ) {
	var ukDatea = a.split('-');
	return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	},

	"date-uk-asc": function ( a, b ) {
	return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"date-uk-desc": function ( a, b ) {
	return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
	} );
var $datatable = $('#datatable-checkbox');

$datatable.dataTable({
	'order': [[ 1, 'asc' ]],
	'columnDefs': [
		{ orderable: false, targets: [0] }
		]
});
$datatable.on('draw.dt', function() {
	$('checkbox input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	});
});

$(document).ready(function() {
	if ($("input.flat")[0]) {
		$(document).ready(function () {
			$('input.flat').iCheck({
				checkboxClass: 'icheckbox_flat-green',
				radioClass: 'iradio_flat-green'
			});
		});
	}
});

function getDispatchFuel(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_quantity=0;
	total_freight=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	
	$.ajax({
		url: '/transport/factory/dipatch/fuel/link/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			
			$(".overlay").hide();
			Factories = response.Factories;
			
			for(var i=0; i<Factories.length; i++){
				factory = Factories[i];
				
					dataSe=[];
					dataSe.push('<input type="checkbox" class="flat" name="sport" value='+factory.pk_factory_dispatch_id+'>');
					dataSe.push('');
					dataSe.push(factory.loading_date);
					dataSe.push(factory.truck_number);
					dataSe.push(factory.association_name);	
					dataSe.push(factory.unload_location);
					dataSe.push(factory.invoice_number);
					dataSet.push(dataSe);
				
			}
			
			fun();
			$('#submit_button').css('display','block');

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {
	 $("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></tfoot>');

	 var t =  $('#example').DataTable( {
        data: dataSet,
        destroy: true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
  //      "bSort" : false,
        dom: 'lBfrtip',
        columns: [
        	{ title: " " },
			{ title: "S.No" },
			{ title: "Date", "sType": "date-uk"  },
			{ title: "VEH. NO" },
			{ title: "Factory Name" },
			{ title: "Place" },
            { title: "Inv No." }
            
        ],
        "order": [[ 1, 'asc' ]]
       
    } );
	 
	 t.on( 'order.dt search.dt', function () {
	        t.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	            t.cell(cell).invalidate('dom');
	        } );
	    } ).draw();

};

var petrolPumpReadingImageBytes=null;
var petrolPumpReadingImageType=null;

var openFilePetrolPumpReading = function(event) {
    var input = event.target;

    var reader_petrol_pump_reading = new FileReader();
    reader_petrol_pump_reading.onload = function(){
      var petrolPumpReadingDataURL = reader_petrol_pump_reading.result;
      var petrol_pump_reading_output = document.getElementById('petrol_pump_reading_output');
      petrol_pump_reading_output.src = petrolPumpReadingDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      petrolPumpReadingImageBytes = petrolPumpReadingDataURL.split(";base64,")[1] ;
      petrolPumpReadingImageType = petrolPumpReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;

    };
    reader_petrol_pump_reading.readAsDataURL(input.files[0]);
  };

  var startingMeterReadingImageBytes=null;
  var startingMeterReadingImageType=null;

  var openFilestartingMeterReading = function(event) {
      var input = event.target;

      var reader_starting_meter_reading = new FileReader();
      reader_starting_meter_reading.onload = function(){
        var startingMeterReadingDataURL = reader_starting_meter_reading.result;
        var starting_meter_reading_output = document.getElementById('starting_meter_reading_output');
        starting_meter_reading_output.src = startingMeterReadingDataURL;
        
//        document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
        startingMeterReadingImageBytes = startingMeterReadingDataURL.split(";base64,")[1] ;
        startingMeterReadingImageType = startingMeterReadingDataURL.split(";base64,")[0].split("data:image/")[1] ;

      };
      reader_starting_meter_reading.readAsDataURL(input.files[0]);
    };
    

/*  $(function(){           
      if (!Modernizr.inputtypes.date) {
          $('input[type=date]').datepicker({
                dateFormat : 'yyyy-mm-dd'
              }
           );
      }
  });*/
/*  $(document).ready(function (){ 
	  $(function() {
		     $( "#date" ).datepicker({ dateFormat: 'yyyy-mm-dd'}); 
		});
  });*/

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
	  var today =yyyy+'-'+mm+'-'+ dd;
$("#date").val(today);

	  }

var closing_meter_reading;

/*$(window).on('scroll', function() {
    var scrollTop = $(this).scrollTop();

    $('#du').each(function() {
        var topDistance = $(this).offset().top;

        if ( (topDistance+100) < scrollTop ) {
            alert( $(this).text() + ' was scrolled to the top' );
        }
    });
});*/

function addDetails() {
			$('#loading').show();
			
			if(petrolPumpReadingImageBytes==null){
				petrolPumpReadingImageBytes="";
			}
			if(petrolPumpReadingImageType==null){ 
				petrolPumpReadingImageType="";
			}
			if(startingMeterReadingImageBytes==null){
				startingMeterReadingImageBytes="";
			}
			if(startingMeterReadingImageType==null){ 
				startingMeterReadingImageType="";
			}
			if(closing_meter_reading==undefined | closing_meter_reading==''){ 
				closing_meter_reading=0;
			}
			
			var favorite = [];
	        $.each($("input[name='sport']:checked"), function(){          
	            favorite.push($(this).val());
	        });
			
			var requestData = new Object();
			requestData.truck_no= $('#truck_list option:selected').text();
			requestData.fk_driver_id= $('#fk_driver_id').val();
//			requestData.advance= $('#advance').val();
			requestData.fuel_quantity = $('#fuel_quantity').val();
			requestData.fuel_rate=$('#fuel_rate').val();
			requestData.starting_meter_reading=$('#starting_meter_reading').val();
			requestData.closing_meter_reading=closing_meter_reading;
	//		requestData.mileage=$('#mileage').val();
			requestData.fk_fuel_station_id=$('#fk_fuel_station_id').val();
			requestData.date=$('#date').val();
			requestData.advance=$('#advance').val();
			requestData.petrol_pump_reading_image_bytes_string = petrolPumpReadingImageBytes;
			requestData.petrol_pump_reading_image_type = petrolPumpReadingImageType;
			requestData.starting_meter_reading_image_bytes_string = startingMeterReadingImageBytes;
			requestData.starting_meter_reading_image_type = startingMeterReadingImageType;
			requestData.dispatch_ids=favorite.join(",");
			
			$.ajax({
	        	url: '/transport/factory/fuel/add',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	
	            	$('#loading').hide();
	            	if(response.message=='inserted successfully'){
	            		alert(response.message+" Voucher : PURF:"+response.primary_id);
	            	}else{
	            		alert(response.message);
	            	}
	            	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    };

function calculate() {
	  var fuel_quantity=document.getElementById("fuel_quantity").value;
	  var fuel_rate=document.getElementById("fuel_rate").value;
	  var amount=document.getElementById("amount");
	  var myResult=fuel_quantity*fuel_rate;
	  amount.value=myResult;
}

function addDropDownOptions2(trucks){
	for(var i=0;i<trucks.length;++i){
		addOption2(document.add.truck_list,trucks[i].truck_number,trucks[i].pk_trucks_id);
	}
}
function addOption2(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list2(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/trucks/all/other/trucks/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var trucks=response.TruckDetails;
				addDropDownOptions2(trucks);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
