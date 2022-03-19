
  
  $(document).ready(function (){ 
		$('#insert').click(function () {
			var requestData = new Object();
			requestData.invoice_number=$('#invoice_number').val();
			requestData.count=$('#count').val();
			requestData.fk_spare_part_id=$('#part_id').val();
			requestData.unit_price=$('#unit_price').val();
			requestData.discount=$('#discount').val();
			requestData.gst=$('#gst').val();
			requestData.total=$('#total').val();
		
	        $.ajax({
	        	url: '/transport/spareparts/inventory',
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
	        });
	    });
	});
  
  function calculate() {
	  var count=document.getElementById("count").value;
	  var unit_price=document.getElementById("unit_price").value;
	  var discount=document.getElementById("discount").value;
	  var total_value=document.getElementById("total_value").value;
	  
	  var myResult=(count*unit_price)-((count*unit_price)*discount/100);
	  $('#total_value').val(myResult);
}
  function calculate1() {
	  var total_value=document.getElementById("total_value").value;
	  var gst=document.getElementById("gst").value;
	  var myResult=(total_value*gst)/100;
	  $('#gst_total').val(myResult);
	  
	  var total=+(total_value)+ +((total_value*gst)/100);
	  $('#total').val(total);
}