var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;
var array=[];
var split_type=[];
var spares_expense='';
var tyres_expense='';
var oil_expense='';
var direct_expense='';
var recoup_tyre_expense=0;


function getMaintenanceCost() {
	
	if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
        xhr.abort();    
    }
	
	$('#table1').css('display','none');
	$('#export_button').css('display','none');
	table="";
	table_rows="";
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date= $('#upper_date').val();
	
	xhr=$.ajax({	
		url: '/transport/trucks/maintenance/cost/get',
		type: 'POST',
		data: JSON.stringify(requestData),
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {

			var truck_list = response;
			for(var i=0; i<truck_list.trucksCost.length; i++){
				factory = truck_list.trucksCost[i];
				
				var maintenance=factory.maintenance;
				array=maintenance.split(',');
				
				spares_expense='';
				tyres_expense='';
				oil_expense='';
				direct_expense='';
				recoup_tyre_expense=0;
				
				for(var j=0; j<array.length; j++){
					
									
					split_type=array[j].split('-');
					if(split_type[0]=='spare parts expenses'){
						spares_expense=split_type[1];
					}
					if(split_type[0]=='tyre expenses'){
						tyres_expense=split_type[1];
					}
					if(split_type[0]=='oil expenses'){
						oil_expense=split_type[1];
					}
					if(split_type[0]=='direct maintenance'){
						direct_expense=split_type[1];
					}
					if(split_type[0]=='recoup tyre expenses'){
						recoup_tyre_expense=split_type[1];
					}
					
				}
				
				table_rows = table_rows + 
				"<tr width=70%>"+
				"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
				"<td width=8% style='text-align:center; padding-left:3px; padding-right:3px;'>" + factory.truck_number + "</td>"+
				"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + spares_expense + "</td>"+	
				"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + (+tyres_expense + +recoup_tyre_expense) + "</td>"+	
				"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + oil_expense + "</td>"+	
				"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + direct_expense + "</td>"+	
				"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + factory.cost + "</td>"+		            						

				"</tr>";

			}	            	           		

			var table_header = "<tr width=70%>"+
			"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Spares Expenses" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Tyres Expense" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Oil Expenses" + "</td>"+
			"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Direct Expenses" + "</td>"+
			"<td width=12% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Cost" + "</td>"+
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

