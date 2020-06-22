var thresholdQuantity = 0;
// add new threshold
$("#threshold-add").on("click", function() {
	thresholdQuantity ++; console.log(thresholdQuantity);
	var html = "<tr>"
		+ "<td class='threshold-count'>" + thresholdQuantity + "</td>"
		+ "<td><input type='text' class='threshold-input-fee form-control' placeholder='Chi mục'  name='thresholds[" + (thresholdQuantity - 1) + "].fee'></td>"
		+ "<td><input type='number' class='threshold-input-cost form-control' name='thresholds[" + (thresholdQuantity - 1) + "].amount'></td>"
		+ "<td><button type='button' class='remove-threshold btn btn-warning'>Xóa</button></td>"
		+ "</tr>";
	console.log(html);
	$("#threshold").append(html);
});
// remove a threshold

$(document).on("click", "button.remove-threshold", function(){
	
	thresholdQuantity --; console.log(thresholdQuantity);
	
	$(this).closest("tr").remove();		//remove parent row of remove button
	// console.log("Length: " + $(".salary_no").length);
	for(var x = 0; x < $(".threshold-count").length; x ++) {
		// console.log($(".salary_no:eq(" + (x) + ")"));
		$(".threshold-count:eq(" + (x) + ")").text(x + 1);
		$(".threshold-input:eq(" + (x) + ")").attr('amount', "thresholds[" + x + "].amount");
	}
});

$("#threshold-save").on("click", function() {
	var thresholds = {
		thresholds: $(".threshold-input").val()
	},
	
	csrf = $("meta[name='_csrf']").attr("content");
	console.log(csrf);
	$.ajax({
		url: "/thresholds",
		method: "post",
		headers: {
			"Content-Type" : "application/json",
			"X-CSRF-TOKEN" : csrf
		},
		data: JSON.stringify(thresholds),
		success: function() {

			console.log(csrf);
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
