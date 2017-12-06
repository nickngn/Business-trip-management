$("#add-finance-incurred-plan").on("click", function(){
	var planId = $("#planId").val(),
		_fee = $("#fee").val(),
		_cost = $("#cost").val(),
		_tax = $("#tax").val(),
		plan = {
			fee : $("#fee").val(),
			cost: $("#cost").val(),
			tax: $("#tax").val()
		},
		csrf = $("meta[name='_csrf']").attr("content");
	$.ajax({
		url: planId + "/finance-incurred-plans",
		type:"PUT",
		headers: {
			"X-CSRF-TOKEN" : csrf,
			"Content-type": "application/json",
			"datatype": "json" 
		},
		data:JSON.stringify(plan),
		success: function(response){
			location.reload();
		},
		error: function(response){
			console.log(response);
			var responseJson = response.responseJSON;
			var errors = "";
			for(var i = 0; i < responseJson.length; i++) {
				errors += "<p class='text-danger' >" + responseJson[i].defaultMessage + "</p>" ;
			}
			$("#errors-incurred-finance").html(errors);
		}
	});
});

$("#add-personel-incurred-plan").on("click", function(){
	var planId = $("#planId").val(),
		_username = $("#username").val(),
		_action = $("#action").val(),
		_date = $("#date").val(),
		_description = $("#description").val(),
		_user = {username : _username},
		csrf = $("meta[name='_csrf']").attr("content");
	var personelIncurredPlan = {
			user: _user,
			action: _action,
			date: _date,
			description: _description
	}
	$.ajax({
		url: planId + "/personel-incurred-plans",
		type:"PUT",
		headers: {
			"X-CSRF-TOKEN" : csrf,
			"Content-type": "application/json",
			"datatype": "json"
		},
		data: JSON.stringify(personelIncurredPlan),
		success: function(response){
			location.reload();
		},
		error: function(response) {
			console.log(response);
			var responseJson = response.responseJSON;
			var errors = "";
			for(var i = 0; i < responseJson.length; i++) {
				errors += "<p class='text-danger' >" + responseJson[i].defaultMessage + "</p>" ;
			}
			$("#errors-incurred-personel").html(errors);
		}
		
	});
});