function fnExcelReport1()
{
      var tab_text="<table width='80%' align='center' style='border: 1px solid black;border-collapse:collapse;text-align:center;line-height: 200%;>" +
      		"<tr style='border: 1px solid black;'><td colspan='9' style='border: 1px solid black;'><h2 align='center' style='padding: 2px;margin: 0;'>SRI VAISHNAVI TRANSPORT COMPANY</h2></td></tr>" +
      		"<tr><td colspan='6'><h4 align='center' style='padding: 2px;margin: 0;'>STOCK TRANSFER :: TRANSPORT BILL NO :&nbsp;&nbsp;&nbsp;</h4></td><td colspan='3'><h4 align='left' style='padding: 2px;margin: 0;'>Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></td></tr>" +
      		"<tr >";
      var textRange; var j=0;
     /* tab = document.getElementById('factories');*/ // id of table
      tab=document.getElementById('factories');
    
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