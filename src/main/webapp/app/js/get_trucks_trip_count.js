var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;
var array=[];

var association_name;
var zuari_count=0;
var chettinad_count=0;
var ramco_count=0;
var bharathi_count=0;
var deccan_count=0;
var jsw_count=0;
var anjani_count=0;
var all_trucks;

function getAllTrucks(){
	$.ajax({
		url: '/transport/trucks/alltrucks/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			all_trucks=response.TruckDetails;
			getTrucksTripCount();

		},
		error: function(error) {
			console.log(error);
		}
	});
} ;



function getTrucksTripCount() {

	if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
		xhr.abort();    
	}

	zuari_count=0;
	chettinad_count=0;
	ramco_count=0;
	bharathi_count=0;
	deccan_count=0;
	jsw_count=0;
	anjani_count=0;

	$('#table1').css('display','none');
	$('#export_button').css('display','none');
	table="";
	truck_list="";
	table_rows="";

	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();

	xhr=$.ajax({	
		url: '/transport/trucks/trip/count/get',
		type: 'POST',
		data: JSON.stringify(requestData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {

			var truck_list = response;

			var props = ['id', 'name'];
			var result2=truck_list.tripCount;

			var result = all_trucks.filter(function(o1){
				// filter out (!) items in result2
				return !result2.some(function(o2){
					return o1.truck_number === o2.truck_number;          // assumes unique id
				});
			});


			var row_count=truck_list.tripCount.length;

			for(var i=0; i<truck_list.tripCount.length; i++){
				factory = truck_list.tripCount[i];

				var factory_name=factory.factory_name;
				array=factory_name.split(',');

				zuari_count=0;
				chettinad_count=0;
				ramco_count=0;
				bharathi_count=0;
				deccan_count=0;
				jsw_count=0;
				anjani_count=0;

				for(var j=0; j<array.length; j++){

					if(array[j]=='zuari cement ltd (sithapuram)' || (array[j]=='zuari')){
						zuari_count=zuari_count+1;
					}
					if(array[j]=='chettinad cement ltd(kallur plant)' || (array[j]=='chettinad')){
						chettinad_count=chettinad_count+1;
					}
					if(array[j]=='ramco cement pvt ltd' || (array[j]=='ramco')){
						ramco_count=ramco_count+1;
					}
					if(array[j]=='kalburgi cement pvt ltd(bharathi)' || (array[j]=='bharathi')){
						bharathi_count=bharathi_count+1;
					}
					if(array[j]=='deccan cement ltd' || (array[j]=='deccan')){
						deccan_count=deccan_count+1;
					}
					if(array[j]=='pavani road lines' || (array[j]=='jsw') ||  (array[j]=='jsw cement ltd') ){
						jsw_count=jsw_count+1;
					}
					if(array[j]=='anjani portland cement ltd' || (array[j]=='anjani')){
						anjani_count=anjani_count+1;
					}

				}

				table_rows = table_rows + 
				"<tr width=70%>"+
				"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px;'>" + factory.truck_number + "</td>"+
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + zuari_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + chettinad_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + ramco_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + bharathi_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + deccan_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + jsw_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + anjani_count + "</td>"+	
				"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + factory.trip_count + "</td>"+	

				"</tr>";

			}	
			if(result!=''){
				for(var i=0; i<result.length; i++){
					var fact = result[i];

					table_rows = table_rows + 
					"<tr width=70%>"+
					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (row_count+i+1) + "</td>"+
					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px;'>" + fact.truck_number + "</td>"+
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	
					"<td width=9% style='text-align:center; padding-left:3px; padding-right:3px'>" + 0 + "</td>"+	

					"</tr>";
				}
			} ;


			var table_header = "<tr width=70%>"+
			"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
			"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Zuari" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Chettinad" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Ramco" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Bharathi" + "</td>"+
			"<td width=9% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Deccan" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Jsw" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Anjani" + "</td>"+
			"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Total Trips" + "</td>"+
			"</tr>";
			table = "<table width=70% align=center>" + table_header + table_rows + "</table>";

			$('#table1').html(table);

			$('#table1').css('display','block');
			$('#export_button').css('display','block');
		},
		error: function(error) {
			console.log(error);
		}
	});

};

