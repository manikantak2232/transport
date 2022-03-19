function oilBrands(brand){
	  for(var i=0;i<brand.length;++i){
		  addBrands(document.add.brand_list,brand[i].brand_name,brand[i].pk_oil_brand_id);
	  }
	  }
  function addBrands(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
	  }
  
  function getOilBrands(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/spareparts/oil/brand/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  var brand=response.brandNames;
        	  oilBrands(brand);
        	  
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
}
  function brandChange(){
	  var selectedBrand=$("#brand_list :selected").text();
	  var selectedBrandId=$("#brand_list :selected").val();
	  $("#fk_oil_brand_id").val(selectedBrandId);
  }