function addPayment(){  
//	$('#loading').show();

//	var check_cash_and_bank_name=names.filter(obj=>obj.account_name==$('#cash_and_bank_ac_name').val());
	var check_cash_and_bank_name=$.inArray($('#cash_and_bank_ac_name').val(), names);
	var check_customer_ac_name=$.inArray($('#customer_ac_name').val(), account_names);
	var check_truck_no=$.inArray($('#truck_no').val(), truck_numbers);
	
	
	if(check_cash_and_bank_name==-1 )
	{
		alert("select Cash & Bank A/c only from Suggestions");
		return false;
	}
	
	if(check_customer_ac_name==-1 )
	{
		alert("select Account Name from Suggestions");
		return false;
	}
	
	if(check_truck_no==-1 )
	{
		alert("select none or truck no from Suggestions");
		return false;
	}

	date=$('#date').val();
	cash_and_bank_ac_name=$('#cash_and_bank_ac_name').val();
	cheque_no=$('#cheque_no').val();
	customer_ac_name=$('#customer_ac_name').val();
	amount=$('#amount').val();
	w_b_no=$('#w_b_no').val();
	narration=$('#narration').val();
	remarks=$('#remarks').val();
	truck_no=$('#truck_no').val();

	if(date==undefined | date==''){ 
		alert("Please Select Date..");
		return false;
	}

	if(cash_and_bank_ac_name==undefined | cash_and_bank_ac_name==''){ 
		alert("Please Select Cash & Bank Name..");
		return false;
	}

	if(customer_ac_name==undefined | customer_ac_name==''){ 
		alert("Please Select Account Name..");
		return false;
	}

	if(amount==undefined | amount==''){ 
		alert("Please Enter Amount..");
		return false;
	}

	if(cheque_no==undefined){ 
		cheque_no='';
	}
	if(w_b_no==undefined){ 
		w_b_no='';
	}
	if(narration==undefined){ 
		narration='';
	}
	if(remarks==undefined){ 
		remarks='';
	}

	var requestData = new Object();
	requestData.date=date;
	requestData.cash_and_bank_ac_name=cash_and_bank_ac_name;
	requestData.cheque_no=cheque_no;
	requestData.customer_ac_name=customer_ac_name;
	requestData.amount=amount;
	requestData.w_b_no=w_b_no;
	requestData.narration=narration;
	requestData.remarks=remarks; 
	requestData.truck_no=truck_no;

	$.ajax({
		url: '/transport/accounts/payment/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
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


