var trip_id;
var fk_driver_id;
var drivers;
var driverAdvance;
var balance;
var driver_name;

function DriverAdvance(driverAdvance){
	
	var resultObject = search(fk_driver_id, driverAdvance);
	  function search(nameKey, myArray){
		    for (var i=0; i < myArray.length; i++) {
		        if (myArray[i].fk_driver_id == nameKey) {
		        	driver_name= myArray[i].driver_name;
		        	balance= myArray[i].balance;
		        
		        }
		    }
		}
	  $("#driver_name").val(driver_name);
	  $("#balance").val(balance);

}



/*function addDriverDropDownOptions(drivers){
	  for(var i=0;i<drivers.length;++i){
		  addDriverOption(document.add.driver_list,drivers[i].driver_name,drivers[i].driver_id);
	  }
	  }
  function addDriverOption(selectbox,text,id )
  {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
	 }*/
  
  function getDrivers(){
	  factory_dispatch_id=querystring('factory_dispatch_id');
	  fk_driver_id=querystringDriver('fk_driver_id');
	  
			var requestData = new Object();
			requestData.factory_dispatch_id=factory_dispatch_id;
			$.ajax({
			url: '/transport/factory/driver/expenditure/close/get',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
        	  drivers=response.Drivers;
        	  driverAdvance=response.driverAdvance;
        	  DriverAdvance(driverAdvance);
        	  
        //	  addDriverDropDownOptions(drivers);
        	  
          },
          error: function(error) {
              console.log(error);
          }
		
	});
}
/*  function driverChange(){
	  var selectedDriver=$("#driver_list :selected").text();
	  var selectedDriverId=$("#driver_list :selected").val();
	  $("#fk_driver_id").val(selectedDriverId);
  }*/
  
  function querystring(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}
  
  function querystringDriver(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}



$(document).ready(function (){ 
	  $('#insert').click(function () {
		  $('#loading').show();
		  
		  var transport=document.getElementById("transport").value;
		  var load=document.getElementById("load").value;
		  var cover_tying=document.getElementById("cover_tying").value;
		  var contonment=document.getElementById("contonment").value;
		  var toll_gate=document.getElementById("toll_gate").value;
		  var loading_wage=document.getElementById("loading_wage").value;
		  var unloading_wage=document.getElementById("unloading_wage").value;
		  var phone_bills=document.getElementById("phone_bills").value;
		  var spares_parts=document.getElementById("spares_parts").value;
		  var puncher=document.getElementById("puncher").value;
		  var entry=document.getElementById("entry").value;
		  var return_transport=document.getElementById("return_transport").value;
		  var return_loading=document.getElementById("return_loading").value;
		  var return_unloading=document.getElementById("return_unloading").value;
		  var others=document.getElementById("others").value;
		  
		  if(transport=='') {
			transport=0;
		  }
		  if(load=='') {
			  load=0;
			  }
		  if(cover_tying=='') {
			  cover_tying=0;
			  }
		  if(contonment=='') {
			  contonment=0;
			  }
		  if(toll_gate=='') {
			  toll_gate=0;
			  }
		  if(loading_wage=='') {
			  loading_wage=0;
			  }
		  if(unloading_wage=='') {
			  unloading_wage=0;
			  }
		  if(phone_bills=='') {
			  phone_bills=0;
			  }
		  if(spares_parts=='') {
			  spares_parts=0;
			  }
		  if(puncher=='') {
				puncher=0;
			  }
		  if(entry=='') {
				entry=0;
			  }
		  if(return_transport=='') {
			  return_transport=0;
			  }
		  if(return_loading=='') {
			  return_loading=0;
			  }
		  if(return_unloading=='') {
			  return_unloading=0;
			  }
		  if(others=='') {
				others=0;
		  } 
		  
		var requestData = new Object();
		requestData.factory_dispatch_id= factory_dispatch_id;
		requestData.fk_driver_id= fk_driver_id;
		requestData.transport= transport;
		requestData.loading= load;
		requestData.cover_tying=cover_tying;
		requestData.contonment=contonment;
		requestData.toll_gate=toll_gate;
		requestData.loading_wage= loading_wage;
		requestData.unloading_wage= unloading_wage;
		requestData.phone_bills= phone_bills;
		requestData.spares_parts= spares_parts;
		requestData.puncher= puncher;
		requestData.entry= entry;
		requestData.return_transport=return_transport;
		requestData.return_loading= return_loading;
		requestData.return_unloading= return_unloading;
		requestData.others= others;
		requestData.balance= $('#balance').val();
		
		$.ajax({
			url: '/transport/factory/driver/expenditure/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
          	          	
          //	  alert("success")  
        	  $('#loading').hide();
          	alert(response.message);
          	
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
});
 function calculate() {
//	 var balance=document.getElementById("balance").value;
	  var transport=document.getElementById("transport").value;
	  var load=document.getElementById("load").value;
	  var cover_tying=document.getElementById("cover_tying").value;
	  var contonment=document.getElementById("contonment").value;
	  var toll_gate=document.getElementById("toll_gate").value;
	  var loading_wage=document.getElementById("loading_wage").value;
	  var unloading_wage=document.getElementById("unloading_wage").value;
	  var phone_bills=document.getElementById("phone_bills").value;
	  var spares_parts=document.getElementById("spares_parts").value;
	  var puncher=document.getElementById("puncher").value;
	  var entry=document.getElementById("entry").value;
	  var return_transport=document.getElementById("return_transport").value;
	  var return_loading=document.getElementById("return_loading").value;
	  var return_unloading=document.getElementById("return_unloading").value;
	  var others=document.getElementById("others").value;
	  
	  if(transport=='') {
		transport=0;
	  }
	  if(load=='') {
		  load=0;
		  }
	  if(cover_tying=='') {
		  cover_tying=0;
		  }
	  if(contonment=='') {
		  contonment=0;
		  }
	  if(toll_gate=='') {
		  toll_gate=0;
		  }
	  if(loading_wage=='') {
		  loading_wage=0;
		  }
	  if(unloading_wage=='') {
		  unloading_wage=0;
		  }
	  if(phone_bills=='') {
		  phone_bills=0;
		  }
	  if(spares_parts=='') {
		  spares_parts=0;
		  }
	  if(puncher=='') {
			puncher=0;
		  }
	  if(entry=='') {
			entry=0;
		  }
	  if(return_transport=='') {
		  return_transport=0;
		  }
	  if(return_loading=='') {
		  return_loading=0;
		  }
	  if(return_unloading=='') {
		  return_unloading=0;
		  }
	  if(others=='') {
			others=0;
		  }
	
	  
	//  var balance=document.getElementById("balance").value;
	  var myResult=balance-(+transport+ +load+ +cover_tying+ +contonment+ +toll_gate+ +loading_wage+
	  		 +unloading_wage+ +phone_bills+ +spares_parts+ +puncher+ +entry+ +return_transport+
	  		 +return_loading+ +return_unloading+ +others);
	  
	 // balance.value=myResult;
	  if (myResult == Number.POSITIVE_INFINITY || myResult == Number.NEGATIVE_INFINITY)
	  {
		  myResult='';
	  }
	  $("#balance").val(myResult);
}