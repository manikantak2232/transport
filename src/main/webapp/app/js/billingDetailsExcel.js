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
var PpcTrade ;
var OpcTrade;

/*var unloadingPpc="";
var loadingPpc="";
var crossingPpc="";
var directPpc="";*/
var unloadingPpc= 0;
var crossingInwardPpc=0;
var directInwardPpc=0;
var prePpc = 0;
var loadingPpc=0;
var crossingOutwardPpc=0;
var directOutwardPpc=0;
var pendingPpc = 0;
var advancePpc = 0;

/*var	unloadingOpc="";
var loadingOpc="";
var crossingOpc="";
var directOpc="";*/

var	unloadingOpc=0;
var crossingInwardOpc=0;
var directInwardOpc=0;
var preOpc =0;
var loadingOpc=0;
var crossingOutwardOpc=0;
var directOutwardOpc=0;
var pendingOpc =0;
var advanceOpc = 0;

var totalPpc="";
var totalOpc="";
var totalCrossPpc = 0;
var totalCrossOpc =0;
var totalCrossPsc = 0;
var totalCrossCon = 0;
var totalCrossPpcTrade = 0;
var totalCrossOpcTrade = 0;

var totalDirectPpc = 0;
var totalDirectOpc =0;
var totalDirectPsc = 0;
var totalDirectCon = 0;
var totalDirectPpcTrade = 0;
var totalDirectOpcTrade = 0;

/*var totalCrossingOpcTrade="";
var totalLoadingOpcTrade="";
var totalDirectOpcTrade="";*/
var totalUnloadingInwardOpcTrade=0;
var totalCrossingInwardOpcTrade=0;
var totalDirectInwardOpcTrade=0;
var preOpcTrade =0;
var totalLoadingOpcTrade=0;
var totalCrossingOutwardOpcTrade=0;
var totalDirectOutwardOpcTrade=0;
var pendingOpcTrade = 0;
var advanceOpcTrade =0;
/*
var totalCrossingPpcTrade="";
var totalLoadingPpcTrade="";
var totalDirectPpcTrade="";*/
var totalCrossingInwardPpcTrade=0;
var totalDirectInwardPpcTrade=0;
var prePpcTrade = 0;
var totalLoadingPpcTrade=0;
var totalCrossingOutwardPpcTrade=0;
var totalDirectOutwardPpcTrade=0;
var pendingPpcTrade =0;
var advancePpcTrade = 0;

/*var  totalDirectPsc ="";
var totalLoadingPsc="";
var totalUnloadingPsc="";
var totalCrossingPsc="";*/

var totalUnloadingPsc=0;
var totalCrossingInwardPsc=0;
var totalDirectInwardPsc =0;
var prePsc = 0;
var totalLoadingPsc=0;
var totalCrossingOutwardPsc=0;
var totalDirectOutwardPsc =0;
var pendingPsc = 0;
var advancePsc =0;

/*var  totalDirectCon ="";
var totalLoadingCon="";
var totalUnloadingCon="";
var totalCrossingCon="";*/

var totalUnloadingCon=0;
var totalCrossingInwardCon=0;
var totalDirectInwardCon = 0;
var preCon=0;
var totalLoadingCon=0;
var totalCrossingOutwardCon=0;
var totalDirectOutwardCon = 0;
var pendingCon = 0;
var advanceCon = 0;

var toUnloadingPsc ;
var  toLoadingPsc;

var toUnloadingCon;
var  toLoadingCon;
var toPpc;
var toPOpc;

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
var invoiceNumber;
var invoiceNumberBharathi  ;
var invoiceNumberDeccan ;
var finalInvoice ;
var grand_totalFinal;

function list(){
	invoiceNumberBharathi=''  ;
	invoiceNumberDeccan='' ;
	finalInvoice='' ;
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
	today =   now.getFullYear() + '/' + month + '/' + day;


	association =  $('#associationName').val();

//	$(".overlay").show();
	var requestData = new Object();

	requestData.lowerDate= $('#lowerDate').val();
//	alert($('#lowerDate').val());
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
			$(".overlay").hide();

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
				$('#block8').css('display','none');

				$('#HamaliTons').val(listOfHamali);
				$('#serviceCharge').val(listOfService);

			}

			if(association == 'deccan'){
				$('#block1').css('display','block');
				$('#block8').css('display','block');
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
				$('#block8').css('display','none');
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
				$('#block8').css('display','none');
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
				$('#block8').css('display','none');
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
				$('#block8').css('display','none');
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
				$('#block8').css('display','none');
				$('#HamaliTons7').val(listOfHamali);
			}

		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}

	});





}

var inwardPpc;
var outwardPpc;
var crossingPpc;
var inwardOpc53;
var outwardOpc53;
var crossingOpc53;
var inwardOpc43;
var outwardOpc43;
var crossingOpc43;
var inwardPsc;
var outwardPsc;
var inwardCon;
var outwardCon;
var inwardPpcPpl;
var outwardPpcPpl;
var inwardOpcPpl;
var outwardOpcPpl;
var inwardPpcUltrafast;
var outwardPpcUltrafast;

