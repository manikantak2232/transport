var dataSet=[];
var dataSet2=[];
var dataSe=[];
var total_income=0;
var total_expenditure=0;

var income_array=[];
var expenditure_array=[];
var income_inc=0;
var exp_inc=0;

var opening_balances='';

function getOpeningBalances(){
	dataSet2=[];
	income_array=[];
	expenditure_array=[];
	total_income=0;
	total_expenditure=0;
	
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

				getProfitAndLoss();
			},
			error: function(error) {
				$(".overlay").hide();
				alert('Something wrong Try Agian..');
				console.log(error);
			}
		});
	} else {
		getProfitAndLoss();
	}

}

function getProfitAndLoss(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();

	lower_date=$('#lower_date').val();
	upper_date=$('#upper_date').val();

	var requestData = new Object();
	requestData.lower_date=lower_date;
	requestData.upper_date=upper_date;

	$.ajax({
		url: '/transport/accounts/profit_and_loss/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			$(".overlay").hide();
			income=response.income;
			expenditure=response.expenditure;

			for(var i=0; i<income.length; i++){
				if(opening_balances!=''){
					var account_count=opening_balances.filter(obj=>obj.account_name==income[i].name);
					if(account_count.length!=0){
						if((income[i].debit+ +account_count[0].debit)>(income[i].credit+ +account_count[0].credit)){
							dataSe=[];
							dataSe.push(exp_inc+1);
							dataSe.push(income[i].name);
							dataSe.push((income[i].debit+ +account_count[0].debit)-(income[i].credit+ +account_count[0].credit));

							total_expenditure = total_expenditure +(income[i].debit + account_count[0].debit) - (income[i].credit + account_count[0].credit);
							expenditure_array.push(dataSe);
							exp_inc=exp_inc+1;
						}else{
							dataSe=[];
							dataSe.push(income_inc+1);
							dataSe.push(income[i].name);
							dataSe.push((income[i].credit+ +account_count[0].credit)-(income[i].debit+ +account_count[0].debit));

							total_income =total_income+ (income[i].credit+ +account_count[0].credit) - (income[i].debit+ +account_count[0].debit);
							income_array.push(dataSe);
							income_inc=income_inc+1;
						}

					}else {
						amount = income[i];
						dataSe=[];
						dataSe.push(income_inc+1);
						dataSe.push(amount.name);
						dataSe.push((amount.debit + + amount.credit) );

						total_income=total_income+ +(amount.debit+ +amount.credit);
						
						income_array.push(dataSe);
						income_inc=income_inc+1;
					}

				}else {
					amount = income[i];
					dataSe=[];
					dataSe.push(income_inc+1);
					dataSe.push(amount.name);
					dataSe.push((amount.debit + amount.credit) );

					total_income=total_income+ +(amount.debit+ +amount.credit);
					
					income_array.push(dataSe);
					income_inc=income_inc+1;
				}

			//	dataSet2.push(dataSe);


			}

			for(var i=0; i<expenditure.length; i++){
				if(opening_balances!=''){
					var account_count=opening_balances.filter(obj=>obj.account_name==expenditure[i].name);
					
					if(account_count.length!=0){
						if((expenditure[i].debit+ +account_count[0].debit)>(expenditure[i].credit+ +account_count[0].credit)){
							dataSe=[];
							dataSe.push(exp_inc+1);
							dataSe.push(expenditure[i].name);
							dataSe.push((expenditure[i].debit+ +account_count[0].debit)-(expenditure[i].credit+ +account_count[0].credit));
							
							total_expenditure+=(expenditure[i].debit+ +account_count[0].debit)-(expenditure[i].credit+ +account_count[0].credit);
							
							expenditure_array.push(dataSe);
							exp_inc=exp_inc+1;
						}else{
							dataSe=[];
							dataSe.push(income_inc+1);
							dataSe.push(expenditure[i].name);
							dataSe.push((expenditure[i].credit+ +account_count[0].credit)-(expenditure[i].debit+ +account_count[0].debit));
							
							total_income+=(expenditure[i].credit+ +account_count[0].credit)-(expenditure[i].debit+ +account_count[0].debit);
							
							income_array.push(dataSe);
							income_inc=income_inc+1;
						}
					}else{
						amount = expenditure[i];
						dataSe=[];
						dataSe.push(exp_inc+1);
						dataSe.push(amount.name);
						dataSe.push((amount.debit + amount.credit) );
						
						total_expenditure=total_expenditure+amount.debit + amount.credit;
						
						expenditure_array.push(dataSe);
						exp_inc=exp_inc+1;
					}

				}else{
					amount = expenditure[i];
					dataSe=[];
					dataSe.push(exp_inc+1);
					dataSe.push(amount.name);
					dataSe.push((amount.debit + amount.credit));
					
					total_expenditure=total_expenditure+amount.debit + amount.credit;
					
					expenditure_array.push(dataSe);
					exp_inc=exp_inc+1;
				}

			//	dataSet2.push(dataSe);

				
			}
			
			dataSe=[];
			dataSe.push('');
			dataSe.push('<b>Income</b>');
			dataSe.push('');

			dataSet2.push(dataSe);
			
			for(var j=0;j<income_array.length;j++){
				dataSet2.push(income_array[j]);
			}
			
			dataSe=[];
			dataSe.push('');
			dataSe.push('<b>Expenditure</b>');
			dataSe.push('');

			dataSet2.push(dataSe);
			
			for(var j=0;j<expenditure_array.length;j++){
				dataSet2.push(expenditure_array[j]);
			}

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
		data: dataSet2,
		destroy: true,
		"bSort" : false,
		"bPaginate": false,
		"bInfo": false,
		"bFilter": true,
		columns: [
			{ title: "S.No" },
			{ title: "Account Name" },
			{ title: "Amount", render: $.fn.dataTable.render.number( ',', '.', 2 ) }
			]
	} );
	if(total_income>total_expenditure){
		$("#example").append('<tfoot><th></th><th>Net Profit</th><th>'+(total_income-total_expenditure).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>');
	}else{
		$("#example").append('<tfoot><th></th><th>Net Loss</th><th>'+(total_expenditure-total_income).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>');
	}


}
function fun2() {

	$('#example2').DataTable( {
		data: dataSet2,
		destroy: true,
		columns: [
			{ title: "S.No" },
			{ title: "Credit Balances" },
			{ title: "Amount" }
			]
	} );

	$("#example2").append('<tfoot><th></th><th></th><th>'+total_income+'</th></tfoot>');
}


