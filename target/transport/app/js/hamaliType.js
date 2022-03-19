function addDropDownOptionsHamali(hamali){
	for(var i=0;i<hamali.length;++i){
		addOption2(document.add.hamaliType,hamali[i].type_list);
	}
}
function addOption2(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
//	optn.value=id;
	selectbox.options.add(optn);
}
function hamaliType_list2(){
	
		$.ajax({
			url: '/transport/godown/hamalitype/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var hamali=response.hamaliTypeDetails;
				addDropDownOptionsHamali(hamali);

			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} ;