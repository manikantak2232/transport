var excelPath =""


function getExcel(){ 
	
    association = $('#associationName').val();
    billActionType = $('#billActionType').val();
	excelPath =$('#excelPath').val();
	
	
	
	if(association==undefined | association=='Select'){ 
		alert("Please Select Associated Company..");
		return false;
	}
	
	if(billActionType==undefined | billActionType=='Select'){ 
		alert("Please Select Action Type..");
		return false;
	}
	
	
	
	if(excelPath==null | excelPath==''){ 
		alert("Please Select excelPath ");
		return false;
	}
	
	$(".overlay").show();
	var requestData = new Object();

	requestData.association = association;
	requestData.billActionType = billActionType;
	requestData.excelPath = excelPath;
	
	
	

	$.ajax({
		url: '/transport/godown/excel/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			$(".overlay").hide();
			alert(response.message)
        },
        error: function(error) {
            console.log(error);
        }
	
});
		    }
	 
