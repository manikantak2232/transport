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
	  $("#date").val(today);

	  }
 
 var spares='';
 function addDropDownOptions1(parts){
	  for(var i=0;i<parts.length;++i){
	//	  addOption1(document.add.part_list,parts[i].name,parts[i].pk_spare_parts_id);
		  addOption4(document.add.part_list,parts[i].name,parts[i].pk_spare_parts_id);
	  }
	  }
 function addOption1(selectbox,text,id )
 {
	  var optn=document.createElement("OPTION");
	  optn.text=text;
	  optn.value=id;
	  selectbox.options.add(optn);
 }
 
 function addOption4(selectbox,text,id )
 {

	  spares=spares+"<label><input type='checkbox' name='chk' value="+id+">"+text+"</label>";
	  $('#sparePart').html(spares);
	
 }

 
 function addOption_list1(){
	  $(document).ready(function() {
	  $.ajax({
			url: '/transport/spareparts/all/get',
			type: 'GET',
		   dataType: 'json',
			contentType: 'application/json; charset=utf-8',
         success: function(response) {
       	  var parts=response.SpareParts;
       	  addDropDownOptions1(parts);  

/*      	  $('#part_list').multiselect({
                 includeSelectAllOption: true
                
             });*/
         },
         
         error: function(error) {
             console.log(error);
         }
		});
	});
}

 function testChange1(){ 

	var selectedPartId2=$("#part_list :selected").val(); 
  	     var option_all = $("select option:selected").map(function () {
  	        return $('this').val();
  	  }).get().join(','); 
  	     
  	    alert(option_all);
  	    
  	//    $("#part_id").val($('#part_list').val());
 }
 
 var partName;
 var partNameList='';
  
 		function funct() {
 			
			var requestData = new Object();
			requestData.fk_truck_id=$('#truck_id').val();
	//		requestData.count=$('#count').val();
			requestData.date=$('#date').val();
			requestData.fk_spare_part_id=part_id;
			requestData.fk_driver_id=$('#fk_driver_id').val();
		
	        $.ajax({
	        	url: '/transport/spareparts/allotment/add1',
				type: 'POST',
	            dataType: 'json',
	            data: JSON.stringify(requestData),
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {	            		 	         
	          //  	$("#Message").html("Inserted Successfully");
	            	var message=response.message;
	            	if(message=='some parts missing'){
	            		var partList=response.partNames;
		            	for(var i=0; i<partList.length; i++){
		            		partName = partList[i];
		            		var part_name=partName.spare_part_name;
		            		
		            		if(partNameList==''){
		            			partNameList = partNameList.concat(part_name);
		          		  	}
		            		else{
		            			partNameList = partNameList.concat(', '+part_name);
		          		  	}
		            	}
		            	
		            	alert('Following Parts Are Not Available in Inventory '+partNameList);
	            	}
	            	else{
	            		alert(message);
	            	}
	            	
	           
	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    };
  
  /*  $('.mutliSelect input[type="checkbox"]').on('click', function() {

	  var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
	    title = $(this).val() + ",";
	alert(title);
		  if ($(this).is(':checked')) {
	    var html = '<span title="' + title + '">' + title + '</span>';
	    $('.multiSel').append(html);
	alert(html);
	  } else {
	    $('span[title="' + title + '"]').remove();
	    var ret = $(".hida");
	    alert(ret);
	    $('.dropdown1 dt a').append(ret);
	    alert(ret);
	    }

	 
	});*/
  
/*  function fu(){
  var $boxes = $('input[name=chk]:checked');
  alert($boxes);
  return false;
  if ($(this).is(':checked')) {
	     var option_all = $("select option:selected").map(function () {
	  	        return $('this').val();
	  	  }).get().join(','); 
  }
  }*/
  
 /* $(document).bind('click', function(e) {
	  var $clicked = $(e.target);
	  if (!$clicked.parents().hasClass("dropdown1")) $(".dropdown1").hide();
	});
 */
  var part_id='';
  
  function CallOnEachCheckBoxChangeEvent(){
//	  part_id=[];
	  part_id='';
	 
	  $("input:checkbox[name=chk]:checked").each(function(){
		//  part_id.push($(this).val());
		  var id=$(this).val();
		  
		  if(part_id==''){
			  part_id = part_id.concat(id);
		  }
		  else{
			  part_id = part_id.concat(','+id);
		  }
		  
		  
		});
	        //    alert(yourArray);
	  funct();
	}
  
  $(document).ready(function() {
  $('#selectBox').on('click', function(e) {
	//  $('#checkboxes').show();
	  
	  var x = document.getElementById('checkboxes');
	    if (x.style.display === 'none') {
	        x.style.display = 'block';
	    } else {
	        x.style.display = 'none';
	    }
	    e.stopPropagation();
	});
  });
  $(document).ready(function() {
	  $('#checkboxes').on('click', function(e) {
		  $('#checkboxes').show();
		    e.stopPropagation();
		});
	  });
  $(document).ready(function() {
	$(document).on('click', function (e) {
		$('#checkboxes').hide();
		
	});
  });  
  
  function viewDetails() {
		/*$('#factories').css("display", 'block');*/
		
		 var x = document.getElementById('checkboxes');
		    if (x.style.display === 'none') {
		        x.style.display = 'block';
		    } else {
		        x.style.display = 'none';
		    }
	}
  
  function hai() {
		
		   alert('hai');
	}
  
  function fu() {
	    $("#ha").html($("input[type=checkbox]:checked").length +' Selected');
	};
