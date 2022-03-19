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
		requestData.fk_truck_id= $('#truck_id').val();	
		xhr=$.ajax({
			url: '/transport/trucks/maintenance_expenditure/get/date',
			type: 'POST',
			data: JSON.stringify(requestData),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {

				var factoriesList = response;
				if(factoriesList.maintenance=='' | factoriesList.maintenance==undefined){
					alert('no data found..');
				}
				if(factoriesList.maintenance!='' | factoriesList.maintenance!=undefined){
					for(var i=0; i<factoriesList.maintenance.length; i++){
						factory = factoriesList.maintenance[i];


						table_rows = table_rows + 
						"<tr width=100%>"+
						"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
						"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date + "</td>"+	
						"<td width=8% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+	
						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.item_names + "</td>"+		            						
						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.cost + "</td>"+
						"<td width=7% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.expenditure_type + "</td>"+
						"<td width=12% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.remarks + "</td>"+
						"</tr>";

					}	            	           		

					var table_header = "<tr width=100%>"+
					"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
					"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
					"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
					"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Item Names" + "</td>"+
					"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Cost" + "</td>"+
					"<td width=7% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Type" + "</td>"+
					"<td width=12% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Remarks" + "</td>"+
					"</tr>";
					table = "<table width=95% align=center>" + table_header + table_rows + "</table>";

					$('#dataTab').html(table);

					if(table_rows!=''){

						$('#dataTab').css('display','block');
						$('#export_button').css("display", 'block');
					}
				}

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
});

