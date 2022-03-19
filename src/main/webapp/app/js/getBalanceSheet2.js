var dataSet=[];
var dataSet2=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var debtors=0;
var value=0;
var total_assests=0;
var total_liabilities=0;
var creditors=0;
var net_profit=0;
var net_loss=0;
var pl_debit=0;
var pl_credit=0;

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
			
			cashAndBank = response.cashAndBank;
	//		dataSet.push(debtors);
			for(var i=0; i<cashAndBank.length; i++){
				amount = cashAndBank[i];
				dataSe=[];
				dataSe.push(i+1);
				dataSe.push(amount.name);
				dataSe.push(amount.debit + amount.credit);
				
				dataSet.push(dataSe);
				
				total_debit=total_debit+(amount.debit);
				total_credit=total_credit+(amount.credit);
				value=value+i+1;
			}
			total_assests=(total_debit+total_credit);
			dataSe=[];
			dataSe.push(value+1);
			dataSe.push('Debtors');
			dataSe.push(debtors);
			
			dataSet.push(dataSe);
			
			fun();
			fun2();

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
			{ title: "S.No" },
			{ title: "Assets" },
			{ title: "Amount" }
        ]
    } );
    
    $("#example").append('<tfoot><th></th><th></th><th>'+(total_assests+debtors)+'</th></tfoot>');	
  
}
    function fun2() {
    	
    	if(pl_credit>pl_debit){
    		total_liabilities=pl_credit-pl_debit+creditors;
    		dataSe=[];
        	dataSe.push(1);
        	dataSe.push('Net Profit');
        	dataSe.push(pl_credit-pl_debit);
        	
        	dataSet2.push(dataSe);
    	}else{
    		total_liabilities=pl_debitpl_credit-creditors;
    		dataSe=[];
    		dataSe.push(1);
        	dataSe.push('Net Loss');
        	dataSe.push(pl_debit-pl_credit);
        	
        	dataSet2.push(dataSe);
    	}
    	
    	dataSe=[];
    	dataSe.push(2);
    	dataSe.push('Creditors');
    	dataSe.push(creditors);
    	
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
        
        $("#example2").append('<tfoot><th></th><th></th><th>'+total_liabilities+'</th></tfoot>');
    }
    
   	
	
		
//	$("#example").append('<br><table><thead><tr><th>S.No</th>Liabilities<th></th><th>Amount</th><tr></thead></table>');



