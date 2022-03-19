function addOption_list4(){
	  $(document).ready(function() {

		  var issued_tyre;
		  
		  if($('#issued_brand_name :selected').val() != 'Select' && 
				  $('#issued_tyre_type :selected').val() != 'Select'  && 
				  $('#issued_tyre_category :selected').val() != 'Select'){
			  
			/*   $('#insert').click(function () { */
					var requestData = new Object();
					requestData.brand_name=$('#issued_brand_name :selected').val();	
					requestData.tyre_type=$('#issued_tyre_type :selected').val();
					requestData.tyre_category=$('#issued_tyre_category :selected').val();
					
					$.ajax({
						url: '/transport/tyre/all/bycategory/get',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
		        	  	issued_tyre=response.IssuedTyres; 
		        	  	$("#issued_tyre_number").autocomplete({
		        		  source:issued_tyre,
		        		  minLength: 1
		        	
		        		});
		        	       	
		          },
		          error: function(error) {
		              console.log(error);
		          }
				});
		    		
		     }

		  $.ui.autocomplete.filter = function (array1, term1) {
		        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term1), "i");
		        x1 = $.grep(array1, function (value) {
		        	return matcher.test(value.label || value.value || value);
		        });
		        return x1;
		    };
	 
	});
 }