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
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href = '${pageContext.request.contextPath}/index.jsp'>
</head>

<body>
	<%@ include file="head.jsp" %>
	<br>
	<div id="content">
		<div id="classify">
			<ul>
				<li><a href="<%=request.getContextPath()%>/search?select=all" target="_blank">全部商品</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=active" target="_blank">生活用品</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=toy" target="_blank">玩具</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=stationery" target="_blank">文具</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=snacks" target="_blank">零食</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=vegetables" target="_blank">蔬菜</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=meat" target="_blank">肉类</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=fruits" target="_blank">水果</a></li>	
			</ul>
		
			
		
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>

</body>


</html>