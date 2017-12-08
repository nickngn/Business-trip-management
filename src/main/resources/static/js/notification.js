$("#form").on("submit", function() {
	var notification = {
		name: $("#name").val(),
		personQuantity: $("#personQuantity").val(),
		location: $("#location").val(),
		startDate: $("#startDate").val(),
		endDate: $("#endDate").val(),
		description: $("#description").val(),
		attachment: $("#file")[0].files[0].name
	},
	
	csrf = $("meta[name='_csrf']").attr("content");
	$.ajax({
		url: "/notifications/add",
		method: "put",
		headers: {
			"Content-Type" : "application/json",
			"X-CSRF-TOKEN" : csrf
		},
		data: JSON.stringify(notification),
		success: function() {
			location.reload();
		},
		error: function(response){
			console.log(response);
			var responseJson = response.responseJSON;
			var errors = "";
			for(var i = 0; i < responseJson.length; i++) {
				errors += "<p class='text-danger' >" + responseJson[i].defaultMessage + "</p>" ;
			}
			$("#errors").html(errors);
		}
	});
});