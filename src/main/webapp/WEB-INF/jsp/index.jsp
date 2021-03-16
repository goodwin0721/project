 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta id="keywords" content="html,css,xml,xhtml,JavaScript">
<meta id="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>主页</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href = '${pageContext.request.contextPath}/welcome.jsp'>
<script src="${pageContext.request.contextPath}/js/addToCart.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
</head>

<body>
	<%@ include file="head.jsp" %>
	<div id="leftContent">
		<ul>
			<li><a href="<%=request.getContextPath()%>/type/all/1">全部商品</a></li>
			<li><a href="<%=request.getContextPath()%>/type/active/1">生活用品</a></li>
			<li><a href="<%=request.getContextPath()%>/type/toy/1">玩具</a></li>
			<li><a href="<%=request.getContextPath()%>/type/stationery/1">文具</a></li>
			<li><a href="<%=request.getContextPath()%>/type/snacks/1">零食</a></li>
			<li><a href="<%=request.getContextPath()%>/type/vegetables/1">蔬菜</a></li>
			<li><a href="<%=request.getContextPath()%>/type/meat/1">肉类</a></li>
			<li><a href="<%=request.getContextPath()%>/type/fruits/1">水果</a></li>
		</ul>
	</div>

	<div id="rightContent">
		<div id="search">		
			<input id="searchMsg" type="search" value="">
			<input id="searchBtn" type="button" value="查询">			
		</div>
		<div id="goodsDisplay">
			<c:forEach items="${hotSale}" var="goods">
				<div class="goods">
					<form class="goodsForm" id="${goods.id}">
						<img class="goodsImg" src="goodsPictures/${goods.pictureUrl}"/>
						<p class="goodsid">商品名称:${goods.name}</p>
						<p class="discribe">商品描述:${goods.described}</p>
						<p class="price">价格:<input value="${goods.price}"></p>
						<p class="stock">库存:<input value="${goods.stock}"></p>
						<input class="decrease" type="button" value="-" onclick="descrease(${goods.id})">
						<input class="pick" type="text" id="pick${goods.id}" value="0" date-max="${goods.stock}">
						<input class="increase" type="button" value="+" onclick="inscrease(${goods.id},${goods.stock})">
						<input class="addToCart" id="addToCart${goods.id}" type="button" onclick="addToCart(${goods.id},${goods.price})" value="加入购物车">
					</form>
				</div>
			</c:forEach>	
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>


</html>