var totalQuantity=0;
var loading_rows;
var Reloading_rows;
var service_rows;
var empty_rows;
var table;
var code_rows;
var header_rows;
var total_rows;
var cgst_rows;
var sgst_rows; 
var  Grand_rows;
var unloadingPpc="";
var	unloadingOpc="";
var loadingPpc="";
var loadingOpc="";
var crossingOpc="";
var crossingPpc="";
var directOpc="";
var directPpc="";
var totalPpc="";
var totalOpc="";
var totalCrossingOpcTrade="";
var totalLoadingOpcTrade="";
var totalCrossingPpcTrade="";
var totalLoadingPpcTrade="";
var totalDirectPpcTrade="";
var totalDirectOpcTrade="";

var  totalDirectPsc ="";
var totalDirectCon = "";
var totalLoadingPsc="";
var totalLoadingCon="";
var totalUnloadingPsc="";
var totalUnloadingCon="";
var totalCrossingPsc="";
var totalCrossingCon="";
var hamaliDetails;
var listOfHamali;
var listOfService;
var listOfFixed;
var listOfAssociation;
var listOfHamaliTrade;
var listOfHamaliPsc;
var listOfHamaliCon;
var association;
var godown;
var total_q;
var serviceCharge;
var fixedExpenses;
var today;


function list(){
	hamaliDetails='';
	listOfHamali ='';
	listOfService='';
	listOfFixed = '';
	 listOfAssociation = '';
	 listOfHamaliTrade = '';
     listOfHamaliPsc = '';
	 listOfHamaliCon = '';
	 
	 var now = new Date();
		var month = (now.getMonth() + 1);               
		var day = now.getDate();
		if(month < 10) 
			month = "0" + month;
		if(day < 10) 
			day = "0" + day;
		today =  day + '/' + month + '/' + now.getFullYear();

	
	  association =  $('#associationName').val();
	var requestData = new Object();

	requestData.lowerDate= $('#lowerDate').val();
	requestData.upperDate= $('#upperDate').val();
	requestData.associationName= $('#associationName').val();	
	
	$.ajax({
		url: '/transport/godown/hamali/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			 hamaliDetails=response.HamaliDetails;
		
			 var listHamali= hamaliDetails.filter(obj=>obj.association_name==$('#associationName option:selected' ).text());
                
			// alert( listHamali[0].hamali);
				listOfHamali=listHamali[0].hamali;
				
				listOfService=listHamali[0].service_charge;
				listOfFixed=listHamali[0].fixed_expenses;
				listOfAssociation=listHamali[0].association_name;
				listOfHamaliTrade=listHamali[0].hamali_trade;
				listOfHamaliPsc=listHamali[0].hamali_psc;
				listOfHamaliCon=listHamali[0].hamali_con;
				
				
			//	alert(listOfHamaliCon);
			//	var association =  $('#associationName').val();
				if(association == 'zuari'){
					 $('#block1').css('display','block');
					 $('#block2').css('display','none');
					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#block7').css('display','none');
					 
					 $('#HamaliTons').val(listOfHamali);
						$('#serviceCharge').val(listOfService);
					 
				}
					
				if(association == 'deccan'){
					 $('#block1').css('display','block');

					 $('#block2').css('display','none');
					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#block7').css('display','none');
					 $('#HamaliTons').val(listOfHamali);
						$('#serviceCharge').val(listOfService);
				}
				if(association == 'ramco'){
					 $('#block1').css('display','block');
					 $('#block2').css('display','block');
	
					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#block7').css('display','none');
					 $('#HamaliTons').val(listOfHamali);
						$('#serviceCharge').val(listOfService);
						$('#fixedExpenses').val(listOfFixed);
						
						
				}
				
				if(association == 'anjani'){
					 $('#block1').css('display','block');
					 $('#block2').css('display','block');

					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#block7').css('display','none');
					 $('#HamaliTons').val(listOfHamali);
						$('#serviceCharge').val(listOfService);
						$('#fixedExpenses').val(listOfFixed);
				}
				if(association == 'jsw'){
					 $('#block3').css('display','block');
					 $('#block4').css('display','block');

					 $('#block1').css('display','none');
					 $('#block2').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#block7').css('display','none');
					 $('#hamaliPsc').val(listOfHamaliPsc);
						$('#hamaliCon').val(listOfHamaliCon);
						$('#fixedExpenses1').val(listOfFixed);
						$('#serviceCharge1').val(listOfService);
				}
				if(association == 'bharathi'){
					 $('#block5').css('display','block');
					 $('#block6').css('display','block');

					$('#block1').css('display','none');
					 $('#block2').css('display','none');
					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block7').css('display','none');
					 $('#HamaliTons5').val(listOfHamali);
						$('#hamaliTrade').val(listOfHamaliTrade);
						$('#fixedExpenses5').val(listOfFixed);
				}
				if(association == 'chettinad'){
					$('#block7').css('display','block');

					$('#block1').css('display','none');
					 $('#block2').css('display','none');
					 $('#block3').css('display','none');
					 $('#block4').css('display','none');
					 $('#block5').css('display','none');
					 $('#block6').css('display','none');
					 $('#HamaliTons7').val(listOfHamali);
				}
				
				
				
				
			
				

		},
		error: function(error) {
			console.log(error);
		}

});
	

		
	
	
}

