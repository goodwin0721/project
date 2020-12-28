<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>登录</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href = '${pageContext.request.contextPath}/index.jsp'>
</head>

<body>

	<div id="loginContent">
		<p id="tip">${message}</p>
		<div>
			<form action="${pageContext.request.contextPath }/login" method="post">
				<span class="loginP">用户名：</span>
				<input class="loginText" type="text" name="username"/><br>
				<span class="loginP">密码：</span>
				<input class="loginText" type="password" name="password"/><br>
				<input id="loginBtn" type="submit" value="登录"/><br>
			</form>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>


</html>