var dataSet=[];
var dataSe=[];
var total_debit=0;
var total_credit=0;
var opening_bal_credit=0;
var account_credit_balance=0;
var account_debit_balance=0;
var opening_bal_debit=0;
var opening_bal=0;
var total=0;

function fu(ac_name){
	alert(ac_name);
}

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"date-uk-pre": function ( a ) {
	    var ukDatea = a.split('-');
	    return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	},

	"date-uk-asc": function ( a, b ) {
	    return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"date-uk-desc": function ( a, b ) {
	    return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
	} );

function getLedger(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_debit=0;
	total_credit=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.account_name=$('#account_name').val();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();

	$.ajax({
		url: '/transport/accounts/ledger/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	

			$(".overlay").hide();
			ledgerDetails = response.ledgerDetails;
			opening_bal_credit = response.opening_bal_credit;
			account_credit_balance = response.account_credit_balance;
			account_debit_balance = response.account_debit_balance;
			opening_bal_debit = response.opening_bal_debit;

			total_dr=opening_bal_debit+account_debit_balance;
			total_cr=opening_bal_credit+account_credit_balance;

			if(total_dr>total_cr){
				opening=total_dr-total_cr;
				//	opening_bal=total_dr;

				dataSe=[];
				dataSe.push('');
				dataSe.push('');
				dataSe.push('');
				dataSe.push('Opening Bal');
				dataSe.push((total_dr-total_cr));
				dataSe.push('');			
				dataSe.push('');
				dataSe.push('');
				dataSet.push(dataSe);
				total_debit=total_debit+(total_dr-total_cr);

			}else{
				opening=total_cr-total_dr;
				//	opening_bal=total_cr;

				dataSe=[];
				dataSe.push('');
				dataSe.push('');
				dataSe.push('');
				dataSe.push('Opening Bal');
				dataSe.push('');
				dataSe.push((total_cr-total_dr));			
				dataSe.push('');
				dataSe.push('');

				dataSet.push(dataSe);
				total_credit=total_credit+(total_cr-total_dr);

			}

			for(var i=0; i<ledgerDetails.length; i++){
				ledger = ledgerDetails[i];
				if(total_dr!=0){
					dataSe=[];
					dataSe.push('');
					dataSe.push(ledger.date);
					dataSe.push(ledger.voucher);
					dataSe.push(ledger.account);
					dataSe.push((ledger.debit));
					dataSe.push((ledger.credit));			
					dataSe.push(ledger.narration);
					dataSe.push(ledger.truck_no);
					dataSet.push(dataSe);
					//			opening=opening+(ledger.debit)-(ledger.credit)
				}else{
					dataSe=[];
					dataSe.push('');
					dataSe.push(ledger.date);
					dataSe.push(ledger.voucher);
					dataSe.push(ledger.account);
					dataSe.push((ledger.debit));
					dataSe.push((ledger.credit));			
					dataSe.push(ledger.narration);
					dataSe.push(ledger.truck_no);
					dataSet.push(dataSe);
					//			opening=opening-(ledger.debit)+(ledger.credit)
				}

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
	var message=($('#account_name').val().toUpperCase())  + '<br><br>' + ' Ledger from '+GetFormattedDate($('#lower_date').val())+' to '+GetFormattedDate($('#upper_date').val())+ '<br>' ;
	$("#example").append('<tfoot><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr></tfoot>');
	
	var t =  $('#example').DataTable( {
		data: dataSet,

		"footerCallback": function ( row, data, start, end, display ) {
			var api = this.api(), data;

			// Remove the formatting to get integer data for summation
			var intVal = function ( i ) {
				return typeof i === 'string' ?
						i.replace(/[\$,]/g, '')*1 :
							typeof i === 'number' ?
									i : 0;
			};
			
			$( api.column( 4 ).footer() ).html('');
			$( api.column( 5 ).footer() ).html('');

			// Total over all pages
			debitTotal = api
			.column( 4 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			creditTotal = api
			.column( 5 )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );
			
			if(debitTotal>creditTotal){
				total=((debitTotal-creditTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"))+' Dr';
			}else{
				total=((creditTotal-debitTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"))+' Cr';
			}

			// Total over this page
			pageDebitTotal = api
			.column( 4, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			pageCreditTotal = api
			.column( 5, { page: 'current'} )
			.data()
			.reduce( function (a, b) {
				return intVal(a) + intVal(b);
			}, 0 );

			// Update footer
			if(pageDebitTotal>pageCreditTotal){
				$( api.column( 4 ).footer() ).html(
					(pageDebitTotal-pageCreditTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") 
				);
			}else{
				$( api.column( 5 ).footer() ).html(
					(pageCreditTotal-pageDebitTotal).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
				);
			}
			
			$( api.column( 6 ).footer() ).html(total);

		},
		destroy: true,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		dom: 'lBfrtip',
		/*"bSort" : false,*/
		buttons: [
        	{
                extend: 'excel',
                title: ($('#account_name').val().toUpperCase()) +' Ledger from '+GetFormattedDate($('#lower_date').val())+' to '+GetFormattedDate($('#upper_date').val()),
                footer: true,
                customize: function( xlsx ) {
                    var sheet = xlsx.xl.worksheets['sheet1.xml'];
                    $('row[r=1] c', sheet).attr( 's', '7' );
                }
        	},
        	{
                extend: 'pdfHtml5',
                footer: true,
            //    messageTop: 'hai this is Nani',
                title : message,
                customize: function(doc) {
                    doc.styles.title = {
                  //    color: 'red',
                      fontSize: '12',
                  //    background: 'blue',
                      alignment: 'center'
                    }   
                  }, 
            },
            {
                extend: 'print',
                title:message,
                footer: true,
                customize: function ( win ) {
                    $(win.document.body).find('h1').css('font-size', '15pt');
                    $(win.document.body).find('h1').css('text-align', 'center'); 
                },
                
            }
        ],
			columns: [
				{ title: "S.No" },
				{ title: "Date","sType": "date-uk" },
				{ title: "Voucher" },
				{ title: "Account" },
				{ title: "Debit" ,render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "Credit" , render: $.fn.dataTable.render.number( ',', '.', 2 )},
				{ title: "Narration"},
				{ title: "Truck No"}
				],
				"order": [[ 1, 'asc' ]]

	} );

	t.on( 'order.dt search.dt', function () {
		t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
			cell.innerHTML = i+1;
			t.cell(cell).invalidate('dom');
		} );
	} ).draw();	

	$('#ledger_name').html(message);

};

function GetFormattedDate(date) {
    
    var now = new Date(date);
    var month = (now.getMonth() + 1);               
    var day = now.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    return day + '-' + month + '-' + now.getFullYear();
}


