function addAccount(){  
//	$('#loading').show();

	customer_account_name=$('#customer_account_name').val();
	customer_account_group_name=$('#customer_account_group_name').val();
	customer_account_alias_name=$('#customer_account_alias_name').val();

	if(customer_account_group_name==undefined | customer_account_group_name==''){ 
		alert('Select Group Name');
		return false;
	}
	if(customer_account_alias_name==undefined){ 
		customer_account_alias_name='';
	}
	
	var requestData = new Object();
	requestData.account_name=customer_account_name;
	requestData.group_name=customer_account_group_name;
	requestData.alias_name=customer_account_alias_name;
		$.ajax({
		url: '/transport/accounts/account_name/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
		//	$('#loading').hide();
			acSuggestions();
			alert(response.message);
		},
		error: function(error) {
			console.log(error);
		}
	});
}