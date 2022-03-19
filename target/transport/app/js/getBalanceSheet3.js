var dataSet=[];
var dataSet2=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var total_liabilities_debit=0;
var total_liabilities_credit=0;
var debtors=0;
var value_debtor=0;
var value_creditor=0;
var total_assests=0;
var total_liabilities=0;
var creditors=0;
var net_profit=0;
var net_loss=0;
var pl_debit=0;
var pl_credit=0;
var accounts;
var assets_inc=0;
var liabilities_inc=1; //net profit or loss index is 1. so intializing it with 1.
var opening_balances='';
var assests_array=[];
var liabilities_array=[];

function getOpeningBalances(){

	var requestData = new Object();

	lower_date=$('#lower_date').val();
	upper_date=$('#upper_date').val();

	$(".overlay").show();

	if(lower_date=='2018-04-01'){
		var requestData = new Object();
		requestData.lower_date=lower_date;
		requestData.upper_date=upper_date;

		$.ajax({
			url: '/transport/accounts/opening_balance/tb/get',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(response) {	
				opening_balances=response.openingBlance;

				getBalanceSheet();
			},
			error: function(error) {
				$(".overlay").hide();
				alert('Something wrong Try Agian..');
				console.log(error);
			}
		});
	} else {
		getBalanceSheet();
	}

}

var incomeAndExpAccounts;
var drAndCrAccounts;

