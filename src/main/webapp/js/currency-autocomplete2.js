  var currencies;
function addOption_list4(){
	 
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/tyre/all/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
        	  currencies = response.Tyres;
        	//  addDropDownOptions4(tyre);        	  
          },
          error: function(error) {
              console.log(error);
          }
		});
		});

};

$(function(){

	  
	  $('#autocomplete').autocomplete({
		    lookup: currencies,
		    onSelect: function (suggestion) {
		      var thehtml = '<strong>Currency Name:</strong> ' + suggestion.name ;
		      $('#outputcontent').html(thehtml);
		    }
		  });

 

});