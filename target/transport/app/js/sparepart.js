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
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
}
  function testChange1(){
	  var selectedPart=$("#part_list :selected").text();
	  var selectedPartId=$("#part_list :selected").val();
	  $("#part_id").val(selectedPartId);		  
  }