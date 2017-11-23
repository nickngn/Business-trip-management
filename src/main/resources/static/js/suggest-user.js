$(document).on("keyup", ".suggest-username", function() {
			var inputValue = $(this).val();
			var target = $(this);
			target.innerHtml = "";
			if(inputValue !== "") {
				$.ajax({
					url: "/api/users/suggested-users/" + inputValue,
					target: target,
					success: function(result) {
						$("#user-suggest-datalist").text("");
						for(var i = 0; i < result.numberOfElements; i++) {
							$("#user-suggest-datalist").append("<option>" + result.content[i].username + "</option>");
						}
					}
				});
			}
		});