function getBalanceSheet(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	total_assests=0;
	total_liabilities=0;
	assets_inc=0;
	liabilities_inc=1;
	assests_array=[];
	liabilities_array=[];
	debtors=0;
	creditors=0;
	
	net_profit=0;
	net_loss=0;

	document.getElementById("example").deleteTFoot();
	$(".overlay").show();

	lower_date=$('#lower_date').val();
	upper_date=$('#upper_date').val();

	var requestData = new Object();
	requestData.lower_date=lower_date;
	requestData.upper_date=upper_date;

	$.ajax({
		url: '/transport/accounts/balance_sheet2/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			$(".overlay").hide();

			accounts = response.data;

			dataSe=[];
			dataSe.push('<b>S.No</b>');
			dataSe.push('<b>Assets</b>');
			dataSe.push('<b>Amount</b>');

			dataSet.push(dataSe);

			$.each(accounts, function(k, v) {

				for(var j=0;j<v.length;j++){
					if(opening_balances!=''){
						var account_count=opening_balances.filter(obj=>obj.account_name==v[j].name);
						if(account_count.length!=0){
							if((v[j].group_type=='income') | (v[j].group_type=='expenditure')){
								if((v[j].debit + account_count[0].debit)>(v[j].credit + account_count[0].credit)){
									pl_debit += ((v[j].debit + account_count[0].debit)-(v[j].credit + account_count[0].credit));
								}else{
									pl_credit += ((v[j].credit + account_count[0].credit)-(v[j].debit + account_count[0].debit));
								}
							}else{
								if(v[j].group_name=='Vendor/Customer'){
									if((v[j].debit + account_count[0].debit)>(v[j].credit + account_count[0].credit)){
										debtors += ((v[j].debit + account_count[0].debit)-(v[j].credit + account_count[0].credit));

									}else{
										creditors += ((v[j].credit + account_count[0].credit)-(v[j].debit + account_count[0].debit));
									}
								}else{
									if((v[j].debit + account_count[0].debit)>(v[j].credit + account_count[0].credit)){
										assets_inc=assets_inc+1
										dataSe=[];
										dataSe.push(assets_inc);
										dataSe.push(v[j].name);
										dataSe.push(((v[j].debit + account_count[0].debit)-(v[j].credit + account_count[0].credit)).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

										assests_array.push(dataSe);

										total_debit=total_debit+((v[j].debit + account_count[0].debit)-(v[j].credit + account_count[0].credit));
										//	account_count[0].account_status=1;
										//		total_credit=total_credit+(v[j].credit);
									}else{
										liabilities_inc=liabilities_inc+1
										dataSe=[];
										dataSe.push(liabilities_inc);
										dataSe.push(v[j].name);
										dataSe.push(((v[j].credit + account_count[0].credit)-(v[j].debit + account_count[0].debit)).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

										liabilities_array.push(dataSe);
										//		total_debit=total_debit+(v[j].debit);
										total_credit=total_credit+((v[j].credit + account_count[0].credit)-(v[j].debit + account_count[0].debit));
										//	account_count[0].account_status=1;
									}
								}
							}
							
						}else{
							if((v[j].group_type=='income') | (v[j].group_type=='expenditure')){
								pl_debit += v[j].debit;
								pl_credit += v[j].credit;
							}else{
								if(v[j].group_name=='Vendor/Customer'){
									debtors += v[j].debit;
									creditors += v[j].credit;
								}else{
									if(v[j].debit!=0){
										assets_inc=assets_inc+1
										dataSe=[];
										dataSe.push(assets_inc);
										dataSe.push(v[j].name);
										dataSe.push((v[j].debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

										assests_array.push(dataSe);

										total_debit=total_debit+(v[j].debit);
									}else{
										liabilities_inc=liabilities_inc+1
										dataSe=[];
										dataSe.push(liabilities_inc);
										dataSe.push(v[j].name);
										dataSe.push((v[j].credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

										liabilities_array.push(dataSe);

										total_credit=total_credit+(v[j].credit);
									}
								}
							}
							
						}
					}else{

						if((v[j].group_type=='income') | (v[j].group_type=='expenditure')){
							pl_debit += v[j].debit;
							pl_credit += v[j].credit;
						}else{
							if(v[j].group_name=='Vendor/Customer'){
								debtors += v[j].debit;
								creditors += v[j].credit;
							}else{
								if(v[j].debit!=0){
									assets_inc=assets_inc+1
									dataSe=[];
									dataSe.push(assets_inc);
									dataSe.push(v[j].name);
									dataSe.push((v[j].debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

									assests_array.push(dataSe);

									total_debit=total_debit+(v[j].debit);
								}else{
									liabilities_inc=liabilities_inc+1
									dataSe=[];
									dataSe.push(liabilities_inc);
									dataSe.push(v[j].name);
									dataSe.push((v[j].credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

									liabilities_array.push(dataSe);

									total_credit=total_credit+(v[j].credit);
								}
							}
						}

					}
				}
			});

			for(var j=0;j<assests_array.length;j++){
				dataSet.push(assests_array[j]);
			}

			total_assests=total_assests+(total_debit);
			dataSe=[];
			dataSe.push(assets_inc+1);
			dataSe.push('Debtors');
			dataSe.push((debtors).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

			dataSet.push(dataSe);

			dataSe=[];
			dataSe.push('');
			dataSe.push('');
			dataSe.push('<b>'+(total_assests+debtors).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</b>');

			dataSet.push(dataSe);

			dataSe=[];
			dataSe.push('<b>S.No</b>');
			dataSe.push('<b>Liabilities</b>');
			dataSe.push('<b>Amount</b>');

			dataSet.push(dataSe);

			if(pl_credit>pl_debit){
				total_liabilities=pl_credit-pl_debit+creditors+total_credit;
				dataSe=[];
				dataSe.push(1);
				dataSe.push('Net Profit');
				dataSe.push((pl_credit-pl_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

				dataSet.push(dataSe);
			}else{
				total_liabilities=pl_debit-pl_credit-creditors;
				if(total_liabilities>total_credit){
					total_liabilities=total_liabilities-total_credit;
				}else{
					total_liabilities=total_credit-total_liabilities;
				}
				dataSe=[];
				dataSe.push(1);
				dataSe.push('Net Loss');
				dataSe.push((pl_debit-pl_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

				dataSet.push(dataSe);
			}

			for(var j=0;j<liabilities_array.length;j++){
				dataSet.push(liabilities_array[j]);
			}

			dataSe=[];
			dataSe.push(liabilities_inc+1);
			dataSe.push('Creditors');
			dataSe.push((creditors).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));

			dataSet.push(dataSe);

			dataSe=[];
			dataSe.push('');
			dataSe.push('');
			dataSe.push('<b>'+((total_liabilities)).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</b>');

			dataSet.push(dataSe);

			fun();
			//	fun2();

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {

	$('#example').DataTable( {
		data: dataSet,
		destroy: true,
		"bSort" : false,
		"bPaginate": false,
		"bInfo": false,
		"bFilter": false,
		columns: [
			{ title: "" },
			{ title: "" },
			{ title: "" }
			]
	} );

	//   $("#example").append('<tfoot><th></th><th></th><th>'+(total_assests+debtors)+'</th></tfoot>');	

}
