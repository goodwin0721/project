<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<title>登录</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<base href = '${pageContext.request.contextPath}/p/index'>
</head>

<body>

	<div id="loginContent">
		<div>
			<form action="${pageContext.request.contextPath }/m/login" method="post">
				<span class="loginP">用户名：</span>
				<input id="usernameTx" class="loginText" type="text" name="username" required="required"/>
                <span id="isE" class="tip"></span><br>
				<span class="loginP">密码：</span>
				<input class="loginText" type="password" name="password" /><br>
				<input id="loginBtn" type="submit" value="登录"/><br>
			</form>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body> 


</html>