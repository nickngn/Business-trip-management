		var personelQuantity = 1;
		var financeQuantity = 1;
		
		//add new salary row
		$("#personel-add").on("click", function() {
			personelQuantity ++; console.log(personelQuantity);
			var html = "<tr><td class='personel-count'>" + personelQuantity +"</td>" 
							+ "<td><input type='text' list='user-suggest' class='personel-input form-control' " 
							+ " name = 'personelPlanList[" + (personelQuantity - 1) + "].user.username' placeholder='Tên'/></td>" 
							+ "<td><button type='button' class='remove-personel btn btn-warning'>Xóa</button></td>" +
							"</tr>"
			$("#personel").append(html);
		});
		
		$("#finance-add").on("click", function() {
			financeQuantity ++; console.log(financeQuantity);
			var html = "<tr>"
				+ "<td class='finance-count'>" + financeQuantity + "</td>"
				+ "<td><input type='text' class='finance-input-fee form-control' placeholder='Chi mục'  name='financePlanList[" + (financeQuantity - 1) + "].fee'></td>"
				+ "<td><input type='number' class='finance-input-cost form-control' placeholder='Chi phí' name='financePlanList[" + (financeQuantity - 1) + "].cost'></td>"
				+ "<td><input type='number' class='finance-input-tax form-control' placeholder='Thuế' name='financePlanList[" + (financeQuantity - 1) + "].tax'></td>"
				+ "<td><button type='button' class='remove-finance btn btn-warning'>Xóa</button></td>"
				+ "</tr>";
			console.log(html);
			$("#finance").append(html);
		});

		//remove a salary row
		$(document).on("click", "button.remove-finance", function(){
			
				financeQuantity --; console.log(financeQuantity);
				
				$(this).closest("tr").remove();	
				for(var x = 0; x < $(".finance-count").length; x ++) {
					// console.log($(".salary_no:eq(" + (x) + ")"));
					$(".finance-count:eq(" + (x) + ")").text(x + 1);
					$(".finance-input-fee:eq(" + (x) + ")").attr('name', "financePlanList[" + x + "].fee");
					$(".finance-input-cost:eq(" + (x) + ")").attr('name', "financePlanList[" + x + "].cost");
					$(".finance-input-tax:eq(" + (x) + ")").attr('name', "financePlanList[" + x + "].tax");
				}
			
		});
		
		$(document).on("click", "button.remove-personel", function(){
			
			personelQuantity --; console.log(personelQuantity);
			
			$(this).closest("tr").remove();		//remove parent row of remove button
			// console.log("Length: " + $(".salary_no").length);
			for(var x = 0; x < $(".personel-count").length; x ++) {
				// console.log($(".salary_no:eq(" + (x) + ")"));
				$(".personel-count:eq(" + (x) + ")").text(x + 1);
				$(".personel-input:eq(" + (x) + ")").attr('name', "personelPlanList[" + x + "].user.username");
			}
	});
		
		$(document).on("keyup", ".personel-input", function() {
			var inputValue = $(this).val();
			var target = $(this);
			target.innerHtml = "";
			if(inputValue !== "") {
				$.ajax({
					url: "/api/users/suggested-users/" + inputValue,
					target: target,
					success: function(result) {
						$("#user-suggest").text("");
						for(var i = 0; i < result.numberOfElements; i++) {
							$("#user-suggest").append("<option>" + result.content[i].username + "</option>");
						}
					}
				});
			}
		});
		