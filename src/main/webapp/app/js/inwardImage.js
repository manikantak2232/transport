var truckNumberImageBytes=null;
var truckNumberImageType=null;
var workDoneImageBytes=null;
var workDoneImageType=null;

var openFiletruckNumberUrl = function(event) {
    var input = event.target;

    var reader_truck_number = new FileReader();
    reader_truck_number.onload = function(){
      var truckNumberDataURL = reader_truck_number.result;
      var truck_Number = document.getElementById('truckNumberUrl');
      truck_Number.src = truckNumberDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      truckNumberImageBytes = truckNumberDataURL.split(";base64,")[1] ;
      truckNumberImageType = truckNumberDataURL.split(";base64,")[0].split("data:image/")[1] ;

    };
    reader_truck_number.readAsDataURL(input.files[0]);
  };
  
  var openFileworkDoneUrl = function(event) {
	    var input = event.target;

	    var reader_work_done = new FileReader();
	    reader_work_done.onload = function(){
	      var workDoneDataURL = reader_work_done.result;
	      var work_done = document.getElementById('workUrl');
	      work_done.src = workDoneDataURL;
	      
//	      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
	      workDoneImageBytes = workDoneDataURL.split(";base64,")[1] ;
	      workDoneImageType = workDoneDataURL.split(";base64,")[0].split("data:image/")[1] ;

	    };
	    reader_work_done.readAsDataURL(input.files[0]);
	  };