function getBill() {

	$('#tabl1').css('display','none');
	$('#ramco').css('display','none');
	$('#anjani').css('display','none');
	$('#deccan').css('display','none');
    $('#bharathi').css('display','none');
    $('#jsw').css('display','none');
    $('#chettinad').css('display','none');
    $('#billInfo').css('display','none');
    $('#export_zuari').css('display','none');
    $('#export_ramco').css('display','none');
    $('#export_jsw').css('display','none');
    $('#export_deccan').css('display','none');
    $('#export_chettinad').css('display','none');
    $('#export_bharathi').css('display','none');
    $('#export_bharathi1').css('display','none');
    $('#export_anjani').css('display','none');
	
	var hamali = $('#HamaliTons').val();
	var hamali5 = $('#HamaliTons5').val();
	var hamali7 = $('#HamaliTons7').val();
	 serviceCharge=$('#serviceCharge').val();
	var serviceCharge1 = $('#serviceCharge1').val();
	var company = $('#associationName').val();
	 fixedExpenses = $('#fixedExpenses').val();
	var fixedExpenses1 = $('#fixedExpenses1').val();
	var fixedExpenses5 = $('#fixedExpenses5').val();
    var fromDate = $('#lowerDate').val();
    var toDate =  $('#upperDate').val();
     godown = $('#unloadLocationName').val();
    var hamaliTrade = $('#hamaliTrade').val();
    var hamaliPsc = $('#hamaliPsc').val();
    var hamaliCon = $('#hamaliCon').val();
    
	var requestData = new Object();

	/*		requestData.lower_date= today;
			requestData.upper_date= today;*/
	requestData.lowerDate= $('#lowerDate').val();
	requestData.upperDate= $('#upperDate').val();
	requestData.associationName= $('#associationName').val();	
	requestData.unloadLocationName= $('#unloadLocationName').val();


	$.ajax({
		url: '/transport/godown/inward/get/bill',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	 

			var inwardDetails = response.bills;
			total_q=0;
			if(inwardDetails!=null){
				for(var i=0; i<inwardDetails.length; i++){
					inward = inwardDetails[i];

					var Qua = inward.totalquantity;
					var action = inward.actionlist;
					var cement = inward.typeCement;
					total_q = total_q+Qua;




					if(action == 'unloading' & cement == 'PPC')
					{

						unloadingPpc = Qua;
					//	alert(unloadingPpc);
					}
					if(action == 'unloading' & cement == 'OPC')
					{

						unloadingOpc =Qua;
					//	alert(unloadingOpc);
					}
					if(action == 'loading' & cement == 'PPC')
					{

						loadingPpc = Qua;
					//	alert(loadingPpc);
					}
					if(action == 'loading' & cement == 'OPC')
					{

						loadingOpc = Qua;
					//	alert(loadingOpc);
					}
					if(action == 'crossing' & cement == 'PPC')
					{

						crossingPpc = Qua;
						//alert(crossingPpc);
					}
					if(action == 'crossing' & cement == 'OPC')
					{

						crossingOpc = Qua;
						//alert(crossingOpc);
					}
					
					if(action == 'direct' & cement == 'PPC')
					{

						directPpc = Qua;
						alert(directPpc);
					}
					if(action == 'direct' & cement == 'OPC')
					{

						directOpc =Qua;
						alert(directOpc);
					}
					
					if(cement == 'PPC'){
						
						totalPpc += Qua;
					//	alert("totalPpc",totalPpc);
					}
					
					if(cement == 'OPC'){
						
						totalOpc = +totalOpc + Qua;
					//	alert(totalOpc);
					}
					
                       if(action == 'loading' & cement == 'OPC 53 PPW (TRADE)'){
						
						totalLoadingOpcTrade =  Qua;
					//	alert(totalOpcTrade);
					}
                       if(action == 'crossing' & cement == 'OPC 53 PPW (TRADE)'){
   						
   						totalCrossingOpcTrade =  Qua;
   					//	alert(totalOpcTrade);
   					}
                       
                       if(action == 'direct' & cement == 'OPC 53 PPW (TRADE)'){
      						
      						totalDirectOpcTrade =  Qua;
      					//	alert(totalOpcTrade);
      					}
                       if(action == 'loading' & cement == 'PPC  PPW (TRADE)'){
      						
      						totalLoadingPpcTrade =  Qua;
      					//	alert(totalOpcTrade);
      					}
                       if(action == 'crossing' & cement == 'PPC  PPW (TRADE)'){
   						
   						totalCrossingPpcTrade =  Qua;
   					//	alert(totalOpcTrade);
   					}
                       if(action == 'direct' & cement == 'PPC  PPW (TRADE)'){
      						
      						totalDirectPpcTrade =  Qua;
      					//	alert(totalOpcTrade);
      					}
                       
                       
                     
                          
                       
                       if(action == 'direct' & cement == 'PSC'){
   						
   						totalDirectPsc =   Qua;
   					
   					}
                       
                       if(action == 'direct' & cement == 'CON'){
      						
      						totalDirectCon =   Qua;
      			
      					}
                       
                       if(action == 'loading' & cement == 'PSC'){
      						
      						totalLoadingPsc =   Qua;
      					
      					}
                          
                          if(action == 'loading' & cement == 'CON'){
         						
         						totalLoadingCon =   Qua;
         			
         					}
   					
                          if(action == 'unloading' & cement == 'PSC'){
         						
         						totalUnloadingPsc =   Qua;
         					
         					}
                             
                             if(action == 'unloading' & cement == 'CON'){
            						
            						totalUnloadingCon =   Qua;
            			
            					}
      					
                             if(action == 'crossing' & cement == 'PSC'){
            						
            						totalCrossingPsc =   Qua;
            					
            					}
                                
                                if(action == 'crossing' & cement == 'CON'){
               						
                                	totalCrossingCon =   Qua;
               			
               					}
         					
					
					

				}

				if(company == 'zuari'){
					
					var tUnloading = (+unloadingPpc + +unloadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc);
					var tUnloadingAmount = ((+loadingPpc + +loadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc)*hamali).toFixed(2);
					var tLoading = (+loadingPpc + +loadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc);
					var tLoadingAmount = ((+loadingPpc + +loadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc)*hamali).toFixed(2);
					var tServiceAmount = ((+unloadingPpc + +unloadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc)*serviceCharge).toFixed(2);
					
					  var  table_mid =                        
						   "<tr width=100% >"+
						   "<td width=50% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "CFA bill details for the period "+fromDate+ " to " +toDate+" at "+godown+" Godown "+ "</td>"+"</tr>"+
						   "<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" +  ""+ "</td>"+
						"</tr>";
						var tablemid= "<table width=95% align=center >" + table_mid+"</table>";		
						
					header_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'SL.NO' + "</td>"+
						"<td colspan='1'style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'PARTICULARS' + "</td>"+		            						
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'QUANTITY' + "</td>"+	
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'RATE' + "</td>"+
						"<td colspan='1'style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'AMOUNT' + "</td>"+

						"</tr>"
						;

					code_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '19KF' + "</td>"+		            						
						"<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+	


						"</tr>"
						;

					loading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 1 + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Loading charges cement bag at godown' + "</td>"+		            						
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tUnloading+ "</td>"+	
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + hamali + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tUnloadingAmount + "</td>"+

						"</tr>"
						;
					Reloading_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Reloading charges cement bag at godown' + "</td>"+		            						
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tLoading + "</td>"+	
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + hamali + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tLoadingAmount + "</td>"+

						"</tr>"
						;
					service_rows = 

						"<tr class='table'>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'Service charges' + "</td>"+		            						
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tUnloading+ "</td>"+	
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + serviceCharge  + "</td>"+
						"<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + tServiceAmount + "</td>"+

						"</tr>"
						;

					empty_rows = 

						"<tr class='table'>"+
						"<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+		            						
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+	


						"</tr>"
						;
					total_rows = 

						"<tr class='table'>"+
						"<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'TOTAL' + "</td>"+		            						
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount ).toFixed(2) + "</td>"+	


						"</tr>"
						;

					cgst_rows = 

						"<tr class='table'>"+
						"<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'CGST - 9%' + "</td>"+		            						
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (( (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount )*9)/(100)).toFixed(2)  + "</td>"+	


						"</tr>"
						;

					sgst_rows = 

						"<tr class='table'>"+
						"<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'SGST - 9%' + "</td>"+		            						
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" +(( (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount )*9)/(100)).toFixed(2)  + "</td>"+	


						"</tr>"
						;
					var gtotal= (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount ).toFixed(2);
					var gcgst=  (( (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount )*9)/(100)).toFixed(2); 
					var gsgst=  (( (+tUnloadingAmount + +tLoadingAmount + +tServiceAmount )*9)/(100)).toFixed(2); 

					Grand_rows = 

						"<tr class='table'>"+
						"<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + '' + "</td>"+
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + 'GRAND TOTAL' + "</td>"+		            						
						"<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>" + (+gtotal + +gcgst + +gsgst).toFixed(2) + "</td>"+	


						"</tr>"
						;


					table= header_rows+code_rows+loading_rows+Reloading_rows+service_rows+empty_rows+empty_rows+empty_rows+empty_rows+empty_rows+empty_rows+total_rows+cgst_rows+sgst_rows+Grand_rows ;
					$('#dataTab').html(table);
					$('#zuarimid').html(tablemid);
					$('#tabl1').css('display','block');
					 $('#export_zuari').css('display','block');
					  $('#billInfo').css('display','block');
				}

				if(company == 'ramco'){


					var now = new Date();
					var month = (now.getMonth() + 1);               
					var day = now.getDate();
					if(month < 10) 
						month = "0" + month;
					if(day < 10) 
						day = "0" + day;
					today =  day + '/' + month + '/' + now.getFullYear();

					table="";
					table_rows = "";
					factory= "";
					tables = "";
					var factory= "";
					var tables = "";  
					var totalCrossing =(+crossingOpc + +crossingPpc);
					var totalUnloading = (+unloadingPpc +  +directPpc + +unloadingOpc + +directOpc);
					var totalLoading = (+loadingPpc + +loadingOpc + +directOpc + +directPpc);
					var totalCrossingAmount = (+totalCrossing*hamali);
					var totalUnloadingAmount = (+totalUnloading*hamali);
					var totalLoadingAmount = (+totalLoading*hamali);
					var totalServiceChargeAmount = ((+totalCrossing + +totalUnloading)*serviceCharge);
					var totalramco = (+totalCrossingAmount + +totalUnloadingAmount + +totalLoadingAmount + +totalServiceChargeAmount);
					var totalCharges = (+totalramco + +fixedExpenses).toFixed(2);
					var CGST = ((+totalCharges*9)/100).toFixed(2);
					var SGST = ((+totalCharges*9)/100).toFixed(2);
					var grandTotal = (+totalCharges + +CGST + +SGST).toFixed(2);
					table_rows = 
						"<tr width=100% >"+
						"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+"invoiceno" + "</th>"+
						"</tr>"+

						"<tr width=100% >"+
						"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "INVOICEDATE: "+today + "</th>"+
						"</tr>";
					tableheading = "<table width=95% align=center >" + table_rows+ "</table>";

					var table_a=
						"<tr width=100% >"+
						"<td width=40% style='text-align:left; padding-left:3px; padding-right:1px'>" +  "Clearing & Forwarding bill for the month of February-2018 for autonagar depot "+ "</td>"+
						"</tr>";
					tabledepot = "<table width=95% align=center >" + table_a+ "</table>";


					var table_c  = 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "S.NO"+ "</th>"+
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "PARTICULARS" + "</th>"+
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "OPC" + "</th>"+
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "PPC" + "</th>"+ 
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "TOTAL QUANTITY"  + "</th>"+ 
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ "RATE" + "</th>"+ 
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "AMOUNT" + "</th>"+ 
						"</tr>"+

						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "1"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "TRANSHIPMENT CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + crossingOpc  + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + crossingPpc + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalCrossing  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalCrossingAmount + "</td>"+ 
						"</tr>"+  
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "GODOWN UNLOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+unloadingOpc + +directOpc) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+unloadingPpc + +directPpc) + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloading  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloadingAmount + "</td>"+ 
						"</tr>"+  

						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "GODOWN LOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+loadingOpc + +directOpc) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+loadingPpc + +directPpc) +"</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoading  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoadingAmount + "</td>"+ 
						"</tr>"+ 

						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "4"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "C&F SERVICE CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingOpc + +unloadingOpc + +directOpc) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingPpc + +unloadingPpc + +directPpc) + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+totalCrossing + +totalUnloading) + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalServiceChargeAmount + "</td>"+ 
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "5"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "FIXED EXPENSES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + fixedExpenses + "</td>"+ 
						"</tr>"+ 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ "TOTAL CHARGES" + "</th>"+
						"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ " " + "</td>"+ 
						"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ " " + "</td>"+ 
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ totalCharges + "</th>"+ 
						"</tr>"+
						"<tr width=150% style= 'border: 1px solid black'>"+
						"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "GST @18% on Total Charges" + "</td>"+
						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"</tr>"+
						"<tr  width=150% style= 'border: 1px solid black'>"+
						"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "CGST @ 9%" + "</td>"+
						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + CGST + "</td>"+ 
						"</tr>"+
						"<tr width=150% style= 'border: 1px solid black'>"+
						"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "SGST @ 9%" + "</td>"+
						"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + SGST  + "</td>"+ 
						"</tr>"+
						"<tr width=150% style= 'border: 1px solid black'>"+
						"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<th width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "GRAND TOTAL" + "</th>"+
						"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
						"<td width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<th width=10% style='background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + grandTotal + "</th>"+ 
						"</tr>"; 
					tabledata = "<table width=95% align=center style= 'border: 1px solid black' >" + table_c +"</table>";
					$('#dataTabramco2').html(tabledepot); 
					$('#dataTabramco1').html(tabledata); 	
					$('#dataTabramco').html(tableheading);
					$('#ramco').css('display','block');
					 $('#export_ramco').css('display','block');
					  $('#billInfo').css('display','block');

					

				}

				if(company == 'anjani'){


					var table = "";
					var table_rows = "";
					var factory= "";
					var tables = "";
					
					var totalUnloading = (+unloadingPpc +  +directPpc + +unloadingOpc + +directOpc + +crossingOpc + +crossingPpc);
					var totalLoading = (+loadingPpc + +loadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc);
					var totalUnloadingAmount = (+totalUnloading * hamali);
					var totalLoadingAmount = (+totalLoading * hamali);
					var serviceChargesAmount = (+totalUnloading * serviceCharge);
					var totalAnjani = (+totalUnloadingAmount + +totalLoadingAmount + +serviceChargesAmount);
					var total = (+totalAnjani + +fixedExpenses ).toFixed(2);
					var CGST  = ((+total*9)/100).toFixed(2);
					var SGST  = ((+total*9)/100).toFixed(2);
					var grandTotal = (+total + +CGST + +SGST ).toFixed(2);


					var now = new Date();
					var month = (now.getMonth() + 1);               
					var day = now.getDate();
					if(month < 10) 
						month = "0" + month;
					if(day < 10) 
						day = "0" + day;
					today =  day + '-' + month + '-' + now.getFullYear();

					table="";
					table_rows = "";
					factory= "";
					tables = "";
					var factory= "";
					var tables = "";

					table_rows = 
						"<tr width=100% >"+
						"<td width=25% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "INVOICE NO :104   "+ "</td>"+

						"<td width=45% style='text-align:left; padding-left:3px; padding-right:3px'>" + "DATE:"+ today+ "</td>"+ 
						"</tr>";

					table1 = "<table width=95% align=center >" + table_rows + "</table>";

					var table_y =
						"<tr width=100% >"+
						"<th width=20% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "Clearing & Forwarding bill for the month of FEBRUARY-2018 FOR AUTO NAGAR DEPOT"+ "</th>"+
						"</tr>";
					table2 = "<table width=95% align=center >" + table_y+"</table>";

					var table_c  = 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "S.NO"+ "</th>"+
						"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "PARTICULARS" + "</th>"+
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "QTY" + "</th>"+
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "RATE" + "</th>"+ 
						"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "AMOUNT" + "</th>"+ 
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "1"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "UNLOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloading + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloadingAmount + "</td>"+ 	
						"</tr>"+ 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "RELOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoading + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoadingAmount + "</td>"+ 
						"</tr>"+ 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "SERVICE CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloading + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + serviceChargesAmount  + "</td>"+  
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "4"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "CFA EXPENSES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + fixedExpenses  + "</td>"+  
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "TOTAL" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						"<td   width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
						"<td  width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + total  + "</td>"+ 
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "CGST@9% "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + CGST+ "</td>"+
						"</tr>" +
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "SGST@9% "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + SGST+ "</td>"+
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "GST@18% "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"</tr>"+
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "GRAND TOTAL "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + grandTotal+ "</td>"+
						"</tr>";  
					table3 = "<table width=95% align=center style= 'border: 1px solid black' >" + table_c + "</table>"   	
					$('#dataTaba3').html(table3);       	
					$('#dataTaba2').html(table2);     	
					$('#dataTaba1').html(table1); 	
					$('#anjani').css('display','block');
					 $('#export_anjani').css('display','block');
					  $('#billInfo').css('display','block');

				}
				if(company == 'deccan'){
					
					
					
					var tquantity = (+crossingOpc + +crossingPpc + +loadingPpc + +loadingOpc + +directPpc + +directOpc)
					var totalService = (+tquantity * serviceCharge);
					var CGST = ((+totalService*9)/100).toFixed(2) ;
					var SGST = ((+totalService*9)/100).toFixed(2) ;
					var grandTotalService = (+totalService + +CGST + +SGST).toFixed(2);
					var totalHamali = (+tquantity * hamali) ;
					var HCGST = ((+totalHamali*9)/100).toFixed(2) ;
					var HSGST = ((+totalHamali*9)/100).toFixed(2) ;
					var grandTotalHamali = (+totalHamali + +HCGST + +HSGST).toFixed(2) ;
					

					var now = new Date();
					var month = (now.getMonth() + 1);               
					var day = now.getDate();
					if(month < 10) 
						month = "0" + month;
					if(day < 10) 
						day = "0" + day;
					today =  day + '.' + month + '.' + now.getFullYear();
				
						table="";
						table_rows = "";
						factory= "";
						tables = "";
						var factory= "";
						var tables = "";  

						table_rows = table_rows +                        
						"<tr width=100% >"+
						"<td width=50%  style='text-align:left; padding-left:3px; padding-right:3px'>" +  "BILL NO : 105 "+ "</td>"+"</tr>"+
						"<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" +"DATE:"+ today+ "</td>"+
						"</tr>";
						var tableheading = "<table width=95% align=center >" + table_rows+"</table>";
						var tableload_rows =                       
							"<tr width=100% >"+
							"<td width=50%  style='text-align:left; padding-left:3px; padding-right:3px'>" +  "BILL NO : 105 "+ "</td>"+"</tr>"+
							"<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" + today + "</td>"+
							"</tr>";
						var tableloadheading = "<table width=95% align=center >" + tableload_rows+"</table>";

						var  table_exp =                        
							"<tr width=100% >"+
							"<td width=50% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "We are here with furnishing C&F commision for the month of February-2018 at Nikhitha Logistics Miyapur godown"+ "</td>"+"</tr>"+
							"<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" +  ""+ "</td>"+
							"</tr>";
						var tableexp= "<table width=95% align=center >" + table_exp+"</table>";		        
						var table_c  = 
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "SAN/HSC NO"+ "</th>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "QTY" + "</th>"+
							"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "RATE PER Mt" + "</th>"+
							"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "AMOUNT" + "</th>"+ 
							"</tr>"+

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "Service Charges On Sales"+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + tquantity + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + totalService + "</td>"+  
							"</tr>"+  
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + ""+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+  
							"</tr>"+  
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "C&F Commission"+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+  
							"</tr>"+  

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "GST @ 18%"+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalService +"@18%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
							"</tr>"+ 

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + ""+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "CGST@9%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (CGST) + "</td>"+  
							"</tr>"+
							"<tr  width=100% >"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "SGST@9%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (SGST) + "</td>"+ 
							"</tr>"+ 
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ "TOTAL Amount" + "</th>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ (grandTotalService) + "</td>"+  
							"</tr>";
						var  tabledata = "<table width=95% align=center >" + table_c+"</table>";
						var table_a  = 
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "SAN/HSC NO"+ "</th>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "QTY" + "</th>"+
							"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "RATE PER Mt" + "</th>"+
							"<th width=10% style= 'border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + "AMOUNT" + "</th>"+ 
							"</tr>"+

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "Unloading&LoadingCharges"+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (total_q) + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (hamali) + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (totalHamali) + "</td>"+  
							"</tr>"+  
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + ""+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+  
							"</tr>"+  

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "GST @ 18%"+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + totalHamali+"@18%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
							"</tr>"+ 

							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + ""+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "CGST@9%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (HCGST) + "</td>"+  
							"</tr>"+
							"<tr  width=100% >"+
							"<td width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
							"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + "SGST@9%" + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							"<td width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + (HSGST) + "</td>"+ 
							"</tr>"+ 
							"<tr  width=100% style= 'border: 1px solid black'>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
							"<th width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ "TOTAL Amount" + "</th>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							"<td width=10% style='border: 1px solid black;background-color:#CCCCCC; padding-left:3px; padding-right:3px'>"+ (grandTotalHamali) + "</td>"+  
							"</tr>";
						var  tableloading = "<table width=95% align=center >" + table_a+"</table>";
						var  table_exp2 =                        
							"<tr width=100% >"+
							"<td width=50% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "We are here with furnishing loading & unloading charges  for the month of February-2018 at Nikhitha Logistics Miyapur godown"+ "</td>"+"</tr>";
						var tableload_exp= "<table width=95% align=center >" + table_exp2+"</table>";
						$('#dataTabdeccan3').html(tableexp); 
						$('#dataTabdeccan').html(tableheading);     	
						$('#dataTabdeccan1').html(tabledata); 	
						$('#dataTabdeccan2').html(tableloading);
						$('#dataTabdeccan4').html(tableload_exp);
						$('#dataTabdeccan5').html(tableloadheading);
						$('#deccan').css('display','block');
						$('#deccan1').css('display','block');
						 $('#export_deccan').css('display','block');
						 $('#export_deccan1').css('display','block');
						  $('#billInfo').css('display','block');

				}
				
			if(company == 'bharathi'){
				
				var table = "";
				var table_rows = "";
				var tPpc = (+loadingPpc + +crossingPpc + +directPpc);
				var tOpc = (+loadingOpc + +crossingOpc+ +directOpc);
				var tPpcTrade = (+totalLoadingPpcTrade + +totalCrossingPpcTrade + +totalDirectPpcTrade);
				var tOpcTrade = (+totalLoadingOpcTrade + +totalCrossingOpcTrade + +totalDirectOpcTrade);
				var tAmountPpc = (+tPpc * hamali5);
				var tAmountOpc = (+tOpc * hamali5);
                var tAmountPpcTrade =(+tPpcTrade * hamaliTrade);
                var tAmountOpcTrade =(+tOpcTrade * hamaliTrade);
                var tQuantity = (+tPpc + +tOpc + +tPpcTrade + +tOpcTrade);
                var totalAmount = (+tAmountPpc + tAmountOpc + tAmountPpcTrade + +tAmountOpcTrade);
                var SGST = ((+totalAmount*9)/100);
                var CGST = ((+totalAmount*9)/100);
                var totalTax = (+SGST + +CGST);
                var grandTotal = (+totalAmount + +totalTax);
                var expensesSGST = ((fixedExpenses5*9)/100);
                var expensesCGST = ((fixedExpenses5*9)/100);
                var expensesTax = (+expensesSGST + +expensesCGST);
                
                
				
					 var now = new Date();
					    var month = (now.getMonth() + 1);                 
					    var day = now.getDate(); 
					    if(month < 10) 
					        month = "0" + month;
					    if(day < 10) 
					        day = "0" + day;
					    today =  day + '.' + month + '.' + now.getFullYear();
								
				                                        table="";
									table_rows = "";
					
							  table_rows = table_rows +
							  "<tr  width=100%>"+
								 "<td rowspan='6' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ '1'+"</td>"+
									"<td rowspan='6' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ '996713'+"</td>"+
									"<td rowspan='6'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Handling Charges at Ware House from : 01.02.2018 to 28.02.2018'+"</td>"+
									"</tr>"+"<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'PPC-PPL WH'+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tPpc+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ hamali5+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tAmountPpc+"</td>"+
									"</tr>"+"<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'PPC  GR-PP WH-TRADE'+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tPpcTrade+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ hamaliTrade+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ tAmountPpcTrade +"</td>"+
									"</tr>"+"<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'OPC 53 GR-PPL WH'+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tOpc+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ hamali5+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tAmountOpc+"</td>"+
									"</tr>"+"<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'OPC 53 GR-PP WH-TRADE'+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tOpcTrade+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ hamaliTrade+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ tAmountOpcTrade +"</td>"+
									"</tr>"+"<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ ' TOTAL'+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tQuantity+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ ' '+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ totalAmount+"</td>"+
									"</tr>";
							  
							  
							  var table_header = 
							  "<tr  width=100%>"+
								"<th   style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ 'Sr No'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'HSN_SAC'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Description of Services'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Packing'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Qty.(MT)'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Rate(RS)'+"</th>"+
								"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Total'+"</th>"+
								"</tr>";
							  var table_result = 
								  "<tr  width=100%>"+
									"<td   style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ' '+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ' '+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ' '+"</td>"+
									"<th  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ' Total Bill Value'+"</th>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ''+"</td>"+
									"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ ''+"</td>"+
									"<th  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ totalAmount+"</th>"+
									"</tr>";
						    	table1 = "<table width=95% align=center >" + table_header + table_rows +table_result+ "</table>";
						    	var table_exp = 
									  "<tr  width=150%>"+
										"<td   style='background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ 'Handling Charges at Ware House from :' +fromDate+ 'to' +toDate+"</td>"+

										"</tr>";
							    	var tableexp = "<table width=95% align=center >" +table_exp+ "</table>";   
							    	var tableload_exp = 
										  "<tr  width=150%>"+
											"<td   style='background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ 'Fixed Expenses from :' +fromDate+ 'to'+toDate +"</td>"+

											"</tr>";
							    	var tableloadexp = "<table width=95% align=center >" +tableload_exp+ "</table>";   
						    	
							    	var table_date="<tr width=100% >"+
				              	   "<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+"invoiceno" + "</th>"+
				    				"</tr>"+
				    	                               
				    	                            	   "<tr width=100% >"+
				    	                            	   "<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "INVOICEDATE: "+today + "</th>"+
				    				"</tr>"; tableheading = "<table width=95% align=center >" + table_date+ "</table>";
				    				var table_fixeddate="<tr width=100% >"+
				               	   "<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+"invoiceno" + "</th>"+
				     				"</tr>"+
				     	                               
				     	                            	   "<tr width=100% >"+
				     	                            	   "<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "INVOICEDATE: "+today + "</th>"+
				     				"</tr>"; tablefixedheading = "<table width=95% align=center >" + table_fixeddate+ "</table>";
				 		    	
				    				
							    	var table_a=
						    		  "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ 'SGST@9%'+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+SGST +"</th>"+
										"</tr>"+ "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ 'CGST@9%'+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ CGST+"</th>"+
										"</tr>" + "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ 'Total Tax'+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ totalTax +"</th>"+
										"</tr>"+ "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ 'Total Invoice Value(Rounded Off) Rs.'+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ grandTotal+"</th>"+
										"</tr>"; 
							    	
							    	table2 = "<table width=95% align=center >" + table_a+ "</table>";  
								   	 	
				     		           	 
						    	
				     		  	var  table_a =
				   			  "<tr  width=150%>"+
				   				 "<td rowspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '1'+"</td>"+
				   					"<td rowspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '996713'+"</td>"+
				   					"<td rowspan='2'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ 'Fixed Expenses from : 01.02.2018 to 28.02.2018'+"</td>"+
				   					+"<tr  width=150%>"+
				   					"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '  '+"</td>"+
				   					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '0.000'+"</td>"+
				   					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '0.000'+"</td>"+
				   					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ fixedExpenses5+"</td>"+
				   					"</tr>"+"<tr  width=150%>"+
				   					"<th   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ 'Total'+"</th>"+
				   					"<th  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '0.00'+"</th>"+
				   					"<th  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ '0.00'+"</th>"+
				   					"<th  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ fixedExpenses5+"</th>"+
				   					"</tr>"+"</tr>";
				     		   var table_result = 
				 				  "<tr  width=150%>"+
				 					"<td   style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ' '+"</td>"+
				 					"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ' '+"</td>"+
				 					"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ' '+"</td>"+
				 					"<th  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ' Total Bill Value'+"</th>"+
				 					"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
				 					"<td  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ ''+"</td>"+
				 					"<th  style='border: 1px solid black;border-collapse:collapse;background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ fixedExpenses5+"</th>"+
				 					"</tr>";
				     		  var table_header = 
				    			  "<tr  width=100%>"+
				    				"<th   style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC;text-align:center;line-height: 150%;'>"+ 'Sr No'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'HSN_SAC'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Description of Services'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Packing'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Qty.(MT)'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Rate(RS)'+"</th>"+
				    				"<th  style='border: 1px solid black;border-collapse:collapse; background-color:#CCCCCC; text-align:center;line-height: 150%;'>"+ 'Total'+"</th>"+
				    				"</tr>";
				     			 var table_b=
						    		  "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ 'SGST@9% '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ''+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ expensesSGST+"</th>"+
										"</tr>"+ "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' CGST@9%'+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ''+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ expensesCGST+"</th>"+
										"</tr>" + "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' Total Tax'+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ''+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ expensesTax+"</th>"+
										"</tr>"+ "<tr  width=150%>"+
										"<th   style='text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ 'Total Invoice Value(Rounded Off) Rs. '+"</th>"+
										"<th  style='  text-align:center;line-height: 100%;'>"+ ''+"</th>"+
										"<th  style=' text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='  text-align:center;line-height: 200%;'>"+ ' '+"</th>"+
										"<th  style='border: 1px solid black;border-collapse:collapse;  text-align:center;line-height: 200%;'>"+ (+fixedExpenses5 + expensesTax)+"</th>"+
										"</tr>";
				     		  	var tablefixed = "<table width=95% align=center >"  +table_header + table_a  +table_result+table_b+"</table>";
				     		  	$('#dataTabbharathi6').html(tablefixedheading);
				     		  	$('#dataTabbharathi5').html(tableheading);	
				     		   $('#dataTabbharathi4').html(tablefixed);	
						    	 $('#dataTabbharathi').html(table1);
							    $('#dataTabbharathi1').html(table2);
							    $('#dataTabbharathi2').html(tableexp);
							    $('#dataTabbharathi3').html(tableloadexp);
							    $('#bharathi').css('display','block');
							    $('#bharathi1').css('display','block');
							    $('#export_bharathi').css('display','block');
							    $('#export_bharathi1').css('display','block');
							    $('#billInfo').css('display','block');	
			                     
			}	
			
			if(company == 'chettinad'){
				var table = "";
				var table_rows = "";
				var factory= "";
				var tables = "";
				
				                                        table="";
									table_rows = "";
									factory= "";
									tables = "";
				                          var factory= "";
							  var tables = "";
							  
							  var tPpc = (+loadingPpc + +crossingPpc + +directPpc);
								var tOpc = (+loadingOpc + +crossingOpc+ +directOpc);
							  
							  var tOpcAmount = (+tOpc * hamali7);
							   var tPpcAmount = (+tPpc * hamali7);
							   var tQuantity = (+tPpc + +tOpc);
							   var tAmount =(+tOpcAmount + +tPpcAmount);
							   var CGST = ((+tAmount*9)/100);
							   var SGST = ((+tAmount*9)/100);
							   var tTax = (+CGST + +SGST);
							   var grandTotal = (+tAmount + +tTax);
							   var ReverseCharge = 0;

								
							var tableheading =	"<tr  width=100% >"+
				 				"<th colspan='2' width='34.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "GSTIN : 36AFHPY3931Q2ZJ"+ "</th>"+
				 				"<th colspan='1' width='7.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 			        "<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " PAN" + "</th>"+ 
				 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "AFHPY3931Q " + "</th>"+ 	
				 				"</tr>"+     
					                              "<tr  width=100% >"+
								"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
								"<td colspan='1' width='7.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "TAX INVOICE " + "</td>"+
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 	
								"</tr>"+ 
				                             "<tr  width=100%>"+
							"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "TO"+ "</td>"+
							"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
							"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
							"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
								"</tr>"+ 
				                "<tr  width=100%>"+
				    			"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "M/s Chettinad Cement Corporation Private Ltd.,"+ "</td>"+
				    			"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				    		        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " Invoice Number" + "</td>"+
				    			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
				    			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " 99"  + "</td>"+ 
				    				"</tr>"+ 

				                    "<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "# 6-3-533,Queen Square , 4th Floor"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				       "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "DATE:" + "</td>"+
					"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "28-02-2018"  + "</td>"+ 
						"</tr>"+ 

				        "<tr  width=100%>"+
				"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "Erramanzil Hyderabad-089"+ "</td>"+
				"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Place of Supply : " + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Medak"  + "</td>"+ 
				"</tr>"+ 

				"<tr  width=100%>"+
				"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "Telangana"+ "</td>"+
				"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " Depot Name:" + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Medak Depot"  + "</td>"+ 
				"</tr>"+ 
				"<tr  width=100%>"+
				"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "State Code : 36"+ "</td>"+
				"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				    "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"</tr>"+ 

				    "<tr  width=100%>"+
				"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "GSTIN : 36AAACC3130A1ZK"+ "</td>"+
				"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
				"</tr>";
				            	
					            	table_rows = table_rows +  
					                "<tr  width=100% >"+
					 				"<th colspan='2' width='34.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "PARTICULARS"+ "</th>"+
					 				"<th colspan='1' width='7.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "HSN/SAC" + "</th>"+
					 			        "<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " QUANTITY" + "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " RATE P/MT" + "</th>"+ 
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "AMOUNT " + "</th>"+ 	
					 				"</tr>"+     
				 	                              "<tr  width=100% >"+
				 				"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "Clearing & Forwarding"+ "</td>"+
				 				"<td colspan='1' width='7.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "996713" + "</td>"+
				 			        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				 				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
				 				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 	
				 				"</tr>"+ 
					                             "<tr  width=100%>"+
								"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "BY ROAD-OPC 53"+ "</td>"+
								"<td colspan='1' width='7.7%'  style='text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tOpc + "</td>"+
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + hamali7 + "</td>"+ 
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tOpcAmount  + "</td>"+ 
									"</tr>"+ 
					                              "<tr  width=100% >"+
								"<td colspan='2' width='34.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "BY ROAD-PPC"+ "</td>"+
								"<td colspan='1' width='7.7%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
							        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tPpc + "</td>"+
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + hamali7 + "</td>"+ 
								"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tPpcAmount  + "</td>"+  
									"</tr>"+
				                             "<tr  width=100% >"+
							"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "TOTAL"+ "</td>"+
							"<td colspan='1' width='7.7%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
						        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tQuantity + "</td>"+
							"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
							"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tAmount  + "</td>"+  
								"</tr>"+
				                "<tr  width=100% >"+
				    			"<td  colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
				    			"<td colspan='1' width='7.7%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				    		        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				    			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " CGST@9%" + "</td>"+ 
				    			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + CGST  + "</td>"+  
				    				"</tr>"+
				        			"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
				        			"<td colspan='1' width='7.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				        		        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				        			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " SGST@9%" + "</td>"+ 
				        			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + SGST  + "</td>"+  
				        				"</tr>"+
				            			"<td colspan='2' width='34.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
				            			"<td colspan='1' width='7.7%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				            		        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				            			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " IGST@9%" + "</td>"+ 
				            			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+  
				            				"</tr>"+
				                			"<td colspan='2' width='34.5'%' style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
				                			"<td colspan='1' width='7.7%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
				                		        "<td  colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
				                			"<td  colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " Round Off" + "</td>"+ 
				                			"<td  colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+  
				                				"</tr>"; 
					            	//table3 = "<table width=100% align=left >" + table_rows +"1</table>" //
					                var table_2 ="<tr  width=100% >"+
					    			
					    			"<td colspan='3' width='42.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					    			"<td colspan='2' width='25%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Amount of Tax Suffered Reverse Charge"  + "</td>"+ 
					    			"<td colspan='1' width='12.5%'  style='text-align:center; padding-left:3px; padding-right:3px'>" + ReverseCharge  + "</td>"+  
					    				"</tr>"+
					        			
					        			"<td colspan='3' width='42.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					        			"<td colspan='2' width='25%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL GST 18%"  + "</td>"+ 
					        			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tTax  + "</td>"+  
					        				"</tr>"+
					            			
					            			"<td colspan='3' width='42.7%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					            			"<th colspan='2' width='25%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL AMOUNT OF CLAIM RS."  + "</th>"+ 
					            			"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>"+ grandTotal  + "</td>"+  
					            				"</tr>";  
					                table2 = "<table width=100% align=left >"+tableheading +table_rows + table_2 +"</table>"   	
				    var table_4 ="<tr  width=100%>"+
					 "<td rowspan='5' width='18.5%'  colspan='1' style=';text-align:center;line-height:150%;'>"+ 'HSN/SAC'+"</td>"+
						"<td rowspan='5' width='34.5%' colspan='2'  style='text-align:center;line-height: 150%;'>"+ 'Taxable Value'+"</td>"+
						"</tr>"+"<tr  width=100%>"+
						"<td colspan='2' width='25%' style='text-align:center;line-height: 150%;'>"+ 'Central Tax'+"</td>"+
						"<td colspan='2' width='25%' style='text-align:center;line-height: 150%;'>"+ 'State Tax'+"</td>"+
						"</tr>"+"<tr  width=100%>"+
						"<td colspan='1'  width='12.5%' style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
						"<td colspan='1'  width='12.5%' style='text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
						"<td colspan='1'  width='12.5%' style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
						"<td  colspan='1'  width='12.5%' style='text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
						"</tr>";
					                table4 = "<table width=100% align=left >" + table_4 +"</table>"  
					           var table_5=     "<tr  width=100% >"+
					 				"<td  colspan='1' width='18.5%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "996713"+ "</td>"+
					 				"<td colspan='2' width='34.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + tAmount + "</td>"+
					 			        "<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " 9%" + "</td>"+
					 				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" +   CGST + "</td>"+ 
					 				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "9% " + "</td>"+ 	
					 				"<td colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</td>"+ 
					 				"</tr>" ;
					               // table5 = "<table width=100% align=left >" + table_5  +"</table>"  //
					                var table_6=     "<tr  width=100% >"+
					 				"<th colspan='3' width='50%' style='text-align:center;padding-left:3px; padding-right:3px'>" + "Total"+ "</th>"+
					 			        "<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + CGST + "</th>"+ 
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 	
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</th>"+ 
					 				"</tr>" ;
					                table6 = "<table width=100% align=left >" + table_5 + table_6  + "</table>"  
					                var tableFOOTER =	"<tr  width=100% >"+
					 				"<th colspan='4' width='50%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "Tax is payable on reverse charge (Yes/No)"+ "</th>"+
					 				"<th colspan='3' width='50%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 	
					 				"</tr>"+"<tr  width=100%>"+
					 				"<th colspan='4' width='50%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "We certify that the claim is as per approved" + "<br>" +"rates. The details given above are correct and" + "<br>" 
					 				+"the stock is tallied as on date. We are enclosing"+"<br>"  +"rake report/stock statement along with this bill."+ "</th>"+
					 				"<th colspan='3' width='50%' style='text-align:center; padding-left:3px; padding-right:3px'>" +  "For NIKHITHA LOGISTICS"+"<br>"+"<br>" +"<br>" +"<br>" + "Authorised Signatory" + "</th>"+ 	
					 				"</tr>";
					                var tabledec =	"<tr  width=100% >"+
					 				"<th colspan='1' width='12.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "Paven Aalla"+ "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " T.Ramesh Babu" + "</th>"+
					 			        "<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "L Ravindra Rao  " + "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " Hitendra R.Jariwala" + "</th>"+ 
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					 				"<th colspan='2' width='25%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					 				"</tr>"+"<tr  width=100% >"+
					 				"<th colspan='1' width='12.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "Sr MKTING"+"<br>"+"Executive"+ "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Dy. Mgr-Sales"+"<br>"+"Accounts"+ "</th>"+
					 			        "<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "GM-Marketing " + "</th>"+
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " Sr.Vice President" + "</th>"+ 
					 				"<th colspan='1' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "Verified "+"<br>"+"By HO" + "</th>"+ 	
					 				"<th colspan='2' width='25%' style='text-align:center; padding-left:3px; padding-right:3px'>" + "  Remarks" + "</th>"+
					 				"</tr>";
					             var  tableamount= "<tr  width=100% >"+
					 				"<th colspan='1' width='12.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "BILL AMOUNT"+ "</th>"+
					 				"<th colspan='7' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					 				"</tr>"+"<tr  width=100% >"+
					 				"<th colspan='1' width='12.5%'  style='text-align:center;padding-left:3px; padding-right:3px'>" + "PASSED AMOUNT"+ "</th>"+
					 				"<th colspan='7' width='12.5%' style='text-align:center; padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					 				"</tr>";
					                tablelast = "<table width=100% align=left >" + tableFOOTER + tabledec + tableamount+"</table>"
					                $('#dataTabc6').html(table6);
					                $('#dataTabclast').html(tablelast);  
					                $('#dataTabc4').html(table4);    	    
					               // $('#dataTabc3').html(table3); //   
					                $('#dataTabc2').html(table2);
				                            $('#chettinad').css('display','block');
				                            $('#export_chettinad').css('display','block');
				                            $('#billInfo').css('display','block');
				
			}
			
			if(company == 'jsw'){
				
				var table = "";
				var table_rows = "";
				var factory= "";
				var tables = "";
				
				                                        table="";
									table_rows = "";
									factory= "";
									tables = "";
				                          var factory= "";
							  var tables = "";
							  
							  
							  
							  var tUnloadingPsc  =(+totalUnloadingPsc + +totalDirectPsc + +totalCrossingPsc);
							  var tUnloadingCon = (+totalUnloadingCon+ +totalDirectCon + +totalCrossingCon);
							  var tLoadingPsc = (+totalLoadingPsc + +totalDirectPsc + +totalCrossingPsc);
							  var tLoadingCon = (+totalLoadingCon+ +totalDirectCon + +totalCrossingCon);
							  var unloadingChargesPsc =((+tUnloadingPsc * 60)/100);
							  var unloadingPscAmount = (+unloadingChargesPsc * hamaliPsc);
							  var unloadingChargesCon = ((+tUnloadingCon * 60)/100);
							  var unloadingConAmount = (+unloadingChargesCon * hamaliCon);
							  var loadingChargesPsc =((+tLoadingPsc * 60)/100);
							  var loadingPscAmount = (+loadingChargesPsc * hamaliPsc);
							  var loadingChargesCon = ((+tLoadingCon * 60)/100);
							  var loadingConAmount = (+loadingChargesCon * hamaliCon);
							  var tBags = (+tUnloadingPsc + +tUnloadingCon);
							  var tService = (+tBags * serviceCharge1);
							  var diversionPsc =((+tUnloadingPsc * 40)/100);
							  var diversionCon = ((+tUnloadingCon * 40)/100);
							  var tAmount = (+unloadingPscAmount + +unloadingConAmount + +loadingPscAmount + +loadingConAmount + +tService + +fixedExpenses1  );
							  
							  var CGST = ((+tAmount*9)/100).toFixed(2);
							  var SGST = ((+tAmount*9)/100).toFixed(2);
							  var grandTotal = (+CGST + +SGST + +tAmount); 
							  
							  
							  
					            	
					            	table_rows = table_rows +  
					                "<tr  width=100% >"+
					 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "Sl  No."+ "</th>"+
					 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "Particular" + "</th>"+
					 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " HSN/SAC" + "</th>"+
					 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " GST Rate " + "</th>"+ 
					 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "Quantity " + "</th>"+ 	
					 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " Rate" + "</th>"+
					 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " per " + "</th>"+ 
					 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "Amount " + "</th>"+
					 				"</tr>"+     
				 	                              "<tr  width=100% >"+
				 	                             "<th  width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
				 	        	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "SERVICE PROVIDER FOR STORAGE<br>AND WAREHOUSING" + "</th>"+
				 	        	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 	        	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "   " + "</th>"+ 
				 	        	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				 	        	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 	        	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	        	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	        	 				"</tr>"+  
				 	       	                "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "1"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "UNLOADING  CHARGES(PSC)" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + unloadingChargesPsc + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + hamaliPsc + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  MT" + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + unloadingPscAmount + "</th>"+
				 	   	 				"</tr>"+        "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "UNLOADING  CHARGES(CON)" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + unloadingChargesCon + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + hamaliCon + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + unloadingConAmount + "</th>"+
				 	   	 				"</tr>"+ 
				 	   	 		   "<tr  width=100% >"+
				   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</th>"+
				   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "RELOADING CHARGES(PSC)" + "</th>"+
				   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + loadingChargesPsc + "</th>"+ 	
				   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + hamaliPsc + "</th>"+
				   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + loadingPscAmount + "</th>"+
				   	 				"</tr>"+      "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "4"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "RELOADING  CHARGES(CON)" + "</th>"+
				 	   	 			    "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + loadingChargesCon + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + hamaliCon + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + loadingConAmount + "</th>"+
				 	   	 				"</tr>"+   
				 	   	 		   "<tr  width=100% >"+
				   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "5"+ "</th>"+
				   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "SERVICE CHARGES" + "</th>"+
				   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tBags + "</th>"+ 	
				   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge1 + "</th>"+
				   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tService + "</th>"+
				   	 				"</tr>"+             "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "6"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "DIVERSION (PSC)" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + diversionPsc + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 	   	 				"</tr>"+ 
				 	   	 		   "<tr  width=100% >"+
				   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "7"+ "</th>"+
				   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "DIVERSION (CON)"+  "</th>"+
				   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + diversionCon + "</th>"+ 	
				   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				   	 				"</tr>"+       "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "8"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "CFA EXPENSES" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + fixedExpenses1 + "</th>"+
				 	   	 				"</tr>"+     "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + "9"+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + tAmount + "</th>"+
				 	   	 				"</tr>"  + "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "CGST@9%" + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + CGST + "</th>"+
				 	   	 				"</tr>"+ 
				 	   	 		   "<tr  width=100% >"+
				   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
				   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "SGST@9%"+  "</th>"+
				   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</th>"+
				   	 				"</tr>"+       "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + "Rounding Off." + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"</tr>"+     "<tr  width=100% >"+
				 	   	 				"<th width=5% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
				 	   	 				"<th width=40% style='text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
				 	   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + " Total" + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "   " + "</th>"+ 	
				 	   	 		        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
				 	   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
				 	   	 				"<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + grandTotal + "</th>"+
				 	   	 				"</tr>"; 	                             
					                table6 = "<table width=100% align=left >" + table_rows +"</table>"  
					            	var table_amnt="<tr  width=100% >"+
					   	 				"<th width=85% style='text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					   	 				"<th width=5% style='text-align:center; padding-left:3px; padding-right:3px'>" + ".<br>.<br>. " + "</th>"+
					   	 			        "<th width=10% style='text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					   	 				"</tr>"; 
					                table7 = "<table width=100% align=left >" + table_amnt +"</table>"
					                var table_end= "<tr  width=100%>"+
					           	 "<td rowspan='5'width=15% style=';text-align:center;line-height:150%;'>"+ 'HSN/SAC'+"</td>"+
					           		"<td rowspan='5'width=10% style='text-align:center;line-height: 150%;'>"+ 'Taxable Value'+"</td>"+
					           		"</tr>"+"<tr  width=100%>"+
					           		"<td colspan='2' width=26% style='text-align:center;line-height: 150%;'>"+ 'Central Tax'+"</td>"+
					           		"<td colspan='2' width=26% style='text-align:center;line-height: 150%;'>"+ 'State Tax'+"</td>"+
					           		"<td colspan='2' width=26% style='text-align:center;line-height: 150%;'>"+ 'Integrated Tax'+"</td>"+
					           		"</tr>"+"<tr  width=100%>"+
					           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					           		"<td width=13% style='text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					           		"<td  width=13%  style='text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					           		"<td  width=13%  style='text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					           		"</tr>";
					                table8 = "<table width=100% align=left >" + table_end +"</table>"
					                var tabledec= "<tr  width=100%>"+
					           		"<td width=15%  style='text-align:center;line-height: 150%;'>"+ '996729'+"</td>"+
					           		"<td  width=10%  style='text-align:center;line-height: 150%;'>"+ tAmount+"</td>"+
						           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ '9%'+"</td>"+
						           		"<td width=13% style='text-align:center;line-height: 150%;'>"+ CGST+"</td>"+
						           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ '9%'+"</td>"+
						           		"<td  width=13%  style='text-align:center;line-height: 150%;'>"+ SGST+"</td>"+
						           		"<td width=13%  style='text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
						           		"<td  width=13%  style='text-align:center;line-height: 150%;'>"+ grandTotal+"</td>"+
						           		"</tr>";
					                tabledec = "<table width=100% align=left >" + tabledec +"</table>"
					                $('#dataTabjsw6').html(table6);
					                $('#dataTabcdec').html(tabledec);
					                $('#dataTabjsw8').html(table8);
					                $('#dataTabjsw7').html(table7);
					                billInfo
				                            $('#jsw').css('display','block');
				                            $('#export_jsw').css('display','block');
				                            $('#billInfo').css('display','block');
				                            
					         
			}

			}


		},
		error: function(error) {
			console.log(error);
		}
		
		


	});


}

