function addCashAndBank(){  
//	$('#loading').show();

	cash_bank_name=$('#cash_bank_name').val();
	cash_bank_group_name=$('#cash_bank_group_name').val();
	cash_bank_alias_name=$('#cash_bank_alias_name').val();

	if(cash_bank_group_name==undefined | cash_bank_group_name==''){ 
		alert('Select Group Name');
		return false;
	}
	if(cash_bank_alias_name==undefined){ 
		cash_bank_alias_name='';
	}
	
	var requestData = new Object();
	requestData.account_name=cash_bank_name;
	requestData.group_name=cash_bank_group_name;
	requestData.alias_name=cash_bank_alias_name;
		$.ajax({
		url: '/transport/accounts/account_name/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
		//	$('#loading').hide();
			auto();
			alert(response.message);
		},
		error: function(error) {
			console.log(error);
		}
	});
}