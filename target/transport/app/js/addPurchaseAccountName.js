function addPurchaseAccount(){  
//	$('#loading').show();
	
	purchase_account_name=$('#purchase_account_name').val();
	purchase_account_group_name=$('#purchase_account_group_name').val();
	purchase_account_alias_name=$('#purchase_account_alias_name').val();
	
	if(purchase_account_group_name==undefined | purchase_account_group_name==''){ 
		alert('Select Group Name');
		return false;
	}
	
	if(purchase_account_alias_name==undefined){ 
		purchase_account_alias_name='';
	}
	
	var requestData = new Object();
	requestData.account_name=purchase_account_name;
	requestData.group_name=purchase_account_group_name;
	requestData.alias_name=purchase_account_alias_name;
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