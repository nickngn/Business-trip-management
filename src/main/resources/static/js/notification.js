$("#add-notification").on("click", function() {
	var notification = {
		name: $("#name").val(),
		personQuantity: $("#personQuantity").val(),
		location: $("#location").val(),
		startDate: $("#startDate").val(),
		endDate: $("#endDate").val(),
		description: $("#description").val()
	},
	
	csrf = $("meta[name='_csrf']").attr("content");
	console.log(notification);
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
		}
	});
});