function fnExcelReport()
{
      var tab_text="<table border='2px' style='width:100%;border: 1px solid black;border-collapse:collapse;text-align:center; line-height: 200%;'><tr style='width:100%;border: 1px solid black;border-collapse:collapse;text-align:center; line-height: 200%;'>";
      var textRange; var j=0;
     /* tab = document.getElementById('factories');*/ // id of table
      tab=document.getElementById('dataTab');
    
      for(j = 0 ; j < tab.rows.length ; j++) 
      {     
            tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
            //tab_text=tab_text+"</tr>";
      }

      tab_text=tab_text+"</table>";


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