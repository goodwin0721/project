<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>个人中心</title>
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/style.css' />
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href='${pageContext.request.contextPath}/index.jsp'>
</head>

<body>
	<%@ include file="head.jsp"%>
	<br>
	<div id="content">
		<div id="personalMsg">
			<table>
				<tr>
					<td class="key">用户名</td>
					<td class="value">${user.username}</td>
				</tr>
				<tr>
					<td class="key">性别</td>
					<c:choose>
						<c:when test="${user.gender == 1}">
							<td class="value">女</td>
						</c:when>
						<c:when test="${user.gender == 0}">
							<td class="value">男</td>
						</c:when>
						<c:otherwise>
							<td class="value"></td>
						</c:otherwise>
					</c:choose>							
				</tr>
				<tr>
					<td class="key">出生日期</td>
					<td class="value">${user.birthdate}</td>
				</tr>
			</table>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>
</html>