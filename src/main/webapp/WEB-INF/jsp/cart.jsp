<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<title>购物车</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<base href = '${pageContext.request.contextPath}/index.jsp'>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/changeCartGoodsAmount.js"></script>
<script src="${pageContext.request.contextPath}/js/pay.js"></script>
</head>

<body>
	<%@ include file="head.jsp" %>
	<br>
	<div id="content">
		<div id="cart">
			<table>
				<tr>
					<th class="cGoodsMsg">商品信息</th>
					<th class="cPrice">单价</th>
					<th class="cAmount">数量</th>
					<th class="cAubtotal">小计</th>
				</tr>
				<c:forEach items="${cartList}" var="cartShow">									
				<tr>
					<td class="cGoodsMsg" id="${cartShow.goodsId}">
						<ul>
							<li><img src="goodsPictures/${cartShow.pictureUrl}"></li>
							<li>商品名称:${cartShow.goodsName}</li>
						</ul>
					</td>
					<td class="cPrice">${cartShow.price}</td>
					<td class="cAmount">					
						<input class="pick" type="text" id="pick${cartShow.goodsId}" value="${cartShow.amount}"/>
						<input class="decrease" type="button" value="-" onclick="descrease(${cartShow.goodsId})"/>
						<input class="increase" type="button" value="+" onclick="inscrease(${cartShow.goodsId})"/>
						<input class="cartBtn" id="goods${cartShow.goodsId}" type="button" onclick="changeAmount(${cartShow.goodsId})" value="修改"/>
					</td>
					<td class="cAubtotal" id="cAubtotal${cartShow.goodsId}">${cartShow.aubtotal}</td>
				</tr>
				</c:forEach>				
			</table> 
		</div>

		<div id="pay">
			<form id="payForm">
				<span id="payText">合计:</span>
				<input id="payCount" type="text" value="${total}">
				<input id="payBtn" type="button" value="结算">
			</form>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>
</html>