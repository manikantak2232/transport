/*function fnExportHTML(tableNames,headerbdcolor, filename){
	if(tableNames.trim()===""){
		alert('hai');
		return;
	}
	if(headerbdcolor.trim()===""){
		headerbdcolor="#0000ff";
	}
	if(filename.trim()===""){
		filename="exportedData";
	}
	
	if(window.navigator.userAgent.indexof("MSIE ")>0 || !!window.navigator.userAgent.match(/Trident.*rv\:11\./))
		{
			exportIf.document.open("txt/html","replace");
			exportIf.document.write(export_data);
			exportIf.document.close();
			exportIf.focus();
			
			sa=exportIf.document.execCommand("SaveAs",true,filename+" .xls");
		}
	else{
			sa=window.open('data:application/vnd.ms-excel,'+encodeURIComponent(export_data));
	}
}


var export_data="";
var arrTableNames=tableNames.split("|");

if(arrTableNames.length>0){
	for(var i=0; i<arrTableNames.length ; i++){
		export_data+="<table border='2px'><tr bgcolor='"+headerbdcolor+"'>";
		objTable=document.getElementById(arrTableNames[i]);
		
		if(objTable === undefined){
			alert("not found");
//			return;
		}
		for(var j=0; j<objTable.rows.length; j++){
			export_data+=objTable.rows[j].innerHtml+"</tr>";
		}
		export_data+="</table>";
	}
}*/