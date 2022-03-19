var dataSet=[];
var dataSe=[];

var openingBalanceDetails;
var pk_opening_balance_id;
var delete_opening_balance_id;

function editFunction(opening_balance_id){
	pk_opening_balance_id=opening_balance_id;

	var arry=openingBalanceDetails.filter(obj=>obj.pk_opening_balance_id==opening_balance_id);
	$('#date').val(arry[0].date);
	$('#account_name').val(arry[0].account_name);
	$('#debit').val(arry[0].debit);
	$('#credit').val(arry[0].credit);
}

function del(opening_balance_id){
	delete_opening_balance_id=opening_balance_id;
}

function delFunction(opening_balance_id){
	$(".overlay").show();
	var requestData = new Object();
	requestData.opening_balance_id=delete_opening_balance_id;

	$.ajax({
		url: '/transport/accounts/opening_balance/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Deleted Successfully..'){ 
				getOpeningBalance();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});
	
}

function getOpeningBalance(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();

	$.ajax({
		url: '/transport/accounts/opening_balances/get',
		type: 'Get',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			openingBalanceDetails = response.openingBalanceDetails;
			if(openingBalanceDetails!=null){
				for(var i=0; i<openingBalanceDetails.length; i++){
					balance = openingBalanceDetails[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(balance.date);
					dataSe.push(balance.account_name);
					dataSe.push(balance.debit);
					dataSe.push(balance.credit);
					dataSe.push("<input  class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+balance.pk_opening_balance_id+"\")'>");
					dataSe.push("<input  class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+balance.pk_opening_balance_id+"\")'>");
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
				{ title: "Account Name" },
				{ title: "Debit" },
				{ title: "Credit" },
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

function updateOpeningBalance(){  
	$(".overlay").show();
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
	requestData.pk_opening_balance_id=pk_opening_balance_id;
	requestData.date=date;
	requestData.account_name=account_name;
	requestData.debit=debit;
	requestData.credit=credit;
	
	$(function () {
		   $('#myModal').modal('toggle');
		});
	
	$.ajax({
		url: '/transport/accounts/opening_balance/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Updated Successfully..'){
				getOpeningBalance();
			}
			
		},
		error: function(error) {
			console.log(error);
		}
	});
}

