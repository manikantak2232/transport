var storage_dispatch_id;
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
	  storage_dispatch_id=querystring('storage_dispatch_id');
	  fk_driver_id=querystringDriver('fk_driver_id');
	  
			var requestData = new Object();
			requestData.storage_dispatch_id=storage_dispatch_id;
			$.ajax({
			url: '/transport/storage/driver/expenditure/close/get',
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
		var requestData = new Object();
		requestData.storage_dispatch_id= storage_dispatch_id;
		requestData.fk_driver_id= fk_driver_id;
		requestData.transport= $('#transport').val();
		requestData.loading= $('#loading').val();
		requestData.cover_tying= $('#cover_tying').val();
		requestData.contonment= $('#contonment').val();
		requestData.toll_gate= $('#toll_gate').val();
		requestData.loading_wage= $('#loading_wage').val();
		requestData.unloading_wage= $('#unloading_wage').val();
		requestData.phone_bills= $('#phone_bills').val();
		requestData.spares_parts= $('#spares_parts').val();
		requestData.puncher= $('#puncher').val();
		requestData.entry= $('#entry').val();
		requestData.return_transport= $('#return_transport').val();
		requestData.return_loading= $('#return_loading').val();
		requestData.return_unloading= $('#return_unloading').val();
		requestData.others= $('#others').val();
		requestData.balance= $('#balance').val();;
		
		$.ajax({
			url: '/transport/storage/driver/expenditure/add',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
          success: function(response) {
          	          	
          //	  alert("success")  
          	$("#Message").html("Updated Successfully");
          	
          },
          error: function(error) {
              console.log(error);
          }
		});
	});
});
 function calculate() {
	// var balance=document.getElementById("balance").value;
	  var transport=document.getElementById("transport").value;
	  var loading=document.getElementById("loading").value;
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
	  
	  
	//  var balance=document.getElementById("balance").value;
	  var myResult=balance-(+transport+ +loading+ +cover_tying+ +contonment+ +toll_gate+ +loading_wage+
	  		 +unloading_wage+ +phone_bills+ +spares_parts+ +puncher+ +entry+ +return_transport+
	  		 +return_loading+ +return_unloading+ +others);
	  
	 // balance.value=myResult;
	  $("#balance").val(myResult);
}