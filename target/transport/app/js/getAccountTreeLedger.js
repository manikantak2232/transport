var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var ledger_name='Ledger';
function getLedger(account_name){  
//	$('#loading').show();
	ledger_name=account_name;
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.account_name=account_name;
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();
	
	$.ajax({
		url: '/transport/accounts/ledger/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			//	$('#loading').hide();
			$(".overlay").hide();
			ledgerDetails = response.ledgerDetails;

			for(var i=0; i<ledgerDetails.length; i++){
				ledger = ledgerDetails[i];
				dataSe=[];
				dataSe.push(i+1);
				dataSe.push(ledger.date);
				dataSe.push(ledger.voucher);
				dataSe.push(ledger.account);
				dataSe.push((ledger.debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
				dataSe.push((ledger.credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));			
				dataSe.push(ledger.narration);
				
				dataSet.push(dataSe);
				
				total_debit=total_debit+(ledger.debit);
				total_credit=total_credit+(ledger.credit);
				
			}
			
			fun();

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
        columns: [
			{ title: "S.No" },
			{ title: "Date" },
			{ title: "Voucher" },
			{ title: "Account" },
            { title: "Debit" },
            { title: "Credit" },
            { title: "Narration"}
        ]
    } );
    
    if(total_debit>total_credit){    	
		$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th>'+(total_debit-total_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th></th><th></th></tfoot>');		
	}else{
		$("#example").append('<tfoot><th></th><th></th><th></th><th></th><th></th><th>'+(total_credit-total_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th></th></tfoot>');
	}
    $('#ledger_name').html(ledger_name);
    
   
    $(function(){
    	  $('#uld li').hide()
    	})

 
/*    
    $(document).ready(function(e){
        
        var children = $(this).parent('li.parent_li').find(' > ul > li');
  	  children.hide('fast');
  	    $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
  	   // i.stopPropagation(); 
  	  e.stopPropagation();
    });*/
    
 
    

};

