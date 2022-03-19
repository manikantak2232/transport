function addInward() {

		invoiceNumber= $('#invoiceNumber').val();
		invoiceDate=$('#invoiceDate').val();
		inwardDate=$('#inwordDate').val();
		quantity=$('#quantity').val();
		typeOfCement=$('#typeOfCement').val();
		truckNumber=$('#truckNumber').val();
		transporter=$('#transporter').val();
		hamaliBag=$('#hamaliBag').val();
		associationName=$('#associationName').val();
		unloadLocationName=$('#unloadLocationName').val();
		actionName=$('#actionName').val();
		crossing_quantity=$('#crossing_quantity').val();
		direct_quantity=$('#direct_quantity').val();
		
		if(truckNumberImageBytes==null){
			truckNumberImageBytes="";
		}
		if(truckNumberImageType==null){ 
			truckNumberImageType="";
		}
		if(workDoneImageBytes==null){
			workDoneImageBytes="";
		}
		if(workDoneImageType==null){ 
			workDoneImageType="";
		}
		if(crossing_quantity=='' | crossing_quantity==undefined){ 
			crossing_quantity=0;
		}
		if(direct_quantity=='' | direct_quantity==null){ 
			direct_quantity=0;
		}

		if(invoiceNumber==undefined | invoiceNumber==''){ 
			alert("Please Select Invoice Number..");
			return false;
		}
		if(invoiceDate==undefined | invoiceDate==''){ 
			alert("Please Select Invoice Date..");
			return false;
		}
		if(inwardDate==undefined | inwordDate==''){ 
			alert("Please Select Inward Date..");
			return false;
		}
		if(quantity==undefined | quantity==''){ 
			alert("Please Select Quantity..");
			return false;
		}
		
		if(truckNumber==undefined | truckNumber==''){ 
			alert("Please Select Truck Number..");
			return false;
		}
		if(transporter==undefined | transporter==''){ 
			alert("Please Select Transporter..");
			return false;
		}
		if(hamaliBag==undefined | hamaliBag==''){ 
			alert("Please Select Hamali Per Bag..");
			return false;
		}
       
		var TypeCementName=$.inArray($('#typeOfCement').val(), type);


		if(TypeCementName==-1 )
		{
			alert("select Type of Cement only from Suggestions");
			return false;
		}
		
		$(".overlay").show();
		var requestData = new Object();
		requestData.invoiceNumber= $('#invoiceNumber').val();
		requestData.invoiceDate=$('#invoiceDate').val();
		requestData.inwordDate=$('#inwordDate').val();
		requestData.quantity=$('#quantity').val();
		requestData.typeOfCement=$('#typeOfCement').val();
		requestData.truckNumber=$('#truckNumber').val();
		requestData.transporter=$('#transporter').val();
		requestData.hamaliBag=$('#hamaliBag').val();
		requestData.associationName=$('#associationName').val();
		requestData.unloadLocationName=$('#unloadLocationName').val();
		requestData.actionName=$('#actionName').val();
		requestData.crossing_quantity=crossing_quantity;
		requestData.direct_quantity=direct_quantity;
		requestData.truckNumberImageBytes=truckNumberImageBytes;
		requestData.truckNumberImageType=truckNumberImageType;
	//	requestData.workUrl=$('#workUrl').val();
		requestData.workDoneImageBytes=workDoneImageBytes;
		requestData.workDoneImageType=workDoneImageType;
		
		$.ajax({
			url: '/transport/godown/inward/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	 
				$(".overlay").hide();
				if(response.message=='No Logged-in User Details Found!'){
					alert('Please Login..')
				}else{
					alert(response.message);
				}
			},
			error: function(error) {
				alert('Something wrong. Try again..');
				$(".overlay").hide();
				console.log(error);
			}
	
	});
}


function getUserLoginDetails(){
		$.ajax({
			url: '/transport/godown/user/login_details/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var loginDetails=response.loginDetails;

				 $('#associationName').val(loginDetails.association_name);
				 $('#unloadLocationName').val(loginDetails.godown_name);

			},
			error: function(error) {
				console.log(error);
			}
		});
}