<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>联系客服</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href = '${pageContext.request.contextPath}/index.jsp'>
</head>

<body>
	<%@ include file="head.jsp" %>
	<br>
	<div id="content">
		<div id="contact">
			<table>
				<tr>
					<td class="key">电话：</td>
					<td class="value">6666666</td>
				</tr>
				<tr>
					<td class="key">QQ：</td>
					<td class="value">1156045952</td>
				</tr>
				<tr>
					<td class="key">email：</td>
					<td class="value">1156045952@qq.com</td>
				</tr>
				<tr>
					<td class="key">地址：</td>
					<td class="value">吉林省长春市朝阳区卫星路6666号</td>
				</tr>
			</table>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>

</body>


</html>