var table_rows='';
var table='';
var factoriesList;

function addDropDownOptions(drivers){
	  for(var i=0;i<drivers.length;++i){
		  addOption(document.add.driver_list,drivers[i].driver_name,drivers[i].driver_id);
	  }
	  }
  function addOption(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  
/*	  table_rows=table_rows+
	  	"<tr>"+
	  		"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + text + "</td>"+
		"</tr>";*/	  
	  selectbox.options.add(optn);
	  }
  
  function addOption_list(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/driver/all/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  var drivers=response.DriverDetails;
        	  factoriesList=response;
        	  addDropDownOptions(drivers);
        	  
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
}
  function testChange(){
	  var selectedDriver=$("#driver_list :selected").text();
	  var selectedDriverId=$("#driver_list :selected").val();
	  $("#fk_driver_id").val(selectedDriverId);
	//  alert(table);
	//  $('#data').html('<table>'+table_rows+'</table>');
  }
  
 /* var factory;
  
  for(var i=0; i<factoriesList.Factories.length; i++){
		factory = factoriesList.Factories[i];
		
		
		var total=((factory.load_quantity)*(factory.freight/factory.load_quantity));
		total_amount += total;
		
  	table_rows = table_rows + 
  	
  					"<tr class='table'>"+
      					"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (i+1) + "</td>"+
      					"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Cement' + "</td>"+		            						
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + factory.unload_location + "</td>"+	
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + factory.load_quantity + "</td>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (factory.freight/factory.load_quantity).toFixed(2) + "</td>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ((factory.load_quantity)*(factory.freight/factory.load_quantity)).toFixed(2)+"</td>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
  						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
  						"</tr>"
  						;
  	

	}
 */