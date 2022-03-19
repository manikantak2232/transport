function addAccountGroup(){  

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

	$.ajax({
		url: '/transport/accounts/account_group/add',
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


