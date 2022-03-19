function logout(){
	$.ajax({
		url: '/transport/log/out/user',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		success: function(response) {

		},
		error: function(error) {
			console.log(error);
		}
	});
}