$(document).ready(function(){
	$("#payBtn").click(function(){
		if($("#payCount").val() != "0"){
			var url = "/"+window.location.pathname.split("/")[1] + "/pay";
			location.href=url;			
		}			
	});
});