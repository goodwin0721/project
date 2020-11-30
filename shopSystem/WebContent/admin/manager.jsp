<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>管理商品</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<!--<base href = 'index.jsp' target = '_blank'/>-->
<base href = '${pageContext.request.contextPath}/welcome.jsp'>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/manager.js?version=1.1.0"></script>
</head>

<body>
	<div id="content">
		<div id="manager">
			<div id="addNewGoods">
				<a href="${pageContext.request.contextPath}/admin/addGoods.jsp" target = "_blank">上架新商品</a>
			</div>	
			<c:forEach items="${goodsList}" var="goods">
				<div class="goods">
					<form class="goodsForm" id="${goods.id}">
						<img class="goodsImg" src="goodsPictures/${goods.pictureUrl}"/>
						<p class="goodsid">商品名称:${goods.name}</p>
						<p class="discribe">商品描述:${goods.described}</p>
						<p class="price">价格:<input value="${goods.price}"></p>
						<p class="stock">库存:<input id="stock${goods.id}" value="${goods.stock}"></p>
						<input id="addCount${goods.id}" type="text" value="0">
						<input id="replenish${goods.id}" type="button" onclick="replenish(${goods.id})" value="补充库存">
						<input id="unShelve${goods.id}" type="button" onclick="unShelve(${goods.id},${goods.stock})" value="下架该商品">
					</form>
				</div>
			</c:forEach>			
		</div>	
	</div>
</body>
</html>