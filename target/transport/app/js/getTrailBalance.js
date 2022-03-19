var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var purchases=[];
var sales_and_income=[];
var cash_and_bank=[];
var vehicle_expenses=[];
var sundry_debtors=[];
var sundry_creditors=[];

function getTrialBalance(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();

	$.ajax({
		url: '/transport/accounts/trail_balance/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
			$(".overlay").hide();
			trialBalance = response.trialBalance;

			for(var i=0; i<trialBalance.length; i++){
				tBalance = trialBalance[i];
				
				if(tBalance.group_name=='Vendor/Customer'){
					if(tBalance.debit==0){
						dataSe=[];
						dataSe.push(tBalance.name);
						dataSe.push((tBalance.debit) );
						dataSe.push((tBalance.credit));
						sundry_creditors.push(dataSe);
						
						total_debit=total_debit+tBalance.debit;
						total_credit=total_credit+tBalance.credit;
					}else{
						dataSe=[];
						dataSe.push(tBalance.name);
						dataSe.push((tBalance.debit));
						dataSe.push((tBalance.credit));
						sundry_debtors.push(dataSe);
						
						total_debit=total_debit+tBalance.debit;
						total_credit=total_credit+tBalance.credit;
					}	
				}
				if(tBalance.group_name=='Sales & Income'){
					dataSe=[];
					dataSe.push(tBalance.name);
					dataSe.push((tBalance.debit));
					dataSe.push((tBalance.credit));
					sales_and_income.push(dataSe);
					
					total_debit=total_debit+tBalance.debit;
					total_credit=total_credit+tBalance.credit;
				}
				if(tBalance.group_name=='Purchases'){
					dataSe=[];
					dataSe.push(tBalance.name);
					dataSe.push((tBalance.debit) );
					dataSe.push((tBalance.credit) );
					purchases.push(dataSe);
					
					total_debit=total_debit+tBalance.debit;
					total_credit=total_credit+tBalance.credit;
				}
				if(tBalance.group_name=='Cash & Bank'){
					dataSe=[];
					dataSe.push(tBalance.name);
					dataSe.push((tBalance.debit) );
					dataSe.push((tBalance.credit));
					cash_and_bank.push(dataSe);
					
					total_debit=total_debit+tBalance.debit;
					total_credit=total_credit+tBalance.credit;
				}
				if(tBalance.group_name=='Vehicle Expences'){
					dataSe=[];
					dataSe.push(tBalance.name);
					dataSe.push((tBalance.debit));
					dataSe.push((tBalance.credit));
					vehicle_expenses.push(dataSe);
					
					total_debit=total_debit+tBalance.debit;
					total_credit=total_credit+tBalance.credit;
				}
				
				/*dataSe=[];
				dataSe.push(i+1);
				dataSe.push(tBalance.name);
				
				if(tBalance.debit>tBalance.credit){
					dataSe.push((tBalance.debit)-(tBalance.credit));
					dataSe.push(0);
					total_debit=total_debit+((tBalance.debit)-(tBalance.credit));
				}else{
					dataSe.push(0);
					dataSe.push((tBalance.credit)-(tBalance.debit));
					total_credit=total_credit+((tBalance.credit)-(tBalance.debit));
				}
				
				dataSet.push(dataSe);*/
				
			}
			
			fun();

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {
	
	if(purchases.length!=0){
		dataSe=[];
		dataSe.push('<b>Purchases A/C</b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
//		dataSet.push(purchases);
		
		for(var i=0; i<purchases.length; i++){
			dataSe=[];
			dataSe.push(purchases[i][0]);
			dataSe.push(purchases[i][1]);
			dataSe.push(purchases[i][2]);
			dataSet.push(dataSe);
		}
		
	}
	if(sales_and_income.length!=0){
		dataSe=[];
		dataSe.push('<b>Sales & Income A/C</b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
	//	dataSet.push(sales_and_income);
		
		for(var i=0; i<sales_and_income.length; i++){
			dataSe=[];
			dataSe.push(sales_and_income[i][0]);
			dataSe.push(sales_and_income[i][1]);
			dataSe.push(sales_and_income[i][2]);
			dataSet.push(dataSe);
		}
	}
	if(cash_and_bank.length!=0){
		dataSe=[];
		dataSe.push('<b>Cash & Bank</b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
	//	dataSet.push(cash_and_bank);
		
		for(var i=0; i<cash_and_bank.length; i++){
			dataSe=[];
			dataSe.push(cash_and_bank[i][0]);
			dataSe.push(cash_and_bank[i][1]);
			dataSe.push(cash_and_bank[i][2]);
			dataSet.push(dataSe);
		}
	}
	
	if(vehicle_expenses.length!=0){
		dataSe=[];
		dataSe.push('<b>Vehicle Expenses</b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
	//	dataSet.push(cash_and_bank);
		
		for(var i=0; i<vehicle_expenses.length; i++){
			dataSe=[];
			dataSe.push(vehicle_expenses[i][0]);
			dataSe.push(vehicle_expenses[i][1]);
			dataSe.push(vehicle_expenses[i][2]);
			dataSet.push(dataSe);
		}
	}
	
	if(sundry_debtors.length!=0){
		dataSe=[];
		dataSe.push('<b>Sundry Debtors</b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
	//	dataSet.push(sundry_debtors);
		
		for(var i=0; i<sundry_debtors.length; i++){
			dataSe=[];
			dataSe.push(sundry_debtors[i][0]);
			dataSe.push(sundry_debtors[i][1]);
			dataSe.push(sundry_debtors[i][2]);
			dataSet.push(dataSe);
		}
	}
	if(sundry_creditors.length!=0){
		dataSe=[];
		dataSe.push('<b>Sundry Creditors </b>');
		dataSe.push('');
		dataSe.push('');
		dataSet.push(dataSe);
	//	dataSet.push(sundry_creditors);
		
		for(var i=0; i<sundry_creditors.length; i++){
			dataSe=[];
			dataSe.push(sundry_creditors[i][0]);
			dataSe.push(sundry_creditors[i][1]);
			dataSe.push(sundry_creditors[i][2]);
			dataSet.push(dataSe);
		}
	}
	
	
	
    $('#example').DataTable( {
        data: dataSet,
        destroy: true,
        "bSort" : false,
        "bPaginate": false,
        "bInfo": false,
        "bFilter": false,
        columns: [
			{ title: "Account Name" },
            { title: "Debit", render: $.fn.dataTable.render.number( ',', '.', 2 ) },
            { title: "Credit", render: $.fn.dataTable.render.number( ',', '.', 2 ) }
        ]
    } );
      	
		$("#example").append('<tfoot><th></th><th>'+(total_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th>'+(total_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>');		

};

