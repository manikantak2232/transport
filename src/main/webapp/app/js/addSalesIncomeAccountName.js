function addSalesAndIncome(){  
//	$('#loading').show();
	
	sales_income_account_name=$('#sales_income_account_name').val();
	sales_income_account_group_name=$('#sales_income_account_group_name').val();
	sales_income_account_alias_name=$('#sales_income_account_alias_name').val();

	if(sales_income_account_group_name==undefined | sales_income_account_group_name==''){ 
		alert('Select Group Name');
		return false;
	}
	
	if(sales_income_account_alias_name==undefined){ 
		sales_income_account_alias_name='';
	}
	
	var requestData = new Object();
	requestData.account_name=sales_income_account_name;
	requestData.group_name=sales_income_account_group_name;
	requestData.alias_name=sales_income_account_alias_name;
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