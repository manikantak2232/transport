var dataSet=[];
var dataSe=[];

var accountGroups;
var pk_account_group_id;
var delete_account_group_id;

function editFunction(account_group_id){
	pk_account_group_id=account_group_id;

	var arry=accountGroups.filter(obj=>obj.pk_account_group_id==account_group_id);
	$('#group_name').val(arry[0].account_name);
	$('#group_type').val(arry[0].group_type);
}

function del(account_group_id){
	delete_account_group_id=account_group_id;
}

function delFunction(account_group_id){
	$(".overlay").show();
	var requestData = new Object();
	requestData.pk_account_group_id=delete_account_group_id;

	$.ajax({
		url: '/transport/accounts/account_group/delete',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Deleted Successfully..'){
				getAccountGroup();
			}

		},
		error: function(error) {
			console.log(error);
		}
	});
	
}

function getAccountGroup(){ 
	dataSet=[];
	dataSe=[];

	$(".overlay").show();

	$.ajax({
		url: '/transport/accounts/account_group/get',
		type: 'Get',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			accountGroups = response.accountGroups;
			if(accountGroups!=null){
				for(var i=0; i<accountGroups.length; i++){
					balance = accountGroups[i];
					dataSe=[];
					dataSe.push('');
					dataSe.push(balance.account_name);
					dataSe.push(balance.group_type);
					dataSe.push("<input class='btn btn-large btn-success' data-toggle='modal' data-target='#myModal' type='button' value='Edit' onclick='return editFunction(\""+balance.pk_account_group_id+"\")'>");
					dataSe.push("<input class='btn btn-large btn-danger' data-toggle='modal' data-target='#myModal1' type='button' value='Del' onclick='return del(\""+balance.pk_account_group_id+"\")'>");
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
				{ title: "Group Name" },
				{ title: "Group Type" },
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

function updateAccountGroup(){  
	$(".overlay").show();
	group_name=$('#group_name').val();
	group_type=$('#group_type').val();
	
	if(group_name==undefined | group_name==''){
		alert("Please Enter Group Name..");
		return false;
	}

	if(group_type=='Select' | group_type==''){
		alert("Please Select Group Type..");
		return false;
	}

	var requestData = new Object();
	requestData.group_name=group_name;
	requestData.group_type=group_type;
	requestData.pk_account_group_id=pk_account_group_id;
	
/*	$(function () {
		   $('#myModal').modal('toggle');
		});*/
	
	$.ajax({
		url: '/transport/accounts/account_group/update',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
			$(".overlay").hide();
			alert(response.message);
			if(response.message=='Updated Successfully..'){
				getAccountGroup();
			}
			
		},
		error: function(error) {
			console.log(error);
		}
	});
}

