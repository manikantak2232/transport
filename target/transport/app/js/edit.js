var table = "";
var table_rows = "";
var inward= "";
var tables = "";
var res = "";
//var inwardDetails = "";
var array = [];
var subArray = [];
var editor;


	$(document).ready(function() {
		var requestData = new Object();
		requestData.lower_date='2018-03-14';
		requestData.upper_date='2018-03-15';

		$.ajax({
			url: '/transport/accounts/payment/get',
			type: 'POST',
			dataType: 'json',
			data: JSON.stringify(requestData),
			contentType: 'application/json; charset=utf-8',
			success: function(data) {

				var inwardDetails=data.paymentDetails;
				alert(inwardDetails);
				editor = new $.fn.dataTable.Editor( {
					data : inwardDetails,
					idSrc:  'pk_payment_id',
					"i18n": {
						"edit": {
							"button": "Edit",
							"title":  "Update Product",
							"submit": "Update"
						}
					},

					table: "#example",
					"fields": [ 
						{
							label: "Date:",
							name:  "date",

						}, {
							label: "Voucher:",
							name:  "voucher"
						}, {
							label: "Cash and Bank Ac:",
							name:  "cash_and_bank_ac_name"
						}, {
							label: "Cheque No:",
							name:  "cheque_no"
						}, {
							label: "Customer Ac:",
							name:  "customer_ac_name"
						},
						{
							label: "Amount:",
							name:  "amount"
						}, {
							label: "Narration:",
							name:  "narration"
						}
						]

				});

				// Activate an inline edit on click of a table cell
				$('#example').on( 'click', 'tbody td:not(:first-child)', function (e) {
					editor.inline( this );
				} );

				var openVals;
				alert(editor);

				editor.on( 'preSubmit', function ( e ) {

					var date= this.field( 'date' );
					var voucher = this.field( 'voucher' );
					var cash_and_bank_ac_name = this.field( 'cash_and_bank_ac_name' );
					var cheque_no = this.field( 'cheque_no' );
					var customer_ac_name = this.field( 'customer_ac_name' );
					var amount = this.field( 'amount' );
					var narration = this.field( 'narration' );

					// Only validate user input values - different values indicate that
					// the end user has not entered a value

					if ( ! date.val() ) {
						date.error( 'date is required' );
					}
					if ( ! voucher.val() ) {
						voucher.error( 'Voucher is required' );
					}
					if ( ! cash_and_bank_ac_name.val() ) {
						cash_and_bank_ac_name.error( 'Cash and bank ac is required' );
					}
					if ( ! cheque_no.val() ) {
						cheque_no.error( 'cheque no is required' );
					}
					if ( ! customer_ac_name.val() ) {
						customer_ac_name.error( 'Customer ac is required' );
					}
					if ( ! amount.val() ) {
						amount.error( 'amount is required' );
					}
					if ( ! narration.val() ) {
						narration.error( 'narration is required' );
					}

					// If any error was reported, cancel the submission so it can be corrected
					if ( this.inError() ) {
						return false;
					}

					openVals = editor.get();	
					var requestData = new Object();
					requestData.pk_payment_id = rowData.pk_payment_id;
					requestData.date =openVals.date;

					requestData.voucher = openVals.voucher;
					requestData.cash_and_bank_ac_name=openVals.cash_and_bank_ac_name;
					requestData.cheque_no = openVals.cheque_no;
					/*alert(requestData.quantity);*/
					requestData.customer_ac_name = openVals.customer_ac_name;
					requestData.amount = openVals.amount;
					requestData.narration = openVals.narration;
					requestData.remarks = '';
					requestData.w_b_no = '';


					$.ajax({
						url: '/transport/accounts/payment/update',
						type: 'POST',
						dataType: 'json',
						data: JSON.stringify(requestData),
						contentType: 'application/json; charset=utf-8',
						success: function(response) {

							var	 mes = response.message;
							alert(mes)

							return mes;
						}

					});

				} ) 

				//    alert(editor);
				var rowData;
				var table =$('#example').DataTable( {
					dom: "Bfrtip",


					data : inwardDetails,

					"columns": [
						{
							'data': 'null',
							defaultContent: '',
							className: 'select-checkbox',
							orderable: false
						},

						{ 'data': 'date' },
						{ 'data': 'voucher' },
						{ 'data': 'cash_and_bank_ac_name' },
						{ 'data': 'cheque_no' },
						{ 'data': 'customer_ac_name'},
						{ 'data': 'amount'},
						{ 'data': 'narration' }

						], 
						order: [ 1, 'asc' ],
						select: {
							style:    'os',
							selector: 'td:first-child'
						},

						buttons: [
							{ extend: "create", editor: editor },
							{ extend: "edit",  
								editor: editor,
								formTitle: function ( editor, dt ) {
									// Get the data for the row and use a property from it in the
									// form title
									rowData = dt.row({selected:true}).data();
									//    alert(rowData.id);
									return 'Editing data for '+rowData.date;  
								}
							}
							]
				});

			},
			error: function(error) {
				console.log(error);
			} 
		});
	});

