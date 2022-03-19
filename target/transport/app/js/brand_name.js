function addDropDownOptions5(brand){
	for(var i=0;i<brand.length;++i){
		addOption5(document.add.brand_list,brand[i].tyre_number);
	}
}
function addOption5(selectbox,text,id )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	optn.value=id;
	selectbox.options.add(optn);
}
function addOption_list5(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/tyre/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.BrandNames;
				addDropDownOptions5(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
/*function testChange2(){
	var selectedTruck=$("#brand_list :selected").text();
	var selectedTruckId=$("#brand_list :selected").val();
	$("#truck_id").val(selectedTruckId);
}*/