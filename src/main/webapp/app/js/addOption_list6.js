  function addOption_list6(){
	  $(document).ready(function() {

		  var returned_tyre;
		  
		  if($('#returned_brand_name :selected').val() != 'Select' && 
				  $('#returned_tyre_type :selected').val() != 'Select' ){
			  
			/*   $('#insert').click(function () { */
					var requestData = new Object();
					requestData.brand_name=$('#returned_brand_name :selected').val();	
					requestData.tyre_type=$('#returned_tyre_type :selected').val();
					
					
					$.ajax({
						url: '/transport/tyre/all/runningtyres/get',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
		        	  	returned_tyre=response.ReturnedTyres; 
		        	  	$("#returned_tyre_number").autocomplete({
		        		  source:returned_tyre ,
		        		  minLength: 1
		    
		        		});
		        	       	
		          },
		          error: function(error) {
		              console.log(error);
		          }
				});
		    		
		     }

		  $.ui.autocomplete.filter = function (array2, term2) {
		        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term2), "i");
		        x2 = $.grep(array2, function (value) {
		        	return matcher.test(value.label || value.value || value);
		        });
		        return x2;
		    };
	 
	});
 }
