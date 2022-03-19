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
var trialBalance={};
var table_ary=[];
var excel_bold=[];

var lower_date;
var upper_date;

var opening_balances='';

function getOpeningBalances(){

	sundry_debtors=[];
	sundry_creditors=[];
	table_ary=[];
	excel_bold=[];

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

				getTrialBalance();
			},
			error: function(error) {
				$(".overlay").hide();
				alert('Something wrong Try Agian..');
				console.log(error);
			}
		});
	} else {
		getTrialBalance();
	}

}

function getTrialBalance(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	$('#export_button').hide();

	lower_date=$('#lower_date').val();
	upper_date=$('#upper_date').val();

	var requestData = new Object();
	requestData.lower_date=lower_date;
	requestData.upper_date=upper_date;

	$.ajax({
		url: '/transport/accounts/trail_balance2/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			trialBalance1 = response.data;

			Object.keys(trialBalance1).sort().forEach(function(key) {
				trialBalance[key] = trialBalance1[key];
			});

			$.each(trialBalance, function(k, v) {

				if(k!='Vendor/Customer'){
					var dt='<b>'+k+'</b>';
					dataSe=[];
					dataSe.push(dt);
					dataSe.push(0);
					dataSe.push(0);
					dataSet.push(dataSe);

					table_ary.push(dataSet.indexOf(dataSe));

					for(var j=0;j<v.length;j++){

						if(opening_balances!=''){
							var account_count=opening_balances.filter(obj=>obj.account_name==v[j].name);
							if(account_count.length!=0){
								if((v[j].debit+ +account_count[0].debit)>(v[j].credit+ +account_count[0].credit)){
									dataSe=[];
									dataSe.push(v[j].name);
									dataSe.push((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));
									dataSe.push(0);

									total_debit=total_debit+((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));

									account_count[0].account_status=1;

								}else{
									dataSe=[];
									dataSe.push(v[j].name);
									dataSe.push(0);
									dataSe.push((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

									total_credit=total_credit+((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

									account_count[0].account_status=1;
								}

							}else{
								dataSe=[];
								dataSe.push(v[j].name);
								dataSe.push(v[j].debit);
								dataSe.push(v[j].credit);

								total_debit=total_debit+v[j].debit;
								total_credit=total_credit+v[j].credit;
							}
						}else{
							dataSe=[];
							dataSe.push(v[j].name);
							dataSe.push(v[j].debit);
							dataSe.push(v[j].credit);

							total_debit=total_debit+v[j].debit;
							total_credit=total_credit+v[j].credit;
						}

						dataSet.push(dataSe);


					}
				}
			});

			$.each(trialBalance, function(k, v) {
				//  alert(v);
				if(k=='Vendor/Customer'){

					for(var j=0;j<v.length;j++){

						if(v[j].debit==0){
							if(opening_balances!=''){
								var account_count=opening_balances.filter(obj=>obj.account_name==v[j].name);

								if(account_count.length!=0){
									if((v[j].debit+ +account_count[0].debit)>(v[j].credit+ +account_count[0].credit)){
										dataSe=[];
										dataSe.push(v[j].name);
										dataSe.push((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));
										dataSe.push(0);

										total_debit=total_debit+((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));

										sundry_debtors.push(dataSe);

										account_count[0].account_status=1;
									}else{
										dataSe=[];
										dataSe.push(v[j].name);
										dataSe.push(0);
										dataSe.push((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

										total_credit=total_credit+((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

										sundry_creditors.push(dataSe);

										account_count[0].account_status=1;
									}

								}else{
									dataSe=[];
									dataSe.push(v[j].name);
									dataSe.push(v[j].debit);
									dataSe.push(v[j].credit);

									total_debit=total_debit+v[j].debit;
									total_credit=total_credit+v[j].credit;

									sundry_creditors.push(dataSe);
								}
							}else{
								dataSe=[];
								dataSe.push(v[j].name);
								dataSe.push(v[j].debit);
								dataSe.push(v[j].credit);

								total_debit=total_debit+v[j].debit;
								total_credit=total_credit+v[j].credit;

								sundry_creditors.push(dataSe);
							}


						}else{
							if(opening_balances!=''){
								var account_count=opening_balances.filter(obj=>obj.account_name==v[j].name);
								if(account_count.length!=0){
									if((v[j].debit+ +account_count[0].debit)>(v[j].credit+ +account_count[0].credit)){
										dataSe=[];
										dataSe.push(v[j].name);
										dataSe.push((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));
										dataSe.push(0);

										total_debit=total_debit+((v[j].debit+ +account_count[0].debit)-(v[j].credit+ +account_count[0].credit));

										sundry_debtors.push(dataSe);

										account_count[0].account_status=1;
									}else{
										dataSe=[];
										dataSe.push(v[j].name);
										dataSe.push(0);
										dataSe.push((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

										total_credit=total_credit+((v[j].credit+ +account_count[0].credit)-(v[j].debit+ +account_count[0].debit));

										sundry_creditors.push(dataSe);

										account_count[0].account_status=1;
									}
								}else{
									dataSe=[];
									dataSe.push(v[j].name);
									dataSe.push(v[j].debit);
									dataSe.push(v[j].credit);

									total_debit=total_debit+v[j].debit;
									total_credit=total_credit+v[j].credit;

									sundry_debtors.push(dataSe);
								}
							}else{
								dataSe=[];
								dataSe.push(v[j].name);
								dataSe.push(v[j].debit);
								dataSe.push(v[j].credit);

								total_debit=total_debit+v[j].debit;
								total_credit=total_credit+v[j].credit;

								sundry_debtors.push(dataSe);
							}
						}
					}


				}
			});
		
		// Checking accounts only in Opening balances and do not have any other transactions. 	
			
			if(opening_balances!=''){
				var opening_bal_names=opening_balances.filter(obj=>obj.account_status==0);

				if(opening_bal_names.length!=0){

					for(var i=0;i<opening_bal_names.length;i++){
						if((opening_bal_names[i].group_name)!='Vendor/Customer'){
							for(var j=0;j<dataSet.length;j++){
								if(dataSet[j][0]==('<b>'+opening_bal_names[i].group_name+'</b>')){

									dataSe=[];
									dataSe.push(opening_bal_names[i].account_name);
									dataSe.push(opening_bal_names[i].debit);
									dataSe.push(opening_bal_names[i].credit);

									total_debit=total_debit+opening_bal_names[i].debit;
									total_credit=total_credit+opening_bal_names[i].credit;

									dataSet.splice((j+1), 0,dataSe);
								}
							}
						}else{
							if(opening_bal_names[i].debit!=0){

								dataSe=[];
								dataSe.push(opening_bal_names[i].account_name);
								dataSe.push(opening_bal_names[i].debit);
								dataSe.push(opening_bal_names[i].credit);

								total_debit=total_debit+opening_bal_names[i].debit;
								total_credit=total_credit+opening_bal_names[i].credit;

								sundry_debtors.push(dataSe);
							}else{
								dataSe=[];
								dataSe.push(opening_bal_names[i].account_name);
								dataSe.push(opening_bal_names[i].debit);
								dataSe.push(opening_bal_names[i].credit);

								total_debit=total_debit+opening_bal_names[i].debit;
								total_credit=total_credit+opening_bal_names[i].credit;

								sundry_creditors.push(dataSe);
							}
						}
					}
				}
			}
		// end of Opening balances checking.
			
			fun();

		},
		error: function(error) {
			$(".overlay").hide();
			alert('Something wrong Try Agian..');
			console.log(error);
		}
	});
}

function fun() {

	if(sundry_debtors.length!=0){
		dataSe=[];
		dataSe.push(('Sundry Debtors').bold());
		dataSe.push(0);
		dataSe.push(0);
		dataSet.push(dataSe);

		table_ary.push(dataSet.indexOf(dataSe));

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
		dataSe.push(0);
		dataSe.push(0);
		dataSet.push(dataSe);
		table_ary.push(dataSet.indexOf(dataSe));


		for(var i=0; i<sundry_creditors.length; i++){
			dataSe=[];
			dataSe.push(sundry_creditors[i][0]);
			dataSe.push((sundry_creditors[i][1]));
			dataSe.push((sundry_creditors[i][2]));
			dataSet.push(dataSe);
		}
	}

	// for displaying individual groups debit or credit sum in Tb
	var group_credit_sum=0;
	var group_debit_sum=0;
	for(var z=0;z<table_ary.length;z++){

		for(var y=0; y<dataSet.length; y++){

			if(y > table_ary[z]){

				group_credit_sum+=dataSet[y][2];
				group_debit_sum+=dataSet[y][1];

				if(y==table_ary[z+1] | y==(dataSet.length -1)){

					if(group_debit_sum > group_credit_sum){
						dataSet[table_ary[z]][1] = group_debit_sum - group_credit_sum;
						dataSet[table_ary[z]][2]= 0 ;
					}else {
						dataSet[table_ary[z]][1]= 0;
						dataSet[table_ary[z]][2] = group_credit_sum - group_debit_sum;
					}

					group_credit_sum=0;
					group_debit_sum=0;
					break;
				}

			}
		}

	}
	
	// end
	
	// removing accounts having both zero debit and zero credit
	var removeIndexes=[];
	for(var i=0; i<dataSet.length; i++){
		if(dataSet[i][1]===0 & dataSet[i][2]===0 ){			
			removeIndexes.push(i);
		}
	}

	for (var i = removeIndexes.length -1; i >= 0; i--)
		dataSet.splice(removeIndexes[i],1);
	
	// end

/*	for(var i=0;i<dataSet.length;i++){
		if(('<b>Sundry Creditors </b>')==(dataSet[i][0])){
			excel_bold.push(i);
		}
	}*/

	$('#example').DataTable( {
		data: dataSet,
		destroy: true,
		"bSort" : false,
		"bPaginate": false,
		"bInfo": false,
		"bFilter": false,
		dom: 'lBfrtip',

		columns: [
			{ title: "Account Name" },
			{ title: "Debit" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
			{ title: "Credit" , render: $.fn.dataTable.render.number( ',', '.', 2 )}
			]
	} );

	$("#example").append('<tfoot><th></th><th>'+(total_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th>'+(total_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>');		
	$('#export_button').show();
};

