var outwardTruckNumberImageBytes=null;
var outwardTtruckNumberImageType=null;
var outwardWorkDoneImageBytes=null;
var outwardWorkDoneImageType=null;

var openFileoutwardTruckNumberUrl = function(event) {
    var input = event.target;

    var reader_outward_truck_number = new FileReader();
    reader_outward_truck_number.onload = function(){
      var outwardTruckNumberDataURL = reader_outward_truck_number.result;
      var outward_truck_Number = document.getElementById('truckNumberUrl');
      outward_truck_Number.src = outwardTruckNumberDataURL;
      
//      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
      outwardTruckNumberImageBytes = outwardTruckNumberDataURL.split(";base64,")[1] ;
      outwardTtruckNumberImageType = outwardTruckNumberDataURL.split(";base64,")[0].split("data:image/")[1] ;

    };
    reader_outward_truck_number.readAsDataURL(input.files[0]);
  };
  
  var openFileoutwardWorkDoneUrl = function(event) {
	    var input = event.target;

	    var reader_outward_work_done = new FileReader();
	    reader_outward_work_done.onload = function(){
	      var outwardWorkDoneDataURL = reader_outward_work_done.result;
	      var outward_work_done = document.getElementById('workUrl');
	      outward_work_done.src = outwardWorkDoneDataURL;
	      
//	      document.getElementById('fitnessImageBytes').innerHTML = fitnessDataURL.split(";base64,")[1] ;
	      outwardWorkDoneImageBytes = outwardWorkDoneDataURL.split(";base64,")[1] ;
	      outwardWorkDoneImageType = outwardWorkDoneDataURL.split(";base64,")[0].split("data:image/")[1] ;

	    };
	    reader_outward_work_done.readAsDataURL(input.files[0]);
	  };