function addDropDownOptionsHamali(stock){
	for(var i=0;i<stock.length;++i){
		addOption2(document.add.stockType,stock[i].stock_type);
	}
}
function addOption2(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function stockType_list2(){
	
		$.ajax({
			url: '/transport/godown/stockType/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var stock=response.stockTypeDetails;
				addDropDownOptionsHamali(stock);

			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} ;