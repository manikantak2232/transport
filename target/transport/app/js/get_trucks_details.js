var rc;
var insurance;
var fitness;
var permit;

$(function() {
	    $('#get').click(function() {
	    	var fk_truck_id = $('#truck_id').val();
	        $.ajax({
	            type: 'GET',
	            url: '/transport/trucks/details/get?'+'fk_truck_id='+fk_truck_id,
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
      	
	            	var trucksDetails = response.trucksDetails;	 
	            	
	            	rc=trucksDetails.rc_image_url;
	            	insurance=trucksDetails.insurance_image_url;
	            	fitness=trucksDetails.fitness_image_url;
	            	permit=trucksDetails.permit_image_url;
	            	
	            	if(rc!==''){
	 	            //	document.getElementById('rc_button').innerHTML = rc;
	 	            //	document.getElementById('myImg').src = 'https://s3.ap-south-1.amazonaws.com/truckimages/fuel starting meter reading/1049.jpeg';
	            		document.getElementById('rc_button').innerHTML = '<input type="button"  value="View Rc" id="rc_id"   onclick="rcOpen()"><br><br>';
	 	            		
	 	            	//	'<input type="button"  value="View Rc" id="rc_id"   onclick="rcOpen()"><br><br>';
	            	//	rc.innerHtml;
	            	} else {
	 	            	document.getElementById('rc_button').innerHTML = '';

		            }
	            	 
	            	 if(insurance!==''){
	 	            	document.getElementById('insurance_button').innerHTML = '<input type="button"  value="View Insurance" id="insurance_id"   onclick="insuranceOpen()"><br><br>';
		            } else {
	 	            	document.getElementById('insurance_button').innerHTML = '';
		            }
	            	 
	            	 if(fitness!==''){
		 	            	document.getElementById('fitness_button').innerHTML = '<input type="button"  value="View Fitness" id="fitness_id"   onclick="fitnessOpen()"><br><br>';
			            } else {
		 	            	document.getElementById('fitness_button').innerHTML = '';

			         }
		            	 
		            	if(permit!==''){
		 	            	document.getElementById('permit_button').innerHTML = '<input type="button"  value="View Permit" id="permit_id"   onclick="permitOpen()"><br><br>';
			            } else {
		 	            	document.getElementById('permit_button').innerHTML = '';
			            }
	            	
	            	$.each(trucksDetails, function(key, value){
	            		$('#'+key+'').val(value);	            	   
	            	});         	
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});
function rcOpen()
{
	window.open(rc);
}

function insuranceOpen()
{
	window.open(insurance);
}
function fitnessOpen()
{
	window.open(fitness);
}

function permitOpen()
{
	window.open(permit);
}

function large(){

	var modal = document.getElementById('myModal');

	// Get the image and insert it inside the modal - use its "alt" text as a caption
	var img = document.getElementById('myImg');
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption");
	img.onclick = function(){
	    modal.style.display = "block";
	    modalImg.src = this.src;
	    captionText.innerHTML = this.alt;
	}

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
	    modal.style.display = "none";
	}
	
}