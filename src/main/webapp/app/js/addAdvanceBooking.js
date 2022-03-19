function addOutward(){
			var invoice_number = $('#invoice_number').val();
			var date_of_outward = $('#date_of_outward').val();
			var quantity = $('#quantity').val();
			var truck_number = $('#truck_number').val();
			var transporter = $('#transporter').val();
			var hamali_per_bags = $('#hamali_per_bags').val();
			var selectedActionId = $('#actionName').val();

			
			if(invoice_number==undefined | invoice_number==''){ 
				alert("Please enter Invoice Number");
				return false;
			} 
			if(date_of_outward==undefined | date_of_outward==''){ 
				alert("Please enter Outward Date ");
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
			requestData.invoice_number = invoice_number;
			requestData.date_of_outward = date_of_outward;
			requestData.quantity = quantity;
			requestData.truck_number = truck_number;
			requestData.transporter = transporter;
			requestData.hamali_per_bags = hamali_per_bags;	
			requestData.outwardTruckNumberImageBytes=outwardTruckNumberImageBytes;
			requestData.outwardTtruckNumberImageType=outwardTtruckNumberImageType;
			requestData.outwardWorkDoneImageBytes=outwardWorkDoneImageBytes;
			requestData.outwardWorkDoneImageType=outwardWorkDoneImageType;
			requestData.action_name = selectedActionId;
	        $.ajax({
	        	url: '/transport/godown/advancebooking/add',
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