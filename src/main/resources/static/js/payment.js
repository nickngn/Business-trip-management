var removedPlans = new Array();
$(document).on("click", "button.remove", function() {

	$(this).closest("tr").remove();
	
	var id = $(this).closest("tr").attr("id");
	removedPlans.push(id);
	console.log("remove :" + id);
	
	for (var x = 0; x < $(".incurred-finance-count").length; x++) {
		$(".incurred-finance-count:eq(" + (x) + ")").text(x + 1);
	}
	
	var totalFinance = 0;
	for (var x = 0; x < $(".finance").length; x++) {
		totalFinance += $(".finance:eq(" + (x) + ")").text();
		
	}
	$("#total-finance").text(totalFinance);
});

$("#submit").on("click", function() {
	var planId = $("#planId").val(),
	csrf = $("meta[name='_csrf']").attr("content");
	
	$.ajax({
		url: "/payment/" + planId,
		method: "post",
		headers: {
			"X-CSRF-TOKEN" : csrf,
			"Content-type": "application/json",
			"datatype": "json"
		},
		data: JSON.stringify(removedPlans),
		success: function() {
			location.reload();
		}
	});
});