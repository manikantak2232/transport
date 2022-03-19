function addIncome(){  
//	$('#loading').show();

	var check_sales_and_income_name=$.inArray($('#sales_and_income_ac_name').val(), names);
	var check_customer_ac_name=$.inArray($('#customer_ac_name').val(), account_names);
	var check_truck_no=$.inArray($('#truck_no').val(), truck_numbers);


	if(check_sales_and_income_name==-1 )
	{
		alert("select Sales & Income A/c only from Suggestions");
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
	sales_and_income_ac_name=$('#sales_and_income_ac_name').val();
	invoice_no=$('#invoice_no').val();
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

	if(sales_and_income_ac_name==undefined | sales_and_income_ac_name==''){ 
		alert("Please Select Sales & Income A/c Name..");
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
	if(invoice_no==undefined){ 
		invoice_no='';
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
	requestData.sales_and_income_ac_name=sales_and_income_ac_name;
	requestData.invoice_no=invoice_no;
	requestData.customer_ac_name=customer_ac_name;
	requestData.amount=amount;
	requestData.w_b_no=w_b_no;
	requestData.narration=narration;
	requestData.remarks=remarks;
	requestData.truck_no=truck_no;

	$.ajax({
		url: '/transport/accounts/income/add',
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


