function addJournalEntry(){  
	
	var dr_account_check=$.inArray($('#dr_account').val(), account_names);
	var cr_account_check=$.inArray($('#cr_account').val(), account_names);
	

	if(dr_account_check==-1 )
	{
		alert("select Dr Account only from Suggestions");
		return false;
	}
	
	if(cr_account_check==-1 )
	{
		alert("select Cr Account Name from Suggestions");
		return false;
	}
	
	date=$('#date').val();
	dr_account=$('#dr_account').val();
	cr_account=$('#cr_account').val();
	amount=$('#amount').val();

	if(date==undefined | date==''){ 
		alert("Please Select Date..");
		return false;
	}

	if(dr_account==undefined | dr_account==''){ 
		alert("Please Select Dr Account Name..");
		return false;
	}

	if(cr_account==undefined | cr_account==''){ 
		alert("Please Enter Dr Account Amount..");
		return false;
	}

	if(amount==undefined | amount==''){ 
		alert("Please Enter Amount..");
		return false;
	}

	var requestData = new Object();
	requestData.date=date;
	requestData.dr_account=dr_account;
	requestData.cr_account=cr_account;
	requestData.amount=amount;
	requestData.narration=$('#narration').val();
	
	$.ajax({
		url: '/transport/accounts/journal/entry/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			if(response.message=='Inserted Successfully..'){
				alert(response.message +" Voucher : "+response.voucher_id);
			}else{
				alert(response.message);
			}
		},
		error: function(error) {
			console.log(error);
		}
	});
}


