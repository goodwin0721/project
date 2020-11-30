<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<title>注册</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<base href = '${pageContext.request.contextPath}/index.jsp'>
</head>

<body>
	<div id="registerContent">
		<p id="tip">${message}</p>
		<div>
			<form action="${pageContext.request.contextPath }/register" method="post">
				<span class="registerP">用户名：</span>
				<input class="registerText" type="text" name="username"/><br>
				<span class="registerP">密码：</span>
				<input class="registerText" type="password" name="password"/><br>
				<span class="registerP">出生日期：</span>
				<input class="registerText" type="date" name="birthdate" max="2019-01-01"/><br>			
				<span class="registerP">性别：</span>
				<input class="sex" type="radio" name="sex" value="male" checked="checked"><span class="sexP">男</span><br>
				<input class="sex" type="radio" name="sex" value="female"><span class="sexP">女</span><br>
				<input class="registerBtn" type="reset"><br>
				<input class="registerBtn" id="registerBtn" type="submit" value="注册"/><br>
			</form>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>

</body>


</html>