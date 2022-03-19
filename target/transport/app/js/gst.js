


function calculateGst() {
	  var enter_price=$('#enter_price').val();

	  var basic=(enter_price/(100+14+14))*100; // 14+14 means cgst 14% and sgst 14%
	  var cgst=(enter_price-basic)/2; 
	  var sgst=(enter_price-basic)/2;
	  var igst=(enter_price-basic);
	  $('#basic_price').val(basic);
	  $('#cgst_price').val(cgst);
	  $('#sgst_price').val(sgst);
	  $('#igst_price').val(igst);

}