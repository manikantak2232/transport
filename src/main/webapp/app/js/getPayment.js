var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;

var paymentDetails;
var pk_payment_id;
var delete_payment_id;

function editFunction(payment_id){
	pk_payment_id=payment_id;

	var arry=paymentDetails.filter(obj=>obj.pk_payment_id==payment_id);
	$('#date').val(arry[0].date);
	$('#cash_and_bank_ac_name').val(arry[0].cash_and_bank_ac_name);
	$('#cheque_no').val(arry[0].cheque_no);
	$('#customer_ac_name').val(arry[0].customer_ac_name);
	$('#amount').val(arry[0].amount);
	$('#w_b_no').val(arry[0].w_b_no);
	$('#narration').val(arry[0].narration);
	$('#remarks').val(arry[0].remarks);
	$('#truck_no').val(arry[0].truck_no);
}

function del(payment_id){
	delete_payment_id=payment_id;
}

function delFunction(payment_id){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_payment_id=delete_payment_id;

	$.ajax({
		url: '/transport/accounts/payment/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Deleted Successfully..'){ 
				getPayment();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});
	
}

function getPayment(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();

	$.ajax({
		url: '/transport/accounts/payment/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			paymentDetails = response.paymentDetails;
			if(paymentDetails!=null){
				for(var i=0; i<paymentDetails.length; i++){
					payment = paymentDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(payment.date);
					dataSe.push(payment.voucher);
					dataSe.push(payment.w_b_no);
					dataSe.push(payment.cash_and_bank_ac_name);
					dataSe.push(payment.cheque_no);
					dataSe.push(payment.customer_ac_name);
					dataSe.push(payment.amount);
					dataSe.push(payment.narration);
					dataSe.push(payment.remarks);
					dataSe.push(payment.truck_no);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+payment.pk_payment_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+payment.pk_payment_id+"\")'>");
					dataSet.push(dataSe);

				}

				fun();
			}else{
				alert(response.message);
				var table = $('#example').DataTable(); 
				table.clear().draw();
				
			}
			

		},
		error: function(error) {
			console.log(error);
			alert('Something Wrong.. Try again..!');
			var table = $('#example').DataTable(); 
			table.clear().draw();
			
		}
	});
}

function fun() {
	var t =  $('#example').DataTable( {
		data: dataSet,
		destroy: true,
		"bSort" : false,
		//   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		buttons: [
			'excel','pdf','print'
			],
			columns: [
				{ title: "S.No" },
				{ title: "Date" },
				{ title: "Voucher" },
				{ title: "W B No" },
				{ title: "Cash & Bank Ac" },
				{ title: "Cheque No"},
				{ title: "Account" },
				{ title: "Amount" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "Narration"},
				{ title: "Remarks"},
				{ title: "Truck No."},
				{ title: ""},
				{ title: ""}
				],
				"order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();

};

function updatePayment(){  

	var check_cash_and_bank_name=$.inArray($('#cash_and_bank_ac_name').val(), names);
	var check_customer_ac_name=$.inArray($('#customer_ac_name').val(), vendor_ac_details);
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
	
	$(function () {
		   $('#myModal').modal('toggle');
		});
	
//	$('body').removeClass('modal-open'); 

	var requestData = new Object();
	requestData.pk_payment_id=pk_payment_id;
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
		url: '/transport/accounts/payment/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
			alert(response.message);
			if(response.message=='Updated Successfully..'){
				getPayment();
			}
			
		},
		error: function(error) {
			console.log(error);
		}
	});
}

