				$(document).ready(function (){ 
					  
					$('#accept_money').click(function () {
			//			$('#loading').show();
						var requestData = new Object();
						requestData.truck_maintenance_advance_id= id;
											  
				        $.ajax({
				        	url: '/transport/spareparts/approval/status/update',
							type: 'POST',
				            dataType: 'json',
				            data: JSON.stringify(requestData),
				            contentType: 'application/json; charset=utf-8',
				            success: function(response) {	
				        //    	$('#loading').hide();
				            	$("#Message").html(response.message);
				            },
				            error: function(error) {
				                console.log(error);
				            }
				        });
				    });
				});