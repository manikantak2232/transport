var table = "";
var table_rows = "";
var factory= "";
var tables = "";
function getUnloadReport() {

	$('#dataTab').css('display','none');
	$('#export_button').css("display", 'none');
	table="";
	table_rows = "";
	factory= "";
	tables = "";
	$.ajax({
		url: '/transport/factory/dispatch/unload/report/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			var factory= "";
			var tables = "";
			var factoriesList = response;
			for(var i=0; i<factoriesList.unloadReport.length; i++){
				factory = factoriesList.unloadReport[i];

				if(factory.closing_meter_reading==null){
					factory.closing_meter_reading=0;
				}
				if(factory.invoice_number==null){
					factory.invoice_number="";
				}

				if(factory.starting_meter_reading==null){
					factory.starting_meter_reading=0;
				}

				table_rows = table_rows + 

				"<tr width=100% >"+
				"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.date + "</td>"+		            						
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.truck_number + "</td>"+
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.invoice_number + "</td>"+
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.starting_meter_reading + "</td>"+	
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + factory.closing_meter_reading + "</td>"+
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + (factory.closing_meter_reading-factory.starting_meter_reading) + "</td>"+	
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
				"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + '' + "</td>"+
				"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'> <input type='button' id='outside_but' onclick='fun(\""+factory.id+"\")' value='update'></td>"+

				"</tr>";

			}	          

			var table_header = 
				"<tr width=100%>"+
				"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Date" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Truck Number" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Invoice Number" + "</td>"+
				"<td width=5%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"  + "Starting Meter Reading" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Closing Meter Reading" + "</td>"+
				"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Kms" + "</td>"+
				"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Unload Location" + "</td>"+
				"<td width=8%  style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "Checked Kms" + "</td>"+


				"</tr>";


			table = "<table width=95% align=center >" + table_header + table_rows + "</table>";

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
}

function fun(factory_dispatch_id){
	window.open("/app/views/update_unload_report.html?"+
		"factory_dispatch_id="+factory_dispatch_id);
	
}
