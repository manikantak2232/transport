function addOpeningBalance(){  

	date=$('#date').val();
	account_name=$('#account_name').val();
	debit=$('#debit').val();
	credit=$('#credit').val();

	if(date==undefined | date==''){ 
		alert("Please Select Date..");
		return false;
	}

	if(account_name==undefined | account_name==''){ 
		alert("Please Select Account Name..");
		return false;
	}

	if(debit==undefined | debit==''){ 
		alert("Please Enter Debit Amount..");
		return false;
	}

	if(credit==undefined | credit==''){ 
		alert("Please Enter Credit Amount..");
		return false;
	}

	var requestData = new Object();
	requestData.date=date;
	requestData.account_name=account_name;
	requestData.debit=debit;
	requestData.credit=credit;

	$.ajax({
		url: '/transport/accounts/opening_balance/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			alert(response.message);
		},
		error: function(error) {
			console.log(error);
		}
	});
}


