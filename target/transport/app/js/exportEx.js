function fnExcelReport()
{
	var textRange; var j=0;
	
	tab=document.getElementById('dataTab');
	tab2=document.getElementById('outsideDispatch');
	
	if(rows!=''& other_rows==''){
		 var tab_text="<h2 style='text-align: center; text-decoration: underline;'>Svtc Report</h2><table border='2px'><tr bgcolor='#87AFC6'>";
	       
	      for(j = 0 ; j < tab.rows.length ; j++) 
	      {     
	            tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	            //tab_text=tab_text+"</tr>";
	      }	
	      tab_text=tab_text+"</table>";
	}
	if(rows==''& other_rows!=''){
		 var tab_text2="<h2 style='text-align: center; text-decoration: underline;'>Other Report</h2></br></br><table border='2px'><tr bgcolor='#87AFC6'>";
         
	      for(j = 0 ; j < tab2.rows.length ; j++) 
	      {     
	    	  tab_text2=tab_text2+tab2.rows[j].innerHTML+"</tr>";
	            //tab_text=tab_text+"</tr>";
	      }
	      tab_text=tab_text2+"</table>";
	}
	if(rows!=''& other_rows!=''){
		 var tab_text="<h2 style='text-align: center; text-decoration: underline;'>Svtc Report</h2><table border='2px'><tr bgcolor='#87AFC6'>";
	       
	      for(j = 0 ; j < tab.rows.length ; j++) 
	      {     
	            tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
	            //tab_text=tab_text+"</tr>";
	      }
	      
	      var tab_text2="<h2 style='text-align: center; text-decoration: underline;'>Other Report</h2></br></br><table border='2px'><tr bgcolor='#87AFC6'>";
	         
	      for(j = 0 ; j < tab2.rows.length ; j++) 
	      {     
	    	  tab_text2=tab_text2+tab2.rows[j].innerHTML+"</tr>";
	            //tab_text=tab_text+"</tr>";
	      }
	      tab_text=tab_text+"</table></br></br>"+tab_text2+"</table>";
	} 


      var ua = window.navigator.userAgent;
      var msie = ua.indexOf("MSIE "); 

      if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
      {
         txtArea1.document.open("txt/html","replace");
         txtArea1.document.write(tab_text);
         txtArea1.document.close();
         txtArea1.focus(); 
         sa=txtArea1.document.execCommand("SaveAs",true,"Global View Task.xls");
      }  
      else //other browser not tested on IE 11
         {
    	  sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
    	  return false;
         }
     
    //  window.stop();
}