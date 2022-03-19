function addDropDownOptions1(parts){
	 
	  for(var i=0;i<parts.length;++i){
		  addOption1(document.add.part_list,parts[i].name,parts[i].pk_spare_parts_id);
	  }
	  }
  function addOption1(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
	  }
  
  function addOption_list1(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/spareparts/all/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  var parts=response.SpareParts;
        	  addDropDownOptions1(parts);  
        	  $('#part_list').multiselect({
                  includeSelectAllOption: true
                 
              });
          },
          
          error: function(error) {
              console.log(error);
          }
		});
	});
}

  function testChange1(){ 

	var selectedPartId2=$("#part_list :selected").val(); 
   	     var option_all = $("select option:selected").map(function () {
   	        return $('this').val();
   	  }).get().join(','); 
   	    
   	    $("#part_id").val($('#part_list').val());
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
			url: '/transport/trucks/all/get',
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
  function testChange2(){
	  var selectedTruck=$("#truck_list :selected").text();
	  var selectedTruckId=$("#truck_list :selected").val();
	  $("#truck_id").val(selectedTruckId);	
	 
  }
  
   

 
  $(document).ready(function (){ 
		$('#insert').click(function () {
			 
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
			requestData.service_closed_date=$('#service_closed_date').val();
			requestData.fk_spare_parts_id=$('#part_id').val();
			requestData.service_total_cost= $('#service_total_cost').val(); 
		    //requestData.service_status= $('#service_status').val(); 
			
		
		
	        $.ajax({
	        	url: '/transport/trucks/service/close',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	            	$("#Message").html(response.errorMessage);
	            },
	           
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});