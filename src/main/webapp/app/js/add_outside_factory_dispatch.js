var association_name;
var manager_type;
var fk_association_id;
var outside_company_id;
var companyName;

function querystringCompanyId(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}

function querystringCompanyName(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
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
$("#dispatch_date").val(today);

	  }
  
  
  var delivery_number2;
  var po_number2;
  var invoice_number2;
  var freight2;
  var load_quantity2;

  $(document).ready(function (){
  	$('#btnAdd').click(function () {
  		var x = document.getElementById('invoice_div');
  		if (x.style.display === 'none') {
  		    x.style.display = 'block';
  			$('#load_quantity_div').css("display", 'block');
  			$('#freight_div').css("display", 'block');
  			if(association_name=='zuari'){
  				$('#numbers2').css("display", 'block');
  			}
  			$('#btnAdd').val("Hide");
  			
  		} else {
  		    x.style.display = 'none';
  			$('#load_quantity_div').css("display", 'none');
  			$('#freight_div').css("display", 'none');
  			$('#numbers2').css("display", 'none');
  			$('#btnAdd').val("Add Invoice");
  		}
  		
  		
  	});	
  });


function addDispatchDetails(){  
	$('#loading').show();
	
	delivery_number=$('#delivery_number').val();
	po_number=$('#po_number').val();
	
	delivery_number2=$('#delivery_number2').val();
	po_number2=$('#po_number2').val();
	invoice_number2=$('#invoice_number2').val();
	load_quantity2=$('#load_quantity2').val();
	freight2=$('#freight2').val();
	
	if(delivery_number==undefined){ 
		delivery_number='';
	}
	if(po_number==undefined){ 
		po_number='';
	}
	if(invoice_number2==undefined){ 
		invoice_number2='';
	}
	if(delivery_number2==undefined){ 
		delivery_number2='';
	}
	if(po_number2==undefined){ 
		po_number2='';
	}
	if(load_quantity2==undefined | load_quantity2==''){ 
		load_quantity2=0;
	}
	if(freight2==undefined | freight2==''){ 
		freight2=0;
	}
	
		var truck_no=$('#truck_number').val();
	
		var requestData = new Object();
		requestData.truck_number=truck_no.toUpperCase();
		requestData.driver_name=$('#driver_name').val();
		requestData.driver_phone_number=$('#driver_phone_number').val();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.start_location=$('#start_location').val();
	//	requestData.unload_location=$('#unload_location').val();
		requestData.unload_location_id=$("#unload_location_id").val();
		requestData.unload_location_name=$('#unload_location_name').val();
		requestData.type_of_cement=$('#type_of_cement').val();
		requestData.dispatch_date=$('#dispatch_date').val();
		requestData.load_quantity=$('#load_quantity').val();
		requestData.freight=$('#freight').val();
		requestData.delivery_number= delivery_number;
		requestData.po_number= po_number;
		requestData.delivery_number2= delivery_number2;
		requestData.po_number2= po_number2;
		requestData.invoice_number2= invoice_number2;
		requestData.load_quantity2= load_quantity2;
		requestData.freight2= freight2;
		requestData.fk_association_id=fk_association_id;
		requestData.outside_company_id=outside_company_id;

		$.ajax({
			url: '/transport/factory/dispatch/outside/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	
				$('#loading').hide();
				$("#Message").html(response.message);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}


function userDetails(){
	$(document).ready(function() {
		outside_company_id=querystringCompanyId('outside_company_id');
		companyName=querystringCompanyName('company_name');
	//	alert(decodeURI(companyName));
//		companyName.replace(/%20/g, " ");
		if(companyName=='other'){
			$('#companyName').val('');
		}
		$('#companyName').val(decodeURI(companyName));
		$.ajax({
			url: '/transport/login/user/details/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var userDetails=response.userDetails;
				association_name=userDetails.association_name;
				manager_type=userDetails.manager_type;
				$('#start_location').val(association_name);
				if(association_name=='zuari'){
					$('#numbers').css("display", 'block');
				}
				fk_association_id=userDetails.association_id;
				getUnloadLocation(userDetails.association_id);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}
$(document).ready(function(){

	$('#load_quantity').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

	$('#freight').keypress(function (e) {
	    var regex = new RegExp("^[0-9.]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});

});

/*function loc(){
	  
	if($("#location_list :selected").text() == 'hyderabad') {		
		$('#unload_loc_id').css("display", 'block');
		
	}else{
		$('#unload_loc_id').css("display", 'none');

	}
};*/
		
