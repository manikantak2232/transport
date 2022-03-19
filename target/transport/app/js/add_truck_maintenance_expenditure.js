function currentDate() {
	  var today = new Date();
	  var dd = today.getDate();
	  var mm = today.getMonth()+1; //January is 0!

	  var yyyy = today.getFullYear();
	  if(dd<10){
	      dd='0'+dd;
	  } 
	  if(mm<10){
	      mm='0'+mm;
	  } 
	  var today =yyyy+'/'+mm+'/'+ dd;
	  $("#date").val(today);

	  }
var xhr;
/*function addTruckMaintenanceExpenditure() {
		var requestData = new Object();
		
		requestData.date=$('#date').val();
		requestData.fk_truck_id=$('#truck_id').val();
		requestData.item_names=$('#item_names').val();
		requestData.cost=$('#cost').val();
		requestData.remarks=$('#remarks').val();
	
        $.ajax({
        	url: '/transport/trucks/maintenance/expenditure/add',
			type: 'POST',
            dataType: 'json',
            data: JSON.stringify(requestData),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {	            		 	         
            	$("#Message").html("Inserted Successfully");
            },
            error: function(error) {
                console.log(error);
            }
        });
    };*/
    
    $(function () {
    	var var1;
    	var var2; 
        $("#btnAdd").bind("click", function () {
            var div = $("<div />");
            div.html(GetDynamicTextBox(""));
            $("#TextBoxContainer").append(div);
           
        });
        $("#insert").bind("click", function () {
            var values = "";
            $("input[name=DynamicTextBox]").each(function () {
                values += $(this).val() + "\n";
            });
            var1=values+$('#item_name').val();
        });
        
        $("#insert").bind("click", function () {
            var values1 = "";
            $("input[name=DynamicTextBox1]").each(function () {
                values1 += $(this).val() + "\n";
            });
            var2=values1+$('#item_cost').val();
        });

        $("body").on("click", ".remove", function () {
            $(this).closest("div").remove();
        });
        
        $('#insert').click(function () {
        	if ( xhr && xhr.readyState > 0 && xhr.readyState < 4 ) {
                xhr.abort();    
            }
    		var requestData = new Object();
    		requestData.date=$('#date').val();
    		requestData.fk_truck_id=$('#truck_id').val();
    		requestData.item_name= var1;
    		requestData.item_cost= var2;
    		requestData.remarks=$('#remarks').val();
    		requestData.expenditure_type=$('#expenditure_type').val();
    	
            xhr=$.ajax({
            	url: '/transport/trucks/maintenance/expenditure/add',
    			type: 'POST',
                dataType: 'json',
                data: JSON.stringify(requestData),
                contentType: 'application/json; charset=utf-8',
                success: function(response) {	            		 	         
                	$("#Message").html(response.message);
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
        
    });

    function GetDynamicTextBox(value) {
        return '<input name = "DynamicTextBox"  placeholder="Enter Item Name" type="text" value = "' + value + '" />&nbsp;' +
        		'<input name = "DynamicTextBox1" placeholder="Enter Cost" type="text" value = "' + value + '" />&nbsp;' +
                '<input type="button" value="Remove" class="remove" /><br><br>'
    }
    