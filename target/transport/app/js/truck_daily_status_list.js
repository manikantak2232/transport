var factory= "";
var tables = "";
var table = "";
var sReading;         	
var table_rows = "";
var factory = "";
var xhr;
var operation;
var association_name;
var zuari_count=0;
var chettinad_count=0;
var ramco_count=0;
var bharathi_count=0;
var deccan_count=0;
var jsw_count=0;
var anjani_count=0;

var zuari_loading=0;
var chettinad_loading=0;
var ramco_loading=0;
var bharathi_loading=0;
var deccan_loading=0;
var jsw_loading=0;
var anjani_loading=0;

var skca_waiting=0;
var hyt_waiting=0;
var all_trucks;


function statusTracking() {
	
	if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
        xhr.abort();    
    }

	$('#table1').css('display','none');
	$('#export_button').css('display','none');
	$('#tab').css('display','none');
	$('#tab1').css('display','none');
	table="";
	table_rows="";
	
	zuari_count=0;
	chettinad_count=0;
	ramco_count=0;
	bharathi_count=0;
	deccan_count=0;
	jsw_count=0;
	anjani_count=0;
	
	zuari_loading=0;
	chettinad_loading=0;
	ramco_loading=0;
	bharathi_loading=0;
	deccan_loading=0;
	jsw_loading=0;
	anjani_loading=0;
	skca_waiting=0;
	hyt_waiting=0;
	
    	var selected_date = $('#selected_date').val();
        xhr=$.ajax({
            type: 'GET',
            url: '/transport/trucks/status/tracking/list/get?'+'selected_date='+selected_date,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function(response) {
            	
            	var truck_list = response;
            	
				for(var i=0; i<truck_list.trucksStatus.length; i++){
					factory = truck_list.trucksStatus[i];
					operation=factory.operation;
					association_name=factory.association_name;
		//			alert(truck_list.trucksStatus[0].waiting_location);
					
								
					if(factory.waiting_location=='skca'){
						skca_waiting=skca_waiting+1;
					}
					if(factory.waiting_location=='hayathnagar'){
						hyt_waiting=hyt_waiting+1;
					}
					if(association_name=='zuari'){
						zuari_count=zuari_count+1;
						
						if(operation=='loading'){
							zuari_loading=zuari_loading+1;
						}
					}
					if(association_name=='chettinad'){
						chettinad_count=chettinad_count+1;
						if(operation=='loading'){
							chettinad_loading=chettinad_loading+1;
						}
					}
					if(association_name=='ramco'){
						ramco_count=ramco_count+1;
						if(operation=='loading'){
							ramco_loading=ramco_loading+1;
						}
					}
					if(association_name=='bharathi'){
						bharathi_count=bharathi_count+1;
						if(operation=='loading'){
							bharathi_loading=bharathi_loading+1;
						}
					}
					if(association_name=='deccan'){
						deccan_count=deccan_count+1;
						if(operation=='loading'){
							deccan_loading=deccan_loading+1;
						}
					}
					if(association_name=='jsw'){
						jsw_count=jsw_count+1;
						if(operation=='loading'){
							jsw_loading=jsw_loading+1;
						}
					}
					if(association_name=='anjani'){
						anjani_count=anjani_count+1;
						if(operation=='loading'){
							anjani_loading=anjani_loading+1;
						}
					}
					
										
					if(operation=='waiting in factory office for loading' | operation=='waiting in branch office for next assignment'){
						operation='idle';
					}
					var tb='';

					var cssAppender = (factory.delay_status==1) ? "background-color:#ff8080" : "";
					table_rows = table_rows + 
					"<tr width=70%>"+
					"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (i+1) + "</td>"+
					"<td id='truck_no' width=8% style='text-align:center; padding-left:3px; padding-right:3px;" + cssAppender + "'>" + factory.truck_number + "</td>"+
					"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + factory.operation_date + "</td>"+	
					"<td width=12% style='text-align:center; padding-left:3px; padding-right:3px'>" + operation + "</td>"+		            						
					
					"</tr>";

				}	            	           		

				var table_header = "<tr width=70%>"+
				"<td width=5% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Sl#" + "</td>"+
				"<td width=8% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Truck Number" + "</td>"+
				"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Last Update Date" + "</td>"+
				"<td width=12% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" +"Status" + "</td>"+
				"</tr>";
				table = "<table width=70% align=center>" + table_header + table_rows + "</table>";

				$('#table1').html(table);
				$('#zuari_count').html(zuari_count);
				$('#chettinad_count').html(chettinad_count);
				$('#ramco_count').html(ramco_count);
				$('#bharathi_count').html(bharathi_count);
				$('#deccan_count').html(deccan_count);
				$('#jsw_count').html(jsw_count);
				$('#anjani_count').html(anjani_count);
				
				$('#zuari_loading').html(zuari_loading);
				$('#chettinad_loading').html(chettinad_loading);
				$('#ramco_loading').html(ramco_loading);
				$('#bharathi_loading').html(bharathi_loading);
				$('#deccan_loading').html(deccan_loading);
				$('#jsw_loading').html(jsw_loading);
				$('#anjani_loading').html(anjani_loading);
				
				$('#skca_waiting').html(skca_waiting);
				$('#hyt_waiting').html(hyt_waiting);
				$('#tab').css('display','block');
				$('#tab1').css('display','block');
				$('#table1').css('display','block');
				$('#export_button').css('display','block');
            },
            error: function(error) {
                console.log(error);
            }
        });
 
};

