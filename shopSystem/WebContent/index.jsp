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
<!--
<script type="text/javascript">
$(document).ready(function() {
	//发送ajax请求
	$.get("<%=request.getContextPath()%>/showPageServlet?currentPage=init",function(data,status){
		// alert(data);
		var goodslist = data.split("#");
		//console.log(goodslist[0]);
		//alert(goodslist[0]);
		//下面加“'”的做法是错的，浪费了一晚上时间，留着做个警醒
		//var str = "\'"+goodslist[0] + "\'"
		//console.log(str);
		//alert(str);
		//var goods = JSON.parse(goodslist[0]);
		//console.log(goods);
		//console.log(goods.id);
		//alert(goodslist[0][0]);
		//alert(goodslist[0].id);
		
		var goods;
		goods = JSON.parse(goodslist[0]);
		console.log(goods);
		console.log(goodslist.length);
		var rightDiv = "";
		for(var i = 0;i < 10; i++){
			goods = JSON.parse(goodslist[i]);
			rightDiv +='<div class="goods">';
			rightDiv +='<form class="goodsForm" id="' + goods.id + ' " action="#" method="post">';
			rightDiv +='<img class="goodsImg" src="' + goods.pictureUrl + ' "/>';
			rightDiv +='<p class="goodsid">商品名称:' + goods.name + ' </p>';
			rightDiv +='<p class="discribe">商品描述:' + goods.described + ' </p>';
			rightDiv +='<p class="price">价格:<input value="' + goods.price + ' "></p>';
			rightDiv +='<p class="stock">库存:<input value="' + goods.stock + ' "></p>';
			rightDiv +='<input class="decrease" type="button" value="-" onclick="descrease(' + goods.id + ' )">';
			rightDiv +='<input class="pick" type="text" id="pick' + goods.id + ' " value="0" date-max="' + goods.stock + ' ">';
			rightDiv +='<input class="increase" type="button" value="+" onclick="inscrease(' + goods.id + ' ,' + goods.stock + ' )">';
			rightDiv +='<input class="addToCart" id="addToCart' + goods.id + ' " type="button" onclick="addToCart(' + goods.id + ' )" value="加入购物车">';
			rightDiv +='</form>';
			rightDiv +='</div>';	
		}
		console.log(rightDiv);
		$("#goodsDisplay").html(rightDiv);
		$("#goodsDisplay").show();			
    });
});
</script>
-->
</head>

<body>
	<%@ include file="head.jsp" %>
	<div id="leftContent">
		<ul>
			<li><a href="<%=request.getContextPath()%>/search?select=all">全部商品</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=active">生活用品</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=toy">玩具</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=stationery">文具</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=snacks">零食</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=vegetables">蔬菜</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=meat">肉类</a></li>
			<li><a href="<%=request.getContextPath()%>/search?select=fruits">水果</a></li>
		</ul>
	</div>

	<div id="rightContent">
		<div id="search">		
			<input id="searchMsg" type="search" value="">
			<input id="searchBtn" type="button" value="查询">			
		</div>
		<!--
		本来想通过链接的方式，在iframe框架插入指定的页面，但是由于iframe的高度不易设置，
		另外京东的源码也是用了<a href="blabla.jsp" target="_blank">的方式，
		不信你看view-source:https://www.jd.com/啊！所以在此还是沿用此方法，
		但是动态设置iframe的高度也是有方法的，
		参考https://blog.csdn.net/qq_40195958/article/details/89952156。
		<iframe id="goodsListFrame" name="iframe_a"></iframe>
		-->		
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