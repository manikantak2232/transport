
 	 function generatePasswordDigest(){
 		var password = document.getElementById("password").value
 		var digest = CryptoJS.SHA1(password);
 		document.getElementById("passwordDigest").value = digest;
 	 }

 var association_count;
 var association_id;
  
  $(document).ready(function (){ 
		$('#login').click(function () {
			var requestData = new Object();
			requestData.email=$('#email').val();
			requestData.digest=$('#passwordDigest').val();
	        $.ajax({
	        	url: '/transport/login/authenticate',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	            	 $("#Message").html(response.errorMessage);
	            	 
	            	var manager_type=response.manager_type;
	            	association_count=response.association_count;
	            	association_id=response.association_id;
	            	
	          		
	          		if((manager_type=="factory manager" & association_count==0) | 
	          								(manager_type=="branch office manager" & association_count==0) ){
	            		window.location.href = "/app/views/truck_status.html"; 
	          		}
	          		if(manager_type=="factory manager" & association_count>0){
	          			window.location.href = "/app/views/nav.html?"+"association_id="+association_id; 
	          		}
	          		if(response.manager_type=="seller manager"){
	            		window.location.href = "/app/views/add_seller_dispatch.html"; 
	          		}
	          		if(response.manager_type=="fuel manager" || response.manager_type=="fuel admin"){
	            		window.location.href = "/app/views/add_factory_fuel.html"; 
	          		}
	          		if(response.manager_type=="maintenance manager"){
	            		window.location.href = "/app/views/add_trucks_details.html"; 
	          		}
	          		if(response.manager_type=="operations manager"){
	            		window.location.href = "/app/views/add_driver_allotment_to_truck.html"; 
	          		}	          		
	          		if(response.manager_type=="admin"){
	            		window.location.href = "/production/dashboard.html"; 
	          		}
	          		if(response.manager_type=="admin2"){
	            		window.location.href = "/app/views/trucks_daily_status_admin.html"; 
	          		}
	          		if(response.manager_type=="storage manager"){
	            		window.location.href = "/app/views/addInward.html"; 
	          		}
	          		if(response.manager_type=="godown admin"){
	            		window.location.href = "/app/views/addInwardAdmin.html"; 
	          		}
	          		if(response.manager_type=="account manager"){
	            		window.location.href = "/app/views/get_factories_cumulative_today_report_ac.html"; 
	          		}
	          		/*if(response.manager_type=="branch office manager"){
	            		window.location.href = "/app/views/branch_office_status.html"; 
	          		}*/
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});