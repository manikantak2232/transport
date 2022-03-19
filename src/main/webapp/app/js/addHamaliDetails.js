  var associationName;
  var startDate;
  var endDate;
  var hamali;
  var fixedExpenses;
  var hamaliPsc;
  var hamaliCon;
  var serviceCharge;
  var hamaliTrade;

function addHamaliDetails(){
	
	associationName=$('#associationName').val();
	startDate=$('#startDate').val();
	endDate=$('#endDate').val();
	hamali=$('#hamali').val();
	fixedExpenses=$('#fixedExpenses').val();
	hamaliPsc=$('#hamaliPsc').val();
	hamaliCon=$('#hamaliCon').val();
	serviceCharge=$('#serviceCharge').val();
	hamaliTrade=$('#hamaliTrade').val();
	
	$(".overlay").show();
	
	if(associationName==undefined | associationName==''){ 
		alert("Please Select Association Company..");
		
	}
	if(startDate==undefined | startDate==''){ 
		alert("Please Select Start Date..");
		
	}
	if(endDate==undefined | endDate==''){ 
		alert("Please Select End Date..");
		
	}
	if(hamali ==''){ 
		hamali=0;
	}
	if(fixedExpenses ==''){ 
		fixedExpenses=0;
	}
	if(hamaliPsc ==''){ 
		hamaliPsc=0;
	}
	if(hamaliCon ==''){ 
		hamaliCon=0;
	}
	if(serviceCharge ==''){ 
		serviceCharge=0;
	}
	if(hamaliTrade ==''){ 
		hamaliTrade=0;
	}
	
	var requestData = new Object();
	
	requestData.associationName = associationName;
	requestData.startDate = startDate;
	requestData.endDate = endDate;
	requestData.hamali = hamali;
	requestData.fixedExpenses = fixedExpenses;
	requestData.hamaliPsc = hamaliPsc;
	requestData.hamaliCon = hamaliCon;
	requestData.serviceCharge = serviceCharge;
	requestData.hamaliTrade = hamaliTrade;
	
	
	$.ajax({
		url: '/transport/godown/hamali/details/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	 
			$(".overlay").hide();
			alert(response.message);
       
			$("#Message").html(response.Message);

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}

});
}