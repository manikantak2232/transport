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
        	  driver_loaded=1;
        	  getFuel();
        	  
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
  }
  
