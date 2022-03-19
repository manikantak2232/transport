var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;

$(document).ready(function (){ 
	$('#btTest').click(function (){
		
		if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
            xhr.abort();    
        }
		
		table="";
		table_rows = "";
		factory= "";
		tables = "";
		$('#dataTab').css('display','none');
		$('#export_button').css("display", 'none');

		var requestData = new Object();
		requestData.lower_date= $('#lower_date').val();
		requestData.upper_date= $('#upper_date').val();	
		requestData.truck_no=  $('#truck_list option:selected').text();	
		xhr=$.ajax({
			url: '/transport/factory/fuel/date/get',
			type: 'POST',
			data: JSON.stringify(requestData),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {

				var factoriesList = response;
				for(var i=0; i<factoriesList.Factories.length; i++){
					factory = factoriesList.Factories[i];
					var milege;
					if(factory.closing_meter_reading==0){
						milege='';
					}
					else{
						milege=((factory.closing_meter_reading-factory.starting_meter_reading)/factory.fuel_quantity).toFixed(2);
					}

					sReading=factory.starting_meter_reading_uploaded_image_url ;

					table_rows = table_rows + 
					"<tr width=100%>"+
					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) +  "</td>"+
					"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date + "</td>"+	
					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+	
					"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+		            						
					"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_quantity + "</td>"+
					"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_rate + "</td>"+
					"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_station_name +"</td>"+
					"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.starting_meter_reading+ "</td>"+
					"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.closing_meter_reading+ "</td>"+
					"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + milege + "</td>"+
					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'> <input type='button' id='but' onclick='fun(\""+factory.pk_factory_fuel_id+"\",\""+factory.fuel_station_name+"\",\""+factory.truck_number+"\",\""+factory.driver_name+"\",\""+factory.fk_truck_id+"\")' value='update'></td>"+
					"</tr>";

				}	            	           		

				var table_header = "<tr width=100%>"+
				"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
				"<td width=12% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Driver Name" + "</td>"+
				"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Fuel Quantity" + "</td>"+
				"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Fuel Rate" + "</td>"+
				"<td width=12%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Fuel Station" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Starting Meter Reading" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closing Meter Reading" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Mileage" + "</td>"+
				"</tr>";
				table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

				$('#dataTab').html(table);

				if(table_rows!=''){

					$('#dataTab').css('display','block');
					$('#export_button').css("display", 'block');
				}

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
});

function fun(factory_fuel_id,fuel_station_name,truck,driver,truck_id){
	window.open("/app/views/update_factory_fuel.html?"+
		"factory_fuel_id="+factory_fuel_id+"&fuel_station_name="+fuel_station_name+"&truck="+truck+"&driver="+driver+"&truck_id="+truck_id);	
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