function getBill() {
	
	
	 inwardPpc = 0 ;
	 outwardPpc = 0;
	 inwardOpc53 = 0;
	 outwardOpc53 = 0;
	 inwardOpc43 = 0;
	 outwardOpc43 = 0;
	 inwardPsc = 0;
	 outwardPsc = 0;
	 inwardCon = 0;
	 outwardCon = 0;
	 inwardPpcPpl = 0;
	 outwardPpcPpl = 0;
	 inwardOpcPpl = 0;
	 outwardOpcPpl = 0;
	 inwardPpcUltrafast = 0;
	 outwardPpcUltrafast = 0;
	/*	
	invoiceNumberBharathi=""  ;
	 invoiceNumberDeccan="" ;
	 finalInvoice="" ;*/
	 unloadingPpc= 0;
	 crossingInwardPpc=0;
	 directInwardPpc=0;
	 prePpc = 0;
	 loadingPpc=0;
	 crossingOutwardPpc=0;
	 directOutwardPpc=0;
	 pendingPpc = 0;
	 advancePpc = 0;

	/*var	unloadingOpc="";
	var loadingOpc="";
	var crossingOpc="";
	var directOpc="";*/

		unloadingOpc=0;
	 crossingInwardOpc=0;
	 directInwardOpc=0;
	 preOpc =0;
	 loadingOpc=0;
	 crossingOutwardOpc=0;
	 directOutwardOpc=0;
	 pendingOpc =0;
	 advanceOpc = 0;

	 totalPpc="";
	 totalOpc="";
	 totalCrossPpc = 0;
	 totalCrossOpc =0;
	 totalCrossPsc = 0;
	 totalCrossCon = 0;
	 totalCrossPpcTrade = 0;
	 totalCrossOpcTrade = 0;
	 
	  totalDirectPpc = 0;
	  totalDirectOpc =0;
	  totalDirectPsc = 0;
	  totalDirectCon = 0;
	  totalDirectPpcTrade = 0;
	  totalDirectOpcTrade = 0;

	/*var totalCrossingOpcTrade="";
	var totalLoadingOpcTrade="";
	var totalDirectOpcTrade="";*/
     
	 totalUnloadingInwardOpcTrade=0;
	 totalCrossingInwardOpcTrade=0;
	 totalDirectInwardOpcTrade=0;
	 preOpcTrade =0;
	 totalLoadingOpcTrade=0;
	 totalCrossingOutwardOpcTrade=0;
	 totalDirectOutwardOpcTrade=0;
	 pendingOpcTrade = 0;
	 advanceOpcTrade =0;
	/*
	var totalCrossingPpcTrade="";
	var totalLoadingPpcTrade="";
	var totalDirectPpcTrade="";*/
	 totalCrossingInwardPpcTrade=0;
	 totalDirectInwardPpcTrade=0;
	 prePpcTrade = 0;
	 totalLoadingPpcTrade=0;
	 totalCrossingOutwardPpcTrade=0;
	 totalDirectOutwardPpcTrade=0;
	 pendingPpcTrade =0;
	 advancePpcTrade = 0;

	/*var  totalDirectPsc ="";
	var totalLoadingPsc="";
	var totalUnloadingPsc="";
	var totalCrossingPsc="";*/

	 totalUnloadingPsc=0;
	 totalCrossingInwardPsc=0;
	 totalDirectInwardPsc =0;
	 prePsc = 0;
	 totalLoadingPsc=0;
	 totalCrossingOutwardPsc=0;
	 totalDirectOutwardPsc =0;
	 pendingPsc = 0;
	 advancePsc =0;

	/*var  totalDirectCon ="";
	var totalLoadingCon="";
	var totalUnloadingCon="";
	var totalCrossingCon="";*/

	 totalUnloadingCon=0;
	 totalCrossingInwardCon=0;
	 totalDirectInwardCon = 0;
	 preCon=0;
	 totalLoadingCon=0;
	 totalCrossingOutwardCon=0;
	 totalDirectOutwardCon = 0;
	 pendingCon = 0;
	 advanceCon = 0;

	loadingPpc =0;
	loadingOpc = 0;
	totalCrossPpc = 0;
	totalCrossOpc =0;
	totalCrossPsc = 0;
	totalCrossCon = 0;
	totalCrossPpcTrade = 0;
	totalCrossOpcTrade = 0;
	grand_totalFinal = '';

	$('#tabl1').css('display','none');
	$('#ramco').css('display','none');
	$('#anjani').css('display','none');
	$('#deccan').css('display','none');
	$('#deccan1').css('display','none');
	$('#bharathi').css('display','none');
	$('#bharathi1').css('display','none');
	$('#jsw').css('display','none');
	$('#chettinad').css('display','none');
	$('#billInfo').css('display','none');
	$('#export_zuari').css('display','none');
	$('#export_ramco').css('display','none');
	$('#export_jsw').css('display','none');
	$('#export_deccan').css('display','none');
	$('#export_deccan1').css('display','none');
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
//	godown = $('#unloadLocationName').val();
	var hamaliTrade = $('#hamaliTrade').val();
	var hamaliPsc = $('#hamaliPsc').val();
	var hamaliCon = $('#hamaliCon').val();
	invoiceNumber = $('#invoiceNumber').val();
	
	var now = new Date(fromDate);
	var month = (now.getMonth() + 1);               
	var day = now.getDate();
	if(month < 10) 
		month = "0" + month;
	if(day < 10) 
		day = "0" + day;
	 var fromDate1 =  day + '/' + month + '/' + now.getFullYear();
	 
	 var now = new Date(toDate);
		var month = (now.getMonth() + 1);               
		var day = now.getDate();
		if(month < 10) 
			month = "0" + month;
		if(day < 10) 
			day = "0" + day;
		 var toDate1 =  day + '/' + month + '/' + now.getFullYear();

	if(invoiceNumber==undefined | invoiceNumber==''){ 
		alert("Please Select Invoice Number..");
		return false;
	}
	if(fromDate==undefined | fromDate==''){ 
		alert("Please Select From Date..");
		return false;
	}
	if(toDate==undefined | toDate==''){ 
		alert("Please Select To Date..");
		return false;
	}

	if(invoiceNumberBharathi==undefined){ 
		invoiceNumberBharathi='';
	}

	if(invoiceNumberDeccan==undefined){ 
		invoiceNumberDeccan='';
	}

	/*unloadLocationName= $('#unloadLocationName').val();


	if(godown==undefined | godown=='Select'){ 
		alert("Please Select Unload Location..");
		return false;
	}*/

	$(".overlay").show();
	var requestData = new Object();

	/*		requestData.lower_date= today;
			requestData.upper_date= today;*/
	requestData.lowerDate= $('#lowerDate').val();
	requestData.upperDate= $('#upperDate').val();
	requestData.associationName= $('#associationName').val();	
//	requestData.unloadLocationName= $('#unloadLocationName').val();


	$.ajax({
		url: '/transport/godown/inward/get/bill/excel',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			$(".overlay").hide();

			var inwardDetails = response.bills;
			total_q=0;
			if(inwardDetails!=null){
				for(var i=0; i<inwardDetails.length; i++){
					inward = inwardDetails[i];
					
					

					var Qua = inward.totalquantity;
					var action = inward.actionList;
					var cement = inward.typeCement;
				//	var cross = inward.totalCrossing;
				//	var directQuantity = inward.totalDirect;

					total_q = total_q+Qua;

					


					//============ PPC =============================================================================

					if(action == 'Inward' & cement == 'PPC')
					{

						inwardPpc = Qua;
						//	alert(unloadingPpc);
					}

					if(action == 'Outward' & cement == 'PPC')
					{

						outwardPpc = Qua;
						//alert(crossingInwardPpc);
					}
					
					if(action == 'Crossing' & cement == 'PPC')
					{

						crossingPpc = Qua;
						//alert(crossingInwardPpc);
					}

		

					//====================================53 OPC =================================================================					

					if(action == 'Inward' & cement == '53 OPC')
					{

						inwardOpc53 =Qua;
						//	alert(unloadingOpc);
					}

					if(action == 'Outward' & cement == '53 OPC')
					{

						outwardOpc53 = Qua;
						//alert(crossingOpc);
					}
					
					if(action == 'Crossing' & cement == '53 OPC')
					{

						crossingOpc53 = Qua;
						//alert(crossingOpc);
					}
					
					//====================================43 OPC =================================================================					

					if(action == 'Inward' & cement == '43 OPC')
					{

						inwardOpc43 =Qua;
						//	alert(unloadingOpc);
					}

					if(action == 'Outward' & cement == '43 OPC')
					{

						outwardOpc43 = Qua;
						//alert(crossingOpc);
					}
					if(action == 'Crossing' & cement == '43 OPC')
					{

						crossingOpc43 = Qua;
						//alert(crossingOpc);
					}
					
					//=========================================== PSC ============================================================


					if(action == 'Inward' & cement == 'PSC'){

						inwardPsc =   Qua;

					}
					if(action == 'Outward' & cement == 'PSC'){

						outwardPsc =   Qua;

					}

					
					
//========================================================== CON =============================================================					

					if(action == 'Inward' & cement == 'CON'){

						inwardCon =   Qua;

					}

					if(action == 'Outward' & cement == 'CON'){

						outwardCon =   Qua;

					}
					
					
//========================================================== PPC-PPL =============================================================					

										if(action == 'Inward' & cement == 'PPC-PPL'){

											inwardPpcPpl =   Qua;

										}

										if(action == 'Outward' & cement == 'PPC-PPL'){

											outwardPpcPpl =   Qua;

										}
										
//========================================================== OPC-PPL =============================================================					

										if(action == 'Inward' & cement == 'OPC-PPL'){

											inwardOpcPpl =   Qua;

										}

										if(action == 'Outward' & cement == 'OPC-PPL'){

											outwardOpcPpl =   Qua;

										}
										
//========================================================== PPC ULTRAFAST =============================================================					

										if(action == 'Inward' & cement == 'PPC ULTRAFAST'){

											inwardPpcUltrafast =   Qua;

										}

										if(action == 'Outward' & cement == 'PPC ULTRAFAST'){

											outwardPpcUltrafast =   Qua;

										}
										






				}

				if(company == 'zuari'){
					
				
					
					var unloadingZuari =(+inwardPpc + +inwardOpc53 + +inwardOpc43 ).toFixed(2);
					var unloadingZuariTotal = (+unloadingZuari * +hamali).toFixed(2);
					var loadingZuari = (+outwardPpc + +outwardOpc53 + +outwardOpc43).toFixed(2);
					var loadingZuariTotal = (+loadingZuari * +hamali).toFixed(2);
					var ServiceZuariTotal = (+unloadingZuari * +serviceCharge).toFixed(2);
					var totalZuari = (+unloadingZuariTotal + +loadingZuariTotal + +ServiceZuariTotal).toFixed(2);
					 var gcgst=  (((totalZuari)*9)/(100)).toFixed(2); 
					 var gsgst=  (((totalZuari)*9)/(100)).toFixed(2); 
					 
					 grand_totalFinal = (+totalZuari + +gcgst + +gsgst).toFixed(2);

					 var  table_invoice=                        
						 "<tr width=100% >"+
						 "<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "TO : "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "ZUARI CEMENT LTD "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "INVOICE No: " +invoiceNumber + "</td>"+"</tr>"+
						 "<tr width=100% >"+
						 "<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "Dondapadu, "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " " +"</td>"+"</tr>"+
						 "<tr width=100% >"+
						 "<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "Telangana 508246 "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " DATE:"+"</td>"+"</tr>"+
						 "<tr width=100% >"+
						 "<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "36 "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+"</td>"+"</tr>"+
						 "<tr width=100% >"+
						 "<td width=15% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  "GSTN : 36AFHPY3931Q2ZJ "+ "</td>"+
						 "<td width=30% style='text-align:center; padding-left:3px; padding-right:3px'>" +  " "+"</td>"+"</tr>";


					 var tableinvoice= "<table width=100% align=center >" + table_invoice+"</table>";	
					 var  table_mid =  
						 "<br>"+
						 "<tr width=100% >"+
						 "<td colspan='7'style='text-align:left; padding-left:3px; padding-right:3px'>" +  "CFA bill details for the period "+fromDate+ " to " +toDate+" at "+godown+" Godown "+ "</td>"+"</tr>"+"<br>";
					 /*"<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" +  ""+ "</td>"+
						"</tr>";*/
					 var tablemid= "<table width=95% align=center >" + table_mid+"</table>";		

					 header_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'style='border: 1px solid black; border-collapse: collapse;'>" + 'SL.NO' + "</td>"+
						 "<td colspan='2'style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'PARTICULARS' + "</td>"+		            						
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'QUANTITY' + "</td>"+	
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'RATE' + "</td>"+
						 "<td colspan='1'style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'AMOUNT' + "</td>"+

						 "</tr>"
						 ;

					 code_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '19KF' + "</td>"+		            						
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+	


						 "</tr>"
						 ;

					 loading_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 1 + "</td>"+
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'Loading charges cement bag at godown' + "</td>"+		            						
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + unloadingZuari+ "</td>"+	
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + hamali + "</td>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + unloadingZuariTotal + "</td>"+

						 "</tr>"
						 ;
					 Reloading_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'Reloading charges cement bag at godown' + "</td>"+		            						
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + loadingZuari + "</td>"+	
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + hamali + "</td>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + loadingZuariTotal + "</td>"+

						 "</tr>"
						 ;
					 service_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'Service charges' + "</td>"+		            						
						 "<td colspan='2' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + unloadingZuari+ "</td>"+	
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + serviceCharge  + "</td>"+
						 "<td colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + ServiceZuariTotal + "</td>"+

						 "</tr>"
						 ;

					 empty_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+		            						
						 "<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+	


						 "</tr>"
						 ;
					 total_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'TOTAL' + "</td>"+		            						
						 "<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + totalZuari + "</td>"+	


						 "</tr>"
						 ;

					 cgst_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'CGST - 9%' + "</td>"+		            						
						 "<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" +gcgst + "</td>"+	


						 "</tr>"
						 ;

					 sgst_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'SGST - 9%' + "</td>"+		            						
						 "<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" +gcgst  + "</td>"+	


						 "</tr>"
						 ;
					
					

					 Grand_rows = 

						 "<tr class='table'>"+
						 "<td colspan='1' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + '' + "</td>"+
						 "<td colspan='4' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + 'GRAND TOTAL' + "</td>"+		            						
						 "<td colspan='2' class='blank_row' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 100%;'>" + grand_totalFinal + "</td>"+	


						 "</tr>"
						 ;

					 
					 //alert(grand_totalFinal);
					 table= header_rows+code_rows+loading_rows+Reloading_rows+service_rows+empty_rows+empty_rows+empty_rows+empty_rows+empty_rows+empty_rows+total_rows+cgst_rows+sgst_rows+Grand_rows ;
					 $('#dataTab').html(table);
					 $('#dataTabInvoice').html(tableinvoice);
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
					var totalCrossing =(+crossingPpc + +crossingOpc53 + +crossingOpc43).toFixed(2);
					var totalUnloading = (+inwardPpc + +inwardOpc53 + +inwardOpc43).toFixed(2);
					var totalLoading = (+outwardPpc + +outwardOpc53 + +outwardOpc43).toFixed(2);
					var totalCrossingAmount = (+totalCrossing*hamali).toFixed(2);
					var totalUnloadingAmount = (+totalUnloading*hamali).toFixed(2);
					var totalLoadingAmount = (+totalLoading*hamali).toFixed(2);
					var totalServiceChargeAmount = ((+totalCrossing + +totalUnloading)*serviceCharge).toFixed(2);
					var totalramco = (+totalCrossingAmount + +totalUnloadingAmount + +totalLoadingAmount + +totalServiceChargeAmount).toFixed(2);
					var totalCharges = (+totalramco + +fixedExpenses).toFixed(2);
					var CGST = ((+totalCharges*9)/100).toFixed(2);
					var SGST = ((+totalCharges*9)/100).toFixed(2);
					var grandTotal = (+totalCharges + +CGST + +SGST).toFixed(2);
					
					
					table_rows = 
						"<tr width=100% >"+
						"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+invoiceNumber + "</th>"+
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
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingOpc53 + +crossingOpc43  )  + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingPpc) + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalCrossing  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalCrossingAmount + "</td>"+ 
						"</tr>"+  
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "GODOWN UNLOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+inwardOpc53 + +inwardOpc43) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+inwardPpc) + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloading  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalUnloadingAmount + "</td>"+ 
						"</tr>"+  

						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "GODOWN LOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+outwardOpc53 + +outwardOpc43) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+outwardPpc) +"</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoading  + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + totalLoadingAmount + "</td>"+ 
						"</tr>"+ 

						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "4"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "C&F SERVICE CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingOpc53 + +crossingOpc43 + +inwardOpc53 + +inwardOpc43) + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + (+crossingPpc + +inwardPpc) + "</td>"+ 
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
					grand_totalFinal = grandTotal;
					//	alert(grand_totalFinal);
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

				/*	var totalUnloading = (+unloadingPpc +  +directPpc + +unloadingOpc + +directOpc + +crossingOpc + +crossingPpc + totalCrossPpc + totalCrossOpc);
					var totalLoading = (+loadingPpc + +loadingOpc + +directOpc + +directPpc + +crossingOpc + +crossingPpc + totalCrossPpc + totalCrossOpc);
					var totalUnloadingAmount = (+totalUnloading * hamali);
					var totalLoadingAmount = (+totalLoading * hamali);
					var serviceChargesAmount = (+totalUnloading * serviceCharge);
					var totalAnjani = (+totalUnloadingAmount + +totalLoadingAmount + +serviceChargesAmount);
					var total = (+totalAnjani + +fixedExpenses ).toFixed(2);
					var CGST  = ((+total*9)/100).toFixed(2);
					var SGST  = ((+total*9)/100).toFixed(2);
					var grandTotal = (+total + +CGST + +SGST ).toFixed(2);*/

					/*var totalUnloading = (+unloadingPpc +  +directInwardPpc + +unloadingOpc + +directInwardOpc + +crossingInwardOpc + +crossingInwardPpc + +prePpc + +preOpc + +totalCrossPpc + +totalCrossOpc  + +totalDirectPpc + +totalDirectOpc +  +totalUnloadingInwardOpcTrade + +totalDirectInwardOpcTrade + +totalCrossingInwardOpcTrade + +preOpcTrade + +totalDirectOpcTrade + +totalCrossOpcTrade);
					var totalLoading = (+loadingPpc + +loadingOpc + +directOutwardOpc + +directOutwardPpc + +crossingOutwardOpc + +crossingOutwardPpc + +pendingOpc + +pendingPpc + +advanceOpc + +advancePpc + +totalLoadingOpcTrade + + +totalCrossingOutwardOpcTrade +totalDirectOutwardOpcTrade + +pendingOpcTrade + +advanceOpcTrade);
					var totalUnloadingAmount = (+totalUnloading * hamali);
					var totalLoadingAmount = (+totalLoading * hamali);
					var serviceChargesAmount = (+totalUnloading * serviceCharge);
					var totalAnjani = (+totalUnloadingAmount + +totalLoadingAmount + +serviceChargesAmount);
					var total = (+totalAnjani + +fixedExpenses ).toFixed(2);
					var CGST  = ((+total*9)/100).toFixed(2);
					var SGST  = ((+total*9)/100).toFixed(2);
					var grandTotal = (+total + +CGST + +SGST ).toFixed(2);*/
					

					var unloadingAnjani =(+inwardPpc + +inwardOpc53 + +inwardOpc43 ).toFixed(2);
					var unloadingAnjaniTotal = (+unloadingAnjani * +hamali).toFixed(2);
					var loadingAnjani = (+outwardPpc + +outwardOpc53 + +outwardOpc43).toFixed(2);
					var loadingAnjaniTotal = (+loadingAnjani * +hamali).toFixed(2);
					var ServiceAnjaniTotal = (+unloadingAnjani * +serviceCharge).toFixed(2);
					var totalAnjani = (+unloadingAnjaniTotal + +loadingAnjaniTotal + +ServiceAnjaniTotal).toFixed(2);
					var total = (+totalAnjani + +fixedExpenses ).toFixed(2);
					 var gcgst=  (((total)*9)/(100)).toFixed(2); 
					 var gsgst=  (((total)*9)/(100)).toFixed(2); 
					  grand_totalFinal = (+total + +gcgst + +gsgst).toFixed(2);


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
						"<td width=25% style='text-align:left; padding-left:3px; padding-right:3px'>" +  "INVOICE NO : "+ invoiceNumber+ "</td>"+

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
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingAnjani + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingAnjaniTotal + "</td>"+ 	
						"</tr>"+ 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "RELOADING CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + loadingAnjani + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + hamali + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + loadingAnjaniTotal + "</td>"+ 
						"</tr>"+ 
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:left; padding-left:3px; padding-right:3px'>" + "SERVICE CHARGES" + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingAnjani + "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge + "</td>"+ 
						"<td width=5% style='border: 1px solid black;text-align:center; padding-left:3px; padding-right:3px'>" + ServiceAnjaniTotal  + "</td>"+  
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
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + gcgst+ "</td>"+
						"</tr>" +
						"<tr  width=100% style= 'border: 1px solid black'>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + "SGST@9% "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
						"<td width=5% style='border: 1px solid black;text-align:center;padding-left:3px; padding-right:3px'>" + gsgst+ "</td>"+
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

				
					//	alert(grand_totalFinal);
					table3 = "<table width=95% align=center style= 'border: 1px solid black' >" + table_c + "</table>"   	
					$('#dataTaba3').html(table3);       	
					$('#dataTaba2').html(table2);     	
					$('#dataTaba1').html(table1); 	
					$('#anjani').css('display','block');
					$('#export_anjani').css('display','block');
					$('#billInfo').css('display','block');

				}
				if(company == 'deccan'){
					invoiceNumberDeccan = $('#invoiceNumber3').val();

					if(invoiceNumberDeccan==undefined | invoiceNumberDeccan=='Select'){ 
						alert("Please Select Invoice Number..");
						return false;
					}

					//	alert(invoiceNumberDeccan);

					/*var tquantity = (+crossingOpc + +crossingPpc + +loadingPpc + +loadingOpc + +directPpc + +directOpc + totalCrossPpc + totalCrossOpc);
					var totalService = (+tquantity * serviceCharge);
					var CGST = ((+totalService*9)/100).toFixed(2) ;
					var SGST = ((+totalService*9)/100).toFixed(2) ;
					var grandTotalService = (+totalService + +CGST + +SGST).toFixed(2);
					var totalHamali = (+tquantity * hamali) ;
					var HCGST = ((+totalHamali*9)/100).toFixed(2) ;
					var HSGST = ((+totalHamali*9)/100).toFixed(2) ;
					var grandTotalHamali = (+totalHamali + +HCGST + +HSGST).toFixed(2) ;*/
                   
					
					var tquantity = (+outwardPpc + +outwardOpc53 + +outwardOpc43).toFixed(2)
					var totalService = (+tquantity * serviceCharge).toFixed(2) ;
					var CGST = ((+totalService*9)/100).toFixed(2) ;
					var SGST = ((+totalService*9)/100).toFixed(2) ;
					var grandTotalService = (+totalService + +CGST + +SGST).toFixed(2);
					var totalHamali = (+tquantity * hamali).toFixed(2)  ;
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
					"<td width=50%  style='text-align:left; padding-left:3px; padding-right:3px'>" +  "BILL NO :+"+ invoiceNumber + "</td>"+"</tr>"+
					"<tr width=100% >"+ "<td width=100% style='text-align:left; padding-left:3px; padding-right:3px'>" +"DATE:"+ today+ "</td>"+
					"</tr>";
					var tableheading = "<table width=95% align=center >" + table_rows+"</table>";
					var tableload_rows =                       
						"<tr width=100% >"+
						"<td width=50%  style='text-align:left; padding-left:3px; padding-right:3px'>" +  "BILL NO :  "+invoiceNumberDeccan+ "</td>"+"</tr>"+
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
						"<td width=5% style='text-align:left; padding-left:3px; padding-right:3px'>" + (tquantity) + "</td>"+
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
					var tableload_exp= "<table width=100% align=center >" + table_exp2+"</table>";

					grand_totalFinal = (+grandTotalService + +grandTotalHamali);
					//	alert(grand_totalFinal);

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

					invoiceNumberBharathi = $('#invoiceNumber2').val();
					if(invoiceNumberBharathi==undefined | invoiceNumberBharathi=='Select'){ 
						alert("Please Select Invoice Number..");
						return false;
					}
					//	 alert(invoiceNumberBharathi);

					/*var table = "";
					var table_rows = "";
					var tPpc = (+loadingPpc + +crossingPpc + +directPpc + totalCrossPpc);
					var tOpc = (+loadingOpc + +crossingOpc+ +directOpc + totalCrossOpc);
					var tPpcTrade = (+totalLoadingPpcTrade + +totalCrossingPpcTrade + +totalDirectPpcTrade + totalCrossPpcTrade);
					var tOpcTrade = (+totalLoadingOpcTrade + +totalCrossingOpcTrade + +totalDirectOpcTrade + totalCrossOpcTrade);
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
					var expensesGrandTotal = (+fixedExpenses5 + expensesTax);
*/

					
					var table = "";
					var table_rows = ""; 
					var tPpc = (+outwardPpcPpl);
					var tOpc = (+outwardOpcPpl );
					var tPpcTrade = (+outwardPpc );
					var tOpcTrade = (+outwardOpc53);
					var tUltrafast = (+outwardPpcUltrafast);
					var tAmountPpc = (+tPpc * hamali5);
					var tAmountOpc = (+tOpc * hamali5);
					var tAmountPpcTrade =(+tPpcTrade * hamaliTrade);
					var tAmountOpcTrade =(+tOpcTrade * hamaliTrade);
					var tAmountUltrafast = (+tUltrafast * hamali5);
					var tQuantity = (+tPpc + +tOpc + +tPpcTrade + +tOpcTrade + +tUltrafast);
					var totalAmount = (+tAmountPpc + tAmountOpc + tAmountPpcTrade + +tAmountOpcTrade + +tAmountUltrafast);
					var SGST = ((+totalAmount*9)/100);
					var CGST = ((+totalAmount*9)/100);
					var totalTax = (+SGST + +CGST);
					var grandTotal = (+totalAmount + +totalTax);
					var expensesSGST = ((fixedExpenses5*9)/100);
					var expensesCGST = ((fixedExpenses5*9)/100);
					var expensesTax = (+expensesSGST + +expensesCGST);
					var expensesGrandTotal = (+fixedExpenses5 + expensesTax);

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
					"<td rowspan='7' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ '1'+"</td>"+
					"<td rowspan='7' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ '996713'+"</td>"+
					"<td rowspan='7'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Handling Charges at Ware House from :'+fromDate1+ ' to '+toDate1 +"</td>"+
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
					"<td   style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ ' ULTRAFAST'+"</td>"+
					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tUltrafast+"</td>"+
					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ hamali5+"</td>"+
					"<td  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ tAmountUltrafast+"</td>"+
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
						"<td   style='background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ 'Handling Charges at Ware House from :' +fromDate1+ ' to ' +toDate1+"</td>"+

						"</tr>";
					var tableexp = "<table width=95% align=center >" +table_exp+ "</table>";   
					var tableload_exp = 
						"<tr  width=150%>"+
						"<td   style='background-color:#CCCCCC;text-align:center;line-height: 200%;'>"+ 'Fixed Expenses from :' +fromDate1+ ' to '+toDate1 +"</td>"+

						"</tr>";
					var tableloadexp = "<table width=95% align=center >" +tableload_exp+ "</table>";   

					var table_date="<tr width=100% >"+
					"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+invoiceNumber + "</th>"+
					"</tr>"+

					"<tr width=100% >"+
					"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + "INVOICEDATE: "+today + "</th>"+
					"</tr>"; tableheading = "<table width=95% align=center >" + table_date+ "</table>";
					var table_fixeddate="<tr width=100% >"+
					"<th width=10% style='text-align:left; padding-left:3px; padding-right:3px'>" + " INVOICE NO: "+invoiceNumberBharathi + "</th>"+
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
						"<td rowspan='2'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;'>"+ 'Fixed Expenses from :'+fromDate1+ ' to '+toDate1 +"</td>"+
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

					grand_totalFinal = (+grandTotal + +expensesGrandTotal).toFixed(2);

					//	alert(grand_totalFinal);

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

					/*var tPpc = (+loadingPpc + +crossingPpc + +directPpc + totalCrossPpc );
					var tOpc = (+loadingOpc + +crossingOpc+ +directOpc + totalCrossOpc);

					var tOpcAmount = (+tOpc * hamali7);
					var tPpcAmount = (+tPpc * hamali7);
					var tQuantity = (+tPpc + +tOpc);
					var tAmount =(+tOpcAmount + +tPpcAmount);
					var CGST = ((+tAmount*9)/100);
					var SGST = ((+tAmount*9)/100);
					var tTax = (+CGST + +SGST);
					var grandTotal = (+tAmount + +tTax);
					var ReverseCharge = 0;*/
					
				//	var tPpc = (+loadingPpc + +crossingOutwardPpc + +directOutwardPpc + +pendingPpc + +advancePpc);
				//	var tOpc = (+loadingOpc + +crossingOutwardOpc+ +directOutwardOpc + +pendingOpc + +advanceOpc);

					var tOpcAmount = (+outwardOpc53 * hamali7);
					var tPpcAmount = (+outwardPpc * hamali7);
					var tQuantity = (+outwardPpc + +outwardOpc53);
					var tAmount =(+tOpcAmount + +tPpcAmount);
					var CGST = ((+tAmount*9)/100);
					var SGST = ((+tAmount*9)/100);
					var tTax = (+CGST + +SGST);
					var grandTotal = (+tAmount + +tTax);
					var ReverseCharge = 0;
					

					var tableheading =	"<tr  width=100% >"+
					"<th colspan='2' width='34.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "GSTIN : 36AFHPY3931Q2ZJ"+ "</th>"+
					"<th colspan='1' width='7.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " PAN" + "</th>"+ 
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "AFHPY3931Q " + "</th>"+ 	
					"</tr>"+     
					"<tr  width=100% >"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
					"<td colspan='1' width='7.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "TAX INVOICE " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 	
					"</tr>"+ 
					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "TO"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"</tr>"+ 
					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "M/s Chettinad Cement Corporation Private Ltd.,"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Invoice Number" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + invoiceNumber  + "</td>"+ 
					"</tr>"+ 

					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "# 6-3-533,Queen Square , 4th Floor"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "DATE:" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "28-02-2018"  + "</td>"+ 
					"</tr>"+ 

					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Erramanzil Hyderabad-089"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Place of Supply : " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Medak"  + "</td>"+ 
					"</tr>"+ 

					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Telangana"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Depot Name:" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Medak Depot"  + "</td>"+ 
					"</tr>"+ 
					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "State Code : 36"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"</tr>"+ 

					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "GSTIN : 36AAACC3130A1ZK"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ""  + "</td>"+ 
					"</tr>";

					table_rows = table_rows +  
					"<tr  width=100% >"+
					"<th colspan='2' width='34.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "PARTICULARS"+ "</th>"+
					"<th colspan='1' width='7.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "HSN/SAC" + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " QUANTITY" + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " RATE P/MT" + "</th>"+ 
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "AMOUNT " + "</th>"+ 	
					"</tr>"+     
					"<tr  width=100% >"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Clearing & Forwarding"+ "</td>"+
					"<td colspan='1' width='7.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "996713" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 	
					"</tr>"+ 
					"<tr  width=100%>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "BY ROAD-OPC 53"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:centert; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + inwardOpc53 + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamali7 + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tOpcAmount  + "</td>"+ 
					"</tr>"+ 
					"<tr  width=100% >"+
					"<td colspan='2' width='34.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "BY ROAD-PPC"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tPpc + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamali7 + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tPpcAmount  + "</td>"+  
					"</tr>"+
					"<tr  width=100% >"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "TOTAL"+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tQuantity + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tAmount  + "</td>"+  
					"</tr>"+
					"<tr  width=100% >"+
					"<td  colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " CGST@9%" + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + CGST  + "</td>"+  
					"</tr>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
					"<td colspan='1' width='7.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " SGST@9%" + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + SGST  + "</td>"+  
					"</tr>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " IGST@9%" + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+  
					"</tr>"+
					"<td colspan='2' width='34.5'%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</td>"+
					"<td colspan='1' width='7.7%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</td>"+
					"<td  colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "" + "</td>"+
					"<td  colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Round Off" + "</td>"+ 
					"<td  colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " "  + "</td>"+  
					"</tr>"; 
					//table3 = "<table width=100% align=left >" + table_rows +"1</table>" //
					var table_2 ="<tr  width=100% >"+

					"<td colspan='3' width='42.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					"<td colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Amount of Tax Suffered Reverse Charge"  + "</td>"+ 
					"<td colspan='1' width='12.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ReverseCharge  + "</td>"+  
					"</tr>"+

					"<td colspan='3' width='42.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					"<td colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL GST 18%"  + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tTax  + "</td>"+  
					"</tr>"+

					"<td colspan='3' width='42.7%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</td>"+ 
					"<th colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL AMOUNT OF CLAIM RS."  + "</th>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>"+ grandTotal  + "</td>"+  
					"</tr>";  
					table2 = "<table width=100% align=left >"+tableheading +table_rows + table_2 +"</table>"   	
					var table_4 ="<tr  width=100%>"+
					"<td rowspan='5' width='18.5%'  colspan='1' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ 'HSN/SAC'+"</td>"+
					"<td rowspan='5' width='34.5%' colspan='2'  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Taxable Value'+"</td>"+
					"</tr>"+"<tr  width=100%>"+
					"<td colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Central Tax'+"</td>"+
					"<td colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'State Tax'+"</td>"+
					"</tr>"+"<tr  width=100%>"+
					"<td colspan='1'  width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td colspan='1'  width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					"<td colspan='1'  width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td  colspan='1'  width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					"</tr>";
					table4 = "<table width=100% align=left >" + table_4 +"</table>"  
					var table_5=     "<tr  width=100% >"+
					"<td  colspan='1' width='18.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "996713"+ "</td>"+
					"<td colspan='2' width='34.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tAmount + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 9%" + "</td>"+
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" +   CGST + "</td>"+ 
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "9% " + "</td>"+ 	
					"<td colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</td>"+ 
					"</tr>" ;
					// table5 = "<table width=100% align=left >" + table_5  +"</table>"  //
					var table_6=     "<tr  width=100% >"+
					"<th colspan='3' width='50%' style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Total"+ "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + CGST + "</th>"+ 
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 	
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</th>"+ 
					"</tr>" ;
					table6 = "<table width=100% align=left >" + table_5 + table_6  + "</table>"  
					var tableFOOTER =	"<tr  width=100% >"+
					"<th colspan='4' width='50%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Tax is payable on reverse charge (Yes/No)"+ "</th>"+
					"<th colspan='3' width='50%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 	
					"</tr>"+"<tr  width=100%>"+
					"<th colspan='4' width='50%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "We certify that the claim is as per approved" + "<br>" +"rates. The details given above are correct and" + "<br>" 
					+"the stock is tallied as on date. We are enclosing"+"<br>"  +"rake report/stock statement along with this bill."+ "</th>"+
					"<th colspan='3' width='50%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" +  "For NIKHITHA LOGISTICS"+"<br>"+"<br>" +"<br>" +"<br>" + "Authorised Signatory" + "</th>"+ 	
					"</tr>";
					var tabledec =	"<tr  width=100% >"+
					"<th colspan='1' width='12.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Paven Aalla"+ "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " T.Ramesh Babu" + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "L Ravindra Rao  " + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Hitendra R.Jariwala" + "</th>"+ 
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"</tr>"+"<tr  width=100% >"+
					"<th colspan='1' width='12.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Sr MKTING"+"<br>"+"Executive"+ "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Dy. Mgr-Sales"+"<br>"+"Accounts"+ "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "GM-Marketing " + "</th>"+
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Sr.Vice President" + "</th>"+ 
					"<th colspan='1' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Verified "+"<br>"+"By HO" + "</th>"+ 	
					"<th colspan='2' width='25%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  Remarks" + "</th>"+
					"</tr>";
					var  tableamount= "<tr  width=100% >"+
					"<th colspan='1' width='12.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "BILL AMOUNT"+ "</th>"+
					"<th colspan='7' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"</tr>"+"<tr  width=100% >"+
					"<th colspan='1' width='12.5%'  style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "PASSED AMOUNT"+ "</th>"+
					"<th colspan='7' width='12.5%' style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"</tr>";
					tablelast = "<table width=100% align=left >" + tableFOOTER + tabledec + tableamount+"</table>"

					grand_totalFinal = grandTotal;
					//	alert(grand_totalFinal);

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

					document.getElementById("invoiceJsw").value = invoiceNumber;	  

					/*var tUnloadingPsc  =(+totalUnloadingPsc + +totalDirectPsc + +totalCrossingPsc +totalCrossPsc);
					var tUnloadingCon = (+totalUnloadingCon+ +totalDirectCon + +totalCrossingCon + totalCrossCon);
					var tLoadingPsc = (+totalLoadingPsc + +totalDirectPsc + +totalCrossingPsc + totalCrossPsc);
					var tLoadingCon = (+totalLoadingCon+ +totalDirectCon + +totalCrossingCon + totalCrossCon);
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
					var grandTotal = (+CGST + +SGST + +tAmount); */
					
					var tUnloadingPsc  =(+totalUnloadingPsc + +totalDirectInwardPsc + +totalCrossingInwardPsc + +prePsc + +totalCrossPsc + +totalDirectPsc);
					var tUnloadingCon = (+totalUnloadingCon+ +totalDirectInwardCon + +totalCrossingInwardCon + +preCon + +totalCrossCon + +totalDirectCon);
					var tLoadingPsc = (+totalLoadingPsc + +totalDirectOutwardPsc + +totalCrossingOutwardPsc + +pendingPsc + +advancePsc);
					var tLoadingCon = (+totalLoadingCon+ +totalDirectOutwardCon + +totalCrossingOutwardCon + +pendingCon + +advanceCon);
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



					grand_totalFinal = grandTotal;
					//	alert(grand_totalFinal);

					table_rows = table_rows +  
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "Sl  No."+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Particular" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " HSN/SAC" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " GST Rate " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Quantity " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Rate" + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " per " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Amount " + "</th>"+
					"</tr>"+     
					"<tr  width=100% >"+
					"<th  width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "SERVICE PROVIDER FOR STORAGE<br>AND WAREHOUSING" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "   " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"</tr>"+  
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "1"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "UNLOADING  CHARGES(PSC)" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingChargesPsc + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamaliPsc + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  MT" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingPscAmount + "</th>"+
					"</tr>"+        "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "2"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "UNLOADING  CHARGES(CON)" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingChargesCon + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamaliCon + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + unloadingConAmount + "</th>"+
					"</tr>"+ 
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "3"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "RELOADING CHARGES(PSC)" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + loadingChargesPsc + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamaliPsc + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + loadingPscAmount + "</th>"+
					"</tr>"+      "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "4"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "RELOADING  CHARGES(CON)" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + loadingChargesCon + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + hamaliCon + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + loadingConAmount + "</th>"+
					"</tr>"+   
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "5"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "SERVICE CHARGES" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 18%" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tBags + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + serviceCharge1 + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tService + "</th>"+
					"</tr>"+             "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "6"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "DIVERSION (PSC)" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + diversionPsc + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"</tr>"+ 
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "7"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "DIVERSION (CON)"+  "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + diversionCon + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"</tr>"+       "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "8"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "CFA EXPENSES" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " 996729" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + fixedExpenses1 + "</th>"+
					"</tr>"+     "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + "9"+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "TOTAL" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + tAmount + "</th>"+
					"</tr>"  + "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "CGST@9%" + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + CGST + "</th>"+
					"</tr>"+ 
					"<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "SGST@9%"+  "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + SGST + "</th>"+
					"</tr>"+       "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "Rounding Off." + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"</tr>"+     "<tr  width=100% >"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=40% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + " Total" + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "   " + "</th>"+ 	
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+ 
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + grandTotal + "</th>"+
					"</tr>"; 	                             
					table6 = "<table width=100% align=left >" + table_rows +"</table>"  
					var table_amnt="<tr  width=100% >"+
					"<th width=85% style='border: 1px solid black;border-collapse:collapse;text-align:center;padding-left:3px; padding-right:3px'>" + " "+ "</th>"+
					"<th width=5% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + ".<br>.<br>. " + "</th>"+
					"<th width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center; padding-left:3px; padding-right:3px'>" + "  " + "</th>"+
					"</tr>"; 
					table7 = "<table width=100% align=left >" + table_amnt +"</table>"
					var table_end= "<tr  width=100%>"+
					"<td rowspan='5'width=15% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height:150%;'>"+ 'HSN/SAC'+"</td>"+
					"<td rowspan='5'width=10% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Taxable Value'+"</td>"+
					"</tr>"+"<tr  width=100%>"+
					"<td colspan='2' width=26% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Central Tax'+"</td>"+
					"<td colspan='2' width=26% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'State Tax'+"</td>"+
					"<td colspan='2' width=26% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Integrated Tax'+"</td>"+
					"</tr>"+"<tr  width=100%>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td width=13% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td  width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td  width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Amount'+"</td>"+
					"</tr>";
					table8 = "<table width=100% align=left >" + table_end +"</table>"
					var tabledec= "<tr  width=100%>"+
					"<td width=15%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ '996729'+"</td>"+
					"<td  width=10%  style=border: 1px solid black;border-collapse:collapse;'text-align:center;line-height: 150%;'>"+ tAmount+"</td>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ '9%'+"</td>"+
					"<td width=13% style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ CGST+"</td>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ '9%'+"</td>"+
					"<td  width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ SGST+"</td>"+
					"<td width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ 'Rate'+"</td>"+
					"<td  width=13%  style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 150%;'>"+ grandTotal+"</td>"+
					"</tr>";
					tabledec = "<table width=100% align=left >" + tabledec +"</table>"
					$('#dataTabjsw6').html(table6);
					$('#dataTabcdec').html(tabledec);
					$('#dataTabjsw8').html(table8);
					$('#dataTabjsw7').html(table7);

					$('#jsw').css('display','block');
					$('#export_jsw').css('display','block');
					$('#billInfo').css('display','block');


				}

			}


		},
		error: function(error) {
			$(".overlay").hide();
			console.log(error);
		}




	});


}

