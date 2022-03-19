function addStationDropDownOptions(name){
	  for(var i=0;i<name.length;++i){
		  addStationOption(document.add.fuel_list,name[i].fuel_station_name,name[i].fuel_station_id);
	  }
	  }
  function addStationOption(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
	  }
  
  function getFuelStaionName(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/factory/fuel/station/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  var name=response.fuelStation;
        	  addStationDropDownOptions(name);
        	  
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
}
  function fuelChange(){
	  var selectedStation=$("#fuel_list :selected").text();
	  var selectedStationId=$("#fuel_list :selected").val();
	  $("#fk_fuel_station_id").val(selectedStationId);
  }