function addBill(){
	
	var loading = (+loadingPpc + +loadingOpc);
	var unloading = (+unloadingPpc + +unloadingOpc);
	var crossing = (+crossingPpc + +crossingOpc);

	if(loading ==''){ 
		loading= 0;
	}
	if(listOfHamali==''){ 
		listOfHamali=0;
	}
	if(unloading ==''){ 
		unloading=0;
	}
	
	if(crossing ==''){ 
		crossing=0;
	}
	
	if(listOfService ==''){ 
		listOfService=0;
	}
	
	if(totalOpcTrade ==''){ 
		totalOpcTrade=0;
	}
	if(listOfHamaliTrade==''){ 
		listOfHamaliTrade=0;
	}
	
	if(listOfFixed ==''){ 
		listOfFixed=0;
	}
	if(totalPsc==''){ 
		totalPsc=0;
	}
	if(listOfHamaliPsc ==''){ 
		listOfHamaliPsc=0;
	}
	if(totalCon==''){ 
		totalCon=0;
	}
	if(listOfHamaliCon ==''){ 
		listOfHamaliCon=0;
	}

	
	
	var requestData = new Object();
	requestData.association_name=listOfAssociation;
//	alert(listOfAssociation);
	requestData.godown = godown;
//	alert(godown);
	requestData.bill_date = today;
//	alert(today);
	requestData.loading_quantity = loading;
// alert(+loadingPpc + +loadingOpc);
	requestData.loading_rate =  listOfHamali;
//	alert(listOfHamali);
	requestData.unloading_quantity =unloading;
//	alert(+unloadingPpc + +unloadingOpc);
	requestData.unloading_rate = listOfHamali;
	requestData.crossing_quantity = crossing;
//	alert(+crossingPpc + +crossingOpc);
	requestData.crossing_rate = listOfHamali;
	requestData.service_charges = unloading;
//	alert(+unloadingPpc + +unloadingOpc);
	requestData.service_rate = listOfService;
//	alert(listOfService);
	requestData.ppc_trade = totalOpcTrade;
//	alert(totalOpcTrade);
	requestData.ppc_rate = listOfHamaliTrade;
//	alert(listOfHamaliTrade);
	requestData.opc_trade = totalOpcTrade;
	requestData.opc_rate = listOfHamaliTrade;
	requestData.fixed_expenses = listOfFixed;
//	alert(listOfFixed);
	requestData.psc = totalPsc;
//	alert(totalPsc);
	requestData.hamali_psc = listOfHamaliPsc;
//	alert(listOfHamaliPsc);
	requestData.con = totalCon;
//	alert(totalCon);
	requestData.hamali_con = listOfHamaliCon;
//	alert(listOfHamaliCon);
	
	
	
	
	$.ajax({
		url: '/transport/godown/bill/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {

		},
		error: function(error) {
			console.log(error);
		}
});
	
}
//});
