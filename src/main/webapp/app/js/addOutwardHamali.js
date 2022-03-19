function addOutward(){

			var quantity = $('#quantity').val();
			var customer_name = $('#customer_name').val();
			var invoice_date = $('#invoice_date').val();
			var truck_number = $('#truck_number').val();
			var transporter = $('#transporter').val();
			var hamali_per_bags = $('#hamali_per_bags').val();
			var associationName =  $('#associationName').val();
			var unloadLocationName = $('#unloadLocationName').val();
			var selectedActionId = $('#actionName').val();
			var type_of_cementid = $('#typeOfCement').val();
			var cementid=$.inArray($('#typeOfCement').val(),type);
			
			var customer_count=$.inArray($('#customer_name').val(), cus);
			
			if(customer_count==-1 )
			{
				alert("Please select customer name from suggestions only..");
				return false;
			}
			
			if(cementid==-1 )
			{
				alert("Please select valid type of cement");
				return false;
			}
			
			if(quantity==undefined | quantity==''){ 
				alert("Please enter Quantity ");
				return false;
			}
			if(truck_number==undefined | truck_number==''){ 
				alert("Please enter Truck number ");
				return false;
			} 

			if(invoice_date==undefined | invoice_date==''){ 
				alert("Please enter Invoice Date ");
				return false;
			}
			if(associationName==undefined | associationName=='Select'){ 
				alert("Please Select Association name ");
				return false;
			}
			if(unloadLocationName==undefined | unloadLocationName=='Select'){ 
				alert("Please Select Unload location ");
				return false;
			}

			if(transporter==undefined){ 
				transporter='';
			}
			if(hamali_per_bags==undefined){ 
				hamali_per_bags='';
			}
			if(outwardTruckNumberImageBytes==null){
				outwardTruckNumberImageBytes="";
			}
			if(outwardTtruckNumberImageType==null){ 
				outwardTtruckNumberImageType="";
			}
			if(outwardWorkDoneImageBytes==null){
				outwardWorkDoneImageBytes="";
			}
			if(outwardWorkDoneImageType==null){ 
				outwardWorkDoneImageType="";
			} 
			
			$(".overlay").show();
			
			var requestData = new Object();
			requestData.customer_name = customer_name;
			requestData.invoice_date = invoice_date;
			requestData.quantity = quantity;
			requestData.typeofcement = type_of_cementid;
			requestData.truck_number = truck_number;
			requestData.transporter = transporter;
			requestData.association_name = associationName;
			requestData.unload_location = unloadLocationName;
			requestData.hamali_per_bags = hamali_per_bags;	
			requestData.outwardTruckNumberImageBytes=outwardTruckNumberImageBytes;
			requestData.outwardTtruckNumberImageType=outwardTtruckNumberImageType;
			requestData.outwardWorkDoneImageBytes=outwardWorkDoneImageBytes;
			requestData.outwardWorkDoneImageType=outwardWorkDoneImageType;
			requestData.action_name = selectedActionId;
	        $.ajax({
	        	url: '/transport/godown/outward/hamali/add',
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
	            	$(".overlay").hide();
	            	alert('Something wrong. Try again..');
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

var cus = [];

function listOfCustomers(){
	
		$.ajax({
			url: '/transport/godown/customers/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var customerNames=response.customerNames;
				if(customerNames!=''){
					for (var i = 0; i < customerNames.length; i++) {
						cus.push(customerNames[i].customer_name);
					}
					customers_sug();
				}
				
				
				
			},
			error: function(error) {
				console.log(error);
			}
	
	});
	
} 

function customers_sug() {
	var availableTags = cus;
	$("#customer_name").autocomplete({
		source : availableTags
	});
};

function addCustomer(){

		var requestData = new Object();

		
		requestData.customer_name= $('#customerName').val();
		


		$.ajax({
			url: '/transport/godown/customer/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	
				//	$('#loading').hide();
				alert(response.message);
				if(response.message=='Inserted Successfully..'){
					listOfCustomers();
				}

			},
			error: function(error) {
				console.log(error);
			}
		});
}