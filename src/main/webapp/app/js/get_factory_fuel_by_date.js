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
		requestData.truck_no= $('#truck_list option:selected').text();	
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
					"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date + "</td>"+	
					"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+	
					"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.driver_name + "</td>"+		            						
					"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_quantity + "</td>"+
					"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_rate + "</td>"+
					"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.fuel_station_name +"</td>"+
					"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.starting_meter_reading+ "</td>"+
					"<td width=8%  style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.closing_meter_reading+ "</td>"+
					"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + milege + "</td>"+	
					"<td><img id='myImgNew"+i+"' onclick='large1("+i+")' style='width:40px;height:40px;'></td>"+
					"<td><img id='myImg"+i+"' onclick='large("+i+")' style='width:40px;height:40px;'></td>"+
					//			"<td width=8% style='display:none; padding-left:3px; padding-right:3px'>" + im() + "</td>"+
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
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Pump Reading" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Meter reading" + "</td>"+
				"</tr>";
				table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

				$('#dataTab').html(table);

				if(table_rows!=''){

					$('#dataTab').css('display','block');
					$('#export_button').css("display", 'block');
				}
				//   	var _img = new Array();
				//	var newImg = new Array();

				for(var i=0; i<factoriesList.Factories.length; i++){
					factory = factoriesList.Factories[i];

					var _img = document.getElementById('myImg'+i+'');
					var _imgNew = document.getElementById('myImgNew'+i+'');	

					$('#myImg'+i+'').css({

						borderRadius: '5px',
						cursor: 'pointer',
						transition: '0.3s'

					});

					$('#myImg'+i+'').hover(function(e) { 
						$(this).css("opacity",e.type === "mouseenter"?"0.7":"1") 
					}) ;
					
					$('#myImgNew'+i+'').css({

						borderRadius: '5px',
						cursor: 'pointer',
						transition: '0.3s'

					});

					$('#myImgNew'+i+'').hover(function(e) { 
						$(this).css("opacity",e.type === "mouseenter"?"0.7":"1") 
					}) ;
					
					if(factory.starting_meter_reading_uploaded_image_url==''){
						_img.style.display='none';
					}
					else{
						_img.src = factory.starting_meter_reading_uploaded_image_url;
					}
					
					if(factory.petrol_pump_reading_image_url==''){
						_imgNew.style.display='none';
					}
					else{
						_imgNew.src = factory.petrol_pump_reading_image_url;
					}
					
				};

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
});

function im(){
	var _img = document.getElementById('myImg');
	alert(_img);
	var newImg = new Image;
	newImg.onload = function() {
		_img.src = this.src;
	} 
	newImg.src = sReading;

} ;

function large(i){

	var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById('myImg'+i+'');
	var modalImg = document.getElementById('img01');
	
	var captionText = document.getElementById("caption");
	img.onclick = function(){
		//		modal.style.width = "50%";
		modalImg.style.width = "50%";
		modalImg.style.height = "90%";
		modalImg.style.margin = "auto";
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
		modal.style.display = "none";
	}

}


function large1(i){

	var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById('myImgNew'+i+'');
	var modalImg = document.getElementById('img01');
	
	var captionText = document.getElementById("caption");
	img.onclick = function(){
		//		modal.style.width = "50%";
		modalImg.style.width = "50%";
		modalImg.style.height = "90%";
		modalImg.style.margin = "auto";
		modal.style.display = "block";
		modalImg.src = this.src;
		captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
		modal.style.display = "none";
	}

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
