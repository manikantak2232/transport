var dataSet=[];
var dataSe=[];

var expenditureDetails;
var pk_expenditure_id;
var delete_expenditure_id;

function editFunction(expenditure_id){
	pk_expenditure_id=expenditure_id;

	var arry=expenditureDetails.filter(obj=>obj.pk_expenditure_id==expenditure_id);
	$('#date').val(arry[0].date);
	$('#purchase_ac_name').val(arry[0].purchase_ac_name);
	$('#invoice_no').val(arry[0].invoice_no);
	$('#customer_ac_name').val(arry[0].customer_ac_name);
	$('#amount').val(arry[0].amount);
	$('#w_b_no').val(arry[0].w_b_no);
	$('#narration').val(arry[0].narration);
	$('#remarks').val(arry[0].remarks);
}

function del(expenditure_id){
	delete_expenditure_id=expenditure_id;
}

function delFunction(){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_expenditure_id=delete_expenditure_id;

	$.ajax({
		url: '/transport/accounts/expenditure/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Deleted Successfully..'){ 
				getExpenditure();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});
	
}

function getExpenditure(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();
	var requestData = new Object();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();

	$.ajax({
		url: '/transport/accounts/expenditure/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			expenditureDetails = response.expenditureDetails;
			if(expenditureDetails!=null){
				for(var i=0; i<expenditureDetails.length; i++){
					expenditure = expenditureDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(expenditure.date);
					dataSe.push(expenditure.voucher);
					dataSe.push(expenditure.w_b_no);
					dataSe.push(expenditure.purchase_ac_name);
					dataSe.push(expenditure.invoice_no);
					dataSe.push(expenditure.customer_ac_name);
					dataSe.push(expenditure.amount);
					dataSe.push(expenditure.narration);
					dataSe.push(expenditure.remarks);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+expenditure.pk_expenditure_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+expenditure.pk_expenditure_id+"\")'>");
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
				{ title: "Purchasee Ac" },
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

function updateExpenditure(){  

	var check_purchase_ac_name=$.inArray($('#purchase_ac_name').val(), names);
	var check_customer_ac_name=$.inArray($('#customer_ac_name').val(), vendor_ac_details);


	if(check_purchase_ac_name==-1 )
	{
		alert("select Purchase A/c only from Suggestions");
		return false;
	}

	if(check_customer_ac_name==-1 )
	{
		alert("select Account Name from Suggestions");
		return false;
	}

	date=$('#date').val();
	purchase_ac_name=$('#purchase_ac_name').val();
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

	if(purchase_ac_name==undefined | purchase_ac_name==''){ 
		alert("Please Select Purchase ac Name..");
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
	purchase_ac_name=$('#purchase_ac_name').val();
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

	if(purchase_ac_name==undefined | purchase_ac_name==''){ 
		alert("Please Select Purchase A/c Name..");
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
	requestData.pk_expenditure_id=pk_expenditure_id;
	requestData.date=date;
	requestData.purchase_ac_name=purchase_ac_name;
	requestData.invoice_no=invoice_no;
	requestData.customer_ac_name=customer_ac_name;
	requestData.amount=amount;
	requestData.w_b_no=w_b_no;
	requestData.narration=narration;
	requestData.remarks=remarks;

	$.ajax({
		url: '/transport/accounts/expenditure/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			alert(response.message);
			if(response.message=='Updated Successfully..'){
				getExpenditure();
			}
		},
		error: function(error) {
			console.log(error); 
		}
	});
}

