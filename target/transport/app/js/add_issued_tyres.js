var issued_tyre;
var returned_tyre;
var ary=[];
var issue;
var recoupTyreCost=0;

/*$(document).ready(function() {
    function disableBack() { window.history.forward() }

    window.onload = disableBack();
    window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
});*/

$(document).ready(function () {
	window.history.forward();
	});

function test(){  

	// var check_issued_tyre=$.inArray($('#issued_tyre_number').val(), issued_tyre.filter(obj=>obj.tyre_number));
	
	var check_issued_tyre=issue.filter(obj=>obj.tyre_number==$('#issued_tyre_number').val());
	
	var check_returned_tyre=$.inArray($('#returned_tyre_number').val(), returned_tyre);

	if(check_issued_tyre.length==-1 || check_returned_tyre==-1)
	{
		alert("select Tyre Number only from Suggestions");
	}
	else
	{
		if($('#issued_tyre_category').val()=='recoup'){
			var is=issue.filter(obj=>obj.tyre_number==$('#issued_tyre_number').val());
			recoupTyreCost=is[0].recoup_tyre_cost;

		}
		
		var requestData = new Object();
		requestData.issued_tyre_number=$('#issued_tyre_number').val();	
		requestData.issued_brand_name=$('#issued_brand_name').val();
		requestData.issued_tyre_type=$('#issued_tyre_type').val();
		requestData.issued_tyre_category=$('#issued_tyre_category').val();
		requestData.issued_and_returned_date=$('#issued_and_returned_date').val();
		requestData.fk_truck_id=$('#truck_id').val();
		requestData.fk_driver_id=$('#fk_driver_id').val();
		requestData.issued_tyre_reading= $('#issued_tyre_reading').val();
		requestData.returned_tyre_number=$('#returned_tyre_number').val();
		requestData.returned_brand_name=$('#returned_brand_name').val();
		requestData.recoupTyreCost=recoupTyreCost;
		requestData.returned_tyre_category=$('#returned_tyre_category').val();
		requestData.returned_tyre_reading=$('#returned_tyre_reading').val();

		$.ajax({
			url: '/transport/tyre/issued/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	            		 	         
				$("#Message").html(response.message);
			},
			error: function(error) {
				console.log(error);
			}
		});

	}
}

function addDropDownOptions5(brand){
	for(var i=0;i<brand.length;++i){
		addOption5(document.add.issued_brand_name,brand[i]);
	}
}
function addOption5(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	selectbox.options.add(optn);
}
function addOption_list5(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/tyre/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.BrandNames;
				addDropDownOptions5(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}

function addDropDownOptions7(brand){
	for(var i=0;i<brand.length;++i){
		addOption7(document.add.returned_brand_name,brand[i]);
	}
}
function addOption7(selectbox,text )
{
	var optn=document.createElement("OPTION");
	optn.text=text;
	selectbox.options.add(optn);
}
function addOption_list7(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/tyre/running/brand/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var brand=response.BrandNames;

				addDropDownOptions7(brand);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}

function addOption_list4(){
	$(document).ready(function() {
		if($('#issued_tyre_category :selected').val() == 'new' ){ 
			if($('#issued_brand_name :selected').val() != 'Select' && 
					$('#issued_tyre_type :selected').val() != 'Select'  && 
					$('#issued_tyre_category :selected').val() != 'Select'){

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

						if(issued_tyre==''){
							alert("No Tyres Available With that Name");	
						}else{
								issue=response.IssuedTyres;
								for(var i=0;i<issued_tyre.length;i++){
									ary.push(issued_tyre[i].tyre_number);
								}
							} 
						

						$("#issued_tyre_number").autocomplete({
							source:ary,

							minLength: 1

						});

					},
					error: function(error) {
						console.log(error);
					}

				});

			}
		} else {
			if($('#issued_brand_name :selected').val() != 'Select' &&  $('#issued_tyre_category :selected').val() != 'Select'){

				if($('#issued_tyre_type :selected').val() == 'Select' ){
					$('#issued_tyre_type :selected').val() == ''
				}		
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

						if(issued_tyre==''){
							alert("No Tyres Available With that Name");	
						} else{
							issue=response.IssuedTyres;
							for(var i=0;i<issued_tyre.length;i++){
								ary.push(issued_tyre[i].tyre_number);
							}
						} 

						$("#issued_tyre_number").autocomplete({
							source:ary,
							minLength: 1

						});

					},
					error: function(error) {
						console.log(error);
					}

				});

			}
		}


		$.ui.autocomplete.filter = function (array, term) {
			var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
			return $.grep(array, function (value) {
				return matcher.test(value.label || value.value || value);
			});
		};

	});
}




function addOption_list6(){
	$(document).ready(function() {

		if($('#returned_brand_name :selected').val() != 'Select'  ){
			//		  &&  $('#returned_tyre_type :selected').val() != 'Select'

			var requestData = new Object();
			requestData.brand_name=$('#returned_brand_name :selected').val();	
			//				requestData.tyre_type=$('#returned_tyre_type :selected').val();

			$.ajax({
				url: '/transport/tyre/all/runningtyres/get',
				type: 'POST',
				dataType: 'json',
				data: JSON.stringify(requestData),
				contentType: 'application/json; charset=utf-8',
				success: function(response) {
					returned_tyre=response.ReturnedTyres;
					//	  	alert(returned_tyre);
					if(returned_tyre==''){
						alert("No Tyres Available With that Name");	
					}
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

		$.ui.autocomplete.filter = function (array, term) {
			var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
			return $.grep(array, function (value) {
				return matcher.test(value.label || value.value || value);
			});
		};

	});
}

function currentDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
		dd='0'+dd;
	} 
	if(mm<10){
		mm='0'+mm;
	} 
	var today =yyyy+'/'+mm+'/'+ dd;
	$("#issued_and_returned_date").val(today);

}

function tyre(){

	if($("#issued_tyre_category :selected").val() == 'new') {		
		$('#tyre_div').css("display", 'block');

	}else{
		$('#tyre_div').css("display", 'none');

	}
};