function addBill(){

	/*var loading = (+loadingPpc + +loadingOpc);
	var unloading = (+unloadingPpc + +unloadingOpc);
	var crossing = (+crossingPpc + +crossingOpc);
	var direct = (+directPpc + +directOpc);


	toPpc = (+loadingPpc + +crossingPpc + +directPpc);
	toOpc = (+loadingOpc + +crossingOpc+ +directOpc);



	PpcTrade = (+totalLoadingPpcTrade + +totalCrossingPpcTrade + +totalDirectPpcTrade);
	OpcTrade = (+totalLoadingOpcTrade + +totalCrossingOpcTrade + +totalDirectOpcTrade);
	toUnloadingPsc  =(+totalUnloadingPsc + +totalDirectPsc + +totalCrossingPsc);
	toUnloadingCon = (+totalUnloadingCon+ +totalDirectCon + +totalCrossingCon);
	toLoadingPsc = (+totalLoadingPsc + +totalDirectPsc + +totalCrossingPsc);
	toLoadingCon = (+totalLoadingCon+ +totalDirectCon + +totalCrossingCon);

	if(!invoiceNumberBharathi == "" ){
		finalInvoice = invoiceNumber + "/" + invoiceNumberBharathi;
	}else if(!invoiceNumberDeccan == ""){
		finalInvoice = invoiceNumber + "/" + invoiceNumberDeccan;	   
	}else{
		finalInvoice = invoiceNumber;	
	} */
//	alert(finalInvoice);
	/*if(loading ==''){ 
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


	if(listOfHamaliTrade==''){ 
		listOfHamaliTrade=0;
	}

	if(listOfFixed ==''){ 
		listOfFixed=0;
	}*/
	/*if(totalPsc==''){ 
		totalPsc=0;
	}*/
//	if(listOfHamaliPsc ==''){ 
//		listOfHamaliPsc=0;
	//}
	/*if(totalCon==''){ 
		totalCon=0;
	}*/
	//if(listOfHamaliCon ==''){ 
	//	listOfHamaliCon=0;
	//}



	var requestData = new Object();
	requestData.association_name=listOfAssociation;
	//requestData.godown = godown;
	requestData.invoiceNumber = finalInvoice;
	requestData.grandTotal = grand_totalFinal;


	$.ajax({
		url: '/transport/godown/bill/add',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			alert(response.message);

		},
		error: function(error) {
			console.log(error);
		}
	});

}
//});
