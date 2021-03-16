<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>商品分类</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<base href = '${pageContext.request.contextPath}/p/index'>
</head>
<body>
	<%@ include file="head.jsp" %>
	<br>
	<div id="content">
		<div id="classify">
			<ul>
				<li><a href="<%=request.getContextPath()%>/type/all/1" target="_blank">全部商品</a></li>
				<li><a href="<%=request.getContextPath()%>/type/active/1" target="_blank">生活用品</a></li>
				<li><a href="<%=request.getContextPath()%>/type/toy/1" target="_blank">玩具</a></li>
				<li><a href="<%=request.getContextPath()%>/type/stationery/1" target="_blank">文具</a></li>
				<li><a href="<%=request.getContextPath()%>/type/snacks/1" target="_blank">零食</a></li>
				<li><a href="<%=request.getContextPath()%>/type/vegetables/1" target="_blank">蔬菜</a></li>
				<li><a href="<%=request.getContextPath()%>/type/meat/1" target="_blank">肉类</a></li>
				<li><a href="<%=request.getContextPath()%>/type/fruits/1" target="_blank">水果</a></li>
			</ul>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>

</body>


</html>