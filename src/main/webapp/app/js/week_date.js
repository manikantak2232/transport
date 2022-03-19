var week_start_date;
var week_last_date;

function weekDates() {
    
    var curr = new Date; // get current date

    var firstDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+1));
    var lastDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+7));
    
    var month = (firstDay.getMonth() + 1);               
    var day = firstDay.getDate();
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var first_day = firstDay.getFullYear() + '-' + month + '-' + day;
    
    var lmonth = (lastDay.getMonth() + 1);               
    var lday = lastDay.getDate();
    if(lmonth < 10) 
    	lmonth = "0" + lmonth;
    if(lday < 10) 
    	lday = "0" + lday;
    var last_day = lastDay.getFullYear() + '-' + lmonth + '-' + lday;
    
/*    $('#lower_date').val(first_day);
    $('#upper_date').val(last_day);*/
    
    week_start_date=first_day;
    week_last_date=last_day;
    
};