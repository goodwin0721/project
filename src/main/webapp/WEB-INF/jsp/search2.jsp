<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<title>商品搜索</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<base href = '${pageContext.request.contextPath}/p/index'>
<script src="${pageContext.request.contextPath}/js/addToCart.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
<style>
.search{
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	padding: 5px;
}
</style>
</head>

<body>
	<%@ include file="searchHead.jsp"%>
	<div id="content">
		<div id="position">
			<span>你的位置&gt;</span>		
			<a href="<%=request.getContextPath()%>/p/index">首页</a>
			<span>&gt;</span>
			<span>${keyword}</span>
		</div>
		<div id="searchDiv" class="search">		
			<input id="searchMsg" type="search" value="">
			<input id="searchBtn" type="button" value="查询">			
		</div>
		<c:if test="${isSuccess == 'false'}">
		<div style="width:80%;margin:0 10%;background:none;">
		没有找到“${keyword}”相关商品，<a href="<%=request.getContextPath()%>/p/index">返回首页</a>
		或重新搜索。
		</div>	
		</c:if>
		<div class="goodsList">		
			<c:forEach items="${goodslist}" var="goods">
				<div class="goods">
					<form class="goodsForm" id="${goods.id}">
						<img class="goodsImg" src="${pageContext.request.contextPath}/goodsPictures/${goods.pictureUrl}"/>
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
	<%@ include file="footer.jsp"%>
	
</body>


</html>