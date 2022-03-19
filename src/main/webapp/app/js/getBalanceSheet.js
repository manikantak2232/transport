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
var cashAndBankCreditor;
var cashAndBankDebtor;

function getBalanceSheet(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	
	$.ajax({
		url: '/transport/accounts/balance_sheet/get',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {
			$(".overlay").hide();
			debtors=response.debtors;
			creditors=response.creditors;
			pl_debit=response.pl_debit;
			pl_credit=response.pl_credit;
			
			cashAndBankDebtor = response.cashAndBankDebtor;
			
			cashAndBankCreditor = response.cashAndBankCreditor;
	//		dataSet.push(debtors);
			
			dataSe=[];
			dataSe.push('<b>S.No</b>');
			dataSe.push('<b>Assets</b>');
			dataSe.push('<b>Amount</b>');
			
			dataSet.push(dataSe);
			
			if(cashAndBankDebtor.length!=0){
				for(var i=0; i<cashAndBankDebtor.length; i++){
					amount = cashAndBankDebtor[i];
					dataSe=[];
					dataSe.push(i+1);
					dataSe.push(amount.name);
					dataSe.push((amount.debit + amount.credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
					
					dataSet.push(dataSe);
					
					total_debit=total_debit+(amount.debit);
					total_credit=total_credit+(amount.credit);
					value_debtor=value_debtor+i+1;
				}
			}
			total_assests=total_assests+(total_debit+total_credit);
			dataSe=[];
			dataSe.push(value_debtor+1);
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
	    		total_liabilities=pl_credit-pl_debit+creditors;
	    		dataSe=[];
	        	dataSe.push(1);
	        	dataSe.push('Net Profit');
	        	dataSe.push((pl_credit-pl_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
	        	
	        	dataSet.push(dataSe);
	    	}else{
	    		total_liabilities=pl_debitpl_credit-creditors;
	    		dataSe=[];
	    		dataSe.push(1);
	        	dataSe.push('Net Loss');
	        	dataSe.push((pl_debit-pl_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
	        	
	        	dataSet.push(dataSe);
	    	}
			
			if(cashAndBankCreditor.length!=0){
				for(var i=0; i<cashAndBankCreditor.length; i++){
					amount = cashAndBankCreditor[i];
					dataSe=[];
					dataSe.push(i+2);
					dataSe.push(amount.name);
					dataSe.push((amount.debit + amount.credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
					
					dataSet.push(dataSe);
					
					total_liabilities_debit=total_liabilities_debit+(amount.debit);
					total_liabilities_credit=total_liabilities_credit+(amount.credit);
					value_creditor=i+2;
				}
			}
			total_liabilities=total_liabilities+(total_liabilities_debit+total_liabilities_credit);
	    	
	    	dataSe=[];
	    	dataSe.push(value_creditor+1);
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
    function fun2() {
    	
    	if(pl_credit>pl_debit){
    		total_liabilities=pl_credit-pl_debit+creditors;
    		dataSe=[];
        	dataSe.push(1);
        	dataSe.push('Net Profit');
        	dataSe.push((pl_credit-pl_debit).toFixed(2));
        	
        	dataSet2.push(dataSe);
    	}else{
    		total_liabilities=pl_debitpl_credit-creditors;
    		dataSe=[];
    		dataSe.push(1);
        	dataSe.push('Net Loss');
        	dataSe.push((pl_debit-pl_credit).toFixed(2));
        	
        	dataSet2.push(dataSe);
    	}
    	
    	dataSe=[];
    	dataSe.push(2);
    	dataSe.push('Creditors');
    	dataSe.push((creditors).toFixed(2));
    	
    	dataSet2.push(dataSe);
    	
        $('#example2').DataTable( {
            data: dataSet2,
            destroy: true,
            "bSort" : false,
            "bPaginate": false,
            "bInfo": false,
            "bFilter": false,
            columns: [
    			{ title: "S.No" },
    			{ title: "Liabilities" },
    			{ title: "Amount" }
            ]
        } );
        
        $("#example2").append('<tfoot><th></th><th></th><th>'+(total_liabilities).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>');
    }
    
   	
	
		
//	$("#example").append('<br><table><thead><tr><th>S.No</th>Liabilities<th></th><th>Amount</th><tr></thead></table>');



