var dataSet=[];
var dataSe=[];
var total_expenditure=0;
var total_income=0;


function getTruckProfitAndLoss(){  
//	$('#loading').show();
	dataSet=[];
	dataSe=[];
	total_expenditure=0;
	total_income=0;
	document.getElementById("example").deleteTFoot();
	$(".overlay").show();
	var requestData = new Object();
	requestData.truck_number=$('#truck_number').val();
	requestData.lower_date=$('#lower_date').val();
	requestData.upper_date=$('#upper_date').val();
	
	$.ajax({
		url: '/transport/accounts/truck/pl/get',
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(requestData),
		contentType: 'application/json; charset=utf-8',
		success: function(response) {	
			
			$(".overlay").hide();
			truckProfitAndLoss = response.truckProfitAndLoss;
			
			for(var i=0;truckProfitAndLoss.length> i; i++){
				
				dataSe=[];
				dataSe.push('');
				dataSe.push(truckProfitAndLoss[i].account_name);
				dataSe.push(truckProfitAndLoss[i].amount );
				
				dataSet.push(dataSe);
				
				if(truckProfitAndLoss[i].amount_type=='expenditure'){
					if(truckProfitAndLoss[i].account_name!='transit advance'){
						total_expenditure+=truckProfitAndLoss[i].amount;
					}					
				}else{
					total_income+=truckProfitAndLoss[i].amount;
				}
			}
			
			fun();

		},
		error: function(error) {
			console.log(error);
		}
	});
}

function fun() {
	var message=($('#truck_number').val().toUpperCase()) +' P&L from '+GetFormattedDate($('#lower_date').val())+' to '+GetFormattedDate($('#upper_date').val());
	$("#example").append('<tfoot><tr><th></th><th></th><th></th></tr></tfoot>');
	 var t =  $('#example').DataTable( {
        data: dataSet,
        "footerCallback": function ( row, data, start, end, display ) {
			var api = this.api(), data;

			// Update footer
			if(total_income>total_expenditure){
				$( api.column( 2 ).footer() ).html(
					'Profit '+(total_income-total_expenditure).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") 
				);
			}else{
				$( api.column( 2 ).footer() ).html(
					'Loss '+(total_expenditure-total_income).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")
				);
			}

		},
        destroy: true,
        "bSort" : false,
     //   "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        dom: 'lBfrtip',
        buttons: [
        	{
                extend: 'excel',
                messageTop: message,
                footer: true,
           //     messageBottom: '<tfoot><th></th><th>'+(total_debit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th><th>'+(total_credit).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tfoot>'
            },
        	{
                extend: 'pdf',
                messageTop: message ,
                footer: true,
            },
            {
                extend: 'print',
                messageTop: message ,
                footer: true,
            }
        ],
        columns: [
			{ title: "S.No" },
			{ title: "Account" },
            { title: "Amount" ,render: $.fn.dataTable.render.number( ',', '.', 2 )}
        ],
        "order": [[ 1, 'asc' ]]
       
    } );
	 
	 t.on( 'order.dt search.dt', function () {
	        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	            t.cell(cell).invalidate('dom');
	        } );
	    } ).draw();
    
/*    if(total_income>total_expenditure){    	
		$("#example").append('<tfoot><tr><th></th><th></th><th>Profit '+(total_income-total_expenditure).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tr></tfoot>');		
	}else{
		$("#example").append('<tfoot><tr><th></th><th></th><th>Loss '+(total_expenditure-total_income).toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,")+'</th></tr></tfoot>');
	}*/
    $('#truck_profit_and_loss').html(message);

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
