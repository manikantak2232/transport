
var factory_dispatch_id;
var company_name;
var association_id;
var companyNames;

function querystringAssociation(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}

function querystringName(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}

function querystringId(key) {
	   var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
	   var r=[], m;
	   while ((m=re.exec(document.location.search)) != null) r.push(m[1]);
	   return r[0];
	}


   function getDispatch() {
	   factory_dispatch_id=querystringId('factory_dispatch_id');
	   company_name=querystringName('company_name');
	   association_id=querystringAssociation('association_id');
	   getUnloadLocation(association_id);
	   getCompanyNames(association_id);
		var requestData = new Object();
		requestData.factory_dispatch_id= factory_dispatch_id;
		requestData.company_name= company_name;	

		$.ajax({
			url: '/transport/factory/dispatch/by/id',
			type: 'POST',
			data: JSON.stringify(requestData),
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
            success: function(response) {
        //     	alert('factory');
            	var  factories = response.Factories ;	            	
            	$.each( factories , function(key, value){
            		$('#'+key+'').val(value);	            	   
            	});
            	setSelectedIndex(document.getElementById("company_list"),$('#companyName').val());
            	setSelectedIndex1(document.getElementById("location_list"),$('#unloadLocation').val());
            	
            },
          error: function(error) {
                console.log(error);
            }
        });
    };
  
    function getCompanyNames(association_id){
    		$.ajax({
    			url: '/transport/factory/outside/company/names/get?'+"association_id="+association_id,
    			type: 'GET',
    			dataType: 'json',
    			contentType: 'application/json; charset=utf-8',
    			success: function(response) {
/*    				names_list=response.names_list;
    				companyNames=response.companyNames;*/
    				 companyNames=response.companyNames;
    				companyDropDownOptions(companyNames);
    				setSelectedIndex(document.getElementById("company_list"),$('#companyName').val());
    				setSelectedIndex1(document.getElementById("location_list"),$('#unloadLocation').val());
    				  
    				},
    			error: function(error) {
    				console.log(error);
    			}
    		});
    	};

    function companyDropDownOptions(companyNames){
    //	alert('drop');
    	for(var i=0;i<companyNames.length;++i){
    		addCompanyOption(document.add.company_list,companyNames[i].outside_company_name,companyNames[i].pk_outside_company_id);
    	}
    }
    function addCompanyOption(selectbox,text,id)
    {
    	var optn=document.createElement("OPTION");
    	optn.text=text;
    	optn.value=text;
    	optn.id=id;
    	selectbox.options.add(optn);
    }
    function companyChange(){
    	var selectedTruck=$("#company_list :selected").text();
    	var selectedTruckId=$("#company_list :selected").attr("id");
    	$("#outside_company_id").val(selectedTruckId);
    	// alert(selectedTruckId);
    }

    function setSelectedIndex(s, v) {
	    for ( var i = 0; i < s.options.length; i++ ) {
	        if ( s.options[i].text == v ) {
	            s.options[i].selected = true;
	            return;
	        }
	    }
    }
    
    function setSelectedIndex1(s, v) {
	    for ( var i = 0; i < s.options.length; i++ ) {
	        if ( s.options[i].text == v ) {
	            s.options[i].selected = true;
	            return;
	        }
	    }
    }
    
    function addDropDownOptions2(locations){
    	for(var i=0;i<locations.length;++i){
    		addOption2(document.add.location_list,locations[i].name,locations[i].pk_unload_location_id);
    	}
    }
    function addOption2(selectbox,text,id)
    {
    	var optn=document.createElement("OPTION");
    	optn.text=text;
    	optn.value=text;
    	optn.id=id;
    	selectbox.options.add(optn);
    }
    function getUnloadLocation(association_id){		
    		$.ajax({
    			url: '/transport/factory/unload/locations/get?'+'association_id='+association_id,
    			type: 'GET',
    			dataType: 'json',
    			contentType: 'application/json; charset=utf-8',
    			success: function(response) {
    				var locations=response.unloadLocations;
    				addDropDownOptions2(locations);
       				setSelectedIndex(document.getElementById("company_list"),$('#companyName').val());
    				setSelectedIndex1(document.getElementById("location_list"),$('#unloadLocation').val());
    			},
    			error: function(error) {
    				console.log(error);
    			}
    	});
    }
    function locationChange(){
    	var selectedTruck=$("#location_list :selected").text();
    	var selectedTruckId=$("#location_list :selected").attr("id");
    	$("#unload_location_id").val(selectedTruckId);
    	// alert(selectedTruckId);
    }

    function updateDispatch() {
		var requestData = new Object();
		requestData.factory_dispatch_id= factory_dispatch_id;
		requestData.truck_number= $('#truck_number').val();
		requestData.driver_name= $('#driver_name').val();
	//	requestData.loading_date= $('#loading_date').val();
		requestData.invoice_date= $('#invoice_date').val();
		requestData.invoice_number=$('#invoice_number').val();
		requestData.unload_location_id=$('#unload_location_id').val();
		requestData.outside_company_id=$('#outside_company_id').val();
		requestData.type_of_cement=$('#type_of_cement').val();
		requestData.unload_location_name=$('#unload_location_name').val();
		requestData.load_quantity=$('#load_quantity').val();
		requestData.freight=$('#freight').val();

		$.ajax({
			url: '/transport/factory/outside/dispatch/update',
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
	};