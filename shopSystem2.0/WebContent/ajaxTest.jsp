<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	  $("button").click(function(){
	    $.get("ajaxServlet",function(data,status){    	
	    	alert(data);
	    	$("#p1").html(data);
	    });
	  });
	});

</script>
</head>
<body>
<div>
	<p id = "p1">修改前</p>
	<button id="btn1" >修改</button>
</div>
</body>
</html>