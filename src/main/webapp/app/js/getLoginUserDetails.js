var association_name;
var manager_type;
function userDetails(){
	$(document).ready(function() {
		$.ajax({
			url: '/transport/login/user/details/get',
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			success: function(response) {
				var userDetails=response.userDetails;
				association_name=userDetails.association_name;
				manager_type=userDetails.manager_type;
				factory(manager_type);

			},
			error: function(error) {
				console.log(error);
			}
		});
	});
}