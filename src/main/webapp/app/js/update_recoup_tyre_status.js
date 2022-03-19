var suggestion;

function test(){  
	
	var check_tyre_number=$.inArray($('#tyre_number').val(), suggestion)
	
	if(check_tyre_number==-1)
		{
		alert("select Tyre Number only from Suggestions");
		
		}
	else{
		alert("success");
		 	/* 	var requestData = new Object();
		 		requestData.tyre_number=$('#tyre_number').val();	
		 		requestData.tyre_status=$('#status').val();

		 		$.ajax({
		 			url: '/transport/tyre/recoup/status/update',
		 			type: 'POST',
		 			dataType: 'json',
		 			data: JSON.stringify(requestData),
		 			contentType: 'application/json; charset=utf-8',
		 			success: function(response) {	            		 	         
		 				$("#Message").html("Inserted Successfully");
		 			},
		 			error: function(error) {
		 				console.log(error);
		 			}
		 		}); */
	}
	}

function addDropDownOptions8(brand){
	for(var i=0;i<brand.length;++i){
		addOption8(document.add.brand_name,brand[i]);
	}
}
function addOption8(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	selectbox.options.add(optn);
}
function addOption_list8(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/tyre/pending/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.BrandNames;
				addDropDownOptions8(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}

function addOption_list9(){
	  $(document).ready(function() {

		  var tyre;
		  
		  if($('#brand_name :selected').val() != 'Select' && 
				  $('#tyre_type :selected').val() != 'Select' ){
			  
			/*   $('#insert').click(function () { */
					var requestData = new Object();
					requestData.brand_name=$('#brand_name :selected').val();	
					requestData.tyre_type=$('#tyre_type :selected').val();
					
					
					$.ajax({
						url: '/transport/tyre/all/pendingtyres/get',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {
		        	  	tyre=response.Tyres; 
		        	  	$("#tyre_number").autocomplete({
		        		  source:tyre
		        			/*   [
		        		          {label:"ASD CUSTOMER",value:1},
		        		          {label:"Customer 2",value:2}
		        		         ] */ ,
		        		  minLength: 1
		        		/*   select: function(event, ui) {
		        		      event.preventDefault();
		        		      $("#tyre").val(ui.item.label);
		        		      
		        		  } */
		        		});
		        	       	
		          },
		          error: function(error) {
		              console.log(error);
		          }
				});
		    		
		     }

		  $.ui.autocomplete.filter = function (array, term) {
		        var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
		        suggestion = $.grep(array, function (value) {
		        	return matcher.test(value.label || value.value || value);
		        });
		        return suggestion;
		    };
	 
	});
}
	  
	  

