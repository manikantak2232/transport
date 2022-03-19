var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;

var incomeDetails;
var pk_income_id;
var delete_income_id;

function editFunction(income_id){
	pk_income_id=income_id;

	var arry=incomeDetails.filter(obj=>obj.pk_income_id==income_id);
	$('#date').val(arry[0].date);
	$('#sales_and_income_ac_name').val(arry[0].sales_and_income_ac_name);
	$('#invoice_no').val(arry[0].invoice_no);
	$('#customer_ac_name').val(arry[0].customer_ac_name);
	$('#amount').val(arry[0].amount);
	$('#w_b_no').val(arry[0].w_b_no);
	$('#narration').val(arry[0].narration);
	$('#remarks').val(arry[0].remarks);
}

function del(income_id){
	delete_income_id=income_id;
}

function delFunction(){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_income_id=delete_income_id;

	$.ajax({
		url: '/transport/accounts/income/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Deleted Successfully..'){ 
				getIncome();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});
	
}

function getIncome(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();

	$.ajax({
		url: '/transport/accounts/income/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			incomeDetails = response.incomeDetails;
			if(incomeDetails!=null){
				for(var i=0; i<incomeDetails.length; i++){
					income = incomeDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(income.date);
					dataSe.push(income.voucher);
					dataSe.push(income.w_b_no);
					dataSe.push(income.sales_and_income_ac_name);
					dataSe.push(income.invoice_no);
					dataSe.push(income.customer_ac_name);
					dataSe.push(income.amount);
					dataSe.push(income.narration);
					dataSe.push(income.remarks);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+income.pk_income_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+income.pk_income_id+"\")'>");
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
				{ title: "Sales & Income Ac" },
				{ title: "Invoice No"},
				{ title: "Account" },
				{ title: "Amount" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "Narration"},
				{ title: "Remarks"},
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

function updateIncome(){  

	var check_sales_and_income_name=$.inArray($('#sales_and_income_ac_name').val(), names);
	var check_customer_ac_name=$.inArray($('#customer_ac_name').val(), vendor_ac_details);


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

	date=$('#date').val();
	sales_and_income_ac_name=$('#sales_and_income_ac_name').val();
	invoice_no=$('#invoice_no').val();
	customer_ac_name=$('#customer_ac_name').val();
	amount=$('#amount').val();
	w_b_no=$('#w_b_no').val();
	narration=$('#narration').val();
	remarks=$('#remarks').val();

	if(date==undefined | date==''){ 
		alert("Please Select Date..");
		return false;
	}

	if(sales_and_income_ac_name==undefined | sales_and_income_ac_name==''){ 
		alert("Please Select Sale & Income Name..");
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
	
	$(function () {
		   $('#myModal').modal('toggle');
		});
	
//	$('body').removeClass('modal-open'); 

	date=$('#date').val();
	sales_and_income_ac_name=$('#sales_and_income_ac_name').val();
	invoice_no=$('#invoice_no').val();
	customer_ac_name=$('#customer_ac_name').val();
	amount=$('#amount').val();
	w_b_no=$('#w_b_no').val();
	narration=$('#narration').val();
	remarks=$('#remarks').val();

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
	requestData.pk_income_id=pk_income_id;
	requestData.date=date;
	requestData.sales_and_income_ac_name=sales_and_income_ac_name;
	requestData.invoice_no=invoice_no;
	requestData.customer_ac_name=customer_ac_name;
	requestData.amount=amount;
	requestData.w_b_no=w_b_no;
	requestData.narration=narration;
	requestData.remarks=remarks;

	$.ajax({
		url: '/transport/accounts/income/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			alert(response.message);
			if(response.message=='Updated Successfully..'){
				getIncome();
			}
		},
		error: function(error) {
			console.log(error);
		}
	});
}

