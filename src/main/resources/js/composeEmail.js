$(function() {

	$("#composeEmailClearBtn").click(function(){		
		$(this).closest('form').find("input[type=text], textarea").val("");		
		$(".msgBlock").text("");
	});
	$("#composeEmailSubmitBtn").click(function(){		
		$("#composeEmailBtnGrp").LoadingOverlay("show");
		$(".msgBlock").text("");
	});
	$("#composeEmailBtnGrp").LoadingOverlay("hide");	

	
});


